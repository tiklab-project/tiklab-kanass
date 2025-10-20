package io.tiklab.kanass.test.instance.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.test.instance.model.ReportSummary;
import io.tiklab.kanass.test.instance.model.TestReport;
import io.tiklab.kanass.test.instance.model.TestReportQuery;
import io.tiklab.kanass.test.instance.service.TestReportService;
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

@RestController
@RequestMapping("/testReport")
//@Api(name = "TestReportController",desc = "测试报告管理")
public class TestReportController {

    private static Logger logger = LoggerFactory.getLogger(TestReportController.class);

    @Autowired
    private TestReportService testReportService;

    @RequestMapping(path="/createTestReport",method = RequestMethod.POST)
//    @ApiMethod(name = "createTestReport",desc = "创建测试报告")
//    @ApiParam(name = "testReport",desc = "testReport",required = true)
    public Result<String> createTestReport(@RequestBody @NotNull @Valid TestReport testReport){
        String id = testReportService.createTestReport(testReport);

        return Result.ok(id);
    }


    @RequestMapping(path="/updateTestReport",method = RequestMethod.POST)
//    @ApiMethod(name = "updateTestReport",desc = "更新测试报告")
//    @ApiParam(name = "testReport",desc = "testReport",required = true)
    public Result<Void> updateTestReport(@RequestBody @NotNull @Valid TestReport testReport){
        testReportService.updateTestReport(testReport);

        return Result.ok();
    }


    @RequestMapping(path="/deleteTestReport",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteTestReport",desc = "删除测试报告")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteTestReport(@NotNull String id){
        testReportService.deleteTestReport(id);

        return Result.ok();
    }

    @RequestMapping(path="/findTestReport",method = RequestMethod.POST)
//    @ApiMethod(name = "findTestReport",desc = "根据id查找测试报告")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<TestReport> findTestReport(@NotNull String id){
        TestReport testReport = testReportService.findTestReport(id);

        return Result.ok(testReport);
    }

    @RequestMapping(path="/findAllTestReport",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllTestReport",desc = "查找所有测试报告")
    public Result<List<TestReport>> findAllTestReport(){
        List<TestReport> testReportList = testReportService.findAllTestReport();

        return Result.ok(testReportList);
    }

    @RequestMapping(path = "/findTestReportList",method = RequestMethod.POST)
//    @ApiMethod(name = "findTestReportList",desc = "查询测试报告列表")
//    @ApiParam(name = "testReportQuery",desc = "testReportQuery",required = true)
    public Result<List<TestReport>> findTestReportList(@RequestBody @Valid @NotNull TestReportQuery testReportQuery){
        List<TestReport> testReportList = testReportService.findTestReportList(testReportQuery);

        return Result.ok(testReportList);
    }

    @RequestMapping(path = "/findTestReportPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findTestReportPage",desc = "按分页查询测试报告")
//    @ApiParam(name = "testReportQuery",desc = "testReportQuery",required = true)
    public Result<Pagination<TestReport>> findTestReportPage(@RequestBody @Valid @NotNull TestReportQuery testReportQuery){
        Pagination<TestReport> pagination = testReportService.findTestReportPage(testReportQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path="/getTestReportSummary",method = RequestMethod.POST)
//    @ApiMethod(name = "getTestReportSummary",desc = "获取摘要")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ReportSummary> getTestReportSummary(@NotNull String id){
        ReportSummary testReportSummary = testReportService.getTestReportSummary(id);

        return Result.ok(testReportSummary);
    }

    

}
