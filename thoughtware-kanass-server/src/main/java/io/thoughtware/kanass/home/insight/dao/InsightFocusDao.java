package io.thoughtware.kanass.home.insight.dao;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.kanass.home.insight.entity.InsightFocusEntity;
import io.thoughtware.kanass.home.insight.model.InsightFocusQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 收藏项目数据访问
 */
@Repository
public class InsightFocusDao {

    private static Logger logger = LoggerFactory.getLogger(InsightFocusDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建收藏项目数据
     * @param insightFocusEntity
     * @return
     */
    public String createInsightFocus(InsightFocusEntity insightFocusEntity) {
        return jpaTemplate.save(insightFocusEntity,String.class);
    }

    /**
     * 更新收藏数据
     * @param insightFocusEntity
     */
    public void updateInsightFocus(InsightFocusEntity insightFocusEntity){
        jpaTemplate.update(insightFocusEntity);
    }

    /**
     * 根据id删除收藏的项目
     * @param id
     */
    public void deleteInsightFocus(String id){
        jpaTemplate.delete(InsightFocusEntity.class,id);
    }

    public void deleteInsightFocus(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找收藏的项目
     * @param id
     * @return
     */
    public InsightFocusEntity findInsightFocus(String id){
        return jpaTemplate.findOne(InsightFocusEntity.class,id);
    }

    /**
    * 查找所有收藏的项目
    * @return
    */
    public List<InsightFocusEntity> findAllInsightFocus() {
        return jpaTemplate.findAll(InsightFocusEntity.class);
    }

    /**
     * 根据多个id 查找项目列表
     * @param idList
     * @return
     */
    public List<InsightFocusEntity> findInsightFocusList(List<String> idList) {
        return jpaTemplate.findList(InsightFocusEntity.class,idList);
    }

    /**
     * 根据条件查找收藏的项目列表
     * @param insightFocusQuery
     * @return
     */
    public List<InsightFocusEntity> findInsightFocusList(InsightFocusQuery insightFocusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(InsightFocusEntity.class)
                .eq("masterId", insightFocusQuery.getMasterId())
                .eq("insightId", insightFocusQuery.getInsightId())
                .orders(insightFocusQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, InsightFocusEntity.class);
    }

    /**
     * 根据条件按分页查找收藏的项目
     * @param insightFocusQuery
     * @return
     */
    public Pagination<InsightFocusEntity> findInsightFocusPage(InsightFocusQuery insightFocusQuery) {
        return jpaTemplate.findPage(insightFocusQuery,InsightFocusEntity.class);
    }
}