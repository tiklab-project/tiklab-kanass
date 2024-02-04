package io.thoughtware.kanass.sprint.controller;

import io.thoughtware.kanass.sprint.model.Sprint;
import io.thoughtware.kanass.sprint.model.SprintQuery;
import io.thoughtware.kanass.sprint.service.SprintService;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
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
 * 迭代控制器
 */
@RestController
@RequestMapping("/sprint")
@Api(name = "SprintController",desc = "迭代管理")
public class SprintController {

    private static Logger logger = LoggerFactory.getLogger(SprintController.class);

    @Autowired
    private SprintService sprintService;


    @RequestMapping(path="/createSprint",method = RequestMethod.POST)
    @ApiMethod(name = "createSprint",desc = "创建迭代")
    @ApiParam(name = "sprint",desc = "迭代DTO",required = true)
    public Result<String> createSprint(@RequestBody @NotNull @Valid @ApiParam Sprint sprint){
        String id = sprintService.createSprint(sprint);

        return Result.ok(id);
    }


    @RequestMapping(path="/updateSprint",method = RequestMethod.POST)
    @ApiMethod(name = "updateSprint",desc = "更新迭代")
    @ApiParam(name = "sprint",desc = "迭代DTO",required = true)
    public Result<Void> updateSprint(@RequestBody @NotNull @Valid @ApiParam Sprint sprint){
        sprintService.updateSprint(sprint);

        return Result.ok();
    }


    @RequestMapping(path="/deleteSprint",method = RequestMethod.POST)
    @ApiMethod(name = "deleteSprint",desc = "根据ID删除迭代")
    @ApiParam(name = "id",desc = "迭代ID",required = true)
    public Result<Void> deleteSprint(@NotNull String id){
        sprintService.deleteSprint(id);

        return Result.ok();
    }


    @RequestMapping(path="/findSprint",method = RequestMethod.POST)
    @ApiMethod(name = "findSprint",desc = "根据id查找迭代列表")
    @ApiParam(name = "id",desc = "迭代ID",required = true)
    public Result<Sprint> findSprint(@NotNull String id){
        Sprint sprint = sprintService.findSprint(id);

        return Result.ok(sprint);
    }

    @RequestMapping(path="/findAllSprint",method = RequestMethod.POST)
    @ApiMethod(name = "findAllSprint",desc = "查找所有迭代列表")
    public Result<List<Sprint>> findAllSprint(){
        List<Sprint> sprintList = sprintService.findAllSprint();

        return Result.ok(sprintList);
    }


    @RequestMapping(path = "/findSprintList",method = RequestMethod.POST)
    @ApiMethod(name = "findSprintList",desc = "根据条件查找迭代列表")
    @ApiParam(name = "sprintQuery",desc = "迭代查询对象",required = true)
    public Result<List<Sprint>> findSprintList(@RequestBody @Valid @NotNull SprintQuery sprintQuery){
        List<Sprint> sprintList = sprintService.findSprintList(sprintQuery);

        return Result.ok(sprintList);
    }

    @RequestMapping(path = "/findSelectSprintList",method = RequestMethod.POST)
    @ApiMethod(name = "findSelectSprintList",desc = "根据条件查找迭代列表")
    @ApiParam(name = "sprintQuery",desc = "迭代查询对象",required = true)
    public Result<List<Sprint>> findSelectSprintList(@RequestBody @Valid @NotNull SprintQuery sprintQuery){
        List<Sprint> sprintList = sprintService.findSelectSprintList(sprintQuery);

        return Result.ok(sprintList);
    }


    @RequestMapping(path = "/findSprintPage",method = RequestMethod.POST)
    @ApiMethod(name = "findSprintPage",desc = "根据条件按照分页查找迭代")
    @ApiParam(name = "sprintQuery",desc = "迭代查询对象",required = true)
    public Result<Pagination<Sprint>> findSprintPage(@RequestBody @Valid @NotNull SprintQuery sprintQuery){
        Pagination<Sprint> pagination = sprintService.findSprintPage(sprintQuery);

        return Result.ok(pagination);
    }
    @RequestMapping(path = "/findFocusSprintList",method = RequestMethod.POST)
    @ApiMethod(name = "findFocusSprintList",desc = "根据条件查找我收藏的迭代")
    @ApiParam(name = "sprintQuery",desc = "迭代查询对象",required = true)
    public Result<Pagination<Sprint>> findFocusSprintList(@RequestBody @Valid @NotNull SprintQuery sprintQuery){
        List<Sprint> sprintList = sprintService.findFocusSprintList(sprintQuery);

        return Result.ok(sprintList);
    }

}
