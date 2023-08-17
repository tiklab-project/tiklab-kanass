package io.tiklab.teamwire.workitem.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.flow.statenode.model.StateNode;

import java.util.List;

/**
 * 事项看板视图模型
 */
@ApiModel
public class WorkBoard {

    @ApiProperty(name="workStatus",desc="事项状态")
    private StateNode state;

    @ApiProperty(name="id",desc="事项列表")
    private List<WorkItem> workItemList;

    public StateNode getState() {
        return state;
    }

    public void setState(StateNode state) {
        this.state = state;
    }

    public List<WorkItem> getWorkItemList() {
        return workItemList;
    }

    public void setWorkItemList(List<WorkItem> workItemList) {
        this.workItemList = workItemList;
    }
}
