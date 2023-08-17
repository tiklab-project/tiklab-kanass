package io.tiklab.teamwire.home.statistic.service;

import io.tiklab.teamwire.home.statistic.model.ProjectWorkItemStat;
import io.tiklab.teamwire.home.statistic.model.WorkItemBusStatusStat;
import io.tiklab.teamwire.home.statistic.model.WorkItemStatistic;
import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.teamwire.project.project.model.ProjectQuery;
import io.tiklab.teamwire.project.project.service.ProjectService;
import io.tiklab.teamwire.sprint.model.Sprint;
import io.tiklab.teamwire.workitem.model.WorkItem;
import io.tiklab.teamwire.workitem.model.WorkItemQuery;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.flow.statenode.model.StateNode;
import io.tiklab.flow.statenode.service.StateNodeService;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teamwire.home.statistic.dao.WorkItemStatDao;

import io.tiklab.teamwire.workitem.entity.WorkItemEntity;
import io.tiklab.teamwire.workitem.service.WorkItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* 统计服务
*/
@Service
public class WorkItemStatServiceImpl implements WorkItemStatService {

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
    public List<WorkItemBusStatusStat> statProjectWorkItemByBusStatus(String projectId, String masterId) {
        //按状态统计事项
        List<WorkItemBusStatusStat> list =  workItemStatDao.statProjectWorkItemByBusStatus(projectId, masterId);

        return list;
    }


//    public List<ProjectWorkItemStat> statProjectWorkItemCount(String masterId) {
//        List<ProjectWorkItemStat> list = workItemStatDao.statProjectWorkItemCount(masterId);
//        for(ProjectWorkItemStat projectWorkItemStat:list){
//            joinTemplate.joinQuery(projectWorkItemStat.getProject());
//        }
//
//        return list;
//    }

    @Override
    public List<ProjectWorkItemStat> statProjectWorkItemCount(String recentMasterId) {
        List<ProjectWorkItemStat> list = new ArrayList<>();
        ProjectQuery projectQuery = new ProjectQuery();
        projectQuery.setRecentMasterId(recentMasterId);
        List<Project> projectList = projectService.findRecentProjectList(projectQuery);

        if(projectList != null && projectList.size() > 0){
            for(Project item:projectList){
                ProjectWorkItemStat projectWorkItemStat = new ProjectWorkItemStat();
                projectWorkItemStat.setProject(item);
                String projectId = item.getId();

                WorkItemQuery workItemQuery = new WorkItemQuery();
                workItemQuery.setProjectId(projectId);
                workItemQuery.setWorkStatusCode("DONE");
                List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);
                projectWorkItemStat.setEndWorkItemCount((workItemList.size()));


                WorkItemQuery workItemQuery2 = new WorkItemQuery();
                workItemQuery2.setProjectId(projectId);
                workItemQuery2.setWorkStatusCode("PROGRESS");
                List<WorkItem> workItemList2 = workItemService.findWorkItemList(workItemQuery2);
                projectWorkItemStat.setProcessWorkItemCount((workItemList2.size()));
                list.add(projectWorkItemStat);
            }
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