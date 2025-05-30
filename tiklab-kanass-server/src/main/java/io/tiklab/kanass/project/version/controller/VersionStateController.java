package io.tiklab.kanass.project.version.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.kanass.project.version.model.VersionState;
import io.tiklab.kanass.project.version.model.VersionStateQuery;
import io.tiklab.kanass.project.version.service.VersionStateService;
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
 * 迭代状态控制器
 */
@RestController
@RequestMapping("/versionState")
//@Api(name = "VersionStateController",desc = "VersionStateController")
public class VersionStateController {

    private static Logger logger = LoggerFactory.getLogger(VersionStateController.class);

    @Autowired
    private VersionStateService versionStateService;


    @RequestMapping(path="/createVersionState",method = RequestMethod.POST)
    //@ApiMethod(name = "createVersionState",desc = "创建迭代状态")
    //@ApiParam(name = "versionState",desc = "迭代状态模板",required = true)
    public Result<String> createVersionState(@RequestBody @NotNull @Valid VersionState versionState){
        String id = versionStateService.createVersionState(versionState);

        return Result.ok(id);
    }


    @RequestMapping(path="/updateVersionState",method = RequestMethod.POST)
    //@ApiMethod(name = "updateVersionState",desc = "更新迭代状态")
    //@ApiParam(name = "versionState",desc = "迭代状态模板",required = true)
    public Result<Void> updateVersionState(@RequestBody @NotNull @Valid VersionState versionState){
        versionStateService.updateVersionState(versionState);

        return Result.ok();
    }


    @RequestMapping(path="/deleteVersionState",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteVersionState",desc = "删除迭代状态")
    //@ApiParam(name = "id",desc = "迭代状态id",required = true)
    public Result<Void> deleteVersionState(@NotNull String id){
        versionStateService.deleteVersionState(id);

        return Result.ok();
    }


    @RequestMapping(path="/findVersionState",method = RequestMethod.POST)
    //@ApiMethod(name = "findVersionState",desc = "根据id查找迭代状态")
    //@ApiParam(name = "id",desc = "迭代状态id",required = true)
    public Result<VersionState> findVersionState(@NotNull String id){
        VersionState versionState = versionStateService.findVersionState(id);

        return Result.ok(versionState);
    }

    @RequestMapping(path="/findAllVersionState",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllVersionState",desc = "查找所有迭代状态")
    public Result<List<VersionState>> findAllVersionState(){
        List<VersionState> versionStateList = versionStateService.findAllVersionState();

        return Result.ok(versionStateList);
    }


    @RequestMapping(path = "/findVersionStateList",method = RequestMethod.POST)
    //@ApiMethod(name = "findVersionStateList",desc = "根据条件查询迭代状态列表")
    //@ApiParam(name = "versionStateQuery",desc = "迭代状态搜索模型",required = true)
    public Result<List<VersionState>> findVersionStateList(@RequestBody @Valid @NotNull VersionStateQuery versionStateQuery){
        List<VersionState> versionStateList = versionStateService.findVersionStateList(versionStateQuery);

        return Result.ok(versionStateList);
    }


    @RequestMapping(path = "/findVersionStatePage",method = RequestMethod.POST)
    //@ApiMethod(name = "findVersionStatePage",desc = "根据条件按分页查询迭代状态列表")
    //@ApiParam(name = "versionStateQuery",desc = "迭代状态搜索模型",required = true)
    public Result<Pagination<VersionState>> findVersionStatePage(@RequestBody @Valid @NotNull VersionStateQuery versionStateQuery){
        Pagination<VersionState> pagination = versionStateService.findVersionStatePage(versionStateQuery);

        return Result.ok(pagination);
    }

}
