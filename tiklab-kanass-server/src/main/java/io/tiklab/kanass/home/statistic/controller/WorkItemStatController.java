package io.tiklab.kanass.home.statistic.controller;

import io.tiklab.kanass.home.statistic.model.ProjectWorkItemStat;
import io.tiklab.kanass.home.statistic.model.WorkItemBusStatusStat;
import io.tiklab.kanass.home.statistic.model.WorkItemStatistic;
import io.tiklab.kanass.home.statistic.service.WorkItemStatService;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.model.WorkItemQuery;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 事项统计控制器
 */
@RestController
@RequestMapping("/workItemStat")
@Api(name = "WorkItemStatController",desc = "事项统计")
public class WorkItemStatController {

    private static Logger logger = LoggerFactory.getLogger(WorkItemStatController.class);

    @Autowired
    private WorkItemStatService workItemStatService;

    @RequestMapping(path="/statWorkItemByStatus",method = RequestMethod.POST)
    @ApiMethod(name = "statWorkItemByStatus",desc = "按状态统计事项分布")
    public Result<List<WorkItemStatistic>> statWorkItemByStatus(){
        List<WorkItemStatistic> list = workItemStatService.statWorkItemByStatus();

        return Result.ok(list);
    }


    @RequestMapping(path="/statSprintWorkItemByStatus",method = RequestMethod.POST)
    @ApiMethod(name = "statSprintWorkItemByStatus",desc = "按状态统计某个迭代下事项分布")
    @ApiParam(name = "sprintId",desc = "迭代Id",required = true)
    public Result<List<WorkItemStatistic>> statSprintWorkItemByStatus(@NotNull String sprintId){
        List<WorkItemStatistic> list = workItemStatService.statSprintWorkItemByStatus(sprintId);

        return Result.ok(list);
    }

    @RequestMapping(path="/statWorkItemByBusStatus",method = RequestMethod.POST)
    @ApiMethod(name = "statWorkItemByBusStatus",desc = "按事项业务状态统计事项分布")
    public Result<List<WorkItemBusStatusStat>> statWorkItemByBusStatus(){
        List<WorkItemBusStatusStat> list = workItemStatService.statWorkItemByBusStatus();

        return Result.ok(list);
    }

    @RequestMapping(path="/statProjectWorkItemByBusStatus",method = RequestMethod.POST)
    @ApiMethod(name = "statProjectWorkItemByBusStatus",desc = "按项目，业务状态（未开始，进行中，逾期，结束）统计事项分布")
    @ApiParam(name = "projectId",desc = "项目Id",required = true)
    public Result<List<WorkItemBusStatusStat>> statProjectWorkItemByBusStatus(@NotNull String projectId, String masterId, String sprintId,String versionId){
        List<WorkItemBusStatusStat> list = workItemStatService.statProjectWorkItemByBusStatus(projectId, masterId, sprintId, versionId);

        return Result.ok(list);
    }

    @RequestMapping(path="/statProjectWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "statProjectWorkItem",desc = "按统计事项各个状态的数量")
    @ApiParam(name = "recentMasterId",desc = "用户Id",required = true)
    public Result<List<ProjectWorkItemStat>> statProjectWorkItemCount(@NotNull Integer num){
        List<ProjectWorkItemStat> list = workItemStatService.statProjectWorkItemCount(num);

        return Result.ok(list);
    }

    @RequestMapping(path="/statManageSprint",method = RequestMethod.POST)
    @ApiMethod(name = "statManageSprint",desc = "按照项目统计迭代")
    @ApiParam(name = "projectId",desc = "项目Id",required = true)
    public Result<List<ProjectWorkItemStat>> statManageSprint(@NotNull String projectId){
        List<Sprint> list = workItemStatService.statManageSprint(projectId);

        return Result.ok(list);
    }

    @RequestMapping(path="/statProjectManageSprint",method = RequestMethod.POST)
    @ApiMethod(name = "statProjectManageSprint",desc = "按照项目统计某个人复制的迭代")
    @ApiParam(name = "masterId",desc = "用户Id",required = true)
    public Result<List<ProjectWorkItemStat>> statProjectManageSprint(@NotNull String masterId,@NotNull String projectId){
        List<Sprint> list = workItemStatService.statProjectManageSprint(masterId,projectId);

        return Result.ok(list);
    }

    @RequestMapping(path="/statSprintProcessWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "statSprintProcessWorkItem",desc = "某个迭代下某个成员管理的进行中的事项")
    @ApiParam(name = "masterId",desc = "用户Id",required = true)
    public Result<List<ProjectWorkItemStat>> statSprintProcessWorkItem(@NotNull String masterId,@NotNull String sprintId){
        List<WorkItem> list = workItemStatService.statSprintProcessWorkItem(masterId,sprintId);

        return Result.ok(list);
    }

    @RequestMapping(path="/statSprintWorkItemByBusStatus",method = RequestMethod.POST)
    @ApiMethod(name = "statSprintWorkItemByBusStatus",desc = "按照项目业务状态（进行，逾期，完成，未开始）统计某个迭代下的事项")
    @ApiParam(name = "sprintId",desc = "迭代id",required = true)
    public Result<List<WorkItemBusStatusStat>> statSprintWorkItemByBusStatus(@NotNull String sprintId){
        List<WorkItemBusStatusStat> list = workItemStatService.statSprintWorkItemByBusStatus(sprintId);

        return Result.ok(list);
    }

    @RequestMapping(path="/statWorkItemProcess",method = RequestMethod.POST)
    @ApiMethod(name = "statWorkItemProcess",desc = "查找全局待办事项")
    public Result<List<WorkItem>> statWorkItemProcess(){
        List<WorkItem> list = workItemStatService.statWorkItemProcess();

        return Result.ok(list);
    }

    @RequestMapping(path="/statProgramSetWorkItemProcess",method = RequestMethod.POST)
    @ApiMethod(name = "statWorkItemProcess",desc = "查找某几个项目下的待办事项")
    @ApiParam(name = "ids",desc = "多个项目Id",required = true)
    public Result<List<WorkItem>> statProgramSetWorkItemProcess(String[] ids){
        List<WorkItem> list = workItemStatService.statProgramSetWorkItemProcess(ids);

        return Result.ok(list);
    }

    @RequestMapping(path="/statProjectWorkItemProcess",method = RequestMethod.POST)
    @ApiMethod(name = "statProjectWorkItemProcess",desc = "查找项目下待办事项")
    @ApiParam(name = "projectId",desc = "项目Id",required = true)
    public Result<List<WorkItem>> statProjectWorkItemProcess(String projectId){
        List<WorkItem> list = workItemStatService.statProjectWorkItemProcess(projectId);

        return Result.ok(list);
    }


    @RequestMapping(path = "/statWorkItemOverdue",method = RequestMethod.POST)
    @ApiMethod(name = "statWorkItemOverdue",desc = "根据条件统计逾期的事项")
    @ApiParam(name = "workItemQuery",desc = "查询对象（项目集，项目，迭代）",required = true)
    public Result<Pagination<WorkItem>> statWorkItemOverdue(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        Pagination<WorkItem> pagination = workItemStatService.statWorkItemOverdue(workItemQuery);

        return Result.ok(pagination);
    }

}

