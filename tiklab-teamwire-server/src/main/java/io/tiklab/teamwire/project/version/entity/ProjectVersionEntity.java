package io.tiklab.teamwire.project.version.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * 项目版本实例
 */
@Entity
@Table(name="pmc_version")
public class ProjectVersionEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 版本名称
    @Column(name = "name",length = 64,notNull = true)
    private String name;

    @Column(name = "master",length = 32)
    private String master;
    // 开始时间
    @Column(name = "start_time")
    private Date startTime;

    // 发布时间
    @Column(name = "publish_date")
    private Date publishDate;

    @Column(name = "rela_publish_date")
    private Date relaPublishDate;

    // 项目id
    @Column(name = "project_id", notNull = true)
    private String projectId;

    // 项目版本状态
    @Column(name="version_state",length = 32,notNull = true)
    private String versionState;

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



    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getVersionState() {
        return versionState;
    }

    public void setVersionState(String versionState) {
        this.versionState = versionState;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getRelaPublishDate() {
        return relaPublishDate;
    }

    public void setRelaPublishDate(Date relaPublishDate) {
        this.relaPublishDate = relaPublishDate;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }
}
