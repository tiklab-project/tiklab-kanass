package io.tiklab.kanass.testplan.cases.service;

import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.kanass.instance.service.InstanceService;
import io.tiklab.kanass.testplan.cases.dao.TestPlanDao;
import io.tiklab.kanass.testplan.cases.entity.TestPlanEntity;
import io.tiklab.kanass.testplan.cases.model.TestPlan;
import io.tiklab.kanass.testplan.cases.model.TestPlanQuery;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

/**
* 测试计划 服务
*/
@Service
@Exporter
public class TestPlanServiceImpl implements TestPlanService {

    @Autowired
    TestPlanDao testPlanDao;

    @Autowired
    TestPlanCaseService testPlanCaseService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    InstanceService instanceService;


    @Override
    public String createTestPlan(@NotNull @Valid TestPlan testPlan) {
        TestPlanEntity testPlanEntity = BeanMapper.map(testPlan, TestPlanEntity.class);
        testPlanEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //状态
        testPlanEntity.setState(0);

        return testPlanDao.createTestPlan(testPlanEntity);
    }

    @Override
    public void updateTestPlan(@NotNull @Valid TestPlan testPlan) {
        TestPlanEntity testPlanEntity = BeanMapper.map(testPlan, TestPlanEntity.class);
        testPlanEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        testPlanDao.updateTestPlan(testPlanEntity);
    }

    @Override
    public void deleteTestPlan(@NotNull String id) {
        testPlanDao.deleteTestPlan(id);

        testPlanCaseService.deleteAllTestPlanCase(id);

        instanceService.deleteAllInstance(id);
    }

    @Override
    public void deleteAllTestPlan(String projectId) {
        TestPlanQuery testPlanQuery = new TestPlanQuery();
        testPlanQuery.setProjectId(projectId);
        List<TestPlan> testPlanList = findTestPlanList(testPlanQuery);
        for(TestPlan testPlan: testPlanList){
            deleteTestPlan(testPlan.getId());
        }
    }


    @Override
    public TestPlan findOne(String id) {
        TestPlanEntity testPlanEntity = testPlanDao.findTestPlan(id);

        TestPlan testPlan = BeanMapper.map(testPlanEntity, TestPlan.class);
        return testPlan;
    }

    @Override
    public int findTestPlanNum(String projectId) {
        int testPlanNum = testPlanDao.findTestPlanNum(projectId);
        return testPlanNum;
    }

    @Override
    public List<TestPlan> findList(List<String> idList) {
        List<TestPlanEntity> testPlanEntityList =  testPlanDao.findTestPlanList(idList);

        List<TestPlan> testPlanList =  BeanMapper.mapList(testPlanEntityList,TestPlan.class);
        return testPlanList;
    }

    @Override
    public TestPlan findTestPlan(@NotNull String id) {
        TestPlan testPlan = findOne(id);

        joinTemplate.joinQuery(testPlan);
        return testPlan;
    }

    @Override
    public List<TestPlan> findAllTestPlan() {
        List<TestPlanEntity> testPlanEntityList =  testPlanDao.findAllTestPlan();

        List<TestPlan> testPlanList =  BeanMapper.mapList(testPlanEntityList,TestPlan.class);

        joinTemplate.joinQuery(testPlanList);
        return testPlanList;
    }

    @Override
    public List<TestPlan> findTestPlanList(TestPlanQuery testPlanQuery) {
        List<TestPlanEntity> testPlanEntityList = testPlanDao.findTestPlanList(testPlanQuery);

        List<TestPlan> testPlanList = BeanMapper.mapList(testPlanEntityList,TestPlan.class);

        joinTemplate.joinQuery(testPlanList);

        return testPlanList;
    }

    @Override
    public Pagination<TestPlan> findTestPlanPage(TestPlanQuery testPlanQuery) {
        Pagination<TestPlanEntity>  pagination = testPlanDao.findTestPlanPage(testPlanQuery);
        List<TestPlan> testPlanList = BeanMapper.mapList(pagination.getDataList(),TestPlan.class);
        joinTemplate.joinQuery(testPlanList);

        //添加测试计划关联的用例数
        for (TestPlan testPlan:testPlanList){
//            if(testPlan.getType()== MagicValue.TEST_TYPE_FUNCTION){
//                continue;
//            }

            int planCaseNum = testPlanCaseService.findPlanCaseNum(testPlan.getId());
            testPlan.setTestCaseNum(planCaseNum);
        }

        return PaginationBuilder.build(pagination,testPlanList);
    }


}