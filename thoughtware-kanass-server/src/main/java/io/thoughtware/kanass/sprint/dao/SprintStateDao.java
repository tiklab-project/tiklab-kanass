package io.thoughtware.kanass.sprint.dao;

import io.thoughtware.kanass.sprint.entity.SprintStateEntity;
import io.thoughtware.kanass.sprint.model.SprintStateQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 迭代状态数据访问
 */
@Repository
public class SprintStateDao{

    private static Logger logger = LoggerFactory.getLogger(SprintStateDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建迭代状态
     * @param sprintStateEntity
     * @return
     */
    public String createSprintState(SprintStateEntity sprintStateEntity) {
        return jpaTemplate.save(sprintStateEntity,String.class);
    }

    /**
     * 更新迭代状态
     * @param sprintStateEntity
     */
    public void updateSprintState(SprintStateEntity sprintStateEntity){
        jpaTemplate.update(sprintStateEntity);
    }

    /**
     * 删除迭代状态
     * @param id
     */
    public void deleteSprintState(String id){
        jpaTemplate.delete(SprintStateEntity.class,id);
    }

    public void deleteSprintState(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找迭代状态
     * @param id
     * @return
     */
    public SprintStateEntity findSprintState(String id){
        return jpaTemplate.findOne(SprintStateEntity.class,id);
    }

    /**
    * 查找所有迭代状态
    * @return
    */
    public List<SprintStateEntity> findAllSprintState() {
        return jpaTemplate.findAll(SprintStateEntity.class);
    }

    /**
     * 根据ids 查找迭代状态列表
     * @param idList
     * @return
     */
    public List<SprintStateEntity> findSprintStateList(List<String> idList) {
        return jpaTemplate.findList(SprintStateEntity.class,idList);
    }

    /**
     * 根据条件查询迭代状态列表
     * @param sprintStateQuery
     * @return
     */
    public List<SprintStateEntity> findSprintStateList(SprintStateQuery sprintStateQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(SprintStateEntity.class)
                .orders(sprintStateQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, SprintStateEntity.class);
    }

    /**
     * 根据条件按分页查询迭代状态列表
     * @param sprintStateQuery
     * @return
     */
    public Pagination<SprintStateEntity> findSprintStatePage(SprintStateQuery sprintStateQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(SprintStateEntity.class)
                .orders(sprintStateQuery.getOrderParams())
                .pagination(sprintStateQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, SprintStateEntity.class);
    }
}