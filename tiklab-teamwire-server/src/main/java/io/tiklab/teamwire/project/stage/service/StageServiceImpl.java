package io.tiklab.teamwire.project.stage.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teamwire.project.stage.model.Stage;
import io.tiklab.teamwire.project.stage.model.StageQuery;
import io.tiklab.teamwire.project.stage.dao.StageDao;
import io.tiklab.teamwire.project.stage.entity.StageEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 项目阶段服务
*/
@Service
public class StageServiceImpl implements StageService {

    @Autowired
    StageDao stageDao;

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