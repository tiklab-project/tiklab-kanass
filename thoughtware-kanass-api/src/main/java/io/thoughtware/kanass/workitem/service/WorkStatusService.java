package io.thoughtware.kanass.workitem.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;
import io.thoughtware.kanass.workitem.model.WorkStatus;
import io.thoughtware.kanass.workitem.model.WorkStatusQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项状态服务接口
*/
@JoinProvider(model = WorkStatus.class)
public interface WorkStatusService {

    /**
    * 创建事项状态
    * @param workStatus
    * @return
    */
    String createWorkStatus(@NotNull @Valid WorkStatus workStatus);

    /**
    * 更新事项状态
    * @param workStatus
    */
    void updateWorkStatus(@NotNull @Valid WorkStatus workStatus);

    /**
    * 删除事项状态
    * @param id
    */
    void deleteWorkStatus(@NotNull String id);

    @FindOne
    WorkStatus findOne(@NotNull String id);

    @FindList
    List<WorkStatus> findList(@NotNull List<String> idList);

    /**
    * 根据id查找事项状态
    * @param id
    * @return
    */
    WorkStatus findWorkStatus(@NotNull String id);

    /**
    * 查找所有事项状态列表
    * @return
    */
    @FindAll
    List<WorkStatus> findAllWorkStatus();

    /**
    * 根据条件查询事项状态列表
    * @param workStatusQuery
    * @return
    */
    List<WorkStatus> findWorkStatusList(WorkStatusQuery workStatusQuery);

    /**
     * 根据条件按照顺序查询事项状态列表
     * @param workStatusQuery
     * @return
     */
    List<WorkStatus> findWorkStatusListBySorts(WorkStatusQuery workStatusQuery);

    /**
    * 按分页查询事项状态列表
    * @param workStatusQuery
    * @return
    */
    Pagination<WorkStatus> findWorkStatusPage(WorkStatusQuery workStatusQuery);

}