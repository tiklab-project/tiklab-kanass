package io.tiklab.kanass.project.stage.dao;

import io.tiklab.kanass.project.stage.entity.StageWorkItemEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.project.stage.model.StageWorkItemQuery;
import io.tiklab.kanass.workitem.entity.WorkItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 项目阶段事项数据访问
 */
@Repository
public class StageWorkItemDao{

    private static Logger logger = LoggerFactory.getLogger(StageWorkItemDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建阶段事项关联关系
     * @param stageWorkItemEntity
     * @return
     */
    public String createStageWorkItem(StageWorkItemEntity stageWorkItemEntity) {
        return jpaTemplate.save(stageWorkItemEntity,String.class);
    }

    /**
     * 更新阶段事项关联关系
     * @param stageWorkItemEntity
     */
    public void updateStageWorkItem(StageWorkItemEntity stageWorkItemEntity){
        jpaTemplate.update(stageWorkItemEntity);
    }

    /**
     * 删除关联关系
     * @param id
     */
    public void deleteStageWorkItem(String id){
        jpaTemplate.delete(StageWorkItemEntity.class,id);
    }

    /**
     * 根据条件删除阶段事项关联关系
     * @param deleteCondition
     */
    public void deleteStageWorkItem(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id 精确查找关联关
     * @param id
     * @return
     */
    public StageWorkItemEntity findStageWorkItem(String id){
        return jpaTemplate.findOne(StageWorkItemEntity.class,id);
    }

    /**
    * 查找所有阶段关联事项
    * @return
    */
    public List<StageWorkItemEntity> findAllStageWorkItem() {
        return jpaTemplate.findAll(StageWorkItemEntity.class);
    }

    /**
     * 根据多个id 查找阶段关联事项列表
     * @param idList
     * @return
     */
    public List<StageWorkItemEntity> findStageWorkItemList(List<String> idList) {
        return jpaTemplate.findList(StageWorkItemEntity.class,idList);
    }

    /**
     * 根据条件查找阶段的关联事项
     * @param stageWorkItemQuery
     * @return
     */
    public List<StageWorkItemEntity> findStageWorkItemList(StageWorkItemQuery stageWorkItemQuery) {
        return jpaTemplate.findList(stageWorkItemQuery,StageWorkItemEntity.class);
    }

    /**
     * 按分页查询阶段事项关联列表
     * @param stageWorkItemQuery
     * @return
     */
    public Pagination<StageWorkItemEntity> findStageWorkItemPage(StageWorkItemQuery stageWorkItemQuery) {
        return jpaTemplate.findPage(stageWorkItemQuery,StageWorkItemEntity.class);
    }

    /**
     * 根据阶段查找关联的事项id
     * @param stageWorkItemQuery
     * @return
     */
    public List<WorkItemEntity> findWorkItemListByStage(StageWorkItemQuery stageWorkItemQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkItemEntity.class, "we")
                .leftJoin(StageWorkItemEntity.class,"sw","we.id=sw.workItemId")
                .eq("sw.stageId", stageWorkItemQuery.getStageId())
                .like("we.title", stageWorkItemQuery.getWorkItemName())
                .orders(stageWorkItemQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,WorkItemEntity.class);
    }

}