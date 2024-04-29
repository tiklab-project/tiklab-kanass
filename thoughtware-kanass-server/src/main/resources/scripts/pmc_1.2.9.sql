
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('d0e68bbfc991', 'KANASS_MESSAGETYPE_SPRINTCREATE', 'PROJECT_ADMINISTRATORS');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('f1facce1b028', 'KANASS_MESSAGE_SPRINTUPDATE', 'SPRINT_MASTER');

INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGE_UPDATESTATUS', 'KANASS_MESSAGETYPE_UPDATESTATUS', 2, 'kanass', 'site,qywechat', 1, 'true');

INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('ebf674a9be6b', 'KANASS_MESSAGE_UPDATESTATUS	', 'WORK_ITEM_ASSIGNER');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('a5c1bbe0b89d', 'KANASS_MESSAGE_UPDATESTATUS	', 'WORK_ITEM_AUDITOR');