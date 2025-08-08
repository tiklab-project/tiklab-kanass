package io.tiklab.kanass.testplan.cases.service;

import io.tiklab.kanass.project.module.model.Module;
import io.tiklab.kanass.project.module.service.ModuleService;
import io.tiklab.kanass.test.func.instance.service.FunctionInstanceService;
import io.tiklab.kanass.testplan.cases.dao.TestPlanCaseDao;
import io.tiklab.kanass.testplan.cases.entity.PlanCaseEntity;
import io.tiklab.kanass.testplan.cases.entity.TestPlanCaseEntity;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.testplan.cases.model.PlanCase;
import io.tiklab.kanass.testplan.cases.model.TestPlanCase;
import io.tiklab.kanass.testplan.cases.model.TestPlanCaseQuery;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
* 测试计划绑定的用例 服务
*/
@Service
public class TestPlanCaseServiceImpl implements TestPlanCaseService {

    @Autowired
    TestPlanCaseDao testPlanDetailDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ModuleService moduleService;

    @Autowired
    UserProcessor userService;

    @Autowired
    TestPlanService testPlanService;


    @Autowired
    FunctionInstanceService functionInstanceService;


    @Override
    public String createTestPlanCase(@NotNull @Valid TestPlanCase testPlanCase) {
        TestPlanCaseEntity testPlanCaseEntity = BeanMapper.map(testPlanCase, TestPlanCaseEntity.class);
        //初始化 2为未执行
        testPlanCaseEntity.setStatus(2);
        return testPlanDetailDao.createTestPlanCase(testPlanCaseEntity);
    }

    @Override
    public void updateTestPlanCase(@NotNull @Valid TestPlanCase testPlanCase) {
        TestPlanCaseEntity testPlanCaseEntity = BeanMapper.map(testPlanCase, TestPlanCaseEntity.class);

        testPlanDetailDao.updateTestPlanCase(testPlanCaseEntity);
    }

    @Override
    public void deleteTestPlanCase(@NotNull String id) {
        testPlanDetailDao.deleteTestPlanCase(id);
    }

    @Override
    public void deleteAllTestPlanCase(String testPlanId) {
        TestPlanCaseQuery testPlanCaseQuery = new TestPlanCaseQuery();
        testPlanCaseQuery.setTestPlanId(testPlanId);
        List<TestPlanCase> testPlanCaseList = findTestPlanCaseList(testPlanCaseQuery);
        for(TestPlanCase testPlanCase:testPlanCaseList){
            deleteTestPlanCase(testPlanCase.getId());
        }
    }


    @Override
    public TestPlanCase findOne(String id) {
        TestPlanCaseEntity testPlanCaseEntity = testPlanDetailDao.findTestPlanCase(id);

        TestPlanCase testPlanCase = BeanMapper.map(testPlanCaseEntity, TestPlanCase.class);
        return testPlanCase;
    }

    @Override
    public List<TestPlanCase> findList(List<String> idList) {
        List<TestPlanCaseEntity> testPlanCaseEntityList =  testPlanDetailDao.findTestPlanCaseList(idList);

        List<TestPlanCase> testPlanCaseList =  BeanMapper.mapList(testPlanCaseEntityList, TestPlanCase.class);
        return testPlanCaseList;
    }

    @Override
    public TestPlanCase findTestPlanCase(@NotNull String id) {
        TestPlanCase testPlanCase = findOne(id);

        joinTemplate.joinQuery(testPlanCase);
        return testPlanCase;
    }

    @Override
    public List<TestPlanCase> findAllTestPlanCase() {
        List<TestPlanCaseEntity> testPlanCaseEntityList =  testPlanDetailDao.findAllTestPlanCase();

        List<TestPlanCase> testPlanCaseList =  BeanMapper.mapList(testPlanCaseEntityList, TestPlanCase.class);

        joinTemplate.joinQuery(testPlanCaseList);
        return testPlanCaseList;
    }

