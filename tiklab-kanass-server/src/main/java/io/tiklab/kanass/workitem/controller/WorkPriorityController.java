package io.tiklab.kanass.workitem.controller;

import io.tiklab.kanass.workitem.model.WorkPriority;
import io.tiklab.kanass.workitem.model.WorkPriorityQuery;
import io.tiklab.kanass.workitem.service.WorkPriorityService;
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

/**
 * 优先级控制器
 */
@RestController
@RequestMapping("/workPriority")
@Api(name = "WorkPriorityController",desc = "事项优先级管理")
public class WorkPriorityController {

    private static Logger logger = LoggerFactory.getLogger(WorkPriorityController.class);

    @Autowired
    private WorkPriorityService workPriorityService;

    @RequestMapping(path="/createWorkPriority",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkPriority",desc = "创建优先级")
    @ApiParam(name = "workPriority",desc = "优先级DTO",required = true)
    public Result<String> createWorkPriority(@RequestBody @NotNull @Valid WorkPriority workPriority){
        String id = workPriorityService.createWorkPriority(workPriority);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkPriority",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkPriority",desc = "更新优先级")
    @ApiParam(name = "workPriority",desc = "优先级DTO",required = true)
    public Result<Void> updateWorkPriority(@RequestBody @NotNull @Valid WorkPriority workPriority){
        workPriorityService.updateWorkPriority(workPriority);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkPriority",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkPriority",desc = "根据优先级ID删除优先级")
    @ApiParam(name = "id",desc = "优先级ID",required = true)
    public Result<Void> deleteWorkPriority(@NotNull String id){
        workPriorityService.deleteWorkPriority(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkPriority",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkPriority",desc = "根据优先级ID查找优先级")
    @ApiParam(name = "id",desc = "优先级ID",required = true)
    public Result<WorkPriority> findWorkPriority(@NotNull String id){
        WorkPriority workPriority = workPriorityService.findWorkPriority(id);

        return Result.ok(workPriority);
    }

    @RequestMapping(path="/findAllWorkPriority",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWorkPriority",desc = "查找所有优先级")
    public Result<List<WorkPriority>> findAllWorkPriority(){
        List<WorkPriority> workPriorityList = workPriorityService.findAllWorkPriority();

        return Result.ok(workPriorityList);
    }


    @RequestMapping(path = "/findWorkPriorityList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkPriorityList",desc = "根据查询对象查找优先级列表")
    @ApiParam(name = "workPriorityQuery",desc = "查询对象",required = true)
    public Result<List<WorkPriority>> findWorkPriorityList(@RequestBody @Valid @NotNull WorkPriorityQuery workPriorityQuery){
        List<WorkPriority> workPriorityList = workPriorityService.findWorkPriorityList(workPriorityQuery);

        return Result.ok(workPriorityList);
    }


    @RequestMapping(path = "/findWorkPriorityPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkPriorityPage",desc = "根据查询对象按分页查询优先级列表")
    @ApiParam(name = "workPriorityQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkPriority>> findWorkPriorityPage(@RequestBody @Valid @NotNull WorkPriorityQuery workPriorityQuery){
        Pagination<WorkPriority> pagination = workPriorityService.findWorkPriorityPage(workPriorityQuery);

        return Result.ok(pagination);
    }

}
