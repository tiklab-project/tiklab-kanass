package io.tiklab.kanass.workitem.dao;

import io.tiklab.kanass.workitem.entity.WorkCommentEntity;
import io.tiklab.kanass.workitem.model.WorkCommentQuery;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 事项评论数据访问
 */
@Repository
public class WorkCommentDao{

    private static Logger logger = LoggerFactory.getLogger(WorkCommentDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项评论
     * @param workCommentEntity
     * @return
     */
    public String createWorkComment(WorkCommentEntity workCommentEntity) {
        return jpaTemplate.save(workCommentEntity,String.class);
    }

    /**
     * 更新事项评论
     * @param workCommentEntity
     */
    public void updateWorkComment(WorkCommentEntity workCommentEntity){
        jpaTemplate.update(workCommentEntity);
    }

    /**
     * 删除事项评论
     * @param id
     */
    public void deleteWorkComment(String id){
        jpaTemplate.delete(WorkCommentEntity.class,id);
    }

    public void deleteWorkComment(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找事项评论
     * @param id
     * @return
     */
    public WorkCommentEntity findWorkComment(String id){
        return jpaTemplate.findOne(WorkCommentEntity.class,id);
    }

    /**
    * 查找所有事项评论
    * @return
    */
    public List<WorkCommentEntity> findAllWorkComment() {
        return jpaTemplate.findAll(WorkCommentEntity.class);
    }

    /**
     * 根据多个id 查找事项评论
     * @param idList
     * @return
     */
    public List<WorkCommentEntity> findWorkCommentList(List<String> idList) {
        return jpaTemplate.findList(WorkCommentEntity.class,idList);
    }

    /**
     * 根据条件查询事项评论列表
     * @param workCommentQuery
     * @return
     */
    public List<WorkCommentEntity> findWorkCommentList(WorkCommentQuery workCommentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkCommentEntity.class)
                .eq("workItemId", workCommentQuery.getWorkItemId())
                .orders(workCommentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,WorkCommentEntity.class);
    }

    /**
     * 根据条件按分页查询事项评论列表
     * @param workCommentQuery
     * @return
     */
    public Pagination<WorkCommentEntity> findWorkCommentPage(WorkCommentQuery workCommentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkCommentEntity.class)
                .eq("workItemId", workCommentQuery.getWorkItemId())
                .orders(workCommentQuery.getOrderParams())
                .pagination(workCommentQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,WorkCommentEntity.class);
    }
}