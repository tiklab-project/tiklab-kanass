INSERT INTO pcs_flc_flow_relation (id, model_id, model_name, model_type, flow_id, bgroup) VALUES ('5ad56f59e2ae', '778222e0', '任务', 'workType', '4d040c6d', 'kanass');
INSERT INTO pcs_flc_flow_relation (id, model_id, model_name, model_type, flow_id, bgroup) VALUES ('84a2f6124d31', '98121701', '缺陷', 'workType', '3d879830', 'kanass');
INSERT INTO pcs_flc_flow_relation (id, model_id, model_name, model_type, flow_id, bgroup) VALUES ('5a142fd02abc', '7055ebc6', '需求', 'workType', 'a96cf9c9', 'kanass');

INSERT INTO pcs_flc_state_node (id, name, status) VALUES ('start', '开始', 'START');
INSERT INTO pcs_flc_state_node (id, name, status) VALUES ('todo', '待办', 'TODO');
INSERT INTO pcs_flc_state_node (id, name, status) VALUES ('done', '完成', 'DONE');
INSERT INTO pcs_flc_state_node (id, name, status, scope) VALUES ('7db3a0d1', '进行中', 'PROGRESS', NULL);

INSERT INTO pcs_flc_flow (id, name, description, grouper, defaults, scope, form_id) VALUES ('a96cf9c9', '需求流程', '需求流程', 'custom', 0, 1, '0c5d4ff9');
INSERT INTO pcs_flc_flow (id, name, description, grouper, defaults, scope, form_id) VALUES ('3d879830', '缺陷流程', '缺陷流程', 'custom', 0, 1, '607f6be6');
INSERT INTO pcs_flc_flow (id, name, description, grouper, defaults, scope, form_id) VALUES ('4d040c6d', '任务流程', '任务流程', 'custom', 0, 1, '515f17bd');

INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('9fc4e881', 'start', 'a96cf9c9', 100, 390, 60, 60, 1, 'START');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('fffd4fe0', '7db3a0d1', 'a96cf9c9', 480, 400, 40, 160, 2, 'PROGRESS');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('0b7b444f', 'done', 'a96cf9c9', 740, 400, 40, 100, 2, 'DONE');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('b191e311', 'todo', 'a96cf9c9', 300, 400, 40, 100, 2, 'TODO');

INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('d940cce1', 'todo', '3d879830', 300, 400, 40, 100, 2, 'TODO');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('8bad1761', 'done', '3d879830', 800, 400, 40, 100, 2, 'DONE');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('9adfa577', 'start', '3d879830', 100, 390, 60, 60, 1, 'START');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('5fdc4fac', '7db3a0d1', '3d879830', 520, 400, 40, 180, 2, 'PROGRESS');

INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('d5cdecdd', 'done', '4d040c6d', 690, 300, 40, 100, 2, 'DONE');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('78258edf', 'start', '4d040c6d', 100, 290, 60, 60, 1, 'START');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('0e1eccfe', 'todo', '4d040c6d', 250, 300, 40, 100, 2, 'TODO');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('73cb56f4', '7db3a0d1', '4d040c6d', 430, 300, 40, 180, 2, 'PROGRESS');



INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('b69480fe', '创建', 'a96cf9c9', 'start', 'todo', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('3ca92376', '开始', 'a96cf9c9', 'todo', '7db3a0d1', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('9e101c66', '已完成', 'a96cf9c9', '7db3a0d1', 'done', 'right1', 'left1');

INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('775cc3f2', '开始', '3d879830', 'todo', '7db3a0d1', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('8298f937', '创建', '3d879830', 'start', 'todo', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('ac19690f', '完成', '3d879830', '7db3a0d1', 'done', 'right1', 'left1');

INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('1e76c11d', '创建', '4d040c6d', 'start', 'todo', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('36b0afc7', '开始', '4d040c6d', 'todo', '7db3a0d1', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('8a464239', '完成', '4d040c6d', '7db3a0d1', 'done', 'right1', 'left1');



