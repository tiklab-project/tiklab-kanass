package io.tiklab.kanass.sql.service;

import io.tiklab.dsm.support.DsmProcessTask;
import io.tiklab.kanass.common.IDGeneratorUtil;
import io.tiklab.kanass.test.test.model.TestCase;
import io.tiklab.kanass.test.test.service.TestCaseService;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.service.WorkItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KanassTest100Task implements DsmProcessTask {

    @Autowired
    private TestCaseService testCaseService;

    // 修改测试用例的编号
    @Override
    public void execute() {
        List<TestCase> allList = testCaseService.findAllTestCase();

        Map<String, List<TestCase>> allTestCase = allList.stream().collect(Collectors.groupingBy(
                item -> item.getProject().getId(),
                Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> list.stream()
                                .sorted(Comparator.comparing(TestCase::getCreateTime))
                                .collect(Collectors.toList())
                )
        ));

        for (String projectId : allTestCase.keySet()) {
            // 生成新的id
            String newId = IDGeneratorUtil.generateID();
            List<TestCase> testCaseList = allTestCase.get(projectId);
            int sort = 1;
            for (TestCase testCase : testCaseList) {
                testCase.setCaseKey(newId);
                testCase.setSort( sort);
                newId = IDGeneratorUtil.incrementID(newId);
                sort++;
                testCaseService.updateTestCase(testCase);
            }
        }


    }
}
