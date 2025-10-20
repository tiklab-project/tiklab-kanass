package io.tiklab.kanass.workitem.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.workitem.entity.WorkItemFunctionEntity;
import io.tiklab.kanass.workitem.model.WorkItemFunctionQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 事项优先级数据访问
 */
@Repository
public class WorkItemFunctionDao {

    private static Logger logger = LoggerFactory.getLogger(WorkItemFunctionDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项优先级
     * @param workItemFunctionEntity
     * @return
     */
    public String createWorkItemFunction(WorkItemFunctionEntity workItemFunctionEntity) {
        return jpaTemplate.save(workItemFunctionEntity,String.class);
    }

    /**
     * 更新事项优先级
     * @param workItemFunctionEntity
     */
    public void updateWorkItemFunction(WorkItemFunctionEntity workItemFunctionEntity){
        jpaTemplate.update(workItemFunctionEntity);
    }

    /**
     * 删除事项优先级
     * @param id
     */
    public void deleteWorkItemFunction(String id){
        jpaTemplate.delete(WorkItemFunctionEntity.class,id);
    }

    /**
     * 根据id 查找事项优先级列表
     * @param id
     * @return
     */
    public WorkItemFunctionEntity findWorkItemFunction(String id){
        return jpaTemplate.findOne(WorkItemFunctionEntity.class,id);
    }

    /**
     * 根据ids 查找事项优先级列表
     * @param idList
     * @return
     */
    public List<WorkItemFunctionEntity> findWorkItemFunctionList(List<String> idList) {
        return jpaTemplate.findList(WorkItemFunctionEntity.class,idList);
    }

    /**
    * 查找所有事项优先级列表
    * @return
    */
    public List<WorkItemFunctionEntity> findAllWorkItemFunction() {
        return jpaTemplate.findAll(WorkItemFunctionEntity.class);
    }

    /**
     * 根据条件查找事项优先级列表
     * @param workItemFunctionQuery
     * @return
     */
    public List<WorkItemFunctionEntity> findWorkItemFunctionList(WorkItemFunctionQuery workItemFunctionQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkItemFunctionEntity.class)
                .eq("name", workItemFunctionQuery.getName())
                .in("id", workItemFunctionQuery.getFunctionIds())
                .orders(workItemFunctionQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkItemFunctionEntity.class);
    }

    /**
     * 根据条件按照分页查找事项优先级列表
     * @param workItemFunctionQuery
     * @return
     */
    public Pagination<WorkItemFunctionEntity> findWorkItemFunctionPage(WorkItemFunctionQuery workItemFunctionQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkItemFunctionEntity.class)
                .like("name", workItemFunctionQuery.getName())
                .orders(workItemFunctionQuery.getOrderParams())
                .pagination(workItemFunctionQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WorkItemFunctionEntity.class);
    }


}