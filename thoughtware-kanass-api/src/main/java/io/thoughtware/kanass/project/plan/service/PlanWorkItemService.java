package io.thoughtware.kanass.project.plan.service;


import io.thoughtware.core.page.Pagination;
import io.thoughtware.kanass.project.plan.model.PlanWorkItem;
import io.thoughtware.kanass.project.plan.model.PlanWorkItemQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 计划事项关联服务接口
*/
public interface PlanWorkItemService {

    /**
    * 创建计划事项关联数据
    * @param planWorkItem
    * @return
    */
    String createPlanWorkItem(@NotNull @Valid PlanWorkItem planWorkItem);

    /**
    * 更新计划数据关联关系
    * @param planWorkItem
    */
    void updatePlanWorkItem(@NotNull @Valid PlanWorkItem planWorkItem);

    /**
    * 删除关联关系
    * @param id
    */
    void deletePlanWorkItem(@NotNull String id);

    /**
     * 根据条件删除关联关系
     * @param planWorkItem
     */
    void deletePlanWorkItem(PlanWorkItem planWorkItem);

    PlanWorkItem findOne(@NotNull String id);

    List<PlanWorkItem> findList(List<String> idList);

    /**
    * 根据id查找关联数据
    * @param id
    * @return
    */
    PlanWorkItem findPlanWorkItem(@NotNull String id);

    /**
    * 查找所有计划事项关联关系
    * @return
    */
    List<PlanWorkItem> findAllPlanWorkItem();

    /**
    * 根据条件计划事项查询关联列表
    * @param planWorkItemQuery
    * @return
    */
    List<PlanWorkItem> findPlanWorkItemList(PlanWorkItemQuery planWorkItemQuery);

    /**
    * 根据条件按分页查询关联关系
    * @param planWorkItemQuery
    * @return
    */
    Pagination<PlanWorkItem> findPlanWorkItemPage(PlanWorkItemQuery planWorkItemQuery);

}