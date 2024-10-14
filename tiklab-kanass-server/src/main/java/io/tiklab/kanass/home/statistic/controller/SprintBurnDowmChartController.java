package io.tiklab.kanass.home.statistic.controller;

import io.tiklab.kanass.home.statistic.model.SprintBurnDowmChart;
import io.tiklab.kanass.home.statistic.model.SprintBurnDowmChartQuery;
import io.tiklab.kanass.home.statistic.service.SprintBurnDowmChartService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
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
 * 迭代下事项数据动态记录控制器
 */
@RestController
@RequestMapping("/sprintBurnDowmChart")
@Api(name = "SprintBurnDowmChartController",desc = "SprintBurnDowmChartController")
public class SprintBurnDowmChartController {

    private static Logger logger = LoggerFactory.getLogger(SprintBurnDowmChartController.class);

    @Autowired
    private SprintBurnDowmChartService sprintBurnDowmChartService;

    @RequestMapping(path="/createSprintBurnDowmChart",method = RequestMethod.POST)
    @ApiMethod(name = "createSprintBurnDowmChart",desc = "创建迭代下事项数据动态记录")
    @ApiParam(name = "sprintBurnDowmChart",desc = "迭代事项数据动态统计模型",required = true)
    public Result<String> createSprintBurnDowmChart(@RequestBody @NotNull @Valid SprintBurnDowmChart sprintBurnDowmChart){
        String id = sprintBurnDowmChartService.createSprintBurnDowmChart(sprintBurnDowmChart);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateSprintBurnDowmChart",method = RequestMethod.POST)
    @ApiMethod(name = "updateSprintBurnDowmChart",desc = "更新迭代下事项数据动态记录")
    @ApiParam(name = "sprintBurnDowmChart",desc = "迭代事项数据动态统计模型",required = true)
    public Result<Void> updateSprintBurnDowmChart(@RequestBody @NotNull @Valid SprintBurnDowmChart sprintBurnDowmChart){
        sprintBurnDowmChartService.updateSprintBurnDowmChart(sprintBurnDowmChart);

        return Result.ok();
    }

    @RequestMapping(path="/deleteSprintBurnDowmChart",method = RequestMethod.POST)
    @ApiMethod(name = "deleteSprintBurnDowmChart",desc = "删除迭代下事项数据动态记录")
    @ApiParam(name = "id",desc = "记录id",required = true)
    public Result<Void> deleteSprintBurnDowmChart(@NotNull String id){
        sprintBurnDowmChartService.deleteSprintBurnDowmChart(id);

        return Result.ok();
    }

    @RequestMapping(path="/findSprintBurnDowmChart",method = RequestMethod.POST)
    @ApiMethod(name = "findSprintBurnDowmChart",desc = "根据id查找迭代下事项数据动态记录")
    @ApiParam(name = "id",desc = "记录id",required = true)
    public Result<SprintBurnDowmChart> findSprintBurnDowmChart(@NotNull String id){
        SprintBurnDowmChart sprintBurnDowmChart = sprintBurnDowmChartService.findSprintBurnDowmChart(id);

        return Result.ok(sprintBurnDowmChart);
    }

    @RequestMapping(path="/findAllSprintBurnDowmChart",method = RequestMethod.POST)
    @ApiMethod(name = "findAllSprintBurnDowmChart",desc = "查找所有迭代下事项数据动态记录列表")
    public Result<List<SprintBurnDowmChart>> findAllSprintBurnDowmChart(){
        List<SprintBurnDowmChart> sprintBurnDowmChartList = sprintBurnDowmChartService.findAllSprintBurnDowmChart();

        return Result.ok(sprintBurnDowmChartList);
    }

    @RequestMapping(path = "/findSprintBurnDowmChartList",method = RequestMethod.POST)
    @ApiMethod(name = "findSprintBurnDowmChartList",desc = "根据迭代数据统计搜索条件查询列表")
    @ApiParam(name = "sprintBurnDowmChartQuery",desc = "迭代数据统计搜索模型",required = true)
    public Result<List<SprintBurnDowmChart>> findSprintBurnDowmChartList(@RequestBody @Valid @NotNull SprintBurnDowmChartQuery sprintBurnDowmChartQuery){
        List<SprintBurnDowmChart> sprintBurnDowmChartList = sprintBurnDowmChartService.findSprintBurnDowmChartList(sprintBurnDowmChartQuery);

        return Result.ok(sprintBurnDowmChartList);
    }

    @RequestMapping(path = "/findSprintBurnDowmChartPage",method = RequestMethod.POST)
    @ApiMethod(name = "findSprintBurnDowmChartPage",desc = "根据搜索条件按分页查询迭代的事项数据动态记录列表")
    @ApiParam(name = "sprintBurnDowmChartQuery",desc = "迭代数据统计搜索模型",required = true)
    public Result<Pagination<SprintBurnDowmChart>> findSprintBurnDowmChartPage(@RequestBody @Valid @NotNull SprintBurnDowmChartQuery sprintBurnDowmChartQuery){
        Pagination<SprintBurnDowmChart> pagination = sprintBurnDowmChartService.findSprintBurnDowmChartPage(sprintBurnDowmChartQuery);

        return Result.ok(pagination);
    }

}
