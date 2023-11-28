package io.tiklab.teamwire.workitem.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.flow.flow.model.Flow;
import io.tiklab.form.form.model.Form;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

/**
 * 事项类型模型
 */

@ApiModel
@Join
@Mapper
public class WorkType extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="projectId",desc="项目id")
    private java.lang.String projectId;

    @NotNull
    @ApiProperty(name="name",desc="事项类型名称",eg="@text32",required = true)
    private java.lang.String name;

    @ApiProperty(name="desc",desc="描述",eg="@text32")
    private java.lang.String desc;

    @ApiProperty(name="useNumber",desc="使用数量",eg="@text32")
    private Integer useNumber;

    @ApiProperty(name="sort",desc="排序")
    private java.lang.Integer sort;

    @ApiProperty(name="scope",desc="类型， 0: 系统 1: 自定义")
    private java.lang.Integer scope;


    @ApiProperty(name="form",desc="关联表单")
    @Mappings({
            @Mapping(source = "form.id",target = "formId")
    })
    @JoinQuery(key = "id")
    private Form form;

    @ApiProperty(name="flow",desc="关联流程")
    @Mappings({
            @Mapping(source = "flow.id",target = "flowId")
    })
    @JoinQuery(key = "id")
    private Flow flow;

    @ApiProperty(name="iconUrl",desc="图标地址",eg="@text32")
    private java.lang.String iconUrl;

    @ApiProperty(name="grouper",desc="分组",eg="@text32")
    private java.lang.String grouper;

    @ApiProperty(name="code",desc="事项类型编码",eg="demand, task, defect")
    private java.lang.String code;


    public java.lang.String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public java.lang.String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public java.lang.String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public java.lang.Integer getSort() {
        return sort;
    }

    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }


    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrouper() {
        return grouper;
    }

    public void setGrouper(String grouper) {
        this.grouper = grouper;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Integer getUseNumber() {
        return useNumber;
    }

    public void setUseNumber(Integer useNumber) {
        this.useNumber = useNumber;
    }
}
