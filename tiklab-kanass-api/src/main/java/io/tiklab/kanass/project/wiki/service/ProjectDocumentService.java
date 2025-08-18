package io.tiklab.kanass.project.wiki.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.wiki.model.KanassDocument;
import io.tiklab.kanass.project.wiki.model.ProjectDocument;
import io.tiklab.kanass.project.wiki.model.ProjectDocumentQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ProjectDocumentService {
    /**
     * 创建文档
     * @param projectDocument
     * @return
     */
    String createProjectDocument(@NotNull List<ProjectDocument> projectDocument);

    /**
     * 更新文档
     * @param projectDocument
     */
    void updateProjectDocument(@NotNull @Valid ProjectDocument projectDocument);

    /**
     * 删除文档
     * @param id
     */
    void deleteProjectDocument(@NotNull String id);

    void deleteProjectDocumentList(@NotNull @Valid ProjectDocumentQuery projectDocumentQuery);


    /**
     * 通过文档id删除  和 事项id删除
     * @param projectDocumentQuery
     */
    void deleteProjectDocumentRele(@NotNull @Valid ProjectDocumentQuery projectDocumentQuery);

    ProjectDocument findOne(@NotNull String id);

    /**
     * 根据ids 查找文档
     * @param idList
     * @return
     */
    List<ProjectDocument> findList(List<String> idList);

    /**
     * 根据id查找文档
     * @param id
     * @return
     */
    ProjectDocument findProjectDocument(@NotNull String id);

    /**
     * 查找所有文档
     * @return
     */
    List<ProjectDocument> findAllProjectDocument();

    /**
     * 根据条件查询文档列表
     * @param projectDocumentQuery
     * @return
     */
    List<ProjectDocument> findProjectDocumentList(ProjectDocumentQuery projectDocumentQuery);

    List<KanassDocument> findDocumentPageByProjectId(String projectId);

    /**
     * 根据条件按分页查询文档列表
     * @param projectDocumentQuery
     * @return
     */
    Pagination<ProjectDocument> findProjectDocumentPage(ProjectDocumentQuery projectDocumentQuery);

}
