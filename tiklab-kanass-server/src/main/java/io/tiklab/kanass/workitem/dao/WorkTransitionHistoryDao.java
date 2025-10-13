package io.tiklab.kanass.workitem.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.workitem.entity.WorkTransitionHistoryEntity;
import io.tiklab.kanass.workitem.model.WorkTransitionHistoryQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkTransitionHistoryDao {
    private static Logger logger = LoggerFactory.getLogger(WorkTransitionHistoryDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项流转历史
     * @param workTransitionHistoryEntity
     * @return
     */
    public String createWorkTransitionHistory(WorkTransitionHistoryEntity workTransitionHistoryEntity) {
        return jpaTemplate.save(workTransitionHistoryEntity,String.class);
    }

    /**
     * 更新事项流转历史
     * @param workTransitionHistoryEntity
     */
    public void updateWorkTransitionHistory(WorkTransitionHistoryEntity workTransitionHistoryEntity){
        jpaTemplate.update(workTransitionHistoryEntity);
    }

    /**
     * 通过id删除事项流转历史
     * @param id
     */
    public void deleteWorkTransitionHistory(String id){
        jpaTemplate.delete(WorkTransitionHistoryEntity.class,id);
    }

    public void deleteWorkTransitionHistory(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找事项流转历史
     * @param id
     * @return
     */
    public WorkTransitionHistoryEntity findWorkTransitionHistory(String id){
        return jpaTemplate.findOne(WorkTransitionHistoryEntity.class,id);
    }

    /**
     * 查找所有事项流转历史
     * @return
     */
    public List<WorkTransitionHistoryEntity> findAllWorkTransitionHistory() {
        return jpaTemplate.findAll(WorkTransitionHistoryEntity.class);
    }

    /**
     * 根据ids 查找事项流转历史
     * @param idList
     * @return
     */
    public List<WorkTransitionHistoryEntity> findWorkTransitionHistoryList(List<String> idList) {
        return jpaTemplate.findList(WorkTransitionHistoryEntity.class,idList);
    }

    /**
     * 根据条件查询事项流转历史列表
     * @param workTransitionHistoryQuery
     * @return
     */
    public List<WorkTransitionHistoryEntity> findWorkTransitionHistoryList(WorkTransitionHistoryQuery workTransitionHistoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkTransitionHistoryEntity.class)
                .eq("workItemId", workTransitionHistoryQuery.getWorkItemId())
                .orders(workTransitionHistoryQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkTransitionHistoryEntity.class);
    }

    /**
     * 根据条件按分页查询事项流转历史列表
     * @param workTransitionHistoryQuery
     * @return
     */
    public Pagination<WorkTransitionHistoryEntity> findWorkTransitionHistoryPage(WorkTransitionHistoryQuery workTransitionHistoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkTransitionHistoryEntity.class)
                .eq("workItemId", workTransitionHistoryQuery.getWorkItemId())
                .orders(workTransitionHistoryQuery.getOrderParams())
                .pagination(workTransitionHistoryQuery.getPageParam())
                .get();

        return jpaTemplate.findPage(queryCondition, WorkTransitionHistoryEntity.class);
    }
}
