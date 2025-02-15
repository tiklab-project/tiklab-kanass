package io.tiklab.kanass.project.workPrivilege.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.workPrivilege.model.WorkRoleFunction;
import io.tiklab.kanass.project.workPrivilege.model.WorkRoleFunctionQuery;
import io.tiklab.kanass.project.workPrivilege.service.WorkRoleFunctionService;
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
import java.util.Set;

/**
 * 事项角色权限接口, 弃用
 */
@RestController
@RequestMapping("/workRoleFunction")
@Api(name = "WorkRoleFunctionController",desc = "事项优先级管理")
public class WorkRoleFunctionController {

    private static Logger logger = LoggerFactory.getLogger(WorkRoleFunctionController.class);

    @Autowired
    private WorkRoleFunctionService workRoleFunctionService;

    @RequestMapping(path="/createWorkRoleFunction",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkRoleFunction",desc = "创建优先级")
    @ApiParam(name = "workRoleFunction",desc = "优先级DTO",required = true)
    public Result<String> createWorkRoleFunction(@RequestBody @NotNull @Valid WorkRoleFunction workRoleFunction){
        String id = workRoleFunctionService.createWorkRoleFunction(workRoleFunction);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkRoleFunction",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkRoleFunction",desc = "更新优先级")
    @ApiParam(name = "workRoleFunction",desc = "优先级DTO",required = true)
    public Result<Void> updateWorkRoleFunction(@RequestBody @NotNull @Valid WorkRoleFunction workRoleFunction){
        workRoleFunctionService.updateWorkRoleFunction(workRoleFunction);

        return Result.ok();
    }

    @RequestMapping(path="/updateWorkRoleAllFunction",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkRoleAllFunction",desc = "更新优先级")
    @ApiParam(name = "workRoleFunction",desc = "优先级DTO",required = true)
    public Result<Void> updateWorkRoleAllFunction(@RequestBody @NotNull @Valid WorkRoleFunction workRoleFunction){
        workRoleFunctionService.updateWorkRoleAllFunction(workRoleFunction);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkRoleFunction",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkRoleFunction",desc = "根据优先级ID删除优先级")
    @ApiParam(name = "id",desc = "优先级ID",required = true)
    public Result<Void> deleteWorkRoleFunction(@NotNull String id){
        workRoleFunctionService.deleteWorkRoleFunction(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkRoleFunction",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkRoleFunction",desc = "根据优先级ID查找优先级")
    @ApiParam(name = "id",desc = "优先级ID",required = true)
    public Result<WorkRoleFunction> findWorkRoleFunction(@NotNull String id){
        WorkRoleFunction workRoleFunction = workRoleFunctionService.findWorkRoleFunction(id);

        return Result.ok(workRoleFunction);
    }




    @RequestMapping(path = "/findWorkRoleFunctionList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkRoleFunctionList",desc = "根据查询对象查找优先级列表")
    @ApiParam(name = "workRoleFunctionQuery",desc = "查询对象",required = true)
    public Result<List<WorkRoleFunction>> findWorkRoleFunctionList(@RequestBody @Valid @NotNull WorkRoleFunctionQuery workRoleFunctionQuery){
        List<WorkRoleFunction> workRoleFunctionList = workRoleFunctionService.findWorkRoleFunctionList(workRoleFunctionQuery);

        return Result.ok(workRoleFunctionList);
    }


    @RequestMapping(path = "/findWorkRoleFunctionPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkRoleFunctionPage",desc = "根据查询对象按分页查询优先级列表")
    @ApiParam(name = "workRoleFunctionQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkRoleFunction>> findWorkRoleFunctionPage(@RequestBody @Valid @NotNull WorkRoleFunctionQuery workRoleFunctionQuery){
        Pagination<WorkRoleFunction> pagination = workRoleFunctionService.findWorkRoleFunctionPage(workRoleFunctionQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findUserWorkFunction",method = RequestMethod.POST)
    @ApiMethod(name = "findUserWorkFunction",desc = "根据查询对象按分页查询优先级列表")
    @ApiParam(name = "workRoleFunctionQuery",desc = "查询对象",required = true)
    public Result<Set<String>> findUserWorkFunction(@NotNull String userId, @NotNull String workId){
        Set<String> userWorkFunction = workRoleFunctionService.findUserWorkFunction(userId, workId);

        return Result.ok(userWorkFunction);
    }

}
