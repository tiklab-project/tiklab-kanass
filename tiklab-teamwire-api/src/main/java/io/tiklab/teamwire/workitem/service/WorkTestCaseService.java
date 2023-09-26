package io.tiklab.teamwire.workitem.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.teamwire.project.test.model.ProjectTestCase;
import io.tiklab.teamwire.workitem.model.WorkTestCase;
import io.tiklab.teamwire.workitem.model.WorkTestCaseQuery;
import io.tiklab.user.user.model.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项文档服务接口
*/
public interface WorkTestCaseService {

    /**
    * 创建事项文档
    * @param workTestCase
    * @return
    */
    String createWorkTestCase(@NotNull  List<WorkTestCase> workTestCase);

    /**
    * 更新事项文档
    * @param workTestCase
    */
    void updateWorkTestCase(@NotNull @Valid WorkTestCase workTestCase);

    /**
    * 删除事项文档
    * @param id
    */
    void deleteWorkTestCase(@NotNull String id);

    /**
     * 通过文档id删除事项文档
     * @param documentId
     */
     void delete(@NotNull String documentId);

    /**
     * 通过文档id删除  和 事项id删除
     * @param workTestCaseQuery
     */
    void deleteWorkTestCaseRele(@NotNull @Valid WorkTestCaseQuery workTestCaseQuery);

    WorkTestCase findOne(@NotNull String id);

    /**
     * 根据ids 查找事项文档
     * @param idList
     * @return
     */
    List<WorkTestCase> findList(List<String> idList);

    /**
    * 根据id查找事项文档
    * @param id
    * @return
    */
    WorkTestCase findWorkTestCase(@NotNull String id);

    /**
    * 查找所有事项文档
    * @return
    */
    List<WorkTestCase> findAllWorkTestCase();

    /**
    * 根据条件查询事项文档列表
    * @param workTestCaseQuery
    * @return
    */
    List<WorkTestCase> findWorkTestCaseList(WorkTestCaseQuery workTestCaseQuery);

    List<ProjectTestCase> findTestCasePageByWorkItemId(String workItemId);

    /**
    * 根据条件按分页查询事项文档列表
    * @param workTestCaseQuery
    * @return
    */
    Pagination<WorkTestCase> findWorkTestCasePage(WorkTestCaseQuery workTestCaseQuery);

    Pagination<ProjectTestCase> findUnRelationWorkTestCaseList(@NotNull @Valid WorkTestCaseQuery workTestCaseQuery);

    List<User> findTestOnRepositoryUserList(String[] repositoryIds);

}