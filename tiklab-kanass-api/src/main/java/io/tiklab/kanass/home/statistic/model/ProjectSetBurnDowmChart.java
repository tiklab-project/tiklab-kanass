package io.tiklab.kanass.home.statistic.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.join.annotation.Join;

import javax.validation.constraints.NotNull;

/**
 * 项目集的事项数据动态模型
 */
@ApiModel
@Mapper
@Join
public class ProjectSetBurnDowmChart extends BaseModel{

    @ApiProperty(name="id",desc="id",required = true)
    private String id;

    @NotNull
    @ApiProperty(name="projectSetId",desc="项目集id",required = true)
    private String projectSetId;

    @ApiProperty(name="recordTime",desc="记录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String recordTime;

    @ApiProperty(name="remainWorkitemCount",desc="未完成事项数量")
    private Integer remainWorkitemCount;

    @ApiProperty(name="totalWorkitemCount",desc="全部事项数量")
    private Integer totalWorkitemCount;

    @ApiProperty(name="endWorkitemCount",desc="完成事项数量")
    private Integer endWorkitemCount;

    @ApiProperty(name="progressWorkitemCount",desc="进行中事项数量")
    private Integer progressWorkitemCount;

    @ApiProperty(name="noStartWorkitemCount",desc="未开始事项数量")
    private Integer noStartWorkitemCount;

    @ApiProperty(name="remainBugCount",desc="未完成缺陷数量")
    private Integer remainBugCount;

    @ApiProperty(name="totalBugCount",desc="全部缺陷数量")
    private Integer totalBugCount;

    @ApiProperty(name="endBugCount",desc="完成缺陷数量")
    private Integer endBugCount;

    @ApiProperty(name="progressBugCount",desc="进行中缺陷数量")
    private Integer progressBugCount;

    @ApiProperty(name="noStartBugCount",desc="未开始缺陷数量")
    private Integer noStartBugCount;

    @ApiProperty(name="remainDemandCount",desc="未完成需求数量")
    private Integer remainDemandCount;

    @ApiProperty(name="totalDemandCount",desc="全部需求数量")
    private Integer totalDemandCount;

    @ApiProperty(name="endDemandCount",desc="完成需求数量")
    private Integer endDemandCount;

    @ApiProperty(name="progressDemandCount",desc="进行中需求数量")
    private Integer progressDemandCount;

    @ApiProperty(name="noStartDemandCount",desc="未开始需求数量")
    private Integer noStartDemandCount;

    @ApiProperty(name="remainTaskCount",desc="未完成任务数量")
    private Integer remainTaskCount;

    @ApiProperty(name="totalTaskCount",desc="全部任务数量")
    private Integer totalTaskCount;

    @ApiProperty(name="endTaskCount",desc="完成任务数量")
    private Integer endTaskCount;

    @ApiProperty(name="progressTaskCount",desc="进行中任务数量")
    private Integer progressTaskCount;

    @ApiProperty(name="noStartTaskCount",desc="未完成任务数量")
    private Integer noStartTaskCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getProjectSetId() {
        return projectSetId;
    }

    public void setProjectSetId(String projectSetId) {
        this.projectSetId = projectSetId;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public Integer getRemainWorkitemCount() {
        return remainWorkitemCount;
    }

    public void setRemainWorkitemCount(Integer remainWorkitemCount) {
        this.remainWorkitemCount = remainWorkitemCount;
    }
    public Integer getTotalWorkitemCount() {
        return totalWorkitemCount;
    }

    public void setTotalWorkitemCount(Integer totalWorkitemCount) {
        this.totalWorkitemCount = totalWorkitemCount;
    }
    public Integer getEndWorkitemCount() {
        return endWorkitemCount;
    }

    public void setEndWorkitemCount(Integer endWorkitemCount) {
        this.endWorkitemCount = endWorkitemCount;
    }
    public Integer getProgressWorkitemCount() {
        return progressWorkitemCount;
    }

    public void setProgressWorkitemCount(Integer progressWorkitemCount) {
        this.progressWorkitemCount = progressWorkitemCount;
    }
    public Integer getNoStartWorkitemCount() {
        return noStartWorkitemCount;
    }

    public void setNoStartWorkitemCount(Integer noStartWorkitemCount) {
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
