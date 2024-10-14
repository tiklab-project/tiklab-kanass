package io.tiklab.kanass.workitem.controller;


import io.tiklab.core.Result;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.kanass.project.wiki.model.KanassRepository;
import io.tiklab.kanass.workitem.service.WorkRepositoryService;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
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
@RequestMapping("/wikirepository")
@Api(name = "WikiDocumentController",desc = "事项和文档关联管理")
public class WorkRepositoryController {
    private static Logger logger = LoggerFactory.getLogger(WikiDocumentController.class);

//    @Autowired
//    @Reference(address = "${sward.address}")
    @Autowired
    private WorkRepositoryService workRepositoryService;

    @RequestMapping(path="/findAllRepository",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRepository",desc = "findAllRepository")
    public Result<List<KanassRepository>> findAllRepository(){
        List<KanassRepository> wikiRepositoryList = workRepositoryService.findAllRepository();

        return Result.ok(wikiRepositoryList);
    }

    @RequestMapping(path="/findRepositoryUserList",method = RequestMethod.POST)
    @ApiMethod(name = "findRepository",desc = "findRepository")
    public Result<List<DmUser>> findRepositoryUserList(@RequestBody @NotNull @Valid DmUserQuery dmUserQuery){
        List<DmUser> repositoryUserList = workRepositoryService.findRepositoryUserList(dmUserQuery);

        return Result.ok(repositoryUserList);
    }


}
