package io.tiklab.teamwire.home.insight.service;


import io.tiklab.teamwire.home.insight.model.ProjectOperateReport;
import io.tiklab.teamwire.home.insight.model.ProjectOperateReportQuery;
import io.tiklab.teamwire.home.insight.model.WorkItemCountQuery;
import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.teamwire.project.project.model.ProjectQuery;
import io.tiklab.teamwire.project.project.service.ProjectService;
import io.tiklab.teamwire.projectset.model.ProjectSet;
import io.tiklab.teamwire.projectset.service.ProjectSetService;
import io.tiklab.teamwire.sprint.model.Sprint;
import io.tiklab.teamwire.sprint.service.SprintService;
import io.tiklab.teamwire.workitem.model.WorkItem;
import io.tiklab.teamwire.workitem.model.WorkItemQuery;
import io.tiklab.teamwire.workitem.model.WorkType;
import io.tiklab.teamwire.workitem.service.WorkTypeService;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.flow.statenode.service.StateNodeService;
import io.tiklab.teamwire.home.insight.dao.ProjectInsightReportDao;
import io.tiklab.teamwire.workitem.service.WorkItemService;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * 仪表盘统计类目服务
 */
@Service
public class ProjectInsightReportServiceImpl implements ProjectInsightReportService {



    @Autowired
    StateNodeService stateNodeService;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    WorkTypeService workTypeService;

    @Autowired
    DmUserService dmUserService;
    @Autowired
    ProjectService projectService;

    @Autowired
    SprintService sprintService;
    @Autowired
    ProjectSetService projectSetService;
    @Autowired
    ProjectInsightReportDao projectInsightReportDao;


    /**
     * 统计某个项目集下所有项目的数据
     * @param projectSetId
     * @return
     */
    @Override
    public List<ProjectOperateReport> statisticsProjectOperateList(String projectSetId) {
        //查找项目集下所有项目
        ProjectQuery projectQuery = new ProjectQuery();
        projectQuery.setProjectSetId(projectSetId);
        List<Project> projectList = projectService.findProjectList(projectQuery);

        List<ProjectOperateReport> projectOperateReports = new ArrayList<ProjectOperateReport>();
        for (Project project : projectList) {
            ProjectOperateReportQuery projectOperateReportQuery = new ProjectOperateReportQuery();
            projectOperateReportQuery.setProjectId(project.getId());
            ProjectOperateReport projectOperateReport = statisticsProjectOperate(projectOperateReportQuery);
            projectOperateReports.add(projectOperateReport);
        }
        return projectOperateReports;

    }

    /**
     * 统计项目集下所有项目的成员数量
     * @param projectSetId
     * @return
     */
    public Map<String, Object> statisticsProjectUserCount(String projectSetId) {
        Map<String, Object> projectSetUserCount = new HashMap<>();
        ProjectQuery projectQuery = new ProjectQuery();
        projectQuery.setProjectSetId(projectSetId);

        List<Project> projectList = projectService.findProjectList(projectQuery);
        List<Map<String, Object>> projectUserCountList = new ArrayList<Map<String, Object>>();
        for (Project project : projectList) {
            String id = project.getId();
            DmUserQuery dmUserQuery = new DmUserQuery();
            dmUserQuery.setDomainId(id);
            List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
            int size = 0;
            Map<String, Object> projectCount = new HashMap<String, Object>();

            if(dmUserList != null && dmUserList.size() > 0){
                size = dmUserList.size();
            }
            projectCount.put("project", project);
            projectCount.put("count", size);
            projectUserCountList.add(projectCount);
        }
        projectUserCountList.sort((s2,s1)-> {
            return s1.get("count").toString().compareTo(s2.get("count").toString());
        });

        ProjectSet projectSet = projectSetService.findProjectSet(projectSetId);
        projectSetUserCount.put("projectSet", projectSet);
        projectSetUserCount.put("projectUserCountList", projectUserCountList);
        return projectSetUserCount;
    }


