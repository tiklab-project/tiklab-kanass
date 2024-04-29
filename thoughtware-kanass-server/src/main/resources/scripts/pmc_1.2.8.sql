UPDATE pmc_work_item SET code = id;

DELETE FROM pcs_mec_message_notice WHERE id = 'KANASS_MESSAGETYPE_SPRINTCREATE';
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGE_SPRINTCREATE', 'KANASS_MESSAGETYPE_SPRINTCREATE', 2, 'kanass', 'site', 1, 'true');

INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_SPRINTUPDATE', '更新迭代状态', '更新迭代状态', 'kanass');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGE_SPRINTUPDATE', 'KANASS_MESSAGETYPE_SPRINTUPDATE', 2, 'kanass', 'site', 1, 'true');
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('93a344ced67b', 'KANASS_MESSAGETYPE_SPRINTCREATE', 'qywechat', NULL, E'添加迭代
\n><font color=comment>创建人:    </font> ${createUser.nickname}
\n><font color=comment>迭代:        </font> ${sprintName}', '/${projectId}/sprintdetail/${sprintId}/workTable', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('10f2797a29bd', 'KANASS_MESSAGETYPE_SPRINTUPDATE', 'qywechat', NULL, E'更新迭代 <font color=info> [${sprintName}](${qywxurl})</font>状态
\n><font color=comment>操作人:</font>  ${createUser.nickname}
\n><font color=comment>状态:    </font>  ${oldValue} - ${newValue}', '/${projectId}/sprintdetail/${sprintId}/workTable', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('35c2fd81850c', 'KANASS_MESSAGETYPE_SPRINTUPDATE', 'site', '更新迭代状态', '更新迭代状态', '/${projectId}/sprintdetail/${sprintId}/workTable', 'kanass', NULL);

INSERT INTO pcs_prc_vrole (id, name, des, type) VALUES ('WORK_ITEM_AUDITOR', '事项审核员', NULL, 2);
INSERT INTO pcs_prc_vrole (id, name, des, type) VALUES ('WORK_ITEM_ASSIGNER', '事项负责人', NULL, 2);
INSERT INTO pcs_prc_vrole (id, name, des, type) VALUES ('PROJECT_ADMINISTRATORS', '项目管理员', NULL, 2);
INSERT INTO pcs_prc_vrole (id, name, des, type) VALUES ('SPRINT_MASTER', '迭代负责人', NULL, 2);

