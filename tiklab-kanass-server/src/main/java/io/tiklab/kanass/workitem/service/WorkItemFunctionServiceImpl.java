package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.kanass.workitem.dao.WorkItemFunctionDao;
import io.tiklab.kanass.workitem.entity.WorkItemFunctionEntity;
import io.tiklab.kanass.workitem.model.WorkItemFunction;
import io.tiklab.kanass.workitem.model.WorkItemFunctionQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项优先级服务
*/
@Service
public class WorkItemFunctionServiceImpl implements WorkItemFunctionService {

    @Autowired
    WorkItemFunctionDao workPriorityDao;

    @Override
    public String createWorkItemFunction(@NotNull @Valid WorkItemFunction workPriority) {
        WorkItemFunctionEntity workPriorityEntity = BeanMapper.map(workPriority, WorkItemFunctionEntity.class);

        return workPriorityDao.createWorkItemFunction(workPriorityEntity);
    }

    @Override
    public void updateWorkItemFunction(@NotNull @Valid WorkItemFunction workPriority) {
        WorkItemFunctionEntity workPriorityEntity = BeanMapper.map(workPriority, WorkItemFunctionEntity.class);

        workPriorityDao.updateWorkItemFunction(workPriorityEntity);
    }

    @Override
    public void deleteWorkItemFunction(@NotNull String id) {
        workPriorityDao.deleteWorkItemFunction(id);
    }

    @Override
    public WorkItemFunction findOne(String id) {
        WorkItemFunctionEntity workPriorityEntity = workPriorityDao.findWorkItemFunction(id);

        return BeanMapper.map(workPriorityEntity, WorkItemFunction.class);
    }

    @Override
    public List<WorkItemFunction> findList(List<String> idList) {
        List<WorkItemFunctionEntity> workPriorityEntityList =  workPriorityDao.findWorkItemFunctionList(idList);

        return BeanMapper.mapList(workPriorityEntityList,WorkItemFunction.class);
    }

    @Override
    public WorkItemFunction findWorkItemFunction(@NotNull String id) {
        return findOne(id);
    }

    @Override
    public List<WorkItemFunction> findAllWorkItemFunction() {
        List<WorkItemFunctionEntity> workPriorityEntityList =  workPriorityDao.findAllWorkItemFunction();

        return BeanMapper.mapList(workPriorityEntityList,WorkItemFunction.class);
    }

    @Override
    public List<WorkItemFunction> findWorkItemFunctionList(WorkItemFunctionQuery workPriorityQuery) {
        List<WorkItemFunctionEntity> workPriorityEntityList = workPriorityDao.findWorkItemFunctionList(workPriorityQuery);

        return BeanMapper.mapList(workPriorityEntityList,WorkItemFunction.class);
    }

    @Override
    public Pagination<WorkItemFunction> findWorkItemFunctionPage(WorkItemFunctionQuery workPriorityQuery) {

        Pagination<WorkItemFunctionEntity>  pagination = workPriorityDao.findWorkItemFunctionPage(workPriorityQuery);

        List<WorkItemFunction> workPriorityList = BeanMapper.mapList(pagination.getDataList(),WorkItemFunction.class);

        return PaginationBuilder.build(pagination,workPriorityList);
    }


}