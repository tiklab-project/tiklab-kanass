package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.workitem.model.WorkItemRoleFunctionDm;
import io.tiklab.kanass.workitem.model.WorkItemRoleFunctionDmQuery;
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
@JoinProvider(model = WorkItemRoleFunctionDm.class)
public interface WorkItemRoleFunctionDmService {

    /**
    * 创建事项优先级
    * @param workItemRoleFunction
    * @return
    */
    String createWorkItemRoleFunctionDm(@NotNull @Valid WorkItemRoleFunctionDm workItemRoleFunction);

    String copyWorkItemRoleFunctionDm(@NotNull @Valid WorkItemRoleFunctionDm workItemRoleFunctionDm);

    void updateWorkItemRoleFunctionDm1();

    /**
    * 更新事项优先级
    * @param workItemRoleFunction
    */
    void updateWorkItemRoleFunctionDm(@NotNull @Valid WorkItemRoleFunctionDm workItemRoleFunction);

    void updateWorkItemRoleAllFunctionDm(@NotNull @Valid WorkItemRoleFunctionDm workItemRoleFunctionDm);
    /**
    * 删除事项优先级
    * @param id
    */
    void deleteWorkItemRoleFunctionDm(@NotNull String id);

    @FindOne
    WorkItemRoleFunctionDm findOne(@NotNull String id);

    /**
     * 根据ids 查找事项优先级列表
     * @param idList
     * @return
     */
    @FindList
    List<WorkItemRoleFunctionDm> findList(@NotNull List<String> idList);

    /**
    *  根据id 查找事项优先级列表
    * @param id
    * @return
    */
    WorkItemRoleFunctionDm findWorkItemRoleFunctionDm(@NotNull String id);

    List<String> findWorkItemRoleFunctionDmCode(@NotNull WorkItemRoleFunctionDmQuery workItemRoleFunctionQuery);

    /**
    * 查找所有事项优先级列表
    * @return
    */
    @FindAll
    List<WorkItemRoleFunctionDm> findAllWorkItemRoleFunctionDm();

    /**
    * 根据条件查找事项优先级列表
    * @param workItemRoleFunctionQuery
    * @return
    */
    List<WorkItemRoleFunctionDm> findWorkItemRoleFunctionDmList(WorkItemRoleFunctionDmQuery workItemRoleFunctionQuery);

    /**
    * 根据条件按照分页查找事项优先级列表
    * @param workItemRoleFunctionQuery
    * @return
    */
    Pagination<WorkItemRoleFunctionDm> findWorkItemRoleFunctionDmPage(WorkItemRoleFunctionDmQuery workItemRoleFunctionQuery);


}