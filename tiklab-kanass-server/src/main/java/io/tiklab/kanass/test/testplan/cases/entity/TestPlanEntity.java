package io.tiklab.kanass.test.testplan.cases.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 测试计划 实体
 */
@Entity @Table(name="test_test_plan")
public class TestPlanEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    //测试计划名称
    @Column(name = "name")
    private String name;

    // 开始时间
    @Column(name = "start_time")
    private Date startTime;

    // 结束时间
    @Column(name = "end_time")
    private Date endTime;

    // 状态
    @Column(name = "state")
    private Integer state;

    // 负责人
    @Column(name = "principals")
    private String principals;

    // 所属仓库
    @Column(name = "project_id")
    private String projectId;

    // 描述
    @Column(name = "description")
    private String desc;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    // 更新时间
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPrincipals() {
        return principals;
    }

    public void setPrincipals(String principals) {
        this.principals = principals;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
