package io.tiklab.teamwire.project.module.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.teamwire.project.project.model.Project;

import javax.validation.constraints.NotNull;

/**
 * 模块模型
 */
@ApiModel
@Join
@Mapper
public class Module extends BaseModel {

    @ApiProperty(name="id",desc="模块ID")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="moduleName",desc="模块名称",required = true)
    private java.lang.String moduleName;

    @ApiProperty(name="desc",desc="模块描述")
    private java.lang.String desc;

    @NotNull
    @ApiProperty(name="project",desc="所属项目",required = true)
    @Mappings({
            @Mapping(source = "project.id",target = "projectId")
    })
    @JoinQuery(key = "id")
    private Project project;

    public java.lang.String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public java.lang.String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    public java.lang.String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
