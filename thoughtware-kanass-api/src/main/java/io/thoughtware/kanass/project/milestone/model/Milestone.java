package io.thoughtware.kanass.project.milestone.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.beans.annotation.Mapping;
import io.thoughtware.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.join.annotation.Join;
import io.thoughtware.join.annotation.JoinQuery;
import io.thoughtware.kanass.project.project.model.Project;
import io.thoughtware.user.user.model.User;

import java.sql.Date;

/**
 * 里程碑模型
 */
@ApiModel
@Join
@Mapper
public class Milestone extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="project",desc="所属项目")
    @Mappings({
            @Mapping(source = "project.id",target = "projectId")
    })
    @JoinQuery(key = "id")
    private Project project;

    @ApiProperty(name="name",desc="里程碑名称")
    private java.lang.String name;

    @ApiProperty(name="milestoneTime",desc="里程碑设置时间")
    private java.sql.Date milestoneTime;

    @ApiProperty(name="master",desc="里程碑负责人")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinQuery(key = "id")
    private User master;

    @ApiProperty(name="milestoneStatus",desc="里程碑状态")
    private java.lang.String milestoneStatus;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public Date getMilestoneTime() {
        return milestoneTime;
    }

    public void setMilestoneTime(Date milestoneTime) {
        this.milestoneTime = milestoneTime;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public String getMilestoneStatus() {
        return milestoneStatus;
    }

    public void setMilestoneStatus(String milestoneStatus) {
        this.milestoneStatus = milestoneStatus;
    }
}
