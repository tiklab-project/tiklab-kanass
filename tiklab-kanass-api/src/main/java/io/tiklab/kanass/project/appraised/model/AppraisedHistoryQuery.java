package io.tiklab.kanass.project.appraised.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.join.annotation.Join;

import java.util.List;

@ApiModel
@Mapper
@Join
public class AppraisedHistoryQuery {

    @ApiProperty(name ="id",desc = "关联id")
    private String id;

    @ApiProperty(name ="ids",desc = "关联id列表，精确匹配")
    private List<String> ids;

    @ApiProperty(name ="appraisedItemId",desc = "事项评审id")
    private String appraisedItemId;

    @ApiProperty(name ="appraisedItemIds",desc = "事项评审id列表，精确匹配")
    private List<String> appraisedItemIds;

    // 0未评审 1通过 2未通过 3建议
    @ApiProperty(name ="appraisedItemState",desc = "评审状态")
    private String appraisedItemState;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("name").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getAppraisedItemState() {
        return appraisedItemState;
    }

    public void setAppraisedItemState(String appraisedItemState) {
        this.appraisedItemState = appraisedItemState;
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

    public List<String> getAppraisedItemIds() {
        return appraisedItemIds;
    }

    public void setAppraisedItemIds(List<String> appraisedItemIds) {
        this.appraisedItemIds = appraisedItemIds;
    }

    public String getAppraisedItemId() {
        return appraisedItemId;
    }

    public void setAppraisedItemId(String appraisedItemId) {
        this.appraisedItemId = appraisedItemId;
    }
}
