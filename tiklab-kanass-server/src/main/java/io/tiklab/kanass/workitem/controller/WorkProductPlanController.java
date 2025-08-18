package io.tiklab.kanass.workitem.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jdbc.support.sort.processor.SortProcessor;
import io.tiklab.kanass.workitem.model.WorkProductPlan;
import io.tiklab.kanass.workitem.model.WorkProductPlanQuery;
import io.tiklab.kanass.workitem.service.WorkProductPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/workProductPlan")
public class WorkProductPlanController {
    @Autowired
    private WorkProductPlanService workProductPlanService;

    @Autowired
    SortProcessor sortService;

    @RequestMapping(path="/createWorkProductPlan",method = RequestMethod.POST)
    //@ApiMethod(name = "createWorkProductPlan",desc = "创建事项状态")
    //@ApiParam(name = "workProductPlan",desc = "事项状态DTO",required = true)
    public Result<String> createWorkProductPlan(@RequestBody @NotNull @Valid WorkProductPlan workProductPlan){
        String id = workProductPlanService.createWorkProductPlan(workProductPlan);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkProductPlan",method = RequestMethod.POST)
    //@ApiMethod(name = "updateWorkProductPlan",desc = "更新事项状态")
    //@ApiParam(name = "workProductPlan",desc = "事项状态DTO",required = true)
    public Result<Void> updateWorkProductPlan(@RequestBody @NotNull @Valid WorkProductPlan workProductPlan){
        workProductPlanService.updateWorkProductPlan(workProductPlan);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkProductPlan",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteWorkProductPlan",desc = "根据状态ID删除事项状态")
    //@ApiParam(name = "id",desc = "事项状态ID",required = true)
    public Result<Void> deleteWorkProductPlan(@NotNull String id){
        workProductPlanService.deleteWorkProductPlan(id);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkProductPlanByQuery",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteWorkProductPlan",desc = "根据状态ID删除事项状态")
    //@ApiParam(name = "id",desc = "事项状态ID",required = true)
    public Result<Void> deleteWorkProductPlan(@RequestBody WorkProductPlanQuery workProductPlanQuery){
        workProductPlanService.deleteWorkProductPlan(workProductPlanQuery);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkProductPlan",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkProductPlan",desc = "根据事项状态ID查找事项状态")
    //@ApiParam(name = "id",desc = "事项状态ID",required = true)
    public Result<WorkProductPlan> findWorkProductPlan(@NotNull String id){
        WorkProductPlan workProductPlan = workProductPlanService.findWorkProductPlan(id);

        return Result.ok(workProductPlan);
    }

    @RequestMapping(path="/findAllWorkProductPlan",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllWorkProductPlan",desc = "查找所有事项状态列表")
    public Result<List<WorkProductPlan>> findAllWorkProductPlan(){
        List<WorkProductPlan> workProductPlanList = workProductPlanService.findAllWorkProductPlan();

        return Result.ok(workProductPlanList);
    }


    @RequestMapping(path = "/findWorkProductPlanList",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkProductPlanList",desc = "根据查询对象查找事项状态列表")
    //@ApiParam(name = "workProductPlanQuery",desc = "查询对象",required = true)
    public Result<List<WorkProductPlan>> findWorkProductPlanList(@RequestBody @Valid @NotNull WorkProductPlanQuery workProductPlanQuery){
        List<WorkProductPlan> workProductPlanList = workProductPlanService.findWorkProductPlanList(workProductPlanQuery);

        return Result.ok(workProductPlanList);
    }

    @RequestMapping(path = "/findWorkProductPlanPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkProductPlanPage",desc = "根据查询对象按分页查找事项状态列表")
    //@ApiParam(name = "workProductPlanQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkProductPlan>> findWorkProductPlanPage(@RequestBody @Valid @NotNull WorkProductPlanQuery workProductPlanQuery){
        Pagination<WorkProductPlan> pagination = workProductPlanService.findWorkProductPlanPage(workProductPlanQuery);

        return Result.ok(pagination);
    }
}
