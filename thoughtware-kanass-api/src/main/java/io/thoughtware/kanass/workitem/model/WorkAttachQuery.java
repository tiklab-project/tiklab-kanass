package io.thoughtware.kanass.workitem.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.core.page.Page;
import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;

import java.util.List;

/**
 * 事项附件查询参数模型
 */
@ApiModel
public class WorkAttachQuery {

    @ApiProperty(name ="workItemId",desc = "事项ID，精确匹配")
    private String workItemId;

    @ApiProperty(name ="workItemIds",desc = "事项ID，精确匹配")
    private String[] workItemIds;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("workItemId").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
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

    public String[] getWorkItemIds() {
        return workItemIds;
    }

    public void setWorkItemIds(String[] workItemIds) {
        this.workItemIds = workItemIds;
    }
}