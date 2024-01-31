package io.thoughtware.kanass.workitem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.thoughtware.flow.transition.service.TransitionRuleService;
import io.thoughtware.flow.transition.service.TransitionRuleUserService;
import io.thoughtware.kanass.project.project.model.Project;
import io.thoughtware.kanass.project.project.service.ProjectService;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.kanass.workitem.model.WorkItemQuery;
import io.thoughtware.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @Override
    public Boolean comparisonWorkStatus(String configValue, String domainId) {
        Boolean isShowTransition = true;
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> statusMap = new HashMap<>();
        try {
            statusMap = objectMapper.readValue(configValue, HashMap.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String comparison = new String();
        String status = new String();
        String workRelation = new String();
        try {
            comparison = statusMap.get("comparison");
            status = statusMap.get("status");
            workRelation = statusMap.get("workRelation");

            switch (workRelation){
                case "childrenWork":
                    isShowTransition = comparisonChildrenWorkStatus(comparison, status, domainId);
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return isShowTransition;
    }

    public Boolean comparisonChildrenWorkStatus(String comparison, String status, String domainId) {
        Boolean isShowTransition = true;
        WorkItemQuery workItemQuery = new WorkItemQuery();
        switch (comparison){
            case "pass":
//                getStatusBeforePass(status)
                break;
        }
        workItemQuery.setParentId(domainId);
        List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);
        return  isShowTransition;
    }



}
