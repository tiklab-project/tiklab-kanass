package io.tiklab.teamwire.project.version.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.teamwire.project.version.model.VersionFocus;
import io.tiklab.teamwire.project.version.model.VersionFocusQuery;
import io.tiklab.teamwire.project.version.service.VersionFocusService;
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

/**
 * 迭代收藏控制器
 */
@RestController
@RequestMapping("/versionFocus")
@Api(name = "VersionFocusController",desc = "VersionFocusController")
public class VersionFocusController {

    private static Logger logger = LoggerFactory.getLogger(VersionFocusController.class);

    @Autowired
    private VersionFocusService versionFocusService;

    @RequestMapping(path="/createVersionFocus",method = RequestMethod.POST)
    @ApiMethod(name = "createVersionFocus",desc = "创建迭代收藏")
    @ApiParam(name = "versionFocus",desc = "收藏的迭代模型",required = true)
    public Result<String> createVersionFocus(@RequestBody @NotNull @Valid VersionFocus versionFocus){
        String id = versionFocusService.createVersionFocus(versionFocus);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateVersionFocus",method = RequestMethod.POST)
    @ApiMethod(name = "updateVersionFocus",desc = "更新收藏的迭代")
    @ApiParam(name = "versionFocus",desc = "收藏的迭代模型",required = true)
    public Result<Void> updateVersionFocus(@RequestBody @NotNull @Valid VersionFocus versionFocus){
        versionFocusService.updateVersionFocus(versionFocus);

        return Result.ok();
    }

    @RequestMapping(path="/deleteVersionFocus",method = RequestMethod.POST)
    @ApiMethod(name = "deleteVersionFocus",desc = "删除收藏的迭代")
    @ApiParam(name = "id",desc = "迭代id",required = true)
    public Result<Void> deleteVersionFocus(@NotNull String id){
        versionFocusService.deleteVersionFocus(id);

        return Result.ok();
    }

    @RequestMapping(path="/deleteVersionFocusByQuery",method = RequestMethod.POST)
    @ApiMethod(name = "deleteVersionFocus",desc = "根据添加删除收藏的迭代")
    @ApiParam(name = "versionFocusQuery",desc = "迭代id",required = true)
    public Result<Void> deleteVersionFocusByQuery(@RequestBody @Valid @NotNull VersionFocusQuery versionFocusQuery){
        versionFocusService.deleteVersionFocusByQuery(versionFocusQuery);

        return Result.ok();
    }

    @RequestMapping(path="/findVersionFocus",method = RequestMethod.POST)
    @ApiMethod(name = "findVersionFocus",desc = "根据id查找迭代收藏")
    @ApiParam(name = "id",desc = "迭代id",required = true)
    public Result<VersionFocus> findVersionFocus(@NotNull String id){
        VersionFocus versionFocus = versionFocusService.findVersionFocus(id);

        return Result.ok(versionFocus);
    }

    @RequestMapping(path="/findAllVersionFocus",method = RequestMethod.POST)
    @ApiMethod(name = "findAllVersionFocus",desc = "查找所有收藏迭代")
    public Result<List<VersionFocus>> findAllVersionFocus(){
        List<VersionFocus> versionFocusList = versionFocusService.findAllVersionFocus();

        return Result.ok(versionFocusList);
    }

    @RequestMapping(path = "/findVersionFocusList",method = RequestMethod.POST)
    @ApiMethod(name = "findVersionFocusList",desc = "根据条件查询收藏的迭代列表")
    @ApiParam(name = "versionFocusQuery",desc = "收藏的迭代查找条件模型",required = true)
    public Result<List<VersionFocus>> findVersionFocusList(@RequestBody @Valid @NotNull VersionFocusQuery versionFocusQuery){
        List<VersionFocus> versionFocusList = versionFocusService.findVersionFocusList(versionFocusQuery);

        return Result.ok(versionFocusList);
    }

    @RequestMapping(path = "/findVersionFocusPage",method = RequestMethod.POST)
    @ApiMethod(name = "findVersionFocusPage",desc = "根据条件按分页查询收藏的迭代列表")
    @ApiParam(name = "versionFocusQuery",desc = "收藏的迭代查找条件模型",required = true)
    public Result<Pagination<VersionFocus>> findVersionFocusPage(@RequestBody @Valid @NotNull VersionFocusQuery versionFocusQuery){
        Pagination<VersionFocus> pagination = versionFocusService.findVersionFocusPage(versionFocusQuery);

        return Result.ok(pagination);
    }

}
