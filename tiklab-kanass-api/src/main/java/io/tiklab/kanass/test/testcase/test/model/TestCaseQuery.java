package io.tiklab.kanass.test.testcase.test.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class TestCaseQuery implements Serializable {

    @ApiProperty(name = "moduleId", desc = "模块")
    private String moduleId;

    @ApiProperty(name = "name",desc = "模糊查询")
    private String name;

    @ApiProperty(name = "id", desc = "接口步骤ID，精确匹配")
    private String id;

    @ApiProperty(name="status",desc="状态")
    private Integer status;

    @ApiProperty(name="priorityLevel",desc="优先级")
    private Integer priorityLevel;

    @ApiProperty(name="仓库Id",desc="projectId")
    private String projectId;

    @ApiProperty(name = "testType", desc = "测试类型：api，app")
    private String testType;

    @ApiProperty(name = "caseType", desc = "用例类型: unit,scene,perform,func")
    private String caseType;

    @ApiProperty(name = "caseTypeList", desc = "caseTypeList")
    private String[] caseTypeList;

    @ApiProperty(name = "notInList", desc = "不包含")
    private String[] notInList;

    @ApiProperty(name = "inList", desc = "包含")
    private String[] inList;

    @ApiProperty(name = "createUser", desc = "创建人")
    private String createUser;

    @ApiProperty(name = "director", desc = "负责人")
    private String director;

    @ApiProperty(name = "orderParams", desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().desc("createTime").get();


    @ApiProperty(name = "pageParam", desc = "分页参数")
    private Page pageParam = new Page();

    private String[] moduleIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModuleId() {
        return moduleId;
    }

    public TestCaseQuery setModuleId(String moduleId) {
        this.moduleId = moduleId;
        return this;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String[] getCaseTypeList() {
        return caseTypeList;
    }

    public void setCaseTypeList(String[] caseTypeList) {
        this.caseTypeList = caseTypeList;
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

    public String[] getNotInList() {
        return notInList;
    }

    public void setNotInList(String[] notInList) {
        this.notInList = notInList;
    }

    public String[] getInList() {
        return inList;
    }

    public void setInList(String[] inList) {
        this.inList = inList;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String[] getModuleIds() {
        return moduleIds;
    }

    public void setModuleIds(String[] moduleIds) {
        this.moduleIds = moduleIds;
    }

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }
}