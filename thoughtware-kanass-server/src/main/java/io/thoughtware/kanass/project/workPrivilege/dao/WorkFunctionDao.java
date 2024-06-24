package io.thoughtware.kanass.project.workPrivilege.dao;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;

import io.thoughtware.kanass.project.workPrivilege.entity.WorkFunctionEntity;
import io.thoughtware.kanass.project.workPrivilege.model.WorkFunctionQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 事项优先级数据访问
 */
@Repository
public class WorkFunctionDao {

    private static Logger logger = LoggerFactory.getLogger(WorkFunctionDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项优先级
     * @param workFunctionEntity
     * @return
     */
    public String createWorkFunction(WorkFunctionEntity workFunctionEntity) {
        return jpaTemplate.save(workFunctionEntity,String.class);
    }

    /**
     * 更新事项优先级
     * @param workFunctionEntity
     */
    public void updateWorkFunction(WorkFunctionEntity workFunctionEntity){
        jpaTemplate.update(workFunctionEntity);
    }

    /**
     * 删除事项优先级
     * @param id
     */
    public void deleteWorkFunction(String id){
        jpaTemplate.delete(WorkFunctionEntity.class,id);
    }

    /**
     * 根据id 查找事项优先级列表
     * @param id
     * @return
     */
    public WorkFunctionEntity findWorkFunction(String id){
        return jpaTemplate.findOne(WorkFunctionEntity.class,id);
    }

    /**
     * 根据ids 查找事项优先级列表
     * @param idList
     * @return
     */
    public List<WorkFunctionEntity> findWorkFunctionList(List<String> idList) {
        return jpaTemplate.findList(WorkFunctionEntity.class,idList);
    }

    /**
    * 查找所有事项优先级列表
    * @return
    */
    public List<WorkFunctionEntity> findAllWorkFunction() {
        return jpaTemplate.findAll(WorkFunctionEntity.class);
    }

    /**
     * 根据条件查找事项优先级列表
     * @param workFunctionQuery
     * @return
     */
    public List<WorkFunctionEntity> findWorkFunctionList(WorkFunctionQuery workFunctionQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(WorkFunctionEntity.class)
                .orders(workFunctionQuery.getOrderParams());

        if(workFunctionQuery.getParentIdIsNull() != null && workFunctionQuery.getParentIdIsNull()){
            queryBuilders = queryBuilders.isNull("parentId");
        }

        QueryCondition queryCondition = queryBuilders.get();
        return jpaTemplate.findList(queryCondition, WorkFunctionEntity.class);
    }

    /**
     * 根据条件按照分页查找事项优先级列表
     * @param workFunctionQuery
     * @return
     */
    public Pagination<WorkFunctionEntity> findWorkFunctionPage(WorkFunctionQuery workFunctionQuery) {

        QueryBuilders queryBuilders = QueryBuilders.createQuery(WorkFunctionEntity.class)
                .orders(workFunctionQuery.getOrderParams())
                .pagination(workFunctionQuery.getPageParam());

        if(workFunctionQuery.getParentIdIsNull() != null && workFunctionQuery.getParentIdIsNull()){
            queryBuilders = queryBuilders.isNull("parentId");
        }

        QueryCondition queryCondition = queryBuilders.get();
        return jpaTemplate.findPage(queryCondition, WorkFunctionEntity.class);
    }


}