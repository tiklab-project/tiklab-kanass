package io.thoughtware.kanass.projectset.controller;

import io.thoughtware.kanass.project.project.model.Project;
import io.thoughtware.kanass.project.project.model.ProjectQuery;
import io.thoughtware.kanass.projectset.model.ProjectSet;
import io.thoughtware.kanass.projectset.model.ProjectSetQuery;
import io.thoughtware.kanass.projectset.service.ProjectSetService;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
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
 * 项目集控制器
 */
@RestController
@RequestMapping("/projectSet")
@Api(name = "ProjectSetController",desc = "项目集管理")
public class ProjectSetController {

    private static Logger logger = LoggerFactory.getLogger(ProjectSetController.class);

    @Autowired
    private ProjectSetService projectSetService;

    @RequestMapping(path="/createProjectSet",method = RequestMethod.POST)
    @ApiMethod(name = "createProjectSet",desc = "创建项目集")
    @ApiParam(name = "projectSet",desc = "项目集对象",required = true)
    public Result<String> createProjectSet(@RequestBody @NotNull @Valid ProjectSet projectSet){
        String id = projectSetService.createProjectSet(projectSet);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateProjectSet",method = RequestMethod.POST)
    @ApiMethod(name = "updateProjectSet",desc = "更新项目集")
    @ApiParam(name = "projectSet",desc = "项目集对象",required = true)
    public Result<Void> updateProjectSet(@RequestBody @NotNull @Valid ProjectSet projectSet){
        projectSetService.updateProjectSet(projectSet);

        return Result.ok();
    }


    @RequestMapping(path="/deleteProjectSet",method = RequestMethod.POST)
    @ApiMethod(name = "deleteProjectSet",desc = "删除项目集")
    @ApiParam(name = "id",desc = "项目集id",required = true)
    public Result<Void> deleteProjectSet(@NotNull String id){
        projectSetService.deleteProjectSet(id);

        return Result.ok();
    }


    @RequestMapping(path="/findProjectSet",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectSet",desc = "通过id查询项目集")
    @ApiParam(name = "id",desc = "项目集id",required = true)
    public Result<ProjectSet> findProjectSet(@NotNull String id){
        ProjectSet projectSet = projectSetService.findProjectSet(id);

        return Result.ok(projectSet);
    }

    @RequestMapping(path="/findAllProjectSet",method = RequestMethod.POST)
    @ApiMethod(name = "findAllProjectSet",desc = "查询所有项目集")
    public Result<List<ProjectSet>> findAllProjectSet(){
        List<ProjectSet> projectSetList = projectSetService.findAllProjectSet();

        return Result.ok(projectSetList);
    }


    @RequestMapping(path = "/findProjectSetList",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectSetList",desc = "通过查询对象查询项目集")
    @ApiParam(name = "projectSetQuery",desc = "项目集查询对象",required = true)
    public Result<List<ProjectSet>> findProjectSetList(@RequestBody @Valid @NotNull ProjectSetQuery projectSetQuery){
        List<ProjectSet> projectSetList = projectSetService.findProjectSetList(projectSetQuery);

        return Result.ok(projectSetList);
    }


    @RequestMapping(path = "/findFocusProjectSetList",method = RequestMethod.POST)
    @ApiMethod(name = "findFocusProjectSetList",desc = "根据条件查找关注的项目集列表")
    @ApiParam(name = "projectSetQuery",desc = "项目集查询参数模型",required = true)
    public Result<List<ProjectSet>> findFocusProjectSetList(@RequestBody @Valid @NotNull ProjectSetQuery projectSetQuery){

        List<ProjectSet> pagination = projectSetService.findFocusProjectSetList(projectSetQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findRecentProjectSetList",method = RequestMethod.POST)
    @ApiMethod(name = "findRecentProjectSetList",desc = "查找最近查看的项目集列表")
    @ApiParam(name = "projectSetQuery",desc = "项目集查询参数模型",required = true)
    public Result<List<ProjectSet>> findRecentProjectSetList(@RequestBody @Valid @NotNull ProjectSetQuery projectSetQuery){

        List<ProjectSet> pagination = projectSetService.findRecentProjectSetList(projectSetQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findProjectList",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectList",desc = "查询项目集下面的项目列表")
    @ApiParam(name = "projectQuery",desc = "项目查询对象",required = true)
    public Result<Project> findProjectSetDetailList(@RequestBody @Valid @NotNull ProjectQuery projectQuery){
        List<Project> projectList = projectSetService.findProjectSetDetailList(projectQuery);

        return Result.ok(projectList);
    }

    @RequestMapping(path = "/findProjectIsOrNotRe",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectIsOrNotRe",desc = "查询所有关联项目集项目和未关联项目")
    public Result<Map> findProjectIsOrNotRe(){
        Map projectIsOrNotRe = projectSetService.findProjectIsOrNotRe();

        return Result.ok(projectIsOrNotRe);
    }


    @RequestMapping(path = "/addRelevance",method = RequestMethod.POST)
    @ApiMethod(name = "addRelevance",desc = "添加关联项目")
    @ApiParam(name = "projectSet",desc = "关联对象",required = true)
    public Result addRelevance(@RequestBody @NotNull @Valid ProjectSet projectSet){
          projectSetService.addRelevance(projectSet);
        return Result.ok();
    }

    @RequestMapping(path = "/findJoinProjectSetList",method = RequestMethod.POST)
    @ApiMethod(name = "findJoinProjectSetList",desc = "查找用户能查看的所有项目集")
    @ApiParam(name = "projectSetQuery",desc = "查询参数",required = true)
    public Result<List<ProjectSet>> findJoinProjectSetList(@RequestBody @NotNull @Valid ProjectSetQuery projectSetQuery){
        List<ProjectSet> joinProjectSetList = projectSetService.findJoinProjectSetList(projectSetQuery);
        return Result.ok(joinProjectSetList);
    }

    @RequestMapping(path = "/findProjectSetSortRecentTime",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectSetSortRecentTime",desc = "查找用户能查看的所有项目集")
    @ApiParam(name = "projectSetQuery",desc = "查询参数",required = true)
    public Result<List<ProjectSet>> findProjectSetSortRecentTime(@RequestBody @NotNull @Valid ProjectSetQuery projectSetQuery){
        List<ProjectSet> joinProjectSetList = projectSetService.findProjectSetSortRecentTime(projectSetQuery);
        return Result.ok(joinProjectSetList);
    }
}
