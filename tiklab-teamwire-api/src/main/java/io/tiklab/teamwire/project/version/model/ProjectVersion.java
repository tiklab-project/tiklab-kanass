package io.tiklab.teamwire.project.version.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.user.user.model.User;

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

    @ApiProperty(name = "name", desc = "版本名称")
    private String name;

    @ApiProperty(name="master",desc="负责人")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinQuery(key = "id")
    private User master;

    @ApiProperty(name = "startTime", desc = "开始日期")
    private Date startTime;

    @ApiProperty(name = "focusIs", desc = "当前用户是否关注")
    private boolean focusIs;

    @ApiProperty(name = "publishDate", desc = "发布日期")
    private Date publishDate;

    @ApiProperty(name = "relaPublishDate", desc = "实际发布日期")
    private Date relaPublishDate;



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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getRelaPublishDate() {
        return relaPublishDate;
    }

    public void setRelaPublishDate(Date relaPublishDate) {
        this.relaPublishDate = relaPublishDate;
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
}
