package io.thoughtware.kanass.project.version.service;

import io.thoughtware.core.page.Pagination;

import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.JoinProvider;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.kanass.project.version.model.ProjectVersion;
import io.thoughtware.kanass.project.version.model.ProjectVersionQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 项目版本服务接口
*/
@JoinProvider(model = ProjectVersion.class)
public interface ProjectVersionService {

    /**
    * 创建项目版本
    * @param projectVersion
    * @return
    */
    String createVersion(@NotNull @Valid ProjectVersion projectVersion);

    /**
    * 更新项目版本
    * @param projectVersion
    */
    void updateVersion(@NotNull @Valid ProjectVersion projectVersion);

    /**
    * 删除项目版本
    * @param id
    */
    void deleteVersion(@NotNull String id);

    @FindOne
    ProjectVersion findOne(@NotNull String id);

    @FindList
    List<ProjectVersion> findList(List<String> idList);

    /**
    * 根据id查找项目版本
    * @param id
    * @return
    */
    ProjectVersion findVersion(@NotNull String id);

    /**
    * 查找所有项目版本
    * @return
    */
    @FindAll
    List<ProjectVersion> findAllVersion();

    /**
    * 根据条件查找项目类型列表
    * @param ProjectVersionQuery
    * @return
    */
    List<ProjectVersion> findVersionList(ProjectVersionQuery ProjectVersionQuery);

    /**
    * 根据条件按分页查询项目类型列表
    * @param ProjectVersionQuery
    * @return
    */
    Pagination<ProjectVersion> findVersionPage(ProjectVersionQuery ProjectVersionQuery);

    List<ProjectVersion> findVersionFocusList(ProjectVersionQuery ProjectVersionQuery);

}