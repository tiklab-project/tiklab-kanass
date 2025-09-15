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
public class AppraisedItemQuery {

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
    private String workItemTitle;

    // 0未评审 1通过 2未通过 3建议
    @ApiProperty(name ="workItemAppraisedState",desc = "评审状态")
    private String workItemAppraisedState;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("name").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    @ApiProperty(name ="workItemTypeCode",desc = "事项类型")
    private String workItemTypeCode;

    @ApiProperty(name ="master",desc = "负责人")
    private String master;

    @ApiProperty(name ="builder",desc = "创建者")
    private String builder;

    @ApiProperty(name ="projectId",desc = "项目id")
    private String projectId;

    @ApiProperty(name ="projectIds",desc = "项目id列表")
    private String[] projectIds;

    @ApiProperty(name ="appraisedTypeId",desc = "评审类型id")
    private String appraisedTypeId;

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

    public String getWorkItemTitle() {
        return workItemTitle;
    }

    public void setWorkItemTitle(String workItemTitle) {
        this.workItemTitle = workItemTitle;
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

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String[] getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String[] projectIds) {
        this.projectIds = projectIds;
    }

    public String getAppraisedTypeId() {
        return appraisedTypeId;
    }

    public void setAppraisedTypeId(String appraisedTypeId) {
        this.appraisedTypeId = appraisedTypeId;
    }
}
