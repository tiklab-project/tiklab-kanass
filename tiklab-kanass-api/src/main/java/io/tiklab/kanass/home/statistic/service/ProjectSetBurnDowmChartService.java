package io.tiklab.kanass.home.statistic.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.home.statistic.model.ProjectSetBurnDowmChart;
import io.tiklab.kanass.home.statistic.model.ProjectSetBurnDowmChartQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 项目集下数据动态记录服务接口
*/
public interface ProjectSetBurnDowmChartService {

    /**
    * 创建项目集数据
    * @param projectSetBurnDowmChart
    * @return
    */
    String createProjectSetBurnDowmChart(@NotNull @Valid ProjectSetBurnDowmChart projectSetBurnDowmChart);

    /**
    * 更新项目集动态数据
    * @param projectSetBurnDowmChart
    */
    void updateProjectSetBurnDowmChart(@NotNull @Valid ProjectSetBurnDowmChart projectSetBurnDowmChart);

    /**
    * 删除项目集动态记录
    * @param id
    */
    void deleteProjectSetBurnDowmChart(@NotNull String id);

    ProjectSetBurnDowmChart findOne(@NotNull String id);

    List<ProjectSetBurnDowmChart> findList(List<String> idList);

    /**
    * 按照id查找项目集动态记录
    * @param id
    * @return
    */
    ProjectSetBurnDowmChart findProjectSetBurnDowmChart(@NotNull String id);

    /**
    * 查找所有记录
    * @return
    */
    List<ProjectSetBurnDowmChart> findAllProjectSetBurnDowmChart();

    /**
    * 按条件查询记录列表
    * @param projectSetBurnDowmChartQuery
    * @return
    */
    List<ProjectSetBurnDowmChart> findProjectSetBurnDowmChartList(ProjectSetBurnDowmChartQuery projectSetBurnDowmChartQuery);

    /**
    * 按条件分页查询记录列表
    * @param projectSetBurnDowmChartQuery
    * @return
    */
    Pagination<ProjectSetBurnDowmChart> findProjectSetBurnDowmChartPage(ProjectSetBurnDowmChartQuery projectSetBurnDowmChartQuery);

}