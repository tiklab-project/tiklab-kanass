package io.tiklab.kanass.product.plan.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.product.plan.model.ProductPlan;
import io.tiklab.kanass.product.plan.model.ProductPlanQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
* 产品计划服务接口
*/
@JoinProvider(model = ProductPlan.class)
public interface ProductPlanService {

    /**
    * 创建产品计划
    * @param productPlan
    * @return
    */
    String createProductPlan(@NotNull @Valid ProductPlan productPlan);

    String createJiraProductPlan(@NotNull @Valid ProductPlan productPlan);

    /**
    * 更新产品计划
    * @param productPlan
    */
    void updateProductPlan(@NotNull @Valid ProductPlan productPlan);

    /**
    * 删除产品计划
    * @param id
    */
    void deleteProductPlan(@NotNull String id);

    /**
     * 根据产品计划id查找产品计划
     * @param id
     * @return
     */
    @FindOne
    ProductPlan findOne(@NotNull String id);

    /**
     * 根据多个id,查找产品计划列表
     * @param idList
     * @return
     */
    @FindList
    List<ProductPlan> findList(@NotNull List<String> idList);

    /**
    * 根据id查找产品计划列表
    * @param id
    * @return
    */
    ProductPlan findProductPlan(@NotNull String id);

    /**
    * 查找所有产品计划列表
    * @return
    */
    @FindAll
    List<ProductPlan> findAllProductPlan();

    /**
     * 根据条件查找产品计划列表
     * @param productPlanQuery
     * @return
     */
    List<ProductPlan> findProductPlanList(ProductPlanQuery productPlanQuery);

    List<ProductPlan> findSelectProductPlanList(ProductPlanQuery productPlanQuery);

    /**
     * 根据条件查找我收藏的产品计划
     * @param productPlanQuery
     * @return
     */
    List<ProductPlan> findFocusProductPlanList(ProductPlanQuery productPlanQuery);

    /**
     * 根据条件按照分页查找产品计划
     * @param productPlanQuery
     * @return
     */
    Pagination<ProductPlan> findProductPlanPage(ProductPlanQuery productPlanQuery);

    /**
     * 查找进行中的产品计划
     * @return
     */
    List<ProductPlan> findProgressProductPlan();

    /**
     * 根据事项id查找产品计划
     * @param workId
     */
    List<ProductPlan> findWorkProductPlanList(@NotNull String workId);

    /**
     * 查询产品计划个数
     * @param productPlanQuery
     * @return
     */
    Map<String, Integer> findProductPlanCount(ProductPlanQuery productPlanQuery);

}