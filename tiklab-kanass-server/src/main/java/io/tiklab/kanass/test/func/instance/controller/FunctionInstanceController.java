package io.tiklab.kanass.test.func.instance.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.kanass.test.func.instance.model.FunctionInstance;
import io.tiklab.kanass.test.func.instance.model.FunctionInstanceQuery;
import io.tiklab.kanass.test.func.instance.service.FunctionInstanceService;
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
 * 功能用例历史实例 控制器
 */
@RestController
@RequestMapping("/functionInstance")
//@Api(name = "FunctionInstanceController",desc = "功能用例实例管理")
public class FunctionInstanceController {

    private static Logger logger = LoggerFactory.getLogger(FunctionInstanceController.class);

    @Autowired
    private FunctionInstanceService functionInstanceService;

    @RequestMapping(path="/createFunctionInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "createFunctionInstance",desc = "创建功能用例历史实例")
//    @ApiParam(name = "functionInstance",desc = "functionInstance",required = true)
    public Result<String> createFunctionInstance(@RequestBody @NotNull @Valid FunctionInstance functionInstance){
        String id = functionInstanceService.createFunctionInstance(functionInstance);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateFunctionInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "updateFunctionInstance",desc = "更新功能用例历史实例")
//    @ApiParam(name = "functionInstance",desc = "functionInstance",required = true)
    public Result<Void> updateFunctionInstance(@RequestBody @NotNull @Valid FunctionInstance functionInstance){
        functionInstanceService.updateFunctionInstance(functionInstance);

        return Result.ok();
    }

    @RequestMapping(path="/deleteFunctionInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteFunctionInstance",desc = "删除功能用例历史实例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteFunctionInstance(@NotNull String id){
        functionInstanceService.deleteFunctionInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findFunctionInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "findFunctionInstance",desc = "通过id查找功能用例历史实例")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<FunctionInstance> findFunctionInstance(@NotNull String id){
        FunctionInstance functionInstance = functionInstanceService.findFunctionInstance(id);

        return Result.ok(functionInstance);
    }

    @RequestMapping(path="/findAllFunctionInstance",method = RequestMethod.POST)
//    @ApiMethod(name = "findAllFunctionInstance",desc = "查找所有功能用例历史实例")
    public Result<List<FunctionInstance>> findAllFunctionInstance(){
        List<FunctionInstance> allFunctionInstance = functionInstanceService.findAllFunctionInstance();

        return Result.ok(allFunctionInstance);
    }

    @RequestMapping(path = "/findFunctionInstanceList",method = RequestMethod.POST)
//    @ApiMethod(name = "findFunctionInstanceList",desc = "根据查询参数查询功能用例历史实例列表")
//    @ApiParam(name = "functionInstanceQuery",desc = "functionInstanceQuery",required = true)
    public Result<List<FunctionInstance>> findFunctionInstanceList(@RequestBody @Valid @NotNull FunctionInstanceQuery functionInstanceQuery){
        List<FunctionInstance> functionInstanceList = functionInstanceService.findFunctionInstanceList(functionInstanceQuery);

        return Result.ok(functionInstanceList);
    }

    @RequestMapping(path = "/findFunctionInstancePage",method = RequestMethod.POST)
//    @ApiMethod(name = "findFunctionInstancePage",desc = "根据查询按分页查询功能用例历史实例")
//    @ApiParam(name = "functionInstanceQuery",desc = "functionInstanceQuery",required = true)
    public Result<Pagination<FunctionInstance>> findFunctionInstancePage(@RequestBody @Valid @NotNull FunctionInstanceQuery functionInstanceQuery){
        Pagination<FunctionInstance> pagination = functionInstanceService.findFunctionInstancePage(functionInstanceQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path="/setFunctionResult",method = RequestMethod.POST)
//    @ApiMethod(name = "setFunctionResult",desc = "设置功能用例历史实例结果")
//    @ApiParam(name = "functionInstance",desc = "functionInstance",required = true)
    public Result<String> setFunctionResult(@RequestBody @NotNull @Valid FunctionInstance functionInstance){
        String result = functionInstanceService.setFunctionResult(functionInstance);

        return Result.ok(result);
    }




}
