package io.tiklab.kanass.project.wiki.model;

import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
@Mapper
public class ProjectDocument {
    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public KanassDocument getKanassDocument() {
        return kanassDocument;
    }

    public void setKanassDocument(KanassDocument kanassDocument) {
        this.kanassDocument = kanassDocument;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
