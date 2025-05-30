package io.tiklab.kanass.project.worklog.controller;

import io.tiklab.kanass.project.worklog.model.WorkLog;
import io.tiklab.kanass.project.worklog.model.WorkLogQuery;
import io.tiklab.kanass.project.worklog.service.WorkLogService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 事项日志控制器
 */
@RestController
@RequestMapping("/workLog")
//@Api(name = "WorkLogController",desc = "工作日志管理")
public class WorkLogController {

    private static Logger logger = LoggerFactory.getLogger(WorkLogController.class);

    @Autowired
    private WorkLogService workLogService;

    @RequestMapping(path="/createWorkLog",method = RequestMethod.POST)
    //@ApiMethod(name = "createWorkLog",desc = "创建日志")
    //@ApiParam(name = "workLog",desc = "日志DTO",required = true)
    public Result<String> createWorkLog(@RequestBody @NotNull @Valid WorkLog workLog){
        String id = workLogService.createWorkLog(workLog);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkLog",method = RequestMethod.POST)
    //@ApiMethod(name = "updateWorkLog",desc = "更新日志")
    //@ApiParam(name = "workLog",desc = "日志DTO",required = true)
    public Result<Void> updateWorkLog(@RequestBody @NotNull @Valid WorkLog workLog){
        workLogService.updateWorkLog(workLog);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkLog",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteWorkLog",desc = "根据ID删除日志")
    //@ApiParam(name = "id",desc = "日志ID",required = true)
    public Result<Void> deleteWorkLog(@NotNull String id){
        workLogService.deleteWorkLog(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkLog",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkLog",desc = "根据ID查找日志")
    //@ApiParam(name = "id",desc = "日志ID",required = true)
    public Result<WorkLog> findWorkLog(@NotNull String id){
        WorkLog workLog = workLogService.findWorkLog(id);

        return Result.ok(workLog);
    }

    @RequestMapping(path="/findAllWorkLog",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllWorkLog",desc = "查找所有日志")
    public Result<List<WorkLog>> findAllWorkLog(){
        List<WorkLog> workLogList = workLogService.findAllWorkLog();

        return Result.ok(workLogList);
    }


    @RequestMapping(path = "/findWorkLogList",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkLogList",desc = "根据查询条件查找日志列表")
    //@ApiParam(name = "workLogQuery",desc = "查询对象",required = true)
    public Result<List<WorkLog>> findWorkLogList(@RequestBody @Valid @NotNull WorkLogQuery workLogQuery){
        List<WorkLog> workLogList = workLogService.findWorkLogList(workLogQuery);

        return Result.ok(workLogList);
    }


    @RequestMapping(path = "/findWorkLogPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkLogPage",desc = "根据查询条件按分页查找日志列表")
    //@ApiParam(name = "workLogQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkLog>> findWorkLogPage(@RequestBody @Valid @NotNull WorkLogQuery workLogQuery){
        Pagination<WorkLog> pagination = workLogService.findWorkLogPage(workLogQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path="/findProjectUserLog",method = RequestMethod.POST)
    //@ApiMethod(name = "findProjectUserLog",desc = "查询所有人员工时")
    //@ApiParam(name = "workLogQuery",desc = "工时查询对象",required = true)
    public Result<Map<String,Object>> findProjectUserLog(@RequestBody  @Valid WorkLogQuery workLogQuery){
        Map<String, Object> stringObjectMap = workLogService.findProjectUserLog(workLogQuery);

        return Result.ok(stringObjectMap);
    }

    @RequestMapping(path="/findUserProjectLog",method = RequestMethod.POST)
    //@ApiMethod(name = "findUserProjectLog",desc = "查询事项工时视图")
    //@ApiParam(name = "workLogQuery",desc = "事项工时查询对象",required = true)
    public Result<Map<String, Object>>  findUserProjectLog(@RequestBody @NotNull @Valid WorkLogQuery workLogQuery){
        Map<String, Object> stringObjectMap=  workLogService.findUserProjectLog(workLogQuery);

        return Result.ok(stringObjectMap);
    }

    @RequestMapping(path="/findProjectWorkItemLog",method = RequestMethod.POST)
    //@ApiMethod(name = "findProjectWorkItemLog",desc = "查询事项工时视图")
    //@ApiParam(name = "workLogQuery",desc = "事项工时查询对象",required = true)
    public Result<Map<String, Object>>  findProjectWorkItemLog(@RequestBody @NotNull @Valid WorkLogQuery workLogQuery){
        Map<String, Object> stringObjectMap=  workLogService.findProjectWorkItemLog(workLogQuery);

        return Result.ok(stringObjectMap);
    }
}
