package io.thoughtware.kanass.workitem.controller;

import io.thoughtware.kanass.workitem.model.WorkTypeDm;
import io.thoughtware.kanass.workitem.model.WorkTypeDmQuery;
import io.thoughtware.kanass.workitem.service.WorkTypeDmService;
import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.annotation.Api;
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
 * 项目的事项类型控制器
 */
@RestController
@RequestMapping("/workTypeDm")
@Api(name = "WorkTypeDmController",desc = "WorkTypeDmController")
public class WorkTypeDmController {

    private static Logger logger = LoggerFactory.getLogger(WorkTypeDmController.class);

    @Autowired
    private WorkTypeDmService workTypeDmService;

    @RequestMapping(path="/createWorkTypeDm",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkTypeDm",desc = "创建事项类型")
    @ApiParam(name = "workTypeDm",desc = "项目下事项类型模型",required = true)
    public Result<WorkTypeDm> createWorkTypeDm(@RequestBody @NotNull @Valid WorkTypeDm workTypeDm){
        WorkTypeDm id = workTypeDmService.createWorkTypeDm(workTypeDm);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkTypeDm",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkTypeDm",desc = "更新事项类型")
    @ApiParam(name = "workTypeDm",desc = "项目下事项类型模型",required = true)
    public Result<Void> updateWorkTypeDm(@RequestBody @NotNull @Valid WorkTypeDm workTypeDm){
        workTypeDmService.updateWorkTypeDm(workTypeDm);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkTypeDm",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkTypeDm",desc = "删除事项类型")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWorkTypeDm(@NotNull String id){
        workTypeDmService.deleteWorkTypeDm(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkTypeDm",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkTypeDm",desc = "根据id查找事项类型")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WorkTypeDm> findWorkTypeDm(@NotNull String id){
        WorkTypeDm workTypeDm = workTypeDmService.findWorkTypeDm(id);

        return Result.ok(workTypeDm);
    }

    @RequestMapping(path="/findAllWorkTypeDm",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWorkTypeDm",desc = "查找所有事项类型列表")
    public Result<List<WorkTypeDm>> findAllWorkTypeDm(){
        List<WorkTypeDm> workTypeDmList = workTypeDmService.findAllWorkTypeDm();

        return Result.ok(workTypeDmList);
    }

    @RequestMapping(path = "/findWorkTypeDmList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkTypeDmList",desc = "根据条件查询事项类型列表")
    @ApiParam(name = "workTypeDmQuery",desc = "项目下事项类型搜索条件模型",required = true)
    public Result<List<WorkTypeDm>> findWorkTypeDmList(@RequestBody @Valid @NotNull WorkTypeDmQuery workTypeDmQuery){
        List<WorkTypeDm> workTypeDmList = workTypeDmService.findWorkTypeDmListNoRepeat(workTypeDmQuery);

        return Result.ok(workTypeDmList);
    }

    @RequestMapping(path = "/findWorkTypeDmPage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkTypeDmPage",desc = "根据条件按分页查询事项类型列表")
    @ApiParam(name = "workTypeDmQuery",desc = "项目下事项类型搜索条件模型",required = true)
    public Result<Pagination<WorkTypeDm>> findWorkTypeDmPage(@RequestBody @Valid @NotNull WorkTypeDmQuery workTypeDmQuery){
        Pagination<WorkTypeDm> pagination = workTypeDmService.findWorkTypeDmPage(workTypeDmQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findDmWorkTypeByCode",method = RequestMethod.POST)
    @ApiMethod(name = "findDmWorkTypeByCode",desc = "根据编码，项目id 查找事项类型")
    @ApiParam(name = "code",desc = "项目id",required = true)
    public Result<WorkTypeDm> findDmWorkTypeByCode(@NotNull String projectId, @NotNull String code){
        WorkTypeDm workTypeDm = workTypeDmService.findDmWorkTypeByCode(projectId, code);

        return Result.ok(workTypeDm);
    }

}
