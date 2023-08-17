package io.tiklab.teamwire.workitem.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.teamwire.workitem.entity.WorkTestCaseEntity;
import io.tiklab.teamwire.workitem.model.WorkTestCaseQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WorkTestCaseDao
 */
@Repository
public class WorkTestCaseDao {

    private static Logger logger = LoggerFactory.getLogger(WorkTestCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项文档
     * @param workTestCaseEntity
     * @return
     */
    public String createWorkTestCase(WorkTestCaseEntity workTestCaseEntity) {
        return jpaTemplate.save(workTestCaseEntity,String.class);
    }

    /**
     * 更新事项文档
     * @param workTestCaseEntity
     */
    public void updateWorkTestCase(WorkTestCaseEntity workTestCaseEntity){
        jpaTemplate.update(workTestCaseEntity);
    }

    /**
     * 通过文档id删除事项文档
     * @param id
     */
    public void deleteWorkTestCase(String id){
        jpaTemplate.delete(WorkTestCaseEntity.class,id);
    }

    public void deleteWorkTestCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找事项文档
     * @param id
     * @return
     */
    public WorkTestCaseEntity findWorkTestCase(String id){
        return jpaTemplate.findOne(WorkTestCaseEntity.class,id);
    }

    /**
    * 查找所有事项文档
    * @return
    */
    public List<WorkTestCaseEntity> findAllWorkTestCase() {
        return jpaTemplate.findAll(WorkTestCaseEntity.class);
    }

    /**
     * 根据ids 查找事项文档
     * @param idList
     * @return
     */
    public List<WorkTestCaseEntity> findWorkTestCaseList(List<String> idList) {
        return jpaTemplate.findList(WorkTestCaseEntity.class,idList);
    }

    /**
     * 根据条件查询事项文档列表
     * @param workTestCaseQuery
     * @return
     */
    public List<WorkTestCaseEntity> findWorkTestCaseList(WorkTestCaseQuery workTestCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkTestCaseEntity.class)
                .eq("workItemId", workTestCaseQuery.getWorkItemId())
                .eq("testCaseId", workTestCaseQuery.getTestCaseId())
                .eq("repositoryId", workTestCaseQuery.getRepositoryId())
                .orders(workTestCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkTestCaseEntity.class);
    }

    /**
     * 根据条件按分页查询事项文档列表
     * @param workTestCaseQuery
     * @return
     */
    public Pagination<WorkTestCaseEntity> findWorkTestCasePage(WorkTestCaseQuery workTestCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkTestCaseEntity.class)
                .eq("workItemId", workTestCaseQuery.getWorkItemId())
                .eq("testCaseId", workTestCaseQuery.getTestCaseId())
                .like("name", workTestCaseQuery.getName())
                .eq("repositoryId", workTestCaseQuery.getRepositoryId())
                .in("repositoryId", workTestCaseQuery.getRepositoryIds())
                .orders(workTestCaseQuery.getOrderParams())
                .pagination(workTestCaseQuery.getPageParam())
                .get();

        return jpaTemplate.findPage(queryCondition, WorkTestCaseEntity.class);
    }
}