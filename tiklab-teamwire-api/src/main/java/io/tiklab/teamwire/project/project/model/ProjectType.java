package io.tiklab.teamwire.project.project.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 项目类型模型
 */
@ApiModel
@Mapper
public class ProjectType extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="type",desc="项目类型名称",required = true)
    private java.lang.String type;

    @NotNull
    @ApiProperty(name="name",desc="项目类型名称",required = true)
    private java.lang.String name;

    @ApiProperty(name="desc",desc="描述")
    private java.lang.String desc;


    @ApiProperty(name="iconUrl",desc="项目类型icon地址",required = true)
    private java.lang.String iconUrl;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
