package io.tiklab.kanass.project.appraised.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.project.appraised.entity.AppraisedEntity;
import io.tiklab.kanass.project.appraised.model.AppraisedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 项目评审数据访问
 */
//@Repository
public class AppraisedDao {
    private static Logger logger = LoggerFactory.getLogger(AppraisedDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建项目评审
     * @param appraisedEntity
     * @return
     */
    public String createAppraised(AppraisedEntity appraisedEntity) {
        return jpaTemplate.save(appraisedEntity,String.class);
    }

    /**
     * 更新项目评审
     * @param appraisedEntity
     */
    public void updateAppraised(AppraisedEntity appraisedEntity) {
        jpaTemplate.update(appraisedEntity);
    }

    /**
     * 删除项目评审
     * @param id
     */
    public void deleteAppraised(String id){
        jpaTemplate.delete(AppraisedEntity.class,id);
    }

    /**
     * 根据id查找项目评审
     * @param id
     * @return
     */
    public AppraisedEntity findAppraised(String id){
        return jpaTemplate.findOne(AppraisedEntity.class,id);
    }

    /**
     * 查找所有项目评审
     * @return
     */
    public List<AppraisedEntity> findAllAppraised() {
        return jpaTemplate.findAll(AppraisedEntity.class);
    }

    /**
     * 根据多个id 查找项目评审列表
     * @param idList
     * @return
     */
    public List<AppraisedEntity> findAppraisedList(List<String> idList) {
        return jpaTemplate.findList(AppraisedEntity.class,idList);
    }

    public List<AppraisedEntity> findAppraisedList(AppraisedQuery appraisedQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppraisedEntity.class)
                .eq("projectId", appraisedQuery.getProjectId())
                .like("name", appraisedQuery.getName())
                .eq("appraisedState", appraisedQuery.getAppraisedState())
                .eq("stageId", appraisedQuery.getStageId())
                .in("stageId", appraisedQuery.getStageIds())
                .orders(appraisedQuery.getOrderParams())
                .get();

        return jpaTemplate.findList(queryCondition,AppraisedEntity.class);
    }

    public Pagination<AppraisedEntity> findAppraisedPage(AppraisedQuery appraisedQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(AppraisedEntity.class)
                .eq("projectId", appraisedQuery.getProjectId())
                .like("name", appraisedQuery.getName())
                .eq("appraisedState", appraisedQuery.getAppraisedState())
                .eq("master", appraisedQuery.getMasterId())
                .eq("builder", appraisedQuery.getAppraisedStates())
                .eq("stageId", appraisedQuery.getStageId())
                .in("stageId", appraisedQuery.getStageIds())
                .orders(appraisedQuery.getOrderParams())
                .pagination(appraisedQuery.getPageParam())
                .get();

        return jpaTemplate.findPage(queryCondition, AppraisedEntity.class);
    }
}
