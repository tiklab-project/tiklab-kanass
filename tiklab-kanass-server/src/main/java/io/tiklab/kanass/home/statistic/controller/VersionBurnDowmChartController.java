package io.tiklab.kanass.home.statistic.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.kanass.home.statistic.model.VersionBurnDowmChart;
import io.tiklab.kanass.home.statistic.model.VersionBurnDowmChartQuery;
import io.tiklab.kanass.home.statistic.service.VersionBurnDowmChartService;
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
 * 迭代下事项数据动态记录控制器
 */
@RestController
@RequestMapping("/versionBurnDowmChart")
//@Api(name = "VersionBurnDowmChartController",desc = "VersionBurnDowmChartController")
public class VersionBurnDowmChartController {

    private static Logger logger = LoggerFactory.getLogger(VersionBurnDowmChartController.class);

    @Autowired
    private VersionBurnDowmChartService versionBurnDowmChartService;

    @RequestMapping(path="/createVersionBurnDowmChart",method = RequestMethod.POST)
    //@ApiMethod(name = "createVersionBurnDowmChart",desc = "创建迭代下事项数据动态记录")
    //@ApiParam(name = "versionBurnDowmChart",desc = "迭代事项数据动态统计模型",required = true)
    public Result<String> createVersionBurnDowmChart(@RequestBody @NotNull @Valid VersionBurnDowmChart versionBurnDowmChart){
        String id = versionBurnDowmChartService.createVersionBurnDowmChart(versionBurnDowmChart);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateVersionBurnDowmChart",method = RequestMethod.POST)
    //@ApiMethod(name = "updateVersionBurnDowmChart",desc = "更新迭代下事项数据动态记录")
    //@ApiParam(name = "versionBurnDowmChart",desc = "迭代事项数据动态统计模型",required = true)
    public Result<Void> updateVersionBurnDowmChart(@RequestBody @NotNull @Valid VersionBurnDowmChart versionBurnDowmChart){
        versionBurnDowmChartService.updateVersionBurnDowmChart(versionBurnDowmChart);

        return Result.ok();
    }

    @RequestMapping(path="/deleteVersionBurnDowmChart",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteVersionBurnDowmChart",desc = "删除迭代下事项数据动态记录")
    //@ApiParam(name = "id",desc = "记录id",required = true)
    public Result<Void> deleteVersionBurnDowmChart(@NotNull String id){
        versionBurnDowmChartService.deleteVersionBurnDowmChart(id);

        return Result.ok();
    }

    @RequestMapping(path="/findVersionBurnDowmChart",method = RequestMethod.POST)
    //@ApiMethod(name = "findVersionBurnDowmChart",desc = "根据id查找迭代下事项数据动态记录")
    //@ApiParam(name = "id",desc = "记录id",required = true)
    public Result<VersionBurnDowmChart> findVersionBurnDowmChart(@NotNull String id){
        VersionBurnDowmChart versionBurnDowmChart = versionBurnDowmChartService.findVersionBurnDowmChart(id);

        return Result.ok(versionBurnDowmChart);
    }

    @RequestMapping(path="/findAllVersionBurnDowmChart",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllVersionBurnDowmChart",desc = "查找所有迭代下事项数据动态记录列表")
    public Result<List<VersionBurnDowmChart>> findAllVersionBurnDowmChart(){
        List<VersionBurnDowmChart> versionBurnDowmChartList = versionBurnDowmChartService.findAllVersionBurnDowmChart();

        return Result.ok(versionBurnDowmChartList);
    }

    @RequestMapping(path = "/findVersionBurnDowmChartList",method = RequestMethod.POST)
    //@ApiMethod(name = "findVersionBurnDowmChartList",desc = "根据迭代数据统计搜索条件查询列表")
    //@ApiParam(name = "versionBurnDowmChartQuery",desc = "迭代数据统计搜索模型",required = true)
    public Result<List<VersionBurnDowmChart>> findVersionBurnDowmChartList(@RequestBody @Valid @NotNull VersionBurnDowmChartQuery versionBurnDowmChartQuery){
        List<VersionBurnDowmChart> versionBurnDowmChartList = versionBurnDowmChartService.findVersionBurnDowmChartList(versionBurnDowmChartQuery);

        return Result.ok(versionBurnDowmChartList);
    }

    @RequestMapping(path = "/findVersionBurnDowmChartPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findVersionBurnDowmChartPage",desc = "根据搜索条件按分页查询迭代的事项数据动态记录列表")
    //@ApiParam(name = "versionBurnDowmChartQuery",desc = "迭代数据统计搜索模型",required = true)
    public Result<Pagination<VersionBurnDowmChart>> findVersionBurnDowmChartPage(@RequestBody @Valid @NotNull VersionBurnDowmChartQuery versionBurnDowmChartQuery){
        Pagination<VersionBurnDowmChart> pagination = versionBurnDowmChartService.findVersionBurnDowmChartPage(versionBurnDowmChartQuery);

        return Result.ok(pagination);
    }

}
