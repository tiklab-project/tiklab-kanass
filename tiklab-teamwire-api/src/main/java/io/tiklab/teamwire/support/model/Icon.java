package io.tiklab.teamwire.support.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 图标模型
 */
@ApiModel
@Mapper
public class Icon extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="iconName",desc="图标名称",required = true)
    private java.lang.String iconName;

    @NotNull
    @ApiProperty(name="iconUrl",desc="图标地址",required = true)
    private java.lang.String iconUrl;

    @ApiProperty(name="iconType",desc="图标类型")
    private java.lang.String iconType;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getIconName() {
        return iconName;
    }

    public void setIconName(java.lang.String iconName) {
        this.iconName = iconName;
    }
    public java.lang.String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(java.lang.String iconUrl) {
        this.iconUrl = iconUrl;
    }
    public java.lang.String getIconType() {
        return iconType;
    }

    public void setIconType(java.lang.String iconType) {
        this.iconType = iconType;
    }
}
