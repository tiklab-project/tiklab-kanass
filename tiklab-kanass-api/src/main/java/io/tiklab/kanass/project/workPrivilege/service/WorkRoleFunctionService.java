package io.tiklab.kanass.project.workPrivilege.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.workPrivilege.model.WorkRoleFunction;
import io.tiklab.kanass.project.workPrivilege.model.WorkRoleFunctionQuery;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

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

    Set<String> findUserWorkFunction(@NotNull String userId, @NotNull String workId);
}