package io.tiklab.kanass.project.workPrivilege.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;

/**
 * 优先级模型
 */
@ApiModel
@Mapper
public class ProjectVrole extends BaseModel {

    @ApiProperty(name="id",desc="事项功能")
    private String id;

    @ApiProperty(name="projectId",desc="项目id")
    private String projectId;

    @ApiProperty(name="vroleId",desc="虚拟角色id")
    private String vroleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getVroleId() {
        return vroleId;
    }

    public void setVroleId(String vroleId) {
        this.vroleId = vroleId;
    }
}
