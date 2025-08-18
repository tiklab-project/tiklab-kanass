package io.tiklab.kanass.product.plan.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

/**
 * 产品计划查询模型
 */
@ApiModel
public class ProductPlanQuery {

    @ApiProperty(name ="productId",desc = "所属产品ID，精确匹配")
    private String productId;

    @ApiProperty(name ="master",desc = "所属负责人，精确匹配")
    private String master;

    @ApiProperty(name ="builderId",desc = "所属负责人，精确匹配")
    private String builderId;

    @ApiProperty(name ="productPlanName",desc = "产品计划名称，模糊匹配")
    private String productPlanName;

    @ApiProperty(name ="followersId",desc = "产品计划关注者id")
    private String followersId;

    @ApiProperty(name ="productPlanStateId",desc = "产品计划状态id，模糊匹配")
    private String productPlanStateId;

    @ApiProperty(name ="productPlanStateIds",desc = "产品计划状态id，模糊匹配")
    private String[] productPlanStateIds;

    @ApiProperty(name ="currentProductPlanId",desc = "产品计划状态id，模糊匹配")
    private String currentProductPlanId;

    @ApiProperty(name ="notProductPlanStateId",desc = "产品计划状态id，模糊匹配")
    private String notProductPlanStateId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("productPlanName").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

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

    public String getProductPlanName() {
        return productPlanName;
    }

    public void setProductPlanName(String productPlanName) {
        this.productPlanName = productPlanName;
    }

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

    public String getProductPlanStateId() {
        return productPlanStateId;
    }

    public void setProductPlanStateId(String productPlanStateId) {
        this.productPlanStateId = productPlanStateId;
    }

    public String getCurrentProductPlanId() {
        return currentProductPlanId;
    }

    public void setCurrentProductPlanId(String currentProductPlanId) {
        this.currentProductPlanId = currentProductPlanId;
    }

    public String getNotProductPlanStateId() {
        return notProductPlanStateId;
    }

    public void setNotProductPlanStateId(String notProductPlanStateId) {
        this.notProductPlanStateId = notProductPlanStateId;
    }

    public String[] getProductPlanStateIds() {
        return productPlanStateIds;
    }

    public void setProductPlanStateIds(String[] productPlanStateIds) {
        this.productPlanStateIds = productPlanStateIds;
    }

    public String getBuilderId() {
        return builderId;
    }

    public void setBuilderId(String builderId) {
        this.builderId = builderId;
    }

    public String getFollowersId() {
        return followersId;
    }

    public void setFollowersId(String followersId) {
        this.followersId = followersId;
    }
}
