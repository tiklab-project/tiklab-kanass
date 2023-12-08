package io.thoughtware.kanass.home.statistic.service;

import io.thoughtware.core.page.Pagination;

import io.thoughtware.kanass.home.statistic.model.ProjectBurnDowmChart;
import io.thoughtware.kanass.home.statistic.model.ProjectBurnDowmChartQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 项目下数据动态记录服务接口
*/
public interface ProjectBurnDowmChartService {

    /**
    * 创建项目数据
    * @param projectBurnDowmChart
    * @return
    */
    String createProjectBurnDowmChart(@NotNull @Valid ProjectBurnDowmChart projectBurnDowmChart);

    /**
    * 更新项目动态数据
    * @param projectBurnDowmChart
    */
    void updateProjectBurnDowmChart(@NotNull @Valid ProjectBurnDowmChart projectBurnDowmChart);

    /**
    * 删除项目动态记录
    * @param id
    */
    void deleteProjectBurnDowmChart(@NotNull String id);

    ProjectBurnDowmChart findOne(@NotNull String id);

    List<ProjectBurnDowmChart> findList(List<String> idList);

    /**
    * 按照id查找项目动态记录
    * @param id
    * @return
    */
    ProjectBurnDowmChart findProjectBurnDowmChart(@NotNull String id);

    /**
    * 查找所有记录
    * @return
    */
    List<ProjectBurnDowmChart> findAllProjectBurnDowmChart();

    /**
    * 按条件查询记录列表
    * @param projectBurnDowmChartQuery
    * @return
    */
    List<ProjectBurnDowmChart> findProjectBurnDowmChartList(ProjectBurnDowmChartQuery projectBurnDowmChartQuery);

    /**
    * 按条件分页查询记录列表
    * @param projectBurnDowmChartQuery
    * @return
    */
    Pagination<ProjectBurnDowmChart> findProjectBurnDowmChartPage(ProjectBurnDowmChartQuery projectBurnDowmChartQuery);

}