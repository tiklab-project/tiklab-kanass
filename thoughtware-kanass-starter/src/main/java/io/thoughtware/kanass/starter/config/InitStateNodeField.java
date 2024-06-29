package io.thoughtware.kanass.starter.config;

import io.thoughtware.core.utils.UuidGenerator;
import io.thoughtware.dal.jdbc.JdbcTemplate;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.flow.statenode.model.StateNodeFlow;
import io.thoughtware.flow.statenode.model.StateNodeRoleField;
import io.thoughtware.flow.statenode.service.StateNodeFlowService;
import io.thoughtware.flow.statenode.service.StateNodeRoleFieldService;
import io.thoughtware.form.form.service.FormService;
import io.thoughtware.kanass.workitem.entity.WorkItemEntity;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.kanass.workitem.model.WorkItemQuery;
import io.thoughtware.kanass.workitem.service.WorkItemService;
import io.thoughtware.message.message.dao.MessageItemDao;
import io.thoughtware.message.message.entity.MessageItemEntity;
import io.thoughtware.message.message.model.MessageItemQuery;
import io.thoughtware.message.message.service.MessageItemService;
import io.thoughtware.security.logging.logging.model.Logging;
import io.thoughtware.security.logging.logging.model.LoggingQuery;
import io.thoughtware.security.logging.logging.service.LoggingService;
import io.thoughtware.todotask.todo.model.Task;
import io.thoughtware.todotask.todo.model.TaskQuery;
import io.thoughtware.todotask.todo.service.TaskService;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;

@Component
public class InitStateNodeField implements ApplicationRunner {

    @Autowired
    StateNodeFlowService stateNodeFlowService;

    @Autowired
    JoinTemplate joinTemplate;

    private static Logger logger = LoggerFactory.getLogger(InitStateNodeField.class);

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        System.out.println("0");
        stateNodeField();
    }

    public void stateNodeField(){
        List<StateNodeFlow> stateNodeFlowList = stateNodeFlowService.findAllStateNodeFlow();
        for (StateNodeFlow stateNodeFlow : stateNodeFlowList) {
            stateNodeFlowService.createNodeAllRoleField(stateNodeFlow);
        }

    }

}
