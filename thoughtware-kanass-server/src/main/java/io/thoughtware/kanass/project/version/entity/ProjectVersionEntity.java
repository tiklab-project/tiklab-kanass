package io.thoughtware.kanass.project.version.entity;


import io.thoughtware.dal.jpa.annotation.Column;
import io.thoughtware.dal.jpa.annotation.GeneratorValue;
import io.thoughtware.dal.jpa.annotation.Id;
import io.thoughtware.dal.jpa.annotation.Table;import io.thoughtware.dal.jpa.annotation.Entity;

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

    @Column(name = "master",length = 12)
    private String master;

    @Column(name = "builder",length = 12)
    private String builder;
    // 开始时间
    @Column(name = "start_time")
    private String startTime;

    // 发布时间
    @Column(name = "publish_time")
    private String publishTime;

    @Column(name = "rela_start_time")
    private String relaStartTime;

    @Column(name = "rela_publish_time")
    private String relaPublishTime;

    // 项目id
    @Column(name = "project_id", notNull = true)
    private String projectId;

    // 项目版本状态
    @Column(name="version_state",length = 12,notNull = true)
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getRelaPublishTime() {
        return relaPublishTime;
    }

    public void setRelaPublishTime(String relaPublishTime) {
        this.relaPublishTime = relaPublishTime;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getRelaStartTime() {
        return relaStartTime;
    }

    public void setRelaStartTime(String relaStartTime) {
        this.relaStartTime = relaStartTime;
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }
}
