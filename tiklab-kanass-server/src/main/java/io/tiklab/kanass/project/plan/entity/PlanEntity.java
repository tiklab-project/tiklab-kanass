package io.tiklab.kanass.project.plan.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.sql.Date;

/**
 * 计划实例
 */
@Entity
@Table(name="pmc_plan")
public class PlanEntity {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;
    
    //计划名字
    @Column(name = "plan_name",length = 64,notNull = true)
    private String planName;

    //计划类型 id
    @Column(name = "project_id",length = 32,notNull = true)
    private String  projectId;

    //计划状态 1 未开始  2 进行中 3 已完成
    @Column(name = "plan_state",length = 32)
    private String  planState;

    // 父类id
    @Column(name = "parent_id",length = 32,notNull = true)
    private String  parentId;

    //开始时间
    @Column(name = "start_time")
    private Date startTime;

    //结束时间
    @Column(name = "end_time")
    private Date endTime;

    //计划负责人
    @Column(name = "master",length = 32)
    private String master;

    //计划描述
    @Column(name = "description",length = 64)
    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public String getPlanState() {
        return planState;
    }

    public void setPlanState(String planState) {
        this.planState = planState;
    }
}
