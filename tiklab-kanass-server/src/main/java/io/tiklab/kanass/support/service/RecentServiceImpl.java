package io.tiklab.kanass.support.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.model.ProjectQuery;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.kanass.support.dao.RecentDao;
import io.tiklab.kanass.support.entity.RecentEntity;
import io.tiklab.kanass.support.model.Recent;
import io.tiklab.kanass.support.model.RecentQuery;


import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.model.WorkItemQuery;
import io.tiklab.kanass.workitem.service.WorkItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* 最近访问的服务
*/
@Service
public class RecentServiceImpl implements RecentService {

    @Autowired
    RecentDao recentDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ProjectService projectService;

    @Autowired
    WorkItemService workItemService;

    /**
     * 创建最近访问的项目，项目集，事项
     * @param recent
     * @return
     */
    @Override
    public String createRecent(@NotNull @Valid Recent recent) {
        recent.setRecentTime(new Timestamp(System.currentTimeMillis()));
        recent.setMasterId(LoginContext.getLoginId());
        String id = new String();
        // 处理之前存储数据多于5条的情况
        String model = recent.getModel();
        switch (model){
            case "project":
                id = createRecentByCondition(recent, 5);
                break;
            case "projectSet":
                id = createRecentByCondition(recent, 5);
                break;
            case "workItem":
                id = createRecentByCondition(recent, 10);
                break;
            default:
                id = createRecentByCondition(recent, 10);
                break;
        }
        return id;
    }

    /**
     * 创建最近查看的，多于5条的删掉之前的
     * @param recent
     * @param num
     * @return
     */
    public String createRecentByCondition(Recent recent, int num){
        String id = new String();
        RecentQuery recentQuery = new RecentQuery();
        String modelId = recent.getModelId();
        String model = recent.getModel();
        recentQuery.setModel(model);
        recentQuery.setMasterId(LoginContext.getLoginId());
        List<Recent> recentList = findRecentList(recentQuery);
        if(recentList.size() > 0){
            List<Recent> recentList2  = new ArrayList<>();
            // 如果大于应该存在的条数，删掉多于的
            if(recentList.size() > num){
                List<Recent> recentList1 = recentList.subList(num, recentList.size());
                if(recentList1.size() >0){
                    String ids = "(" + recentList1.stream().map(item -> "'" + item.getId() + "'").collect(Collectors.joining(", ")) + ")";
                    recentDao.deleteRecentByIds(ids);
                }
                recentList2 = recentList.subList(0, num);
            }else {
                // 如果小于等于，则获取应该存在的
                recentList2 = recentList.subList(0, recentList.size());
            }

            int size = recentList2.size();
            if(size > 0){
                List<Recent> recentList3 = recentList2.stream().filter(recent1 -> recent1.getModelId().equals(modelId)).collect(Collectors.toList());
                if(recentList3.size() > 0){
                    Recent recent1 = recentList3.get(0);
                    id = recent1.getId();
                    recent.setId(recent1.getId());
                    updateRecent(recent);
                }else {
                    RecentEntity recentEntity = BeanMapper.map(recent, RecentEntity.class);
                    id = recentDao.createRecent(recentEntity);
                    Recent recent1 = recentList2.get(size - 1);
                    // 如果刚好5条，删除最早的那一条
                    if(size == num){
                        deleteRecent(recent1.getId());
                    }
                }
            }
        }else {
            RecentEntity recentEntity = BeanMapper.map(recent, RecentEntity.class);
            id = recentDao.createRecent(recentEntity);
        }
        return id;
    }
    @Override
    public void updateRecent(@NotNull @Valid Recent recent) {
        recent.setRecentTime(new Timestamp(System.currentTimeMillis()));
        RecentEntity recentEntity = BeanMapper.map(recent, RecentEntity.class);

        recentDao.updateRecent(recentEntity);
    }

    @Override
    public void deleteRecent(@NotNull String id) {
        recentDao.deleteRecent(id);
    }
    public void deleteRecent(DeleteCondition deleteCondition){
        recentDao.deleteRecent(deleteCondition);
    }
    @Override
    public Recent findOne(String id) {
        RecentEntity recentEntity = recentDao.findRecent(id);

        Recent recent = BeanMapper.map(recentEntity, Recent.class);
        return recent;
    }

    @Override
    public List<Recent> findList(List<String> idList) {
        List<RecentEntity> recentEntityList =  recentDao.findRecentList(idList);

        List<Recent> recentList =  BeanMapper.mapList(recentEntityList,Recent.class);
        return recentList;
    }

