package io.tiklab.kanass.test.instance.service;

import io.tiklab.kanass.test.instance.model.InstanceStatusSummary;
import io.tiklab.kanass.test.testplan.cases.service.TestPlanService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.test.instance.dao.InstanceDao;
import io.tiklab.kanass.test.instance.entity.InstanceEntity;
import io.tiklab.kanass.test.instance.model.Instance;
import io.tiklab.kanass.test.instance.model.InstanceQuery;
import io.tiklab.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 公共实例 服务
*/
@Service
public class InstanceServiceImpl implements InstanceService {

    @Autowired
    InstanceDao instanceDao;

    @Autowired
    JoinTemplate joinTemplate;


    @Autowired
    TestPlanService testPlanService;

    @Override
    public String createInstance(@NotNull @Valid Instance instance) {

        instance.setCreateTime(new Timestamp(System.currentTimeMillis()));
        User user = new User();
        user.setId(LoginContext.getLoginId());
        instance.setCreateUser(user);
        InstanceEntity instanceEntity = BeanMapper.map(instance, InstanceEntity.class);

        return instanceDao.createInstance(instanceEntity);
    }

    @Override
    public void updateInstance(@NotNull @Valid Instance instance) {
        InstanceEntity instanceEntity = BeanMapper.map(instance, InstanceEntity.class);

        instanceDao.updateInstance(instanceEntity);
    }

    @Override
    public void deleteInstance(@NotNull String id,String caseType) {
        instanceDao.deleteInstance(id);
    }




    @Override
    public void deleteAllInstance(String caseId) {

        InstanceQuery instanceQuery = new InstanceQuery();
        instanceQuery.setCaseId(caseId);
        List<Instance> instanceList = findInstanceList(instanceQuery);

        for(Instance instance : instanceList){
            deleteInstance(instance.getId(),instance.getType());
        }
    }



    @Override
    public Instance findOne(String id) {
        InstanceEntity instanceEntity = instanceDao.findInstance(id);

        Instance instance = BeanMapper.map(instanceEntity, Instance.class);
        return instance;
    }

    @Override
    public List<Instance> findList(List<String> idList) {
        List<InstanceEntity> instanceEntityList =  instanceDao.findInstanceList(idList);

        List<Instance> instanceList =  BeanMapper.mapList(instanceEntityList, Instance.class);
        return instanceList;
    }

    @Override
    public Instance findInstance(@NotNull String id) {
        Instance instance = findOne(id);

        joinTemplate.joinQuery(instance,new String[]{
                "createUser",
                "testPlan",
                "testCase"
        });
        return instance;
    }

    @Override
    public Instance findRecentInstance(String belongId) {
        InstanceEntity recentInstance = instanceDao.findRecentInstance(belongId);
        Instance instance = BeanMapper.map(recentInstance, Instance.class);
        return instance;
    }


    @Override
    public List<Instance> findAllInstance() {
        List<InstanceEntity> instanceEntityList =  instanceDao.findAllInstance();

        List<Instance> instanceList =  BeanMapper.mapList(instanceEntityList, Instance.class);

        joinTemplate.joinQuery(instanceList,new String[]{
                "createUser",
                "testPlan",
                "testCase"
        });
        return instanceList;
    }

    @Override
    public List<Instance> findInstanceList(InstanceQuery instanceQuery) {
        List<InstanceEntity> instanceEntityList = instanceDao.findInstanceList(instanceQuery);

        List<Instance> instanceList = BeanMapper.mapList(instanceEntityList, Instance.class);

        joinTemplate.joinQuery(instanceList,new String[]{
                "createUser",
                "testPlan",
                "testCase"
        });

        return instanceList;
    }

    @Override
    public Pagination<Instance> findInstancePage(InstanceQuery instanceQuery) {

        Pagination<InstanceEntity>  pagination = instanceDao.findInstancePage(instanceQuery);

        List<Instance> instanceList = BeanMapper.mapList(pagination.getDataList(), Instance.class);

        joinTemplate.joinQuery(instanceList,new String[]{
                "createUser",
                "testPlan",
                "testCase"
        });

//        if(instanceList!=null&& !instanceList.isEmpty()){
//            if(Objects.equals(instanceQuery.getType(), MagicValue.TEST_PLAN)){
//                for (Instance instance : instanceList){
//                    TestPlan testPlan = testPlanService.findTestPlan(instance.getTestPlan().getId());
//                    instance.setPlanType(testPlan.getType());
//                }
//            }
//        }

        return PaginationBuilder.build(pagination, instanceList);
    }

    @Override
    public int getRecentExecuteNum(String caseId) {
        int recentExecuteNum = instanceDao.getRecentExecuteNum(caseId);
        if(recentExecuteNum==0){
            return 1;
        }else {
            return ++recentExecuteNum;
        }
    }

    @Override
    public int getRecentPlanExecuteNum(String testPlanId) {
        int recentExecuteNum = instanceDao.getRecentPlanExecuteNum(testPlanId);
        if(recentExecuteNum==0){
            return 1;
        }else {
            return ++recentExecuteNum;
        }
    }

    @Override
    public String getRecentExecuteInstanceId(String caseId) {
        String recentExecuteInstanceId = instanceDao.getRecentExecuteInstanceId(caseId);
        return recentExecuteInstanceId;
    }

    @Override
    public String getRecentExecutePlanInstanceId(String testPlanId) {
        String recentExecutePlanInstanceId = instanceDao.getRecentExecutePlanInstanceId(testPlanId);

        return recentExecutePlanInstanceId;
    }

    @Override
    public int findInstanceNum(String caseId){
        return instanceDao.findInstanceNum(caseId);
    }

    @Override
    public int findPlanInstanceNum(String testPlanId) {
        return instanceDao.findPlanInstanceNum(testPlanId);
    }


    @Override
    public int findInstanceNumByProjectId(String projectId) {
        return instanceDao.findInstanceNumByProjectId(projectId);
    }

    @Override
    public InstanceStatusSummary getInstanceStatusSummary(InstanceQuery instanceQuery) {
        return instanceDao.getInstanceStatusSummary(instanceQuery);
    }


}