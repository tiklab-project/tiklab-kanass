package io.thoughtware.kanass.support.service;

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
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class InitStateNodeFieldImpl implements InitStateNodeFiledService {

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

    private static Logger logger = LoggerFactory.getLogger(InitStateNodeFieldImpl.class);

//    @Override
//    @Transactional
//    public void run(ApplicationArguments args) {
//        System.out.println("0");
//        setRoleParent();
////        addFormField();
//    }
    public final ExecutorService executorService = Executors.newCachedThreadPool();
    @Override
    public void addFormField(){
        executorService.submit(() -> {
            String sql = "INSERT INTO pcs_foc_form_field (id, form_id, field_id, required, sort) VALUES (?, ?, ?, ?, ?)";

            List<Form> allForm = formService.findAllForm();
            List<Object[]> fieldFormData = new ArrayList<>();
            for (Form form : allForm) {
                String formId = form.getId();


                FormFieldQuery formFieldQuery = new FormFieldQuery();
                formFieldQuery.setFormId(formId);
                List<FormField> formFieldList = formFieldService.findFormFieldList(formFieldQuery);
                int size = formFieldList.size();
                String id = UuidGenerator.getRandomIdByUUID(8);
                fieldFormData.add(new Object[]{id, formId, "bbbf26c3", 0, ++size});
                id = UuidGenerator.getRandomIdByUUID(8);
                fieldFormData.add(new Object[]{id, formId, "c5710576", 0, ++size});
                id = UuidGenerator.getRandomIdByUUID(8);
                fieldFormData.add(new Object[]{id, formId, "d72ab706", 0, ++size});
                id = UuidGenerator.getRandomIdByUUID(8);
                fieldFormData.add(new Object[]{id, formId, "da0fbb82", 0, ++size});
                id = UuidGenerator.getRandomIdByUUID(8);
                fieldFormData.add(new Object[]{id, formId, "765e3fce", 0, ++size});
                id = UuidGenerator.getRandomIdByUUID(8);
                fieldFormData.add(new Object[]{id, formId, "c5e9a38a", 0, ++size});
                id = UuidGenerator.getRandomIdByUUID(8);
                fieldFormData.add(new Object[]{id, formId, "30026c42", 0, ++size});
                id = UuidGenerator.getRandomIdByUUID(8);
                fieldFormData.add(new Object[]{id, formId, "18f35f29", 0, ++size});
                id = UuidGenerator.getRandomIdByUUID(8);
                fieldFormData.add(new Object[]{id, formId, "91532da2", 0, ++size});
            }
            try {
                int[] newStateNodeFlow = jdbcTemplate.batchUpdate(sql, fieldFormData);
                System.out.println("节点个数: " + newStateNodeFlow.length);
//            flowRelationForm();
            } catch (Exception e) {
                // 处理异常，例如回滚事务（如果在一个事务中）
                e.printStackTrace();
            }
        });

    }


    // 洗项目成员

    @Override
    public void setRoleParent(){
        RoleQuery roleQuery = new RoleQuery();
        roleQuery.setType("2");
        roleQuery.setScope(1);
        List<Role> roleList = roleService.findRoleList(roleQuery);

        roleQuery.setScope(2);
        List<Role> projectRoleList = roleService.findRoleList(roleQuery);
        for (Role role : projectRoleList) {
            String name = role.getName();
            List<Role> systemRole = roleList.stream().filter(role1 -> role1.getName().equals(name)).collect(Collectors.toList());
            if(systemRole.size() > 0){
                Role role1 = systemRole.get(0);
                role.setParentId(role1.getId());
                roleService.updateRole(role);
            }
        }

    }



    @Override
    public void flowRelationForm(){
        List<WorkTypeDm> allWorkTypeDm = workTypeDmService.findAllWorkTypeDm();
        FlowQuery flowQuery = new FlowQuery();
        flowQuery.setScope(2);
        List<Flow> flowList = flowService.findFlowList(flowQuery);
        for (Flow flow : flowList) {
            String id = flow.getId();
            List<WorkTypeDm> workTypeDmList = allWorkTypeDm.stream().filter(workTypeDm -> workTypeDm.getFlow().getId().equals(id)).collect(Collectors.toList());
            int size = workTypeDmList.size();
            if(size > 0){
                WorkTypeDm workTypeDm = workTypeDmList.get(0);
                Form form = workTypeDm.getForm();
                flow.setForm(form);
                flowService.updateFlow(flow);
            }
        }
    }

    @Override
    public void newformFieldList(){
        executorService.submit(() -> {
            String sql = "INSERT INTO pcs_flc_state_node_role_field (id, role_id, role_type, action, node_id, field_id, form_id, flow_id, scope) VALUES" +
                    " (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            List<Object[]> allNewStateNodeRoleFieldList = new ArrayList<>();
            for (Project project : projectService.findAllProject()) {
                String projectId = project.getId();
                List<HashMap<String, String>> roleList = getRoleList(projectId);
                DmFlowQuery dmFlowQuery = new DmFlowQuery();
                dmFlowQuery.setDomainId(projectId);
                List<DmFlow> dmFlowList = dmFlowService.findDmFlowList(dmFlowQuery);
                ArrayList<String> flowIds = new ArrayList<>();
                ArrayList<String> globalIds = new ArrayList<>();
                for (DmFlow dmFlow : dmFlowList) {
                    String id = dmFlow.getFlow().getId();
                    Flow flow = flowService.findFlow(id);
                    String formId = flow.getForm().getId();

                    String globalFlowId = dmFlow.getGlobalFlowId();
                    // 获取要复制全局的流程的节点
                    StateNodeFlowQuery stateNodeFlowQuery = new StateNodeFlowQuery();
                    stateNodeFlowQuery.setFlowId(globalFlowId);
                    List<StateNodeFlow> stateNodeFlowList = stateNodeFlowService.findStateNodeFlowList(stateNodeFlowQuery);


                    // 获取项目流程的节点
                    stateNodeFlowQuery.setFlowId(id);
                    List<StateNodeFlow> projectStateNodeFlowList = stateNodeFlowService.findStateNodeFlowList(stateNodeFlowQuery);

                    // 获取流程的关联的字段权利
                    StateNodeRoleFieldQuery stateNodeRoleFieldQuery = new StateNodeRoleFieldQuery();
                    stateNodeRoleFieldQuery.setFlowId(globalFlowId);
                    List<StateNodeRoleField> stateNodeRoleFieldList = stateNodeRoleFieldService.findStateNodeRoleFieldList(stateNodeRoleFieldQuery);


                    for (StateNodeFlow stateNodeFlow : stateNodeFlowList) {
                        String nodeId = stateNodeFlow.getNode().getId();
                        List<StateNodeFlow> stateNodeFlowListEqual = projectStateNodeFlowList.stream().filter(projectStateNodeFlow -> projectStateNodeFlow.getNode().getId().equals(nodeId))
                                .collect(Collectors.toList());
                        int size = stateNodeFlowListEqual.size();
                        if(size > 0){
                            // 获取对应权限的id
                            StateNodeFlow stateNodeFlow1 = stateNodeFlowListEqual.get(0);
                            String stateNodeFlowId = stateNodeFlow1.getId();
                            // 角色
                            for(HashMap<String, String> entry : roleList) {
                                String roleId = entry.get("id");
                                String roleType = entry.get("type");
                                String parentId = entry.get("parentId");
                                FormFieldQuery formFieldQuery = new FormFieldQuery();
                                formFieldQuery.setFormId(formId);
                                List<FormField> formFieldList = formFieldService.findFormFieldList(formFieldQuery);
                                for (FormField formField : formFieldList) {
                                    Integer noEdit = formField.getField().getNoEdit();
                                    if(noEdit.equals(0)){
                                        String id1 = UuidGenerator.getRandomIdByUUID(8);
                                        String fieldId = formField.getField().getId();
                                        if(roleType.equals("role")){
                                            List<StateNodeRoleField> stateNodeRoleFieldList1 = stateNodeRoleFieldList.stream().filter(stateNodeRoleField ->
                                                    stateNodeRoleField.getField().getId().equals(fieldId) &&
                                                            stateNodeRoleField.getRoleId().equals(parentId) &&
                                                            stateNodeRoleField.getStateNodeFlow().getNode().getId().equals(nodeId)).collect(Collectors.toList());
                                            int size1 = stateNodeRoleFieldList1.size();
                                            if(size1 > 0){
                                                StateNodeRoleField stateNodeRoleField = stateNodeRoleFieldList1.get(0);
                                                Integer action = stateNodeRoleField.getAction();
                                                allNewStateNodeRoleFieldList.add(new Object[]{id1, roleId, roleType, action, stateNodeFlowId, fieldId, formId, id, "2" });
                                            }else {
                                                allNewStateNodeRoleFieldList.add(new Object[]{id1, roleId, roleType, 0, stateNodeFlowId, fieldId, formId, id, "2" });
                                            }
                                        }
                                        if(roleType.equals("vRole")){
                                            List<StateNodeRoleField> stateNodeRoleFieldList1 = stateNodeRoleFieldList.stream().filter(stateNodeRoleField ->
                                                    stateNodeRoleField.getField().getId().equals(fieldId) &&
                                                            stateNodeRoleField.getRoleId().equals(roleId) &&
                                                            stateNodeRoleField.getStateNodeFlow().getNode().getId().equals(nodeId)).collect(Collectors.toList());
                                            int size1 = stateNodeRoleFieldList1.size();
                                            if(size1 > 0){
                                                StateNodeRoleField stateNodeRoleField = stateNodeRoleFieldList1.get(0);
                                                Integer action = stateNodeRoleField.getAction();
                                                allNewStateNodeRoleFieldList.add(new Object[]{id1, roleId, roleType, action, stateNodeFlowId, fieldId, formId, id, "2" });
                                            }else {
                                                allNewStateNodeRoleFieldList.add(new Object[]{id1, roleId, roleType, 0, stateNodeFlowId, fieldId, formId, id, "2" });
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            try {
                int[] newStateNodeFlowField = jdbcTemplate.batchUpdate(sql, allNewStateNodeRoleFieldList);
                System.out.println("权限个数: " + newStateNodeFlowField.length);
            } catch (Exception e) {
                // 处理异常，例如回滚事务（如果在一个事务中）
                e.printStackTrace();
            }
        });

    }

    public List<HashMap<String, String>> getRoleList(String projectId){
        List<Role> roleList = new ArrayList<>();
        if(StringUtils.isEmpty(projectId)){
            RoleQuery roleQuery = new RoleQuery();
            roleQuery.setScope(1);
            roleQuery.setType("2");
            roleList = roleService.findRoleList(roleQuery);
        }else {
            DmRoleQuery dmRoleQuery = new DmRoleQuery();
            dmRoleQuery.setDomainId(projectId);
            List<DmRole> dmRoleList = dmRoleService.findDmRoleList(dmRoleQuery);
            for (DmRole dmRole : dmRoleList) {
                Role role = dmRole.getRole();
                roleList.add(role);
            }
        }

        // 获取虚拟角色列表
        List<VRole> vRoleList = vRoleService.findAllVRole();

        List<HashMap<String, String>> roleMapList = new ArrayList<>();

        for (Role role : roleList) {
            HashMap<String, String> roleHashMap = new HashMap<>();
            roleHashMap.put("id", role.getId());
            roleHashMap.put("type", "role");
            roleHashMap.put("name", role.getName());
            roleHashMap.put("parentId", role.getParentId());
            roleMapList.add(roleHashMap);
        }

        for (VRole vRole : vRoleList) {
            HashMap<String, String> roleHashMap = new HashMap<>();
            roleHashMap.put("id", vRole.getId());
            roleHashMap.put("type", "vRole");
            roleMapList.add(roleHashMap);
        }

        return roleMapList;
    }

    @Override
    public void stateNodeField(){
        executorService.submit(() -> {
//            String[] nodeIds = {"todo", "done", "7db3a0d1"};
            String[] nodeIds = {"5b3ff31d", "3b037eb2", "5076fe7e", "b5a4ffce"};

            String[] flowIds = {"a96cf9c9", "3d879830", "4d040c6d"};
            StateNodeFlowQuery stateNodeFlowQuery = new StateNodeFlowQuery();
            stateNodeFlowQuery.setNodeIds(nodeIds);
            stateNodeFlowQuery.setInFlowIds(flowIds);
            List<StateNodeFlow> stateNodeFlowList = stateNodeFlowService.findStateNodeFlowList(stateNodeFlowQuery);
            for (StateNodeFlow stateNodeFlow : stateNodeFlowList) {
                stateNodeFlowService.createNodeAllRoleField(stateNodeFlow);
            }
        });
    }

    /**
     * 梳理表单字段， 任务表单添加任务类型， 缺陷表单添加缺陷类型，需求表单添加需求类型去掉多余的事项类型字段
     */
    @Override
    public void updateFormField() {
        // 任务表单
        DmFormQuery dmFormQuery = new DmFormQuery();
        dmFormQuery.setGlobalFormId("515f17bd");
        List<DmForm> dmFormList = dmFormService.findDmFormList(dmFormQuery);
        for (DmForm dmForm : dmFormList) {
            String id = dmForm.getForm().getId();
            FormField formField = new FormField();
            Form form = new Form();
            form.setId(id);
            formField.setForm(form);
            FieldEx fieldEx = new FieldEx();
            fieldEx.setId("a34d7fc3");
            formField.setField(fieldEx);
            formFieldService.createFormField(formField);
        }

        // 需求
        dmFormQuery.setGlobalFormId("0c5d4ff9");
        dmFormList = dmFormService.findDmFormList(dmFormQuery);
        for (DmForm dmForm : dmFormList) {
            String id = dmForm.getForm().getId();
            FormField formField = new FormField();
            Form form = new Form();
            form.setId(id);
            formField.setForm(form);
            FieldEx fieldEx = new FieldEx();
            fieldEx.setId("c4579b11");
            formField.setField(fieldEx);
            formFieldService.createFormField(formField);

            // 去掉多余的事项类型字段
            FormFieldQuery formFieldQuery = new FormFieldQuery();
            formFieldQuery.setFieldId("bd073750");
            formFieldQuery.setFormId(id);
            List<FormField> formFieldList = formFieldService.findFormFieldList(formFieldQuery);
            if(formFieldList.size() > 1){
                formFieldList.remove(0);
                for (FormField field : formFieldList) {
                    String id1 = field.getId();
                    formFieldService.deleteFormField(id1);
                }
            }

            // 去掉多余的迭代字段
            formFieldQuery = new FormFieldQuery();
            formFieldQuery.setFieldId("0f9ff2ce");
            formFieldQuery.setFormId(id);
            formFieldList = formFieldService.findFormFieldList(formFieldQuery);
            if(formFieldList.size() > 1){
                formFieldList.remove(0);
                for (FormField field : formFieldList) {
                    String id1 = field.getId();
                    formFieldService.deleteFormField(id1);
                }
            }
        }

        // 缺陷表单
        dmFormQuery.setGlobalFormId("607f6be6");
        dmFormList = dmFormService.findDmFormList(dmFormQuery);
        for (DmForm dmForm : dmFormList) {
            String id = dmForm.getForm().getId();
            FormField formField = new FormField();
            Form form = new Form();
            form.setId(id);
            formField.setForm(form);
            FieldEx fieldEx = new FieldEx();
            fieldEx.setId("0763e387");
            formField.setField(fieldEx);
            formFieldService.createFormField(formField);
        }

    }
}
