package io.tiklab.kanass.home.statistic.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.kanass.home.statistic.model.SprintBurnDowmChart;
import io.tiklab.kanass.home.statistic.model.SprintBurnDowmChartQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 迭代下事项数据动态记录服务
 */
public interface SprintBurnDowmChartService {

    /**
    * 创建迭代下事项数据动态记录
    * @param sprintBurnDowmChart
    * @return
    */
    String createSprintBurnDowmChart(@NotNull @Valid SprintBurnDowmChart sprintBurnDowmChart);

    /**
    * 更新迭代下事项数据动态记录
    * @param sprintBurnDowmChart
    */
    void updateSprintBurnDowmChart(@NotNull @Valid SprintBurnDowmChart sprintBurnDowmChart);

    /**
    * 删除迭代下事项数据动态记录
    * @param id
    */
    void deleteSprintBurnDowmChart(@NotNull String id);

    SprintBurnDowmChart findOne(@NotNull String id);

    List<SprintBurnDowmChart> findList(List<String> idList);

    /**
    * 根据id查找迭代下事项数据动态记录
    * @param id
    * @return
    */
    SprintBurnDowmChart findSprintBurnDowmChart(@NotNull String id);

    /**
    * 查找所有迭代下事项数据动态记录列表
    * @return
    */
    List<SprintBurnDowmChart> findAllSprintBurnDowmChart();

    /**
    * 根据迭代数据统计搜索条件查询列表
    * @param sprintBurnDowmChartQuery
    * @return
    */
    List<SprintBurnDowmChart> findSprintBurnDowmChartList(SprintBurnDowmChartQuery sprintBurnDowmChartQuery);

    /**
    * 根据搜索条件按分页查询迭代的事项数据动态记录列表
    * @param sprintBurnDowmChartQuery
    * @return
    */
    Pagination<SprintBurnDowmChart> findSprintBurnDowmChartPage(SprintBurnDowmChartQuery sprintBurnDowmChartQuery);

}