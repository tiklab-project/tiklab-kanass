package io.tiklab.teamwire.home.insight.dao;

import io.tiklab.teamwire.home.insight.model.WorkItemCountQuery;
import io.tiklab.teamwire.home.statistic.entity.ProjectBurnDowmChartEntity;
import io.tiklab.teamwire.home.statistic.entity.SprintBurnDowmChartEntity;
import io.tiklab.teamwire.home.statistic.service.SprintBurnDowmChartServiceImpl;
import io.tiklab.teamwire.project.project.service.ProjectService;
import io.tiklab.dal.jdbc.JdbcTemplate;
import io.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 仪表盘统计类目数据访问
 */
@Repository
public class ProjectInsightReportDao {
    private static Logger logger = LoggerFactory.getLogger(ProjectInsightReportDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    ProjectService projectService;

    /**
     * 新增事项统计
     * @param workItemCountQuery
     * @return
     */
    public Integer findAllCountByType(WorkItemCountQuery workItemCountQuery) {
        String projectId = workItemCountQuery.getProjectId();
        String startDate = workItemCountQuery.getStartDate();
        String endDate = workItemCountQuery.getEndDate();
        String workItemTypeCode = workItemCountQuery.getWorkItemTypeCode();

        String sql = "select count(1) as totalCount from kanass_document_template t where t.project_id = '" + projectId + "'";

        if(workItemTypeCode != null && workItemTypeCode.length() > 0 && !workItemTypeCode.equals("all")){
            sql = sql.concat(" and t.work_type_code = '" + workItemTypeCode + "'");
        }

        if(startDate != null && endDate != null){
            sql = sql.concat("' and t.build_time < '"+ startDate +"' and t.build_time >='" + endDate);
        }
        Integer totalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{},Integer.class);
        return totalCount;
    }

