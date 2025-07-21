package io.tiklab.kanass.project.appraised.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 事项与评审关联表
 */
@Entity
@Table(name = "pmc_work_appraised_history")
public class AppraisedHistoryEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 事项评审id
    @Column(name = "work_appraised_id",length = 12,notNull = true)
    private String workAppraisedId;

    // 评审状态  0未评审 1通过 2未通过 3建议
    @Column(name = "work_item_appraised_state",length = 12)
    private String workItemAppraisedState;

    // 事项评审id
    @Column(name = "creater",length = 12,notNull = true)
    private String creater;

    // 评审建议
    @Column(name="advice",length = 64)
    private String advice;

    // 创建日期
    @Column(name = "create_time")
    private Date createTime;

    // 创建日期
    @Column(name = "update_time")
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkAppraisedId() {
        return workAppraisedId;
    }

    public void setWorkAppraisedId(String workAppraisedId) {
        this.workAppraisedId = workAppraisedId;
    }

    public String getWorkItemAppraisedState() {
        return workItemAppraisedState;
    }

    public void setWorkItemAppraisedState(String workItemAppraisedState) {
        this.workItemAppraisedState = workItemAppraisedState;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String userId) {
        this.creater = userId;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
