package io.tiklab.kanass.product.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.product.entity.ProductModuleEntity;
import io.tiklab.kanass.product.model.ProductModuleQuery;
import io.tiklab.kanass.project.project.entity.ProjectEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductModuleDao {
    private static Logger logger = LoggerFactory.getLogger(ProductModuleDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建模块
     * @param moduleEntity
     * @return
     */
    public String createModule(ProductModuleEntity moduleEntity) {
        return jpaTemplate.save(moduleEntity,String.class);
    }

    /**
     * 更新模块
     * @param moduleEntity
     */
    public void updateModule(ProductModuleEntity moduleEntity){
        jpaTemplate.update(moduleEntity);
    }

    /**
     * 删除模块
     * @param id
     */
    public void deleteModule(String id){
        jpaTemplate.delete(ProductModuleEntity.class,id);
    }

    /**
     * 查找模块
     * @param id
     * @return
     */
    public ProductModuleEntity findModule(String id){
        return jpaTemplate.findOne(ProductModuleEntity.class,id);
    }

    /**
     * 根据模块多个id查找模块列表
     * @param idList
     * @return
     */
    public List<ProductModuleEntity> findModuleList(List<String> idList) {
        return jpaTemplate.findList(ProductModuleEntity.class,idList);
    }

    /**
     * 查找所有模块
     * @return
     */
    public List<ProductModuleEntity> findAllModule() {
        return jpaTemplate.findAll(ProductModuleEntity.class);
    }

    /**
     * 根据条件查找模块
     * @param moduleQuery
     * @return
     */
    public List<ProductModuleEntity> findModuleList(ProductModuleQuery moduleQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProductModuleEntity.class, "pm")
                .eq("pm.productId", moduleQuery.getProductId())
                .like("pm.moduleName", moduleQuery.getModuleName())
                .orders(moduleQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ProductModuleEntity.class);
    }


    /**
     * 根据条件按分页查找模块
     * @param moduleQuery
     * @return
     */
    public Pagination<ProductModuleEntity> findModulePage(ProductModuleQuery moduleQuery){
        QueryCondition queryCondition = QueryBuilders.createQuery(ProductModuleEntity.class)
                .eq("productId", moduleQuery.getProductId())
                .like("moduleName", moduleQuery.getModuleName())
                .orders(moduleQuery.getOrderParams())
                .pagination(moduleQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ProductModuleEntity.class);
    }
}
