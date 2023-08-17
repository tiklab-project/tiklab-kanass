package io.tiklab.teamwire.project.wiki.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.teamwire.project.wiki.entity.ProjectWikiRepositoryEntity;
import io.tiklab.teamwire.project.wiki.model.ProjectWikiRepositoryQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 计划数据访问
 */
@Repository
public class ProjectWikiRepositoryDao {

    private static Logger logger = LoggerFactory.getLogger(ProjectWikiRepositoryDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建计划
     * @param projectWikiRepositoryEntity
     * @return
     */
    public String createProjectWikiRepository(ProjectWikiRepositoryEntity projectWikiRepositoryEntity) {
        return jpaTemplate.save(projectWikiRepositoryEntity,String.class);
    }

    /**
     * 更新计划
     * @param projectWikiRepositoryEntity
     */
    public void updateProjectWikiRepository(ProjectWikiRepositoryEntity projectWikiRepositoryEntity){
        jpaTemplate.update(projectWikiRepositoryEntity);
    }

    /**
     * 删除计划
     * @param id
     */
    public void deleteProjectWikiRepository(String id){
        jpaTemplate.delete(ProjectWikiRepositoryEntity.class,id);
    }

    public void deleteProjectWikiRepository(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找计划
     * @param id
     * @return
     */
    public ProjectWikiRepositoryEntity findProjectWikiRepository(String id){
        return jpaTemplate.findOne(ProjectWikiRepositoryEntity.class,id);
    }

    /**
    * 查找所有计划
    * @return
    */
    public List<ProjectWikiRepositoryEntity> findAllProjectWikiRepository() {
        return jpaTemplate.findAll(ProjectWikiRepositoryEntity.class);
    }

    /**
     * 根据多个id查找计划列表
     * @param idList
     * @return
     */
    public List<ProjectWikiRepositoryEntity> findProjectWikiRepositoryList(List<String> idList) {
        return jpaTemplate.findList(ProjectWikiRepositoryEntity.class,idList);
    }

    /**
     * 根据条件查找计划列表
     * @param projectWikiRepositoryQuery
     * @return
     */
    public List<ProjectWikiRepositoryEntity> findProjectWikiRepositoryList(ProjectWikiRepositoryQuery projectWikiRepositoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectWikiRepositoryEntity.class)
                .eq("projectId", projectWikiRepositoryQuery.getProjectId())
                .eq("wikiRepositoryId", projectWikiRepositoryQuery.getWikiRepositoryId())
                .get();
        return jpaTemplate.findList(queryCondition, ProjectWikiRepositoryEntity.class);
    }

    /**
     * 根据添加按分页查找计划列表
     * @param projectWikiRepositoryQuery
     * @return
     */
    public Pagination<ProjectWikiRepositoryEntity> findProjectWikiRepositoryPage(ProjectWikiRepositoryQuery projectWikiRepositoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectWikiRepositoryEntity.class)
                .get();
        return jpaTemplate.findPage(queryCondition, ProjectWikiRepositoryEntity.class);
    }
}