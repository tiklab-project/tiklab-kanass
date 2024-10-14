package io.tiklab.kanass.project.project.service;


import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.project.model.ProjectFocus;
import io.tiklab.kanass.project.project.model.ProjectFocusQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 项目收藏服务接口
*/
public interface ProjectFocusService {

    /**
    * 创建收藏的项目
    * @param projectFocus
    * @return
    */
    String createProjectFocus(@NotNull @Valid ProjectFocus projectFocus);

    /**
    * 更新项目收藏数据
    * @param projectFocus
    */
    void updateProjectFocus(@NotNull @Valid ProjectFocus projectFocus);

    void deleteProjectFocusByQuery(@NotNull @Valid ProjectFocusQuery projectFocusQuery);

    /**
    * 根据id删除收藏的项目
    * @param id
    */
    void deleteProjectFocus(@NotNull String id);

    ProjectFocus findOne(@NotNull String id);

    /**
     * 根据多个id 查找收藏的项目列表
     * @param idList
     * @return
     */
    List<ProjectFocus> findList(List<String> idList);

    /**
    * 根据查找收藏的项目
    * @param id
    * @return
    */
    ProjectFocus findProjectFocus(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<ProjectFocus> findAllProjectFocus();

    /**
    * 根据条件查询项目收藏列表
    * @param projectFocusQuery
    * @return
    */
    List<ProjectFocus> findProjectFocusList(ProjectFocusQuery projectFocusQuery);

    /**
    * 根据条件按分页查询项目的收藏列表
    * @param projectFocusQuery
    * @return
    */
    Pagination<ProjectFocus> findProjectFocusPage(ProjectFocusQuery projectFocusQuery);

}