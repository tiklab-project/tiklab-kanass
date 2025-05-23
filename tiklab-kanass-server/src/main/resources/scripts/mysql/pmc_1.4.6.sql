
INSERT INTO pcs_mec_message_template
(id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params)
VALUES
    ('maildfdsdf', 'KANASS_MESSAGETYPE_SPRINTUPDATE', 'email', '更新迭代状态',
     '迭代状态更新 | <font color=\\"#409EFF\\">${sprintName}</font>    <br>\\n▫️ 操作人：${createUser.nickname}   <br>\\n▫️ 状态变更：<font color=\\"#909399\\">${oldValue}</font> ➔ <font color=\\"#67C23A\\">${newValue}</font>',
     '/${projectId}/sprintdetail/${sprintId}/workTable', 'kanass', NULL);

INSERT INTO pcs_mec_message_template
(id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params)
VALUES
    ('maildfsdggh', 'KANASS_MESSAGETYPE_SPRINTCREATE', 'email', '添加迭代',
     '添加迭代 | <font color=\\"#409EFF\\">${sprintName}</font>\\n▫️ 创建人：${createUser.nickname}',
     '/${projectId}/sprintdetail/${sprintId}/workTable', 'kanass', NULL);

INSERT INTO pcs_mec_message_template
(id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params)
VALUES
    ('emailworkitemstatus', 'KANASS_MESSAGETYPE_UPDATESTATUS', 'email', '事项状态变更',
     '更新事项 | <font color=\\"info\\">${workItemTitle}</font> 状态 <br>\\n▫️ 操作人：${createUser.nickname} <br>\\n▫️ 状态变更：<font color=\\"#909399\\">${oldValue}</font> ➔ <font color=\\"#67C23A\\">${newValue}</font>\\n',
     '/projectDetail/${projectId}/work/${workItemId}', 'kanass', NULL);