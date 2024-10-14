package io.tiklab.kanass.workitem.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

/**
 * 事项附件模型
 */

@ApiModel
@Join
@Mapper
public class WorkAttach extends BaseModel {

    @ApiProperty(name = "id", desc = "唯一标识")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name = "workItem", desc = "事项", required = true)
    @Mappings({
            @Mapping(source = "workItem.id", target = "workItemId")
    })
    @JoinQuery(key = "id")
    private WorkItem workItem;

    @NotNull
    @ApiProperty(name = "attachmentName", desc = "附件", required = true)
    private String attachmentName;

    @NotNull
    @ApiProperty(name = "attachmentUrl", desc = "附件地址", required = true)
    private String attachmentUrl;

    @NotNull
    @ApiProperty(name = "type", desc = "附件", required = true)
    private String type;

    @ApiProperty(name = "sort", desc = "排序", eg = "@int16")
    private java.lang.Integer sort;

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


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


