package io.thoughtware.kanass.sprint.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;

/**
 * 收藏的迭代模型
 */
@ApiModel
@Mapper
public class SprintFocus extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="sprintId",desc="收藏的迭代id")
    private java.lang.String sprintId;

    @ApiProperty(name="masterId",desc="迭代创建人")
    private java.lang.String masterId;

    @ApiProperty(name="projectId",desc="迭代所属项目")
    private java.lang.String projectId;

    @ApiProperty(name="sort",desc="迭代排序")
    private java.lang.Integer sort;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getSprintId() {
        return sprintId;
    }

    public void setSprintId(java.lang.String sprintId) {
        this.sprintId = sprintId;
    }
    public java.lang.String getMasterId() {
        return masterId;
    }

    public void setMasterId(java.lang.String masterId) {
        this.masterId = masterId;
    }
    public java.lang.Integer getSort() {
        return sort;
    }

    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