    /**
     * 统计项目集下所有项目的每个类型的事项数量
     * @param projectSetId
     * @return
     */
    public Map<String, Object> statisticsProjectWorkItemCount(String projectSetId) {
        ProjectQuery projectQuery = new ProjectQuery();
        projectQuery.setProjectSetId(projectSetId);
        List<Project> projectList = projectService.findProjectList(projectQuery);

        Map<String, Object> workItemTypeCountList = new HashMap<String, Object>();
        ArrayList<String> types = new ArrayList<>();
        Map<String, Object> workTypeCount = new HashMap<String, Object>();
        ArrayList<Object> integers = new ArrayList<>();

        List<WorkType> allWorkType = workTypeService.findAllWorkType();
        //安装事项类型循环查找每个类型，每个项目下的事项数量
        for (WorkType workType : allWorkType) {
            String workTypeId = workType.getId();
            String code = workType.getCode();
            types.add(code);

            List<Project> project1 = new ArrayList<Project>();
            ArrayList<Integer> integers1 = new ArrayList<>();
            for (Project project : projectList) {
                String id = project.getId();
                WorkItemQuery workItemQuery = new WorkItemQuery();
                workItemQuery.setProjectId(id);
                workItemQuery.setWorkTypeId(workTypeId);
                List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);
                int count = workItemList.size();
                integers1.add(count);
                project1.add(project);
            }
            integers.add(integers1);
            workTypeCount.put("project",project1);

        }
        workTypeCount.put("countList", integers);
        workItemTypeCountList.put("types", types);
        workItemTypeCountList.put("project", workTypeCount);

