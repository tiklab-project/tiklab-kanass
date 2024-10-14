INSERT INTO pcs_flc_flow (id, name, description, grouper, defaults, scope, form_id) VALUES ('a96cf9c9', '需求流程', '需求流程', 'custom', 0, 1, '0c5d4ff9');
INSERT INTO pcs_flc_flow (id, name, description, grouper, defaults, scope, form_id) VALUES ('3d879830', '缺陷流程', '缺陷流程', 'custom', 0, 1, '607f6be6');
INSERT INTO pcs_flc_flow (id, name, description, grouper, defaults, scope, form_id) VALUES ('4d040c6d', '任务流程', '任务流程', 'custom', 0, 1, '515f17bd');

INSERT INTO pcs_flc_flow_relation (id, model_id, model_name, model_type, flow_id, bgroup) VALUES ('5ad56f59e2ae', '778222e0', '任务', 'workType', '4d040c6d', 'kanass');
INSERT INTO pcs_flc_flow_relation (id, model_id, model_name, model_type, flow_id, bgroup) VALUES ('84a2f6124d31', '98121701', '缺陷', 'workType', '3d879830', 'kanass');
INSERT INTO pcs_flc_flow_relation (id, model_id, model_name, model_type, flow_id, bgroup) VALUES ('5a142fd02abc', '7055ebc6', '需求', 'workType', 'a96cf9c9', 'kanass');

INSERT INTO pcs_flc_state_node (id, name, status, scope) VALUES ('start', '开始', 'START', NULL);
INSERT INTO pcs_flc_state_node (id, name, status, scope) VALUES ('todo', '代办', 'TODO', NULL);
INSERT INTO pcs_flc_state_node (id, name, status, scope) VALUES ('done', '完成', 'DONE', NULL);
INSERT INTO pcs_flc_state_node (id, name, status, scope) VALUES ('7db3a0d1', '进行中', 'PROGRESS', NULL);
INSERT INTO pcs_flc_state_node (id, name, status, scope) VALUES ('5b3ff31d', '已解决', 'PROGRESS', NULL);
INSERT INTO pcs_flc_state_node (id, name, status, scope) VALUES ('3b037eb2', '未通过', 'PROGRESS', NULL);
INSERT INTO pcs_flc_state_node (id, name, status, scope) VALUES ('5076fe7e', '审核', 'PROGRESS', 2);
INSERT INTO pcs_flc_state_node (id, name, status, scope) VALUES ('b5a4ffce', '已驳回', 'PROGRESS', 2);

INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('b191e311', 'todo', 'a96cf9c9', 300, 400, 40, 100, 2, 'TODO');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('0b7b444f', 'done', 'a96cf9c9', 1120, 400, 40, 100, 2, 'DONE');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('d74da6a5', '3b037eb2', 'a96cf9c9', 470, 530, 40, 180, 2, 'PROGRESS');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('6eea1339', '5b3ff31d', 'a96cf9c9', 800, 400, 40, 180, 2, 'PROGRESS');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('fffd4fe0', '7db3a0d1', 'a96cf9c9', 480, 400, 40, 160, 2, 'PROGRESS');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('9fc4e881', 'start', 'a96cf9c9', 100, 390, 60, 60, 1, 'START');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('0156f015', 'b5a4ffce', 'a96cf9c9', 260, 210, 40, 180, 2, 'PROGRESS');

INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('9adfa577', 'start', '3d879830', 100, 390, 60, 60, 1, 'START');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('b6d4e978', 'b5a4ffce', '3d879830', 260, 210, 40, 180, 2, 'PROGRESS');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('4a0d284f', '5b3ff31d', '3d879830', 830, 400, 40, 180, 2, 'PROGRESS');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('e3834850', '3b037eb2', '3d879830', 520, 520, 40, 180, 2, 'PROGRESS');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('8bad1761', 'done', '3d879830', 1170, 400, 40, 100, 2, 'DONE');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('d940cce1', 'todo', '3d879830', 300, 400, 40, 100, 2, 'TODO');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('5fdc4fac', '7db3a0d1', '3d879830', 520, 400, 40, 180, 2, 'PROGRESS');

INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('d5cdecdd', 'done', '4d040c6d', 1020, 300, 40, 100, 2, 'DONE');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('73cb56f4', '7db3a0d1', '4d040c6d', 460, 300, 40, 180, 2, 'PROGRESS');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('0e1eccfe', 'todo', '4d040c6d', 270, 300, 40, 100, 2, 'TODO');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('a170463e', '3b037eb2', '4d040c6d', 460, 450, 40, 180, 2, 'PROGRESS');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('ec0d1c03', '5b3ff31d', '4d040c6d', 740, 300, 40, 180, 2, 'PROGRESS');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('6f9e1175', 'b5a4ffce', '4d040c6d', 230, 110, 40, 180, 2, 'PROGRESS');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('78258edf', 'start', '4d040c6d', 100, 290, 60, 60, 1, 'START');


INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('0e53ba67', '重新开始', 'a96cf9c9', '3b037eb2', '7db3a0d1', 'top1', 'bottom1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('a740a20a', '驳回', 'a96cf9c9', 'todo', 'b5a4ffce', 'top1', 'bottom1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('3ca92376', '开始', 'a96cf9c9', 'todo', '7db3a0d1', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('b69480fe', '创建', 'a96cf9c9', 'start', 'todo', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('c8e809d2', '结束', 'a96cf9c9', 'b5a4ffce', 'done', 'right1', 'top1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('e1511ba5', '已解决', 'a96cf9c9', '7db3a0d1', '5b3ff31d', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('b663d06b', '完成', 'a96cf9c9', '5b3ff31d', 'done', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('1f615d78', '驳回', 'a96cf9c9', '5b3ff31d', '3b037eb2', 'bottom1', 'right1');

INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('c7dc729b', '驳回', '3d879830', 'todo', 'b5a4ffce', 'top1', 'bottom1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('775cc3f2', '开始', '3d879830', 'todo', '7db3a0d1', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('179df45b', '重新开始', '3d879830', '3b037eb2', '7db3a0d1', 'top1', 'bottom1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('4299eb2a', '已解决', '3d879830', '7db3a0d1', '5b3ff31d', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('f8b579a6', '完成', '3d879830', '5b3ff31d', 'done', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('4c4b740c', '驳回', '3d879830', '5b3ff31d', '3b037eb2', 'bottom1', 'right1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('8298f937', '创建', '3d879830', 'start', 'todo', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('96d5afe5', '结束', '3d879830', 'b5a4ffce', 'done', 'right1', 'top1');

INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('c6e57d8c', '已解决', '4d040c6d', '7db3a0d1', '5b3ff31d', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('8cdca0f0', '已完成', '4d040c6d', '5b3ff31d', 'done', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('4c76d0bc', '驳回', '4d040c6d', '5b3ff31d', '3b037eb2', 'bottom1', 'right1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('a1d2a0db', '重新开始', '4d040c6d', '3b037eb2', '7db3a0d1', 'top1', 'bottom1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('b7f6835a', '驳回', '4d040c6d', 'todo', 'b5a4ffce', 'top1', 'bottom1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('1e76c11d', '创建', '4d040c6d', 'start', 'todo', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('4aaaa30b', '结束', '4d040c6d', 'b5a4ffce', 'done', 'right1', 'top1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('36b0afc7', '开始', '4d040c6d', 'todo', '7db3a0d1', 'right1', 'left1');

INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('f68da26ce73d', '限制转换人员', '3d879830', 'c7dc729b', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('a01ebd0631c4', '限制转换人员', '3d879830', 'f8b579a6', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('0f486e42ccec', '限制转换人员', '3d879830', '4c4b740c', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('e8f2f11c971f', '限制转换人员', '3d879830', '179df45b', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('256a67f6c8cf', '限制转换人员', '3d879830', '4299eb2a', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('f41bdbd63991', '限制转换人员', '3d879830', '775cc3f2', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('b89240f228ed', '限制转换人员', '3d879830', '96d5afe5', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('a05da415b35b', '分配事项', '3d879830', 'c7dc729b', 'distributionWork', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('1b6bb79d1bc2', '分配事项', '4d040c6d', 'b7f6835a', 'distributionWork', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('e492bd6c1ad0', '限制转换人员', '4d040c6d', '4aaaa30b', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('21d363a92ba4', '限制转换人员', '4d040c6d', 'b7f6835a', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('da9e280a1226', '限制转换人员', '4d040c6d', '36b0afc7', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('bd2685d48a8c', '限制转换人员', '4d040c6d', 'c6e57d8c', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('6decf735a66c', '限制转换人员', '4d040c6d', '8cdca0f0', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('313605058dbe', '限制转换人员', '4d040c6d', '4c76d0bc', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('066fb9811b09', '限制转换人员', '4d040c6d', 'a1d2a0db', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('43c5bade5314', '限制转换人员', 'a96cf9c9', '1f615d78', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('17956d49e2a4', '限制转换人员', 'a96cf9c9', '0e53ba67', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('5615c50e1806', '限制转换人员', 'a96cf9c9', 'e1511ba5', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('d9a75477925d', '限制转换人员', 'a96cf9c9', 'c8e809d2', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('365505789b1c', '分配事项', 'a96cf9c9', 'a740a20a', 'distributionWork', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('04e7684bb614', '限制转换人员', 'a96cf9c9', 'a740a20a', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('d118b0d2f269', '限制转换人员', 'a96cf9c9', '3ca92376', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('8d1cf8f60aa2', '限制转换人员', 'a96cf9c9', 'b663d06b', 'limitUser', '111111', NULL, NULL);


INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('623b62d22870', '1b6bb79d1bc2', 'distributionWork', 'user', '{"roleIds":["WORK_ITEM_CREATOR"],"name":"事项创建人"}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('74b4c21b377b', 'e492bd6c1ad0', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('7d367f6ba5e9', '21d363a92ba4', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('bf05d88978e9', 'da9e280a1226', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('967845e241ab', 'bd2685d48a8c', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('43b0b4bcc1f0', '6decf735a66c', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_AUDITOR"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('2e767e75acc9', '313605058dbe', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_AUDITOR"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('fa8403e32704', 'a05da415b35b', 'distributionWork', 'user', '{"roleIds":["WORK_ITEM_CREATOR"],"name":"事项创建人"}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('e1af95b76996', 'f68da26ce73d', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('5147ca2002f3', 'a01ebd0631c4', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_AUDITOR"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('44074faf95a9', '0f486e42ccec', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_AUDITOR"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('cfc61a2129c9', '04e7684bb614', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('09aacafc1714', '365505789b1c', 'distributionWork', 'user', '{"roleIds":["WORK_ITEM_CREATOR"],"name":"事项创建人"}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('b93592196270', 'd9a75477925d', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('2478e8316865', '5615c50e1806', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('455dc7eed4ae', '8d1cf8f60aa2', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_AUDITOR"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('89aa06c71c01', '43c5bade5314', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_AUDITOR"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('fe5124cb39d8', '17956d49e2a4', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('1fbd4b9d8ffb', 'b89240f228ed', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('7e23f6df8b76', 'f41bdbd63991', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('09686ae40cf6', '256a67f6c8cf', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('63f3804d8415', 'e8f2f11c971f', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('9512a4ef3ef4', 'd118b0d2f269', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('6c7c4021980e', '066fb9811b09', 'limitUser', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);


