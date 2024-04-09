package io.thoughtware.kanass.project.stage.dao;

import io.thoughtware.kanass.project.stage.entity.StageEntity;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.kanass.project.stage.model.StageQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

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

    public void deleteStageChildren(String id){


        jpaTemplate.delete(StageEntity.class,id);
    }

    public void deleteStageCondition(DeleteCondition deleteCondition){
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
                .in("rootId", stageQuery.getRootIds())
                .eq("rootId", stageQuery.getRootId())
                .like("stageName", stageQuery.getStageName())
                .like("treePath", stageQuery.getTreePath())
                .eq("deep", stageQuery.getDeep())
                .in("deep", stageQuery.getDeeps())
                .orders(stageQuery.getOrderParams());

        if(stageQuery.getStageParentNull() != null){
            if(stageQuery.getStageParentNull()){
                queryBuilders = queryBuilders.isNull("parentId");
            }else {
                queryBuilders = queryBuilders.isNotNull("parentId");
            }
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
        QueryBuilders queryBuilders = QueryBuilders.createQuery(StageEntity.class)
                .eq("projectId", stageQuery.getProjectId())
                .eq("parentId", stageQuery.getParentId())
                .in("rootId", stageQuery.getRootIds())
                .like("stageName", stageQuery.getStageName())
                .eq("rootId", stageQuery.getRootId())
                .eq("deep", stageQuery.getDeep())
                .like("treePath", stageQuery.getTreePath())
                .in("deep", stageQuery.getDeeps())
                .orders(stageQuery.getOrderParams());

        if(stageQuery.getStageParentNull() != null){
            if(stageQuery.getStageParentNull()){
                queryBuilders = queryBuilders.isNull("parentId");
            }else {
                queryBuilders = queryBuilders.isNotNull("parentId");
            }

        }

        QueryCondition queryCondition = queryBuilders.pagination(stageQuery.getPageParam()).get();
        return jpaTemplate.findPage(queryCondition,StageEntity.class);
    }

    public Pagination<StageEntity> findStageChildren(StageQuery stageQuery) {
        QueryBuilders queryBuilders = QueryBuilders.createQuery(StageEntity.class)
                .eq("projectId", stageQuery.getProjectId())
                .eq("parentId", stageQuery.getParentId())
                .like("stageName", stageQuery.getStageName())
                .orders(stageQuery.getOrderParams());

        if(stageQuery.getStageParentNull() != null && stageQuery.getStageParentNull()){
            queryBuilders = queryBuilders.isNull("parentId");
        }
        QueryCondition queryCondition = queryBuilders.get();
        return jpaTemplate.findPage(queryCondition,StageEntity.class);
    }

    // 获取当前阶段是第几层，没有子级的事0层，有子集的是一层层
    public Integer findStageLevel(String id){
        Integer level = new Integer(0);
        String sql = "Select id from pmc_stage where parent_id = '" + id + "'";
        List<String> stageIdList = jpaTemplate.getJdbcTemplate().queryForList(sql, String.class);
        if(stageIdList.size() > 0){
            level = 1;
            String stageIds = stageIdList.stream().map(stageId -> "'" + stageId + "'").collect(Collectors.joining(", "));
            sql = "Select count(1) as total from pmc_stage where parent_id in (" + stageIds + ")";
            Integer num = jpaTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{}, Integer.class);
            if(num > 0){
                level = 2;
            }
        }
        return  level;
    }
}