package io.thoughtware.kanass.home.insight.controller;

import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import io.thoughtware.kanass.home.insight.model.InsightFocus;
import io.thoughtware.kanass.home.insight.model.InsightFocusQuery;
import io.thoughtware.kanass.home.insight.service.InsightFocusService;
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
 * 收藏的项目控制器
 */
@RestController
@RequestMapping("/insightFocus")
@Api(name = "InsightFocusController",desc = "InsightFocusController")
public class InsightFocusController {

    private static Logger logger = LoggerFactory.getLogger(InsightFocusController.class);

    @Autowired
    private InsightFocusService insightFocusService;

    @RequestMapping(path="/createInsightFocus",method = RequestMethod.POST)
    @ApiMethod(name = "createInsightFocus",desc = "添加收藏项目")
    @ApiParam(name = "insightFocus",desc = "项目收藏模型",required = true)
    public Result<String> createInsightFocus(@RequestBody @NotNull @Valid InsightFocus insightFocus){
        String id = insightFocusService.createInsightFocus(insightFocus);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateInsightFocus",method = RequestMethod.POST)
    @ApiMethod(name = "updateInsightFocus",desc = "更新收藏项目")
    @ApiParam(name = "insightFocus",desc = "项目收藏模型",required = true)
    public Result<Void> updateInsightFocus(@RequestBody @NotNull @Valid InsightFocus insightFocus){
        insightFocusService.updateInsightFocus(insightFocus);

        return Result.ok();
    }

    @RequestMapping(path="/deleteInsightFocusByQuery",method = RequestMethod.POST)
    @ApiMethod(name = "deleteInsightFocusByQuery",desc = "根据收藏者和项目id删除收藏的项目")
    @ApiParam(name = "insightFocusQuery",desc = "项目收藏模型",required = true)
    public Result<Void> deleteInsightFocusByQuery(@RequestBody @NotNull @Valid InsightFocusQuery insightFocusQuery){
        insightFocusService.deleteInsightFocusByQuery(insightFocusQuery);

        return Result.ok();
    }

    @RequestMapping(path="/deleteInsightFocus",method = RequestMethod.POST)
    @ApiMethod(name = "deleteInsightFocus",desc = "根据id 删除收藏的项目")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteInsightFocus(@NotNull String id){
        insightFocusService.deleteInsightFocus(id);

        return Result.ok();
    }

    @RequestMapping(path="/findInsightFocus",method = RequestMethod.POST)
    @ApiMethod(name = "findInsightFocus",desc = "根据id 查找收藏的项目")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<InsightFocus> findInsightFocus(@NotNull String id){
        InsightFocus insightFocus = insightFocusService.findInsightFocus(id);

        return Result.ok(insightFocus);
    }

    @RequestMapping(path="/findAllInsightFocus",method = RequestMethod.POST)
    @ApiMethod(name = "findAllInsightFocus",desc = "查找所有收藏的项目")
    public Result<List<InsightFocus>> findAllInsightFocus(){
        List<InsightFocus> insightFocusList = insightFocusService.findAllInsightFocus();

        return Result.ok(insightFocusList);
    }

    @RequestMapping(path = "/findInsightFocusList",method = RequestMethod.POST)
    @ApiMethod(name = "findInsightFocusList",desc = "根据条件查询项目收藏列表")
    @ApiParam(name = "insightFocusQuery",desc = "项目收藏模型",required = true)
    public Result<List<InsightFocus>> findInsightFocusList(@RequestBody @Valid @NotNull InsightFocusQuery insightFocusQuery){
        List<InsightFocus> insightFocusList = insightFocusService.findInsightFocusList(insightFocusQuery);

        return Result.ok(insightFocusList);
    }

    @RequestMapping(path = "/findInsightFocusPage",method = RequestMethod.POST)
    @ApiMethod(name = "findInsightFocusPage",desc = "根据条件按分页查询项目的收藏列表")
    @ApiParam(name = "insightFocusQuery",desc = "项目收藏模型",required = true)
    public Result<Pagination<InsightFocus>> findInsightFocusPage(@RequestBody @Valid @NotNull InsightFocusQuery insightFocusQuery){
        Pagination<InsightFocus> pagination = insightFocusService.findInsightFocusPage(insightFocusQuery);

        return Result.ok(pagination);
    }

}
