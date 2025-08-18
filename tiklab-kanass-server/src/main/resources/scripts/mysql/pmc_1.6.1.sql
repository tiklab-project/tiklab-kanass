-- 添加 create_time 列并设置注释
ALTER TABLE pmc_project
    ADD COLUMN create_time TIMESTAMP NULL COMMENT '创建时间';

-- 将 start_time 的值更新到 create_time（仅当 create_time 为 NULL 时）
UPDATE pmc_project
SET create_time = start_time
WHERE create_time IS NULL;

CREATE TABLE pmc_appraised_type(
                                   id VARCHAR(32) PRIMARY KEY,
                                   name VARCHAR(64),
                                   description VARCHAR(64),
                                   sort INT,
                                   grouper VARCHAR(32)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO pmc_appraised_type (id, name, description, sort, grouper) VALUES ('000000', '需求评审', '需求评审', 1, 'system');
INSERT INTO pmc_appraised_type (id, name, description, sort, grouper) VALUES ('111111', '用例评审', '用例评审', 2, 'system');
INSERT INTO pmc_appraised_type (id, name, description, sort, grouper) VALUES ('222222', '技术评审', '技术评审', 3, 'system');
INSERT INTO pmc_appraised_type (id, name, description, sort, grouper) VALUES ('333333', '决策评审', '决策评审', 4, 'system');

ALTER TABLE pmc_appraised ADD COLUMN appraised_type_id VARCHAR(12) DEFAULT NULL COMMENT '评审类型id';

update pmc_appraised set appraised_type_id = '000000' where appraised_type_id is null;

CREATE TABLE pmc_testcase_appraised (
    id VARCHAR(12) NOT NULL PRIMARY KEY,
    case_id VARCHAR(12) NOT NULL COMMENT '用例id',
    appraised_id VARCHAR(12) NOT NULL COMMENT '评审id',
    case_appraised_state VARCHAR(32) NOT NULL COMMENT '评审状态 0未评审 1通过 2未通过',
    advice TEXT COMMENT '建议'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建新的文档关系表
CREATE TABLE pmc_project_document (
                                      id VARCHAR(12) PRIMARY KEY,
                                      document_id VARCHAR(12) NOT NULL,
                                      repository_id VARCHAR(12) NOT NULL,
                                      sort INT,
                                      project_id VARCHAR(12)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目文档关联表';

-- 将事项关联的文档数据复制到项目文档表
INSERT INTO pmc_project_document (id, document_id, repository_id, sort, project_id)
SELECT
    id,
    document_id,
    repository_id,
    sort,
    project_id
FROM pmc_work_item_document;