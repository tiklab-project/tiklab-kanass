package io.tiklab.kanass.home.insight.controller;


import io.tiklab.kanass.home.insight.model.ProjectOperateReport;
import io.tiklab.kanass.home.insight.model.ProjectOperateReportQuery;
import io.tiklab.kanass.home.insight.model.WorkItemCountQuery;
import io.tiklab.kanass.home.insight.service.ProjectInsightReportService;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * 仪表盘统计类目控制器
 */
@RestController
@RequestMapping("/projectInsightReport")
@Api(name = "ProjectInsightReportController",desc = "ProjectInsightReportController")
public class ProjectInsightReportController {
    private static Logger logger = LoggerFactory.getLogger(ProjectInsightReportController.class);

    @Autowired
    private ProjectInsightReportService projectInsightReportService;

    @RequestMapping(path="/statisticsProjectOperate",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsProjectOperate",desc = "统计某个项目的数据")
    @ApiParam(name = "projectOperateReportQuery",desc = "统计项目动态数据条件模型",required = true)
    public Result<ProjectOperateReport> statisticsProjectOperate(@RequestBody @NotNull @Valid ProjectOperateReportQuery projectOperateReportQuery){
        ProjectOperateReport projectOperateReport = projectInsightReportService.statisticsProjectOperate(projectOperateReportQuery);

        return Result.ok(projectOperateReport);
    }

    //查找所有项目进展
    @RequestMapping(path="/statisticsProjectOperateList",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsProjectOperateList",desc = "统计某个项目集下所有项目的数据")
    @ApiParam(name = "projectSetId",desc = "项目集id",required = true)
    public Result<HashMap<String, Object>> statisticsProjectOperateList(@NotNull String projectSetId){
        HashMap<String, Object> projectOperateListMap = projectInsightReportService.statisticsProjectOperateList(projectSetId);

        return Result.ok(projectOperateListMap);
    }

    @RequestMapping(path="/statisticsNewWorkItemCount",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsNewWorkItemCount",desc = "统计某段时间，以天，周，月，季，年为单位新增事项的数据")
    @ApiParam(name = "workItemCountQuery",desc = "事项统计条件模型",required = true)
    public Result<Map<String,Object>> statisticsNewWorkItemCount(@RequestBody @NotNull @Valid WorkItemCountQuery workItemCountQuery){
        Map<String,Object> newWorkItemCount = projectInsightReportService.statisticsNewWorkItemCountList(workItemCountQuery);

        return Result.ok(newWorkItemCount);
    }

    @RequestMapping(path = "/statisticsAllNewWorkItemTend",method = RequestMethod.POST)
    @ApiMethod(name  = "statisticsAllNewWorkItemTend",desc = "根据查询对象查事项新增趋势")
    public Result<Map<String, Object>> statisticsAllNewWorkItemTend(){
        Map<String, Object> dayCalendar = projectInsightReportService.statisticsAllNewWorkItemTend();
        return Result.ok(dayCalendar);
    }

    @RequestMapping(path="/statisticsEndWorkItemCount",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsEndWorkItemCount",desc = "统计某段时间，以天，周，月，季，年为单位完成事项的数据")
    @ApiParam(name = "workItemCountQuery",desc = "事项统计条件模型",required = true)
    public Result<Map<String,Object>> statisticsEndWorkItemCount(@RequestBody @NotNull @Valid WorkItemCountQuery workItemCountQuery){
        Map<String,Object> newWorkItemCount = projectInsightReportService.statisticsEndWorkItemCountList(workItemCountQuery);

        return Result.ok(newWorkItemCount);
    }

    @RequestMapping(path="/statisticsWorkItemTotalCountList",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsWorkItemTotalCountList",desc = "统计某段时间，以天，周，月，季，年为单位累计新增事项的数据")
    @ApiParam(name = "workItemCountQuery",desc = "事项统计条件模型",required = true)
    public Result<Map<String,Object>> statisticsWorkItemTotalCountList(@RequestBody @NotNull @Valid WorkItemCountQuery workItemCountQuery){
        Map<String,Object> newWorkItemCount = projectInsightReportService.statisticsWorkItemTotalCountList(workItemCountQuery);
        return Result.ok(newWorkItemCount);
    }

    @RequestMapping(path="/statisticsSprintWorkItemTotalCountList",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsSprintWorkItemTotalCountList",desc = "统计某段时间，以天，周，月，季，年为单位累计新增事项的数据")
    @ApiParam(name = "workItemCountQuery",desc = "事项统计条件模型",required = true)
    public Result<Map<String,Object>> statisticsSprintWorkItemTotalCountList(@RequestBody @NotNull @Valid WorkItemCountQuery workItemCountQuery){
        Map<String,Object> newWorkItemCount = projectInsightReportService.statisticsSprintWorkItemTotalCountList(workItemCountQuery);
        return Result.ok(newWorkItemCount);
    }

    @RequestMapping(path="/statisticsEndWorkItemTotalCountList",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsEndWorkItemTotalCountList",desc = "统计某段时间，以天，周，月，季，年为单位累计完成事项的数据")
    @ApiParam(name = "workItemCountQuery",desc = "事项统计条件模型",required = true)
    public Result<Map<String,Object>> statisticsEndWorkItemTotalCountList(@RequestBody @NotNull @Valid WorkItemCountQuery workItemCountQuery){
        Map<String,Object> newWorkItemCount = projectInsightReportService.statisticsEndWorkItemTotalCountList(workItemCountQuery);

        return Result.ok(newWorkItemCount);
    }

    @RequestMapping(path="/statisticsSprintEndWorkItemTotalCountList",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsSprintEndWorkItemTotalCountList",desc = "统计某段时间，以天，周，月，季，年为单位累计完成事项的数据")
    @ApiParam(name = "workItemCountQuery",desc = "事项统计条件模型",required = true)
    public Result<Map<String,Object>> statisticsSprintEndWorkItemTotalCountList(@RequestBody @NotNull @Valid WorkItemCountQuery workItemCountQuery){
        Map<String,Object> newWorkItemCount = projectInsightReportService.statisticsSprintEndWorkItemTotalCountList(workItemCountQuery);

        return Result.ok(newWorkItemCount);
    }

    @RequestMapping(path="/statisticsPorcessWorkItemCount",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsPorcessWorkItemCount",desc = "统计某段时间进行中的事项的数据")
    @ApiParam(name = "workItemCountQuery",desc = "事项统计条件模型",required = true)
    public Result<Map<String,Object>> statisticsPorcessWorkItemCount(@RequestBody @NotNull @Valid WorkItemCountQuery workItemCountQuery){
        Map<String,Object> newWorkItemCount = projectInsightReportService.statisticsProcessWorkItemCountList(workItemCountQuery);

        return Result.ok(newWorkItemCount);
    }

    @RequestMapping(path="/statisticsProcessBugCount",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsNewWorkItemCount",desc = "统计某段时间未修改的缺陷的数据")
    @ApiParam(name = "workItemCountQuery",desc = "事项统计条件模型",required = true)
    public Result<Map<String,Object>> statisticsProcessBugCount(@RequestBody @NotNull @Valid WorkItemCountQuery workItemCountQuery){
        Map<String,Object> newWorkItemCount = projectInsightReportService.statisticsProcessBugCountList(workItemCountQuery);

        return Result.ok(newWorkItemCount);
    }

    @RequestMapping(path="/statisticsProjectUserCount",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsProjectUserCount",desc = "统计项目集下所有项目的成员数量")
    @ApiParam(name = "projectSetId",desc = "项目集id",required = true)
    public Result<Map<String,Object>> statisticsProjectUserCount(@NotNull String projectSetId){
        Map<String, Object> projectUserCountList = projectInsightReportService.statisticsProjectUserCount(projectSetId);

        return Result.ok(projectUserCountList);
    }

    @RequestMapping(path="/statisticsProjectWorkItemCount",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsProjectWorkItemCount",desc = "统计项目集下所有项目的每个类型的事项数量")
    @ApiParam(name = "projectSetId",desc = "项目集id",required = true)
    public Result<Map<String,Object>> statisticsProjectWorkItemCount(@NotNull String projectSetId){
        Map<String, Object> projectWorkItemCountList = projectInsightReportService.statisticsProjectWorkItemCount(projectSetId);

        return Result.ok(projectWorkItemCountList);
    }

    @RequestMapping(path="/statisticsWorkItemStatusCount",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsWorkItemStatusCount",desc = "统计某个项目下，各个状态的事项数量")
    @ApiParam(name = "workItemCountQuery",desc = "事项统计条件模型",required = true)
    public Result<Map<String, Object>> statisticsWorkItemStatusCount(@RequestBody @NotNull @Valid WorkItemCountQuery workItemCountQuery){
        Map<String, Object> projectWorkItemCountList = projectInsightReportService.statisticsWorkItemStatusCount(workItemCountQuery);

        return Result.ok(projectWorkItemCountList);
    }

    @RequestMapping(path="/statisticsDayWorkItemCount",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsDayWorkItemCount",desc = "统计某个项目下事项单位时间（天，周，月，季，年）的新增，完成，剩余趋势 用于仪表盘")
    @ApiParam(name = "workItemCountQuery",desc = "事项统计条件模型",required = true)
    public Result<Map<String,Object>> statisticsDayWorkItemCount(@RequestBody @NotNull @Valid WorkItemCountQuery workItemCountQuery){
        Map<String, Object> dayWorkItemCount = projectInsightReportService.statisticsDayWorkItemCount(workItemCountQuery);

        return Result.ok(dayWorkItemCount);
    }

    @RequestMapping(path="/statisticsDayAllWorkItemCount",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsDayAllWorkItemCount",desc = "统计某个项目下事项单位时间（天）的新增，完成，剩余趋势 用于仪表盘")
    public Result<Map<String,Object>> statisticsDayAllWorkItemCount(){
        Map<String, Object> dayWorkItemCount = projectInsightReportService.statisticsDayAllWorkItemCount();

        return Result.ok(dayWorkItemCount);
    }


    @RequestMapping(path="/statisticsUserWorkItemCount",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsUserWorkItemCount",desc = "统计某个项目下，某个成员负责的事项对比")
    @ApiParam(name = "workItemCountQuery",desc = "事项统计条件模型",required = true)
    public Result<Map<String,Object>> statisticsUserWorkItemCount(@RequestBody @NotNull @Valid WorkItemCountQuery workItemCountQuery){
        Map<String,Object> userCount = projectInsightReportService.statisticsUserWorkItemCount(workItemCountQuery);

        return Result.ok(userCount);
    }

    @RequestMapping(path="/statisticsProjectByStatus",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsProjectByStatus",desc = "统计某个项目下，某个成员负责的事项对比")
    public Result<Map<String,Object>> statisticsProjectByStatus(){
        Map<String, Integer> projectCount = projectInsightReportService.statisticsProjectByStatus();

        return Result.ok(projectCount);
    }
    @RequestMapping(path="/statisticsWorkItemByStatus",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsWorkItemByStatus",desc = "统计某个项目下，某个成员负责的事项对比")
    public Result<Map<String,Object>> statisticsWorkItemByStatus(){
        Map<String, Integer> workItemCount = projectInsightReportService.statisticsWorkItemByStatus();

        return Result.ok(workItemCount);
    }

    @RequestMapping(path="/statisticsTodoWorkByStatus",method = RequestMethod.POST)
    @ApiMethod(name = "statisticsTodoWorkByStatus",desc = "统计某个项目下，某个成员负责的事项对比")
    public Result<Map<String,Object>> statisticsTodoWorkByStatus(@RequestBody @NotNull @Valid  HashMap<String, String> params){
        Map<String, Integer> todoCount = projectInsightReportService.statisticsTodoWorkByStatus(params);

        return Result.ok(todoCount);
    }

}
