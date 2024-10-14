package io.tiklab.kanass.starter.config;

import io.tiklab.dal.jdbc.JdbcTemplate;
import io.tiklab.flow.flow.service.DmFlowService;
import io.tiklab.flow.flow.service.FlowService;
import io.tiklab.flow.statenode.service.StateNodeFlowService;
import io.tiklab.flow.statenode.service.StateNodeRoleFieldService;
import io.tiklab.form.form.service.DmFormService;
import io.tiklab.form.form.service.FormFieldService;
import io.tiklab.form.form.service.FormService;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.kanass.workitem.service.WorkTypeDmService;
import io.tiklab.privilege.dmRole.service.DmRoleService;
import io.tiklab.privilege.role.service.RoleService;
import io.tiklab.privilege.vRole.service.VRoleService;
import io.tiklab.toolkit.join.JoinTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
