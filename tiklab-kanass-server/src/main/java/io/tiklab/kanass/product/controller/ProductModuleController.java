package io.tiklab.kanass.product.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.product.model.ProductModule;
import io.tiklab.kanass.product.model.ProductModuleQuery;
import io.tiklab.kanass.product.service.ProductModuleService;
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

@RestController
@RequestMapping("/productModule")
public class ProductModuleController {
    private static Logger logger = LoggerFactory.getLogger(ProductModuleController.class);

    @Autowired
    private ProductModuleService moduleService;


    @RequestMapping(path="/createModule",method = RequestMethod.POST)
    public Result<String> createModule(@RequestBody @NotNull @Valid ProductModule module){
        String id = moduleService.createModule(module);

        return Result.ok(id);
    }


    @RequestMapping(path="/updateModule",method = RequestMethod.POST)
    public Result<Void> updateModule(@RequestBody @NotNull @Valid ProductModule module){
        moduleService.updateModule(module);

        return Result.ok();
    }


    @RequestMapping(path="/deleteModule",method = RequestMethod.POST)
    public Result<Boolean> deleteModule(@NotNull String id){
        return Result.ok(moduleService.deleteModule(id));
    }


    @RequestMapping(path="/findModule",method = RequestMethod.POST)
    public Result<ProductModule> findModule(@NotNull String id){
        ProductModule module = moduleService.findModule(id);

        return Result.ok(module);
    }

    @RequestMapping(path="/findAllModule",method = RequestMethod.POST)
    public Result<List<ProductModule>> findAllModule(){
        List<ProductModule> moduleList = moduleService.findAllModule();

        return Result.ok(moduleList);
    }


    @RequestMapping(path = "/findModuleList",method = RequestMethod.POST)
    public Result<List<ProductModule>> findModuleList(@RequestBody @Valid @NotNull ProductModuleQuery moduleQuery){
        List<ProductModule> moduleList = moduleService.findModuleList(moduleQuery);

        return Result.ok(moduleList);
    }

    @RequestMapping(path = "/findSeleteParentModuleList",method = RequestMethod.POST)
    public Result<List<ProductModule>> findSeleteParentModuleList(@NotNull String id){
        List<ProductModule> moduleList = moduleService.findSeleteParentModuleList(id);

        return Result.ok(moduleList);
    }

    @RequestMapping(path = "/findModuleListTree",method = RequestMethod.POST)
    public Result<List<ProductModule>> findModuleListTree(@RequestBody @Valid @NotNull ProductModuleQuery moduleQuery){
        List<ProductModule> moduleList = moduleService.findModuleListTree(moduleQuery);

        return Result.ok(moduleList);
    }

    @RequestMapping(path = "/findModulePage",method = RequestMethod.POST)
    public Result<Pagination<ProductModule>> findModulePage(@RequestBody @Valid @NotNull ProductModuleQuery moduleQuery){
        Pagination<ProductModule> pagination = moduleService.findModulePage(moduleQuery);

        return Result.ok(pagination);
    }
}
