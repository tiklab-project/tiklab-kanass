package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.form.field.model.FieldEx;
import io.tiklab.form.field.service.FieldService;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.kanass.sprint.service.SprintService;
import io.tiklab.kanass.workitem.dao.WorkItemRoleFunctionDmDao;
import io.tiklab.kanass.workitem.entity.WorkItemRoleFunctionDmEntity;
import io.tiklab.kanass.workitem.model.*;
import io.tiklab.message.message.model.MessageNoticePatch;
import io.tiklab.privilege.dmRole.model.DmRole;
import io.tiklab.privilege.dmRole.model.DmRoleUser;
import io.tiklab.privilege.dmRole.model.DmRoleUserQuery;
import io.tiklab.privilege.dmRole.service.DmRoleUserService;
import io.tiklab.privilege.role.model.Role;
import io.tiklab.privilege.role.service.RoleService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* 事项优先级服务
*/
@Service
public class WorkItemRoleFunctionDmServiceImpl implements WorkItemRoleFunctionDmService {
    public final ExecutorService executorService = Executors.newCachedThreadPool();
    @Autowired
    WorkItemRoleFunctionDmDao workItemRoleFunctionDmDao;
    
    @Autowired
    WorkItemFunctionService workItemFunctionService;

    @Autowired
    FieldService fieldService;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    ProjectService projectService;

    @Autowired
    WorkItemRoleFunctionService workItemRoleFunctionService;

    @Autowired
    SprintService sprintService;

    @Autowired
    RoleService roleService;

    @Autowired
    DmRoleUserService dmRoleUserService;

    @Autowired
    WorkTypeDmService workTypeDmService;

    @Override
    public String createWorkItemRoleFunctionDm(@NotNull @Valid WorkItemRoleFunctionDm workItemRoleFunctionDm) {
        WorkItemRoleFunctionDmEntity workItemRoleFunctionDmEntity = BeanMapper.map(workItemRoleFunctionDm, WorkItemRoleFunctionDmEntity.class);

        return workItemRoleFunctionDmDao.createWorkItemRoleFunctionDm(workItemRoleFunctionDmEntity);
    }

    @Override
    public String copyWorkItemRoleFunctionDm(@NotNull @Valid WorkItemRoleFunctionDm workItemRoleFunctionDm) {
        String newWorkTypeId = workItemRoleFunctionDm.getNewWorkTypeId();
        String workTypeId = workItemRoleFunctionDm.getWorkTypeId();
        String domainId = workItemRoleFunctionDm.getDomainId();
        WorkItemRoleFunctionQuery workItemRoleFunctionQuery = new WorkItemRoleFunctionQuery();
        workItemRoleFunctionQuery.setWorkTypeId(workTypeId);
        List<WorkItemRoleFunction> workItemRoleFunctionList = workItemRoleFunctionService.findWorkItemRoleFunctionList(workItemRoleFunctionQuery);

        for (WorkItemRoleFunction itemRoleFunction : workItemRoleFunctionList) {
            WorkItemRoleFunctionDm workItemRoleFunctionDm1 = new WorkItemRoleFunctionDm();
            workItemRoleFunctionDm1.setWorkTypeId(newWorkTypeId);
            workItemRoleFunctionDm1.setFunctionId(itemRoleFunction.getFunctionId());
            workItemRoleFunctionDm1.setFunctionType(itemRoleFunction.getFunctionType());
            workItemRoleFunctionDm1.setDomainId(domainId);
            workItemRoleFunctionDm1.setRoleId(itemRoleFunction.getRoleId());
            createWorkItemRoleFunctionDm(workItemRoleFunctionDm1);
        }

        return null;
    }

    @Override
    public void updateWorkItemRoleFunctionDm1() {
        for (Project project : projectService.findAllProject()) {
            String projectId = project.getId();

            WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
            workTypeDmQuery.setProjectId(projectId);
            List<WorkTypeDm> workTypeDmList = workTypeDmService.findWorkTypeDmList(workTypeDmQuery);
            for (WorkTypeDm workTypeDm : workTypeDmList) {
                String id = workTypeDm.getWorkType().getId();
                String newWorkTypeId = workTypeDm.getId();
                WorkItemRoleFunctionDm workItemRoleFunctionDm = new WorkItemRoleFunctionDm();
                workItemRoleFunctionDm.setDomainId(projectId);
                workItemRoleFunctionDm.setNewWorkTypeId(newWorkTypeId);
                workItemRoleFunctionDm.setWorkTypeId(id);
                executorService.submit(() -> {
                    copyWorkItemRoleFunctionDm(workItemRoleFunctionDm);
                });

            }

        }

    }

