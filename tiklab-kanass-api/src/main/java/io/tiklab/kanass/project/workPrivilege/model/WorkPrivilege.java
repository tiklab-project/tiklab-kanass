package io.tiklab.kanass.project.workPrivilege.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;

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

    private String workTypeId;
    private String projectId;
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

    public String getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(String workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
