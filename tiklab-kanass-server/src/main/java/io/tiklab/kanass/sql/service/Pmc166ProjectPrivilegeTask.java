package io.tiklab.kanass.sql.service;

import io.tiklab.core.utils.UuidGenerator;
import io.tiklab.dsm.support.DsmProcessTask;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.privilege.dmRole.model.DmRole;
import io.tiklab.privilege.dmRole.service.DmRoleService;
import io.tiklab.privilege.role.service.RoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Pmc166ProjectPrivilegeTask implements DsmProcessTask {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RoleService roleService;

    @Autowired
    ProjectService projectService;

    @Autowired
    DmRoleService dmRoleService;

    @Override
    public void execute() {
        addSysRoleFunction();
    }

    private void addSysRoleFunction() {
        for (String sysRoleId : sysRoleIds) {
            String[] systemRoleIds = findSystemRoleIds(sysRoleId);
            execBatchInsert(systemRoleIds, sysRoleId);
        }
        List<String> projectIdList = new ArrayList<>();
        List<Project> allProject = projectService.findAllProject();
        if (CollectionUtils.isNotEmpty(allProject)){
            projectIdList = allProject.stream().map(Project::getId).collect(Collectors.toList());
        }

        List<String> projectSetIdList = new ArrayList<>();

        List<String> productIdList = new ArrayList<>();

        // 修改初始化权限

        // 修改已存在的权限
        List<DmRole> allDmRole = dmRoleService.findAllDmRole();
        for (DmRole dmRole : allDmRole) {
            String domainId = dmRole.getDomainId();
            Integer businessType = dmRole.getBusinessType();
            String[] systemRoleIds = new String[]{};
            if (projectIdList.contains(domainId)){
                if (businessType == 0){
                    // 普通用户
                    systemRoleIds = projectFunctionIds;
                }else {
                    // 管理员
                    systemRoleIds = projectAdminFunctionIds;
                }
            } else {
                continue;
            }

            execBatchInsert(systemRoleIds, dmRole.getRole().getId());
        }

//        RoleQuery roleQuery = new RoleQuery();
//        roleQuery.setScope(2);
//        roleQuery.setType("2");
//        List<Role> roleList = roleService.findRoleList(roleQuery);
//
//        for (Role role : roleList) {
//            String[] systemRoleIds = new String[]{};
//            String parentId = role.getParentId();
//            if (!StringUtils.isEmpty(parentId)) {
//                systemRoleIds = findSystemRoleIds(parentId);
//            }
//            if (systemRoleIds.length == 0) {
//                Integer businessType = role.getBusinessType();
//                if (businessType.equals(2)) {
//                    systemRoleIds = domainAdminFunctionIds;
//                } else {
//                    systemRoleIds = domainFunctionIds;
//                }
//            }
//
//            execBatchInsert(systemRoleIds, role.getId());
//        }
    }

    private String[] findSystemRoleIds(String id) {
        switch (id) {
            case "111111", "1" -> {
                return allSysFunctionIds;
            }
            case "77f512ab7c53" -> {
                return sysFunctionIds;
            }

            // 项目
            case "pro_111111" -> {
                return projectAdminFunctionIds;
            }
            case "4559d54bc8b7" -> {
                return projectFunctionIds;
            }


            default -> {
                return new String[]{};
            }
        }
    }


    private final String[] sysRoleIds = new String[]{"111111", "1", "77f512ab7c53", "pro_111111", "4559d54bc8b7"};


    private final String[] allSysFunctionIds = new String[]{
            "user", "user_add_user", "user_update_user", "user_delete_user", "user_update_user_password", "user_update_user_recover",
            "orga", "orga_add_orga", "orga_update_orga", "orga_delete_orga", "orga_add_user", "orga_delete_user",
            "user_group", "user_add_group", "user_update_group", "user_delete_group", "user_add_group_user", "user_delete_group_user",
            "user_dir", "user_dir_sync", "user_dir_open", "user_dir_config", "user_dir_forbid",
            "permission", "permission_role_add", "permission_role_delete", "permission_role_update", "permission_role_user_add", "permission_role_user_delete", "permission_role_permission_update", "permission_role_update_default",
            "message", "message_update_send_way", "message_update_plan_status", "message_update_plan_send_way", "message_plan_user_add", "message_plan_delete",
            "openapi", "openapi_add", "openapi_delete", "openapi_find",
            "backups_and_recover", "backups_update_status", "backups_create", "recover_create",
            "log","log_find",
            "ip_whitelist", "ip_whitelist_white", "ip_whitelist_black",
            "licence", "licence_import",
            "apply_limits", "apply_limits_add_user", "apply_limits_delete_user", "apply_limits_open_user", "apply_limits_close_user",
            "custom_logo", "custom_logo_update_title", "custom_logo_update_status", "custom_logo_update_pic",

            //
            "work_type","work_type_add","work_type_update","work_type_update_flow","work_type_update_form","work_type_update_permissions","work_type_delete",
            "field","field_add","field_update","field_delete",
            "form","form_add","form_update","form_delete","form_copy",
            "flow","flow_add","flow_update","flow_delete","flow_config",
            "status","status_add","status_update","status_delete",
            "url_data","url_data_update","url_data_delete",
            "import","import_jira","import_mantis",
            "project","project_add","project_update","project_delete",
            "project_set","project_set_add","project_set_update","project_set_delete",
            "product","product_add","product_update","product_delete",
    };

    private final String[] sysFunctionIds = new String[]{

//            "user",
//            "orga",
//            "user_group",
//            "user_dir",
//            "permission",
//            "message",
//            "openapi",
//            "backups_and_recover",
//            "log",
//            "ip_whitelist",
//            "licence",
//            "apply_limits",
//            "custom_logo",
//
////
//
//            "work_type",
//            "field",
//            "form",
//            "flow",
//            "status",
//            "url_data",
//            "import",
//            "project",
//            "project_set",
//            "product",
    };


    private final String[] projectAdminFunctionIds = new String[]{
            // 系统全局设置权限
            "project_setting","domain_user","domain_role","domain_message",
//            "project_basic_info","project_work_type","project_flow","project_form","project_module","project_integration",
            "domain_user_add", "domain_user_delete", "domain_user_update",
            "domain_role_add", "domain_role_delete", "domain_role_update", "domain_role_user_add", "domain_role_user_delete", "domain_role_permission_update", "domain_role_update_default",
            "domain_message_status", "domain_message_way", "domain_message_user_add", "domain_message_user_delete",
            "project_basic_info","project_basic_info_update","project_basic_info_delete",
            "project_work_type","project_work_type_add","project_work_type_update","project_work_type_update_flow","project_work_type_update_form","project_work_type_update_permissions","project_work_type_delete",
            "project_flow","project_flow_add","project_flow_link","project_flow_update","project_flow_delete","project_flow_config",
            "project_form","project_form_add","project_form_link","project_form_update","project_form_delete","project_form_config",
            "project_module","project_module_add","project_module_update","project_module_delete",
            "project_integration","project_integration_sward_add","project_integration_sward_delete",
            "work",
            "work","work_add","work_update","work_delete","work_link_work","work_create_test_case","work_link_test_case",
            "stage",
            "stage","stage_add","stage_update","stage_delete",
            "sprint",
            "sprint","sprint_add","sprint_update","sprint_delete","sprint_plan_work",
            "version",
            "version","version_add","version_update","version_delete","version_plan_work",
            "test",
            "test_case","test_case_add","test_case_update","test_case_delete","test_case_create_defect","test_case_link_defect",
            "test_plan","test_plan_add","test_plan_update","test_plan_delete",
            "test_report","test_report_add","test_report_update","test_report_delete",
            "wiki",
            "wiki","wiki_add","wiki_delete",
            "appraised",
            "appraised","appraised_add","appraised_update","appraised_delete","appraised_link_item","appraised_delete_item","appraised_update_item",
            "milestone",
            "milestone","milestone_add","milestone_update","milestone_delete",
    };

    private final String[] projectFunctionIds = new String[]{
//            "project_setting",
//
//            "project_basic_info",
//            "project_work_type",
//            "project_flow",
//            "project_form",
//            "project_module",
//            "project_integration",
//            "work",
//            "stage",
//            "sprint",
//            "version",
//            "test_case",
//            "test_plan",
//            "test_report",
//            "wiki",
//            "appraised",

    };

    private final String[] projectSetAdminFunctionIds = new String[]{
            // 系统全局设置权限
            "project_set_setting","domain_user","domain_role","project_set_basic_info",
            "domain_user_add", "domain_user_delete", "domain_user_update",
            "domain_role_add", "domain_role_delete", "domain_role_update", "domain_role_user_add", "domain_role_user_delete", "domain_role_permission_update", "domain_role_update_default",
            "project_set_basic_info_update","project_set_basic_info_delete",
            "project_set_project",
            "project_set_project","project_set_project_add","project_set_project_link","project_set_project_delete",
            "project_set_work",
            "work","work_add","work_update","work_delete","work_link_work","work_create_test_case","work_link_test_case",
    };

    private final String[] projectSetFunctionIds = new String[]{
//            "project_set_setting",
//            "project_set_project",
//            "project_set_work",
    };

    private final String[] productAdminFunctionIds = new String[]{
            // 系统全局设置权限
            "product_setting","domain_user","domain_role",
            "domain_user_add", "domain_user_delete", "domain_user_update",
            "domain_role_add", "domain_role_delete", "domain_role_update", "domain_role_user_add", "domain_role_user_delete", "domain_role_permission_update", "domain_role_update_default",
            "product_basic_info", "product_basic_info_update", "product_basic_info_delete",
            "product_plan",
            "product_plan","product_plan_add","product_plan_update","product_plan_delete","product_plan_link_work",
            "work","work_add","work_update","work_delete","work_link_work","work_create_test_case","work_link_test_case",
            "demand",
            "work","work_add","work_update","work_delete","work_link_work","work_create_test_case","work_link_test_case",
            "product_linemap",
            "work","work_add","work_update","work_delete","work_link_work","work_create_test_case","work_link_test_case",
            "product_module",
            "product_module","product_module_add","product_module_update","product_module_delete",
    };

    private final String[] productFunctionIds = new String[]{
//            "ka_setting"
    };


    public void execBatchInsert(String[] functionIds, String roleId) {

        String sql = "INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, Arrays.stream(functionIds).toList(), functionIds.length,
                (ps, functionId) -> {
                    ps.setString(1, UuidGenerator.getRandomIdByUUID(12));
                    ps.setString(2, roleId);
                    ps.setString(3, functionId);
                }
        );
    }
}
