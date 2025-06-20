package io.tiklab.kanass.workitem.service;

import io.tiklab.kanass.workitem.model.WorkBoard;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.model.WorkItemQuery;
import io.tiklab.kanass.workitem.model.WorkUserGroupBoard;
import io.tiklab.core.page.Pagination;

import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.JoinProvider;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    String createImportWorkItem(@NotNull @Valid WorkItem workItem);

    /**
    * 更新事项
    * @param workItem
    */
    void updateWorkItem(@NotNull @Valid WorkItem workItem);

    void updateWork(@NotNull WorkItem workItem);

    void updateTodoTaskData(WorkItem workItem);
    /**
    * 删除事项
    * @param id
    */
    void deleteWorkItem(@NotNull String id);
    void deleteWorkItemAndChildren(@NotNull String id);
    void deleteWorkItemCondition(WorkItemQuery workItemQuery);
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

    WorkItem findWorkItemAndSprintVersion(@NotNull String id);

    WorkItem findWorkItemAndUsedTime(@NotNull String id);

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

    List<WorkItem> findWorkItemListNoJoinQuery(WorkItemQuery workItemQuery);
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
    Pagination<WorkItem> findSelectChildrenWorkItemList(WorkItemQuery workItemQuery);

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

    HashMap<String, Integer> findWorkItemListNumByWorkType(WorkItemQuery workItemQuery);

    HashMap<String, Integer> findWorkItemNumByWorkStatus(WorkItemQuery workItemQuery);

    HashMap<String, Integer> findWorkItemNumByQuickSearch(WorkItemQuery workItemQuery);

    Pagination<WorkItem>findCanBeRelationParentWorkItemList(WorkItemQuery workItemQuery);

    Pagination<WorkItem>findCanBeRelationPerWorkItemList(WorkItemQuery workItemQuery);

    HashMap<String, Integer>findWorkItemRelationModelCount(String workItemId, String workTypeCode);



    /**
     * 批量更新事项的迭代
     * @param oldSprintId
     * @param newSprintId
     */
    void updateBatchWorkItemSprint(@NotNull String oldSprintId, String newSprintId);

    /**
     * 批量更新事项的版本
     * @param oldVersionId
     * @param newVersionId
     */
    void updateBatchWorkItemVersion(@NotNull String oldVersionId, String newVersionId);


    List<WorkItem> findSprintWorkItemList(@NotNull String sprintId);

    List<WorkItem> findVersionWorkItemList(@NotNull String versionId);

    /**
     * 查找迭代下各个状态的事项
     * @param sprintId
     * @return
     */
    HashMap<String, Integer> findSprintWorkItemNum(@NotNull String sprintId);

    HashMap<String, Integer> findVersionWorkItemNum(@NotNull String versionId);

    /**
     * 查找迭代下的预计工时和剩余工时
     * @param springId
     * @return
     */
    Map<String, Integer> findSprintWorkTime(@NotNull String springId);

    /**
     * 查找版本下的预计工时和剩余工时
     * @param versionId
     * @return
     */
    Map<String, Integer> findVersionWorkTime(@NotNull String versionId);

    /**
     * 查看事项有几级下级事项
     */
    Integer findChildrenLevel(@NotNull String id);

    void updateEpicWork(String projectId, String workTypeId, String dmWorkTypeId);
    List<WorkItem> findConditionWorkItemList(WorkItemQuery workItemQuery);

    WorkItem findWorkItemAndChidren(String id);

    boolean haveChildren(@NotNull String id);

    List<String> findWorkItemAndChildrenIds(String workItemId);

    /**
     * 查询首页、项目等概览页面待办事项数量
     * @param workItemQuery
     * @return
     */
    Map<String, Integer> findTodoPageWorkItemNum(WorkItemQuery workItemQuery);
}