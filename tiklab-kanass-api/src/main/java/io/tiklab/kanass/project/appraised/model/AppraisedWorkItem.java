package io.tiklab.kanass.project.appraised.model;

import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;

import java.util.List;

/**
 * 评审与事项关联模型
 */
@ApiModel
@Mapper
@Join
public class AppraisedWorkItem {
    @ApiProperty(name = "id", desc = "唯一ID")
    private String id;

    @ApiProperty(name = "appraised", desc = "所属评审")
    @Mappings({
            @Mapping(source = "appraised.id", target = "appraisedId")
    })
    @JoinQuery(key = "id")
    private Appraised appraised;

    // 多个事项ID
    private List<String> workItemIds;

    @ApiProperty(name = "workItem", desc = "所属事项")
    @Mappings({
            @Mapping(source = "workItem.id", target = "workItemId")
    })
    @JoinQuery(key = "id")
    private WorkItem workItem;

    // 0未评审 1通过 2未通过 3建议
    @ApiProperty(name = "workItemAppraisedState", desc = "评审状态")
    private String workItemAppraisedState;

    @ApiProperty(name = "advice", desc = "评审意见")
    private String advice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Appraised getAppraised() {
        return appraised;
    }

    public void setAppraised(Appraised appraised) {
        this.appraised = appraised;
    }

    public WorkItem getWorkItem() {
        return workItem;
    }

    public void setWorkItem(WorkItem workItem) {
        this.workItem = workItem;
    }

    public String getWorkItemAppraisedState() {
        return workItemAppraisedState;
    }

    public void setWorkItemAppraisedState(String workItemAppraisedState) {
        this.workItemAppraisedState = workItemAppraisedState;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public List<String> getWorkItemIds() {
        return workItemIds;
    }

    public void setWorkItemIds(List<String> workItemIds) {
        this.workItemIds = workItemIds;
    }
}
