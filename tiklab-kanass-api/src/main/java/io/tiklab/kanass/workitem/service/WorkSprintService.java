package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.workitem.model.WorkSprint;
import io.tiklab.kanass.workitem.model.WorkSprintQuery;
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
@JoinProvider(model = WorkSprint.class)
public interface WorkSprintService {

    /**
    * 创建事项状态
    * @param workSprint
    * @return
    */
    String createWorkSprint(@NotNull @Valid WorkSprint workSprint);
    void createBatchWorkSprint(@NotNull String valueStrings);
    /**
    * 更新事项状态
    * @param workSprint
    */
    void updateWorkSprint(@NotNull @Valid WorkSprint workSprint);

    /**
    * 删除事项状态
    * @param id
    */
    void deleteWorkSprint(@NotNull String id);

    void deleteWorkSprint(WorkSprintQuery workSprintQuery);
    @FindOne
    WorkSprint findOne(@NotNull String id);

    @FindList
    List<WorkSprint> findList(@NotNull List<String> idList);

    /**
    * 根据id查找事项状态
    * @param id
    * @return
    */
    WorkSprint findWorkSprint(@NotNull String id);

    /**
    * 查找所有事项状态列表
    * @return
    */
    @FindAll
    List<WorkSprint> findAllWorkSprint();

    /**
    * 根据条件查询事项状态列表
    * @param workSprintQuery
    * @return
    */
    List<WorkSprint> findWorkSprintList(WorkSprintQuery workSprintQuery);


    /**
    * 按分页查询事项状态列表
    * @param workSprintQuery
    * @return
    */
    Pagination<WorkSprint> findWorkSprintPage(WorkSprintQuery workSprintQuery);
    List<String> findSprintWorkItemIds(@NotNull String sprintId);
    List<Map<String, String>> findSprintWorkItemNum(String sprintIds);

}