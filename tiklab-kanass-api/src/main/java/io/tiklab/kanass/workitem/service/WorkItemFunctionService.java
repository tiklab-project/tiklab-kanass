package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.workitem.model.WorkItemFunction;
import io.tiklab.kanass.workitem.model.WorkItemFunctionQuery;
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
@JoinProvider(model = WorkItemFunction.class)
public interface WorkItemFunctionService {

    /**
    * 创建事项优先级
    * @param workItemFunction
    * @return
    */
    String createWorkItemFunction(@NotNull @Valid WorkItemFunction workItemFunction);

    /**
    * 更新事项优先级
    * @param workItemFunction
    */
    void updateWorkItemFunction(@NotNull @Valid WorkItemFunction workItemFunction);

    /**
    * 删除事项优先级
    * @param id
    */
    void deleteWorkItemFunction(@NotNull String id);

    @FindOne
    WorkItemFunction findOne(@NotNull String id);

    /**
     * 根据ids 查找事项优先级列表
     * @param idList
     * @return
     */
    @FindList
    List<WorkItemFunction> findList(@NotNull List<String> idList);

    /**
    *  根据id 查找事项优先级列表
    * @param id
    * @return
    */
    WorkItemFunction findWorkItemFunction(@NotNull String id);

    /**
    * 查找所有事项优先级列表
    * @return
    */
    @FindAll
    List<WorkItemFunction> findAllWorkItemFunction();

    /**
    * 根据条件查找事项优先级列表
    * @param workItemFunctionQuery
    * @return
    */
    List<WorkItemFunction> findWorkItemFunctionList(WorkItemFunctionQuery workItemFunctionQuery);

    /**
    * 根据条件按照分页查找事项优先级列表
    * @param workItemFunctionQuery
    * @return
    */
    Pagination<WorkItemFunction> findWorkItemFunctionPage(WorkItemFunctionQuery workItemFunctionQuery);


}