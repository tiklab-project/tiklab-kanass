package io.tiklab.kanass.product.product.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.OrQueryCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.OrQueryBuilders;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.product.product.entity.ProductEntity;
import io.tiklab.kanass.product.product.entity.ProductFocusEntity;
import io.tiklab.kanass.product.product.model.ProductQuery;
import io.tiklab.kanass.project.project.entity.ProjectEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 根据条件查询产品列表
     * @param productQuery
     * @return
     */
    public List<ProductEntity> findProductList(ProductQuery productQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProductEntity.class)
                .like("name", productQuery.getName())
                .eq("master", productQuery.getMaster())
                .eq("id", productQuery.getProductId())
                .in("id", productQuery.getProductIds())
                .eq("productLimits", productQuery.getProductLimits())
                .orders(productQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ProductEntity.class);
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

    /**
     * 查询产品下面的项目数量
     * @param
     * @return
     */
    public Integer findProjectNum(String productId){
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectEntity.class)
                .eq("productId",productId)
                .count("productId")
                .get();
        Integer num = jpaTemplate.findObject(queryCondition, Integer.class);
        return  num;
    }

    /**
     * 根据条件按分页查询产品列表
     * @param productQuery
     * @return
     */
    public Pagination<ProductEntity> findProductPage(ProductQuery productQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProductEntity.class, "p")
                .leftJoin(ProductFocusEntity.class,"pf", "pf.productId=p.id")
                .eq("p.productLimits", "0")
                .like("p.name", productQuery.getName())
                .eq("p.master", productQuery.getMaster())
                .eq("pf.masterId", productQuery.getFocusMasterId())
                .orders(productQuery.getOrderParams())
                .pagination(productQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ProductEntity.class);
    }

    /**
     * 查找最近查看的项目集列表
     * @param productQuery
     * @return
     */
    public List<ProductEntity> findRecentProductList(ProductQuery productQuery){
        String master = productQuery.getRecentMasterId();
        String sql = "select pp.* from pmc_product pp left join pmc_recent rc on pp.id = rc.model_id where rc.model='product' and rc.master_id= '" +
                master + "' order by rc.recent_time desc";
        List<ProductEntity> productEntityList = jpaTemplate.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(ProductEntity.class));

        return productEntityList;
    }

    public Map<String, Integer> findProductCount(ProductQuery productQuery){
        Map<String, Integer> productCount = new HashMap<>();
        String userId = productQuery.getCreator();
        String[] productIds = productQuery.getProductIds();
        String s = new String();
        s =  "(";
        for(String productId:productIds){
            s = s.concat("'" + productId + "',");
        }
        s= s.substring(0, s.length() - 1);
        s = s.concat(")");
        String sql1 = "";
        if(StringUtils.isNotBlank(productQuery.getName())){
            sql1 = "select count(id) as total from pmc_product where ( product_limits = '0' or id in " + s + ") and name like '%" + productQuery.getName() + "%'";
        }else {
            sql1 = "select count(id) as total from pmc_product where ( product_limits = '0' or id in " + s + ")";
        }
        Integer total = jpaTemplate.getJdbcTemplate().queryForObject(sql1, new Object[]{}, Integer.class);
        productCount.put("total", total);

        String sql2 = "SELECT count(1) as total from pmc_product where creator = '" + userId + "'";
        if (StringUtils.isNotBlank(productQuery.getName())){
            sql2 = sql2.concat(" and name like '%" + productQuery.getName() + "%'");
        }
        Integer myCreated = jpaTemplate.getJdbcTemplate().queryForObject(sql2, new Object[]{}, Integer.class);
        productCount.put("myCreated", myCreated);

        String sql3 = "SELECT count(1) as total from pmc_product_focus psf left join pmc_product ps on ps.id = psf.product_id " +
                "where psf.master_id = '" + userId + "'";
        if (StringUtils.isNotBlank(productQuery.getName())){
            sql3 = sql3.concat(" and ps.name like '%" + productQuery.getName() + "%'");
        }
        Integer myFocus = jpaTemplate.getJdbcTemplate().queryForObject(sql3, new Object[]{}, Integer.class);
        productCount.put("myFocus", myFocus);
        return productCount;

    }

    public List<ProductEntity> findJoinProductList(ProductQuery productQuery) {

        QueryBuilders queryBuilders = QueryBuilders.createQuery(ProductEntity.class);
        OrQueryCondition orQueryBuildCondition = OrQueryBuilders.instance()
                .eq("productLimits","0")
                .in("id",productQuery.getProductIds())
                .get();
        QueryCondition queryCondition = queryBuilders.or(orQueryBuildCondition)
                .like("name", productQuery.getName())
                .eq("master", productQuery.getMaster())
                .orders(productQuery.getOrderParams())
                .pagination(productQuery.getPageParam())
                .get();
        return jpaTemplate.findList(queryCondition, ProductEntity.class);
    }
}
