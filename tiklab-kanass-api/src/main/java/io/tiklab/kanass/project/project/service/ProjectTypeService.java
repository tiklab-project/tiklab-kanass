package io.tiklab.kanass.project.project.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.JoinProvider;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.kanass.project.project.model.ProjectType;
import io.tiklab.kanass.project.project.model.ProjectTypeQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 项目类型服务接口
*/
@JoinProvider(model = ProjectType.class)
public interface ProjectTypeService {

    /**
    * 创建项目类型
    * @param projectType
    * @return
    */
    String createProjectType(@NotNull @Valid ProjectType projectType);

    /**
    * 更新项目类型
    * @param projectType
    */
    void updateProjectType(@NotNull @Valid ProjectType projectType);

    /**
    * 删除项目类型
    * @param id
    */
    String deleteProjectType(@NotNull String id);

    @FindOne
    ProjectType findOne(@NotNull String id);

    @FindList
    List<ProjectType> findList(List<String> idList);


    /**
    * 根据id查找项目类型
    * @param id
    * @return
    */
    ProjectType findProjectType(@NotNull String id);

    /**
    * 查找所有项目类型
    * @return
    */
    @FindAll
    List<ProjectType> findAllProjectType();

    /**
    * 根据条件查询项目类型列表
    * @param projectTypeQuery
    * @return
    */
    List<ProjectType> findProjectTypeList(ProjectTypeQuery projectTypeQuery);

    /**
    * 根据条件按分页查询项目类型
    * @param projectTypeQuery
    * @return
    */
    Pagination<ProjectType> findProjectTypePage(ProjectTypeQuery projectTypeQuery);

}