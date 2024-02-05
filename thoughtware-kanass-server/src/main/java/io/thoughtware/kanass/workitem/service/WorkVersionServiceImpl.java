package io.thoughtware.kanass.workitem.service;

import io.thoughtware.kanass.workitem.model.WorkVersion;
import io.thoughtware.kanass.workitem.model.WorkVersionQuery;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.kanass.workitem.dao.WorkVersionDao;
import io.thoughtware.kanass.workitem.entity.WorkVersionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 事项状态服务
 */
@Service
public class WorkVersionServiceImpl implements WorkVersionService {

    @Autowired
    WorkVersionDao workVersionDao;

    @Override
    public String createWorkVersion(@NotNull @Valid WorkVersion workVersion) {
        WorkVersionEntity workVersionEntity = BeanMapper.map(workVersion, WorkVersionEntity.class);

        return workVersionDao.createWorkVersion(workVersionEntity);
    }

    @Override
    public void createBatchWorkVersion(String valueStrings) {
        workVersionDao.createBatchWorkVersion(valueStrings);
    }

    @Override
    public void updateWorkVersion(@NotNull @Valid WorkVersion workVersion) {
        WorkVersionEntity workVersionEntity = BeanMapper.map(workVersion, WorkVersionEntity.class);

        workVersionDao.updateWorkVersion(workVersionEntity);
    }

    @Override
    public void deleteWorkVersion(@NotNull String id) {
        workVersionDao.deleteWorkVersion(id);
    }

    @Override
    public WorkVersion findOne(String id) {
        WorkVersionEntity workVersionEntity = workVersionDao.findWorkVersion(id);

        return BeanMapper.map(workVersionEntity, WorkVersion.class);
    }

    @Override
    public List<WorkVersion> findList(List<String> idList) {
        List<WorkVersionEntity> workVersionEntityList = workVersionDao.findWorkVersionList(idList);

        return BeanMapper.mapList(workVersionEntityList,WorkVersion.class);
    }

    @Override
    public WorkVersion findWorkVersion(@NotNull String id) {
        return findOne(id);
    }

    @Override
    public List<WorkVersion> findAllWorkVersion() {
        List<WorkVersionEntity> workVersionEntityList =  workVersionDao.findAllWorkVersion();

        return BeanMapper.mapList(workVersionEntityList,WorkVersion.class);
    }

    @Override
    public List<WorkVersion> findWorkVersionList(WorkVersionQuery workVersionQuery) {
        List<WorkVersionEntity> workVersionEntityList = workVersionDao.findWorkVersionList(workVersionQuery);

        return BeanMapper.mapList(workVersionEntityList,WorkVersion.class);
    }



    @Override
    public Pagination<WorkVersion> findWorkVersionPage(WorkVersionQuery workVersionQuery) {

        Pagination<WorkVersionEntity>  pagination = workVersionDao.findWorkVersionPage(workVersionQuery);

        List<WorkVersion> workVersionList = BeanMapper.mapList(pagination.getDataList(),WorkVersion.class);

        return PaginationBuilder.build(pagination,workVersionList);

    }

}