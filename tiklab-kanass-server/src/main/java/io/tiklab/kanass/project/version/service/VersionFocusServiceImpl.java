package io.tiklab.kanass.project.version.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.project.version.dao.VersionFocusDao;
import io.tiklab.kanass.project.version.entity.VersionFocusEntity;
import io.tiklab.kanass.project.version.model.VersionFocus;
import io.tiklab.kanass.project.version.model.VersionFocusQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 收藏版本的接口
*/
@Service
public class VersionFocusServiceImpl implements VersionFocusService {

    @Autowired
    VersionFocusDao versionFocusDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createVersionFocus(@NotNull @Valid VersionFocus versionFocus) {
        VersionFocusQuery versionFocusQuery = new VersionFocusQuery();
        versionFocusQuery.setVersionId(versionFocus.getVersion().getId());
        versionFocusQuery.setMasterId(versionFocus.getMasterId());
        List<VersionFocus> versionFocusList = findVersionFocusList(versionFocusQuery);
        int size = versionFocusList.size();
        if(size > 0){
            throw new ApplicationException("已经关注过");
        }
        VersionFocusEntity versionFocusEntity = BeanMapper.map(versionFocus, VersionFocusEntity.class);

        return versionFocusDao.createVersionFocus(versionFocusEntity);
    }

    @Override
    public void updateVersionFocus(@NotNull @Valid VersionFocus versionFocus) {
        VersionFocusEntity versionFocusEntity = BeanMapper.map(versionFocus, VersionFocusEntity.class);

        versionFocusDao.updateVersionFocus(versionFocusEntity);
    }

    @Override
    public void deleteVersionFocus(@NotNull String id) {
        versionFocusDao.deleteVersionFocus(id);
    }

    @Override
    public void deleteVersionFocusByQuery(VersionFocusQuery versionFocusQuery) {

        DeleteCondition deleteCondition = DeleteBuilders.createDelete(VersionFocusEntity.class)
                .eq("masterId", versionFocusQuery.getMasterId())
                .eq("versionId", versionFocusQuery.getVersionId())
                .get();
        versionFocusDao.deleteVersionFocus(deleteCondition);
    }

    @Override
    public VersionFocus findOne(String id) {
        VersionFocusEntity versionFocusEntity = versionFocusDao.findVersionFocus(id);

        VersionFocus versionFocus = BeanMapper.map(versionFocusEntity, VersionFocus.class);
        return versionFocus;
    }

    @Override
    public List<VersionFocus> findList(List<String> idList) {
        List<VersionFocusEntity> versionFocusEntityList =  versionFocusDao.findVersionFocusList(idList);

        List<VersionFocus> versionFocusList =  BeanMapper.mapList(versionFocusEntityList,VersionFocus.class);
        return versionFocusList;
    }

    @Override
    public VersionFocus findVersionFocus(@NotNull String id) {
        VersionFocus versionFocus = findOne(id);

        joinTemplate.joinQuery(versionFocus, new String[]{"version"});

        return versionFocus;
    }

    @Override
    public List<VersionFocus> findAllVersionFocus() {
        List<VersionFocusEntity> versionFocusEntityList =  versionFocusDao.findAllVersionFocus();

        List<VersionFocus> versionFocusList =  BeanMapper.mapList(versionFocusEntityList,VersionFocus.class);

        joinTemplate.joinQuery(versionFocusList, new String[]{"version"});

        return versionFocusList;
    }

    @Override
    public List<VersionFocus> findVersionFocusList(VersionFocusQuery versionFocusQuery) {
        List<VersionFocusEntity> versionFocusEntityList = versionFocusDao.findVersionFocusList(versionFocusQuery);

        List<VersionFocus> versionFocusList = BeanMapper.mapList(versionFocusEntityList,VersionFocus.class);

        joinTemplate.joinQuery(versionFocusList, new String[]{"version"});

        return versionFocusList;
    }

    @Override
    public Pagination<VersionFocus> findVersionFocusPage(VersionFocusQuery versionFocusQuery) {
        Pagination<VersionFocusEntity>  pagination = versionFocusDao.findVersionFocusPage(versionFocusQuery);

        List<VersionFocus> versionFocusList = BeanMapper.mapList(pagination.getDataList(),VersionFocus.class);

        joinTemplate.joinQuery(versionFocusList, new String[]{"version"});

        return PaginationBuilder.build(pagination,versionFocusList);
    }

    @Override
    public List<String> findFocusVersionIds() {
        String loginId = LoginContext.getLoginId();
        List<String> focusVersionIds = versionFocusDao.findFocusVersionIds(loginId);

        return focusVersionIds;
    }
}