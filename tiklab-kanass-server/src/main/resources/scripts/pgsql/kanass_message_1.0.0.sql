
ALTER TABLE pcs_mec_message ALTER COLUMN message_type_id TYPE varchar(64);
ALTER TABLE pcs_mec_message_dispatch_item ALTER COLUMN message_type_id TYPE varchar(64);
-- 消息类型
DELETE FROM pcs_mec_message_type where bgroup = 'kanass';
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_PROJECT_UPDATESTATUS', '项目状态变更', '项目状态变更', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_PROJECT_ADDUSER', '项目中添加成员', '项目中添加成员', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_PROJECT_DELETE', '删除项目', '删除项目', 'kanass');

INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_WORKITEM_CREATE', '创建事项', '创建事项', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_WORKITEM_UPDATE', '编辑事项', '编辑事项', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_WORKITEM_DELETE', '删除事项', '删除事项', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_WORKITEM_UPDATESTATUS', '事项状态流转', '事项状态流转', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_WORKITEM_COMMENT', '事项评论', '事项评论', 'kanass');

INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_APPRAISED_CREATE', '添加评审', '添加评审', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_APPRAISED_UPDATE', '编辑评审', '编辑评审', 'kanass');


-- 消息发送方式配置
ALTER TABLE pcs_mec_message_notice ALTER COLUMN id TYPE varchar(64);
DELETE FROM pcs_mec_message_notice where bgroup = 'kanass';
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGETYPE_PROJECT_UPDATESTATUS', 'KANASS_MESSAGETYPE_PROJECT_UPDATESTATUS', 2, 'kanass', 'site,qywechat,email', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGETYPE_PROJECT_ADDUSER', 'KANASS_MESSAGETYPE_PROJECT_ADDUSER', 2, 'kanass', 'site,qywechat,email', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGETYPE_PROJECT_DELETE', 'KANASS_MESSAGETYPE_PROJECT_DELETE', 2, 'kanass', 'site,qywechat,email', 1, 'true');

INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGETYPE_WORKITEM_CREATE', 'KANASS_MESSAGETYPE_WORKITEM_CREATE', 2, 'kanass', 'site,qywechat,email', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGETYPE_WORKITEM_UPDATE', 'KANASS_MESSAGETYPE_WORKITEM_UPDATE', 2, 'kanass', 'site,qywechat', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGETYPE_WORKITEM_DELETE', 'KANASS_MESSAGETYPE_WORKITEM_DELETE', 2, 'kanass', 'site,qywechat,email', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGETYPE_WORKITEM_UPDATESTATUS', 'KANASS_MESSAGETYPE_WORKITEM_UPDATESTATUS', 2, 'kanass', 'site,qywechat,email', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGETYPE_WORKITEM_COMMENT', 'KANASS_MESSAGETYPE_WORKITEM_COMMENT', 2, 'kanass', 'site', 1, 'true');

INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGETYPE_APPRAISED_CREATE', 'KANASS_MESSAGETYPE_APPRAISED_CREATE', 2, 'kanass', 'site,qywechat,email', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGETYPE_APPRAISED_UPDATE', 'KANASS_MESSAGETYPE_APPRAISED_UPDATE', 2, 'kanass', 'site', 1, 'true');

-- 删除项目内的消息通知，后面手动刷新数据
DELETE FROM pcs_mec_message_dm_notice;


