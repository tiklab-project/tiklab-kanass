package io.tiklab.kanass.test.testplan.instance.service;

import io.tiklab.kanass.test.testplan.instance.model.FunctionTestReport;

public interface FunctionTestReportService {

    /**
     * testPlanId一样
     * @param testPlanId
     * @return
     */
    FunctionTestReport getFunctionTestReport(String testPlanId);

}
