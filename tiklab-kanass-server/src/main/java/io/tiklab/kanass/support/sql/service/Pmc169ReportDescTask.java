package io.tiklab.kanass.support.sql.service;

import io.tiklab.dsm.support.DsmProcessTask;
import io.tiklab.kanass.test.instance.model.TestReport;
import io.tiklab.kanass.test.instance.service.TestReportService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Pmc169ReportDescTask implements DsmProcessTask {

    @Autowired
    private TestReportService testReportService;

    @Override
    public void execute() {
        // 把描述改成类似事项的富文本输入框
        List<TestReport> testReportList = testReportService.findAllTestReport();
        for (TestReport testReport : testReportList) {
            if (StringUtils.isEmpty(testReport.getDesc())){
                testReport.setDesc("[{\"type\":\"paragraph\",\"children\":[{\"text\":\"\"}]}]");
            }else {
                String desc = replaceNewlinesWithSpaces(testReport.getDesc());
                String result = "[{\"type\":\"paragraph\",\"children\":[{\"text\":\"" + desc + "\"}]}]";
                testReport.setDesc(result);
            }

            testReportService.updateTestReport(testReport);
        }
    }

    /**
     * 将字符串中的换行符替换为空格
     * @param input 原始字符串
     * @return 替换后的字符串（所有换行符被替换为空格）
     */
    public static String replaceNewlinesWithSpaces(String input) {
        if (input == null) {
            return null;
        }
        // 替换所有换行符（包括\n, \r, \r\n等）为空格
        return input.replaceAll("\\r\\n|\\r|\\n", " ");
    }
}
