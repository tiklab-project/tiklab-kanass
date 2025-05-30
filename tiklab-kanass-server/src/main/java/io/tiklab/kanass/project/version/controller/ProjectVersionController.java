package io.tiklab.kanass.project.version.controller;

import io.tiklab.kanass.project.version.model.ProjectVersion;
import io.tiklab.kanass.project.version.model.ProjectVersionQuery;
import io.tiklab.kanass.project.version.service.ProjectVersionService;
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
//@Api(name = "ProjectVersionController",desc = "版本管理")
public class ProjectVersionController {

    private static Logger logger = LoggerFactory.getLogger(ProjectVersionController.class);

    @Autowired
    private ProjectVersionService projectVersionService;

    @RequestMapping(path="/createVersion",method = RequestMethod.POST)
    //@ApiMethod(name = "createVersion",desc = "创建项目版本")
    //@ApiParam(name = "projectVersion",desc = "项目版本模型",required = true)
    public Result<String> createVersion(@RequestBody @NotNull @Valid ProjectVersion projectVersion){
        String id = projectVersionService.createVersion(projectVersion);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateVersion",method = RequestMethod.POST)
    //@ApiMethod(name = "updateVersion",desc = "更新项目版本")
    //@ApiParam(name = "projectVersion",desc = "项目版本模型",required = true)
    public Result<Void> updateVersion(@RequestBody @NotNull @Valid ProjectVersion projectVersion){
        projectVersionService.updateVersion(projectVersion);

        return Result.ok();
    }


    @RequestMapping(path="/deleteVersion",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteVersion",desc = "删除项目版本")
    //@ApiParam(name = "id",desc = "版本id",required = true)
    public Result<Void> deleteVersion(@NotNull String id){
        projectVersionService.deleteVersion(id);

        return Result.ok();
    }


    @RequestMapping(path="/findVersion",method = RequestMethod.POST)
    //@ApiMethod(name = "findVersion",desc = "根据id查找项目版本")
    //@ApiParam(name = "id",desc = "版本id",required = true)
    public Result<ProjectVersion> findVersion(@NotNull String id){
        ProjectVersion projectVersion = projectVersionService.findVersion(id);

        return Result.ok(projectVersion);
    }

    @RequestMapping(path="/findAllVersion",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllVersion",desc = "查找所有项目版本")
    public Result<List<ProjectVersion>> findAllVersion(){
        List<ProjectVersion> projectVersionList = projectVersionService.findAllVersion();

        return Result.ok(projectVersionList);
    }


    @RequestMapping(path = "/findVersionList",method = RequestMethod.POST)
    //@ApiMethod(name = "findVersionList",desc = "根据条件查找项目类型列表")
    //@ApiParam(name = "projectVersionQuery",desc = "项目版本搜索模型",required = true)
    public Result<List<ProjectVersion>> findVersionList(@RequestBody @Valid @NotNull ProjectVersionQuery projectVersionQuery){
        List<ProjectVersion> projectVersionList = projectVersionService.findVersionList(projectVersionQuery);

        return Result.ok(projectVersionList);
    }


    @RequestMapping(path = "/findVersionPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findVersionPage",desc = "根据条件按分页查询项目类型列表")
    //@ApiParam(name = "projectVersionQuery",desc = "项目版本搜索模型",required = true)
    public Result<Pagination<ProjectVersion>> findVersionPage(@RequestBody @Valid @NotNull ProjectVersionQuery projectVersionQuery){
        Pagination<ProjectVersion> pagination = projectVersionService.findVersionPage(projectVersionQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findVersionFocusList",method = RequestMethod.POST)
    //@ApiMethod(name = "findVersionFocusList",desc = "根据条件按分页查询项目类型列表")
    //@ApiParam(name = "projectVersionQuery",desc = "项目版本搜索模型",required = true)
    public Result<List<ProjectVersion>> findVersionFocusList(@RequestBody @Valid @NotNull ProjectVersionQuery projectVersionQuery){
        List<ProjectVersion> versionFocusList = projectVersionService.findVersionFocusList(projectVersionQuery);

        return Result.ok(versionFocusList);
    }

    @RequestMapping(path = "/findSelectVersionList",method = RequestMethod.POST)
    //@ApiMethod(name = "findSelectVersionList",desc = "根据条件查找迭代列表")
    //@ApiParam(name = "projectVersionQuery",desc = "迭代查询对象",required = true)
    public Result<List<ProjectVersion>> findSelectVersionList(@RequestBody @Valid @NotNull ProjectVersionQuery projectVersionQuery){
        List<ProjectVersion> versionList = projectVersionService.findSelectVersionList(projectVersionQuery);

        return Result.ok(versionList);
    }

    @RequestMapping(path = "/findWorkVersionList",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkVersionList",desc = "查询事项关联的所有版本")
    //@ApiParam(name = "workId",desc = "迭代查询对象",required = true)
    public Result<List<ProjectVersion>> findWorkVersionList(@NotNull String workId){
        List<ProjectVersion> versionList = projectVersionService.findWorkVersionList(workId);
        return Result.ok(versionList);
    }

}
