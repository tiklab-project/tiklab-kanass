-- 更新测试用例表，将null记录默认改为中
update test_testcase set priority_level = 1 where priority_level is null;

