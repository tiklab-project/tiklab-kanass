package io.tiklab.kanass.product.plan.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;

/**
 * 收藏的产品计划模型
 */
@ApiModel
@Mapper
public class ProductPlanFocus extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="productPlanId",desc="收藏的产品计划id")
    private String productPlanId;

    @ApiProperty(name="masterId",desc="产品计划创建人")
    private String masterId;

    @ApiProperty(name="productId",desc="产品计划所属产品")
    private String productId;

    @ApiProperty(name="sort",desc="产品计划排序")
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
