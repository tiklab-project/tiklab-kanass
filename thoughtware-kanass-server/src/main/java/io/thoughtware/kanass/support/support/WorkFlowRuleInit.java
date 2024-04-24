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

//@Component
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
    }

    public void initWorkFlowRule(){


    }
}
