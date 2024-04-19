INSERT INTO pcs_mec_message_type(id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_JOINPROJECT', '加入项目', '加入项目', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_JOINPROSET', '加入项目集', '加入项目集', 'kanass');


INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('360bc3434154', 'KANASS_MESSAGETYPE_JOINPROSET', 'qywechat', NULL, '你成为项目集新成员
><font color=comment>项目集: </font> [${projectSetName}](${qywxurl})
><font color=comment>邀请人: </font>  ${createUser.nickname}
', '/projectSetdetail/${projectSetId}/projectSetProjectList', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('d5a6bb377044', 'KANASS_MESSAGETYPE_JOINPROJECT', 'qywechat', NULL, '你成为项目新成员
><font color=comment>项目:     </font>  [${projectName}](${qywxurl})
><font color=comment>邀请人: </font>  ${createUser.nickname}
', '/projectDetail/${projectId}/workTable', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('508ace46c291', 'KANASS_MESSAGETYPE_UPDATESTATUS', 'qywechat', NULL, '更新事项 <font color=info> [${workItemTitle}](${qywxurl})</font>状态
><font color=comment>操作人:</font>  ${createUser.nickname}
><font color=comment>状态:    </font>  ${oldValue} - ${newValue}', '/projectDetail/${projectId}/work/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('c8793d24e88d', 'KANASS_MESSAGETYPE_TASKTODO', 'qywechat', NULL, '** ${createUser.nickname} ** 给你分配一个新的待办事项
><font color=comment>事项: </font><font color=info> [${workItemTitle}](${qywxurl})</font>
><font color=comment>类型: </font>  ${workType}', '/projectDetail/${projectId}/work/${workItemId}', 'kanass', NULL);


INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('922ecff071f6', 'KANASS_MESSAGETYPE_JOINPROSET', 'site', '加入项目集', '加入项目集', '/projectSetdetail/${projectSetId}/projectSetProjectList', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('0ef0ea2297b1', 'KANASS_MESSAGETYPE_JOINPROJECT', 'site', '加入项目-站内信', '加入项目', '/projectDetail/${projectId}/workTable', 'kanass', NULL);

UPDATE pcs_mec_message_template SET msg_type_id = 'KANASS_MESSAGETYPE_TASKTODO', msg_send_type_id = 'site', title = '任务待办-站内信', content = '<div style=display: flex; align-items: center; font-size: 14px; justify-content: space-between;> <div style=display: flex;align-items: center;> <div style=width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 25px;text-align: center; color: #fff;>${createUserIcon}</div> <div style=display: flex; flex-direction: column; padding: 0 10px; width: 250px> <div> <span style=font-weight: 600;> ${createUser.name} </span> 向您分配了事项 </div> <div style=line-height: 30px; display: flex; align-items: center; height: 30px;> <img src=${workTypeIcon} alt= width=16px height=16px> <div style=color: #5d70ea; margin-left: 10px;text-overflow: ellipsis;white-space: nowrap;height: 30px;overflow: hidden;>${workItemTitle}</div> </div> </div> </div> <div style=font-size: 13px;> ${receiveTime} </div> </div>', link = '/projectDetail/${projectId}/work/${workItemId}', bgroup = 'kanass', link_params = NULL WHERE id = 'KANASS_MESSAGETEMPLATE_UPDATEASSIGNER';
UPDATE pcs_mec_message_template SET msg_type_id = 'KANASS_MESSAGETYPE_UPDATESTATUS', msg_send_type_id = 'site', title = '事项状态变更-站内信', content = '<div style=display: flex;  align-items: center;  font-size: 14px;  justify-content: space-between;> <div style=display: flex;align-items: center;> <div style=width: 25px;height: 25px; line-height: 25px; background-color: #ccc; border-radius: 32px; text-align: center; color: #fff;> ${createUserIcon}</div> <div style=display: flex;  flex-direction: column;  padding: 0 20px;> <div> <span style=padding-right: 10px;> ${master.nickname} </span> 更新了 <span style=color: #5d70ea;font-size: 13px; font-weight: 500;> ${workItemTitle} </span> 状态 </div> <div style=line-height: 40px; display: flex; align-items: center; height: 40px;> <div style=padding: 5px 10px; background-color: #F4F5F7; font-size: 12px; line-height: 15px;border-radius: 5px; margin-right: 10px;> ${oldValue. name} </div> ——— <div style=padding: 5px 10px; background-color: #F4F5F7; font-size: 12px; line-height: 15px; border-radius: 5px;margin-left: 10px;> ${newValue. name} </div> </div> </div> </div> <div style=font-size: 13px;> ${receiveTime} </div> </div>', link = '/projectDetail/${projectId}/work/${workItemId}', bgroup = 'kanass', link_params = NULL WHERE id = 'KANASS_MESSAGETEMPLATE_UPDATESTATUS';
