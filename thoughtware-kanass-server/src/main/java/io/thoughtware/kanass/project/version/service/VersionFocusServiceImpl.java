package io.thoughtware.kanass.project.version.service;

import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.kanass.project.version.dao.VersionFocusDao;
import io.thoughtware.kanass.project.version.entity.VersionFocusEntity;
import io.thoughtware.kanass.project.version.model.VersionFocus;
import io.thoughtware.kanass.project.version.model.VersionFocusQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 收藏迭代的服务
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
        List<VersionFocusEntity> versionFocusList = versionFocusDao.findVersionFocusList(versionFocusQuery);
        String id = versionFocusList.get(0).getId();

        versionFocusDao.deleteVersionFocus(id);
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

        joinTemplate.joinQuery(versionFocus);

        return versionFocus;
    }

    @Override
    public List<VersionFocus> findAllVersionFocus() {
        List<VersionFocusEntity> versionFocusEntityList =  versionFocusDao.findAllVersionFocus();

        List<VersionFocus> versionFocusList =  BeanMapper.mapList(versionFocusEntityList,VersionFocus.class);

        joinTemplate.joinQuery(versionFocusList);

        return versionFocusList;
    }

    @Override
    public List<VersionFocus> findVersionFocusList(VersionFocusQuery versionFocusQuery) {
        List<VersionFocusEntity> versionFocusEntityList = versionFocusDao.findVersionFocusList(versionFocusQuery);

        List<VersionFocus> versionFocusList = BeanMapper.mapList(versionFocusEntityList,VersionFocus.class);

        joinTemplate.joinQuery(versionFocusList);

        return versionFocusList;
    }

    @Override
    public Pagination<VersionFocus> findVersionFocusPage(VersionFocusQuery versionFocusQuery) {
        Pagination<VersionFocusEntity>  pagination = versionFocusDao.findVersionFocusPage(versionFocusQuery);

        List<VersionFocus> versionFocusList = BeanMapper.mapList(pagination.getDataList(),VersionFocus.class);

        joinTemplate.joinQuery(versionFocusList);

        return PaginationBuilder.build(pagination,versionFocusList);
    }

    @Override
    public List<String> findFocusVersionIds() {
        String loginId = LoginContext.getLoginId();
        List<String> focusVersionIds = versionFocusDao.findFocusVersionIds(loginId);

        return focusVersionIds;
    }
}