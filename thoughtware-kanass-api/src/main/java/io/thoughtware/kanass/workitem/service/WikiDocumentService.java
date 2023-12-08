package io.thoughtware.kanass.workitem.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.kanass.project.wiki.model.DocumentQuery;
import io.thoughtware.kanass.project.wiki.model.KanassDocument;
import io.thoughtware.kanass.project.wiki.model.WikiDocument;
import io.thoughtware.kanass.workitem.model.WorkItemDocumentQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项附件服务接口
*/
public interface WikiDocumentService {

    /**
    * 根据条件查询事项附件列表
    * @param workItemDocumentQuery
    * @return
    */
    Pagination<KanassDocument> findUnRelationWorkDocumentList(@NotNull @Valid WorkItemDocumentQuery workItemDocumentQuery);
    List<WikiDocument> findDocumentList(@NotNull @Valid DocumentQuery documentQuery);

}