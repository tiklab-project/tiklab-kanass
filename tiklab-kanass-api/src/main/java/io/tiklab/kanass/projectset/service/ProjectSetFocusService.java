package io.tiklab.kanass.projectset.service;


import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.projectset.model.ProjectSetFocus;
import io.tiklab.kanass.projectset.model.ProjectSetFocusQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 项目集收藏服务接口
*/
public interface ProjectSetFocusService {

    /**
    * 创建收藏的项目集
    * @param projectSetFocus
    * @return
    */
    String createProjectSetFocus(@NotNull @Valid ProjectSetFocus projectSetFocus);

    /**
    * 更新收藏的项目集
    * @param projectSetFocus
    */
    void updateProjectSetFocus(@NotNull @Valid ProjectSetFocus projectSetFocus);

    /**
    * 删除收藏的项目集
    * @param id
    */
    void deleteProjectSetFocus(@NotNull String id);

    /**
     * 根据id 查找收藏项目集
     * @param id
     * @return
     */
    ProjectSetFocus findOne(@NotNull String id);

    /**
     * 根据多个ids 查找关注项目集列表
     * @param idList
     * @return
     */
    List<ProjectSetFocus> findList(List<String> idList);

    /**
    * 根据id 查找收藏的项目集
    * @param id
    * @return
    */
    ProjectSetFocus findProjectSetFocus(@NotNull String id);

    /**
    * 查找所有收藏的项目集
    * @return
    */
    List<ProjectSetFocus> findAllProjectSetFocus();

    /**
    * 根据条件查询收藏项目集列表
    * @param projectSetFocusQuery
    * @return
    */
    List<ProjectSetFocus> findProjectSetFocusList(ProjectSetFocusQuery projectSetFocusQuery);

    /**
    * 根据条件按分页查询收藏项目集列表
    * @param projectSetFocusQuery
    * @return
    */
    Pagination<ProjectSetFocus> findProjectSetFocusPage(ProjectSetFocusQuery projectSetFocusQuery);


    /**
     * 按照添加删除收藏的项目集
     * @param projectSetFocusQuery
     */
    void deleteProjectSetFocusByQuery(@NotNull @Valid ProjectSetFocusQuery projectSetFocusQuery);

}