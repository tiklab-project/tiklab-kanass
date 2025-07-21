package io.tiklab.kanass.project.appraised.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.project.appraised.entity.AppraisedWorkItemEntity;
import io.tiklab.kanass.project.appraised.model.AppraisedWorkItemQuery;
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
public class AppraisedWorkItemDao {
    private static Logger logger = LoggerFactory.getLogger(AppraisedDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建项目评审
     * @param entity
     * @return
     */
    public String createAppraisedWorkItem(AppraisedWorkItemEntity entity) {
        return jpaTemplate.save(entity,String.class);
    }

    /**
     * 更新项目评审
     * @param appraisedWorkItemEntity
     */
    public void updateAppraisedWorkItem(AppraisedWorkItemEntity appraisedWorkItemEntity) {
        jpaTemplate.update(appraisedWorkItemEntity);
    }

    /**
     * 删除项目评审
     * @param id
     */
    public void deleteAppraisedWorkItem(String id){
        jpaTemplate.delete(AppraisedWorkItemEntity.class,id);
    }

    /**
     * 根据id查找项目评审
     * @param id
     * @return
     */
    public AppraisedWorkItemEntity findAppraisedWorkItem(String id){
        return jpaTemplate.findOne(AppraisedWorkItemEntity.class,id);
    }

    /**
     * 查找所有项目评审
     * @return
     */
    public List<AppraisedWorkItemEntity> findAllAppraisedWorkItem() {
        return jpaTemplate.findAll(AppraisedWorkItemEntity.class);
    }

    /**
     * 根据多个id 查找项目评审列表
     * @param idList
     * @return
     */
    public List<AppraisedWorkItemEntity> findAppraisedWorkItemList(List<String> idList) {
        return jpaTemplate.findList(AppraisedWorkItemEntity.class,idList);
    }

    public List<AppraisedWorkItemEntity> findAppraisedList(AppraisedWorkItemQuery appraisedWorkItemQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppraisedWorkItemEntity.class,"awi")
                .leftJoin(WorkItemEntity.class, "wi", "wi.id=awi.workItemId")
//                .leftJoin(AppraisedEntity.class, "a", "a.id=awi.appraised_id")
                .like("wi.title", appraisedWorkItemQuery.getWorkItemTitle())
                .eq("workItemAppraisedState", appraisedWorkItemQuery.getWorkItemAppraisedState())
                .eq("appraisedId", appraisedWorkItemQuery.getAppraisedId())
                .get();

        return jpaTemplate.findList(queryCondition,AppraisedWorkItemEntity.class);
    }

    public Pagination<AppraisedWorkItemEntity> findAppraisedPage(AppraisedWorkItemQuery appraisedWorkItemQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppraisedWorkItemEntity.class,"awi")
                .leftJoin(WorkItemEntity.class, "wi", "wi.id=awi.workItemId")
//                .leftJoin(AppraisedEntity.class, "a", "a.id=awi.appraised_id")
                .like("wi.title", appraisedWorkItemQuery.getWorkItemTitle())
                .eq("workItemAppraisedState", appraisedWorkItemQuery.getWorkItemAppraisedState())
                .eq("appraisedId", appraisedWorkItemQuery.getAppraisedId())
                .orders(appraisedWorkItemQuery.getOrderParams())
                .orders(appraisedWorkItemQuery.getOrderParams())
                .pagination(appraisedWorkItemQuery.getPageParam())
                .get();

        return jpaTemplate.findPage(queryCondition, AppraisedWorkItemEntity.class);
    }
}
