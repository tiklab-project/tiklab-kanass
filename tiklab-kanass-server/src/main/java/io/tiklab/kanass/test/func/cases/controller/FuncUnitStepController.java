package io.tiklab.kanass.test.func.cases.controller;

import io.tiklab.kanass.test.func.cases.model.FuncUnitStep;
import io.tiklab.kanass.test.func.cases.model.FuncUnitStepQuery;
import io.tiklab.kanass.test.func.cases.service.FuncUnitStepService;
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
 * 功能用例下步骤 控制器
 */
@RestController
@RequestMapping("/funcUnitStep")
//@Api(name = "FuncUnitStepController",desc = "功能单元测试用例")
public class FuncUnitStepController {

    private static Logger logger = LoggerFactory.getLogger(FuncUnitStepController.class);

    @Autowired
    private FuncUnitStepService funcUnitStepService;

    @RequestMapping(path="/createFuncUnitStep",method = RequestMethod.POST)
//    @ApiMethod(name = "createFuncUnitStep",desc = "创建功能测试步骤")
//    @ApiParam(name = "funcUnitStep",desc = "funcUnitStep",required = true)
    public Result<String> createFuncUnitStep(@RequestBody @NotNull @Valid FuncUnitStep funcUnitStep){
        String id = funcUnitStepService.createFuncUnitStep(funcUnitStep);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateFuncUnitStep",method = RequestMethod.POST)
//    @ApiMethod(name = "updateFuncUnitStep",desc = "修改功能测试步骤")
//    @ApiParam(name = "funcUnitStep",desc = "funcUnitStep",required = true)
    public Result<Void> updateFuncUnitStep(@RequestBody @NotNull @Valid FuncUnitStep funcUnitStep){
        funcUnitStepService.updateFuncUnitStep(funcUnitStep);

        return Result.ok();
    }

    @RequestMapping(path="/deleteFuncUnitStep",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteFuncUnitStep",desc = "删除功能测试步骤")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteFuncUnitStep(@NotNull String id){
        funcUnitStepService.deleteFuncUnitStep(id);

        return Result.ok();
    }

    @RequestMapping(path="/findFuncUnitStep",method = RequestMethod.POST)
//    @ApiMethod(name = "findFuncUnitStep",desc = "通过id查询")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<FuncUnitStep> findFuncUnitStep(@NotNull String id){
        FuncUnitStep funcUnitStep = funcUnitStepService.findFuncUnitStep(id);

        return Result.ok(funcUnitStep);
    }

    @RequestMapping(path="/findAllFuncUnitStep",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllFuncUnitStep",desc = "查询所有")
    public Result<List<FuncUnitStep>> findAllFuncUnitStep(){
        List<FuncUnitStep> funcUnitStepList = funcUnitStepService.findAllFuncUnitStep();

        return Result.ok(funcUnitStepList);
    }

    @RequestMapping(path = "/findFuncUnitStepList",method = RequestMethod.POST)
//    @ApiMethod(name = "findFuncUnitStepList",desc = "通过查询对象查询")
//    @ApiParam(name = "funcUnitStepQuery",desc = "funcUnitStepQuery",required = true)
    public Result<List<FuncUnitStep>> findFuncUnitStepList(@RequestBody @Valid @NotNull FuncUnitStepQuery funcUnitStepQuery){
            List<FuncUnitStep> funcUnitStepList = funcUnitStepService.findFuncUnitStepList(funcUnitStepQuery);

        return Result.ok(funcUnitStepList);
    }

    @RequestMapping(path = "/findFuncUnitStepPage",method = RequestMethod.POST)
//    @ApiMethod(name = "findFuncUnitStepPage",desc = "通过查询对象分页查询")
//    @ApiParam(name = "funcUnitStepQuery",desc = "funcUnitStepQuery",required = true)
    public Result<Pagination<FuncUnitStep>> findFuncUnitStepPage(@RequestBody @Valid @NotNull FuncUnitStepQuery funcUnitStepQuery){
        Pagination<FuncUnitStep> pagination = funcUnitStepService.findFuncUnitStepPage(funcUnitStepQuery);

        return Result.ok(pagination);
    }

}
