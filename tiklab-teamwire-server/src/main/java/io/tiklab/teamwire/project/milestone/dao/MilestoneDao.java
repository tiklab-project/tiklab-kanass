package io.tiklab.teamwire.project.milestone.dao;

import io.tiklab.teamwire.project.milestone.entity.MilestoneEntity;
import io.tiklab.teamwire.project.milestone.model.MilestoneQuery;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 里程碑数据访问
 */
@Repository
public class MilestoneDao{

    private static Logger logger = LoggerFactory.getLogger(MilestoneDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建里程碑
     * @param milestoneEntity
     * @return
     */
    public String createMilestone(MilestoneEntity milestoneEntity) {
        return jpaTemplate.save(milestoneEntity,String.class);
    }

    /**
     * 更新里程碑
     * @param milestoneEntity
     */
    public void updateMilestone(MilestoneEntity milestoneEntity){
        jpaTemplate.update(milestoneEntity);
    }

    /**
     * 根据id删除里程碑
     * @param id
     */
    public void deleteMilestone(String id){
        jpaTemplate.delete(MilestoneEntity.class,id);
    }

    public void deleteMilestone(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找里程碑
     * @param id
     * @return
     */
    public MilestoneEntity findMilestone(String id){
        return jpaTemplate.findOne(MilestoneEntity.class,id);
    }

    /**
    * 查找所有里程碑
    * @return
    */
    public List<MilestoneEntity> findAllMilestone() {
        return jpaTemplate.findAll(MilestoneEntity.class);
    }

    /**
     * 根据id列表查找里程碑
     * @param idList
     * @return
     */
    public List<MilestoneEntity> findMilestoneList(List<String> idList) {
        return jpaTemplate.findList(MilestoneEntity.class,idList);
    }

    /**
     * 根据条件查找里程碑
     * @param milestoneQuery
     * @return
     */
    public List<MilestoneEntity> findMilestoneList(MilestoneQuery milestoneQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(MilestoneEntity.class)
                .eq("projectId", milestoneQuery.getProjectId())
                .like("name", milestoneQuery.getName())
                .orders(milestoneQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,MilestoneEntity.class);
    }

    /**
     * 根据条件按照分页查找里程碑
     * @param milestoneQuery
     * @return
     */
    public Pagination<MilestoneEntity> findMilestonePage(MilestoneQuery milestoneQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(MilestoneEntity.class)
                .like("name", milestoneQuery.getName())
                .eq("projectId", milestoneQuery.getProjectId())
                .orders(milestoneQuery.getOrderParams())
                .pagination(milestoneQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,MilestoneEntity.class);
    }
}