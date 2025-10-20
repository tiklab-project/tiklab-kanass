package io.tiklab.kanass.support.sql.service;

import io.tiklab.core.utils.UuidGenerator;
import io.tiklab.dsm.support.DsmProcessTask;
import io.tiklab.privilege.role.model.Role;
import io.tiklab.privilege.role.model.RoleQuery;
import io.tiklab.privilege.role.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class Pmc164PrivilegeTask implements DsmProcessTask {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RoleService roleService;

    @Override
    public void execute() {
        addSysRoleFunction();
    }

    private void addSysRoleFunction() {
        for (String sysRoleId : sysRoleIds) {
            String[] systemRoleIds = findSystemRoleIds(sysRoleId);
            execBatchInsert(systemRoleIds, sysRoleId);
        }
        RoleQuery roleQuery = new RoleQuery();
        roleQuery.setScope(2);
        roleQuery.setType("2");
        List<Role> roleList = roleService.findRoleList(roleQuery);

        for (Role role : roleList) {
            String[] systemRoleIds = new String[]{};
            String parentId = role.getParentId();
            if (!StringUtils.isEmpty(parentId)) {
                systemRoleIds = findSystemRoleIds(parentId);
            }
            if (systemRoleIds.length == 0) {
                Integer businessType = role.getBusinessType();
                if (businessType.equals(2)) {
                    systemRoleIds = domainAdminFunctionIds;
                } else {
                    systemRoleIds = domainFunctionIds;
                }
            }

            execBatchInsert(systemRoleIds, role.getId());
        }
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
                return domainAdminFunctionIds;
            }
            case "4559d54bc8b7" -> {
                return domainFunctionIds;
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

            // 流水线权限
    };

    private final String[] sysFunctionIds = new String[]{

            "user",
            "orga",
            "user_group",
            "user_dir",
            "permission",
            "message",
            "openapi",
            "backups_and_recover",
            "log",
            "ip_whitelist",
            "licence",
            "apply_limits",
            "custom_logo",

//            // 流水线权限
    };


    private final String[] domainAdminFunctionIds = new String[]{
            // 系统全局设置权限
            "ka_setting","domain_user","domain_role","domain_message",
            "domain_user_add", "domain_user_delete", "domain_user_update",
            "domain_role_add", "domain_role_delete", "domain_role_update", "domain_role_user_add", "domain_role_user_delete", "domain_role_permission_update", "domain_role_update_default",
            "domain_message_status", "domain_message_way", "domain_message_user_add", "domain_message_user_delete"
    };

    private final String[] domainFunctionIds = new String[]{
            "ka_setting"
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
