package io.tiklab.kanass.project.appraised.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.appraised.model.AppraisedItem;
import io.tiklab.kanass.project.appraised.model.AppraisedItemQuery;
import io.tiklab.kanass.project.appraised.service.AppraisedItemService;
import io.tiklab.kanass.project.wiki.model.ProjectDocument;
import io.tiklab.kanass.test.testcase.test.model.TestCase;
import io.tiklab.kanass.workitem.model.WorkItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 项目评审控制器
 */
//@RestController
//@RequestMapping("/appraisedItem")
//@Api(name = "AppraisedItemController",desc = "项目评审关联表控制器")
public class AppraisedItemController {
    private static Logger logger = LoggerFactory.getLogger(AppraisedItemController.class);

    @Autowired
    private AppraisedItemService appraisedItemService;

    @RequestMapping(path="/createAppraisedItem",method = RequestMethod.POST)
    //@ApiMethod(name = "createAppraisedItem",desc = "创建项目评审")
    //@ApiParam(name = "appraisedItem",desc = "项目评审模型",required = true)
    public Result<String> createAppraisedItem(@RequestBody @NotNull @Valid AppraisedItem appraisedItem){
        String id = appraisedItemService.createAppraisedItem(appraisedItem);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAppraisedItem",method = RequestMethod.POST)
    //@ApiMethod(name = "updateAppraisedItem",desc = "更新项目评审")
    //@ApiParam(name = "appraisedItem",desc = "项目评审模型",required = true)
    public Result<Void> updateAppraisedItem(@RequestBody @NotNull @Valid AppraisedItem appraisedItem){
        appraisedItemService.updateAppraisedItem(appraisedItem);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAppraisedItem",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteAppraisedItem",desc = "删除项目评审")
    //@ApiParam(name = "id",desc = "评审id",required = true)
    public Result<Void> deleteAppraisedItem(@NotNull String id){
        appraisedItemService.deleteAppraisedItem(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAppraisedItem",method = RequestMethod.POST)
    //@ApiMethod(name = "findAppraisedItem",desc = "根据id查找项目评审")
    //@ApiParam(name = "id",desc = "评审id",required = true)
    public Result<AppraisedItem> findAppraisedItem(@NotNull String id){
        AppraisedItem appraisedItem = appraisedItemService.findAppraisedItem(id);

        return Result.ok(appraisedItem);
    }

    @RequestMapping(path="/findAllAppraisedItem",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllAppraisedItem",desc = "查找所有项目评审")
    public Result<List<AppraisedItem>> findAllAppraisedItem(){
        List<AppraisedItem> appraisedItemList = appraisedItemService.findAll();

        return Result.ok(appraisedItemList);
    }


    @RequestMapping(path = "/findAppraisedItemList",method = RequestMethod.POST)
    //@ApiMethod(name = "findAppraisedItemList",desc = "根据条件查找项目类型列表")
    //@ApiParam(name = "appraisedItemQuery",desc = "项目评审搜索模型",required = true)
    public Result<List<AppraisedItem>> findAppraisedItemList(@RequestBody @Valid @NotNull AppraisedItemQuery appraisedItemQuery){
        List<AppraisedItem> appraisedItemList = appraisedItemService.findAppraisedItemList(appraisedItemQuery);

        return Result.ok(appraisedItemList);
    }


    @RequestMapping(path = "/findAppraisedItemPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findAppraisedItemPage",desc = "根据条件按分页查询项目类型列表")
    //@ApiParam(name = "appraisedItemQuery",desc = "项目评审搜索模型",required = true)
    public Result<Pagination<AppraisedItem>> findAppraisedItemPage(@RequestBody @Valid @NotNull AppraisedItemQuery appraisedItemQuery){
        Pagination<AppraisedItem> pagination = appraisedItemService.findAppraisedItemPage(appraisedItemQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findCanConnectWorkItemPage",method = RequestMethod.POST)
    public Result<Pagination<WorkItem>> findCanConnectWorkItemPage(@RequestBody @Valid @NotNull AppraisedItemQuery appraisedItemQuery){
        Pagination<WorkItem> pagination = appraisedItemService.findCanConnectWorkItemPage(appraisedItemQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findCanConnectDocumentPage",method = RequestMethod.POST)
    public Result<Pagination<ProjectDocument>> findCanConnectDocumentPage(@RequestBody @Valid @NotNull AppraisedItemQuery appraisedItemQuery){
        Pagination<ProjectDocument> pagination = appraisedItemService.findCanConnectDocumentPage(appraisedItemQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findCanConnectTestCasePage",method = RequestMethod.POST)
    public Result<Pagination<TestCase>> findCanConnectTestCasePage(@RequestBody @Valid @NotNull AppraisedItemQuery appraisedItemQuery){
        Pagination<TestCase> pagination = appraisedItemService.findCanConnectTestCasePage(appraisedItemQuery);

        return Result.ok(pagination);
    }
}
