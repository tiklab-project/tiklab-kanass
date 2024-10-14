package io.tiklab.kanass.support.support;//package io.tiklab.kanass.support.support;


import io.tiklab.flow.flow.service.FlowService;
import io.tiklab.flow.transition.service.TransitionRuleService;
import io.tiklab.flow.transition.service.TransitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.transaction.annotation.Transactional;

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
