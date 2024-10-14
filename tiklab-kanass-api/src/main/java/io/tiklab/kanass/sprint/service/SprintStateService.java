package io.tiklab.kanass.sprint.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;
import io.tiklab.kanass.sprint.model.SprintState;
import io.tiklab.kanass.sprint.model.SprintStateQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 迭代状态服务
*/
@JoinProvider(model = SprintState.class)
public interface SprintStateService {

    /**
    * 创建迭代状态
    * @param sprintState
    * @return
    */
    String createSprintState(@NotNull @Valid SprintState sprintState);

    /**
    * 更新迭代状态
    * @param sprintState
    */
    void updateSprintState(@NotNull @Valid SprintState sprintState);

    /**
    * 删除迭代状态
    * @param id
    */
    void deleteSprintState(@NotNull String id);
    @FindOne
    SprintState findOne(@NotNull String id);
    @FindList
    List<SprintState> findList(List<String> idList);

    /**
    * 根据id查找迭代状态
    * @param id
    * @return
    */
    SprintState findSprintState(@NotNull String id);

    /**
    * 查找所有迭代状态
    * @return
    */
    @FindAll
    List<SprintState> findAllSprintState();

    /**
    * 根据条件查询迭代状态列表
    * @param sprintStateQuery
    * @return
    */
    List<SprintState> findSprintStateList(SprintStateQuery sprintStateQuery);

    /**
    * 根据条件按分页查询迭代状态列表
    * @param sprintStateQuery
    * @return
    */
    Pagination<SprintState> findSprintStatePage(SprintStateQuery sprintStateQuery);

}