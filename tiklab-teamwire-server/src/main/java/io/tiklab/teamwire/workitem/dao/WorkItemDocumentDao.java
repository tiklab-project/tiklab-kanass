package io.tiklab.teamwire.workitem.dao;

import io.tiklab.teamwire.workitem.entity.WorkItemDocumentEntity;
import io.tiklab.teamwire.workitem.model.WorkItemDocumentQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WorkItemDocumentDao
 */
@Repository
public class WorkItemDocumentDao{

    private static Logger logger = LoggerFactory.getLogger(WorkItemDocumentDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项文档
     * @param workItemDocumentEntity
     * @return
     */
    public String createWorkItemDocument(WorkItemDocumentEntity workItemDocumentEntity) {
        return jpaTemplate.save(workItemDocumentEntity,String.class);
    }

    /**
     * 更新事项文档
     * @param workItemDocumentEntity
     */
    public void updateWorkItemDocument(WorkItemDocumentEntity workItemDocumentEntity){
        jpaTemplate.update(workItemDocumentEntity);
    }

    /**
     * 通过文档id删除事项文档
     * @param id
     */
    public void deleteWorkItemDocument(String id){
        jpaTemplate.delete(WorkItemDocumentEntity.class,id);
    }

    public void deleteWorkItemDocument(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找事项文档
     * @param id
     * @return
     */
    public WorkItemDocumentEntity findWorkItemDocument(String id){
        return jpaTemplate.findOne(WorkItemDocumentEntity.class,id);
    }

    /**
    * 查找所有事项文档
    * @return
    */
    public List<WorkItemDocumentEntity> findAllWorkItemDocument() {
        return jpaTemplate.findAll(WorkItemDocumentEntity.class);
    }

    /**
     * 根据ids 查找事项文档
     * @param idList
     * @return
     */
    public List<WorkItemDocumentEntity> findWorkItemDocumentList(List<String> idList) {
        return jpaTemplate.findList(WorkItemDocumentEntity.class,idList);
    }

    /**
     * 根据条件查询事项文档列表
     * @param workItemDocumentQuery
     * @return
     */
    public List<WorkItemDocumentEntity> findWorkItemDocumentList(WorkItemDocumentQuery workItemDocumentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkItemDocumentEntity.class)
                .eq("workItemId", workItemDocumentQuery.getWorkItemId())
                .eq("documentId", workItemDocumentQuery.getDocumentId())
                .eq("repositoryId", workItemDocumentQuery.getRepositoryId())
                .orders(workItemDocumentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkItemDocumentEntity.class);
    }

    /**
     * 根据条件按分页查询事项文档列表
     * @param workItemDocumentQuery
     * @return
     */
    public Pagination<WorkItemDocumentEntity> findWorkItemDocumentPage(WorkItemDocumentQuery workItemDocumentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkItemDocumentEntity.class)
                .eq("workItemId", workItemDocumentQuery.getWorkItemId())
                .eq("documentId", workItemDocumentQuery.getDocumentId())
                .like("name", workItemDocumentQuery.getName())
                .eq("repositoryId", workItemDocumentQuery.getRepositoryId())
                .in("repositoryId", workItemDocumentQuery.getRepositoryIds())
                .orders(workItemDocumentQuery.getOrderParams())
                .pagination(workItemDocumentQuery.getPageParam())
                .get();

        return jpaTemplate.findPage(queryCondition, WorkItemDocumentEntity.class);
    }
}