package io.tiklab.teamwire.project.test.model;

import io.tiklab.postin.annotation.ApiProperty;

public class ProjectTestCase {
    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="TestCategoryName",desc="测试仓库名称")
    private String TestCategoryName;

    @ApiProperty(name="TestCaseName",desc="测试仓库名称")
    private String TestCaseName;

    @ApiProperty(name="createUser",desc="父级阶段")
    private String createUser;

    @ApiProperty(name="caseType",desc="类型")
    private String caseType;

    @ApiProperty(name="isExist",desc="类型")
    private boolean isExist = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestCategoryName() {
        return TestCategoryName;
    }

    public void setTestCategoryName(String testCategoryName) {
        TestCategoryName = testCategoryName;
    }

    public String getTestCaseName() {
        return TestCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        TestCaseName = testCaseName;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }
}