    @Override
    public List<TestPlanCase> findTestPlanCaseList(TestPlanCaseQuery testPlanCaseQuery) {
        List<TestPlanCaseEntity> testPlanCaseEntityList = testPlanDetailDao.findTestPlanCaseList(testPlanCaseQuery);

        List<TestPlanCase> testPlanCaseList = BeanMapper.mapList(testPlanCaseEntityList, TestPlanCase.class);

        joinTemplate.joinQuery(testPlanCaseList);

        return testPlanCaseList;
    }

    @Override
    public int findPlanCaseNum(String testPlanId) {
        int planCaseNum = testPlanDetailDao.findPlanCaseNum(testPlanId);

        return planCaseNum;
    }

    @Override
    public Pagination<TestPlanCase> findTestPlanCasePage(TestPlanCaseQuery testPlanCaseQuery) {
        Pagination<TestPlanCaseEntity>  pagination = testPlanDetailDao.findTestPlanCasePage(testPlanCaseQuery);
        List<TestPlanCase> testPlanCaseList = BeanMapper.mapList(pagination.getDataList(), TestPlanCase.class);

        joinTemplate.joinQuery(testPlanCaseList);

        if(testPlanCaseList!=null){
            for(TestPlanCase testPlanCase:testPlanCaseList){
                Module module = null;
                if(testPlanCase.getTestCase().getModule()!=null){
                     module = moduleService.findModule(testPlanCase.getTestCase().getModule().getId());
                }

                User user = null;
                if(testPlanCase.getTestCase().getCreateUser()!=null){
                    user = userService.findUser(testPlanCase.getTestCase().getCreateUser().getId());
                }

                testPlanCase.getTestCase().setModule(module);
                testPlanCase.getTestCase().setCreateUser(user);
            }
        }
        return PaginationBuilder.build(pagination, testPlanCaseList);
    }



    @Override
    public List<PlanCase> findPlanCaseList(TestPlanCaseQuery testPlanCaseQuery) {
        List<PlanCaseEntity> planCaseEntityList= testPlanDetailDao.findPlanCaseList(testPlanCaseQuery);
        List<PlanCase> planCaseList = BeanMapper.mapList(planCaseEntityList, PlanCase.class);
        joinTemplate.joinQuery(planCaseList);
        return planCaseList;
    }


    @Override
    public Pagination<PlanCase> findPlanCasePage(TestPlanCaseQuery testPlanCaseQuery) {
        Pagination<PlanCaseEntity> planCasePage = testPlanDetailDao.findPlanCasePage(testPlanCaseQuery);
        List<PlanCase> testCaseList = BeanMapper.mapList(planCasePage.getDataList(), PlanCase.class);
        joinTemplate.joinQuery(testCaseList,new String[]{"module","createUser","director"});

        return PaginationBuilder.build(planCasePage, testCaseList);
    }


    @Override
    public Pagination<PlanCase> findTestCasePage(TestPlanCaseQuery testPlanCaseQuery) {
        Pagination<PlanCaseEntity> planCasePage = testPlanDetailDao.findTestCasePage(testPlanCaseQuery);
        List<PlanCase> testCaseList = BeanMapper.mapList(planCasePage.getDataList(), PlanCase.class);
        joinTemplate.joinQuery(testCaseList,new String[]{"module","createUser"});
        return PaginationBuilder.build(planCasePage, testCaseList);
    }



    @Override
    public void planBindCase(List<TestPlanCase> testPlanCaseList) {
        for(TestPlanCase testPlanCase: testPlanCaseList){
            createTestPlanCase(testPlanCase);
        }
    }

    @Override
    public Boolean isCaseExist(String caseId){
        Integer caseExist = testPlanDetailDao.isCaseExist(caseId);

        if(caseExist!=null&&caseExist>0){
            return true;
        }else {
            return false;
        }
    }
    @Override
    public Map<String,Integer> getCaseTypeNum(String testPlanId){
        List<Map<String, Object>> resultList = testPlanDetailDao.getCaseTypeNum(testPlanId);
        Map<String, Integer> resultMap = new HashMap<>();
        for (Map<String, Object> row : resultList) {
            String caseType = (String) row.get("case_type");
            int total = ((Number) row.get("total")).intValue();
            resultMap.put(caseType, total);
        }
        return resultMap;
    }

}