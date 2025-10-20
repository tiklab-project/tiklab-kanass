package io.tiklab.kanass.test.testcase.func.instance.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.test.testcase.func.instance.model.FunctionInstance;
import io.tiklab.kanass.test.testcase.func.instance.model.FunctionInstanceQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 功能用例历史实例 服务接口
*/
@JoinProvider(model = FunctionInstance.class)
public interface FunctionInstanceService {

    /**
    * 创建功能用例历史实例
    * @param functionInstance
    * @return
    */
    String createFunctionInstance(@NotNull @Valid FunctionInstance functionInstance);

    /**
    * 更新
    * @param functionInstance
    */
    void updateFunctionInstance(@NotNull @Valid FunctionInstance functionInstance);

    /**
    * 删除功能用例历史实例
    * @param id
    */
    void deleteFunctionInstance(@NotNull String id);

    @FindOne
    FunctionInstance findOne(@NotNull String id);

    @FindList
    List<FunctionInstance> findList(List<String> idList);

    /**
    * 通过id查找功能用例历史实例
    * @param id
    * @return
    */
    FunctionInstance findFunctionInstance(@NotNull String id);

    /**
    * 查找所有功能用例历史实例
    * @return
    */
    @FindAll
    List<FunctionInstance> findAllFunctionInstance();

    /**
    * 根据查询参数查询功能用例历史实例列表
    * @param scenInstanceQuery
    * @return
    */
    List<FunctionInstance> findFunctionInstanceList(FunctionInstanceQuery scenInstanceQuery);

    /**
     * 查询用例最近的实例列表
     * @param functionInstanceQuery
     * @return
     */
    List<FunctionInstance> findRecentCaseFunctionInstanceList(FunctionInstanceQuery functionInstanceQuery);

    /**
    * 根据查询按分页查询功能用例历史实例
    * @param scenInstanceQuery
    * @return
    */
    Pagination<FunctionInstance> findFunctionInstancePage(FunctionInstanceQuery scenInstanceQuery);


    /**
     * 设置功能用例历史实例
     * @param functionInstance
     * @return
     */
    String setFunctionResult(FunctionInstance functionInstance);
}