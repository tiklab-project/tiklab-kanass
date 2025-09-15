package io.tiklab.kanass.test.func.instance.service;

import com.alibaba.fastjson.JSON;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.common.MagicValue;
import io.tiklab.kanass.instance.entity.InstanceEntity;
import io.tiklab.kanass.instance.model.Instance;
import io.tiklab.kanass.instance.service.InstanceService;
import io.tiklab.kanass.test.func.cases.model.FuncUnitCase;
import io.tiklab.kanass.test.func.cases.model.FuncUnitCaseQuery;
import io.tiklab.kanass.test.func.cases.service.FuncUnitCaseService;
import io.tiklab.kanass.test.func.instance.dao.FunctionInstanceDao;
import io.tiklab.kanass.test.func.instance.entity.FunctionInstanceEntity;
import io.tiklab.kanass.test.func.instance.model.FunctionInstance;
import io.tiklab.kanass.test.func.instance.model.FunctionInstanceQuery;
import io.tiklab.kanass.testplan.cases.model.TestPlan;
import io.tiklab.kanass.testplan.instance.model.FunctionTestReport;
import io.tiklab.kanass.testplan.instance.service.FunctionTestReportService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 接口场景实例 服务
*/
@Service
public class FunctionInstanceServiceImpl implements FunctionInstanceService {

    @Autowired
    FunctionInstanceDao functionInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    InstanceService instanceService;

    @Autowired
    FuncUnitCaseService funcUnitCaseService;

    @Autowired
    FunctionTestReportService functionTestReportService;


    @Override
    public String createFunctionInstance(@NotNull @Valid FunctionInstance functionInstance) {
        FunctionInstanceEntity functionInstanceEntity = BeanMapper.map(functionInstance, FunctionInstanceEntity.class);
        String userId = LoginContext.getLoginId();
        functionInstanceEntity.setCreateUser(userId);
        functionInstanceEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return functionInstanceDao.createFunctionInstance(functionInstanceEntity);
    }

    @Override
    public void updateFunctionInstance(@NotNull @Valid FunctionInstance functionInstance) {
        FunctionInstanceEntity testInstanceEntity = BeanMapper.map(functionInstance, FunctionInstanceEntity.class);

        functionInstanceDao.updateFunctionInstance(testInstanceEntity);
    }

    @Override
    public void deleteFunctionInstance(@NotNull String id) {
        functionInstanceDao.deleteFunctionInstance(id);

    }

    @Override
    public FunctionInstance findOne(String id) {
        FunctionInstanceEntity testInstanceEntity = functionInstanceDao.findFunctionInstance(id);

        FunctionInstance testInstance = BeanMapper.map(testInstanceEntity, FunctionInstance.class);
        return testInstance;
    }

    @Override
    public List<FunctionInstance> findList(List<String> idList) {
        List<FunctionInstanceEntity> testInstanceEntityList =  functionInstanceDao.findFunctionInstanceList(idList);

        List<FunctionInstance> testInstanceList =  BeanMapper.mapList(testInstanceEntityList, FunctionInstance.class);
        return testInstanceList;
    }

    @Override
    public FunctionInstance findFunctionInstance(@NotNull String id) {
        FunctionInstance functionInstance = findOne(id);

        joinTemplate.joinQuery(functionInstance,new String[]{"funcUnitCase","createUser"});
        return functionInstance;
    }

    @Override
    public List<FunctionInstance> findAllFunctionInstance() {
        List<FunctionInstanceEntity> testInstanceEntities =  functionInstanceDao.findAllFunctionInstance();

        List<FunctionInstance> testInstanceList =  BeanMapper.mapList(testInstanceEntities, FunctionInstance.class);

        joinTemplate.joinQuery(testInstanceList,new String[]{"funcUnitCase","createUser"});
        return testInstanceList;
    }

