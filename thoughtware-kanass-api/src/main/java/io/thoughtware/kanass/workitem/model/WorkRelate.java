package io.thoughtware.kanass.workitem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.beans.annotation.Mapping;
import io.thoughtware.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.join.annotation.Join;
import io.thoughtware.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 关联事项模型
 */
@ApiModel
@Join
@Mapper
public class WorkRelate extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="workItem",desc="事项",required = true)
    @Mappings({
            @Mapping(source = "workItem.id",target = "workItemId")
    })
    @JoinQuery(key = "id")
    private WorkItem workItem;

    @NotNull
    @ApiProperty(name="relateWorkItem",desc="关联事项",required = true)
    @Mappings({
            @Mapping(source = "relateWorkItem.id",target = "relateItemId")
    })
    @JoinQuery(key = "id")
    private WorkItem relateWorkItem;

    @ApiProperty(name="createTime",desc="关联关系创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WorkItem getWorkItem() {
        return workItem;
    }

    public void setWorkItem(WorkItem workItem) {
        this.workItem = workItem;
    }

    public WorkItem getRelateWorkItem() {
        return relateWorkItem;
    }

    public void setRelateWorkItem(WorkItem relateWorkItem) {
        this.relateWorkItem = relateWorkItem;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
