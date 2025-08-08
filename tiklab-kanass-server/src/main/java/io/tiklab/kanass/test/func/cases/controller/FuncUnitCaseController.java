package io.tiklab.kanass.test.func.cases.controller;

import io.tiklab.kanass.test.func.cases.model.FuncUnitCase;
import io.tiklab.kanass.test.func.cases.model.FuncUnitCaseQuery;
import io.tiklab.kanass.test.func.cases.service.FuncUnitCaseService;
import io.tiklab.kanass.test.test.model.TestCaseQuery;
import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
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
import java.util.List;

/**
 * 功能用例 控制器
 */
@RestController
@RequestMapping("/funcUnitCase")
//@Api(name = "FuncUnitCaseController",desc = "功能单元测试用例")
public class FuncUnitCaseController {

    private static Logger logger = LoggerFactory.getLogger(FuncUnitCaseController.class);

    @Autowired
    private FuncUnitCaseService funcUnitCaseService;

    @RequestMapping(path="/createFuncUnitCase",method = RequestMethod.POST)
//    @ApiMethod(name = "createFuncUnitCase",desc = "创建功能测试步骤")
//    @ApiParam(name = "funcUnitCase",desc = "funcUnitCase",required = true)
    public Result<String> createFuncUnitCase(@RequestBody @NotNull @Valid FuncUnitCase funcUnitCase){
        String id = funcUnitCaseService.createFuncUnitCase(funcUnitCase);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateFuncUnitCase",method = RequestMethod.POST)
//    @ApiMethod(name = "updateFuncUnitCase",desc = "修改功能测试步骤")
//    @ApiParam(name = "funcUnitCase",desc = "funcUnitCase",required = true)
    public Result<Void> updateFuncUnitCase(@RequestBody @NotNull @Valid FuncUnitCase funcUnitCase){
        funcUnitCaseService.updateFuncUnitCase(funcUnitCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteFuncUnitCase",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteFuncUnitCase",desc = "删除功能测试步骤")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteFuncUnitCase(@NotNull String id){
        funcUnitCaseService.deleteFuncUnitCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findFuncUnitCase",method = RequestMethod.POST)
//    @ApiMethod(name = "findFuncUnitCase",desc = "通过id查询")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<FuncUnitCase> findFuncUnitCase(@NotNull String id){
        FuncUnitCase funcUnitCase = funcUnitCaseService.findFuncUnitCase(id);

        return Result.ok(funcUnitCase);
    }

    @RequestMapping(path="/findAllFuncUnitCase",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllFuncUnitCase",desc = "查询所有")
    public Result<List<FuncUnitCase>> findAllFuncUnitCase(){
        List<FuncUnitCase> funcUnitCaseList = funcUnitCaseService.findAllFuncUnitCase();

        return Result.ok(funcUnitCaseList);
    }

    @RequestMapping(path = "/findFuncUnitCaseList",method = RequestMethod.POST)
//    @ApiMethod(name = "findFuncUnitCaseList",desc = "通过查询对象查询")
//    @ApiParam(name = "funcUnitCaseQuery",desc = "funcUnitCaseQuery",required = true)
    public Result<List<FuncUnitCase>> findFuncUnitCaseList(@RequestBody @Valid @NotNull FuncUnitCaseQuery funcUnitCaseQuery){
            List<FuncUnitCase> funcUnitCaseList = funcUnitCaseService.findFuncUnitCaseList(funcUnitCaseQuery);

        return Result.ok(funcUnitCaseList);
    }

    @RequestMapping(path = "/findFuncUnitCasePage",method = RequestMethod.POST)
//    @ApiMethod(name = "findFuncUnitCasePage",desc = "通过查询对象分页查询")
//    @ApiParam(name = "funcUnitCaseQuery",desc = "funcUnitCaseQuery",required = true)
    public Result<Pagination<FuncUnitCase>> findFuncUnitCasePage(@RequestBody @Valid @NotNull FuncUnitCaseQuery funcUnitCaseQuery){
        Pagination<FuncUnitCase> pagination = funcUnitCaseService.findFuncUnitCasePage(funcUnitCaseQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findFuncUnitCaseListByTestCase",method = RequestMethod.POST)
//    @ApiMethod(name = "findFuncUnitCaseListByTestCase",desc = "findFuncUnitCaseListByTestCase")
//    @ApiParam(name = "testCaseQuery",desc = "testCaseQuery",required = true)
    public Result<List<FuncUnitCase>> findFuncUnitCaseListByTestCase(@RequestBody @Valid @NotNull TestCaseQuery testCaseQuery ){
        List<FuncUnitCase> funcUnitCaseList = funcUnitCaseService.findFuncUnitCaseListByTestCase(testCaseQuery);

        return Result.ok(funcUnitCaseList);
    }


}
