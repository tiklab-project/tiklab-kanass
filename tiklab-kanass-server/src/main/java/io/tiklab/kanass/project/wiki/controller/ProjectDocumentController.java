package io.tiklab.kanass.project.wiki.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.wiki.model.KanassDocument;
import io.tiklab.kanass.project.wiki.model.ProjectDocument;
import io.tiklab.kanass.project.wiki.model.ProjectDocumentQuery;
import io.tiklab.kanass.project.wiki.service.ProjectDocumentService;
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

@RestController
@RequestMapping("/projectDocument")
public class ProjectDocumentController {
    private static Logger logger = LoggerFactory.getLogger(ProjectDocumentController.class);
    
    @Autowired
    private ProjectDocumentService projectDocumentService;

    @RequestMapping(path="/createProjectDocument",method = RequestMethod.POST)
    public Result<String> createProjectDocument(@NotNull @RequestBody List<ProjectDocument> projectDocumentList){
        String id = projectDocumentService.createProjectDocument(projectDocumentList);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateProjectDocument",method = RequestMethod.POST)
    public Result<Void> updateProjectDocument(@RequestBody @NotNull @Valid ProjectDocument projectDocument){
        projectDocumentService.updateProjectDocument(projectDocument);

        return Result.ok();
    }

    @RequestMapping(path="/deleteProjectDocument",method = RequestMethod.POST)
    public Result<Void> deleteProjectDocument(@NotNull String id){
        projectDocumentService.deleteProjectDocument(id);

        return Result.ok();
    }

    @RequestMapping(path="/findProjectDocument",method = RequestMethod.POST)
    public Result<ProjectDocument> findProjectDocument(@NotNull String id){
        ProjectDocument projectDocument = projectDocumentService.findProjectDocument(id);

        return Result.ok(projectDocument);
    }

    @RequestMapping(path="/findAllProjectDocument",method = RequestMethod.POST)
    public Result<List<ProjectDocument>> findAllProjectDocument(){
        List<ProjectDocument> projectDocumentList = projectDocumentService.findAllProjectDocument();

        return Result.ok(projectDocumentList);
    }

    @RequestMapping(path = "/findProjectDocumentList",method = RequestMethod.POST)
    public Result<List<ProjectDocument>> findProjectDocumentList(@RequestBody @Valid @NotNull ProjectDocumentQuery projectDocumentQuery){
        List<ProjectDocument> projectDocumentList = projectDocumentService.findProjectDocumentList(projectDocumentQuery);
        return Result.ok(projectDocumentList);
    }

    @RequestMapping(path = "/findProjectDocumentPage",method = RequestMethod.POST)
    public Result<Pagination<ProjectDocument>> findProjectDocumentPage(@RequestBody @Valid @NotNull ProjectDocumentQuery projectDocumentQuery){
        Pagination<ProjectDocument> pagination = projectDocumentService.findProjectDocumentPage(projectDocumentQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findDocumentPageByProjectId",method = RequestMethod.POST)
    public Result<List<KanassDocument>> findDocumentPageByProjectId(@NotNull String projectId){
        List<KanassDocument> documentPageByProjectId = projectDocumentService.findDocumentPageByProjectId(projectId);

        return Result.ok(documentPageByProjectId);
    }



    @RequestMapping(path="/deleteProjectDocumentRele",method = RequestMethod.POST)
    public Result<Void> deleteProjectDocumentRele(@RequestBody @NotNull @Valid ProjectDocumentQuery projectDocumentQuery){
        projectDocumentService.deleteProjectDocumentRele( projectDocumentQuery);

        return Result.ok();
    }
}
