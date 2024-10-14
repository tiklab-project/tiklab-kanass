package io.tiklab.kanass.support.service;

import io.tiklab.flow.statenode.model.StateNodeRoleField;
import io.tiklab.flow.statenode.model.StateNodeRoleFieldQuery;
import io.tiklab.flow.statenode.service.StateNodeRoleFieldService;
import io.tiklab.flow.statenode.service.StateNodeUserFieldService;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.kanass.sprint.service.SprintService;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.service.WorkItemService;
import io.tiklab.privilege.dmRole.model.DmRoleUser;
import io.tiklab.privilege.dmRole.model.DmRoleUserQuery;
import io.tiklab.privilege.dmRole.service.DmRoleUserService;
import io.tiklab.privilege.role.model.Role;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* 图标服务
*/
@Service
public class StateNodeUserFieldServiceImpl implements StateNodeUserFieldService {
    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    ProjectService projectService;

    @Autowired
    DmRoleUserService dmRoleUserService;

    @Autowired
    StateNodeRoleFieldService stateNodeRoleFieldService;

    @Autowired
    SprintService sprintService;

    @Override
    public Set<String> findStateNodeUserFieldList(String userId, String workId) {
        Set<String> permissions = new HashSet<>();

        WorkItem workItem = workItemService.findWorkItem(workId);
        String projectId = workItem.getProject().getId();
        String stateNodeId = workItem.getWorkStatus().getId();
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
        List<String> userVrole = findUserVrole(userId, workItem);
        roleIdList.addAll(userVrole);
        if(roleIdList.size()> 0){
            StateNodeRoleFieldQuery stateNodeRoleFieldQuery = new StateNodeRoleFieldQuery();
            stateNodeRoleFieldQuery.setNodeId(stateNodeId);

            String[] roleIds = roleIdList.toArray(new String[roleIdList.size()]);
            stateNodeRoleFieldQuery.setRoleIds(roleIds);
            List<StateNodeRoleField> stateNodeRoleFieldList = stateNodeRoleFieldService.findStateNodeRoleFieldList(stateNodeRoleFieldQuery);

            for (StateNodeRoleField stateNodeRoleField : stateNodeRoleFieldList) {
                if(stateNodeRoleField.getAction() == 1){
                    String code = stateNodeRoleField.getField().getCode();
                    permissions.add(code);
                }
            }
        }

        return permissions;
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
}