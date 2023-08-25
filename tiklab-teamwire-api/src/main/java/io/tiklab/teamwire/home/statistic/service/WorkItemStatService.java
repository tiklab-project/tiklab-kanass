package io.tiklab.teamwire.home.statistic.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.teamwire.home.statistic.model.WorkItemStatistic;
import io.tiklab.teamwire.sprint.model.Sprint;
import io.tiklab.teamwire.home.statistic.model.ProjectWorkItemStat;
import io.tiklab.teamwire.home.statistic.model.WorkItemBusStatusStat;
import io.tiklab.teamwire.workitem.model.WorkItem;
import io.tiklab.teamwire.workitem.model.WorkItemQuery;

import java.util.List;

/**
* 统计服务接口
*/
public interface WorkItemStatService {

    /**
     * 按状态统计事项分布
     * @return
     */
    List<WorkItemStatistic> statWorkItemByStatus();

    /**
     * 按状态统计某个迭代下事项分布
     * @return
     */
    List<WorkItemStatistic> statSprintWorkItemByStatus(String sprintId);

    /**
     * 按业务状态统计事项分布
     * @return
     */
    List<WorkItemBusStatusStat> statWorkItemByBusStatus();

    /**
     * 按项目，业务状态统计事项分布
     * @return
     */
    List<WorkItemBusStatusStat> statProjectWorkItemByBusStatus(String projectId, String masterId);

    /**
     * 按用户统计事项各个状态的数量
     * @return
     */
    List<ProjectWorkItemStat> statProjectWorkItemCount(Integer num);

    /**
     * 按用户统计事项分布
     * @return
     */
    List<Sprint> statManageSprint(String mastetId);

    /**
     * 按用户统计事项分布
     * @return
     */
    List<Sprint> statProjectManageSprint(String mastesId,String projectId);

    /**
     * 按用户统计事项分布
     * @return
     */
    List<WorkItem> statSprintProcessWorkItem(String masterId, String sprintId);

    /**
     * 按项目，迭代 业务状态统计事项分布
     * @return
     */
    List<WorkItemBusStatusStat> statSprintWorkItemByBusStatus(String sprintId);

    List<WorkItem> statWorkItemProcess();

    List<WorkItem> statProgramSetWorkItemProcess(String[] ids);

    List<WorkItem> statProjectWorkItemProcess(String sprintId);

    Pagination<WorkItem> statWorkItemOverdue(WorkItemQuery workItemQuery);

}