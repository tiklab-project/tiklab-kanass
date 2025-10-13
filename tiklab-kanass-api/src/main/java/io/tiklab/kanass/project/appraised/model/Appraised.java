package io.tiklab.kanass.project.appraised.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.core.BaseModel;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.stage.model.Stage;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.user.user.model.User;

import java.sql.Timestamp;

/**
 * 项目评审模型
 */
@ApiModel
@Mapper(targetName = "io.tiklab.kanass.appraised.entity.AppraisedEntity")
@Join
public class Appraised extends BaseModel {
    @ApiProperty(name = "id", desc = "唯一ID")
    private String id;

    @ApiProperty(name = "name", desc = "评审名称")
    private String name;

    private int color;

    @ApiProperty(name = "project", desc = "所属项目")
    @Mappings({
            @Mapping(source = "project.id", target = "projectId")
    })
    @JoinField(key = "id")
    private Project project;

    @ApiProperty(name="master",desc="负责人")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinField(key = "id")
    private User master;

    @ApiProperty(name="builder",desc="创建人")
    @Mappings({
            @Mapping(source = "builder.id",target = "builder")
    })
    @JoinField(key = "id")
    private User builder;

    @ApiProperty(name = "startTime", desc = "开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String startTime;

    @ApiProperty(name = "endTime", desc = "结束日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

    // 0未开始 1进行中 2已完成
    @ApiProperty(name="status",desc="状态")
    private String appraisedState = "0";

    @ApiProperty(name="description",desc="描述")
    private String description;

    @ApiProperty(name="appraisedType",desc="评审类型")
    @Mappings({
            @Mapping(source = "appraisedType.id",target = "appraisedTypeId")
    })
    @JoinField(key = "id")
    private AppraisedType appraisedType;

    @ApiProperty(name = "stage", desc = "所属阶段")
    @Mappings({
            @Mapping(source = "stage.id", target = "stageId")
    })
    @JoinField(key = "id")
    private Stage stage;

    @ApiProperty(name = "createTime", desc = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @ApiProperty(name = "updateTime", desc = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;

    // 所有评审事项
    private Integer allAppraisedWorkItemNumber;
    // 通过的评审事项
    private Integer passAppraisedWorkItemNumber;
    // 未通过的评审事项
    private Integer unPassAppraisedWorkItemNumber;

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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
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

    public User getBuilder() {
        return builder;
    }

    public void setBuilder(User builder) {
        this.builder = builder;
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

    public String getAppraisedState() {
        return appraisedState;
    }

    public void setAppraisedState(String appraisedState) {
        this.appraisedState = appraisedState;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAllAppraisedWorkItemNumber() {
        return allAppraisedWorkItemNumber;
    }

    public void setAllAppraisedWorkItemNumber(Integer allAppraisedWorkItemNumber) {
        this.allAppraisedWorkItemNumber = allAppraisedWorkItemNumber;
    }

    public Integer getPassAppraisedWorkItemNumber() {
        return passAppraisedWorkItemNumber;
    }

    public void setPassAppraisedWorkItemNumber(Integer passAppraisedWorkItemNumber) {
        this.passAppraisedWorkItemNumber = passAppraisedWorkItemNumber;
    }

    public Integer getUnPassAppraisedWorkItemNumber() {
        return unPassAppraisedWorkItemNumber;
    }

    public void setUnPassAppraisedWorkItemNumber(Integer unPassAppraisedWorkItemNumber) {
        this.unPassAppraisedWorkItemNumber = unPassAppraisedWorkItemNumber;
    }

    public AppraisedType getAppraisedType() {
        return appraisedType;
    }

    public void setAppraisedType(AppraisedType appraisedType) {
        this.appraisedType = appraisedType;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
