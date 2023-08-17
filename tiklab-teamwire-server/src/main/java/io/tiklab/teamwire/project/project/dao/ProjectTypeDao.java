package io.tiklab.teamwire.project.project.dao;

import io.tiklab.teamwire.project.project.entity.ProjectTypeEntity;
import io.tiklab.teamwire.project.project.model.ProjectTypeQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 项目类型数据操作
 */
@Repository
public class ProjectTypeDao{

    private static Logger logger = LoggerFactory.getLogger(ProjectTypeDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建项目类型
     * @param projectTypeEntity
     * @return
     */
    public String createProjectType(ProjectTypeEntity projectTypeEntity) {
        return jpaTemplate.save(projectTypeEntity,String.class);
    }

    /**
     * 更新项目类型
     * @param projectTypeEntity
     */
    public void updateProjectType(ProjectTypeEntity projectTypeEntity){
        jpaTemplate.update(projectTypeEntity);
    }

    /**
     * 删除项目类型
     * @param id
     */
    public void deleteProjectType(String id){
        jpaTemplate.delete(ProjectTypeEntity.class,id);
    }

    /**
     * 查找项目类型
     * @param id
     * @return
     */
    public ProjectTypeEntity findProjectType(String id){
        return jpaTemplate.findOne(ProjectTypeEntity.class,id);
    }

    /**
    * 查找所有项目类型
    * @return
    */
    public List<ProjectTypeEntity> findAllProjectType() {
        return jpaTemplate.findAll(ProjectTypeEntity.class);
    }

    /**
     * 根据多个项目id 查找项目类型
     * @param idList
     * @return
     */
    public List<ProjectTypeEntity> findProjectTypeList(List<String> idList) {
        return jpaTemplate.findList(ProjectTypeEntity.class,idList);
    }

    /**
     * 根据条件查找项目类型
     * @param projectTypeQuery
     * @return
     */
    public List<ProjectTypeEntity> findProjectTypeList(ProjectTypeQuery projectTypeQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectTypeEntity.class)
                .like("name", projectTypeQuery.getName())
                .orders(projectTypeQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ProjectTypeEntity.class);
    }

    /**
     * 根据条件按照分页查找项目类型
     * @param projectTypeQuery
     * @return
     */
    public Pagination<ProjectTypeEntity> findProjectTypePage(ProjectTypeQuery projectTypeQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectTypeEntity.class)
                .like("name", projectTypeQuery.getName())
                .orders(projectTypeQuery.getOrderParams())
                .pagination(projectTypeQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ProjectTypeEntity.class);
    }
}