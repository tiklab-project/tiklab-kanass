/* 整理role */
update pcs_prc_role set name = '管理员' where id = '1' or parent_id = '1';
update pcs_prc_role set name = '项目管理员' where id = 'pro_111111' or parent_id = 'pro_111111';
update pcs_prc_role set name = '项目成员' where id = '4559d54bc8b7' or parent_id = '4559d54bc8b7';
update pcs_prc_role set grouper = 'system' where id = '77f512ab7c53' or parent_id = '77f512ab7c53';

/* 删除不需要的role */
DELETE FROM pcs_prc_dm_role
WHERE id IN (
    SELECT pdr.id
    FROM pcs_prc_dm_role pdr
             JOIN pcs_prc_role pr ON pdr.role_id = pr.id
    WHERE pr.parent_id = '2'
);
delete from pcs_prc_role where parent_id = '2' or id = '2';