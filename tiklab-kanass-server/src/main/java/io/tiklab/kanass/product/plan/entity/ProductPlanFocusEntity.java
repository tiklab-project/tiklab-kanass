package io.tiklab.kanass.product.plan.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 迭代收藏实例
 */
@Entity
@Table(name="pmc_product_plan_focus")
public class ProductPlanFocusEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 迭代所属项目id
    @Column(name = "product_id",length = 64)
    private String productId;

    // 迭代id
    @Column(name = "product_plan_id",length = 64)
    private String productPlanId;

    // 迭代负责人
    @Column(name = "master_id",length = 64)
    private String masterId;

    // 迭代排序
    @Column(name = "sort")
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductPlanId() {
        return productPlanId;
    }

    public void setProductPlanId(String productPlanId) {
        this.productPlanId = productPlanId;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
