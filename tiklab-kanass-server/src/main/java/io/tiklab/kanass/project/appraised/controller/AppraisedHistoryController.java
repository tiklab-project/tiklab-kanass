package io.tiklab.kanass.project.appraised.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.appraised.model.AppraisedHistory;
import io.tiklab.kanass.project.appraised.model.AppraisedHistoryQuery;
import io.tiklab.kanass.project.appraised.service.AppraisedHistoryService;
import io.tiklab.kanass.workitem.model.WorkItem;
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
 * 评审记录控制器
 */
@RestController
@RequestMapping("/appraisedHistory")
public class AppraisedHistoryController {
    private static Logger logger = LoggerFactory.getLogger(AppraisedHistoryController.class);

    @Autowired
    private AppraisedHistoryService appraisedHistoryService;

    @RequestMapping(path="/createAppraisedHistory",method = RequestMethod.POST)
    //@ApiMethod(name = "createAppraisedHistory",desc = "创建评审记录")
    //@ApiParam(name = "appraisedHistory",desc = "评审记录模型",required = true)
    public Result<String> createAppraisedHistory(@RequestBody @NotNull @Valid AppraisedHistory appraisedHistory){
        String id = appraisedHistoryService.createAppraisedHistory(appraisedHistory);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateAppraisedHistory",method = RequestMethod.POST)
    //@ApiMethod(name = "updateAppraisedHistory",desc = "更新评审记录")
    //@ApiParam(name = "appraisedHistory",desc = "评审记录模型",required = true)
    public Result<Void> updateAppraisedHistory(@RequestBody @NotNull @Valid AppraisedHistory appraisedHistory){
        appraisedHistoryService.updateAppraisedHistory(appraisedHistory);

        return Result.ok();
    }

    @RequestMapping(path="/deleteAppraisedHistory",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteAppraisedHistory",desc = "删除评审记录")
    //@ApiParam(name = "id",desc = "评审id",required = true)
    public Result<Void> deleteAppraisedHistory(@NotNull String id){
        appraisedHistoryService.deleteAppraisedHistory(id);

        return Result.ok();
    }

    @RequestMapping(path="/findAppraisedHistory",method = RequestMethod.POST)
    //@ApiMethod(name = "findAppraisedHistory",desc = "根据id查找评审记录")
    //@ApiParam(name = "id",desc = "评审id",required = true)
    public Result<AppraisedHistory> findAppraisedHistory(@NotNull String id){
        AppraisedHistory appraisedHistory = appraisedHistoryService.findAppraisedHistory(id);

        return Result.ok(appraisedHistory);
    }

    @RequestMapping(path="/findAllAppraisedHistory",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllAppraisedHistory",desc = "查找所有评审记录")
    public Result<List<AppraisedHistory>> findAllAppraisedHistory(){
        List<AppraisedHistory> appraisedHistoryList = appraisedHistoryService.findAll();

        return Result.ok(appraisedHistoryList);
    }


    @RequestMapping(path = "/findAppraisedHistoryList",method = RequestMethod.POST)
    //@ApiMethod(name = "findAppraisedHistoryList",desc = "根据条件查找项目类型列表")
    //@ApiParam(name = "appraisedHistoryQuery",desc = "评审记录搜索模型",required = true)
    public Result<List<AppraisedHistory>> findAppraisedHistoryList(@RequestBody @Valid @NotNull AppraisedHistoryQuery appraisedHistoryQuery){
        List<AppraisedHistory> appraisedHistoryList = appraisedHistoryService.findAppraisedHistoryList(appraisedHistoryQuery);

        return Result.ok(appraisedHistoryList);
    }


    @RequestMapping(path = "/findAppraisedHistoryPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findAppraisedHistoryPage",desc = "根据条件按分页查询项目类型列表")
    //@ApiParam(name = "appraisedHistoryQuery",desc = "评审记录搜索模型",required = true)
    public Result<Pagination<AppraisedHistory>> findAppraisedHistoryPage(@RequestBody @Valid @NotNull AppraisedHistoryQuery appraisedHistoryQuery){
        Pagination<AppraisedHistory> pagination = appraisedHistoryService.findAppraisedHistoryPage(appraisedHistoryQuery);

        return Result.ok(pagination);
    }
}
