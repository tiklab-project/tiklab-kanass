package io.tiklab.kanass.workitem.model;

import io.tiklab.core.BaseModel;
import io.tiklab.flow.flow.model.Flow;
import io.tiklab.form.form.model.Form;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

/**
 * 事项类型模型
 */

@ApiModel
@Join
@Mapper
public class WorkVersion extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @NotNull
    @ApiProperty(name="workItemId",desc="事项id",required = true)
    private String workItemId;

    @NotNull
    @ApiProperty(name="versionId",desc="版本名称",required = true)
    private String versionId;

    // null表示没有迁移  有id表示迁移的目标版本
    @ApiProperty(name="targetSprintId",desc="迁移的目标版本id")
    private String targetVersionId;

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

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getTargetVersionId() {
        return targetVersionId;
    }

    public void setTargetVersionId(String targetVersionId) {
        this.targetVersionId = targetVersionId;
    }
}
