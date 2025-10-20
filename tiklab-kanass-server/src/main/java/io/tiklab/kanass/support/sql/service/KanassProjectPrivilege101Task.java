package io.tiklab.kanass.support.sql.service;

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
public class KanassProjectPrivilege101Task implements DsmProcessTask {
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


    private final String[] sysRoleIds = new String[]{"77f512ab7c53"};


    private final String[] allSysFunctionIds = new String[]{

    };

    private final String[] sysFunctionIds = new String[]{

            "user",
            "project","project_add","project_update",
            "project_set","project_set_add","project_set_update",
            "product","product_add","product_update",
    };


    private final String[] projectAdminFunctionIds = new String[]{

    };

    private final String[] projectFunctionIds = new String[]{

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
