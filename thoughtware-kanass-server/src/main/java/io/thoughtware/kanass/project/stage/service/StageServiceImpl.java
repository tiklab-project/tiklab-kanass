package io.thoughtware.kanass.project.stage.service;

import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.flow.flow.entity.DmFlowEntity;
import io.thoughtware.kanass.project.stage.entity.StageWorkItemEntity;
import io.thoughtware.kanass.project.stage.model.StageWorkItemQuery;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.kanass.workitem.model.WorkItemQuery;
import io.thoughtware.kanass.workitem.service.WorkItemService;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.kanass.project.stage.model.Stage;
import io.thoughtware.kanass.project.stage.model.StageQuery;
import io.thoughtware.kanass.project.stage.dao.StageDao;
import io.thoughtware.kanass.project.stage.entity.StageEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* 项目阶段服务
*/
@Service
public class StageServiceImpl implements StageService {

    @Autowired
    StageDao stageDao;

    @Autowired
    StageWorkItemService stageWorkItemService;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createStage(@NotNull @Valid Stage stage) {
        StageEntity stageEntity = BeanMapper.map(stage, StageEntity.class);
        String stageId = stageDao.createStage(stageEntity);
        stageEntity.setId(stageId);
        if(stage.getParentStage() != null && stage.getParentStage().getId() != "nullstring"){
            String id = stage.getParentStage().getId();
            Stage stageParent = findStage(id);
            String treeId = new String();
            if(stageParent.getTreePath() != null){
                treeId = stageParent.getTreePath()  + id + ";";
            }else {
                treeId = id + ";";
            }
            stageEntity.setTreePath(treeId);
            stageEntity.setRootId(stageParent.getRootId());
            stageEntity.setDeep(stage.getDeep() + 1);
        }else {
            stageEntity.setRootId(stageId);
            stageEntity.setDeep(0);
        }
        stageDao.updateStage(stageEntity);
        return stageId;

    }

    @Override
    public void updateStage(@NotNull @Valid Stage stage) {

        boolean isChangeParent = stage.getIsChangeParent();
        String id = stage.getId();
        Stage oldStage = findStage(id);
        Integer deep = oldStage.getDeep();
        // 如果更新上级
        if(isChangeParent){
            if(stage.getParentStage() != null && !stage.getParentStage().getId().equals("nullstring")){
                String parentId = stage.getParentStage().getId();
                Stage stageParent = findStage(parentId);
                String treeId = stageParent.getTreePath() != null ?   stageParent.getTreePath() + parentId + ";" : parentId + ";";
                stage.setTreePath(treeId);
                stage.setRootId(stageParent.getRootId());
                Integer newDeep = stageParent.getDeep() + 1;
                Integer distance = newDeep - deep;
                stage.setDeep(stageParent.getDeep() + 1);
                // 更新当前事项的所有下级

                StageQuery stageQuery = new StageQuery();
                stageQuery.setTreePath(id);
                List<Stage> stageList = findStageList(stageQuery);
                for (Stage stage1 : stageList) {
                    Integer deep1 = stage1.getDeep();
                    stage1.setDeep(deep1 + distance);
                    stage1.setRootId(stageParent.getRootId());

                    String treePath1 = stage1.getTreePath();
                    int index = treePath1.indexOf(id);
                    treePath1 = treePath1.substring(index);
                    if(treeId != null && treeId != "nullstring"){
                        treePath1 = treeId + treePath1;
                    }
                    stage1.setTreePath(treePath1);
                    StageEntity stageEntity1 = BeanMapper.map(stage1, StageEntity.class);
                    stageDao.updateStage(stageEntity1);
                }

            }

            if(stage.getParentStage() != null && stage.getParentStage().getId().equals("nullstring") ){
                stage.setTreePath("nullstring");
                stage.setRootId(id);
                stage.setDeep(0);

                Integer distance = 0 - deep;
                StageQuery stageQuery = new StageQuery();
                stageQuery.setRootId(id);
                List<Stage> stageList = findStageList(stageQuery);
                for (Stage stage1 : stageList) {
                    Integer deep1 = stage1.getDeep();
                    stage1.setDeep(deep1 + distance);
                    stage1.setRootId(id);
                    String treePath1 = stage1.getTreePath();
                    int index = treePath1.indexOf(id);
                    treePath1 = treePath1.substring(index);
                    treePath1 = id + ";" + treePath1;
                    stage1.setTreePath(treePath1);
                    StageEntity stageEntity1 = BeanMapper.map(stage1, StageEntity.class);
                    stageDao.updateStage(stageEntity1);
                }

            }
        }
        StageEntity stageEntity = BeanMapper.map(stage, StageEntity.class);
        stageDao.updateStage(stageEntity);
    }

