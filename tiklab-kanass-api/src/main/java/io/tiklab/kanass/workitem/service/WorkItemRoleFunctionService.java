package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.workitem.model.WorkItemRoleFunction;
import io.tiklab.kanass.workitem.model.WorkItemRoleFunctionQuery;
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
@JoinProvider(model = WorkItemRoleFunction.class)
public interface WorkItemRoleFunctionService {

    /**
    * 创建事项优先级
    * @param workItemRoleFunction
    * @return
    */
    String createWorkItemRoleFunction(@NotNull @Valid WorkItemRoleFunction workItemRoleFunction);

    /**
    * 更新事项优先级
    * @param workItemRoleFunction
    */
    void updateWorkItemRoleFunction(@NotNull @Valid WorkItemRoleFunction workItemRoleFunction);

    void updateWorkItemRoleAllFunction(@NotNull @Valid WorkItemRoleFunction workItemRoleFunction);


    void deleteWorkItemRoleFunctionCondition(@NotNull @Valid WorkItemRoleFunctionQuery workItemRoleFunctionQuery);

    /**
    * 删除事项优先级
    * @param id
    */
    void deleteWorkItemRoleFunction(@NotNull String id);

    @FindOne
    WorkItemRoleFunction findOne(@NotNull String id);

    /**
     * 根据ids 查找事项优先级列表
     * @param idList
     * @return
     */
    @FindList
    List<WorkItemRoleFunction> findList(@NotNull List<String> idList);

    /**
    *  根据id 查找事项优先级列表
    * @param id
    * @return
    */
    WorkItemRoleFunction findWorkItemRoleFunction(@NotNull String id);

    /**
    * 查找所有事项优先级列表
    * @return
    */
    @FindAll
    List<WorkItemRoleFunction> findAllWorkItemRoleFunction();

    /**
    * 根据条件查找事项优先级列表
    * @param workItemRoleFunctionQuery
    * @return
    */
    List<WorkItemRoleFunction> findWorkItemRoleFunctionList(WorkItemRoleFunctionQuery workItemRoleFunctionQuery);

    /**
    * 根据条件按照分页查找事项优先级列表
    * @param workItemRoleFunctionQuery
    * @return
    */
    Pagination<WorkItemRoleFunction> findWorkItemRoleFunctionPage(WorkItemRoleFunctionQuery workItemRoleFunctionQuery);



}