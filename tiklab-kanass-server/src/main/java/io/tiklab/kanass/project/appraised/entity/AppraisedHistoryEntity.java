package io.tiklab.kanass.project.appraised.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 事项与评审关联表
 */
@Entity
@Table(name = "pmc_appraised_item_history")
public class AppraisedHistoryEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 评审id
    @Column(name = "appraised_item_id",length = 12,notNull = true)
    private String appraisedItemId;

    // 评审状态  0未评审 1通过 2未通过 3建议
    @Column(name = "appraised_item_state",length = 12)
    private String appraisedItemState;

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

    public String getAppraisedItemId() {
        return appraisedItemId;
    }

    public void setAppraisedItemId(String appraisedItemId) {
        this.appraisedItemId = appraisedItemId;
    }

    public String getAppraisedItemState() {
        return appraisedItemState;
    }

    public void setAppraisedItemState(String appraisedItemState) {
        this.appraisedItemState = appraisedItemState;
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
