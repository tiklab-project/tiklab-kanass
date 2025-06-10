package io.tiklab.kanass.project.test.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.project.test.entity.ProjectTestRepositoryPlanEntity;
import io.tiklab.kanass.project.test.model.ProjectTestRepositoryPlanQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectTestRepositoryPlanDao {
    private static Logger logger = LoggerFactory.getLogger(ProjectTestRepositoryPlanDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建计划
     * @param projectTestRepositoryPlanEntity
     * @return
     */
    public String createProjectTestRepositoryPlan(ProjectTestRepositoryPlanEntity projectTestRepositoryPlanEntity) {
        return jpaTemplate.save(projectTestRepositoryPlanEntity,String.class);
    }

    /**
     * 更新计划
     * @param projectTestRepositoryPlanEntity
     */
    public void updateProjectTestRepositoryPlan(ProjectTestRepositoryPlanEntity projectTestRepositoryPlanEntity){
        jpaTemplate.update(projectTestRepositoryPlanEntity);
    }

    /**
     * 删除计划
     * @param id
     */
    public void deleteProjectTestRepositoryPlan(String id){
        jpaTemplate.delete(ProjectTestRepositoryPlanEntity.class,id);
    }

    public void deleteProjectTestRepositoryPlan(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找计划
     * @param id
     * @return
     */
    public ProjectTestRepositoryPlanEntity findProjectTestRepositoryPlan(String id){
        return jpaTemplate.findOne(ProjectTestRepositoryPlanEntity.class,id);
    }

    /**
     * 查找所有计划
     * @return
     */
    public List<ProjectTestRepositoryPlanEntity> findAllProjectTestRepositoryPlan() {
        return jpaTemplate.findAll(ProjectTestRepositoryPlanEntity.class);
    }

    /**
     * 根据多个id查找计划列表
     * @param idList
     * @return
     */
    public List<ProjectTestRepositoryPlanEntity> findProjectTestRepositoryPlanList(List<String> idList) {
        return jpaTemplate.findList(ProjectTestRepositoryPlanEntity.class,idList);
    }

    /**
     * 根据条件查找计划列表
     * @param projectTestRepositoryPlanQuery
     * @return
     */
    public List<ProjectTestRepositoryPlanEntity> findProjectTestRepositoryPlanList(ProjectTestRepositoryPlanQuery projectTestRepositoryPlanQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectTestRepositoryPlanEntity.class)
                .eq("projectId", projectTestRepositoryPlanQuery.getProjectId())
                .eq("testRepositoryId", projectTestRepositoryPlanQuery.getTestRepositoryId())
                .eq("planId", projectTestRepositoryPlanQuery.getPlanId())
                .get();
        return jpaTemplate.findList(queryCondition, ProjectTestRepositoryPlanEntity.class);
    }

    /**
     * 根据添加按分页查找计划列表
     * @param projectTestRepositoryPlanQuery
     * @return
     */
    public Pagination<ProjectTestRepositoryPlanEntity> findProjectTestRepositoryPlanPage(ProjectTestRepositoryPlanQuery projectTestRepositoryPlanQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectTestRepositoryPlanEntity.class)
                .eq("projectId", projectTestRepositoryPlanQuery.getProjectId())
                .eq("test_repositoryId", projectTestRepositoryPlanQuery.getTestRepositoryId())
                .eq("planId", projectTestRepositoryPlanQuery.getPlanId())
                .get();
        return jpaTemplate.findPage(queryCondition, ProjectTestRepositoryPlanEntity.class);
    }
}
