package io.thoughtware.kanass.projectset.controller;

import io.thoughtware.kanass.projectset.model.ProjectSetFocus;
import io.thoughtware.kanass.projectset.model.ProjectSetFocusQuery;
import io.thoughtware.kanass.projectset.service.ProjectSetFocusService;
import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.annotation.Api;
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

/**
 * 收藏的项目集控制器
 */
@RestController
@RequestMapping("/projectSetFocus")
@Api(name = "ProjectSetFocusController",desc = "ProjectSetFocusController")
public class ProjectSetFocusController {

    private static Logger logger = LoggerFactory.getLogger(ProjectSetFocusController.class);

    @Autowired
    private ProjectSetFocusService projectSetFocusService;

    @RequestMapping(path="/createProjectSetFocus",method = RequestMethod.POST)
    @ApiMethod(name = "createProjectSetFocus",desc = "创建收藏的项目集")
    @ApiParam(name = "projectSetFocus",desc = "项目集收藏模板",required = true)
    public Result<String> createProjectSetFocus(@RequestBody @NotNull @Valid ProjectSetFocus projectSetFocus){
        String id = projectSetFocusService.createProjectSetFocus(projectSetFocus);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateProjectSetFocus",method = RequestMethod.POST)
    @ApiMethod(name = "updateProjectSetFocus",desc = "更新收藏的项目集")
    @ApiParam(name = "projectSetFocus",desc = "项目集收藏模板",required = true)
    public Result<Void> updateProjectSetFocus(@RequestBody @NotNull @Valid ProjectSetFocus projectSetFocus){
        projectSetFocusService.updateProjectSetFocus(projectSetFocus);

        return Result.ok();
    }

    @RequestMapping(path="/deleteProjectSetFocus",method = RequestMethod.POST)
    @ApiMethod(name = "deleteProjectSetFocus",desc = "删除收藏的项目集")
    @ApiParam(name = "id",desc = "项目集id",required = true)
    public Result<Void> deleteProjectSetFocus(@NotNull String id){
        projectSetFocusService.deleteProjectSetFocus(id);

        return Result.ok();
    }

    @RequestMapping(path="/findProjectSetFocus",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectSetFocus",desc = "根据id 查找收藏的项目集")
    @ApiParam(name = "id",desc = "项目集id",required = true)
    public Result<ProjectSetFocus> findProjectSetFocus(@NotNull String id){
        ProjectSetFocus projectSetFocus = projectSetFocusService.findProjectSetFocus(id);

        return Result.ok(projectSetFocus);
    }

    @RequestMapping(path="/findAllProjectSetFocus",method = RequestMethod.POST)
    @ApiMethod(name = "findAllProjectSetFocus",desc = "查找所有收藏的项目集")
    public Result<List<ProjectSetFocus>> findAllProjectSetFocus(){
        List<ProjectSetFocus> projectSetFocusList = projectSetFocusService.findAllProjectSetFocus();

        return Result.ok(projectSetFocusList);
    }

    @RequestMapping(path = "/findProjectSetFocusList",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectSetFocusList",desc = "根据条件查询收藏项目集列表")
    @ApiParam(name = "projectSetFocusQuery",desc = "收藏的项目集查询条件模板",required = true)
    public Result<List<ProjectSetFocus>> findProjectSetFocusList(@RequestBody @Valid @NotNull ProjectSetFocusQuery projectSetFocusQuery){
        List<ProjectSetFocus> projectSetFocusList = projectSetFocusService.findProjectSetFocusList(projectSetFocusQuery);

        return Result.ok(projectSetFocusList);
    }


    @RequestMapping(path = "/findProjectSetFocusPage",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectSetFocusPage",desc = "根据条件按分页查询收藏项目集列表")
    @ApiParam(name = "projectSetFocusQuery",desc = "收藏的项目集查询条件模板",required = true)
    public Result<Pagination<ProjectSetFocus>> findProjectSetFocusPage(@RequestBody @Valid @NotNull ProjectSetFocusQuery projectSetFocusQuery){
        Pagination<ProjectSetFocus> pagination = projectSetFocusService.findProjectSetFocusPage(projectSetFocusQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path="/deleteProjectSetFocusByQuery",method = RequestMethod.POST)
    @ApiMethod(name = "deleteProjectSetFocusByQuery",desc = "按照添加删除收藏的项目集")
    @ApiParam(name = "projectSetFocusQuery",desc = "收藏的项目集查询条件模板",required = true)
    public Result<Void> deleteProjectSetFocusByQuery(@RequestBody @NotNull @Valid ProjectSetFocusQuery projectSetFocusQuery){
        projectSetFocusService.deleteProjectSetFocusByQuery(projectSetFocusQuery);

        return Result.ok();
    }

}
