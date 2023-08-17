package io.tiklab.teamwire.home.statistic.dao;

import io.tiklab.teamwire.home.statistic.model.StatisticWorkItemQuery;
import io.tiklab.teamwire.home.statistic.model.WorkItemBuildAndEndStatistic;
import io.tiklab.teamwire.home.statistic.model.WorkItemStatistic;
import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.teamwire.project.project.model.ProjectQuery;
import io.tiklab.teamwire.project.project.service.ProjectService;
import io.tiklab.dal.jpa.JpaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 按照传入字段统计单位时间内事项数量的搜索数据访问
 */

@Repository
public class StatisticWorkItemDao {
    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    ProjectService projectService;

    public JdbcTemplate getJdbcTemplate() {
        return jpaTemplate.getJdbcTemplate();
    }

    /**
     * 按所选字段统计事项分布
     * @param statisticWorkItemQuery
     * @return
     */
    public List<WorkItemStatistic> statisticWorkItem(StatisticWorkItemQuery statisticWorkItemQuery) {
        List<WorkItemStatistic> list = new ArrayList<>();
        String collectionField = statisticWorkItemQuery.getCollectionField();
        String sql = new String();
        String sql1 = new String();
        Object[] params = new Object[]{};
        String firstParams = new String();
        String projectId = statisticWorkItemQuery.getProjectId();

        //统计所有事项总数
        Integer totalCount = new Integer(0);
        if(!StringUtils.isEmpty(projectId)){
            firstParams = projectId;
            sql = "select count(1) as totalCount from pmc_work_item t where t.project_id = ?";
            params = new Object[]{projectId};
            totalCount = getJdbcTemplate().queryForObject(sql, params,Integer.class);
            if(totalCount == 0){
                return list;
            }else {
                sql1 = " t.project_id = '" + projectId +"'";
                params = new Object[]{};
            }
        }

        //安装项目集统计事项总数
        String projectSetId = statisticWorkItemQuery.getProjectSetId();
        if(!StringUtils.isEmpty(projectSetId) && StringUtils.isEmpty(projectId)){

            ProjectQuery projectQuery = new ProjectQuery();
            projectQuery.setProjectSetId(projectSetId);
            List<Project> projectList = projectService.findProjectList(projectQuery);
            if(projectList.size() > 0){
                String s = new String();
                s =  "(";
                for (Project project : projectList) {
                    s = s.concat("'" + project.getId() + "',");
                }
                s= s.substring(0, s.length() - 1);
                s = s.concat(")");
                sql = "select count(1) as totalCount from pmc_work_item t where t.project_id in " + s;
                firstParams = s;
                totalCount = getJdbcTemplate().queryForObject(sql,Integer.class);
            }
            if(totalCount == 0){
                return list;
            }else {
                sql1 = " t.project_id in " + firstParams;
            }
        }

        //拼接按照状态统计事项数量的sql，bug
        if (collectionField.equals("state")) {
            if(statisticWorkItemQuery.getSprintId() != null){
                String sprintId = statisticWorkItemQuery.getSprintId();
                params = new Object[]{sprintId};
                sql = "select t.work_status_node_id as statisticalId,count(1) as groupCount from pmc_work_item t where" + sql1 + " and t.sprint_id = ? group by t.work_status_node_id";
            }else {
                sql = "select t.work_status_node_id as statisticalId,count(1) as groupCount from pmc_work_item t where" + sql1 + " group by t.work_status_node_id";
            }
        }

        //拼接按照负责人统计事项数量的sql
        if(collectionField.equals("assigner")){
            if(statisticWorkItemQuery.getSprintId() != null){
                String sprintId = statisticWorkItemQuery.getSprintId();
                params = new Object[]{sprintId};
                sql = "select t.assigner_id as statisticalId,count(1) as groupCount from pmc_work_item t where" + sql1 + " and t.sprint_id = ? group by t.assigner_id";
            }else {
                sql = "select t.assigner_id as statisticalId,count(1) as groupCount from pmc_work_item t where" + sql1 + " group by t.assigner_id";
            }
        }

        //拼接按照创建人统计事项数量的sql
        if(collectionField.equals("builder")){
            if(statisticWorkItemQuery.getSprintId() != null){
                String sprintId = statisticWorkItemQuery.getSprintId();
                params = new Object[]{sprintId};
                sql = "select t.builder_id as statisticalId,count(1) as groupCount from pmc_work_item t where" + sql1 + " and t.sprint_id = ? group by t.builder_id";
            }else {
                sql = "select t.builder_id as statisticalId,count(1) as groupCount from pmc_work_item t where" + sql1 + " group by t.builder_id";
            }
        }

        //拼接按照报告人统计事项数量的sql
        if(collectionField.equals("reporter")){
            if(statisticWorkItemQuery.getSprintId() != null){
                String sprintId = statisticWorkItemQuery.getSprintId();
                params = new Object[]{sprintId};
                sql = "select t.reporter_id as statisticalId,count(1) as groupCount from pmc_work_item t where" + sql1 + " and t.sprint_id = ? group by t.reporter_id";
            }else {
                sql = "select t.reporter_id as statisticalId,count(1) as groupCount from pmc_work_item t where" + sql1 + " group by t.reporter_id";
            }
        }

        //拼接按照类型统计事项数量的sql
        if(collectionField.equals("workType")){
            if(statisticWorkItemQuery.getSprintId() != null){
                String sprintId = statisticWorkItemQuery.getSprintId();
                params = new Object[]{sprintId};
                sql = "select t.work_type_id as statisticalId,count(1) as groupCount from pmc_work_item t where" + sql1 + " and t.sprint_id = ? group by t.work_type_id";
            }else {
                sql = "select t.work_type_id as statisticalId,count(1) as groupCount from pmc_work_item t where" + sql1 + " group by t.work_type_id";
            }
        }

        list = getJdbcTemplate().query(sql,params,new BeanPropertyRowMapper(WorkItemStatistic.class));

        //计算每个子类所占的百分比
        if(list != null && list.size() > 0){
            for(WorkItemStatistic item:list){
                item.setTotalCount(totalCount);
                item.process();
            }
        }
        return list;
    }

