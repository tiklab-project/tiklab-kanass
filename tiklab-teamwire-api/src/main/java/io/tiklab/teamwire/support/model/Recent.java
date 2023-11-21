package io.tiklab.teamwire.support.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.teamwire.project.project.model.ProjectType;

import java.sql.Timestamp;

/**
 * 最近访问的模型
 */
@ApiModel
@Join
@Mapper
public class Recent extends BaseModel {

    @ApiProperty(name="id",desc="id",required = true)
    private java.lang.String id;

    @ApiProperty(name="object",desc="object")
    private Object object;
    @ApiProperty(name="name",desc="name")
    private java.lang.String name;

    @ApiProperty(name="model",desc="model")
    private java.lang.String model;

    @ApiProperty(name="modelId",desc="modelId")
    private java.lang.String modelId;


    @ApiProperty(name="masterId",desc="masterId")
    private java.lang.String masterId;

    @ApiProperty(name="project",desc="xiangmid")
    @Mappings({
            @Mapping(source = "project.id",target = "projectId")
    })
    @JoinQuery(key = "id")
    private Project project;

    @ApiProperty(name="projectType",desc = "项目类型")
    @Mappings({
            @Mapping(source = "projectType.id",target = "projectTypeId")
    })
    @JoinQuery(key = "id")
    private ProjectType projectType;


    @ApiProperty(name="recentTime",desc="recentTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp recentTime;

    @ApiProperty(name="iconUrl",desc="iconUrl")
    private String iconUrl;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }
    public java.lang.String getModel() {
        return model;
    }

    public void setModel(java.lang.String model) {
        this.model = model;
    }
    public java.lang.String getModelId() {
        return modelId;
    }

    public void setModelId(java.lang.String modelId) {
        this.modelId = modelId;
    }

    public java.lang.String getMasterId() {
        return masterId;
    }

    public void setMasterId(java.lang.String masterId) {
        this.masterId = masterId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Timestamp getRecentTime() {
        return recentTime;
    }

    public void setRecentTime(Timestamp recentTime) {
        this.recentTime = recentTime;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
