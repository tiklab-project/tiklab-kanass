package io.thoughtware.kanass.workitem.controller;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.kanass.project.wiki.model.NodeQuery;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import io.thoughtware.core.Result;
import io.thoughtware.kanass.project.wiki.model.KanassDocument;
import io.thoughtware.kanass.project.wiki.model.WikiDocument;
import io.thoughtware.kanass.workitem.model.WorkItemDocumentQuery;
import io.thoughtware.kanass.workitem.service.WikiDocumentService;
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
 * DocumentController
 */
@RestController
@RequestMapping("/wikidocument")
@Api(name = "DocumentController",desc = "文档管理")
public class WikiDocumentController {

    private static Logger logger = LoggerFactory.getLogger(WikiDocumentController.class);


    @Autowired
    private WikiDocumentService wikiDocumentService;


    @RequestMapping(path = "/findDocumentList",method = RequestMethod.POST)
    @ApiMethod(name = "findDocumentList",desc = "findDocumentList")
    @ApiParam(name = "documentQuery",desc = "documentQuery",required = true)
    public Result<List<WikiDocument>> findDocumentList(@RequestBody @Valid @NotNull NodeQuery nodeQuery){
        List<WikiDocument> wikiDocumentList = wikiDocumentService.findDocumentList(nodeQuery);

        return Result.ok(wikiDocumentList);
    }



    @RequestMapping(path="/findUnRelationWorkDocumentList",method = RequestMethod.POST)
    @ApiMethod(name = "findUnRelationWorkDocumentList",desc = "通过id查询")
    @ApiParam(name = "workItemDocumentQuery",desc = "workItemDocumentQuery",required = true)
    public Result<KanassDocument> findUnRelationWorkDocumentList(@RequestBody @Valid @NotNull WorkItemDocumentQuery workItemDocumentQuery){
        Pagination<KanassDocument> unRelationWorkDocumentList = wikiDocumentService.findUnRelationWorkDocumentList(workItemDocumentQuery);

        return Result.ok(unRelationWorkDocumentList);
    }
}
