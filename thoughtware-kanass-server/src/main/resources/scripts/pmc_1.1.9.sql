ALTER TABLE pmc_sprint ADD COLUMN rela_start_time timestamp;
ALTER TABLE pmc_sprint ADD COLUMN rela_end_time timestamp;

ALTER TABLE pmc_version ADD COLUMN rela_start_time timestamp;
ALTER TABLE pmc_version RENAME COLUMN publish_date TO publish_time;
ALTER TABLE pmc_version RENAME COLUMN rela_publish_date TO rela_publish_time;

INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('03af033bf8b9', '根据关联事项的状态限制', '4d040c6d', '36b0afc7', 'limitWorkStatus', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('5fda67c7b127', '根据关联事项的状态限制', '4d040c6d', '8a464239', 'limitWorkStatus', '111111', NULL, NULL);

INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('daca9fb880b1', '03af033bf8b9', 'limitWorkStatus', 'user', '{"preDependWorkStatus":"done"}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('d5a88b8f9506', '5fda67c7b127', 'limitWorkStatus', 'user', '{"childWorkStatus":"done"}', NULL);