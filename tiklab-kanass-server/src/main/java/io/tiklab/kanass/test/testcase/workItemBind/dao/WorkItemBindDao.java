package io.tiklab.kanass.test.testcase.workItemBind.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.test.testcase.workItemBind.entity.WorkItemBindEntity;
import io.tiklab.kanass.test.testcase.workItemBind.model.WorkItemBindQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 绑定的缺陷 数据访问
 */
@Repository
public class WorkItemBindDao {

    private static Logger logger = LoggerFactory.getLogger(WorkItemBindDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建缺陷
     * @param workItemBindEntity
     * @return
     */
    public String createWorkItemBind(WorkItemBindEntity workItemBindEntity) {
        return jpaTemplate.save(workItemBindEntity,String.class);
    }

    /**
     * 更新缺陷
     * @param workItemBindEntity
     */
    public void updateWorkItemBind(WorkItemBindEntity workItemBindEntity){
        jpaTemplate.update(workItemBindEntity);
    }

    /**
     * 删除缺陷
     * @param id
     */
    public void deleteWorkItemBind(String id){
        jpaTemplate.delete(WorkItemBindEntity.class,id);
    }

    public void deleteWorkItemBind(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    public int findTotalNum(String caseId) {
        String stepSql = "Select count(1) as total from test_workitem_bind where case_id = '" + caseId+ "'";
        Integer stepNum = jpaTemplate.getJdbcTemplate().queryForObject(stepSql, new Object[]{}, Integer.class);

        return stepNum;
    }


    /**
     * 根据id查找缺陷
     * @param id
     * @return
     */
    public WorkItemBindEntity findWorkItemBind(String id){
        return jpaTemplate.findOne(WorkItemBindEntity.class,id);
    }

    /**
    * 查找所有缺陷
    * @return
    */
    public List<WorkItemBindEntity> findAllWorkItemBind() {
        return jpaTemplate.findAll(WorkItemBindEntity.class);
    }

    public List<WorkItemBindEntity> findWorkItemBindList(List<String> idList) {
        return jpaTemplate.findList(WorkItemBindEntity.class,idList);
    }

    /**
     * 根据查询参数查询缺陷列表
     * @param workItemBindQuery
     * @return
     */
    public List<WorkItemBindEntity> findWorkItemBindList(WorkItemBindQuery workItemBindQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(WorkItemBindEntity.class)
                .eq("caseId",workItemBindQuery.getCaseId())
                .eq("projectId",workItemBindQuery.getProjectId())
                .eq("testPlanId",workItemBindQuery.getTestPlanId())
                .orders(workItemBindQuery.getOrderParams());

        QueryCondition queryCondition =queryBuilders.get();

        return jpaTemplate.findList(queryCondition, WorkItemBindEntity.class);
    }

    /**
     * 根据查询参数按分页查询缺陷
     * @param workItemBindQuery
     * @return
     */
//    public Pagination<WorkItemBindEntity> findWorkItemBindPage(WorkItemBindQuery workItemBindQuery) {
//        QueryCondition queryCondition = QueryBuilders.createQuery(WorkItemBindEntity.class)
//                .eq("caseId", workItemBindQuery.getCaseId())
//                .eq("projectId", workItemBindQuery.getProjectId())
//                .eq("testPlanId", workItemBindQuery.getTestPlanId())
//                .orders(workItemBindQuery.getOrderParams())
//                .pagination(workItemBindQuery.getPageParam())
//                .get();
//
//        return jpaTemplate.findPage(queryCondition, WorkItemBindEntity.class);
//    }

    public Pagination<WorkItemBindEntity> findWorkItemBindPage(WorkItemBindQuery workItemBindQuery) {
        // 初始化SQL和参数列表
        StringBuilder sql = new StringBuilder("SELECT * FROM test_workitem_bind");
        List<Object> params = new ArrayList<>();

        // 拼接 WHERE 子句和动态条件
        sql.append(" WHERE 1=1");

        if (workItemBindQuery.getCaseId() != null) {
            sql.append(" AND case_id = ?");
            params.add(workItemBindQuery.getCaseId());
        }
        // 新增 caseIds 数组条件
        if (workItemBindQuery.getCaseIds() != null && workItemBindQuery.getCaseIds().length > 0) {
            sql.append(" AND case_id IN (");
            String[] caseIds = workItemBindQuery.getCaseIds();
            for (int i = 0; i < caseIds.length; i++) {
                if (i > 0) {
                    sql.append(",");
                }
                sql.append("?");
                params.add(caseIds[i]);
            }
            sql.append(")");
        }

        if (workItemBindQuery.getProjectId() != null) {
            sql.append(" AND project_id = ?");
            params.add(workItemBindQuery.getProjectId());
        }
        if (workItemBindQuery.getTestPlanId() != null) {
            sql.append(" AND test_plan_id = ?");
            params.add(workItemBindQuery.getTestPlanId());
        }

        // 处理时间范围
        if (workItemBindQuery.getStartTime() != null) {
            sql.append(" AND create_time >= ?");
            params.add(workItemBindQuery.getStartTime());
        }
        if (workItemBindQuery.getEndTime() != null) {
            sql.append(" AND create_time <= ?");
            params.add(workItemBindQuery.getEndTime());
        }

        Pagination<WorkItemBindEntity> page = jpaTemplate.getJdbcTemplate().findPage(
                sql.toString(),
                params.toArray(),
                workItemBindQuery.getPageParam(),
                new BeanPropertyRowMapper<>(WorkItemBindEntity.class)
        );

        return page;
    }
}