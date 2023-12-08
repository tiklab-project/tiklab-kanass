package io.thoughtware.kanass.project.stage.controller;

import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import io.thoughtware.kanass.project.stage.model.Stage;
import io.thoughtware.kanass.project.stage.model.StageQuery;
import io.thoughtware.kanass.project.stage.service.StageService;
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
 * 项目阶段控制器
 */
@RestController
@RequestMapping("/stage")
@Api(name = "StageController",desc = "StageController")
public class StageController {

    private static Logger logger = LoggerFactory.getLogger(StageController.class);

    @Autowired
    private StageService stageService;

    @RequestMapping(path="/createStage",method = RequestMethod.POST)
    @ApiMethod(name = "createStage",desc = "创建阶段")
    @ApiParam(name = "stage",desc = "项目阶段模型",required = true)
    public Result<String> createStage(@RequestBody @NotNull @Valid Stage stage){
        String id = stageService.createStage(stage);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateStage",method = RequestMethod.POST)
    @ApiMethod(name = "updateStage",desc = "更新阶段")
    @ApiParam(name = "stage",desc = "项目阶段模型",required = true)
    public Result<Void> updateStage(@RequestBody @NotNull @Valid Stage stage){
        stageService.updateStage(stage);

        return Result.ok();
    }

    @RequestMapping(path="/deleteStage",method = RequestMethod.POST)
    @ApiMethod(name = "deleteStage",desc = "根据id删除阶段")
    @ApiParam(name = "id",desc = "阶段id",required = true)
    public Result<Void> deleteStage(@NotNull String id){
        stageService.deleteStage(id);

        return Result.ok();
    }

    @RequestMapping(path="/findStage",method = RequestMethod.POST)
    @ApiMethod(name = "findStage",desc = "根据id查找项目阶段")
    @ApiParam(name = "id",desc = "阶段id",required = true)
    public Result<Stage> findStage(@NotNull String id){
        Stage stage = stageService.findStage(id);

        return Result.ok(stage);
    }

    @RequestMapping(path="/findAllStage",method = RequestMethod.POST)
    @ApiMethod(name = "findAllStage",desc = "查找所有阶段")
    public Result<List<Stage>> findAllStage(){
        List<Stage> stageList = stageService.findAllStage();

        return Result.ok(stageList);
    }

    @RequestMapping(path = "/findStageList",method = RequestMethod.POST)
    @ApiMethod(name = "findStageList",desc = "根据条件查找阶段列表")
    @ApiParam(name = "stageQuery",desc = "项目阶段查找参数模型",required = true)
    public Result<List<Stage>> findStageList(@RequestBody @Valid @NotNull StageQuery stageQuery){
        List<Stage> stageList = stageService.findStageList(stageQuery);

        return Result.ok(stageList);
    }

    @RequestMapping(path = "/findStagePage",method = RequestMethod.POST)
    @ApiMethod(name = "findStagePage",desc = "根据条件按分页查询阶段列表")
    @ApiParam(name = "stageQuery",desc = "项目阶段查找参数模型",required = true)
    public Result<Pagination<Stage>> findStagePage(@RequestBody @Valid @NotNull StageQuery stageQuery){
        Pagination<Stage> pagination = stageService.findStagePage(stageQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findStageListTree",method = RequestMethod.POST)
    @ApiMethod(name = "findStageListTree",desc = "根据条件查找阶段树形列表")
    @ApiParam(name = "stageQuery",desc = "项目阶段查找参数模型",required = true)
    public Result<List<Stage>> findStageListTree(@RequestBody @Valid @NotNull StageQuery stageQuery){
        List<Stage> stageList = stageService.findStageListTree(stageQuery);

        return Result.ok(stageList);
    }


}
