package io.tiklab.kanass.testplan.cases.service;

import io.tiklab.kanass.testplan.cases.model.TestPlanQuery;
import io.tiklab.toolkit.join.annotation.JoinProvider;
import io.tiklab.kanass.testplan.cases.model.PlanCase;
import io.tiklab.kanass.testplan.cases.model.TestPlanCase;
import io.tiklab.kanass.testplan.cases.model.TestPlanCaseQuery;
import io.tiklab.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
* 测试计划绑定的用例 服务接口
*/
@JoinProvider(model = TestPlanCase.class)
public interface TestPlanCaseService {

    /**
    * 创建绑定的用例
    * @param testPlanCase
    * @return
    */
    String createTestPlanCase(@NotNull @Valid TestPlanCase testPlanCase);

    /**
    * 更新绑定的用例
    * @param testPlanCase
    */
    void updateTestPlanCase(@NotNull @Valid TestPlanCase testPlanCase);

    /**
    * 删除绑定的用例
    * @param id
    */
    void deleteTestPlanCase(@NotNull String id);
    void deleteAllTestPlanCase(String testPlanId);

    TestPlanCase findOne(@NotNull String id);

    List<TestPlanCase> findList(List<String> idList);

    /**
    * 根据id查找绑定的用例
    * @param id
    * @return
    */
    TestPlanCase findTestPlanCase(@NotNull String id);

    /**
    * 查找所有绑定的用例
    * @return
    */
    List<TestPlanCase> findAllTestPlanCase();

    /**
    * 查询绑定的用例 列表
    * @param testPlanCaseQuery
    * @return
    */
    List<TestPlanCase> findTestPlanCaseList(TestPlanCaseQuery testPlanCaseQuery);

    /**
     * 查询绑定的用例 数量
     * @param testPlanId
     * @return
     */
    int findPlanCaseNum(String testPlanId);

    List<PlanCase> findPlanCaseList(TestPlanCaseQuery testPlanCaseQuery);

    /**
    * 按分页查询绑定的用例
    * @param testPlanCaseQuery
    * @return
    */
    Pagination<TestPlanCase> findTestPlanCasePage(TestPlanCaseQuery testPlanCaseQuery);
    /**
     * 添加用例弹窗列表
     * @param testPlanCaseQuery
     * @return
     */
    Pagination<PlanCase> findPlanCasePage(TestPlanCaseQuery testPlanCaseQuery);

    /**
     * 通过用例查询计划下用例结果
     * @param testPlanQuery
     * @return
     */
    List<PlanCase> findPlanCaseResultListByCase(TestPlanCaseQuery testPlanCaseQuery);
    /**
     * 关联用例，剔除已关联的用例
     * @param testPlanCaseQuery
     * @return
     */
    Pagination<PlanCase> findTestCasePage(TestPlanCaseQuery testPlanCaseQuery);


    /**
     * 测试计划中关联用例
     * @param testPlanCaseList
     */
    void planBindCase(List<TestPlanCase> testPlanCaseList);

    /**
     * 判断用例是否被绑定
     * @param caseId
     * @return
     */
    Boolean isCaseExist(String caseId);

    /**
     * 获取不同类型的用例数量
     * @param testPlanId
     * @return
     */
    Map<String,Integer> getCaseTypeNum(String testPlanId);



}