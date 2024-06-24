package io.thoughtware.kanass.project.workPrivilege.dao;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.kanass.project.workPrivilege.entity.ProjectVroleEntity;
import io.thoughtware.kanass.project.workPrivilege.model.ProjectVroleQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 事项优先级数据访问
 */
@Repository
public class ProjectVroleDao {

    private static Logger logger = LoggerFactory.getLogger(ProjectVroleDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项优先级
     * @param projectVroleEntity
     * @return
     */
    public String createProjectVrole(ProjectVroleEntity projectVroleEntity) {
        return jpaTemplate.save(projectVroleEntity,String.class);
    }

    /**
     * 更新事项优先级
     * @param projectVroleEntity
     */
    public void updateProjectVrole(ProjectVroleEntity projectVroleEntity){
        jpaTemplate.update(projectVroleEntity);
    }

    /**
     * 删除事项优先级
     * @param id
     */
    public void deleteProjectVrole(String id){
        jpaTemplate.delete(ProjectVroleEntity.class,id);
    }

    /**
     * 根据id 查找事项优先级列表
     * @param id
     * @return
     */
    public ProjectVroleEntity findProjectVrole(String id){
        return jpaTemplate.findOne(ProjectVroleEntity.class,id);
    }

    /**
     * 根据ids 查找事项优先级列表
     * @param idList
     * @return
     */
    public List<ProjectVroleEntity> findProjectVroleList(List<String> idList) {
        return jpaTemplate.findList(ProjectVroleEntity.class,idList);
    }

    /**
    * 查找所有事项优先级列表
    * @return
    */
    public List<ProjectVroleEntity> findAllProjectVrole() {
        return jpaTemplate.findAll(ProjectVroleEntity.class);
    }

    /**
     * 根据条件查找事项优先级列表
     * @param projectVroleQuery
     * @return
     */
    public List<ProjectVroleEntity> findProjectVroleList(ProjectVroleQuery projectVroleQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectVroleEntity.class)
                .eq("projectId", projectVroleQuery.getProjectId())
                .orders(projectVroleQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ProjectVroleEntity.class);
    }

    /**
     * 根据条件按照分页查找事项优先级列表
     * @param projectVroleQuery
     * @return
     */
    public Pagination<ProjectVroleEntity> findProjectVrolePage(ProjectVroleQuery projectVroleQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectVroleEntity.class)
                .eq("projectId", projectVroleQuery.getProjectId())
                .orders(projectVroleQuery.getOrderParams())
                .pagination(projectVroleQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ProjectVroleEntity.class);
    }


}