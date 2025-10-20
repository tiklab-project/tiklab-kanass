package io.tiklab.kanass.test.testcase.func.cases.service;

import io.tiklab.kanass.test.testcase.common.stepcommon.model.StepCommon;
import io.tiklab.kanass.test.testcase.common.stepcommon.service.StepCommonService;
import io.tiklab.kanass.test.testcase.func.cases.model.FuncUnitStep;
import io.tiklab.kanass.test.testcase.func.cases.model.FuncUnitStepQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.common.MagicValue;
import io.tiklab.kanass.test.testcase.func.cases.dao.FuncUnitStepDao;
import io.tiklab.kanass.test.testcase.func.cases.entity.FuncUnitStepEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 功能用例下步骤 服务
*/
@Service
public class FuncUnitStepServiceImpl implements FuncUnitStepService {

    @Autowired
    FuncUnitStepDao funcUnitStepDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    StepCommonService stepCommonService;

    @Override
    public String createFuncUnitStep(@NotNull @Valid FuncUnitStep funcUnitStep) {

        //公共步骤 创建
        StepCommon stepCommon = new StepCommon();
        stepCommon.setCaseId(funcUnitStep.getFuncUnitId());
        stepCommon.setType(MagicValue.CASE_TYPE_FUNCTION);
        String stepId = stepCommonService.createStepCommon(stepCommon);

        FuncUnitStepEntity funcUnitStepEntity = BeanMapper.map(funcUnitStep, FuncUnitStepEntity.class);
        funcUnitStepEntity.setId(stepId);
        funcUnitStepDao.createFuncUnitStep(funcUnitStepEntity);

        return stepId;
    }

    @Override
    public void updateFuncUnitStep(@NotNull @Valid FuncUnitStep funcUnitStep) {
        FuncUnitStepEntity funcUnitStepEntity = BeanMapper.map(funcUnitStep, FuncUnitStepEntity.class);
        funcUnitStepDao.updateFuncUnitStep(funcUnitStepEntity);
    }

    @Override
    public void deleteFuncUnitStep(@NotNull String id) {
        funcUnitStepDao.deleteFuncUnitStep(id);
    }

    @Override
    public FuncUnitStep findOne(String id) {
        FuncUnitStepEntity funcUnitStepEntity = funcUnitStepDao.findFuncUnitStep(id);

        FuncUnitStep funcUnitStep = BeanMapper.map(funcUnitStepEntity, FuncUnitStep.class);
        return funcUnitStep;
    }

    @Override
    public List<FuncUnitStep> findList(List<String> idList) {
        List<FuncUnitStepEntity> funcUnitStepEntityList =  funcUnitStepDao.findFuncUnitStepList(idList);

        List<FuncUnitStep> funcUnitStepList =  BeanMapper.mapList(funcUnitStepEntityList,FuncUnitStep.class);
        return funcUnitStepList;
    }

    @Override
    public FuncUnitStep findFuncUnitStep(@NotNull String id) {
        FuncUnitStep funcUnitStep = findOne(id);

        joinTemplate.joinQuery(funcUnitStep);

        return funcUnitStep;
    }



    @Override
    public List<FuncUnitStep> findAllFuncUnitStep() {
        List<FuncUnitStepEntity> funcUnitStepEntityList =  funcUnitStepDao.findAllFuncUnitStep();

        List<FuncUnitStep> funcUnitStepList =  BeanMapper.mapList(funcUnitStepEntityList,FuncUnitStep.class);

        joinTemplate.joinQuery(funcUnitStepList);

        return funcUnitStepList;
    }

    @Override
    public List<FuncUnitStep> findFuncUnitStepList(FuncUnitStepQuery funcUnitStepQuery) {
        List<FuncUnitStepEntity> funcUnitStepEntityList = funcUnitStepDao.findFuncUnitStepList(funcUnitStepQuery);

        List<FuncUnitStep> funcUnitStepList = BeanMapper.mapList(funcUnitStepEntityList,FuncUnitStep.class);

        joinTemplate.joinQuery(funcUnitStepList);

        return funcUnitStepList;
    }

    @Override
    public Pagination<FuncUnitStep> findFuncUnitStepPage(FuncUnitStepQuery funcUnitStepQuery) {
        Pagination<FuncUnitStepEntity>  pagination = funcUnitStepDao.findFuncUnitStepPage(funcUnitStepQuery);

        List<FuncUnitStep> funcUnitStepList = BeanMapper.mapList(pagination.getDataList(),FuncUnitStep.class);

        joinTemplate.joinQuery(funcUnitStepList);

        return PaginationBuilder.build(pagination,funcUnitStepList);
    }
}