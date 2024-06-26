package io.thoughtware.kanass.project.workPrivilege.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.kanass.project.workPrivilege.model.WorkPrivilege;
import io.thoughtware.kanass.project.workPrivilege.model.WorkPrivilegeQuery;
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
@JoinProvider(model = WorkPrivilege.class)
public interface WorkPrivilegeService {

    /**
    * 创建事项优先级
    * @param workPrivilege
    * @return
    */
    String createWorkPrivilege(@NotNull @Valid WorkPrivilege workPrivilege);

    String copyWorkPrivilege(@NotNull @Valid WorkPrivilege workPrivilege);

    /**
    * 更新事项优先级
    * @param workPrivilege
    */
    void updateWorkPrivilege(@NotNull @Valid WorkPrivilege workPrivilege);

    /**
    * 删除事项优先级
    * @param id
    */
    void deleteWorkPrivilege(@NotNull String id);

    @FindOne
    WorkPrivilege findOne(@NotNull String id);

    /**
     * 根据ids 查找事项优先级列表
     * @param idList
     * @return
     */
    @FindList
    List<WorkPrivilege> findList(@NotNull List<String> idList);

    /**
    *  根据id 查找事项优先级列表
    * @param id
    * @return
    */
    WorkPrivilege findWorkPrivilege(@NotNull String id);

    /**
    * 查找所有事项优先级列表
    * @return
    */
    @FindAll
    List<WorkPrivilege> findAllWorkPrivilege();

    /**
    * 根据条件查找事项优先级列表
    * @param workPrivilegeQuery
    * @return
    */
    List<WorkPrivilege> findWorkPrivilegeList(WorkPrivilegeQuery workPrivilegeQuery);

    /**
    * 根据条件按照分页查找事项优先级列表
    * @param workPrivilegeQuery
    * @return
    */
    Pagination<WorkPrivilege> findWorkPrivilegePage(WorkPrivilegeQuery workPrivilegeQuery);

}