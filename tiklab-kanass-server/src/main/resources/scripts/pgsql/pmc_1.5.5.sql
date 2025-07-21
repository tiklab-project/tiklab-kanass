CREATE TABLE pmc_work_appraised_history (
    id varchar(12) NOT NULL PRIMARY KEY,
    work_appraised_id varchar(12) NOT NULL ,
    work_item_appraised_state varchar(32) NOT NULL,
    creater varchar(12) NOT NULL,
    advice text NOT NULL,
    create_time timestamp(6) NOT NULL,
    update_time timestamp(6) NOT NULL
);

COMMENT ON COLUMN pmc_work_appraised_history.id IS '主键';
COMMENT ON COLUMN pmc_work_appraised_history.work_appraised_id IS '事项与评审关联id';
COMMENT ON COLUMN pmc_work_appraised_history.work_item_appraised_state IS '评审状态 0未评审 1通过 2未通过';
COMMENT ON COLUMN pmc_work_appraised_history.creater IS '评审记录用户id';
COMMENT ON COLUMN pmc_work_appraised_history.advice IS '评审建议';
COMMENT ON COLUMN pmc_work_appraised_history.create_time IS '创建时间';
COMMENT ON COLUMN pmc_work_appraised_history.update_time IS '修改时间';
COMMENT ON TABLE pmc_work_appraised_history IS '评审记录表';

-- 将已有评审记录的插入到新表
-- INSERT INTO pmc_work_appraised_history
-- (id, work_appraised_id, work_item_appraised_state, creater, advice, create_time, update_time)
-- SELECT
--     REPLACE(gen_random_uuid()::text, '-', '')::varchar(12),
--     pwa."id" AS "work_appraised_id",
--     pwa.work_item_appraised_state,
--     pa.master AS "creater",
--     pwa.advice,
--     CURRENT_TIMESTAMP,
--     CURRENT_TIMESTAMP
-- FROM
--     pmc_work_appraised pwa
--         LEFT JOIN pmc_appraised pa ON pwa.appraised_id = pa."id"
-- WHERE
--     pwa.work_item_appraised_state IN ('1', '2');

update pmc_work_appraised set work_item_appraised_state = '0', advice = '' where work_item_appraised_state = '3';
