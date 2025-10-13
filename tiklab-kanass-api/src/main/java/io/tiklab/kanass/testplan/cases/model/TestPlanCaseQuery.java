package io.tiklab.kanass.testplan.cases.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;

import java.util.List;

@ApiModel
public class TestPlanCaseQuery {
    @ApiProperty(name = "orderParams", desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name = "pageParam", desc = "分页参数")
    private Page pageParam = new Page();

    @ApiProperty(name = "testPlanId", desc = "测试计划id")
    private String testPlanId;

    @ApiProperty(name = "projectId", desc = "项目id")
    private String projectId;

    @ApiProperty(name = "name", desc = "用例名称")
    private String name;

    @ApiProperty(name = "caseType", desc = "用例类型")
    private String caseType;

    @ApiProperty(name = "testType", desc = "用例类型")
    private String testType;

    private String[] caseTypeList;

    @ApiProperty(name = "moduleId", desc = "测试用例id")
    private String moduleId;

    @ApiProperty(name = "testPlanType", desc = "测试计划类型，功能测试使用")
    private String testPlanType;
    @ApiProperty(name = "result", desc = "用于结果筛选")
    private Integer result;

    @ApiProperty(name = "caseId", desc = "用例id")
    private String caseId;

    @ApiProperty(name = "priorityLevel", desc = "优先级")
    private Integer priorityLevel;

    @ApiProperty(name = "director", desc = "负责人")
    private String director;

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

    public String getTestPlanId() {
        return testPlanId;
    }

    public TestPlanCaseQuery setTestPlanId(String testPlanId) {
        this.testPlanId = testPlanId;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String[] getCaseTypeList() {
        return caseTypeList;
    }

    public void setCaseTypeList(String[] caseTypeList) {
        this.caseTypeList = caseTypeList;
    }

    public String getTestPlanType() {
        return testPlanType;
    }

    public void setTestPlanType(String testPlanType) {
        this.testPlanType = testPlanType;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}