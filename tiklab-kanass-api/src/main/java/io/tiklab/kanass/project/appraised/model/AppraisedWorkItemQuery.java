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
public class AppraisedWorkItemQuery {

    @ApiProperty(name ="id",desc = "关联id")
    private String id;

    @ApiProperty(name ="ids",desc = "关联id列表，精确匹配")
    private List<String> ids;

    @ApiProperty(name ="appraisedId",desc = "评审id")
    private String appraisedId;

    @ApiProperty(name ="appraisedIds",desc = "评审id列表，精确匹配")
    private List<String> appraisedIds;

    @ApiProperty(name ="workItemId",desc = "事项id")
    private String workItemId;

    @ApiProperty(name ="workItemIds",desc = "事项id列表，精确匹配")
    private String workItemIds;

    @ApiProperty(name ="workItemName",desc = "事项名称")
    private String workItemName;

    // 0未评审 1通过 2未通过 3建议
    @ApiProperty(name ="workItemAppraisedState",desc = "评审状态")
    private String workItemAppraisedState;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("name").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    @ApiProperty(name ="workItemTypeCode",desc = "事项类型")
    private String workItemTypeCode;

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

    public String getAppraisedId() {
        return appraisedId;
    }

    public void setAppraisedId(String appraisedId) {
        this.appraisedId = appraisedId;
    }

    public List<String> getAppraisedIds() {
        return appraisedIds;
    }

    public void setAppraisedIds(List<String> appraisedIds) {
        this.appraisedIds = appraisedIds;
    }

    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
    }

    public String getWorkItemIds() {
        return workItemIds;
    }

    public void setWorkItemIds(String workItemIds) {
        this.workItemIds = workItemIds;
    }

    public String getWorkItemName() {
        return workItemName;
    }

    public void setWorkItemName(String workItemName) {
        this.workItemName = workItemName;
    }

    public String getWorkItemAppraisedState() {
        return workItemAppraisedState;
    }

    public void setWorkItemAppraisedState(String workItemAppraisedState) {
        this.workItemAppraisedState = workItemAppraisedState;
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

    public String getWorkItemTypeCode() {
        return workItemTypeCode;
    }

    public void setWorkItemTypeCode(String workItemTypeCode) {
        this.workItemTypeCode = workItemTypeCode;
    }
}
