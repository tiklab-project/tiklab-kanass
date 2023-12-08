package io.thoughtware.kanass.sprint.controller;

import io.thoughtware.kanass.sprint.model.SprintFocus;
import io.thoughtware.kanass.sprint.model.SprintFocusQuery;
import io.thoughtware.kanass.sprint.service.SprintFocusService;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
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
 * 迭代收藏控制器
 */
@RestController
@RequestMapping("/sprintFocus")
@Api(name = "SprintFocusController",desc = "SprintFocusController")
public class SprintFocusController {

    private static Logger logger = LoggerFactory.getLogger(SprintFocusController.class);

    @Autowired
    private SprintFocusService sprintFocusService;

    @RequestMapping(path="/createSprintFocus",method = RequestMethod.POST)
    @ApiMethod(name = "createSprintFocus",desc = "创建迭代收藏")
    @ApiParam(name = "sprintFocus",desc = "收藏的迭代模型",required = true)
    public Result<String> createSprintFocus(@RequestBody @NotNull @Valid SprintFocus sprintFocus){
        String id = sprintFocusService.createSprintFocus(sprintFocus);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateSprintFocus",method = RequestMethod.POST)
    @ApiMethod(name = "updateSprintFocus",desc = "更新收藏的迭代")
    @ApiParam(name = "sprintFocus",desc = "收藏的迭代模型",required = true)
    public Result<Void> updateSprintFocus(@RequestBody @NotNull @Valid SprintFocus sprintFocus){
        sprintFocusService.updateSprintFocus(sprintFocus);

        return Result.ok();
    }

    @RequestMapping(path="/deleteSprintFocus",method = RequestMethod.POST)
    @ApiMethod(name = "deleteSprintFocus",desc = "删除收藏的迭代")
    @ApiParam(name = "id",desc = "迭代id",required = true)
    public Result<Void> deleteSprintFocus(@NotNull String id){
        sprintFocusService.deleteSprintFocus(id);

        return Result.ok();
    }

    @RequestMapping(path="/deleteSprintFocusByQuery",method = RequestMethod.POST)
    @ApiMethod(name = "deleteSprintFocus",desc = "根据添加删除收藏的迭代")
    @ApiParam(name = "sprintFocusQuery",desc = "迭代id",required = true)
    public Result<Void> deleteSprintFocusByQuery(@RequestBody @Valid @NotNull SprintFocusQuery sprintFocusQuery){
        sprintFocusService.deleteSprintFocusByQuery(sprintFocusQuery);

        return Result.ok();
    }

    @RequestMapping(path="/findSprintFocus",method = RequestMethod.POST)
    @ApiMethod(name = "findSprintFocus",desc = "根据id查找迭代收藏")
    @ApiParam(name = "id",desc = "迭代id",required = true)
    public Result<SprintFocus> findSprintFocus(@NotNull String id){
        SprintFocus sprintFocus = sprintFocusService.findSprintFocus(id);

        return Result.ok(sprintFocus);
    }

    @RequestMapping(path="/findAllSprintFocus",method = RequestMethod.POST)
    @ApiMethod(name = "findAllSprintFocus",desc = "查找所有收藏迭代")
    public Result<List<SprintFocus>> findAllSprintFocus(){
        List<SprintFocus> sprintFocusList = sprintFocusService.findAllSprintFocus();

        return Result.ok(sprintFocusList);
    }

    @RequestMapping(path = "/findSprintFocusList",method = RequestMethod.POST)
    @ApiMethod(name = "findSprintFocusList",desc = "根据条件查询收藏的迭代列表")
    @ApiParam(name = "sprintFocusQuery",desc = "收藏的迭代查找条件模型",required = true)
    public Result<List<SprintFocus>> findSprintFocusList(@RequestBody @Valid @NotNull SprintFocusQuery sprintFocusQuery){
        List<SprintFocus> sprintFocusList = sprintFocusService.findSprintFocusList(sprintFocusQuery);

        return Result.ok(sprintFocusList);
    }

    @RequestMapping(path = "/findSprintFocusPage",method = RequestMethod.POST)
    @ApiMethod(name = "findSprintFocusPage",desc = "根据条件按分页查询收藏的迭代列表")
    @ApiParam(name = "sprintFocusQuery",desc = "收藏的迭代查找条件模型",required = true)
    public Result<Pagination<SprintFocus>> findSprintFocusPage(@RequestBody @Valid @NotNull SprintFocusQuery sprintFocusQuery){
        Pagination<SprintFocus> pagination = sprintFocusService.findSprintFocusPage(sprintFocusQuery);

        return Result.ok(pagination);
    }

}
