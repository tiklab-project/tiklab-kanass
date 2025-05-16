package io.tiklab.kanass.workitem.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.join.annotation.Join;

import javax.validation.constraints.NotNull;

/**
 * 事项类型模型
 */

@ApiModel
@Join
@Mapper
public class WorkSprint extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @NotNull
    @ApiProperty(name="workItemId",desc="事项id",required = true)
    private String workItemId;

    @NotNull
    @ApiProperty(name="sprintId",desc="版本名称",required = true)
    private String sprintId;

    // null表示没有迁移   有id表示迁移的目标迭代
    @ApiProperty(name="targetSprintId",desc="迁移的目标迭代id")
    private String targetSprintId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }

    public String getTargetSprintId() {
        return targetSprintId;
    }

    public void setTargetSprintId(String targetSprintId) {
        this.targetSprintId = targetSprintId;
    }
}
