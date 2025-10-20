package io.tiklab.kanass.test.testcase.common.stepcommon.service;

import io.tiklab.kanass.test.testcase.common.stepcommon.dao.StepCommonDao;
import io.tiklab.kanass.test.testcase.common.stepcommon.entity.StepCommonEntity;
import io.tiklab.kanass.test.testcase.common.stepcommon.model.StepCommon;
import io.tiklab.kanass.test.testcase.common.stepcommon.model.StepCommonQuery;
import io.tiklab.kanass.test.testcase.func.cases.model.FuncUnitStep;
import io.tiklab.kanass.test.testcase.func.instance.service.FunctionInstanceService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.test.testcase.func.cases.service.FuncUnitStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 功能用例下步骤 服务
*/
@Service
public class StepCommonServiceImpl implements StepCommonService {

    @Autowired
    StepCommonDao stepCommonDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    FuncUnitStepService funcUnitStepService;


    @Autowired
    FunctionInstanceService functionInstanceService;

    @Override
    public String createStepCommon(@NotNull @Valid StepCommon stepCommon) {

        List<StepCommon> stepCommonList = getStepCommonList(stepCommon.getCaseId());

        if(stepCommonList!=null && stepCommonList.size()>0){
            stepCommon.setSort(stepCommonList.size());
        }else {
            stepCommon.setSort(0);
        }

        StepCommonEntity stepCommonEntity = BeanMapper.map(stepCommon, StepCommonEntity.class);
        stepCommonEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        String id = stepCommonDao.createStepCommon(stepCommonEntity);

        return id;
    }

    @Override
    public void updateStepCommon(@NotNull @Valid StepCommon stepCommon) {

        if(stepCommon.getOldSort()!=null){
            Integer curSort = stepCommon.getSort();
            Integer oldSort = stepCommon.getOldSort();

            List<StepCommon> stepCommonList = getStepCommonList(stepCommon.getCaseId());

            //如果当前排序大于源排序，中间项的排序都得减1
            if(curSort > oldSort){
                for(int i=oldSort;i<=curSort;i++){
                    StepCommon stepCommonItem = stepCommonList.get(i);
                    stepCommonItem.setSort(stepCommonItem.getSort()-1);

                    StepCommonEntity stepCommonEntity = BeanMapper.map(stepCommonItem, StepCommonEntity.class);
                    stepCommonDao.updateStepCommon(stepCommonEntity);
                }
            }

            //如果当前排序小于源排序，中间项的排序都得加1
            if(curSort < oldSort){
                for (int i=oldSort;i>=curSort;i--){
                    StepCommon stepCommonItem = stepCommonList.get(i);
                    stepCommonItem.setSort(stepCommonItem.getSort()+1);

                    StepCommonEntity stepCommonEntity = BeanMapper.map(stepCommonItem, StepCommonEntity.class);
                    stepCommonDao.updateStepCommon(stepCommonEntity);
                }
            }
        }


        StepCommonEntity entity = BeanMapper.map(stepCommon, StepCommonEntity.class);
        stepCommonDao.updateStepCommon(entity);

    }

    @Override
    public void deleteStepCommon(@NotNull String id,String caseType) {
        StepCommon stepCommon = findStepCommon(id);
        if(stepCommon== null){return;}
        Integer sort = stepCommon.getSort();

        List<StepCommon> stepCommonList = getStepCommonList(stepCommon.getCaseId());

        for(StepCommon stepCommonItem:stepCommonList){
            if(stepCommonItem.getSort() > sort){
                stepCommonItem.setSort(stepCommonItem.getSort()-1);
                updateStepCommon(stepCommonItem);
            }

            if(stepCommonItem.getSort().equals(sort)){
                stepCommonDao.deleteStepCommon(id);

                funcUnitStepService.deleteFuncUnitStep(id);
            }
        }
    }

    @Override
    public void deleteAllStepCommon(String caseId) {
        StepCommonQuery stepCommonQuery = new StepCommonQuery();
        stepCommonQuery.setCaseId(caseId);
        List<StepCommon> stepCommonList = findStepCommonList(stepCommonQuery);
        for(StepCommon stepCommon : stepCommonList){
            deleteStepCommon(stepCommon.getId(),stepCommon.getType());
        }
    }


    @Override
    public StepCommon findOne(String id) {
        StepCommonEntity stepCommonEntity = stepCommonDao.findStepCommon(id);

        StepCommon stepCommon = BeanMapper.map(stepCommonEntity, StepCommon.class);
        return stepCommon;
    }

    @Override
    public StepCommon findStepCommon(@NotNull String id) {
        StepCommon stepCommon = findOne(id);
        joinTemplate.joinQuery(stepCommon);

        return stepCommon;
    }

    @Override
    public int findStepNum(String caseId) {
        int stepNum = stepCommonDao.findStepNum(caseId);
        return stepNum;
    }


    @Override
    public List<StepCommon> findStepCommonList(StepCommonQuery stepCommonQuery) {
        List<StepCommonEntity> stepCommonEntityList = stepCommonDao.findStepCommonList(stepCommonQuery);
        List<StepCommon> stepCommonList = BeanMapper.mapList(stepCommonEntityList, StepCommon.class);
        if(stepCommonList!=null){
            for(StepCommon stepCommon:stepCommonList){
                FuncUnitStep funcUnitStep = funcUnitStepService.findFuncUnitStep(stepCommon.getId());
                stepCommon.setFuncUnitStep(funcUnitStep);
            }
        }

        return stepCommonList;
    }

    /**
     * 获取公共步骤列表
     */
    @Override
    public List<StepCommon> getStepCommonList(String caseId){
        StepCommonQuery stepCommonQuery = new StepCommonQuery();
        stepCommonQuery.setCaseId(caseId);
        List<StepCommon> stepCommonList = findStepCommonList(stepCommonQuery);

        return stepCommonList;
    }

}