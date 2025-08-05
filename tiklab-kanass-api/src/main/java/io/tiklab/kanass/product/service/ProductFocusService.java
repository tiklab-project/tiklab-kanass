package io.tiklab.kanass.product.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.product.model.ProductFocus;
import io.tiklab.kanass.product.model.ProductFocusQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ProductFocusService {
    /**
     * 创建收藏的产品
     * @param ProductFocus
     * @return
     */
    String createProductFocus(@NotNull @Valid ProductFocus ProductFocus);

    /**
     * 更新收藏的产品
     * @param ProductFocus
     */
    void updateProductFocus(@NotNull @Valid ProductFocus ProductFocus);

    /**
     * 删除收藏的产品
     * @param id
     */
    void deleteProductFocus(@NotNull String id);

    /**
     * 根据id 查找收藏产品
     * @param id
     * @return
     */
    ProductFocus findOne(@NotNull String id);

    /**
     * 根据多个ids 查找关注产品列表
     * @param idList
     * @return
     */
    List<ProductFocus> findList(List<String> idList);

    /**
     * 根据id 查找收藏的产品
     * @param id
     * @return
     */
    ProductFocus findProductFocus(@NotNull String id);

    /**
     * 查找所有收藏的产品
     * @return
     */
    List<ProductFocus> findAllProductFocus();

    /**
     * 根据条件查询收藏产品列表
     * @param ProductFocusQuery
     * @return
     */
    List<ProductFocus> findProductFocusList(ProductFocusQuery ProductFocusQuery);

    /**
     * 根据条件按分页查询收藏产品列表
     * @param ProductFocusQuery
     * @return
     */
    Pagination<ProductFocus> findProductFocusPage(ProductFocusQuery ProductFocusQuery);

}
