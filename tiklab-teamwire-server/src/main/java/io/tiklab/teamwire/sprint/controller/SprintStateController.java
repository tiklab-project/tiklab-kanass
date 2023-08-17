package io.tiklab.teamwire.sprint.controller;

import io.tiklab.teamwire.sprint.model.SprintState;
import io.tiklab.teamwire.sprint.model.SprintStateQuery;
import io.tiklab.teamwire.sprint.service.SprintStateService;
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
 * 迭代状态控制器
 */
@RestController
@RequestMapping("/sprintState")
@Api(name = "SprintStateController",desc = "SprintStateController")
public class SprintStateController {

    private static Logger logger = LoggerFactory.getLogger(SprintStateController.class);

    @Autowired
    private SprintStateService sprintStateService;


    @RequestMapping(path="/createSprintState",method = RequestMethod.POST)
    @ApiMethod(name = "createSprintState",desc = "创建迭代状态")
    @ApiParam(name = "sprintState",desc = "迭代状态模板",required = true)
    public Result<String> createSprintState(@RequestBody @NotNull @Valid SprintState sprintState){
        String id = sprintStateService.createSprintState(sprintState);

        return Result.ok(id);
    }


    @RequestMapping(path="/updateSprintState",method = RequestMethod.POST)
    @ApiMethod(name = "updateSprintState",desc = "更新迭代状态")
    @ApiParam(name = "sprintState",desc = "迭代状态模板",required = true)
    public Result<Void> updateSprintState(@RequestBody @NotNull @Valid SprintState sprintState){
        sprintStateService.updateSprintState(sprintState);

        return Result.ok();
    }


    @RequestMapping(path="/deleteSprintState",method = RequestMethod.POST)
    @ApiMethod(name = "deleteSprintState",desc = "删除迭代状态")
    @ApiParam(name = "id",desc = "迭代状态id",required = true)
    public Result<Void> deleteSprintState(@NotNull String id){
        sprintStateService.deleteSprintState(id);

        return Result.ok();
    }


    @RequestMapping(path="/findSprintState",method = RequestMethod.POST)
    @ApiMethod(name = "findSprintState",desc = "根据id查找迭代状态")
    @ApiParam(name = "id",desc = "迭代状态id",required = true)
    public Result<SprintState> findSprintState(@NotNull String id){
        SprintState sprintState = sprintStateService.findSprintState(id);

        return Result.ok(sprintState);
    }

    @RequestMapping(path="/findAllSprintState",method = RequestMethod.POST)
    @ApiMethod(name = "findAllSprintState",desc = "查找所有迭代状态")
    public Result<List<SprintState>> findAllSprintState(){
        List<SprintState> sprintStateList = sprintStateService.findAllSprintState();

        return Result.ok(sprintStateList);
    }


    @RequestMapping(path = "/findSprintStateList",method = RequestMethod.POST)
    @ApiMethod(name = "findSprintStateList",desc = "根据条件查询迭代状态列表")
    @ApiParam(name = "sprintStateQuery",desc = "迭代状态搜索模型",required = true)
    public Result<List<SprintState>> findSprintStateList(@RequestBody @Valid @NotNull SprintStateQuery sprintStateQuery){
        List<SprintState> sprintStateList = sprintStateService.findSprintStateList(sprintStateQuery);

        return Result.ok(sprintStateList);
    }


    @RequestMapping(path = "/findSprintStatePage",method = RequestMethod.POST)
    @ApiMethod(name = "findSprintStatePage",desc = "根据条件按分页查询迭代状态列表")
    @ApiParam(name = "sprintStateQuery",desc = "迭代状态搜索模型",required = true)
    public Result<Pagination<SprintState>> findSprintStatePage(@RequestBody @Valid @NotNull SprintStateQuery sprintStateQuery){
        Pagination<SprintState> pagination = sprintStateService.findSprintStatePage(sprintStateQuery);

        return Result.ok(pagination);
    }

}
