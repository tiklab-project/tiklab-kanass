package io.thoughtware.kanass.workitem.dao;

import io.thoughtware.kanass.project.workPrivilege.entity.WorkFunctionEntity;
import io.thoughtware.kanass.workitem.model.WorkItemDocumentQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.dal.jpa.JpaTemplate;
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
    public String createWorkItemDocument(WorkFunctionEntity workItemDocumentEntity) {
        return jpaTemplate.save(workItemDocumentEntity,String.class);
    }

    /**
     * 更新事项文档
     * @param workItemDocumentEntity
     */
    public void updateWorkItemDocument(WorkFunctionEntity workItemDocumentEntity){
        jpaTemplate.update(workItemDocumentEntity);
    }

    /**
     * 通过文档id删除事项文档
     * @param id
     */
    public void deleteWorkItemDocument(String id){
        jpaTemplate.delete(WorkFunctionEntity.class,id);
    }

    public void deleteWorkItemDocument(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找事项文档
     * @param id
     * @return
     */
    public WorkFunctionEntity findWorkItemDocument(String id){
        return jpaTemplate.findOne(WorkFunctionEntity.class,id);
    }

    /**
    * 查找所有事项文档
    * @return
    */
    public List<WorkFunctionEntity> findAllWorkItemDocument() {
        return jpaTemplate.findAll(WorkFunctionEntity.class);
    }

    /**
     * 根据ids 查找事项文档
     * @param idList
     * @return
     */
    public List<WorkFunctionEntity> findWorkItemDocumentList(List<String> idList) {
        return jpaTemplate.findList(WorkFunctionEntity.class,idList);
    }

    /**
     * 根据条件查询事项文档列表
     * @param workItemDocumentQuery
     * @return
     */
    public List<WorkFunctionEntity> findWorkItemDocumentList(WorkItemDocumentQuery workItemDocumentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkFunctionEntity.class)
                .eq("workItemId", workItemDocumentQuery.getWorkItemId())
                .eq("documentId", workItemDocumentQuery.getDocumentId())
                .eq("repositoryId", workItemDocumentQuery.getRepositoryId())
                .orders(workItemDocumentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkFunctionEntity.class);
    }

    /**
     * 根据条件按分页查询事项文档列表
     * @param workItemDocumentQuery
     * @return
     */
    public Pagination<WorkFunctionEntity> findWorkItemDocumentPage(WorkItemDocumentQuery workItemDocumentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkFunctionEntity.class)
                .eq("workItemId", workItemDocumentQuery.getWorkItemId())
                .eq("documentId", workItemDocumentQuery.getDocumentId())
                .like("name", workItemDocumentQuery.getName())
                .eq("repositoryId", workItemDocumentQuery.getRepositoryId())
                .in("repositoryId", workItemDocumentQuery.getRepositoryIds())
                .orders(workItemDocumentQuery.getOrderParams())
                .pagination(workItemDocumentQuery.getPageParam())
                .get();

        return jpaTemplate.findPage(queryCondition, WorkFunctionEntity.class);
    }
}