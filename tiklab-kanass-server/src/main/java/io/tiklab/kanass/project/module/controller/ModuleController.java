package io.tiklab.kanass.project.module.controller;

import io.tiklab.kanass.project.module.model.Module;
import io.tiklab.kanass.project.module.model.ModuleQuery;
import io.tiklab.kanass.project.module.service.ModuleService;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;

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
 * 模块控制器
 */
@RestController
@RequestMapping("/module")
@Api(name = "ModuleController",desc = "模块管理")
public class ModuleController {

    private static Logger logger = LoggerFactory.getLogger(ModuleController.class);

    @Autowired
    private ModuleService moduleService;


    @RequestMapping(path="/createModule",method = RequestMethod.POST)
    @ApiMethod(name = "createModule",desc = "创建模块")
    @ApiParam(name = "module",desc = "模块DTO",required = true)
    public Result<String> createModule(@RequestBody @NotNull @Valid @ApiParam Module module){
        String id = moduleService.createModule(module);

        return Result.ok(id);
    }


    @RequestMapping(path="/updateModule",method = RequestMethod.POST)
    @ApiMethod(name = "updateModule",desc = "更新模块")
    @ApiParam(name = "module",desc = "模块DTO",required = true)
    public Result<Void> updateModule(@RequestBody @NotNull @Valid @ApiParam Module module){
        moduleService.updateModule(module);

        return Result.ok();
    }


    @RequestMapping(path="/deleteModule",method = RequestMethod.POST)
    @ApiMethod(name = "deleteModule",desc = "根据模块ID删除模块")
    @ApiParam(name = "id",desc = "模块ID",required = true)
    public Result<Void> deleteModule(@NotNull String id){
        moduleService.deleteModule(id);

        return Result.ok();
    }


    @RequestMapping(path="/findModule",method = RequestMethod.POST)
    @ApiMethod(name = "findModule",desc = "根据模块ID查找模块")
    @ApiParam(name = "id",desc = "模块ID",required = true)
    public Result<Module> findModule(@NotNull String id){
        Module module = moduleService.findModule(id);

        return Result.ok(module);
    }

    @RequestMapping(path="/findAllModule",method = RequestMethod.POST)
    @ApiMethod(name = "findAllModule",desc = "查找所有模块")
    public Result<List<Module>> findAllModule(){
        List<Module> moduleList = moduleService.findAllModule();

        return Result.ok(moduleList);
    }


    @RequestMapping(path = "/findModuleList",method = RequestMethod.POST)
    @ApiMethod(name = "findModuleList",desc = "根据查询对象查询模块列表")
    @ApiParam(name = "moduleQuery",desc = "模块查询对象",required = true)
    public Result<List<Module>> findModuleList(@RequestBody @Valid @NotNull ModuleQuery moduleQuery){
        List<Module> moduleList = moduleService.findModuleList(moduleQuery);

        return Result.ok(moduleList);
    }

    @RequestMapping(path = "/findSeleteParentModuleList",method = RequestMethod.POST)
    @ApiMethod(name = "findSeleteParentModuleList",desc = "根据查询对象查询模块列表")
    @ApiParam(name = "id",desc = "模块查询对象",required = true)
    public Result<List<Module>> findSeleteParentModuleList(@NotNull String id){
        List<Module> moduleList = moduleService.findSeleteParentModuleList(id);

        return Result.ok(moduleList);
    }

    @RequestMapping(path = "/findModuleListTree",method = RequestMethod.POST)
    @ApiMethod(name = "findModuleListTree",desc = "根据查询对象查询模块列表")
    @ApiParam(name = "moduleQuery",desc = "模块查询对象",required = true)
    public Result<List<Module>> findModuleListTree(@RequestBody @Valid @NotNull ModuleQuery moduleQuery){
        List<Module> moduleList = moduleService.findModuleListTree(moduleQuery);

        return Result.ok(moduleList);
    }

    @RequestMapping(path = "/findModulePage",method = RequestMethod.POST)
    @ApiMethod(name = "findModulePage",desc = "根据查询对象按分页查询模块列表")
    @ApiParam(name = "moduleQuery",desc = "模块查询对象",required = true)
    public Result<Pagination<Module>> findModulePage(@RequestBody @Valid @NotNull ModuleQuery moduleQuery){
        Pagination<Module> pagination = moduleService.findModulePage(moduleQuery);

        return Result.ok(pagination);
    }

}
