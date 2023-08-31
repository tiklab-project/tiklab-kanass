package io.tiklab.teamwire.project.wiki.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.Date;

public class KanassRepository {
    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="KanassRepositoryName",desc="知识库名称")
    private String KanassRepositoryName;

    @ApiProperty(name="UserName",desc="用户")
    private String UserName;

    @ApiProperty(name="iconUrl",desc="icon")
    private String iconUrl;

    @ApiProperty(name="createTime",desc="createTime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKanassRepositoryName() {
        return KanassRepositoryName;
    }

    public void setKanassRepositoryName(String kanassRepositoryName) {
        KanassRepositoryName = kanassRepositoryName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
