package io.tiklab.kanass.workitem.controller;

import io.tiklab.kanass.workitem.model.WorkRelate;
import io.tiklab.kanass.workitem.model.WorkRelateQuery;
import io.tiklab.kanass.workitem.service.WorkRelateService;
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
import java.util.Map;

/**
 * 关联事项控制器
 */
@RestController
@RequestMapping("/workRelate")
@Api(name = "WorkRelateController",desc = "事项关联管理")
public class WorkRelateController {

    private static Logger logger = LoggerFactory.getLogger(WorkRelateController.class);

    @Autowired
    private WorkRelateService workRelateService;

    @RequestMapping(path="/createWorkRelate",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkRelate",desc = "创建关联事项")
    @ApiParam(name = "workRelate",desc = "关联事项模型",required = true)
    public Result<String> createWorkRelate(@RequestBody @NotNull @Valid WorkRelate workRelate){
        String id = workRelateService.createWorkRelate(workRelate);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkRelate",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkRelate",desc = "更新关联事项")
    @ApiParam(name = "workRelate",desc = "关联事项模型",required = true)
    public Result<Void> updateWorkRelate(@RequestBody @NotNull @Valid WorkRelate workRelate){
        workRelateService.updateWorkRelate(workRelate);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkRelate",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkRelate",desc = "删除关联事项")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteWorkRelate(@NotNull String id){
        workRelateService.deleteWorkRelate(id);

        return Result.ok();
    }

    @RequestMapping(path="/findWorkRelate",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkRelate",desc = "根据id查找关联事项")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WorkRelate> findWorkRelate(@NotNull String id){
        WorkRelate workRelate = workRelateService.findWorkRelate(id);

        return Result.ok(workRelate);
    }

    @RequestMapping(path="/findAllWorkRelate",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWorkRelate",desc = "查找所有关联事项")
    public Result<List<WorkRelate>> findAllWorkRelate(){
        List<WorkRelate> workRelateList = workRelateService.findAllWorkRelate();

        return Result.ok(workRelateList);
    }


    @RequestMapping(path = "/findWorkRelateList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkRelateList",desc = "查询关联事项列表")
    @ApiParam(name = "workRelateQuery",desc = "关联事项搜索条件模型",required = true)
    public Result<List<Map<String, Object>>> findWorkRelateList(@RequestBody @Valid @NotNull WorkRelateQuery workRelateQuery){
        List<Map<String, Object>> workRelateList = workRelateService.findWorkRelateList(workRelateQuery);

        return Result.ok(workRelateList);
    }


    @RequestMapping(path = "/findWorkRelatePage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkRelatePage",desc = "按分页查询关联事项列表")
    @ApiParam(name = "workRelateQuery",desc = "关联事项搜索条件模型",required = true)
    public Result<Pagination<WorkRelate>> findWorkRelatePage(@RequestBody @Valid @NotNull WorkRelateQuery workRelateQuery){
        Pagination<WorkRelate> pagination = workRelateService.findWorkRelatePage(workRelateQuery);

        return Result.ok(pagination);
    }

}
