package io.tiklab.kanass.testplan.cases.dao;

import io.tiklab.kanass.common.MagicValue;
import io.tiklab.kanass.testplan.cases.entity.TestPlanCaseEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.testplan.cases.model.TestPlanCaseQuery;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.kanass.testplan.cases.entity.PlanCaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 测试计划绑定的用例 服务接口
 */
@Repository
public class TestPlanCaseDao {

    private static Logger logger = LoggerFactory.getLogger(TestPlanCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建绑定的用例
     * @param testPlanCaseEntity
     * @return
     */
    public String createTestPlanCase(TestPlanCaseEntity testPlanCaseEntity) {
        return jpaTemplate.save(testPlanCaseEntity,String.class);
    }

    /**
     * 更新绑定的用例
     * @param testPlanCaseEntity
     */
    public void updateTestPlanCase(TestPlanCaseEntity testPlanCaseEntity){
        jpaTemplate.update(testPlanCaseEntity);
    }

    /**
     * 删除绑定的用例
     * @param id
     */
    public void deleteTestPlanCase(String id){
        jpaTemplate.delete(TestPlanCaseEntity.class,id);
    }

    public void deleteTestPlanCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找绑定的用例
     * @param id
     * @return
     */
    public TestPlanCaseEntity findTestPlanCase(String id){
        return jpaTemplate.findOne(TestPlanCaseEntity.class,id);
    }

    /**
    * 查找所有绑定的用例
    * @return
    */
    public List<TestPlanCaseEntity> findAllTestPlanCase() {
        return jpaTemplate.findAll(TestPlanCaseEntity.class);
    }

    public List<TestPlanCaseEntity> findTestPlanCaseList(List<String> idList) {
        return jpaTemplate.findList(TestPlanCaseEntity.class,idList);
    }

    /**
     * 查询绑定的用例 列表
     * @param testPlanCaseQuery
     * @return
     */
    public List<TestPlanCaseEntity> findTestPlanCaseList(TestPlanCaseQuery testPlanCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestPlanCaseEntity.class)
                .eq("testPlanId", testPlanCaseQuery.getTestPlanId())
                .orders(testPlanCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, TestPlanCaseEntity.class);
    }

