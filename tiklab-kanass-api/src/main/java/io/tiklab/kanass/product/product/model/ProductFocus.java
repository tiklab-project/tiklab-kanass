package io.tiklab.kanass.product.product.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.join.annotation.Join;

@ApiModel
@Join
@Mapper
public class ProductFocus extends BaseModel {
    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="productId",desc="产品id")
    private String productId;

    @ApiProperty(name="masterId",desc="产品负责人")
    private String masterId;

    @ApiProperty(name="sort",desc="产品排序")
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
