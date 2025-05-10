CREATE TABLE pmc_project_test_repository_plan (
                                    id varchar(12) NOT NULL PRIMARY KEY,
                                    test_repository_id varchar(12) NOT NULL,
                                    project_id varchar(12) NOT NULL,
                                    plan_id varchar(12) NOT NULL,
                                    sort int4
);
COMMENT ON COLUMN pmc_project_test_repository_plan.test_repository_id IS '测试用例库id';
COMMENT ON COLUMN pmc_project_test_repository_plan.project_id IS '项目id';
COMMENT ON COLUMN pmc_project_test_repository_plan.plan_id IS '测试计划id';