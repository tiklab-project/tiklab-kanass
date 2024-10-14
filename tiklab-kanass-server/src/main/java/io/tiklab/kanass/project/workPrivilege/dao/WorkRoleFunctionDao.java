package io.tiklab.kanass.project.workPrivilege.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.project.workPrivilege.entity.WorkRoleFunctionEntity;
import io.tiklab.kanass.project.workPrivilege.model.WorkRoleFunctionQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 事项优先级数据访问
 */
@Repository
public class WorkRoleFunctionDao {

    private static Logger logger = LoggerFactory.getLogger(WorkRoleFunctionDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项优先级
     * @param workRoleFunctionEntity
     * @return
     */
    public String createWorkRoleFunction(WorkRoleFunctionEntity workRoleFunctionEntity) {
        return jpaTemplate.save(workRoleFunctionEntity,String.class);
    }

    /**
     * 更新事项优先级
     * @param workRoleFunctionEntity
     */
    public void updateWorkRoleFunction(WorkRoleFunctionEntity workRoleFunctionEntity){
        jpaTemplate.update(workRoleFunctionEntity);
    }

    /**
     * 删除事项优先级
     * @param id
     */
    public void deleteWorkRoleFunction(String id){
        jpaTemplate.delete(WorkRoleFunctionEntity.class,id);
    }

    public void deleteWorkRoleFunctionCondition(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id 查找事项优先级列表
     * @param id
     * @return
     */
    public WorkRoleFunctionEntity findWorkRoleFunction(String id){
        return jpaTemplate.findOne(WorkRoleFunctionEntity.class,id);
    }

    /**
     * 根据ids 查找事项优先级列表
     * @param idList
     * @return
     */
    public List<WorkRoleFunctionEntity> findWorkRoleFunctionList(List<String> idList) {
        return jpaTemplate.findList(WorkRoleFunctionEntity.class,idList);
    }


    /**
     * 根据条件查找事项优先级列表
     * @param workRoleFunctionQuery
     * @return
     */
    public List<WorkRoleFunctionEntity> findWorkRoleFunctionList(WorkRoleFunctionQuery workRoleFunctionQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkRoleFunctionEntity.class)
                .eq("roleId", workRoleFunctionQuery.getRoleId())
                .eq("functionType", workRoleFunctionQuery.getFunctionType())
                .eq("privilegeId", workRoleFunctionQuery. getPrivilegeId())
                .orders(workRoleFunctionQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkRoleFunctionEntity.class);
    }

    /**
     * 根据条件按照分页查找事项优先级列表
     * @param workRoleFunctionQuery
     * @return
     */
    public Pagination<WorkRoleFunctionEntity> findWorkRoleFunctionPage(WorkRoleFunctionQuery workRoleFunctionQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkRoleFunctionEntity.class)
                .eq("roleId", workRoleFunctionQuery.getRoleId())
                .orders(workRoleFunctionQuery.getOrderParams())
                .pagination(workRoleFunctionQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WorkRoleFunctionEntity.class);
    }


}