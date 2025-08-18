package io.tiklab.kanass.product.plan.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 迭代实例
 */
@Entity
@Table(name="pmc_product_plan")
public class ProductPlanEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 迭代名称
    @Column(name = "product_plan_name",length = 64,notNull = true)
    private String productPlanName;

    // 迭代负责人
    @Column(name = "master",length = 12)
    private String master;

    // 创建人
    @Column(name = "builder",length = 12)
    private String builder;

    // 迭代描述
    @Column(name = "description",length = 64)
    private String desc;

    @Column(name = "color")
    private int color;

    // 迭代所属项目
    @Column(name = "product_id",length = 12,notNull = true)
    private String productId;

    // 迭代状态id
    @Column(name = "product_plan_state_id",length = 12)
    private String productPlanStateId;

    // 迭代计划开始日期
    @Column(name = "start_time")
    private String startTime;

    // 迭代计划结束日期
    @Column(name = "end_time")
    private String endTime;

    @Column(name = "rela_start_time")
    private String relaStartTime;

    @Column(name = "rela_end_time")
    private String relaEndTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductPlanName() {
        return productPlanName;
    }

    public void setProductPlanName(String productPlanName) {
        this.productPlanName = productPlanName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getProductPlanStateId() {
        return productPlanStateId;
    }

    public void setProductPlanStateId(String productPlanStateId) {
        this.productPlanStateId = productPlanStateId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRelaStartTime() {
        return relaStartTime;
    }

    public void setRelaStartTime(String relaStartTime) {
        this.relaStartTime = relaStartTime;
    }

    public String getRelaEndTime() {
        return relaEndTime;
    }

    public void setRelaEndTime(String relaEndTime) {
        this.relaEndTime = relaEndTime;
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
