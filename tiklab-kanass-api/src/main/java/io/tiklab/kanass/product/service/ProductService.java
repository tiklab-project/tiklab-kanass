package io.tiklab.kanass.product.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.product.model.Product;
import io.tiklab.kanass.product.model.ProductQuery;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.model.ProjectQuery;
import io.tiklab.kanass.projectset.model.ProjectSet;
import io.tiklab.kanass.projectset.model.ProjectSetQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@JoinProvider(model = Product.class)
public interface ProductService {
    /**
     * 创建产品
     * @param product
     * @return
     */
    String createProduct(@NotNull @Valid Product product);

    /**
     * 更新产品
     * @param product
     */
    void updateProduct(@NotNull @Valid Product product);

    /**
     * 删除产品
     * @param id
     */
    void deleteProduct(@NotNull String id);

    /**
     * 根据id查找产品
     * @param id
     * @return
     */
    @FindOne
    Product findOne(@NotNull String id);

    /**
     * 根据多个id,查找产品列表
     * @param idList
     * @return
     */
    @FindList
    List<Product> findList(List<String> idList);

    /**
     * 根据id查找产品
     * @param id
     * @return
     */
    Product findProduct(@NotNull String id);

    /**
     * 查找所有产品
     * @return
     */
    @FindAll
    List<Product> findAllProduct();

    /**
     * 根据条件查询产品列表
     * @param productQuery
     * @return
     */
    List<Product> findProductList(ProductQuery productQuery);

    /**
     * 根据条件按分页查询产品列表
     * @param productQuery
     * @return
     */
    Pagination<Product> findProductPage(ProductQuery productQuery);

    /**
     * 最近的产品
     * @param productQuery
     * @return
     */
    List<Product> findRecentProductList(ProductQuery productQuery);

    List<Product> findProductSortRecentTime(ProductQuery productQuery);

    /**
     * 查询产品数量 包括 所有 我收藏的 我创建的
     * @return
     */
    Map<String, Integer> findProductCount(ProductQuery productQuery);

    List<Product> findJoinProductList(ProductQuery productQuery);

    /**
     * 查询产品下面的项目列表
     * @param projectQuery
     * @return
     */
    List<Project> findProductDetailList(ProjectQuery projectQuery);

    Pagination<Project> findProductDetailPage(ProjectQuery projectQuery);
}
