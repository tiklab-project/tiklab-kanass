package io.tiklab.kanass.project.worklog.service;

import com.alibaba.fastjson.JSON;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.project.worklog.dao.WorkLogDao;
import io.tiklab.kanass.project.worklog.model.CalendarHeader;
import io.tiklab.kanass.project.worklog.model.ProjectLog;
import io.tiklab.kanass.project.worklog.model.WorkLog;
import io.tiklab.kanass.project.worklog.model.WorkLogQuery;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.model.WorkItemQuery;
import io.tiklab.kanass.workitem.service.WorkItemService;
import io.tiklab.security.logging.logging.model.Logging;
import io.tiklab.security.logging.logging.model.LoggingType;
import io.tiklab.security.logging.logging.service.LoggingByTempService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.project.worklog.entity.WorkLogEntity;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* 事项工时接口
*/
@Service
public class WorkLogServiceImpl implements WorkLogService {
    private static Logger logger = LoggerFactory.getLogger(WorkLogServiceImpl.class);
    @Autowired
    WorkLogDao workLogDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    UserProcessor userProcessor;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    LoggingByTempService opLogByTemplService;

    @Value("${base.url:null}")
    String baseUrl;

    @Override
    public String createWorkLog(@NotNull @Valid WorkLog workLog) {
        workLog.setWorkDate(new Timestamp(System.currentTimeMillis()));

        WorkLogEntity workLogEntity = BeanMapper.map(workLog, WorkLogEntity.class);
        return workLogDao.createWorkLog(workLogEntity);
    }

    @Override
    public void updateWorkLog(@NotNull @Valid WorkLog workLog) {
        WorkLog oldWorkLog = findWorkLog(workLog.getId());

        WorkLogEntity workLogEntity = BeanMapper.map(workLog, WorkLogEntity.class);
        workLogDao.updateWorkLog(workLogEntity);

        WorkLog newWorkLog = findWorkLog(workLog.getId());

        if (!oldWorkLog.getTakeupTime().equals(newWorkLog.getTakeupTime())){
            HashMap<String, Object> logContent = new HashMap<>();
            if(ObjectUtils.isEmpty(oldWorkLog.getTakeupTime())){
                logContent.put("oldValue", 0);
            }else {
                logContent.put("oldValue", oldWorkLog.getTakeupTime());
            }
            logContent.put("newValue", newWorkLog.getTakeupTime());
            creatUpdateOplog(newWorkLog, logContent, "KANASS_LOGTYPE_WORKUPDATETAKEUPTIME");
        }

    }

    @Override
    public void deleteWorkLog(@NotNull String id) {
        workLogDao.deleteWorkLog(id);
    }

    @Override
    public void deleteWorkLogList(@NotNull @Valid WorkLogQuery workLogQuery){
        DeleteBuilders deleteBuilders = DeleteBuilders.createDelete(WorkLogEntity.class)
                .eq("workItemId", workLogQuery.getWorkItemId());


        if(workLogQuery.getWorkItemIds()!= null && workLogQuery.getWorkItemIds().length != 0){
            deleteBuilders.in("workItemId", workLogQuery.getWorkItemIds());
        }
        DeleteCondition deleteCondition = deleteBuilders.get();
        workLogDao.deleteWorkLogList(deleteCondition);
    }

    @Override
    public WorkLog findWorkLog(@NotNull String id) {
        WorkLogEntity workLogEntity = workLogDao.findWorkLog(id);

        WorkLog workLog = BeanMapper.map(workLogEntity, WorkLog.class);

        joinTemplate.joinQuery(workLog, new String[]{"project", "user"});
        WorkItem workItem = workItemService.findWorkItem(workLog.getWorkItem().getId());
        workLog.setWorkItem(workItem);
        return workLog;
    }

    @Override
    public List<WorkLog> findAllWorkLog() {
        List<WorkLogEntity> workLogEntityList =  workLogDao.findAllWorkLog();

        List<WorkLog> workLogList = BeanMapper.mapList(workLogEntityList,WorkLog.class);

        joinTemplate.joinQuery(workLogList, new String[]{"workItem", "project", "user"});

        return workLogList;
    }

