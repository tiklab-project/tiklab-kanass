package io.tiklab.kanass.project.appraised.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.join.annotation.Join;

@ApiModel
@Join
@Mapper
public class AppraisedType {

    @ApiProperty(name="id",desc="id")
    private String id;

    // 评审类型名称
    @ApiProperty(name="name",desc="评审类型名称")
    private String name;

    // 评审类型描述
    @ApiProperty(name="description",desc="评审类型描述")
    private String description;

    // 评审类型排序
    @ApiProperty(name="sort",desc="评审类型排序")
    private Integer sort;

    // 分组
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
