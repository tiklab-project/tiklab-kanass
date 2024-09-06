package io.thoughtware.kanass.workitem.model;


import io.thoughtware.kanass.project.workPrivilege.model.WorkPrivilege;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.flow.flow.model.Flow;
import io.thoughtware.form.form.model.Form;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

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


    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }


}
