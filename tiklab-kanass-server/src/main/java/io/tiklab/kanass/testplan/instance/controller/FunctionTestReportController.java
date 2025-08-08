package io.tiklab.kanass.testplan.instance.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.kanass.testplan.instance.model.FunctionTestReport;
import io.tiklab.kanass.testplan.instance.service.FunctionTestReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/functionTestReport")
//@Api(name = "TestPlanInstanceController",desc = "功能测试计划的报告")
public class FunctionTestReportController {

    private static Logger logger = LoggerFactory.getLogger(FunctionTestReportController.class);

    @Autowired
    private FunctionTestReportService functionTestReportService;


    @RequestMapping(path="/getFunctionTestReport",method = RequestMethod.POST)
//    @ApiMethod(name = "getFunctionTestReport",desc = "获取功能测试测试报告")
//    @ApiParam(name = "testPlanId",desc = "testPlanId",required = true)
    public Result<FunctionTestReport> getFunctionTestReport(@NotNull @Valid String testPlanId){
        FunctionTestReport functionTestReport = functionTestReportService.getFunctionTestReport(testPlanId);

        return Result.ok(functionTestReport);
    }


}
