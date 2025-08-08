package io.tiklab.kanass.testplan.instance.dao;

import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.kanass.testplan.instance.entity.FunctionTestReportEntity;
import io.tiklab.kanass.testplan.instance.model.FunctionTestReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

/**
 * 测试计划实例 数据访问
 */
@Repository
public class FunctionTestReportDao {

    private static Logger logger = LoggerFactory.getLogger(FunctionTestReportDao.class);

    @Autowired
    JpaTemplate jpaTemplate;





    public FunctionTestReportEntity getFunctionTestReport(String testPlanId){
        StringBuilder modelSqlBuilder = new StringBuilder();
        modelSqlBuilder.append("SELECT ");
        modelSqlBuilder.append("COUNT(*) AS total, ");
        modelSqlBuilder.append("COUNT(CASE WHEN i.result = 1 THEN 1 END) AS success_count, ");
        modelSqlBuilder.append("COUNT(CASE WHEN i.result = 0 THEN 1 END) AS fail_count, ");
        modelSqlBuilder.append("COUNT(*) - COUNT(i.result) AS not_executed_count ");
        modelSqlBuilder.append("FROM test_test_plan_cases p ");
        modelSqlBuilder.append("LEFT JOIN test_function_instance i ");
        modelSqlBuilder.append("ON p.test_plan_id = i.test_plan_instance_id ");
        modelSqlBuilder.append("AND p.test_case_id = i.case_id ");
        modelSqlBuilder.append("WHERE p.test_plan_id =  '").append(testPlanId).append("'");

        String modelSql = modelSqlBuilder.toString();
        FunctionTestReportEntity functionTestReportEntity = jpaTemplate.getJdbcTemplate().queryForObject(modelSql, new Object[]{}, new BeanPropertyRowMapper<>(FunctionTestReportEntity.class));


        return functionTestReportEntity;
    }

}