        return workItemTypeCountList;
    }

    /**
     * 统计某段时间，以天，周，月，季，年为单位新增事项的数据
     * @param workItemCountQuery
     * @return
     */
    @Override
    public Map<String, Object> statisticsNewWorkItemCountList(WorkItemCountQuery workItemCountQuery) {
        String projectSetId = workItemCountQuery.getProjectSetId();
        ProjectQuery projectQuery = new ProjectQuery();
        projectQuery.setProjectSetId(projectSetId);

        String projectId = workItemCountQuery.getProjectId();
        projectQuery.setProjectId(projectId);
        List<Project> projectList = projectService.findProjectList(projectQuery);

        //安照统计单位（日，周，月...）计算时间点
        List<String> daylist = getDaylist(workItemCountQuery);
        List<Object> countList = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        for (Project project : projectList) {
            Map<String, Object> projectCount = new HashMap<String, Object>();
            workItemCountQuery.setProjectId(project.getId());
            List<Integer> projectCountList = statisticsProjectNewWorkItemCount(workItemCountQuery, daylist);
            projectCount.put("project", project);
            projectCount.put("countList", projectCountList);
            countList.add(projectCount);
        }

        paramMap.put("dateList", daylist);
        paramMap.put("projectCountList", countList);

        return paramMap;
    }

    /**
     * 统计某段时间，以天，周，月，季，年为单位完成事项的数据
     * @param workItemCountQuery
     * @return
     */
    public Map<String, Object> statisticsEndWorkItemCountList(WorkItemCountQuery workItemCountQuery) {
        String projectSetId = workItemCountQuery.getProjectSetId();
        ProjectQuery projectQuery = new ProjectQuery();
        projectQuery.setProjectSetId(projectSetId);

        String projectId = workItemCountQuery.getProjectId();
        projectQuery.setProjectId(projectId);
        List<Project> projectList = projectService.findProjectList(projectQuery);

        List<String> daylist = getDaylist(workItemCountQuery);
        List<Object> countList = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        for (Project project : projectList) {
            Map<String, Object> projectCount = new HashMap<String, Object>();
            workItemCountQuery.setProjectId(project.getId());
            List<Integer> projectCountList = statisticsProjectEndWorkItemCount(workItemCountQuery, daylist);
            projectCount.put("project", project);
            projectCount.put("countList", projectCountList);
            countList.add(projectCount);
        }

        paramMap.put("dateList", daylist);
        paramMap.put("projectCountList", countList);

        return paramMap;
    }

    /**
     * 统计某段时间，以天，周，月，季，年为单位累计新增事项的数据
     * @param workItemCountQuery
     * @return
     */
    public Map<String, Object> statisticsSprintWorkItemTotalCountList(WorkItemCountQuery workItemCountQuery) {
        String sprintId = workItemCountQuery.getSprintId();
        List<String> daylist = getDaylist(workItemCountQuery);
        Map<String, Object> sprintCount = new HashMap<String, Object>();
        workItemCountQuery.setSprintId(sprintId);
        Sprint sprint = sprintService.findSprint(sprintId);
        List<Integer> projectCountList = statisticsSprintWorkItemTotalCount(workItemCountQuery, daylist);
        sprintCount.put("sprint", sprint);
        sprintCount.put("countList", projectCountList);
        sprintCount.put("dateList", daylist);
        return sprintCount;
    }

    @Override
    public Map<String, Object> statisticsWorkItemTotalCountList(WorkItemCountQuery workItemCountQuery) {
        String projectSetId = workItemCountQuery.getProjectSetId();
        ProjectQuery projectQuery = new ProjectQuery();
        projectQuery.setProjectSetId(projectSetId);

        String projectId = workItemCountQuery.getProjectId();
        projectQuery.setProjectId(projectId);
        List<Project> projectList = projectService.findProjectList(projectQuery);

        List<String> daylist = getDaylist(workItemCountQuery);
        List<Object> countList = new ArrayList<>();
        for (Project project : projectList) {
            Map<String, Object> projectCount = new HashMap<String, Object>();
            workItemCountQuery.setProjectId(project.getId());
            List<Integer> projectCountList = statisticsProjectWorkItemTotalCount(workItemCountQuery, daylist);
            projectCount.put("project", project);
            projectCount.put("countList", projectCountList);
            countList.add(projectCount);
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("dateList", daylist);
        paramMap.put("projectCountList", countList);

        return paramMap;
    }

    /**
     * 统计某段时间，以天，周，月，季，年为单位累计完成事项的数据
     * @param workItemCountQuery
     * @return
     */
    public Map<String, Object> statisticsEndWorkItemTotalCountList(WorkItemCountQuery workItemCountQuery) {
        String projectSetId = workItemCountQuery.getProjectSetId();
        ProjectQuery projectQuery = new ProjectQuery();
        projectQuery.setProjectSetId(projectSetId);

        String projectId = workItemCountQuery.getProjectId();
        projectQuery.setProjectId(projectId);
        List<Project> projectList = projectService.findProjectList(projectQuery);

        List<String> daylist = getDaylist(workItemCountQuery);
        List<Object> countList = new ArrayList<>();
        for (Project project : projectList) {
            Map<String, Object> projectCount = new HashMap<String, Object>();
            workItemCountQuery.setProjectId(project.getId());
            List<Integer> projectCountList = statisticsEndProjectWorkItemTotalCount(workItemCountQuery, daylist);
            projectCount.put("project", project);
            projectCount.put("countList", projectCountList);
            countList.add(projectCount);
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("dateList", daylist);
        paramMap.put("projectCountList", countList);

        return paramMap;
    }

    @Override
    public Map<String, Object> statisticsSprintEndWorkItemTotalCountList(WorkItemCountQuery workItemCountQuery) {
        String sprintId = workItemCountQuery.getSprintId();
        List<String> daylist = getDaylist(workItemCountQuery);
        Map<String, Object> sprintCount = new HashMap<String, Object>();
        workItemCountQuery.setSprintId(sprintId);
        Sprint sprint = sprintService.findSprint(sprintId);
        List<Integer> projectCountList = statisticsEndSprintWorkItemTotalCount(workItemCountQuery, daylist);
        sprintCount.put("sprint", sprint);
        sprintCount.put("countList", projectCountList);
        sprintCount.put("dateList", daylist);
        return sprintCount;
    }

    /**
     * 统计某段时间进行中的事项的数据
     * @param workItemCountQuery
     * @return
     */
    public Map<String, Object> statisticsProcessWorkItemCountList(WorkItemCountQuery workItemCountQuery) {
        String projectSetId = workItemCountQuery.getProjectSetId();
        ProjectQuery projectQuery = new ProjectQuery();
        projectQuery.setProjectSetId(projectSetId);
        List<Project> projectList = projectService.findProjectList(projectQuery);

        List<String> daylist = getDaylist(workItemCountQuery);
        List<Object> countList = new ArrayList<>();
        for (Project project : projectList) {
            Map<String, Object> projectCount = new HashMap<String, Object>();
            List<Integer> projectCountList = statisticsProjectProcessWorkItemCount(project.getId(), daylist);
            projectCount.put("project", project);
            projectCount.put("countList", projectCountList);
            countList.add(projectCount);
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("dateList", daylist);
        paramMap.put("projectCountList", countList);

        return paramMap;
    }

    /**
     * 统计某段时间未修改的缺陷的数据
     * @param workItemCountQuery
     * @return
     */
    public Map<String, Object> statisticsProcessBugCountList(WorkItemCountQuery workItemCountQuery) {
        String projectSetId = workItemCountQuery.getProjectSetId();
        ProjectQuery projectQuery = new ProjectQuery();
        projectQuery.setProjectSetId(projectSetId);
        List<Project> projectList = projectService.findProjectList(projectQuery);

        List<String> daylist = getDaylist(workItemCountQuery);
        List<Object> countList = new ArrayList<>();
        for (Project project : projectList) {
            Map<String, Object> projectCount = new HashMap<String, Object>();
            List<Integer> projectCountList = statisticsProjectProcessBugCount(project.getId(), daylist);
            projectCount.put("project", project);
            projectCount.put("countList", projectCountList);
            countList.add(projectCount);
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("dateList", daylist);
        paramMap.put("bugCountList", countList);

        return paramMap;
    }


    /**
     * 按照时间统计日期，统计某段时间新增事项的数据
     * @param workItemCountQuery
     * @param dayList
     * @return
     */
    public List<Integer> statisticsProjectNewWorkItemCount(WorkItemCountQuery workItemCountQuery, List<String> dayList) {
        int size = dayList.size();
        List<Integer> countList = new ArrayList<>();
        for (int i= 0; i<size-1; i++ ) {
            String startTime = dayList.get(i);
            String endTime = dayList.get(i+1);
            int sum = projectInsightReportDao.statisticsProjectNewWorkItemCount(workItemCountQuery, startTime, endTime);
            countList.add(sum);
        }
        return countList;
    }

    /**
     * 按照时间统计日期，统计某段时间新增事项的数据
     * @param workItemCountQuery
     * @param dayList
     * @return
     */
    public List<Integer> statisticsProjectEndWorkItemCount(WorkItemCountQuery workItemCountQuery, List<String> dayList) {
        int size = dayList.size();
        List<Integer> countList = new ArrayList<>();
        for (int i= 0; i<size-1; i++ ) {
            String startTime = dayList.get(i);
            String endTime = dayList.get(i+1);
            int sum = projectInsightReportDao.statisticsProjectEndWorkItemCount(workItemCountQuery, startTime, endTime);
            countList.add(sum);
        }
        return countList;
    }

    /**
     * 按照时间统计日期，统计某段时间累计新增事项的数据
     * @param workItemCountQuery
     * @param dayList
     * @return
     */
    public List<Integer> statisticsSprintWorkItemTotalCount(WorkItemCountQuery workItemCountQuery, List<String> dayList) {
        int size = dayList.size();
        List<Integer> countList = new ArrayList<>();

        for (int i= 0; i<size-1; i++ ) {
            String startTime = dayList.get(i);
            String endTime = dayList.get(i+1);
            int sum = projectInsightReportDao.statisticsSprintWorkItemTotalCount(workItemCountQuery, startTime, endTime);
            countList.add(sum);
        }
        return countList;
    }

    /**
     * 按照时间统计日期，统计某段时间累计新增事项的数据
     * @param workItemCountQuery
     * @param dayList
     * @return
     */
    public List<Integer> statisticsProjectWorkItemTotalCount(WorkItemCountQuery workItemCountQuery, List<String> dayList) {
        int size = dayList.size();
        List<Integer> countList = new ArrayList<>();

        for (int i= 0; i<size-1; i++ ) {
            String startTime = dayList.get(i);
            String endTime = dayList.get(i+1);
            int sum = projectInsightReportDao.statisticsProjectWorkItemTotalCount(workItemCountQuery, startTime, endTime);
            countList.add(sum);
        }
        return countList;
    }

    /**
     * 按照时间统计日期，统计某段时间累计结束事项的数据
     * @param workItemCountQuery
     * @param dayList
     * @return
     */
    public List<Integer> statisticsEndProjectWorkItemTotalCount(WorkItemCountQuery workItemCountQuery, List<String> dayList) {
        int size = dayList.size();
        List<Integer> countList = new ArrayList<>();

        for (int i= 0; i<size-1; i++ ) {
            String startTime = dayList.get(i);
            String endTime = dayList.get(i+1);
            int sum = projectInsightReportDao.statisticsEndProjectWorkItemTotalCount(workItemCountQuery, startTime, endTime);
            countList.add(sum);
        }
        return countList;
    }

    /**
     * 按照时间统计日期，统计某段时间累计结束事项的数据
     * @param workItemCountQuery
     * @param dayList
     * @return
     */
    public List<Integer> statisticsEndSprintWorkItemTotalCount(WorkItemCountQuery workItemCountQuery, List<String> dayList) {
        int size = dayList.size();
        List<Integer> countList = new ArrayList<>();

        for (int i= 0; i<size-1; i++ ) {
            String startTime = dayList.get(i);
            String endTime = dayList.get(i+1);
            int sum = projectInsightReportDao.statisticsEndSprintWorkItemTotalCount(workItemCountQuery, startTime, endTime);
            countList.add(sum);
        }
        return countList;
    }
    /**
     * 按照时间统计日期，统计某段时间累计进行中事项的数据
     * @param projectId
     * @param dayList
     * @return
     */
    public List<Integer> statisticsProjectProcessWorkItemCount(String projectId, List<String> dayList) {
        int size = dayList.size();
        List<Integer> countList = new ArrayList<>();

        for (int i= 0; i<size-1; i++ ) {
            String startTime = dayList.get(i);
            String endTime = dayList.get(i+1);
            int sum = projectInsightReportDao.statisticsProjectProcessWorkItemCount(projectId, startTime, endTime);
            countList.add(sum);
        }
        return countList;
    }

    /**
     * 按照时间统计日期，统计某段时间累计进行中事项的缺陷
     * @param projectId
     * @param dayList
     * @return
     */
    public List<Integer> statisticsProjectProcessBugCount(String projectId, List<String> dayList) {
        int size = dayList.size();
        List<Integer> countList = new ArrayList<>();

        for (int i= 0; i<size-1; i++ ) {
            String startTime = dayList.get(i);
            String endTime = dayList.get(i+1);
            int sum = projectInsightReportDao.statisticsProjectProcessBugCount(projectId, startTime, endTime);
            countList.add(sum);
        }
        return countList;
    }

    /**
     * 根据单位时间分割出一段时间的时间点，用于统计
     * @param workItemCountQuery
     * @return
     */
    public List<String> getDaylist(WorkItemCountQuery workItemCountQuery){
        String startDate = workItemCountQuery.getStartDate();
        String endDate = workItemCountQuery.getEndDate();
        String cellTime = workItemCountQuery.getCellTime();
        int integer = 1;
        switch (cellTime){
            case "day":
                integer = 1;
                break;
            case "week":
                integer = 7;
                break;
            case "month":
                integer = 30;
                break;
            case "quarter":
                integer = 90;
                break;
            default:
                break;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> dateList = new ArrayList<>();
        try {
            Date dateOne = sdf.parse(startDate);
            Date dateTwo = sdf.parse(endDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateOne);
            dateList.add(startDate);

            while (dateTwo.after(calendar.getTime())) {
                calendar.add(Calendar.DAY_OF_MONTH, integer);
                dateList.add(sdf.format(calendar.getTime()));
            }
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        return dateList;
    }


    /**
     * 统计项目的相关数据
     * @param projectOperateReportQuery
     * @return
     */
    @Override
    public ProjectOperateReport statisticsProjectOperate(@NotNull @Valid ProjectOperateReportQuery projectOperateReportQuery) {
        //查找项目信息
        ProjectOperateReport projectOperateReport = new ProjectOperateReport();
        String projectId = projectOperateReportQuery.getProjectId();
        Project project = projectService.findProject(projectId);
        projectOperateReport.setProjectName(project.getProjectName());
        projectOperateReport.setProjectId(projectId);

        WorkItemCountQuery workItemCountQuery = new WorkItemCountQuery();
        workItemCountQuery.setProjectId(projectId);

        //新增事项统计
        Integer newWorkItemCount = projectInsightReportDao.findAllCountByType(workItemCountQuery);
        projectOperateReport.setNewWorkItemCount(newWorkItemCount);

        //已结束事项统计
        Integer endWorkItem = projectInsightReportDao.findStatusCountByType(workItemCountQuery, "DONE");
        projectOperateReport.setEndWorkItemCount(endWorkItem);

        //未结束事项统计
        Integer noEndWorkItem = projectInsightReportDao.findNoEndCountByType(workItemCountQuery);
        projectOperateReport.setNoEndWorkItemCount(noEndWorkItem);

        //逾期事项统计
        Integer overdueWorkItemCount = projectInsightReportDao.findOverduedCountByType(workItemCountQuery);
        projectOperateReport.setOverdueWorkItemCount(overdueWorkItemCount);

        //事项完成周期
        float workItemEndAveragePeriod = findAllEndWorkItemTime(projectId, "all");
        projectOperateReport.setWorkItemEndAveragePeriod(workItemEndAveragePeriod);

        //新增需求统计
        workItemCountQuery.setWorkItemTypeCode("demand");
        Integer newDemandCount = projectInsightReportDao.findAllCountByType(workItemCountQuery);
        projectOperateReport.setNewDemand(newDemandCount);

        //已结束需求统计
        Integer endDemand = projectInsightReportDao.findStatusCountByType(workItemCountQuery, "DONE");
        projectOperateReport.setEndDemandCount(endDemand);

        //未结束需求统计
        Integer noEndDemand = projectInsightReportDao.findNoEndCountByType(workItemCountQuery);
        projectOperateReport.setNoEndDemandCount(noEndDemand);

        //逾期需求统计
        Integer overdueDemandCount = projectInsightReportDao.findOverduedCountByType(workItemCountQuery);
        projectOperateReport.setOverdueDemandCount(overdueDemandCount);

        //需求完成周期
        float demandEndAveragePeriod = findAllEndWorkItemTime(projectId, "demand");
        projectOperateReport.setDemandEndAveragePeriod(demandEndAveragePeriod);

        //新增任务统计
        workItemCountQuery.setWorkItemTypeCode("task");
        Integer newTaskCount = projectInsightReportDao.findAllCountByType(workItemCountQuery);
        projectOperateReport.setNewDemand(newTaskCount);

        //已结束任务统计
        Integer endTask = projectInsightReportDao.findStatusCountByType(workItemCountQuery, "DONE");
        projectOperateReport.setEndTaskCount(endTask);

        //未结束任务统计
        Integer noEndTask = projectInsightReportDao.findNoEndCountByType(workItemCountQuery);
        projectOperateReport.setNoEndTaskCount(noEndTask);

        //逾期任务统计
        Integer overdueTaskCount = projectInsightReportDao.findOverduedCountByType(workItemCountQuery);
        projectOperateReport.setOverdueTaskCount(overdueTaskCount);

        //任务完成周期
        float taskEndAveragePeriod = findAllEndWorkItemTime(projectId, "task");
        projectOperateReport.setTaskEndAveragePeriod(taskEndAveragePeriod);

        //新增缺陷统计
        workItemCountQuery.setWorkItemTypeCode("defect");
        Integer newBugCount = projectInsightReportDao.findAllCountByType(workItemCountQuery);
        projectOperateReport.setNewBug(newBugCount);

        //已结束缺陷统计
        Integer endBugCount = projectInsightReportDao.findStatusCountByType(workItemCountQuery, "DONE");
        projectOperateReport.setEndBugCount(endBugCount);

        //未结束缺陷统计
        Integer noEndBugCount = projectInsightReportDao.findNoEndCountByType(workItemCountQuery);
        projectOperateReport.setNoEndBugCount(noEndBugCount);

        //逾期缺陷统计
        Integer overdueBugCount = projectInsightReportDao.findOverduedCountByType(workItemCountQuery);
        projectOperateReport.setOverdueBugCount(overdueBugCount);

        //缺陷完成周期
        float bugEndAveragePeriod = findAllEndWorkItemTime(projectId, "defect");
        projectOperateReport.setBugEndAveragePeriod(bugEndAveragePeriod);

        //项目进度
        int all = newBugCount + newWorkItemCount;
        int end = endBugCount + endWorkItem;
        float precent = 0;
        if(all != 0){
            precent = end / all;
        }

        projectOperateReport.setPrecent(precent);

        return projectOperateReport;
    }

    /**
     * 事项的解决平均时间周期
     * @param projectId
     * @param workTypeCode
     * @return
     */
    public float findAllEndWorkItemTime(String projectId, String workTypeCode) {
        //查找已完成事项
        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setProjectId(projectId);
        workItemQuery.setWorkStatusCode("DONE");
        if(!workTypeCode.equals("all")){
            workItemQuery.setWorkTypeCode(workTypeCode);
        }
        List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);

        //计算完成事项的所有时间
        float allDiffDay = 0;
        for (WorkItem workItem : workItemList) {
            String actualEndTime = workItem.getActualEndTime();
            String buildTime = workItem.getBuildTime();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1 = null;
            Date d2 = null;
            try {
                d1 = format.parse(buildTime);
                d2 = format.parse(actualEndTime);
                long diff = d2.getTime() - d1.getTime();
                float diffDays = diff / (24 * 60 * 60 * 1000);
                allDiffDay = allDiffDay + diffDays;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int size = workItemList.size();
        float average = new Float(0.0);
        if(size > 0){
            average = allDiffDay / size;
        }

        return average;
    }


    /**
     * 按照状态统计事项数量
     * @param workItemCountQuery
     * @return
     */
    public Map<String, Integer> statisticsWorkItemStatusCount(WorkItemCountQuery workItemCountQuery){
        Map<String, Integer> workItemStatusCount = projectInsightReportDao.statisticsWorkItemStatusCount(workItemCountQuery);
        return  workItemStatusCount;
    }

    /**
     * 每天新增，完成，剩余事项统计
     * @param workItemCountQuery
     * @return、
     * 0
     */
    public Map<String, Object> statisticsDayWorkItemCount(WorkItemCountQuery workItemCountQuery) {
        HashMap<String, Object> dayWorkItem = new HashMap<>();
        List<String> dayList = getDaylist(workItemCountQuery);
        int size = dayList.size();
        List<Map<String, Integer>> countList = new ArrayList<Map<String, Integer>>();
        for (int i= 0; i<size-1; i++ ) {
            Map<String, Integer> everyCount = projectInsightReportDao.statisticsWorkItemTrend(workItemCountQuery);
            countList.add(everyCount);
        }
        dayWorkItem.put("date", dayList);
        dayWorkItem.put("conntList", countList);

        return dayWorkItem;
    }

    /**
     * 统计某个项目下，某个成员负责的事项对比
     * @param workItemCountQuery
     * @return
     */
    public  Map<String, Object> statisticsUserWorkItemCount(WorkItemCountQuery workItemCountQuery) {
        String projectId = workItemCountQuery.getProjectId();
        ArrayList<Map<String, Object>> userCount = new ArrayList<>();

        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setDomainId(projectId);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
        for (DmUser dmUser : dmUserList) {
            HashMap<String, Object> userWorkItemCount = new HashMap<>();
            String userId = dmUser.getUser().getId();
            Map<String, Integer> workItemTypeCount = projectInsightReportDao.statisticsUserWorkItemCount(projectId, userId);
            userWorkItemCount.put("user", dmUser.getUser());
            userWorkItemCount.put("workItemTypeCount", workItemTypeCount);
            userCount.add(userWorkItemCount);
        }
        Map<String, Object> projectUserCount = new HashMap<>();
        Project project = projectService.findProject(projectId);
        projectUserCount.put("project", project);
        projectUserCount.put("userCount", userCount);
        return projectUserCount;
    }


}
