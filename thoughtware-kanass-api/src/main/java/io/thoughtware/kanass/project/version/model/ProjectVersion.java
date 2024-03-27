package io.thoughtware.kanass.project.version.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;
import io.thoughtware.kanass.project.project.model.Project;
import io.thoughtware.user.user.model.User;

import java.sql.Date;

/**
 * 项目版本模型
 */
@ApiModel
@Join
@Mapper
public class ProjectVersion extends BaseModel {

    @ApiProperty(name = "id", desc = "唯一ID")
    private String id;

    @ApiProperty(name="newVersionId",desc="新的迭代ID")
    private String newVersionId;

    @ApiProperty(name = "name", desc = "版本名称")
    private String name;

    @ApiProperty(name="master",desc="负责人")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinQuery(key = "id")
    private User master;

    @ApiProperty(name="builder",desc="创建人")
    @Mappings({
            @Mapping(source = "builder.id",target = "builder")
    })
    @JoinQuery(key = "id")
    private User builder;


    @ApiProperty(name = "focusIs", desc = "当前用户是否关注")
    private boolean focusIs;

    @ApiProperty(name = "startTime", desc = "开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String startTime;

    @ApiProperty(name = "publishTime", desc = "发布日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String publishTime;

    @ApiProperty(name = "relaStartTime", desc = "实际开始 日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String relaStartTime;

    @ApiProperty(name = "relaPublishTime", desc = "实际发布日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String relaPublishTime;

    @ApiProperty(name = "workNumber", desc = "事项数量")
    private int workNumber;


    @ApiProperty(name = "workProgressNumber", desc = "事项数量")
    private int workProgressNumber;


    @ApiProperty(name = "workDoneNumber", desc = "事项数量")
    private int workDoneNumber;


    @ApiProperty(name="versionState",desc="版本状态")
    @Mappings({
            @Mapping(source = "versionState.id",target = "versionState")
    })
    @JoinQuery(key = "id")
    private VersionState versionState;

    @ApiProperty(name = "project", desc = "所属项目")
    @Mappings({
            @Mapping(source = "project.id", target = "projectId")
    })
    @JoinQuery(key = "id")
    private Project project;


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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public VersionState getVersionState() {
        return versionState;
    }

    public void setVersionState(VersionState versionState) {
        this.versionState = versionState;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getRelaStartTime() {
        return relaStartTime;
    }

    public void setRelaStartTime(String relaStartTime) {
        this.relaStartTime = relaStartTime;
    }

    public String getRelaPublishTime() {
        return relaPublishTime;
    }

    public void setRelaPublishTime(String relaPublishTime) {
        this.relaPublishTime = relaPublishTime;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public boolean isFocusIs() {
        return focusIs;
    }

    public void setFocusIs(boolean focusIs) {
        this.focusIs = focusIs;
    }

    public int getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(int workNumber) {
        this.workNumber = workNumber;
    }

    public String getNewVersionId() {
        return newVersionId;
    }

    public void setNewVersionId(String newVersionId) {
        this.newVersionId = newVersionId;
    }

    public int getWorkProgressNumber() {
        return workProgressNumber;
    }

    public void setWorkProgressNumber(int workProgressNumber) {
        this.workProgressNumber = workProgressNumber;
    }

    public int getWorkDoneNumber() {
        return workDoneNumber;
    }

    public void setWorkDoneNumber(int workDoneNumber) {
        this.workDoneNumber = workDoneNumber;
    }

    public User getBuilder() {
        return builder;
    }

    public void setBuilder(User builder) {
        this.builder = builder;
    }
}
