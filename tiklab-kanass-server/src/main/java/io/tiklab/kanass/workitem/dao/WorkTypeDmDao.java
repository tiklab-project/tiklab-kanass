package io.tiklab.kanass.workitem.dao;

import io.tiklab.core.order.Order;
import io.tiklab.dal.jdbc.JdbcTemplate;
import io.tiklab.flow.flow.entity.DmFlowEntity;
import io.tiklab.kanass.project.project.entity.ProjectEntity;
import io.tiklab.kanass.workitem.entity.WorkTypeDmEntity;
import io.tiklab.kanass.workitem.entity.WorkTypeEntity;
import io.tiklab.kanass.workitem.model.WorkTypeDmQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * 项目的事项类型数据访问
 */
@Repository
public class WorkTypeDmDao{

    private static Logger logger = LoggerFactory.getLogger(WorkTypeDmDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项类型
     * @param workTypeDmEntity
     * @return
     */
    public String createWorkTypeDm(WorkTypeDmEntity workTypeDmEntity) {
        return jpaTemplate.save(workTypeDmEntity,String.class);
    }

    public void batchCreateWorkTypeDm(List<WorkTypeDmEntity> workTypeDmEntityList){
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        jdbcTemplate.batchUpdate("insert into pmc_work_type_dm (id,project_id,work_type_id,flow_id,form_id) " +
                " values (?,?,?,?,?) ", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, workTypeDmEntityList.get(i).getId());
                setStringOrNull(ps, 2, workTypeDmEntityList.get(i).getProjectId());
                setStringOrNull(ps, 3, workTypeDmEntityList.get(i).getWorkTypeId());
                setStringOrNull(ps, 4, workTypeDmEntityList.get(i).getFlowId());
                setStringOrNull(ps, 5, workTypeDmEntityList.get(i).getFormId());
            }

            @Override
            public int getBatchSize() {
                return workTypeDmEntityList.size();
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

    /**
     * 更新事项类型
     * @param workTypeDmEntity
     */
    public void updateWorkTypeDm(WorkTypeDmEntity workTypeDmEntity){
        jpaTemplate.update(workTypeDmEntity);
    }

    /**
     * 删除事项类型
     * @param id
     */
    public void deleteWorkTypeDm(String id){
        jpaTemplate.delete(WorkTypeDmEntity.class,id);
    }

    public void deleteWorkTypeDm(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找事项类型
     * @param id
     * @return
     */
    public WorkTypeDmEntity findWorkTypeDm(String id){
        return jpaTemplate.findOne(WorkTypeDmEntity.class,id);
    }

    /**
    * 查找所有事项类型列表
    * @return
    */
    public List<WorkTypeDmEntity> findAllWorkTypeDm() {
        return jpaTemplate.findAll(WorkTypeDmEntity.class);
    }

    public List<WorkTypeDmEntity> findWorkTypeDmList(List<String> idList) {
        return jpaTemplate.findList(WorkTypeDmEntity.class,idList);
    }

    /**
     * 根据条件查询事项类型列表
     * @param workTypeDmQuery
     * @return
     */
    public List<WorkTypeDmEntity> findWorkTypeDmList(WorkTypeDmQuery workTypeDmQuery) {
        for (Order orderParam : workTypeDmQuery.getOrderParams()) {
            if (orderParam.getName().equals("id")){
                orderParam.setName("wd.id");
            }
        }
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkTypeDmEntity.class, "wd")
                .leftJoin( WorkTypeEntity.class, "wt","wd.workTypeId=wt.id")
                .leftJoin(ProjectEntity.class, "p", "wd.projectId=p.id")
                .eq("wd.projectId",workTypeDmQuery.getProjectId())
                .in("wd.projectId", workTypeDmQuery.getProjectIds())
                .eq("p.productId",workTypeDmQuery.getProductId())
                .in("p.productId", workTypeDmQuery.getProductIds())
                .eq("wd.workTypeId",workTypeDmQuery.getWorkTypeId())
                .eq("wt.grouper", workTypeDmQuery.getGrouper())
                .eq("wt.code", workTypeDmQuery.getCode())
                .in("wt.code", workTypeDmQuery.getCodes())
                .orders(workTypeDmQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkTypeDmEntity.class);
    }

    /**
     * 根据条件按分页查询事项类型列表
     * @param workTypeDmQuery
     * @return
     */
    public Pagination<WorkTypeDmEntity> findWorkTypeDmPage(WorkTypeDmQuery workTypeDmQuery) {
        return jpaTemplate.findPage(workTypeDmQuery,WorkTypeDmEntity.class);
    }
}