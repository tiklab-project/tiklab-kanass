package io.tiklab.kanass.test.func.cases.service;

import io.tiklab.kanass.test.func.cases.model.FuncUnitCase;
import io.tiklab.kanass.test.func.cases.model.FuncUnitCaseQuery;
import io.tiklab.kanass.test.test.model.TestCaseQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 功能用例 服务
*/
@JoinProvider(model = FuncUnitCase.class)
public interface FuncUnitCaseService {

    /**
    * 创建功能用例
    * @param unitCase
    * @return
    */
    String createFuncUnitCase(@NotNull @Valid FuncUnitCase unitCase);

    /**
    * 更新功能用例
    * @param unitCase
    */
    void updateFuncUnitCase(@NotNull @Valid FuncUnitCase unitCase);

    /**
    * 删除功能用例
    * @param id
    */
    void deleteFuncUnitCase(@NotNull String id);

    @FindOne
    FuncUnitCase findOne(@NotNull String id);

    @FindList
    List<FuncUnitCase> findList(List<String> idList);

    /**
    * 根据id查找功能用例
    * @param id
    * @return
    */
    FuncUnitCase findFuncUnitCase(@NotNull String id);

    /**
    * 查找所有功能用例
    * @return
    */
    @FindAll
    List<FuncUnitCase> findAllFuncUnitCase();

    /**
    * 根据查询参数查询列表功能用例
    * @param functionTestCaseQuery
    * @return
    */
    List<FuncUnitCase> findFuncUnitCaseList(FuncUnitCaseQuery functionTestCaseQuery);

    /**
    * 根据查询参数按分页查询功能用例
    * @param functionTestCaseQuery
    * @return
    */
    Pagination<FuncUnitCase> findFuncUnitCasePage(FuncUnitCaseQuery functionTestCaseQuery);

    /**
     * 通过testCaseQuery查询功能用例
     * @param testCaseQuery
     * @return
     */
    List<FuncUnitCase> findFuncUnitCaseListByTestCase(TestCaseQuery testCaseQuery);


}