package io.thoughtware.kanass.workitem.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 事项文档模型
 */
@ApiModel
@Mapper
public class WorkItemDocument extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="workItemId",desc="事项id",required = true)
    private java.lang.String workItemId;

    @NotNull
    @ApiProperty(name="documentId",desc="文档id",required = true)
    private java.lang.String documentId;

    @NotNull
    @ApiProperty(name="repositoryId",desc="知识库id",required = true)
    private java.lang.String repositoryId;

    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(java.lang.String workItemId) {
        this.workItemId = workItemId;
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
}
