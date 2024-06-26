package io.thoughtware.kanass.project.workPrivilege.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.kanass.project.workPrivilege.model.WorkRoleFunction;
import io.thoughtware.kanass.project.workPrivilege.model.WorkRoleFunctionQuery;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
* 事项优先级服务接口
*/
@JoinProvider(model = WorkRoleFunction.class)
public interface WorkRoleFunctionService {

    /**
    * 创建事项优先级
    * @param workRoleFunction
    * @return
    */
    String createWorkRoleFunction(@NotNull @Valid WorkRoleFunction workRoleFunction);

    void copyAllWorkRoleFunction(@NotNull @Valid WorkRoleFunction workRoleFunction);

    /**
    * 更新事项优先级
    * @param workRoleFunction
    */
    void updateWorkRoleFunction(@NotNull @Valid WorkRoleFunction workRoleFunction);

    void updateWorkRoleAllFunction(@NotNull @Valid WorkRoleFunction workRoleFunction);
    /**
    * 删除事项优先级
    * @param id
    */
    void deleteWorkRoleFunction(@NotNull String id);

    void deleteRoleFunctionByRoleId(@NotNull @Valid WorkRoleFunction workRoleFunction);

    @FindOne
    WorkRoleFunction findOne(@NotNull String id);

    /**
     * 根据ids 查找事项优先级列表
     * @param idList
     * @return
     */
    @FindList
    List<WorkRoleFunction> findList(@NotNull List<String> idList);

    /**
    *  根据id 查找事项优先级列表
    * @param id
    * @return
    */
    WorkRoleFunction findWorkRoleFunction(@NotNull String id);



    /**
    * 根据条件查找事项优先级列表
    * @param workRoleFunctionQuery
    * @return
    */
    List<WorkRoleFunction> findWorkRoleFunctionList(WorkRoleFunctionQuery workRoleFunctionQuery);

    /**
    * 根据条件按照分页查找事项优先级列表
    * @param workRoleFunctionQuery
    * @return
    */
    Pagination<WorkRoleFunction> findWorkRoleFunctionPage(WorkRoleFunctionQuery workRoleFunctionQuery);

    List<String> findUserWorkFunction(@NotNull String userId, @NotNull String workId, @NotNull String projectId);
}