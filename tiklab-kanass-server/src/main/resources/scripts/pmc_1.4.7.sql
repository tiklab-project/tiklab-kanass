-- 删除 添加迭代的消息

DELETE FROM "pcs_mec_message_notice"
WHERE "message_type_id" = 'KANASS_MESSAGETYPE_SPRINTCREATE';

DELETE FROM "pcs_mec_message_type"
WHERE "id" = 'KANASS_MESSAGETYPE_SPRINTCREATE';

DELETE FROM "pcs_mec_message"
WHERE "message_type_id" = 'KANASS_MESSAGETYPE_SPRINTCREATE';

DELETE FROM "pcs_mec_message_dispatch_item"
WHERE "message_type_id" = 'KANASS_MESSAGETYPE_SPRINTCREATE';

DELETE FROM "pcs_mec_message_template"
WHERE "msg_type_id" = 'KANASS_MESSAGETYPE_SPRINTCREATE';

DELETE FROM "pcs_mec_message_dm_notice"
WHERE "source_notice_id" = 'KANASS_MESSAGE_SPRINTCREATE';


DELETE FROM "pcs_mec_message_type"
WHERE "id" = 'KANASS_MESSAGETYPE_JOINPROSET';


INSERT INTO "pcs_mec_message_notice" ("id", "message_type_id", "type", "bgroup", "message_send_type_id", "scope", "is_open") VALUES ('KANASS_MESSAGE_TASKTODO', 'KANASS_MESSAGETYPE_TASKTODO', 2, 'kanass', 'site', 1, 'true');

--INSERT INTO "pcs_mec_message_notice" ("id", "message_type_id", "type", "bgroup", "message_send_type_id", "scope", "is_open") VALUES ('KANASS_MESSAGE_JOINPROJECT', 'KANASS_MESSAGETYPE_JOINPROJECT', 2, 'kanass', 'site', 1, 'true');

-- 邮箱模版：分配事项
INSERT INTO "pcs_mec_message_template" ("id", "msg_type_id", "msg_send_type_id", "title", "content", "link", "bgroup", "link_params") VALUES ('mailTASKTODO', 'KANASS_MESSAGETYPE_TASKTODO', 'email', '分配事项', '✨ 给你分配了事项 - <font color="#409EFF">${workItemTitle}</font>', '/project/${projectId}/workitem/${workItemId}', 'kanass', NULL);
-- 邮箱模版：加入项目
--INSERT INTO "pcs_mec_message_template" ("id", "msg_type_id", "msg_send_type_id", "title", "content", "link", "bgroup", "link_params") VALUES ('mailJOINPROJECT', 'KANASS_MESSAGETYPE_JOINPROJECT', 'email', '加入项目', '✨ 你加入了项目 - <font color="#409EFF">${name}</font>', '/project/${projectId}/overview', 'kanass', NULL);


INSERT INTO "pcs_mec_message_notice" ("id", "message_type_id", "type", "bgroup", "message_send_type_id", "scope", "is_open") VALUES ('todo7741c720', 'KANASS_MESSAGETYPE_TASKTODO', 2, 'kanass', 'site', 2, 'true');
INSERT INTO "public"."pcs_mec_message_dm_notice" ("id", "message_notice_id", "domain_id", "source_notice_id", "is_open") VALUES ('demo287a14b0', 'todo7741c720', 'f3fdc893ff25', 'KANASS_MESSAGE_SPRINTUPDATE', 'true');

INSERT INTO "pcs_mec_message_notice" ("id", "message_type_id", "type", "bgroup", "message_send_type_id", "scope", "is_open") VALUES ('todo2544234', 'KANASS_MESSAGETYPE_TASKTODO', 2, 'kanass', 'site', 2, 'true');
INSERT INTO "public"."pcs_mec_message_dm_notice" ("id", "message_notice_id", "domain_id", "source_notice_id", "is_open") VALUES ('demo12345678', 'todo2544234', '4cf6d4dec7d0', 'KANASS_MESSAGE_SPRINTUPDATE', 'true');