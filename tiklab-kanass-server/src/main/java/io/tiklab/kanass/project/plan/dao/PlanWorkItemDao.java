package io.tiklab.kanass.project.plan.dao;

import io.tiklab.kanass.project.plan.entity.PlanWorkItemEntity;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.project.plan.model.PlanWorkItemQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 计划事项关联模型
 */
@Repository
public class PlanWorkItemDao{

    private static Logger logger = LoggerFactory.getLogger(PlanWorkItemDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建计划事项关联
     * @param planWorkItemEntity
     * @return
     */
    public String createPlanWorkItem(PlanWorkItemEntity planWorkItemEntity) {
        return jpaTemplate.save(planWorkItemEntity,String.class);
    }

    /**
     * 更新计划事项关联关系
     * @param planWorkItemEntity
     */
    public void updatePlanWorkItem(PlanWorkItemEntity planWorkItemEntity){
        jpaTemplate.update(planWorkItemEntity);
    }

    /**
     * 删除关联关系
     * @param id
     */
    public void deletePlanWorkItem(String id){
        jpaTemplate.delete(PlanWorkItemEntity.class,id);
    }

    /**
     * 根据条件删除符合条件的所有计划
     * @param deleteCondition
     */
    public void deletePlanWorkItem(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找计划事项关联关系
     * @param id
     * @return
     */
    public PlanWorkItemEntity findPlanWorkItem(String id){
        return jpaTemplate.findOne(PlanWorkItemEntity.class,id);
    }

    /**
    * 查找所有计划事项关联关系
    * @return
    */
    public List<PlanWorkItemEntity> findAllPlanWorkItem() {
        return jpaTemplate.findAll(PlanWorkItemEntity.class);
    }

    /**
     * 根据多个id查找计划事项关联列表
     * @param idList
     * @return
     */
    public List<PlanWorkItemEntity> findPlanWorkItemList(List<String> idList) {
        return jpaTemplate.findList(PlanWorkItemEntity.class,idList);
    }

    /**
     * 根据条件查找计划事项关联列表
     * @param planWorkItemQuery
     * @return
     */
    public List<PlanWorkItemEntity> findPlanWorkItemList(PlanWorkItemQuery planWorkItemQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PlanWorkItemEntity.class)
                .eq("planId", planWorkItemQuery.getPlanId())
                .orders(planWorkItemQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,PlanWorkItemEntity.class);
    }

    /**
     * 根据条件分页查找计划事项关联列表
     * @param planWorkItemQuery
     * @return
     */
    public Pagination<PlanWorkItemEntity> findPlanWorkItemPage(PlanWorkItemQuery planWorkItemQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PlanWorkItemEntity.class)
                .eq("planId", planWorkItemQuery.getPlanId())
                .orders(planWorkItemQuery.getOrderParams())
                .pagination(planWorkItemQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,PlanWorkItemEntity.class);
    }
}