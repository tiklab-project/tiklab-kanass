package io.thoughtware.kanass.workitem.model;

import io.thoughtware.core.page.Page;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;

import java.util.List;

/**
 * 关联事项搜索条件模型
 */
@ApiModel
public class WorkRelateQuery {

//    @JoinField(entityAlias = "WorkItemEntity",alias = "wi",joinExp = "wr.relateItemId=wi.id")
    private String leftJoinWorkItemEntity;

    @ApiProperty(name ="workItemId",desc = "事项ID，精确匹配")
    private String workItemId;

    @ApiProperty(name ="workItemIds",desc = "事项ID，精确匹配")
    private String[] workItemIds;


    @ApiProperty(name ="title",desc = "标题，模糊匹配")
    private String title;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().desc("createTime").get();

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLeftJoinWorkItemEntity() {
        return leftJoinWorkItemEntity;
    }

    public void setLeftJoinWorkItemEntity(String leftJoinWorkItemEntity) {
        this.leftJoinWorkItemEntity = leftJoinWorkItemEntity;
    }

    public String[] getWorkItemIds() {
        return workItemIds;
    }

    public void setWorkItemIds(String[] workItemIds) {
        this.workItemIds = workItemIds;
    }
}