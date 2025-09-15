package io.tiklab.kanass.test.test.service;

import io.tiklab.kanass.test.test.model.TestCase;
import io.tiklab.kanass.test.test.model.TestCaseQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
* 测试用例 服务接口
*/
@JoinProvider(model = TestCase.class)
public interface TestCaseService {

    /**
    * 创建测试用例
    * @param testCase
    * @return
    */
    String createTestCase(@NotNull @Valid TestCase testCase);

    /**
    * 更新测试用例
    * @param testCase
    */
    void updateTestCase(@NotNull @Valid TestCase testCase);

    void updateTestCaseList(List<TestCase> testCaseList);

    /**
    * 删除测试用例
    * @param id
    */
    void deleteTestCase(@NotNull String id,String caseType);

    /**
     * 通过projectId删除用例
     * @param projectId
     */
    void deleteAllTestCase( String projectId);


    /**
     * 通过目录id删除
     * @param moduleId
     */
    void deleteTestCaseByModuleId(@NotNull String moduleId);

    @FindOne
    TestCase findOne(@NotNull String id);

    @FindList
    List<TestCase> findList(List<String> idList);

    /**
     * 查询测试用例总数
     * @param projectId
     * @return
     */
    int findTestCaseNum(String projectId);

    /**
     * 查询不同测试类型的数量，功能，接口，ui，性能
     * @param projectId
     * @return
     */
    HashMap<String, Integer> findDiffTypeCaseNum(String projectId);

    /**
    * 根据id查找测试用例
    * @param id
    * @return
    */
    TestCase findTestCase(@NotNull String id);

    /**
    * 查找所有测试用例
    * @return
    */
    @FindAll
    List<TestCase> findAllTestCase();

    /**
    * 根据查询参数查询测试用例列表
    * @param testCaseQuery
    * @return
    */
    List<TestCase> findTestCaseList(TestCaseQuery testCaseQuery);

    /**
    * 根据查询参数按分页查询测试用例
    * @param testCaseQuery
    * @return
    */
    Pagination<TestCase> findTestCasePage(TestCaseQuery testCaseQuery);

    /**
     * 根据查询参数获取各个优先级的用例数量
     * @param testCaseQuery
     * @return
     */
    HashMap<String, Integer> findTestCasePriorityLevelNum(TestCaseQuery testCaseQuery);

    /**
     * 根据查询参数获取各个测试类型的用例数量
     * @param testCaseQuery
     * @return
     */
    HashMap<String, Integer> findDiffTestCaseNum(TestCaseQuery testCaseQuery);

    /**
     * 获取该模块下的用例总数
     * @param moduleId
     * @return
     */
    int countCasesByModuleId(String moduleId);

    /**
     * 获取该模块下的我创建的、我负责的用例 数量
     * @param projectId
     * @return
     */
     HashMap<String, Integer> findCreateUserAndDirectorNum(String projectId);


}