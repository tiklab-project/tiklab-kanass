package io.thoughtware.kanass.project.workPrivilege.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.kanass.project.workPrivilege.dao.WorkFunctionDao;
import io.thoughtware.kanass.project.workPrivilege.entity.WorkFunctionEntity;
import io.thoughtware.kanass.project.workPrivilege.model.WorkFunction;
import io.thoughtware.kanass.project.workPrivilege.model.WorkFunctionQuery;
import io.thoughtware.toolkit.beans.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项优先级服务
*/
@Service
public class WorkFunctionServiceImpl implements WorkFunctionService {

    @Autowired
    WorkFunctionDao workFunctionDao;

    @Override
    public String createWorkFunction(@NotNull @Valid WorkFunction workFunction) {
        WorkFunctionEntity workFunctionEntity = BeanMapper.map(workFunction, WorkFunctionEntity.class);

        return workFunctionDao.createWorkFunction(workFunctionEntity);
    }

    @Override
    public void updateWorkFunction(@NotNull @Valid WorkFunction workFunction) {
        WorkFunctionEntity workFunctionEntity = BeanMapper.map(workFunction, WorkFunctionEntity.class);

        workFunctionDao.updateWorkFunction(workFunctionEntity);
    }

    @Override
    public void deleteWorkFunction(@NotNull String id) {
        workFunctionDao.deleteWorkFunction(id);
    }

    @Override
    public WorkFunction findOne(String id) {
        WorkFunctionEntity workFunctionEntity = workFunctionDao.findWorkFunction(id);

        return BeanMapper.map(workFunctionEntity, WorkFunction.class);
    }

    @Override
    public List<WorkFunction> findList(List<String> idList) {
        List<WorkFunctionEntity> workFunctionEntityList =  workFunctionDao.findWorkFunctionList(idList);

        return BeanMapper.mapList(workFunctionEntityList,WorkFunction.class);
    }

    @Override
    public WorkFunction findWorkFunction(@NotNull String id) {
        return findOne(id);
    }

    @Override
    public List<WorkFunction> findAllWorkFunction() {
        List<WorkFunctionEntity> workFunctionEntityList =  workFunctionDao.findAllWorkFunction();

        return BeanMapper.mapList(workFunctionEntityList,WorkFunction.class);
    }

    @Override
    public List<WorkFunction> findWorkFunctionList(WorkFunctionQuery workFunctionQuery) {
        List<WorkFunctionEntity> workFunctionEntityList = workFunctionDao.findWorkFunctionList(workFunctionQuery);

        return BeanMapper.mapList(workFunctionEntityList,WorkFunction.class);
    }

    @Override
    public Pagination<WorkFunction> findWorkFunctionPage(WorkFunctionQuery workFunctionQuery) {

        Pagination<WorkFunctionEntity>  pagination = workFunctionDao.findWorkFunctionPage(workFunctionQuery);

        List<WorkFunction> workFunctionList = BeanMapper.mapList(pagination.getDataList(),WorkFunction.class);

        return PaginationBuilder.build(pagination,workFunctionList);
    }

}