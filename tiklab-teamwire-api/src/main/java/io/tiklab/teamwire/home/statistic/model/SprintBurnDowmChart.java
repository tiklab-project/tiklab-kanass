package io.tiklab.teamwire.home.statistic.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 迭代事项数据动态统计模型
 */
@ApiModel
@Mapper
public class SprintBurnDowmChart extends BaseModel{

    @ApiProperty(name="id",desc="id",required = true)
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="sprintId",desc="迭代名称",required = true)
    private java.lang.String sprintId;

    @ApiProperty(name="recordTime",desc="记录时间")
    private java.util.Date recordTime;

    @ApiProperty(name="remainWorkItemCount",desc="未完成事项数量")
    private java.lang.Integer remainWorkItemCount;

    @ApiProperty(name="totalWorkItemCount",desc="全部事项数量")
    private java.lang.Integer totalWorkItemCount;

    @ApiProperty(name="endWorkItemCount",desc="完成事项数量")
    private java.lang.Integer endWorkItemCount;

    @ApiProperty(name="progressWorkItemCount",desc="进行中事项数量")
    private java.lang.Integer progressWorkItemCount;

    @ApiProperty(name="noStartWorkItemCount",desc="未开始事项数量")
    private java.lang.Integer noStartWorkItemCount;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getSprintId() {
        return sprintId;
    }

    public void setSprintId(java.lang.String sprintId) {
        this.sprintId = sprintId;
    }
    public java.util.Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(java.util.Date recordTime) {
        this.recordTime = recordTime;
    }
    public java.lang.Integer getRemainWorkItemCount() {
        return remainWorkItemCount;
    }

    public void setRemainWorkItemCount(java.lang.Integer remainWorkItemCount) {
        this.remainWorkItemCount = remainWorkItemCount;
    }
    public java.lang.Integer getTotalWorkItemCount() {
        return totalWorkItemCount;
    }

    public void setTotalWorkItemCount(java.lang.Integer totalWorkItemCount) {
        this.totalWorkItemCount = totalWorkItemCount;
    }
    public java.lang.Integer getEndWorkItemCount() {
        return endWorkItemCount;
    }

    public void setEndWorkItemCount(java.lang.Integer endWorkItemCount) {
        this.endWorkItemCount = endWorkItemCount;
    }
    public java.lang.Integer getProgressWorkItemCount() {
        return progressWorkItemCount;
    }

    public void setProgressWorkItemCount(java.lang.Integer progressWorkItemCount) {
        this.progressWorkItemCount = progressWorkItemCount;
    }
    public java.lang.Integer getNoStartWorkItemCount() {
        return noStartWorkItemCount;
    }

    public void setNoStartWorkItemCount(java.lang.Integer noStartWorkItemCount) {
        this.noStartWorkItemCount = noStartWorkItemCount;
    }
}
