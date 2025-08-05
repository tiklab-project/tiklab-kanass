package io.tiklab.kanass.product.dao;

import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.product.entity.ProductEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {
    private static Logger logger = LoggerFactory.getLogger(ProductDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建产品
     * @param productEntity
     * @return
     */
    public String createProduct(ProductEntity productEntity) {
        //设置序号
        Integer max = findMax();
        productEntity.setSort(max+1);
        return jpaTemplate.save(productEntity,String.class);
    }

    /**
     * 更新产品
     * @param productEntity
     */
    public void updateProduct(ProductEntity productEntity){
        jpaTemplate.update(productEntity);
    }

    /**
     * 删除产品
     * @param id
     */
    public void deleteProduct(String id){
        jpaTemplate.delete(ProductEntity.class,id);
    }

    /**
     * 根据条件删除产品
     * @param deleteCondition
     */
    public void deleteProduct(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找产品
     * @param id
     * @return
     */
    public ProductEntity findProduct(String id){
        return jpaTemplate.findOne(ProductEntity.class,id);
    }

    /**
     * 查找所有产品
     * @return
     */
    public List<ProductEntity> findAllProduct() {

        return jpaTemplate.findAll(ProductEntity.class);
    }

    /**
     * 根据多个id,查找产品列表
     * @param idList
     * @return
     */
    public List<ProductEntity> findProductList(List<String> idList) {
        return jpaTemplate.findList(ProductEntity.class,idList);
    }

    /**
     * 查找序号最大值
     * @param
     * @return
     */
    Integer findMax(){
        QueryCondition queryCondition = QueryBuilders.createQuery(ProductEntity.class)
                .max("sort")
                .get();
        Integer max = jpaTemplate.findObject(queryCondition,Integer.class);
        if(max == null){
            return 0;
        }
        return max;
    }
}
