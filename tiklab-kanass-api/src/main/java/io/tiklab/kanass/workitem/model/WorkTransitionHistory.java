package io.tiklab.kanass.workitem.model;

import io.tiklab.flow.statenode.model.StateNode;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.user.user.model.User;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel
@Mapper(targetName = "io.tiklab.kanass.workitem.entity.WorkTransitionHistoryEntity")
@Join
public class WorkTransitionHistory {
    @ApiProperty(name = "id", desc = "唯一ID")
    private String id;

    @NotNull
    @ApiProperty(name="workItem",desc="事项",required = true)
    @Mappings({
            @Mapping(source = "workItem.id",target = "workItemId")
    })
    @JoinField(key = "id")
    private WorkItem workItem;


    @ApiProperty(name="oldNode",desc="旧节点id")
    @Mappings({
            @Mapping(source = "oldNode.id",target = "oldNodeId")
    })
    @JoinField(key = "id")
    private StateNode oldNode;

    @ApiProperty(name="newNode",desc="新节点id")
    @Mappings({
            @Mapping(source = "newNode.id",target = "newNodeId")
    })
    @JoinField(key = "id")
    private StateNode newNode;

    @ApiProperty(name="creater",desc="创建人")
    @Mappings({
            @Mapping(source = "creater.id",target = "creater")
    })
    @JoinField(key = "id")
    private User creater;

    @ApiProperty(name = "transitionDesc", desc = "流转描述")
    private String transitionDesc;

    @ApiProperty(name="createTime",desc="创建时间")
    private java.util.Date createTime;

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

    public StateNode getOldNode() {
        return oldNode;
    }

    public void setOldNode(StateNode oldNode) {
        this.oldNode = oldNode;
    }

    public StateNode getNewNode() {
        return newNode;
    }

    public void setNewNode(StateNode newNode) {
        this.newNode = newNode;
    }

    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }

    public String getTransitionDesc() {
        return transitionDesc;
    }

    public void setTransitionDesc(String transitionDesc) {
        this.transitionDesc = transitionDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
