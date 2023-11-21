package io.tiklab.teamwire.support.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.teamwire.support.model.Recent;
import io.tiklab.teamwire.support.model.RecentQuery;
import io.tiklab.teamwire.support.service.RecentService;
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
 * 最近访问的控制器
 */
@RestController
@RequestMapping("/recent")
@Api(name = "RecentController",desc = "RecentController")
public class RecentController {

    private static Logger logger = LoggerFactory.getLogger(RecentController.class);

    @Autowired
    private RecentService recentService;

    @RequestMapping(path="/createRecent",method = RequestMethod.POST)
    @ApiMethod(name = "createRecent",desc = "创建最近访问的")
    @ApiParam(name = "recent",desc = "最近访问的模型",required = true)
    public Result<String> createRecent(@RequestBody @NotNull @Valid Recent recent){
        String id = recentService.createRecent(recent);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRecent",method = RequestMethod.POST)
    @ApiMethod(name = "updateRecent",desc = "更新最近访问的")
    @ApiParam(name = "recent",desc = "最近访问的模型",required = true)
    public Result<Void> updateRecent(@RequestBody @NotNull @Valid Recent recent){
        recentService.updateRecent(recent);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRecent",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRecent",desc = "删除最近访问的")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRecent(@NotNull String id){
        recentService.deleteRecent(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRecent",method = RequestMethod.POST)
    @ApiMethod(name = "findRecent",desc = "根据id 查找最近访问的")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Recent> findRecent(@NotNull String id){
        Recent recent = recentService.findRecent(id);

        return Result.ok(recent);
    }

    @RequestMapping(path="/findAllRecent",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRecent",desc = "查找所有最近访问的")
    public Result<List<Recent>> findAllRecent(){
        List<Recent> recentList = recentService.findAllRecent();

        return Result.ok(recentList);
    }

    @RequestMapping(path = "/findRecentList",method = RequestMethod.POST)
    @ApiMethod(name = "findRecentList",desc = "查询最近访问列表")
    @ApiParam(name = "recentQuery",desc = "查找最近访问的添加模型",required = true)
    public Result<List<Recent>> findRecentList(@RequestBody @Valid @NotNull RecentQuery recentQuery){
        List<Recent> recentList = recentService.findRecentList(recentQuery);

        return Result.ok(recentList);
    }

    @RequestMapping(path = "/findRecentListToModel",method = RequestMethod.POST)
    @ApiMethod(name = "findRecentListToModel",desc = "查询最近访问列表")
    @ApiParam(name = "recentQuery",desc = "查找最近访问的添加模型",required = true)
    public Result<List<Recent>> findRecentListToModel(@RequestBody @Valid @NotNull RecentQuery recentQuery){
        List<Recent> recentList = recentService.findRecentListToModel(recentQuery);

        return Result.ok(recentList);
    }


    @RequestMapping(path = "/findRecentPage",method = RequestMethod.POST)
    @ApiMethod(name = "findRecentPage",desc = "按分页查询最近访问的")
    @ApiParam(name = "recentQuery",desc = "查找最近访问的添加模型",required = true)
    public Result<Pagination<Recent>> findRecentPage(@RequestBody @Valid @NotNull RecentQuery recentQuery){
        Pagination<Recent> pagination = recentService.findRecentPage(recentQuery);

        return Result.ok(pagination);
    }

}
