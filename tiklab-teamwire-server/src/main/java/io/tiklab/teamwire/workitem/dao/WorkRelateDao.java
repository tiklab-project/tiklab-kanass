package io.tiklab.teamwire.workitem.dao;

import io.tiklab.teamwire.workitem.entity.WorkItemEntity;
import io.tiklab.teamwire.workitem.entity.WorkRelateEntity;
import io.tiklab.teamwire.workitem.model.WorkRelateQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * 关联事项数据访问
 */
@Repository
public class WorkRelateDao{

    private static Logger logger = LoggerFactory.getLogger(WorkRelateDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建关联事项
     * @param workRelateEntity
     * @return
     */
    public String createWorkRelate(WorkRelateEntity workRelateEntity) {
        workRelateEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return jpaTemplate.save(workRelateEntity,String.class);
    }

    /**
     * 更新关联事项
     * @param workRelateEntity
     */
    public void updateWorkRelate(WorkRelateEntity workRelateEntity){
        jpaTemplate.update(workRelateEntity);
    }

    /**
     * 删除关联事项
     * @param id
     */
    public void deleteWorkRelate(String id){
        jpaTemplate.delete(WorkRelateEntity.class,id);
    }

    /**
     * 根据id查找关联事项
     * @param id
     * @return
     */
    public WorkRelateEntity findWorkRelate(String id){
        return jpaTemplate.findOne(WorkRelateEntity.class,id);
    }

    /**
    * 查找所有关联事项
    * @return
    */
    public List<WorkRelateEntity> findAllWorkRelate() {
        return jpaTemplate.findAll(WorkRelateEntity.class);
    }

    /**
     * 查询关联事项列表
     * @param workRelateQuery
     * @return
     */
    public List<WorkRelateEntity> findWorkRelateList(WorkRelateQuery workRelateQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkRelateEntity.class,"wr")
                .leftJoin(WorkItemEntity.class,"wi","wr.relateItemId=wi.id")
                .eq("wr.workItemId", workRelateQuery.getWorkItemId())
                .like("wi.title", workRelateQuery.getTitle())
                .orders(workRelateQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, WorkRelateEntity.class);
    }


    /**
     * 按分页查询关联事项列表
     * @param workRelateQuery
     * @return
     */
    public Pagination<WorkRelateEntity> findWorkRelatePage(WorkRelateQuery workRelateQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkRelateEntity.class, "wr")
                .leftJoin(WorkItemEntity.class,"wi","wr.relateItemId=wi.id")
                .eq("wr.workItemId", workRelateQuery.getWorkItemId())
                .like("wi.title", workRelateQuery.getTitle())
                .orders(workRelateQuery.getOrderParams())
                .pagination(workRelateQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, WorkRelateEntity.class);
    }
}