package io.thoughtware.kanass.home.insight.dao;

import io.thoughtware.kanass.home.insight.entity.InsightEntity;
import io.thoughtware.kanass.home.insight.entity.InsightFocusEntity;
import io.thoughtware.kanass.home.insight.model.InsightQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.kanass.support.entity.RecentEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * 仪表盘数据访问
 */
@Repository
public class InsightDao{

    private static Logger logger = LoggerFactory.getLogger(InsightDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建仪表盘
     * @param insightEntity
     * @return
     */
    public String createInsight(InsightEntity insightEntity) {
        insightEntity.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        return jpaTemplate.save(insightEntity,String.class);
    }

    /**
     * 更新仪表盘
     * @param insightEntity
     */
    public void updateInsight(InsightEntity insightEntity){
        jpaTemplate.update(insightEntity);
    }

    /**
     * 删除仪表盘
     * @param id
     */
    public void deleteInsight(String id){
        jpaTemplate.delete(InsightEntity.class,id);
    }

    public void deleteInsight(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找仪表盘
     * @param id
     * @return
     */
    public InsightEntity findInsight(String id){
        return jpaTemplate.findOne(InsightEntity.class,id);
    }

    /**
    * 查找所有仪表盘
    * @return
    */
    public List<InsightEntity> findAllInsight() {
        return jpaTemplate.findAll(InsightEntity.class);
    }

    public List<InsightEntity> findInsightList(List<String> idList) {

        return jpaTemplate.findList(InsightEntity.class,idList);
    }

    /**
     * 根据条件查找仪表盘列表
     * @param insightQuery
     * @return
     */
    public List<InsightEntity> findInsightList(InsightQuery insightQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(InsightEntity.class)
                .like("insightName", insightQuery.getInsightName())
                .eq("master", insightQuery.getMasterId())
                .orders(insightQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,InsightEntity.class);
    }

    /**
     * 根据条件按照分页查找仪表盘
     * @param insightQuery
     * @return
     */
    public Pagination<InsightEntity> findInsightPage(InsightQuery insightQuery) {
        return jpaTemplate.findPage(insightQuery,InsightEntity.class);
    }

    /**
     * 查找我最近查看的项目
     * @param insightQuery
     * @return
     */
    public List<InsightEntity> findRecentInsightList(InsightQuery insightQuery){
        QueryCondition queryBuilders =  QueryBuilders.createQuery(InsightEntity.class, "insi")
                .leftJoin(RecentEntity.class,"rc","rc.modelId=insi.id")
                .eq("rc.model", "insight")
                .like("insi.insightName", insightQuery.getInsightName())
                .eq("insi.id", insightQuery.getInsightId())
                .eq("rc.masterId", insightQuery.getMasterId())
                .orders(insightQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryBuilders, InsightEntity.class);
    }

    public List<InsightEntity> findFocusInsightList(InsightQuery insightQuery){
        QueryCondition queryBuilders =  QueryBuilders.createQuery(InsightEntity.class, "insi")
                .leftJoin(InsightFocusEntity.class,"insif","insif.insightId=insi.id")
                .like("insi.insightName", insightQuery.getInsightName())
                .eq("insi.id", insightQuery.getInsightId())
                .eq("insif.masterId", insightQuery.getMasterId())
//                .orders(insightQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryBuilders, InsightEntity.class);
    }
}