package io.thoughtware.kanass.project.stage.entity;

import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 阶段事项关联关系实例
 */
@Entity
@Table(name="pmc_stage_work_item")
public class StageWorkItemEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 事项id
    @Column(name = "work_item_id",length = 32,notNull = true)
    private String workItemId;

    // 阶段id
    @Column(name = "stage_id",length = 32,notNull = true)
    private String stageId;

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

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
}
