package io.tiklab.teston.test.test.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class TestCaseQuery implements Serializable {

    @ApiProperty(name = "categoryId", desc = "测试用例id")
    private String categoryId;

    @ApiProperty(name = "name",desc = "模糊查询")
    private String name;

    @ApiProperty(name = "id", desc = "接口步骤ID，精确匹配")
    private String id;

    @ApiProperty(name="仓库Id",desc="repositoryId")
    private String repositoryId;

    @ApiProperty(name = "testType", desc = "测试类型：api，app")
    private String testType;

    @ApiProperty(name = "caseType", desc = "用例类型: unit,scene,perform,func")
    private String caseType;

    @ApiProperty(name = "notInList", desc = "不包含")
    private String[] notInList;

    @ApiProperty(name = "inList", desc = "包含")
    private String[] inList;

    @ApiProperty(name = "createUser", desc = "创建人")
    private String createUser;

    @ApiProperty(name = "orderParams", desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().desc("createTime").get();

    @ApiProperty(name = "pageParam", desc = "分页参数")
    private Page pageParam = new Page();

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

    public String getCategoryId() {
        return categoryId;
    }

    public TestCaseQuery setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
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
}