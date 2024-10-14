

INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, is_edit_col, sort, no_edit) VALUES ('bbbf26c3', '所属版本', 'projectVersion', '2acdb67b', 'system', 0, NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, is_edit_col, sort, no_edit) VALUES ('c5710576', '审核人', 'reporter', '2acdb67b', 'system', 0, NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, is_edit_col, sort, no_edit) VALUES ('d72ab706', '创建人', 'builder', '2acdb67b', 'system', 0, NULL, 1);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, is_edit_col, sort, no_edit) VALUES ('da0fbb82', '剩余用时', 'surplusTime', '2acdb67b', 'system', 0, NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, is_edit_col, sort, no_edit) VALUES ('765e3fce', '上级事项', 'parentWorkItem', '2acdb67b', 'system', 0, NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, is_edit_col, sort, no_edit) VALUES ('c5e9a38a', '所属计划', 'stage', '2acdb67b', 'system', 0, NULL, 0);

INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, is_edit_col, sort, no_edit) VALUES ('30026c42', '状态', 'status', '2acdb67b', 'system', 0, NULL, 1);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, is_edit_col, sort, no_edit) VALUES ('18f35f29', '进度', 'percent', 'ddb4490b', 'system', 0, NULL, 0);



UPDATE pcs_flc_flow SET form_id = '0c5d4ff9' WHERE id = 'a96cf9c9';
UPDATE pcs_flc_flow SET form_id = '607f6be6' WHERE id = '3d879830';
UPDATE pcs_flc_flow SET form_id = '515f17bd' WHERE id = '4d040c6d';

UPDATE pcs_foc_field SET code = 'preDependWorkItem' WHERE id = '3b3ac1bf';

DELETE FROM pcs_foc_form_field WHERE field_id in ('4b18b18a', 'e8787ab9', 'ca554bdd');
INSERT INTO pcs_foc_form_field (id, form_id, field_id, required, sort) VALUES ('ea09f08f', '607f6be6', '0763e387', 0, 25);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, required, sort) VALUES ('ed305ca0', '515f17bd', 'a34d7fc3', 0, 25);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, required, sort) VALUES ('37879bb0', '0c5d4ff9', 'c4579b11', 0, 27);
DELETE FROM pcs_foc_form_field WHERE id = 'b4b83345';
DELETE FROM pcs_foc_form_field WHERE id = '22afc333';


UPDATE pcs_flc_flow SET name = '任务流程' WHERE name = '事项流程';
UPDATE pcs_flc_flow SET description = '任务流程' WHERE description = '事项流程';

INSERT INTO pcs_flc_flow (id, name, description, grouper, defaults, scope) VALUES ('3d879830', '缺陷流程', '缺陷流程', 'custom', 0, 1);
INSERT INTO pcs_flc_flow (id, name, description, grouper, defaults, scope) VALUES ('a96cf9c9', '需求流程', '需求流程', 'custom', 0, 1);

INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('d940cce1', 'todo', '3d879830', 300, 400, 40, 100, 2, 'TODO');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('8bad1761', 'done', '3d879830', 800, 400, 40, 100, 2, 'DONE');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('9adfa577', 'start', '3d879830', 100, 390, 60, 60, 1, 'START');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('5fdc4fac', '7db3a0d1', '3d879830', 520, 400, 40, 180, 2, 'PROGRESS');

INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('775cc3f2', '开始', '3d879830', 'todo', '7db3a0d1', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('8298f937', '创建', '3d879830', 'start', 'todo', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('ac19690f', '完成', '3d879830', '7db3a0d1', 'done', 'right1', 'left1');


INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('b69480fe', '创建', 'a96cf9c9', 'start', 'todo', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('3ca92376', '开始', 'a96cf9c9', 'todo', '7db3a0d1', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('9e101c66', '已完成', 'a96cf9c9', '7db3a0d1', 'done', 'right1', 'left1');

INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('9fc4e881', 'start', 'a96cf9c9', 100, 390, 60, 60, 1, 'START');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('fffd4fe0', '7db3a0d1', 'a96cf9c9', 480, 400, 40, 160, 2, 'PROGRESS');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('0b7b444f', 'done', 'a96cf9c9', 740, 400, 40, 100, 2, 'DONE');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('b191e311', 'todo', 'a96cf9c9', 300, 400, 40, 100, 2, 'TODO');
