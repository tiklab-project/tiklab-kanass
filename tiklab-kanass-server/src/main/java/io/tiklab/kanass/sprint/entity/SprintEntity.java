package io.tiklab.kanass.sprint.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;
import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * 迭代实例
 */
@Entity
@Table(name="pmc_sprint")
public class SprintEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 迭代名称
    @Column(name = "sprint_name",length = 64,notNull = true)
    private String sprintName;

    // 迭代负责人
    @Column(name = "master",length = 12)
    private String master;

    // 创建人
    @Column(name = "builder",length = 12)
    private String builder;

    // 迭代描述
    @Column(name = "description",length = 64)
    private String desc;

    @Column(name = "color")
    private int color;

    // 迭代所属项目
    @Column(name = "project_id",length = 12,notNull = true)
    private String projectId;

    // 迭代状态id
    @Column(name = "sprint_state_id",length = 12)
    private String sprintStateId;

    // 迭代计划开始日期
    @Column(name = "start_time")
    private String startTime;

    // 迭代计划结束日期
    @Column(name = "end_time")
    private String endTime;

    @Column(name = "rela_start_time")
    private String relaStartTime;

    @Column(name = "rela_end_time")
    private String relaEndTime;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getSprintStateId() {
        return sprintStateId;
    }

    public void setSprintStateId(String sprintStateId) {
        this.sprintStateId = sprintStateId;
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

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
