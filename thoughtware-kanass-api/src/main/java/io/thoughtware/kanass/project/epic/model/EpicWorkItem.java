package io.thoughtware.kanass.project.epic.model;

import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.beans.annotation.Mapping;
import io.thoughtware.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.join.annotation.Join;
import io.thoughtware.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

/**
 * 史诗事项关联模型
 */
@ApiModel
@Join
@Mapper
public class EpicWorkItem extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="workItem",desc="事项",required = true)
    @Mappings({
            @Mapping(source = "workItem.id",target = "workItemId")
    })
    @JoinQuery(key = "id")
    private WorkItem workItem;

    @NotNull
    @ApiProperty(name="epicId",desc="史诗id",required = true)
    private java.lang.String epicId;

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

    public java.lang.String getEpicId() {
        return epicId;
    }

    public void setEpicId(java.lang.String epicId) {
        this.epicId = epicId;
    }
}