    /**
     * 事项创建解决统计
     * @param statisticWorkItemQuery
     * @return
     */
    public List<WorkItemBuildAndEndStatistic> statisticBuildAndEndWorkItem(StatisticWorkItemQuery statisticWorkItemQuery) {
        List<WorkItemBuildAndEndStatistic> list = new ArrayList<>();
        String cellTime = statisticWorkItemQuery.getCellTime();

        if(cellTime.equals("day")){
            getDayNewAndEndWorkData(list,statisticWorkItemQuery);
        }

        if(cellTime.equals("week")){
            getWeekNewAndEndWorkData(list,statisticWorkItemQuery);
        }

        if(cellTime.equals("month")){
            getMonthNewAndEndWorkData(list,statisticWorkItemQuery);
        }

        if(cellTime.equals("quarter")){
            getQuarterNewAndEndWorkData(list,statisticWorkItemQuery);
        }

        if(cellTime.equals("year")){
            getYearNewAndEndWorkData(list,statisticWorkItemQuery);
        }
        return list;
    }

    /**
     * 统计每天的新增与结束事项数量
     * @param list
     * @param statisticWorkItemQuery
     */
    void getDayNewAndEndWorkData(List<WorkItemBuildAndEndStatistic> list, StatisticWorkItemQuery statisticWorkItemQuery){
        String sql = new String();
        int buildCountTotal = 0;
        int endCountTotal = 0;
        Integer dateRanger = statisticWorkItemQuery.getDateRanger();
        String collectionType = statisticWorkItemQuery.getCollectionType();

        String projectId = statisticWorkItemQuery.getProjectId();
        String projectSetId = statisticWorkItemQuery.getProjectSetId();
        String sql1 = new String();

        //如果有项目id,按照项目id查找事项数量
        if(!StringUtils.isEmpty(projectId)){
            sql1 = " t.project_id = '" + projectId + "' and ";
        }

        //如果有项目集id,按照项目集id查找事项数量
        if(!StringUtils.isEmpty(projectSetId) && StringUtils.isEmpty(projectId)){
            ProjectQuery projectQuery = new ProjectQuery();
            projectQuery.setProjectSetId(projectSetId);
            List<Project> projectList = projectService.findProjectList(projectQuery);
            if(projectList.size() > 0){
                String s = new String();
                s =  "(";
                for (Project project : projectList) {
                    s = s.concat("'" + project.getId() + "',");
                }
                s= s.substring(0, s.length() - 1);
                s = s.concat(")");
                sql1 = " t.project_id in " + s + " and ";
            }
        }

        for(int date = dateRanger; date >= 0; date--){
            String time = getPastDate(date);
            Timestamp timestamp = Timestamp.valueOf(time);
            String time1 = getPastDate(date - 1);
            Timestamp timestamp1 = Timestamp.valueOf(time1);
            WorkItemBuildAndEndStatistic workItemBuildAndEndStatistic = new WorkItemBuildAndEndStatistic();
            workItemBuildAndEndStatistic.setTime(time.substring(0,10));

            //新增事项统计
            sql = "select count(1) as totalCount from pmc_work_item t where " + sql1 + "t.build_time between ? and ?";
            Object[] params = new Object[]{timestamp,timestamp1};
            //如果有迭代id,添加迭代限制
            if( statisticWorkItemQuery.getSprintId() != null ){
                String sprintId = statisticWorkItemQuery.getSprintId();
                sql = "select count(1) as totalCount from pmc_work_item t where "+ sql1 + "t.sprint_id = ? and t.build_time between ? and ?";
                params = new Object[]{sprintId,timestamp, timestamp1};
            }
            Integer buildCount = getJdbcTemplate().queryForObject(sql,params,Integer.class);

            //完成事项统计
            sql = "select count(1) as totalCount from pmc_work_item t where " + sql1 + "t.actual_end_time between ? and ?";
            Object[] params1 = new Object[]{timestamp,timestamp1};
            //如果有迭代id,添加迭代限制
            if( statisticWorkItemQuery.getSprintId() != null ){
                String sprintId = statisticWorkItemQuery.getSprintId();
                sql = "select count(1) as totalCount from pmc_work_item t where " + sql1 + "t.sprint_id = ? and t.actual_end_time between ? and ?";
                params1 = new Object[]{sprintId,timestamp,timestamp1};
            }
            Integer endCount = getJdbcTemplate().queryForObject(sql,params1,Integer.class);

            //统计新增
            if(collectionType.equals("count")){
                workItemBuildAndEndStatistic.setBuildCount(buildCount);
                workItemBuildAndEndStatistic.setEndCount(endCount);
            }

            //统计累计数据
            if(collectionType.equals("countTotal")){
                int buildCountInt = buildCount;
                buildCountTotal = buildCountTotal + buildCountInt;
                workItemBuildAndEndStatistic.setBuildCount(buildCountTotal);

                int endCountInt = endCount;
                endCountTotal = endCountTotal + endCountInt;
                workItemBuildAndEndStatistic.setEndCount(endCountTotal);
            }

            list.add(workItemBuildAndEndStatistic);
        }
    }

