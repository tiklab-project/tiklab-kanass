package io.tiklab.kanass.home.statistic.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.home.statistic.model.StageBurnDowmChart;
import io.tiklab.kanass.home.statistic.model.StageBurnDowmChartQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 迭代下事项数据动态记录服务
 */
public interface StageBurnDowmChartService {

    /**
    * 创建迭代下事项数据动态记录
    * @param stageBurnDowmChart
    * @return
    */
    String createStageBurnDowmChart(@NotNull @Valid StageBurnDowmChart stageBurnDowmChart);

    /**
    * 更新迭代下事项数据动态记录
    * @param stageBurnDowmChart
    */
    void updateStageBurnDowmChart(@NotNull @Valid StageBurnDowmChart stageBurnDowmChart);

    /**
    * 删除迭代下事项数据动态记录
    * @param id
    */
    void deleteStageBurnDowmChart(@NotNull String id);

    StageBurnDowmChart findOne(@NotNull String id);

    List<StageBurnDowmChart> findList(List<String> idList);

    /**
    * 根据id查找迭代下事项数据动态记录
    * @param id
    * @return
    */
    StageBurnDowmChart findStageBurnDowmChart(@NotNull String id);

    /**
    * 查找所有迭代下事项数据动态记录列表
    * @return
    */
    List<StageBurnDowmChart> findAllStageBurnDowmChart();

    /**
    * 根据迭代数据统计搜索条件查询列表
    * @param stageBurnDowmChartQuery
    * @return
    */
    List<StageBurnDowmChart> findStageBurnDowmChartList(StageBurnDowmChartQuery stageBurnDowmChartQuery);

    /**
    * 根据搜索条件按分页查询迭代的事项数据动态记录列表
    * @param stageBurnDowmChartQuery
    * @return
    */
    Pagination<StageBurnDowmChart> findStageBurnDowmChartPage(StageBurnDowmChartQuery stageBurnDowmChartQuery);

}