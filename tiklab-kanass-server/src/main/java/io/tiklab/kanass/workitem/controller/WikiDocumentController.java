package io.tiklab.kanass.workitem.controller;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.wiki.model.NodeQuery;
import io.tiklab.kanass.project.wiki.model.ProjectDocumentQuery;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.core.Result;
import io.tiklab.kanass.project.wiki.model.KanassDocument;
import io.tiklab.kanass.project.wiki.model.WikiDocument;
import io.tiklab.kanass.workitem.model.WorkItemDocumentQuery;
import io.tiklab.kanass.workitem.service.WikiDocumentService;
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
//@Api(name = "DocumentController",desc = "文档管理")
public class WikiDocumentController {

    private static Logger logger = LoggerFactory.getLogger(WikiDocumentController.class);


    @Autowired
    private WikiDocumentService wikiDocumentService;


    @RequestMapping(path = "/findDocumentList",method = RequestMethod.POST)
    //@ApiMethod(name = "findDocumentList",desc = "findDocumentList")
    //@ApiParam(name = "nodeQuery",desc = "documentQuery",required = true)
    public Result<List<WikiDocument>> findDocumentList(@RequestBody @Valid @NotNull NodeQuery nodeQuery){
        List<WikiDocument> wikiDocumentList = wikiDocumentService.findDocumentList(nodeQuery);

        return Result.ok(wikiDocumentList);
    }



    @RequestMapping(path="/findUnRelationWorkDocumentList",method = RequestMethod.POST)
    //@ApiMethod(name = "findUnRelationWorkDocumentList",desc = "通过id查询")
    //@ApiParam(name = "workItemDocumentQuery",desc = "workItemDocumentQuery",required = true)
    public Result<KanassDocument> findUnRelationWorkDocumentList(@RequestBody @Valid @NotNull WorkItemDocumentQuery workItemDocumentQuery){
        Pagination<KanassDocument> unRelationWorkDocumentList = wikiDocumentService.findUnRelationWorkDocumentList(workItemDocumentQuery);

        return Result.ok(unRelationWorkDocumentList);
    }

    @RequestMapping(path="/findUnRelationProjectDocumentList",method = RequestMethod.POST)
    public Result<KanassDocument> findUnRelationProjectDocumentList(@RequestBody @Valid @NotNull ProjectDocumentQuery projectDocumentQuery){
        Pagination<KanassDocument> unRelationWorkDocumentList = wikiDocumentService.findUnRelationProjectDocumentList(projectDocumentQuery);

        return Result.ok(unRelationWorkDocumentList);
    }
}
