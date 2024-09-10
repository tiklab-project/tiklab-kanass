INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('7f63d59a4f3a', 'project1.png', 'project1.png', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('1566ca2f8ea6', 'project2.png', 'project2.png', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('8fe41d811db8', 'project3.png', 'project3.png', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('c35cb5b60b2d', 'project4.png', 'project4.png', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('f6f63073d412', 'workType1.png', 'workType1.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('24e8be1867b8', 'workType2.png', 'workType2.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('abeb5a0e194a', 'workType3.png', 'workType3.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('070287809b9a', 'workType4.png', 'workType4.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('ff54eafd3756', 'workType5.png', 'workType5.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('2f950b04363c', 'workType6.png', 'workType6.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('5ce7cab32924', 'workType7.png', 'workType7.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('c3ee42e730ac', 'workType8.png', 'workType8.png', 'workType');


INSERT INTO pmc_project (id, project_key, project_name, project_type_id, project_limits, creator, master, description, project_set_id, project_state, start_time, end_time, icon_url) VALUES ('4cf6d4dec7d0', 'apis', '接口关联软件开发', '5a46432a', '0', '111111', '111111', 'asa', NULL, '1', '2022-12-13 00:00:00', '2022-12-31 00:00:00', 'project3.png');
INSERT INTO pmc_project (id, project_key, project_name, project_type_id, project_limits, creator, master, description, project_set_id, project_state, start_time, end_time, icon_url) VALUES ('f3fdc893ff25', 'matflow', '流水线项目', 'ea782c6d', '0', '111111', '111111', NULL, NULL, '1', '2022-12-13 00:00:00', '2023-01-31 00:00:00', 'project1.png');

INSERT INTO pmc_project_type (id, type, name, icon_url, description) VALUES ('5a46432a', 'scrum', '敏捷式项目', 'project1.png', '敏捷式项目');
INSERT INTO pmc_project_type (id, type, name, icon_url, description) VALUES ('ea782c6d', 'nomal', '瀑布研发项目', 'project2.png', '瀑布研发项目');

INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time, icon_url, project_type_id) VALUES ('2ef6e8e5c480', '开发流水线项目', 'workItem', '295c16e604c3', '111111', 'f3fdc893ff25', '2024-08-22 15:39:16.641', 'workType2.png', 'ea782c6d');
INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time, icon_url, project_type_id) VALUES ('bfe1fd387d19', '流水线启动失败', 'workItem', 'e3a468f851a2', '111111', 'f3fdc893ff25', '2024-08-22 15:39:19.423', 'workType3.png', 'ea782c6d');
INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time, icon_url, project_type_id) VALUES ('91760ccada62', '流水线运行', 'workItem', '41ba6346e99c', '111111', 'f3fdc893ff25', '2024-08-22 15:39:22.154', 'workType1.png', 'ea782c6d');
INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time, icon_url, project_type_id) VALUES ('126acc7f4bba', '接口关联软件开发', 'project', '4cf6d4dec7d0', '111111', '4cf6d4dec7d0', '2024-08-22 15:56:25.813', 'project3.png', '5a46432a');
INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time, icon_url, project_type_id) VALUES ('e22cd51190d7', '接口开发', 'workItem', '511b05fdde15', '111111', '4cf6d4dec7d0', '2024-08-22 16:14:18.078', 'workType2.png', '5a46432a');
INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time, icon_url, project_type_id) VALUES ('4d0350b1fbba', '接受数据失败', 'workItem', '4c2d13dbc873', '111111', '4cf6d4dec7d0', '2024-08-22 16:14:23.47', 'workType3.png', '5a46432a');
INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time, icon_url, project_type_id) VALUES ('84666a41a261', '接口设计', 'workItem', 'aaf1bdb01f96', '111111', '4cf6d4dec7d0', '2024-08-22 16:14:26.089', 'workType1.png', '5a46432a');

INSERT INTO pmc_sprint (id, sprint_name, description, project_id, master, sprint_state_id, start_time, end_time, rela_start_time, rela_end_time, builder, color) VALUES ('0410da30425b', '迭代1', NULL, '4cf6d4dec7d0', '111111', '000000', '2022-12-13 00:00:00', '2023-01-31 00:00:00', NULL, NULL, NULL, 1);
INSERT INTO pmc_sprint (id, sprint_name, description, project_id, master, sprint_state_id, start_time, end_time, rela_start_time, rela_end_time, builder, color) VALUES ('caa684dfa094', '迭代2', NULL, '4cf6d4dec7d0', '111111', '000000', '2022-12-13 00:00:00', '2023-01-31 00:00:00', NULL, NULL, NULL, 3);

INSERT INTO pmc_sprint_status (id, name, description, sort, grouper) VALUES ('000000', '未开始', '未开始', 1, 'system');
INSERT INTO pmc_sprint_status (id, name, description, sort, grouper) VALUES ('111111', '进行中', '进行中', 2, 'system');
INSERT INTO pmc_sprint_status (id, name, description, sort, grouper) VALUES ('222222', '已完成', '已完成', 3, 'system');

INSERT INTO pmc_stage (id, stage_name, parent_id, status, progress, master, description, project_id, start_time, end_time, tree_path, root_id, deep, color) VALUES ('17fd0338dbca', '开发阶段', NULL, '0', 0, '111111', NULL, 'f3fdc893ff25', '2024-05-14 00:00:00', '2024-06-25 00:00:00', NULL, '17fd0338dbca', 0, 4);

INSERT INTO pmc_system_url (id, name, system_url, web_url) VALUES ('f7ee9a10', 'sward', 'http://sward.tiklab.net', 'http://sward.tiklab.net');
INSERT INTO pmc_system_url (id, name, system_url, web_url) VALUES ('991f95a1', 'teston', 'http://teston.tiklab.net', 'http://teston.tiklab.net');

INSERT INTO pmc_version_status (id, name, description, sort, grouper) VALUES ('000000', '未开始', '未开始', 1, 'system');
INSERT INTO pmc_version_status (id, name, description, sort, grouper) VALUES ('111111', '进行中', '进行中', 2, 'system');
INSERT INTO pmc_version_status (id, name, description, sort, grouper) VALUES ('222222', '已完成', '已完成', 3, 'system');

INSERT INTO pmc_work_item (id, order_num, root_id, title, description, parent_id, tree_path, project_id, work_type_id, work_priority_id, work_status_id, module_id, sprint_id, version_id, pre_depend_id, builder_id, assigner_id, reporter_id, plan_begin_time, plan_end_time, actual_begin_time, actual_end_time, percent, estimate_time, ext_data, build_time, work_status_code, work_type_code, surplus_time, work_type_sys_id, work_status_node_id, each_type, update_time, code, stage_id) VALUES ('4c2d13dbc873', 3, '4c2d13dbc873', '接受数据失败', '[{type:paragraph,children:[{text:}]}]', NULL, NULL, '4cf6d4dec7d0', 'a64e8050', 'faaecb3d', 'b86f5b33', NULL, '0410da30425b', NULL, NULL, '111111', '111111', NULL, '2024-05-14 00:00:00', '2024-05-14 23:59:59', NULL, NULL, 0, 0, NULL, '2024-05-14 14:44:56', 'TODO', 'defect', 0, '98121701', 'todo', '4922a294', NULL, 'apis-3', NULL);
INSERT INTO pmc_work_item (id, order_num, root_id, title, description, parent_id, tree_path, project_id, work_type_id, work_priority_id, work_status_id, module_id, sprint_id, version_id, pre_depend_id, builder_id, assigner_id, reporter_id, plan_begin_time, plan_end_time, actual_begin_time, actual_end_time, percent, estimate_time, ext_data, build_time, work_status_code, work_type_code, surplus_time, work_type_sys_id, work_status_node_id, each_type, update_time, code, stage_id) VALUES ('aaf1bdb01f96', 1, 'aaf1bdb01f96', '接口设计', '[{type:paragraph,children:[{text:}]}]', NULL, NULL, '4cf6d4dec7d0', 'b81aef3b', 'faaecb3d', '21bfa706', NULL, '0410da30425b', NULL, NULL, '111111', '111111', NULL, '2024-05-14 00:00:00', '2024-05-14 23:59:59', NULL, NULL, 0, 0, NULL, '2024-05-14 14:44:42', 'TODO', 'demand', 0, '7055ebc6', 'todo', '6c1d0472', NULL, 'apis-1', NULL);
INSERT INTO pmc_work_item (id, order_num, root_id, title, description, parent_id, tree_path, project_id, work_type_id, work_priority_id, work_status_id, module_id, sprint_id, version_id, pre_depend_id, builder_id, assigner_id, reporter_id, plan_begin_time, plan_end_time, actual_begin_time, actual_end_time, percent, estimate_time, ext_data, build_time, work_status_code, work_type_code, surplus_time, work_type_sys_id, work_status_node_id, each_type, update_time, code, stage_id) VALUES ('511b05fdde15', 2, '511b05fdde15', '接口开发', '[{type:paragraph,children:[{text:}]}]', NULL, NULL, '4cf6d4dec7d0', 'd3ef6551', 'faaecb3d', '5d4f471a', NULL, '0410da30425b', NULL, NULL, '111111', '111111', NULL, '2024-05-14 00:00:00', '2024-05-14 23:59:59', NULL, NULL, 0, 0, NULL, '2024-05-14 14:44:50', 'TODO', 'task', 0, '778222e0', 'todo', '03da940d', NULL, 'apis-2', NULL);
INSERT INTO pmc_work_item (id, order_num, root_id, title, description, parent_id, tree_path, project_id, work_type_id, work_priority_id, work_status_id, module_id, sprint_id, version_id, pre_depend_id, builder_id, assigner_id, reporter_id, plan_begin_time, plan_end_time, actual_begin_time, actual_end_time, percent, estimate_time, ext_data, build_time, work_status_code, work_type_code, surplus_time, work_type_sys_id, work_status_node_id, each_type, update_time, code, stage_id) VALUES ('e3a468f851a2', 3, 'e3a468f851a2', '流水线启动失败', '[{type:paragraph,children:[{text:}]}]', NULL, NULL, 'f3fdc893ff25', 'dcbf7211', 'faaecb3d', 'b73addff', NULL, NULL, NULL, NULL, '111111', '111111', NULL, '2024-05-14 00:00:00', '2024-05-14 23:59:59', NULL, NULL, 0, 0, NULL, '2024-05-14 14:42:55', 'TODO', 'defect', 0, '98121701', 'todo', '4922a294', NULL, 'matflow-3', '17fd0338dbca');
INSERT INTO pmc_work_item (id, order_num, root_id, title, description, parent_id, tree_path, project_id, work_type_id, work_priority_id, work_status_id, module_id, sprint_id, version_id, pre_depend_id, builder_id, assigner_id, reporter_id, plan_begin_time, plan_end_time, actual_begin_time, actual_end_time, percent, estimate_time, ext_data, build_time, work_status_code, work_type_code, surplus_time, work_type_sys_id, work_status_node_id, each_type, update_time, code, stage_id) VALUES ('41ba6346e99c', 1, '41ba6346e99c', '流水线运行', '[{type:paragraph,children:[{text:}]}]', NULL, NULL, 'f3fdc893ff25', 'ef23b25f', 'faaecb3d', '962987f7', NULL, NULL, NULL, NULL, '111111', '111111', NULL, '2024-05-14 00:00:00', '2024-05-14 23:59:59', NULL, NULL, 0, 0, NULL, '2024-05-14 14:42:24', 'TODO', 'demand', 0, '7055ebc6', 'todo', '6c1d0472', NULL, 'matflow-1', '17fd0338dbca');
INSERT INTO pmc_work_item (id, order_num, root_id, title, description, parent_id, tree_path, project_id, work_type_id, work_priority_id, work_status_id, module_id, sprint_id, version_id, pre_depend_id, builder_id, assigner_id, reporter_id, plan_begin_time, plan_end_time, actual_begin_time, actual_end_time, percent, estimate_time, ext_data, build_time, work_status_code, work_type_code, surplus_time, work_type_sys_id, work_status_node_id, each_type, update_time, code, stage_id) VALUES ('295c16e604c3', 2, '295c16e604c3', '开发流水线项目', '[{type:paragraph,children:[{text:}]}]', NULL, NULL, 'f3fdc893ff25', 'c9e59337', 'faaecb3d', 'a1297b4b', NULL, NULL, NULL, NULL, '111111', '111111', NULL, '2024-05-14 00:00:00', '2024-05-14 23:59:59', NULL, NULL, 0, 0, NULL, '2024-05-14 14:42:46', 'TODO', 'task', 0, '778222e0', 'todo', '03da940d', NULL, 'matflow-2', '17fd0338dbca');

INSERT INTO pmc_work_type (id, name, description, code, form_id, flow_id, icon_url, grouper, sort, scope) VALUES ('7055ebc6', '需求', '需求', 'demand', '0c5d4ff9', 'a96cf9c9', 'workType1.png', 'system', '1', 0);
INSERT INTO pmc_work_type (id, name, description, code, form_id, flow_id, icon_url, grouper, sort, scope) VALUES ('778222e0', '任务', '任务', 'task', '515f17bd', '4d040c6d', 'workType2.png', 'system', '2', 0);
INSERT INTO pmc_work_type (id, name, description, code, form_id, flow_id, icon_url, grouper, sort, scope) VALUES ('98121701', '缺陷', '缺陷', 'defect', '607f6be6', '3d879830', 'workType3.png', 'system', '3', 0);

INSERT INTO pmc_work_type_dm (id, project_id, work_type_id, form_id, flow_id) VALUES ('b81aef3b', '4cf6d4dec7d0', '7055ebc6', 'cd46de1f', '6fef9d3d');
INSERT INTO pmc_work_type_dm (id, project_id, work_type_id, form_id, flow_id) VALUES ('a64e8050', '4cf6d4dec7d0', '98121701', 'c323f12b', '5b1c9c40');
INSERT INTO pmc_work_type_dm (id, project_id, work_type_id, form_id, flow_id) VALUES ('d3ef6551', '4cf6d4dec7d0', '778222e0', '12267b6d', '9e7f5ea4');
INSERT INTO pmc_work_type_dm (id, project_id, work_type_id, form_id, flow_id) VALUES ('ef23b25f', 'f3fdc893ff25', '7055ebc6', '0e425461', 'b1c53a54');
INSERT INTO pmc_work_type_dm (id, project_id, work_type_id, form_id, flow_id) VALUES ('dcbf7211', 'f3fdc893ff25', '98121701', 'c430e1ad', '34d7bf2c');
INSERT INTO pmc_work_type_dm (id, project_id, work_type_id, form_id, flow_id) VALUES ('c9e59337', 'f3fdc893ff25', '778222e0', '9c338860', '3b0fc788');

INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('7f63d59a4f3a', 'project1.png', 'project1.png', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('1566ca2f8ea6', 'project2.png', 'project2.png', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('8fe41d811db8', 'project3.png', 'project3.png', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('c35cb5b60b2d', 'project4.png', 'project4.png', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('f6f63073d412', 'workType1.png', 'workType1.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('24e8be1867b8', 'workType2.png', 'workType2.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('abeb5a0e194a', 'workType3.png', 'workType3.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('070287809b9a', 'workType4.png', 'workType4.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('ff54eafd3756', 'workType5.png', 'workType5.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('2f950b04363c', 'workType6.png', 'workType6.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('5ce7cab32924', 'workType7.png', 'workType7.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('c3ee42e730ac', 'workType8.png', 'workType8.png', 'workType');


INSERT INTO pmc_project (id, project_key, project_name, project_type_id, project_limits, creator, master, description, project_set_id, project_state, start_time, end_time, icon_url) VALUES ('4cf6d4dec7d0', 'apis', '接口关联软件开发', '5a46432a', '0', '111111', '111111', 'asa', NULL, '1', '2022-12-13 00:00:00', '2022-12-31 00:00:00', 'project3.png');
INSERT INTO pmc_project (id, project_key, project_name, project_type_id, project_limits, creator, master, description, project_set_id, project_state, start_time, end_time, icon_url) VALUES ('f3fdc893ff25', 'matflow', '流水线项目', 'ea782c6d', '0', '111111', '111111', NULL, NULL, '1', '2022-12-13 00:00:00', '2023-01-31 00:00:00', 'project1.png');

INSERT INTO pmc_project_type (id, type, name, icon_url, description) VALUES ('5a46432a', 'scrum', '敏捷式项目', 'project1.png', '敏捷式项目');
INSERT INTO pmc_project_type (id, type, name, icon_url, description) VALUES ('ea782c6d', 'nomal', '瀑布研发项目', 'project2.png', '瀑布研发项目');

INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time, icon_url, project_type_id) VALUES ('2ef6e8e5c480', '开发流水线项目', 'workItem', '295c16e604c3', '111111', 'f3fdc893ff25', '2024-08-22 15:39:16.641', 'workType2.png', 'ea782c6d');
INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time, icon_url, project_type_id) VALUES ('bfe1fd387d19', '流水线启动失败', 'workItem', 'e3a468f851a2', '111111', 'f3fdc893ff25', '2024-08-22 15:39:19.423', 'workType3.png', 'ea782c6d');
INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time, icon_url, project_type_id) VALUES ('91760ccada62', '流水线运行', 'workItem', '41ba6346e99c', '111111', 'f3fdc893ff25', '2024-08-22 15:39:22.154', 'workType1.png', 'ea782c6d');
INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time, icon_url, project_type_id) VALUES ('126acc7f4bba', '接口关联软件开发', 'project', '4cf6d4dec7d0', '111111', '4cf6d4dec7d0', '2024-08-22 15:56:25.813', 'project3.png', '5a46432a');
INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time, icon_url, project_type_id) VALUES ('e22cd51190d7', '接口开发', 'workItem', '511b05fdde15', '111111', '4cf6d4dec7d0', '2024-08-22 16:14:18.078', 'workType2.png', '5a46432a');
INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time, icon_url, project_type_id) VALUES ('4d0350b1fbba', '接受数据失败', 'workItem', '4c2d13dbc873', '111111', '4cf6d4dec7d0', '2024-08-22 16:14:23.47', 'workType3.png', '5a46432a');
INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time, icon_url, project_type_id) VALUES ('84666a41a261', '接口设计', 'workItem', 'aaf1bdb01f96', '111111', '4cf6d4dec7d0', '2024-08-22 16:14:26.089', 'workType1.png', '5a46432a');

INSERT INTO pmc_sprint (id, sprint_name, description, project_id, master, sprint_state_id, start_time, end_time, rela_start_time, rela_end_time, builder, color) VALUES ('0410da30425b', '迭代1', NULL, '4cf6d4dec7d0', '111111', '000000', '2022-12-13 00:00:00', '2023-01-31 00:00:00', NULL, NULL, NULL, 1);
INSERT INTO pmc_sprint (id, sprint_name, description, project_id, master, sprint_state_id, start_time, end_time, rela_start_time, rela_end_time, builder, color) VALUES ('caa684dfa094', '迭代2', NULL, '4cf6d4dec7d0', '111111', '000000', '2022-12-13 00:00:00', '2023-01-31 00:00:00', NULL, NULL, NULL, 3);

INSERT INTO pmc_sprint_status (id, name, description, sort, grouper) VALUES ('000000', '未开始', '未开始', 1, 'system');
INSERT INTO pmc_sprint_status (id, name, description, sort, grouper) VALUES ('111111', '进行中', '进行中', 2, 'system');
INSERT INTO pmc_sprint_status (id, name, description, sort, grouper) VALUES ('222222', '已完成', '已完成', 3, 'system');

INSERT INTO pmc_stage (id, stage_name, parent_id, status, progress, master, description, project_id, start_time, end_time, tree_path, root_id, deep, color) VALUES ('17fd0338dbca', '开发阶段', NULL, '0', 0, '111111', NULL, 'f3fdc893ff25', '2024-05-14 00:00:00', '2024-06-25 00:00:00', NULL, '17fd0338dbca', 0, 4);

INSERT INTO pmc_system_url (id, name, system_url, web_url) VALUES ('f7ee9a10', 'sward', 'http://sward.tiklab.net', 'http://sward.tiklab.net');
INSERT INTO pmc_system_url (id, name, system_url, web_url) VALUES ('991f95a1', 'teston', 'http://teston.tiklab.net', 'http://teston.tiklab.net');

INSERT INTO pmc_version_status (id, name, description, sort, grouper) VALUES ('000000', '未开始', '未开始', 1, 'system');
INSERT INTO pmc_version_status (id, name, description, sort, grouper) VALUES ('111111', '进行中', '进行中', 2, 'system');
INSERT INTO pmc_version_status (id, name, description, sort, grouper) VALUES ('222222', '已完成', '已完成', 3, 'system');

INSERT INTO pmc_work_item (id, order_num, root_id, title, description, parent_id, tree_path, project_id, work_type_id, work_priority_id, work_status_id, module_id, sprint_id, version_id, pre_depend_id, builder_id, assigner_id, reporter_id, plan_begin_time, plan_end_time, actual_begin_time, actual_end_time, percent, estimate_time, ext_data, build_time, work_status_code, work_type_code, surplus_time, work_type_sys_id, work_status_node_id, each_type, update_time, code, stage_id) VALUES ('4c2d13dbc873', 3, '4c2d13dbc873', '接受数据失败', '[{type:paragraph,children:[{text:}]}]', NULL, NULL, '4cf6d4dec7d0', 'a64e8050', 'faaecb3d', 'b86f5b33', NULL, '0410da30425b', NULL, NULL, '111111', '111111', NULL, '2024-05-14 00:00:00', '2024-05-14 23:59:59', NULL, NULL, 0, 0, NULL, '2024-05-14 14:44:56', 'TODO', 'defect', 0, '98121701', 'todo', '4922a294', NULL, 'apis-3', NULL);
INSERT INTO pmc_work_item (id, order_num, root_id, title, description, parent_id, tree_path, project_id, work_type_id, work_priority_id, work_status_id, module_id, sprint_id, version_id, pre_depend_id, builder_id, assigner_id, reporter_id, plan_begin_time, plan_end_time, actual_begin_time, actual_end_time, percent, estimate_time, ext_data, build_time, work_status_code, work_type_code, surplus_time, work_type_sys_id, work_status_node_id, each_type, update_time, code, stage_id) VALUES ('aaf1bdb01f96', 1, 'aaf1bdb01f96', '接口设计', '[{type:paragraph,children:[{text:}]}]', NULL, NULL, '4cf6d4dec7d0', 'b81aef3b', 'faaecb3d', '21bfa706', NULL, '0410da30425b', NULL, NULL, '111111', '111111', NULL, '2024-05-14 00:00:00', '2024-05-14 23:59:59', NULL, NULL, 0, 0, NULL, '2024-05-14 14:44:42', 'TODO', 'demand', 0, '7055ebc6', 'todo', '6c1d0472', NULL, 'apis-1', NULL);
INSERT INTO pmc_work_item (id, order_num, root_id, title, description, parent_id, tree_path, project_id, work_type_id, work_priority_id, work_status_id, module_id, sprint_id, version_id, pre_depend_id, builder_id, assigner_id, reporter_id, plan_begin_time, plan_end_time, actual_begin_time, actual_end_time, percent, estimate_time, ext_data, build_time, work_status_code, work_type_code, surplus_time, work_type_sys_id, work_status_node_id, each_type, update_time, code, stage_id) VALUES ('511b05fdde15', 2, '511b05fdde15', '接口开发', '[{type:paragraph,children:[{text:}]}]', NULL, NULL, '4cf6d4dec7d0', 'd3ef6551', 'faaecb3d', '5d4f471a', NULL, '0410da30425b', NULL, NULL, '111111', '111111', NULL, '2024-05-14 00:00:00', '2024-05-14 23:59:59', NULL, NULL, 0, 0, NULL, '2024-05-14 14:44:50', 'TODO', 'task', 0, '778222e0', 'todo', '03da940d', NULL, 'apis-2', NULL);
INSERT INTO pmc_work_item (id, order_num, root_id, title, description, parent_id, tree_path, project_id, work_type_id, work_priority_id, work_status_id, module_id, sprint_id, version_id, pre_depend_id, builder_id, assigner_id, reporter_id, plan_begin_time, plan_end_time, actual_begin_time, actual_end_time, percent, estimate_time, ext_data, build_time, work_status_code, work_type_code, surplus_time, work_type_sys_id, work_status_node_id, each_type, update_time, code, stage_id) VALUES ('e3a468f851a2', 3, 'e3a468f851a2', '流水线启动失败', '[{type:paragraph,children:[{text:}]}]', NULL, NULL, 'f3fdc893ff25', 'dcbf7211', 'faaecb3d', 'b73addff', NULL, NULL, NULL, NULL, '111111', '111111', NULL, '2024-05-14 00:00:00', '2024-05-14 23:59:59', NULL, NULL, 0, 0, NULL, '2024-05-14 14:42:55', 'TODO', 'defect', 0, '98121701', 'todo', '4922a294', NULL, 'matflow-3', '17fd0338dbca');
INSERT INTO pmc_work_item (id, order_num, root_id, title, description, parent_id, tree_path, project_id, work_type_id, work_priority_id, work_status_id, module_id, sprint_id, version_id, pre_depend_id, builder_id, assigner_id, reporter_id, plan_begin_time, plan_end_time, actual_begin_time, actual_end_time, percent, estimate_time, ext_data, build_time, work_status_code, work_type_code, surplus_time, work_type_sys_id, work_status_node_id, each_type, update_time, code, stage_id) VALUES ('41ba6346e99c', 1, '41ba6346e99c', '流水线运行', '[{type:paragraph,children:[{text:}]}]', NULL, NULL, 'f3fdc893ff25', 'ef23b25f', 'faaecb3d', '962987f7', NULL, NULL, NULL, NULL, '111111', '111111', NULL, '2024-05-14 00:00:00', '2024-05-14 23:59:59', NULL, NULL, 0, 0, NULL, '2024-05-14 14:42:24', 'TODO', 'demand', 0, '7055ebc6', 'todo', '6c1d0472', NULL, 'matflow-1', '17fd0338dbca');
INSERT INTO pmc_work_item (id, order_num, root_id, title, description, parent_id, tree_path, project_id, work_type_id, work_priority_id, work_status_id, module_id, sprint_id, version_id, pre_depend_id, builder_id, assigner_id, reporter_id, plan_begin_time, plan_end_time, actual_begin_time, actual_end_time, percent, estimate_time, ext_data, build_time, work_status_code, work_type_code, surplus_time, work_type_sys_id, work_status_node_id, each_type, update_time, code, stage_id) VALUES ('295c16e604c3', 2, '295c16e604c3', '开发流水线项目', '[{type:paragraph,children:[{text:}]}]', NULL, NULL, 'f3fdc893ff25', 'c9e59337', 'faaecb3d', 'a1297b4b', NULL, NULL, NULL, NULL, '111111', '111111', NULL, '2024-05-14 00:00:00', '2024-05-14 23:59:59', NULL, NULL, 0, 0, NULL, '2024-05-14 14:42:46', 'TODO', 'task', 0, '778222e0', 'todo', '03da940d', NULL, 'matflow-2', '17fd0338dbca');

INSERT INTO pmc_work_type (id, name, description, code, form_id, flow_id, icon_url, grouper, sort, scope) VALUES ('7055ebc6', '需求', '需求', 'demand', '0c5d4ff9', 'a96cf9c9', 'workType1.png', 'system', '1', 0);
INSERT INTO pmc_work_type (id, name, description, code, form_id, flow_id, icon_url, grouper, sort, scope) VALUES ('778222e0', '任务', '任务', 'task', '515f17bd', '4d040c6d', 'workType2.png', 'system', '2', 0);
INSERT INTO pmc_work_type (id, name, description, code, form_id, flow_id, icon_url, grouper, sort, scope) VALUES ('98121701', '缺陷', '缺陷', 'defect', '607f6be6', '3d879830', 'workType3.png', 'system', '3', 0);

INSERT INTO pmc_work_type_dm (id, project_id, work_type_id, form_id, flow_id) VALUES ('b81aef3b', '4cf6d4dec7d0', '7055ebc6', 'cd46de1f', '6fef9d3d');
INSERT INTO pmc_work_type_dm (id, project_id, work_type_id, form_id, flow_id) VALUES ('a64e8050', '4cf6d4dec7d0', '98121701', 'c323f12b', '5b1c9c40');
INSERT INTO pmc_work_type_dm (id, project_id, work_type_id, form_id, flow_id) VALUES ('d3ef6551', '4cf6d4dec7d0', '778222e0', '12267b6d', '9e7f5ea4');
INSERT INTO pmc_work_type_dm (id, project_id, work_type_id, form_id, flow_id) VALUES ('ef23b25f', 'f3fdc893ff25', '7055ebc6', '0e425461', 'b1c53a54');
INSERT INTO pmc_work_type_dm (id, project_id, work_type_id, form_id, flow_id) VALUES ('dcbf7211', 'f3fdc893ff25', '98121701', 'c430e1ad', '34d7bf2c');
INSERT INTO pmc_work_type_dm (id, project_id, work_type_id, form_id, flow_id) VALUES ('c9e59337', 'f3fdc893ff25', '778222e0', '9c338860', '3b0fc788');
