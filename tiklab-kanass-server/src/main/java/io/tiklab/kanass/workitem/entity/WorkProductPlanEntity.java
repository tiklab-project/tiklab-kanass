package io.tiklab.kanass.workitem.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 事项类型实例
 */
@Entity
@Table(name="pmc_work_product_plan")
public class WorkProductPlanEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 事项类型名称
    @Column(name = "work_item_id",length = 16,notNull = true)
    private String workItemId;

    // 事项类型描述
    @Column(name = "product_plan_id",length = 12,notNull = true)
    private String productPlanId;

    // 迭代完成后 未完成的事项迁移的目标迭代id
    @Column(name = "target_product_plan_id",length = 12)
    private String targetProductPlanIdId;

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

    public String getProductPlanId() {
        return productPlanId;
    }

    public void setProductPlanId(String productPlanId) {
        this.productPlanId = productPlanId;
    }

    public String getTargetProductPlanIdId() {
        return targetProductPlanIdId;
    }

    public void setTargetProductPlanIdId(String targetProductPlanIdId) {
        this.targetProductPlanIdId = targetProductPlanIdId;
    }
}
