package io.thoughtware.kanass.project.version.service;

import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.kanass.project.version.model.VersionState;
import io.thoughtware.kanass.project.version.model.VersionStateQuery;
import io.thoughtware.kanass.project.version.dao.VersionStateDao;
import io.thoughtware.kanass.project.version.entity.VersionStateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 迭代状态服务
*/
@Service
public class VersionStateServiceImpl implements VersionStateService {

    @Autowired
    VersionStateDao versionStateDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createVersionState(@NotNull @Valid VersionState versionState) {
        VersionStateEntity versionStateEntity = BeanMapper.map(versionState, VersionStateEntity.class);

        return versionStateDao.createVersionState(versionStateEntity);
    }

    @Override
    public void updateVersionState(@NotNull @Valid VersionState versionState) {
        VersionStateEntity versionStateEntity = BeanMapper.map(versionState, VersionStateEntity.class);

        versionStateDao.updateVersionState(versionStateEntity);
    }

    @Override
    public void deleteVersionState(@NotNull String id) {
        versionStateDao.deleteVersionState(id);
    }

    @Override
    public VersionState findOne(String id) {
        VersionStateEntity versionStateEntity = versionStateDao.findVersionState(id);

        VersionState versionState = BeanMapper.map(versionStateEntity, VersionState.class);
        return versionState;
    }

    @Override
    public List<VersionState> findList(List<String> idList) {
        List<VersionStateEntity> versionStateEntityList =  versionStateDao.findVersionStateList(idList);

        List<VersionState> versionStateList =  BeanMapper.mapList(versionStateEntityList,VersionState.class);
        return versionStateList;
    }

    @Override
    public VersionState findVersionState(@NotNull String id) {
        VersionState versionState = findOne(id);

        joinTemplate.joinQuery(versionState);
        return versionState;
    }

    @Override
    public List<VersionState> findAllVersionState() {
        List<VersionStateEntity> versionStateEntityList =  versionStateDao.findAllVersionState();

        List<VersionState> versionStateList =  BeanMapper.mapList(versionStateEntityList,VersionState.class);

        joinTemplate.joinQuery(versionStateList);
        return versionStateList;
    }

    @Override
    public List<VersionState> findVersionStateList(VersionStateQuery versionStateQuery) {
        List<VersionStateEntity> versionStateEntityList = versionStateDao.findVersionStateList(versionStateQuery);

        List<VersionState> versionStateList = BeanMapper.mapList(versionStateEntityList,VersionState.class);


        joinTemplate.joinQuery(versionStateList);

        return versionStateList;
    }

    @Override
    public Pagination<VersionState> findVersionStatePage(VersionStateQuery versionStateQuery) {

        Pagination<VersionStateEntity>  pagination = versionStateDao.findVersionStatePage(versionStateQuery);

        List<VersionState> versionStateList = BeanMapper.mapList(pagination.getDataList(),VersionState.class);

        joinTemplate.joinQuery(versionStateList);

        return PaginationBuilder.build(pagination,versionStateList);
    }
}