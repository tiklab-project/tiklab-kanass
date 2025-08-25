package io.tiklab.kanass.project.appraised.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 事项与评审关联表
 */
@Entity
@Table(name = "pmc_appraised_item")
public class AppraisedItemEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 评审对象id
    @Column(name = "target_id",length = 12,notNull = true)
    private String targetId;

    //评审id
    @Column(name="appraised_id",length = 12,notNull = true)
    private String appraisedId;

    // 评审状态  0未评审 1通过 2未通过
    @Column(name = "appraised_item_state",length = 12)
    private String appraisedItemState;

    // 评审类型id
    @Column(name = "appraised_type_id",length = 12)
    private String appraisedTypeId;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "update_time")
    private Timestamp updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppraisedId() {
        return appraisedId;
    }

    public void setAppraisedId(String appraisedId) {
        this.appraisedId = appraisedId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getAppraisedItemState() {
        return appraisedItemState;
    }

    public void setAppraisedItemState(String appraisedItemState) {
        this.appraisedItemState = appraisedItemState;
    }

    public String getAppraisedTypeId() {
        return appraisedTypeId;
    }

    public void setAppraisedTypeId(String appraisedTypeId) {
        this.appraisedTypeId = appraisedTypeId;
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
}
