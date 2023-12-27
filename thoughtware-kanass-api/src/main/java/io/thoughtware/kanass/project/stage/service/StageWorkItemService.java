package io.thoughtware.kanass.project.stage.service;

import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;
import io.thoughtware.kanass.project.stage.model.StageWorkItemQuery;
import io.thoughtware.kanass.project.stage.model.StageWorkItem;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
* 阶段事项关联服务接口
*/
@JoinProvider(model = StageWorkItem.class)
public interface StageWorkItemService {

    /**
    * 创建阶段事项关联关系
    * @param stageWorkItem
    * @return
    */
    String createStageWorkItem(@NotNull @Valid StageWorkItem stageWorkItem);

    /**
    * 更新阶段事项关联关系
    * @param stageWorkItem
    */
    void updateStageWorkItem(@NotNull @Valid StageWorkItem stageWorkItem);

    /**
    * 删除关联关系
    * @param id
    */
    void deleteStageWorkItem(@NotNull String id);

    /**
     * 根据条件删除阶段事项关联关系
     * @param stageWorkItem
     */
    void deleteStageWorkItem(StageWorkItem stageWorkItem);

    /**
     * 根据id 精确查找关联关系
     * @param id
     * @return
     */
    @FindOne
    StageWorkItem findOne(@NotNull String id);

    /**
     * 根据多个id 查找阶段关联事项列表
     * @param idList
     * @return
     */
    @FindList
    List<StageWorkItem> findList(List<String> idList);

    /**
    * 根据id查找阶段事项关联
    * @param id
    * @return
    */
    @FindAll
    StageWorkItem findStageWorkItem(@NotNull String id);

    /**
    * 查找所有阶段关联事项
    * @return
    */
    List<StageWorkItem> findAllStageWorkItem();

    /**
    * 根据条件查找阶段的关联事项
    * @param stageWorkItemQuery
    * @return
    */
    List<StageWorkItem> findStageWorkItemList(StageWorkItemQuery stageWorkItemQuery);

    /**
    * 按分页查询阶段事项关联列表
    * @param stageWorkItemQuery
    * @return
    */
    Pagination<StageWorkItem> findStageWorkItemPage(StageWorkItemQuery stageWorkItemQuery);

    /**
     * 根据阶段查找关联的事项id
     * @param stageWorkItemQuery
     * @return
     */
    List<WorkItem> findWorkItemListByStage(StageWorkItemQuery stageWorkItemQuery);

    /**
     * 查找某个阶段下的子阶段和关联事项
     * @param stageWorkItemQuery
     * @return
     */
    Map<String, Object> findStageChildWorkItemAndStage(StageWorkItemQuery stageWorkItemQuery);

}