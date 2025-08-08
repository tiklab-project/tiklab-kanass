package io.tiklab.kanass.instance.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 公共实例 实体
 */
@Entity
@Table(name="test_instance")
public class InstanceEntity implements Serializable {

    @Id
   // @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    //
    @Column(name = "name")
    private String name;

    // 类型
    @Column(name = "type")
    private String type;

    @Column(name = "project_id")
    private String projectId;

    @Column(name="case_id")
    private String caseId;

    @Column(name="test_plan_id")
    private String testPlanId;

    @Column(name = "create_user")
    private String createUser;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    // 内容：json字符串概要
    @Column(name = "content")
    private String content;

    @Column(name = "execute_number")
    private int executeNumber;

    @Column(name = "status")
    private String status;

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getExecuteNumber() {
        return executeNumber;
    }

    public void setExecuteNumber(int executeNumber) {
        this.executeNumber = executeNumber;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(String testPlanId) {
        this.testPlanId = testPlanId;
    }
}
