package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.kanass.workitem.dao.WorkItemRoleFunctionDao;
import io.tiklab.kanass.workitem.entity.WorkItemRoleFunctionEntity;
import io.tiklab.kanass.workitem.model.WorkItemRoleFunction;
import io.tiklab.kanass.workitem.model.WorkItemRoleFunctionQuery;
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
public class WorkItemRoleFunctionServiceImpl implements WorkItemRoleFunctionService {

    @Autowired
    WorkItemRoleFunctionDao workItemRoleFunctionDao;

    @Override
    public String createWorkItemRoleFunction(@NotNull @Valid WorkItemRoleFunction workItemRoleFunction) {
        WorkItemRoleFunctionEntity workItemRoleFunctionEntity = BeanMapper.map(workItemRoleFunction, WorkItemRoleFunctionEntity.class);

        return workItemRoleFunctionDao.createWorkItemRoleFunction(workItemRoleFunctionEntity);
    }

    @Override
    public void updateWorkItemRoleFunction(@NotNull @Valid WorkItemRoleFunction workItemRoleFunction) {
        WorkItemRoleFunctionEntity workItemRoleFunctionEntity = BeanMapper.map(workItemRoleFunction, WorkItemRoleFunctionEntity.class);

        workItemRoleFunctionDao.updateWorkItemRoleFunction(workItemRoleFunctionEntity);
    }
    
    @Override
    public void updateWorkItemRoleAllFunction(@NotNull @Valid WorkItemRoleFunction workItemRoleFunction){
        WorkItemRoleFunctionQuery workItemRoleFunctionQuery = new WorkItemRoleFunctionQuery();
        workItemRoleFunctionQuery.setRoleId(workItemRoleFunction.getRoleId());
        workItemRoleFunctionQuery.setWorkTypeId(workItemRoleFunction.getWorkTypeId());
        workItemRoleFunctionQuery.setFunctionType(workItemRoleFunction.getFunctionType());
        deleteWorkItemRoleFunctionCondition(workItemRoleFunctionQuery);

        for (String functionId : workItemRoleFunction.getFunctionListId()) {
            workItemRoleFunction.setFunctionId(functionId);
            createWorkItemRoleFunction(workItemRoleFunction);
        }
    }


    public void deleteWorkItemRoleFunctionCondition(@NotNull @Valid WorkItemRoleFunctionQuery workItemRoleFunctionQuery) {
        workItemRoleFunctionDao.deleteWorkItemRoleFunctionCondition(workItemRoleFunctionQuery);
    }


    @Override
    public void deleteWorkItemRoleFunction(@NotNull String id) {
        workItemRoleFunctionDao.deleteWorkItemRoleFunction(id);
    }

    @Override
    public WorkItemRoleFunction findOne(String id) {
        WorkItemRoleFunctionEntity workItemRoleFunctionEntity = workItemRoleFunctionDao.findWorkItemRoleFunction(id);

        return BeanMapper.map(workItemRoleFunctionEntity, WorkItemRoleFunction.class);
    }

    @Override
    public List<WorkItemRoleFunction> findList(List<String> idList) {
        List<WorkItemRoleFunctionEntity> workItemRoleFunctionEntityList =  workItemRoleFunctionDao.findWorkItemRoleFunctionList(idList);

        return BeanMapper.mapList(workItemRoleFunctionEntityList,WorkItemRoleFunction.class);
    }

    @Override
    public WorkItemRoleFunction findWorkItemRoleFunction(@NotNull String id) {
        return findOne(id);
    }

    @Override
    public List<WorkItemRoleFunction> findAllWorkItemRoleFunction() {
        List<WorkItemRoleFunctionEntity> workItemRoleFunctionEntityList =  workItemRoleFunctionDao.findAllWorkItemRoleFunction();

        return BeanMapper.mapList(workItemRoleFunctionEntityList,WorkItemRoleFunction.class);
    }

    @Override
    public List<WorkItemRoleFunction> findWorkItemRoleFunctionList(WorkItemRoleFunctionQuery workItemRoleFunctionQuery) {
        List<WorkItemRoleFunctionEntity> workItemRoleFunctionEntityList = workItemRoleFunctionDao.findWorkItemRoleFunctionList(workItemRoleFunctionQuery);

        return BeanMapper.mapList(workItemRoleFunctionEntityList,WorkItemRoleFunction.class);
    }

    @Override
    public Pagination<WorkItemRoleFunction> findWorkItemRoleFunctionPage(WorkItemRoleFunctionQuery workItemRoleFunctionQuery) {

        Pagination<WorkItemRoleFunctionEntity>  pagination = workItemRoleFunctionDao.findWorkItemRoleFunctionPage(workItemRoleFunctionQuery);

        List<WorkItemRoleFunction> workItemRoleFunctionList = BeanMapper.mapList(pagination.getDataList(),WorkItemRoleFunction.class);

        return PaginationBuilder.build(pagination,workItemRoleFunctionList);
    }

}