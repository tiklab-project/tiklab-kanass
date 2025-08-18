package io.tiklab.kanass.product.plan.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 收藏的产品计划查找条件模型
 */
@ApiModel
public class ProductPlanFocusQuery {

        @ApiProperty(name ="masterId",desc = "所属负责人，精确匹配")
        private String masterId;

        @ApiProperty(name ="productId",desc = "所属产品，精确匹配")
        private String productId;

        @ApiProperty(name ="productPlanId",desc = "所属产品计划，精确匹配")
        private String productPlanId;
        @ApiProperty(name ="orderParams",desc = "排序参数")
        private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

        @ApiProperty(name ="pageParam",desc = "分页参数")
        private Page pageParam = new Page();

        public List<Order> getOrderParams() {
            return orderParams;
        }

        public void setOrderParams(List<Order> orderParams) {
            this.orderParams = orderParams;
        }

        public Page getPageParam() {
            return pageParam;
        }

        public void setPageParam(Page pageParam) {
            this.pageParam = pageParam;
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

    public String getProductPlanId() {
        return productPlanId;
    }

    public void setProductPlanId(String productPlanId) {
        this.productPlanId = productPlanId;
    }
}