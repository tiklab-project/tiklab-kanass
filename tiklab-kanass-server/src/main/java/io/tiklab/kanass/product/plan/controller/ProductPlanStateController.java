package io.tiklab.kanass.product.plan.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.product.plan.model.ProductPlanState;
import io.tiklab.kanass.product.plan.model.ProductPlanStateQuery;
import io.tiklab.kanass.product.plan.service.ProductPlanStateService;
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
 * 产品计划状态控制器
 */
@RestController
@RequestMapping("/productPlanState")
public class ProductPlanStateController {

    private static Logger logger = LoggerFactory.getLogger(ProductPlanStateController.class);

    @Autowired
    private ProductPlanStateService productPlanStateService;


    @RequestMapping(path="/createProductPlanState",method = RequestMethod.POST)
    public Result<String> createProductPlanState(@RequestBody @NotNull @Valid ProductPlanState productPlanState){
        String id = productPlanStateService.createProductPlanState(productPlanState);

        return Result.ok(id);
    }


    @RequestMapping(path="/updateProductPlanState",method = RequestMethod.POST)
    public Result<Void> updateProductPlanState(@RequestBody @NotNull @Valid ProductPlanState productPlanState){
        productPlanStateService.updateProductPlanState(productPlanState);

        return Result.ok();
    }


    @RequestMapping(path="/deleteProductPlanState",method = RequestMethod.POST)
    public Result<Void> deleteProductPlanState(@NotNull String id){
        productPlanStateService.deleteProductPlanState(id);

        return Result.ok();
    }


    @RequestMapping(path="/findProductPlanState",method = RequestMethod.POST)
    public Result<ProductPlanState> findProductPlanState(@NotNull String id){
        ProductPlanState productPlanState = productPlanStateService.findProductPlanState(id);

        return Result.ok(productPlanState);
    }

    @RequestMapping(path="/findAllProductPlanState",method = RequestMethod.POST)
    public Result<List<ProductPlanState>> findAllProductPlanState(){
        List<ProductPlanState> productPlanStateList = productPlanStateService.findAllProductPlanState();

        return Result.ok(productPlanStateList);
    }


    @RequestMapping(path = "/findProductPlanStateList",method = RequestMethod.POST)
    public Result<List<ProductPlanState>> findProductPlanStateList(@RequestBody @Valid @NotNull ProductPlanStateQuery productPlanStateQuery){
        List<ProductPlanState> productPlanStateList = productPlanStateService.findProductPlanStateList(productPlanStateQuery);

        return Result.ok(productPlanStateList);
    }


    @RequestMapping(path = "/findProductPlanStatePage",method = RequestMethod.POST)
    public Result<Pagination<ProductPlanState>> findProductPlanStatePage(@RequestBody @Valid @NotNull ProductPlanStateQuery productPlanStateQuery){
        Pagination<ProductPlanState> pagination = productPlanStateService.findProductPlanStatePage(productPlanStateQuery);

        return Result.ok(pagination);
    }

}
