package io.thoughtware.kanass.home.statistic.controller;

import io.thoughtware.kanass.home.statistic.model.ProjectBurnDowmChart;
import io.thoughtware.kanass.home.statistic.model.ProjectBurnDowmChartQuery;
import io.thoughtware.kanass.home.statistic.service.ProjectBurnDowmChartService;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 项目下数据动态记录控制器
 */
@RestController
@RequestMapping("/projectBurnDowmChart")
@Api(name = "ProjectBurnDowmChartController",desc = "ProjectBurnDowmChartController")
public class ProjectBurnDowmChartController {

    private static Logger logger = LoggerFactory.getLogger(ProjectBurnDowmChartController.class);

    @Autowired
    private ProjectBurnDowmChartService projectBurnDowmChartService;

    @RequestMapping(path="/createProjectBurnDowmChart",method = RequestMethod.POST)
    @ApiMethod(name = "createProjectBurnDowmChart",desc = "创建项目动态数据")
    @ApiParam(name = "projectBurnDowmChart",desc = "项目的事项数据动态模型",required = true)
    public Result<String> createProjectBurnDowmChart(@RequestBody @NotNull @Valid ProjectBurnDowmChart projectBurnDowmChart){
        String id = projectBurnDowmChartService.createProjectBurnDowmChart(projectBurnDowmChart);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateProjectBurnDowmChart",method = RequestMethod.POST)
    @ApiMethod(name = "updateProjectBurnDowmChart",desc = "更新项目动态数据")
    @ApiParam(name = "projectBurnDowmChart",desc = "项目的事项数据动态模型",required = true)
    public Result<Void> updateProjectBurnDowmChart(@RequestBody @NotNull @Valid ProjectBurnDowmChart projectBurnDowmChart){
        projectBurnDowmChartService.updateProjectBurnDowmChart(projectBurnDowmChart);

        return Result.ok();
    }

    @RequestMapping(path="/deleteProjectBurnDowmChart",method = RequestMethod.POST)
    @ApiMethod(name = "deleteProjectBurnDowmChart",desc = "删除项目动态记录")
    @ApiParam(name = "id",desc = "记录id",required = true)
    public Result<Void> deleteProjectBurnDowmChart(@NotNull String id){
        projectBurnDowmChartService.deleteProjectBurnDowmChart(id);

        return Result.ok();
    }

    @RequestMapping(path="/findProjectBurnDowmChart",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectBurnDowmChart",desc = "按照id查找项目动态记录")
    @ApiParam(name = "id",desc = "记录id",required = true)
    public Result<ProjectBurnDowmChart> findProjectBurnDowmChart(@NotNull String id){
        ProjectBurnDowmChart projectBurnDowmChart = projectBurnDowmChartService.findProjectBurnDowmChart(id);

        return Result.ok(projectBurnDowmChart);
    }

    @RequestMapping(path="/findAllProjectBurnDowmChart",method = RequestMethod.POST)
    @ApiMethod(name = "findAllProjectBurnDowmChart",desc = "查找所有项目动态记录")
    public Result<List<ProjectBurnDowmChart>> findAllProjectBurnDowmChart(){
        List<ProjectBurnDowmChart> projectBurnDowmChartList = projectBurnDowmChartService.findAllProjectBurnDowmChart();

        return Result.ok(projectBurnDowmChartList);
    }

    @RequestMapping(path = "/findProjectBurnDowmChartList",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectBurnDowmChartList",desc = "根据查询条件模型查找项目动态记录")
    @ApiParam(name = "projectBurnDowmChartQuery",desc = "项目的事项动态查询模型",required = true)
    public Result<List<ProjectBurnDowmChart>> findProjectBurnDowmChartList(@RequestBody @Valid @NotNull ProjectBurnDowmChartQuery projectBurnDowmChartQuery){
        List<ProjectBurnDowmChart> projectBurnDowmChartList = projectBurnDowmChartService.findProjectBurnDowmChartList(projectBurnDowmChartQuery);

        return Result.ok(projectBurnDowmChartList);
    }

    @RequestMapping(path = "/findProjectBurnDowmChartPage",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectBurnDowmChartPage",desc = "根据查询条件模型按分页查找项目动态记录")
    @ApiParam(name = "projectBurnDowmChartQuery",desc = "项目的事项动态查询模型",required = true)
    public Result<Pagination<ProjectBurnDowmChart>> findProjectBurnDowmChartPage(@RequestBody @Valid @NotNull ProjectBurnDowmChartQuery projectBurnDowmChartQuery){
        Pagination<ProjectBurnDowmChart> pagination = projectBurnDowmChartService.findProjectBurnDowmChartPage(projectBurnDowmChartQuery);

        return Result.ok(pagination);
    }

}
