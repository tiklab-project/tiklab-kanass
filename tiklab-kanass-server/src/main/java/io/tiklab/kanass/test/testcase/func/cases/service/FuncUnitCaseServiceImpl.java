package io.tiklab.kanass.test.testcase.func.cases.service;

import io.tiklab.kanass.project.module.model.Module;
import io.tiklab.kanass.project.module.service.ModuleService;
import io.tiklab.kanass.test.testcase.common.stepcommon.service.StepCommonService;
import io.tiklab.kanass.test.testcase.func.cases.model.FuncUnitCase;
import io.tiklab.kanass.test.testcase.func.cases.model.FuncUnitCaseQuery;
import io.tiklab.kanass.test.testcase.test.model.TestCase;
import io.tiklab.kanass.test.testcase.test.model.TestCaseQuery;
import io.tiklab.kanass.test.testcase.test.service.TestCaseService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.test.testcase.func.cases.dao.FuncUnitCaseDao;
import io.tiklab.kanass.test.testcase.func.cases.entity.FuncUnitCaseEntity;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserProcessor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
* 功能用例 服务
*/
@Service
public class FuncUnitCaseServiceImpl implements FuncUnitCaseService {

    @Autowired
    FuncUnitCaseDao funcUnitCaseDao;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ModuleService moduleService;

    @Autowired
    UserProcessor userService;

    @Autowired
    StepCommonService stepCommonService;

    @Override
    public String createFuncUnitCase(@NotNull @Valid FuncUnitCase funcUnitCase) {
        FuncUnitCaseEntity funcUnitCaseEntity = BeanMapper.map(funcUnitCase, FuncUnitCaseEntity.class);
        String id = funcUnitCaseDao.createFuncUnitCase(funcUnitCaseEntity);
        
        funcUnitCaseEntity.setTestCaseId(id);
        funcUnitCaseEntity.setId(id);
        funcUnitCaseDao.updateFuncUnitCase(funcUnitCaseEntity);

        TestCase testCase = funcUnitCase.getTestCase();
        testCase.setId(id);
        testCaseService.createTestCase(testCase);
        
        return id;
    }

    @Override
    public void updateFuncUnitCase(@NotNull @Valid FuncUnitCase funcUnitCase) {
        FuncUnitCaseEntity funcUnitCaseEntity = BeanMapper.map(funcUnitCase, FuncUnitCaseEntity.class);

        funcUnitCaseDao.updateFuncUnitCase(funcUnitCaseEntity);

        testCaseService.updateTestCase(funcUnitCase.getTestCase());
    }

    @Override
    public void deleteFuncUnitCase(@NotNull String id) {
        funcUnitCaseDao.deleteFuncUnitCase(id);

        stepCommonService.deleteAllStepCommon(id);

    }

    @Override
    public FuncUnitCase findOne(String id) {
        FuncUnitCaseEntity funcUnitCaseEntity = funcUnitCaseDao.findFuncUnitCase(id);

        FuncUnitCase funcUnitCase = BeanMapper.map(funcUnitCaseEntity, FuncUnitCase.class);
        return funcUnitCase;
    }

    @Override
    public List<FuncUnitCase> findList(List<String> idList) {
        List<FuncUnitCaseEntity> funcUnitCaseEntityList =  funcUnitCaseDao.findFuncUnitCaseList(idList);

        List<FuncUnitCase> funcUnitCaseList =  BeanMapper.mapList(funcUnitCaseEntityList, FuncUnitCase.class);
        return funcUnitCaseList;
    }

    @Override
    public FuncUnitCase findFuncUnitCase(@NotNull String id) {
        FuncUnitCase funcUnitCase = findOne(id);
        TestCase testCase = testCaseService.findTestCase(id);
        funcUnitCase.setTestCase(testCase);

        int funcSceneStepNum = stepCommonService.findStepNum(id);
        funcUnitCase.setStepNum(funcSceneStepNum);

        //手动添加字段
        if(testCase.getModule()!=null){
            Module module = moduleService.findModule(testCase.getModule().getId());
            funcUnitCase.getTestCase().setModule(module);
        }
        if(testCase.getUpdateUser()!=null){
            User updateUser = userService.findUser(testCase.getUpdateUser().getId());
            funcUnitCase.getTestCase().setUpdateUser(updateUser);
        }


        return funcUnitCase;
    }

    @Override
    public List<FuncUnitCase> findAllFuncUnitCase() {
        List<FuncUnitCaseEntity> funcUnitCaseEntityList =  funcUnitCaseDao.findAllFuncUnitCase();

        List<FuncUnitCase> funcUnitCaseList =  BeanMapper.mapList(funcUnitCaseEntityList, FuncUnitCase.class);

        joinTemplate.joinQuery(funcUnitCaseList);
        return funcUnitCaseList;
    }

    @Override
    public List<FuncUnitCase> findFuncUnitCaseList(FuncUnitCaseQuery functionTestCaseQuery) {
        List<FuncUnitCaseEntity> funcUnitCaseEntityList = funcUnitCaseDao.findFuncUnitCaseList(functionTestCaseQuery);

        List<FuncUnitCase> funcUnitCaseList = BeanMapper.mapList(funcUnitCaseEntityList, FuncUnitCase.class);

        joinTemplate.joinQuery(funcUnitCaseList);

        return funcUnitCaseList;
    }

    @Override
    public Pagination<FuncUnitCase> findFuncUnitCasePage(FuncUnitCaseQuery functionTestCaseQuery) {
        Pagination<FuncUnitCaseEntity>  pagination = funcUnitCaseDao.findFuncUnitCasePage(functionTestCaseQuery);

        List<FuncUnitCase> funcUnitCaseList = BeanMapper.mapList(pagination.getDataList(), FuncUnitCase.class);

        joinTemplate.joinQuery(funcUnitCaseList);

        return PaginationBuilder.build(pagination,funcUnitCaseList);
    }

    @Override
    public List<FuncUnitCase> findFuncUnitCaseListByTestCase(TestCaseQuery testCaseQuery) {
        List<TestCase> testCaseList = testCaseService.findTestCaseList(testCaseQuery);

        List<FuncUnitCase> funcUnitCaseList = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(testCaseList)){
            for(TestCase testCase : testCaseList){
                FuncUnitCase funcUnitCase = findFuncUnitCase(testCase.getId());

                if(!ObjectUtils.isEmpty(funcUnitCase)){
                    funcUnitCaseList.add(funcUnitCase);
                }
            }
        }

        return funcUnitCaseList;
    }
}