    @Override
    public Recent findRecent(@NotNull String id) {
        Recent recent = findOne(id);

        joinTemplate.joinQuery(recent, new String[]{"project", "projectType"});

        return recent;
    }

    @Override
    public List<Recent> findAllRecent() {
        List<RecentEntity> recentEntityList =  recentDao.findAllRecent();

        List<Recent> recentList =  BeanMapper.mapList(recentEntityList,Recent.class);

        joinTemplate.joinQuery(recentList, new String[]{"project", "projectType"});

        return recentList;
    }
    @Override
    public List<Recent> findRecentList(RecentQuery recentQuery) {
        List<RecentEntity> recentEntityList = recentDao.findRecentList(recentQuery);
        List<Recent> recentList =  BeanMapper.mapList(recentEntityList,Recent.class);
        joinTemplate.joinQuery(recentList, new String[]{"project", "projectType"});

        return recentList;
    }

    /**
     * 获取最近查看的事项列表、项目列表
     * @param recentQuery
     * @return
     */
    @Override
    public List<Recent> findRecentListToModel(RecentQuery recentQuery) {
        //查询最近访问的实体列表
        List<RecentEntity> recentEntityList = recentDao.findRecentList(recentQuery);

        List<Recent> recentList = new ArrayList<>();
        //使用流操作和 Collectors.groupingBy 方法，将 recentEntityList 按照模型类型（model）分组
        Map<String, List<RecentEntity>> collect = recentEntityList.stream().collect(Collectors.groupingBy(RecentEntity::getModel));
        //获取模型类型为 "project" 的最近访问记录列表 projectRecentList
        List<RecentEntity> projectRecentList = collect.get("project");

        List<Project> projectList = new ArrayList<>();
        if(projectRecentList != null && projectRecentList.size() > 0){
            //提取所有项目的 ID 列表 projectIds
            List<String> projectIds = projectRecentList.stream().map((project -> project.getModelId())).collect(Collectors.toList());
            String[] ids = new String[projectIds.size()];
            //将 projectIds 转换为数组 ids
            ids = projectIds.toArray(ids);
            ProjectQuery projectQuery = new ProjectQuery();
            projectQuery.setProjectIds(ids);
            //调方法，查询与这些 ID 对应的项目列表
            projectList = projectService.findProjectList(projectQuery);
        }

        List<RecentEntity> workItemRecentList = collect.get("workItem");

        List<WorkItem> workItemList = new ArrayList<>();
        if(workItemRecentList != null && workItemRecentList.size() > 0){
            List<String> workItemListIds = workItemRecentList.stream().map((project -> project.getModelId())).collect(Collectors.toList());
            String[] ids = new String[workItemListIds.size()];
            ids = workItemListIds.toArray(ids);
            WorkItemQuery workItemQuery = new WorkItemQuery();
            workItemQuery.setIds(ids);
            workItemList = workItemService.findWorkItemList(workItemQuery);
        }

        for (RecentEntity recentEntity : recentEntityList) {
            Recent recent = new Recent();
            String id = recentEntity.getId();
            String modelId = recentEntity.getModelId();
            String model = recentEntity.getModel();
            Timestamp recentTime = recentEntity.getRecentTime();
            recent.setId(id);
            if(model.equals("project")){
                List<Project> collect1 = projectList.stream().filter(project -> project.getId().equals(modelId)).collect(Collectors.toList());
                recent.setObject(collect1.get(0));
                recent.setRecentTime(recentTime);
                recentList.add(recent);
            }

            if(model.equals("workItem")){
                List<WorkItem> collect1 = workItemList.stream().filter(workItem -> workItem.getId().equals(modelId)).collect(Collectors.toList());
                if(collect1.size() > 0){
                    recent.setObject(collect1.get(0));
                    recent.setRecentTime(recentTime);
                    recentList.add(recent);
                }

            }

        }


        return recentList;
    }

    @Override
    public Pagination<Recent> findRecentPage(RecentQuery recentQuery) {
        Pagination<RecentEntity>  pagination = recentDao.findRecentPage(recentQuery);

        List<Recent> recentList = BeanMapper.mapList(pagination.getDataList(),Recent.class);

        joinTemplate.joinQuery(recentList, new String[]{"project", "projectType"});

        return PaginationBuilder.build(pagination,recentList);
    }
}