package io.tiklab.kanass.instance.entity;


import java.io.Serializable;
import java.sql.Timestamp;

import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.Entity;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;

/**
 * 测试报告
 */
@Entity
@Table(name="test_test_report")
public class TestReportEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    // 报告名称
    @Column(name = "name")
    private String name;

    // 所属仓库
    @Column(name = "project_id")
    private String projectId;

    // 所属计划
    @Column(name = "test_plan_id")
    private String testPlanId;

    // 起始时间
    @Column(name = "start_time")
    private Timestamp startTime;

    // 结束时间
    @Column(name = "end_time")
    private Timestamp endTime;

    // 创建人
    @Column(name = "create_user")
    private String createUser;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    // 描述
    @Column(name = "description")
    private String desc;

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


    public String getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(String testPlanId) {
        this.testPlanId = testPlanId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
