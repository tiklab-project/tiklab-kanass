package io.tiklab.kanass.test.instance.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.test.instance.model.ReportSummary;
import io.tiklab.kanass.test.instance.model.TestReport;
import io.tiklab.kanass.test.instance.model.TestReportQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 测试报告 服务模型
*/
@JoinProvider(model = TestReport.class)
public interface TestReportService {

    /**
    * 创建测试报告
    * @param testReport
    * @return
    */
    String createTestReport(@NotNull @Valid TestReport testReport);

    /**
    * 更新
    * @param testReport
    */
    void updateTestReport(@NotNull @Valid TestReport testReport);

    /**
    * 删除测试报告
    * @param id
    */
    void deleteTestReport(@NotNull String id);

    /**
     * 通过caseId删除
     * @param caseId
     */
    void deleteAllTestReport(String caseId);

    @FindOne
    TestReport findOne(@NotNull String id);

    @FindList
    List<TestReport> findList(List<String> idList);

    /**
    * 查找测试报告
    * @param id
    * @return
    */
    TestReport findTestReport(@NotNull String id);

    /**
    * 查找所有测试报告
    * @return
    */
    @FindAll
    List<TestReport> findAllTestReport();

    /**
    * 查询测试报告列表
    * @param testReportQuery
    * @return
    */
    List<TestReport> findTestReportList(TestReportQuery testReportQuery);

    /**
    * 按分页查询测试报告
    * @param testReportQuery
    * @return
    */
    Pagination<TestReport> findTestReportPage(TestReportQuery testReportQuery);


    /**
     * 获取测试报告统计信息
     * @return
     */
    ReportSummary getTestReportSummary(String testReportId);


}