package io.tiklab.teamwire.project.epic.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 史诗实例
 */
@Entity
@Table(name="pmc_epic")
public class EpicEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    //史诗名字
    @Column(name = "epic_name",length = 64)
    private String epicName;

    //父级id
    @Column(name = "parent_id",length = 64)
    private String parentId;

    //状态
    @Column(name = "status",length = 64)
    private String status;

    //史诗进度
    @Column(name = "progress",length = 64)
    private Integer progress;

    //负责人
    @Column(name = "master",length = 32)
    private String master;

    //史诗描述
    @Column(name = "description",length = 64)
    private String desc;

    //史诗所属项目
    @Column(name = "project_id",length = 32)
    private String projectId;

    //开始时间
    @Column(name = "start_time")
    private Date startTime;

    //结束时间
    @Column(name = "end_time")
    private Date endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEpicName() {
        return epicName;
    }

    public void setEpicName(String epicName) {
        this.epicName = epicName;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
