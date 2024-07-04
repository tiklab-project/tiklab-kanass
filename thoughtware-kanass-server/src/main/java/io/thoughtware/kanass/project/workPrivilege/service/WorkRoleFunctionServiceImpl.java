package io.thoughtware.kanass.project.workPrivilege.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.form.field.model.FieldEx;
import io.thoughtware.form.field.service.FieldService;
import io.thoughtware.kanass.project.project.model.Project;
import io.thoughtware.kanass.project.project.service.ProjectService;
import io.thoughtware.kanass.project.workPrivilege.dao.WorkRoleFunctionDao;
import io.thoughtware.kanass.project.workPrivilege.entity.WorkRoleFunctionEntity;
import io.thoughtware.kanass.project.workPrivilege.model.*;
import io.thoughtware.kanass.sprint.model.Sprint;
import io.thoughtware.kanass.sprint.service.SprintService;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.kanass.workitem.service.WorkItemService;
import io.thoughtware.privilege.dmRole.model.DmRoleUser;
import io.thoughtware.privilege.dmRole.model.DmRoleUserQuery;
import io.thoughtware.privilege.dmRole.service.DmRoleUserService;
import io.thoughtware.privilege.role.model.Role;
import io.thoughtware.privilege.role.service.RoleFunctionService;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.user.user.model.User;
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

    @Autowired
    WorkFunctionService workFunctionService;

    @Autowired
    FieldService fieldService;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    WorkPrivilegeService workPrivilegeService;

    @Autowired
    ProjectService projectService;

    @Autowired
    SprintService sprintService;

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
    public Set<String> findUserWorkFunction(String userId, String workId) {
        Set<String> permissions = new HashSet<>();
        WorkItem workItem = workItemService.findWorkItem(workId);
        String projectId = workItem.getProject().getId();
        String workTypeId = workItem.getWorkType().getId();

        // 获取功能权限id
        WorkPrivilegeQuery workPrivilegeQuery = new WorkPrivilegeQuery();
        workPrivilegeQuery.setWorkTypeId(workTypeId);
        List<WorkPrivilege> workPrivilegeList = workPrivilegeService.findWorkPrivilegeList(workPrivilegeQuery);
        if(workPrivilegeList.size() > 0){
            WorkPrivilege workPrivilege = workPrivilegeList.get(0);
            String workPrivilegeId = workPrivilege.getId();

            // 用户所有角色集合
//            Map<String, Role> hashMapRole = new HashMap<>();
            List<String> roleIdList = new ArrayList<>();
            //查询项目具有角色列表
            DmRoleUserQuery dmRoleQuery = new DmRoleUserQuery();
            dmRoleQuery.setDomainId(projectId);
            dmRoleQuery.setUserId(userId);
            List<DmRoleUser> dmRoleUserList = dmRoleUserService.findDmRoleUserList(dmRoleQuery);

            for(DmRoleUser dmRoleUser:dmRoleUserList){
                Role role = dmRoleUser.getRole();
                if (Objects.equals(role.getType(), "2")) {
                    roleIdList.add(role.getId());
                }
            }

            // 查找虚拟角色列表
//            List<String> userVrole = findUserVrole(userId, workItem);
//            roleIdList.addAll(userVrole);
            for (String roleId : roleIdList) {
                // 根据roleId 获取功能列表
                WorkRoleFunctionQuery workRoleFunctionQuery = new WorkRoleFunctionQuery();
                workRoleFunctionQuery.setRoleId(roleId);
                workRoleFunctionQuery.setPrivilegeId(workPrivilegeId);
                List<WorkRoleFunction> workRoleFunctionList = findWorkRoleFunctionList(workRoleFunctionQuery);
                if(workRoleFunctionList == null || workRoleFunctionList.isEmpty()){
                    continue;
                }
                for (WorkRoleFunction workRoleFunction : workRoleFunctionList) {
                    String functionId = workRoleFunction.getFunctionId();
                    String functionType = workRoleFunction.getFunctionType();
                    if(functionType.equals("function")){
                        WorkFunction workFunction = workFunctionService.findWorkFunction(functionId);
                        String code = workFunction.getCode();
                        permissions.add(code);
                    }
                    if(functionType.equals("field")){
                        FieldEx field = fieldService.findField(functionId);
                        String code = field.getCode();
                        permissions.add(code);
                    }
                }
            }
        }
        return permissions;
    }


}