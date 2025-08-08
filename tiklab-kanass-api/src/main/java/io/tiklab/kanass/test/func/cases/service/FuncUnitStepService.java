package io.tiklab.kanass.test.func.cases.service;


import io.tiklab.kanass.test.func.cases.model.FuncUnitStep;
import io.tiklab.kanass.test.func.cases.model.FuncUnitStepQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 功能用例下步骤 服务接口
*/
@JoinProvider(model = FuncUnitStep.class)
public interface FuncUnitStepService {

    /**
    * 创建功能用例下步骤
    * @param funcUnitStep
    * @return
    */
    String createFuncUnitStep(@NotNull @Valid FuncUnitStep funcUnitStep);

    /**
    * 更新功能用例下步骤
    * @param funcUnitStep
    */
    void updateFuncUnitStep(@NotNull @Valid FuncUnitStep funcUnitStep);

    /**
    * 删除功能用例下步骤
    * @param id
    */
    void deleteFuncUnitStep(@NotNull String id);
    @FindOne
    FuncUnitStep findOne(@NotNull String id);
    @FindList
    List<FuncUnitStep> findList(List<String> idList);

    /**
    * 根据id查找功能用例下步骤
    * @param id
    * @return
    */
    FuncUnitStep findFuncUnitStep(@NotNull String id);



    /**
    * 查找所有功能用例下步骤
    * @return
    */
    @FindAll
    List<FuncUnitStep> findAllFuncUnitStep();

    /**
    * 根据查询参数查询功能用例下步骤列表
    * @param funcUnitStepQuery
    * @return
    */
    List<FuncUnitStep> findFuncUnitStepList(FuncUnitStepQuery funcUnitStepQuery);

    /**
    * 根据查询参数按分页查询功能用例下步骤
    * @param funcUnitStepQuery
    * @return
    */
    Pagination<FuncUnitStep> findFuncUnitStepPage(FuncUnitStepQuery funcUnitStepQuery);

}