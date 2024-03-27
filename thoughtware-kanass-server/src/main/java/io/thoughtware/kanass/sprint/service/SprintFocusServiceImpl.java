package io.thoughtware.kanass.sprint.service;

import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.kanass.sprint.model.SprintFocus;
import io.thoughtware.kanass.sprint.model.SprintFocusQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.kanass.sprint.entity.SprintFocusEntity;
import io.thoughtware.kanass.sprint.dao.SprintFocusDao;

import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 收藏迭代的服务
*/
@Service
public class SprintFocusServiceImpl implements SprintFocusService {

    @Autowired
    SprintFocusDao sprintFocusDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createSprintFocus(@NotNull @Valid SprintFocus sprintFocus) {
        SprintFocusEntity sprintFocusEntity = BeanMapper.map(sprintFocus, SprintFocusEntity.class);

        return sprintFocusDao.createSprintFocus(sprintFocusEntity);
    }

    @Override
    public void updateSprintFocus(@NotNull @Valid SprintFocus sprintFocus) {
        SprintFocusEntity sprintFocusEntity = BeanMapper.map(sprintFocus, SprintFocusEntity.class);

        sprintFocusDao.updateSprintFocus(sprintFocusEntity);
    }

    @Override
    public void deleteSprintFocus(@NotNull String id) {
        sprintFocusDao.deleteSprintFocus(id);
    }

    @Override
    public void deleteSprintFocusByQuery(SprintFocusQuery sprintFocusQuery) {
        List<SprintFocusEntity> sprintFocusList = sprintFocusDao.findSprintFocusList(sprintFocusQuery);
        String id = sprintFocusList.get(0).getId();

        sprintFocusDao.deleteSprintFocus(id);
    }

    @Override
    public SprintFocus findOne(String id) {
        SprintFocusEntity sprintFocusEntity = sprintFocusDao.findSprintFocus(id);

        SprintFocus sprintFocus = BeanMapper.map(sprintFocusEntity, SprintFocus.class);
        return sprintFocus;
    }

    @Override
    public List<SprintFocus> findList(List<String> idList) {
        List<SprintFocusEntity> sprintFocusEntityList =  sprintFocusDao.findSprintFocusList(idList);

        List<SprintFocus> sprintFocusList =  BeanMapper.mapList(sprintFocusEntityList,SprintFocus.class);
        return sprintFocusList;
    }

    @Override
    public SprintFocus findSprintFocus(@NotNull String id) {
        SprintFocus sprintFocus = findOne(id);

        joinTemplate.joinQuery(sprintFocus);

        return sprintFocus;
    }

    @Override
    public List<SprintFocus> findAllSprintFocus() {
        List<SprintFocusEntity> sprintFocusEntityList =  sprintFocusDao.findAllSprintFocus();

        List<SprintFocus> sprintFocusList =  BeanMapper.mapList(sprintFocusEntityList,SprintFocus.class);

        joinTemplate.joinQuery(sprintFocusList);

        return sprintFocusList;
    }

    @Override
    public List<SprintFocus> findSprintFocusList(SprintFocusQuery sprintFocusQuery) {
        List<SprintFocusEntity> sprintFocusEntityList = sprintFocusDao.findSprintFocusList(sprintFocusQuery);

        List<SprintFocus> sprintFocusList = BeanMapper.mapList(sprintFocusEntityList,SprintFocus.class);

        joinTemplate.joinQuery(sprintFocusList);

        return sprintFocusList;
    }

    @Override
    public Pagination<SprintFocus> findSprintFocusPage(SprintFocusQuery sprintFocusQuery) {
        Pagination<SprintFocusEntity>  pagination = sprintFocusDao.findSprintFocusPage(sprintFocusQuery);

        List<SprintFocus> sprintFocusList = BeanMapper.mapList(pagination.getDataList(),SprintFocus.class);

        joinTemplate.joinQuery(sprintFocusList);

        return PaginationBuilder.build(pagination,sprintFocusList);
    }

    @Override
    public List<String> findFocusSprintIds(){
        String loginId = LoginContext.getLoginId();
        List<String> focusSprintIds = sprintFocusDao.findFocusSprintIds(loginId);
        return  focusSprintIds;
    }
}