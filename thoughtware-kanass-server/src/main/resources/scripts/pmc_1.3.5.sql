DELETE FROM pcs_flc_flow;
DELETE FROM pcs_flc_dm_flow;
DELETE FROM pcs_flc_state_node;
DELETE FROM pcs_flc_state_node_flow;
DELETE FROM pcs_flc_state_node_role_field;
DELETE FROM pcs_flc_flow_relation;

DELETE FROM pcs_flc_transition;
DELETE FROM pcs_flc_transition_rule;
DELETE FROM pcs_flc_transition_rule_config;
DELETE FROM pcs_flc_business_role;

DELETE FROM pcs_foc_dm_form;
DELETE FROM pcs_foc_form;
DELETE FROM pcs_foc_form_field;
DELETE FROM pcs_foc_field;
DELETE FROM pcs_foc_form_relation;

INSERT INTO pcs_flc_flow_relation (id, model_id, model_name, model_type, flow_id, bgroup) VALUES ('5ad56f59e2ae', '778222e0', '任务', 'workType', '4d040c6d', 'kanass');
INSERT INTO pcs_flc_flow_relation (id, model_id, model_name, model_type, flow_id, bgroup) VALUES ('84a2f6124d31', '98121701', '缺陷', 'workType', '3d879830', 'kanass');
INSERT INTO pcs_flc_flow_relation (id, model_id, model_name, model_type, flow_id, bgroup) VALUES ('5a142fd02abc', '7055ebc6', '需求', 'workType', 'a96cf9c9', 'kanass');