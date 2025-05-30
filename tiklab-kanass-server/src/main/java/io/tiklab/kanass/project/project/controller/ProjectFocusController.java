package io.tiklab.kanass.project.project.controller;

import io.tiklab.kanass.project.project.model.ProjectFocus;
import io.tiklab.kanass.project.project.model.ProjectFocusQuery;
import io.tiklab.kanass.project.project.service.ProjectFocusService;
import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
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
 * 收藏的项目控制器
 */
@RestController
@RequestMapping("/projectFocus")
//@Api(name = "ProjectFocusController",desc = "ProjectFocusController")
public class ProjectFocusController {

    private static Logger logger = LoggerFactory.getLogger(ProjectFocusController.class);

    @Autowired
    private ProjectFocusService projectFocusService;

    @RequestMapping(path="/createProjectFocus",method = RequestMethod.POST)
    //@ApiMethod(name = "createProjectFocus",desc = "添加收藏项目")
    //@ApiParam(name = "projectFocus",desc = "项目收藏模型",required = true)
    public Result<String> createProjectFocus(@RequestBody @NotNull @Valid ProjectFocus projectFocus){
        String id = projectFocusService.createProjectFocus(projectFocus);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateProjectFocus",method = RequestMethod.POST)
    //@ApiMethod(name = "updateProjectFocus",desc = "更新收藏项目")
    //@ApiParam(name = "projectFocus",desc = "项目收藏模型",required = true)
    public Result<Void> updateProjectFocus(@RequestBody @NotNull @Valid ProjectFocus projectFocus){
        projectFocusService.updateProjectFocus(projectFocus);

        return Result.ok();
    }

    @RequestMapping(path="/deleteProjectFocusByQuery",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteProjectFocusByQuery",desc = "根据收藏者和项目id删除收藏的项目")
    //@ApiParam(name = "projectFocusQuery",desc = "项目收藏模型",required = true)
    public Result<Void> deleteProjectFocusByQuery(@RequestBody @NotNull @Valid ProjectFocusQuery projectFocusQuery){
        projectFocusService.deleteProjectFocusByQuery(projectFocusQuery);

        return Result.ok();
    }

    @RequestMapping(path="/deleteProjectFocus",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteProjectFocus",desc = "根据id 删除收藏的项目")
    //@ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteProjectFocus(@NotNull String id){
        projectFocusService.deleteProjectFocus(id);

        return Result.ok();
    }

    @RequestMapping(path="/findProjectFocus",method = RequestMethod.POST)
    //@ApiMethod(name = "findProjectFocus",desc = "根据id 查找收藏的项目")
    //@ApiParam(name = "id",desc = "id",required = true)
    public Result<ProjectFocus> findProjectFocus(@NotNull String id){
        ProjectFocus projectFocus = projectFocusService.findProjectFocus(id);

        return Result.ok(projectFocus);
    }

    @RequestMapping(path="/findAllProjectFocus",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllProjectFocus",desc = "查找所有收藏的项目")
    public Result<List<ProjectFocus>> findAllProjectFocus(){
        List<ProjectFocus> projectFocusList = projectFocusService.findAllProjectFocus();

        return Result.ok(projectFocusList);
    }

    @RequestMapping(path = "/findProjectFocusList",method = RequestMethod.POST)
    //@ApiMethod(name = "findProjectFocusList",desc = "根据条件查询项目收藏列表")
    //@ApiParam(name = "projectFocusQuery",desc = "项目收藏模型",required = true)
    public Result<List<ProjectFocus>> findProjectFocusList(@RequestBody @Valid @NotNull ProjectFocusQuery projectFocusQuery){
        List<ProjectFocus> projectFocusList = projectFocusService.findProjectFocusList(projectFocusQuery);

        return Result.ok(projectFocusList);
    }

    @RequestMapping(path = "/findProjectFocusPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findProjectFocusPage",desc = "根据条件按分页查询项目的收藏列表")
    //@ApiParam(name = "projectFocusQuery",desc = "项目收藏模型",required = true)
    public Result<Pagination<ProjectFocus>> findProjectFocusPage(@RequestBody @Valid @NotNull ProjectFocusQuery projectFocusQuery){
        Pagination<ProjectFocus> pagination = projectFocusService.findProjectFocusPage(projectFocusQuery);

        return Result.ok(pagination);
    }

}