    @Override
    public void updateWorkItemRoleFunctionDm(@NotNull @Valid WorkItemRoleFunctionDm workItemRoleFunctionDm) {
        WorkItemRoleFunctionDmEntity workItemRoleFunctionDmEntity = BeanMapper.map(workItemRoleFunctionDm, WorkItemRoleFunctionDmEntity.class);

        workItemRoleFunctionDmDao.updateWorkItemRoleFunctionDm(workItemRoleFunctionDmEntity);
    }

    @Override
    public void updateWorkItemRoleAllFunctionDm(WorkItemRoleFunctionDm workItemRoleFunctionDm) {
        WorkItemRoleFunctionDmQuery workItemRoleFunctionDmQuery = new WorkItemRoleFunctionDmQuery();
        workItemRoleFunctionDmQuery.setRoleId(workItemRoleFunctionDm.getRoleId());
        workItemRoleFunctionDmQuery.setWorkTypeId(workItemRoleFunctionDm.getWorkTypeId());
        workItemRoleFunctionDmQuery.setFunctionType(workItemRoleFunctionDm.getFunctionType());
        deleteWorkItemRoleFunctionCondition(workItemRoleFunctionDmQuery);

        for (String functionId : workItemRoleFunctionDm.getFunctionListId()) {
            workItemRoleFunctionDm.setFunctionId(functionId);
            createWorkItemRoleFunctionDm(workItemRoleFunctionDm);
        }
    }

    public void deleteWorkItemRoleFunctionCondition(WorkItemRoleFunctionDmQuery workItemRoleFunctionDmQuery){
        workItemRoleFunctionDmDao.deleteWorkItemRoleFunctionDmCondition(workItemRoleFunctionDmQuery);
    }
    @Override
    public void deleteWorkItemRoleFunctionDm(@NotNull String id) {
        workItemRoleFunctionDmDao.deleteWorkItemRoleFunctionDm(id);
    }

    @Override
    public WorkItemRoleFunctionDm findOne(String id) {
        WorkItemRoleFunctionDmEntity workItemRoleFunctionDmEntity = workItemRoleFunctionDmDao.findWorkItemRoleFunctionDm(id);

        return BeanMapper.map(workItemRoleFunctionDmEntity, WorkItemRoleFunctionDm.class);
    }

    @Override
    public List<WorkItemRoleFunctionDm> findList(List<String> idList) {
        List<WorkItemRoleFunctionDmEntity> workItemRoleFunctionDmEntityList =  workItemRoleFunctionDmDao.findWorkItemRoleFunctionDmList(idList);

        return BeanMapper.mapList(workItemRoleFunctionDmEntityList,WorkItemRoleFunctionDm.class);
    }

    @Override
    public WorkItemRoleFunctionDm findWorkItemRoleFunctionDm(@NotNull String id) {
        return findOne(id);
    }

