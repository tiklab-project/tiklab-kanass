package io.thoughtware.kanass.project.workPrivilege.controller;

import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.kanass.project.workPrivilege.model.ProjectVrole;
import io.thoughtware.kanass.project.workPrivilege.model.ProjectVroleQuery;
import io.thoughtware.kanass.project.workPrivilege.service.ProjectVroleService;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
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
 * 优先级控制器
 */
@RestController
@RequestMapping("/projectVrole")
@Api(name = "ProjectVroleController",desc = "事项优先级管理")
public class ProjectVroleController {

    private static Logger logger = LoggerFactory.getLogger(ProjectVroleController.class);

    @Autowired
    private ProjectVroleService projectVroleService;

    @RequestMapping(path="/createProjectVrole",method = RequestMethod.POST)
    @ApiMethod(name = "createProjectVrole",desc = "创建优先级")
    @ApiParam(name = "projectVrole",desc = "优先级DTO",required = true)
    public Result<String> createProjectVrole(@RequestBody @NotNull @Valid ProjectVrole projectVrole){
        String id = projectVroleService.createProjectVrole(projectVrole);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateProjectVrole",method = RequestMethod.POST)
    @ApiMethod(name = "updateProjectVrole",desc = "更新优先级")
    @ApiParam(name = "projectVrole",desc = "优先级DTO",required = true)
    public Result<Void> updateProjectVrole(@RequestBody @NotNull @Valid ProjectVrole projectVrole){
        projectVroleService.updateProjectVrole(projectVrole);

        return Result.ok();
    }

    @RequestMapping(path="/deleteProjectVrole",method = RequestMethod.POST)
    @ApiMethod(name = "deleteProjectVrole",desc = "根据优先级ID删除优先级")
    @ApiParam(name = "id",desc = "优先级ID",required = true)
    public Result<Void> deleteProjectVrole(@NotNull String id){
        projectVroleService.deleteProjectVrole(id);

        return Result.ok();
    }

    @RequestMapping(path="/findProjectVrole",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectVrole",desc = "根据优先级ID查找优先级")
    @ApiParam(name = "id",desc = "优先级ID",required = true)
    public Result<ProjectVrole> findProjectVrole(@NotNull String id){
        ProjectVrole projectVrole = projectVroleService.findProjectVrole(id);

        return Result.ok(projectVrole);
    }

    @RequestMapping(path="/findAllProjectVrole",method = RequestMethod.POST)
    @ApiMethod(name = "findAllProjectVrole",desc = "查找所有优先级")
    public Result<List<ProjectVrole>> findAllProjectVrole(){
        List<ProjectVrole> projectVroleList = projectVroleService.findAllProjectVrole();

        return Result.ok(projectVroleList);
    }


    @RequestMapping(path = "/findProjectVroleList",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectVroleList",desc = "根据查询对象查找优先级列表")
    @ApiParam(name = "projectVroleQuery",desc = "查询对象",required = true)
    public Result<List<ProjectVrole>> findProjectVroleList(@RequestBody @Valid @NotNull ProjectVroleQuery projectVroleQuery){
        List<ProjectVrole> projectVroleList = projectVroleService.findProjectVroleList(projectVroleQuery);

        return Result.ok(projectVroleList);
    }


    @RequestMapping(path = "/findProjectVrolePage",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectVrolePage",desc = "根据查询对象按分页查询优先级列表")
    @ApiParam(name = "projectVroleQuery",desc = "查询对象",required = true)
    public Result<Pagination<ProjectVrole>> findProjectVrolePage(@RequestBody @Valid @NotNull ProjectVroleQuery projectVroleQuery){
        Pagination<ProjectVrole> pagination = projectVroleService.findProjectVrolePage(projectVroleQuery);

        return Result.ok(pagination);
    }

}
