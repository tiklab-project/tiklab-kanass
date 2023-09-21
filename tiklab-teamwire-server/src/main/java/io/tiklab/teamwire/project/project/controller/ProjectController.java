package io.tiklab.teamwire.project.project.controller;

import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.teamwire.project.project.model.ProjectQuery;
import io.tiklab.teamwire.project.project.service.ProjectService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;

import io.tiklab.teamwire.workitem.model.WorkItem;
import io.tiklab.teamwire.workitem.model.WorkItemQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @pi.protocol: http
 * @pi.groupName: 项目控制器
 */
@RestController
@RequestMapping("/project")
@Api(name = "ProjectController",desc = "项目管理")
public class ProjectController {

    private static Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    /**
     * @pi.name:创建项目
     * @pi.path:/project/createProject
     * @pi.method:post
     * @pi.request-type:json
     * @pi.param: model=Project
     */
    @RequestMapping(path="/createProject",method = RequestMethod.POST)
    @ApiMethod(name = "createProject",desc = "创建项目")
    @ApiParam(name = "project",desc = "项目DTO",required = true)
    public Result<String> createProject(@Validated @RequestBody Project project){

        String id = projectService.createProject(project);

        return Result.ok(id);
    }

    /**
     * @pi.name:更新项目
     * @pi.path:/project/updateProject
     * @pi.method:post
     * @pi.request-type:json
     * @pi.param: model=Project
     */
    @RequestMapping(path="/updateProject",method = RequestMethod.POST)
    @ApiMethod(name = "updateProject",desc = "更新项目")
    @ApiParam(name = "project",desc = "项目DTO",required = true)
    public Result<Void> updateProject(@Validated @RequestBody Project project){
        projectService.updateProject(project);

        return Result.ok();
    }


    /**
     * @pi.name:根据项目ID删除项目
     * @pi.path:/project/deleteProject
     * @pi.method:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;
     */
    @RequestMapping(path="/deleteProject",method = RequestMethod.POST)
    @ApiMethod(name = "deleteProject",desc = "根据项目ID删除项目")
    @ApiParam(name = "id",desc = "项目ID",required = true)
    public Result<Void> deleteProject(@NotNull String id){
        projectService.deleteProject(id);

        return Result.ok();
    }

    /**
     * @pi.name:根据项目ID查找项目
     * @pi.path:/project/findProject
     * @pi.method:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;
     */
    @RequestMapping(path="/findProject",method = RequestMethod.POST)
    @ApiMethod(name = "findProject",desc = "根据项目ID查找项目")
    @ApiParam(name = "id",desc = "项目ID",required = true)
       public Result<Project> findProject(@NotNull String id){
        Project project = projectService.findProject(id);

        return Result.ok(project);
    }

