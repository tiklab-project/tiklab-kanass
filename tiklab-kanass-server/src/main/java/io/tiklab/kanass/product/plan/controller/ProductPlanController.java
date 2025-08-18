package io.tiklab.kanass.product.plan.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.product.plan.model.ProductPlan;
import io.tiklab.kanass.product.plan.model.ProductPlanQuery;
import io.tiklab.kanass.product.plan.service.ProductPlanService;
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
import java.util.Map;

/**
 * 产品计划控制器
 */
@RestController
@RequestMapping("/productPlan")
public class ProductPlanController {

    private static Logger logger = LoggerFactory.getLogger(ProductPlanController.class);

    @Autowired
    private ProductPlanService productPlanService;


    @RequestMapping(path="/createProductPlan",method = RequestMethod.POST)
    public Result<String> createProductPlan(@RequestBody @NotNull @Valid ProductPlan productPlan){
        String id = productPlanService.createProductPlan(productPlan);

        return Result.ok(id);
    }


    @RequestMapping(path="/updateProductPlan",method = RequestMethod.POST)
    public Result<Void> updateProductPlan(@RequestBody @NotNull @Valid ProductPlan productPlan){
        productPlanService.updateProductPlan(productPlan);

        return Result.ok();
    }


    @RequestMapping(path="/deleteProductPlan",method = RequestMethod.POST)
    public Result<Void> deleteProductPlan(@NotNull String id){
        productPlanService.deleteProductPlan(id);

        return Result.ok();
    }


    @RequestMapping(path="/findProductPlan",method = RequestMethod.POST)
    public Result<ProductPlan> findProductPlan(@NotNull String id){
        ProductPlan productPlan = productPlanService.findProductPlan(id);

        return Result.ok(productPlan);
    }

    @RequestMapping(path="/findAllProductPlan",method = RequestMethod.POST)
    public Result<List<ProductPlan>> findAllProductPlan(){
        List<ProductPlan> productPlanList = productPlanService.findAllProductPlan();

        return Result.ok(productPlanList);
    }


    @RequestMapping(path = "/findProductPlanList",method = RequestMethod.POST)
    public Result<List<ProductPlan>> findProductPlanList(@RequestBody @Valid @NotNull ProductPlanQuery productPlanQuery){
        List<ProductPlan> productPlanList = productPlanService.findProductPlanList(productPlanQuery);

        return Result.ok(productPlanList);
    }

    @RequestMapping(path = "/findSelectProductPlanList",method = RequestMethod.POST)
    public Result<List<ProductPlan>> findSelectProductPlanList(@RequestBody @Valid @NotNull ProductPlanQuery productPlanQuery){
        List<ProductPlan> productPlanList = productPlanService.findSelectProductPlanList(productPlanQuery);

        return Result.ok(productPlanList);
    }


    @RequestMapping(path = "/findProductPlanPage",method = RequestMethod.POST)
    public Result<Pagination<ProductPlan>> findProductPlanPage(@RequestBody @Valid @NotNull ProductPlanQuery productPlanQuery){
        Pagination<ProductPlan> pagination = productPlanService.findProductPlanPage(productPlanQuery);

        return Result.ok(pagination);
    }
    @RequestMapping(path = "/findFocusProductPlanList",method = RequestMethod.POST)
    public Result<Pagination<ProductPlan>> findFocusProductPlanList(@RequestBody @Valid @NotNull ProductPlanQuery productPlanQuery){
        List<ProductPlan> productPlanList = productPlanService.findFocusProductPlanList(productPlanQuery);

        return Result.ok(productPlanList);
    }

    @RequestMapping(path = "/findWorkProductPlanList",method = RequestMethod.POST)
    public Result<Pagination<ProductPlan>> findWorkProductPlanList(@NotNull String workId){
        List<ProductPlan> productPlanList = productPlanService.findWorkProductPlanList(workId);

        return Result.ok(productPlanList);
    }

    @RequestMapping(path = "/findProductPlanCount",method = RequestMethod.POST)
    public Result<Map<String, Integer>> findProductPlanCount(@RequestBody ProductPlanQuery productPlanQuery){
        Map<String, Integer> productPlanCount = productPlanService.findProductPlanCount(productPlanQuery);
        return Result.ok(productPlanCount);
    }
}
