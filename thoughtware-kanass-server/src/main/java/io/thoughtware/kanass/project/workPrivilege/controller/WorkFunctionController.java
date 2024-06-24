package io.thoughtware.kanass.project.workPrivilege.controller;

import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.kanass.project.workPrivilege.model.WorkFunction;
import io.thoughtware.kanass.project.workPrivilege.model.WorkFunctionQuery;
import io.thoughtware.kanass.project.workPrivilege.service.WorkFunctionService;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
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
@RequestMapping("/workFunction")
@Api(name = "WorkFunctionController",desc = "事项优先级管理")
public class WorkFunctionController {

    private static Logger logger = LoggerFactory.getLogger(WorkFunctionController.class);

    @Autowired
    private WorkFunctionService workFunctionService;

    @RequestMapping(path="/createWorkFunction",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkFunction",desc = "创建优先级")
    @ApiParam(name = "workFunction",desc = "优先级DTO",required = true)
    public Result<String> createWorkFunction(@RequestBody @NotNull @Valid WorkFunction workFunction){
        String id = workFunctionService.createWorkFunction(workFunction);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkFunction",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkFunction",desc = "更新优先级")
    @ApiParam(name = "workFunction",desc = "优先级DTO",required = true)
    public Result<Void> updateWorkFunction(@RequestBody @NotNull @Valid WorkFunction workFunction){
        workFunctionService.updateWorkFunction(workFunction);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkFunction",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkFunction",desc = "根据优先级ID删除优先级")
    @ApiParam(name = "id",desc = "优先级ID",required = true)
    public Result<Void> deleteWorkFunction(@NotNull String id){
        workFunctionService.deleteWorkFunction(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkFunction",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkFunction",desc = "根据优先级ID查找优先级")
    @ApiParam(name = "id",desc = "优先级ID",required = true)
    public Result<WorkFunction> findWorkFunction(@NotNull String id){
        WorkFunction workFunction = workFunctionService.findWorkFunction(id);

        return Result.ok(workFunction);
    }

    @RequestMapping(path="/findAllWorkFunction",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWorkFunction",desc = "查找所有优先级")
    public Result<List<WorkFunction>> findAllWorkFunction(){
        List<WorkFunction> workFunctionList = workFunctionService.findAllWorkFunction();

        return Result.ok(workFunctionList);
    }


    @RequestMapping(path = "/findWorkFunctionList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkFunctionList",desc = "根据查询对象查找优先级列表")
    @ApiParam(name = "workFunctionQuery",desc = "查询对象",required = true)
    public Result<List<WorkFunction>> findWorkFunctionList(@RequestBody @Valid @NotNull WorkFunctionQuery workFunctionQuery){
        List<WorkFunction> workFunctionList = workFunctionService.findWorkFunctionList(workFunctionQuery);

        return Result.ok(workFunctionList);
    }


    @RequestMapping(path = "/findWorkFunctionPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkFunctionPage",desc = "根据查询对象按分页查询优先级列表")
    @ApiParam(name = "workFunctionQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkFunction>> findWorkFunctionPage(@RequestBody @Valid @NotNull WorkFunctionQuery workFunctionQuery){
        Pagination<WorkFunction> pagination = workFunctionService.findWorkFunctionPage(workFunctionQuery);

        return Result.ok(pagination);
    }

}
