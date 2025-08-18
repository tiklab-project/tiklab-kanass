INSERT INTO pmc_project_type (id, type, name, icon_url, description) VALUES ('cd782a20', 'ipd', 'IPD项目', 'project5.png', 'IPD项目');

-- 产品计划表
CREATE TABLE pmc_product_plan (
                                  id VARCHAR(12) PRIMARY KEY,
                                  product_plan_name VARCHAR(64) NOT NULL,
                                  description TEXT,
                                  product_id VARCHAR(12) NOT NULL,
                                  master VARCHAR(12),
                                  product_plan_state_id VARCHAR(12),
                                  start_time TIMESTAMP NULL DEFAULT NULL,
                                  end_time TIMESTAMP NULL DEFAULT NULL,
                                  builder VARCHAR(12),
                                  rela_start_time TIMESTAMP NULL DEFAULT NULL,
                                  rela_end_time TIMESTAMP NULL DEFAULT NULL,
                                  color INT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 产品计划状态表
CREATE TABLE pmc_product_plan_status (
                                         id VARCHAR(32) PRIMARY KEY,
                                         name VARCHAR(64),
                                         description VARCHAR(64),
                                         sort INT,
                                         grouper VARCHAR(32)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 产品计划关注表
CREATE TABLE pmc_product_plan_focus (
                                        id VARCHAR(12) PRIMARY KEY,
                                        product_plan_id VARCHAR(12),
                                        master_id VARCHAR(12),
                                        product_id VARCHAR(12),
                                        sort INT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 工作项-产品计划关联表
CREATE TABLE pmc_work_product_plan (
                                       id VARCHAR(12) PRIMARY KEY,
                                       work_item_id VARCHAR(16) NOT NULL,
                                       product_plan_id VARCHAR(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO pmc_product_plan_status (id, name, description, sort, grouper) VALUES ('000000', '未开始', '未开始', 1, 'system');
INSERT INTO pmc_product_plan_status (id, name, description, sort, grouper) VALUES ('111111', '进行中', '进行中', 2, 'system');
INSERT INTO pmc_product_plan_status (id, name, description, sort, grouper) VALUES ('222222', '已完成', '已完成', 3, 'system');

-- 修改工作项表（添加 product_plan_id 列）
ALTER TABLE pmc_work_item
    ADD COLUMN product_plan_id VARCHAR(12) DEFAULT NULL COMMENT '产品计划id';