    /**
     * 统计每周新增与完成事项
     * @param list
     * @param statisticWorkItemQuery
     */
    void getWeekNewAndEndWorkData(List<WorkItemBuildAndEndStatistic> list, StatisticWorkItemQuery statisticWorkItemQuery){
        String sql = new String();
        Integer dateRanger = statisticWorkItemQuery.getDateRanger();
        String collectionType = statisticWorkItemQuery.getCollectionType();
        String projectId = statisticWorkItemQuery.getProjectId();
        String projectSetId = statisticWorkItemQuery.getProjectSetId();
        String sql1 = new String();

        //如果有项目id,按照项目id查找事项数量
        if(!StringUtils.isEmpty(projectId)){
            sql1 = " t.project_id = '" + projectId + "' and ";
        }

        //如果有项目集id,按照项目集id查找事项数量
        if(!StringUtils.isEmpty(projectSetId) && StringUtils.isEmpty(projectId)){
            ProjectQuery projectQuery = new ProjectQuery();
            projectQuery.setProjectSetId(projectSetId);
            List<Project> projectList = projectService.findProjectList(projectQuery);
            if(projectList.size() > 0){
                String s = new String();
                s =  "(";
                for (Project project : projectList) {
                    s = s.concat("'" + projectId + "',");
                }
                s= s.substring(0, s.length() - 1);
                s = s.concat(")");
                sql1 = " t.project_id in " + s + " and ";
            }
        }

        int dateRangerInt = dateRanger;
        int week = dateRangerInt / 7;
        int remainder = dateRangerInt % 7;
        if(remainder > 0){
            week ++;
        }
        int buildCountTotal = 0;
        int endCountTotal = 0;

        for(int date = week-1; date >= 0; date--){
            String startTime = getPastDate(date*7);
            String endTime = getPastDate(date*7 + 7);
            WorkItemBuildAndEndStatistic workItemBuildAndEndStatistic = new WorkItemBuildAndEndStatistic();
            workItemBuildAndEndStatistic.setTime(startTime.substring(0,10));

            //新增事项统计
            sql = "select count(1) as totalCount from pmc_work_item t where " + sql1 + "t.build_time between ? and ?";
            Object[] params = new Object[]{endTime,startTime};
            if( statisticWorkItemQuery.getSprintId() != null ){
                String sprintId = statisticWorkItemQuery.getSprintId();
                sql = "select count(1) as totalCount from pmc_work_item t where " + sql1 + "t.sprint_id = ? and t.build_time between ? and  ?";
                params = new Object[]{sprintId,endTime,startTime};
            }
            Integer buildCount = getJdbcTemplate().queryForObject(sql,params,Integer.class);

            //完成事项统计
            sql = "select count(1) as totalCount from pmc_work_item t where " + sql1 + "t.actual_end_time between ? and ?";
            Object[] params1 = new Object[]{endTime,startTime};
            if( statisticWorkItemQuery.getSprintId() != null ){
                String sprintId = statisticWorkItemQuery.getSprintId();
                sql = "select count(1) as totalCount from pmc_work_item t where " + sql1 + "t.sprint_id = ? and t.actual_end_time between ? and  ?";
                params1 = new Object[]{sprintId,endTime,startTime};
            }
            Integer endCount = getJdbcTemplate().queryForObject(sql,params1,Integer.class);

            if(collectionType.equals("count")){
                workItemBuildAndEndStatistic.setBuildCount(buildCount);
                workItemBuildAndEndStatistic.setEndCount(endCount);
            }
            if(collectionType.equals("countTotal")){
                int buildCountInt = buildCount;
                buildCountTotal = buildCountTotal + buildCountInt;
                workItemBuildAndEndStatistic.setBuildCount(buildCountTotal);

                int endCountInt = endCount;
                endCountTotal = endCountTotal + endCountInt;
                workItemBuildAndEndStatistic.setEndCount(endCountTotal);
            }

            list.add(workItemBuildAndEndStatistic);
        }
    }

