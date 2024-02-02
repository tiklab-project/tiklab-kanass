package io.thoughtware.kanass.support.support;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.thoughtware.flow.transition.model.BusinessRole;
import io.thoughtware.flow.transition.model.TransitionRule;
import io.thoughtware.flow.transition.model.TransitionRuleConfig;
import io.thoughtware.flow.transition.service.BusinessRoleService;
import io.thoughtware.flow.transition.service.TransitionRuleConfigService;
import io.thoughtware.flow.transition.service.TransitionRuleService;
import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Component
public class TransilationRuleConfigInit implements ApplicationRunner {
    @Autowired
    TransitionRuleService transitionRuleService;

    @Autowired
    TransitionRuleConfigService transitionRuleConfigService;

    @Autowired
    BusinessRoleService businessRoleService;

    @Autowired
    UserService userService;

    private static Logger logger = LoggerFactory.getLogger(TransilationRuleConfigInit.class);
    @Override
    @Transactional
    public void run(ApplicationArguments args) {
//        initTransilationRuleConfigInit();
    }

    public void initTransilationRuleConfigInit(){
        List<TransitionRule> allTransitionRule = transitionRuleService.findAllTransitionRule();
        for (TransitionRule transitionRule : allTransitionRule) {
            String id = transitionRule.getId();
            TransitionRuleConfig transitionRuleConfig = new TransitionRuleConfig();
            transitionRuleConfig.setRuleId(id);
            transitionRuleConfig.setConfigType("user");
            transitionRuleConfig.setRuleType("distributionWork");
            HashMap<String, String> configValue = new HashMap<>();
            String userType = transitionRule.getUserType();
            String allocationUserId = transitionRule.getAllocationUserId();
            if(userType.equals("role")){
                BusinessRole businessRole = businessRoleService.findBusinessRole(allocationUserId);
                configValue.put("roleId", allocationUserId);
                configValue.put("name", businessRole.getName());
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String configValueJson = objectMapper.writeValueAsString(configValue);
                    transitionRuleConfig.setConfigValue(configValueJson);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                transitionRuleConfigService.createTransitionRuleCondition(transitionRuleConfig);
            }
            if(userType.equals("user")){
                User user = userService.findUser(allocationUserId);
                configValue.put("userId", allocationUserId);
                configValue.put("name", user.getNickname());
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String configValueJson = objectMapper.writeValueAsString(configValue);
                    transitionRuleConfig.setConfigValue(configValueJson);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                transitionRuleConfigService.createTransitionRuleCondition(transitionRuleConfig);
            }
        }

    }
}
