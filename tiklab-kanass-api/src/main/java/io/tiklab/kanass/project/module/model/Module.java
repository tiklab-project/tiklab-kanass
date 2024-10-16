package io.tiklab.kanass.project.module.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;
import io.tiklab.kanass.project.project.model.Project;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 模块模型
 */
@ApiModel
@Join
@Mapper
public class Module extends BaseModel {

    @ApiProperty(name="id",desc="模块ID")
    private java.lang.String id;

    @ApiProperty(name="moduleName",desc="模块名称")
    private String moduleName;


    @ApiProperty(name="parent",desc="上级事项")
    @Mappings({
            @Mapping(source = "parent.id",target = "parentId")
    })
    @JoinQuery(key = "id")
    private Module parent;

    @ApiProperty(name="children",desc="下级事项列表")
    private List<Module> children;



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

    public Module getParent() {
        return parent;
    }

    public void setParent(Module parent) {
        this.parent = parent;
    }


    public List<Module> getChildren() {
        return children;
    }

    public void setChildren(List<Module> children) {
        this.children = children;
    }
}
