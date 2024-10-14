package io.tiklab.kanass.projectset.model;

import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

/**
 * 项目集收藏模板
 */
@ApiModel
@Join
@Mapper
public class ProjectSetFocus extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="projectSetId",desc="项目集id")
    private java.lang.String projectSetId;

    @ApiProperty(name="masterId",desc="项目集负责人")
    private java.lang.String masterId;

    @ApiProperty(name="sort",desc="项目集排序")
    private java.lang.Integer sort;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public String getProjectSetId() {
        return projectSetId;
    }

    public void setProjectSetId(String projectSetId) {
        this.projectSetId = projectSetId;
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
