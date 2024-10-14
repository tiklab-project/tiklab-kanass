package io.tiklab.kanass.home.statistic.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.home.statistic.model.StageBurnDowmChart;
import io.tiklab.kanass.home.statistic.model.StageBurnDowmChartQuery;
import io.tiklab.kanass.home.statistic.service.StageBurnDowmChartService;
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
 * 迭代下事项数据动态记录控制器
 */
@RestController
@RequestMapping("/stageBurnDowmChart")
@Api(name = "StageBurnDowmChartController",desc = "StageBurnDowmChartController")
public class StageBurnDowmChartController {

    private static Logger logger = LoggerFactory.getLogger(StageBurnDowmChartController.class);

    @Autowired
    private StageBurnDowmChartService stageBurnDowmChartService;

    @RequestMapping(path="/createStageBurnDowmChart",method = RequestMethod.POST)
    @ApiMethod(name = "createStageBurnDowmChart",desc = "创建迭代下事项数据动态记录")
    @ApiParam(name = "stageBurnDowmChart",desc = "迭代事项数据动态统计模型",required = true)
    public Result<String> createStageBurnDowmChart(@RequestBody @NotNull @Valid StageBurnDowmChart stageBurnDowmChart){
        String id = stageBurnDowmChartService.createStageBurnDowmChart(stageBurnDowmChart);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateStageBurnDowmChart",method = RequestMethod.POST)
    @ApiMethod(name = "updateStageBurnDowmChart",desc = "更新迭代下事项数据动态记录")
    @ApiParam(name = "stageBurnDowmChart",desc = "迭代事项数据动态统计模型",required = true)
    public Result<Void> updateStageBurnDowmChart(@RequestBody @NotNull @Valid StageBurnDowmChart stageBurnDowmChart){
        stageBurnDowmChartService.updateStageBurnDowmChart(stageBurnDowmChart);

        return Result.ok();
    }

    @RequestMapping(path="/deleteStageBurnDowmChart",method = RequestMethod.POST)
    @ApiMethod(name = "deleteStageBurnDowmChart",desc = "删除迭代下事项数据动态记录")
    @ApiParam(name = "id",desc = "记录id",required = true)
    public Result<Void> deleteStageBurnDowmChart(@NotNull String id){
        stageBurnDowmChartService.deleteStageBurnDowmChart(id);

        return Result.ok();
    }

    @RequestMapping(path="/findStageBurnDowmChart",method = RequestMethod.POST)
    @ApiMethod(name = "findStageBurnDowmChart",desc = "根据id查找迭代下事项数据动态记录")
    @ApiParam(name = "id",desc = "记录id",required = true)
    public Result<StageBurnDowmChart> findStageBurnDowmChart(@NotNull String id){
        StageBurnDowmChart stageBurnDowmChart = stageBurnDowmChartService.findStageBurnDowmChart(id);

        return Result.ok(stageBurnDowmChart);
    }

    @RequestMapping(path="/findAllStageBurnDowmChart",method = RequestMethod.POST)
    @ApiMethod(name = "findAllStageBurnDowmChart",desc = "查找所有迭代下事项数据动态记录列表")
    public Result<List<StageBurnDowmChart>> findAllStageBurnDowmChart(){
        List<StageBurnDowmChart> stageBurnDowmChartList = stageBurnDowmChartService.findAllStageBurnDowmChart();

        return Result.ok(stageBurnDowmChartList);
    }

    @RequestMapping(path = "/findStageBurnDowmChartList",method = RequestMethod.POST)
    @ApiMethod(name = "findStageBurnDowmChartList",desc = "根据迭代数据统计搜索条件查询列表")
    @ApiParam(name = "stageBurnDowmChartQuery",desc = "迭代数据统计搜索模型",required = true)
    public Result<List<StageBurnDowmChart>> findStageBurnDowmChartList(@RequestBody @Valid @NotNull StageBurnDowmChartQuery stageBurnDowmChartQuery){
        List<StageBurnDowmChart> stageBurnDowmChartList = stageBurnDowmChartService.findStageBurnDowmChartList(stageBurnDowmChartQuery);

        return Result.ok(stageBurnDowmChartList);
    }

    @RequestMapping(path = "/findStageBurnDowmChartPage",method = RequestMethod.POST)
    @ApiMethod(name = "findStageBurnDowmChartPage",desc = "根据搜索条件按分页查询迭代的事项数据动态记录列表")
    @ApiParam(name = "stageBurnDowmChartQuery",desc = "迭代数据统计搜索模型",required = true)
    public Result<Pagination<StageBurnDowmChart>> findStageBurnDowmChartPage(@RequestBody @Valid @NotNull StageBurnDowmChartQuery stageBurnDowmChartQuery){
        Pagination<StageBurnDowmChart> pagination = stageBurnDowmChartService.findStageBurnDowmChartPage(stageBurnDowmChartQuery);

        return Result.ok(pagination);
    }

}
