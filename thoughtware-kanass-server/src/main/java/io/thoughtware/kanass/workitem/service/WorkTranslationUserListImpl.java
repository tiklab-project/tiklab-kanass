package io.thoughtware.kanass.workitem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.flow.transition.service.TransitionRuleService;
import io.thoughtware.flow.transition.service.TransitionRuleUserService;
import io.thoughtware.kanass.project.project.model.Project;
import io.thoughtware.kanass.project.project.service.ProjectService;
import io.thoughtware.kanass.workitem.entity.WorkItemEntity;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.kanass.workitem.model.WorkItemQuery;
import io.thoughtware.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Primary
@Service
public class WorkTranslationUserListImpl implements TransitionRuleUserService {
    @Autowired
    TransitionRuleService transitionRuleService;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    ProjectService projectService;

    @Override
    public User findBusinessUser(String allocationUserId, String domainId) {
        User user = new User();
        WorkItem workItem = new WorkItem();
        switch (allocationUserId){
            case "WORK_ITEM_AUDITOR":
                workItem = workItemService.findWorkItem(domainId);
                user = workItem.getReporter();
                break;
            case "WORK_ITEM_ASSIGNER":
                workItem = workItemService.findWorkItem(domainId);
                user = workItem.getAssigner();
                break;
            case "PROJECT_ADMINISTRATORS":
                workItem = workItemService.findWorkItem(domainId);
                String projectId = workItem.getProject().getId();
                Project project = projectService.findProject(projectId);
                user = project.getMaster();
                break;
            default:
                break;
        }
        return user;
    }


}
