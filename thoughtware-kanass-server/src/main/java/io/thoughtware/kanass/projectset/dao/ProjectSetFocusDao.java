package io.thoughtware.kanass.projectset.dao;

import io.thoughtware.kanass.projectset.entity.ProjectSetFocusEntity;
import io.thoughtware.kanass.projectset.model.ProjectSetFocusQuery;
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
 * 项目集收藏数据访问
 */
@Repository
public class ProjectSetFocusDao{

    private static Logger logger = LoggerFactory.getLogger(ProjectSetFocusDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建收藏的项目集
     * @param projectSetFocusEntity
     * @return
     */
    public String createProjectSetFocus(ProjectSetFocusEntity projectSetFocusEntity) {
        return jpaTemplate.save(projectSetFocusEntity,String.class);
    }

    /**
     * 更新收藏的项目集
     * @param projectSetFocusEntity
     */
    public void updateProjectSetFocus(ProjectSetFocusEntity projectSetFocusEntity){
        jpaTemplate.update(projectSetFocusEntity);
    }

    /**
     * 删除收藏的项目集
     * @param id
     */
    public void deleteProjectSetFocus(String id){
        jpaTemplate.delete(ProjectSetFocusEntity.class,id);
    }

    /**
     * 根据条件删除项目集
     * @param deleteCondition
     */
    public void deleteProjectSetFocus(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id 查找收藏项目集
     * @param id
     * @return
     */
    public ProjectSetFocusEntity findProjectSetFocus(String id){
        return jpaTemplate.findOne(ProjectSetFocusEntity.class,id);
    }

    /**
    * 查找所有收藏的项目集
    * @return
    */
    public List<ProjectSetFocusEntity> findAllProjectSetFocus() {
        return jpaTemplate.findAll(ProjectSetFocusEntity.class);
    }

    /**
     * 根据多个ids 查找关注项目集列表
     * @param idList
     * @return
     */
    public List<ProjectSetFocusEntity> findProjectSetFocusList(List<String> idList) {
        return jpaTemplate.findList(ProjectSetFocusEntity.class,idList);
    }

    /**
     * 根据条件查询收藏项目集列表
     * @param projectSetFocusQuery
     * @return
     */
    public List<ProjectSetFocusEntity> findProjectSetFocusList(ProjectSetFocusQuery projectSetFocusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectSetFocusEntity.class)
                .eq("masterId", projectSetFocusQuery.getMasterId())
                .eq("projectSetId", projectSetFocusQuery.getProjectSetId())
                .orders(projectSetFocusQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition, ProjectSetFocusEntity.class);
    }

    /**
     * 根据条件按分页查询收藏项目集列表
     * @param projectSetFocusQuery
     * @return
     */
    public Pagination<ProjectSetFocusEntity> findProjectSetFocusPage(ProjectSetFocusQuery projectSetFocusQuery) {
        return jpaTemplate.findPage(projectSetFocusQuery,ProjectSetFocusEntity.class);
    }
}