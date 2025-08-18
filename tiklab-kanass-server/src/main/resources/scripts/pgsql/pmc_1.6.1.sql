-- 项目新增创建时间
ALTER TABLE "pmc_project" ADD COLUMN create_time timestamp;
COMMENT ON COLUMN pmc_project.create_time IS '创建时间';

update pmc_project set create_time = start_time where create_time is null;

-- 新建评审类型
CREATE TABLE pmc_appraised_type(
                                        id VARCHAR(32) PRIMARY KEY,
                                        name VARCHAR(64) ,
                                        description VARCHAR(64),
                                        sort integer,
                                        grouper VARCHAR(32)
);

INSERT INTO pmc_appraised_type (id, name, description, sort, grouper) VALUES ('000000', '需求评审', '需求评审', 1, 'system');
INSERT INTO pmc_appraised_type (id, name, description, sort, grouper) VALUES ('111111', '用例评审', '用例评审', 2, 'system');
INSERT INTO pmc_appraised_type (id, name, description, sort, grouper) VALUES ('222222', '技术评审', '技术评审', 3, 'system');
INSERT INTO pmc_appraised_type (id, name, description, sort, grouper) VALUES ('333333', '决策评审', '决策评审', 4, 'system');


ALTER TABLE "pmc_appraised" ADD COLUMN appraised_type_id VARCHAR(12) DEFAULT '000000';
COMMENT ON COLUMN pmc_appraised.appraised_type_id IS '评审类型id';

-- update pmc_appraised set appraised_type_id = '000000' where appraised_type_id is null;

CREATE TABLE pmc_testcase_appraised (
                                        id varchar(12) NOT NULL PRIMARY KEY,
                                        case_id varchar(12) NOT NULL,
                                        appraised_id varchar(12) NOT NULL,
                                        case_appraised_state varchar(32) NOT NULL,
                                        advice text
);
COMMENT ON COLUMN pmc_testcase_appraised.case_id IS '用例id';
COMMENT ON COLUMN pmc_testcase_appraised.appraised_id IS '评审id';
COMMENT ON COLUMN pmc_testcase_appraised.case_appraised_state IS '评审状态 0未评审 1通过 2未通过';
COMMENT ON COLUMN pmc_testcase_appraised.advice IS '建议';


-- 创建新的文档关系
CREATE TABLE pmc_project_document(
       id VARCHAR(12) PRIMARY KEY,
       document_id VARCHAR(12) NOT NULL,
       repository_id VARCHAR(12) NOT NULL,
       sort int,
       project_id VARCHAR(12)
);
    -- 事项关联的文档，复制一份到项目下
INSERT INTO pmc_project_document (id, document_id, repository_id, sort, project_id)
SELECT id, document_id, repository_id, sort, project_id
FROM pmc_work_item_document;
