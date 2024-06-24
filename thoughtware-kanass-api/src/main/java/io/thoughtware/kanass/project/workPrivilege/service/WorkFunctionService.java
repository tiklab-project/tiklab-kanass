package io.thoughtware.kanass.project.workPrivilege.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.kanass.project.workPrivilege.model.WorkFunction;
import io.thoughtware.kanass.project.workPrivilege.model.WorkFunctionQuery;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项优先级服务接口
*/
@JoinProvider(model = WorkFunction.class)
public interface WorkFunctionService {

    /**
    * 创建事项优先级
    * @param workFunction
    * @return
    */
    String createWorkFunction(@NotNull @Valid WorkFunction workFunction);

    /**
    * 更新事项优先级
    * @param workFunction
    */
    void updateWorkFunction(@NotNull @Valid WorkFunction workFunction);

    /**
    * 删除事项优先级
    * @param id
    */
    void deleteWorkFunction(@NotNull String id);

    @FindOne
    WorkFunction findOne(@NotNull String id);

    /**
     * 根据ids 查找事项优先级列表
     * @param idList
     * @return
     */
    @FindList
    List<WorkFunction> findList(@NotNull List<String> idList);

    /**
    *  根据id 查找事项优先级列表
    * @param id
    * @return
    */
    WorkFunction findWorkFunction(@NotNull String id);

    /**
    * 查找所有事项优先级列表
    * @return
    */
    @FindAll
    List<WorkFunction> findAllWorkFunction();

    /**
    * 根据条件查找事项优先级列表
    * @param workFunctionQuery
    * @return
    */
    List<WorkFunction> findWorkFunctionList(WorkFunctionQuery workFunctionQuery);

    /**
    * 根据条件按照分页查找事项优先级列表
    * @param workFunctionQuery
    * @return
    */
    Pagination<WorkFunction> findWorkFunctionPage(WorkFunctionQuery workFunctionQuery);

}