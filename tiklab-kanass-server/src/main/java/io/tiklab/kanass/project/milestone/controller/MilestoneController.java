package io.tiklab.kanass.project.milestone.controller;

import io.tiklab.kanass.project.milestone.model.Milestone;
import io.tiklab.kanass.project.milestone.model.MilestoneQuery;
import io.tiklab.kanass.project.milestone.service.MilestoneService;
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
 * 里程碑控制器
 */
@RestController
@RequestMapping("/milestone")
//@Api(name = "MilestoneController",desc = "MilestoneController")
public class MilestoneController {

    private static Logger logger = LoggerFactory.getLogger(MilestoneController.class);

    @Autowired
    private MilestoneService milestoneService;

    @RequestMapping(path="/createMilestone",method = RequestMethod.POST)
    //@ApiMethod(name = "createMilestone",desc = "创建里程碑")
    //@ApiParam(name = "milestone",desc = "里程碑模型",required = true)
    public Result<String> createMilestone(@RequestBody @NotNull @Valid Milestone milestone){
        String id = milestoneService.createMilestone(milestone);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateMilestone",method = RequestMethod.POST)
    //@ApiMethod(name = "updateMilestone",desc = "更新里程碑")
    //@ApiParam(name = "milestone",desc = "里程碑模型",required = true)
    public Result<Void> updateMilestone(@RequestBody @NotNull @Valid Milestone milestone){
        milestoneService.updateMilestone(milestone);

        return Result.ok();
    }

    @RequestMapping(path="/deleteMilestone",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteMilestone",desc = "根据id删除里程碑")
    //@ApiParam(name = "id",desc = "里程碑id",required = true)
    public Result<Void> deleteMilestone(@NotNull String id){
        milestoneService.deleteMilestone(id);

        return Result.ok();
    }

    @RequestMapping(path="/findMilestone",method = RequestMethod.POST)
    //@ApiMethod(name = "findMilestone",desc = "根据id查找里程碑")
    //@ApiParam(name = "id",desc = "里程碑id",required = true)
    public Result<Milestone> findMilestone(@NotNull String id){
        Milestone milestone = milestoneService.findMilestone(id);

        return Result.ok(milestone);
    }

    @RequestMapping(path="/findAllMilestone",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllMilestone",desc = "查找全部里程碑")
    public Result<List<Milestone>> findAllMilestone(){
        List<Milestone> milestoneList = milestoneService.findAllMilestone();

        return Result.ok(milestoneList);
    }

    @RequestMapping(path = "/findMilestoneList",method = RequestMethod.POST)
    //@ApiMethod(name = "findMilestoneList",desc = "根据条件查找里程碑列表")
    //@ApiParam(name = "milestoneQuery",desc = "里程碑搜索参数模型",required = true)
    public Result<List<Milestone>> findMilestoneList(@RequestBody @Valid @NotNull MilestoneQuery milestoneQuery){
        List<Milestone> milestoneList = milestoneService.findMilestoneList(milestoneQuery);

        return Result.ok(milestoneList);
    }

    @RequestMapping(path = "/findMilestonePage",method = RequestMethod.POST)
    //@ApiMethod(name = "findMilestonePage",desc = "根据条件按分页查找里程碑列表")
    //@ApiParam(name = "milestoneQuery",desc = "里程碑搜索参数模型",required = true)
    public Result<Pagination<Milestone>> findMilestonePage(@RequestBody @Valid @NotNull MilestoneQuery milestoneQuery){
        Pagination<Milestone> pagination = milestoneService.findMilestonePage(milestoneQuery);

        return Result.ok(pagination);
    }

}
