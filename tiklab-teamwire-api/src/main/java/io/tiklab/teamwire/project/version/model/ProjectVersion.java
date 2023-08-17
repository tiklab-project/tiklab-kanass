package io.tiklab.teamwire.project.version.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.teamwire.project.project.model.Project;

import java.sql.Date;

/**
 * 项目版本模型
 */
@ApiModel
@Join
@Mapper
public class ProjectVersion extends BaseModel {

    @ApiProperty(name="id",desc="唯一ID")
    private java.lang.String id;

    @ApiProperty(name="name",desc="版本名称")
    private java.lang.String name;

    @ApiProperty(name="startTime",desc="开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @ApiProperty(name="publishDate",desc="发布日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;

    @ApiProperty(name="relaPublishDate",desc="实际发布日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date relaPublishDate;

    @ApiProperty(name="versionState",desc="项目状态")
    private java.lang.String  versionState;

    @ApiProperty(name="project",desc="所属项目")
    @Mappings({
            @Mapping(source = "project.id",target = "projectId")
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getVersionState() {
        return versionState;
    }

    public void setVersionState(String versionState) {
        this.versionState = versionState;
    }

    public Date getRelaPublishDate() {
        return relaPublishDate;
    }

    public void setRelaPublishDate(Date relaPublishDate) {
        this.relaPublishDate = relaPublishDate;
    }
}
