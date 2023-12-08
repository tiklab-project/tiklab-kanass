package io.thoughtware.kanass.project.plan.dao;

import io.thoughtware.kanass.project.plan.entity.PlanEntity;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.kanass.project.plan.model.PlanQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 计划数据访问
 */
@Repository
public class PlanDao{

    private static Logger logger = LoggerFactory.getLogger(PlanDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建计划
     * @param planEntity
     * @return
     */
    public String createPlan(PlanEntity planEntity) {
        return jpaTemplate.save(planEntity,String.class);
    }

    /**
     * 更新计划
     * @param planEntity
     */
    public void updatePlan(PlanEntity planEntity){
        jpaTemplate.update(planEntity);
    }

    /**
     * 删除计划
     * @param id
     */
    public void deletePlan(String id){
        jpaTemplate.delete(PlanEntity.class,id);
    }

    public void deletePlan(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找计划
     * @param id
     * @return
     */
    public PlanEntity findPlan(String id){
        return jpaTemplate.findOne(PlanEntity.class,id);
    }

    /**
    * 查找所有计划
    * @return
    */
    public List<PlanEntity> findAllPlan() {
        return jpaTemplate.findAll(PlanEntity.class);
    }

    /**
     * 根据多个id查找计划列表
     * @param idList
     * @return
     */
    public List<PlanEntity> findPlanList(List<String> idList) {
        return jpaTemplate.findList(PlanEntity.class,idList);
    }

    /**
     * 根据条件查找计划列表
     * @param planQuery
     * @return
     */
    public List<PlanEntity> findPlanList(PlanQuery planQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PlanEntity.class)
                .eq("planId", planQuery.getParentId())
                .like("planName", planQuery.getPlanName())
                .eq("projectId", planQuery.getProjectId())
                .isNull("parentId")
                .eq("parentId", planQuery.getParentId())
                .in("parentId", planQuery.getParentIdIn())
                .orders(planQuery.getOrderParams())
                .pagination(planQuery.getPageParam())
                .get();
        return jpaTemplate.findList(queryCondition,PlanEntity.class);
    }

    /**
     * 根据添加按分页查找计划列表
     * @param planQuery
     * @return
     */
    public Pagination<PlanEntity> findPlanPage(PlanQuery planQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(PlanEntity.class)
                .eq("planId", planQuery.getParentId())
                .like("planName", planQuery.getPlanName())
                .eq("projectId", planQuery.getProjectId())
                .isNull("parentId")
                .eq("parentId", planQuery.getParentId())
                .in("parentId", planQuery.getParentIdIn())
                .orders(planQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition,PlanEntity.class);
    }
}