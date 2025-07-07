package io.tiklab.kanass.project.module.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.JoinProvider;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.kanass.project.module.model.Module;
import io.tiklab.kanass.project.module.model.ModuleQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 模块服务接口
*/
@JoinProvider(model = Module.class)
public interface ModuleService {

    /**
    * 创建模块
    * @param module
    * @return
    */
    String createModule(@NotNull @Valid Module module);

    /**
    * 更新模块
    * @param module
    */
    void updateModule(@NotNull @Valid Module module);

    /**
    * 删除模块
    * @param id
    */
    Boolean deleteModule(@NotNull String id);

    @FindOne
    Module findOne(@NotNull String id);

    /**
     * 根据多个id 查找模块列表
     * @param idList
     * @return
     */
    @FindList
    List<Module> findList(@NotNull List<String> idList);

    /**
    * 根据id查找模块
    * @param id
    * @return
    */
    Module findModule(@NotNull String id);

    /**
    * 查找所有模块
    * @return
    */
    @FindAll
    List<Module> findAllModule();

    /**
     * 查找模块列表
     * @param moduleQuery
     * @return
     */
    List<Module> findModuleList(ModuleQuery moduleQuery);

    List<Module> findSeleteParentModuleList(String id);
    List<Module> findModuleListTree(ModuleQuery moduleQuery);
    /**
     * 根据条件按分页查找模块
     * @param moduleQuery
     * @return
     */
    Pagination<Module> findModulePage(ModuleQuery moduleQuery);

}