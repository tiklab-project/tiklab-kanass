package io.tiklab.teamwire.workitem.model;


import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.flow.flow.model.Flow;
import io.tiklab.form.form.model.Form;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

/**
 * 项目下事项类型模型
 */
@ApiModel
@Join
@Mapper
public class WorkTypeDm extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="projectId",desc="所属项目")
    private java.lang.String projectId;

    @ApiProperty(name="workType",desc="事项类型")
    @Mappings({
            @Mapping(source = "workType.id",target = "workTypeId")
    })
    @JoinQuery(key = "id")
    private WorkType workType;

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

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
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
}
