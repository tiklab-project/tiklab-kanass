package io.thoughtware.kanass.workitem.service;

import io.thoughtware.flow.transition.model.TransitionRule;
import io.thoughtware.flow.transition.model.TransitionRuleQuery;
import io.thoughtware.flow.transition.service.TransitionRuleService;
import io.thoughtware.flow.transition.service.TransitionRuleUserService;
import io.thoughtware.kanass.workitem.entity.WorkItemEntity;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class WorkTranslationUserListImpl implements TransitionRuleUserService {
    @Autowired
    TransitionRuleService transitionRuleService;

    @Override
    public List<User> findTransitionRuleUserList(@NotNull @Valid String transitionId) {
        List<User> userList = new ArrayList<>();
        TransitionRuleQuery transitionRuleQuery = new TransitionRuleQuery();
        transitionRuleQuery.setTransitionId(transitionId);
        List<TransitionRule> transitionRuleList = transitionRuleService.findTransitionRuleList(transitionRuleQuery);
        for (TransitionRule transitionRule : transitionRuleList) {
            if(transitionRule.getUserType() == "user"){
                User allocationUser = transitionRule.getAllocationUser();
                userList.add(allocationUser);
                // 修改事项负责人
//                workItem.setAssigner(allocationUser);
//                WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
//                workItemDao.updateWorkItem(workItemEntity);
//                // 发送消息和待办事项
//                WorkItem newWorkItem = findWorkItem(id);
//                creatTodoTask(oldWorkItem, allocationUser);
//                sendMessageForUpdateStatus(oldWorkItem, newWorkItem, allocationUser);
//                sendMessageForUpdateAssigner(oldWorkItem, allocationUser);
            }
            if(transitionRule.getUserType() == "role"){

            }
        }

        return null;
    }
}
