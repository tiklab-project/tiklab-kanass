package io.tiklab.teamwire.project.test.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.teamwire.project.test.entity.ProjectTestRepositoryEntity;
import io.tiklab.teamwire.project.test.model.ProjectTestRepositoryQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 计划数据访问
 */
@Repository
public class ProjectTestRepositoryDao {

    private static Logger logger = LoggerFactory.getLogger(ProjectTestRepositoryDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建计划
     * @param projectTestRepositoryEntity
     * @return
     */
    public String createProjectTestRepository(ProjectTestRepositoryEntity projectTestRepositoryEntity) {
        return jpaTemplate.save(projectTestRepositoryEntity,String.class);
    }

    /**
     * 更新计划
     * @param projectTestRepositoryEntity
     */
    public void updateProjectTestRepository(ProjectTestRepositoryEntity projectTestRepositoryEntity){
        jpaTemplate.update(projectTestRepositoryEntity);
    }

    /**
     * 删除计划
     * @param id
     */
    public void deleteProjectTestRepository(String id){
        jpaTemplate.delete(ProjectTestRepositoryEntity.class,id);
    }

    public void deleteProjectTestRepository(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找计划
     * @param id
     * @return
     */
    public ProjectTestRepositoryEntity findProjectTestRepository(String id){
        return jpaTemplate.findOne(ProjectTestRepositoryEntity.class,id);
    }

    /**
    * 查找所有计划
    * @return
    */
    public List<ProjectTestRepositoryEntity> findAllProjectTestRepository() {
        return jpaTemplate.findAll(ProjectTestRepositoryEntity.class);
    }

    /**
     * 根据多个id查找计划列表
     * @param idList
     * @return
     */
    public List<ProjectTestRepositoryEntity> findProjectTestRepositoryList(List<String> idList) {
        return jpaTemplate.findList(ProjectTestRepositoryEntity.class,idList);
    }

    /**
     * 根据条件查找计划列表
     * @param projectTestRepositoryQuery
     * @return
     */
    public List<ProjectTestRepositoryEntity> findProjectTestRepositoryList(ProjectTestRepositoryQuery projectTestRepositoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectTestRepositoryEntity.class)
                .eq("projectId", projectTestRepositoryQuery.getProjectId())
                .eq("testRepositoryId", projectTestRepositoryQuery.getTestRepositoryId())
                .get();
        return jpaTemplate.findList(queryCondition, ProjectTestRepositoryEntity.class);
    }

    /**
     * 根据添加按分页查找计划列表
     * @param projectTestRepositoryQuery
     * @return
     */
    public Pagination<ProjectTestRepositoryEntity> findProjectTestRepositoryPage(ProjectTestRepositoryQuery projectTestRepositoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectTestRepositoryEntity.class)
                .get();
        return jpaTemplate.findPage(queryCondition, ProjectTestRepositoryEntity.class);
    }
}