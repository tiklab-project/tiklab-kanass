package io.tiklab.teamwire.project.wiki.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.Date;

public class KanassDocument {
    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="KanassRepositoryName",desc="测试仓库名称")
    private String KanassRepositoryName;

    @ApiProperty(name="KanassRepositoryId",desc="测试仓库id")
    private String KanassRepositoryId;

    @ApiProperty(name="DocumentName",desc="测试仓库名称")
    private String DocumentName;

    @ApiProperty(name="UserName",desc="父级阶段")
    private String UserName;

    @ApiProperty(name="isExist",desc="父级阶段")
    private boolean isExist = true;

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

    public String getDocumentName() {
        return DocumentName;
    }

    public void setDocumentName(String documentName) {
        DocumentName = documentName;
    }

    public String getKanassRepositoryId() {
        return KanassRepositoryId;
    }

    public void setKanassRepositoryId(String kanassRepositoryId) {
        KanassRepositoryId = kanassRepositoryId;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }
}
