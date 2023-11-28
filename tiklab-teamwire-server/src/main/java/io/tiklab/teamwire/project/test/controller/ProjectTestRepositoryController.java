package io.tiklab.teamwire.project.test.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.teamwire.project.test.model.ProjectTestRepository;
import io.tiklab.teamwire.project.test.model.ProjectTestRepositoryQuery;
import io.tiklab.teamwire.project.test.model.TestRepository;
import io.tiklab.teamwire.project.test.service.ProjectTestRepositoryService;
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
 * 迭代控制器
 */
@RestController
@RequestMapping("/projectTestRepository")
@Api(name = "ProjectTestRepositoryController",desc = "迭代管理")
public class ProjectTestRepositoryController {

    private static Logger logger = LoggerFactory.getLogger(ProjectTestRepositoryController.class);

    @Autowired
    private ProjectTestRepositoryService projectTestRepositoryService;


    @RequestMapping(path="/createProjectTestRepository",method = RequestMethod.POST)
    @ApiMethod(name = "createProjectTestRepository",desc = "创建迭代")
    @ApiParam(name = "projectTestRepository",desc = "迭代DTO",required = true)
    public Result<String> createProjectTestRepository(@RequestBody @NotNull @Valid @ApiParam ProjectTestRepository projectTestRepository){
        String id = projectTestRepositoryService.createProjectTestRepository(projectTestRepository);

        return Result.ok(id);
    }


    @RequestMapping(path="/updateProjectTestRepository",method = RequestMethod.POST)
    @ApiMethod(name = "updateProjectTestRepository",desc = "更新迭代")
    @ApiParam(name = "projectTestRepository",desc = "迭代DTO",required = true)
    public Result<Void> updateProjectTestRepository(@RequestBody @NotNull @Valid @ApiParam ProjectTestRepository projectTestRepository){
        projectTestRepositoryService.updateProjectTestRepository(projectTestRepository);

        return Result.ok();
    }


    @RequestMapping(path="/deleteProjectTestRepository",method = RequestMethod.POST)
    @ApiMethod(name = "deleteProjectTestRepository",desc = "根据ID删除迭代")
    @ApiParam(name = "id",desc = "迭代ID",required = true)
    public Result<Void> deleteProjectTestRepository(@NotNull String id){
        projectTestRepositoryService.deleteProjectTestRepository(id);

        return Result.ok();
    }

    @RequestMapping(path="/deleteProjectTestRepositoryByCondition",method = RequestMethod.POST)
    @ApiMethod(name = "deleteProjectTestRepositoryByCondition",desc = "根据ID删除迭代")
    @ApiParam(name = "repositoryId",desc = "仓库ID",required = true)
    public Result<Void> deleteProjectTestRepositoryByCondition(@NotNull String repositoryId, @NotNull String projectId){
        projectTestRepositoryService.deleteProjectTestRepositoryByCondition(repositoryId, projectId);

        return Result.ok();
    }



    @RequestMapping(path="/findProjectTestRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectTestRepository",desc = "根据id查找知识库列表")
    @ApiParam(name = "id",desc = "知识库ID",required = true)
    public Result<ProjectTestRepository> findProjectTestRepository(@NotNull String id){
        ProjectTestRepository projectTestRepository = projectTestRepositoryService.findProjectTestRepository(id);

        return Result.ok(projectTestRepository);
    }

    @RequestMapping(path="/findUnProjectTestRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findUnProjectTestRepository",desc = "根据id查找知识库列表")
    @ApiParam(name = "projectId",desc = "知识库ID",required = true)
    public Result<List<TestRepository>> findUnProjectTestRepository(@NotNull String projectId){
        List<TestRepository> repositoryList = projectTestRepositoryService.findUnProjectTestRepository(projectId);

        return Result.ok(repositoryList);
    }

    @RequestMapping(path="/findAllProjectTestRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findAllProjectTestRepository",desc = "查找所有迭代列表")
    public Result<List<ProjectTestRepository>> findAllProjectTestRepository(){
        List<ProjectTestRepository> projectTestRepositoryList = projectTestRepositoryService.findAllProjectTestRepository();

        return Result.ok(projectTestRepositoryList);
    }


    @RequestMapping(path = "/findProjectTestRepositoryList",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectTestRepositoryList",desc = "根据条件查找迭代列表")
    @ApiParam(name = "projectTestRepositoryQuery",desc = "迭代查询对象",required = true)
    public Result<List<TestRepository>> findProjectTestRepositoryList(@RequestBody @Valid @NotNull ProjectTestRepositoryQuery projectTestRepositoryQuery){
        List<TestRepository> projectTestRepositoryList = projectTestRepositoryService.findProjectTestRepositoryList(projectTestRepositoryQuery);

        return Result.ok(projectTestRepositoryList);
    }


    @RequestMapping(path = "/findProjectTestRepositoryPage",method = RequestMethod.POST)
    @ApiMethod(name = "findProjectTestRepositoryPage",desc = "根据条件按照分页查找迭代")
    @ApiParam(name = "projectTestRepositoryQuery",desc = "迭代查询对象",required = true)
    public Result<Pagination<ProjectTestRepository>> findProjectTestRepositoryPage(@RequestBody @Valid @NotNull ProjectTestRepositoryQuery projectTestRepositoryQuery){
        Pagination<ProjectTestRepository> pagination = projectTestRepositoryService.findProjectTestRepositoryPage(projectTestRepositoryQuery);

        return Result.ok(pagination);
    }
}