    /**
     * 统计每月新增与完成事项
     * @param list
     * @param statisticWorkItemQuery
     */
    void getMonthNewAndEndWorkData(List<WorkItemBuildAndEndStatistic> list, StatisticWorkItemQuery statisticWorkItemQuery){
        String sql = new String();

        Integer dateRanger = statisticWorkItemQuery.getDateRanger();
        String collectionType = statisticWorkItemQuery.getCollectionType();
        String projectId = statisticWorkItemQuery.getProjectId();
        String projectSetId = statisticWorkItemQuery.getProjectSetId();
        String sql1 = new String();

        //如果有项目id,按照项目id查找事项数量
        if(!StringUtils.isEmpty(projectId)){
            sql1 = " t.project_id = '" + projectId + "' and ";
        }

        //如果有项目集id,按照项目集id查找事项数量
        if(!StringUtils.isEmpty(projectSetId) && StringUtils.isEmpty(projectId)){
            ProjectQuery projectQuery = new ProjectQuery();
            projectQuery.setProjectSetId(projectSetId);
            List<Project> projectList = projectService.findProjectList(projectQuery);
            if(projectList.size() > 0){
                String s = new String();
                s =  "(";
                for (Project project : projectList) {
                    s = s.concat("'" + projectId + "',");
                }
                s= s.substring(0, s.length() - 1);
                s = s.concat(")");
                sql1 = " t.project_id in " + s + " and ";
            }
        }

        int dateRangerInt = dateRanger;
        int month = dateRangerInt / 30;
        int remainder = dateRangerInt % 30;
        if(remainder > 0){
            month ++;
        }

        int buildCountTotal = 0;
        int endCountTotal = 0;

        for(int date = month - 1; date >= 0; date--){
            String startTime = getPastDate(date*30);
            String endTime = getPastDate(date*30 + 30);
            WorkItemBuildAndEndStatistic workItemBuildAndEndStatistic = new WorkItemBuildAndEndStatistic();
            workItemBuildAndEndStatistic.setTime(startTime.substring(0,10));

            //统计新增事项
            sql = "select count(1) as totalCount from pmc_work_item t where " + sql1 + "t.build_time between ? and  ?";
            Object[] params = new Object[]{endTime,startTime};
            if( statisticWorkItemQuery.getSprintId() != null ){
                String sprintId = statisticWorkItemQuery.getSprintId();
                sql = "select count(1) as totalCount from pmc_work_item t where " + sql1 + "t.sprint_id = ? and t.build_time between ? and  ?";
                params = new Object[]{sprintId,endTime,startTime};
            }
            Integer buildCount = getJdbcTemplate().queryForObject(sql,params,Integer.class);

            //统计完成事项
            sql = "select count(1) as totalCount from pmc_work_item t where " + sql1 + "t.actual_end_time between ? and ?";
            Object[] params1 = new Object[]{endTime,startTime};
            if( statisticWorkItemQuery.getSprintId() != null ){
                String sprintId = statisticWorkItemQuery.getSprintId();
                sql = "select count(1) as totalCount from pmc_work_item t where " + sql1 + "t.sprint_id = ? and t.actual_end_time between ? and  ?";
                params1 = new Object[]{sprintId,endTime,startTime};
            }
            Integer endCount = getJdbcTemplate().queryForObject(sql,params1,Integer.class);

            //计数统计
            if(collectionType.equals("count")){
                workItemBuildAndEndStatistic.setBuildCount(buildCount);
                workItemBuildAndEndStatistic.setEndCount(endCount);
            }

            //累计统计
            if(collectionType.equals("countTotal")){
                int buildCountInt = buildCount;
                buildCountTotal = buildCountTotal + buildCountInt;
                workItemBuildAndEndStatistic.setBuildCount(buildCountTotal);

                int endCountInt = endCount;
                endCountTotal = endCountTotal + endCountInt;
                workItemBuildAndEndStatistic.setEndCount(endCountTotal);
            }
            list.add(workItemBuildAndEndStatistic);
        }
    }

