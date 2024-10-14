package io.tiklab.kanass.project.workPrivilege.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.project.workPrivilege.entity.WorkPrivilegeEntity;
import io.tiklab.kanass.project.workPrivilege.model.WorkPrivilegeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 事项优先级数据访问
 */
@Repository
public class WorkPrivilegeDao {

    private static Logger logger = LoggerFactory.getLogger(WorkPrivilegeDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项优先级
     * @param workPrivilegeEntity
     * @return
     */
    public String createWorkPrivilege(WorkPrivilegeEntity workPrivilegeEntity) {
        return jpaTemplate.save(workPrivilegeEntity,String.class);
    }

    /**
     * 更新事项优先级
     * @param workPrivilegeEntity
     */
    public void updateWorkPrivilege(WorkPrivilegeEntity workPrivilegeEntity){
        jpaTemplate.update(workPrivilegeEntity);
    }

    /**
     * 删除事项优先级
     * @param id
     */
    public void deleteWorkPrivilege(String id){
        jpaTemplate.delete(WorkPrivilegeEntity.class,id);
    }

    /**
     * 根据id 查找事项优先级列表
     * @param id
     * @return
     */
    public WorkPrivilegeEntity findWorkPrivilege(String id){
        return jpaTemplate.findOne(WorkPrivilegeEntity.class,id);
    }

    /**
     * 根据ids 查找事项优先级列表
     * @param idList
     * @return
     */
    public List<WorkPrivilegeEntity> findWorkPrivilegeList(List<String> idList) {
        return jpaTemplate.findList(WorkPrivilegeEntity.class,idList);
    }

    /**
    * 查找所有事项优先级列表
    * @return
    */
    public List<WorkPrivilegeEntity> findAllWorkPrivilege() {
        return jpaTemplate.findAll(WorkPrivilegeEntity.class);
    }

    /**
     * 根据条件查找事项优先级列表
     * @param workPrivilegeQuery
     * @return
     */
    public List<WorkPrivilegeEntity> findWorkPrivilegeList(WorkPrivilegeQuery workPrivilegeQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(WorkPrivilegeEntity.class)
                .eq("projectId", workPrivilegeQuery.getProjectId())
                .eq("workTypeId", workPrivilegeQuery.getWorkTypeId())
                .eq("scope", workPrivilegeQuery.getScope())
                .orders(workPrivilegeQuery.getOrderParams());

        QueryCondition queryCondition = queryBuilders.get();
        return jpaTemplate.findList(queryCondition, WorkPrivilegeEntity.class);
    }

    /**
     * 根据条件按照分页查找事项优先级列表
     * @param workPrivilegeQuery
     * @return
     */
    public Pagination<WorkPrivilegeEntity> findWorkPrivilegePage(WorkPrivilegeQuery workPrivilegeQuery) {

        QueryBuilders queryBuilders = QueryBuilders.createQuery(WorkPrivilegeEntity.class)
                .eq("projectId", workPrivilegeQuery.getProjectId())
                .orders(workPrivilegeQuery.getOrderParams())
                .pagination(workPrivilegeQuery.getPageParam());

        QueryCondition queryCondition = queryBuilders.get();
        return jpaTemplate.findPage(queryCondition, WorkPrivilegeEntity.class);
    }


}