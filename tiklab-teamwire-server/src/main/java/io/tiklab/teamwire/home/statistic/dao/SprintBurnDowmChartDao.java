package io.tiklab.teamwire.home.statistic.dao;

import io.tiklab.teamwire.home.statistic.entity.SprintBurnDowmChartEntity;
import io.tiklab.teamwire.home.statistic.model.SprintBurnDowmChartQuery;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 迭代事项数据动态记录数据访问
 */
@Repository
public class SprintBurnDowmChartDao{

    private static Logger logger = LoggerFactory.getLogger(SprintBurnDowmChartDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建迭代的数据动态记录
     * @param sprintBurnDowmChartEntity
     * @return
     */

    public String createSprintBurnDowmChart(SprintBurnDowmChartEntity sprintBurnDowmChartEntity) {
        //统计总数
        String sql = "select count(1) as totalCount from pmc_work_item t";
        sql = sql.concat(" where t.sprint_id = ?");
        String sprintId = sprintBurnDowmChartEntity.getSprintId();
        Integer totalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);
        sprintBurnDowmChartEntity.setTotalWorkitemCount(totalCount);

        // 统计已完成的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.sprint_id= ? and t.work_status_code = 'DONE'");
        Integer endTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);
        sprintBurnDowmChartEntity.setEndWorkitemCount(endTotalCount);

