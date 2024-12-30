package io.tiklab.kanass.home.insight.service;


import io.tiklab.core.page.Pagination;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.home.insight.model.ProjectOperateReport;
import io.tiklab.kanass.home.insight.model.ProjectOperateReportQuery;
import io.tiklab.kanass.home.insight.model.WorkItemCountQuery;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.model.ProjectQuery;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.kanass.projectset.model.ProjectSet;
import io.tiklab.kanass.projectset.service.ProjectSetService;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.kanass.sprint.service.SprintService;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.model.WorkItemQuery;
import io.tiklab.kanass.workitem.model.WorkType;
import io.tiklab.kanass.workitem.service.WorkTypeService;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.flow.statenode.service.StateNodeService;
import io.tiklab.kanass.home.insight.dao.ProjectInsightReportDao;
import io.tiklab.kanass.workitem.service.WorkItemService;
import io.tiklab.todotask.todo.model.Task;
import io.tiklab.todotask.todo.model.TaskQuery;
import io.tiklab.todotask.todo.service.TaskService;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    TaskService taskService;

    /**
     * 统计某个项目集下所有项目的数据
     *
     * @param projectSetId
     * @return
     */
    @Override
    public HashMap<String, Object> statisticsProjectOperateList(String projectSetId) {
        HashMap<String, Object> projectOperateMap = new HashMap<>();
        ProjectSet projectSet = projectSetService.findProjectSet(projectSetId);
        projectOperateMap.put("projectSet", projectSet);
        if(projectSet != null){
            ProjectQuery projectQuery = new ProjectQuery();
            projectQuery.setProjectSetId(projectSetId);
            List<Project> projectList = projectService.findProjectList(projectQuery);
            List<ProjectOperateReport> projectOperateReports = new ArrayList<ProjectOperateReport>();
            if(projectList.size() > 0){
                for (Project project : projectList) {
                    ProjectOperateReportQuery projectOperateReportQuery = new ProjectOperateReportQuery();
                    projectOperateReportQuery.setProjectId(project.getId());
                    ProjectOperateReport projectOperateReport = statisticsProjectOperate(projectOperateReportQuery);
                    projectOperateReports.add(projectOperateReport);
                }
                projectOperateMap.put("projectOperateReportList", projectOperateReports);
            }else {
                projectOperateMap.put("projectOperateReportList", projectOperateReports);
            }
        }else {
            projectOperateMap.put("projectOperateReportList", null);
        }
        return projectOperateMap;

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
        ProjectSet projectSet = projectSetService.findProjectSet(projectSetId);
        Map<String, Object> workItemTypeCountList = new HashMap<String, Object>();
        workItemTypeCountList.put("projectSet", projectSet);
        if(projectSet != null){
            // 查找项目
            ProjectQuery projectQuery = new ProjectQuery();
            projectQuery.setProjectSetId(projectSetId);
            List<Project> projectList = projectService.findProjectList(projectQuery);


            if(projectList.size() <= 0) {
                workItemTypeCountList.put("project", null);
                return workItemTypeCountList;
            }

            ArrayList<String> types = new ArrayList<>();
            Map<String, Object> workTypeCount = new HashMap<String, Object>();
            ArrayList<Object> integers = new ArrayList<>();

            String projectIds = "(" + projectList.stream().map(item -> "'" + item.getId() + "'").collect(Collectors.joining(", ")) + ")";


            List<Map<String, Object>> projectWorkItemCount = projectService.findProjectWorkItemType(projectIds);
            List<WorkType> allWorkType = workTypeService.findAllWorkType();
            for (WorkType workType : allWorkType) {
                String code = workType.getCode();
                types.add(code);

                List<Project> project1 = new ArrayList<Project>();
                ArrayList<Integer> integers1 = new ArrayList<>();
                for (Project project : projectList) {
                    String id = project.getId();
                    List<Map<String, Object>> doneList = projectWorkItemCount.stream().filter(workItem -> (workItem.get("project_id").equals(id) && workItem.get("work_type_code").equals(code))).collect(Collectors.toList());
                    integers1.add(doneList.size());
                    project1.add(project);
                }
                integers.add(integers1);
                workTypeCount.put("project",project1);

            }

            workTypeCount.put("countList", integers);
            workItemTypeCountList.put("types", types);
            workItemTypeCountList.put("project", workTypeCount);
        }


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
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if(projectList.size() > 0){
            //安照统计单位（日，周，月...）计算时间点
            List<String> daylist = getDaylist(workItemCountQuery);
            List<Object> countList = new ArrayList<>();

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
        }else {
            paramMap.put("dateList", null);
            paramMap.put("projectCountList", null);
        }
        return paramMap;
    }


    /**
     * 统计某段时间，以天，周，月，季，年为单位完成事项的数据
     * 备注9.28修改
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
        List<Object> countList = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if(projectList.size() > 0){
            List<String> dayList = getDaylist(workItemCountQuery);
            if(projectList.size() > 0){
                List<String> projectIds = projectList.stream().map(item -> item.getId()).collect(Collectors.toList());
                workItemCountQuery.setProjectIds(projectIds);
                List<Map<String, Object>> endWorkItemList = projectInsightReportDao.statisticsProjectEndWorkItem(workItemCountQuery, dayList);
                for (Project project : projectList) {
                    Map<String, Object> projectCount = new HashMap<String, Object>();
                    int size = dayList.size();
                    List<Integer> projectCountList = new ArrayList<>();
                    for (int i= 0; i< size-1; i++ ) {
                        String start= dayList.get(i);
                        String end = dayList.get(i+1);
                        List<Map<String, Object>> collect = endWorkItemList.stream().filter(work -> (work.get("actual_end_time").
                                        toString().compareTo(start) >= 0 && work.get("actual_end_time").toString().compareTo(end) <= 0 &&
                                        work.get("project_id").equals(project.getId())))
                                .collect(Collectors.toList());
                        projectCountList.add(collect.size());
                    }
                    projectCount.put("project", project);
                    projectCount.put("countList", projectCountList);
                    countList.add(projectCount);
                }
            }
            paramMap.put("dateList", dayList);
            paramMap.put("projectCountList", countList);
        }else {
            paramMap.put("dateList", null);
            paramMap.put("projectCountList", null);
        }


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

    /**
     * 统计某段时间，以天，周，月，季，年为单位累计新增事项的数据
     * @param workItemCountQuery
     * @return
     */
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

    /**
     * 统计某段时间，以天，周，月，季，年为单位累计完成事项的数据
     * @param workItemCountQuery
     * @return
     */
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
    public Map<String, Object> statisticsWorkItemStatusCount(WorkItemCountQuery workItemCountQuery){
        String projectId = workItemCountQuery.getProjectId();
        Project project = projectService.findProject(projectId);
        Map<String, Object> workItemStatusCount = new HashMap<>();
        if(project != null){
            workItemStatusCount = projectInsightReportDao.statisticsWorkItemStatusCount(workItemCountQuery);
            workItemStatusCount.put("project", project);
        }else {
            workItemStatusCount.put("project", project);
        }
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
        String projectId = workItemCountQuery.getProjectId();
        Project project = projectService.findProject(projectId);
        if(project != null){
            List<String> dayList = getDaylist(workItemCountQuery);
            List<Map<String, Integer>> countList = projectInsightReportDao.findProjectBurnDowmOnTime(workItemCountQuery, dayList);
            dayWorkItem.put("date", dayList);
            dayWorkItem.put("countList", countList);
            dayWorkItem.put("project", project);
        }else {
            dayWorkItem.put("date", null);
            dayWorkItem.put("countList", null);
            dayWorkItem.put("project", project);
        }
        return dayWorkItem;
    }

    /**
     * 获取前7天的日期数组
     * @return
     */
    public List<String> getFirstSevenDay(){
        List<String> dateList = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String endDate = sdf.format(date);

        Calendar calendar = Calendar.getInstance();
        // 将日历时间设置为当前时间
        calendar.setTime(new Date());
        // 减去7天
        calendar.add(Calendar.DATE, -7);
        // 使用Calendar的getTime()方法获取7天前的Date对象
        Date sevenDaysAgo = calendar.getTime();
        // 使用SimpleDateFormat的format()方法将Date对象格式化为字符串
        String startDate = sdf.format(sevenDaysAgo);

        try {
            Date dateOne = sdf.parse(startDate);
            Date dateTwo = sdf.parse(endDate);
            calendar.setTime(dateOne);
            dateList.add(startDate);

            while (dateTwo.after(calendar.getTime())) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                dateList.add(sdf.format(calendar.getTime()));
            }
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        return dateList;
    }

    @Override
    public Map<String, Object> statisticsDayAllWorkItemCount() {
        List<String> dayList = getFirstSevenDay();
        HashMap<String, Object> dayWorkItem = new HashMap<>();
        List<Map<String, Integer>> countList = projectInsightReportDao.findAllWorkItemTrend(dayList);
        dayWorkItem.put("date", dayList);
        dayWorkItem.put("countList", countList);
        return dayWorkItem;
    }

    /**
     * 统计某个项目下，某个成员负责的事项对比
     * @param workItemCountQuery
     * @return
     */
    @Override
    public  Map<String, Object> statisticsUserWorkItemCount(WorkItemCountQuery workItemCountQuery) {
        String projectId = workItemCountQuery.getProjectId();
        Project project = projectService.findProject(projectId);
        Map<String, Object> projectUserCount = new HashMap<>();
        ArrayList<Map<String, Object>> userCount = new ArrayList<>();
        if(project != null){
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
        }
        projectUserCount.put("project", project);
        projectUserCount.put("userCount", userCount);
        return projectUserCount;
    }

    /**
     * 统计各个状态下的项目数量
     * @return
     */
    @Override
    public Map<String, Integer> statisticsProjectByStatus(){
        Map<String, Integer> projectCount = projectInsightReportDao.statisticeProjectByStatus();
        return projectCount;
    };

    /**
     * 统计各个状态下的事项的数量
     * @return
     */
    @Override
    public Map<String, Integer> statisticsWorkItemByStatus(){
        Map<String, Integer> projectCount = projectInsightReportDao.statisticsWorkItemByStatus();
        return projectCount;
    }

    /**
     * 统计全部，已逾期，完成，进行中的待办事项数量
     * @param params
     * @return
     */
    @Override
    public Map<String, Integer> statisticsTodoWorkByStatus(HashMap<String, String> params) {
        Map<String, Integer> todoCount = new HashMap<>();
        LinkedHashMap data = new LinkedHashMap();

        String projectId = params.get("projectId");
        String projectSetId = params.get("projectSetId");
        String sprintId = params.get("sprintId");
        String versionId = params.get("versionId");
        if(!StringUtils.isEmpty(projectId)){
            data.put("projectId", projectId);
            todoCount = getTodoStatistics(data);
        }
        if(!StringUtils.isEmpty(sprintId)){
            data.put("sprintId", sprintId);
            todoCount = getTodoStatistics(data);
        }
        if(!StringUtils.isEmpty(versionId)){
            data.put("versionId", versionId);
            todoCount = getTodoStatistics(data);
        }
        if(!StringUtils.isEmpty(projectSetId)){
            ProjectQuery projectQuery = new ProjectQuery();
            projectQuery.setProjectSetId(projectSetId);
            List<Project> projectList = projectService.findProjectList(projectQuery);
            int total = 0;
            int progress = 0;
            int end = 0;
            int overdue = 0;
            for (Project project : projectList) {
                String projectId1 = project.getId();
                data.put("projectId", projectId1);
                todoCount = getTodoStatistics(data);

                Integer total1 = todoCount.get("total");
                total = total + total1;

                Integer progress1 = todoCount.get("progress");
                progress = progress + progress1;

                Integer end1 = todoCount.get("end");
                end = end + end1;

                Integer overdue1 = todoCount.get("overdue");
                overdue = overdue + overdue1;
            }
            todoCount.put("total", total);
            todoCount.put("progress", progress);
            todoCount.put("end", end);
            todoCount.put("overdue", overdue);
        }

        if(StringUtils.isEmpty(projectSetId) && StringUtils.isEmpty(projectId)
                && StringUtils.isEmpty(sprintId) && StringUtils.isEmpty(versionId)){
            todoCount = getTodoStatistics(data);
        }
        return todoCount;

    }

    /**
     * 获取不同待办的个数
     * @param data
     * @return
     */
    public Map<String, Integer> getTodoStatistics(LinkedHashMap data){
        Map<String, Integer> todoCount = new HashMap<>();

        String loginId = LoginContext.getLoginId();
        TaskQuery taskQuery = new TaskQuery();
        taskQuery.setData(data);

        taskQuery.setBgroup("kanass");
        taskQuery.setAssignUserId(loginId);

        Pagination<Task> taskPage = taskService.findTaskPage(taskQuery);
        todoCount.put("total", taskPage.getTotalRecord());

        taskQuery.setStatus(1);
        taskPage = taskService.findTaskPage(taskQuery);
        todoCount.put("progress", taskPage.getTotalRecord());

        taskQuery.setStatus(2);
        taskPage = taskService.findTaskPage(taskQuery);
        todoCount.put("end", taskPage.getTotalRecord());

        taskQuery.setStatus(1);
        taskQuery.setIsExpire(3);
        taskPage = taskService.findTaskPage(taskQuery);
        todoCount.put("overdue", taskPage.getTotalRecord());

        return todoCount;
    }

}
