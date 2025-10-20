package io.tiklab.kanass.test.testplan.cases.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.kanass.test.testplan.cases.model.TestPlan;
import io.tiklab.kanass.test.testplan.cases.model.TestPlanQuery;
import io.tiklab.kanass.test.testplan.cases.service.TestPlanService;
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
 * @pi.protocol: http
 * @pi.groupName: 测试计划 控制器
 * @pi.path:/testPlan
 */
@RestController
@RequestMapping("/testPlan")
@Api(name = "测试计划",desc = "测试计划")
public class TestPlanController {

    private static Logger logger = LoggerFactory.getLogger(TestPlanController.class);

    @Autowired
    private TestPlanService testPlanService;

    /**
     * @pi.name:createTestPlan
     * @pi.url:/createTestPlan
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.testplan.cases.model.TestPlan
     */
    @RequestMapping(path="/createTestPlan",method = RequestMethod.POST)
//    @ApiMethod(name = "createTestPlan",desc = "创建测试i计划")
//    @ApiParam(name = "testPlan",desc = "testPlan",required = true)
    public Result<String> createTestPlan(@RequestBody @NotNull @Valid TestPlan testPlan){
        String id = testPlanService.createTestPlan(testPlan);

        return Result.ok(id);
    }

    /**
     * @pi.name:updateTestPlan
     * @pi.url:/updateTestPlan
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.testplan.cases.model.TestPlan
     */
    @RequestMapping(path="/updateTestPlan",method = RequestMethod.POST)
//    @ApiMethod(name = "updateTestPlan",desc = "修改测试计划")
//    @ApiParam(name = "testPlan",desc = "testPlan",required = true)
    public Result<Void> updateTestPlan(@RequestBody @NotNull @Valid TestPlan testPlan){
        testPlanService.updateTestPlan(testPlan);

        return Result.ok();
    }

    /**
     * @pi.name:deleteTestPlan
     * @pi.url:/deleteTestPlan
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: name=id;dataType=string;value=id;desc=当前删除的id
     */
    @RequestMapping(path="/deleteTestPlan",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteTestPlan",desc = "通过id删除测试计划")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteTestPlan(@NotNull String id){
        testPlanService.deleteTestPlan(id);

        return Result.ok();
    }


    /**
     * @pi.name:findTestPlan
     * @pi.url:/findTestPlan
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;desc=当前查找的id
     */
    @RequestMapping(path="/findTestPlan",method = RequestMethod.POST)
    @ApiMethod(name = "通过id查询测试计划",desc = "通过id查询测试计划")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<TestPlan> findTestPlan(@NotNull String id){
        TestPlan testPlan = testPlanService.findTestPlan(id);

        return Result.ok(testPlan);
    }

    /**
     * @pi.name:findAllTestPlan
     * @pi.url:/findAllTestPlan
     * @pi.methodType:post
     * @pi.request-type:none
     */
    @RequestMapping(path="/findAllTestPlan",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllTestPlan",desc = "查询所有测试计划")
    public Result<List<TestPlan>> findAllTestPlan(){
        List<TestPlan> testPlanList = testPlanService.findAllTestPlan();

        return Result.ok(testPlanList);
    }

    /**
     * @pi.name:findTestPlanList
     * @pi.url:/findTestPlanList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.testplan.cases.model.TestPlanQuery
     */
    @RequestMapping(path = "/findTestPlanList",method = RequestMethod.POST)
    @ApiMethod(name = "通过查询对象查询测试计划列表",desc = "通过查询对象查询测试计划")
    @ApiParam(name = "testPlanQuery",desc = "testPlanQuery",required = true)
    public Result<List<TestPlan>> findTestPlanList(@RequestBody @Valid @NotNull TestPlanQuery testPlanQuery){
        List<TestPlan> testPlanList = testPlanService.findTestPlanList(testPlanQuery);

        return Result.ok(testPlanList);
    }

    /**
     * @pi.name:findTestPlanPage
     * @pi.url:/findTestPlanPage
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.testplan.cases.model.TestPlanQuery
     */
    @RequestMapping(path = "/findTestPlanPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findTestPlanPage",desc = "通过查询对象分页查询测试计划")
//    @ApiParam(name = "testPlanQuery",desc = "testPlanQuery",required = true)
    public Result<Pagination<TestPlan>> findTestPlanPage(@RequestBody @Valid @NotNull TestPlanQuery testPlanQuery){
        Pagination<TestPlan> pagination = testPlanService.findTestPlanPage(testPlanQuery);

        return Result.ok(pagination);
    }

}
