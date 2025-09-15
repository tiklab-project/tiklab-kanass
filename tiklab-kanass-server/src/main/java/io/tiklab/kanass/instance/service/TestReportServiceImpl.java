package io.tiklab.kanass.instance.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.instance.dao.TestReportDao;
import io.tiklab.kanass.instance.entity.TestReportEntity;
import io.tiklab.kanass.instance.model.*;
import io.tiklab.kanass.test.test.service.TestCaseService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
* 目录 服务
*/
@Service
public class TestReportServiceImpl implements TestReportService {

    @Autowired
    TestReportDao testReportDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    InstanceService instanceService;

//    @Autowired
//    WorkItemBindService workItemBindService;


    @Override
    public String createTestReport(@NotNull @Valid TestReport testReport) {
        TestReportEntity testReportEntity = BeanMapper.map(testReport, TestReportEntity.class);
        String userId = LoginContext.getLoginId();
        testReportEntity.setCreateUser(userId);
        testReportEntity.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
        testReportEntity.setStartTime(testReportEntity.getCreateTime());
        testReportEntity.setEndTime(testReportEntity.getCreateTime());
        return testReportDao.createTestReport(testReportEntity);
    }

    @Override
    public void updateTestReport(@NotNull @Valid TestReport testReport) {
        TestReportEntity testReportEntity = BeanMapper.map(testReport, TestReportEntity.class);

        testReportDao.updateTestReport(testReportEntity);
    }

    @Override
    public void deleteTestReport(@NotNull String id) {
        testReportDao.deleteTestReport(id);
    }

    @Override
    public void deleteAllTestReport(String projectId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(TestReportEntity.class)
                .eq("projectId",projectId)
                .get();
        testReportDao.deleteAllCategory(deleteCondition);
    }

    @Override
    public TestReport findOne(String id) {
        TestReportEntity testReportEntity = testReportDao.findTestReport(id);

        TestReport testReport = BeanMapper.map(testReportEntity, TestReport.class);
        return testReport;
    }

    @Override
    public List<TestReport> findList(List<String> idList) {
        List<TestReportEntity> testReportEntityList =  testReportDao.findTestReportList(idList);

        List<TestReport> testReportList =  BeanMapper.mapList(testReportEntityList, TestReport.class);
        return testReportList;
    }

    @Override
    public TestReport findTestReport(@NotNull String id) {
        TestReport testReport = findOne(id);

        joinTemplate.joinQuery(testReport);
        return testReport;
    }

    @Override
    public List<TestReport> findAllTestReport() {
        List<TestReportEntity> testReportEntityList =  testReportDao.findAllTestReport();

        List<TestReport> testReportList =  BeanMapper.mapList(testReportEntityList, TestReport.class);

        joinTemplate.joinQuery(testReportList);
        return testReportList;
    }

    @Override
    public List<TestReport> findTestReportList(TestReportQuery testReportQuery) {
        List<TestReportEntity> testReportEntityList = testReportDao.findTestReportList(testReportQuery);

        List<TestReport> testReportList = BeanMapper.mapList(testReportEntityList, TestReport.class);

        joinTemplate.joinQuery(testReportList);

        return testReportList;
    }

    @Override
    public Pagination<TestReport> findTestReportPage(TestReportQuery testReportQuery) {

        Pagination<TestReportEntity> pagination = testReportDao.findTestReportPage(testReportQuery);

        List<TestReport> testReportList = BeanMapper.mapList(pagination.getDataList(), TestReport.class);

        joinTemplate.joinQuery(testReportList);

        return PaginationBuilder.build(pagination, testReportList);
    }

    @Override
    public ReportSummary getTestReportSummary(String testReportId) {
        TestReport testReport = findTestReport(testReportId);

        if (testReport == null) {
            return new ReportSummary();
        }

        ReportSummary reportSummary = new ReportSummary();

        // 获取实例状态摘要
        InstanceQuery instanceQuery = new InstanceQuery();
        instanceQuery.setTestPlanId(testReport.getTestPlan().getId());
        instanceQuery.setStartTime(testReport.getStartTime());
        instanceQuery.setEndTime(testReport.getEndTime());
        InstanceStatusSummary instanceStatusSummary = instanceService.getInstanceStatusSummary(instanceQuery);
        reportSummary.setInstanceStatusSummary(instanceStatusSummary);

        // 获取并计算缺陷摘要
//        WorkItemBindQuery workItemBindQuery = new WorkItemBindQuery();
//        workItemBindQuery.setTestPlanId(testReport.getTestPlan().getId());
//        workItemBindQuery.setStartTime(testReport.getStartTime());
//        workItemBindQuery.setEndTime(testReport.getEndTime());
//        List<WorkItemBind> workItemBindList = workItemBindService.findWorkItemBindList(workItemBindQuery);
//
//        if (workItemBindList != null && !workItemBindList.isEmpty()) {
//            DefectSummary defectSummary = calculateDefectSummary(workItemBindList);
//            reportSummary.setDefectSummary(defectSummary);
//        }else {
//            reportSummary.setDefectSummary(new DefectSummary());
//        }

        return reportSummary;
    }

    /**
     * 提取出的私有方法，专门用于计算缺陷摘要
     */
//    private DefectSummary calculateDefectSummary(List<WorkItemBind> workItemBindList) {
//        DefectSummary defectSummary = new DefectSummary();
//        // 总数
//        defectSummary.setTotal(workItemBindList.size());
//
//        for (WorkItemBind workItemBind : workItemBindList) {
//            WorkItem workItem = workItemBind.getWorkItem();
//            if (workItem == null) {
//                continue; // 如果 workItem 为空，直接跳过此次循环
//            }
//
//            if (workItem.getWorkStatusCode() != null) {
//                switch (workItem.getWorkStatusCode()) {
//                    case "DONE":
//                        defectSummary.setDoneStatusCount(defectSummary.getDoneStatusCount() + 1);
//                        break;
//                    case "PROGRESS":
//                        defectSummary.setProgressStatusCount(defectSummary.getProgressStatusCount() + 1);
//                        break;
//                    case "TODO":
//                        defectSummary.setTodoStatusCount(defectSummary.getTodoStatusCount() + 1);
//                        break;
//                }
//            }
//
//            // 同样，对优先级使用 switch
//            if (workItem.getWorkPriority() != null && workItem.getWorkPriority().getValue() != null) {
//                switch (workItem.getWorkPriority().getValue()) {
//                    case "low":
//                        defectSummary.setLowCount(defectSummary.getLowCount() + 1);
//                        break;
//                    case "medium":
//                        defectSummary.setMediumCount(defectSummary.getMediumCount() + 1);
//                        break;
//                    case "high":
//                        defectSummary.setHighCount(defectSummary.getHighCount() + 1);
//                        break;
//                }
//            }
//        }
//        return defectSummary;
//    }

}