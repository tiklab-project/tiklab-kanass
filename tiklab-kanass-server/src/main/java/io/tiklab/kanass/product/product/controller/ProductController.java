package io.tiklab.kanass.product.product.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.product.product.model.Product;
import io.tiklab.kanass.product.product.model.ProductQuery;
import io.tiklab.kanass.product.product.service.ProductService;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.model.ProjectQuery;
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

@RestController
@RequestMapping("/product")
public class ProductController {
    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(path="/createProduct",method = RequestMethod.POST)
    public Result<String> createProduct(@RequestBody @NotNull @Valid Product product){
        String id = productService.createProduct(product);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateProduct",method = RequestMethod.POST)
    public Result<Void> updateProduct(@RequestBody @NotNull @Valid Product product){
        productService.updateProduct(product);

        return Result.ok();
    }


    @RequestMapping(path="/deleteProduct",method = RequestMethod.POST)
    public Result<Void> deleteProduct(@NotNull String id){
        productService.deleteProduct(id);

        return Result.ok();
    }


    @RequestMapping(path="/findProduct",method = RequestMethod.POST)
    public Result<Product> findProduct(@NotNull String id){
        Product product = productService.findProduct(id);

        return Result.ok(product);
    }

    @RequestMapping(path="/findAllProduct",method = RequestMethod.POST)
    public Result<List<Product>> findAllProduct(){
        List<Product> productList = productService.findAllProduct();

        return Result.ok(productList);
    }


    @RequestMapping(path = "/findProductList",method = RequestMethod.POST)
    public Result<List<Product>> findProductList(@RequestBody @Valid ProductQuery productQuery){
        List<Product> productList = productService.findProductList(productQuery);

        return Result.ok(productList);
    }

//
//    @RequestMapping(path = "/findFocusProductList",method = RequestMethod.POST)
//    public Result<List<Product>> findFocusProductList(@RequestBody @Valid @NotNull ProductQuery productQuery){
//
//        List<Product> pagination = productService.findFocusProductList(productQuery);
//
//        return Result.ok(pagination);
//    }

    @RequestMapping(path = "/findRecentProductList",method = RequestMethod.POST)
    public Result<List<Product>> findRecentProductList(@RequestBody @Valid @NotNull ProductQuery productQuery){

        List<Product> pagination = productService.findRecentProductList(productQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findProductSortRecentTime",method = RequestMethod.POST)
    public Result<List<Product>> findProductSortRecentTime(@RequestBody @NotNull @Valid ProductQuery productQuery){
        List<Product> joinProductList = productService.findProductSortRecentTime(productQuery);
        return Result.ok(joinProductList);
    }

    @RequestMapping(path = "/findProjectList",method = RequestMethod.POST)
    public Result<Project> findProductDetailList(@RequestBody @Valid @NotNull ProjectQuery projectQuery){
        List<Project> projectList = productService.findProductDetailList(projectQuery);

        return Result.ok(projectList);
    }

    @RequestMapping(path = "/findProjectPage",method = RequestMethod.POST)
    public Result<Pagination<Project>> findProductDetailPage(@RequestBody @Valid @NotNull ProjectQuery projectQuery){
        Pagination<Project> projectPage = productService.findProductDetailPage(projectQuery);

        return Result.ok(projectPage);
    }
//
//    @RequestMapping(path = "/findProjectIsOrNotRe",method = RequestMethod.POST)
//    public Result<Map> findProjectIsOrNotRe(){
//        Map projectIsOrNotRe = productService.findProjectIsOrNotRe();
//
//        return Result.ok(projectIsOrNotRe);
//    }
//
//
//    @RequestMapping(path = "/addRelevance",method = RequestMethod.POST)
//    public Result addRelevance(@RequestBody @NotNull @Valid Product product){
//        productService.addRelevance(product);
//        return Result.ok();
//    }
//
    @RequestMapping(path = "/findJoinProductList",method = RequestMethod.POST)
    public Result<List<Product>> findJoinProductList(@RequestBody @NotNull @Valid ProductQuery productQuery){
        List<Product> joinProductList = productService.findJoinProductList(productQuery);
        return Result.ok(joinProductList);
    }

    @RequestMapping(path = "/findProductPage",method = RequestMethod.POST)
    public Result<Pagination<Product>> findProductPage(@RequestBody @NotNull @Valid ProductQuery productQuery){
        Pagination<Product> joinProductList = productService.findProductPage(productQuery);
        return Result.ok(joinProductList);
    }

    @RequestMapping(path = "/findProductCount",method = RequestMethod.POST)
    public Result<Map<String, Integer>> findProductCount(@RequestBody @NotNull @Valid ProductQuery productQuery){
        Map<String, Integer> productCount = productService.findProductCount(productQuery);
        return Result.ok(productCount);
    }

}
