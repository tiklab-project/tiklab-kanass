package io.tiklab.teamwire.workitem.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.teamwire.workitem.model.WorkPriority;
import io.tiklab.teamwire.workitem.model.WorkPriorityQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项优先级服务接口
*/
@JoinProvider(model = WorkPriority.class)
public interface WorkPriorityService {

    /**
    * 创建事项优先级
    * @param workPriority
    * @return
    */
    String createWorkPriority(@NotNull @Valid WorkPriority workPriority);

    /**
    * 更新事项优先级
    * @param workPriority
    */
    void updateWorkPriority(@NotNull @Valid WorkPriority workPriority);

    /**
    * 删除事项优先级
    * @param id
    */
    void deleteWorkPriority(@NotNull String id);

    @FindOne
    WorkPriority findOne(@NotNull String id);

    /**
     * 根据ids 查找事项优先级列表
     * @param idList
     * @return
     */
    @FindList
    List<WorkPriority> findList(@NotNull List<String> idList);

    /**
    *  根据id 查找事项优先级列表
    * @param id
    * @return
    */
    WorkPriority findWorkPriority(@NotNull String id);

    /**
    * 查找所有事项优先级列表
    * @return
    */
    @FindAll
    List<WorkPriority> findAllWorkPriority();

    /**
    * 根据条件查找事项优先级列表
    * @param workPriorityQuery
    * @return
    */
    List<WorkPriority> findWorkPriorityList(WorkPriorityQuery workPriorityQuery);

    /**
    * 根据条件按照分页查找事项优先级列表
    * @param workPriorityQuery
    * @return
    */
    Pagination<WorkPriority> findWorkPriorityPage(WorkPriorityQuery workPriorityQuery);

}