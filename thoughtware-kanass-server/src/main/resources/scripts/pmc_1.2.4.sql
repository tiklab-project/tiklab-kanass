INSERT INTO pcs_mec_message_type(id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_JOINPROJECT', '加入项目', '加入项目', 'kanass');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('KANASS_MESSAGETYPE_JOINPROSET', '加入项目集', '加入项目集', 'kanass');


INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('51c487985a82', 'KANASS_MESSAGETYPE_JOINPROJECT', 'site', '成为项目新成员', '你成为项目新成员
><font color=comment>项目:     </font>  ${projectName}
><font color=comment>邀请人: </font>  ${createUser.nickname}
', '/projectDetail/${projectId}/workTable', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('d5a6bb377044', 'KANASS_MESSAGETYPE_JOINPROJECT', 'qywechat', NULL, '你成为项目新成员
><font color=comment>项目:     </font>  ${projectName}
><font color=comment>邀请人: </font>  ${createUser.nickname}
', '/projectDetail/${projectId}/workTable', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('360bc3434154', 'KANASS_MESSAGETYPE_JOINPROSET', 'qywechat', NULL, '你成为项目集新成员
><font color=comment>项目集: </font> ${projectSetName}
><font color=comment>邀请人: </font>  ${createUser.nickname}
', '/projectSetdetail/${projectSetId}/projectSetProjectList', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('c78eb1817307', 'KANASS_MESSAGETYPE_JOINPROSET', 'site', '加入项目集', '加入项目集', NULL, 'kanass', NULL);

INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('508ace46c291', 'KANASS_MESSAGETYPE_UPDATESTATUS', 'qywechat', NULL, '更新事项 <font color=info> ${workItemTitle}</font>状态
><font color=comment>操作人:</font>  ${createUser.nickname}
><font color=comment>状态:    </font>  ${oldValue} - ${newValue}', '/index/work/workone/${workItemId}', 'kanass', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('c8793d24e88d', 'KANASS_MESSAGETYPE_TASKTODO', 'qywechat', NULL, '** ${createUser.nickname} ** 给你分配一个新的待办事项
><font color=comment>事项: </font><font color=info> ${workItemTitle}</font>
><font color=comment>类型: </font>  ${workType}', '/index/work/workone/${workItemId}', 'kanass', NULL);