package io.tiklab.kanass.product.plan.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.product.plan.entity.ProductPlanEntity;
import io.tiklab.kanass.product.plan.entity.ProductPlanFocusEntity;
import io.tiklab.kanass.product.plan.model.ProductPlanQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品计划数据访问
 */
@Repository
public class ProductPlanDao {

    private static Logger logger = LoggerFactory.getLogger(ProductPlanDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建产品计划
     * @param productPlanEntity
     * @return
     */
    public String createProductPlan(ProductPlanEntity productPlanEntity) {
        return jpaTemplate.save(productPlanEntity,String.class);
    }

    /**
     * 更新产品计划
     * @param productPlanEntity
     */
    public void updateProductPlan(ProductPlanEntity productPlanEntity){
        jpaTemplate.update(productPlanEntity);
    }

    /**
     * 删除产品计划
     * @param id
     */
    public void deleteProductPlan(String id){
        jpaTemplate.delete(ProductPlanEntity.class,id);
    }

    public void deleteProductPlanFocus(String productPlanId){
        // 删除产品计划与事项的关系
        String sql = "UPDATE pmc_work_item SET product_plan_id = NULL WHERE product_plan_id = '" + productPlanId + "'";
        jpaTemplate.getJdbcTemplate().execute(sql);
          // 删除关注的产品计划
        sql = "DELETE FROM pmc_product_plan_focus where product_plan_id = '" + productPlanId + "'";
        int update = jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除关注的产品计划成功");
        }else {
            logger.info("删除关注的产品计划失败");
        }

        // 删除产品计划燃尽图数据
        sql = "DELETE FROM pmc_product_plan_burndowm where product_plan_id = '" + productPlanId + "'";
        update = jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除产品计划燃尽图数据成功");
        }else {
            logger.info("删除产品计划燃尽图数据失败");
        }

