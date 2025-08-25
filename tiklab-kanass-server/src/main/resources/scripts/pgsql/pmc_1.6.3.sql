-- 评审表中增加评审阶段
ALTER TABLE "pmc_appraised" ADD COLUMN stage_id VARCHAR(12) DEFAULT null;
COMMENT ON COLUMN pmc_appraised.stage_id IS '阶段id';

ALTER TABLE "pmc_appraised" ADD COLUMN create_time timestamp;
COMMENT ON COLUMN pmc_appraised.create_time IS '创建时间';
ALTER TABLE "pmc_appraised" ADD COLUMN update_time timestamp;
COMMENT ON COLUMN pmc_appraised.update_time IS '更新时间';
update pmc_appraised set create_time = start_time where create_time is null;
update pmc_appraised set update_time = start_time where update_time is null;

-- -- 删除用例评审表
DROP TABLE IF EXISTS pmc_testcase_appraised;
--
-- -- 修改评审历史表名以及字段
ALTER TABLE "pmc_work_appraised_history" RENAME TO "pmc_appraised_item_history";

ALTER TABLE "pmc_appraised_item_history" RENAME COLUMN work_appraised_id TO appraised_item_id;
COMMENT ON COLUMN pmc_appraised_item_history.appraised_item_id IS '评审项id';

ALTER TABLE "pmc_appraised_item_history" RENAME COLUMN work_item_appraised_state TO appraised_item_state;
COMMENT ON COLUMN pmc_appraised_item_history.appraised_item_state IS '评审项状态';

-- 事项评审表修改为评审项表
ALTER TABLE "pmc_work_appraised" RENAME TO "pmc_appraised_item";

ALTER TABLE "pmc_appraised_item" RENAME COLUMN work_item_id TO target_id;
COMMENT ON COLUMN pmc_appraised_item.target_id IS '评审对象id';

ALTER TABLE "pmc_appraised_item" RENAME COLUMN work_item_appraised_state TO appraised_item_state;
COMMENT ON COLUMN pmc_appraised_item.appraised_item_state IS '评审项状态';

ALTER TABLE "pmc_appraised_item" ADD COLUMN appraised_type_id VARCHAR(12) DEFAULT '000000';
COMMENT ON COLUMN pmc_appraised_item.appraised_type_id IS '评审类型id';

ALTER TABLE "pmc_appraised_item" ADD COLUMN create_time timestamp;
COMMENT ON COLUMN pmc_appraised_item.create_time IS '创建时间';
ALTER TABLE "pmc_appraised_item" ADD COLUMN update_time timestamp;
COMMENT ON COLUMN pmc_appraised_item.update_time IS '更新时间';

ALTER TABLE pmc_appraised_item DROP COLUMN IF EXISTS advice;





