package io.tiklab.kanass.project.plan.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.kanass.project.plan.model.Plan;
import io.tiklab.kanass.project.plan.model.PlanQuery;
import io.tiklab.kanass.project.plan.service.PlanService;
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
 * 版本控制器
 */
@RestController
@RequestMapping("/plan")
@Api(name = "PlanController",desc = "PlanController")
public class PlanController {

    private static Logger logger = LoggerFactory.getLogger(PlanController.class);

    @Autowired
    private PlanService planService;

    @RequestMapping(path="/createPlan",method = RequestMethod.POST)
    @ApiMethod(name = "createPlan",desc = "创建计划")
    @ApiParam(name = "plan",desc = "plan",required = true)
    public Result<String> createPlan(@RequestBody @NotNull @Valid Plan plan){
        String id = planService.createPlan(plan);

        return Result.ok(id);
    }

    @RequestMapping(path="/updatePlan",method = RequestMethod.POST)
    @ApiMethod(name = "updatePlan",desc = "更新计划")
    @ApiParam(name = "plan",desc = "plan",required = true)
    public Result<Void> updatePlan(@RequestBody @NotNull @Valid Plan plan){
        planService.updatePlan(plan);

        return Result.ok();
    }

    @RequestMapping(path="/deletePlan",method = RequestMethod.POST)
    @ApiMethod(name = "deletePlan",desc = "删除计划")
    @ApiParam(name = "id",desc = "计划id",required = true)
    public Result<Void> deletePlan(@NotNull String id){
        planService.deletePlan(id);

        return Result.ok();
    }

    @RequestMapping(path="/findPlan",method = RequestMethod.POST)
    @ApiMethod(name = "findPlan",desc = "根据id查找计划")
    @ApiParam(name = "id",desc = "计划id",required = true)
    public Result<Plan> findPlan(@NotNull String id){
        Plan plan = planService.findPlan(id);

        return Result.ok(plan);
    }

    @RequestMapping(path="/findAllPlan",method = RequestMethod.POST)
    @ApiMethod(name = "findAllPlan",desc = "查找所有计划")
    public Result<List<Plan>> findAllPlan(){
        List<Plan> planList = planService.findAllPlan();

        return Result.ok(planList);
    }

    @RequestMapping(path = "/findPlanList",method = RequestMethod.POST)
    @ApiMethod(name = "findPlanList",desc = "根据条件查找计划列表")
    @ApiParam(name = "planQuery",desc = "计划搜索参数模型",required = true)
    public Result<List<Plan>> findPlanList(@RequestBody @Valid @NotNull PlanQuery planQuery){
        List<Plan> planList = planService.findPlanList(planQuery);

        return Result.ok(planList);
    }

    @RequestMapping(path = "/findPlanPage",method = RequestMethod.POST)
    @ApiMethod(name = "findPlanPage",desc = "根据条件按分页查找计划列表")
    @ApiParam(name = "planQuery",desc = "计划搜索参数模型",required = true)
    public Result<Pagination<Plan>> findPlanPage(@RequestBody @Valid @NotNull PlanQuery planQuery){
        Pagination<Plan> pagination = planService.findPlanPage(planQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findPlanPageTree",method = RequestMethod.POST)
    @ApiMethod(name = "findPlanPageTree",desc = "根据条件按分页查找树形计划列表")
    @ApiParam(name = "planQuery",desc = "计划搜索参数模型",required = true)
    public Result<Pagination<Plan>> findPlanPageTree(@RequestBody @Valid @NotNull PlanQuery planQuery){
        Pagination<Plan> pagination = planService.findPlanPageTree(planQuery);

        return Result.ok(pagination);
    }
}
