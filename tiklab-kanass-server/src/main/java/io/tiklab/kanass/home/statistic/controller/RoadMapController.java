package io.tiklab.kanass.home.statistic.controller;

import io.tiklab.kanass.home.statistic.service.RoadMapService;
import io.tiklab.kanass.project.version.model.ProjectVersion;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.core.Result;
import io.tiklab.kanass.project.epic.model.Epic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 路线图控制器
 */
@RestController
@RequestMapping("/roadMap")
@Api(name = "RoadMapController",desc = "路线图")
public class RoadMapController {
    @Autowired
    RoadMapService roadMapService;

    @RequestMapping(path="/findSprintRoadMap",method = RequestMethod.POST)
    @ApiMethod(name = "findSprintRoadMap",desc = "迭代路线图")
    @ApiParam(name = "projectId",desc = "项目id",required = true)
    public Result<List<Sprint>> findSprintRoadMap(@NotNull String projectId){
      List<Sprint> springList = roadMapService.findSprintRoadMap(projectId);
      return Result.ok(springList);
    }

    @RequestMapping(path="/findVersionRoadMap",method = RequestMethod.POST)
    @ApiMethod(name = "findVersionRoadMap",desc = "版本路线图")
    @ApiParam(name = "projectId",desc = "项目id",required = true)
    public Result<List<ProjectVersion>> findVersionRoadMap(@NotNull String projectId){
        List<ProjectVersion> projectVersionList = roadMapService.findVersionRoadMap(projectId);
        return Result.ok(projectVersionList);
    }

    @RequestMapping(path="/findEpicRoadMap",method = RequestMethod.POST)
    @ApiMethod(name = "findEpicRoadMap",desc = "史诗路线图， 弃用")
    @ApiParam(name = "projectId",desc = "项目id",required = true)
    public Result<List<Epic>> findEpicRoadMap(@NotNull String projectId){
        List<Epic> epicList = roadMapService.findEpicRoadMap(projectId);
        return Result.ok(epicList);
    }
}
