package io.tiklab.kanass.workitem.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jdbc.JdbcTemplate;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.workitem.entity.WorkItemRoleFunctionDmEntity;
import io.tiklab.kanass.workitem.entity.WorkItemRoleFunctionEntity;
import io.tiklab.kanass.workitem.model.WorkItemRoleFunctionDmQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * 事项优先级数据访问
 */
@Repository
public class WorkItemRoleFunctionDmDao {

    private static Logger logger = LoggerFactory.getLogger(WorkItemRoleFunctionDmDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项优先级
     * @param workItemFunctionEntity
     * @return
     */
    public String createWorkItemRoleFunctionDm(WorkItemRoleFunctionDmEntity workItemFunctionEntity) {
        return jpaTemplate.save(workItemFunctionEntity,String.class);
    }

    public void batchCreateWorkItemRoleFunctionDm(List<WorkItemRoleFunctionDmEntity> workItemRoleFunctionDmEntityList) {
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        jdbcTemplate.batchUpdate("INSERT INTO pmc_work_role_function_dm (id, domain_id, work_type_id, function_id, function_type, role_id) VALUES " +
                " (?, ?, ?, ?, ?, ?) ", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, workItemRoleFunctionDmEntityList.get(i).getId());
                setStringOrNull(ps, 2, workItemRoleFunctionDmEntityList.get(i).getDomainId());
                setStringOrNull(ps, 3, workItemRoleFunctionDmEntityList.get(i).getWorkTypeId());
                setStringOrNull(ps, 4, workItemRoleFunctionDmEntityList.get(i).getFunctionId());
                setStringOrNull(ps, 5, workItemRoleFunctionDmEntityList.get(i).getFunctionType());
                setStringOrNull(ps, 6, workItemRoleFunctionDmEntityList.get(i).getRoleId());
            }

            @Override
            public int getBatchSize() {
                return workItemRoleFunctionDmEntityList.size();
            }
        });
    }

    // 辅助方法：处理可能为null的String字段
    private void setStringOrNull(PreparedStatement ps, int parameterIndex, String value) throws SQLException {
        if (value != null) {
            ps.setString(parameterIndex, value);
        } else {
            ps.setNull(parameterIndex, Types.VARCHAR);
        }
    }

    // 辅助方法：处理可能为null的String字段
    private void setIntOrNull(PreparedStatement ps, int parameterIndex, Integer value) throws SQLException {
        if (value != null) {
            ps.setInt(parameterIndex, value);
        } else {
            ps.setNull(parameterIndex, Types.INTEGER);
        }
    }

    /**
     * 更新事项优先级
     * @param workItemFunctionEntity
     */
    public void updateWorkItemRoleFunctionDm(WorkItemRoleFunctionDmEntity workItemFunctionEntity){
        jpaTemplate.update(workItemFunctionEntity);
    }

    /**
     * 删除事项优先级
     * @param id
     */
    public void deleteWorkItemRoleFunctionDm(String id){
        jpaTemplate.delete(WorkItemRoleFunctionDmEntity.class,id);
    }

    public void  deleteWorkItemRoleFunctionDmCondition(WorkItemRoleFunctionDmQuery workItemRoleFunctionDmQuery){
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(WorkItemRoleFunctionDmEntity.class)
                .eq("workTypeId", workItemRoleFunctionDmQuery.getWorkTypeId())
                .eq("roleId", workItemRoleFunctionDmQuery.getRoleId())
                .eq("functionType", workItemRoleFunctionDmQuery.getFunctionType())
                .eq("domainId", workItemRoleFunctionDmQuery.getDomainId())
                .get();
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id 查找事项优先级列表
     * @param id
     * @return
     */
    public WorkItemRoleFunctionDmEntity findWorkItemRoleFunctionDm(String id){
        return jpaTemplate.findOne(WorkItemRoleFunctionDmEntity.class,id);
    }

    /**
     * 根据ids 查找事项优先级列表
     * @param idList
     * @return
     */
    public List<WorkItemRoleFunctionDmEntity> findWorkItemRoleFunctionDmList(List<String> idList) {
        return jpaTemplate.findList(WorkItemRoleFunctionDmEntity.class,idList);
    }

    /**
    * 查找所有事项优先级列表
    * @return
    */
    public List<WorkItemRoleFunctionDmEntity> findAllWorkItemRoleFunctionDm() {
        return jpaTemplate.findAll(WorkItemRoleFunctionDmEntity.class);
    }

    /**
     * 根据条件查找事项优先级列表
     * @param workItemRoleFunctionDm
     * @return
     */
    public List<WorkItemRoleFunctionDmEntity> findWorkItemRoleFunctionDmList(WorkItemRoleFunctionDmQuery workItemRoleFunctionDm) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkItemRoleFunctionDmEntity.class)
                .eq("domainId", workItemRoleFunctionDm.getDomainId())
                .eq("workTypeId", workItemRoleFunctionDm.getWorkTypeId())
                .eq("roleId", workItemRoleFunctionDm.getRoleId())
                .eq("functionType", workItemRoleFunctionDm.getFunctionType())
                .in("roleId", workItemRoleFunctionDm.getRoleIds())
                .orders(workItemRoleFunctionDm.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkItemRoleFunctionDmEntity.class);
    }

    /**
     * 根据条件按照分页查找事项优先级列表
     * @param workItemRoleFunctionDm
     * @return
     */
    public Pagination<WorkItemRoleFunctionDmEntity> findWorkItemRoleFunctionDmPage(WorkItemRoleFunctionDmQuery workItemRoleFunctionDm) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkItemRoleFunctionDmEntity.class)
                .eq("domainId", workItemRoleFunctionDm.getDomainId())
                .eq("workTypeId", workItemRoleFunctionDm.getWorkTypeId())
                .orders(workItemRoleFunctionDm.getOrderParams())
                .pagination(workItemRoleFunctionDm.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WorkItemRoleFunctionDmEntity.class);
    }


}