    @Override
    public List<String> findWorkItemRoleFunctionDmCode(WorkItemRoleFunctionDmQuery workItemRoleFunctionQuery) {
        List<String> codes = new ArrayList<>();

        // 获取人员的虚拟角色
        String workId = workItemRoleFunctionQuery.getWorkId();
        String userId = workItemRoleFunctionQuery.getUserId();
        WorkItem workItem = workItemService.findWorkItem(workId);
        List<String> userVrole = findUserVrole(userId, workItem);

        // 获取人员的角色
        String domainId = workItemRoleFunctionQuery.getDomainId();
        DmRoleUserQuery dmRoleUserQuery = new DmRoleUserQuery();
        dmRoleUserQuery.setDomainId(domainId);
        dmRoleUserQuery.setUserId(userId);
        List<DmRoleUser> dmRoleUserList = dmRoleUserService.findDmRoleUserList(dmRoleUserQuery);
//        List<String> roleIds = dmRoleUserList.stream().map(dmRoleUser -> dmRoleUser.getRole().getId()).collect(Collectors.toList());
        for (DmRoleUser dmRoleUser : dmRoleUserList) {
            DmRole dmRole = dmRoleUser.getDmRole();
            String id = dmRole.getRole().getId();
            Role role = roleService.findRole(id);
            String parentId = role.getParentId();
            if(parentId != null){
                userVrole.add(parentId);
            }else {
                userVrole.add(role.getId());
            }
        }


        String[] userVroles = userVrole.toArray(new String[userVrole.size()]);
        workItemRoleFunctionQuery.setRoleIds(userVroles);
        workItemRoleFunctionQuery.setWorkTypeId(workItem.getWorkType().getId());
        List<WorkItemRoleFunctionDm> workItemRoleFunctionDmList = findWorkItemRoleFunctionDmList(workItemRoleFunctionQuery);
        for (WorkItemRoleFunctionDm workItemRoleFunctionDm : workItemRoleFunctionDmList) {
            String functionType = workItemRoleFunctionDm.getFunctionType();
            String functionId = workItemRoleFunctionDm.getFunctionId();
            if(functionType.equals("field")){
                FieldEx field = fieldService.findField(functionId);
                String code = field.getCode();
                codes.add(code);
            }

            if(functionType.equals("function")){
                WorkItemFunction workItemFunction = workItemFunctionService.findWorkItemFunction(functionId);
                String code = workItemFunction.getCode();
                codes.add(code);
            }
        }

        return codes;
    }

    public List<String> findUserVrole(String userId, WorkItem workItem){
        List<String> roleIds = new ArrayList<>();
        User builder = workItem.getBuilder();
        if(builder.getId().equals(userId)){
            roleIds.add("WORK_ITEM_CREATOR");
        }

        User assigner = workItem.getAssigner();
        if(assigner.getId().equals(userId)){
            roleIds.add("WORK_ITEM_ASSIGNER");
        }

        User reporter = workItem.getReporter();
        if(!Objects.isNull(reporter) && reporter.getId().equals(userId)){
            roleIds.add("WORK_ITEM_AUDITOR");
        }

        String projectId = workItem.getProject().getId();
        Project project = projectService.findProject(projectId);
        if(project.getMaster().getId().equals(userId)){
            roleIds.add("PROJECT_ADMINISTRATORS");
        }

        if(workItem.getSprint() != null){
            String sprintId = workItem.getSprint().getId();
            Sprint sprint = sprintService.findSprint(sprintId);
            if(sprint.getMaster().getId().equals(userId)){
                roleIds.add("SPRINT_MASTER");
            }
        }

        return roleIds;
    }

    @Override
    public List<WorkItemRoleFunctionDm> findAllWorkItemRoleFunctionDm() {
        List<WorkItemRoleFunctionDmEntity> workItemRoleFunctionDmEntityList =  workItemRoleFunctionDmDao.findAllWorkItemRoleFunctionDm();

        return BeanMapper.mapList(workItemRoleFunctionDmEntityList,WorkItemRoleFunctionDm.class);
    }

    @Override
    public List<WorkItemRoleFunctionDm> findWorkItemRoleFunctionDmList(WorkItemRoleFunctionDmQuery workItemRoleFunctionDmQuery) {
        List<WorkItemRoleFunctionDmEntity> workItemRoleFunctionDmEntityList = workItemRoleFunctionDmDao.findWorkItemRoleFunctionDmList(workItemRoleFunctionDmQuery);

        return BeanMapper.mapList(workItemRoleFunctionDmEntityList,WorkItemRoleFunctionDm.class);
    }

    @Override
    public Pagination<WorkItemRoleFunctionDm> findWorkItemRoleFunctionDmPage(WorkItemRoleFunctionDmQuery workItemRoleFunctionDmQuery) {

        Pagination<WorkItemRoleFunctionDmEntity>  pagination = workItemRoleFunctionDmDao.findWorkItemRoleFunctionDmPage(workItemRoleFunctionDmQuery);

        List<WorkItemRoleFunctionDm> workItemRoleFunctionDmList = BeanMapper.mapList(pagination.getDataList(),WorkItemRoleFunctionDm.class);

        return PaginationBuilder.build(pagination,workItemRoleFunctionDmList);
    }


}