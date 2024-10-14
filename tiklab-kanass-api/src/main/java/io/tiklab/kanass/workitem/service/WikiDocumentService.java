package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.wiki.model.KanassDocument;
import io.tiklab.kanass.project.wiki.model.NodeQuery;
import io.tiklab.kanass.project.wiki.model.WikiDocument;
import io.tiklab.kanass.workitem.model.WorkItemDocumentQuery;

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
    List<WikiDocument> findDocumentList(@NotNull @Valid NodeQuery nodeQuery);

}