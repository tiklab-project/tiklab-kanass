UPDATE pcs_mec_message_template SET msg_type_id = 'KANASS_MESSAGETYPE_SPRINTCREATE', msg_send_type_id = 'qywechat', title = NULL, content = E'添加迭代
\n><font color=comment>创建人:    </font> ${createUser.nickname}
\n><font color=comment>迭代:        </font>  [${sprintName}](${qywxurl})', link = '/${projectId}/sprintdetail/${sprintId}/workTable', bgroup = 'kanass', link_params = NULL WHERE id = '93a344ced67b';
