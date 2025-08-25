-- 迭代新增创建时间、更新时间
update `pmc_sprint` set `builder` = '111111' where `builder` is null;
ALTER TABLE `pmc_sprint` ADD COLUMN `create_time` timestamp NULL COMMENT '创建时间';
ALTER TABLE `pmc_sprint` ADD COLUMN `update_time` timestamp NULL COMMENT '更新时间';

UPDATE `pmc_sprint` SET `create_time` = `start_time` WHERE `create_time` IS NULL;
UPDATE `pmc_sprint` SET `update_time` = `start_time` WHERE `update_time` IS NULL;

-- 版本新增创建时间、更新时间
ALTER TABLE `pmc_version` ADD COLUMN `create_time` timestamp NULL COMMENT '创建时间';
ALTER TABLE `pmc_version` ADD COLUMN `update_time` timestamp NULL COMMENT '更新时间';

UPDATE `pmc_version` SET `create_time` = `start_time` WHERE `create_time` IS NULL;
UPDATE `pmc_version` SET `update_time` = `start_time` WHERE `update_time` IS NULL;

-- 版本新增更新时间
ALTER TABLE `pmc_project` ADD COLUMN `update_time` timestamp NULL COMMENT '创建时间';

UPDATE `pmc_project` SET `update_time` = `create_time` WHERE `update_time` IS NULL;