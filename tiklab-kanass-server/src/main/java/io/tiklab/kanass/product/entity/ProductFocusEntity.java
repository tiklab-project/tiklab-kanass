package io.tiklab.kanass.product.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 产品收藏实例
 */
@Entity
@Table(name="pmc_product_focus")
public class ProductFocusEntity implements Serializable {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    // 产品id
    @Column(name = "product_id",length = 64)
    private String productId;

    // 收藏者id
    @Column(name = "master_id",length = 64)
    private String masterId;

    // 排序参数
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
