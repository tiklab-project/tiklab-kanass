/* 在项目列表下引入评审模块 */
CREATE TABLE pmc_appraised (
      id varchar(12) NOT NULL PRIMARY KEY,
      name varchar(64) NOT NULL,
      appraised_state varchar(32) NOT NULL,
      project_id varchar(12) NOT NULL,
      description text,
      builder varchar(12),
      master varchar(12),
      start_time timestamp(6) NOT NULL,
      end_time timestamp(6) NOT NULL,
      color int4
);

COMMENT ON COLUMN pmc_appraised.id IS '主键';
COMMENT ON COLUMN pmc_appraised.name IS '评审名称';
COMMENT ON COLUMN pmc_appraised.appraised_state IS '评审状态  0未开始 1进行中 2已完成';
COMMENT ON COLUMN pmc_appraised.project_id IS '关联的项目id';
COMMENT ON COLUMN pmc_appraised.description IS '描述';
COMMENT ON COLUMN pmc_appraised.builder IS '创建者';
COMMENT ON COLUMN pmc_appraised.master IS '负责人';
COMMENT ON COLUMN pmc_appraised.start_time IS '开始时间';
COMMENT ON COLUMN pmc_appraised.end_time IS '结束时间';
COMMENT ON COLUMN pmc_appraised.color IS '颜色';
COMMENT ON TABLE pmc_appraised IS '项目评审';


CREATE TABLE pmc_work_appraised (
                                    id varchar(12) NOT NULL PRIMARY KEY,
                                    work_item_id varchar(12) NOT NULL,
                                    appraised_id varchar(12) NOT NULL,
                                    work_item_appraised_state varchar(32) NOT NULL,
                                    advice text
);
COMMENT ON COLUMN pmc_work_appraised.work_item_id IS '事项id';
COMMENT ON COLUMN pmc_work_appraised.appraised_id IS '评审id';
COMMENT ON COLUMN pmc_work_appraised.work_item_appraised_state IS '评审状态 0未评审 1通过 2未通过 3建议';
COMMENT ON COLUMN pmc_work_appraised.advice IS '建议';