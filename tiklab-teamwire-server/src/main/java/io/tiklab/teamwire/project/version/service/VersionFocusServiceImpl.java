package io.tiklab.teamwire.project.version.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teamwire.project.version.dao.VersionFocusDao;
import io.tiklab.teamwire.project.version.entity.VersionFocusEntity;
import io.tiklab.teamwire.project.version.model.VersionFocus;
import io.tiklab.teamwire.project.version.model.VersionFocusQuery;
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
}