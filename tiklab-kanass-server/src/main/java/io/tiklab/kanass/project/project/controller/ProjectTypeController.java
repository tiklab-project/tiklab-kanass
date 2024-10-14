package io.tiklab.kanass.project.project.controller;

import io.tiklab.kanass.project.project.model.ProjectType;
import io.tiklab.kanass.project.project.model.ProjectTypeQuery;
import io.tiklab.kanass.project.project.service.ProjectTypeService;
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
 * 项目类型控制器
 */
@RestController
@RequestMapping("/projectType")
@Api(name = "ProjectTypeController",desc = "项目类型管理")
public class ProjectTypeController {

    private static Logger logger = LoggerFactory.getLogger(ProjectTypeController.class);

    @Autowired
    private ProjectTypeService projectTypeService;

    @RequestMapping(path="/createProjectType",method = RequestMethod.POST)
    @ApiMethod(name = "createProjectType",desc = "创建项目类型")
    @ApiParam(name = "projectType",desc = "项目类型模型",required = true)
    public Result<String> createProjectType(@RequestBody @NotNull @Valid ProjectType projectType){
        String id = projectTypeService.createProjectType(projectType);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateProjectType",method = RequestMethod.POST)
    @ApiMethod(name = "updateProjectType",desc = "更新项目类型")
    @ApiParam(name = "projectType",desc = "项目类型模型",required = true)
    public Result<Void> updateProjectType(@RequestBody @NotNull @Valid ProjectType projectType){
        projectTypeService.updateProjectType(projectType);

        return Result.ok();
    }

    @RequestMapping(path="/deleteProjectType",method = RequestMethod.POST)
    @ApiMethod(name = "deleteProjectType",desc = "删除项目类型")
    @ApiParam(name = "id",desc = "项目类型id",required = true)
    public Result<String> deleteProjectType(@NotNull String id){
        projectTypeService.deleteProjectType(id);

        return Result.ok();
    }

    @RequestMapping(path="/findProjectType",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectType",desc = "根据id查找项目类型")
    @ApiParam(name = "id",desc = "项目类型id",required = true)
    public Result<ProjectType> findProjectType(@NotNull String id){
        ProjectType projectType = projectTypeService.findProjectType(id);

        return Result.ok(projectType);
    }

    @RequestMapping(path="/findAllProjectType",method = RequestMethod.POST)
    @ApiMethod(name = "findAllProjectType",desc = "查找所有项目类型")
    public Result<List<ProjectType>> findAllProjectType(){
        List<ProjectType> projectTypeList = projectTypeService.findAllProjectType();

        return Result.ok(projectTypeList);
    }


    @RequestMapping(path = "/findProjectTypeList",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectTypeList",desc = "根据条件查询项目类型列表")
    @ApiParam(name = "projectTypeQuery",desc = "项目类型搜索模型",required = true)
    public Result<List<ProjectType>> findProjectTypeList(@RequestBody @Valid @NotNull ProjectTypeQuery projectTypeQuery){
        List<ProjectType> projectTypeList = projectTypeService.findProjectTypeList(projectTypeQuery);

        return Result.ok(projectTypeList);
    }


    @RequestMapping(path = "/findProjectTypePage",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectTypePage",desc = "根据条件按分页查询项目类型")
    @ApiParam(name = "projectTypeQuery",desc = "项目类型搜索模型",required = true)
    public Result<Pagination<ProjectType>> findProjectTypePage(@RequestBody @Valid @NotNull ProjectTypeQuery projectTypeQuery){
        Pagination<ProjectType> pagination = projectTypeService.findProjectTypePage(projectTypeQuery);

        return Result.ok(pagination);
    }

}
