package io.tiklab.kanass.test.common.stepcommon.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class StepCommonQuery implements Serializable {
    @ApiProperty(name ="caseId",desc = "caseId，精确匹配")
    private String caseId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("sort").get();

    @ApiProperty(name = "pageParam", desc = "分页参数")
    private Page pageParam = new Page();

    @ApiProperty(name ="caseType",desc = "caseType")
    private String caseType;

    @ApiProperty(name ="isPlanEntry",desc = "是否从测试计划进入功能用例")
    private Boolean isPlanEntry;

    @ApiProperty(name ="testPlanId",desc = "testPlanId")
    private String testPlanId;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
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

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public Boolean getIsPlanEntry() {
        return isPlanEntry;
    }

    public void setIsPlanEntry(Boolean planEntry) {
        isPlanEntry = planEntry;
    }


    public String getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(String testPlanId) {
        this.testPlanId = testPlanId;
    }
}