-- 消息模版
ALTER TABLE pcs_mec_message_template ALTER COLUMN msg_type_id TYPE varchar(64);
DELETE FROM pcs_mec_message_template where bgroup = 'kanass';
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('62ac1bacdfb0', 'KANASS_MESSAGETYPE_WORKITEM_COMMENT', 'site', '事项评论--站内信', '${creater} 对事项 ${workItemTitle} 创建了评论 ${comment}', '/project/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('835a89906d4d', 'KANASS_MESSAGETYPE_WORKITEM_UPDATESTATUS', 'site', '事项状态流转--站内信', '${creater} 更改事项 ${workItemTitle} 状态， ${oldValue} --> ${newValue}', '/project/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('cc5d001ad374', 'KANASS_MESSAGETYPE_WORKITEM_UPDATESTATUS', 'qywechat', NULL, '${creater} 更改事项 ${workItemTitle} 状态， ${oldValue} --> ${newValue}', '/project/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('e83387d593ac', 'KANASS_MESSAGETYPE_WORKITEM_UPDATESTATUS', 'email', '事项状态流转--邮件', '${creater} 更改事项 ${workItemTitle} 状态， ${oldValue} --> ${newValue}', '/project/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('7d8bc0178880', 'KANASS_MESSAGETYPE_WORKITEM_COMMENT', 'email', '事项评论--邮件', '${creater} 对事项 ${workItemTitle} 创建了评论 ${comment}', '/project/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('1fd456475203', 'KANASS_MESSAGETYPE_WORKITEM_COMMENT', 'qywechat', NULL, '${creater} 对事项 ${workItemTitle} 创建了评论 ${comment}', '/project/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('07327b24b0b0', 'KANASS_MESSAGETYPE_WORKITEM_CREATE', 'site', '创建事项--站内信', '${creater} 创建事项 ${workItemTitle}', '/project/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('2ba807baccb3', 'KANASS_MESSAGETYPE_WORKITEM_CREATE', 'email', '创建事项--邮件', '${creater} 创建事项 ${workItemTitle}', '/project/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('022a3953b65d', 'KANASS_MESSAGETYPE_WORKITEM_CREATE', 'qywechat', NULL, '${creater} 创建事项 ${workItemTitle}', '/project/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('1c20b951d2a5', 'KANASS_MESSAGETYPE_WORKITEM_DELETE', 'site', '删除事项--站内信', '${creater} 删除事项 ${workItemTitle}', '/project/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('b88a6b5cd41b', 'KANASS_MESSAGETYPE_WORKITEM_DELETE', 'email', '删除事项--邮件', '${creater} 删除事项 ${workItemTitle}', '/project/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('054a62d154f2', 'KANASS_MESSAGETYPE_WORKITEM_DELETE', 'qywechat', NULL, '${creater} 删除事项 ${workItemTitle}', '/project/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('c23b4e572bb1', 'KANASS_MESSAGETYPE_PROJECT_DELETE', 'site', '删除项目--站内信', '${creater} 删除项目 ${projectName}', '/project', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('9fd72961e263', 'KANASS_MESSAGETYPE_PROJECT_DELETE', 'email', '删除项目--邮件', '${creater} 删除项目 ${projectName}', '/project', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('f41236adf21f', 'KANASS_MESSAGETYPE_PROJECT_DELETE', 'qywechat', NULL, '${creater} 删除项目 ${projectName}', '/project', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('b8c9f5782da1', 'KANASS_MESSAGETYPE_APPRAISED_CREATE', 'site', '添加评审--站内信', '${creater} 添加评审 ${appraisedName}', '/project/${projectId}/appraised/${appraisedId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('79b8c57fe5fe', 'KANASS_MESSAGETYPE_APPRAISED_CREATE', 'email', '添加评审--邮件', '${creater} 添加评审 ${appraisedName}', '/project/${projectId}/appraised/${appraisedId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('008be5cc4e0d', 'KANASS_MESSAGETYPE_APPRAISED_CREATE', 'qywechat', NULL, '${creater} 添加评审 ${appraisedName}', '/project/${projectId}/appraised/${appraisedId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('7348060d7ede', 'KANASS_MESSAGETYPE_WORKITEM_UPDATE', 'site', '编辑事项--站内信', '${creater} 编辑事项 ${workItemTitle}   ${fieldName}  信息', '/project/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('89918af74693', 'KANASS_MESSAGETYPE_WORKITEM_UPDATE', 'email', '编辑事项--邮件', '${creater} 编辑事项 ${workItemTitle}   ${fieldName}  信息', '/project/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('22843902205c', 'KANASS_MESSAGETYPE_WORKITEM_UPDATE', 'qywechat', NULL, '${creater} 编辑事项 ${workItemTitle}   ${fieldName}  信息', '/project/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('a094a70eb954', 'KANASS_MESSAGETYPE_APPRAISED_UPDATE', 'site', '编辑评审--站内信', '${creater} 编辑了评审 ${appraisedName}  ', '/project/${projectId}/appraised/${appraisedId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('d3c3cb85d582', 'KANASS_MESSAGETYPE_APPRAISED_UPDATE', 'email', '编辑评审--邮件', '${creater} 编辑了评审 ${appraisedName}', '/project/${projectId}/appraised/${appraisedId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('198dbd6fff21', 'KANASS_MESSAGETYPE_APPRAISED_UPDATE', 'qywechat', NULL, '${creater} 编辑了评审 ${appraisedName}', '/project/${projectId}/appraised/${appraisedId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('98dc0d0064aa', 'KANASS_MESSAGETYPE_PROJECT_ADDUSER', 'site', '项目添加成员--站内信', '${creater} 向项目 ${projectName} 中添加了成员 ${projectUser}', '/project/${projectId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('99f6acaf06c1', 'KANASS_MESSAGETYPE_PROJECT_ADDUSER', 'email', '项目添加成员--邮件', '${creater} 向项目 ${projectName} 中添加了成员 ${projectUser}', '/project/${projectId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('2bad6a3d8b46', 'KANASS_MESSAGETYPE_PROJECT_ADDUSER', 'qywechat', NULL, '${creater} 向项目 ${projectName} 中添加了成员 ${projectUser}', '/project/${projectId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('cdf138287144', 'KANASS_MESSAGETYPE_PROJECT_UPDATESTATUS', 'site', '项目状态变更--站内信', '${creater} 更改项目 ${projectName} 状态， ${oldValue} --> ${newValue}', '/project/${projectId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('ca1b720a3523', 'KANASS_MESSAGETYPE_PROJECT_UPDATESTATUS', 'email', '项目状态变更--邮件', '${creater} 更改项目 ${projectName} 状态， ${oldValue} --> ${newValue}', '/project/${projectId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('f4114de08306', 'KANASS_MESSAGETYPE_PROJECT_UPDATESTATUS', 'qywechat', NULL, '${creater} 更改项目 ${projectName} 状态， ${oldValue} --> ${newValue}', '/project/${projectId}', 'kanass', NULL);
