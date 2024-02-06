package io.thoughtware.kanass.workitem.dao;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.kanass.workitem.entity.WorkSprintEntity;
import io.thoughtware.kanass.workitem.model.WorkSprintQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class WorkSprintDao {

    private static Logger logger = LoggerFactory.getLogger(WorkSprintDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项类型
     * @param workSprintEntity
     * @return
     */
    public String createWorkSprint(WorkSprintEntity workSprintEntity) {
        return jpaTemplate.save(workSprintEntity,String.class);
    }

    /**
     * 更新事项类型
     * @param workSprintEntity
     */
    public void updateWorkSprint(WorkSprintEntity workSprintEntity){
        jpaTemplate.update(workSprintEntity);
    }

    /**
     * 删除事项类型
     * @param id
     */
    public void deleteWorkSprint(String id){
        jpaTemplate.delete(WorkSprintEntity.class,id);
    }

    /**
     * 根据id查找事项类型
     * @param id
     * @return
     */
    public WorkSprintEntity findWorkSprint(String id){
        return jpaTemplate.findOne(WorkSprintEntity.class,id);
    }

    /**
    * 查找所有事项类型
    * @return
    */
    public List<WorkSprintEntity> findAllWorkSprint() {
        return jpaTemplate.findAll(WorkSprintEntity.class);
    }

    public List<WorkSprintEntity> findWorkSprintList(List<String> idList) {
        return jpaTemplate.findList(WorkSprintEntity.class,idList);
    }

    /**
     * 根据条件查询事项类型列表
     * @param workSprintQuery
     * @return
     */
    public List<WorkSprintEntity> findWorkSprintList(WorkSprintQuery workSprintQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkSprintEntity.class)
                .eq("sprintId", workSprintQuery.getSprintId())
                .eq("workItemId", workSprintQuery.getWorkItemId())
                .orders(workSprintQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkSprintEntity.class);
    }



    /**
     * 根据条件按分页查询事项类型列表
     * @param workSprintQuery
     * @return
     */
    public Pagination<WorkSprintEntity> findWorkSprintPage(WorkSprintQuery workSprintQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkSprintEntity.class)
                .eq("sprintId", workSprintQuery.getSprintId())
                .orders(workSprintQuery.getOrderParams())
                .pagination(workSprintQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WorkSprintEntity.class);
    }
    public void createBatchWorkSprint(String valueStrings){
        String sql = "INSERT INTO pmc_work_sprint (id, work_item_id, sprint_id) VALUES " + valueStrings + ";";
        jpaTemplate.getJdbcTemplate().execute(sql);
    }

    public List<String> findSprintWorkItemIds(String sprintId){
        String sql = "SELECT work_item_id from pmc_work_sprint WHERE sprint_id = '" + sprintId + "'";
        List<String> workItemIds = jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);
        return workItemIds;
    }

}