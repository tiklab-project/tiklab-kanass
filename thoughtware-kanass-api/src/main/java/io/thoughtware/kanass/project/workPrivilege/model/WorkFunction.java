package io.thoughtware.kanass.project.workPrivilege.model;

import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.toolkit.beans.annotation.Mapper;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 优先级模型
 */
@ApiModel
@Mapper
public class WorkFunction extends BaseModel {

    @ApiProperty(name="id",desc="事项功能")
    private String id;

    @ApiProperty(name="name",desc="事项功能名字")
    private String name;

    @ApiProperty(name="code",desc="事项功能code")
    private String code;

    @ApiProperty(name="parentId",desc="上级id")
    private String parentId;

    @ApiProperty(name="children",desc="上级id")
    private List<WorkFunction> children;

    @ApiProperty(name="sort",desc="事项功能排序")
    private String sort;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<WorkFunction> getChildren() {
        return children;
    }

    public void setChildren(List<WorkFunction> children) {
        this.children = children;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

}
