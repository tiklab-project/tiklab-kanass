-- 清空权限，重新写入
TRUNCATE TABLE pcs_prc_role_function;

-- 系统级的权限
INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('work_type', '事项类型', 'work_type', 'work_type', 4, 1);

INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('form', '表单', 'form', 'field,form', 5, 1);

INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('flow', '流程', 'flow', 'flow,status', 6, 1);

INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('integration', '集成与开放', 'integration', 'url_data,import,openapi', 7, 1);

INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('project', '项目', 'project', 'project', 10, 1);

INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('project_set', '项目集', 'project_set', 'project_set', 11, 1);

INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('product', '产品', 'product', 'product', 12, 1);

-- 事项类型
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('work_type', '事项类型', 'work_type', NULL, 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('work_type_add', '新增事项类型', 'work_type_add', 'work_type', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('work_type_update', '编辑事项类型', 'work_type_update', 'work_type', 2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('work_type_update_flow', '流程配置', 'work_type_update_flow', 'work_type', 3, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('work_type_update_form', '表单配置', 'work_type_update_form', 'work_type', 4, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('work_type_update_permissions', '权限配置', 'work_type_update_permissions', 'work_type', 5, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('work_type_delete', '删除事项类型', 'work_type_delete', 'work_type', 6, '1');

-- 字段
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('field', '字段', 'field', NULL, 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('field_add', '新增字段', 'field_add', 'field', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('field_update', '编辑字段', 'field_update', 'field', 2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('field_delete', '删除字段', 'field_delete', 'field', 3, '1');

-- 表单
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('form', '表单', 'form', NULL, 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('form_add', '新增表单', 'form_add', 'form', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('form_update', '编辑表单', 'form_update', 'form', 2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('form_delete', '删除表单', 'form_delete', 'form', 3, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('form_copy', '删除表单', 'form_copy', 'form', 4, '1');

-- 流程
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('flow', '流程', 'flow', NULL, 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('flow_add', '新增流程', 'flow_add', 'flow', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('flow_update', '编辑流程', 'flow_update', 'flow', 2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('flow_delete', '删除流程', 'flow_delete', 'flow', 3, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('flow_config', '配置流程', 'flow_config', 'flow', 4, '1');

-- 状态
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('status', '状态', 'status', NULL, 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('status_add', '新增状态', 'status_add', 'status', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('status_update', '编辑状态', 'status_update', 'status', 2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('status_delete', '删除状态', 'status_delete', 'status', 3, '1');

-- 集成开发下的服务集成、导入外部数据菜单权限
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('url_data', '服务集成', 'url_data', NULL, 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('url_data_update', '编辑路径', 'url_data_update', 'url_data', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('url_data_delete', '删除路径', 'url_data_delete', 'url_data', 2, '1');

INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('import', '导入外部数据', 'import', NULL, 2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('import_jira', '导入Jira', 'import_jira', 'import', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('import_mantis', '导入Mantis', 'import_mantis', 'import', 2, '1');

-- 项目
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project', '项目', 'project', NULL, 2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_add', '新增项目', 'project_add', 'project', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_update', '编辑项目', 'project_update', 'project', 2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_delete', '删除项目', 'project_delete', 'project', 3, '1');

-- 项目集
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_set', '项目集', 'project_set', NULL, 2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_set_add', '新增项目集', 'project_set_add', 'project_set', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_set_update', '编辑项目集', 'project_set_update', 'project_set', 2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_set_delete', '删除项目集', 'project_set_delete', 'project_set', 3, '1');

-- 产品
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('product', '产品', 'product', NULL, 2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('product_add', '新增产品', 'product_add', 'product', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('product_update', '编辑产品', 'product_update', 'product', 2, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('product_delete', '删除产品', 'product_delete', 'product', 3, '1');

-- 项目下设置的权限
UPDATE pcs_prc_function_group SET function_ids='domain_user,domain_role,domain_message,project_basic_info,project_work_type,project_flow,project_form,project_module,project_integration', sort=99, code='project_setting', id='project_setting' WHERE id ='ka_setting';
UPDATE pcs_prc_function SET name='删除成员' WHERE id = 'domain_user_delete';
UPDATE pcs_prc_function SET name='修改成员角色' WHERE id = 'domain_user_update';

-- 基础信息
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_basic_info', '基础信息', 'project_basic_info', NULL, 0, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_basic_info_update', '编辑项目', 'project_basic_info_update', 'project_basic_info', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_basic_info_delete', '删除项目', 'project_basic_info_delete', 'project_basic_info', 2, '2');

-- 事项类型
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_work_type', '事项类型', 'project_work_type', NULL, 4, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_work_type_add', '新增项目事项类型', 'project_work_type_add', 'project_work_type', 18, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_work_type_update', '编辑项目事项类型', 'project_work_type_update', 'project_work_type', 19, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_work_type_update_flow', '项目事项类型流程配置', 'project_work_type_update_flow', 'project_work_type', 20, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_work_type_update_form', '项目事项类型表单配置', 'project_work_type_update_form', 'project_work_type', 21, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_work_type_update_permissions', '项目事项类型权限配置', 'project_work_type_update_permissions', 'project_work_type', 22, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_work_type_delete', '删除项目事项类型', 'project_work_type_delete', 'project_work_type', 23, '2');

-- 流程
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_flow', '流程', 'project_flow', NULL, 5, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_flow_add', '创建项目流程', 'project_flow_add', 'project_flow', 23, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_flow_link', '关联系统流程', 'project_flow_link', 'project_flow', 24, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_flow_update', '编辑项目流程', 'project_flow_update', 'project_flow', 25, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_flow_delete', '删除项目流程', 'project_flow_delete', 'project_flow', 26, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_flow_config', '配置项目流程', 'project_flow_config', 'project_flow', 27, '2');

-- 表单
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_form', '表单', 'project_form', NULL, 6, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_form_add', '创建项目表单', 'project_form_add', 'project_form', 28, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_form_link', '添加系统表单', 'project_form_link', 'project_form', 29, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_form_update', '编辑项目表单', 'project_form_update', 'project_form', 30, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_form_delete', '删除项目表单', 'project_form_delete', 'project_form', 31, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_form_config', '配置项目表单', 'project_form_config', 'project_form', 32, '2');

-- 模块
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_module', '模块', 'project_module', NULL, 7, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_module_add', '新增项目模块', 'project_module_add', 'project_module', 33, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_module_update', '编辑项目模块', 'project_module_update', 'project_module', 34, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_module_delete', '删除项目模块', 'project_module_delete', 'project_module', 35, '2');

-- 集成
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_integration', '集成', 'project_integration', NULL, 8, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_integration_sward_add', '关联sward知识库', 'project_integration_sward_add', 'project_integration', 36, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_integration_sward_delete', '删除sward知识库', 'project_integration_sward_delete', 'project_integration', 37, '2');

-- 项目级权限  事项
INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('work', '事项', 'work', 'work', 1, 2);

INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('work', '事项', 'work', NULL, 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('work_add', '新增事项', 'work_add', 'work', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('work_update', '编辑事项', 'work_update', 'work', 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('work_delete', '删除事项', 'work_delete', 'work', 3, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('work_link_work', '关联事项', 'work_link_work', 'work', 4, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('work_create_test_case', '事项创建测试用例', 'work_create_test_case', 'work', 5, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('work_link_test_case', '事项关联测试用例', 'work_link_test_case', 'work', 6, '2');

-- 项目级权限  阶段
INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('stage', '阶段', 'stage', 'stage', 2, 2);

INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('stage', '阶段', 'stage', NULL, 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('stage_add', '新增阶段', 'stage_add', 'stage', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('stage_update', '编辑阶段', 'stage_update', 'stage', 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('stage_delete', '删除阶段', 'stage_delete', 'stage', 3, '2');

-- 项目级权限  迭代
INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('sprint', '迭代', 'sprint', 'sprint', 3, 2);

INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('sprint', '迭代', 'sprint', NULL, 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('sprint_add', '新增迭代', 'sprint_add', 'sprint', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('sprint_update', '编辑迭代', 'sprint_update', 'sprint', 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('sprint_delete', '删除迭代', 'sprint_delete', 'sprint', 3, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('sprint_plan_work', '规划事项', 'sprint_plan_work', 'sprint', 4, '2');

-- 项目级权限  版本
INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('version', '版本', 'version', 'version', 4, 2);

INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('version', '版本', 'version', NULL, 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('version_add', '新增版本', 'version_add', 'version', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('version_update', '编辑版本', 'version_update', 'version', 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('version_delete', '删除版本', 'version_delete', 'version', 3, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('version_plan_work', '规划事项', 'version_plan_work', 'version', 4, '2');

-- 项目权限  测试
INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('test', '测试', 'test', 'test_case,test_plan,test_report', 5, 2);

INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('test_case', '测试用例', 'test_case', NULL, 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('test_case_add', '新增测试用例', 'test_case_add', 'test_case', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('test_case_update', '编辑测试用例', 'test_case_update', 'test_case', 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('test_case_delete', '删除测试用例', 'test_case_delete', 'test_case', 3, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('test_case_create_defect', '测试用例创建缺陷', 'test_case_create_defect', 'test_case', 4, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('test_case_link_defect', '测试用例关联缺陷', 'test_case_link_defect', 'test_case', 5, '2');

INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('test_plan', '测试计划', 'test_plan', NULL, 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('test_plan_add', '新增测试计划', 'test_plan_add', 'test_plan', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('test_plan_update', '编辑测试计划', 'test_plan_update', 'test_plan', 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('test_plan_delete', '删除测试计划', 'test_plan_delete', 'test_plan', 3, '2');

INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('test_report', '测试报告', 'test_report', NULL, 3, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('test_report_add', '新增测试报告', 'test_report_add', 'test_report', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('test_report_update', '编辑测试报告', 'test_report_update', 'test_report', 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('test_report_delete', '删除测试报告', 'test_report_delete', 'test_report', 3, '2');

-- 项目权限  文档
INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('wiki', '文档', 'wiki', 'wiki', 6, 2);

INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki', '文档', 'wiki', NULL, 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_add', '新增文档', 'wiki_add', 'wiki', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('wiki_delete', '删除文档', 'wiki_delete', 'wiki', 2, '2');

-- 项目权限 评审
INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('appraised', '评审', 'appraised', 'appraised', 7, 2);

INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('appraised', '评审', 'appraised', NULL, 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('appraised_add', '新增评审', 'appraised_add', 'appraised', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('appraised_update', '编辑评审', 'appraised_update', 'appraised', 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('appraised_delete', '删除评审', 'appraised_delete', 'appraised', 3, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('appraised_link_item', '关联评审项', 'appraised_link_item', 'appraised', 4, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('appraised_delete_item', '删除评审项', 'appraised_delete_item', 'appraised', 5, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('appraised_update_item', '编辑评审项', 'appraised_update_item', 'appraised', 6, '2');

-- 项目权限 里程碑
INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
VALUES ('milestone', '里程碑', 'milestone', 'appraised', 8, 2);

INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('milestone', '里程碑', 'milestone', NULL, 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('milestone_add', '新增里程碑', 'milestone_add', 'milestone', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('milestone_update', '编辑里程碑', 'milestone_update', 'milestone', 2, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('milestone_delete', '删除里程碑', 'milestone_delete', 'milestone', 3, '2');

-- -- 项目集权限  设置
-- INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
-- VALUES ('project_set_setting', '设置', 'project_set_setting', 'domain_user,domain_role,project_set_basic_info', 100, 2);
--
-- -- 项目集下基础信息
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_set_basic_info', '基础信息', 'project_set_basic_info', NULL, 0, '2');
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_set_basic_info_update', '编辑项目集', 'project_set_basic_info_update', 'project_set_basic_info', 1, '2');
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_set_basic_info_delete', '删除项目集', 'project_set_basic_info_delete', 'project_set_basic_info', 2, '2');
--
-- -- 项目集下项目
-- INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
-- VALUES ('project_set_project', '项目', 'project_set_project', 'project_set_project', 20, 2);
--
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_set_project', '项目', 'project_set_project', NULL, 1, '2');
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_set_project_add', '添加项目', 'project_set_project_add', 'project_set_project', 4, '2');
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_set_project_link', '关联项目', 'project_set_project_link', 'project_set_project', 5, '2');
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('project_set_project_delete', '删除项目', 'project_set_project_delete', 'project_set_project', 6, '2');
--
-- -- 项目集下事项
-- INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
-- VALUES ('project_set_work', '事项', 'project_set_work', 'work', 21, 2);
--
-- -- 产品权限  设置
-- INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
-- VALUES ('product_setting', '设置', 'product_setting', 'domain_user,domain_role,product_basic_info', 100, 2);
--
-- -- 产品 基础信息
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('product_basic_info', '基础信息', 'product_basic_info', NULL, 0, '2');
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('product_basic_info_update', '编辑产品', 'product_basic_info_update', 'product_basic_info', 1, '2');
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('product_basic_info_delete', '删除产品', 'product_basic_info_delete', 'product_basic_info', 2, '2');
--
-- -- 产品 计划
-- INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
-- VALUES ('product_plan', '计划', 'product_plan', 'product_plan,work', 40, 2);
--
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('product_plan', '计划', 'product_plan', NULL, 1, '2');
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('product_plan_add', '添加计划', 'product_plan_add', 'product_plan', 1, '2');
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('product_plan_update', '编辑计划', 'product_plan_link', 'product_plan', 2, '2');
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('product_plan_delete', '删除计划', 'product_plan_delete', 'product_plan', 3, '2');
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('product_plan_link_work', '关联事项', 'product_plan_link_work', 'product_plan', 6, '2');
--
-- -- 产品 需求
-- INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
-- VALUES ('product_demand', '需求', 'product_demand', 'work', 41, 2);
--
-- -- 产品 路线图
-- INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
-- VALUES ('product_linemap', '路线图', 'product_linemap', 'work', 42, 2);
--
-- -- 产品 模块
-- INSERT INTO pcs_prc_function_group (id, name, code, function_ids, sort, type)
-- VALUES ('product_module', '模块', 'product_module', 'product_module', 42, 2);
--
-- -- 模块
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('product_module', '模块', 'product_module', NULL, 7, '2');
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('product_module_add', '新增产品模块', 'product_module_add', 'product_module', 33, '2');
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('product_module_update', '编辑产品模块', 'product_module_update', 'product_module', 34, '2');
-- INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('product_module_delete', '删除产品模块', 'product_module_delete', 'product_module', 35, '2');