package io.thoughtware.kanass.home.insight.service;

import io.thoughtware.kanass.home.insight.model.*;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 仪表盘统计类目服务接口
 */
public interface ProjectInsightReportService {

    /**
     * 统计某个项目的数据
     */
    ProjectOperateReport statisticsProjectOperate(@NotNull @Valid ProjectOperateReportQuery projectOperateReportQuery);

    /**
     * 统计某个项目集下所有项目的数据
     * @param projectSetId
     * @return
     */
    List<ProjectOperateReport> statisticsProjectOperateList(String projectSetId);

    /**
     * 统计某段时间，以天，周，月，季，年为单位新增事项的数据
     * @param workItemCountQuery
     * @return
     */
    Map<String, Object> statisticsNewWorkItemCountList(@NotNull @Valid WorkItemCountQuery workItemCountQuery);

    /**
     * 统计某段时间，以天，周，月，季，年为单位完成事项的数据
     * @param workItemCountQuery
     * @return
     */
    Map<String, Object> statisticsEndWorkItemCountList(@NotNull @Valid WorkItemCountQuery workItemCountQuery);

    /**
     * 统计某段时间，以天，周，月，季，年为单位累计新增事项的数据
     * @param workItemCountQuery
     * @return
     */
    Map<String, Object> statisticsWorkItemTotalCountList(@NotNull @Valid WorkItemCountQuery workItemCountQuery);

    /**
     * 统计某段时间，以天，周，月，季，年为单位累计迭代的新增事项的数据
     * @param workItemCountQuery
     * @return
     */
    Map<String, Object> statisticsSprintWorkItemTotalCountList(@NotNull @Valid WorkItemCountQuery workItemCountQuery);

    /**
     * 统计某段时间，以天，周，月，季，年为单位累计完成事项的数据
     * @param workItemCountQuery
     * @return
     */
    Map<String, Object> statisticsEndWorkItemTotalCountList(@NotNull @Valid WorkItemCountQuery workItemCountQuery);

    /**
     * 统计某段时间，以天，周，月，季，年为单位累计完成事项的数据
     * @param workItemCountQuery
     * @return
     */
    Map<String, Object> statisticsSprintEndWorkItemTotalCountList(@NotNull @Valid WorkItemCountQuery workItemCountQuery);

    /**
     * 统计某段时间进行中的事项的数据
     * @param workItemCountQuery
     * @return
     */
    Map<String, Object> statisticsProcessWorkItemCountList(@NotNull @Valid WorkItemCountQuery workItemCountQuery);

    /**
     * 统计某段时间未修改的缺陷的数据
     * @param workItemCountQuery
     * @return
     */
    Map<String, Object> statisticsProcessBugCountList(@NotNull @Valid WorkItemCountQuery workItemCountQuery);

    /**
     * 统计项目集下所有项目的成员数量
     * @param projectSetId
     * @return
     */
    Map<String, Object> statisticsProjectUserCount(String projectSetId);

    /**
     * 统计项目集下所有项目的每个类型的事项数量
     * @param projectSetId
     * @return
     */
    Map<String, Object> statisticsProjectWorkItemCount(String projectSetId);

    /**
     * 统计某个项目下，各个状态的事项数量
     * @param workItemCountQuery
     * @return
     */
    Map<String, Integer> statisticsWorkItemStatusCount(WorkItemCountQuery workItemCountQuery);

    /**
     * 统计某个项目下事项
     * 单位时间（天，周，月，季，年）的新增，完成，剩余趋势，
     * 用于仪表盘
     * @param workItemCountQuery
     * @return
     */
    Map<String, Object> statisticsDayWorkItemCount(WorkItemCountQuery workItemCountQuery);

    /**
     * 统计某个项目下，某个成员负责的事项对比
     * @param workItemCountQuery
     * @return
     */
    Map<String, Object> statisticsUserWorkItemCount(WorkItemCountQuery workItemCountQuery);


}
