package io.tiklab.teamwire.project.epic.service;

import io.tiklab.teamwire.workitem.model.WorkItem;
import io.tiklab.core.page.Pagination;

import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.teamwire.project.epic.model.EpicWorkItem;
import io.tiklab.teamwire.project.epic.model.EpicWorkItemQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
* EpicWorkItemService
*/
public interface EpicWorkItemService {

    /**
    * 创建史诗事项关联
    * @param epicWorkItem
    * @return
    */
    String createEpicWorkItem(@NotNull @Valid EpicWorkItem epicWorkItem);

    /**
    * 更新关联史诗事项关联关系
    * @param epicWorkItem
    */
    void updateEpicWorkItem(@NotNull @Valid EpicWorkItem epicWorkItem);

    /**
    * 删除事项史诗关联
    * @param id
    */
    void deleteEpicWorkItem(@NotNull String id);

    /**
     * 根据条件删除事项史诗关联关系
     * @param epicWorkItem
     */
    void deleteEpicWorkItem(EpicWorkItem epicWorkItem);


    @FindOne
    EpicWorkItem findOne(@NotNull String id);
    @FindList
    List<EpicWorkItem> findList(List<String> idList);

    /**
    * 根据id查找史诗下事项列表
    * @param id
    * @return
    */
    EpicWorkItem findEpicWorkItem(@NotNull String id);

    /**
    * 查找所有史诗关联的事项
    * @return
    */
    List<EpicWorkItem> findAllEpicWorkItem();

    /**
    * 查询史诗关联的事项列表
    * @param epicWorkItemQuery
    * @return
    */
    List<EpicWorkItem> findEpicWorkItemList(EpicWorkItemQuery epicWorkItemQuery);

    /**
     * 根据史诗查找事项
     * @param epicWorkItemQuery
     * @return
     */
    List<WorkItem> findWorkItemListByEpic(EpicWorkItemQuery epicWorkItemQuery);

    /**
    * 根据查询条件按分页查询史诗与事项的关联
    * @param epicWorkItemQuery
    * @return
    */
    Pagination<EpicWorkItem> findEpicWorkItemPage(EpicWorkItemQuery epicWorkItemQuery);


    /**
     * 查询需求集下面的子需求集和事项
     * @param epicWorkItemQuery
     * @return
     */
    Map<String, Object> findEpicChildWorkItemAndEpic(EpicWorkItemQuery epicWorkItemQuery);
}