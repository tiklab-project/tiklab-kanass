package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.kanass.project.wiki.model.KanassDocument;
import io.tiklab.kanass.workitem.model.WorkItemDocument;
import io.tiklab.kanass.workitem.model.WorkItemDocumentQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项文档服务接口
*/
public interface WorkItemDocumentService {

    /**
    * 创建事项文档
    * @param workItemDocument
    * @return
    */
    String createWorkItemDocument(@NotNull  List<WorkItemDocument> workItemDocument);

    /**
    * 更新事项文档
    * @param workItemDocument
    */
    void updateWorkItemDocument(@NotNull @Valid WorkItemDocument workItemDocument);

    /**
    * 删除事项文档
    * @param id
    */
    void deleteWorkItemDocument(@NotNull String id);

    void deleteWorkItemDocumentList(@NotNull @Valid WorkItemDocumentQuery workItemDocumentQuery);


    /**
     * 通过文档id删除  和 事项id删除
     * @param workItemDocumentQuery
     */
    void deleteWorkItemDocumentRele(@NotNull @Valid WorkItemDocumentQuery workItemDocumentQuery);

    WorkItemDocument findOne(@NotNull String id);

    /**
     * 根据ids 查找事项文档
     * @param idList
     * @return
     */
    List<WorkItemDocument> findList(List<String> idList);

    /**
    * 根据id查找事项文档
    * @param id
    * @return
    */
    WorkItemDocument findWorkItemDocument(@NotNull String id);

    /**
    * 查找所有事项文档
    * @return
    */
    List<WorkItemDocument> findAllWorkItemDocument();

    /**
    * 根据条件查询事项文档列表
    * @param workItemDocumentQuery
    * @return
    */
    List<WorkItemDocument> findWorkItemDocumentList(WorkItemDocumentQuery workItemDocumentQuery);

    List<KanassDocument> findDocumentPageByWorkItemId(String workItemId);

    /**
    * 根据条件按分页查询事项文档列表
    * @param workItemDocumentQuery
    * @return
    */
    Pagination<WorkItemDocument> findWorkItemDocumentPage(WorkItemDocumentQuery workItemDocumentQuery);



}