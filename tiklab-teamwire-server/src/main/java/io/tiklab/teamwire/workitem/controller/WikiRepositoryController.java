package io.tiklab.teamwire.workitem.controller;


import io.tiklab.core.Result;
import io.tiklab.kanass.repository.model.WikiRepository;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.teamwire.project.wiki.model.KanassRepository;
import io.tiklab.teamwire.project.wiki.service.WikiRepositoryService;
import io.tiklab.user.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wikirepository")
@Api(name = "WikiDocumentController",desc = "事项和文档关联管理")
public class WikiRepositoryController {
    private static Logger logger = LoggerFactory.getLogger(WikiDocumentController.class);

//    @Autowired
//    @Reference(address = "${kanass.address}")
    @Autowired
    private WikiRepositoryService wikiRepositoryService;

    @RequestMapping(path="/findAllRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRepository",desc = "findAllRepository")
    public Result<List<KanassRepository>> findAllRepository(){
        List<KanassRepository> wikiRepositoryList = wikiRepositoryService.findAllRepository();

        return Result.ok(wikiRepositoryList);
    }


}
