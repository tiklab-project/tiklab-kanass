package io.tiklab.kanass.project.worklog.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 工作日志实例
 */
@Entity
@Table(name="pmc_work_log")
public class WorkLogEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 事项id
    @Column(name = "work_item_id",length = 32,notNull = true)
    private String workItemId;

    // 所属项目id
    @Column(name = "project_id",length = 32,notNull = true)
    private String projectId;

    // 事项工作人员id
    @Column(name = "worker",length = 32,notNull = true)
    private String worker;

    // 添加日志时间
    @Column(name = "work_date",notNull = true)
    private Timestamp workDate;

    // 用时
    @Column(name = "takeup_time",notNull = true)
    private Integer takeupTime;

    // 工作内容
    @Column(name = "work_content",length = 256,notNull = true)
    private String workContent;

    // 创建时间
    @Column(name = "creat_time")
    private Timestamp creatTime;

    // 更新时间
    @Column(name = "update_time")
    private Timestamp updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public Timestamp getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Timestamp workDate) {
        this.workDate = workDate;
    }

    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getTakeupTime() {
        return takeupTime;
    }

    public void setTakeupTime(Integer takeupTime) {
        this.takeupTime = takeupTime;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }



    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
