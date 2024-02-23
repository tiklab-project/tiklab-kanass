package io.thoughtware.kanass.support.support;//package io.thoughtware.kanass.support.support;


import io.thoughtware.core.utils.UuidGenerator;
import io.thoughtware.flow.flow.model.Flow;
import io.thoughtware.flow.flow.model.FlowQuery;
import io.thoughtware.flow.flow.model.FlowStartNode;
import io.thoughtware.flow.flow.service.FlowService;
import io.thoughtware.flow.statenode.model.StateNodeFlowQuery;
import io.thoughtware.flow.transition.model.Transition;
import io.thoughtware.flow.transition.model.TransitionQuery;
import io.thoughtware.flow.transition.model.TransitionRule;
import io.thoughtware.flow.transition.service.TransitionRuleService;
import io.thoughtware.flow.transition.service.TransitionService;
import io.thoughtware.kanass.project.version.model.ProjectVersion;
import io.thoughtware.kanass.sprint.model.Sprint;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.kanass.workitem.service.WorkItemService;
import io.thoughtware.kanass.workitem.service.WorkSprintService;
import io.thoughtware.kanass.workitem.service.WorkVersionService;
import io.thoughtware.user.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class WorkFlowRuleInit implements ApplicationRunner {
    @Autowired
    FlowService flowService;

    @Autowired
    TransitionService transitionService;

    @Autowired
    TransitionRuleService transitionRuleService;

    private static Logger logger = LoggerFactory.getLogger(WorkFlowRuleInit.class);
    @Override
    @Transactional
    public void run(ApplicationArguments args) {
//        initWorkFlowRule();
    }

    public void initWorkFlowRule(){
        FlowQuery flowQuery = new FlowQuery();
        flowQuery.setScope(2);
        List<Flow> flowList = flowService.findFlowList(flowQuery);
        for (Flow flow : flowList) {
            String id = flow.getId();
            TransitionQuery transitionQuery = new TransitionQuery();

            //创建前置事项影响事项开始的限制
            transitionQuery.setFromNodeId("todo");
            transitionQuery.setFlowId(id);
            List<Transition> transitionList = transitionService.findTransitionList(transitionQuery);
            if(transitionList.size() > 0){

                Transition transition = transitionList.get(0);
                TransitionRule transitionRule = new TransitionRule();
                transitionRule.setFlow(flow);
                transitionRule.setName("根据关联事项的状态限制");
                transitionRule.setRuleType("limitWorkStatus");
                transitionRule.setTransition(transition);

                User user = new User();
                user.setId("111111");
                transitionRule.setCreateUser(user);
                transitionRule.setConfigValue("{\"preDependWorkStatus\":\"done\"}");
                transitionRuleService.createTransitionRule(transitionRule);
            }

            transitionQuery.setToNodeId("done");
            transitionQuery.setFromNodeId(null);
            transitionList = transitionService.findTransitionList(transitionQuery);
            if(transitionList.size() > 0){
                Transition transition = transitionList.get(0);
                TransitionRule transitionRule = new TransitionRule();
                transitionRule.setFlow(flow);
                transitionRule.setName("根据关联事项的状态限制");
                transitionRule.setRuleType("limitWorkStatus");
                transitionRule.setTransition(transition);

                User user = new User();
                user.setId("111111");
                transitionRule.setCreateUser(user);
                transitionRule.setConfigValue("{\"childWorkStatus\":\"done\"}");
                transitionRuleService.createTransitionRule(transitionRule);
            }

        }

    }
}
