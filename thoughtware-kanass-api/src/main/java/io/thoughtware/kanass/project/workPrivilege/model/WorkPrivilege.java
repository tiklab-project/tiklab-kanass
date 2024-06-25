package io.thoughtware.kanass.project.workPrivilege.model;

import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.toolkit.beans.annotation.Mapper;

import java.util.List;

/**
 * 优先级模型
 */
@ApiModel
@Mapper
public class WorkPrivilege extends BaseModel {

    @ApiProperty(name="id",desc="事项权限")
    private String id;

    @ApiProperty(name="name",desc="事项权限名字")
    private String name;

    @ApiProperty(name="grouper",desc="事项权限grouper")
    private String grouper;

    @ApiProperty(name="scope",desc="范围")
    private String scope;

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

    public String getGrouper() {
        return grouper;
    }

    public void setGrouper(String grouper) {
        this.grouper = grouper;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
