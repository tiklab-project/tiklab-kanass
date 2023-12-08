package io.thoughtware.kanass.workitem.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 事项状态模型
 */
@ApiModel
@Mapper
public class WorkStatus extends BaseModel {

    @ApiProperty(name="id",desc="事项状态ID")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="name",desc="事项状态名称",eg="@text32",required = true)
    private java.lang.String name;

    @ApiProperty(name="desc",desc="描述",eg="@text32")
    private java.lang.String desc;

    @ApiProperty(name="sort",desc="排序",eg="@int16")
    private java.lang.Integer sort;

    @NotNull
    @ApiProperty(name="group",desc="分组,系统:system;自定义:custom",required = true)
    private java.lang.String group;

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
    public java.lang.String getDesc() {
        return desc;
    }

    public void setDesc(java.lang.String desc) {
        this.desc = desc;
    }
    public java.lang.Integer getSort() {
        return sort;
    }

    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }
    public java.lang.String getGroup() {
        return group;
    }

    public void setGroup(java.lang.String group) {
        this.group = group;
    }
}
