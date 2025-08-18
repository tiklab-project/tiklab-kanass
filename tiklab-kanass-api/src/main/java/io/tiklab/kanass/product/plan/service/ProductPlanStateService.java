package io.tiklab.kanass.product.plan.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.product.plan.model.ProductPlanState;
import io.tiklab.kanass.product.plan.model.ProductPlanStateQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 迭代状态服务
*/
@JoinProvider(model = ProductPlanState.class)
public interface ProductPlanStateService {

    /**
    * 创建迭代状态
    * @param productPlanState
    * @return
    */
    String createProductPlanState(@NotNull @Valid ProductPlanState productPlanState);

    /**
    * 更新迭代状态
    * @param productPlanState
    */
    void updateProductPlanState(@NotNull @Valid ProductPlanState productPlanState);

    /**
    * 删除迭代状态
    * @param id
    */
    void deleteProductPlanState(@NotNull String id);
    @FindOne
    ProductPlanState findOne(@NotNull String id);
    @FindList
    List<ProductPlanState> findList(List<String> idList);

    /**
    * 根据id查找迭代状态
    * @param id
    * @return
    */
    ProductPlanState findProductPlanState(@NotNull String id);

    /**
    * 查找所有迭代状态
    * @return
    */
    @FindAll
    List<ProductPlanState> findAllProductPlanState();

    /**
    * 根据条件查询迭代状态列表
    * @param productPlanStateQuery
    * @return
    */
    List<ProductPlanState> findProductPlanStateList(ProductPlanStateQuery productPlanStateQuery);

    /**
    * 根据条件按分页查询迭代状态列表
    * @param productPlanStateQuery
    * @return
    */
    Pagination<ProductPlanState> findProductPlanStatePage(ProductPlanStateQuery productPlanStateQuery);

}