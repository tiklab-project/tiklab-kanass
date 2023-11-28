package io.tiklab.teamwire.projectset.service;

import io.tiklab.core.page.Pagination;


import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.teamwire.project.project.model.ProjectQuery;
import io.tiklab.teamwire.projectset.model.ProjectSet;
import io.tiklab.teamwire.projectset.model.ProjectSetQuery;
import io.tiklab.teamwire.sprint.model.Sprint;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
* 项目集服务接口
*/
public interface ProjectSetService {

    /**
    * 创建项目集
    * @param projectSet
    * @return
    */
    String createProjectSet(@NotNull @Valid ProjectSet projectSet);

    /**
    * 更新项目集
    * @param projectSet
    */
    void updateProjectSet(@NotNull @Valid ProjectSet projectSet);

    /**
    * 删除项目集
    * @param id
    */
    void deleteProjectSet(@NotNull String id);

    /**
     * 根据id查找项目集
     * @param id
     * @return
     */
    ProjectSet findOne(@NotNull String id);

    /**
     * 根据多个id,查找项目集列表
     * @param idList
     * @return
     */
    List<ProjectSet> findList(List<String> idList);

    /**
    * 根据id查找项目集
    * @param id
    * @return
    */
    ProjectSet findProjectSet(@NotNull String id);

    /**
    * 查找所有项目集
    * @return
    */
    List<ProjectSet> findAllProjectSet();

    /**
    * 根据条件查询项目集列表
    * @param projectSetQuery
    * @return
    */
    List<ProjectSet> findProjectSetList(ProjectSetQuery projectSetQuery);

    /**
    * 根据条件按分页查询项目集列表
    * @param projectSetQuery
    * @return
    */
    Pagination<ProjectSet> findProjectSetPage(ProjectSetQuery projectSetQuery);

    /**
     * 查询项目集下面的项目列表
     * @param projectQuery
     * @return
     */
    List<Project> findProjectSetDetailList(ProjectQuery projectQuery);

    /**
     * 查询所有关联项目集项目和未关联项目
     * @param
     * @return
     */
    Map findProjectIsOrNotRe();

    /**
     * 添加关联对象
     * @param
     * @return
     */
    void addRelevance(ProjectSet projectSet);

    /**
     * 根据项目id查询相关的迭代
     * @param
     * @return
     */
    List<Sprint> findSprintList(String projectId);

    /**
     * 根据条件查找关注的项目集列表
     * @param projectSetQuery
     * @return
     */
    List<ProjectSet> findFocusProjectSetList(ProjectSetQuery projectSetQuery);

    /**
     * 查找最近查看的项目集列表
     * @param projectSetQuery
     * @return
     */
    List<ProjectSet> findRecentProjectSetList(ProjectSetQuery projectSetQuery);
}