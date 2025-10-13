package io.tiklab.kanass.project.wiki.model;

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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

    @ApiProperty(name="limits",desc="limits")
    private String limits;

    @ApiProperty(name="desc",desc="desc")
    private String desc;

    @ApiProperty(name="name",desc="name")
    private String name;

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

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
