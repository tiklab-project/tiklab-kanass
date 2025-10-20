package io.tiklab.kanass.test.testplan.cases.controller;

import io.tiklab.kanass.test.testplan.cases.model.PlanCase;
import io.tiklab.kanass.test.testplan.cases.model.TestPlanCase;
import io.tiklab.kanass.test.testplan.cases.model.TestPlanCaseQuery;
import io.tiklab.kanass.test.testplan.cases.service.TestPlanCaseService;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
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
import java.util.Map;

/**
 * 测试计划绑定的用例 控制器
 */
@RestController
@RequestMapping("/testPlanCase")
//@Api(name = "TestPlanCaseController",desc = "测试计划绑定的用例(测试计划关联用例表)")
public class TestPlanCaseController {

    private static Logger logger = LoggerFactory.getLogger(TestPlanCaseController.class);

    @Autowired
    private TestPlanCaseService testPlanCaseService;

    @RequestMapping(path="/createTestPlanCase",method = RequestMethod.POST)
//    @ApiMethod(name = "createTestPlanCase",desc = "创建测试计划绑定的用例")
//    @ApiParam(name = "testPlanCase",desc = "testPlanCase",required = true)
    public Result<String> createTestPlanCase(@RequestBody @NotNull @Valid TestPlanCase testPlanCase){
        String id = testPlanCaseService.createTestPlanCase(testPlanCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateTestPlanCase",method = RequestMethod.POST)
//    @ApiMethod(name = "updateTestPlanCase",desc = "修改测试计划绑定的用例")
//    @ApiParam(name = "testPlanCase",desc = "testPlanCase",required = true)
    public Result<Void> updateTestPlanCase(@RequestBody @NotNull @Valid TestPlanCase testPlanCase){
        testPlanCaseService.updateTestPlanCase(testPlanCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteTestPlanCase",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteTestPlanCase",desc = "删除测试计划绑定的用例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteTestPlanCase(@NotNull String id){
        testPlanCaseService.deleteTestPlanCase(id);

        return Result.ok();
    }


    @RequestMapping(path="/findTestPlanCase",method = RequestMethod.POST)
//    @ApiMethod(name = "findTestPlanCase",desc = "通过id查询")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<TestPlanCase> findTestPlanCase(@NotNull String id){
        TestPlanCase testPlanCase = testPlanCaseService.findTestPlanCase(id);

        return Result.ok(testPlanCase);
    }

    @RequestMapping(path="/findAllTestPlanCase",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllTestPlanCase",desc = "查询所有")
    public Result<List<TestPlanCase>> findAllTestPlanCase(){
        List<TestPlanCase> testPlanCaseList = testPlanCaseService.findAllTestPlanCase();

        return Result.ok(testPlanCaseList);
    }

    @RequestMapping(path = "/findPlanCasePage",method = RequestMethod.POST)
//    @ApiMethod(name = "findPlanCasePage",desc = "测试计划已绑定的用例")
//    @ApiParam(name = "testPlanCaseQuery",desc = "testCaseQuery",required = true)
    public Result<Pagination<PlanCase>> findPlanCasePage(@RequestBody @Valid @NotNull TestPlanCaseQuery testPlanCaseQuery){
        Pagination<PlanCase> pagination = testPlanCaseService.findPlanCasePage(testPlanCaseQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findPlanCaseList",method = RequestMethod.POST)
//    @ApiMethod(name = "findPlanCasePage",desc = "测试计划已绑定的用例")
//    @ApiParam(name = "testPlanCaseQuery",desc = "testCaseQuery",required = true)
    public Result<List<PlanCase>> findPlanCaseList(@RequestBody @Valid @NotNull TestPlanCaseQuery testPlanCaseQuery){
        List<PlanCase> list = testPlanCaseService.findPlanCaseList(testPlanCaseQuery);

        return Result.ok(list);
    }

    @RequestMapping(path = "/findTestCasePage",method = RequestMethod.POST)
//    @ApiMethod(name = "findPlanCasePage",desc = "查询关联的用例，剔除已关联的用例")
//    @ApiParam(name = "testPlanCaseQuery",desc = "testCaseQuery",required = true)
    public Result<Pagination<PlanCase>> findBindTestCaseList(@RequestBody @Valid @NotNull TestPlanCaseQuery testPlanCaseQuery){
        Pagination<PlanCase> pagination =  testPlanCaseService.findTestCasePage(testPlanCaseQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path="/planBindCase",method = RequestMethod.POST)
//    @ApiMethod(name = "createTestPlanCaseList",desc = "给测试计划关联测试用例")
//    @ApiParam(name = "testPlanCaseList",desc = "testPlanCaseList",required = true)
    public Result<String> createTestPlanCaseList( @RequestBody @NotNull @Valid List<TestPlanCase> testPlanCaseList){
            testPlanCaseService.planBindCase(testPlanCaseList);

        return Result.ok();

    }


    @RequestMapping(path="/isCaseExist",method = RequestMethod.POST)
//    @ApiMethod(name = "isCaseExist",desc = "判断用例是否被绑定")
//    @ApiParam(name = "caseId",desc = "caseId",required = true)
    public Result<Boolean> isCaseExist(@NotNull String caseId){
        Boolean isBind = testPlanCaseService.isCaseExist(caseId);

        return Result.ok(isBind);
    }

    @RequestMapping(path="/getCaseTypeNum",method = RequestMethod.POST)
//    @ApiMethod(name = "getCaseTypeNum",desc = "判断用例是否被绑定")
//    @ApiParam(name = "testPlanId",desc = "testPlanId",required = true)
    public Result<Map<String, Integer>> getCaseTypeNum(@NotNull String testPlanId){
        Map<String, Integer> caseTypeNum = testPlanCaseService.getCaseTypeNum(testPlanId);

        return Result.ok(caseTypeNum);
    }


}
