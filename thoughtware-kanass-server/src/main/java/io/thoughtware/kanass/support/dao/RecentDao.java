package io.thoughtware.kanass.support.dao;

import io.thoughtware.kanass.support.entity.RecentEntity;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.kanass.support.model.RecentQuery;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 最近访问的数据访问
 */
@Repository
public class RecentDao{

    private static Logger logger = LoggerFactory.getLogger(RecentDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建最近访问的
     * @param recentEntity
     * @return
     */
    public String createRecent(RecentEntity recentEntity) {

        return jpaTemplate.save(recentEntity,String.class);
    }

    /**
     * 更新最近访问的
     * @param recentEntity
     */
    public void updateRecent(RecentEntity recentEntity){
        jpaTemplate.update(recentEntity);
    }

    /**
     * 删除最近访问的
     * @param id
     */
    public void deleteRecent(String id){
        jpaTemplate.delete(RecentEntity.class,id);
    }

    public void deleteRecentByIds(String ids){
        String sql = "DELETE FROM pmc_recent where id in " + ids ;
        int update = jpaTemplate.getJdbcTemplate().update(sql);
        if(update >= 0){
            logger.info("删除多于的数据成功");
        }else {
            logger.info("删除多于的数据失败");
        }
    }

    public void deleteRecent(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id 查找最近访问的
     * @param id
     * @return
     */
    public RecentEntity findRecent(String id){
        return jpaTemplate.findOne(RecentEntity.class,id);
    }

    /**
    * 查找所有最近访问的
    * @return
    */
    public List<RecentEntity> findAllRecent() {
        return jpaTemplate.findAll(RecentEntity.class);
    }

    /**
     * 根据多个id, 查找最近访问的
     * @param idList
     * @return
     */
    public List<RecentEntity> findRecentList(List<String> idList) {
        return jpaTemplate.findList(RecentEntity.class,idList);
    }

    /**
     * 查询最近访问列表
     * @param recentQuery
     * @return
     */
    public List<RecentEntity> findRecentList(RecentQuery recentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RecentEntity.class)
                .eq("modelId", recentQuery.getModelId())
                .eq("masterId", recentQuery.getMasterId())
                .eq("model", recentQuery.getModel())
                .eq("projectId", recentQuery.getProjectId())
                .pagination(recentQuery.getPageParam())
                .orders(recentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,RecentEntity.class);
    }

    /**
     * 按分页查询最近访问的
     * @param recentQuery
     * @return
     */
    public Pagination<RecentEntity> findRecentPage(RecentQuery recentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(RecentEntity.class)
                .eq("modelId", recentQuery.getModelId())
                .eq("model", recentQuery.getModel())
                .eq("projectId", recentQuery.getProjectId())
                .eq("masterId", recentQuery.getMasterId())
                .orders(recentQuery.getOrderParams())
                .pagination(recentQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,RecentEntity.class);
    }
}