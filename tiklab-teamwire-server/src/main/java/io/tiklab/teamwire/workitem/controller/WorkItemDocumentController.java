package io.tiklab.teamwire.workitem.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.teamwire.project.wiki.model.KanassDocument;
import io.tiklab.teamwire.workitem.model.WorkItemDocument;
import io.tiklab.teamwire.workitem.model.WorkItemDocumentQuery;
import io.tiklab.teamwire.workitem.service.WorkItemDocumentService;
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
import java.util.stream.Collectors;


/**
 * WorkItemDocumentController
 */
@RestController
@RequestMapping("/workItemDocument")
@Api(name = "WorkItemDocumentController",desc = "事项和文档关联管理")
public class WorkItemDocumentController {

    private static Logger logger = LoggerFactory.getLogger(WorkItemDocumentController.class);

    @Autowired
    private WorkItemDocumentService workItemDocumentService;




    @RequestMapping(path="/createWorkItemDocument",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkItemDocument",desc = "创建事项和文档关联")
    @ApiParam(name = "workItemDocumentList",desc = "workItemDocumentList",required = true)
    public Result<String> createWorkItemDocument(@NotNull @RequestBody List<WorkItemDocument> workItemDocumentList){
        String id = workItemDocumentService.createWorkItemDocument(workItemDocumentList);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkItemDocument",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkItemDocument",desc = "updateWorkItemDocument")
    @ApiParam(name = "workItemDocument",desc = "workItemDocument",required = true)
    public Result<Void> updateWorkItemDocument(@RequestBody @NotNull @Valid WorkItemDocument workItemDocument){
        workItemDocumentService.updateWorkItemDocument(workItemDocument);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkItemDocument",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkItemDocument",desc = "删除事项和文档关联")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWorkItemDocument(@NotNull String id){
        workItemDocumentService.deleteWorkItemDocument(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkItemDocument",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkItemDocument",desc = "findWorkItemDocument")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WorkItemDocument> findWorkItemDocument(@NotNull String id){
        WorkItemDocument workItemDocument = workItemDocumentService.findWorkItemDocument(id);

        return Result.ok(workItemDocument);
    }

    @RequestMapping(path="/findAllWorkItemDocument",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWorkItemDocument",desc = "findAllWorkItemDocument")
    public Result<List<WorkItemDocument>> findAllWorkItemDocument(){
        List<WorkItemDocument> workItemDocumentList = workItemDocumentService.findAllWorkItemDocument();

        return Result.ok(workItemDocumentList);
    }

    @RequestMapping(path = "/findWorkItemDocumentList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkItemDocumentList",desc = "findWorkItemDocumentList")
    @ApiParam(name = "workItemDocumentQuery",desc = "workItemDocumentQuery",required = true)
    public Result<List<WorkItemDocument>> findWorkItemDocumentList(@RequestBody @Valid @NotNull WorkItemDocumentQuery workItemDocumentQuery){
        List<WorkItemDocument> workItemDocumentList = workItemDocumentService.findWorkItemDocumentList(workItemDocumentQuery);
        return Result.ok(workItemDocumentList);
    }

    @RequestMapping(path = "/findWorkItemDocumentPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkItemDocumentPage",desc = "findWorkItemDocumentPage")
    @ApiParam(name = "workItemDocumentQuery",desc = "workItemDocumentQuery",required = true)
    public Result<Pagination<WorkItemDocument>> findWorkItemDocumentPage(@RequestBody @Valid @NotNull WorkItemDocumentQuery workItemDocumentQuery){
        Pagination<WorkItemDocument> pagination = workItemDocumentService.findWorkItemDocumentPage(workItemDocumentQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findDocumentPageByWorkItemId",method = RequestMethod.POST)
    @ApiMethod(name = "findDocumentPageByItemId",desc = "查询事项下面关联的文档")
    @ApiParam(name = "workItemId",desc = "workItemId",required = true)
    public Result<List<KanassDocument>> findDocumentPageByWorkItemId(@NotNull String workItemId){
        List<KanassDocument> documentPageByWorkItemId = workItemDocumentService.findDocumentPageByWorkItemId(workItemId);

        return Result.ok(documentPageByWorkItemId);
    }



    @RequestMapping(path="/deleteWorkItemDocumentRele",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkItemDocumentRele",desc = "通过文档id 和事项id 删除")
    @ApiParam(name = "workItemDocumentQuery",desc = "传参数文档id 和事项id",required = true)
    public Result<Void> deleteWorkItemDocumentRele(@RequestBody @NotNull @Valid WorkItemDocumentQuery workItemDocumentQuery){
        workItemDocumentService.deleteWorkItemDocumentRele( workItemDocumentQuery);

        return Result.ok();
    }
}
