package io.tiklab.teamwire.home.statistic.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.teamwire.home.statistic.entity.VersionBurnDowmChartEntity;
import io.tiklab.teamwire.home.statistic.model.VersionBurnDowmChartQuery;
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
public class VersionBurnDowmChartDao {

    private static Logger logger = LoggerFactory.getLogger(VersionBurnDowmChartDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建迭代的数据动态记录
     * @param versionBurnDowmChartEntity
     * @return
     */
    public String createVersionBurnDowmChart(VersionBurnDowmChartEntity versionBurnDowmChartEntity) {
        //统计总数
        String sql = "select count(1) as totalCount from pmc_work_item t";
        sql = sql.concat(" where t.version_id = ?");
        String versionId = versionBurnDowmChartEntity.getVersionId();
        Integer totalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{versionId},Integer.class);
        versionBurnDowmChartEntity.setTotalWorkitemCount(totalCount);

        // 统计已完成的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.version_id= ? and t.work_status_code = 'DONE'");
        Integer endTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{versionId},Integer.class);
        versionBurnDowmChartEntity.setEndWorkitemCount(endTotalCount);

        // 统计进行中的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.version_id= ? and t.work_status_code = 'START'");
        Integer progressTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{versionId},Integer.class);
        versionBurnDowmChartEntity.setProgressWorkitemCount(progressTotalCount);

        // 统计未开始的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.version_id= ? and t.work_status_code = 'TODO'");
        Integer noStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{versionId},Integer.class);
        versionBurnDowmChartEntity.setEndWorkitemCount(noStartTotalCount);

        // 统计未完成的事项
        Integer remainTotalCount = totalCount - endTotalCount;
        versionBurnDowmChartEntity.setRemainWorkitemCount(remainTotalCount);

        //所有缺陷
        sql = "select count(1) as totalBugCount from pmc_work_item t";
        sql = sql.concat(" where t.version_id = ? and t.work_type_code = 'defect'");
        Integer totalBugCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{versionId},Integer.class);
        versionBurnDowmChartEntity.setTotalBugCount(totalBugCount);

        // 统计已完成的缺陷
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.version_id = ? and t.work_status_code = 'DONE' and t.work_type_code = 'defect'");
        Integer endBugTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{versionId},Integer.class);
        versionBurnDowmChartEntity.setEndBugCount(endBugTotalCount);

        // 统计进行中的缺陷
        sql = "select count(1) as progressTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.version_id = ? and t.work_status_code = 'START' and t.work_type_code = 'defect'");
        Integer progressBugTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{versionId},Integer.class);
        versionBurnDowmChartEntity.setProgressBugCount(progressBugTotalCount);

        // 统计未开始的缺陷
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.version_id = ? and t.work_status_code = 'TODO' and t.work_type_code = 'defect'");
        Integer noBugStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{versionId},Integer.class);
        versionBurnDowmChartEntity.setNostartBugCount(noBugStartTotalCount);

        // 统计未完成的缺陷
        Integer remainBugTotalCount = totalBugCount - endBugTotalCount;
        versionBurnDowmChartEntity.setRemainBugCount(remainBugTotalCount);

        //所有需求
        sql = "select count(1) as totalDemandCount from pmc_work_item t";
        sql = sql.concat(" where t.version_id = ? and t.work_type_code = 'demand'");
        Integer totalDemandCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{versionId},Integer.class);
        versionBurnDowmChartEntity.setTotalDemandCount(totalDemandCount);

        // 统计已完成的需求
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.version_id = ? and t.work_status_code = 'DONE' and t.work_type_code = 'demand'");
        Integer endDemandTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{versionId},Integer.class);
        versionBurnDowmChartEntity.setEndDemandCount(endDemandTotalCount);

        // 统计进行中的需求
        sql = "select count(1) as progressTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.version_id = ? and t.work_status_code = 'START' and t.work_type_code = 'demand'");
        Integer progressDemandTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{versionId},Integer.class);
        versionBurnDowmChartEntity.setProgressDemandCount(progressDemandTotalCount);

        // 统计未开始的需求
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.version_id= ? and t.work_status_code = 'TODO' and t.work_type_code = 'demand'");
        Integer noDemandStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{versionId},Integer.class);
        versionBurnDowmChartEntity.setNostartDemandCount(noDemandStartTotalCount);

        // 统计未完成的需求
        Integer remainDemandTotalCount = totalDemandCount - endDemandTotalCount;
        versionBurnDowmChartEntity.setRemainDemandCount(remainDemandTotalCount);

        //所有任务
        sql = "select count(1) as totalTaskCount from pmc_work_item t";
        sql = sql.concat(" where t.version_id = ? and t.work_type_code = 'task'");
        Integer totalTaskCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{versionId},Integer.class);
        versionBurnDowmChartEntity.setTotalTaskCount(totalTaskCount);

        // 统计已完成的任务
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.version_id = ? and t.work_status_code = 'DONE' and t.work_type_code = 'task'");
        Integer endTaskTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{versionId},Integer.class);
        versionBurnDowmChartEntity.setEndTaskCount(endTaskTotalCount);

        // 统计进行中的任务
        sql = "select count(1) as progressTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.version_id = ? and t.work_status_code = 'START' and t.work_type_code = 'task'");
        Integer progressTaskTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{versionId},Integer.class);
        versionBurnDowmChartEntity.setProgressTaskCount(progressTaskTotalCount);

        // 统计未开始的任务
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.version_id = ? and t.work_status_code = 'TODO' and t.work_type_code = 'task'");
        Integer noTaskStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{versionId},Integer.class);
        versionBurnDowmChartEntity.setNostartTaskCount(noTaskStartTotalCount);

        // 统计未完成的任务
        Integer remainTaskTotalCount = totalTaskCount - endTaskTotalCount;
        versionBurnDowmChartEntity.setRemainTaskCount(remainTaskTotalCount);

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formater.format(new Date());
        versionBurnDowmChartEntity.setRecordTime(format);

        return jpaTemplate.save(versionBurnDowmChartEntity,String.class);
    }

    /**
     * 更新动态记录
     * @param versionBurnDowmChartEntity
     */
    public void updateVersionBurnDowmChart(VersionBurnDowmChartEntity versionBurnDowmChartEntity){
        jpaTemplate.update(versionBurnDowmChartEntity);
    }

    /**
     * 删除动态记录
     * @param id
     */
    public void deleteVersionBurnDowmChart(String id){
        jpaTemplate.delete(VersionBurnDowmChartEntity.class,id);
    }

    public void deleteVersionBurnDowmChart(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找记录
     * @param id
     * @return
     */
    public VersionBurnDowmChartEntity findVersionBurnDowmChart(String id){
        return jpaTemplate.findOne(VersionBurnDowmChartEntity.class,id);
    }

    /**
    * 查找所有的记录
    * @return
    */
    public List<VersionBurnDowmChartEntity> findAllVersionBurnDowmChart() {
        return jpaTemplate.findAll(VersionBurnDowmChartEntity.class);
    }

    /**
     * 根据多个id搜索迭代的事项数据记录
     * @param idList
     * @return
     */
    public List<VersionBurnDowmChartEntity> findVersionBurnDowmChartList(List<String> idList) {
        return jpaTemplate.findList(VersionBurnDowmChartEntity.class,idList);
    }

    /**
     * 根据搜索条件搜索迭代的事项数据记录
     * @param versionBurnDowmChartQuery
     * @return
     */
    public List<VersionBurnDowmChartEntity> findVersionBurnDowmChartList(VersionBurnDowmChartQuery versionBurnDowmChartQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(VersionBurnDowmChartEntity.class)
                .orders(versionBurnDowmChartQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,VersionBurnDowmChartEntity.class);
    }

    /**
     * 根据搜索条件按分页搜索迭代的事项数据记录
     * @param versionBurnDowmChartQuery
     * @return
     */
    public Pagination<VersionBurnDowmChartEntity> findVersionBurnDowmChartPage(VersionBurnDowmChartQuery versionBurnDowmChartQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(VersionBurnDowmChartEntity.class)
                .eq("versionId", versionBurnDowmChartQuery.getVersionId())
                .orders(versionBurnDowmChartQuery.getOrderParams())
                .pagination(versionBurnDowmChartQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,VersionBurnDowmChartEntity.class);
    }
}