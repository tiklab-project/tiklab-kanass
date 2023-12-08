package io.thoughtware.kanass.home.statistic.controller;

import io.thoughtware.kanass.home.statistic.model.StatisticWorkItemQuery;
import io.thoughtware.kanass.home.statistic.model.WorkItemBuildAndEndStatistic;
import io.thoughtware.kanass.home.statistic.model.WorkItemStatistic;
import io.thoughtware.kanass.home.statistic.service.StatisticWorkItemService;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 按照传入字段统计单位时间内事项数量的搜索控制器
 */
@RestController
@RequestMapping("/statistic")
@Api(name = "StatisticWorkItemController",desc = "事项统计")
public class StatisticWorkItemController {
    @Autowired
    private StatisticWorkItemService statisticWorkItemService;

    @RequestMapping(path="/statisticWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "statisticWorkItem",desc = "按事项字段统计事项分布")
    @ApiParam(name = "statisticWorkItemQuery",desc = "统计参数 (项目集ID, 项目ID, 迭代ID, 统计时长, 统计时间单位, 统计字段, 统计类型)",required = true)
    public Result<List<WorkItemStatistic>> statisticWorkItem(@RequestBody StatisticWorkItemQuery statisticWorkItemQuery){
        List<WorkItemStatistic> list = statisticWorkItemService.statisticWorkItem(statisticWorkItemQuery);

        return Result.ok(list);
    }

    @RequestMapping(path="/statisticBuildAndEndWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "statisticBuildAndEndWorkItem",desc = "统计事项的创建与解决")
    @ApiParam(name = "statisticWorkItemQuery",desc = "统计参数 (项目集ID, 项目ID, 迭代ID, 统计时长, 统计时间单位, 统计字段, 统计类型)",required = true)
    public Result<List<WorkItemBuildAndEndStatistic>> statisticBuildAndEndWorkItem(@RequestBody StatisticWorkItemQuery statisticWorkItemQuery){
        List<WorkItemBuildAndEndStatistic> list = statisticWorkItemService.statisticBuildAndEndWorkItem(statisticWorkItemQuery);

        return Result.ok(list);
    }
}
