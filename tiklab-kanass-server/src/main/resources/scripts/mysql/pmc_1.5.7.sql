ALTER TABLE pmc_project ADD COLUMN product_id VARCHAR(12) DEFAULT NULL COMMENT '产品id';
-- 产品表
CREATE TABLE pmc_product(
                            id VARCHAR(12) PRIMARY KEY COMMENT '主键',
                            name VARCHAR(64) NOT NULL COMMENT '产品名称',
                            start_time timestamp NOT NULL COMMENT '开始时间',
                            end_time timestamp NOT NULL COMMENT '结束时间',
                            creator VARCHAR(12) NOT NULL COMMENT '创建人',
                            master VARCHAR(12) NOT NULL COMMENT '负责人',
                            product_limits VARCHAR(12) NOT NULL COMMENT '产品可见范围  0公共 1私有',
                            remark VARCHAR(2048) COMMENT '备注',
                            sort int NOT NULL COMMENT '排序',
                            creat_time timestamp COMMENT '创建时间',
                            update_time timestamp COMMENT '更新时间',
                            color int NOT NULL COMMENT '随机颜色',
                            status varchar(8) NOT NULL DEFAULT '0' COMMENT '状态'
) COMMENT='产品表';

-- 产品关注表
CREATE TABLE pmc_product_focus(
                                  id VARCHAR(12) PRIMARY KEY COMMENT '主键',
                                  product_id VARCHAR(12) COMMENT '产品id',
                                  master_id VARCHAR(12) COMMENT '关注者',
                                  sort int COMMENT '排序'
) COMMENT='产品关注表';

-- 产品模块表
CREATE TABLE pmc_product_module(
                                   id VARCHAR(12) PRIMARY KEY COMMENT '主键',
                                   module_name VARCHAR(64) COMMENT '模块名称',
                                   description TEXT COMMENT '描述',
                                   parent_id VARCHAR(12) COMMENT '父id',
                                   product_id VARCHAR(12) COMMENT '产品id'
) COMMENT='产品模块表';