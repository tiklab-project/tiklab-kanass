CREATE TABLE pmc_project_test_repository_plan (
                                                  id VARCHAR(12) NOT NULL PRIMARY KEY,
                                                  test_repository_id VARCHAR(12) NOT NULL,
                                                  project_id VARCHAR(12) NOT NULL,
                                                  plan_id VARCHAR(12) NOT NULL,
                                                  sort INT
);

ALTER TABLE pmc_project_test_repository_plan MODIFY COLUMN test_repository_id VARCHAR(12) COMMENT '测试用例库id';
ALTER TABLE pmc_project_test_repository_plan MODIFY COLUMN project_id VARCHAR(12) COMMENT '项目id';
ALTER TABLE pmc_project_test_repository_plan MODIFY COLUMN plan_id VARCHAR(12) COMMENT '测试计划id';