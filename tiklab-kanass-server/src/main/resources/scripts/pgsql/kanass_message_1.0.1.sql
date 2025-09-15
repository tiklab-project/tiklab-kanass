-- 将项目级删除项目改为系统级
update pcs_mec_message_notice set type = 1 where id = 'KANASS_MESSAGETYPE_PROJECT_DELETE';
delete from pcs_mec_message_dm_notice where message_notice_id = 'KANASS_MESSAGETYPE_PROJECT_DELETE';

-- 新增项目、项目集、产品消息
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_PROJECT_CREATE', '新增项目', '新增项目', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_PROJECT_UPDATE', '修改项目', '修改项目', 'kanass');

INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_PROJECTSET_CREATE', '新增项目集', '新增项目集', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_PROJECTSET_UPDATE', '修改项目集', '修改项目集', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_PROJECTSET_DELETE', '删除项目集', '删除项目集', 'kanass');

INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_PRODUCT_CREATE', '新增产品', '新增产品', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_PRODUCT_UPDATE', '修改产品', '修改产品', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_PRODUCT_DELETE', '删除产品', '删除产品', 'kanass');

-- 新增消息发送方式
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGETYPE_PROJECT_CREATE', 'KANASS_MESSAGETYPE_PROJECT_CREATE', 1, 'kanass', 'site,qywechat,email', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGETYPE_PROJECT_UPDATE', 'KANASS_MESSAGETYPE_PROJECT_UPDATE', 1, 'kanass', 'site,qywechat,email', 1, 'true');

INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGETYPE_PROJECTSET_CREATE', 'KANASS_MESSAGETYPE_PROJECTSET_CREATE', 1, 'kanass', 'site,qywechat,email', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGETYPE_PROJECTSET_UPDATE', 'KANASS_MESSAGETYPE_PROJECTSET_UPDATE', 1, 'kanass', 'site,qywechat,email', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGETYPE_PROJECTSET_DELETE', 'KANASS_MESSAGETYPE_PROJECTSET_DELETE', 1, 'kanass', 'site,qywechat,email', 1, 'true');

INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGETYPE_PRODUCT_CREATE', 'KANASS_MESSAGETYPE_PRODUCT_CREATE', 1, 'kanass', 'site,qywechat,email', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGETYPE_PRODUCT_UPDATE', 'KANASS_MESSAGETYPE_PRODUCT_UPDATE', 1, 'kanass', 'site,qywechat,email', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGETYPE_PRODUCT_DELETE', 'KANASS_MESSAGETYPE_PRODUCT_DELETE', 1, 'kanass', 'site,qywechat,email', 1, 'true');

