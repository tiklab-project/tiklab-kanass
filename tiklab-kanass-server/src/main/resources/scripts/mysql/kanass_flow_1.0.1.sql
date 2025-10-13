-- 新增两个内置节点
INSERT INTO pcs_flc_state_node (id, name, status, scope) VALUES ('ef5c1ab5', '已撤回', 'DONE', NULL);
INSERT INTO pcs_flc_state_node (id, name, status, scope) VALUES ('8ca4bc2d', '重新打开', 'PROGRESS', NULL);

-- 添加到全局缺陷流程中 3d879830
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('f59baa8b', '8ca4bc2d', '3d879830', 740, 270, 40, 180, 2, 'PROGRESS');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, x, y, height, width, type, node_status) VALUES ('426566df', 'ef5c1ab5', '3d879830', 740, 550, 40, 180, 2, 'DONE');

-- 给节点之间加上链接线
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('768044e9', '完成', '3d879830', 'ef5c1ab5', 'done', 'right1', 'bottom1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('eda6a343', '重新打开', '3d879830', 'done', '8ca4bc2d', 'top1', 'right1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('882ddd55', '撤回', '3d879830', '7db3a0d1', 'ef5c1ab5', 'bottom1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('f1b16185', '开始', '3d879830', '8ca4bc2d', '7db3a0d1', 'left1', 'top1');

-- 给每条链接线配置规则
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('ee45b745b4af', '限制转换人员', '3d879830', '768044e9', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('14b67455e701', '限制转换人员', '3d879830', 'eda6a343', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('5aa4d25f5be2', '限制转换人员', '3d879830', '882ddd55', 'limitUser', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('fab000057ff8', '限制转换人员', '3d879830', 'f1b16185', 'limitUser', '111111', NULL, NULL);

INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('9ba5afab7698', 'ee45b745b4af', 'distributionWork', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('abd9ab091828', '14b67455e701', 'distributionWork', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('5caffee07fc9', '5aa4d25f5be2', 'distributionWork', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('92a246fbfa81', 'fab000057ff8', 'distributionWork', 'user', '{"roleIds":["WORK_ITEM_ASSIGNER"]}', NULL);