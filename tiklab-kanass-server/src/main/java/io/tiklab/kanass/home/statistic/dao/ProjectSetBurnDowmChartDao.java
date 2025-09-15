package io.tiklab.kanass.home.statistic.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.kanass.home.statistic.entity.ProjectSetBurnDowmChartEntity;
import io.tiklab.kanass.home.statistic.model.ProjectSetBurnDowmChartQuery;
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
public class ProjectSetBurnDowmChartDao {

    private static Logger logger = LoggerFactory.getLogger(ProjectSetBurnDowmChartDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建记录
     * @param projectSetBurnDowmChartEntity
     * @return
     */
    public String createProjectSetBurnDowmChart(ProjectSetBurnDowmChartEntity projectSetBurnDowmChartEntity) {

        //统计总数
        String sql = "select count(1) as totalCount from pmc_work_item t left join pmc_project p on p.id=t.project_id ";
        sql = sql.concat(" where p.project_set_id = ?");
        String projectSetId = projectSetBurnDowmChartEntity.getProjectSetId();
        Integer totalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectSetId},Integer.class);
        projectSetBurnDowmChartEntity.setTotalWorkitemCount(totalCount);

        // 统计已完成的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t left join pmc_project p on p.id=t.project_id ";
        sql = sql.concat(" where p.project_set_id= ? and t.work_status_code = 'DONE'");
        Integer endTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectSetId},Integer.class);
        projectSetBurnDowmChartEntity.setEndWorkitemCount(endTotalCount);

        // 统计进行中的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t left join pmc_project p on p.id=t.project_id ";
        sql = sql.concat(" where p.project_set_id= ? and t.work_status_code = 'START'");
        Integer progressTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectSetId},Integer.class);
        projectSetBurnDowmChartEntity.setProgressWorkitemCount(progressTotalCount);

        // 统计未开始的事项
        sql = "select count(1) as endTotalCount from pmc_work_item t left join pmc_project p on p.id=t.project_id ";
        sql = sql.concat(" where p.project_set_id= ? and t.work_status_code = 'TODO'");
        Integer noStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectSetId},Integer.class);
        projectSetBurnDowmChartEntity.setNoStartWorkitemCount(noStartTotalCount);

        // 统计未完成的事项
        Integer remainTotalCount = totalCount - endTotalCount;
        projectSetBurnDowmChartEntity.setRemainWorkitemCount(remainTotalCount);

        //所有缺陷
        sql = "select count(1) as totalBugCount from pmc_work_item t left join pmc_project p on p.id=t.project_id ";
        sql = sql.concat(" where p.project_set_id = ? and t.work_type_code = 'defect'");
        Integer totalBugCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectSetId},Integer.class);
        projectSetBurnDowmChartEntity.setTotalBugCount(totalBugCount);

        // 统计已完成的缺陷
        sql = "select count(1) as endTotalCount from pmc_work_item t left join pmc_project p on p.id=t.project_id ";
        sql = sql.concat(" where p.project_set_id= ? and t.work_status_code = 'DONE' and t.work_type_code = 'defect'");
        Integer endBugTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectSetId},Integer.class);
        projectSetBurnDowmChartEntity.setEndBugCount(endBugTotalCount);

        // 统计进行中的缺陷
        sql = "select count(1) as progressTotalCount from pmc_work_item t left join pmc_project p on p.id=t.project_id ";
        sql = sql.concat(" where p.project_set_id= ? and t.work_status_code = 'START' and t.work_type_code = 'defect'");
        Integer progressBugTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectSetId},Integer.class);
        projectSetBurnDowmChartEntity.setProgressBugCount(progressBugTotalCount);

        // 统计未开始的缺陷
        sql = "select count(1) as endTotalCount from pmc_work_item t left join pmc_project p on p.id=t.project_id ";
        sql = sql.concat(" where p.project_set_id= ? and t.work_status_code = 'TODO' and t.work_type_code = 'defect'");
        Integer noBugStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectSetId},Integer.class);
        projectSetBurnDowmChartEntity.setNostartBugCount(noBugStartTotalCount);

        // 统计未完成的缺陷
        Integer remainBugTotalCount = totalBugCount - endBugTotalCount;
        projectSetBurnDowmChartEntity.setRemainBugCount(remainBugTotalCount);

        //所有需求
        sql = "select count(1) as totalDemandCount from pmc_work_item t left join pmc_project p on p.id=t.project_id ";
        sql = sql.concat(" where p.project_set_id = ? and t.work_type_code = 'demand'");
        Integer totalDemandCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectSetId},Integer.class);
        projectSetBurnDowmChartEntity.setTotalDemandCount(totalDemandCount);

        // 统计已完成的需求
        sql = "select count(1) as endTotalCount from pmc_work_item t left join pmc_project p on p.id=t.project_id ";
        sql = sql.concat(" where p.project_set_id= ? and t.work_status_code = 'DONE' and t.work_type_code = 'demand'");
        Integer endDemandTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectSetId},Integer.class);
        projectSetBurnDowmChartEntity.setEndDemandCount(endDemandTotalCount);

        // 统计进行中的需求
        sql = "select count(1) as progressTotalCount from pmc_work_item t left join pmc_project p on p.id=t.project_id ";
        sql = sql.concat(" where p.project_set_id= ? and t.work_status_code = 'START' and t.work_type_code = 'demand'");
        Integer progressDemandTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectSetId},Integer.class);
        projectSetBurnDowmChartEntity.setProgressDemandCount(progressDemandTotalCount);

        // 统计未开始的需求
        sql = "select count(1) as endTotalCount from pmc_work_item t left join pmc_project p on p.id=t.project_id ";
        sql = sql.concat(" where p.project_set_id= ? and t.work_status_code = 'TODO' and t.work_type_code = 'demand'");
        Integer noDemandStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectSetId},Integer.class);
        projectSetBurnDowmChartEntity.setNostartDemandCount(noDemandStartTotalCount);

        // 统计未完成的需求
        Integer remainDemandTotalCount = totalDemandCount - endDemandTotalCount;
        projectSetBurnDowmChartEntity.setRemainDemandCount(remainDemandTotalCount);

        //所有任务
        sql = "select count(1) as totalTaskCount from pmc_work_item t left join pmc_project p on p.id=t.project_id ";
        sql = sql.concat(" where p.project_set_id = ? and t.work_type_code = 'task'");
        Integer totalTaskCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectSetId},Integer.class);
        projectSetBurnDowmChartEntity.setTotalTaskCount(totalTaskCount);

        // 统计已完成的任务
        sql = "select count(1) as endTotalCount from pmc_work_item t left join pmc_project p on p.id=t.project_id ";
        sql = sql.concat(" where p.project_set_id= ? and t.work_status_code = 'DONE' and t.work_type_code = 'task'");
        Integer endTaskTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectSetId},Integer.class);
        projectSetBurnDowmChartEntity.setEndTaskCount(endTaskTotalCount);

        // 统计进行中的任务
        sql = "select count(1) as progressTotalCount from pmc_work_item t left join pmc_project p on p.id=t.project_id ";
        sql = sql.concat(" where p.project_set_id= ? and t.work_status_code = 'START' and t.work_type_code = 'task'");
        Integer progressTaskTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectSetId},Integer.class);
        projectSetBurnDowmChartEntity.setProgressTaskCount(progressTaskTotalCount);

        // 统计未开始的任务
        sql = "select count(1) as endTotalCount from pmc_work_item t left join pmc_project p on p.id=t.project_id ";
        sql = sql.concat(" where p.project_set_id= ? and t.work_status_code = 'TODO' and t.work_type_code = 'task'");
        Integer noTaskStartTotalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{projectSetId},Integer.class);
        projectSetBurnDowmChartEntity.setNostartTaskCount(noTaskStartTotalCount);

        // 统计未完成的任务
        Integer remainTaskTotalCount = totalTaskCount - endTaskTotalCount;
        projectSetBurnDowmChartEntity.setRemainTaskCount(remainTaskTotalCount);

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formater.format(new Date());
        projectSetBurnDowmChartEntity.setRecordTime(format);

        return jpaTemplate.save(projectSetBurnDowmChartEntity,String.class);
    }

    /**
     * 更新数据记录
     * @param projectSetBurnDowmChartEntity
     */
    public void updateProjectSetBurnDowmChart(ProjectSetBurnDowmChartEntity projectSetBurnDowmChartEntity){
        jpaTemplate.update(projectSetBurnDowmChartEntity);
    }

    /**
     * 删除记录
     * @param id
     */
    public void deleteProjectSetBurnDowmChart(String id){
        jpaTemplate.delete(ProjectSetBurnDowmChartEntity.class,id);
    }

    public void deleteProjectSetBurnDowmChart(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 按照id查找记录
     * @param id
     * @return
     */
    public ProjectSetBurnDowmChartEntity findProjectSetBurnDowmChart(String id){
        return jpaTemplate.findOne(ProjectSetBurnDowmChartEntity.class,id);
    }

    /**
    * 查找所有记录
    * @return
    */
    public List<ProjectSetBurnDowmChartEntity> findAllProjectSetBurnDowmChart() {
        return jpaTemplate.findAll(ProjectSetBurnDowmChartEntity.class);
    }


    public List<ProjectSetBurnDowmChartEntity> findProjectSetBurnDowmChartList(List<String> idList) {
        return jpaTemplate.findList(ProjectSetBurnDowmChartEntity.class,idList);
    }

    /**
     * 按条件查询记录列表
     * @param projectSetBurnDowmChartQuery
     * @return
     */
    public List<ProjectSetBurnDowmChartEntity> findProjectSetBurnDowmChartList(ProjectSetBurnDowmChartQuery projectSetBurnDowmChartQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectSetBurnDowmChartEntity.class)
                .eq("projectSetId", projectSetBurnDowmChartQuery.getProjectSetId())
                .orders(projectSetBurnDowmChartQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition,ProjectSetBurnDowmChartEntity.class);
    }

    /**
     * 按条件分页查询记录列表
     * @param projectSetBurnDowmChartQuery
     * @return
     */
    public Pagination<ProjectSetBurnDowmChartEntity> findProjectSetBurnDowmChartPage(ProjectSetBurnDowmChartQuery projectSetBurnDowmChartQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ProjectSetBurnDowmChartEntity.class)
                .eq("projectSetId", projectSetBurnDowmChartQuery.getProjectSetId())
                .orders(projectSetBurnDowmChartQuery.getOrderParams())
                .pagination(projectSetBurnDowmChartQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition,ProjectSetBurnDowmChartEntity.class);
    }
}