        // 统计进行中的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.sprint_id= ? and t.work_status_code = 'START'");
        Integer progressTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);
        sprintBurnDowmChartEntity.setProgressWorkitemCount(progressTotalCount);

        // 统计未开始的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.sprint_id= ? and t.work_status_code = 'TODO'");
        Integer noStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);
        sprintBurnDowmChartEntity.setEndWorkitemCount(noStartTotalCount);

        // 统计未完成的事项
        Integer remainTotalCount = totalCount - endTotalCount;
        sprintBurnDowmChartEntity.setRemainWorkitemCount(remainTotalCount);

        //所有缺陷
        sql = "select count(1) as totalBugCount from pmc_work_item t";
        sql = sql.concat(" where t.sprint_id = ? and t.work_type_code = 'defect'");
        Integer totalBugCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);
        sprintBurnDowmChartEntity.setTotalBugCount(totalBugCount);

        // 统计已完成的缺陷
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.sprint_id = ? and t.work_status_code = 'DONE' and t.work_type_code = 'defect'");
        Integer endBugTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);
        sprintBurnDowmChartEntity.setEndBugCount(endBugTotalCount);

        // 统计进行中的缺陷
        sql = "select count(1) as progressTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.sprint_id = ? and t.work_status_code = 'START' and t.work_type_code = 'defect'");
        Integer progressBugTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);
        sprintBurnDowmChartEntity.setProgressBugCount(progressBugTotalCount);

        // 统计未开始的缺陷
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.sprint_id = ? and t.work_status_code = 'TODO' and t.work_type_code = 'defect'");
        Integer noBugStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);
        sprintBurnDowmChartEntity.setNostartBugCount(noBugStartTotalCount);

        // 统计未完成的缺陷
        Integer remainBugTotalCount = totalBugCount - endBugTotalCount;
        sprintBurnDowmChartEntity.setRemainBugCount(remainBugTotalCount);

        //所有需求
        sql = "select count(1) as totalDemandCount from pmc_work_item t";
        sql = sql.concat(" where t.sprint_id = ? and t.work_type_code = 'demand'");
        Integer totalDemandCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);
        sprintBurnDowmChartEntity.setTotalDemandCount(totalDemandCount);

        // 统计已完成的需求
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.sprint_id = ? and t.work_status_code = 'DONE' and t.work_type_code = 'demand'");
        Integer endDemandTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);
        sprintBurnDowmChartEntity.setEndDemandCount(endDemandTotalCount);

        // 统计进行中的需求
        sql = "select count(1) as progressTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.sprint_id = ? and t.work_status_code = 'START' and t.work_type_code = 'demand'");
        Integer progressDemandTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);
        sprintBurnDowmChartEntity.setProgressDemandCount(progressDemandTotalCount);

        // 统计未开始的需求
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.sprint_id= ? and t.work_status_code = 'TODO' and t.work_type_code = 'demand'");
        Integer noDemandStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);
        sprintBurnDowmChartEntity.setNostartDemandCount(noDemandStartTotalCount);

        // 统计未完成的需求
        Integer remainDemandTotalCount = totalDemandCount - endDemandTotalCount;
        sprintBurnDowmChartEntity.setRemainDemandCount(remainDemandTotalCount);

        //所有任务
        sql = "select count(1) as totalTaskCount from pmc_work_item t";
        sql = sql.concat(" where t.sprint_id = ? and t.work_type_code = 'task'");
        Integer totalTaskCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);
        sprintBurnDowmChartEntity.setTotalTaskCount(totalTaskCount);

        // 统计已完成的任务
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.sprint_id = ? and t.work_status_code = 'DONE' and t.work_type_code = 'task'");
        Integer endTaskTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);
        sprintBurnDowmChartEntity.setEndTaskCount(endTaskTotalCount);

        // 统计进行中的任务
        sql = "select count(1) as progressTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.sprint_id = ? and t.work_status_code = 'START' and t.work_type_code = 'task'");
        Integer progressTaskTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);
        sprintBurnDowmChartEntity.setProgressTaskCount(progressTaskTotalCount);

        // 统计未开始的任务
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.sprint_id = ? and t.work_status_code = 'TODO' and t.work_type_code = 'task'");
        Integer noTaskStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{sprintId},Integer.class);
        sprintBurnDowmChartEntity.setNostartTaskCount(noTaskStartTotalCount);

        // 统计未完成的任务
        Integer remainTaskTotalCount = totalTaskCount - endTaskTotalCount;
        sprintBurnDowmChartEntity.setRemainTaskCount(remainTaskTotalCount);

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formater.format(new Date());
        sprintBurnDowmChartEntity.setRecordTime(format);

        return jpaTemplate.save(sprintBurnDowmChartEntity,String.class);
    }

    /**
     * 更新动态记录
     * @param sprintBurnDowmChartEntity
     */
    public void updateSprintBurnDowmChart(SprintBurnDowmChartEntity sprintBurnDowmChartEntity){
        jpaTemplate.update(sprintBurnDowmChartEntity);
    }

    /**
     * 删除动态记录
     * @param id
     */
    public void deleteSprintBurnDowmChart(String id){
        jpaTemplate.delete(SprintBurnDowmChartEntity.class,id);
    }

    public void deleteSprintBurnDowmChart(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找记录
     * @param id
     * @return
     */
    public SprintBurnDowmChartEntity findSprintBurnDowmChart(String id){
        return jpaTemplate.findOne(SprintBurnDowmChartEntity.class,id);
    }

    /**
    * 查找所有的记录
    * @return
    */
    public List<SprintBurnDowmChartEntity> findAllSprintBurnDowmChart() {
        return jpaTemplate.findAll(SprintBurnDowmChartEntity.class);
    }

    /**
     * 根据多个id搜索迭代的事项数据记录
     * @param idList
     * @return
     */
    public List<SprintBurnDowmChartEntity> findSprintBurnDowmChartList(List<String> idList) {
        return jpaTemplate.findList(SprintBurnDowmChartEntity.class,idList);
    }

    /**
     * 根据搜索条件搜索迭代的事项数据记录
     * @param sprintBurnDowmChartQuery
     * @return
     */
    public List<SprintBurnDowmChartEntity> findSprintBurnDowmChartList(SprintBurnDowmChartQuery sprintBurnDowmChartQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(SprintBurnDowmChartEntity.class)
                .orders(sprintBurnDowmChartQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,SprintBurnDowmChartEntity.class);
    }

    /**
     * 根据搜索条件按分页搜索迭代的事项数据记录
     * @param sprintBurnDowmChartQuery
     * @return
     */
    public Pagination<SprintBurnDowmChartEntity> findSprintBurnDowmChartPage(SprintBurnDowmChartQuery sprintBurnDowmChartQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(SprintBurnDowmChartEntity.class)
                .eq("sprintId", sprintBurnDowmChartQuery.getSprintId())
                .orders(sprintBurnDowmChartQuery.getOrderParams())
                .pagination(sprintBurnDowmChartQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,SprintBurnDowmChartEntity.class);
    }
}