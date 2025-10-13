package io.tiklab.kanass.project.appraised.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

//@Entity
//@Table(name = "pmc_appraised")
public class AppraisedEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 评审名称
    @Column(name = "name",length = 64,notNull = true)
    private String name;

    // 0未开始 1进行中 2已完成
    @Column(name="appraised_state",length = 12,notNull = true)
    private String appraisedState;

    @Column(name="project_id",length = 12,notNull = true)
    private String projectId;

    @Column(name = "description",length = 64)
    private String description;

    // 创建人
    @Column(name = "builder",length = 12)
    private String builder;

    // 负责人
    @Column(name = "master",length = 12)
    private String master;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "color")
    private int color;

    @Column(name = "appraised_type_id",length = 12)
    private String appraisedTypeId;

    @Column(name = "stage_id",length = 12)
    private String stageId;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "update_time")
    private Timestamp updateTime;

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

    public String getAppraisedState() {
        return appraisedState;
    }

    public void setAppraisedState(String appraisedState) {
        this.appraisedState = appraisedState;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getAppraisedTypeId() {
        return appraisedTypeId;
    }

    public void setAppraisedTypeId(String appraisedTypeId) {
        this.appraisedTypeId = appraisedTypeId;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
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
