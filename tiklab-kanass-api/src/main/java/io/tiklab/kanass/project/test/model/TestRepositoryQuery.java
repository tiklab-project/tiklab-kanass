package io.tiklab.kanass.project.test.model;

import io.tiklab.postin.annotation.ApiProperty;

public class TestRepositoryQuery {
    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="TestRepositoryName",desc="测试仓库名称")
    private String TestRepositoryName;

    @ApiProperty(name="iconUrl",desc="测试仓库名称")
    private String iconUrl;

    @ApiProperty(name="user",desc="父级阶段")
    private String user;

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


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
