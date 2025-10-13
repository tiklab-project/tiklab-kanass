package io.tiklab.kanass.project.appraised.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.project.appraised.entity.AppraisedHistoryEntity;
import io.tiklab.kanass.project.appraised.entity.AppraisedItemEntity;
import io.tiklab.kanass.project.appraised.model.AppraisedHistoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public class AppraisedHistoryDao {
    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建评审记录
     * @param entity
     * @return
     */
    public String createAppraisedHistory(AppraisedHistoryEntity entity) {
        return jpaTemplate.save(entity,String.class);
    }

    /**
     * 更新评审记录
     * @param appraisedWorkItemEntity
     */
    public void updateAppraisedHistory(AppraisedHistoryEntity appraisedWorkItemEntity) {
        jpaTemplate.update(appraisedWorkItemEntity);
    }

    /**
     * 删除评审记录
     * @param id
     */
    public void deleteAppraisedHistory(String id){
        jpaTemplate.delete(AppraisedHistoryEntity.class,id);
    }

    /**
     * 根据id查找评审记录
     * @param id
     * @return
     */
    public AppraisedHistoryEntity findAppraisedHistory(String id){
        return jpaTemplate.findOne(AppraisedHistoryEntity.class,id);
    }

    /**
     * 查找所有评审记录
     * @return
     */
    public List<AppraisedHistoryEntity> findAllAppraisedHistory() {
        return jpaTemplate.findAll(AppraisedHistoryEntity.class);
    }

    /**
     * 根据多个id 查找评审记录列表
     * @param idList
     * @return
     */
    public List<AppraisedHistoryEntity> findAppraisedHistoryList(List<String> idList) {
        return jpaTemplate.findList(AppraisedHistoryEntity.class,idList);
    }

    public List<AppraisedHistoryEntity> findAppraisedList(AppraisedHistoryQuery appraisedHistoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppraisedHistoryEntity.class,"ah")
                .leftJoin(AppraisedItemEntity.class, "awi", "awi.id=ah.appraisedItemId")
                .eq("ah.appraisedItemId", appraisedHistoryQuery.getAppraisedItemId())
                .eq("ah.AppraisedItemState", appraisedHistoryQuery.getAppraisedItemState())
                .orders(appraisedHistoryQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition,AppraisedHistoryEntity.class);
    }

    public Pagination<AppraisedHistoryEntity> findAppraisedPage(AppraisedHistoryQuery appraisedHistoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppraisedHistoryEntity.class,"ah")
                .leftJoin(AppraisedItemEntity.class, "awi", "awi.id=ah.appraisedItemId")
                .eq("ah.appraisedItemId", appraisedHistoryQuery.getAppraisedItemId())
                .eq("awi.appraisedItemState", appraisedHistoryQuery.getAppraisedItemState())
                .orders(appraisedHistoryQuery.getOrderParams())
                .pagination(appraisedHistoryQuery.getPageParam())
                .get();

        return jpaTemplate.findPage(queryCondition, AppraisedHistoryEntity.class);
    }
}
