package io.tiklab.teamwire.workitem.dao;

import io.tiklab.teamwire.workitem.entity.WorkPriorityEntity;
import io.tiklab.teamwire.workitem.model.WorkPriorityQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 事项优先级数据访问
 */
@Repository
public class WorkPriorityDao{

    private static Logger logger = LoggerFactory.getLogger(WorkPriorityDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项优先级
     * @param workPriorityEntity
     * @return
     */
    public String createWorkPriority(WorkPriorityEntity workPriorityEntity) {
        return jpaTemplate.save(workPriorityEntity,String.class);
    }

    /**
     * 更新事项优先级
     * @param workPriorityEntity
     */
    public void updateWorkPriority(WorkPriorityEntity workPriorityEntity){
        jpaTemplate.update(workPriorityEntity);
    }

    /**
     * 删除事项优先级
     * @param id
     */
    public void deleteWorkPriority(String id){
        jpaTemplate.delete(WorkPriorityEntity.class,id);
    }

    /**
     * 根据id 查找事项优先级列表
     * @param id
     * @return
     */
    public WorkPriorityEntity findWorkPriority(String id){
        return jpaTemplate.findOne(WorkPriorityEntity.class,id);
    }

    /**
     * 根据ids 查找事项优先级列表
     * @param idList
     * @return
     */
    public List<WorkPriorityEntity> findWorkPriorityList(List<String> idList) {
        return jpaTemplate.findList(WorkPriorityEntity.class,idList);
    }

    /**
    * 查找所有事项优先级列表
    * @return
    */
    public List<WorkPriorityEntity> findAllWorkPriority() {
        return jpaTemplate.findAll(WorkPriorityEntity.class);
    }

    /**
     * 根据条件查找事项优先级列表
     * @param workPriorityQuery
     * @return
     */
    public List<WorkPriorityEntity> findWorkPriorityList(WorkPriorityQuery workPriorityQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkPriorityEntity.class)
                .like("name", workPriorityQuery.getName())
                .orders(workPriorityQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkPriorityEntity.class);
    }

    /**
     * 根据条件按照分页查找事项优先级列表
     * @param workPriorityQuery
     * @return
     */
    public Pagination<WorkPriorityEntity> findWorkPriorityPage(WorkPriorityQuery workPriorityQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkPriorityEntity.class)
                .like("name", workPriorityQuery.getName())
                .orders(workPriorityQuery.getOrderParams())
                .pagination(workPriorityQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WorkPriorityEntity.class);
    }
}