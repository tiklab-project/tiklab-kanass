package io.tiklab.kanass.home.statistic.controller;

import io.tiklab.kanass.home.statistic.model.Report;
import io.tiklab.kanass.home.statistic.model.ReportQuery;
import io.tiklab.kanass.home.statistic.service.ReportService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
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
 * 统计报表控制器
 */
@RestController
@RequestMapping("/report")
//@Api(name = "ReportController",desc = "ReportController")
public class ReportController {

    private static Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private ReportService reportService;

    @RequestMapping(path="/createReport",method = RequestMethod.POST)
    //@ApiMethod(name = "createReport",desc = "创建报表")
    //@ApiParam(name = "report",desc = "统计报表模型",required = true)
    public Result<String> createReport(@RequestBody @NotNull @Valid Report report){
        String id = reportService.createReport(report);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateReport",method = RequestMethod.POST)
    //@ApiMethod(name = "updateReport",desc = "更新报表")
    //@ApiParam(name = "report",desc = "统计报表模型",required = true)
    public Result<Void> updateReport(@RequestBody @NotNull @Valid Report report){
        reportService.updateReport(report);

        return Result.ok();
    }

    @RequestMapping(path="/deleteReport",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteReport",desc = "删除报表")
    //@ApiParam(name = "id",desc = "报表id",required = true)
    public Result<Void> deleteReport(@NotNull String id){
        reportService.deleteReport(id);

        return Result.ok();
    }

    @RequestMapping(path="/findReport",method = RequestMethod.POST)
    //@ApiMethod(name = "findReport",desc = "按照id查找报表")
    //@ApiParam(name = "id",desc = "报表id",required = true)
    public Result<Report> findReport(@NotNull String id){
        Report report = reportService.findReport(id);

        return Result.ok(report);
    }

    @RequestMapping(path="/findAllReport",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllReport",desc = "查找所有报表")
    public Result<List<Report>> findAllReport(){
        List<Report> reportList = reportService.findAllReport();

        return Result.ok(reportList);
    }

    @RequestMapping(path = "/findReportList",method = RequestMethod.POST)
    //@ApiMethod(name = "findReportList",desc = "按条件查询报表列表")
    //@ApiParam(name = "reportQuery",desc = "报表查询参数模型",required = true)
    public Result<List<Report>> findReportList(@RequestBody @Valid @NotNull ReportQuery reportQuery){
        List<Report> reportList = reportService.findReportList(reportQuery);

        return Result.ok(reportList);
    }

    @RequestMapping(path = "/findReportPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findReportPage",desc = "按条件分页查询报表列表")
    //@ApiParam(name = "reportQuery",desc = "报表查询参数模型",required = true)
    public Result<Pagination<Report>> findReportPage(@RequestBody @Valid @NotNull ReportQuery reportQuery){
        Pagination<Report> pagination = reportService.findReportPage(reportQuery);

        return Result.ok(pagination);
    }

}
