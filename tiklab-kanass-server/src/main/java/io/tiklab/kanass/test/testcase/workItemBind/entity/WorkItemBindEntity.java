package io.tiklab.kanass.test.testcase.workItemBind.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 绑定的缺陷 实体
 */
@Entity
@Table(name="test_workitem_bind")
public class WorkItemBindEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 绑定的缺陷
    @Column(name = "work_item_id")
    private String workItemId;

    // 用例
    @Column(name = "case_id")
    private String caseId;

    @Column(name = "project_id")
    private String projectId;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "test_plan_id")
    private String testPlanId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
    }
}
