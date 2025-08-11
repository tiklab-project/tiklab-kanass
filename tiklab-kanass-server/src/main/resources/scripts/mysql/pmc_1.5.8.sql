-- ----------------------------
-- test_testcase
-- ----------------------------
CREATE TABLE test_testcase (
                               id varchar(32) NOT NULL,
                               case_key varchar(32),
                               name varchar(64) NOT NULL,
                               module_id varchar(32),
                               project_id varchar(32),
                               case_type varchar(32),
                               create_user varchar(32),
                               update_user varchar(32),
                               create_time timestamp NULL DEFAULT NULL,
                               update_time timestamp NULL DEFAULT NULL,
                               sort int(11),
                               director varchar(32),
                               status int(11),
                               priority_level int(11),
                               description varchar(64),
                               PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 功能用例
CREATE TABLE test_func_unit (
                                id varchar(32) NOT NULL,
                                testcase_id varchar(32),
                                precondition text,
                                PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- 功能用例步骤
-- ----------------------------
CREATE TABLE test_func_unit_step (
                                     id varchar(32) NOT NULL,
                                     func_unit_id varchar(32),
                                     expect varchar(512),
                                     described varchar(512),
                                     PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- 功能用例步骤公共表
-- ----------------------------
CREATE TABLE test_case_step_common (
                                       id varchar(32) NOT NULL,
                                       case_id varchar(32),
                                       create_time timestamp NULL DEFAULT NULL,
                                       type varchar(32),
                                       sort int(11),
                                       PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- 测试计划
-- ----------------------------
CREATE TABLE test_test_plan (
                                id varchar(32) NOT NULL,
                                name varchar(32),
                                start_time timestamp NULL DEFAULT NULL,
                                end_time timestamp NULL DEFAULT NULL,
                                state int(11),
                                principals varchar(32),
                                project_id varchar(32),
                                description varchar(64),
                                create_time timestamp NULL DEFAULT NULL,
                                update_time timestamp NULL DEFAULT NULL,
                                PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- 测试计划中的用例
-- ----------------------------
CREATE TABLE test_test_plan_cases (
                                      id varchar(32) NOT NULL,
                                      test_plan_id varchar(32),
                                      test_case_id varchar(32),
                                      status int(11),
                                      sort int(11),
                                      PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- 功能用例的实例
-- ----------------------------
CREATE TABLE test_function_instance (
                                        id varchar(12) NOT NULL,
                                        test_plan_instance_id varchar(32),
                                        case_id varchar(32),
                                        result int(11),
                                        create_time timestamp NULL DEFAULT NULL,
                                        create_user varchar(32),
                                        comment text,
                                        PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- 实例公共表
-- ----------------------------
CREATE TABLE test_instance (
                               id varchar(32) NOT NULL,
                               project_id varchar(32),
                               type varchar(64) NOT NULL,
                               name varchar(256),
                               create_time timestamp NULL DEFAULT NULL,
                               create_user varchar(32),
                               execute_number int(11),
                               content text,
                               status varchar(8),
                               case_id varchar(32),
                               test_plan_id varchar(32),
                               PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- 计划中的测试报告
-- ----------------------------
CREATE TABLE test_test_report (
                                  id varchar(12) NOT NULL,
                                  project_id varchar(32),
                                  name varchar(64),
                                  start_time timestamp NULL DEFAULT NULL,
                                  end_time timestamp NULL DEFAULT NULL,
                                  create_user varchar(32),
                                  description varchar(256),
                                  create_time timestamp NULL DEFAULT NULL,
                                  test_plan_id varchar(32),
                                  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE test_workitem_bind (
                                    id varchar(32) NOT NULL,
                                    work_item_id varchar(32),
                                    case_id varchar(32),
                                    create_time timestamp NULL DEFAULT NULL,
                                    project_id varchar(32),
                                    test_plan_id varchar(32),
                                    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;