    @Override
    public List<WorkLog> findWorkLogList(WorkLogQuery workLogQuery) {
        List<WorkLogEntity> workLogEntityList = workLogDao.findWorkLogList(workLogQuery);

        List<WorkLog> workLogList = BeanMapper.mapList(workLogEntityList,WorkLog.class);

        joinTemplate.joinQuery(workLogList, new String[]{"workItem", "project", "user"});

        return workLogList;
    }

    @Override
    public Pagination<WorkLog> findWorkLogPage(WorkLogQuery workLogQuery) {

        Pagination<WorkLogEntity> pagination = workLogDao.findWorkLogPage(workLogQuery);

        List<WorkLog> workLogList = BeanMapper.mapList(pagination.getDataList(),WorkLog.class);
        joinTemplate.joinQuery(workLogList, new String[]{"workItem", "project", "user"});
        // 获取日志的事项详情
        List<String> workItemIds = workLogList.stream().map(workLog -> workLog.getWorkItem().getId()).collect(Collectors.toList());
        WorkItemQuery workItemQuery = new WorkItemQuery();
        String[] ids = workItemIds.toArray(new String[0]);
        workItemQuery.setIds(ids);
        List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);

        for (WorkLog workLog : workLogList) {
            String id = workLog.getWorkItem().getId();
            List<WorkItem> workItemList1 = workItemList.stream().filter(workItem -> workItem.getId().equals(id)).collect(Collectors.toList());
            //设置日志的关联事项的信息
            workLog.setWorkItem(workItemList1.get(0));
        }
        return PaginationBuilder.build(pagination,workLogList);
    }

    /**
     * 统计项目成员的日志
     * @param workLogQuery
     * @return
     */
    @Override
    public Map<String,Object> findProjectUserLog(WorkLogQuery workLogQuery) {
        //开始时间
        Date startTime = workLogQuery.getStartTime();
        //结束时间
        Date endTime = workLogQuery.getEndTime();
        //当前页
        Integer currentPage = workLogQuery.getCurrentPage();
        //每页条数
        Integer pageNum = workLogQuery.getPageNum();  //每页条数

        //计算传入时间段之间的天数
        int intervalDay = differentDays(startTime, endTime);
        //计算分页跳转之后的日期
        Date eachStartDay = countDay(currentPage, pageNum, startTime);

        Map<String, Object> workItemManhour=null;
        //传入时间段天数小于等与每页条数
        if (intervalDay <= pageNum){
            workItemManhour = findProjectUserhour(eachStartDay, intervalDay,workLogQuery,intervalDay);
        }
        //传入时间段天数大于每页条数
        if (intervalDay>pageNum){
            int remain = intervalDay - (currentPage - 1)*pageNum;
            if (remain<pageNum && remain>0){
                workItemManhour = findProjectUserhour(eachStartDay,remain,workLogQuery,intervalDay);
            }else {
                workItemManhour = findProjectUserhour(eachStartDay,pageNum,workLogQuery,intervalDay);
            }
        }
        return workItemManhour;
    }

    /**
     * 查询成员负责的每个项目的工时
     * @param workLogQuery
     * @return
     */
    @Override
    public Map<String, Object> findUserProjectLog(WorkLogQuery workLogQuery) {
        Date startTime = workLogQuery.getStartTime();  //开始时间
        Date endTime = workLogQuery.getEndTime();  //结束时间
        Integer currentPage = workLogQuery.getCurrentPage(); //当前页
        Integer pageNum = workLogQuery.getPageNum();  //每页条数

        //计算传入时间段之间的天数
        int intervalDay = differentDays(startTime, endTime);

        //计算分页跳转之后的日期
        Date eachStartDay = countDay(currentPage, pageNum, startTime);

        Map<String, Object> workItemManhour=null;
        //传入时间段天数小于等与每页条数
        if (intervalDay<=pageNum){
            workItemManhour = findUserProjecthour(eachStartDay, intervalDay,workLogQuery,intervalDay);
        }
        //传入时间段天数大于每页条数
        if (intervalDay>pageNum){
            int remain = intervalDay - (currentPage - 1)*pageNum;
            if (remain<pageNum&&remain>0){
                workItemManhour = findUserProjecthour(eachStartDay,remain,workLogQuery,intervalDay);
            }else {
                workItemManhour = findUserProjecthour(eachStartDay,pageNum,workLogQuery,intervalDay);
            }
        }
        return workItemManhour;
    }

    /**
     * 统计人员负责的项目日志
     * @param
     * @return
     */
    public Map<String,Object> findUserProjecthour(Date eachStartDay,Integer pageNum,WorkLogQuery workLogQuery,int intervalDay){
        HashMap<String, Object> returnDateMap = new HashMap<>();
        Date startTime = workLogQuery.getStartTime();

        //根据起始日期，分页条数，总间隔日期拼接日历，包括年月日，和周几
        Map<String, List> dayMap = getDaylist(eachStartDay, pageNum,startTime,intervalDay);

        List<String> dateList = dayMap.get("dateList");
        List<CalendarHeader> headers = dayMap.get("headerDay");

        List<ProjectLog> projectLog = workLogDao.findUserProjectLog(dateList,workLogQuery);

        returnDateMap.put("headerDay",headers);
        returnDateMap.put("workItemManhour",projectLog);
        returnDateMap.put("total",intervalDay);
        return returnDateMap;
    }

    /**
     * 查询项目下所有成员的日志
     * @param
     * @return
     */
    public Map<String,Object> findProjectUserhour(Date eachStartDay,Integer pageNum, WorkLogQuery workLogQuery, int intervalDay){
        HashMap<String, Object> returnDateMap = new HashMap<>();
        Date startTime = workLogQuery.getStartTime();

        //根据起始日期，分页条数，总间隔日期拼接日历，包括年月日，和周几
        Map<String, List> dayMap = getDaylist(eachStartDay, pageNum,startTime,intervalDay);

        List<String> dateList = dayMap.get("dateList");
        List<CalendarHeader> headers = dayMap.get("headerDay");

        //根据日历查找项目成员每天的日志
        List<ProjectLog> projectLog = workLogDao.findProjectUserhour(dateList,workLogQuery);

        //日历
        returnDateMap.put("headerDay",headers);
        returnDateMap.put("workItemManhour",projectLog);
        returnDateMap.put("total",intervalDay);
        return returnDateMap;
    }

    @Override
    public Map<String,Object> findProjectWorkItemLog(WorkLogQuery workLogQuery) {
        Date startTime = workLogQuery.getStartTime();  //开始时间
        Date endTime = workLogQuery.getEndTime();  //结束时间
        Integer currentPage = workLogQuery.getCurrentPage(); //当前页
        Integer pageNum = workLogQuery.getPageNum();  //每页条数

        //计算传入时间段之间的天数
        int intervalDay = differentDays(startTime, endTime);


        Date eachStartDay = countDay(currentPage, pageNum, startTime);

        Map<String, Object> workItemManhour=null;
        //传入时间段天数小于等与每页条数
        if (intervalDay<=pageNum){
            workItemManhour = findProjectWorkItemhour(eachStartDay, intervalDay,workLogQuery,intervalDay);
        }
        //传入时间段天数大于每页条数
        if (intervalDay>pageNum){
            int remain = intervalDay - (currentPage - 1)*pageNum;
            if (remain<pageNum&&remain>0){
                workItemManhour = findProjectWorkItemhour(eachStartDay,remain,workLogQuery,intervalDay);
            }else {
                workItemManhour = findProjectWorkItemhour(eachStartDay,pageNum,workLogQuery,intervalDay);
            }
        }
        return workItemManhour;
    }

    @Override
    public Integer findWorkItemUsedTime(String workId) {
        Integer workItemUsedTime = workLogDao.findWorkItemUsedTime(workId);
        return workItemUsedTime;
    }

    /**
     * 统计项目事项日志，拼接日历，并有日历循环
     * @param eachStartDay
     * @param pageNum
     * @param workLogQuery
     * @param intervalDay
     * @return
     */
    public Map<String,Object> findProjectWorkItemhour(Date eachStartDay,Integer pageNum, WorkLogQuery workLogQuery, int intervalDay){
        HashMap<String, Object> returnDateMap = new HashMap<>();
        Date startTime = workLogQuery.getStartTime();

        Map<String, List> dayMap = getDaylist(eachStartDay, pageNum,startTime,intervalDay);

        List<String> dateList = dayMap.get("dateList");
        List<CalendarHeader> headers = dayMap.get("headerDay");

        List<ProjectLog> projectLog = workLogDao.findProjectWorkItemhour(dateList,workLogQuery);

        returnDateMap.put("headerDay",headers);
        returnDateMap.put("workItemManhour",projectLog);
        returnDateMap.put("total",intervalDay);
        return returnDateMap;
    }
    /**
     * 分页跳转后 当前页的第一天时间
     * @param
     * @return
     */
    public Date countDay( Integer currentPage,int pageNum,Date startTime){
        //计算跳转多少天后的时间
        int dayNum = (currentPage - 1) * pageNum;
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        cal.add(Calendar.DATE,dayNum);
        return cal.getTime();
    }

    public Map<String, List> getDaylist(Date eachStartDay,Integer pageNum,Date startTime,int interval){

        Map<String, List> workLogHashMap = new HashMap<>();
        String[] weekDays = { "星期日","星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(eachStartDay);

        //把周一设置为一周第一天
        cal.setFirstDayOfWeek(Calendar.MONDAY);

        //当前时间是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);

        //这个list只装时间
        List<String> dateList = new ArrayList<>();
        //这个list里面是对象 日历头部
        List<CalendarHeader> headers=new ArrayList<>();

        //循环添加当前页的时间
        for (int i=0;i<pageNum;i++){
            CalendarHeader calendarHeader = new CalendarHeader();
            if (i!=0){
                //在当前时间加1
                cal.add(Calendar.DATE,1);
            }
            String format1 = sdf.format(cal.getTime());
            dateList.add(format1);

            String weekDay = weekDays[day-1];
            calendarHeader.setDateTime(format1);
            calendarHeader.setWeekDay(weekDay);
            headers.add(calendarHeader);

            day+=1;
            if (day==8){
                day=1;
            }
        }

        //查询时间小于第二天凌晨
        cal.add(Calendar.DATE,1);
        String format1 = sdf.format(cal.getTime());
        dateList.add(format1);

        //查询的时间列表
        List<String> allWork = findAllWork(startTime, interval, sdf);

        workLogHashMap.put("headerDay",headers);
        workLogHashMap.put("dateList",dateList);
        workLogHashMap.put("allWork",allWork);
        return workLogHashMap;
    }


    /**
     * 计算两个时间段之间天数
     * @param
     * @return
     */
    @Override
    public int differentDays(Date startTime, Date endTime){

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(startTime);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(endTime);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2) {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++) {
                //闰年
                if(i%4==0 && i%100!=0 || i%400==0){
                    timeDistance += 366;
                } else{    //不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2-day1)+1 ;
        } else{    //同年
            return day2-day1+1;
        }
    }

    /**
     * 把数据放入 allDate
     * @param
     * @return
     */
    public List<String> findAllWork(Date datetme,int interval,SimpleDateFormat sdf){
        //放的总的传入时间
        List<String> allDate=new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(datetme);
        //根据时间段查询总工时
        for (int a=0;a<interval;a++){
            if (a==0){
                String format = sdf.format(cal.getTime());
                allDate.add(format);
            }

            //对日期进行加一操作
            cal.add(Calendar.DATE,1);
            String format = sdf.format(cal.getTime());
            allDate.add(format);
        }
        return allDate;
    }

    /**
     * 更新事项创建日志
     * @param logContent
     * @param workLog
     */
    void creatUpdateOplog(WorkLog workLog, HashMap<String, Object> logContent, String actionType){
        Logging log = new Logging();
        log.setBgroup("kanass");
        log.setModule("workItem");
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));

        String createUserId = LoginContext.getLoginId();
        User user = userProcessor.findOne(createUserId);
        log.setUser(user);

        logContent.put("workItemTitle", workLog.getWorkItem().getTitle());
        logContent.put("workItemId", workLog.getWorkItem().getId());
        logContent.put("projectId", workLog.getWorkItem().getProject().getId());
        logContent.put("master", user);
        logContent.put("receiveTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        logContent.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());

        if(workLog.getWorkItem().getSprint() != null) {
            logContent.put("sprintId", workLog.getWorkItem().getSprint().getId());
        }
        if(workLog.getWorkItem().getProjectVersion() != null) {
            logContent.put("versionId", workLog.getWorkItem().getProjectVersion().getId());
        }

        LoggingType opLogType = new LoggingType();
        opLogType.setId(actionType);
        log.setActionType(opLogType);

        log.setBaseUrl(baseUrl);
        log.setAction(workLog.getWorkItem().getTitle());
        log.setLink("/project/${projectId}/workitem/${workItemId}");
        log.setData(JSON.toJSONString(logContent));

        opLogByTemplService.createLog(log);
    }
}