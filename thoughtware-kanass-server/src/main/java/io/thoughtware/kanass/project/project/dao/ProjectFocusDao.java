package io.thoughtware.kanass.project.project.dao;

import io.thoughtware.kanass.project.project.entity.ProjectFocusEntity;
import io.thoughtware.kanass.project.project.model.ProjectFocusQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 收藏项目数据访问
 */
@Repository
public class ProjectFocusDao{

    private static Logger logger = LoggerFactory.getLogger(ProjectFocusDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建收藏项目数据
     * @param projectFocusEntity
     * @return
     */
    public String createProjectFocus(ProjectFocusEntity projectFocusEntity) {
        return jpaTemplate.save(projectFocusEntity,String.class);
    }

    /**
     * 更新收藏数据
     * @param projectFocusEntity
     */
    public void updateProjectFocus(ProjectFocusEntity projectFocusEntity){
        jpaTemplate.update(projectFocusEntity);
    }

    /**
     * 根据id删除收藏的项目
     * @param id
     */
    public void deleteProjectFocus(String id){
        jpaTemplate.delete(ProjectFocusEntity.class,id);
    }

    public void deleteProjectFocus(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找收藏的项目
     * @param id
     * @return
     */
    public ProjectFocusEntity findProjectFocus(String id){
        return jpaTemplate.findOne(ProjectFocusEntity.class,id);
    }

    /**
    * 查找所有收藏的项目
    * @return
    */
    public List<ProjectFocusEntity> findAllProjectFocus() {
        return jpaTemplate.findAll(ProjectFocusEntity.class);
    }

    /**
     * 根据多个id 查找项目列表
     * @param idList
     * @return
     */
    public List<ProjectFocusEntity> findProjectFocusList(List<String> idList) {
        return jpaTemplate.findList(ProjectFocusEntity.class,idList);
    }

    /**
     * 根据条件查找收藏的项目列表
     * @param projectFocusQuery
     * @return
     */
    public List<ProjectFocusEntity> findProjectFocusList(ProjectFocusQuery projectFocusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectFocusEntity.class)
                .eq("masterId", projectFocusQuery.getMasterId())
                .eq("projectId", projectFocusQuery.getProjectId())
                .orders(projectFocusQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, ProjectFocusEntity.class);
    }

    /**
     * 根据条件按分页查找收藏的项目
     * @param projectFocusQuery
     * @return
     */
    public Pagination<ProjectFocusEntity> findProjectFocusPage(ProjectFocusQuery projectFocusQuery) {
        return jpaTemplate.findPage(projectFocusQuery,ProjectFocusEntity.class);
    }
}