    /**
     * @pi.name:根据项目ID查找项目和事项数量
     * @pi.path:/project/findProjectAndWorkNum
     * @pi.method:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;
     */
    @RequestMapping(path="/findProjectAndWorkNum",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectAndWorkNum",desc = "根据项目ID查找项目")
    @ApiParam(name = "id",desc = "项目ID",required = true)
    public Result<Project> findProjectAndWorkNum(@NotNull String id){
        Project project = projectService.findProjectAndWorkNum(id);

        return Result.ok(project);
    }

    /**
     * @pi.name:根据项目ID查找项目和事项数量
     * @pi.path:/project/findAllProject
     * @pi.method:post
     * @pi.request-type:none
     */
    @RequestMapping(path="/findAllProject",method = RequestMethod.POST)
    @ApiMethod(name = "findAllProject",desc = "查找所有项目")
    public Result<List<Project>> findAllProject(){
        List<Project> projectList = projectService.findAllProject();

        return Result.ok(projectList);
    }

    /**
     * @pi.name:根据查询对象查询项目列表
     * @pi.path:/project/findProjectList
     * @pi.method:post
     * @pi.request-type:json
     * @pi.param: model=ProjectQuery
     */
    @RequestMapping(path = "/findProjectList",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectList",desc = "根据查询对象查询项目列表")
    @ApiParam(name = "projectQuery",desc = "项目查询对象",required = true)
    public Result<List<Project>> findProjectList(@RequestBody ProjectQuery projectQuery){
        List<Project> projectList = projectService.findProjectList(projectQuery);

        return Result.ok(projectList);
    }

    /**
     * @pi.name:查询我负责的项目列表
     * @pi.path:/project/findMaterProjectList
     * @pi.method:post
     * @pi.request-type:formdata
     * @pi.param: name=masterId;dataType=string;value=masterId;
     */
    @RequestMapping(path = "/findMaterProjectList",method = RequestMethod.POST)
    @ApiMethod(name = "findMaterProjectList",desc = "查询我负责的项目列表")
    @ApiParam(name = "masterId",desc = "负责人id",required = true)
    public Result<List<Project>> findMaterProjectList(@NotNull @Valid String masterId){
        List<Project> projectList = projectService.findMaterProjectList(masterId);

        return Result.ok(projectList);
    }

    /**
     * @pi.name:查询我参与的项目列表
     * @pi.path:/project/findJoinProjectList
     * @pi.method:post
     * @pi.request-type:json
     * @pi.param: model=ProjectQuery
     */
    @RequestMapping(path = "/findJoinProjectList",method = RequestMethod.POST)
    @ApiMethod(name = "findJoinProjectList",desc = "查询我参与的项目列表")
    @ApiParam(name = "projectQuery",desc = "负责人id",required = true)
    public Result<List<Project>> findJoinProjectList(@RequestBody ProjectQuery projectQuery){
        List<Project> projectList = projectService.findJoinProjectList(projectQuery);

        return Result.ok(projectList);
    }

    /**
     * @pi.name:根据查询对象按分页查询项目列表
     * @pi.path:/project/findProjectPage
     * @pi.method:post
     * @pi.request-type:json
     * @pi.param: model=ProjectQuery
     */
    @RequestMapping(path = "/findProjectPage",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectPage",desc = "根据查询对象按分页查询项目列表")
    @ApiParam(name = "projectQuery",desc = "项目查询对象",required = true)
    public Result<Pagination<Project>> findProjectPage(@RequestBody ProjectQuery projectQuery){
        Pagination<Project> pagination = projectService.findProjectPage(projectQuery);

        return Result.ok(pagination);
    }

    /**
     * @pi.name:根据查询对象查询最近的项目列表
     * @pi.path:/project/findRecentProjectPage
     * @pi.method:post
     * @pi.request-type:json
     * @pi.param: model=ProjectQuery
     */
    @RequestMapping(path = "/findRecentProjectPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRecentProjectPage",desc = "根据查询对象查询最近的项目列表")
    @ApiParam(name = "projectQuery",desc = "项目查询对象",required = true)
    public Result<List<Project>> findRecentProjectPage(@RequestBody ProjectQuery projectQuery){
        List<Project> projectList = projectService.findRecentProjectList(projectQuery);

        return Result.ok(projectList);
    }

    /**
     * @pi.name:根据查询对象查询关注的项目列表
     * @pi.path:/project/findFocusProjectList
     * @pi.method:post
     * @pi.request-type:json
     * @pi.param: model=projectQuery
     */
    @RequestMapping(path = "/findFocusProjectList",method = RequestMethod.POST)
    @ApiMethod(name = "findFocusProjectList",desc = "根据查询对象查询关注的项目列表")
    @ApiParam(name = "projectQuery",desc = "项目查询对象",required = true)
    public Result<List<Project>> findFocusProjectList(@RequestBody ProjectQuery projectQuery){
        List<Project> projectList = projectService.findFocusProjectList(projectQuery);

        return Result.ok(projectList);
    }

    /**
     * @pi.name:根据标题关键字查找事项列表
     * @pi.path:/project/findProjectListByKeyWords
     * @pi.method:post
     * @pi.request-type:formdata
     * @pi.param: name=keyWord;dataType=string;value=masterId;
     */
    @RequestMapping(path = "/findProjectListByKeyWords",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectListByKeyWords",desc = "根据标题关键字查找事项列表")
    @ApiParam(name = "keyWord",desc = "查询对象",required = true)
    public Result<List<Project>> findProjectListByKeyWords(@Valid @NotNull String keyWord){
        List<Project> projectListByKeyWords = projectService.findProjectListByKeyWords(keyWord);

        return Result.ok(projectListByKeyWords);
    }

    @RequestMapping(path = "/creatProjectKey",method = RequestMethod.POST)
    @ApiMethod(name = "creatProjectKey",desc = "根据标题关键字查找事项列表")
    public Result<String> creatProjectKey(@Valid @NotNull String projectName){
        String key = projectService.creatProjectKey(projectName);

        return Result.ok(key);
    }

}
