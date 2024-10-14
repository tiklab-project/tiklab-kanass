package io.tiklab.kanass.workitem.model;

import io.tiklab.kanass.project.wiki.model.KanassDocument;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

/**
 * 事项文档模型
 */
@ApiModel
@Mapper
@Join
public class WorkItemDocument extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

//    @NotNull
//    @ApiProperty(name="workItemId",desc="事项id",required = true)
//    private java.lang.String workItemId;

    @NotNull
    @ApiProperty(name="workItem",desc="关联事项",required = true)
    @Mappings({
            @Mapping(source = "workItem.id",target = "workItemId")
    })
    @JoinQuery(key = "id")
    private WorkItem workItem;

    @NotNull
    @ApiProperty(name="documentId",desc="文档id",required = true)
    private java.lang.String documentId;


    private KanassDocument kanassDocument;

    @NotNull
    @ApiProperty(name="repositoryId",desc="知识库id",required = true)
    private java.lang.String repositoryId;

    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

    @ApiProperty(name="projectId",desc="所属项目")
    private String projectId;


    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public WorkItem getWorkItem() {
        return workItem;
    }

    public void setWorkItem(WorkItem workItem) {
        this.workItem = workItem;
    }

    public java.lang.String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(java.lang.String documentId) {
        this.documentId = documentId;
    }
    public java.lang.Integer getSort() {
        return sort;
    }

    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public KanassDocument getKanassDocument() {
        return kanassDocument;
    }

    public void setKanassDocument(KanassDocument kanassDocument) {
        this.kanassDocument = kanassDocument;
    }
}
