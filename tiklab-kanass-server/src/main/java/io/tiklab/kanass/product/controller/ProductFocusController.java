package io.tiklab.kanass.product.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.product.model.ProductFocus;
import io.tiklab.kanass.product.model.ProductFocusQuery;
import io.tiklab.kanass.product.service.ProductFocusService;
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
@RequestMapping("/productFocus")
public class ProductFocusController {
    private static Logger logger = LoggerFactory.getLogger(ProductFocusController.class);

    @Autowired
    private ProductFocusService productFocusService;

    @RequestMapping(path="/createProductFocus",method = RequestMethod.POST)
    public Result<String> createProductFocus(@RequestBody @NotNull @Valid ProductFocus productFocus){
        String id = productFocusService.createProductFocus(productFocus);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateProductFocus",method = RequestMethod.POST)
    public Result<Void> updateProductFocus(@RequestBody @NotNull @Valid ProductFocus productFocus){
        productFocusService.updateProductFocus(productFocus);

        return Result.ok();
    }

    @RequestMapping(path="/deleteProductFocus",method = RequestMethod.POST)
    public Result<Void> deleteProductFocus(@NotNull String id){
        productFocusService.deleteProductFocus(id);

        return Result.ok();
    }

    @RequestMapping(path="/findProductFocus",method = RequestMethod.POST)
    public Result<ProductFocus> findProductFocus(@NotNull String id){
        ProductFocus productFocus = productFocusService.findProductFocus(id);

        return Result.ok(productFocus);
    }

    @RequestMapping(path="/findAllProductFocus",method = RequestMethod.POST)
    public Result<List<ProductFocus>> findAllProductFocus(){
        List<ProductFocus> productFocusList = productFocusService.findAllProductFocus();

        return Result.ok(productFocusList);
    }

    @RequestMapping(path = "/findProductFocusList",method = RequestMethod.POST)
    public Result<List<ProductFocus>> findProductFocusList(@RequestBody @Valid @NotNull ProductFocusQuery productFocusQuery){
        List<ProductFocus> productFocusList = productFocusService.findProductFocusList(productFocusQuery);

        return Result.ok(productFocusList);
    }


    @RequestMapping(path = "/findProductFocusPage",method = RequestMethod.POST)
    public Result<Pagination<ProductFocus>> findProductFocusPage(@RequestBody @Valid @NotNull ProductFocusQuery productFocusQuery){
        Pagination<ProductFocus> pagination = productFocusService.findProductFocusPage(productFocusQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path="/deleteProductFocusByQuery",method = RequestMethod.POST)
    public Result<Void> deleteProductFocusByQuery(@RequestBody @NotNull @Valid ProductFocusQuery productFocusQuery){
        productFocusService.deleteProductFocusByQuery(productFocusQuery);

        return Result.ok();
    }
}
