package io.tiklab.kanass.workitem.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.workitem.model.WorkItemRoleFunctionDm;
import io.tiklab.kanass.workitem.model.WorkItemRoleFunctionDmQuery;
import io.tiklab.kanass.workitem.service.WorkItemRoleFunctionDmService;
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
@RequestMapping("/workItemRoleFunctionDm")
//@Api(name = "WorkItemRoleFunctionDmController",desc = "事项优先级管理")
public class WorkItemRoleFunctionDmController {

    private static Logger logger = LoggerFactory.getLogger(WorkItemRoleFunctionDmController.class);

    @Autowired
    private WorkItemRoleFunctionDmService workItemRoleFunctionDmService;

    @RequestMapping(path="/createWorkItemRoleFunctionDm",method = RequestMethod.POST)
    //@ApiMethod(name = "createWorkItemRoleFunctionDm",desc = "创建优先级")
    //@ApiParam(name = "workItemRoleFunctionDm",desc = "优先级DTO",required = true)
    public Result<String> createWorkItemRoleFunctionDm(@RequestBody @NotNull @Valid WorkItemRoleFunctionDm workItemRoleFunctionDm){
        String id = workItemRoleFunctionDmService.createWorkItemRoleFunctionDm(workItemRoleFunctionDm);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkItemRoleFunctionDm",method = RequestMethod.POST)
    //@ApiMethod(name = "updateWorkItemRoleFunctionDm",desc = "更新优先级")
    //@ApiParam(name = "workItemRoleFunctionDm",desc = "优先级DTO",required = true)
    public Result<Void> updateWorkItemRoleFunctionDm(@RequestBody @NotNull @Valid WorkItemRoleFunctionDm workItemRoleFunctionDm){
        workItemRoleFunctionDmService.updateWorkItemRoleFunctionDm(workItemRoleFunctionDm);

        return Result.ok();
    }

    @RequestMapping(path="/updateWorkItemRoleFunctionDm1",method = RequestMethod.POST)
    //@ApiMethod(name = "updateWorkItemRoleFunctionDm1",desc = "更新优先级")
    //@ApiParam(name = "workItemRoleFunctionDm",desc = "优先级DTO",required = true)
    public Result<Void> updateWorkItemRoleFunctionDm1(){
        workItemRoleFunctionDmService.updateWorkItemRoleFunctionDm1();

        return Result.ok();
    }

    @RequestMapping(path="/updateWorkItemRoleAllFunctionDm",method = RequestMethod.POST)
    //@ApiMethod(name = "updateWorkItemRoleAllFunctionDm",desc = "更新优先级")
    //@ApiParam(name = "workItemRoleFunctionDm",desc = "优先级DTO",required = true)
    public Result<Void> updateWorkItemRoleAllFunctionDm(@RequestBody @NotNull @Valid WorkItemRoleFunctionDm workItemRoleFunctionDm){
        workItemRoleFunctionDmService.updateWorkItemRoleAllFunctionDm(workItemRoleFunctionDm);

        return Result.ok();
    }


    @RequestMapping(path="/deleteWorkItemRoleFunctionDm",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteWorkItemRoleFunctionDm",desc = "根据优先级ID删除优先级")
    //@ApiParam(name = "id",desc = "优先级ID",required = true)
    public Result<Void> deleteWorkItemRoleFunctionDm(@NotNull String id){
        workItemRoleFunctionDmService.deleteWorkItemRoleFunctionDm(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkItemRoleFunctionDm",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemRoleFunctionDm",desc = "根据优先级ID查找优先级")
    //@ApiParam(name = "id",desc = "优先级ID",required = true)
    public Result<WorkItemRoleFunctionDm> findWorkItemRoleFunctionDm(@NotNull String id){
        WorkItemRoleFunctionDm workItemRoleFunctionDm = workItemRoleFunctionDmService.findWorkItemRoleFunctionDm(id);

        return Result.ok(workItemRoleFunctionDm);
    }


    @RequestMapping(path="/findWorkItemRoleFunctionDmCode",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemRoleFunctionDmCode",desc = "查找权限code")
    public Result<List<String>> findWorkItemRoleFunctionDmCode(@RequestBody @Valid @NotNull WorkItemRoleFunctionDmQuery workItemRoleFunctionDmQuery){
        List<String> workItemRoleFunctionDmCode = workItemRoleFunctionDmService.findWorkItemRoleFunctionDmCode(workItemRoleFunctionDmQuery);

        return Result.ok(workItemRoleFunctionDmCode);
    }


    @RequestMapping(path="/findAllWorkItemRoleFunctionDm",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllWorkItemRoleFunctionDm",desc = "查找所有优先级")
    public Result<List<WorkItemRoleFunctionDm>> findAllWorkItemRoleFunctionDm(){
        List<WorkItemRoleFunctionDm> workItemRoleFunctionDmList = workItemRoleFunctionDmService.findAllWorkItemRoleFunctionDm();

        return Result.ok(workItemRoleFunctionDmList);
    }


    @RequestMapping(path = "/findWorkItemRoleFunctionDmList",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemRoleFunctionDmList",desc = "根据查询对象查找优先级列表")
    //@ApiParam(name = "workItemRoleFunctionDmQuery",desc = "查询对象",required = true)
    public Result<List<WorkItemRoleFunctionDm>> findWorkItemRoleFunctionDmList(@RequestBody @Valid @NotNull WorkItemRoleFunctionDmQuery workItemRoleFunctionDmQuery){
        List<WorkItemRoleFunctionDm> workItemRoleFunctionDmList = workItemRoleFunctionDmService.findWorkItemRoleFunctionDmList(workItemRoleFunctionDmQuery);

        return Result.ok(workItemRoleFunctionDmList);
    }


    @RequestMapping(path = "/findWorkItemRoleFunctionDmPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemRoleFunctionDmPage",desc = "根据查询对象按分页查询优先级列表")
    //@ApiParam(name = "workItemRoleFunctionDmQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkItemRoleFunctionDm>> findWorkItemRoleFunctionDmPage(@RequestBody @Valid @NotNull WorkItemRoleFunctionDmQuery workItemRoleFunctionDmQuery){
        Pagination<WorkItemRoleFunctionDm> pagination = workItemRoleFunctionDmService.findWorkItemRoleFunctionDmPage(workItemRoleFunctionDmQuery);

        return Result.ok(pagination);
    }

}