    @Override
    public List<FunctionInstance> findFunctionInstanceList(FunctionInstanceQuery functionInstanceQuery) {
        List<FunctionInstanceEntity> testInstanceEntities = functionInstanceDao.findFunctionInstanceList(functionInstanceQuery);

        List<FunctionInstance> testInstanceList = BeanMapper.mapList(testInstanceEntities, FunctionInstance.class);

        joinTemplate.joinQuery(testInstanceList,new String[]{"funcUnitCase","createUser"});

        return testInstanceList;
    }

    /**
     * 查询用例最近的实例列表
     *
     * @param functionInstanceQuery
     * @return
     */
    @Override
    public List<FunctionInstance> findRecentCaseFunctionInstanceList(FunctionInstanceQuery functionInstanceQuery) {
        List<FunctionInstanceEntity> instanceEntityList = functionInstanceDao.findRecentCaseFunctionInstanceList(functionInstanceQuery);

        List<FunctionInstance> instanceList = BeanMapper.mapList(instanceEntityList, FunctionInstance.class);

        joinTemplate.joinQuery(instanceList,new String[]{
                "createUser",
                "testPlan",
                "testCase"
        });

        return instanceList;
    }

    @Override
    public Pagination<FunctionInstance> findFunctionInstancePage(FunctionInstanceQuery functionInstanceQuery) {
        Pagination<FunctionInstanceEntity>  pagination = functionInstanceDao.findFunctionInstancePage(functionInstanceQuery);

        List<FunctionInstance> testInstanceList = BeanMapper.mapList(pagination.getDataList(), FunctionInstance.class);

        joinTemplate.joinQuery(testInstanceList,new String[]{"funcUnitCase","createUser"});

        return PaginationBuilder.build(pagination,testInstanceList);
    }

    @Override
    public String setFunctionResult(FunctionInstance functionInstanceSource) {
        FunctionInstanceQuery functionInstanceQuery = new FunctionInstanceQuery();
        functionInstanceQuery.setTestPlanInstanceId(functionInstanceSource.getTestPlan().getId());
        functionInstanceQuery.setFunctionId(functionInstanceSource.getFuncUnitCase().getId());
        List<FunctionInstance> functionInstanceList = findFunctionInstanceList(functionInstanceQuery);

        String functionInstanceId;
        if(functionInstanceList!=null){
            FunctionInstance functionInstance = functionInstanceList.get(0);
            functionInstance.setResult(functionInstanceSource.getResult());
            functionInstanceId = functionInstance.getId();
            updateFunctionInstance(functionInstance);
        }else {
            String id = createFunctionInstance(functionInstanceSource);
            functionInstanceId = id;
        }


        setInstance(functionInstanceSource);

        return functionInstanceId;
    }

    private void setInstance(FunctionInstance functionInstanceSource) {
        FunctionTestReport functionTestReport = functionTestReportService.getFunctionTestReport(functionInstanceSource.getTestPlan().getId());
        String jsonString = JSON.toJSONString(functionTestReport);

        Instance existInstance = instanceService.findInstance(functionInstanceSource.getTestPlan().getId());
        if(existInstance==null) {
            Instance instance = new Instance();
            instance.setExecuteNumber(1);
            instance.setContent(jsonString);
            instance.setId(functionInstanceSource.getTestPlan().getId());
            TestPlan testPlan = new TestPlan();
            testPlan.setId(functionInstanceSource.getTestPlan().getId());
            instance.setTestPlan(testPlan);
            instance.setPlanType(MagicValue.TEST_TYPE_FUNCTION);
            instance.setProjectId(functionInstanceSource.getProjectId());
            instance.setType(MagicValue.TEST_PLAN);
            FuncUnitCase funcUnitCase = functionInstanceSource.getFuncUnitCase();
            FuncUnitCase funcUnitCase1 = funcUnitCaseService.findFuncUnitCase(funcUnitCase.getId());
            instance.setName(funcUnitCase1.getTestCase().getName());

            instanceService.createInstance(instance);
        }else {
            if(functionTestReport!=null){
                existInstance.setContent(jsonString);
                instanceService.updateInstance(existInstance);
            }
        }
    }

}