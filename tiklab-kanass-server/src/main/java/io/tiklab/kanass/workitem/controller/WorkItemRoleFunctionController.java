package io.tiklab.kanass.workitem.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.workitem.model.WorkItemRoleFunction;
import io.tiklab.kanass.workitem.model.WorkItemRoleFunctionQuery;
import io.tiklab.kanass.workitem.service.WorkItemRoleFunctionService;
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
@RequestMapping("/workItemRoleFunction")
//@Api(name = "WorkItemRoleFunctionController",desc = "事项优先级管理")
public class WorkItemRoleFunctionController {

    private static Logger logger = LoggerFactory.getLogger(WorkItemRoleFunctionController.class);

    @Autowired
    private WorkItemRoleFunctionService workItemRoleFunctionService;

    @RequestMapping(path="/createWorkItemRoleFunction",method = RequestMethod.POST)
    //@ApiMethod(name = "createWorkItemRoleFunction",desc = "创建优先级")
    //@ApiParam(name = "workItemRoleFunction",desc = "优先级DTO",required = true)
    public Result<String> createWorkItemRoleFunction(@RequestBody @NotNull @Valid WorkItemRoleFunction workItemRoleFunction){
        String id = workItemRoleFunctionService.createWorkItemRoleFunction(workItemRoleFunction);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkItemRoleFunction",method = RequestMethod.POST)
    //@ApiMethod(name = "updateWorkItemRoleFunction",desc = "更新优先级")
    //@ApiParam(name = "workItemRoleFunction",desc = "优先级DTO",required = true)
    public Result<Void> updateWorkItemRoleFunction(@RequestBody @NotNull @Valid WorkItemRoleFunction workItemRoleFunction){
        workItemRoleFunctionService.updateWorkItemRoleFunction(workItemRoleFunction);

        return Result.ok();
    }
    @RequestMapping(path="/updateWorkItemRoleAllFunction",method = RequestMethod.POST)
    //@ApiMethod(name = "updateWorkItemRoleAllFunction",desc = "更新优先级")
    //@ApiParam(name = "workItemRoleFunction",desc = "优先级DTO",required = true)
    public Result<Void> updateWorkItemRoleAllFunction(@RequestBody @NotNull @Valid WorkItemRoleFunction workItemRoleFunction){
        workItemRoleFunctionService.updateWorkItemRoleAllFunction(workItemRoleFunction);

        return Result.ok();
    }


    @RequestMapping(path="/deleteWorkItemRoleFunction",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteWorkItemRoleFunction",desc = "根据优先级ID删除优先级")
    //@ApiParam(name = "id",desc = "优先级ID",required = true)
    public Result<Void> deleteWorkItemRoleFunction(@NotNull String id){
        workItemRoleFunctionService.deleteWorkItemRoleFunction(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkItemRoleFunction",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemRoleFunction",desc = "根据优先级ID查找优先级")
    //@ApiParam(name = "id",desc = "优先级ID",required = true)
    public Result<WorkItemRoleFunction> findWorkItemRoleFunction(@NotNull String id){
        WorkItemRoleFunction workItemRoleFunction = workItemRoleFunctionService.findWorkItemRoleFunction(id);

        return Result.ok(workItemRoleFunction);
    }

    @RequestMapping(path="/findAllWorkItemRoleFunction",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllWorkItemRoleFunction",desc = "查找所有优先级")
    public Result<List<WorkItemRoleFunction>> findAllWorkItemRoleFunction(){
        List<WorkItemRoleFunction> workItemRoleFunctionList = workItemRoleFunctionService.findAllWorkItemRoleFunction();

        return Result.ok(workItemRoleFunctionList);
    }


    @RequestMapping(path = "/findWorkItemRoleFunctionList",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemRoleFunctionList",desc = "根据查询对象查找优先级列表")
    //@ApiParam(name = "workItemRoleFunctionQuery",desc = "查询对象",required = true)
    public Result<List<WorkItemRoleFunction>> findWorkItemRoleFunctionList(@RequestBody @Valid @NotNull WorkItemRoleFunctionQuery workItemRoleFunctionQuery){
        List<WorkItemRoleFunction> workItemRoleFunctionList = workItemRoleFunctionService.findWorkItemRoleFunctionList(workItemRoleFunctionQuery);

        return Result.ok(workItemRoleFunctionList);
    }


    @RequestMapping(path = "/findWorkItemRoleFunctionPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemRoleFunctionPage",desc = "根据查询对象按分页查询优先级列表")
    //@ApiParam(name = "workItemRoleFunctionQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkItemRoleFunction>> findWorkItemRoleFunctionPage(@RequestBody @Valid @NotNull WorkItemRoleFunctionQuery workItemRoleFunctionQuery){
        Pagination<WorkItemRoleFunction> pagination = workItemRoleFunctionService.findWorkItemRoleFunctionPage(workItemRoleFunctionQuery);

        return Result.ok(pagination);
    }

}
