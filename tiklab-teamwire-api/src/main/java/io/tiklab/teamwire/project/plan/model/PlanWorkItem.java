package io.tiklab.teamwire.project.plan.model;

import io.tiklab.teamwire.workitem.model.WorkItem;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

/**
 * 计划事项关联模型
 */
@ApiModel
@Join
@Mapper
public class PlanWorkItem extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

;
    @NotNull
    @ApiProperty(name="workItem",desc="关联事项",required = true)
    @Mappings({
            @Mapping(source = "workItem.id",target = "workItemId")
    })
    @JoinQuery(key = "id")
    private WorkItem workItem;

    @NotNull
    @ApiProperty(name="planId",desc="关联计划id",required = true)
    private java.lang.String planId;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public WorkItem getWorkItem() {
        return workItem;
    }

    public void setWorkItem(WorkItem workItem) {
        this.workItem = workItem;
    }

    public java.lang.String getPlanId() {
        return planId;
    }

    public void setPlanId(java.lang.String planId) {
        this.planId = planId;
    }
}
