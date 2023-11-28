package io.tiklab.teamwire.workitem.dao;

import io.tiklab.teamwire.workitem.entity.WorkAttachEntity;
import io.tiklab.teamwire.workitem.model.WorkAttachQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户数据操作
 */
@Repository
public class WorkAttachDao{

    private static Logger logger = LoggerFactory.getLogger(WorkAttachDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建事项附件
     * @param workAttachEntity
     * @return
     */
    public String createWorkAttach(WorkAttachEntity workAttachEntity) {
        return jpaTemplate.save(workAttachEntity,String.class);
    }

    /**
     * 更新事项附件
     * @param workAttachEntity
     */
    public void updateWorkAttach(WorkAttachEntity workAttachEntity){
        jpaTemplate.update(workAttachEntity);
    }

    /**
     * 删除事项附件
     * @param id
     */
    public void deleteWorkAttach(String id){
        jpaTemplate.delete(WorkAttachEntity.class,id);
    }

    /**
     * 根据id查找事项附件
     * @param id
     * @return
     */
    public WorkAttachEntity findWorkAttach(String id){
        return jpaTemplate.findOne(WorkAttachEntity.class,id);
    }

    /**
    * 查找所有事项附件
    * @return
    */
    public List<WorkAttachEntity> findAllWorkAttach() {
        return jpaTemplate.findAll(WorkAttachEntity.class);
    }

    /**
     * 根据条件查询事项附件列表
     * @param workAttachQuery
     * @return
     */
    public List<WorkAttachEntity> findWorkAttachList(WorkAttachQuery workAttachQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkAttachEntity.class)
                .eq("workItemId", workAttachQuery.getWorkItemId())
                .orders(workAttachQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkAttachEntity.class);
    }

    /**
     * 根据条件按分页查询事项附件
     * @param workAttachQuery
     * @return
     */
    public Pagination<WorkAttachEntity> findWorkAttachPage(WorkAttachQuery workAttachQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkAttachEntity.class)
                .eq("workItemId", workAttachQuery.getWorkItemId())
                .orders(workAttachQuery.getOrderParams())
                .pagination(workAttachQuery.getPageParam())
                .get();

        return jpaTemplate.findPage(queryCondition, WorkAttachEntity.class);
    }
}