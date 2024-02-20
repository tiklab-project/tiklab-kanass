package io.thoughtware.kanass.sprint.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.kanass.project.project.model.Project;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;
import io.thoughtware.user.user.model.User;

import java.sql.Date;

/**
 * 迭代模板
 */
@ApiModel
@Join
@Mapper
public class Sprint extends BaseModel {

    @ApiProperty(name="id",desc="迭代ID")
    private String id;

    @ApiProperty(name="newSprintId",desc="迭代ID")
    private String newSprintId;


    @ApiProperty(name="sprintName",desc="迭代名称",eg="@text32")
    private String sprintName;

    @ApiProperty(name="desc",desc="迭代描述",eg="@text32")
    private String desc;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String startTime;

    @ApiProperty(name="endTime",desc="结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

    @ApiProperty(name="relaStartTime",desc="实际开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String relaStartTime;

    @ApiProperty(name="relaEndTime",desc="实际结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String relaEndTime;

    @ApiProperty(name="work_number",desc="迭代的事项数量")
    private Integer workNumber;

    @ApiProperty(name="work_done_number",desc="迭代的已完成事项数量")
    private Integer workDoneNumber;

    @ApiProperty(name="work_progress_number",desc="迭代的未完成事项数量")
    private Integer workProgressNumber;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRelaStartTime() {
        return relaStartTime;
    }

    public void setRelaStartTime(String relaStartTime) {
        this.relaStartTime = relaStartTime;
    }

    public String getRelaEndTime() {
        return relaEndTime;
    }

    public void setRelaEndTime(String relaEndTime) {
        this.relaEndTime = relaEndTime;
    }

    public Integer getQuantityNumber() {
        return quantityNumber;
    }

    public void setQuantityNumber(Integer quantityNumber) {
        this.quantityNumber = quantityNumber;
    }


    public Integer getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(Integer workNumber) {
        this.workNumber = workNumber;
    }

    public String getNewSprintId() {
        return newSprintId;
    }

    public void setNewSprintId(String newSprintId) {
        this.newSprintId = newSprintId;
    }

    public Integer getWorkDoneNumber() {
        return workDoneNumber;
    }

    public void setWorkDoneNumber(Integer workDoneNumber) {
        this.workDoneNumber = workDoneNumber;
    }

    public Integer getWorkProgressNumber() {
        return workProgressNumber;
    }

    public void setWorkProgressNumber(Integer workProgressNumber) {
        this.workProgressNumber = workProgressNumber;
    }
}
