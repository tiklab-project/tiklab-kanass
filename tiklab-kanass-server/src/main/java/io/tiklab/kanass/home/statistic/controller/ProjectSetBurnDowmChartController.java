package io.tiklab.kanass.home.statistic.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.home.statistic.model.ProjectSetBurnDowmChart;
import io.tiklab.kanass.home.statistic.model.ProjectSetBurnDowmChartQuery;
import io.tiklab.kanass.home.statistic.service.ProjectSetBurnDowmChartService;
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
 * 项目下数据动态记录控制器，用于项目燃尽图和统计
 */
@RestController
@RequestMapping("/projectSetBurnDowmChart")
public class ProjectSetBurnDowmChartController {

    private static Logger logger = LoggerFactory.getLogger(ProjectSetBurnDowmChartController.class);

    @Autowired
    private ProjectSetBurnDowmChartService projectSetBurnDowmChartService;

    @RequestMapping(path="/createProjectSetBurnDowmChart",method = RequestMethod.POST)
    //@ApiMethod(name = "createProjectSetBurnDowmChart",desc = "创建项目动态数据")
    //@ApiParam(name = "projectSetBurnDowmChart",desc = "项目的事项数据动态模型",required = true)
    public Result<String> createProjectSetBurnDowmChart(@RequestBody @NotNull @Valid ProjectSetBurnDowmChart projectSetBurnDowmChart){
        String id = projectSetBurnDowmChartService.createProjectSetBurnDowmChart(projectSetBurnDowmChart);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateProjectSetBurnDowmChart",method = RequestMethod.POST)
    //@ApiMethod(name = "updateProjectSetBurnDowmChart",desc = "更新项目动态数据")
    //@ApiParam(name = "projectSetBurnDowmChart",desc = "项目的事项数据动态模型",required = true)
    public Result<Void> updateProjectSetBurnDowmChart(@RequestBody @NotNull @Valid ProjectSetBurnDowmChart projectSetBurnDowmChart){
        projectSetBurnDowmChartService.updateProjectSetBurnDowmChart(projectSetBurnDowmChart);

        return Result.ok();
    }

    @RequestMapping(path="/deleteProjectSetBurnDowmChart",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteProjectSetBurnDowmChart",desc = "删除项目动态记录")
    //@ApiParam(name = "id",desc = "记录id",required = true)
    public Result<Void> deleteProjectSetBurnDowmChart(@NotNull String id){
        projectSetBurnDowmChartService.deleteProjectSetBurnDowmChart(id);

        return Result.ok();
    }

    @RequestMapping(path="/findProjectSetBurnDowmChart",method = RequestMethod.POST)
    //@ApiMethod(name = "findProjectSetBurnDowmChart",desc = "按照id查找项目动态记录")
    //@ApiParam(name = "id",desc = "记录id",required = true)
    public Result<ProjectSetBurnDowmChart> findProjectSetBurnDowmChart(@NotNull String id){
        ProjectSetBurnDowmChart projectSetBurnDowmChart = projectSetBurnDowmChartService.findProjectSetBurnDowmChart(id);

        return Result.ok(projectSetBurnDowmChart);
    }

    @RequestMapping(path="/findAllProjectSetBurnDowmChart",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllProjectSetBurnDowmChart",desc = "查找所有项目动态记录")
    public Result<List<ProjectSetBurnDowmChart>> findAllProjectSetBurnDowmChart(){
        List<ProjectSetBurnDowmChart> projectSetBurnDowmChartList = projectSetBurnDowmChartService.findAllProjectSetBurnDowmChart();

        return Result.ok(projectSetBurnDowmChartList);
    }

    @RequestMapping(path = "/findProjectSetBurnDowmChartList",method = RequestMethod.POST)
    //@ApiMethod(name = "findProjectSetBurnDowmChartList",desc = "根据查询条件模型查找项目动态记录")
    //@ApiParam(name = "projectSetBurnDowmChartQuery",desc = "项目的事项动态查询模型",required = true)
    public Result<List<ProjectSetBurnDowmChart>> findProjectSetBurnDowmChartList(@RequestBody @Valid @NotNull ProjectSetBurnDowmChartQuery projectSetBurnDowmChartQuery){
        List<ProjectSetBurnDowmChart> projectSetBurnDowmChartList = projectSetBurnDowmChartService.findProjectSetBurnDowmChartList(projectSetBurnDowmChartQuery);

        return Result.ok(projectSetBurnDowmChartList);
    }

    @RequestMapping(path = "/findProjectSetBurnDowmChartPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findProjectSetBurnDowmChartPage",desc = "根据查询条件模型按分页查找项目动态记录")
    //@ApiParam(name = "projectSetBurnDowmChartQuery",desc = "项目的事项动态查询模型",required = true)
    public Result<Pagination<ProjectSetBurnDowmChart>> findProjectSetBurnDowmChartPage(@RequestBody @Valid @NotNull ProjectSetBurnDowmChartQuery projectSetBurnDowmChartQuery){
        Pagination<ProjectSetBurnDowmChart> pagination = projectSetBurnDowmChartService.findProjectSetBurnDowmChartPage(projectSetBurnDowmChartQuery);

        return Result.ok(pagination);
    }


}
