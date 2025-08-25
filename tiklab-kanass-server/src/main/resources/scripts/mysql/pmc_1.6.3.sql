-- 评审表中增加评审阶段
ALTER TABLE `pmc_appraised` ADD COLUMN `stage_id` VARCHAR(12) DEFAULT NULL COMMENT '阶段id';

ALTER TABLE `pmc_appraised` ADD COLUMN `create_time` timestamp COMMENT '创建时间';
ALTER TABLE `pmc_appraised` ADD COLUMN `update_time` timestamp COMMENT '更新时间';
UPDATE `pmc_appraised` SET `create_time` = `start_time` WHERE `create_time` IS NULL;
UPDATE `pmc_appraised` SET `update_time` = `start_time` WHERE `update_time` IS NULL;

-- 删除用例评审表
DROP TABLE IF EXISTS `pmc_testcase_appraised`;

-- 修改评审历史表名以及字段
ALTER TABLE `pmc_work_appraised_history` RENAME TO `pmc_appraised_item_history`;

ALTER TABLE `pmc_appraised_item_history` CHANGE `work_appraised_id` `appraised_item_id` VARCHAR(12) COMMENT '评审项id';

ALTER TABLE `pmc_appraised_item_history` CHANGE `work_item_appraised_state` `appraised_item_state` VARCHAR(12) COMMENT '评审项状态';

-- 事项评审表修改为评审项表
ALTER TABLE `pmc_work_appraised` RENAME TO `pmc_appraised_item`;

ALTER TABLE `pmc_appraised_item` CHANGE `work_item_id` `target_id` VARCHAR(12) COMMENT '评审对象id';

ALTER TABLE `pmc_appraised_item` CHANGE `work_item_appraised_state` `appraised_item_state` VARCHAR(12) COMMENT '评审项状态';

ALTER TABLE `pmc_appraised_item` ADD COLUMN `appraised_type_id` VARCHAR(12) DEFAULT '000000' COMMENT '评审类型id';

ALTER TABLE `pmc_appraised_item` ADD COLUMN `create_time` timestamp COMMENT '创建时间';
ALTER TABLE `pmc_appraised_item` ADD COLUMN `update_time` timestamp COMMENT '更新时间';

ALTER TABLE `pmc_appraised_item` DROP COLUMN IF EXISTS `advice`;