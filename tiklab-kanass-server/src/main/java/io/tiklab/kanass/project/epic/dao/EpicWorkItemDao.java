package io.tiklab.kanass.project.epic.dao;

import io.tiklab.kanass.project.epic.entity.EpicWorkItemEntity;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.project.epic.model.EpicWorkItemQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.kanass.workitem.entity.WorkItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 史诗事项关联关系数据访问
 */
@Repository
public class EpicWorkItemDao{

    private static Logger logger = LoggerFactory.getLogger(EpicWorkItemDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建史诗事项关联
     * @param epicWorkItemEntity
     * @return
     */
    public String createEpicWorkItem(EpicWorkItemEntity epicWorkItemEntity) {
        return jpaTemplate.save(epicWorkItemEntity,String.class);
    }

    /**
     * 更新关联史诗事项关联关系
     * @param epicWorkItemEntity
     */
    public void updateEpicWorkItem(EpicWorkItemEntity epicWorkItemEntity){
        jpaTemplate.update(epicWorkItemEntity);
    }

    /**
     * 删除事项史诗关联
     * @param id
     */
    public void deleteEpicWorkItem(String id){
        jpaTemplate.delete(EpicWorkItemEntity.class,id);
    }

    /**
     * 根据条件删除事项史诗关联关系
     * @param deleteCondition
     */
    public void deleteEpicWorkItem(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找史诗下事项列表
     * @param id
     * @return
     */
    public EpicWorkItemEntity findEpicWorkItem(String id){
        return jpaTemplate.findOne(EpicWorkItemEntity.class,id);
    }

    /**
    * 查找所有史诗关联的事项
    * @return
    */
    public List<EpicWorkItemEntity> findAllEpicWorkItem() {
        return jpaTemplate.findAll(EpicWorkItemEntity.class);
    }

    /**
     *
     * @param idList
     * @return
     */
    public List<EpicWorkItemEntity> findEpicWorkItemList(List<String> idList) {
        return jpaTemplate.findList(EpicWorkItemEntity.class,idList);
    }

    public List<EpicWorkItemEntity> findEpicWorkItemList(EpicWorkItemQuery epicWorkItemQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(EpicWorkItemEntity.class)
                .eq("epicId", epicWorkItemQuery.getEpicId())
                .orders(epicWorkItemQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,EpicWorkItemEntity.class);
    }

    public List<WorkItemEntity> findWorkItemListByEpic(EpicWorkItemQuery epicWorkItemQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(WorkItemEntity.class, "we")
                .leftJoin(EpicWorkItemEntity.class,"ew","we.id=ew.workItemId")
                .eq("ew.epicId", epicWorkItemQuery.getEpicId())
                .like("we.title", epicWorkItemQuery.getWorkItemName())
                .orders(epicWorkItemQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,WorkItemEntity.class);
    }

    public Pagination<EpicWorkItemEntity> findEpicWorkItemPage(EpicWorkItemQuery epicWorkItemQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(EpicWorkItemEntity.class)
                .eq("epicId", epicWorkItemQuery.getEpicId())
                .orders(epicWorkItemQuery.getOrderParams())
                .pagination(epicWorkItemQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,EpicWorkItemEntity.class);
    }

//    public Pagination<EpicWorkItemEntity> findUnEpicWorkItemPage(EpicWorkItemQuery epicWorkItemQuery) {
//        return jpaTemplate.findPage(epicWorkItemQuery,EpicWorkItemEntity.class);
//    }
}