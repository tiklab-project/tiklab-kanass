package io.tiklab.teamwire.project.wiki.controller;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.repository.model.WikiRepository;
import io.tiklab.postin.annotation.Api;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.teamwire.project.wiki.model.KanassRepository;
import io.tiklab.teamwire.project.wiki.model.ProjectWikiRepository;
import io.tiklab.teamwire.project.wiki.model.ProjectWikiRepositoryQuery;
import io.tiklab.teamwire.project.wiki.service.ProjectWikiRepositoryService;
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
 * 项目知识库控制器
 */
@RestController
@RequestMapping("/projectWikiRepository")
@Api(name = "ProjectWikiRepositoryController",desc = "迭代管理")
public class ProjectWikiRepositoryController {

    private static Logger logger = LoggerFactory.getLogger(ProjectWikiRepositoryController.class);

    @Autowired
    private ProjectWikiRepositoryService projectWikiRepositoryService;


    @RequestMapping(path="/createProjectWikiRepository",method = RequestMethod.POST)
    @ApiMethod(name = "createProjectWikiRepository",desc = "创建迭代")
    @ApiParam(name = "projectWikiRepository",desc = "迭代DTO",required = true)
    public Result<String> createProjectWikiRepository(@RequestBody @NotNull @Valid @ApiParam ProjectWikiRepository projectWikiRepository){
        String id = projectWikiRepositoryService.createProjectWikiRepository(projectWikiRepository);

        return Result.ok(id);
    }


    @RequestMapping(path="/updateProjectWikiRepository",method = RequestMethod.POST)
    @ApiMethod(name = "updateProjectWikiRepository",desc = "更新项目知识库")
    @ApiParam(name = "projectWikiRepository",desc = "迭代DTO",required = true)
    public Result<Void> updateProjectWikiRepository(@RequestBody @NotNull @Valid @ApiParam ProjectWikiRepository projectWikiRepository){
        projectWikiRepositoryService.updateProjectWikiRepository(projectWikiRepository);

        return Result.ok();
    }


    @RequestMapping(path="/deleteProjectWikiRepository",method = RequestMethod.POST)
    @ApiMethod(name = "deleteProjectWikiRepository",desc = "根据ID删除迭代")
    @ApiParam(name = "id",desc = "迭代ID",required = true)
    public Result<Void> deleteProjectWikiRepository(@NotNull String id){
        projectWikiRepositoryService.deleteProjectWikiRepository(id);

        return Result.ok();
    }

    @RequestMapping(path="/deleteProjectWikiRepositoryByCondition",method = RequestMethod.POST)
    @ApiMethod(name = "deleteProjectWikiRepositoryByCondition",desc = "根据ID删除迭代")
    @ApiParam(name = "repositoryId",desc = "仓库ID",required = true)
    public Result<Void> deleteProjectWikiRepositoryByCondition(@NotNull String repositoryId, @NotNull String projectId){
        projectWikiRepositoryService.deleteProjectWikiRepositoryByCondition(repositoryId, projectId);

        return Result.ok();
    }



    @RequestMapping(path="/findProjectWikiRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectWikiRepository",desc = "根据id查找知识库列表")
    @ApiParam(name = "id",desc = "知识库ID",required = true)
    public Result<ProjectWikiRepository> findProjectWikiRepository(@NotNull String id){
        ProjectWikiRepository projectWikiRepository = projectWikiRepositoryService.findProjectWikiRepository(id);

        return Result.ok(projectWikiRepository);
    }

    @RequestMapping(path="/findUnProjectWikiRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findUnProjectWikiRepository",desc = "根据id查找知识库列表")
    @ApiParam(name = "projectId",desc = "知识库ID",required = true)
    public Result<List<KanassRepository>> findUnProjectWikiRepository(@NotNull String projectId){
        List<KanassRepository> wikiRepositoryList = projectWikiRepositoryService.findUnProjectWikiRepository(projectId);

        return Result.ok(wikiRepositoryList);
    }

    @RequestMapping(path="/findAllProjectWikiRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findAllProjectWikiRepository",desc = "查找所有迭代列表")
    public Result<List<ProjectWikiRepository>> findAllProjectWikiRepository(){
        List<ProjectWikiRepository> projectWikiRepositoryList = projectWikiRepositoryService.findAllProjectWikiRepository();

        return Result.ok(projectWikiRepositoryList);
    }


    @RequestMapping(path = "/findProjectWikiRepositoryList",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectWikiRepositoryList",desc = "根据条件查找迭代列表")
    @ApiParam(name = "projectWikiRepositoryQuery",desc = "迭代查询对象",required = true)
    public Result<List<KanassRepository>> findProjectWikiRepositoryList(@RequestBody @Valid @NotNull ProjectWikiRepositoryQuery projectWikiRepositoryQuery){
        List<KanassRepository> projectWikiWikiRepositoryList = projectWikiRepositoryService.findProjectWikiRepositoryList(projectWikiRepositoryQuery);

        return Result.ok(projectWikiWikiRepositoryList);
    }


    @RequestMapping(path = "/findProjectWikiRepositoryPage",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectWikiRepositoryPage",desc = "根据条件按照分页查找迭代")
    @ApiParam(name = "projectWikiRepositoryQuery",desc = "迭代查询对象",required = true)
    public Result<Pagination<ProjectWikiRepository>> findProjectWikiRepositoryPage(@RequestBody @Valid @NotNull ProjectWikiRepositoryQuery projectWikiRepositoryQuery){
        Pagination<ProjectWikiRepository> pagination = projectWikiRepositoryService.findProjectWikiRepositoryPage(projectWikiRepositoryQuery);

        return Result.ok(pagination);
    }
}
