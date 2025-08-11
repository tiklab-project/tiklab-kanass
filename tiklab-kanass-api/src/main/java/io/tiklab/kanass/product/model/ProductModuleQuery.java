package io.tiklab.kanass.product.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

public class ProductModuleQuery {
    @ApiProperty(name ="productId",desc = "所属产品ID，精确匹配")
    private String productId;

//    @ApiProperty(name ="projectIds",desc = "项目ID，精确匹配")
//    private String[] projectIds;

    @ApiProperty(name ="moduleName",desc = "模块名称，模糊匹配")
    private String moduleName;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("moduleName").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
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

//    public String[] getProjectIds() {
//        return projectIds;
//    }
//
//    public void setProjectIds(String[] projectIds) {
//        this.projectIds = projectIds;
//    }
}
