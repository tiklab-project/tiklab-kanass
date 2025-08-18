package io.tiklab.kanass.product.plan.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.product.plan.entity.ProductPlanStateEntity;
import io.tiklab.kanass.product.plan.model.ProductPlanStateQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品计划状态数据访问
 */
@Repository
public class ProductPlanStateDao {

    private static Logger logger = LoggerFactory.getLogger(ProductPlanStateDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建产品计划状态
     * @param productPlanStateEntity
     * @return
     */
    public String createProductPlanState(ProductPlanStateEntity productPlanStateEntity) {
        return jpaTemplate.save(productPlanStateEntity,String.class);
    }

    /**
     * 更新产品计划状态
     * @param productPlanStateEntity
     */
    public void updateProductPlanState(ProductPlanStateEntity productPlanStateEntity){
        jpaTemplate.update(productPlanStateEntity);
    }

    /**
     * 删除产品计划状态
     * @param id
     */
    public void deleteProductPlanState(String id){
        jpaTemplate.delete(ProductPlanStateEntity.class,id);
    }

    public void deleteProductPlanState(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找产品计划状态
     * @param id
     * @return
     */
    public ProductPlanStateEntity findProductPlanState(String id){
        return jpaTemplate.findOne(ProductPlanStateEntity.class,id);
    }

    /**
    * 查找所有产品计划状态
    * @return
    */
    public List<ProductPlanStateEntity> findAllProductPlanState() {
        return jpaTemplate.findAll(ProductPlanStateEntity.class);
    }

    /**
     * 根据ids 查找产品计划状态列表
     * @param idList
     * @return
     */
    public List<ProductPlanStateEntity> findProductPlanStateList(List<String> idList) {
        return jpaTemplate.findList(ProductPlanStateEntity.class,idList);
    }

    /**
     * 根据条件查询产品计划状态列表
     * @param productPlanStateQuery
     * @return
     */
    public List<ProductPlanStateEntity> findProductPlanStateList(ProductPlanStateQuery productPlanStateQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProductPlanStateEntity.class)
                .orders(productPlanStateQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ProductPlanStateEntity.class);
    }

    /**
     * 根据条件按分页查询产品计划状态列表
     * @param productPlanStateQuery
     * @return
     */
    public Pagination<ProductPlanStateEntity> findProductPlanStatePage(ProductPlanStateQuery productPlanStateQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProductPlanStateEntity.class)
                .orders(productPlanStateQuery.getOrderParams())
                .pagination(productPlanStateQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ProductPlanStateEntity.class);
    }
}