    /**
     * 统计每季度新增与完成事项
     * @param list
     * @param statisticWorkItemQuery
     */
    void getQuarterNewAndEndWorkData(List<WorkItemBuildAndEndStatistic> list, StatisticWorkItemQuery statisticWorkItemQuery){
        String sql = new String();
        Integer dateRanger = statisticWorkItemQuery.getDateRanger();
        String collectionType = statisticWorkItemQuery.getCollectionType();
        String projectId = statisticWorkItemQuery.getProjectId();
        String projectSetId = statisticWorkItemQuery.getProjectSetId();
        String sql1 = new String();

        //如果有项目id,按照项目id查找事项数量
        if(!StringUtils.isEmpty(projectId)){
            sql1 = " t.project_id = '" + projectId + "' and ";
        }

        //如果有项目集id,按照项目集id查找事项数量
        if(!StringUtils.isEmpty(projectSetId) && StringUtils.isEmpty(projectId)){
            ProjectQuery projectQuery = new ProjectQuery();
            projectQuery.setProjectSetId(projectSetId);
            List<Project> projectList = projectService.findProjectList(projectQuery);
            if(projectList.size() > 0){
                String s = new String();
                s =  "(";
                for (Project project : projectList) {
                    s = s.concat("'" + projectId + "',");
                }
                s= s.substring(0, s.length() - 1);
                s = s.concat(")");
                sql1 = " t.project_id in " + s + " and ";
            }
        }
        int dateRangerInt = dateRanger;
        int quarter = dateRangerInt / 90;
        int remainder = dateRangerInt % 90;
        if(remainder > 0){
            quarter ++;
        }
        int buildCountTotal = 0;
        int endCountTotal = 0;


        for(int date = quarter - 1; date >= 0; date--){
            String startTime = getPastDate(date*90);
            String endTime = getPastDate(date*90 + 90);
            WorkItemBuildAndEndStatistic workItemBuildAndEndStatistic = new WorkItemBuildAndEndStatistic();
            workItemBuildAndEndStatistic.setTime(startTime.substring(0,10));

            //查找创建事项数量
            sql = "select count(1) as totalCount from pmc_work_item t where "+ sql1 +"t.build_time between ? and  ?";
            Object[] params = new Object[]{endTime,startTime};
            if( statisticWorkItemQuery.getSprintId() != null ){
                String sprintId = statisticWorkItemQuery.getSprintId();
                sql = "select count(1) as totalCount from pmc_work_item t where "+ sql1 +"t.sprint_id = ? and t.build_time between ? and  ?";
                params = new Object[]{sprintId,endTime,startTime};
            }
            Integer buildCount = getJdbcTemplate().queryForObject(sql,params,Integer.class);

            //查找完成事项数量
            sql = "select count(1) as totalCount from pmc_work_item t where "+ sql1 +"t.actual_end_time between ? and ?";
            Object[] params1 = new Object[]{endTime,startTime};
            if( statisticWorkItemQuery.getSprintId() != null ){
                String sprintId = statisticWorkItemQuery.getSprintId();
                sql = "select count(1) as totalCount from pmc_work_item t where "+ sql1 +"t.sprint_id = ? and t.actual_end_time between ? and  ?";
                params1 = new Object[]{sprintId,endTime,startTime};
            }
            Integer endCount = getJdbcTemplate().queryForObject(sql,params1,Integer.class);

            if(collectionType.equals("count")){
                workItemBuildAndEndStatistic.setBuildCount(buildCount);
                workItemBuildAndEndStatistic.setEndCount(endCount);
            }
            if(collectionType.equals("countTotal")){
                int buildCountInt = buildCount;
                buildCountTotal = buildCountTotal + buildCountInt;
                workItemBuildAndEndStatistic.setBuildCount(buildCountTotal);

                int endCountInt = endCount;
                endCountTotal = endCountTotal + endCountInt;
                workItemBuildAndEndStatistic.setEndCount(endCountTotal);
            }

            list.add(workItemBuildAndEndStatistic);
        }
    }

