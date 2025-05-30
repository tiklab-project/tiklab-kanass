package io.tiklab.kanass.workitem.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jdbc.support.sort.processor.SortProcessor;
import io.tiklab.kanass.workitem.model.WorkSprint;
import io.tiklab.kanass.workitem.model.WorkSprintQuery;
import io.tiklab.kanass.workitem.service.WorkSprintService;
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
 * 事项状态控制器
 */
@RestController
@RequestMapping("/workSprint")
//@Api(name = "WorkSprintController",desc = "事项状态管理")
public class WorkSprintController {

    private static Logger logger = LoggerFactory.getLogger(WorkSprintController.class);

    @Autowired
    private WorkSprintService workSprintService;

    @Autowired
    SortProcessor sortService;

    @RequestMapping(path="/createWorkSprint",method = RequestMethod.POST)
    //@ApiMethod(name = "createWorkSprint",desc = "创建事项状态")
    //@ApiParam(name = "workSprint",desc = "事项状态DTO",required = true)
    public Result<String> createWorkSprint(@RequestBody @NotNull @Valid WorkSprint workSprint){
        String id = workSprintService.createWorkSprint(workSprint);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkSprint",method = RequestMethod.POST)
    //@ApiMethod(name = "updateWorkSprint",desc = "更新事项状态")
    //@ApiParam(name = "workSprint",desc = "事项状态DTO",required = true)
    public Result<Void> updateWorkSprint(@RequestBody @NotNull @Valid WorkSprint workSprint){
        workSprintService.updateWorkSprint(workSprint);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkSprint",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteWorkSprint",desc = "根据状态ID删除事项状态")
    //@ApiParam(name = "id",desc = "事项状态ID",required = true)
    public Result<Void> deleteWorkSprint(@NotNull String id){
        workSprintService.deleteWorkSprint(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkSprint",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkSprint",desc = "根据事项状态ID查找事项状态")
    //@ApiParam(name = "id",desc = "事项状态ID",required = true)
    public Result<WorkSprint> findWorkSprint(@NotNull String id){
        WorkSprint workSprint = workSprintService.findWorkSprint(id);

        return Result.ok(workSprint);
    }

    @RequestMapping(path="/findAllWorkSprint",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllWorkSprint",desc = "查找所有事项状态列表")
    public Result<List<WorkSprint>> findAllWorkSprint(){
        List<WorkSprint> workSprintList = workSprintService.findAllWorkSprint();

        return Result.ok(workSprintList);
    }


    @RequestMapping(path = "/findWorkSprintList",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkSprintList",desc = "根据查询对象查找事项状态列表")
    //@ApiParam(name = "workSprintQuery",desc = "查询对象",required = true)
    public Result<List<WorkSprint>> findWorkSprintList(@RequestBody @Valid @NotNull WorkSprintQuery workSprintQuery){
        List<WorkSprint> workSprintList = workSprintService.findWorkSprintList(workSprintQuery);

        return Result.ok(workSprintList);
    }

    @RequestMapping(path = "/findWorkSprintPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkSprintPage",desc = "根据查询对象按分页查找事项状态列表")
    //@ApiParam(name = "workSprintQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkSprint>> findWorkSprintPage(@RequestBody @Valid @NotNull WorkSprintQuery workSprintQuery){
        Pagination<WorkSprint> pagination = workSprintService.findWorkSprintPage(workSprintQuery);

        return Result.ok(pagination);
    }


}
