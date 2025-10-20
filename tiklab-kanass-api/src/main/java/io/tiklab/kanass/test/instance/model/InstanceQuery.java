package io.tiklab.kanass.test.instance.model;


import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.sql.Timestamp;
import java.util.List;

@ApiModel
public class InstanceQuery {

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().desc("createTime").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();


    @ApiProperty(name ="caseId",desc = "用例id")
    private String caseId;

    @ApiProperty(name ="testPlanId",desc = "计划id")
    private String testPlanId;

    @ApiProperty(name ="type",desc = "类型")
    private String type;

    @ApiProperty(name ="name",desc = "name")
    private String name;

    @ApiProperty(name ="createUser",desc = "创建人")
    private String createUser;

    @ApiProperty(name ="status",desc = "status")
    private String status;

    @ApiProperty(name ="projectId",desc = "projectId")
    private String projectId;

    private String[] typeList;

    @ApiProperty(name ="planType",desc = "计划类型")
    private String planType;

    @ApiProperty(name ="startTime",desc = "开始时间")
    private Timestamp startTime;
    @ApiProperty(name ="endTime",desc = "结束时间")
    private Timestamp endTime;


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


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String[] getTypeList() {
        return typeList;
    }

    public void setTypeList(String[] typeList) {
        this.typeList = typeList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(String testPlanId) {
        this.testPlanId = testPlanId;
    }
}