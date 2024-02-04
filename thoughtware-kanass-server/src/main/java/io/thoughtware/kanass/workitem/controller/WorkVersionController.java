package io.thoughtware.kanass.workitem.controller;

import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jdbc.support.sort.processor.SortProcessor;
import io.thoughtware.kanass.workitem.model.WorkVersion;
import io.thoughtware.kanass.workitem.model.WorkVersionQuery;
import io.thoughtware.kanass.workitem.service.WorkVersionService;
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
 * 事项状态控制器
 */
@RestController
@RequestMapping("/workVersion")
@Api(name = "WorkVersionController",desc = "事项状态管理")
public class WorkVersionController {

    private static Logger logger = LoggerFactory.getLogger(WorkVersionController.class);

    @Autowired
    private WorkVersionService workVersionService;

    @Autowired
    SortProcessor sortService;

    @RequestMapping(path="/createWorkVersion",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkVersion",desc = "创建事项状态")
    @ApiParam(name = "workVersion",desc = "事项状态DTO",required = true)
    public Result<String> createWorkVersion(@RequestBody @NotNull @Valid WorkVersion workVersion){
        String id = workVersionService.createWorkVersion(workVersion);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkVersion",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkVersion",desc = "更新事项状态")
    @ApiParam(name = "workVersion",desc = "事项状态DTO",required = true)
    public Result<Void> updateWorkVersion(@RequestBody @NotNull @Valid WorkVersion workVersion){
        workVersionService.updateWorkVersion(workVersion);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkVersion",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkVersion",desc = "根据状态ID删除事项状态")
    @ApiParam(name = "id",desc = "事项状态ID",required = true)
    public Result<Void> deleteWorkVersion(@NotNull String id){
        workVersionService.deleteWorkVersion(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkVersion",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkVersion",desc = "根据事项状态ID查找事项状态")
    @ApiParam(name = "id",desc = "事项状态ID",required = true)
    public Result<WorkVersion> findWorkVersion(@NotNull String id){
        WorkVersion workVersion = workVersionService.findWorkVersion(id);

        return Result.ok(workVersion);
    }

    @RequestMapping(path="/findAllWorkVersion",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWorkVersion",desc = "查找所有事项状态列表")
    public Result<List<WorkVersion>> findAllWorkVersion(){
        List<WorkVersion> workVersionList = workVersionService.findAllWorkVersion();

        return Result.ok(workVersionList);
    }


    @RequestMapping(path = "/findWorkVersionList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkVersionList",desc = "根据查询对象查找事项状态列表")
    @ApiParam(name = "workVersionQuery",desc = "查询对象",required = true)
    public Result<List<WorkVersion>> findWorkVersionList(@RequestBody @Valid @NotNull WorkVersionQuery workVersionQuery){
        List<WorkVersion> workVersionList = workVersionService.findWorkVersionList(workVersionQuery);

        return Result.ok(workVersionList);
    }

    @RequestMapping(path = "/findWorkVersionPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkVersionPage",desc = "根据查询对象按分页查找事项状态列表")
    @ApiParam(name = "workVersionQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkVersion>> findWorkVersionPage(@RequestBody @Valid @NotNull WorkVersionQuery workVersionQuery){
        Pagination<WorkVersion> pagination = workVersionService.findWorkVersionPage(workVersionQuery);

        return Result.ok(pagination);
    }


}
