package io.thoughtware.kanass.workitem.controller;

import io.thoughtware.kanass.workitem.model.WorkStatus;
import io.thoughtware.kanass.workitem.model.WorkStatusQuery;
import io.thoughtware.kanass.workitem.service.WorkStatusService;
import io.thoughtware.dal.jdbc.support.sort.model.Sort;
import io.thoughtware.dal.jdbc.support.sort.processor.SortProcessor;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 事项状态控制器
 */
@RestController
@RequestMapping("/workStatus")
@Api(name = "WorkStatusController",desc = "事项状态管理")
public class WorkStatusController {

    private static Logger logger = LoggerFactory.getLogger(WorkStatusController.class);

    @Autowired
    private WorkStatusService workStatusService;

    @Autowired
    SortProcessor sortService;

    @RequestMapping(path="/createWorkStatus",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkStatus",desc = "创建事项状态")
    @ApiParam(name = "workStatus",desc = "事项状态DTO",required = true)
    public Result<String> createWorkStatus(@RequestBody @NotNull @Valid WorkStatus workStatus){
        String id = workStatusService.createWorkStatus(workStatus);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkStatus",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkStatus",desc = "更新事项状态")
    @ApiParam(name = "workStatus",desc = "事项状态DTO",required = true)
    public Result<Void> updateWorkStatus(@RequestBody @NotNull @Valid WorkStatus workStatus){
        workStatusService.updateWorkStatus(workStatus);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkStatus",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkStatus",desc = "根据状态ID删除事项状态")
    @ApiParam(name = "id",desc = "事项状态ID",required = true)
    public Result<Void> deleteWorkStatus(@NotNull String id){
        workStatusService.deleteWorkStatus(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkStatus",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkStatus",desc = "根据事项状态ID查找事项状态")
    @ApiParam(name = "id",desc = "事项状态ID",required = true)
    public Result<WorkStatus> findWorkStatus(@NotNull String id){
        WorkStatus workStatus = workStatusService.findWorkStatus(id);

        return Result.ok(workStatus);
    }

    @RequestMapping(path="/findAllWorkStatus",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWorkStatus",desc = "查找所有事项状态列表")
    public Result<List<WorkStatus>> findAllWorkStatus(){
        List<WorkStatus> workStatusList = workStatusService.findAllWorkStatus();

        return Result.ok(workStatusList);
    }


    @RequestMapping(path = "/findWorkStatusList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkStatusList",desc = "根据查询对象查找事项状态列表")
    @ApiParam(name = "workStatusQuery",desc = "查询对象",required = true)
    public Result<List<WorkStatus>> findWorkStatusList(@RequestBody @Valid @NotNull WorkStatusQuery workStatusQuery){
        List<WorkStatus> workStatusList = workStatusService.findWorkStatusList(workStatusQuery);

        return Result.ok(workStatusList);
    }

    @RequestMapping(path = "/findWorkStatusListBySorts",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkStatusListBySorts",desc = "根据查询对象查找事项状态列表")
    @ApiParam(name = "workStatusQuery",desc = "查询对象",required = true)
    public Result<List<WorkStatus>> findWorkStatusListBySorts(@RequestBody @Valid @NotNull WorkStatusQuery workStatusQuery){
        List<WorkStatus> workStatusList = workStatusService.findWorkStatusListBySorts(workStatusQuery);

        return Result.ok(workStatusList);
    }

    @RequestMapping(path = "/findWorkStatusPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkStatusPage",desc = "根据查询对象按分页查找事项状态列表")
    @ApiParam(name = "workStatusQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkStatus>> findWorkStatusPage(@RequestBody @Valid @NotNull WorkStatusQuery workStatusQuery){
        Pagination<WorkStatus> pagination = workStatusService.findWorkStatusPage(workStatusQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path="/exchange",method = RequestMethod.POST)
    @ApiMethod(name = "exchange",desc = "交换排序")
    @ApiParam(name = "exchange",desc = "交换排序DTO",required = true)
    public Result<Void> exchange(@RequestBody @Valid @NotNull Sort exchange){
        sortService.sort("pmc_work_status","id","sort",exchange);

        return Result.ok();
    }

}
