package io.tiklab.kanass.sprint.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.sprint.model.SprintFocus;
import io.tiklab.kanass.sprint.model.SprintFocusQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 迭代收藏服务接口
*/
public interface SprintFocusService {

    /**
    * 创建迭代收藏
    * @param sprintFocus
    * @return
    */
    String createSprintFocus(@NotNull @Valid SprintFocus sprintFocus);

    /**
    * 更新收藏的迭代
    * @param sprintFocus
    */
    void updateSprintFocus(@NotNull @Valid SprintFocus sprintFocus);

    /**
    * 删除收藏的迭代
    * @param id
    */
    void deleteSprintFocus(@NotNull String id);

    /**
     * 根据添加删除收藏的迭代
     * @param sprintFocusQuery
     */
    void deleteSprintFocusByQuery(SprintFocusQuery sprintFocusQuery);

    /**
     * 根据id查找收藏的迭代
     * @param id
     * @return
     */
    SprintFocus findOne(@NotNull String id);

    /**
     * 根据多个迭代id,查找收藏的迭代
     * @param idList
     * @return
     */
    List<SprintFocus> findList(List<String> idList);

    /**
    * 根据id查找迭代收藏
    * @param id
    * @return
    */
    SprintFocus findSprintFocus(@NotNull String id);

    /**
    * 查找所有收藏迭代
    * @return
    */
    List<SprintFocus> findAllSprintFocus();

    /**
    * 根据条件查询收藏的迭代列表
    * @param sprintFocusQuery
    * @return
    */
    List<SprintFocus> findSprintFocusList(SprintFocusQuery sprintFocusQuery);

    /**
    * 根据条件按分页查询收藏的迭代列表
    * @param sprintFocusQuery
    * @return
    */
    Pagination<SprintFocus> findSprintFocusPage(SprintFocusQuery sprintFocusQuery);

    List<String> findFocusSprintIds();
}