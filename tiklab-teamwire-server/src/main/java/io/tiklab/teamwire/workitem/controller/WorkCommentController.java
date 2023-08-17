package io.tiklab.teamwire.workitem.controller;

import io.tiklab.teamwire.workitem.model.WorkComment;
import io.tiklab.teamwire.workitem.model.WorkCommentQuery;
import io.tiklab.teamwire.workitem.service.WorkCommentService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
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
 * 事项评论控制器
 */
@RestController
@RequestMapping("/workComment")
@Api(name = "WorkCommentController",desc = "WorkCommentController")
public class WorkCommentController {

    private static Logger logger = LoggerFactory.getLogger(WorkCommentController.class);

    @Autowired
    private WorkCommentService workCommentService;

    @RequestMapping(path="/createWorkComment",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkComment",desc = "创建事项评论")
    @ApiParam(name = "workComment",desc = "事项评论模型",required = true)
    public Result<String> createWorkComment(@RequestBody @NotNull @Valid WorkComment workComment){
        String id = workCommentService.createWorkComment(workComment);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkComment",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkComment",desc = "更新事项评论")
    @ApiParam(name = "workComment",desc = "事项评论模型",required = true)
    public Result<Void> updateWorkComment(@RequestBody @NotNull @Valid WorkComment workComment){
        workCommentService.updateWorkComment(workComment);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkComment",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkComment",desc = "删除事项评论")
    @ApiParam(name = "id",desc = "评论id",required = true)
    public Result<Void> deleteWorkComment(@NotNull String id){
        workCommentService.deleteWorkComment(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkComment",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkComment",desc = "根据id查找事项评论")
    @ApiParam(name = "id",desc = "评论id",required = true)
    public Result<WorkComment> findWorkComment(@NotNull String id){
        WorkComment workComment = workCommentService.findWorkComment(id);

        return Result.ok(workComment);
    }

    @RequestMapping(path="/findAllWorkComment",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWorkComment",desc = "查找所有事项评论")
    public Result<List<WorkComment>> findAllWorkComment(){
        List<WorkComment> workCommentList = workCommentService.findAllWorkComment();

        return Result.ok(workCommentList);
    }

    @RequestMapping(path = "/findWorkCommentList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkCommentList",desc = "根据条件查询事项评论列表")
    @ApiParam(name = "workCommentQuery",desc = "事项评论搜索条件模型",required = true)
    public Result<List<WorkComment>> findWorkCommentList(@RequestBody @Valid @NotNull WorkCommentQuery workCommentQuery){
        List<WorkComment> workCommentList = workCommentService.findWorkCommentList(workCommentQuery);

        return Result.ok(workCommentList);
    }

    @RequestMapping(path = "/findWorkCommentPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkCommentPage",desc = "根据条件按分页查询事项评论列表")
    @ApiParam(name = "workCommentQuery",desc = "事项评论搜索条件模型",required = true)
    public Result<Pagination<WorkComment>> findWorkCommentPage(@RequestBody @Valid @NotNull WorkCommentQuery workCommentQuery){
        Pagination<WorkComment> pagination = workCommentService.findWorkCommentPage(workCommentQuery);

        return Result.ok(pagination);
    }

}