-- 配置消息模板
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('a3c1e0b9d2f4', 'KANASS_MESSAGETYPE_PROJECT_CREATE', 'site', '创建项目--站内信', '${creater} 创建了项目 ${projectName}', '/project/${projectId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('5b1f0a2e4c3d', 'KANASS_MESSAGETYPE_PROJECT_CREATE', 'email', '创建项目--邮件', '${creater} 创建了项目 ${projectName}', '/project/${projectId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('f2e1d0a5b4c6', 'KANASS_MESSAGETYPE_PROJECT_CREATE', 'qywechat', NULL, '${creater} 创建了项目 ${projectName}', '/project/${projectId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('0d1c2b3a4f5e', 'KANASS_MESSAGETYPE_PROJECT_UPDATE', 'site', '编辑项目--站内信', '${creater} 编辑了项目 ${projectName}', '/project/${projectId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('c4a1f3e2d0b5', 'KANASS_MESSAGETYPE_PROJECT_UPDATE', 'email', '编辑项目--邮件', '${creater} 编辑了项目 ${projectName}', '/project/${projectId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('e2a1f0b3c4d5', 'KANASS_MESSAGETYPE_PROJECT_UPDATE', 'qywechat', NULL, '${creater} 编辑了项目 ${projectName}', '/project/${projectId}', 'kanass', NULL);

INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('3d1f0e2a4b5c', 'KANASS_MESSAGETYPE_PROJECTSET_CREATE', 'site', '创建项目集--站内信', '${creater} 创建了项目集 ${projectSetName}', '/projectSet/${projectSetId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('f4b2a0c1e3d5', 'KANASS_MESSAGETYPE_PROJECTSET_CREATE', 'email', '创建项目集--邮件', '${creater} 创建了项目集 ${projectSetName}', '/projectSet/${projectSetId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('0a1b2c3d4e5f', 'KANASS_MESSAGETYPE_PROJECTSET_CREATE', 'qywechat', NULL, '${creater} 创建了项目集 ${projectSetName}', '/projectSet/${projectSetId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('c3e1f0a2b4d5', 'KANASS_MESSAGETYPE_PROJECTSET_UPDATE', 'site', '编辑项目集--站内信', '${creater} 编辑了项目集 ${projectSetName}', '/projectSet/${projectSetId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('5a0b1c2d3e4f', 'KANASS_MESSAGETYPE_PROJECTSET_UPDATE', 'email', '编辑项目集--邮件', '${creater} 编辑了项目集 ${projectSetName}', '/projectSet/${projectSetId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('d2f1a0e3b4c5', 'KANASS_MESSAGETYPE_PROJECTSET_UPDATE', 'qywechat', NULL, '${creater} 编辑了项目集 ${projectSetName}', '/projectSet/${projectSetId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('1e0f2a3b4c5d', 'KANASS_MESSAGETYPE_PROJECTSET_DELETE', 'site', '删除项目集--站内信', '${creater} 删除项目集 ${projectSetName}', '/projectSet', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('b3a0c1d2e4f5', 'KANASS_MESSAGETYPE_PROJECTSET_DELETE', 'email', '删除项目集--邮件', '${creater} 删除项目集 ${projectSetName}', '/projectSet', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('4f0a1b2c3d5e', 'KANASS_MESSAGETYPE_PROJECTSET_DELETE', 'qywechat', NULL, '${creater} 删除项目集 ${projectSetName}', '/projectSet', 'kanass', NULL);

INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('a0b1c2d3e4f5', 'KANASS_MESSAGETYPE_PRODUCT_CREATE', 'site', '创建产品--站内信', '${creater} 创建了产品 ${productName}', '/product/${productId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('2c1d0e3f4a5b', 'KANASS_MESSAGETYPE_PRODUCT_CREATE', 'email', '创建产品--邮件', '${creater} 创建了产品 ${productName}', '/product/${productId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('e5d4c3b2a1f0', 'KANASS_MESSAGETYPE_PRODUCT_CREATE', 'qywechat', NULL, '${creater} 创建了产品 ${productName}', '/product/${productId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('0f1e2d3c4b5a', 'KANASS_MESSAGETYPE_PRODUCT_UPDATE', 'site', '编辑产品--站内信', '${creater} 编辑了产品 ${productName}', '/product/${productId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('b5a4f3e2d1c0', 'KANASS_MESSAGETYPE_PRODUCT_UPDATE', 'email', '编辑产品--邮件', '${creater} 编辑了产品 ${productName}', '/product/${productId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('3a0b1f2e4d5c', 'KANASS_MESSAGETYPE_PRODUCT_UPDATE', 'qywechat', NULL, '${creater} 编辑了产品 ${productName}', '/product/${productId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('d1c0b5a4f3e2', 'KANASS_MESSAGETYPE_PRODUCT_DELETE', 'site', '删除产品--站内信', '${creater} 删除产品 ${productName}', '/product', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('6a1b2f3e4d5c', 'KANASS_MESSAGETYPE_PRODUCT_DELETE', 'email', '删除产品--邮件', '${creater} 删除产品 ${productName}', '/product', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('f0e1d2c3b4a5', 'KANASS_MESSAGETYPE_PRODUCT_DELETE', 'qywechat', NULL, '${creater} 删除产品 ${productName}', '/product', 'kanass', NULL);