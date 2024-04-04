package io.thoughtware.kanass.support.service;

import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.kanass.support.dao.SystemUrlDao;
import io.thoughtware.kanass.support.entity.SystemUrlEntity;
import io.thoughtware.kanass.support.model.SystemUrl;
import io.thoughtware.kanass.support.model.SystemUrlQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 最近访问的服务
*/
@Service
public class SystemUrlServiceImpl implements SystemUrlService {

    @Autowired
    SystemUrlDao systemUrlDao;

    @Autowired
    JoinTemplate joinTemplate;
    @Override
    public String createSystemUrl(@NotNull @Valid SystemUrl systemUrl) {
        SystemUrlEntity systemUrlEntity = BeanMapper.map(systemUrl, SystemUrlEntity.class);

        return systemUrlDao.createSystemUrl(systemUrlEntity);
    }

    @Override
    public void updateSystemUrl(@NotNull @Valid SystemUrl systemUrl) {
        SystemUrlEntity systemUrlEntity = BeanMapper.map(systemUrl, SystemUrlEntity.class);

        systemUrlDao.updateSystemUrl(systemUrlEntity);
    }

    @Override
    public void deleteSystemUrl(@NotNull String id) {
        systemUrlDao.deleteSystemUrl(id);
    }

    @Override
    public SystemUrl findOne(String id) {
        SystemUrlEntity systemUrlEntity = systemUrlDao.findSystemUrl(id);

        SystemUrl systemUrl = BeanMapper.map(systemUrlEntity, SystemUrl.class);
        return systemUrl;
    }

    @Override
    public List<SystemUrl> findList(List<String> idList) {
        List<SystemUrlEntity> systemUrlEntityList =  systemUrlDao.findSystemUrlList(idList);

        List<SystemUrl> systemUrlList =  BeanMapper.mapList(systemUrlEntityList,SystemUrl.class);
        return systemUrlList;
    }

    @Override
    public SystemUrl findSystemUrl(@NotNull String id) {
        SystemUrl systemUrl = findOne(id);

        joinTemplate.joinQuery(systemUrl);

        return systemUrl;
    }

    @Override
    public List<SystemUrl> findAllSystemUrl() {
        List<SystemUrlEntity> systemUrlEntityList =  systemUrlDao.findAllSystemUrl();

        List<SystemUrl> systemUrlList =  BeanMapper.mapList(systemUrlEntityList,SystemUrl.class);

        joinTemplate.joinQuery(systemUrlList);

        return systemUrlList;
    }

    @Override
    public List<SystemUrl> findSystemUrlList(SystemUrlQuery systemUrlQuery) {
        List<SystemUrlEntity> systemUrlEntityList = systemUrlDao.findSystemUrlList(systemUrlQuery);

        List<SystemUrl> systemUrlList = BeanMapper.mapList(systemUrlEntityList,SystemUrl.class);

        joinTemplate.joinQuery(systemUrlList);

        return systemUrlList;
    }

    @Override
    public Pagination<SystemUrl> findSystemUrlPage(SystemUrlQuery systemUrlQuery) {
        Pagination<SystemUrlEntity>  pagination = systemUrlDao.findSystemUrlPage(systemUrlQuery);

        List<SystemUrl> systemUrlList = BeanMapper.mapList(pagination.getDataList(),SystemUrl.class);

        joinTemplate.joinQuery(systemUrlList);

        return PaginationBuilder.build(pagination,systemUrlList);
    }

    @Override
    public Integer findSystemUrlNumber() {
        Integer systemUrlNumber = systemUrlDao.findSystemUrlNumber();

        return systemUrlNumber;
    }
}