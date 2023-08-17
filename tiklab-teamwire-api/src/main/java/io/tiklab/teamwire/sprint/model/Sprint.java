package io.tiklab.teamwire.sprint.model;

import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.user.user.model.User;

import java.sql.Date;

/**
 * 迭代模板
 */
@ApiModel
@Join
@Mapper
public class Sprint extends BaseModel {

    @ApiProperty(name="id",desc="迭代ID")
    private java.lang.String id;


    @ApiProperty(name="sprintName",desc="迭代名称",eg="@text32")
    private java.lang.String sprintName;

    @ApiProperty(name="desc",desc="迭代描述",eg="@text32")
    private java.lang.String desc;

    @ApiProperty(name="master",desc="负责人")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinQuery(key = "id")
    private User master;

    @ApiProperty(name="project",desc="所属项目",eg="@selectOne")
    @Mappings({
            @Mapping(source = "project.id",target = "projectId")
    })
    @JoinQuery(key = "id")
    private Project project;

    @ApiProperty(name="sprintState",desc="迭代状态")
    @Mappings({
            @Mapping(source = "sprintState.id",target = "sprintStateId")
    })
    @JoinQuery(key = "id")
    private SprintState sprintState;

    @ApiProperty(name="startTime",desc="开始时间")
    private Date startTime;

    @ApiProperty(name="endTime",desc="结束时间")
    private Date endTime;

    @ApiProperty(name="work_number",desc="项目数量")
    private Integer workNumber;

    @ApiProperty(name="quantityNumber",desc="完成数量")
    private Integer quantityNumber;

    public java.lang.String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public java.lang.String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }
    public java.lang.String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public SprintState getSprintState() {
        return sprintState;
    }

    public void setSprintState(SprintState sprintState) {
        this.sprintState = sprintState;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    public Integer getQuantityNumber() {
        return quantityNumber;
    }

    public void setQuantityNumber(Integer quantityNumber) {
        this.quantityNumber = quantityNumber;
    }

    @Override
    public String toString() {
        return "Sprint{" +
                "id='" + id + '\'' +
                ", sprintName='" + sprintName + '\'' +
                ", desc='" + desc + '\'' +
                ", master=" + master +
                ", project=" + project +
                ", sprintState=" + sprintState +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public Integer getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(Integer workNumber) {
        this.workNumber = workNumber;
    }
}
