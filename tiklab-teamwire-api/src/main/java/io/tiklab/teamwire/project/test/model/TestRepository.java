package io.tiklab.teamwire.project.test.model;

import io.tiklab.postin.annotation.ApiProperty;

public class TestRepository {
    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="TestRepositoryName",desc="测试仓库名称")
    private String TestRepositoryName;

    @ApiProperty(name="iconUrl",desc="测试仓库名称")
    private String iconUrl;

    @ApiProperty(name="UserName",desc="父级阶段")
    private String UserName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestRepositoryName() {
        return TestRepositoryName;
    }

    public void setTestRepositoryName(String testRepositoryName) {
        TestRepositoryName = testRepositoryName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
