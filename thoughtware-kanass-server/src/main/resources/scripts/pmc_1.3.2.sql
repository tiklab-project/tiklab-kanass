INSERT INTO pcs_mec_message_dm_notice (id, message_notice_id, domain_id, source_notice_id, is_open) VALUES ('c34e73e1f4ea', '78914538e0e1', '4cf6d4dec7d0', 'KANASS_MESSAGE_UPDATESTATUS', 'true');
INSERT INTO pcs_mec_message_dm_notice (id, message_notice_id, domain_id, source_notice_id, is_open) VALUES ('31d4efbd6387', 'c40917e34fe7', '4cf6d4dec7d0', 'KANASS_MESSAGE_SPRINTUPDATE', 'true');
INSERT INTO pcs_mec_message_dm_notice (id, message_notice_id, domain_id, source_notice_id, is_open) VALUES ('c66bc7a613da', '2aa75d9b7404', '4cf6d4dec7d0', 'KANASS_MESSAGE_SPRINTCREATE', 'true');
INSERT INTO pcs_mec_message_dm_notice (id, message_notice_id, domain_id, source_notice_id, is_open) VALUES ('b143c9d1b205', 'ec2e7d6f2a16', 'f3fdc893ff25', 'KANASS_MESSAGE_UPDATESTATUS', 'true');
INSERT INTO pcs_mec_message_dm_notice (id, message_notice_id, domain_id, source_notice_id, is_open) VALUES ('3cd2870a14b0', '0a7741cfc720', 'f3fdc893ff25', 'KANASS_MESSAGE_SPRINTUPDATE', 'true');
INSERT INTO pcs_mec_message_dm_notice (id, message_notice_id, domain_id, source_notice_id, is_open) VALUES ('7e3ecfde7ea5', '0b6ae5039901', 'f3fdc893ff25', 'KANASS_MESSAGE_SPRINTCREATE', 'true');

INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('85ec3cbfc67d', 'KANASS_MESSAGETYPE_SPRINTCREATE', 2, 'kanass', 'site', 2, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('c884dfbdb8a7', 'KANASS_MESSAGETYPE_SPRINTCREATE', 2, 'kanass', 'site', 2, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGE_UPDATESTATUS', 'KANASS_MESSAGETYPE_UPDATESTATUS', 2, 'kanass', 'site,qywechat', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGE_SPRINTUPDATE', 'KANASS_MESSAGETYPE_SPRINTUPDATE', 2, 'kanass', 'site,qywechat', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('KANASS_MESSAGE_SPRINTCREATE', 'KANASS_MESSAGETYPE_SPRINTCREATE', 2, 'kanass', 'site,qywechat', 1, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('78914538e0e1', 'KANASS_MESSAGETYPE_UPDATESTATUS', 2, 'kanass', 'site,qywechat', 2, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('c40917e34fe7', 'KANASS_MESSAGETYPE_SPRINTUPDATE', 2, 'kanass', 'site,qywechat', 2, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('2aa75d9b7404', 'KANASS_MESSAGETYPE_SPRINTCREATE', 2, 'kanass', 'site,qywechat', 2, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('ec2e7d6f2a16', 'KANASS_MESSAGETYPE_UPDATESTATUS', 2, 'kanass', 'site,qywechat', 2, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('0a7741cfc720', 'KANASS_MESSAGETYPE_SPRINTUPDATE', 2, 'kanass', 'site,qywechat', 2, 'true');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope, is_open) VALUES ('0b6ae5039901', 'KANASS_MESSAGETYPE_SPRINTCREATE', 2, 'kanass', 'site,qywechat', 2, 'true');

INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('d0e68bbfc991', 'KANASS_MESSAGE_SPRINTCREATE', 'PROJECT_ADMINISTRATORS');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('f1facce1b028', 'KANASS_MESSAGE_SPRINTUPDATE', 'SPRINT_MASTER');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('ebf674a9be6b', 'KANASS_MESSAGE_UPDATESTATUS', 'WORK_ITEM_ASSIGNER');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('a5c1bbe0b89d', 'KANASS_MESSAGE_UPDATESTATUS', 'WORK_ITEM_AUDITOR');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('2a2374d57557', '78914538e0e1', 'WORK_ITEM_ASSIGNER');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('de2ea5d3d8c2', '78914538e0e1', 'WORK_ITEM_AUDITOR');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('4fa2b293e3d9', 'c40917e34fe7', 'SPRINT_MASTER');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('34296aa57dfe', '2aa75d9b7404', 'PROJECT_ADMINISTRATORS');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('5585b3e16b9b', 'ec2e7d6f2a16', 'WORK_ITEM_ASSIGNER');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('359f22044db6', 'ec2e7d6f2a16', 'WORK_ITEM_AUDITOR');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('55b6958342d1', '0a7741cfc720', 'SPRINT_MASTER');
INSERT INTO pcs_mec_message_notice_connect_vrole (id, message_notice_id, vrole_id) VALUES ('777fab6513b9', '0b6ae5039901', 'PROJECT_ADMINISTRATORS');


INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('KANASS_MESSAGETEMPLATE_SPRINTCREATE', 'KANASS_MESSAGETYPE_SPRINTCREATE', 'site', '添加迭代站内信', '添加迭代', NULL, 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('360bc3434154', 'KANASS_MESSAGETYPE_JOINPROSET', 'qywechat', NULL, E'你成为项目集新成员
\n><font color=comment>项目集: </font> [${projectSetName}](${qywxurl})
\n><font color=comment>邀请人: </font>  ${createUser.nickname} ', '/projectSetdetail/${projectSetId}/projectSetProjectList', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('d5a6bb377044', 'KANASS_MESSAGETYPE_JOINPROJECT', 'qywechat', NULL, E'你成为项目新成员
\n><font color=comment>项目:     </font>  [${projectName}](${qywxurl})
\n><font color=comment>邀请人: </font>  ${createUser.nickname} ', '/projectDetail/${projectId}/workTable', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('508ace46c291', 'KANASS_MESSAGETYPE_UPDATESTATUS', 'qywechat', NULL, E'更新事项 <font color=info> [${workItemTitle}](${qywxurl})</font>状态
\n><font color=comment>操作人:</font>  ${createUser.nickname}
\n><font color=comment>状态:    </font>  ${oldValue} - ${newValue}', '/projectDetail/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('c8793d24e88d', 'KANASS_MESSAGETYPE_TASKTODO', 'qywechat', NULL, E'** ${createUser.nickname} ** 给你分配一个新的待办事项
\n><font color=comment>事项: </font><font color=info> [${workItemTitle}](${qywxurl})</font>
\n><font color=comment>类型: </font>  ${workType}', '/projectDetail/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('922ecff071f6', 'KANASS_MESSAGETYPE_JOINPROSET', 'site', '加入项目集', '加入项目集', '/projectSetdetail/${projectSetId}/projectSetProjectList', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('0ef0ea2297b1', 'KANASS_MESSAGETYPE_JOINPROJECT', 'site', '加入项目-站内信', '加入项目', '/projectDetail/${projectId}/workTable', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('KANASS_MESSAGETEMPLATE_UPDATEASSIGNER', 'KANASS_MESSAGETYPE_TASKTODO', 'site', '任务待办-站内信', '<div style=display: flex; align-items: center; font-size: 14px; justify-content: space-between;> <div style=display: flex;align-items: center;> <div style=width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 25px;text-align: center; color: #fff;>${createUserIcon}</div> <div style=display: flex; flex-direction: column; padding: 0 10px; width: 250px> <div> <span style=font-weight: 600;> ${createUser.name} </span> 向您分配了事项 </div> <div style=line-height: 30px; display: flex; align-items: center; height: 30px;> <img src=${workTypeIcon} alt= width=16px height=16px> <div style=color: #5d70ea; margin-left: 10px;text-overflow: ellipsis;white-space: nowrap;height: 30px;overflow: hidden;>${workItemTitle}</div> </div> </div> </div> <div style=font-size: 13px;> ${receiveTime} </div> </div>', '/projectDetail/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('KANASS_MESSAGETEMPLATE_UPDATESTATUS', 'KANASS_MESSAGETYPE_UPDATESTATUS', 'site', '事项状态变更-站内信', '<div style=display: flex;  align-items: center;  font-size: 14px;  justify-content: space-between;> <div style=display: flex;align-items: center;> <div style=width: 25px;height: 25px; line-height: 25px; background-color: #ccc; border-radius: 32px; text-align: center; color: #fff;> ${createUserIcon}</div> <div style=display: flex;  flex-direction: column;  padding: 0 20px;> <div> <span style=padding-right: 10px;> ${master.nickname} </span> 更新了 <span style=color: #5d70ea;font-size: 13px; font-weight: 500;> ${workItemTitle} </span> 状态 </div> <div style=line-height: 40px; display: flex; align-items: center; height: 40px;> <div style=padding: 5px 10px; background-color: #F4F5F7; font-size: 12px; line-height: 15px;border-radius: 5px; margin-right: 10px;> ${oldValue. name} </div> ——— <div style=padding: 5px 10px; background-color: #F4F5F7; font-size: 12px; line-height: 15px; border-radius: 5px;margin-left: 10px;> ${newValue. name} </div> </div> </div> </div> <div style=font-size: 13px;> ${receiveTime} </div> </div>', '/projectDetail/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('10f2797a29bd', 'KANASS_MESSAGETYPE_SPRINTUPDATE', 'qywechat', NULL, E'更新迭代 <font color=info> [${sprintName}](${qywxurl})</font>状态
\n><font color=comment>操作人:</font>  ${createUser.nickname}
\n><font color=comment>状态:    </font>  ${oldValue} - ${newValue}', '/${projectId}/sprintdetail/${sprintId}/workTable', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('35c2fd81850c', 'KANASS_MESSAGETYPE_SPRINTUPDATE', 'site', '更新迭代状态', '更新迭代状态', '/${projectId}/sprintdetail/${sprintId}/workTable', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('93a344ced67b', 'KANASS_MESSAGETYPE_SPRINTCREATE', 'qywechat', NULL, E'添加迭代
\n><font color=comment>创建人:    </font> ${createUser.nickname}
\n><font color=comment>迭代:        </font>  [${sprintName}](${qywxurl})', '/${projectId}/sprintdetail/${sprintId}/workTable', 'kanass', NULL);

INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_UPDATESTATUS', '更改事项状态', '事项变更', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_TASKTODO', '给你分配了事项', '任务通知', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_SPRINTCREATE', '添加迭代', '添加迭代', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_JOINPROJECT', '加入项目', '加入项目', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_JOINPROSET', '加入项目集', '加入项目集', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_SPRINTUPDATE', '更新迭代状态', '更新迭代状态', 'kanass');