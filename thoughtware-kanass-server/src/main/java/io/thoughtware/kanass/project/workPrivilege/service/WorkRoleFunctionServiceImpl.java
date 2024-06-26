package io.thoughtware.kanass.project.workPrivilege.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.kanass.project.workPrivilege.dao.WorkRoleFunctionDao;
import io.thoughtware.kanass.project.workPrivilege.entity.WorkRoleFunctionEntity;
import io.thoughtware.kanass.project.workPrivilege.model.WorkRoleFunction;
import io.thoughtware.kanass.project.workPrivilege.model.WorkRoleFunctionQuery;
import io.thoughtware.privilege.dmRole.model.DmRoleUser;
import io.thoughtware.privilege.dmRole.model.DmRoleUserQuery;
import io.thoughtware.privilege.dmRole.service.DmRoleUserService;
import io.thoughtware.privilege.function.model.Function;
import io.thoughtware.privilege.role.model.Role;
import io.thoughtware.privilege.role.model.RoleFunction;
import io.thoughtware.privilege.role.model.RoleFunctionQuery;
import io.thoughtware.privilege.role.service.RoleFunctionService;
import io.thoughtware.toolkit.beans.BeanMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
* 事项优先级服务
*/
@Service
public class WorkRoleFunctionServiceImpl implements WorkRoleFunctionService {

    @Autowired
    DmRoleUserService dmRoleUserService;

    @Autowired
    WorkRoleFunctionDao workRoleFunctionDao;

    @Autowired
    RoleFunctionService roleFunctionService;

    @Override
    public String createWorkRoleFunction(@NotNull @Valid WorkRoleFunction workRoleFunction) {
        WorkRoleFunctionEntity workRoleFunctionEntity = BeanMapper.map(workRoleFunction, WorkRoleFunctionEntity.class);

        return workRoleFunctionDao.createWorkRoleFunction(workRoleFunctionEntity);
    }

    @Override
    public void copyAllWorkRoleFunction(@NotNull @Valid WorkRoleFunction workRoleFunction) {
        String privilegeId = workRoleFunction.getPrivilegeId();
        String newPrivilegeId = workRoleFunction.getNewPrivilegeId();
        WorkRoleFunctionQuery workRoleFunctionQuery = new WorkRoleFunctionQuery();
        workRoleFunctionQuery.setPrivilegeId(privilegeId);
        workRoleFunctionQuery.setFunctionType("field");
        List<WorkRoleFunction> workRoleFunctionList = findWorkRoleFunctionList(workRoleFunctionQuery);
        for (WorkRoleFunction roleFunction : workRoleFunctionList) {
            roleFunction.setPrivilegeId(newPrivilegeId);
            roleFunction.setId(null);
            String workRoleFunction1 = createWorkRoleFunction(roleFunction);
        }

    }

    @Override
    public void updateWorkRoleFunction(@NotNull @Valid WorkRoleFunction workRoleFunction) {
        WorkRoleFunctionEntity workRoleFunctionEntity = BeanMapper.map(workRoleFunction, WorkRoleFunctionEntity.class);

        workRoleFunctionDao.updateWorkRoleFunction(workRoleFunctionEntity);
    }

    @Override
    public void updateWorkRoleAllFunction(WorkRoleFunction workRoleFunction) {
        deleteRoleFunctionByRoleId(workRoleFunction);
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
    public void deleteRoleFunctionByRoleId(WorkRoleFunction workRoleFunction) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(WorkRoleFunctionEntity.class)
                .eq("roleId",workRoleFunction.getRoleId())
                .eq("functionType", workRoleFunction.getFunctionType())
                .eq("privilegeId", workRoleFunction.getPrivilegeId())
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

    @Override
    public List<String> findUserWorkFunction(String userId, String workId, String projectId) {

        Set<String> permissions = new HashSet<>();

        // 用户所有角色集合
        Map<String, Role> hashMapRole = new HashMap<>();

        //查询项目具有角色列表
        DmRoleUserQuery dmRoleQuery = new DmRoleUserQuery();
        dmRoleQuery.setDomainId(projectId);
        dmRoleQuery.setUserId(userId);
        List<DmRoleUser> dmRoleUserList = dmRoleUserService.findDmRoleUserList(dmRoleQuery);

        for(DmRoleUser dmRoleUser:dmRoleUserList){
            Role role = dmRoleUser.getRole();
            if (Objects.equals(role.getType(), "2")) {
                hashMapRole.put(role.getId(), role);
            }
        }

        for(String key:hashMapRole.keySet()){
            RoleFunctionQuery roleFunctionQuery = new RoleFunctionQuery();
            roleFunctionQuery.setRoleId(key);
            List<RoleFunction> roleFunctionDmList = roleFunctionService.findRoleFunctionList(roleFunctionQuery);
            if(roleFunctionDmList == null || roleFunctionDmList.isEmpty()){
                continue;
            }
            for(RoleFunction roleFunction:roleFunctionDmList){
                Function function = roleFunction.getFunction();
                if(StringUtils.isEmpty(function.getCode())){
                    continue;
                }
                if (Objects.equals(function.getType(), "2")) {
                    //添加到许可列表
                    permissions.add(function.getCode());
                }
            }
        }


        return null;
    }

}