package io.tiklab.kanass.project.test.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.plan.entity.PlanEntity;
import io.tiklab.kanass.project.plan.service.PlanService;
import io.tiklab.kanass.project.test.model.*;
import io.tiklab.kanass.project.test.service.ProjectTestRepositoryPlanService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/projectTestRepositoryPlan")
@Api(name = "ProjectTestRepositoryPlanController",desc = "测试计划管理")
public class ProjectTestRepositoryPlanController {
    @Autowired
    private ProjectTestRepositoryPlanService planService;

    @RequestMapping(path="/createProjectTestRepositoryPlan",method = RequestMethod.POST)
    @ApiMethod(name = "createProjectTestRepositoryPlan",desc = "创建迭代")
    @ApiParam(name = "projectTestRepositoryPlan",desc = "迭代DTO",required = true)
    public Result<String> createProjectTestRepositoryPlan(@RequestBody @NotNull @Valid @ApiParam ProjectTestRepositoryPlan projectTestRepositoryPlan){
        String id = planService.createProjectTestRepositoryPlan(projectTestRepositoryPlan);

        return Result.ok(id);
    }

    @RequestMapping(path="/deleteProjectTestRepositoryPlan",method = RequestMethod.POST)
    @ApiMethod(name = "deleteProjectTestRepositoryPlan",desc = "创建迭代")
    @ApiParam(name = "id",desc = "迭代DTO",required = true)
    public Result<Void> deleteProjectTestRepositoryPlan(@NotNull String id){
        planService.deleteProjectTestRepositoryPlan(id);

        return Result.ok();
    }

    @RequestMapping(path="/deleteProjectTestRepositoryPlanByCondition",method = RequestMethod.POST)
    @ApiMethod(name = "deleteProjectTestRepositoryPlanByCondition",desc = "创建迭代")
    @ApiParam(name = "repositoryId",desc = "迭代DTO",required = true)
    public Result<Void> deleteProjectTestRepositoryPlanByCondition(@NotNull String repositoryId, @NotNull String planId, @NotNull String projectId){
        planService.deleteProjectTestRepositoryPlanByCondition(repositoryId, planId, projectId);

        return Result.ok();
    }


    @RequestMapping(path="/findAllProjectTestRepositoryPlanByRepositoryId",method = RequestMethod.POST)
    @ApiMethod(name = "findAllProjectTestRepositoryPlanByRepositoryId",desc = "根据仓库ID查询计划")
    @ApiParam(name = "repositoryId",desc = "仓库ID",required = true)
    public Result<List<TestPlan>> findAllProjectTestRepositoryPlanByRepositoryId(@NotNull String repositoryId){
        List<TestPlan> testPlanList = planService.findAllProjectTestRepositoryPlanByRepositoryId(repositoryId);

        return Result.ok(testPlanList);
    }

    @RequestMapping(path="/findProjectTestRepositoryPlanList",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectTestRepositoryPlanList",desc = "根据仓库ID查询计划")
    @ApiParam(name = "query",desc = "查询参数",required = true)
    public Result<List<ProjectTestRepositoryPlan>> findProjectTestRepositoryPlanList(@NotNull ProjectTestRepositoryPlanQuery query){
        List<ProjectTestRepositoryPlan> list = planService.findProjectTestRepositoryPlanList(query);

        return Result.ok(list);
    }

    @RequestMapping(path = "/findProjectTestRepositoryPlanPage",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectTestRepositoryPlanPage",desc = "根据条件按照分页查找迭代")
    @ApiParam(name = "projectTestRepositoryPlanQuery",desc = "迭代查询对象",required = true)
    public Result<Pagination<ProjectTestRepositoryPlan>> findProjectTestRepositoryPlanPage(@RequestBody @Valid @NotNull ProjectTestRepositoryPlanQuery projectTestRepositoryPlanQuery){
        Pagination<ProjectTestRepositoryPlan> pagination = planService.findProjectTestRepositoryPlanPage(projectTestRepositoryPlanQuery);

        return Result.ok(pagination);
    }
}
