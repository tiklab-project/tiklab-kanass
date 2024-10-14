package io.tiklab.kanass.project.workPrivilege.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.workPrivilege.model.WorkPrivilege;
import io.tiklab.kanass.project.workPrivilege.model.WorkPrivilegeQuery;
import io.tiklab.kanass.project.workPrivilege.service.WorkPrivilegeService;
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
@RequestMapping("/workPrivilege")
@Api(name = "WorkPrivilegeController",desc = "事项优先级管理")
public class WorkPrivilegeController {

    private static Logger logger = LoggerFactory.getLogger(WorkPrivilegeController.class);

    @Autowired
    private WorkPrivilegeService workPrivilegeService;

    @RequestMapping(path="/createWorkPrivilege",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkPrivilege",desc = "创建优先级")
    @ApiParam(name = "workPrivilege",desc = "优先级DTO",required = true)
    public Result<String> createWorkPrivilege(@RequestBody @NotNull @Valid WorkPrivilege workPrivilege){
        String id = workPrivilegeService.createWorkPrivilege(workPrivilege);

        return Result.ok(id);
    }


    @RequestMapping(path="/copyWorkPrivilege",method = RequestMethod.POST)
    @ApiMethod(name = "copyWorkPrivilege",desc = "创建优先级")
    @ApiParam(name = "workPrivilege",desc = "优先级DTO",required = true)
    public Result<String> copyWorkPrivilege(@RequestBody @NotNull @Valid WorkPrivilege workPrivilege, String dmWorkTypeId){
        String id = workPrivilegeService.copyWorkPrivilege(workPrivilege, dmWorkTypeId);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkPrivilege",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkPrivilege",desc = "更新优先级")
    @ApiParam(name = "workPrivilege",desc = "优先级DTO",required = true)
    public Result<Void> updateWorkPrivilege(@RequestBody @NotNull @Valid WorkPrivilege workPrivilege){
        workPrivilegeService.updateWorkPrivilege(workPrivilege);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkPrivilege",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkPrivilege",desc = "根据优先级ID删除优先级")
    @ApiParam(name = "id",desc = "优先级ID",required = true)
    public Result<Void> deleteWorkPrivilege(@NotNull String id){
        workPrivilegeService.deleteWorkPrivilege(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkPrivilege",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkPrivilege",desc = "根据优先级ID查找优先级")
    @ApiParam(name = "id",desc = "优先级ID",required = true)
    public Result<WorkPrivilege> findWorkPrivilege(@NotNull String id){
        WorkPrivilege workPrivilege = workPrivilegeService.findWorkPrivilege(id);

        return Result.ok(workPrivilege);
    }

    @RequestMapping(path="/findAllWorkPrivilege",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWorkPrivilege",desc = "查找所有优先级")
    public Result<List<WorkPrivilege>> findAllWorkPrivilege(){
        List<WorkPrivilege> workPrivilegeList = workPrivilegeService.findAllWorkPrivilege();

        return Result.ok(workPrivilegeList);
    }


    @RequestMapping(path = "/findWorkPrivilegeList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkPrivilegeList",desc = "根据查询对象查找优先级列表")
    @ApiParam(name = "workPrivilegeQuery",desc = "查询对象",required = true)
    public Result<List<WorkPrivilege>> findWorkPrivilegeList(@RequestBody @Valid @NotNull WorkPrivilegeQuery workPrivilegeQuery){
        List<WorkPrivilege> workPrivilegeList = workPrivilegeService.findWorkPrivilegeList(workPrivilegeQuery);

        return Result.ok(workPrivilegeList);
    }


    @RequestMapping(path = "/findWorkPrivilegePage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkPrivilegePage",desc = "根据查询对象按分页查询优先级列表")
    @ApiParam(name = "workPrivilegeQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkPrivilege>> findWorkPrivilegePage(@RequestBody @Valid @NotNull WorkPrivilegeQuery workPrivilegeQuery){
        Pagination<WorkPrivilege> pagination = workPrivilegeService.findWorkPrivilegePage(workPrivilegeQuery);

        return Result.ok(pagination);
    }



}
