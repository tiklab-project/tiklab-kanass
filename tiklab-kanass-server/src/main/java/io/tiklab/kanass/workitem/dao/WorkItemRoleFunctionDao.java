package io.tiklab.kanass.workitem.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.workitem.entity.WorkItemRoleFunctionEntity;
import io.tiklab.kanass.workitem.model.WorkItemRoleFunctionDmQuery;
import io.tiklab.kanass.workitem.model.WorkItemRoleFunctionQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 事项优先级数据访问
 */
@Repository
public class WorkItemRoleFunctionDao {

    private static Logger logger = LoggerFactory.getLogger(WorkItemRoleFunctionDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项优先级
     * @param workItemFunctionEntity
     * @return
     */
    public String createWorkItemRoleFunction(WorkItemRoleFunctionEntity workItemFunctionEntity) {
        return jpaTemplate.save(workItemFunctionEntity,String.class);
    }

    /**
     * 更新事项优先级
     * @param workItemFunctionEntity
     */
    public void updateWorkItemRoleFunction(WorkItemRoleFunctionEntity workItemFunctionEntity){
        jpaTemplate.update(workItemFunctionEntity);
    }

    /**
     * 删除事项优先级
     * @param id
     */
    public void deleteWorkItemRoleFunction(String id){
        jpaTemplate.delete(WorkItemRoleFunctionEntity.class,id);
    }

    public void deleteWorkItemRoleFunctionCondition(WorkItemRoleFunctionQuery workItemRoleFunctionQuery){
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(WorkItemRoleFunctionEntity.class)
                .eq("workTypeId", workItemRoleFunctionQuery.getWorkTypeId())
                .eq("roleId", workItemRoleFunctionQuery.getRoleId())
                .eq("functionType", workItemRoleFunctionQuery.getFunctionType())
                .get();
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id 查找事项优先级列表
     * @param id
     * @return
     */
    public WorkItemRoleFunctionEntity findWorkItemRoleFunction(String id){
        return jpaTemplate.findOne(WorkItemRoleFunctionEntity.class,id);
    }

    /**
     * 根据ids 查找事项优先级列表
     * @param idList
     * @return
     */
    public List<WorkItemRoleFunctionEntity> findWorkItemRoleFunctionList(List<String> idList) {
        return jpaTemplate.findList(WorkItemRoleFunctionEntity.class,idList);
    }

    /**
    * 查找所有事项优先级列表
    * @return
    */
    public List<WorkItemRoleFunctionEntity> findAllWorkItemRoleFunction() {
        return jpaTemplate.findAll(WorkItemRoleFunctionEntity.class);
    }

    /**
     * 根据条件查找事项优先级列表
     * @param workItemFunctionQuery
     * @return
     */
    public List<WorkItemRoleFunctionEntity> findWorkItemRoleFunctionList(WorkItemRoleFunctionQuery workItemFunctionQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkItemRoleFunctionEntity.class)
                .eq("workTypeId", workItemFunctionQuery.getWorkTypeId())
                .in("workTypeId", workItemFunctionQuery.getWorkTypeIds())
                .eq("roleId", workItemFunctionQuery.getRoleId())
                .eq("functionType", workItemFunctionQuery.getFunctionType())
                .orders(workItemFunctionQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkItemRoleFunctionEntity.class);
    }

    /**
     * 根据条件按照分页查找事项优先级列表
     * @param workItemFunctionQuery
     * @return
     */
    public Pagination<WorkItemRoleFunctionEntity> findWorkItemRoleFunctionPage(WorkItemRoleFunctionQuery workItemFunctionQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkItemRoleFunctionEntity.class)
                .eq("workTypeId", workItemFunctionQuery.getWorkTypeId())
                .orders(workItemFunctionQuery.getOrderParams())
                .pagination(workItemFunctionQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WorkItemRoleFunctionEntity.class);
    }


}