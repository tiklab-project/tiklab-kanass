package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.test.model.ProjectTestCase;
import io.tiklab.kanass.workitem.model.WorkTransitionHistory;
import io.tiklab.kanass.workitem.model.WorkTransitionHistoryQuery;
import io.tiklab.user.user.model.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项流转历史服务接口
*/
public interface WorkTransitionHistoryService {

    /**
    * 创建事项流转历史
    * @param workTransitionHistory
    * @return
    */
    String createWorkTransitionHistory(@NotNull WorkTransitionHistory workTransitionHistory);

    /**
    * 更新事项流转历史
    * @param workTransitionHistory
    */
    void updateWorkTransitionHistory(@NotNull @Valid WorkTransitionHistory workTransitionHistory);

    /**
    * 删除事项流转历史
    * @param id
    */
    void deleteWorkTransitionHistory(@NotNull String id);


    /**
     * 通过文档id删除  和 事项id删除
     * @param workTransitionHistoryQuery
     */
    void deleteWorkTransitionHistoryList(@NotNull @Valid WorkTransitionHistoryQuery workTransitionHistoryQuery);

    WorkTransitionHistory findOne(@NotNull String id);

    /**
     * 根据ids 查找事项流转历史
     * @param idList
     * @return
     */
    List<WorkTransitionHistory> findList(List<String> idList);

    /**
    * 根据id查找事项流转历史
    * @param id
    * @return
    */
    WorkTransitionHistory findWorkTransitionHistory(@NotNull String id);

    /**
    * 查找所有事项流转历史
    * @return
    */
    List<WorkTransitionHistory> findAllWorkTransitionHistory();

    /**
    * 根据条件查询事项流转历史列表
    * @param workTransitionHistoryQuery
    * @return
    */
    List<WorkTransitionHistory> findWorkTransitionHistoryList(WorkTransitionHistoryQuery workTransitionHistoryQuery);

    /**
    * 根据条件按分页查询事项流转历史列表
    * @param workTransitionHistoryQuery
    * @return
    */
    Pagination<WorkTransitionHistory> findWorkTransitionHistoryPage(WorkTransitionHistoryQuery workTransitionHistoryQuery);


}