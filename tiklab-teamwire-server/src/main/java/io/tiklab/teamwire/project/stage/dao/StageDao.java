package io.tiklab.teamwire.project.stage.dao;

import io.tiklab.teamwire.project.stage.entity.StageEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.teamwire.project.stage.model.StageQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 项目阶段数据访问
 */
@Repository
public class StageDao{

    private static Logger logger = LoggerFactory.getLogger(StageDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建阶段
     * @param stageEntity
     * @return
     */
    public String createStage(StageEntity stageEntity) {
        return jpaTemplate.save(stageEntity,String.class);
    }

    /**
     * 更新阶段
     * @param stageEntity
     */
    public void updateStage(StageEntity stageEntity){
        jpaTemplate.update(stageEntity);
    }

    /**
     * 删除阶段
     * @param id
     */
    public void deleteStage(String id){
        jpaTemplate.delete(StageEntity.class,id);
    }

    public void deleteStage(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id 查找阶段
     * @param id
     * @return
     */
    public StageEntity findStage(String id){
        return jpaTemplate.findOne(StageEntity.class,id);
    }

    /**
    * 查找所有阶段
    * @return
    */
    public List<StageEntity> findAllStage() {
        return jpaTemplate.findAll(StageEntity.class);
    }

    /**
     * 根据多个id 查找阶段列表
     * @param idList
     * @return
     */
    public List<StageEntity> findStageList(List<String> idList) {
        return jpaTemplate.findList(StageEntity.class,idList);
    }

    /**
     * 根据条件查找阶段列表
     * @param stageQuery
     * @return
     */
    public List<StageEntity> findStageList(StageQuery stageQuery) {

        QueryBuilders queryBuilders = QueryBuilders.createQuery(StageEntity.class)
                .eq("projectId", stageQuery.getProjectId())
                .eq("parentId", stageQuery.getParentId())
                .like("stageName", stageQuery.getStageName())
                .orders(stageQuery.getOrderParams());

        if(stageQuery.getStageParentNull() != null && stageQuery.getStageParentNull()){
            queryBuilders = queryBuilders.isNull("parentId");
        }

        QueryCondition queryCondition = queryBuilders.get();
        return jpaTemplate.findList(queryCondition,StageEntity.class);
    }

    /**
     * 根据条件按分页查找项目阶段列表
     * @param stageQuery
     * @return
     */
    public Pagination<StageEntity> findStagePage(StageQuery stageQuery) {
        return jpaTemplate.findPage(stageQuery,StageEntity.class);
    }
}