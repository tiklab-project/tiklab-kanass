ALTER TABLE pmc_sprint ADD COLUMN rela_start_time timestamp;
ALTER TABLE pmc_sprint ADD COLUMN rela_end_time timestamp;

ALTER TABLE pmc_version ADD COLUMN rela_start_time timestamp;
ALTER TABLE pmc_version RENAME COLUMN publish_date TO publish_time;
ALTER TABLE pmc_version RENAME COLUMN rela_publish_date TO rela_publish_time;

INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('03af033bf8b9', '根据关联事项的状态限制', '4d040c6d', '36b0afc7', 'limitWorkStatus', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('5fda67c7b127', '根据关联事项的状态限制', '4d040c6d', '8a464239', 'limitWorkStatus', '111111', NULL, NULL);

INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('bb901d200ba3', '根据关联事项的状态限制', '024227ab', 'aa7bc2e6', 'limitWorkStatus', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('effc487ac9f7', '根据关联事项的状态限制', '024227ab', 'e15c8797', 'limitWorkStatus', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('a972d5c23da5', '根据关联事项的状态限制', '22751ea5', '19dc5e5e', 'limitWorkStatus', '111111', NULL, NULL);
INSERT INTO pcs_flc_transition_rule (id, name, flow_id, transition_id, rule_type, create_user_id, allocation_user_id, user_type) VALUES ('f4da4b2f211b', '根据关联事项的状态限制', '22751ea5', 'fef23ab8', 'limitWorkStatus', '111111', NULL, NULL);

INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('5e414bd0ea60', 'bb901d200ba3', 'limitWorkStatus', 'user', '{"childWorkStatus":"done"}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('3c246fd9d0cb', 'a972d5c23da5', 'limitWorkStatus', 'user', '{"preDependWorkStatus":"done"}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('b8a6b861333c', 'effc487ac9f7', 'limitWorkStatus', 'user', '{"preDependWorkStatus":"done"}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('c0bccf659830', 'f4da4b2f211b', 'limitWorkStatus', 'user', '{"childWorkStatus":"done"}', NULL);


INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('daca9fb880b1', '03af033bf8b9', 'limitWorkStatus', 'user', '{"preDependWorkStatus":"done"}', NULL);
INSERT INTO pcs_flc_transition_rule_config (id, rule_id, rule_type, config_type, config_value, determine) VALUES ('d5a88b8f9506', '5fda67c7b127', 'limitWorkStatus', 'user', '{"childWorkStatus":"done"}', NULL);

UPDATE pmc_work_priority sort = 1 WHERE id = "04b440ad";
UPDATE pmc_work_priority sort = 2 WHERE id = "faaecb3d";
UPDATE pmc_work_priority sort = 3 WHERE id = "56035266";