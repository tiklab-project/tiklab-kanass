package io.tiklab.kanass.workitem.controller;

import io.tiklab.kanass.workitem.model.WorkType;
import io.tiklab.kanass.workitem.model.WorkTypeQuery;
import io.tiklab.kanass.workitem.service.WorkTypeService;
import io.tiklab.postin.annotation.Api;
import io.tiklab.form.form.model.Form;
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
 * 事项类型控制器
 */
@RestController
@RequestMapping("/workType")
@Api(name = "WorkTypeController",desc = "事项类型管理")
public class WorkTypeController {

    private static Logger logger = LoggerFactory.getLogger(WorkTypeController.class);


    @Autowired
    private WorkTypeService workTypeService;

    @RequestMapping(path="/createWorkType",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkType",desc = "创建事项类型")
    @ApiParam(name = "workType",desc = "事项类型DTO",required = true)
    public Result<String> createWorkType(@RequestBody @NotNull @Valid WorkType workType){
        String id = workTypeService.createWorkType(workType);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateWorkType",method = RequestMethod.POST)
    @ApiMethod(name = "updateWorkType",desc = "更新事项类型")
    @ApiParam(name = "workType",desc = "事项类型DTO",required = true)
    public Result<Void> updateWorkType(@RequestBody @NotNull @Valid WorkType workType){
        workTypeService.updateWorkType(workType);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkType",method = RequestMethod.POST)
    @ApiMethod(name = "deleteWorkType",desc = "根据ID删除事项类型")
    @ApiParam(name = "id",desc = "事项ID",required = true)
    public Result<String> deleteWorkType(@NotNull String id){
        String result = workTypeService.deleteWorkType(id);

        return Result.ok(result);
    }

    @RequestMapping(path="/findWorkType",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkType",desc = "根据ID查找事项类型")
    @ApiParam(name = "id",desc = "事项ID",required = true)
    public Result<WorkType> findWorkType(@NotNull String id){
        WorkType workType = workTypeService.findWorkType(id);

        return Result.ok(workType);
    }

    @RequestMapping(path="/findAllWorkType",method = RequestMethod.POST)
    @ApiMethod(name = "findAllWorkType",desc = "查找所有事项类型")
    public Result<List<WorkType>> findAllWorkType(){
        List<WorkType> workTypeList = workTypeService.findAllWorkType();

        return Result.ok(workTypeList);
    }


    @RequestMapping(path = "/findWorkTypeList",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkTypeList",desc = "根据查询对象查找事项类型列表")
    @ApiParam(name = "workTypeQuery",desc = "查询对象",required = true)
    public Result<List<WorkType>> findWorkTypeList(@RequestBody @Valid @NotNull WorkTypeQuery workTypeQuery){
        List<WorkType> workTypeList = workTypeService.findWorkTypeList(workTypeQuery);

        return Result.ok(workTypeList);
    }


    @RequestMapping(path = "/findWorkTypePage",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkTypePage",desc = "根据查询对象按分页查询事项类型列表")
    @ApiParam(name = "workTypeQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkType>> findWorkTypePage(@RequestBody @Valid @NotNull WorkTypeQuery workTypeQuery){
        Pagination<WorkType> pagination = workTypeService.findWorkTypePage(workTypeQuery);

        return Result.ok(pagination);
    }


    @RequestMapping(path="/findFormConfig",method = RequestMethod.POST)
    @ApiMethod(name = "findFormConfig",desc = "根据事项类型ID查找关联表单配置")
    @ApiParam(name = "formId",desc = "事项类型ID",required = true)
    public Result<Form> findFormConfig(@NotNull String formId){
        Form form = workTypeService.findFormConfig(formId);

        return Result.ok(form);
    }



    @RequestMapping(path = "/findWorkTypeListByCode",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkTypeListByCode",desc = "根据查询对象查找事项类型列表")
    @ApiParam(name = "code",desc = "查询对象",required = true)
    public Result<String> findWorkTypeListByCode(@NotNull String code){
        String workTypeId= workTypeService.findWorkTypeByCode(code);

        return Result.ok(workTypeId);
    }
}
