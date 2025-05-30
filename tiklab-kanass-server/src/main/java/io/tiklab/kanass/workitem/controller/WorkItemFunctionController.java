package io.tiklab.kanass.workitem.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.workitem.model.WorkItemFunction;
import io.tiklab.kanass.workitem.model.WorkItemFunctionQuery;
import io.tiklab.kanass.workitem.service.WorkItemFunctionService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
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
 * 优先级控制器
 */
@RestController
@RequestMapping("/workItemFunction")
//@Api(name = "WorkItemFunctionController",desc = "事项优先级管理")
public class WorkItemFunctionController {

    private static Logger logger = LoggerFactory.getLogger(WorkItemFunctionController.class);

    @Autowired
    private WorkItemFunctionService workItemFunctionService;

    @RequestMapping(path="/createWorkItemFunction",method = RequestMethod.POST)
    //@ApiMethod(name = "createWorkItemFunction",desc = "创建优先级")
    //@ApiParam(name = "workItemFunction",desc = "优先级DTO",required = true)
    public Result<String> createWorkItemFunction(@RequestBody @NotNull @Valid WorkItemFunction workItemFunction){
        String id = workItemFunctionService.createWorkItemFunction(workItemFunction);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkItemFunction",method = RequestMethod.POST)
    //@ApiMethod(name = "updateWorkItemFunction",desc = "更新优先级")
    //@ApiParam(name = "workItemFunction",desc = "优先级DTO",required = true)
    public Result<Void> updateWorkItemFunction(@RequestBody @NotNull @Valid WorkItemFunction workItemFunction){
        workItemFunctionService.updateWorkItemFunction(workItemFunction);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkItemFunction",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteWorkItemFunction",desc = "根据优先级ID删除优先级")
    //@ApiParam(name = "id",desc = "优先级ID",required = true)
    public Result<Void> deleteWorkItemFunction(@NotNull String id){
        workItemFunctionService.deleteWorkItemFunction(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkItemFunction",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemFunction",desc = "根据优先级ID查找优先级")
    //@ApiParam(name = "id",desc = "优先级ID",required = true)
    public Result<WorkItemFunction> findWorkItemFunction(@NotNull String id){
        WorkItemFunction workItemFunction = workItemFunctionService.findWorkItemFunction(id);

        return Result.ok(workItemFunction);
    }

    @RequestMapping(path="/findAllWorkItemFunction",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllWorkItemFunction",desc = "查找所有优先级")
    public Result<List<WorkItemFunction>> findAllWorkItemFunction(){
        List<WorkItemFunction> workItemFunctionList = workItemFunctionService.findAllWorkItemFunction();

        return Result.ok(workItemFunctionList);
    }


    @RequestMapping(path = "/findWorkItemFunctionList",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemFunctionList",desc = "根据查询对象查找优先级列表")
    //@ApiParam(name = "workItemFunctionQuery",desc = "查询对象",required = true)
    public Result<List<WorkItemFunction>> findWorkItemFunctionList(@RequestBody @Valid @NotNull WorkItemFunctionQuery workItemFunctionQuery){
        List<WorkItemFunction> workItemFunctionList = workItemFunctionService.findWorkItemFunctionList(workItemFunctionQuery);

        return Result.ok(workItemFunctionList);
    }


    @RequestMapping(path = "/findWorkItemFunctionPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemFunctionPage",desc = "根据查询对象按分页查询优先级列表")
    //@ApiParam(name = "workItemFunctionQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkItemFunction>> findWorkItemFunctionPage(@RequestBody @Valid @NotNull WorkItemFunctionQuery workItemFunctionQuery){
        Pagination<WorkItemFunction> pagination = workItemFunctionService.findWorkItemFunctionPage(workItemFunctionQuery);

        return Result.ok(pagination);
    }

}