        // 删除最近点击的产品计划
        sql = "DELETE FROM pmc_recent where model_id = '" + productPlanId + "'";
        update = jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除产品计划燃尽图数据成功");
        }else {
            logger.info("删除产品计划燃尽图数据失败");
        }


    }
    /**
     * 根据产品计划id查找产品计划
     * @param id
     * @return
     */
    public ProductPlanEntity findProductPlan(String id){
        return jpaTemplate.findOne(ProductPlanEntity.class,id);
    }

    /**
    * 查找所有产品计划列表
    * @return
    */
    public List<ProductPlanEntity> findAllProductPlan() {
        return jpaTemplate.findAll(ProductPlanEntity.class);
    }

    /**
     * 根据多个产品计划id,查找产品计划列表
     * @param idList
     * @return
     */
    public List<ProductPlanEntity> findProductPlanList(List<String> idList) {
        return jpaTemplate.findList(ProductPlanEntity.class,idList);
    }

    /**
     * 根据条件查找产品计划列表
     * @param productPlanQuery
     * @return
     */
    public List<ProductPlanEntity> findProductPlanList(ProductPlanQuery productPlanQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(ProductPlanEntity.class, "sp")
                .eq("sp.productId", productPlanQuery.getProductId())
                .eq("sp.master", productPlanQuery.getMaster())
                .like("sp.productPlanName", productPlanQuery.getProductPlanName())
                .eq("sp.productPlanStateId", productPlanQuery.getProductPlanStateId())
                .in("sp.productPlanStateId", productPlanQuery.getProductPlanStateIds())
                .eq("sp.builder", productPlanQuery.getBuilderId())
                .orders(productPlanQuery.getOrderParams());

        if(productPlanQuery.getFollowersId() != null){
            queryBuilders = queryBuilders.leftJoin(ProductPlanFocusEntity.class, "sf", "sf.productPlanId=sp.id")
                    .eq("sf.masterId", productPlanQuery.getFollowersId());

        }
        QueryCondition queryCondition = queryBuilders.get();
        return jpaTemplate.findList(queryCondition, ProductPlanEntity.class);
    }

    public List<ProductPlanEntity> findSelectProductPlanList(ProductPlanQuery productPlanQuery) {
        String currentProductPlanId = productPlanQuery.getCurrentProductPlanId();
        String productId = productPlanQuery.getProductId();
        String sql = "SELECT * FROM pmc_product_plan WHERE id != '" + currentProductPlanId + "' and " +
                "product_plan_state_id != '222222' and product_id = '" + productId + "' order by start_time desc";
        List<ProductPlanEntity> productPlanEntityList = this.jpaTemplate.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(ProductPlanEntity.class));
        return productPlanEntityList;
    }

    /**
     * 根据条件按照分页查找产品计划
     * @param productPlanQuery
     * @return
     */
    public Pagination<ProductPlanEntity> findProductPlanPage(ProductPlanQuery productPlanQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(ProductPlanEntity.class, "sp")
                .eq("sp.productId", productPlanQuery.getProductId())
                .eq("sp.master", productPlanQuery.getMaster())
                .like("sp.productPlanName", productPlanQuery.getProductPlanName())
                .eq("sp.productPlanStateId", productPlanQuery.getProductPlanStateId())
                .in("sp.productPlanStateId", productPlanQuery.getProductPlanStateIds())
                .eq("sp.builder", productPlanQuery.getBuilderId())
                .orders(productPlanQuery.getOrderParams())
                .pagination(productPlanQuery.getPageParam());

        if(productPlanQuery.getFollowersId() != null){
            queryBuilders = queryBuilders.leftJoin(ProductPlanFocusEntity.class, "sf", "sf.productPlanId=sp.id")
                    .eq("sf.masterId", productPlanQuery.getFollowersId());
        }
        QueryCondition queryCondition = queryBuilders.get();
        return jpaTemplate.findPage(queryCondition, ProductPlanEntity.class);
    }

    /**
     * 根据条件查找我收藏的产品计划
     * @param productPlanQuery
     * @return
     */
    public List<ProductPlanEntity> findFocusProductPlanList(ProductPlanQuery productPlanQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProductPlanEntity.class, "sp")
                .leftJoin(ProductPlanFocusEntity.class,"sf","sp.id=sf.productPlanId")
                .eq("sf.masterId", productPlanQuery.getMaster())
                .eq("sf.productId", productPlanQuery.getProductId())
                .get();
        return jpaTemplate.findList(queryCondition,ProductPlanEntity.class);
    }

    /**
     * 查找进行中的产品计划
     * @return
     */
    public List<ProductPlanEntity> findProgressProductPlan() {
        List<ProductPlanEntity> ProductPlanEntityList = null;
        String sql = "select * from pmc_product_plan s";
        sql = sql.concat(" where s.product_plan_state_id = 111111");
        ProductPlanEntityList = this.jpaTemplate.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(ProductPlanEntity.class));

        return ProductPlanEntityList;
    }

    public List<ProductPlanEntity> findWorkProductPlanList(String workId){
        String sql = "select sr.* from pmc_product_plan sr LEFT JOIN pmc_work_product_plan " +
                "ws on sr.id = ws.product_plan_id WHERE ws.work_item_id = '" + workId  + "'";
        List ProductPlanEntityList = this.jpaTemplate.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(ProductPlanEntity.class));
        return  ProductPlanEntityList;
    }

    public Map<String, Integer> findProductPlanCount(ProductPlanQuery productPlanQuery) {
        String userId = productPlanQuery.getBuilderId();
        String productId = productPlanQuery.getProductId();
        Map<String, Integer>  countMap = new HashMap<>();

        String sql1 = "select count(*) as count from pmc_product_plan where product_id = '" + productId + "' ";
        if (StringUtils.isNotBlank(productPlanQuery.getProductPlanName())){
            sql1 = sql1.concat(" and product_plan_name like '%" + productPlanQuery.getProductPlanName() + "%'");
        }
        if (productPlanQuery.getProductPlanStateIds() != null && productPlanQuery.getProductPlanStateIds().length != 0){
            sql1 = sql1.concat(" and product_plan_state_id in ('" + StringUtils.join(productPlanQuery.getProductPlanStateIds(), "','") + "')");
        }
        Integer total = jpaTemplate.getJdbcTemplate().queryForObject(sql1, new Object[]{}, Integer.class);
        countMap.put("total", total);

        String sql2 = "select count(*) as count from pmc_product_plan where product_id = '" + productId + "' and builder = '" + userId + "' ";
        if (StringUtils.isNotBlank(productPlanQuery.getProductPlanName())){
            sql2 = sql2.concat(" and product_plan_name like '%" + productPlanQuery.getProductPlanName() + "%'");
        }
        if (productPlanQuery.getProductPlanStateIds() != null && productPlanQuery.getProductPlanStateIds().length != 0){
            sql2 = sql2.concat(" and product_plan_state_id in ('" + StringUtils.join(productPlanQuery.getProductPlanStateIds(), "','") + "')");
        }
        Integer myCreated = jpaTemplate.getJdbcTemplate().queryForObject(sql2, new Object[]{}, Integer.class);
        countMap.put("myCreated", myCreated);

        String sql3 = "select count(*) as count from pmc_product_plan_focus psf left join pmc_product_plan ps on ps.id = psf.product_plan_id " +
                " where ps.product_id = '" + productId + "' and ps.builder = '" + userId + "' ";
        if (StringUtils.isNotBlank(productPlanQuery.getProductPlanName())){
            sql2 = sql2.concat(" and ps.product_plan_name like '%" + productPlanQuery.getProductPlanName() + "%'");
        }
        if (productPlanQuery.getProductPlanStateIds() != null && productPlanQuery.getProductPlanStateIds().length != 0){
            sql2 = sql2.concat(" and ps.product_plan_state_id in ('" + StringUtils.join(productPlanQuery.getProductPlanStateIds(), "','") + "')");
        }
        Integer myFocus = jpaTemplate.getJdbcTemplate().queryForObject(sql3, new Object[]{}, Integer.class);
        countMap.put("myFocus", myFocus);

        return countMap;
    }
}