    @Override
    public void deleteStage(@NotNull String id) {
        StageQuery stageQuery = new StageQuery();
        stageQuery.setTreePath(id);
        List<Stage> stageList = findStageList(stageQuery);
        List<String> stageIdList = stageList.stream().map(stage -> stage.getId()).collect(Collectors.toList());
        stageIdList.add(id);
        String[] stageIds = stageIdList.toArray(new String[stageIdList.size()]);

        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setStageIds(stageIds);
        List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);
        if(workItemList.size() > 0){

        }
//        stageWorkItemService.deleteStageWorkItem(stageWorkItemQuery);
    }


    @Override
    public Stage findOne(String id) {
        StageEntity stageEntity = stageDao.findStage(id);

        Stage stage = BeanMapper.map(stageEntity, Stage.class);
        return stage;
    }

    @Override
    public List<Stage> findList(List<String> idList) {
        List<StageEntity> stageEntityList =  stageDao.findStageList(idList);

        List<Stage> stageList =  BeanMapper.mapList(stageEntityList,Stage.class);
        return stageList;
    }

    @Override
    public Stage findStage(@NotNull String id) {
        Stage stage = findOne(id);

        joinTemplate.joinQuery(stage);

        return stage;
    }

    @Override
    public List<Stage> findAllStage() {
        List<StageEntity> stageEntityList =  stageDao.findAllStage();

        List<Stage> stageList =  BeanMapper.mapList(stageEntityList,Stage.class);

        joinTemplate.joinQuery(stageList);

        return stageList;
    }

    @Override
    public List<Stage> findStageList(StageQuery stageQuery) {
        List<StageEntity> stageEntityList = stageDao.findStageList(stageQuery);

        List<Stage> stageList = BeanMapper.mapList(stageEntityList,Stage.class);

        joinTemplate.joinQuery(stageList);

        return stageList;
    }

    @Override
    public Pagination<Stage> findStagePage(StageQuery stageQuery) {

        Pagination<StageEntity> pagination = stageDao.findStagePage(stageQuery);

        List<Stage> stageList = BeanMapper.mapList(pagination.getDataList(),Stage.class);

        joinTemplate.joinQuery(stageList);

        return PaginationBuilder.build(pagination,stageList);
    }

    @Override
    public Pagination<Stage> findStageListTreePage(StageQuery stageQuery) {
        stageQuery.setStageParentNull(true);
        Pagination<Stage> stagePage = findStagePage(stageQuery);
        List<Stage> stageList = stagePage.getDataList();
        if(stageList.size() > 0){
            List<String> stageIdList = stageList.stream().map(stage -> stage.getId()).collect(Collectors.toList());
            // 根据第一级阶段的ids 查找下级的所有阶段和事项
            String[] stageIds = stageIdList.toArray(new String[stageIdList.size()]);
            stageQuery.setRootIds(stageIds);
            stageQuery.setStageParentNull(false);
            List<Stage> stageListChildren = findStageList(stageQuery);
            if(stageListChildren.size() > 0){
                List<String> stageListChildrenIdList = stageListChildren.stream().map(stage -> stage.getId()).collect(Collectors.toList());
                stageIdList.addAll(stageListChildrenIdList);

                WorkItemQuery workItemQuery = new WorkItemQuery();
                String[] allStageIds = stageIdList.toArray(new String[stageIdList.size()]);
                workItemQuery.setStageIds(allStageIds);
                List<WorkItem> workItemList = workItemService.findWorkItemListTree(workItemQuery);

                //
                for (Stage stage : stageList) {
                    setStageTree(stage, stageListChildren, workItemList);
                }
            }
        }

        return stagePage;
    }

    @Override
    public List<Stage> findStageListTree(StageQuery stageQuery) {
        // 安装分页查找第一页的一级阶段
        Pagination<Stage> stagePage = findStagePage(stageQuery);
        List<Stage> stageList = stagePage.getDataList();

        if(stageList.size() > 0){
            List<String> stageIdList = stageList.stream().map(stage -> stage.getId()).collect(Collectors.toList());
            // 根据第一级阶段的ids 查找下级的所有阶段和事项
            String[] stageIds = stageIdList.toArray(new String[stageIdList.size()]);
            stageQuery.setRootIds(stageIds);
            stageQuery.setStageParentNull(false);
            List<Stage> stageListChildren = findStageList(stageQuery);
            if(stageListChildren.size() > 0){
                List<String> stageListChildrenIdList = stageListChildren.stream().map(stage -> stage.getId()).collect(Collectors.toList());
                stageIdList.addAll(stageListChildrenIdList);

                WorkItemQuery workItemQuery = new WorkItemQuery();
                String[] allStageIds = stageIdList.toArray(new String[stageIdList.size()]);
                workItemQuery.setStageIds(allStageIds);
                List<WorkItem> workItemList = workItemService.findWorkItemListTree(workItemQuery);

                //
                for (Stage stage : stageList) {
                    setStageTree(stage, stageListChildren, workItemList);
                }
            }

            // 查找所有阶段关联的下级事项

        }
        return stageList;
    }

    void setStageTree(Stage stage, List<Stage> stageListChildren, List<WorkItem> workItemList){
        String id = stage.getId();
        List<Stage> childrenList = new ArrayList<>();
        // 设置子阶段
        if(stageListChildren.size() > 0){
            childrenList = stageListChildren.stream().filter(item -> item.getParentStage().getId().equals(id)).collect(Collectors.toList());
            stageListChildren.removeAll(childrenList);
            if(childrenList.size() > 0){
                for (Stage childStage : childrenList) {
                    setStageTree(childStage, stageListChildren, workItemList);
                }
            }
            stage.setChildren(childrenList);
        }

        if(workItemList.size() > 0){
            // 设置子事项
            List<WorkItem> childrenWorkItem = workItemList.stream().filter(workItem -> workItem.getStage().getId().equals(id)).collect(Collectors.toList());
            stage.setChildrenWorkItem(childrenWorkItem);
            workItemList.removeAll(childrenWorkItem);

        }


    }

    /**
     * 因为计划设置为三级，查找当时计划可选的上级事项
     * @param stageQuery
     * @return
     */
    @Override
    public Pagination<Stage> findParentStageList(StageQuery stageQuery){
        String id = stageQuery.getId();
        Pagination<Stage> stagePage = new Pagination<>();
        Integer stageLevel = findStageLevel(id);
        // 如果有一层下级，只能找0 deep的作为上级
        if(stageLevel == 1){
            stageQuery.setDeep(0);
            stagePage = findStagePage(stageQuery);
        }
        // 如果没有下级，能找0, 1 deep的作为上级
        if(stageLevel == 0){
            List<Integer> deepList = new ArrayList<>();
            deepList.add(0);
            deepList.add(1);
            Integer[] deeps = deepList.toArray(new Integer[deepList.size()]);
            stageQuery.setDeeps(deeps);
            stagePage = findStagePage(stageQuery);
        }
        return stagePage;
    }


    @Override
    public Integer findStageLevel(String id){
        Integer stageLevel = stageDao.findStageLevel(id);
        return stageLevel;
    }
}