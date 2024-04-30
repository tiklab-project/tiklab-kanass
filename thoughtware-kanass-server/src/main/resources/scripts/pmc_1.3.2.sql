INSERT INTO pcs_mec_message_dm_notice (id, message_notice_id, domain_id, source_notice_id, is_open) VALUES ('c34e73e1f4ea', '78914538e0e1', '4cf6d4dec7d0', 'KANASS_MESSAGE_UPDATESTATUS', 'true');
INSERT INTO pcs_mec_message_dm_notice (id, message_notice_id, domain_id, source_notice_id, is_open) VALUES ('31d4efbd6387', 'c40917e34fe7', '4cf6d4dec7d0', 'KANASS_MESSAGE_SPRINTUPDATE', 'true');
INSERT INTO pcs_mec_message_dm_notice (id, message_notice_id, domain_id, source_notice_id, is_open) VALUES ('c66bc7a613da', '2aa75d9b7404', '4cf6d4dec7d0', 'KANASS_MESSAGE_SPRINTCREATE', 'true');
INSERT INTO pcs_mec_message_dm_notice (id, message_notice_id, domain_id, source_notice_id, is_open) VALUES ('b143c9d1b205', 'ec2e7d6f2a16', 'f3fdc893ff25', 'KANASS_MESSAGE_UPDATESTATUS', 'true');
INSERT INTO pcs_mec_message_dm_notice (id, message_notice_id, domain_id, source_notice_id, is_open) VALUES ('3cd2870a14b0', '0a7741cfc720', 'f3fdc893ff25', 'KANASS_MESSAGE_SPRINTUPDATE', 'true');
INSERT INTO pcs_mec_message_dm_notice (id, message_notice_id, domain_id, source_notice_id, is_open) VALUES ('7e3ecfde7ea5', '0b6ae5039901', 'f3fdc893ff25', 'KANASS_MESSAGE_SPRINTCREATE', 'true');


INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('78914538e0e1', 'KANASS_MESSAGETYPE_UPDATESTATUS', 2, 'kanass', 'site,qywechat', 2, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('c40917e34fe7', 'KANASS_MESSAGETYPE_SPRINTUPDATE', 2, 'kanass', 'site,qywechat', 2, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('2aa75d9b7404', 'KANASS_MESSAGETYPE_SPRINTCREATE', 2, 'kanass', 'site,qywechat', 2, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('ec2e7d6f2a16', 'KANASS_MESSAGETYPE_UPDATESTATUS', 2, 'kanass', 'site,qywechat', 2, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('0a7741cfc720', 'KANASS_MESSAGETYPE_SPRINTUPDATE', 2, 'kanass', 'site,qywechat', 2, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('0b6ae5039901', 'KANASS_MESSAGETYPE_SPRINTCREATE', 2, 'kanass', 'site,qywechat', 2, 'true');

INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('2a2374d57557', '78914538e0e1', 'WORK_ITEM_ASSIGNER');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('de2ea5d3d8c2', '78914538e0e1', 'WORK_ITEM_AUDITOR');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('4fa2b293e3d9', 'c40917e34fe7', 'SPRINT_MASTER');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('34296aa57dfe', '2aa75d9b7404', 'PROJECT_ADMINISTRATORS');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('5585b3e16b9b', 'ec2e7d6f2a16', 'WORK_ITEM_ASSIGNER');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('359f22044db6', 'ec2e7d6f2a16', 'WORK_ITEM_AUDITOR');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('55b6958342d1', '0a7741cfc720', 'SPRINT_MASTER');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('777fab6513b9', '0b6ae5039901', 'PROJECT_ADMINISTRATORS');