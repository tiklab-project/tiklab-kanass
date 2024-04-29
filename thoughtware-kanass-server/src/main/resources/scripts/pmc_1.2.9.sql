
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('d0e68bbfc991', 'KANASS_MESSAGE_SPRINTCREATE', 'PROJECT_ADMINISTRATORS');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('f1facce1b028', 'KANASS_MESSAGE_SPRINTUPDATE', 'SPRINT_MASTER');

INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGE_UPDATESTATUS', 'KANASS_MESSAGETYPE_UPDATESTATUS', 2, 'kanass', 'site,qywechat', 1, 'true');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('ebf674a9be6b', 'KANASS_MESSAGE_UPDATESTATUS', 'WORK_ITEM_ASSIGNER');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('a5c1bbe0b89d', 'KANASS_MESSAGE_UPDATESTATUS', 'WORK_ITEM_AUDITOR');

UPDATE pcs_mec_message_notice  SET message_send_type_id = 'site,qywechat' WHERE id = 'KANASS_MESSAGE_SPRINTUPDATE';
UPDATE pcs_mec_message_notice  SET message_send_type_id = 'site,qywechat' WHERE id = 'KANASS_MESSAGE_SPRINTCREATE';


UPDATE pcs_mec_message_template SET msg_type_id = 'KANASS_MESSAGETYPE_SPRINTCREATE', msg_send_type_id = 'qywechat', title = NULL, content = E'添加迭代
\n><font color=comment>创建人:    </font> ${createUser.nickname}
\n><font color=comment>迭代:        </font>  [${sprintName}](${qywxurl})', link = '/${projectId}/sprintdetail/${sprintId}/workTable', bgroup = 'kanass', link_params = NULL WHERE id = '93a344ced67b';