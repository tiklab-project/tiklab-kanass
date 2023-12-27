package io.thoughtware.kanass.sprint.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;

/**
 * 迭代状态模板
 */
@ApiModel
@Join
@Mapper
public class SprintState extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="name",desc="迭代状态名称")
    private java.lang.String name;

    @ApiProperty(name="description",desc="迭代描述")
    private java.lang.String description;

    @ApiProperty(name="sort",desc="迭代状态排序")
    private java.lang.Integer sort;

    @ApiProperty(name="grouper",desc="迭代状态分组")
    private java.lang.String grouper;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }
    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public java.lang.String getGrouper() {
        return grouper;
    }

    public void setGrouper(java.lang.String grouper) {
        this.grouper = grouper;
    }

}
