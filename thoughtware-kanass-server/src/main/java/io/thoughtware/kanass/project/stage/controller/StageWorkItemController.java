package io.thoughtware.kanass.project.stage.controller;

import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import io.thoughtware.kanass.project.stage.model.StageWorkItem;
import io.thoughtware.kanass.project.stage.model.StageWorkItemQuery;
import io.thoughtware.kanass.project.stage.service.StageWorkItemService;
import io.thoughtware.kanass.workitem.model.WorkItem;
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
 * 项目阶段事项关联关系控制器
 */
@RestController
@RequestMapping("/stageWorkItem")
@Api(name = "StageWorkItemController",desc = "StageWorkItemController")
public class StageWorkItemController {

    private static Logger logger = LoggerFactory.getLogger(StageWorkItemController.class);

    @Autowired
    private StageWorkItemService stageWorkItemService;

    @RequestMapping(path="/createStageWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "createStageWorkItem",desc = "创建阶段事项关联关系")
    @ApiParam(name = "stageWorkItem",desc = "阶段事项关联关系模型",required = true)
    public Result<String> createStageWorkItem(@RequestBody @NotNull @Valid StageWorkItem stageWorkItem){
        String id = stageWorkItemService.createStageWorkItem(stageWorkItem);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateStageWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "updateStageWorkItem",desc = "更新阶段事项关联关系")
    @ApiParam(name = "stageWorkItem",desc = "阶段事项关联关系模型",required = true)
    public Result<Void> updateStageWorkItem(@RequestBody @NotNull @Valid StageWorkItem stageWorkItem){
        stageWorkItemService.updateStageWorkItem(stageWorkItem);

        return Result.ok();
    }

    @RequestMapping(path="/deleteStageWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "deleteStageWorkItem",desc = "删除关联关系")
    @ApiParam(name = "id",desc = "阶段id",required = true)
    public Result<Void> deleteStageWorkItem(@NotNull String id){
        stageWorkItemService.deleteStageWorkItem(id);

        return Result.ok();
    }

    @RequestMapping(path="/findStageWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "findStageWorkItem",desc = "根据id查找阶段事项关联")
    @ApiParam(name = "id",desc = "阶段id",required = true)
    public Result<StageWorkItem> findStageWorkItem(@NotNull String id){
        StageWorkItem stageWorkItem = stageWorkItemService.findStageWorkItem(id);

        return Result.ok(stageWorkItem);
    }

    @RequestMapping(path="/findAllStageWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "findAllStageWorkItem",desc = "查找所有阶段关联事项")
    public Result<List<StageWorkItem>> findAllStageWorkItem(){
        List<StageWorkItem> stageWorkItemList = stageWorkItemService.findAllStageWorkItem();

        return Result.ok(stageWorkItemList);
    }

    @RequestMapping(path = "/findStageWorkItemList",method = RequestMethod.POST)
    @ApiMethod(name = "findStageWorkItemList",desc = "根据条件查找阶段的关联事项")
    @ApiParam(name = "stageWorkItemQuery",desc = "阶段事项查找参数模型",required = true)
    public Result<List<StageWorkItem>> findStageWorkItemList(@RequestBody @Valid @NotNull StageWorkItemQuery stageWorkItemQuery){
        List<StageWorkItem> stageWorkItemList = stageWorkItemService.findStageWorkItemList(stageWorkItemQuery);

        return Result.ok(stageWorkItemList);
    }

    @RequestMapping(path = "/findStageWorkItemPage",method = RequestMethod.POST)
    @ApiMethod(name = "findStageWorkItemPage",desc = "按分页查询阶段事项关联列表")
    @ApiParam(name = "stageWorkItemQuery",desc = "阶段事项查找参数模型",required = true)
    public Result<Pagination<StageWorkItem>> findStageWorkItemPage(@RequestBody @Valid @NotNull StageWorkItemQuery stageWorkItemQuery){
        Pagination<StageWorkItem> pagination = stageWorkItemService.findStageWorkItemPage(stageWorkItemQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findWorkItemListByStage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkItemListByStage",desc = "根据阶段查找关联的事项id")
    @ApiParam(name = "stageWorkItemQuery",desc = "阶段事项查找参数模型",required = true)
    public Result<List<WorkItem>> findWorkItemListByStage(@RequestBody @Valid @NotNull StageWorkItemQuery stageWorkItemQuery){
        List<WorkItem> workItemList = stageWorkItemService.findWorkItemListByStage(stageWorkItemQuery);

        return Result.ok(workItemList);
    }

    @RequestMapping(path="/deleteStageWorkItemCondition",method = RequestMethod.POST)
    @ApiMethod(name = "deleteStageWorkItemCondition",desc = "根据条件删除阶段事项关联关系")
    @ApiParam(name = "stageWorkItem",desc = "删除条件",required = true)
    public Result<Void> deleteStageWorkItemCondition(@RequestBody @NotNull StageWorkItemQuery stageWorkItemQuery){
        stageWorkItemService.deleteStageWorkItem(stageWorkItemQuery);

        return Result.ok();
    }

    @RequestMapping(path="/findStageChildWorkItemAndStage",method = RequestMethod.POST)
    @ApiMethod(name = "findStageChildWorkItemAndStage",desc = "查找某个阶段下的子阶段和关联事项")
    @ApiParam(name = "stageWorkItemQuery",desc = "删除条件",required = true)
    public Result<Map<String, Object>> findStageChildWorkItemAndStage(@RequestBody @NotNull StageWorkItemQuery stageWorkItemQuery){
        Map<String, Object> stageAndWorkItem = stageWorkItemService.findStageChildWorkItemAndStage(stageWorkItemQuery);

        return Result.ok(stageAndWorkItem);
    }
    
}
