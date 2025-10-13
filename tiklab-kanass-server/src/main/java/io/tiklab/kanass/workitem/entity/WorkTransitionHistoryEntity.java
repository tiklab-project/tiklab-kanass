package io.tiklab.kanass.workitem.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.util.Date;

/**
 * 事项流转历史
 */
@Entity
@Table(name="pmc_work_transition_history")
public class WorkTransitionHistoryEntity {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 评审id
    @Column(name = "work_item_id",length = 12,notNull = true)
    private String workItemId;

    // 旧节点id
    @Column(name = "old_node_id",length = 12,notNull = true)
    private String oldNodeId;

    // 新节点id
    @Column(name = "new_node_id",length = 12,notNull = true)
    private String newNodeId;

    // 创建人id
    @Column(name = "creater",length = 12,notNull = true)
    private String creater;

    // 流转描述
    @Column(name="transition_desc",length = 64)
    private String transitionDesc;

    // 创建日期
    @Column(name = "create_time")
    private Date createTime;

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

    public String getOldNodeId() {
        return oldNodeId;
    }

    public void setOldNodeId(String oldNodeId) {
        this.oldNodeId = oldNodeId;
    }

    public String getNewNodeId() {
        return newNodeId;
    }

    public void setNewNodeId(String newNodeId) {
        this.newNodeId = newNodeId;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getTransitionDesc() {
        return transitionDesc;
    }

    public void setTransitionDesc(String transitionDesc) {
        this.transitionDesc = transitionDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
