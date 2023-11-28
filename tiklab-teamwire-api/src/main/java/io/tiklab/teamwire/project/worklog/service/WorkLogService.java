package io.tiklab.teamwire.project.worklog.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.teamwire.project.worklog.model.WorkLog;
import io.tiklab.teamwire.project.worklog.model.WorkLogQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* 事项日志服务接口
*/
public interface WorkLogService {

    /**
    * 创建工作日志
    * @param workLog
    * @return
    */
    String createWorkLog(@NotNull @Valid WorkLog workLog);

    /**
    * 更新工作日志
    * @param workLog
    */
    void updateWorkLog(@NotNull @Valid WorkLog workLog);

    /**
    * 删除工作日志
    * @param id
    */
    void deleteWorkLog(@NotNull String id);

    /**
    * 根据id查找工作日志
    * @param id
    * @return
    */
    WorkLog findWorkLog(@NotNull String id);

    /**
    * 查找所有工作日志
    * @return
    */
    List<WorkLog> findAllWorkLog();

    /**
    * 按照条件查询工作日志列表
    * @param workLogQuery
    * @return
    */
    List<WorkLog> findWorkLogList(WorkLogQuery workLogQuery);

    /**
    * 根据条件按照分页查找日志列表
    * @param workLogQuery
    * @return
    */
    Pagination<WorkLog> findWorkLogPage(WorkLogQuery workLogQuery);

    /**
     * 查询项目每个成员的工时
     * @param workLogQuery
     * @return
     */
    Map<String,Object> findProjectUserLog(WorkLogQuery workLogQuery);
    int differentDays(Date startTime, Date endTime);
    /**
     * 查询成员负责的每个项目的工时
     * @param workLogQuery
     * @return
     */
    Map<String, Object> findUserProjectLog(WorkLogQuery workLogQuery);

    /**
     * 查找项目下每个事项的工时
     * @param workLogQuery
     * @return
     */
    Map<String, Object> findProjectWorkItemLog(WorkLogQuery workLogQuery);
}