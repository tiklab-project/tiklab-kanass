package io.tiklab.kanass.project.plan.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.kanass.project.plan.model.PlanWorkItem;
import io.tiklab.kanass.project.plan.model.PlanWorkItemQuery;
import io.tiklab.kanass.project.plan.service.PlanWorkItemService;
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
 * 计划事项关联关系控制器
 */
@RestController
@RequestMapping("/planWorkItem")
@Api(name = "PlanWorkItemController",desc = "PlanWorkItemController")
public class PlanWorkItemController {

    private static Logger logger = LoggerFactory.getLogger(PlanWorkItemController.class);

    @Autowired
    private PlanWorkItemService planWorkItemService;

    @RequestMapping(path="/createPlanWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "createPlanWorkItem",desc = "创建计划事项关联数据")
    @ApiParam(name = "planWorkItem",desc = "计划事项关联模型",required = true)
    public Result<String> createPlanWorkItem(@RequestBody @NotNull @Valid PlanWorkItem planWorkItem){
        String id = planWorkItemService.createPlanWorkItem(planWorkItem);

        return Result.ok(id);
    }

    @RequestMapping(path="/updatePlanWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "updatePlanWorkItem",desc = "更新计划数据关联关系")
    @ApiParam(name = "planWorkItem",desc = "计划事项关联模型",required = true)
    public Result<Void> updatePlanWorkItem(@RequestBody @NotNull @Valid PlanWorkItem planWorkItem){
        planWorkItemService.updatePlanWorkItem(planWorkItem);

        return Result.ok();
    }

    @RequestMapping(path="/deletePlanWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "deletePlanWorkItem",desc = "删除计划关联关系")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deletePlanWorkItem(@NotNull String id){
        planWorkItemService.deletePlanWorkItem(id);

        return Result.ok();
    }

    @RequestMapping(path="/deletePlanWorkItemCondition",method = RequestMethod.POST)
    @ApiMethod(name = "deletePlanWorkItemCondition",desc = "根据条件删除关联关系")
    @ApiParam(name = "planWorkItem",desc = "删除条件",required = true)
    public Result<Void> deletePlanWorkItemCondition(@RequestBody @NotNull PlanWorkItem planWorkItem){
        planWorkItemService.deletePlanWorkItem(planWorkItem);

        return Result.ok();
    }

    @RequestMapping(path="/findPlanWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "findPlanWorkItem",desc = "根据id查找关联数据")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<PlanWorkItem> findPlanWorkItem(@NotNull String id){
        PlanWorkItem planWorkItem = planWorkItemService.findPlanWorkItem(id);

        return Result.ok(planWorkItem);
    }

    @RequestMapping(path="/findAllPlanWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "findAllPlanWorkItem",desc = "查找所有计划事项关联关系")
    public Result<List<PlanWorkItem>> findAllPlanWorkItem(){
        List<PlanWorkItem> planWorkItemList = planWorkItemService.findAllPlanWorkItem();

        return Result.ok(planWorkItemList);
    }

    @RequestMapping(path = "/findPlanWorkItemList",method = RequestMethod.POST)
    @ApiMethod(name = "findPlanWorkItemList",desc = "根据条件计划事项查询关联列表")
    @ApiParam(name = "planWorkItemQuery",desc = "计划关联事项搜索参数模型",required = true)
    public Result<List<PlanWorkItem>> findPlanWorkItemList(@RequestBody @Valid @NotNull PlanWorkItemQuery planWorkItemQuery){
        List<PlanWorkItem> planWorkItemList = planWorkItemService.findPlanWorkItemList(planWorkItemQuery);

        return Result.ok(planWorkItemList);
    }

    @RequestMapping(path = "/findPlanWorkItemPage",method = RequestMethod.POST)
    @ApiMethod(name = "findPlanWorkItemPage",desc = "根据条件按分页查询关联关系")
    @ApiParam(name = "planWorkItemQuery",desc = "计划关联事项搜索参数模型",required = true)
    public Result<Pagination<PlanWorkItem>> findPlanWorkItemPage(@RequestBody @Valid @NotNull PlanWorkItemQuery planWorkItemQuery){
        Pagination<PlanWorkItem> pagination = planWorkItemService.findPlanWorkItemPage(planWorkItemQuery);

        return Result.ok(pagination);
    }

}
