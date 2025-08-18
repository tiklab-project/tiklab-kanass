package io.tiklab.kanass.product.product.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.product.product.model.ProductModule;
import io.tiklab.kanass.product.product.model.ProductModuleQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@JoinProvider(model = ProductModule.class)
public interface ProductModuleService {
    /**
     * 创建模块
     * @param module
     * @return
     */
    String createModule(@NotNull @Valid ProductModule module);

    /**
     * 更新模块
     * @param module
     */
    void updateModule(@NotNull @Valid ProductModule module);

    /**
     * 删除模块
     * @param id
     */
    Boolean deleteModule(@NotNull String id);

    @FindOne
    ProductModule findOne(@NotNull String id);

    /**
     * 根据多个id 查找模块列表
     * @param idList
     * @return
     */
    @FindList
    List<ProductModule> findList(@NotNull List<String> idList);

    /**
     * 根据id查找模块
     * @param id
     * @return
     */
    ProductModule findModule(@NotNull String id);

    /**
     * 查找所有模块
     * @return
     */
    @FindAll
    List<ProductModule> findAllModule();

    /**
     * 查找模块列表
     * @param moduleQuery
     * @return
     */
    List<ProductModule> findModuleList(ProductModuleQuery moduleQuery);

    List<ProductModule> findSeleteParentModuleList(String id);
    List<ProductModule> findModuleListTree(ProductModuleQuery moduleQuery);
    /**
     * 根据条件按分页查找模块
     * @param moduleQuery
     * @return
     */
    Pagination<ProductModule> findModulePage(ProductModuleQuery moduleQuery);

}
