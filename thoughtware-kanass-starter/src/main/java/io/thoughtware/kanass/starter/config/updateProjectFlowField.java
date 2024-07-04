package io.thoughtware.kanass.starter.config;

import io.thoughtware.core.utils.UuidGenerator;
import io.thoughtware.dal.jdbc.JdbcTemplate;
import io.thoughtware.flow.flow.model.DmFlow;
import io.thoughtware.flow.flow.model.DmFlowQuery;
import io.thoughtware.flow.flow.model.Flow;
import io.thoughtware.flow.flow.model.FlowQuery;
import io.thoughtware.flow.flow.service.DmFlowService;
import io.thoughtware.flow.flow.service.FlowService;
import io.thoughtware.flow.statenode.model.StateNodeFlow;
import io.thoughtware.flow.statenode.model.StateNodeFlowQuery;
import io.thoughtware.flow.statenode.model.StateNodeRoleField;
import io.thoughtware.flow.statenode.model.StateNodeRoleFieldQuery;
import io.thoughtware.flow.statenode.service.StateNodeFlowService;
import io.thoughtware.flow.statenode.service.StateNodeRoleFieldService;
import io.thoughtware.form.field.model.FieldEx;
import io.thoughtware.form.form.model.*;
import io.thoughtware.form.form.service.DmFormService;
import io.thoughtware.form.form.service.FormFieldService;
import io.thoughtware.form.form.service.FormService;
import io.thoughtware.kanass.project.project.model.Project;
import io.thoughtware.kanass.project.project.service.ProjectService;
import io.thoughtware.kanass.workitem.model.WorkTypeDm;
import io.thoughtware.kanass.workitem.service.WorkTypeDmService;
import io.thoughtware.privilege.dmRole.model.DmRole;
import io.thoughtware.privilege.dmRole.model.DmRoleQuery;
import io.thoughtware.privilege.dmRole.service.DmRoleService;
import io.thoughtware.privilege.role.model.Role;
import io.thoughtware.privilege.role.model.RoleQuery;
import io.thoughtware.privilege.role.service.RoleService;
import io.thoughtware.privilege.vRole.model.VRole;
import io.thoughtware.privilege.vRole.service.VRoleService;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

//@Component
public class updateProjectFlowField implements ApplicationRunner {

    @Autowired
    StateNodeFlowService stateNodeFlowService;

    @Autowired
    FormService formService;

    @Autowired
    FlowService flowService;

    @Autowired
    FormFieldService formFieldService;

    @Autowired
    WorkTypeDmService workTypeDmService;

    @Autowired
    ProjectService projectService;

    @Autowired
    RoleService roleService;

    @Autowired
    DmRoleService dmRoleService;

    @Autowired
    DmFlowService dmFlowService;

    @Autowired
    VRoleService vRoleService;

    @Autowired
    StateNodeRoleFieldService stateNodeRoleFieldService;

    @Autowired
    DmFormService dmFormService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static Logger logger = LoggerFactory.getLogger(updateProjectFlowField.class);


    @Transactional
    public void run(ApplicationArguments args) {
        System.out.println("0");
//        addFormField();
    }

   public void copyFlow(){
       List<Project> allProject = projectService.findAllProject();
       
       for (Project project : allProject) {
           String projectId = project.getId();
       }

   }

}
