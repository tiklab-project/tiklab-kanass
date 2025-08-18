INSERT INTO pmc_project_type (id, type, name, icon_url, description) VALUES ('cd782a20', 'ipd', 'IPD项目', 'project5.png', 'IPD项目');

CREATE TABLE pmc_product_plan(
                                 id VARCHAR(12) PRIMARY KEY,
                                 product_plan_name VARCHAR(64) NOT NULL,
                                 description TEXT,
                                 product_id VARCHAR(12) NOT NULL,
                                 master VARCHAR(12),
                                 product_plan_state_id varchar (12),
                                 start_time timestamp,
                                 end_time  timestamp,
                                 builder VARCHAR(12),
                                 rela_start_time timestamp,
                                 rela_end_time timestamp,
                                 color INT
);

CREATE TABLE pmc_product_plan_status(
                                        id VARCHAR(32) PRIMARY KEY,
                                        name VARCHAR(64) ,
                                        description VARCHAR(64),
                                        sort integer,
                                        grouper VARCHAR(32)
);

CREATE TABLE pmc_product_plan_focus(
                                       id VARCHAR(12) PRIMARY KEY,
                                       product_plan_id VARCHAR(12),
                                       master_id VARCHAR(12),
                                       product_id VARCHAR(12),
                                       sort int
);

CREATE TABLE pmc_work_product_plan(
                                      id VARCHAR(12) PRIMARY KEY,
                                      work_item_id VARCHAR(16) NOT NULL,
                                      product_plan_id VARCHAR(12) NOT NULL
);

INSERT INTO pmc_product_plan_status (id, name, description, sort, grouper) VALUES ('000000', '未开始', '未开始', 1, 'system');
INSERT INTO pmc_product_plan_status (id, name, description, sort, grouper) VALUES ('111111', '进行中', '进行中', 2, 'system');
INSERT INTO pmc_product_plan_status (id, name, description, sort, grouper) VALUES ('222222', '已完成', '已完成', 3, 'system');

ALTER TABLE "pmc_work_item" ADD COLUMN product_plan_id VARCHAR(12) DEFAULT NULL;
COMMENT ON COLUMN pmc_work_item.product_plan_id IS '产品计划id';