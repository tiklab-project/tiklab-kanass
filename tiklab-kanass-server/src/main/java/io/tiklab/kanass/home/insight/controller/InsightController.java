package io.tiklab.kanass.home.insight.controller;

import io.tiklab.kanass.home.insight.model.Insight;
import io.tiklab.kanass.home.insight.model.InsightQuery;
import io.tiklab.kanass.home.insight.service.InsightService;
import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 仪表盘控制器
 */
@RestController
@RequestMapping("/insight")
@Api(name = "InsightController",desc = "InsightController")
public class InsightController {

    private static Logger logger = LoggerFactory.getLogger(InsightController.class);

    @Autowired
    private InsightService insightService;

    @RequestMapping(path="/createInsight",method = RequestMethod.POST)
    @ApiMethod(name = "createInsight",desc = "创建仪表盘")
    @ApiParam(name = "insight",desc = "仪表盘模型",required = true)
    public Result<String> createInsight(@RequestBody @NotNull @Valid Insight insight){
        String id = insightService.createInsight(insight);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateInsight",method = RequestMethod.POST)
    @ApiMethod(name = "updateInsight",desc = "更新仪表盘")
    @ApiParam(name = "insight",desc = "仪表盘模型",required = true)
    public Result<Void> updateInsight(@RequestBody @NotNull @Valid Insight insight){
        insightService.updateInsight(insight);

        return Result.ok();
    }

    @RequestMapping(path="/deleteInsight",method = RequestMethod.POST)
    @ApiMethod(name = "deleteInsight",desc = "删除仪表盘")
    @ApiParam(name = "id",desc = "仪表盘id",required = true)
    public Result<Void> deleteInsight(@NotNull String id){
        insightService.deleteInsight(id);

        return Result.ok();
    }

    @RequestMapping(path="/findInsight",method = RequestMethod.POST)
    @ApiMethod(name = "findInsight",desc = "根据id查找仪表盘")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Insight> findInsight(@NotNull String id){
        Insight insight = insightService.findInsight(id);

        return Result.ok(insight);
    }

    @RequestMapping(path="/findAllInsight",method = RequestMethod.POST)
    @ApiMethod(name = "findAllInsight",desc = "查找所有仪表盘")
    public Result<List<Insight>> findAllInsight(){
        List<Insight> insightList = insightService.findAllInsight();

        return Result.ok(insightList);
    }

    @RequestMapping(path = "/findInsightList",method = RequestMethod.POST)
    @ApiMethod(name = "findInsightList",desc = "根据条件查找仪表盘")
    @ApiParam(name = "insightQuery",desc = "仪表盘搜索条件模型",required = true)
    public Result<List<Insight>>    findInsightList(@RequestBody @Valid @NotNull InsightQuery insightQuery){
        List<Insight> insightList = insightService.findInsightList(insightQuery);

        return Result.ok(insightList);
    }

    @RequestMapping(path = "/findInsightPage",method = RequestMethod.POST)
    @ApiMethod(name = "findInsightPage",desc = "根据条件按照分页查找仪表盘")
    @ApiParam(name = "insightQuery",desc = "仪表盘搜索条件模型",required = true)
    public Result<Pagination<Insight>> findInsightPage(@RequestBody @Valid @NotNull InsightQuery insightQuery){
        Pagination<Insight> pagination = insightService.findInsightPage(insightQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findRecentInsightList",method = RequestMethod.POST)
    @ApiMethod(name = "findRecentInsightList",desc = "根据条件按照分页查找仪表盘")
    @ApiParam(name = "insightQuery",desc = "仪表盘搜索条件模型",required = true)
    public Result<List<Insight>> findRecentInsightList(@RequestBody @Valid @NotNull InsightQuery insightQuery){
        List<Insight> insightList = insightService.findRecentInsightList(insightQuery);

        return Result.ok(insightList);
    }

    @RequestMapping(path = "/findFocusInsightList",method = RequestMethod.POST)
    @ApiMethod(name = "findFocusInsightList",desc = "根据条件按照分页查找仪表盘")
    @ApiParam(name = "insightQuery",desc = "仪表盘搜索条件模型",required = true)
    public Result<List<Insight>> findFocusInsightList(@RequestBody @Valid @NotNull InsightQuery insightQuery){
        List<Insight> insightList = insightService.findFocusInsightList(insightQuery);

        return Result.ok(insightList);
    }
}
