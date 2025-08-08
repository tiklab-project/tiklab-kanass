package io.tiklab.kanass.test.workItemBind.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.test.workItemBind.model.WorkItemBind;
import io.tiklab.kanass.test.workItemBind.model.WorkItemBindQuery;
import io.tiklab.kanass.test.workItemBind.service.WorkItemBindService;
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
 * 绑定的缺陷 控制器
 */
@RestController
@RequestMapping("/workItemBind")
//@Api(name = "WorkItemBindController",desc = "绑定的缺陷管理")
public class WorkItemBindController {

    private static Logger logger = LoggerFactory.getLogger(WorkItemBindController.class);

    @Autowired
    private WorkItemBindService workItemBindService;

    @RequestMapping(path="/createWorkItemBind",method = RequestMethod.POST)
//    @ApiMethod(name = "createWorkItemBind",desc = "创建绑定的缺陷")
//    @ApiParam(name = "workItemBind",desc = "workItemBind",required = true)
    public Result<String> createWorkItemBind(@RequestBody @NotNull @Valid WorkItemBind workItemBind){
        String id = workItemBindService.createWorkItemBind(workItemBind);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkItemBind",method = RequestMethod.POST)
//    @ApiMethod(name = "updateWorkItemBind",desc = "更新绑定的缺陷")
//    @ApiParam(name = "workItemBind",desc = "workItemBind",required = true)
    public Result<Void> updateWorkItemBind(@RequestBody @NotNull @Valid WorkItemBind workItemBind){
        workItemBindService.updateWorkItemBind(workItemBind);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkItemBind",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteWorkItemBind",desc = "删除绑定的缺陷")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWorkItemBind(@NotNull String id){
        workItemBindService.deleteWorkItemBind(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkItemBind",method = RequestMethod.POST)
//    @ApiMethod(name = "findWorkItemBind",desc = "根据id查找绑定的缺陷")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WorkItemBind> findWorkItemBind(@NotNull String id){
        WorkItemBind workItemBind = workItemBindService.findWorkItemBind(id);

        return Result.ok(workItemBind);
    }

    @RequestMapping(path="/findAllWorkItemBind",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllWorkItemBind",desc = "查找所有绑定的缺陷")
    public Result<List<WorkItemBind>> findAllWorkItemBind(){
        List<WorkItemBind> workItemBindList = workItemBindService.findAllWorkItemBind();

        return Result.ok(workItemBindList);
    }

    @RequestMapping(path = "/findWorkItemBindList",method = RequestMethod.POST)
//    @ApiMethod(name = "findWorkItemBindList",desc = "根据查询参数查询绑定的缺陷列表")
//    @ApiParam(name = "workItemBindQuery",desc = "workItemBindQuery",required = true)
    public Result<List<WorkItemBind>> findWorkItemBindList(@RequestBody @Valid @NotNull WorkItemBindQuery workItemBindQuery){
        List<WorkItemBind> workItemBindList = workItemBindService.findWorkItemBindList(workItemBindQuery);

        return Result.ok(workItemBindList);
    }

    @RequestMapping(path = "/findWorkItemBindPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findWorkItemBindPage",desc = "根据查询参数按分页查询绑定的缺陷")
//    @ApiParam(name = "workItemBindQuery",desc = "workItemBindQuery",required = true)
    public Result<Pagination<WorkItemBind>> findWorkItemBindPage(@RequestBody @Valid @NotNull WorkItemBindQuery workItemBindQuery){
        Pagination<WorkItemBind> pagination = workItemBindService.findWorkItemBindPage(workItemBindQuery);

        return Result.ok(pagination);
    }



}
