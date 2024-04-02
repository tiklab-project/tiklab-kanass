package io.thoughtware.kanass.home.statistic.dao;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.kanass.home.statistic.entity.StageBurnDowmChartEntity;
import io.thoughtware.kanass.home.statistic.model.StageBurnDowmChartQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 迭代事项数据动态记录数据访问
 */
@Repository
public class StageBurnDowmChartDao {

    private static Logger logger = LoggerFactory.getLogger(StageBurnDowmChartDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建迭代的数据动态记录
     * @param stageBurnDowmChartEntity
     * @return
     */
    public String createStageBurnDowmChart(StageBurnDowmChartEntity stageBurnDowmChartEntity) {
        //统计总数
        String sql = "select count(1) as totalCount from pmc_work_item t";
        sql = sql.concat(" where t.stage_id = ?");
        String stageId = stageBurnDowmChartEntity.getStageId();
        Integer totalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{stageId},Integer.class);
        stageBurnDowmChartEntity.setTotalWorkitemCount(totalCount);

        // 统计已完成的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.stage_id= ? and t.work_status_code = 'DONE'");
        Integer endTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{stageId},Integer.class);
        stageBurnDowmChartEntity.setEndWorkitemCount(endTotalCount);

        // 统计进行中的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.stage_id= ? and t.work_status_code = 'START'");
        Integer progressTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{stageId},Integer.class);
        stageBurnDowmChartEntity.setProgressWorkitemCount(progressTotalCount);

        // 统计未开始的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.stage_id= ? and t.work_status_code = 'TODO'");
        Integer noStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{stageId},Integer.class);
        stageBurnDowmChartEntity.setEndWorkitemCount(noStartTotalCount);

        // 统计未完成的事项
        Integer remainTotalCount = totalCount - endTotalCount;
        stageBurnDowmChartEntity.setRemainWorkitemCount(remainTotalCount);

        //所有缺陷
        sql = "select count(1) as totalBugCount from pmc_work_item t";
        sql = sql.concat(" where t.stage_id = ? and t.work_type_code = 'defect'");
        Integer totalBugCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{stageId},Integer.class);
        stageBurnDowmChartEntity.setTotalBugCount(totalBugCount);

        // 统计已完成的缺陷
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.stage_id = ? and t.work_status_code = 'DONE' and t.work_type_code = 'defect'");
        Integer endBugTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{stageId},Integer.class);
        stageBurnDowmChartEntity.setEndBugCount(endBugTotalCount);

        // 统计进行中的缺陷
        sql = "select count(1) as progressTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.stage_id = ? and t.work_status_code = 'START' and t.work_type_code = 'defect'");
        Integer progressBugTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{stageId},Integer.class);
        stageBurnDowmChartEntity.setProgressBugCount(progressBugTotalCount);

        // 统计未开始的缺陷
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.stage_id = ? and t.work_status_code = 'TODO' and t.work_type_code = 'defect'");
        Integer noBugStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{stageId},Integer.class);
        stageBurnDowmChartEntity.setNostartBugCount(noBugStartTotalCount);

        // 统计未完成的缺陷
        Integer remainBugTotalCount = totalBugCount - endBugTotalCount;
        stageBurnDowmChartEntity.setRemainBugCount(remainBugTotalCount);

        //所有需求
        sql = "select count(1) as totalDemandCount from pmc_work_item t";
        sql = sql.concat(" where t.stage_id = ? and t.work_type_code = 'demand'");
        Integer totalDemandCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{stageId},Integer.class);
        stageBurnDowmChartEntity.setTotalDemandCount(totalDemandCount);

        // 统计已完成的需求
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.stage_id = ? and t.work_status_code = 'DONE' and t.work_type_code = 'demand'");
        Integer endDemandTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{stageId},Integer.class);
        stageBurnDowmChartEntity.setEndDemandCount(endDemandTotalCount);

        // 统计进行中的需求
        sql = "select count(1) as progressTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.stage_id = ? and t.work_status_code = 'START' and t.work_type_code = 'demand'");
        Integer progressDemandTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{stageId},Integer.class);
        stageBurnDowmChartEntity.setProgressDemandCount(progressDemandTotalCount);

        // 统计未开始的需求
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.stage_id= ? and t.work_status_code = 'TODO' and t.work_type_code = 'demand'");
        Integer noDemandStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{stageId},Integer.class);
        stageBurnDowmChartEntity.setNostartDemandCount(noDemandStartTotalCount);

        // 统计未完成的需求
        Integer remainDemandTotalCount = totalDemandCount - endDemandTotalCount;
        stageBurnDowmChartEntity.setRemainDemandCount(remainDemandTotalCount);

        //所有任务
        sql = "select count(1) as totalTaskCount from pmc_work_item t";
        sql = sql.concat(" where t.stage_id = ? and t.work_type_code = 'task'");
        Integer totalTaskCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{stageId},Integer.class);
        stageBurnDowmChartEntity.setTotalTaskCount(totalTaskCount);

        // 统计已完成的任务
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.stage_id = ? and t.work_status_code = 'DONE' and t.work_type_code = 'task'");
        Integer endTaskTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{stageId},Integer.class);
        stageBurnDowmChartEntity.setEndTaskCount(endTaskTotalCount);

        // 统计进行中的任务
        sql = "select count(1) as progressTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.stage_id = ? and t.work_status_code = 'START' and t.work_type_code = 'task'");
        Integer progressTaskTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{stageId},Integer.class);
        stageBurnDowmChartEntity.setProgressTaskCount(progressTaskTotalCount);

        // 统计未开始的任务
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.stage_id = ? and t.work_status_code = 'TODO' and t.work_type_code = 'task'");
        Integer noTaskStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{stageId},Integer.class);
        stageBurnDowmChartEntity.setNostartTaskCount(noTaskStartTotalCount);

        // 统计未完成的任务
        Integer remainTaskTotalCount = totalTaskCount - endTaskTotalCount;
        stageBurnDowmChartEntity.setRemainTaskCount(remainTaskTotalCount);

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formater.format(new Date());
        stageBurnDowmChartEntity.setRecordTime(format);

        return jpaTemplate.save(stageBurnDowmChartEntity,String.class);
    }

    /**
     * 更新动态记录
     * @param stageBurnDowmChartEntity
     */
    public void updateStageBurnDowmChart(StageBurnDowmChartEntity stageBurnDowmChartEntity){
        jpaTemplate.update(stageBurnDowmChartEntity);
    }

    /**
     * 删除动态记录
     * @param id
     */
    public void deleteStageBurnDowmChart(String id){
        jpaTemplate.delete(StageBurnDowmChartEntity.class,id);
    }

    public void deleteStageBurnDowmChart(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找记录
     * @param id
     * @return
     */
    public StageBurnDowmChartEntity findStageBurnDowmChart(String id){
        return jpaTemplate.findOne(StageBurnDowmChartEntity.class,id);
    }

    /**
    * 查找所有的记录
    * @return
    */
    public List<StageBurnDowmChartEntity> findAllStageBurnDowmChart() {
        return jpaTemplate.findAll(StageBurnDowmChartEntity.class);
    }

    /**
     * 根据多个id搜索迭代的事项数据记录
     * @param idList
     * @return
     */
    public List<StageBurnDowmChartEntity> findStageBurnDowmChartList(List<String> idList) {
        return jpaTemplate.findList(StageBurnDowmChartEntity.class,idList);
    }

    /**
     * 根据搜索条件搜索迭代的事项数据记录
     * @param stageBurnDowmChartQuery
     * @return
     */
    public List<StageBurnDowmChartEntity> findStageBurnDowmChartList(StageBurnDowmChartQuery stageBurnDowmChartQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(StageBurnDowmChartEntity.class)
                .orders(stageBurnDowmChartQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,StageBurnDowmChartEntity.class);
    }

    /**
     * 根据搜索条件按分页搜索迭代的事项数据记录
     * @param stageBurnDowmChartQuery
     * @return
     */
    public Pagination<StageBurnDowmChartEntity> findStageBurnDowmChartPage(StageBurnDowmChartQuery stageBurnDowmChartQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(StageBurnDowmChartEntity.class)
                .eq("stageId", stageBurnDowmChartQuery.getStageId())
                .orders(stageBurnDowmChartQuery.getOrderParams())
                .pagination(stageBurnDowmChartQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,StageBurnDowmChartEntity.class);
    }
}