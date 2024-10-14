package io.tiklab.kanass.project.plan.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 计划事项关联关系实例
 */
@Entity
@Table(name="pmc_plan_work_item")
public class PlanWorkItemEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    /**
     * 事项id
     */
    @Column(name = "work_item_id",length = 32,notNull = true)
    private String workItemId;

    /**
     * 计划id
     */
    @Column(name = "plan_id",length = 32,notNull = true)
    private String planId;

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

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }
}
