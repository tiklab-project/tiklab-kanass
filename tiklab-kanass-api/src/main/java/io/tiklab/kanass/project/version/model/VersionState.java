package io.tiklab.kanass.project.version.model;

import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

/**
 * 迭代状态模板
 */
@ApiModel
@Join
@Mapper
public class VersionState extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="name",desc="迭代状态名称")
    private String name;

    @ApiProperty(name="description",desc="迭代描述")
    private String description;

    @ApiProperty(name="sort",desc="迭代状态排序")
    private Integer sort;

    @ApiProperty(name="grouper",desc="迭代状态分组")
    private String grouper;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
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

    public String getGrouper() {
        return grouper;
    }

    public void setGrouper(String grouper) {
        this.grouper = grouper;
    }

}
