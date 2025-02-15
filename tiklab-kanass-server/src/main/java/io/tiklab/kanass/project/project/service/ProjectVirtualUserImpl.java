package io.tiklab.kanass.project.project.service;

import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.kanass.sprint.service.SprintService;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.service.WorkItemService;
import io.tiklab.privilege.vRole.model.VRole;
import io.tiklab.privilege.vRole.model.VRoleDomain;
import io.tiklab.privilege.vRole.service.VRoleUserServiceImpl;
import io.tiklab.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取项目虚拟角色的成员，用于发送消息，控制流程转换规则指定
 */
@Primary
@Service
public class ProjectVirtualUserImpl extends VRoleUserServiceImpl {
    @Autowired
    WorkItemService workItemService;


    @Autowired
    ProjectService projectService;

    @Autowired
    SprintService sprintService;

    @Override
    public List<User> findDmVRoleUser(VRoleDomain vRoleDomain) {
        String modelId = vRoleDomain.getModelId();
        String type = vRoleDomain.getType();
        List<User> userList = new ArrayList<>();
        WorkItem workItem = new WorkItem();
        for (VRole vRole : vRoleDomain.getvRoleList()) {
            String id = vRole.getId();
            switch (id){
                case "WORK_ITEM_AUDITOR":
                    workItem = workItemService.findWorkItem(modelId);
                    if(workItem != null){
                        User reporter = workItem.getReporter();
                        if(reporter != null){
                            userList.add(reporter);
                        }
                    }
                    break;
                case "WORK_ITEM_CREATOR":
                    workItem = workItemService.findWorkItem(modelId);
                    if(workItem != null){
                        User builder = workItem.getBuilder();
                        if(builder != null){
                            userList.add(builder);
                        }
                    }
                    break;
                case "WORK_ITEM_ASSIGNER":
                    workItem = workItemService.findWorkItem(modelId);
                    if(workItem != null){
                        User assigner = workItem.getAssigner();
                        if(assigner != null){
                            userList.add(assigner);
                        }
                    }
                    break;
                case "SPRINT_MASTER":
                    Sprint sprint = sprintService.findSprint(modelId);
                    if(sprint != null){
                        User assigner = sprint.getMaster();
                        if(assigner != null){
                            userList.add(assigner);
                        }
                    }
                    break;
                case "PROJECT_ADMINISTRATORS":
                    User projectVRoleUser = findProjectVRoleUser(modelId, type);
                    if(projectVRoleUser != null){
                        userList.add(projectVRoleUser);
                    }
                    break;
                default:
                    break;
            }
        }

        return userList;
    }

    private User findProjectVRoleUser(String domainId, String type){
        User user = new User();
        switch (type){
            case "workItem":
                WorkItem workItem = workItemService.findWorkItem(domainId);
                String projectId = workItem.getProject().getId();
                Project project = projectService.findProject(projectId);
                user = project.getMaster();
                break;
            case "sprint":
                Sprint sprint = sprintService.findSprint(domainId);
                String projectId1 = sprint.getProject().getId();
                Project project1 = projectService.findProject(projectId1);
                user = project1.getMaster();
                break;
            case "project":
                Project project2 = projectService.findProject(domainId);
                user = project2.getMaster();
                break;
            default:
                break;
        }
        return user;
    }
}
