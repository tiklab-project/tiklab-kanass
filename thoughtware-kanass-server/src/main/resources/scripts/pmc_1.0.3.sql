

UPDATE pmc_project set icon_url='/image/9de30c3db540aaad' WHERE id!='f3fdc893ff25';
UPDATE pmc_project set icon_url='/image/eee419bfc40067e2' WHERE id='f3fdc893ff25';

UPDATE pmc_work_type set icon_url='/image/5c2274bfdefae884' WHERE id='7055ebc6';
UPDATE pmc_work_type set icon_url='/image/fe76d31972a34040' WHERE id='778222e0';
UPDATE pmc_work_type set icon_url='/image/70b0780504f874c2' WHERE id='98121701';

INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('f6f63073d412', 'workType1.png', '/image/5c2274bfdefae884', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('24e8be1867b8', 'workType2.png', '/image/fe76d31972a34040', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('abeb5a0e194a', 'workType3.png', '/image/70b0780504f874c2', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('070287809b9a', 'workType4.png', '/image/99597455c4ad1412', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('ff54eafd3756', 'workType5.png', '/image/c41feef8be35d02e', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('7f63d59a4f3a', 'project1.png', '/image/9de30c3db540aaad', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('1566ca2f8ea6', 'project2.png', '/image/eee419bfc40067e2', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('8fe41d811db8', 'project3.png', '/image/0399813979cf69db', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('c35cb5b60b2d', 'project4.png', '/image/65a3b6bea9114921', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('696ba15d67d1', 'project5.png', '/image/75e1c4995ec1517c', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('8367702a321f', 'project6.png', '/image/ce755adf646c1d2e', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('9c0b0be9bcef', 'project7.png', '/image/cbbf55e8c139a875', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('184e7527162c', 'project8.png', '/image/21580b81196f7ba2', 'project');

update pcs_prc_role set default_role ='1' where id = '4559d54bc8b7' or id = '77f512ab7c53';

INSERT INTO pcs_flc_flow_relation (id, model_id, model_name, model_type, flow_id, bgroup) VALUES ('5ad56f59e2ae', '778222e0', '任务', 'workType', '4d040c6d', 'kanass');
INSERT INTO pcs_flc_flow_relation (id, model_id, model_name, model_type, flow_id, bgroup) VALUES ('5a142fd02abc', '7055ebc6', '需求', 'workType', '4d040c6d', 'kanass');
INSERT INTO pcs_flc_flow_relation (id, model_id, model_name, model_type, flow_id, bgroup) VALUES ('84a2f6124d31', '98121701', '缺陷', 'workType', '4d040c6d', 'kanass');

INSERT INTO pcs_flc_state_node_relation (id, work_name, work_id, node_id, state_node_id, project_id, flow_id) VALUES ('5474178a', '接受数据失败', 'apis-4', 'todo', 'a0f286c2', '4cf6d4dec7d0', '22751ea5');
INSERT INTO pcs_flc_state_node_relation (id, work_name, work_id, node_id, state_node_id, project_id, flow_id) VALUES ('b269cf6b', '接口开发', 'apis-3', 'todo', 'a0f286c2', '4cf6d4dec7d0', '22751ea5');
INSERT INTO pcs_flc_state_node_relation (id, work_name, work_id, node_id, state_node_id, project_id, flow_id) VALUES ('b78cab90', '接口设计', 'apis-1', 'todo', 'a0f286c2', '4cf6d4dec7d0', '22751ea5');
INSERT INTO pcs_flc_state_node_relation (id, work_name, work_id, node_id, state_node_id, project_id, flow_id) VALUES ('fedea0e8', '流水线启动失败', 'matflow-4', 'todo', 'eedd26da', 'f3fdc893ff25', '024227ab');
INSERT INTO pcs_flc_state_node_relation (id, work_name, work_id, node_id, state_node_id, project_id, flow_id) VALUES ('38642ad2', '开发流水线项目', 'matflow-3', 'todo', 'eedd26da', 'f3fdc893ff25', '024227ab');
INSERT INTO pcs_flc_state_node_relation (id, work_name, work_id, node_id, state_node_id, project_id, flow_id) VALUES ('7372e4a6', '流水线运行', 'matflow-1', 'todo', 'eedd26da', 'f3fdc893ff25', '024227ab');

INSERT INTO pcs_foc_form_relation (id, model_id, model_name, model_type, form_id, bgroup) VALUES ('96189ba4fb8c', '778222e0', '任务', 'workType', '515f17bd', 'kanass');
INSERT INTO pcs_foc_form_relation (id, model_id, model_name, model_type, form_id, bgroup) VALUES ('fe80bae0c210', '7055ebc6', '需求', 'workType', '0c5d4ff9', 'kanass');
INSERT INTO pcs_foc_form_relation (id, model_id, model_name, model_type, form_id, bgroup) VALUES ('ee3e9055c269', '98121701', '缺陷', 'workType', '607f6be6', 'kanass');