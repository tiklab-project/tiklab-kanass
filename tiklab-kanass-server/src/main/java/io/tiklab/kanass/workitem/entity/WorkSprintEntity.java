package io.tiklab.kanass.workitem.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 事项类型实例
 */
@Entity
@Table(name="pmc_work_sprint")
public class WorkSprintEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 事项类型名称
    @Column(name = "work_item_id",length = 16,notNull = true)
    private String workItemId;

    // 事项类型描述
    @Column(name = "sprint_id",length = 12,notNull = true)
    private String sprintId;

    // 迭代完成后 未完成的事项迁移的目标迭代id
    @Column(name = "target_sprint_id",length = 12)
    private String targetSprintId;

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

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }

    public String getTargetSprintId() {
        return targetSprintId;
    }

    public void setTargetSprintId(String targetSprintId) {
        this.targetSprintId = targetSprintId;
    }
}
