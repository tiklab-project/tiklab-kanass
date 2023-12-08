package io.thoughtware.kanass.project.milestone.entity;

import io.thoughtware.dal.jpa.annotation.*;

import java.sql.Date;

/**
 * 里程碑实例
 */
@Entity
@Table(name="pmc_milestone")
public class MilestoneEntity {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    //里程碑所属项目
    @Column(name = "project_id",length = 32,notNull = true)
    private String projectId;

    //标题
    @Column(name = "name",length = 64,notNull = true)
    private String name;

    //里程碑日期
    @Column(name = "milestone_time")
    private Date milestoneTime;

    //负责人ID
    @Column(name = "master",length = 32)
    private String master;

    //里程碑状态ID
    @Column(name = "milestone_status",length = 32,notNull = true)
    private String milestoneStatus;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getMilestoneTime() {
        return milestoneTime;
    }

    public void setMilestoneTime(Date milestoneTime) {
        this.milestoneTime = milestoneTime;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getMilestoneStatus() {
        return milestoneStatus;
    }

    public void setMilestoneStatus(String milestoneStatus) {
        this.milestoneStatus = milestoneStatus;
    }
}
