package io.tiklab.teamwire.home.statistic.service;


import io.tiklab.teamwire.home.statistic.model.WorkItemStatistic;
import io.tiklab.teamwire.home.statistic.model.StatisticWorkItemQuery;
import io.tiklab.teamwire.home.statistic.model.WorkItemBuildAndEndStatistic;

import java.util.List;

/**
 * 事项统计服务接口
 */
public interface StatisticWorkItemService {

    /**
     * 事项状态统计
     * @param statisticWorkItemQuery
     * @return
     */
    List<WorkItemStatistic> statisticWorkItem(StatisticWorkItemQuery statisticWorkItemQuery);

    /**
     * 事项创建解决统计
     * @param statisticWorkItemQuery
     * @return
     */
    List<WorkItemBuildAndEndStatistic> statisticBuildAndEndWorkItem(StatisticWorkItemQuery statisticWorkItemQuery);
}
