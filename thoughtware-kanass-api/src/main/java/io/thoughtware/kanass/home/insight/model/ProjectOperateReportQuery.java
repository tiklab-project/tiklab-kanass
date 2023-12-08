package io.thoughtware.kanass.home.insight.model;

import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;
import io.thoughtware.core.page.Page;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.Date;
import java.util.List;

/**
 * 统计项目动态数据条件模型
 */
@ApiModel
public class ProjectOperateReportQuery {
    @ApiProperty(name ="projectId",desc = "所属项目ID，精确匹配")
    private String projectId;
    @ApiProperty(name ="workProgressStatusId",desc = "事项进行中状态id，精确匹配")
    private List<String> workProgressStatusId;

    @ApiProperty(name ="workEndStatusId",desc = "项目状态IDs，精确匹配")
    private List<String> workEndStatusId;
    @ApiProperty(name ="startDate",desc = "开始时间")
    private Date startDate;

    @ApiProperty(name ="endDate",desc = "结束时间")
    private Date endDate;
    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public List<String> getWorkProgressStatusId() {
        return workProgressStatusId;
    }
    public void setWorkProgressStatusId(List<String> workProgressStatusId) {
        this.workProgressStatusId = workProgressStatusId;
    }

    public List<String> getWorkEndStatusId() {
        return workEndStatusId;
    }

    public void setWorkEndStatusId(List<String> workEndStatusId) {
        this.workEndStatusId = workEndStatusId;
    }
}