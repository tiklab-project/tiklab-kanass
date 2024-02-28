package io.thoughtware.kanass.project.worklog.dao;

import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderTypeEnum;
import io.thoughtware.kanass.project.project.model.Project;
import io.thoughtware.kanass.project.project.model.ProjectQuery;
import io.thoughtware.kanass.project.project.service.ProjectService;
import io.thoughtware.kanass.project.worklog.model.EneryDayTotal;
import io.thoughtware.kanass.project.worklog.model.ProjectListLog;
import io.thoughtware.kanass.project.worklog.model.ProjectLog;
import io.thoughtware.kanass.project.worklog.model.WorkLogQuery;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.kanass.workitem.model.WorkItemQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.thoughtware.kanass.project.worklog.model.*;
import io.thoughtware.kanass.workitem.service.WorkItemService;
import io.thoughtware.kanass.project.worklog.entity.WorkLogEntity;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.user.dmUser.model.DmUser;
import io.thoughtware.user.dmUser.model.DmUserQuery;
import io.thoughtware.user.dmUser.service.DmUserService;
import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import io.thoughtware.dal.jdbc.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 事项日志数据操作
 */
@Repository
public class WorkLogDao{

    private static Logger logger = LoggerFactory.getLogger(WorkLogDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    ProjectService projectService;

    @Autowired
    UserService userService;

    @Autowired
    DmUserService dmUserService;

    @Autowired
    WorkItemService workItemService;
    
    /**
     * 创建事项日志
     * @param workLogEntity
     * @return
     */
    public String createWorkLog(WorkLogEntity workLogEntity) {
        return jpaTemplate.save(workLogEntity,String.class);
    }

    /**
     * 更新事项日志
     * @param workLogEntity
     */
    public void updateWorkLog(WorkLogEntity workLogEntity){
        jpaTemplate.update(workLogEntity);
    }

    /**
     * 删除事项日志
     * @param id
     */
    public void deleteWorkLog(String id){
        jpaTemplate.delete(WorkLogEntity.class,id);
    }

    /**
     * 根据id查找工作日志
     * @param id
     * @return
     */
    public WorkLogEntity findWorkLog(String id){
        return jpaTemplate.findOne(WorkLogEntity.class,id);
    }

    /**
    * 查找所有工作日志
    * @return
    */
    public List<WorkLogEntity> findAllWorkLog() {
        return jpaTemplate.findAll(WorkLogEntity.class);
    }

    /**
     * 按照条件查询工作日志列表
     * @param workLogQuery
     * @return
     */
    public List<WorkLogEntity> findWorkLogList(WorkLogQuery workLogQuery) {
        QueryCondition workItemId = QueryBuilders.createQuery(WorkLogEntity.class)
                .eq("workItemId", workLogQuery.getWorkItemId())
                .orders(workLogQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(workItemId, WorkLogEntity.class);
    }

    /**
     * 查询项目每个成员的工时
     * @param workLogQuery
     * @return
     */
    public Map<String, Object> WorkItemSearchSql(WorkLogQuery workLogQuery) {
        String sql = new String();
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, String> sqlMap = new HashMap<String, String>();
//        ArrayList<Object> objects = new ArrayList<>();
        Object[] objects = {};

        if(workLogQuery.getProjectId() != null && workLogQuery.getProjectId().length()>0){
            if(paramMap.isEmpty()){
                sql = sql.concat(" l.project_id = '" + workLogQuery.getProjectId() + "'");
            }else {
                sql = sql.concat(" and l.project_id = " + workLogQuery.getProjectId());
            }
            paramMap.put("projectId", workLogQuery.getProjectId());
        }

        if(workLogQuery.getWorker() != null && workLogQuery.getWorker().length()>0){

            if(paramMap.isEmpty()){
                sql = sql.concat(" l.worker = '" + workLogQuery.getWorker() + "'");
            }else {
                sql = sql.concat(" and l.worker = '"+ workLogQuery.getWorker() + "'");
            }
            paramMap.put("worker", workLogQuery.getWorker());
        }

        if(workLogQuery.getWorkItemId() != null && workLogQuery.getWorkItemId().length()>0){

            if(paramMap.isEmpty()){
                sql = sql.concat(" l.work_item_id = '" + workLogQuery.getWorkItemId() + "'");
            }else {
                sql = sql.concat(" and l.work_item_id = '"+ workLogQuery.getWorkItemId() + "'");
            }
            paramMap.put("worker", workLogQuery.getWorker());
        }

        if(workLogQuery.getStartTime() != null && workLogQuery.getEndTime() != null){

            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd 00:00:00");
            String startTime= ft.format(workLogQuery.getStartTime());
            String endTime= ft.format(workLogQuery.getEndTime());
            if(paramMap.isEmpty()){
                sql = sql.concat(" l.work_date between '" + startTime + "' and '" + endTime + "'");
            }else {
                sql = sql.concat(" and l.work_date between '" + startTime + "' and '" + endTime + "'");
            }
            paramMap.put("timeRange", workLogQuery.getWorker());
        }

        sqlMap.put("sql", sql);
        objectObjectHashMap.put("query", objects);
        objectObjectHashMap.put("sql", sql);

        return objectObjectHashMap;
    }

    /**
     * 根据条件按照分页查找日志列表
     * @param workLogQuery
     * @return
     */
    public Pagination<WorkLogEntity> findWorkLogPage(WorkLogQuery workLogQuery) {
        String sql = new String();
        sql = "Select * from pmc_work_log l ";
        Map<String, Object> stringObjectMap = WorkItemSearchSql(workLogQuery);
        Object o1 = stringObjectMap.get("sql");
        if(String.valueOf(o1).length()>0){
            sql = sql.concat("where " + String.valueOf(o1));
        }
        if(!ObjectUtils.isEmpty(workLogQuery.getOrderParams())){
            sql= sql.concat(" order by ");
        }
        for (Order orderParam : workLogQuery.getOrderParams()) {
            OrderTypeEnum orderType = orderParam.getOrderType();
            String name = orderParam.getName();
            sql = sql.concat(name + " " + orderType );
        }
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        Pagination page = jdbcTemplate.findPage(sql, new Object[]{}, workLogQuery.getPageParam(), new BeanPropertyRowMapper(WorkLogEntity.class));
        return page;
    }

    /**
     * 按照日历统计人员的项目日志
     * @param dateList
     * @param workLogQuery
     * @return
     */
    public List<ProjectLog> findUserProjectLog(List<String> dateList, WorkLogQuery workLogQuery){
        //按事项和时间分组统计
        Date startTime = workLogQuery.getStartTime();
        Date endTime = workLogQuery.getEndTime();
        String searchUserId = workLogQuery.getWorker();
        String projectId1 = workLogQuery.getProjectId();
        String sql = new String();

        //查找我有关的所有项目
        List<String> userIds = new ArrayList<String>();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startTime", startTime);
        paramMap.put("endTime", endTime);
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(getJdbcTemplate());
        List<ProjectLog> userGroupList = new ArrayList<>();
        List<ProjectLog> UserProjectLogList = new ArrayList<>();
        List<String> projectIds = new ArrayList<>();

        //如果有人员查询条件
        if(searchUserId != null && searchUserId.length() >0){
            paramMap.put("worker", searchUserId);
            sql = "select worker as userId,sum(takeup_time) as total from pmc_work_log t where work_date > (:startTime) and work_date < (:endTime) and worker = (:worker) group by t.worker";
            //按照人员分组统计日志
            userGroupList =  jdbc.query(sql, paramMap, new BeanPropertyRowMapper(ProjectLog.class));
            userIds.add(searchUserId);
        }
//        List<Project> projectList = new ArrayList<>();
        //查找项目集的所有项目，在此范围分组统计人员日志
        if(workLogQuery.getProjectSetId() != null || (workLogQuery.getProjectSetId() == null && projectId1 == null)){
            List<Project> projectList = new ArrayList<Project>();
            ProjectQuery projectQuery = new ProjectQuery();
            if(workLogQuery.getProjectSetId() != null){
                projectQuery.setProjectSetId(workLogQuery.getProjectSetId());
                projectList = projectService.findProjectList(projectQuery);
            }else {
                projectList = projectService.findJoinProjectList(projectQuery);
            }

            String projectIdList = new String();
            projectIdList =  "(";
            if(projectList.size() > 0){
                for (Project project : projectList) {
                    DmUserQuery dmUserQuery1 = new DmUserQuery();
                    String id = project.getId();
                    dmUserQuery1.setDomainId(id);
                    List<DmUser> dmUserList1 = dmUserService.findDmUserList(dmUserQuery1);
                    List<User> userList = dmUserList1.stream().map(DmUser::getUser).collect(Collectors.toList());
                    List<String> userListIds = userList.stream().map(User::getId).collect(Collectors.toList());
                    userIds.addAll(userListIds);

                    // 获取项目集项目ids
                    projectIds.add(id);
                    projectIdList = projectIdList.concat("'" + id + "',");
                }
//                if(!StringUtils.isEmpty(projectId1)){
//                    projectIds.add(projectId1);
//                    projectIdList = projectIdList.concat("'" + projectId1 + "',");
//                }
                projectIdList= projectIdList.substring(0, projectIdList.length() - 1);
                projectIdList = projectIdList.concat(")");
                sql = "select worker as userId,sum(takeup_time) as total from pmc_work_log t where project_id in " + projectIdList + " and work_date > (:startTime) and work_date < (:endTime) group by t.worker";
                userGroupList =  jdbc.query(sql, paramMap, new BeanPropertyRowMapper(ProjectLog.class));
            }
        }
        if(projectId1 != null){
            // 获取项目的成员
            projectIds.add(projectId1);

            DmUserQuery dmUserQuery1 = new DmUserQuery();
            dmUserQuery1.setDomainId(projectId1);
            List<DmUser> dmUserList1 = dmUserService.findDmUserList(dmUserQuery1);
            List<User> users = dmUserList1.stream().map(DmUser::getUser).collect(Collectors.toList());
            List<String> roleIds = users.stream().map(User::getId).collect(Collectors.toList());
            userIds.addAll(roleIds);

            sql = "select worker as userId,sum(takeup_time) as total from pmc_work_log t where project_id = '" + projectId1 + "' and work_date > (:startTime) and work_date < (:endTime) group by t.worker";
            userGroupList =  jdbc.query(sql, paramMap, new BeanPropertyRowMapper(ProjectLog.class));
        }


        userIds = userIds.stream().distinct().collect(Collectors.toList());
        for (String userId : userIds) {
            ProjectLog userLog = new ProjectLog();

            //筛选在统计范围内的人员日志
            List<ProjectLog> userGroups = userGroupList.stream().filter(a -> a.getUserId().equals(userId)).collect(Collectors.toList());
            ArrayList<ProjectListLog> projectListLogs = new ArrayList<>();

            //若有成员有日志记录
            if(userGroups != null && userGroups.size() > 0){
               userLog = userGroups.get(0);
                String worker = userLog.getUserId();
                User user = userService.findUser(worker);
                userLog.setUser(user);
                Map<String, Object> paramMap1 = new HashMap<String, Object>();
                paramMap1.put("startTime", startTime);
                paramMap1.put("endTime", endTime);
                paramMap1.put("worker", worker);

                //把人员日志按照项目分组
                sql = "select project_id,sum(takeup_time) as projectLogTotal from pmc_work_log t where work_date > (:startTime) and work_date < (:endTime) and t.worker = (:worker) group by t.project_id";
                NamedParameterJdbcTemplate jdbc1 = new NamedParameterJdbcTemplate(getJdbcTemplate());
                List<ProjectListLog> projectListLogList = jdbc1.query(sql, paramMap1, new BeanPropertyRowMapper(ProjectListLog.class));

                // 按照项目循环查找人员的项目日志
                for (String projectId : projectIds) {
                    List<ProjectListLog> projectGroups = projectListLogList.stream().filter(a -> a.getProjectId().equals(projectId)).collect(Collectors.toList());
                    //若有成员有项目的日志记录，则设置每天的日志用时
                    if(projectGroups != null && projectGroups.size() > 0){
                        ProjectListLog projectListLog = projectGroups.get(0);
                        Project project = projectService.findOne(projectId);
                        projectListLog.setProject(project);
                        List<EneryDayTotal> integers = new ArrayList<EneryDayTotal>();
                        for(int i=0; i< dateList.size()-1; i++){

                            String s1 = dateList.get(i);
                            String s2 = dateList.get(i + 1);
                            Map<String, Object> paramMap2 = new HashMap<String, Object>();
                            paramMap2.put("startTime", s1);
                            paramMap2.put("endTime", s2);
                            paramMap2.put("projectId", projectId);
                            paramMap2.put("worker", worker);
                            sql = "select sum(takeup_time) as statistic from pmc_work_log t where work_date > (:startTime) and work_date < (:endTime) and t.project_id = (:projectId) and t.worker = (:worker) group by t.project_id";
                            List<EneryDayTotal> query = jdbc1.query(sql, paramMap2, new BeanPropertyRowMapper(EneryDayTotal.class));
                            EneryDayTotal eneryDayTotal = new EneryDayTotal();
                            eneryDayTotal.setStatistic(0);
                            if(query != null && query.size() >= 1){
                                integers.add(query.get(0));
                            }else {
                                integers.add(eneryDayTotal);
                            }
                        }
                        projectListLog.setStatisticsList(integers);
                        projectListLogs.add(projectListLog);
                    }else {
                        //若有成员没有项目的日志记录，则设置每天的日志用时为0
                        ProjectListLog projectListLog = new ProjectListLog();
                        Project project = projectService.findOne(projectId);
                        projectListLog.setProject(project);
                        projectListLog.setProjectId(projectId);

                        List<EneryDayTotal> statisticsLists = new ArrayList<EneryDayTotal>();

                        for(int i=0; i< dateList.size()-1; i++){
                            EneryDayTotal eneryDayTotal = new EneryDayTotal();
                            eneryDayTotal.setStatistic(0);
                            statisticsLists.add(eneryDayTotal);
                        }
                        projectListLog.setStatisticsList(statisticsLists);
                        projectListLogs.add(projectListLog);
                    }
                }
                userLog.setProjectListLogList(projectListLogs);
            }else {
                //若没有成员日志记录，则查找此人负责所有项目，并设置每天日志统计用时为0
                userLog.setUserId(userId);
                User user = userService.findOne(userId);
                userLog.setUser(user);
                userLog.setTotal(0);

                ArrayList<ProjectListLog> userListLogs = new ArrayList<ProjectListLog>();

                //补齐项目下所有人员日志的空缺
                DmUserQuery dmUserQuery = new DmUserQuery();
                dmUserQuery.setUserId(userId);
                List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
                List<DmUser> collect = dmUserList.stream().filter(a -> projectIds.indexOf(a.getDomainId()) > -1).collect(Collectors.toList());
                for(DmUser dmUser:collect){
                    ProjectListLog projectListLog = new ProjectListLog();
                    String projectId = dmUser.getDomainId();
                    Project project = projectService.findOne(projectId);
                    projectListLog.setProject(project);
                    projectListLog.setProjectId(projectId);

                    List<EneryDayTotal> statisticsLists = new ArrayList<EneryDayTotal>();
                    for(int i=0; i< dateList.size()-1; i++){
                        EneryDayTotal eneryDayTotal = new EneryDayTotal();
                        eneryDayTotal.setStatistic(0);
                        statisticsLists.add(eneryDayTotal);
                    }
                    projectListLog.setStatisticsList(statisticsLists);
                    userListLogs.add(projectListLog);
                }
                userLog.setProjectListLogList(userListLogs);
            }
            UserProjectLogList.add(userLog);
        }

       return UserProjectLogList;
    }

    /**
     * 统计项目的成员日志统计
     * @param dateList
     * @param workLogQuery
     * @return
     */
    public List<ProjectLog> findProjectUserhour(List<String> dateList,WorkLogQuery workLogQuery){

        Date startTime = workLogQuery.getStartTime();
        Date endTime = workLogQuery.getEndTime();
        String searchProjectId = workLogQuery.getProjectId();

        String sql = new String();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startTime", startTime);
        paramMap.put("endTime", endTime);
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(getJdbcTemplate());
        List<ProjectLog> projectGroupList = new ArrayList<ProjectLog>();
        List<ProjectLog> projectUserLogList = new ArrayList<ProjectLog>();
        List<String> projectIds = new ArrayList<String>();

        //若有项目查询id
        if(searchProjectId != null && searchProjectId.length()>0){
            paramMap.put("projectId", searchProjectId);

            //按照项目分组日志，和计算这个项目一共花费的工时
            sql = "select project_id,sum(takeup_time) as total from pmc_work_log t where work_date > (:startTime) and work_date < (:endTime) and project_id = (:projectId) group by t.project_id";
            projectGroupList = jdbc.query(sql, paramMap, new BeanPropertyRowMapper(ProjectLog.class));
            projectIds.add(searchProjectId);
        }

        //如果按照项目集查询
        if(workLogQuery.getProjectSetId() != null && searchProjectId == null){
            ProjectQuery projectQuery = new ProjectQuery();
            projectQuery.setProjectSetId(workLogQuery.getProjectSetId());
            List<Project> projectList = projectService.findProjectList(projectQuery);
            for (Project project : projectList) {
                projectIds.add(project.getId());
            }
            //按照项目分组日志，和计算这个项目一共花费的工时
            sql = "select project_id,sum(takeup_time) as total from pmc_work_log t where work_date > (:startTime) and work_date < (:endTime) group by t.project_id";
            projectGroupList = jdbc.query(sql, paramMap, new BeanPropertyRowMapper(ProjectLog.class));
        }
        //如果查找全部项目
        if(workLogQuery.getProjectSetId() == null && searchProjectId == null){
            ProjectQuery projectQuery = new ProjectQuery();
            List<Project> projectList = projectService.findJoinProjectList(projectQuery);
            for (Project project : projectList) {
                projectIds.add(project.getId());
            }
            //按照项目分组日志，和计算这个项目一共花费的工时
            sql = "select project_id,sum(takeup_time) as total from pmc_work_log t where work_date > (:startTime) and work_date < (:endTime) group by t.project_id";
            projectGroupList = jdbc.query(sql, paramMap, new BeanPropertyRowMapper(ProjectLog.class));
        }
        for (String projectId : projectIds) {
            //统计每个项目的下所有成员的日志
            ProjectLog oneProjectUserLog = findOneProjectUserLog(projectGroupList, workLogQuery, projectId, dateList);
            projectUserLogList.add(oneProjectUserLog);
        }
        return projectUserLogList;
    }

    /**
     * 按照日历，统计一段时间内某个项目的下所有成员的每天的日志
     * @param projectGroupList
     * @param workLogQuery
     * @param projectId
     * @param dateList
     * @return
     */
    public ProjectLog findOneProjectUserLog(List<ProjectLog> projectGroupList,WorkLogQuery workLogQuery, String projectId,  List<String> dateList){
        Date startTime = workLogQuery.getStartTime();
        Date endTime = workLogQuery.getEndTime();
        String worker = workLogQuery.getWorker();
        ProjectLog projectLog = new ProjectLog();
        String sql = new String();

        List<ProjectLog> projectGroup = projectGroupList.stream().filter(a -> a.getProjectId().equals(projectId)).collect(Collectors.toList());

        //如果项目下有日志记录
        if(projectGroup != null && projectGroup.size() > 0){
            projectLog = projectGroup.get(0);
            Project project = projectService.findOne(projectId);
            projectLog.setProject(project);


            Map<String, Object> paramMap1 = new HashMap<String, Object>();
            paramMap1.put("startTime", startTime);
            paramMap1.put("endTime", endTime);
            paramMap1.put("projectId", projectId);

            //按照条件，以人员为分组，查找日志
            sql = "select worker as userId,sum(takeup_time) as projectLogTotal from pmc_work_log t where work_date > (:startTime) and work_date < (:endTime) and t.project_id = (:projectId) group by t.worker";
            NamedParameterJdbcTemplate jdbc1 = new NamedParameterJdbcTemplate(getJdbcTemplate());
            List<ProjectListLog> userListLogList = jdbc1.query(sql, paramMap1, new BeanPropertyRowMapper(ProjectListLog.class));

            //查找项目下的成员
            List<String> userIds = new ArrayList<String>();
            if(worker == null){
                DmUserQuery dmUserQuery = new DmUserQuery();
                dmUserQuery.setDomainId(projectId);
                List<DmUser> roleList = dmUserService.findDmUserList(dmUserQuery);
                List<User> userList = roleList.stream().map(DmUser::getUser).collect(Collectors.toList());
                userIds = userList.stream().map(User::getId).collect(Collectors.toList());
            }else {
                userIds.add(worker);
            }

            //查找项目下成员的日志
            List<ProjectListLog> userListLogs = findOneProjectAllUserLog(userIds, userListLogList, dateList, projectId);
            projectLog.setProjectListLogList(userListLogs);
        }else {
            //如果项目下没有日志记录，则查找所有项目成员，循环用0补空每天日志
            projectLog.setProjectId(projectId);
            Project project = projectService.findOne(projectId);
            projectLog.setProject(project);
            projectLog.setTotal(0);

            ArrayList<ProjectListLog> projectListLogs = new ArrayList<ProjectListLog>();

            //若有人员筛选条件，补齐筛选人员的项目日志统计
            if(worker != null){
                ProjectListLog projectListLog = new ProjectListLog();

                String userId = worker;
                User user = userService.findOne(userId);
                projectListLog.setUser(user);
                projectListLog.setUserId(userId);

                List<EneryDayTotal> statisticsLists = new ArrayList<EneryDayTotal>();

                for(int i=0; i< dateList.size()-1; i++){
                    EneryDayTotal eneryDayTotal = new EneryDayTotal();
                    eneryDayTotal.setStatistic(0);
                    statisticsLists.add(eneryDayTotal);
                }
                projectListLog.setStatisticsList(statisticsLists);
                projectListLogs.add(projectListLog);

            }else {
                //若没有人员筛选条件，补齐项目下所有人员日志的空缺
                DmUserQuery dmUserQuery = new DmUserQuery();
                dmUserQuery.setDomainId(projectId);
                List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
                for(DmUser dmUser:dmUserList){
                    ProjectListLog projectListLog = new ProjectListLog();

                    String userId = dmUser.getUser().getId();
                    User user = userService.findOne(userId);
                    projectListLog.setUser(user);
                    projectListLog.setUserId(userId);

                    List<EneryDayTotal> statisticsLists = new ArrayList<EneryDayTotal>();
                    for(int i=0; i< dateList.size()-1; i++){
                        EneryDayTotal eneryDayTotal = new EneryDayTotal();
                        eneryDayTotal.setStatistic(0);
                        statisticsLists.add(eneryDayTotal);
                    }
                    projectListLog.setStatisticsList(statisticsLists);
                    projectListLogs.add(projectListLog);
                }
            }
            projectLog.setProjectListLogList(projectListLogs);
        }
        return projectLog;
    }

    /**
     * 统计某个项目下所有成员的日志统计
     * @param userIds
     * @param userListLogList
     * @param dateList
     * @param projectId
     * @return
     */
    public  List<ProjectListLog> findOneProjectAllUserLog(List<String> userIds, List<ProjectListLog> userListLogList, List<String> dateList, String projectId){
        String sql = new String();
        ArrayList<ProjectListLog> userListLogs = new ArrayList<>();

        //循环查找每个成员的日志
        for (String userId : userIds) {
            List<ProjectListLog> userGroups = userListLogList.stream().filter(a -> a.getUserId().equals(userId)).collect(Collectors.toList());
            ProjectListLog userListLog = new ProjectListLog();
            if(userGroups != null && userGroups.size() > 0){
                userListLog = userGroups.get(0);

                userListLog.setUserId(userId);
                User user = userService.findOne(userId);
                userListLog.setUser(user);
                List<EneryDayTotal> integers = new ArrayList<EneryDayTotal>();

                //循环查找项目成员每天的日志记录
                for(int i=0; i< dateList.size()-1; i++){
                    String s1 = dateList.get(i);
                    String s2 = dateList.get(i + 1);

                    Map<String, Object> paramMap2 = new HashMap<String, Object>();
                    paramMap2.put("startTime", s1);
                    paramMap2.put("endTime", s2);
                    paramMap2.put("projectId", projectId);
                    paramMap2.put("worker", userId);

                    sql = "select sum(takeup_time) as statistic from pmc_work_log t where work_date > (:startTime) and work_date < (:endTime) and t.project_id = (:projectId) and t.worker = (:worker) group by t.project_id";
                    NamedParameterJdbcTemplate jdbc1 = new NamedParameterJdbcTemplate(getJdbcTemplate());
                    List<EneryDayTotal> query = jdbc1.query(sql, paramMap2, new BeanPropertyRowMapper(EneryDayTotal.class));
                    EneryDayTotal eneryDayTotal = new EneryDayTotal();
                    eneryDayTotal.setStatistic(0);
                    if(query != null && query.size() >= 1){
                        integers.add(query.get(0));
                    }else {
                        integers.add(eneryDayTotal);
                    }
                }
                userListLog.setStatisticsList(integers);
            }else {
                //如成员无记录，则用0 补齐
                userListLog.setUserId(userId);
                User user = userService.findOne(userId);
                userListLog.setUser(user);
                userListLog.setProjectLogTotal(0);

                ArrayList<ProjectListLog> projectListLogs = new ArrayList<ProjectListLog>();
                //补齐项目下所有人员日志的空缺
                List<EneryDayTotal> statisticsLists = new ArrayList<EneryDayTotal>();

                for(int i=0; i< dateList.size()-1; i++){
                    EneryDayTotal eneryDayTotal = new EneryDayTotal();
                    eneryDayTotal.setStatistic(0);
                    statisticsLists.add(eneryDayTotal);
                }
                userListLog.setStatisticsList(statisticsLists);
            }
            userListLogs.add(userListLog);
        }
        return userListLogs;
    }

    /**
     * 按照日历，统计项目的事项日志
     * @param dateList
     * @param workLogQuery
     * @return
     */
    public List<ProjectLog> findProjectWorkItemhour(List<String> dateList,WorkLogQuery workLogQuery){
        Date startTime = workLogQuery.getStartTime();
        Date endTime = workLogQuery.getEndTime();
        String searchProjectId = workLogQuery.getProjectId();

        String sql = new String();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startTime", startTime);
        paramMap.put("endTime", endTime);
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(getJdbcTemplate());
        List<ProjectLog> projectGroupList = new ArrayList<ProjectLog>();
        List<ProjectLog> projectWorkItemLogList = new ArrayList<ProjectLog>();

        List<String> projectIds = new ArrayList<String>();
        //若有项目查询条件
        if(searchProjectId != null && searchProjectId.length()>0){
            paramMap.put("projectId", searchProjectId);
            sql = "select project_id,sum(takeup_time) as total from pmc_work_log t where work_date > (:startTime) and work_date < (:endTime) and project_id = (:projectId) group by t.project_id";
            projectGroupList = jdbc.query(sql, paramMap, new BeanPropertyRowMapper(ProjectLog.class));
            projectIds.add(searchProjectId);
        }

        //若有项目集查询条件,查找项目集的项目，并在此范围内以项目分组日志
        if(workLogQuery.getProjectSetId() != null && searchProjectId == null ){
            ProjectQuery projectQuery = new ProjectQuery();
            projectQuery.setProjectSetId(workLogQuery.getProjectSetId());
            List<Project> projectList = projectService.findProjectList(projectQuery);
            for (Project project : projectList) {
                projectIds.add(project.getId());
            }
            sql = "select project_id,sum(takeup_time) as total from pmc_work_log t where work_date > (:startTime) and work_date < (:endTime) group by t.project_id";
            projectGroupList = jdbc.query(sql, paramMap, new BeanPropertyRowMapper(ProjectLog.class));

        }

        //没有项目id与项目集id,查找所有项目
        if(workLogQuery.getProjectSetId() == null && searchProjectId == null ){
            ProjectQuery projectQuery = new ProjectQuery();
            projectQuery.setProjectSetId(workLogQuery.getProjectSetId());
            List<Project> projectList = projectService.findJoinProjectList(projectQuery);
            for (Project project : projectList) {
                projectIds.add(project.getId());
            }
            sql = "select project_id,sum(takeup_time) as total from pmc_work_log t where work_date > (:startTime) and work_date < (:endTime) group by t.project_id";
            projectGroupList = jdbc.query(sql, paramMap, new BeanPropertyRowMapper(ProjectLog.class));

        }

        // 循环统计项目的日志
        for (String projectId : projectIds) {
            ProjectLog projectWorkItemLog = new ProjectLog();
            List<ProjectLog> projectGroup = projectGroupList.stream().filter(a -> a.getProjectId().equals(projectId)).collect(Collectors.toList());

            if(projectGroup != null && projectGroup.size() > 0){
                projectWorkItemLog = projectGroup.get(0);
                Project project = projectService.findOne(projectId);
                projectWorkItemLog.setProject(project);

                Map<String, Object> paramMap1 = new HashMap<String, Object>();
                paramMap1.put("startTime", startTime);
                paramMap1.put("endTime", endTime);
                paramMap1.put("projectId", projectId);
                // 按照事项分组统计日志
                sql = "select work_item_id as workItemId,sum(takeup_time) as projectLogTotal from pmc_work_log t where work_date > (:startTime) and work_date < (:endTime) and t.project_id = (:projectId) group by t.work_item_id";
                NamedParameterJdbcTemplate jdbc1 = new NamedParameterJdbcTemplate(getJdbcTemplate());
                List<ProjectListLog> workItemListLogList = jdbc1.query(sql, paramMap1, new BeanPropertyRowMapper(ProjectListLog.class));

                //查找项目下所有的事项
                WorkItemQuery workItemQuery = new WorkItemQuery();
                workItemQuery.setProjectId(projectId);
                List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);
                List<String> workItemIds = workItemList.stream().map(WorkItem::getId).collect(Collectors.toList());

                List<ProjectListLog> workItemLogList = new ArrayList<ProjectListLog>();

                //循环统计事项的日志
                for (String workItemId : workItemIds) {
                    List<ProjectListLog> workItems = workItemListLogList.stream().filter(a -> a.getWorkItemId().equals(workItemId)).collect(Collectors.toList());
                    ProjectListLog workItemLog = new ProjectListLog();
                    if(workItems != null && workItems.size() > 0){
                        //设置事项信息
                        workItemLog = workItems.get(0);
                        WorkItem workItem = workItemService.findOne(workItemId);
                        workItemLog.setWorkItem(workItem);
                        workItemLog.setWorkItemId(workItemId);

                        //按照日历循环统计设置事项的日志统计
                        List<EneryDayTotal> integers = new ArrayList<EneryDayTotal>();
                        for(int i=0; i< dateList.size()-1; i++){
                            String s1 = dateList.get(i);
                            String s2 = dateList.get(i + 1);

                            Map<String, Object> paramMap2 = new HashMap<String, Object>();
                            paramMap2.put("startTime", s1);
                            paramMap2.put("endTime", s2);
                            paramMap2.put("projectId", projectId);
                            paramMap2.put("workItemId", workItemId);

                            sql = "select sum(takeup_time) as statistic from pmc_work_log t where work_date > (:startTime) and work_date < (:endTime) and t.project_id = (:projectId) and t.work_item_id = (:workItemId) group by t.project_id";
                            List<EneryDayTotal> query = jdbc1.query(sql, paramMap2, new BeanPropertyRowMapper(EneryDayTotal.class));
                            EneryDayTotal eneryDayTotal = new EneryDayTotal();
                            eneryDayTotal.setStatistic(0);
                            if(query != null && query.size() >= 1){
                                integers.add(query.get(0));
                            }else {
                                integers.add(eneryDayTotal);
                            }
                        }
                        workItemLog.setStatisticsList(integers);
                    }else {

                        WorkItem workItem = workItemService.findOne(workItemId);
                        workItemLog.setWorkItem(workItem);
                        workItemLog.setWorkItemId(workItemId);

                        //设置事项的日志统计
                        List<EneryDayTotal> integers = new ArrayList<EneryDayTotal>();
                        for(int i=0; i< dateList.size()-1; i++){
                            EneryDayTotal eneryDayTotal = new EneryDayTotal();
                            eneryDayTotal.setStatistic(0);
                            integers.add(eneryDayTotal);
                        }
                        workItemLog.setStatisticsList(integers);
                    }
                    workItemLogList.add(workItemLog);

                }
                projectWorkItemLog.setProjectListLogList(workItemLogList);

            }else {
                //若无项目日志统计，则设置所有事项日志为0
                projectWorkItemLog.setProjectId(projectId);
                Project project = projectService.findOne(projectId);
                projectWorkItemLog.setProject(project);
                projectWorkItemLog.setTotal(0);

                ArrayList<ProjectListLog> projectWorkItemListLogs = new ArrayList<ProjectListLog>();

                WorkItemQuery workItemQuery = new WorkItemQuery();
                workItemQuery.setProjectId(projectId);
                List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);
                for(WorkItem workItem:workItemList){
                    ProjectListLog projectWorkItemListLog = new ProjectListLog();

                    String id = workItem.getId();
                    projectWorkItemListLog.setWorkItemId(id);
                    projectWorkItemListLog.setWorkItem(workItem);

                    List<EneryDayTotal> statisticsLists = new ArrayList<EneryDayTotal>();

                    for(int i=0; i< dateList.size()-1; i++){
                        EneryDayTotal eneryDayTotal = new EneryDayTotal();
                        eneryDayTotal.setStatistic(0);
                        statisticsLists.add(eneryDayTotal);
                    }
                    projectWorkItemListLog.setStatisticsList(statisticsLists);
                    projectWorkItemListLogs.add(projectWorkItemListLog);
                }

                projectWorkItemLog.setProjectListLogList(projectWorkItemListLogs);
            }
            projectWorkItemLogList.add(projectWorkItemLog);
        }
        return projectWorkItemLogList;
    }



    public JdbcTemplate getJdbcTemplate() {

        return jpaTemplate.getJdbcTemplate();
    }



}