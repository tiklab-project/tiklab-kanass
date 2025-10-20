
-- 把多余的_MESSAGETYPE_去掉,替换成_
update pcs_mec_message_type set id = replace(id,'_MESSAGETYPE_','_');
update pcs_mec_message_notice set id = replace(id,'_MESSAGETYPE_','_'), message_type_id=replace(message_type_id,'_MESSAGETYPE_','_');
update pcs_mec_message_dm_notice set message_notice_id = replace(message_notice_id,'_MESSAGETYPE_','_'), source_notice_id=replace(source_notice_id,'_MESSAGETYPE_','_');
update pcs_mec_message_notice_connect_orga set message_notice_id = replace(message_notice_id,'_MESSAGETYPE_','_');
update pcs_mec_message_notice_connect_role set message_notice_id = replace(message_notice_id,'_MESSAGETYPE_','_');
update pcs_mec_message_notice_connect_user set message_notice_id = replace(message_notice_id,'_MESSAGETYPE_','_');
update pcs_mec_message_notice_connect_usergroup set message_notice_id = replace(message_notice_id,'_MESSAGETYPE_','_');
update pcs_mec_message_notice_connect_vrole set message_notice_id = replace(message_notice_id,'_MESSAGETYPE_','_');
update pcs_mec_message_template set msg_type_id = replace(msg_type_id,'_MESSAGETYPE_','_');
update pcs_mec_message set message_type_id = replace(message_type_id,'_MESSAGETYPE_','_');
update pcs_mec_message_dispatch_item set message_type_id = replace(message_type_id,'_MESSAGETYPE_','_');

ALTER TABLE pcs_mec_message ALTER COLUMN message_type_id TYPE varchar(32);
ALTER TABLE pcs_mec_message_dispatch_item ALTER COLUMN message_type_id TYPE varchar(32);
ALTER TABLE pcs_mec_message_notice ALTER COLUMN id TYPE varchar(32);
ALTER TABLE pcs_mec_message_template ALTER COLUMN msg_type_id TYPE varchar(32);