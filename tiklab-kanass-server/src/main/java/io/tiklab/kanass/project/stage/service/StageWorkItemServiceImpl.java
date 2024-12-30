package io.tiklab.kanass.project.stage.service;

import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.project.stage.dao.StageWorkItemDao;
import io.tiklab.kanass.project.stage.entity.StageWorkItemEntity;
import io.tiklab.kanass.project.stage.model.Stage;
import io.tiklab.kanass.project.stage.model.StageQuery;
import io.tiklab.kanass.project.stage.model.StageWorkItemQuery;
import io.tiklab.kanass.project.stage.model.StageWorkItem;

import io.tiklab.kanass.workitem.entity.WorkItemEntity;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.service.WorkItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 项目阶段事项管理关系
*/
@Service
public class StageWorkItemServiceImpl implements StageWorkItemService {

    @Autowired
    StageWorkItemDao stageWorkItemDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    StageService stageService;

    
    @Autowired
    WorkItemService workItemService;

    @Override
    public String createStageWorkItem(@NotNull @Valid StageWorkItem stageWorkItem) {
        StageWorkItemEntity stageWorkItemEntity = BeanMapper.map(stageWorkItem, StageWorkItemEntity.class);

        return stageWorkItemDao.createStageWorkItem(stageWorkItemEntity);
    }

    @Override
    public void updateStageWorkItem(@NotNull @Valid StageWorkItem stageWorkItem) {
        StageWorkItemEntity stageWorkItemEntity = BeanMapper.map(stageWorkItem, StageWorkItemEntity.class);

        stageWorkItemDao.updateStageWorkItem(stageWorkItemEntity);
    }

    @Override
    public void deleteStageWorkItem(@NotNull String id) {
        stageWorkItemDao.deleteStageWorkItem(id);
    }

    @Override
    public void deleteStageWorkItem(@NotNull @Valid StageWorkItemQuery stageWorkItemQuery) {
        DeleteBuilders deleteBuilders = DeleteBuilders.createDelete(StageWorkItemEntity.class)
                .eq("stageId", stageWorkItemQuery.getStageId())
                .eq("workItemId", stageWorkItemQuery.getWorkItemId());

        if(stageWorkItemQuery.getStageIds() != null && stageWorkItemQuery.getStageIds().length > 0){
            deleteBuilders.in("stageId", stageWorkItemQuery.getStageIds());
        }
        DeleteCondition deleteCondition = deleteBuilders.get();
        stageWorkItemDao.deleteStageWorkItem(deleteCondition);
    }

    @Override
    public StageWorkItem findOne(String id) {
        StageWorkItemEntity stageWorkItemEntity = stageWorkItemDao.findStageWorkItem(id);

        StageWorkItem stageWorkItem = BeanMapper.map(stageWorkItemEntity, StageWorkItem.class);
        return stageWorkItem;
    }

    /**
     * 查找某个阶段下的子阶段和关联事项
     * @param stageWorkItemQuery
     * @return
     */
    public Map<String, Object> findStageChildWorkItemAndStage(StageWorkItemQuery stageWorkItemQuery) {
        Map<String, Object> workItemAndStage = new HashMap<String, Object>();
        StageQuery stageQuery = new StageQuery();
        stageQuery.setParentId((stageWorkItemQuery.getStageId()));
        List<Stage> stageListTree = stageService.findStageListTree(stageQuery);
        for (Stage stage : stageListTree) {
            findStageChild(stage);
        }
        workItemAndStage.put("stage", stageListTree);
        List<WorkItem> workItemListByStage = findWorkItemListByStage(stageWorkItemQuery);
        workItemAndStage.put("workItem", workItemListByStage);
        return workItemAndStage;
    }

    /**
     * 查找子级阶段
     * @param stage
     */
    public void findStageChild(Stage stage){
        StageWorkItemQuery stageWorkItemQuery = new StageWorkItemQuery();
        stageWorkItemQuery.setStageId(stage.getId());
        List<WorkItem> workItemListByStage = findWorkItemListByStage(stageWorkItemQuery);
        stage.setChildrenWorkItem(workItemListByStage);
        if(stage.getChildren() != null && stage.getChildren().size() > 0){
            List<Stage> children = stage.getChildren();
            for (Stage child : children) {
                findStageChild(child);
            }

        }
    }
    
    @Override
    public List<StageWorkItem> findList(List<String> idList) {
        List<StageWorkItemEntity> stageWorkItemEntityList =  stageWorkItemDao.findStageWorkItemList(idList);

        List<StageWorkItem> stageWorkItemList =  BeanMapper.mapList(stageWorkItemEntityList,StageWorkItem.class);
        return stageWorkItemList;
    }

    @Override
    public StageWorkItem findStageWorkItem(@NotNull String id) {
        StageWorkItem stageWorkItem = findOne(id);

        joinTemplate.joinQuery(stageWorkItem);

        return stageWorkItem;
    }

    @Override
    public List<StageWorkItem> findAllStageWorkItem() {
        List<StageWorkItemEntity> stageWorkItemEntityList =  stageWorkItemDao.findAllStageWorkItem();

        List<StageWorkItem> stageWorkItemList =  BeanMapper.mapList(stageWorkItemEntityList,StageWorkItem.class);

        joinTemplate.joinQuery(stageWorkItemList);

        return stageWorkItemList;
    }

    @Override
    public List<StageWorkItem> findStageWorkItemList(StageWorkItemQuery stageWorkItemQuery) {
        List<StageWorkItemEntity> stageWorkItemEntityList = stageWorkItemDao.findStageWorkItemList(stageWorkItemQuery);

        List<StageWorkItem> stageWorkItemList = BeanMapper.mapList(stageWorkItemEntityList,StageWorkItem.class);

        joinTemplate.joinQuery(stageWorkItemList);

        return stageWorkItemList;
    }

    @Override
    public Pagination<StageWorkItem> findStageWorkItemPage(StageWorkItemQuery stageWorkItemQuery) {
        Pagination<StageWorkItemEntity>  pagination = stageWorkItemDao.findStageWorkItemPage(stageWorkItemQuery);

        List<StageWorkItem> stageWorkItemList = BeanMapper.mapList(pagination.getDataList(),StageWorkItem.class);

        joinTemplate.joinQuery(stageWorkItemList);

        return PaginationBuilder.build(pagination,stageWorkItemList);
    }

    @Override
    public List<WorkItem> findWorkItemListByStage(StageWorkItemQuery stageWorkItemQuery) {
        List<WorkItemEntity> workItemEntityList = stageWorkItemDao.findWorkItemListByStage(stageWorkItemQuery);

        List<WorkItem> stageWorkItemList = BeanMapper.mapList(workItemEntityList,WorkItem.class);

        joinTemplate.joinQuery(stageWorkItemList);

        if(stageWorkItemList == null || stageWorkItemList.size() == 0){
            return stageWorkItemList;
        }

        //查询子事项列表
        List<WorkItem> childWorkItemList = workItemService.findChildWorkItemList(stageWorkItemList);

        //设置子事项
        if(childWorkItemList != null && childWorkItemList.size() > 0){
            for(WorkItem topWorkItem:stageWorkItemList){
                workItemService.setChildren(childWorkItemList,topWorkItem);
            }
        }

        return stageWorkItemList;
    }
}