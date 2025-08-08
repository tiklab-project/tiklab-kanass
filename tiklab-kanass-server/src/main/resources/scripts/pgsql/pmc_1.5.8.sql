-- ----------------------------
-- test_testcase
-- ----------------------------
CREATE TABLE test_testcase (
  id varchar(32)  NOT NULL,
  case_key varchar(32),
  name varchar(64)  NOT NULL,
  module_id varchar(32),
  project_id varchar(32),
  case_type varchar(32),
  create_user varchar(32),
  update_user varchar(32),
  create_time timestamp(6),
  update_time timestamp(6),
  sort int,
  director varchar(32),
  status int,
  priority_level int,
  description varchar(64)
);

-- 功能用例
CREATE TABLE test_func_unit (
  id varchar(32)  NOT NULL,
  testcase_id varchar(32),
  precondition text
);

-- ----------------------------
-- 功能用例步骤
-- ----------------------------
CREATE TABLE test_func_unit_step (
  id varchar(32)  NOT NULL,
  func_unit_id varchar(32),
  expect varchar(512),
  described varchar(512)
);


-- ----------------------------
-- 功能用例步骤公共表
-- ----------------------------
CREATE TABLE test_case_step_common (
  id varchar(32)  NOT NULL,
  case_id varchar(32),
  create_time timestamp(6),
  type varchar(32),
  sort int
);



-- ----------------------------
-- 测试计划
-- ----------------------------
CREATE TABLE test_test_plan (
  id varchar(32)  NOT NULL,
  name varchar(32) ,
  start_time timestamp(6),
  end_time timestamp(6),
  state int,
  principals varchar(32),
  project_id varchar(32),
  description varchar(64),
  create_time timestamp(6),
  update_time timestamp(6)
);


-- ----------------------------
-- 测试计划中的用例
-- ----------------------------
CREATE TABLE test_test_plan_cases (
  id varchar(32)  NOT NULL,
  test_plan_id varchar(32) ,
  test_case_id varchar(32) ,
  status int,
  sort int
);

-- ----------------------------
-- 功能用例的实例
-- ----------------------------
CREATE TABLE test_function_instance (
  id varchar(12)  NOT NULL,
  test_plan_instance_id varchar(32) ,
  case_id varchar(32) ,
  result int,
  create_time timestamp(6),
  create_user varchar(32) ,
  comment text
);


-- ----------------------------
-- 实例公共表
-- ----------------------------
CREATE TABLE test_instance (
  id varchar(32)  NOT NULL,
  project_id varchar(32),
  type varchar(64)  NOT NULL,
  name varchar(256) ,
  create_time timestamp(6),
  create_user varchar(32) ,
  execute_number int,
  content text ,
  status varchar(8) ,
  case_id varchar(32) ,
  test_plan_id varchar(32)
);

-- ----------------------------
-- 计划中的测试报告
-- ----------------------------
CREATE TABLE test_test_report (
  id varchar(12)  NOT NULL,
  project_id varchar(32) ,
  name varchar(64) ,
  start_time timestamp(6),
  end_time timestamp(6),
  create_user varchar(32) ,
  description varchar(256) ,
  create_time timestamp(6),
  test_plan_id varchar(32)
);

CREATE TABLE test_workitem_bind (
  id varchar(32) NOT NULL,
  work_item_id varchar(32),
  case_id varchar(32),
  create_time timestamp,
  project_id varchar(32),
  test_plan_id varchar(32)
);
