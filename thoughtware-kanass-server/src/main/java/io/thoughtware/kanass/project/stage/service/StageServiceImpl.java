package io.thoughtware.kanass.project.stage.service;

import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.flow.flow.entity.DmFlowEntity;
import io.thoughtware.kanass.project.stage.entity.StageWorkItemEntity;
import io.thoughtware.kanass.project.stage.model.StageWorkItemQuery;
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
    JoinTemplate joinTemplate;

    @Override
    public String createStage(@NotNull @Valid Stage stage) {
        StageEntity stageEntity = BeanMapper.map(stage, StageEntity.class);

        return stageDao.createStage(stageEntity);
    }

    @Override
    public void updateStage(@NotNull @Valid Stage stage) {
        StageEntity stageEntity = BeanMapper.map(stage, StageEntity.class);

        stageDao.updateStage(stageEntity);
    }

    @Override
    public void deleteStage(@NotNull String id) {
        stageDao.deleteStage(id);
        // 删除子阶段和关联表的数据
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(StageEntity.class)
                .eq("parentId", id)
                .get();
        stageDao.deleteStageCondition(deleteCondition);

        StageQuery stageQuery = new StageQuery();
        stageQuery.setParentId(id);
        List<Stage> stageList = findStageList(stageQuery);
        List<String> stageIdList = stageList.stream().map(stage -> stage.getId()).collect(Collectors.toList());
        stageIdList.add(id);
        String[] stageIds = stageIdList.toArray(new String[stageIdList.size()]);
        StageWorkItemQuery stageWorkItemQuery = new StageWorkItemQuery();
        stageWorkItemQuery.setStageIds(stageIds);
        stageWorkItemService.deleteStageWorkItem(stageWorkItemQuery);
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
        Pagination<StageEntity>  pagination = stageDao.findStagePage(stageQuery);

        List<Stage> stageList = BeanMapper.mapList(pagination.getDataList(),Stage.class);

        joinTemplate.joinQuery(stageList);

        return PaginationBuilder.build(pagination,stageList);
    }

    @Override
    public List<Stage> findStageListTree(StageQuery stageQuery) {
//        stageQuery.setStageParentNull(true);
        List<Stage> stageList = findStageList(stageQuery);

        if(stageList == null || stageList.size() == 0){
            return stageList;
        }
        for (Stage stage : stageList) {
            List<Stage> childStageList = findChildStageList(stageQuery, stage.getId());
            stage.setChildren(childStageList);
        }
        return stageList;
    }

    public List<Stage> findChildStageList(StageQuery stageQuery, String parentId) {
        stageQuery.setStageParentNull(false);
        stageQuery.setParentId(parentId);
        List<Stage> stageList = findStageList(stageQuery);

        if(stageList == null || stageList.size() == 0){
            return stageList;
        }

        for (Stage stage : stageList) {
            List<Stage> childStageList = findChildStageList(stageQuery, stage.getId());
            stage.setChildren(childStageList);
        }
        return stageList;
    }
}