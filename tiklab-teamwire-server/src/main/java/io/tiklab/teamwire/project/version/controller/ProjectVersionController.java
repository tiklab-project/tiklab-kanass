package io.tiklab.teamwire.project.version.controller;

import io.tiklab.teamwire.project.version.model.ProjectVersion;
import io.tiklab.teamwire.project.version.model.ProjectVersionQuery;
import io.tiklab.teamwire.project.version.service.ProjectVersionService;
import io.tiklab.postin.annotation.Api;
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
 * 项目版本控制器
 */
@RestController
@RequestMapping("/projectVersion")
@Api(name = "ProjectVersionController",desc = "版本管理")
public class ProjectVersionController {

    private static Logger logger = LoggerFactory.getLogger(ProjectVersionController.class);

    @Autowired
    private ProjectVersionService projectVersionService;

    @RequestMapping(path="/createVersion",method = RequestMethod.POST)
    @ApiMethod(name = "createVersion",desc = "创建项目版本")
    @ApiParam(name = "projectVersion",desc = "项目版本模型",required = true)
    public Result<String> createVersion(@RequestBody @NotNull @Valid ProjectVersion projectVersion){
        String id = projectVersionService.createVersion(projectVersion);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateVersion",method = RequestMethod.POST)
    @ApiMethod(name = "updateVersion",desc = "更新项目版本")
    @ApiParam(name = "projectVersion",desc = "项目版本模型",required = true)
    public Result<Void> updateVersion(@RequestBody @NotNull @Valid ProjectVersion projectVersion){
        projectVersionService.updateVersion(projectVersion);

        return Result.ok();
    }


    @RequestMapping(path="/deleteVersion",method = RequestMethod.POST)
    @ApiMethod(name = "deleteVersion",desc = "删除项目版本")
    @ApiParam(name = "id",desc = "版本id",required = true)
    public Result<Void> deleteVersion(@NotNull String id){
        projectVersionService.deleteVersion(id);

        return Result.ok();
    }


    @RequestMapping(path="/findVersion",method = RequestMethod.POST)
    @ApiMethod(name = "findVersion",desc = "根据id查找项目版本")
    @ApiParam(name = "id",desc = "版本id",required = true)
    public Result<ProjectVersion> findVersion(@NotNull String id){
        ProjectVersion projectVersion = projectVersionService.findVersion(id);

        return Result.ok(projectVersion);
    }

    @RequestMapping(path="/findAllVersion",method = RequestMethod.POST)
    @ApiMethod(name = "findAllVersion",desc = "查找所有项目版本")
    public Result<List<ProjectVersion>> findAllVersion(){
        List<ProjectVersion> projectVersionList = projectVersionService.findAllVersion();

        return Result.ok(projectVersionList);
    }


    @RequestMapping(path = "/findVersionList",method = RequestMethod.POST)
    @ApiMethod(name = "findVersionList",desc = "根据条件查找项目类型列表")
    @ApiParam(name = "ProjectVersionQuery",desc = "项目版本搜索模型",required = true)
    public Result<List<ProjectVersion>> findVersionList(@RequestBody @Valid @NotNull ProjectVersionQuery ProjectVersionQuery){
        List<ProjectVersion> projectVersionList = projectVersionService.findVersionList(ProjectVersionQuery);

        return Result.ok(projectVersionList);
    }


    @RequestMapping(path = "/findVersionPage",method = RequestMethod.POST)
    @ApiMethod(name = "findVersionPage",desc = "根据条件按分页查询项目类型列表")
    @ApiParam(name = "ProjectVersionQuery",desc = "项目版本搜索模型",required = true)
    public Result<Pagination<ProjectVersion>> findVersionPage(@RequestBody @Valid @NotNull ProjectVersionQuery ProjectVersionQuery){
        Pagination<ProjectVersion> pagination = projectVersionService.findVersionPage(ProjectVersionQuery);

        return Result.ok(pagination);
    }

}
