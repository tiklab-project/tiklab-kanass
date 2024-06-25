package io.thoughtware.kanass.project.workPrivilege.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.kanass.project.workPrivilege.dao.WorkRoleFunctionDao;
import io.thoughtware.kanass.project.workPrivilege.entity.WorkRoleFunctionEntity;
import io.thoughtware.kanass.project.workPrivilege.model.WorkRoleFunction;
import io.thoughtware.kanass.project.workPrivilege.model.WorkRoleFunctionQuery;
import io.thoughtware.privilege.role.entity.RoleFunctionEntity;
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
public class WorkRoleFunctionServiceImpl implements WorkRoleFunctionService {

    @Autowired
    WorkRoleFunctionDao workRoleFunctionDao;

    @Override
    public String createWorkRoleFunction(@NotNull @Valid WorkRoleFunction workRoleFunction) {
        WorkRoleFunctionEntity workRoleFunctionEntity = BeanMapper.map(workRoleFunction, WorkRoleFunctionEntity.class);

        return workRoleFunctionDao.createWorkRoleFunction(workRoleFunctionEntity);
    }

    @Override
    public void updateWorkRoleFunction(@NotNull @Valid WorkRoleFunction workRoleFunction) {
        WorkRoleFunctionEntity workRoleFunctionEntity = BeanMapper.map(workRoleFunction, WorkRoleFunctionEntity.class);

        workRoleFunctionDao.updateWorkRoleFunction(workRoleFunctionEntity);
    }

    @Override
    public void updateWorkRoleAllFunction(WorkRoleFunction workRoleFunction) {
        String roleId = workRoleFunction.getRoleId();
        deleteRoleFunctionByRoleId(roleId);
        for (String functionId : workRoleFunction.getFunctionList()) {
            workRoleFunction.setFunctionId(functionId);
            createWorkRoleFunction(workRoleFunction);
        }

    }

    @Override
    public void deleteWorkRoleFunction(@NotNull String id) {
        workRoleFunctionDao.deleteWorkRoleFunction(id);
    }

    @Override
    public void deleteRoleFunctionByRoleId(String roleId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(WorkRoleFunctionEntity.class)
                .eq("roleId",roleId)
                .get();
        workRoleFunctionDao.deleteWorkRoleFunctionCondition(deleteCondition);
    }

    @Override
    public WorkRoleFunction findOne(String id) {
        WorkRoleFunctionEntity workRoleFunctionEntity = workRoleFunctionDao.findWorkRoleFunction(id);

        return BeanMapper.map(workRoleFunctionEntity, WorkRoleFunction.class);
    }

    @Override
    public List<WorkRoleFunction> findList(List<String> idList) {
        List<WorkRoleFunctionEntity> workRoleFunctionEntityList =  workRoleFunctionDao.findWorkRoleFunctionList(idList);

        return BeanMapper.mapList(workRoleFunctionEntityList,WorkRoleFunction.class);
    }

    @Override
    public WorkRoleFunction findWorkRoleFunction(@NotNull String id) {
        return findOne(id);
    }



    @Override
    public List<WorkRoleFunction> findWorkRoleFunctionList(WorkRoleFunctionQuery workRoleFunctionQuery) {
        List<WorkRoleFunctionEntity> workRoleFunctionEntityList = workRoleFunctionDao.findWorkRoleFunctionList(workRoleFunctionQuery);

        return BeanMapper.mapList(workRoleFunctionEntityList,WorkRoleFunction.class);
    }

    @Override
    public Pagination<WorkRoleFunction> findWorkRoleFunctionPage(WorkRoleFunctionQuery workRoleFunctionQuery) {

        Pagination<WorkRoleFunctionEntity>  pagination = workRoleFunctionDao.findWorkRoleFunctionPage(workRoleFunctionQuery);

        List<WorkRoleFunction> workRoleFunctionList = BeanMapper.mapList(pagination.getDataList(),WorkRoleFunction.class);

        return PaginationBuilder.build(pagination,workRoleFunctionList);
    }

}