    /**
     * 统计每年新增与完成事项
     * @param list
     * @param statisticWorkItemQuery
     */
    void getYearNewAndEndWorkData(List<WorkItemBuildAndEndStatistic> list,  StatisticWorkItemQuery statisticWorkItemQuery){
        String sql = new String();
        Integer dateRanger = statisticWorkItemQuery.getDateRanger();
        String collectionType = statisticWorkItemQuery.getCollectionType();
        String projectId = statisticWorkItemQuery.getProjectId();
        String projectSetId = statisticWorkItemQuery.getProjectSetId();
        String sql1 = new String();

        if(!StringUtils.isEmpty(projectId)){
            sql1 = " t.project_id = '" + projectId + "' and ";
        }

        if(!StringUtils.isEmpty(projectSetId) && StringUtils.isEmpty(projectId)){
            ProjectQuery projectQuery = new ProjectQuery();
            projectQuery.setProjectSetId(projectSetId);
            List<Project> projectList = projectService.findProjectList(projectQuery);
            if(projectList.size() > 0){
                String s = new String();
                s =  "(";
                for (Project project : projectList) {
                    s = s.concat("'" + projectId + "',");
                }
                s= s.substring(0, s.length() - 1);
                s = s.concat(")");
                sql1 = " t.project_id in " + s + " and ";
            }
        }
        int dateRangerInt = dateRanger;
        int year = dateRangerInt / 365;
        int remainder = dateRangerInt % 365;
        if(remainder > 0){
            year ++;
        }
        int buildCountTotal = 0;
        int endCountTotal = 0;

        for(int date = year - 1; date >= 0; date--){
            String startTime = getPastDate(date*365);
            Timestamp startTimetamp = Timestamp.valueOf(startTime);
            String endTime = getPastDate(date*365 + 365);
            Timestamp endTimetamp = Timestamp.valueOf(endTime);

            WorkItemBuildAndEndStatistic workItemBuildAndEndStatistic = new WorkItemBuildAndEndStatistic();
            workItemBuildAndEndStatistic.setTime(startTime.substring(0,10));

            //查找创建事项数量
            sql = "select count(1) as totalCount from pmc_work_item t where "+ sql1 +" t.build_time between ? and  ?";
            Object[] params = new Object[]{endTimetamp,startTimetamp};
            if( statisticWorkItemQuery.getSprintId() != null ){
                String sprintId = statisticWorkItemQuery.getSprintId();
                sql = "select count(1) as totalCount from pmc_work_item t where "+ sql1 +"t.sprint_id = ? and t.build_time between ? and  ?";
                params = new Object[]{sprintId,endTimetamp,startTime};
            }
            Integer buildCount = getJdbcTemplate().queryForObject(sql,params,Integer.class);

            //查找完成事项数量
            sql = "select count(1) as totalCount from pmc_work_item t where "+ sql1 +"t.actual_end_time between ? and ?";
            Object[] params1 = new Object[]{endTimetamp,startTimetamp};
            if( statisticWorkItemQuery.getSprintId() != null ){
                String sprintId = statisticWorkItemQuery.getSprintId();
                sql = "select count(1) as totalCount from pmc_work_item t where "+ sql1 +"t.sprint_id = ? and t.actual_end_time between ? and  ?";
                params1 = new Object[]{sprintId,endTimetamp,startTimetamp};
            }
            Integer endCount = getJdbcTemplate().queryForObject(sql,params1,Integer.class);

            //计数
            if(collectionType.equals("count")){
                workItemBuildAndEndStatistic.setBuildCount(buildCount);
                workItemBuildAndEndStatistic.setEndCount(endCount);
            }

            //累计
            if(collectionType.equals("countTotal")){
                int buildCountInt = buildCount;
                buildCountTotal = buildCountTotal + buildCountInt;
                workItemBuildAndEndStatistic.setBuildCount(buildCountTotal);

                int endCountInt = endCount;
                endCountTotal = endCountTotal + endCountInt;
                workItemBuildAndEndStatistic.setEndCount(endCountTotal);
            }

            list.add(workItemBuildAndEndStatistic);
        }
    }

    /**
     * 获取过去第几天的日期
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        // 分
        calendar.set(Calendar.MINUTE, 0);
        // 秒
        calendar.set(Calendar.SECOND, 0);

        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = format.format(today);
        return result;
    }

}