    /**
     * 按分页查询绑定的用例
     * @param testPlanCaseQuery
     * @return
     */
    public Pagination<TestPlanCaseEntity> findTestPlanCasePage(TestPlanCaseQuery testPlanCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestPlanCaseEntity.class)
                .eq("testPlanId", testPlanCaseQuery.getTestPlanId())
                .orders(testPlanCaseQuery.getOrderParams())
                .pagination(testPlanCaseQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, TestPlanCaseEntity.class);
    }

    /**
     * 查询测试计划绑定的用例总数
     * @param testPlanId
     * @return
     */
    public int findPlanCaseNum(String testPlanId) {
        String sql = "Select count(1) as total from test_test_plan_cases where test_plan_id = '" + testPlanId+ "'";
        Integer total = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);

        return total;
    }


    /**
     * 查询绑定的用例 列表
     * @param testPlanCaseQuery
     * @return
     */
    public List<PlanCaseEntity> findPlanCaseList(TestPlanCaseQuery testPlanCaseQuery) {
        StringBuilder modelSqlBuilder = new StringBuilder();
        modelSqlBuilder.append("SELECT test_test_plan_cases.id AS plan_case_id,test_testcase.id, test_testcase.create_user, test_testcase.create_time, test_testcase.case_type, test_testcase.module_id, test_testcase.name ")
                .append("FROM test_test_plan_cases ")
                .append("JOIN test_testcase ON test_testcase.id = test_test_plan_cases.test_case_id ")
                .append("JOIN test_test_plan ON test_test_plan.id = test_test_plan_cases.test_plan_id ")
                .append("WHERE test_test_plan.id = '").append(testPlanCaseQuery.getTestPlanId()).append("'");

        if(testPlanCaseQuery.getCaseTypeList()!=null&&testPlanCaseQuery.getCaseTypeList().length>0){
            StringBuilder caseTypeList = new StringBuilder();
            for (int i = 0; i < testPlanCaseQuery.getCaseTypeList().length; i++) {
                String s = testPlanCaseQuery.getCaseTypeList()[i];
                caseTypeList.append("'").append(s).append("'");

                if (i < testPlanCaseQuery.getCaseTypeList().length - 1) {
                    caseTypeList.append(",");
                }
            }

            modelSqlBuilder.append(" AND test_testcase.case_type in (").append(caseTypeList).append(")");
        }

        String modelSql = modelSqlBuilder.toString();
        List<PlanCaseEntity> planCaseEntityList = jpaTemplate.getJdbcTemplate()
                .query(modelSql,new BeanPropertyRowMapper<>(PlanCaseEntity.class));

        return planCaseEntityList;
    }


    /**
     * 按分页查询绑定的用例
     * @param testPlanCaseQuery
     * @return
     */
    public Pagination<PlanCaseEntity> findPlanCasePage(TestPlanCaseQuery testPlanCaseQuery) {
        StringBuilder modelSqlBuilder = new StringBuilder();
        modelSqlBuilder.append("SELECT test_test_plan_cases.id AS plan_case_id, test_testcase.id, test_testcase.create_user, test_testcase.create_time, ")
                .append("test_testcase.case_type, test_testcase.module_id, test_testcase.name ");

        boolean isFunctionPlan = Objects.equals(testPlanCaseQuery.getTestPlanType(), MagicValue.TEST_TYPE_FUNCTION);

        // FUNCTION 计划时加入 result 字段
        modelSqlBuilder.append(", (SELECT result FROM test_function_instance ")
                .append("WHERE test_function_instance.test_plan_instance_id = test_test_plan.id ")
                .append("AND test_function_instance.case_id = test_testcase.id ")
                .append("ORDER BY create_time DESC LIMIT 1) AS result ");


        modelSqlBuilder.append("FROM test_test_plan_cases ")
                .append("JOIN test_testcase ON test_testcase.id = test_test_plan_cases.test_case_id ")
                .append("JOIN test_test_plan ON test_test_plan.id = test_test_plan_cases.test_plan_id ");

        modelSqlBuilder.append("WHERE test_test_plan.id = '").append(testPlanCaseQuery.getTestPlanId()).append("'");

        if (testPlanCaseQuery.getName() != null) {
            modelSqlBuilder.append(" AND test_testcase.name LIKE '%").append(testPlanCaseQuery.getName()).append("%'");
        }

        String moduleId = testPlanCaseQuery.getModuleId();
        if (moduleId != null && !"nullstring".equals(moduleId)) {
            // 如果 moduleId 不为 null 且不是 "nullstring"，则按指定 moduleId 查询
            modelSqlBuilder.append(" AND test_testcase.module_id = '").append(moduleId).append("'");
        } else if ("nullstring".equals(moduleId)) {
            // 如果 moduleId 是 "nullstring"，则只查询 module_id 为 null 的记录
            modelSqlBuilder.append(" AND test_testcase.module_id IS NULL");
        }

        if (testPlanCaseQuery.getCaseType() != null) {
            modelSqlBuilder.append(" AND test_testcase.case_type = '").append(testPlanCaseQuery.getCaseType()).append("'");
        }

        if (isFunctionPlan && testPlanCaseQuery.getResult() != null) {
            modelSqlBuilder.append(" AND EXISTS (SELECT 1 FROM test_function_instance ")
                    .append("WHERE test_function_instance.test_plan_instance_id = test_test_plan.id ")
                    .append("AND test_function_instance.case_id = test_testcase.id ")
                    .append("AND test_function_instance.result = ").append(testPlanCaseQuery.getResult())
                    .append(" ORDER BY create_time DESC LIMIT 1)");
        }

        if (testPlanCaseQuery.getCaseTypeList() != null && testPlanCaseQuery.getCaseTypeList().length > 0) {
            String caseTypeList = String.join(",", Arrays.stream(testPlanCaseQuery.getCaseTypeList())
                    .map(type -> "'" + type + "'")
                    .toArray(String[]::new));
            modelSqlBuilder.append(" AND test_testcase.case_type IN (").append(caseTypeList).append(")");
        }

        String modelSql = modelSqlBuilder.toString();
        Pagination<PlanCaseEntity> page = jpaTemplate.getJdbcTemplate().findPage(
                modelSql, new Object[]{}, testPlanCaseQuery.getPageParam(), new BeanPropertyRowMapper<>(PlanCaseEntity.class)
        );
        return page;
    }



    /**
     * 查询未关联的用例
     * @param testPlanCaseQuery
     * @return
     */
    public Pagination<PlanCaseEntity> findTestCasePage(TestPlanCaseQuery testPlanCaseQuery) {
        StringBuilder modelSqlBuilder = new StringBuilder();
        modelSqlBuilder.append("SELECT  test_testcase.id AS plan_case_id, test_testcase.id, test_testcase.director,  test_testcase.create_user, test_testcase.create_time, test_testcase.case_type, test_testcase.module_id, test_testcase.name, test_testcase.status,test_testcase.priority_level ")
                .append(" FROM test_testcase ")
                .append(" WHERE test_testcase.project_id = '").append(testPlanCaseQuery.getProjectId()).append("'")
                .append(" AND NOT EXISTS (  ")
                .append(" SELECT 1   ")
                .append(" FROM test_test_plan_cases  ")
                .append(" JOIN test_test_plan ON test_test_plan.id = test_test_plan_cases.test_plan_id ")
                .append(" WHERE test_test_plan_cases.test_case_id = test_testcase.id ")
                .append(" AND test_test_plan.id = '").append(testPlanCaseQuery.getTestPlanId()).append("')");


        if (testPlanCaseQuery.getName() != null) {
            modelSqlBuilder.append(" AND test_testcase.name LIKE '%").append(testPlanCaseQuery.getName()).append("%'");
        }

        String modelSql = modelSqlBuilder.toString();

        Pagination<PlanCaseEntity> page = jpaTemplate.getJdbcTemplate().findPage(modelSql, new Object[]{}, testPlanCaseQuery.getPageParam(), new BeanPropertyRowMapper<>(PlanCaseEntity.class));
        return page;
    }

    /**
     * 通过用例id，判断是否用例被绑定
     * @param caseId
     * @return
     */
    public Integer isCaseExist(String caseId) {
        String sql = "Select count(1) as total from test_test_plan_cases where test_case_id = '" + caseId + "'";
        Integer modelTotal = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);

        return modelTotal;
    }

    /**
     * 获取不同用例类型的数量
     * @param testPlanId
     * @return
     */
    public List<Map<String, Object>> getCaseTypeNum(String testPlanId){
        String sql = "SELECT tc.case_type, COUNT(*) AS total " +
                "FROM test_test_plan_cases AS tpd " +
                "INNER JOIN test_testcase AS tc ON tpd.test_case_id = tc.id " +
                "INNER JOIN test_test_plan AS tp ON tpd.test_plan_id = tp.id " +
                "WHERE tp.id = ? " +
                "GROUP BY tc.case_type";

        List<Map<String, Object>> maps = jpaTemplate.getJdbcTemplate().queryForList(sql, testPlanId);

        return maps;
    }




}