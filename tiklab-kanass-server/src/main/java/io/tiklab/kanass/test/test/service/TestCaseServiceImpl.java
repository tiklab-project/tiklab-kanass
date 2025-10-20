package io.tiklab.kanass.test.test.service;

import io.tiklab.kanass.common.IDGeneratorUtil;
import io.tiklab.kanass.common.MagicValue;
import io.tiklab.kanass.instance.service.InstanceService;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.kanass.test.func.cases.service.FuncUnitCaseService;
import io.tiklab.kanass.test.test.model.TestCase;
import io.tiklab.kanass.test.test.model.TestCaseQuery;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.kanass.test.test.entity.TestCasesEntity;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.test.test.dao.TestCaseDao;
import io.tiklab.eam.common.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
* 测试用例 服务
*/
@Service
@Exporter
public class TestCaseServiceImpl implements TestCaseService {

    @Autowired
    TestCaseDao testCaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    InstanceService instanceService;

    @Autowired
    FuncUnitCaseService funcUnitCaseService;

    @Autowired
    ProjectService projectService;


    @Override
    public String createTestCase(TestCase testCase) {
        TestCasesEntity testCasesEntity = BeanMapper.map(testCase, TestCasesEntity.class);

        String projectId = testCasesEntity.getProjectId();
        // 设置用例key,  项目key-用例编号
        int i = testCaseDao.bigSort(projectId);
        int num = i+1;
        testCasesEntity.setSort(num);
        String oldKey = testCaseDao.findMaxCaseKey(projectId);

        // 创建key
        if (num == 1){
            testCasesEntity.setCaseKey(IDGeneratorUtil.generateID());
        }else {
            testCasesEntity.setCaseKey(IDGeneratorUtil.incrementID(oldKey));
        }

        //初始化项目成员
        String userId = LoginContext.getLoginId();
        testCasesEntity.setCreateUser(userId);
        testCasesEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        testCasesEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        String id = testCaseDao.createTestCase(testCasesEntity);

        return id;
    }

    @Override
    public void updateTestCase(TestCase testCase) {
        TestCasesEntity testCasesEntity = BeanMapper.map(testCase, TestCasesEntity.class);

        testCasesEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        String userId = LoginContext.getLoginId();
        testCasesEntity.setUpdateUser(userId);

        testCaseDao.updateTestCase(testCasesEntity);
    }

    @Override
    public void updateTestCaseList(List<TestCase> testCaseList) {
        for (TestCase testCase : testCaseList) {
            TestCase one = findOne(testCase.getId());
            one.setDirector(testCase.getDirector());

            updateTestCase(one);
        }
    }

    @Override
    public void deleteTestCase(@NotNull String id,String caseType) {

        //删除用例
        testCaseDao.deleteTestCase(id);

        //删除对应的用例
        switch (caseType) {
            case MagicValue.CASE_TYPE_FUNCTION -> {
                funcUnitCaseService.deleteFuncUnitCase(id);
                break;
            }
            default -> {
            }
        }
    }

    @Override
    public void deleteAllTestCase( String projectId) {
        TestCaseQuery testCaseQuery = new TestCaseQuery();
        testCaseQuery.setProjectId(projectId);
        List<TestCase> testCaseList = findTestCaseList(testCaseQuery);
        for(TestCase testCase:testCaseList){
            deleteTestCase(testCase.getId(),testCase.getCaseType());
        }
    }




    @Override
    public void deleteTestCaseByModuleId(String moduleId) {
        //删除相关联的子表
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(TestCasesEntity.class)
                .eq("moduleId", moduleId)
                .get();
        testCaseDao.deleteTestCase(deleteCondition);
    }

    @Override
    public TestCase findOne(String id) {
        TestCasesEntity testCasesEntity = testCaseDao.findTestCase(id);

        TestCase testCase = BeanMapper.map(testCasesEntity, TestCase.class);

        return testCase;
    }

    @Override
    public List<TestCase> findList(List<String> idList) {
        List<TestCasesEntity> testCaseList = testCaseDao.findTestCaseList(idList);

        List<TestCase> mapList = BeanMapper.mapList(testCaseList, TestCase.class);

        return mapList;
    }

    @Override
    public int findTestCaseNum(String projectId) {
        int testCaseNum = testCaseDao.findTestCaseNum(projectId);
        return testCaseNum;
    }



    @Override
    public HashMap<String, Integer> findDiffTypeCaseNum(String projectId) {
        HashMap<String, Integer> diffTypeCaseNum = testCaseDao.findDiffTypeCaseNum(projectId);
        return diffTypeCaseNum;
    }

    @Override
    public TestCase findTestCase(@NotNull String id) {
        TestCase testCase = findOne(id);

        joinTemplate.joinQuery(testCase,new String[]{
                "module",
                "project",
                "updateUser",
                "createUser",
                "director",
                "demand"
        });

        return testCase;
    }

    @Override
    public List<TestCase> findAllTestCase() {
        List<TestCasesEntity> testCasesEntityList = testCaseDao.findAllTestCase();

        List<TestCase> testCaseList = BeanMapper.mapList(testCasesEntityList, TestCase.class);

        joinTemplate.joinQuery(testCaseList);

        return testCaseList;
    }

    @Override
    public List<TestCase> findTestCaseList(TestCaseQuery testCaseQuery) {
        List<TestCasesEntity> testCasesEntityList = testCaseDao.findTestCaseList(testCaseQuery);

        List<TestCase> testCaseList = BeanMapper.mapList(testCasesEntityList, TestCase.class);

        joinTemplate.joinQuery(testCaseList);

        return testCaseList;
    }

    @Override
    public Pagination<TestCase> findTestCasePage(TestCaseQuery testCaseQuery) {
        Pagination<TestCasesEntity> pagination = testCaseDao.findTestCasePage(testCaseQuery);

        List<TestCase> testCaseList = BeanMapper.mapList(pagination.getDataList(), TestCase.class);

        joinTemplate.joinQuery(testCaseList,new String[]{
                "module",
                "createUser",
                "project",
                "updateUser",
                "director",
                "demand"
        });

//        List<TestCase> newTestCaseList = recentInstance(testCaseList);

        return PaginationBuilder.build(pagination, testCaseList);
    }

    /**
     * 根据查询参数获取各个状态的用例数量
     *
     * @param testCaseQuery
     * @return
     */
    @Override
    public HashMap<String, Integer> findTestCasePriorityLevelNum(TestCaseQuery testCaseQuery) {
        HashMap<String, Integer> testCaseStatusNum = testCaseDao.findTestCasePriorityLevelNum(testCaseQuery);
        return testCaseStatusNum;
    }

    @Override
    public HashMap<String, Integer> findDiffTestCaseNum(TestCaseQuery testCaseQuery) {
        HashMap<String, Integer> testCaseDiffNum = testCaseDao.findDiffTestCaseNum(testCaseQuery);
        return testCaseDiffNum;
    }


    @Override
    public int countCasesByModuleId(String moduleId) {
        int caseNum = testCaseDao.countCasesByModuleId(moduleId);
        if(caseNum>0){
            return caseNum;
        }
        return 0;
    }

    @Override
    public HashMap<String, Integer> findCreateUserAndDirectorNum(String projectId) {
        String loginId = LoginContext.getLoginId();
        Integer createUserNum = testCaseDao.countCreateUser(projectId, loginId);
        Integer directorNum = testCaseDao.countDirector(projectId, loginId);

        HashMap<String, Integer> countMap = new HashMap<>();
        countMap.put("createUserNum", createUserNum);
        countMap.put("directorNum", directorNum);

        return countMap;
    }

}