package io.tiklab.kanass.product.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.product.entity.ProductFocusEntity;
import io.tiklab.kanass.product.model.ProductFocusQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductFocusDao {
    private static Logger logger = LoggerFactory.getLogger(ProductFocusDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建收藏的项目集
     * @param productFocusEntity
     * @return
     */
    public String createProductFocus(ProductFocusEntity productFocusEntity) {
        return jpaTemplate.save(productFocusEntity,String.class);
    }

    /**
     * 更新收藏的项目集
     * @param productFocusEntity
     */
    public void updateProductFocus(ProductFocusEntity productFocusEntity){
        jpaTemplate.update(productFocusEntity);
    }

    /**
     * 删除收藏的项目集
     * @param id
     */
    public void deleteProductFocus(String id){
        jpaTemplate.delete(ProductFocusEntity.class,id);
    }

    /**
     * 根据条件删除项目集
     * @param deleteCondition
     */
    public void deleteProductFocus(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id 查找收藏项目集
     * @param id
     * @return
     */
    public ProductFocusEntity findProductFocus(String id){
        return jpaTemplate.findOne(ProductFocusEntity.class,id);
    }

    /**
     * 查找所有收藏的项目集
     * @return
     */
    public List<ProductFocusEntity> findAllProductFocus() {
        return jpaTemplate.findAll(ProductFocusEntity.class);
    }

    /**
     * 根据多个ids 查找关注项目集列表
     * @param idList
     * @return
     */
    public List<ProductFocusEntity> findProductFocusList(List<String> idList) {
        return jpaTemplate.findList(ProductFocusEntity.class,idList);
    }

    /**
     * 根据条件查询收藏项目集列表
     * @param productFocusQuery
     * @return
     */
    public List<ProductFocusEntity> findProductFocusList(ProductFocusQuery productFocusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProductFocusEntity.class)
                .eq("masterId", productFocusQuery.getMasterId())
                .eq("productId", productFocusQuery.getProductId())
                .orders(productFocusQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, ProductFocusEntity.class);
    }

    /**
     * 根据条件按分页查询收藏项目集列表
     * @param productFocusQuery
     * @return
     */
    public Pagination<ProductFocusEntity> findProductFocusPage(ProductFocusQuery productFocusQuery) {
        return jpaTemplate.findPage(productFocusQuery,ProductFocusEntity.class);
    }
}
