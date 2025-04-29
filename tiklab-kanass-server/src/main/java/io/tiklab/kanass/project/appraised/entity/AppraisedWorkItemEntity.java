package io.tiklab.kanass.project.appraised.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 事项与评审关联表
 */
@Entity
@Table(name = "pmc_work_appraised")
public class AppraisedWorkItemEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 事项id
    @Column(name = "work_item_id",length = 12,notNull = true)
    private String workItemId;

    //评审id
    @Column(name="appraised_id",length = 12,notNull = true)
    private String appraisedId;

    // 评审状态  0未评审 1通过 2未通过 3建议
    @Column(name = "work_item_appraised_state",length = 12)
    private String workItemAppraisedState;

    // 评审建议
    @Column(name="advice",length = 64)
    private String advice;

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

    public String getAppraisedId() {
        return appraisedId;
    }

    public void setAppraisedId(String appraisedId) {
        this.appraisedId = appraisedId;
    }

    public String getWorkItemAppraisedState() {
        return workItemAppraisedState;
    }

    public void setWorkItemAppraisedState(String workItemAppraisedState) {
        this.workItemAppraisedState = workItemAppraisedState;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}