    /**
     * 按照事项状态统计新增事项数量
     * @param workItemCountQuery
     * @param workItemStatusCode
     * @return
     */
    public Integer findStatusCountByType(WorkItemCountQuery workItemCountQuery,String workItemStatusCode) {
        String projectId = workItemCountQuery.getProjectId();
        String startDate = workItemCountQuery.getStartDate();
        String endDate = workItemCountQuery.getEndDate();
        String workItemTypeCode = workItemCountQuery.getWorkItemTypeCode();
        String sql = "select count(1) as totalCount from pmc_work_item t where t.project_id = '"
                + projectId + "' and t.work_status_code = '" + workItemStatusCode + "'";

        if(workItemTypeCode != null && workItemTypeCode.length() > 0 && !workItemTypeCode.equals("all")){
            sql = sql.concat(" and t.work_type_code = '" + workItemTypeCode + "'");
        }
        if(startDate != null && endDate != null){
            sql = sql.concat("' and t.build_time < '"+ startDate +"' and t.build_time >='" + endDate);
        }

        Integer totalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{},Integer.class);
        return totalCount;
    }

    /**
     * 按照事项类型统计未结束事项数量
     * @param workItemCountQuery
     * @return
     */
    public Integer findNoEndCountByType(WorkItemCountQuery workItemCountQuery) {
        String projectId = workItemCountQuery.getProjectId();
        String startDate = workItemCountQuery.getStartDate();
        String endDate = workItemCountQuery.getEndDate();
        String workItemTypeCode = workItemCountQuery.getWorkItemTypeCode();

        String sql = "select count(1) as totalCount from pmc_work_item t where t.project_id = '"
                + projectId + "' and t.work_status_code != 'DONE'";

        if(workItemTypeCode != null && workItemTypeCode.length() > 0 && !workItemTypeCode.equals("all")){
            sql = sql.concat(" and t.work_type_code = '" + workItemTypeCode + "'");
        }
        if(startDate != null && endDate != null){
            sql.concat("' and t.build_time < '"+ startDate +"' and t.build_time >='" + endDate);
        }

        Integer totalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{},Integer.class);
        return totalCount;
    }

    /**
     * 逾期事项
     * @param workItemCountQuery
     * @return
     */
    public Integer findOverduedCountByType(WorkItemCountQuery workItemCountQuery) {
        String projectId = workItemCountQuery.getProjectId();
        String workItemTypeCode = workItemCountQuery.getWorkItemTypeCode();
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        String newDate = ft.format(dNow);

        String sql = "select count(1) as totalCount from pmc_work_item t where t.project_id = '"
                + projectId + "' and t.plan_end_time < '"+ newDate + "' and t.work_status_code != 'DONE'";

        if(workItemTypeCode != null && workItemTypeCode.length() > 0 && !workItemTypeCode.equals("all")){
            sql = sql.concat(" and t.work_type_code = '" + workItemTypeCode + "'");
        }
        Integer totalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{},Integer.class);
        return totalCount;
    }

    /**
     * 统计某段时间迭代累计新增事项的数据
     * @param workItemCountQuery
     * @param startTime
     * @param endTime
     * @return
     */
    public int statisticsSprintWorkItemTotalCount(WorkItemCountQuery workItemCountQuery, String startTime, String endTime) {
        String sprintId = workItemCountQuery.getSprintId();
        String workItemTypeCode = workItemCountQuery.getWorkItemTypeCode();
        String sql = "select * from pmc_sprint_burndowm p where p.sprint_id = '" +
                sprintId + "' and p.record_time < '"+ endTime +"' and p.record_time >='" + startTime +"'";

        List<SprintBurnDowmChartEntity> query = this.jpaTemplate.getJdbcTemplate().query(sql, new String[]{}, new BeanPropertyRowMapper(SprintBurnDowmChartEntity.class));

        Integer workItemCount = 0;
        if(query.size() > 0){
            if(workItemTypeCode != null && workItemTypeCode.length() > 0) {
                switch (workItemTypeCode){
                    case "all":
                        workItemCount = query.get(0).getTotalWorkitemCount();
                        break;
                    case "demand":
                        workItemCount = query.get(0).getTotalDemandCount();
                        break;
                    case "task":
                        workItemCount = query.get(0).getTotalTaskCount();
                        break;
                    case "defect":
                        workItemCount = query.get(0).getEndBugCount();
                        break;
                    default:
                        workItemCount = 0;
                        break;
                }
            }
        }

        return workItemCount;
    }

    /**
     * 统计某段时间累计新增事项的数据
     * @param workItemCountQuery
     * @param startTime
     * @param endTime
     * @return
     */
    public int statisticsProjectWorkItemTotalCount(WorkItemCountQuery workItemCountQuery, String startTime, String endTime) {
        String projectId = workItemCountQuery.getProjectId();
        String workItemTypeCode = workItemCountQuery.getWorkItemTypeCode();
        String sql = "select * from pmc_project_burndowm p where p.project_id = '" +
                projectId + "' and p.record_time < '"+ endTime +"' and p.record_time >='" + startTime +"'";

        List<ProjectBurnDowmChartEntity> query = this.jpaTemplate.getJdbcTemplate().query(sql, new String[]{}, new BeanPropertyRowMapper(ProjectBurnDowmChartEntity.class));

        Integer workItemCount = 0;
        if(query.size() > 0){
            if(workItemTypeCode != null && workItemTypeCode.length() > 0) {
                switch (workItemTypeCode){
                    case "all":
                        workItemCount = query.get(0).getTotalWorkitemCount();
                        break;
                    case "demand":
                        workItemCount = query.get(0).getTotalDemandCount();
                        break;
                    case "task":
                        workItemCount = query.get(0).getTotalTaskCount();
                        break;
                    case "defect":
                        workItemCount = query.get(0).getEndBugCount();
                        break;
                    default:
                        workItemCount = 0;
                        break;
                }
            }
        }

        return workItemCount;
    }

    /**
     * 统计某段时间累计结束事项的数据
     * @param workItemCountQuery
     * @param startTime
     * @param endTime
     * @return
     */
    public int statisticsEndProjectWorkItemTotalCount(WorkItemCountQuery workItemCountQuery, String startTime, String endTime) {
        String projectId = workItemCountQuery.getProjectId();
        String workItemTypeCode = workItemCountQuery.getWorkItemTypeCode();
        String sql = "select * from pmc_project_burndowm p where p.project_id = '" +
                projectId + "' and p.record_time < '"+ endTime +"' and p.record_time >='" + startTime +"'";

        List<ProjectBurnDowmChartEntity> query = this.jpaTemplate.getJdbcTemplate().query(sql, new String[]{}, new BeanPropertyRowMapper(ProjectBurnDowmChartEntity.class));

        Integer workItemCount = 0;
        if(query.size() > 0){
            if(workItemTypeCode != null && workItemTypeCode.length() > 0) {
                switch (workItemTypeCode){
                    case "all":
                        workItemCount = query.get(0).getEndWorkitemCount();
                        break;
                    case "demand":
                        workItemCount = query.get(0).getEndDemandCount();
                        break;
                    case "task":
                        workItemCount = query.get(0).getEndTaskCount();
                        break;
                    case "defect":
                        workItemCount = query.get(0).getEndBugCount();
                        break;
                    default:
                        workItemCount = 0;
                        break;

                }
            }

        }

        return workItemCount;
    }

    /**
     * 统计某段时间累计结束事项的数据
     * @param workItemCountQuery
     * @param startTime
     * @param endTime
     * @return
     */
    public int statisticsEndSprintWorkItemTotalCount(WorkItemCountQuery workItemCountQuery, String startTime, String endTime) {
        String sprintId = workItemCountQuery.getSprintId();
        String workItemTypeCode = workItemCountQuery.getWorkItemTypeCode();
        String sql = "select * from pmc_sprint_burndowm p where p.sprint_id = '" +
                sprintId + "' and p.record_time < '"+ endTime +"' and p.record_time >='" + startTime +"'";

        List<SprintBurnDowmChartEntity> query = this.jpaTemplate.getJdbcTemplate().query(sql, new String[]{}, new BeanPropertyRowMapper(SprintBurnDowmChartEntity.class));

        Integer workItemCount = 0;
        if(query.size() > 0){
            if(workItemTypeCode != null && workItemTypeCode.length() > 0) {
                switch (workItemTypeCode){
                    case "all":
                        workItemCount = query.get(0).getEndWorkitemCount();
                        break;
                    case "demand":
                        workItemCount = query.get(0).getEndDemandCount();
                        break;
                    case "task":
                        workItemCount = query.get(0).getEndTaskCount();
                        break;
                    case "defect":
                        workItemCount = query.get(0).getEndBugCount();
                        break;
                    default:
                        workItemCount = 0;
                        break;

                }
            }

        }

        return workItemCount;
    }
    /**
     * 统计某段时间累计进行中事项的数据
     * @param projectId
     * @param startTime
     * @param endTime
     * @return
     */
    public int statisticsProjectProcessWorkItemCount(String projectId, String startTime, String endTime) {
        String sql = "select * from pmc_project_burndowm p where p.project_id = '" +
                projectId + "' and p.record_time < '"+ endTime +"' and p.record_time >='" + startTime +"'";

        List<ProjectBurnDowmChartEntity> query = this.jpaTemplate.getJdbcTemplate().query(sql, new String[]{}, new BeanPropertyRowMapper(ProjectBurnDowmChartEntity.class));

        Integer progressWorkItemCount = 0;
        if(query.size() > 0){
            progressWorkItemCount = query.get(0).getProgressWorkitemCount();
        }

        return progressWorkItemCount;
    }

    public Integer statisticsDayWorkItemCount(WorkItemCountQuery workItemCountQuery, String startTime, String endTime) {
        String projectId = workItemCountQuery.getProjectId();
        String workItemTypeCode = workItemCountQuery.getWorkItemTypeCode();

        String sql = "select * from pmc_project_burndowm p where p.project_id = '" +
                projectId + "' and p.record_time < '"+ endTime +"' and p.record_time >='" + startTime +"'";

        List<ProjectBurnDowmChartEntity> query = this.jpaTemplate.getJdbcTemplate().query(sql, new String[]{}, new BeanPropertyRowMapper(ProjectBurnDowmChartEntity.class));
        Integer workItemCount = 0;

        if(query.size() > 0){
            if(workItemTypeCode != null && workItemTypeCode.length() > 0) {
                switch (workItemTypeCode){
                    case "all":
                        workItemCount = query.get(0).getRemainWorkitemCount();
                        break;
                    case "demand":
                        workItemCount = query.get(0).getEndDemandCount();
                        break;
                    case "task":
                        workItemCount = query.get(0).getRemainTaskCount();
                        break;
                    case "defect":
                        workItemCount = query.get(0).getRemainBugCount();
                        break;
                    default:
                        workItemCount = 0;
                        break;
                }
            }
        }
        return workItemCount;
    }

    //每天新增，完成，剩余事项统计
    public Map<String, Integer> statisticsWorkItemTrend(WorkItemCountQuery workItemCountQuery){
        HashMap<String, Integer> workItemTrend = new HashMap<>();
        String startTime = workItemCountQuery.getStartDate();
        String endTime = workItemCountQuery.getEndDate();

        //总剩余趋势
        Integer nostartWorkItem = statisticsDayWorkItemCount(workItemCountQuery, startTime, endTime);

        //每天新增
        Integer newWorkItemCount = statisticsProjectNewWorkItemCount(workItemCountQuery, startTime, endTime);

        //每天完成
        Integer endWorkItemCount  = statisticsProjectEndWorkItemCount(workItemCountQuery, startTime, endTime);

        workItemTrend.put("remain", nostartWorkItem);
        workItemTrend.put("end", endWorkItemCount);
        workItemTrend.put("new", newWorkItemCount);

        return  workItemTrend;
    }


    /**
     * 按照条件统计开始时间与结束事件之间的事项数量
     * @param workItemCountQuery
     * @param startTime
     * @param endTime
     * @return
     */
    public Integer statisticsProjectNewWorkItemCount(WorkItemCountQuery workItemCountQuery, String startTime, String endTime) {
        String workItemTypeCode = workItemCountQuery.getWorkItemTypeCode();
        String projectId = workItemCountQuery.getProjectId();
        String sprintId = workItemCountQuery.getSprintId();

        String sql = "select count(1) as totalCount from pmc_work_item t where t.project_id = '" + projectId +
                "' and t.build_time < '"+ endTime +"' and t.build_time >='" + startTime +"' ";

        if(!StringUtils.isEmpty(sprintId)){
            sql = sql.concat("and t.sprint_id = '" + sprintId + "'");
        }

        if(workItemTypeCode != null && workItemTypeCode.length() > 0 && !workItemTypeCode.equals("all")){
            sql = sql.concat("and t.work_type_code = '" + workItemTypeCode + "'");
        }

        Integer totalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{},Integer.class);
        return totalCount;
    }

    /**
     * 按照时间统计日期，统计某段时间完成事项的数据
     * @param workItemCountQuery
     * @param startTime
     * @param endTime
     * @return
     */
    public Integer statisticsProjectEndWorkItemCount(WorkItemCountQuery workItemCountQuery, String startTime, String endTime) {
        String workItemTypeCode = workItemCountQuery.getWorkItemTypeCode();
        String projectId = workItemCountQuery.getProjectId();
        String sprintId = workItemCountQuery.getSprintId();

        String sql = "select count(1) as totalCount from pmc_work_item t where t.project_id = '" + projectId
                +  "' and t.actual_end_time < '"+ endTime +"' and t.actual_end_time >='" +
                startTime +"' and t.work_status_code = 'DONE'";
        if(!StringUtils.isEmpty(sprintId)){
            sql = sql.concat("and t.sprint_id = '" + sprintId + "'");
        }
        if(workItemTypeCode != null && workItemTypeCode.length() > 0 && !workItemTypeCode.equals("all")){
            sql = sql.concat("and t.work_type_code = '" + workItemTypeCode + "'");
        }

        Integer totalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{},Integer.class);
        return totalCount;
    }

    /**
     * 统计某段时间累计进行中事项的缺陷
     * @param projectId
     * @param startTime
     * @param endTime
     * @return
     */
    public int statisticsProjectProcessBugCount(String projectId, String startTime, String endTime) {
        String sql = "select * from pmc_project_burndowm p where p.project_id = '" +
                projectId + "' and p.record_time < '"+ endTime +"' and p.record_time >='" + startTime +"'";

        List<ProjectBurnDowmChartEntity> query = this.jpaTemplate.getJdbcTemplate().query(sql, new String[]{}, new BeanPropertyRowMapper(ProjectBurnDowmChartEntity.class));

        Integer progressBugCount = 0;
        if(query.size()>0){
            progressBugCount = query.get(0).getProgressBugCount();
        }

        return progressBugCount;
    }


    /**
     * 按照状态统计事项数量
     * @param workItemCountQuery
     * @return
     */
    public Map<String, Integer> statisticsWorkItemStatusCount(WorkItemCountQuery workItemCountQuery){
        HashMap<String, Integer> workItemStatusCount = new HashMap<>();

        Integer newWorkItemCount = findAllCountByType(workItemCountQuery);
        Integer noEndWorkItemCount = findNoEndCountByType(workItemCountQuery);
        Integer todoWorkItemCount = findStatusCountByType(workItemCountQuery, "TODO");
        Integer startWorkItemCount = findStatusCountByType(workItemCountQuery, "START");
        Integer overduedWorkItemCount = findOverduedCountByType(workItemCountQuery);

        workItemStatusCount.put("all", newWorkItemCount);
        workItemStatusCount.put("noEnd", noEndWorkItemCount);
        workItemStatusCount.put("todo", todoWorkItemCount);
        workItemStatusCount.put("start", startWorkItemCount);
        workItemStatusCount.put("overdue", overduedWorkItemCount);

        return workItemStatusCount;
    }

    /**
     * 统计某个项目下，某个成员负责的事项对比
     * @param projectId
     * @param userId
     * @return
     */
    public Map<String,Integer> statisticsUserWorkItemCount(String projectId, String userId) {
        HashMap<String, Integer> workItemCount = new HashMap<>();
        String sql = "select count(1) as totalCount from pmc_work_item t where t.project_id = '" +
                projectId + "' and t.reporter_id = '" + userId + "'";
        Integer totalCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql,new String[]{},Integer.class);
        workItemCount.put("workItem",totalCount);

        String sql1 = "select count(1) as totalCount from pmc_work_item t where t.work_type_code = 'demand' and t.project_id = '" +
                projectId + "' and t.reporter_id = '" + userId + "'";
        Integer demandCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql1,new String[]{},Integer.class);
        workItemCount.put("demand",demandCount);

        String sql2 = "select count(1) as totalCount from pmc_work_item t where t.work_type_code = 'task' and t.project_id = '" +
                projectId + "' and t.reporter_id = '" + userId + "'";
        Integer taskCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql2,new String[]{},Integer.class);
        workItemCount.put("task",taskCount);

        String sql3 = "select count(1) as totalCount from pmc_work_item t where t.work_type_code = 'defect' and t.project_id = '" +
                projectId + "' and t.reporter_id = '" + userId + "'";
        Integer bugCount = this.jpaTemplate.getJdbcTemplate().queryForObject(sql3,new String[]{},Integer.class);
        workItemCount.put("bug",bugCount);

        return workItemCount;
    }

    public JdbcTemplate getJdbcTemplate() {

        return jpaTemplate.getJdbcTemplate();
    }
}
