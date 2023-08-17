package io.tiklab.teamwire.home.statistic.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 项目的事项数据动态模型
 */
@ApiModel
@Mapper
public class ProjectBurnDowmChart extends BaseModel{

    @ApiProperty(name="id",desc="id",required = true)
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="projectId",desc="项目id",required = true)
    private java.lang.String projectId;

    @ApiProperty(name="recordTime",desc="记录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String recordTime;

    @ApiProperty(name="remainWorkitemCount",desc="未完成事项数量")
    private java.lang.Integer remainWorkitemCount;

    @ApiProperty(name="totalWorkitemCount",desc="全部事项数量")
    private java.lang.Integer totalWorkitemCount;

    @ApiProperty(name="endWorkitemCount",desc="完成事项数量")
    private java.lang.Integer endWorkitemCount;

    @ApiProperty(name="progressWorkitemCount",desc="进行中事项数量")
    private java.lang.Integer progressWorkitemCount;

    @ApiProperty(name="noStartWorkitemCount",desc="未开始事项数量")
    private java.lang.Integer noStartWorkitemCount;

    @ApiProperty(name="remainBugCount",desc="未完成缺陷数量")
    private java.lang.Integer remainBugCount;

    @ApiProperty(name="totalBugCount",desc="全部缺陷数量")
    private java.lang.Integer totalBugCount;

    @ApiProperty(name="endBugCount",desc="完成缺陷数量")
    private java.lang.Integer endBugCount;

    @ApiProperty(name="progressBugCount",desc="进行中缺陷数量")
    private java.lang.Integer progressBugCount;

    @ApiProperty(name="noStartBugCount",desc="未开始缺陷数量")
    private java.lang.Integer noStartBugCount;

    @ApiProperty(name="remainDemandCount",desc="未完成需求数量")
    private java.lang.Integer remainDemandCount;

    @ApiProperty(name="totalDemandCount",desc="全部需求数量")
    private java.lang.Integer totalDemandCount;

    @ApiProperty(name="endDemandCount",desc="完成需求数量")
    private java.lang.Integer endDemandCount;

    @ApiProperty(name="progressDemandCount",desc="进行中需求数量")
    private java.lang.Integer progressDemandCount;

    @ApiProperty(name="noStartDemandCount",desc="未开始需求数量")
    private java.lang.Integer noStartDemandCount;

    @ApiProperty(name="remainTaskCount",desc="未完成任务数量")
    private java.lang.Integer remainTaskCount;

    @ApiProperty(name="totalTaskCount",desc="全部任务数量")
    private java.lang.Integer totalTaskCount;

    @ApiProperty(name="endTaskCount",desc="完成任务数量")
    private java.lang.Integer endTaskCount;

    @ApiProperty(name="progressTaskCount",desc="进行中任务数量")
    private java.lang.Integer progressTaskCount;

    @ApiProperty(name="noStartTaskCount",desc="未完成任务数量")
    private java.lang.Integer noStartTaskCount;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getProjectId() {
        return projectId;
    }

    public void setProjectId(java.lang.String projectId) {
        this.projectId = projectId;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public java.lang.Integer getRemainWorkitemCount() {
        return remainWorkitemCount;
    }

    public void setRemainWorkitemCount(java.lang.Integer remainWorkitemCount) {
        this.remainWorkitemCount = remainWorkitemCount;
    }
    public java.lang.Integer getTotalWorkitemCount() {
        return totalWorkitemCount;
    }

    public void setTotalWorkitemCount(java.lang.Integer totalWorkitemCount) {
        this.totalWorkitemCount = totalWorkitemCount;
    }
    public java.lang.Integer getEndWorkitemCount() {
        return endWorkitemCount;
    }

    public void setEndWorkitemCount(java.lang.Integer endWorkitemCount) {
        this.endWorkitemCount = endWorkitemCount;
    }
    public java.lang.Integer getProgressWorkitemCount() {
        return progressWorkitemCount;
    }

    public void setProgressWorkitemCount(java.lang.Integer progressWorkitemCount) {
        this.progressWorkitemCount = progressWorkitemCount;
    }
    public java.lang.Integer getNoStartWorkitemCount() {
        return noStartWorkitemCount;
    }

    public void setNoStartWorkitemCount(java.lang.Integer noStartWorkitemCount) {
        this.noStartWorkitemCount = noStartWorkitemCount;
    }

    public Integer getRemainBugCount() {
        return remainBugCount;
    }

    public void setRemainBugCount(Integer remainBugCount) {
        this.remainBugCount = remainBugCount;
    }

    public Integer getTotalBugCount() {
        return totalBugCount;
    }

    public void setTotalBugCount(Integer totalBugCount) {
        this.totalBugCount = totalBugCount;
    }

    public Integer getEndBugCount() {
        return endBugCount;
    }

    public void setEndBugCount(Integer endBugCount) {
        this.endBugCount = endBugCount;
    }

    public Integer getProgressBugCount() {
        return progressBugCount;
    }

    public void setProgressBugCount(Integer progressBugCount) {
        this.progressBugCount = progressBugCount;
    }

    public Integer getNoStartBugCount() {
        return noStartBugCount;
    }

    public void setNoStartBugCount(Integer noStartBugCount) {
        this.noStartBugCount = noStartBugCount;
    }

    public Integer getRemainDemandCount() {
        return remainDemandCount;
    }

    public void setRemainDemandCount(Integer remainDemandCount) {
        this.remainDemandCount = remainDemandCount;
    }

    public Integer getTotalDemandCount() {
        return totalDemandCount;
    }

    public void setTotalDemandCount(Integer totalDemandCount) {
        this.totalDemandCount = totalDemandCount;
    }

    public Integer getEndDemandCount() {
        return endDemandCount;
    }

    public void setEndDemandCount(Integer endDemandCount) {
        this.endDemandCount = endDemandCount;
    }

    public Integer getProgressDemandCount() {
        return progressDemandCount;
    }

    public void setProgressDemandCount(Integer progressDemandCount) {
        this.progressDemandCount = progressDemandCount;
    }

    public Integer getNoStartDemandCount() {
        return noStartDemandCount;
    }

    public void setNoStartDemandCount(Integer noStartDemandCount) {
        this.noStartDemandCount = noStartDemandCount;
    }

    public Integer getRemainTaskCount() {
        return remainTaskCount;
    }

    public void setRemainTaskCount(Integer remainTaskCount) {
        this.remainTaskCount = remainTaskCount;
    }

    public Integer getTotalTaskCount() {
        return totalTaskCount;
    }

    public void setTotalTaskCount(Integer totalTaskCount) {
        this.totalTaskCount = totalTaskCount;
    }

    public Integer getEndTaskCount() {
        return endTaskCount;
    }

    public void setEndTaskCount(Integer endTaskCount) {
        this.endTaskCount = endTaskCount;
    }

    public Integer getProgressTaskCount() {
        return progressTaskCount;
    }

    public void setProgressTaskCount(Integer progressTaskCount) {
        this.progressTaskCount = progressTaskCount;
    }

    public Integer getNoStartTaskCount() {
        return noStartTaskCount;
    }

    public void setNoStartTaskCount(Integer noStartTaskCount) {
        this.noStartTaskCount = noStartTaskCount;
    }
}
