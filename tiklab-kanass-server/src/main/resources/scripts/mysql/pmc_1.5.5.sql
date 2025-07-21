CREATE TABLE pmc_work_appraised_history (
    id VARCHAR(12) NOT NULL PRIMARY KEY,
    work_appraised_id VARCHAR(12) NOT NULL,
    work_item_appraised_state VARCHAR(32) NOT NULL,
    creater VARCHAR(12) NOT NULL,
    advice TEXT NOT NULL,
    create_time timestamp NOT NULL,
    update_time timestamp NOT NULL
) COMMENT '评审记录表';

ALTER TABLE pmc_work_appraised MODIFY COLUMN work_appraised_id VARCHAR(12) COMMENT '事项评审关联id';
ALTER TABLE pmc_work_appraised MODIFY COLUMN work_item_appraised_state VARCHAR(12) COMMENT '评审状态 0未评审 1通过 2未通过';
ALTER TABLE pmc_work_appraised MODIFY COLUMN creater VARCHAR(12) COMMENT '评审记录用户id';
ALTER TABLE pmc_work_appraised MODIFY COLUMN advice VARCHAR(32) COMMENT '评审记录';
ALTER TABLE pmc_work_appraised MODIFY COLUMN create_time timestamp COMMENT '创建时间';
ALTER TABLE pmc_work_appraised MODIFY COLUMN update_time timestamp COMMENT '更新时间';

-- 将已有评审记录的插入到新表
-- INSERT INTO pmc_work_appraised_history
-- (id, work_appraised_id, work_item_appraised_state, creater, advice, create_time, update_time)
-- SELECT
--     LEFT(REPLACE(UUID(), '-', ''), 12),
--     pwa.id,
--     pwa.work_item_appraised_state,
--     pa.master,
--     pwa.advice,
--     NOW(),
--     NOW()
-- FROM pmc_work_appraised pwa
--     LEFT JOIN pmc_appraised pa ON pwa.appraised_id = pa.id
-- WHERE pwa.work_item_appraised_state IN ('1','2');

update pmc_work_appraised set work_item_appraised_state = '0', advice = '' where work_item_appraised_state = '3';