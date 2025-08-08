package io.tiklab.kanass.instance.model;


import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class TestReportQuery {

    @ApiProperty(name = "orderParams", desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().desc("createTime").get();

    @ApiProperty(name = "pageParam", desc = "分页参数")
    private Page pageParam = new Page();

    @ApiProperty(name = "projectId", desc = "仓库ID")
    private String projectId;

    @ApiProperty(name = "testPlanId", desc = "测试计划ID")
    private String testPlanId;

    @ApiProperty(name = "name", desc = "名称")
    private String name;

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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(String testPlanId) {
        this.testPlanId = testPlanId;
    }
}