package io.tiklab.kanass.product.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.product.model.Product;
import io.tiklab.kanass.product.model.ProductQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    Product findOne(@NotNull String id);

    /**
     * 根据多个id,查找产品列表
     * @param idList
     * @return
     */
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
}
