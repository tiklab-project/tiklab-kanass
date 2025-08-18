package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.workitem.model.WorkProductPlan;
import io.tiklab.kanass.workitem.model.WorkProductPlanQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
* 事项状态服务接口
*/
@JoinProvider(model = WorkProductPlan.class)
public interface WorkProductPlanService {

    /**
    * 创建事项状态
    * @param workProductPlan
    * @return
    */
    String createWorkProductPlan(@NotNull @Valid WorkProductPlan workProductPlan);
    void createBatchWorkProductPlan(@NotNull String valueStrings);
    /**
    * 更新事项状态
    * @param workProductPlan
    */
    void updateWorkProductPlan(@NotNull @Valid WorkProductPlan workProductPlan);

    /**
    * 删除事项状态
    * @param id
    */
    void deleteWorkProductPlan(@NotNull String id);

    void deleteWorkProductPlan(WorkProductPlanQuery workProductPlanQuery);
    @FindOne
    WorkProductPlan findOne(@NotNull String id);

    @FindList
    List<WorkProductPlan> findList(@NotNull List<String> idList);

    /**
    * 根据id查找事项状态
    * @param id
    * @return
    */
    WorkProductPlan findWorkProductPlan(@NotNull String id);

    /**
    * 查找所有事项状态列表
    * @return
    */
    @FindAll
    List<WorkProductPlan> findAllWorkProductPlan();

    /**
    * 根据条件查询事项状态列表
    * @param workProductPlanQuery
    * @return
    */
    List<WorkProductPlan> findWorkProductPlanList(WorkProductPlanQuery workProductPlanQuery);


    /**
    * 按分页查询事项状态列表
    * @param workProductPlanQuery
    * @return
    */
    Pagination<WorkProductPlan> findWorkProductPlanPage(WorkProductPlanQuery workProductPlanQuery);
    List<String> findProductPlanWorkItemIds(@NotNull String productPlanId);
    List<Map<String, String>> findProductPlanWorkItemNum(String productPlanIds);

}