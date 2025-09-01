ALTER TABLE "test_testcase" ADD COLUMN demand VARCHAR(12) DEFAULT null;
COMMENT ON COLUMN test_testcase.demand IS '关联需求';