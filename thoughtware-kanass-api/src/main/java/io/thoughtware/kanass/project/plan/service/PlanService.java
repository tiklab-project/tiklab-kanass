package io.thoughtware.kanass.project.plan.service;

import io.thoughtware.core.page.Pagination;

import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;
import io.thoughtware.kanass.project.plan.model.Plan;
import io.thoughtware.kanass.project.plan.model.PlanQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 计划服务接口
*/
@JoinProvider(model = Plan.class)
public interface PlanService {

    /**
    * 创建计划
    * @param plan
    * @return
    */
    String createPlan(@NotNull @Valid Plan plan);

    /**
    * 更新计划
    * @param plan
    */
    void updatePlan(@NotNull @Valid Plan plan);

    /**
    * 删除计划
    * @param id
    */
    void deletePlan(@NotNull String id);
    @FindOne
    Plan findOne(@NotNull String id);
    @FindList
    List<Plan> findList(List<String> idList);

    /**
    * 根据id查找计划
    * @param id
    * @return
    */
    Plan findPlan(@NotNull String id);

    /**
    * 查找所有计划
    * @return
    */
    @FindAll
    List<Plan> findAllPlan();

    /**
     * 根据条件查找计划列表
     * @param planQuery
     * @param isJoinQuery
     * @return
     */
    List<Plan> findPlanList(PlanQuery planQuery, boolean isJoinQuery);

    /**
    * 根据条件查找计划列表
    * @param planQuery
    * @return
    */
    List<Plan> findPlanList(PlanQuery planQuery);

    /**
    * 根据条件按照分页查找计划列表
    * @param planQuery
    * @return
    */
    Pagination<Plan> findPlanPage(PlanQuery planQuery);

    /**
     * 根据条件按照分页查询列表树
     * @param planQuery
     * @return
     */
    Pagination<Plan> findPlanPageTree(PlanQuery planQuery);

}