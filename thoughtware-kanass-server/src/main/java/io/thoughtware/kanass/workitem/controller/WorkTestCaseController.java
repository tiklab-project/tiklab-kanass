package io.thoughtware.kanass.workitem.controller;

import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import io.thoughtware.kanass.project.test.model.ProjectTestCase;
import io.thoughtware.kanass.workitem.model.WorkTestCase;
import io.thoughtware.kanass.workitem.model.WorkTestCaseQuery;
import io.thoughtware.kanass.workitem.service.WorkTestCaseService;
import io.thoughtware.user.user.model.User;
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
 * WorkTestCaseController
 */
@RestController
@RequestMapping("/workTestCase")
@Api(name = "WorkTestCaseController",desc = "事项和文档关联管理")
public class WorkTestCaseController {

    private static Logger logger = LoggerFactory.getLogger(WorkTestCaseController.class);

    @Autowired
    private WorkTestCaseService workTestCaseService;



    @RequestMapping(path="/createWorkTestCase",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkTestCase",desc = "创建事项和文档关联")
    @ApiParam(name = "workTestCaseList",desc = "workTestCaseList",required = true)
    public Result<String> createWorkTestCase(@NotNull @RequestBody List<WorkTestCase> workTestCaseList){
        String id = workTestCaseService.createWorkTestCase(workTestCaseList);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkTestCase",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkTestCase",desc = "updateWorkTestCase")
    @ApiParam(name = "workTestCase",desc = "workTestCase",required = true)
    public Result<Void> updateWorkTestCase(@RequestBody @NotNull @Valid WorkTestCase workTestCase){
        workTestCaseService.updateWorkTestCase(workTestCase);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkTestCase",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkTestCase",desc = "删除事项和文档关联")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWorkTestCase(@NotNull String id){
        workTestCaseService.deleteWorkTestCase(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkTestCase",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkTestCase",desc = "findWorkTestCase")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WorkTestCase> findWorkTestCase(@NotNull String id){
        WorkTestCase workTestCase = workTestCaseService.findWorkTestCase(id);

        return Result.ok(workTestCase);
    }

    @RequestMapping(path="/findAllWorkTestCase",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWorkTestCase",desc = "findAllWorkTestCase")
    public Result<List<WorkTestCase>> findAllWorkTestCase(){
        List<WorkTestCase> workTestCaseList = workTestCaseService.findAllWorkTestCase();

        return Result.ok(workTestCaseList);
    }

    @RequestMapping(path = "/findWorkTestCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkTestCaseList",desc = "findWorkTestCaseList")
    @ApiParam(name = "workTestCaseQuery",desc = "workTestCaseQuery",required = true)
    public Result<List<WorkTestCase>> findWorkTestCaseList(@RequestBody @Valid @NotNull WorkTestCaseQuery workTestCaseQuery){
        List<WorkTestCase> workTestCaseList = workTestCaseService.findWorkTestCaseList(workTestCaseQuery);
        return Result.ok(workTestCaseList);
    }

    @RequestMapping(path = "/findWorkTestCasePage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkTestCasePage",desc = "findWorkTestCasePage")
    @ApiParam(name = "workTestCaseQuery",desc = "workTestCaseQuery",required = true)
    public Result<Pagination<WorkTestCase>> findWorkTestCasePage(@RequestBody @Valid @NotNull WorkTestCaseQuery workTestCaseQuery){
        Pagination<WorkTestCase> pagination = workTestCaseService.findWorkTestCasePage(workTestCaseQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findTestCasePageByWorkItemId",method = RequestMethod.POST)
    @ApiMethod(name = "findTestCasePageByItemId",desc = "查询事项下面关联的文档")
    @ApiParam(name = "workItemId",desc = "workItemId",required = true)
    public Result<List<ProjectTestCase>> findTestCasePageByWorkItemId(@NotNull String workItemId){
        List<ProjectTestCase> documentPageByWorkItemId = workTestCaseService.findTestCasePageByWorkItemId(workItemId);

        return Result.ok(documentPageByWorkItemId);
    }


    @RequestMapping(path="/deleteWorkTestCaseRele",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkTestCaseRele",desc = "通过文档id 和事项id 删除")
    @ApiParam(name = "workTestCaseQuery",desc = "传参数文档id 和事项id",required = true)
    public Result<Void> deleteWorkTestCaseRele(@RequestBody @NotNull @Valid WorkTestCaseQuery workTestCaseQuery){
        workTestCaseService.deleteWorkTestCaseRele(workTestCaseQuery);

        return Result.ok();
    }

    @RequestMapping(path="/findUnRelationWorkTestCaseList",method = RequestMethod.POST)
    @ApiMethod(name = "findUnRelationWorkTestCaseList",desc = "未关联用例")
    @ApiParam(name = "workTestCaseQuery",desc = "传参数文档id 和事项id",required = true)
    public Result<Pagination<ProjectTestCase>> findUnRelationWorkTestCaseList(@RequestBody @NotNull @Valid WorkTestCaseQuery workTestCaseQuery){
        Pagination<ProjectTestCase> unRelationWorkTestCaseList = workTestCaseService.findUnRelationWorkTestCaseList(workTestCaseQuery);

        return Result.ok(unRelationWorkTestCaseList);
    }

    @RequestMapping(path="/findTestOnRepositoryUserList",method = RequestMethod.POST)
    @ApiMethod(name = "findTestOnRepositoryUserList",desc = "findTestOnRepositoryUserList")
    public Result<List<User>> findTestOnRepositoryUserList(String[] repositoryIds){
        List<User> repositoryUserList = workTestCaseService.findTestOnRepositoryUserList(repositoryIds);

        return Result.ok(repositoryUserList);
    }

}
