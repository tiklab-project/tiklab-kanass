package io.tiklab.kanass.test.common.stepcommon.service;

import io.tiklab.kanass.test.common.stepcommon.model.StepCommon;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;
import io.tiklab.kanass.test.common.stepcommon.model.StepCommonQuery;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 步骤公共 服务接口
*/
@JoinProvider(model = StepCommon.class)
public interface StepCommonService {

    /**
    * 创建步骤公共
    * @param stepCommon
    * @return
    */
    String createStepCommon(@NotNull @Valid StepCommon stepCommon);

    /**
    * 更新步骤公共
    * @param stepCommon
    */
    void updateStepCommon(@NotNull @Valid StepCommon stepCommon);

    /**
    * 删除步骤公共
    * @param id
    */
    void deleteStepCommon(@NotNull String id,String caseType);

    void deleteAllStepCommon(String caseId);

    @FindOne
    StepCommon findOne(@NotNull String id);

    /**
    * 根据id查找步骤公共
    * @param id
    * @return
    */
    StepCommon findStepCommon(@NotNull String id);

    /**
     * 查询步骤数量
     * @param caseId
     * @return
     */
    int findStepNum(String caseId);


    /**
    * 根据查询参数查询步骤公共列表
    * @param stepCommonQuery
    * @return
    */
    List<StepCommon> findStepCommonList(StepCommonQuery stepCommonQuery);


    /**
     * 获取列表
     * @param caseId
     * @return
     */
    List<StepCommon> getStepCommonList(String caseId);
}