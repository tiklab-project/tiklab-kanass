package io.tiklab.kanass.workitem.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.workitem.entity.WorkProductPlanEntity;
import io.tiklab.kanass.workitem.model.WorkProductPlanQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户数据操作
 */
@Repository
public class WorkProductPlanDao {

    private static Logger logger = LoggerFactory.getLogger(WorkProductPlanDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项类型
     * @param workProductPlanEntity
     * @return
     */
    public String createWorkProductPlan(WorkProductPlanEntity workProductPlanEntity) {
        return jpaTemplate.save(workProductPlanEntity,String.class);
    }

    /**
     * 更新事项类型
     * @param workProductPlanEntity
     */
    public void updateWorkProductPlan(WorkProductPlanEntity workProductPlanEntity){
        jpaTemplate.update(workProductPlanEntity);
    }

    /**
     * 删除事项类型
     * @param id
     */
    public void deleteWorkProductPlan(String id){
        jpaTemplate.delete(WorkProductPlanEntity.class,id);
    }
    public void deleteWorkProductPlanList(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }
    /**
     * 根据id查找事项类型
     * @param id
     * @return
     */
    public WorkProductPlanEntity findWorkProductPlan(String id){
        return jpaTemplate.findOne(WorkProductPlanEntity.class,id);
    }

    /**
    * 查找所有事项类型
    * @return
    */
    public List<WorkProductPlanEntity> findAllWorkProductPlan() {
        return jpaTemplate.findAll(WorkProductPlanEntity.class);
    }

    public List<WorkProductPlanEntity> findWorkProductPlanList(List<String> idList) {
        return jpaTemplate.findList(WorkProductPlanEntity.class,idList);
    }

    /**
     * 根据条件查询事项类型列表
     * @param workProductPlanQuery
     * @return
     */
    public List<WorkProductPlanEntity> findWorkProductPlanList(WorkProductPlanQuery workProductPlanQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkProductPlanEntity.class)
                .eq("productPlanId", workProductPlanQuery.getProductPlanId())
                .eq("workItemId", workProductPlanQuery.getWorkItemId())
                .orders(workProductPlanQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkProductPlanEntity.class);
    }



    /**
     * 根据条件按分页查询事项类型列表
     * @param workProductPlanQuery
     * @return
     */
    public Pagination<WorkProductPlanEntity> findWorkProductPlanPage(WorkProductPlanQuery workProductPlanQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkProductPlanEntity.class)
                .eq("productPlanId", workProductPlanQuery.getProductPlanId())
                .orders(workProductPlanQuery.getOrderParams())
                .pagination(workProductPlanQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WorkProductPlanEntity.class);
    }
    public void createBatchWorkProductPlan(String valueStrings){
        String sql = "INSERT INTO pmc_work_product_plan (id, work_item_id, product_plan_id) VALUES " + valueStrings + ";";
        jpaTemplate.getJdbcTemplate().execute(sql);
    }

    public List<String> findProductPlanWorkItemIds(String productPlanId){
        String sql = "SELECT work_item_id from pmc_work_product_plan WHERE product_plan_id = '" + productPlanId + "'";
        List<String> workItemIds = jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);
        return workItemIds;
    }

    public List<Map<String, String>> findProductPlanWorkItemNum(String ids) {
        String sql = "select pws.product_plan_id, pws.work_item_id, pwi.work_status_code  from pmc_work_product_plan pws left join pmc_work_item pwi on pwi.id = pws.work_item_id where pwi.work_status_code is not null and pws.product_plan_id in "+ ids;
        List<Map<String, Object>> productPlanIdMap = this.jpaTemplate.getJdbcTemplate().queryForList(sql);
        if (productPlanIdMap.isEmpty()){
            return new ArrayList<>();
        }else {
            List<Map<String, String>> convertedList = productPlanIdMap.stream()
                    .map(rawMap -> {
                        Map<String, String> stringMap = new HashMap<>();
                        rawMap.forEach((k, v) ->
                                stringMap.put(
                                        k != null ? k.toString() : null,
                                        v != null ? v.toString() : null
                                )
                        );
                        return stringMap;
                    })
                    .collect(Collectors.toList());
            return convertedList;
        }
    }

    public void updateWorkItemProductPlan(String workItemId, String productPlanId){
        String sql = "";
        if (productPlanId != null){
            sql = "update pmc_work_item set product_plan_id = '" + productPlanId + "' where id = '" + workItemId + "'";
        }else {
            sql = "update pmc_work_item set product_plan_id = null where id = '" + workItemId + "'";
        }
        jpaTemplate.getJdbcTemplate().execute(sql);
    }
}