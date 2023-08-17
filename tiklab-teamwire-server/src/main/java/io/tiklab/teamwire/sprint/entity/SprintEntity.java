package io.tiklab.teamwire.sprint.entity;


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
    @Column(name = "master",length = 32)
    private String master;

    // 迭代描述
    @Column(name = "description",length = 64)
    private String desc;

    // 迭代所属项目
    @Column(name = "project_id",length = 32,notNull = true)
    private String projectId;

    // 迭代状态id
    @Column(name = "sprint_state_id",length = 32)
    private String sprintStateId;

    // 迭代开始日期
    @Column(name = "start_time")
    private Date startTime;

    // 迭代结束日期
    @Column(name = "end_time")
    private Date endTime;

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
}
