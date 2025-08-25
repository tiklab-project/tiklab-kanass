package io.tiklab.kanass.project.wiki.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.project.wiki.entity.ProjectDocumentEntity;
import io.tiklab.kanass.project.wiki.model.ProjectDocumentQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDocumentDao {
    private static Logger logger = LoggerFactory.getLogger(ProjectDocumentDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项文档
     * @param projectDocumentEntity
     * @return
     */
    public String createProjectDocument(ProjectDocumentEntity projectDocumentEntity) {
        return jpaTemplate.save(projectDocumentEntity,String.class);
    }

    /**
     * 更新事项文档
     * @param projectDocumentEntity
     */
    public void updateProjectDocument(ProjectDocumentEntity projectDocumentEntity){
        jpaTemplate.update(projectDocumentEntity);
    }

    /**
     * 通过文档id删除事项文档
     * @param id
     */
    public void deleteProjectDocument(String id){
        jpaTemplate.delete(ProjectDocumentEntity.class,id);
    }

    public void deleteProjectDocument(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找事项文档
     * @param id
     * @return
     */
    public ProjectDocumentEntity findProjectDocument(String id){
        return jpaTemplate.findOne(ProjectDocumentEntity.class,id);
    }

    /**
     * 查找所有事项文档
     * @return
     */
    public List<ProjectDocumentEntity> findAllProjectDocument() {
        return jpaTemplate.findAll(ProjectDocumentEntity.class);
    }

    /**
     * 根据ids 查找事项文档
     * @param idList
     * @return
     */
    public List<ProjectDocumentEntity> findProjectDocumentList(List<String> idList) {
        return jpaTemplate.findList(ProjectDocumentEntity.class,idList);
    }

    /**
     * 根据条件查询事项文档列表
     * @param projectDocumentQuery
     * @return
     */
    public List<ProjectDocumentEntity> findProjectDocumentList(ProjectDocumentQuery projectDocumentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectDocumentEntity.class)
                .eq("projectId", projectDocumentQuery.getProjectId())
                .eq("documentId", projectDocumentQuery.getDocumentId())
                .eq("repositoryId", projectDocumentQuery.getRepositoryId())
                .in("repositoryId", projectDocumentQuery.getRepositoryIds())
                .orders(projectDocumentQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ProjectDocumentEntity.class);
    }

    /**
     * 根据条件按分页查询事项文档列表
     * @param projectDocumentQuery
     * @return
     */
    public Pagination<ProjectDocumentEntity> findProjectDocumentPage(ProjectDocumentQuery projectDocumentQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectDocumentEntity.class)
                .eq("workItemId", projectDocumentQuery.getWorkItemId())
                .eq("documentId", projectDocumentQuery.getDocumentId())
                .notIn("id", projectDocumentQuery.getIdNotIn())
//                .like("name", projectDocumentQuery.getName())
                .eq("repositoryId", projectDocumentQuery.getRepositoryId())
                .in("repositoryId", projectDocumentQuery.getRepositoryIds())
                .eq("projectId", projectDocumentQuery.getProjectId())
                .orders(projectDocumentQuery.getOrderParams())
                .pagination(projectDocumentQuery.getPageParam())
                .get();

            return jpaTemplate.findPage(queryCondition, ProjectDocumentEntity.class);
    }
}
