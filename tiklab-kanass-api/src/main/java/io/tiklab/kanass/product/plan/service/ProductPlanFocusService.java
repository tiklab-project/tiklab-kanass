package io.tiklab.kanass.product.plan.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.product.plan.model.ProductPlanFocus;
import io.tiklab.kanass.product.plan.model.ProductPlanFocusQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 产品计划收藏服务接口
*/
public interface ProductPlanFocusService {

    /**
    * 创建产品计划收藏
    * @param productPlanFocus
    * @return
    */
    String createProductPlanFocus(@NotNull @Valid ProductPlanFocus productPlanFocus);

    /**
    * 更新收藏的产品计划
    * @param productPlanFocus
    */
    void updateProductPlanFocus(@NotNull @Valid ProductPlanFocus productPlanFocus);

    /**
    * 删除收藏的产品计划
    * @param id
    */
    void deleteProductPlanFocus(@NotNull String id);

    /**
     * 根据添加删除收藏的产品计划
     * @param productPlanFocusQuery
     */
    void deleteProductPlanFocusByQuery(ProductPlanFocusQuery productPlanFocusQuery);

    /**
     * 根据id查找收藏的产品计划
     * @param id
     * @return
     */
    ProductPlanFocus findOne(@NotNull String id);

    /**
     * 根据多个产品计划id,查找收藏的产品计划
     * @param idList
     * @return
     */
    List<ProductPlanFocus> findList(List<String> idList);

    /**
    * 根据id查找产品计划收藏
    * @param id
    * @return
    */
    ProductPlanFocus findProductPlanFocus(@NotNull String id);

    /**
    * 查找所有收藏产品计划
    * @return
    */
    List<ProductPlanFocus> findAllProductPlanFocus();

    /**
    * 根据条件查询收藏的产品计划列表
    * @param productPlanFocusQuery
    * @return
    */
    List<ProductPlanFocus> findProductPlanFocusList(ProductPlanFocusQuery productPlanFocusQuery);

    /**
    * 根据条件按分页查询收藏的产品计划列表
    * @param productPlanFocusQuery
    * @return
    */
    Pagination<ProductPlanFocus> findProductPlanFocusPage(ProductPlanFocusQuery productPlanFocusQuery);

    List<String> findFocusProductPlanIds();
}