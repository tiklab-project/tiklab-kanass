package io.tiklab.kanass.home.statistic.service;

import io.tiklab.kanass.home.statistic.model.ProjectWorkItemStat;
import io.tiklab.kanass.home.statistic.model.WorkItemBusStatusStat;
import io.tiklab.kanass.home.statistic.model.WorkItemStatistic;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.model.ProjectQuery;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.model.WorkItemQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.flow.statenode.model.StateNode;
import io.tiklab.flow.statenode.service.StateNodeService;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.home.statistic.dao.WorkItemStatDao;

import io.tiklab.kanass.workitem.entity.WorkItemEntity;
import io.tiklab.kanass.workitem.service.WorkItemService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* 统计服务
*/
@Service
public class WorkItemStatServiceImpl implements WorkItemStatService {
    private static Logger logger = LoggerFactory.getLogger(WorkItemStatServiceImpl.class);
    @Autowired
    WorkItemStatDao workItemStatDao;

    @Autowired
    StateNodeService stateNodeService;

    @Autowired
    ProjectService projectService;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public List<WorkItemStatistic> statWorkItemByStatus() {
        //按状态统计事项
        List<WorkItemStatistic> list =  workItemStatDao.statWorkItemByStatus();

        //查找事项状态信息
        if(list != null && list.size() > 0){
            for(WorkItemStatistic item:list){
                StateNode startNode = stateNodeService.findStateNode(item.getStatisticalId());
                item.setStatisticalTitle(startNode.getName());
            }
        }
        return list;
    }

    @Override
    public List<WorkItemStatistic> statSprintWorkItemByStatus(String sprintId) {
        //按状态统计事项
        List<WorkItemStatistic> list =  workItemStatDao.statSprintWorkItemByStatus(sprintId);

        //查找事项状态信息
        if(list != null && list.size() > 0){
            for(WorkItemStatistic item:list){
                StateNode startNode = stateNodeService.findStateNode(item.getStatisticalId());
                item.setStatisticalTitle(startNode.getName());
            }
        }
        return list;
    }

    @Override
    public List<WorkItemBusStatusStat> statWorkItemByBusStatus() {
        //按状态统计事项
        List<WorkItemBusStatusStat> list =  workItemStatDao.statWorkItemByBusStatus();

        return list;
    }

    @Override
    public List<WorkItemBusStatusStat> statProjectWorkItemByBusStatus(String projectId, String masterId, String sprintId, String versionId) {
        //按状态统计事项
        List<WorkItemBusStatusStat> list =  workItemStatDao.statProjectWorkItemByBusStatus(projectId, masterId, sprintId, versionId);

        return list;
    }


    @Override
    public List<ProjectWorkItemStat> statProjectWorkItemCount(Integer num) {
        List<ProjectWorkItemStat> list = new ArrayList<>();
        ProjectQuery projectQuery = new ProjectQuery();

        List<Project> projectList = projectService.findRecentProjectList(projectQuery);
        if(projectList.size() <= 0)  return list;

        // 拼接projects
        if(projectList.size() > num){
            projectList = projectList.subList(0, num);
        }
        String projectIds = "(" + projectList.stream().map(item -> "'" + item.getId() + "'").collect(Collectors.joining(", ")) + ")";


        List<Map<String, Object>> projectWorkItemCount = projectService.findProjectWorkItemStatus(projectIds);
        for (Project project : projectList) {
            ProjectWorkItemStat projectWorkItemStat = new ProjectWorkItemStat();
            projectWorkItemStat.setProject(project);
            String id = project.getId();
            List<Map<String, Object>> doneList = projectWorkItemCount.stream().filter(workItem -> (workItem.get("project_id").
                    equals(id) && workItem.get("work_status_code").equals("DONE"))).collect(Collectors.toList());
            projectWorkItemStat.setEndWorkItemCount(doneList.size());

            List<Map<String, Object>> processList = projectWorkItemCount.stream().filter(workItem -> (workItem.get("project_id").
                    equals(id) && !workItem.get("work_status_code").equals("DONE"))).collect(Collectors.toList());
            projectWorkItemStat.setProcessWorkItemCount(processList.size());
            list.add(projectWorkItemStat);
        }
        return list;
    }

    @Override
    public List<Sprint> statManageSprint(String projectId) {
        List<Sprint> list =  workItemStatDao.statManageSprint(projectId);
        joinTemplate.joinQuery(list);
        return list;
    }

    @Override
    public List<Sprint> statProjectManageSprint(String masterId, String projectId) {

        List<Sprint> list =  workItemStatDao.statProjectManageSprint(masterId, projectId);
        joinTemplate.joinQuery(list);
        return list;
    }

    @Override
    public List<WorkItem> statSprintProcessWorkItem(String masterId, String sprintId) {
        List<WorkItem> list =  workItemStatDao.statSprintProcessWorkItem(masterId, sprintId);
        joinTemplate.joinQuery(list);
        return list;
    }

    @Override
    public List<WorkItemBusStatusStat> statSprintWorkItemByBusStatus(String sprintId) {
        List<WorkItemBusStatusStat> list =  workItemStatDao.statSprintWorkItemByBusStatus(sprintId);
        joinTemplate.joinQuery(list);
        return list;
    }

    @Override
    public List<WorkItem> statWorkItemProcess() {
        List<WorkItemEntity> workItemEntityList = workItemStatDao.statWorkItemProcess();

        List<WorkItem> workItemList = BeanMapper.mapList(workItemEntityList,WorkItem.class);

        joinTemplate.joinQuery(workItemList);

        return workItemList;
    }

    @Override
    public List<WorkItem> statProgramSetWorkItemProcess(String[] ids) {
        List<WorkItemEntity> workItemEntityList = workItemStatDao.statProgramSetWorkItemProcess(ids);

        List<WorkItem> workItemList = BeanMapper.mapList(workItemEntityList,WorkItem.class);

        joinTemplate.joinQuery(workItemList);

        return workItemList;
    }

    @Override
    public List<WorkItem> statProjectWorkItemProcess(String projectId) {
        List<WorkItemEntity> workItemEntityList = workItemStatDao.statProjectWorkItemProcess(projectId);

        List<WorkItem> workItemList = BeanMapper.mapList(workItemEntityList,WorkItem.class);

        joinTemplate.joinQuery(workItemList);

        return workItemList;
    }

    //逾期
    @Override
    public Pagination<WorkItem> statWorkItemOverdue(WorkItemQuery workItemQuery) {
        Pagination<WorkItemEntity>  pagination = workItemStatDao.statWorkItemOverdue(workItemQuery);

        List<WorkItem> workItemList = BeanMapper.mapList(pagination.getDataList(),WorkItem.class);

        joinTemplate.joinQuery(workItemList);

        return PaginationBuilder.build(pagination,workItemList);
    }
}