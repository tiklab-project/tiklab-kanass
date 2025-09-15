package io.tiklab.kanass.instance.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.instance.entity.InstanceEntity;
import io.tiklab.kanass.instance.model.Instance;
import io.tiklab.kanass.instance.model.InstanceQuery;
import io.tiklab.kanass.instance.model.InstanceStatusSummary;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 公共实例 数据访问
 */
@Repository
public class InstanceDao {

    private static Logger logger = LoggerFactory.getLogger(InstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建公共实例
     */
    public String createInstance(InstanceEntity instanceEntity) {
        return jpaTemplate.save(instanceEntity,String.class);
    }

    /**
     * 更新公共实例
     * @param instanceEntity
     */
    public void updateInstance(InstanceEntity instanceEntity){
        jpaTemplate.update(instanceEntity);
    }

    /**
     * 删除公共实例
     * @param id
     */
    public void deleteInstance(String id){
        jpaTemplate.delete(InstanceEntity.class,id);
    }

    public void deleteInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找公共实例
     * @param id
     * @return
     */
    public InstanceEntity findInstance(String id){
        return jpaTemplate.findOne(InstanceEntity.class,id);
    }


    /**
    * 查找所有公共实例
    * @return
    */
    public List<InstanceEntity> findAllInstance() {
        return jpaTemplate.findAll(InstanceEntity.class);
    }

    public List<InstanceEntity> findInstanceList(List<String> idList) {
        return jpaTemplate.findList(InstanceEntity.class,idList);
    }

    public InstanceEntity findRecentInstance(String belongId){
        try {
            String sql = "SELECT * FROM test_instance WHERE belong_id = ? ORDER BY create_time DESC LIMIT 1";

            InstanceEntity instanceEntity = jpaTemplate.getJdbcTemplate()
                    .queryForObject(sql, new Object[]{belongId}, new BeanPropertyRowMapper<>(InstanceEntity.class));

            return instanceEntity;
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            return null;
        }

    }


    /**
     * 查询公共实例列表
     * @param instanceQuery
     * @return
     */
    public List<InstanceEntity> findInstanceList(InstanceQuery instanceQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(InstanceEntity.class,"instance")
                .eq("instance.projectId",instanceQuery.getProjectId())
                .eq("instance.caseId",instanceQuery.getCaseId())
                .eq("instance.testPlanId",instanceQuery.getTestPlanId())
                .eq("instance.type",instanceQuery.getType())
                .eq("instance.createUser",instanceQuery.getCreateUser())
                .eq("instance.status",instanceQuery.getStatus())
                .like("instance.name",instanceQuery.getName())
                .pagination(instanceQuery.getPageParam())
                .orders(instanceQuery.getOrderParams());

        if(ArrayUtils.isNotEmpty(instanceQuery.getTypeList())){
            queryBuilders.in("instance.type",instanceQuery.getTypeList());
        }

        QueryCondition queryCondition = queryBuilders.get();

        return jpaTemplate.findList(queryCondition, InstanceEntity.class);
    }


    /**
     * 按分页查询公共实例
     * @param instanceQuery
     * @return
     */
//    public Pagination<InstanceEntity> findInstancePage(InstanceQuery instanceQuery) {
//        QueryBuilders queryBuilders = QueryBuilders.createQuery(InstanceEntity.class,"instance")
//                .eq("instance.projectId",instanceQuery.getProjectId())
//                .eq("instance.belongId",instanceQuery.getBelongId())
//                .eq("instance.type",instanceQuery.getType())
//                .eq("instance.createUser",instanceQuery.getCreateUser())
//                .eq("instance.status",instanceQuery.getStatus())
//                .like("instance.name",instanceQuery.getName())
//                .pagination(instanceQuery.getPageParam())
//                .orders(instanceQuery.getOrderParams());
//
//        if(ArrayUtils.isNotEmpty(instanceQuery.getTypeList())){
//            queryBuilders.in("instance.type",instanceQuery.getTypeList());
//        }
//
//        // 动态关联testPlan表并加上planType条件
//        if (instanceQuery.getPlanType()!=null) {
//            queryBuilders.leftJoin(TestPlanEntity.class, "testPlan", "testPlan.id=instance.belongId")
//                    .eq("testPlan.type", instanceQuery.getPlanType());
//        }
//
//
//        QueryCondition queryCondition =queryBuilders.get();
//
//        return jpaTemplate.findPage(queryCondition, InstanceEntity.class);
//    }

    public Pagination<InstanceEntity> findInstancePage(InstanceQuery instanceQuery) {
        // 使用 StringBuilder 高效拼接 SQL
        StringBuilder sql = new StringBuilder("SELECT i.* FROM test_instance i");

        // 用于存储 PreparedStatement 的参数，顺序必须与 '?' 占位符一致
        List<Object> params = new ArrayList<>();

        // 处理动态 JOIN
        // 如果需要根据 planType 查询，则必须关联 test_plan 表
        if (instanceQuery.getPlanType() != null) {
            sql.append(" LEFT JOIN test_test_plan tp ON i.belong_id = tp.id");
        }

        sql.append(" WHERE 1=1");

        if (instanceQuery.getProjectId() != null) {
            sql.append(" AND i.project_id = ?");
            params.add(instanceQuery.getProjectId());
        }
        if (instanceQuery.getCaseId() != null) {
            sql.append(" AND i.case_id = ?");
            params.add(instanceQuery.getCaseId());
        }
        if (instanceQuery.getTestPlanId() != null) {
            sql.append(" AND i.test_plan_id = ?");
            params.add(instanceQuery.getTestPlanId());
        }
        if (instanceQuery.getType() != null) {
            sql.append(" AND i.type = ?");
            params.add(instanceQuery.getType());
        }
        if (instanceQuery.getCreateUser() != null) {
            sql.append(" AND i.create_user = ?");
            params.add(instanceQuery.getCreateUser());
        }
        if (instanceQuery.getStatus() != null) {
            sql.append(" AND i.status = ?");
            params.add(instanceQuery.getStatus());
        }
        // 处理 LIKE 查询
        if (instanceQuery.getName() != null && !instanceQuery.getName().isEmpty()) {
            sql.append(" AND i.name LIKE ?");
            params.add("%" + instanceQuery.getName() + "%");
        }

        if (instanceQuery.getStartTime() != null) {
            sql.append(" AND i.create_time >= ?");
            params.add(instanceQuery.getStartTime());
        }
        if (instanceQuery.getEndTime() != null) {
            sql.append(" AND i.create_time <= ?");
            params.add(instanceQuery.getEndTime());
        }

        if (ArrayUtils.isNotEmpty(instanceQuery.getTypeList())) {
            // 生成 ?,?,? 占位
            String placeholders = Arrays.stream(instanceQuery.getTypeList())
                    .map(id -> "?")
                    .collect(Collectors.joining(", "));

            sql.append("AND i.type IN (").append(placeholders).append(") ");

            params.addAll(Arrays.asList(instanceQuery.getTypeList()));
        }

        // 处理来自 JOIN 表的条件
        if (instanceQuery.getPlanType() != null) {
            sql.append(" AND tp.type = ?");
            params.add(instanceQuery.getPlanType());
        }

        // 按创建时间倒序排列
        sql.append(" ORDER BY i.create_time DESC");

        Pagination<InstanceEntity> page = jpaTemplate.getJdbcTemplate().findPage(
                sql.toString(), params.toArray(), instanceQuery.getPageParam(), new BeanPropertyRowMapper<>(InstanceEntity.class)
        );

        return page;
    }




    /**
     * 获取最近一次执行次数
     * @param caseId
     * @return
     */
    public int getRecentExecuteNum(String caseId) {
        try {
            String exeSql = "SELECT COALESCE(execute_number, 0) AS execute_number FROM test_instance WHERE case_id = '" + caseId+ "' ORDER BY execute_number DESC LIMIT 1;";
            return jpaTemplate.getJdbcTemplate().queryForObject(exeSql, new Object[]{}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }


    /**
     *
     * 获取最近一次执行次数
     * @param testPlanId
     * @return
     */
    public int getRecentPlanExecuteNum(String testPlanId) {
        try {
            String exeSql = "SELECT COALESCE(execute_number, 0) AS execute_number FROM test_instance WHERE test_plan_id = '" + testPlanId+ "' ORDER BY execute_number DESC LIMIT 1;";
            return jpaTemplate.getJdbcTemplate().queryForObject(exeSql, new Object[]{}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    /**
     * case
     * 获取最近一次执行历史id
     * @param caseId
     * @return
     */
    public String getRecentExecuteInstanceId(String caseId) {
        try {
            String exeSql = "SELECT id FROM test_instance WHERE case_id =  '" + caseId + "'  ORDER BY execute_number DESC LIMIT 1;";
            return jpaTemplate.getJdbcTemplate().queryForObject(exeSql, new Object[]{}, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    /**
     * test plan
     * 获取最近一次执行历史id
     * @param testPlanId
     * @return
     */
    public String getRecentExecutePlanInstanceId(String testPlanId) {
        try {
            String exeSql = "SELECT id FROM test_instance WHERE test_plan_id =  '" + testPlanId + "'  ORDER BY execute_number DESC LIMIT 1;";
            return jpaTemplate.getJdbcTemplate().queryForObject(exeSql, new Object[]{}, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * 获取case执行次数
     * @param caseId
     * @return
     */
    public int findInstanceNum(String caseId) {
        String numSql = "Select count(1) as total from test_instance where case_id = '" + caseId+ "'";
        Integer num = jpaTemplate.getJdbcTemplate().queryForObject(numSql, new Object[]{}, Integer.class);

        return num;
    }

    public int findPlanInstanceNum(String testPlanId) {
        String numSql = "Select count(1) as total from test_instance where test_plan_id = '" + testPlanId+ "'";
        Integer num = jpaTemplate.getJdbcTemplate().queryForObject(numSql, new Object[]{}, Integer.class);

        return num;
    }



    public int findInstanceNumByProjectId(String projectId) {
        String numSql = "Select count(1) as total from test_instance where project_id = '" + projectId+ "'";
        Integer num = jpaTemplate.getJdbcTemplate().queryForObject(numSql, new Object[]{}, Integer.class);

        return num;
    }

    /**
     * 获取历史状态的统计的信息
     * @return
     */
    public InstanceStatusSummary getInstanceStatusSummary(InstanceQuery instanceQuery) {
        Timestamp startTime = instanceQuery.getStartTime();
        Timestamp endTime = instanceQuery.getEndTime();
        String testPlanId = instanceQuery.getTestPlanId();

        String sql = "SELECT " +
                "    COUNT(id) AS total, " +
                "    SUM(CASE WHEN status = 'fail' THEN 1 ELSE 0 END) AS fail_count, " +
                "    SUM(CASE WHEN status = 'success' THEN 1 ELSE 0 END) AS success_count " +
                "FROM " +
                "    test_instance " +
                "WHERE " +
                "    test_plan_id = ? " +
                "    AND create_time >= ? " +
                "    AND create_time < ?";

        Map<String, Object> resultFromDb = jpaTemplate.getJdbcTemplate().queryForMap(sql, testPlanId, startTime, endTime);

        InstanceStatusSummary instanceStatusSummary = new InstanceStatusSummary();

        // 安全的类型转换
        Object totalObj = resultFromDb.getOrDefault("total", 0);
        int total = (totalObj != null) ? ((Number) totalObj).intValue() : 0;

        Object failObj = resultFromDb.getOrDefault("fail_count", 0);
        int failCount = (failObj != null) ? ((Number) failObj).intValue() : 0;

        Object successObj = resultFromDb.getOrDefault("success_count", 0);
        int successCount = (successObj != null) ? ((Number) successObj).intValue() : 0;

        instanceStatusSummary.setTotal(total);
        instanceStatusSummary.setFailCount(failCount);
        instanceStatusSummary.setSuccessCount(successCount);

        return instanceStatusSummary;
    }


}