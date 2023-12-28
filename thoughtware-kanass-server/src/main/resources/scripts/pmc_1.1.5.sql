--消息
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope) VALUES ('KANASS_MESSAGETYPE_SPRINTCREATE', 'KANASS_MESSAGETYPE_SPRINTCREATE', 2, 'kanass', 'site', 1);
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope) VALUES ('85ec3cbfc67d', 'KANASS_MESSAGETYPE_SPRINTCREATE', 2, 'kanass', 'site', 2);
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id, scope) VALUES ('c884dfbdb8a7', 'KANASS_MESSAGETYPE_SPRINTCREATE', 2, 'kanass', 'site', 2);

INSERT INTO pcs_mec_message_dm_notice (id, message_notice_id, domain_id, source_notice_id) VALUES ('b18a0e707140', '85ec3cbfc67d', '4cf6d4dec7d0', 'KANASS_MESSAGETYPE_SPRINTCREATE');
INSERT INTO pcs_mec_message_dm_notice (id, message_notice_id, domain_id, source_notice_id) VALUES ('689a4b5f5e52', 'c884dfbdb8a7', 'f3fdc893ff25', 'KANASS_MESSAGETYPE_SPRINTCREATE');


INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('KANASS_MESSAGETEMPLATE_UPDATESTATUS', 'KANASS_MESSAGETYPE_UPDATESTATUS', 'site', '事项状态变更-站内信', '<div style="display: flex;  align-items: center;  font-size: 14px;  justify-content: space-between;"> <div style="display: flex;align-items: center;"> <div style="width: 25px;height: 25px; line-height: 25px; background-color: #ccc; border-radius: 32px; text-align: center; color: #fff;"> ${createUserIcon}</div> <div style="display: flex;  flex-direction: column;  padding: 0 20px;"> <div> <span style="padding-right: 10px;"> ${master.nickname} </span> 更新了 <span style="color: #5d70ea;font-size: 13px; font-weight: 500;"> ${workItemTitle} </span> 状态 </div> <div style="line-height: 40px; display: flex; align-items: center; height: 40px;"> <div style="padding: 5px 10px; background-color: #F4F5F7; font-size: 12px; line-height: 15px;border-radius: 5px; margin-right: 10px;"> ${oldValue. name} </div> ——— <div style="padding: 5px 10px; background-color: #F4F5F7; font-size: 12px; line-height: 15px; border-radius: 5px;margin-left: 10px;"> ${newValue. name} </div> </div> </div> </div> <div style="font-size: 13px;"> ${receiveTime} </div> </div>', '/index/work/workone/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('KANASS_MESSAGETEMPLATE_UPDATEASSIGNER', 'KANASS_MESSAGETYPE_TASKTODO', 'site', '任务待办-站内信', '<div style="display: flex; align-items: center; font-size: 14px; justify-content: space-between;"> <div style="display: flex;align-items: center;"> <div style="width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 25px;text-align: center; color: #fff;">${createUserIcon}</div> <div style="display: flex; flex-direction: column; padding: 0 10px; width: 250px"> <div> <span style="font-weight: 600;"> ${createUser.name} </span> 向您分配了事项 </div> <div style="line-height: 30px; display: flex; align-items: center; height: 30px;"> <img src="${workTypeIcon}" alt="" width="16px" height="16px"> <div style="color: #5d70ea; margin-left: 10px;text-overflow: ellipsis;white-space: nowrap;height: 30px;overflow: hidden;">${workItemTitle}</div> </div> </div> </div> <div style="font-size: 13px;"> ${receiveTime} </div> </div>', '/index/work/workone/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('KANASS_MESSAGETEMPLATE_SPRINTCREATE', 'KANASS_MESSAGETYPE_SPRINTCREATE', 'site', '添加迭代站内信', '添加迭代', NULL, 'kanass', NULL);

INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_UPDATESTATUS', '更改事项状态', '事项变更', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_TASKTODO', '给你分配了事项', '任务通知', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_SPRINTCREATE', '添加迭代', '添加迭代', 'kanass');

INSERT INTO pcs_todo_task_type (id, name, bgroup) VALUES ('KANASS_TODOTYPE_WORKITEMTODO', '给你分配了事项', 'kanass');

INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('KANASS_LOGTYPE_WORKITEMADD', '添加事项', 'kanass');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('KANASS_LOGTYPE_PROJECTADD', '添加了项目', 'kanass');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('KANASS_LOGTYPE_WORKUPDATEMASTER', '更新了事项负责人', 'kanass');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('KANASS_LOGTYPE_WORKUPDATESTATUS', '更新了事项状态', 'kanass');