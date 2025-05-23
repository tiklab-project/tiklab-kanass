/* 在项目列表下引入评审模块 */
CREATE TABLE pmc_appraised (
                               id VARCHAR(12) NOT NULL PRIMARY KEY,
                               name VARCHAR(64) NOT NULL,
                               appraised_state VARCHAR(32) NOT NULL,
                               project_id VARCHAR(12) NOT NULL,
                               description TEXT,
                               builder VARCHAR(12),
                               master VARCHAR(12),
                               start_time TIMESTAMP NOT NULL,
                               end_time TIMESTAMP NOT NULL,
                               color INT
) COMMENT '项目评审';

ALTER TABLE pmc_appraised MODIFY COLUMN id VARCHAR(12) COMMENT '主键';
ALTER TABLE pmc_appraised MODIFY COLUMN name VARCHAR(64) COMMENT '评审名称';
ALTER TABLE pmc_appraised MODIFY COLUMN appraised_state VARCHAR(64) COMMENT '评审状态 0未开始 1进行中 2已完成';
ALTER TABLE pmc_appraised MODIFY COLUMN project_id VARCHAR(12) COMMENT '关联的项目id';
ALTER TABLE pmc_appraised MODIFY COLUMN description TEXT COMMENT '描述';
ALTER TABLE pmc_appraised MODIFY COLUMN builder VARCHAR(12) COMMENT '创建者';
ALTER TABLE pmc_appraised MODIFY COLUMN master VARCHAR(12) COMMENT '负责人';
ALTER TABLE pmc_appraised MODIFY COLUMN start_time TIMESTAMP COMMENT '开始时间';
ALTER TABLE pmc_appraised MODIFY COLUMN end_time TIMESTAMP COMMENT '结束时间';
ALTER TABLE pmc_appraised MODIFY COLUMN color INT COMMENT '颜色';

CREATE TABLE pmc_work_appraised (
                                    id VARCHAR(12) NOT NULL PRIMARY KEY,
                                    work_item_id VARCHAR(12) NOT NULL,
                                    appraised_id VARCHAR(12) NOT NULL,
                                    work_item_appraised_state VARCHAR(32) NOT NULL,
                                    advice TEXT
);

ALTER TABLE pmc_work_appraised MODIFY COLUMN work_item_id VARCHAR(12) COMMENT '事项id';
ALTER TABLE pmc_work_appraised MODIFY COLUMN appraised_id VARCHAR(12) COMMENT '评审id';
ALTER TABLE pmc_work_appraised MODIFY COLUMN work_item_appraised_state VARCHAR(32) COMMENT '评审状态 0未评审 1通过 2未通过 3建议';
ALTER TABLE pmc_work_appraised MODIFY COLUMN advice TEXT COMMENT '建议';