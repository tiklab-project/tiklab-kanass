package io.tiklab.kanass.sprint.dao;

import io.tiklab.kanass.sprint.entity.SprintFocusEntity;
import io.tiklab.kanass.sprint.model.SprintFocusQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 迭代收藏数据访问
 */
@Repository
public class SprintFocusDao{

    private static Logger logger = LoggerFactory.getLogger(SprintFocusDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建迭代收藏
     * @param sprintFocusEntity
     * @return
     */
    public String createSprintFocus(SprintFocusEntity sprintFocusEntity) {
        return jpaTemplate.save(sprintFocusEntity,String.class);
    }

    /**
     * 更新收藏的迭代
     * @param sprintFocusEntity
     */
    public void updateSprintFocus(SprintFocusEntity sprintFocusEntity){
        jpaTemplate.update(sprintFocusEntity);
    }

    /**
     * 删除收藏的迭代
     * @param id
     */
    public void deleteSprintFocus(String id){
        jpaTemplate.delete(SprintFocusEntity.class,id);
    }

    /**
     * 根据添加删除收藏的迭代
     * @param deleteCondition
     */
    public void deleteSprintFocus(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找收藏的迭代
     * @param id
     * @return
     */
    public SprintFocusEntity findSprintFocus(String id){
        return jpaTemplate.findOne(SprintFocusEntity.class,id);
    }

    /**
    * 查找所有收藏迭代
    * @return
    */
    public List<SprintFocusEntity> findAllSprintFocus() {
        return jpaTemplate.findAll(SprintFocusEntity.class);
    }

    /**
     * 根据多个迭代id,查找收藏的迭代
     * @param idList
     * @return
     */
    public List<SprintFocusEntity> findSprintFocusList(List<String> idList) {
        return jpaTemplate.findList(SprintFocusEntity.class,idList);
    }

    /**
     * 根据条件查询收藏的迭代列表
     * @param sprintFocusQuery
     * @return
     */
    public List<SprintFocusEntity> findSprintFocusList(SprintFocusQuery sprintFocusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(SprintFocusEntity.class)
                .eq("projectId", sprintFocusQuery.getProjectId())
                .eq("masterId", sprintFocusQuery.getMasterId())
                .eq("sprintId", sprintFocusQuery.getSprintId())
                .orders(sprintFocusQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, SprintFocusEntity.class);
    }

    /**
     * 根据条件按分页查询收藏的迭代列表
     * @param sprintFocusQuery
     * @return
     */
    public Pagination<SprintFocusEntity> findSprintFocusPage(SprintFocusQuery sprintFocusQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(SprintFocusEntity.class)
                .eq("projectId", sprintFocusQuery.getProjectId())
                .eq("masterId", sprintFocusQuery.getMasterId())
                .orders(sprintFocusQuery.getOrderParams())
                .pagination(sprintFocusQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,SprintFocusEntity.class);
    }

    public List<String> findFocusSprintIds(String masterId) {
        String sql = "select sprint_id from pmc_sprint_focus sf where sf.master_id = '"+ masterId + "'";
        List<String> versionIds = this.jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);
        return versionIds;
    }
}