/* 整理role */
UPDATE pcs_prc_role SET name = '管理员' WHERE id = '1' OR parent_id = '1';
UPDATE pcs_prc_role SET name = '项目管理员' WHERE id = 'pro_111111' OR parent_id = 'pro_111111';
UPDATE pcs_prc_role SET name = '项目成员' WHERE id = '4559d54bc8b7' OR parent_id = '4559d54bc8b7';
UPDATE pcs_prc_role SET grouper = 'system' WHERE id = '77f512ab7c53' OR parent_id = '77f512ab7c53';

/* 删除不需要的role */
DELETE FROM pcs_prc_dm_role
WHERE id IN (
    SELECT pdr.id
    FROM pcs_prc_dm_role pdr
             JOIN pcs_prc_role pr ON pdr.role_id = pr.id
    WHERE pr.parent_id = '2'
);

DELETE FROM pcs_prc_role
WHERE parent_id = '2' OR id = '2';