package io.tiklab.kanass.project.appraised.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.appraised.model.AppraisedWorkItem;
import io.tiklab.kanass.project.appraised.model.AppraisedWorkItemQuery;
import io.tiklab.kanass.project.appraised.service.AppraisedWorkItemService;
import io.tiklab.kanass.workitem.model.WorkItem;
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
 * 项目评审控制器
 */
@RestController
@RequestMapping("/appraisedWorkItem")
//@Api(name = "AppraisedWorkItemController",desc = "项目评审关联表控制器")
public class AppraisedWorkItemController {
    private static Logger logger = LoggerFactory.getLogger(AppraisedWorkItemController.class);

    @Autowired
    private AppraisedWorkItemService appraisedWorkItemService;

    @RequestMapping(path="/createAppraisedWorkItem",method = RequestMethod.POST)
    //@ApiMethod(name = "createAppraisedWorkItem",desc = "创建项目评审")
    //@ApiParam(name = "appraisedWorkItem",desc = "项目评审模型",required = true)
    public Result<String> createAppraisedWorkItem(@RequestBody @NotNull @Valid AppraisedWorkItem appraisedWorkItem){
        String id = appraisedWorkItemService.createAppraisedWorkItem(appraisedWorkItem);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAppraisedWorkItem",method = RequestMethod.POST)
    //@ApiMethod(name = "updateAppraisedWorkItem",desc = "更新项目评审")
    //@ApiParam(name = "appraisedWorkItem",desc = "项目评审模型",required = true)
    public Result<Void> updateAppraisedWorkItem(@RequestBody @NotNull @Valid AppraisedWorkItem appraisedWorkItem){
        appraisedWorkItemService.updateAppraisedWorkItem(appraisedWorkItem);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAppraisedWorkItem",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteAppraisedWorkItem",desc = "删除项目评审")
    //@ApiParam(name = "id",desc = "评审id",required = true)
    public Result<Void> deleteAppraisedWorkItem(@NotNull String id){
        appraisedWorkItemService.deleteAppraisedWorkItem(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAppraisedWorkItem",method = RequestMethod.POST)
    //@ApiMethod(name = "findAppraisedWorkItem",desc = "根据id查找项目评审")
    //@ApiParam(name = "id",desc = "评审id",required = true)
    public Result<AppraisedWorkItem> findAppraisedWorkItem(@NotNull String id){
        AppraisedWorkItem appraisedWorkItem = appraisedWorkItemService.findAppraisedWorkItem(id);

        return Result.ok(appraisedWorkItem);
    }

    @RequestMapping(path="/findAllAppraisedWorkItem",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllAppraisedWorkItem",desc = "查找所有项目评审")
    public Result<List<AppraisedWorkItem>> findAllAppraisedWorkItem(){
        List<AppraisedWorkItem> appraisedWorkItemList = appraisedWorkItemService.findAll();

        return Result.ok(appraisedWorkItemList);
    }


    @RequestMapping(path = "/findAppraisedWorkItemList",method = RequestMethod.POST)
    //@ApiMethod(name = "findAppraisedWorkItemList",desc = "根据条件查找项目类型列表")
    //@ApiParam(name = "appraisedWorkItemQuery",desc = "项目评审搜索模型",required = true)
    public Result<List<AppraisedWorkItem>> findAppraisedWorkItemList(@RequestBody @Valid @NotNull AppraisedWorkItemQuery appraisedWorkItemQuery){
        List<AppraisedWorkItem> appraisedWorkItemList = appraisedWorkItemService.findAppraisedWorkItemList(appraisedWorkItemQuery);

        return Result.ok(appraisedWorkItemList);
    }


    @RequestMapping(path = "/findAppraisedWorkItemPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findAppraisedWorkItemPage",desc = "根据条件按分页查询项目类型列表")
    //@ApiParam(name = "appraisedWorkItemQuery",desc = "项目评审搜索模型",required = true)
    public Result<Pagination<AppraisedWorkItem>> findAppraisedWorkItemPage(@RequestBody @Valid @NotNull AppraisedWorkItemQuery appraisedWorkItemQuery){
        Pagination<AppraisedWorkItem> pagination = appraisedWorkItemService.findAppraisedWorkItemPage(appraisedWorkItemQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findCanConnectWorkItemPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findCanConnectWorkItemPage",desc = "根据条件按分页查询项目类型列表")
    //@ApiParam(name = "appraisedWorkItemQuery",desc = "项目评审搜索模型",required = true)
    public Result<Pagination<WorkItem>> findCanConnectWorkItemPage(@RequestBody @Valid @NotNull AppraisedWorkItemQuery appraisedWorkItemQuery){
        Pagination<WorkItem> pagination = appraisedWorkItemService.findCanConnectWorkItemPage(appraisedWorkItemQuery);

        return Result.ok(pagination);
    }
}
