ALTER TABLE "pmc_project" ADD COLUMN product_id VARCHAR(12) DEFAULT NULL;
COMMENT ON COLUMN pmc_project.product_id IS '产品id';
CREATE TABLE pmc_product(
    id VARCHAR(12) PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    start_time timestamp NOT NULL,
    end_time timestamp NOT NULL,
    creator VARCHAR(12) NOT NULL,
    master VARCHAR(12) NOT NULL,
    product_limits VARCHAR(12) NOT NULL,
    remark VARCHAR(2048),
    sort int NOT NULL,
    creat_time timestamp,
    update_time timestamp,
    color int NOT NULL,
    status VARCHAR(8) DEFAULT '0' NOT NULL
);

COMMENT ON COLUMN pmc_product.id IS '主键';
COMMENT ON COLUMN pmc_product.name IS '产品名称';
COMMENT ON COLUMN pmc_product.start_time IS '开始时间';
COMMENT ON COLUMN pmc_product.end_time IS '结束时间';
COMMENT ON COLUMN pmc_product.creator IS '创建人';
COMMENT ON COLUMN pmc_product.master IS '负责人';
COMMENT ON COLUMN pmc_product.product_limits IS '产品可见范围  0公共 1私有';
COMMENT ON COLUMN pmc_product.remark IS '备注';
COMMENT ON COLUMN pmc_product.sort IS '排序';
COMMENT ON COLUMN pmc_product.creat_time IS '创建时间';
COMMENT ON COLUMN pmc_product.update_time IS '更新时间';
COMMENT ON COLUMN pmc_product.color IS '随机颜色';
COMMENT ON TABLE pmc_product IS '产品表';

CREATE TABLE pmc_product_focus(
      id VARCHAR(12) PRIMARY KEY,
      product_id VARCHAR(12),
      master_id VARCHAR(12),
      sort int
);

COMMENT ON COLUMN pmc_product_focus.id IS '主键';
COMMENT ON COLUMN pmc_product_focus.product_id IS '产品id';
COMMENT ON COLUMN pmc_product_focus.master_id IS '关注者';
COMMENT ON COLUMN pmc_product_focus.sort IS '排序';
COMMENT ON TABLE pmc_product_focus IS '产品关注表';

CREATE TABLE pmc_product_module(
      id VARCHAR(12) PRIMARY KEY,
      module_name VARCHAR(64) NOT NULL,
      description TEXT,
      parent_id VARCHAR(12),
      product_id VARCHAR(12) NOT NULL
);

COMMENT ON COLUMN pmc_product_module.id IS '主键';
COMMENT ON COLUMN pmc_product_module.module_name IS '模块名称';
COMMENT ON COLUMN pmc_product_module.description IS '描述';
COMMENT ON COLUMN pmc_product_module.parent_id IS '父id';
COMMENT ON COLUMN pmc_product_module.product_id IS '产品id';
COMMENT ON TABLE pmc_product_module IS '产品模块表';