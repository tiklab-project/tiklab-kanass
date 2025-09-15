package io.tiklab.kanass.test.func.instance.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;

import io.tiklab.kanass.instance.entity.InstanceEntity;
import io.tiklab.kanass.test.func.instance.entity.FunctionInstanceEntity;
import io.tiklab.kanass.test.func.instance.model.FunctionInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能用例历史实例 数据访问
 */
@Repository
public class FunctionInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(FunctionInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建功能用例历史实例
     * @param testInstanceEntity
     * @return
     */
    public String createFunctionInstance(FunctionInstanceEntity testInstanceEntity) {
        return jpaTemplate.save(testInstanceEntity,String.class);
    }

    /**
     * 更新
     * @param testInstanceEntity
     */
    public void updateFunctionInstance(FunctionInstanceEntity testInstanceEntity){
        jpaTemplate.update(testInstanceEntity);
    }

    /**
     * 删除功能用例历史实例
     * @param id
     */
    public void deleteFunctionInstance(String id){
        jpaTemplate.delete(FunctionInstanceEntity.class,id);
    }

    public void deleteFunctionInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 通过id查找功能用例历史实例
     * @param id
     * @return
     */
    public FunctionInstanceEntity findFunctionInstance(String id){
        return jpaTemplate.findOne(FunctionInstanceEntity.class,id);
    }

    /**
    * 查找所有功能用例历史实例
    * @return
    */
    public List<FunctionInstanceEntity> findAllFunctionInstance() {
        return jpaTemplate.findAll(FunctionInstanceEntity.class);
    }

    public List<FunctionInstanceEntity> findFunctionInstanceList(List<String> idList) {
        return jpaTemplate.findList(FunctionInstanceEntity.class,idList);
    }

    /**
     * 根据查询参数查询功能用例历史实例列表
     * @param functionInstanceQuery
     * @return
     */
    public List<FunctionInstanceEntity> findFunctionInstanceList(FunctionInstanceQuery functionInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FunctionInstanceEntity.class)
                .eq("testPlanInstanceId", functionInstanceQuery.getTestPlanInstanceId())
                .eq("caseId", functionInstanceQuery.getFunctionId())
                .orders(functionInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, FunctionInstanceEntity.class);
    }

    public List<FunctionInstanceEntity> findRecentCaseFunctionInstanceList(FunctionInstanceQuery functionInstanceQuery){
        String sql = "SELECT t1.* FROM test_function_instance t1 JOIN (" +
                "    SELECT test_plan_instance_id, MAX(create_time) AS max_time " +
                "    FROM test_function_instance " +
                "    WHERE case_id = ? " +
                "    GROUP BY test_plan_instance_id " +
                ") t2 ON t1.test_plan_instance_id = t2.test_plan_instance_id AND t1.create_time = t2.max_time " +
                "WHERE t1.case_id = ? " +
                "ORDER BY t1.create_time DESC;";

        return jpaTemplate.getJdbcTemplate().query(
                sql,
                new Object[]{functionInstanceQuery.getFunctionId(), functionInstanceQuery.getFunctionId()},
                new BeanPropertyRowMapper<>(FunctionInstanceEntity.class)
        );
    }

    /**
     * 根据查询按分页查询功能用例历史实例
     * @param functionInstanceQuery
     * @return
     */
    public Pagination<FunctionInstanceEntity> findFunctionInstancePage(FunctionInstanceQuery functionInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(FunctionInstanceEntity.class)
                .eq("testPlanInstanceId", functionInstanceQuery.getTestPlanInstanceId())
                .eq("caseId", functionInstanceQuery.getFunctionId())
                .orders(functionInstanceQuery.getOrderParams())
                .pagination(functionInstanceQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, FunctionInstanceEntity.class);
    }
}