package io.tiklab.kanass.test.common.stepcommon.controller;

import io.tiklab.kanass.test.common.stepcommon.model.StepCommon;
import io.tiklab.kanass.test.common.stepcommon.model.StepCommonQuery;
import io.tiklab.kanass.test.common.stepcommon.service.StepCommonService;
import io.tiklab.core.Result;
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
 * 公共步骤 服务
 */
@RestController
@RequestMapping("/stepCommon")
//@Api(name = "StepCommonController",desc = "公共步骤管理")
public class StepCommonController {

    private static Logger logger = LoggerFactory.getLogger(StepCommonController.class);

    @Autowired
    private StepCommonService stepCommonService;

    @RequestMapping(path="/createStepCommon",method = RequestMethod.POST)
//    @ApiMethod(name = "createStepCommon",desc = "创建公共步骤")
//    @ApiParam(name = "stepCommon",desc = "stepCommon",required = true)
    public Result<String> createStepCommon(@RequestBody @NotNull @Valid StepCommon stepCommon){
        String id = stepCommonService.createStepCommon(stepCommon);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateStepCommon",method = RequestMethod.POST)
//    @ApiMethod(name = "updateStepCommon",desc = "更新公共步骤")
//    @ApiParam(name = "stepCommon",desc = "stepCommon",required = true)
    public Result<Void> updateStepCommon(@RequestBody @NotNull @Valid StepCommon stepCommon){
        stepCommonService.updateStepCommon(stepCommon);

        return Result.ok();
    }

    @RequestMapping(path="/deleteStepCommon",method = RequestMethod.POST)
//    @ApiMethod(name = "deleteStepCommon",desc = "删除公共步骤")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteStepCommon(@NotNull String id,String caseType){
        stepCommonService.deleteStepCommon(id,caseType);

        return Result.ok();
    }

    @RequestMapping(path="/findStepCommon",method = RequestMethod.POST)
//    @ApiMethod(name = "findStepCommon",desc = "根据id查找公共步骤")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<StepCommon> findStepCommon(@NotNull String id){
        StepCommon stepCommon = stepCommonService.findStepCommon(id);

        return Result.ok(stepCommon);
    }
    
    @RequestMapping(path = "/findStepCommonList",method = RequestMethod.POST)
//    @ApiMethod(name = "findStepCommonList",desc = "根据查询参数查询公共步骤列表")
//    @ApiParam(name = "stepCommonQuery",desc = "stepCommonQuery",required = true)
    public Result<List<StepCommon>> findStepCommonList(@RequestBody @Valid @NotNull StepCommonQuery stepCommonQuery){
        List<StepCommon> stepCommonList = stepCommonService.findStepCommonList(stepCommonQuery);

        return Result.ok(stepCommonList);
    }


}
