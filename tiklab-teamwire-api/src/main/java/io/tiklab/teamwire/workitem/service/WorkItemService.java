package io.tiklab.teamwire.workitem.service;

import io.tiklab.teamwire.workitem.model.WorkBoard;
import io.tiklab.teamwire.workitem.model.WorkItem;
import io.tiklab.teamwire.workitem.model.WorkItemQuery;
import io.tiklab.teamwire.workitem.model.WorkUserGroupBoard;
import io.tiklab.core.page.Pagination;

import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
* 事项服务接口
*/
@JoinProvider(model = WorkItem.class)
public interface WorkItemService {

    /**
    * 创建事项
    * @param workItem
    * @return
    */
    String createWorkItem(@NotNull @Valid WorkItem workItem);

    String createJiraWorkItem(@NotNull @Valid WorkItem workItem);

    /**
    * 更新事项
    * @param workItem
    */
    void updateWorkItem(@NotNull @Valid WorkItem workItem);

    /**
    * 删除事项
    * @param id
    */
    void deleteWorkItem(@NotNull String id);

    @FindOne
    WorkItem findOne(@NotNull String id);

    @FindList
    List<WorkItem> findList(List<String> idList);

    /**
    * 根据id查找事项
    * @param id
    * @return
    */
    WorkItem findWorkItem(@NotNull String id);

    /**
    * 查找所有事项
    * @return
    */
    @FindAll
    List<WorkItem> findAllWorkItem();


    List<WorkItem> findWorkItemList(WorkItemQuery workItemQuery, boolean isJoinQuery);

    /**
    * 根据条件查询事项列表
    * @param workItemQuery
    * @return
    */
    List<WorkItem> findWorkItemList(WorkItemQuery workItemQuery);
    Integer findWorkItemListCount(WorkItemQuery workItemQuery);
    /**
     * 查询可被史诗关联事项列表
     * @param workItemQuery
     * @return
     */
    Pagination<WorkItem> findEpicSelectWorkItemList(WorkItemQuery workItemQuery);

    /**
     * 查询可被需求缺陷任务关联事项列表
     * @param workItemQuery
     * @return
     */
    Pagination<WorkItem> findSelectWorkItemList(WorkItemQuery workItemQuery);

    /**
    * 根据条件按分页查询事项列表
    * @param workItemQuery
    * @return
    */
    Pagination<WorkItem> findWorkItemPage(WorkItemQuery workItemQuery);


    /**
     * 按条件分页查询列表
     * @param workItemQuery
     * @return
     */
    Pagination<WorkItem> findConditionWorkItemPage(WorkItemQuery workItemQuery);

    /**
     * 按条件查询列表树
     * @param workItemQuery
     * @return
     */
    List<WorkItem> findWorkItemListTree(WorkItemQuery workItemQuery);

    /**
     * 按条件分页查询列表树
     * @param workItemQuery
     * @return
     */
    Pagination<WorkItem> findWorkItemPageTree(WorkItemQuery workItemQuery);

    /**
     * 按条件分页查询列表树
     * @param workItemQuery
     * @return
     */
    Pagination<WorkItem> findWorkItemPageTreeByQuery(WorkItemQuery workItemQuery);

    /**
     * 查找所有事项看板列表
     * @return
     * @param workItemQuery
     */
    List<WorkBoard> findWorkBoardList(WorkItemQuery workItemQuery);

    WorkBoard findChangePageWorkBoardList(WorkItemQuery workItemQuery);
    /**
     * 查找按照处理人查找分配的事项看板
     * @return
     * @param workItemQuery
     */
    List<WorkUserGroupBoard> findWorkUserGroupBoardList(WorkItemQuery workItemQuery);


    /**
     * 根据项目ID，史诗id查找事项看板
     *
     * @param workItemQuery@return
     */
    Pagination<WorkItem> findUnEpicWorkItemPage(WorkItemQuery workItemQuery);

    /**
     * 根据项目ID，计划id查找未被计划关联的事项
     * @param workItemQuery@return
     */
    Pagination<WorkItem> findUnPlanWorkItemPage(WorkItemQuery workItemQuery);

    /**
     * 根据项目ID，计划id查找被计划关联的事项
     * @param workItemQuery
     * @return
     */
    List<WorkItem> findPlanWorkItemPage(WorkItemQuery workItemQuery);

    /**
     * 查找符合条件的子级事项，用于项目阶段查找子事项
     * @param workItemList
     * @return
     */
    List<WorkItem> findChildWorkItemList(List<WorkItem> workItemList);

    /**
     * 设置子事项
     * @param matchWorkItemList
     * @param parentWorkItem
     * @return
     */
    List<WorkItem> setChildren(List<WorkItem> matchWorkItemList, WorkItem parentWorkItem);

    Pagination<WorkItem> findWorkItemByKeyWorks(WorkItemQuery workItemQuery);

    HashMap<String, Integer> findWorkItemNumByWorkType(WorkItemQuery workItemQuery);

    HashMap<String, Integer> findWorkItemNumByWorkStatus(WorkItemQuery workItemQuery);

    HashMap<String, Integer> findWorkItemNumByQuickSearch(WorkItemQuery workItemQuery);

    Pagination<WorkItem>findCanBeRelationParentWorkItemList(WorkItemQuery workItemQuery);

    Pagination<WorkItem>findCanBeRelationPerWorkItemList(WorkItemQuery workItemQuery);

    HashMap<String, Integer>findWorkItemRelationModelCount(String workItemId, String workTypeCode);
}