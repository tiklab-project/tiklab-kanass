package io.tiklab.kanass.test.testplan.instance.service;

import io.tiklab.kanass.test.testplan.instance.dao.FunctionTestReportDao;
import io.tiklab.kanass.test.testplan.instance.entity.FunctionTestReportEntity;
import io.tiklab.kanass.test.testplan.instance.model.FunctionTestReport;
import io.tiklab.toolkit.beans.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionTestReportServiceImpl implements FunctionTestReportService {


    @Autowired
    FunctionTestReportDao functionTestReportDao;

    @Override
    public FunctionTestReport getFunctionTestReport(String testPlanId) {
        FunctionTestReportEntity functionTestReportEntity = functionTestReportDao.getFunctionTestReport(testPlanId);
        FunctionTestReport functionTestReport = BeanMapper.map(functionTestReportEntity, FunctionTestReport.class);
        int successRate = (int) (((double) functionTestReport.getSuccessCount() / functionTestReport.getTotal()) * 100);
        functionTestReport.setSuccessRate(successRate);

        return functionTestReport;
    }
}
