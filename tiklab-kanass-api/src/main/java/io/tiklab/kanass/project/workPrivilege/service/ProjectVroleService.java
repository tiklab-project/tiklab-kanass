package io.tiklab.kanass.project.workPrivilege.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.workPrivilege.model.ProjectVrole;
import io.tiklab.kanass.project.workPrivilege.model.ProjectVroleQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项优先级服务接口
*/
@JoinProvider(model = ProjectVrole.class)
public interface ProjectVroleService {

    /**
    * 创建事项优先级
    * @param projectVrole
    * @return
    */
    String createProjectVrole(@NotNull @Valid ProjectVrole projectVrole);

    /**
    * 更新事项优先级
    * @param projectVrole
    */
    void updateProjectVrole(@NotNull @Valid ProjectVrole projectVrole);

    /**
    * 删除事项优先级
    * @param id
    */
    void deleteProjectVrole(@NotNull String id);

    @FindOne
    ProjectVrole findOne(@NotNull String id);

    /**
     * 根据ids 查找事项优先级列表
     * @param idList
     * @return
     */
    @FindList
    List<ProjectVrole> findList(@NotNull List<String> idList);

    /**
    *  根据id 查找事项优先级列表
    * @param id
    * @return
    */
    ProjectVrole findProjectVrole(@NotNull String id);

    /**
    * 查找所有事项优先级列表
    * @return
    */
    @FindAll
    List<ProjectVrole> findAllProjectVrole();

    /**
    * 根据条件查找事项优先级列表
    * @param projectVroleQuery
    * @return
    */
    List<ProjectVrole> findProjectVroleList(ProjectVroleQuery projectVroleQuery);

    /**
    * 根据条件按照分页查找事项优先级列表
    * @param projectVroleQuery
    * @return
    */
    Pagination<ProjectVrole> findProjectVrolePage(ProjectVroleQuery projectVroleQuery);
    
}