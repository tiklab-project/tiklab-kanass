package io.tiklab.teamwire.home.statistic.dao;

import io.tiklab.teamwire.home.statistic.entity.ProjectBurnDowmChartEntity;
import io.tiklab.teamwire.home.statistic.model.ProjectBurnDowmChartQuery;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 项目下数据动态记录数据访问
 */
@Repository
public class ProjectBurnDowmChartDao{

    private static Logger logger = LoggerFactory.getLogger(ProjectBurnDowmChartDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建记录
     * @param projectBurnDowmChartEntity
     * @return
     */
    public String createProjectBurnDowmChart(ProjectBurnDowmChartEntity projectBurnDowmChartEntity) {

        //统计总数
        String sql = "select count(1) as totalCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id = ?");
        String projectId = projectBurnDowmChartEntity.getProjectId();
        Integer totalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);
        projectBurnDowmChartEntity.setTotalWorkitemCount(totalCount);

        // 统计已完成的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id= ? and t.work_status_code = 'DONE'");
        Integer endTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);
        projectBurnDowmChartEntity.setEndWorkitemCount(endTotalCount);

        // 统计进行中的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id= ? and t.work_status_code = 'START'");
        Integer progressTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);
        projectBurnDowmChartEntity.setProgressWorkitemCount(progressTotalCount);

        // 统计未开始的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id= ? and t.work_status_code = 'TODO'");
        Integer noStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);
        projectBurnDowmChartEntity.setNoStartWorkitemCount(noStartTotalCount);

        // 统计未完成的事项
        Integer remainTotalCount = totalCount - endTotalCount;
        projectBurnDowmChartEntity.setRemainWorkitemCount(remainTotalCount);

        //所有缺陷
        sql = "select count(1) as totalBugCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id = ? and t.work_type_code = 'defect'");
        Integer totalBugCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);
        projectBurnDowmChartEntity.setTotalBugCount(totalBugCount);

        // 统计已完成的缺陷
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id= ? and t.work_status_code = 'DONE' and t.work_type_code = 'defect'");
        Integer endBugTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);
        projectBurnDowmChartEntity.setEndBugCount(endBugTotalCount);

        // 统计进行中的缺陷
        sql = "select count(1) as progressTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id= ? and t.work_status_code = 'START' and t.work_type_code = 'defect'");
        Integer progressBugTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);
        projectBurnDowmChartEntity.setProgressBugCount(progressBugTotalCount);

        // 统计未开始的缺陷
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id= ? and t.work_status_code = 'TODO' and t.work_type_code = 'defect'");
        Integer noBugStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);
        projectBurnDowmChartEntity.setNostartBugCount(noBugStartTotalCount);

        // 统计未完成的缺陷
        Integer remainBugTotalCount = totalBugCount - endBugTotalCount;
        projectBurnDowmChartEntity.setRemainBugCount(remainBugTotalCount);

        //所有需求
        sql = "select count(1) as totalDemandCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id = ? and t.work_type_code = 'demand'");
        Integer totalDemandCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);
        projectBurnDowmChartEntity.setTotalDemandCount(totalDemandCount);

        // 统计已完成的需求
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id= ? and t.work_status_code = 'DONE' and t.work_type_code = 'demand'");
        Integer endDemandTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);
        projectBurnDowmChartEntity.setEndDemandCount(endDemandTotalCount);

        // 统计进行中的需求
        sql = "select count(1) as progressTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id= ? and t.work_status_code = 'START' and t.work_type_code = 'demand'");
        Integer progressDemandTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);
        projectBurnDowmChartEntity.setProgressDemandCount(progressDemandTotalCount);

        // 统计未开始的需求
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id= ? and t.work_status_code = 'TODO' and t.work_type_code = 'demand'");
        Integer noDemandStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);
        projectBurnDowmChartEntity.setNostartDemandCount(noDemandStartTotalCount);

        // 统计未完成的需求
        Integer remainDemandTotalCount = totalDemandCount - endDemandTotalCount;
        projectBurnDowmChartEntity.setRemainDemandCount(remainDemandTotalCount);

        //所有任务
        sql = "select count(1) as totalTaskCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id = ? and t.work_type_code = 'task'");
        Integer totalTaskCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);
        projectBurnDowmChartEntity.setTotalTaskCount(totalTaskCount);

        // 统计已完成的任务
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id= ? and t.work_status_code = 'DONE' and t.work_type_code = 'task'");
        Integer endTaskTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);
        projectBurnDowmChartEntity.setEndTaskCount(endTaskTotalCount);

        // 统计进行中的任务
        sql = "select count(1) as progressTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id= ? and t.work_status_code = 'START' and t.work_type_code = 'task'");
        Integer progressTaskTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);
        projectBurnDowmChartEntity.setProgressTaskCount(progressTaskTotalCount);

        // 统计未开始的任务
        sql = "select count(1) as endTotalCount from pmc_work_item t";
        sql = sql.concat(" where t.project_id= ? and t.work_status_code = 'TODO' and t.work_type_code = 'task'");
        Integer noTaskStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectId},Integer.class);
        projectBurnDowmChartEntity.setNostartTaskCount(noTaskStartTotalCount);

        // 统计未完成的任务
        Integer remainTaskTotalCount = totalTaskCount - endTaskTotalCount;
        projectBurnDowmChartEntity.setRemainTaskCount(remainTaskTotalCount);

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formater.format(new Date());
        projectBurnDowmChartEntity.setRecordTime(format);

        return jpaTemplate.save(projectBurnDowmChartEntity,String.class);
    }

    /**
     * 更新数据记录
     * @param projectBurnDowmChartEntity
     */
    public void updateProjectBurnDowmChart(ProjectBurnDowmChartEntity projectBurnDowmChartEntity){
        jpaTemplate.update(projectBurnDowmChartEntity);
    }

    /**
     * 删除记录
     * @param id
     */
    public void deleteProjectBurnDowmChart(String id){
        jpaTemplate.delete(ProjectBurnDowmChartEntity.class,id);
    }

    public void deleteProjectBurnDowmChart(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 按照id查找记录
     * @param id
     * @return
     */
    public ProjectBurnDowmChartEntity findProjectBurnDowmChart(String id){
        return jpaTemplate.findOne(ProjectBurnDowmChartEntity.class,id);
    }

    /**
    * 查找所有记录
    * @return
    */
    public List<ProjectBurnDowmChartEntity> findAllProjectBurnDowmChart() {
        return jpaTemplate.findAll(ProjectBurnDowmChartEntity.class);
    }


    public List<ProjectBurnDowmChartEntity> findProjectBurnDowmChartList(List<String> idList) {
        return jpaTemplate.findList(ProjectBurnDowmChartEntity.class,idList);
    }

    /**
     * 按条件查询记录列表
     * @param projectBurnDowmChartQuery
     * @return
     */
    public List<ProjectBurnDowmChartEntity> findProjectBurnDowmChartList(ProjectBurnDowmChartQuery projectBurnDowmChartQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectBurnDowmChartEntity.class)
                .eq("projectId", projectBurnDowmChartQuery.getProjectId())
                .orders(projectBurnDowmChartQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,ProjectBurnDowmChartEntity.class);
    }

    /**
     * 按条件分页查询记录列表
     * @param projectBurnDowmChartQuery
     * @return
     */
    public Pagination<ProjectBurnDowmChartEntity> findProjectBurnDowmChartPage(ProjectBurnDowmChartQuery projectBurnDowmChartQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectBurnDowmChartEntity.class)
                .eq("projectId", projectBurnDowmChartQuery.getProjectId())
                .orders(projectBurnDowmChartQuery.getOrderParams())
                .pagination(projectBurnDowmChartQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,ProjectBurnDowmChartEntity.class);
    }
}