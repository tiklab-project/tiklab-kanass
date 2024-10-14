package io.tiklab.kanass.sprint.service;

import io.tiklab.kanass.sprint.model.SprintState;
import io.tiklab.kanass.sprint.model.SprintStateQuery;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.sprint.entity.SprintStateEntity;
import io.tiklab.kanass.sprint.dao.SprintStateDao;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.beans.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 迭代状态服务
*/
@Service
public class SprintStateServiceImpl implements SprintStateService {

    @Autowired
    SprintStateDao sprintStateDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createSprintState(@NotNull @Valid SprintState sprintState) {
        SprintStateEntity sprintStateEntity = BeanMapper.map(sprintState, SprintStateEntity.class);

        return sprintStateDao.createSprintState(sprintStateEntity);
    }

    @Override
    public void updateSprintState(@NotNull @Valid SprintState sprintState) {
        SprintStateEntity sprintStateEntity = BeanMapper.map(sprintState, SprintStateEntity.class);

        sprintStateDao.updateSprintState(sprintStateEntity);
    }

    @Override
    public void deleteSprintState(@NotNull String id) {
        sprintStateDao.deleteSprintState(id);
    }

    @Override
    public SprintState findOne(String id) {
        SprintStateEntity sprintStateEntity = sprintStateDao.findSprintState(id);

        SprintState sprintState = BeanMapper.map(sprintStateEntity, SprintState.class);
        return sprintState;
    }

    @Override
    public List<SprintState> findList(List<String> idList) {
        List<SprintStateEntity> sprintStateEntityList =  sprintStateDao.findSprintStateList(idList);

        List<SprintState> sprintStateList =  BeanMapper.mapList(sprintStateEntityList,SprintState.class);
        return sprintStateList;
    }

    @Override
    public SprintState findSprintState(@NotNull String id) {
        SprintState sprintState = findOne(id);

        joinTemplate.joinQuery(sprintState);
        return sprintState;
    }

    @Override
    public List<SprintState> findAllSprintState() {
        List<SprintStateEntity> sprintStateEntityList =  sprintStateDao.findAllSprintState();

        List<SprintState> sprintStateList =  BeanMapper.mapList(sprintStateEntityList,SprintState.class);

        joinTemplate.joinQuery(sprintStateList);
        return sprintStateList;
    }

    @Override
    public List<SprintState> findSprintStateList(SprintStateQuery sprintStateQuery) {
        List<SprintStateEntity> sprintStateEntityList = sprintStateDao.findSprintStateList(sprintStateQuery);

        List<SprintState> sprintStateList = BeanMapper.mapList(sprintStateEntityList,SprintState.class);

        joinTemplate.joinQuery(sprintStateList);

        return sprintStateList;
    }

    @Override
    public Pagination<SprintState> findSprintStatePage(SprintStateQuery sprintStateQuery) {

        Pagination<SprintStateEntity>  pagination = sprintStateDao.findSprintStatePage(sprintStateQuery);

        List<SprintState> sprintStateList = BeanMapper.mapList(pagination.getDataList(),SprintState.class);

        joinTemplate.joinQuery(sprintStateList);

        return PaginationBuilder.build(pagination,sprintStateList);
    }
}