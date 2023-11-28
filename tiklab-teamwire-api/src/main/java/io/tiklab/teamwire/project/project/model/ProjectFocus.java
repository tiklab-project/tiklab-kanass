package io.tiklab.teamwire.project.project.model;

import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

/**
 * 项目收藏模型
 */
@ApiModel
@Mapper
public class ProjectFocus extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="projectId",desc="收藏项目id")
    private java.lang.String projectId;

    @ApiProperty(name="masterId",desc="收藏者id")
    private java.lang.String masterId;

    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getProjectId() {
        return projectId;
    }

    public void setProjectId(java.lang.String projectId) {
        this.projectId = projectId;
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
}
