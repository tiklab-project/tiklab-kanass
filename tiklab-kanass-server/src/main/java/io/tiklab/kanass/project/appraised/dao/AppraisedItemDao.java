package io.tiklab.kanass.project.appraised.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.project.appraised.entity.AppraisedEntity;
import io.tiklab.kanass.project.appraised.entity.AppraisedItemEntity;
import io.tiklab.kanass.project.appraised.model.AppraisedItemQuery;
import io.tiklab.kanass.workitem.entity.WorkItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 事项与评审关联表
 */
@Repository
public class AppraisedItemDao {
    private static Logger logger = LoggerFactory.getLogger(AppraisedDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建项目评审
     * @param entity
     * @return
     */
    public String createAppraisedItem(AppraisedItemEntity entity) {
        return jpaTemplate.save(entity,String.class);
    }

    /**
     * 更新项目评审
     * @param appraisedItemEntity
     */
    public void updateAppraisedItem(AppraisedItemEntity appraisedItemEntity) {
        jpaTemplate.update(appraisedItemEntity);
    }

    /**
     * 删除项目评审
     * @param id
     */
    public void deleteAppraisedItem(String id){
        jpaTemplate.delete(AppraisedItemEntity.class,id);
    }

    /**
     * 根据id查找项目评审
     * @param id
     * @return
     */
    public AppraisedItemEntity findAppraisedItem(String id){
        return jpaTemplate.findOne(AppraisedItemEntity.class,id);
    }

    /**
     * 查找所有项目评审
     * @return
     */
    public List<AppraisedItemEntity> findAllAppraisedItem() {
        return jpaTemplate.findAll(AppraisedItemEntity.class);
    }

    /**
     * 根据多个id 查找项目评审列表
     * @param idList
     * @return
     */
    public List<AppraisedItemEntity> findAppraisedItemList(List<String> idList) {
        return jpaTemplate.findList(AppraisedItemEntity.class,idList);
    }

    public List<AppraisedItemEntity> findAppraisedList(AppraisedItemQuery appraisedItemQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppraisedItemEntity.class,"ai")
//                .leftJoin(WorkItemEntity.class, "wi", "wi.id=ai.workItemId")
//                .leftJoin(AppraisedEntity.class, "a", "a.id=ai.appraised_id")
//                .like("wi.title", appraisedItemQuery.getWorkItemTitle())
                .eq("appraisedItemState", appraisedItemQuery.getWorkItemAppraisedState())
                .eq("appraisedId", appraisedItemQuery.getAppraisedId())
                .get();

        return jpaTemplate.findList(queryCondition, AppraisedItemEntity.class);
    }

    public Pagination<AppraisedItemEntity> findAppraisedPage(AppraisedItemQuery appraisedItemQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppraisedItemEntity.class,"ai")
//                .leftJoin(WorkItemEntity.class, "wi", "wi.id=ai.workItemId")
                .leftJoin(AppraisedEntity.class, "a", "a.id=ai.appraisedId")
//                .like("wi.title", appraisedItemQuery.getWorkItemTitle())
                .eq("ai.appraisedItemState", appraisedItemQuery.getWorkItemAppraisedState())
                .eq("ai.appraisedId", appraisedItemQuery.getAppraisedId())
                .eq("a.master", appraisedItemQuery.getMaster())
                .eq("a.builder", appraisedItemQuery.getBuilder())
                .eq("a.projectId", appraisedItemQuery.getProjectId())
                .in("a.projectId", appraisedItemQuery.getProjectIds())
                .eq("ai.appraisedTypeId", appraisedItemQuery.getAppraisedTypeId())
                .orders(appraisedItemQuery.getOrderParams())
                .pagination(appraisedItemQuery.getPageParam())
                .get();

        return jpaTemplate.findPage(queryCondition, AppraisedItemEntity.class);
    }
}
