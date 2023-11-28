package io.tiklab.teamwire.home.statistic.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.teamwire.home.statistic.model.VersionBurnDowmChart;
import io.tiklab.teamwire.home.statistic.model.VersionBurnDowmChartQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 迭代下事项数据动态记录服务
 */
public interface VersionBurnDowmChartService {

    /**
    * 创建迭代下事项数据动态记录
    * @param versionBurnDowmChart
    * @return
    */
    String createVersionBurnDowmChart(@NotNull @Valid VersionBurnDowmChart versionBurnDowmChart);

    /**
    * 更新迭代下事项数据动态记录
    * @param versionBurnDowmChart
    */
    void updateVersionBurnDowmChart(@NotNull @Valid VersionBurnDowmChart versionBurnDowmChart);

    /**
    * 删除迭代下事项数据动态记录
    * @param id
    */
    void deleteVersionBurnDowmChart(@NotNull String id);

    VersionBurnDowmChart findOne(@NotNull String id);

    List<VersionBurnDowmChart> findList(List<String> idList);

    /**
    * 根据id查找迭代下事项数据动态记录
    * @param id
    * @return
    */
    VersionBurnDowmChart findVersionBurnDowmChart(@NotNull String id);

    /**
    * 查找所有迭代下事项数据动态记录列表
    * @return
    */
    List<VersionBurnDowmChart> findAllVersionBurnDowmChart();

    /**
    * 根据迭代数据统计搜索条件查询列表
    * @param versionBurnDowmChartQuery
    * @return
    */
    List<VersionBurnDowmChart> findVersionBurnDowmChartList(VersionBurnDowmChartQuery versionBurnDowmChartQuery);

    /**
    * 根据搜索条件按分页查询迭代的事项数据动态记录列表
    * @param versionBurnDowmChartQuery
    * @return
    */
    Pagination<VersionBurnDowmChart> findVersionBurnDowmChartPage(VersionBurnDowmChartQuery versionBurnDowmChartQuery);

}