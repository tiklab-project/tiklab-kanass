package io.tiklab.kanass.product.plan.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.product.plan.model.ProductPlanFocus;
import io.tiklab.kanass.product.plan.model.ProductPlanFocusQuery;
import io.tiklab.kanass.product.plan.service.ProductPlanFocusService;
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
 * 产品计划收藏控制器
 */
@RestController
@RequestMapping("/productPlanFocus")
public class ProductPlanFocusController {

    private static Logger logger = LoggerFactory.getLogger(ProductPlanFocusController.class);

    @Autowired
    private ProductPlanFocusService productPlanFocusService;

    @RequestMapping(path="/createProductPlanFocus",method = RequestMethod.POST)
    public Result<String> createProductPlanFocus(@RequestBody @NotNull @Valid ProductPlanFocus productPlanFocus){
        String id = productPlanFocusService.createProductPlanFocus(productPlanFocus);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateProductPlanFocus",method = RequestMethod.POST)
    public Result<Void> updateProductPlanFocus(@RequestBody @NotNull @Valid ProductPlanFocus productPlanFocus){
        productPlanFocusService.updateProductPlanFocus(productPlanFocus);

        return Result.ok();
    }

    @RequestMapping(path="/deleteProductPlanFocus",method = RequestMethod.POST)
    public Result<Void> deleteProductPlanFocus(@NotNull String id){
        productPlanFocusService.deleteProductPlanFocus(id);

        return Result.ok();
    }

    @RequestMapping(path="/deleteProductPlanFocusByQuery",method = RequestMethod.POST)
    public Result<Void> deleteProductPlanFocusByQuery(@RequestBody @Valid @NotNull ProductPlanFocusQuery productPlanFocusQuery){
        productPlanFocusService.deleteProductPlanFocusByQuery(productPlanFocusQuery);

        return Result.ok();
    }

    @RequestMapping(path="/findProductPlanFocus",method = RequestMethod.POST)
    public Result<ProductPlanFocus> findProductPlanFocus(@NotNull String id){
        ProductPlanFocus productPlanFocus = productPlanFocusService.findProductPlanFocus(id);

        return Result.ok(productPlanFocus);
    }

    @RequestMapping(path="/findAllProductPlanFocus",method = RequestMethod.POST)
    public Result<List<ProductPlanFocus>> findAllProductPlanFocus(){
        List<ProductPlanFocus> productPlanFocusList = productPlanFocusService.findAllProductPlanFocus();

        return Result.ok(productPlanFocusList);
    }

    @RequestMapping(path = "/findProductPlanFocusList",method = RequestMethod.POST)
    public Result<List<ProductPlanFocus>> findProductPlanFocusList(@RequestBody @Valid @NotNull ProductPlanFocusQuery productPlanFocusQuery){
        List<ProductPlanFocus> productPlanFocusList = productPlanFocusService.findProductPlanFocusList(productPlanFocusQuery);

        return Result.ok(productPlanFocusList);
    }

    @RequestMapping(path = "/findProductPlanFocusPage",method = RequestMethod.POST)
    public Result<Pagination<ProductPlanFocus>> findProductPlanFocusPage(@RequestBody @Valid @NotNull ProductPlanFocusQuery productPlanFocusQuery){
        Pagination<ProductPlanFocus> pagination = productPlanFocusService.findProductPlanFocusPage(productPlanFocusQuery);

        return Result.ok(pagination);
    }

}
