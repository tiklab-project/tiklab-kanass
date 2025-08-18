package io.tiklab.kanass.product.plan.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.product.plan.entity.ProductPlanFocusEntity;
import io.tiklab.kanass.product.plan.model.ProductPlanFocusQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品计划收藏数据访问
 */
@Repository
public class ProductPlanFocusDao {

    private static Logger logger = LoggerFactory.getLogger(ProductPlanFocusDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建产品计划收藏
     * @param productPlanFocusEntity
     * @return
     */
    public String createProductPlanFocus(ProductPlanFocusEntity productPlanFocusEntity) {
        return jpaTemplate.save(productPlanFocusEntity,String.class);
    }

    /**
     * 更新收藏的产品计划
     * @param productPlanFocusEntity
     */
    public void updateProductPlanFocus(ProductPlanFocusEntity productPlanFocusEntity){
        jpaTemplate.update(productPlanFocusEntity);
    }

    /**
     * 删除收藏的产品计划
     * @param id
     */
    public void deleteProductPlanFocus(String id){
        jpaTemplate.delete(ProductPlanFocusEntity.class,id);
    }

    /**
     * 根据添加删除收藏的产品计划
     * @param deleteCondition
     */
    public void deleteProductPlanFocus(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找收藏的产品计划
     * @param id
     * @return
     */
    public ProductPlanFocusEntity findProductPlanFocus(String id){
        return jpaTemplate.findOne(ProductPlanFocusEntity.class,id);
    }

    /**
    * 查找所有收藏产品计划
    * @return
    */
    public List<ProductPlanFocusEntity> findAllProductPlanFocus() {
        return jpaTemplate.findAll(ProductPlanFocusEntity.class);
    }

    /**
     * 根据多个产品计划id,查找收藏的产品计划
     * @param idList
     * @return
     */
    public List<ProductPlanFocusEntity> findProductPlanFocusList(List<String> idList) {
        return jpaTemplate.findList(ProductPlanFocusEntity.class,idList);
    }

    /**
     * 根据条件查询收藏的产品计划列表
     * @param productPlanFocusQuery
     * @return
     */
    public List<ProductPlanFocusEntity> findProductPlanFocusList(ProductPlanFocusQuery productPlanFocusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProductPlanFocusEntity.class)
                .eq("productId", productPlanFocusQuery.getProductId())
                .eq("masterId", productPlanFocusQuery.getMasterId())
                .eq("productPlanId", productPlanFocusQuery.getProductPlanId())
                .orders(productPlanFocusQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ProductPlanFocusEntity.class);
    }

    /**
     * 根据条件按分页查询收藏的产品计划列表
     * @param productPlanFocusQuery
     * @return
     */
    public Pagination<ProductPlanFocusEntity> findProductPlanFocusPage(ProductPlanFocusQuery productPlanFocusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProductPlanFocusEntity.class)
                .eq("productId", productPlanFocusQuery.getProductId())
                .eq("masterId", productPlanFocusQuery.getMasterId())
                .orders(productPlanFocusQuery.getOrderParams())
                .pagination(productPlanFocusQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,ProductPlanFocusEntity.class);
    }

    public List<String> findFocusProductPlanIds(String masterId) {
        String sql = "select product_plan_id from pmc_product_plan_focus sf where sf.master_id = '"+ masterId + "'";
        List<String> versionIds = this.jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);
        return versionIds;
    }
}