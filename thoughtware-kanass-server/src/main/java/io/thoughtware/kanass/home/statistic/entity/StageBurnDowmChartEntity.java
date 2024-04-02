package io.thoughtware.kanass.home.statistic.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.dal.jpa.annotation.*;

/**
 * 迭代的数据记录实例
 */
@Entity
@Table(name="pmc_stage_burndowm")
public class StageBurnDowmChartEntity {
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12,notNull = true)
    private String id;

    //项目迭代id
    @Column(name = "stage_id",length = 32,notNull = true)
    private String stageId;

    //记录时间
    @Column(name = "record_time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private String recordTime;

    //剩余工作量
    @Column(name = "remain_workitem_count")
    private Integer remainWorkitemCount;

    //全部工作量
    @Column(name = "total_workitem_count")
    private Integer totalWorkitemCount;

    //已完成工作
    @Column(name = "end_workitem_count")
    private Integer endWorkitemCount;

    //进行中工作
    @Column(name = "progress_workitem_count")
    private Integer progressWorkitemCount;

    //未开始工作
    @Column(name = "nostart_workitem_count")
    private Integer noStartWorkitemCount;

    //剩余缺陷量
    @Column(name = "remain_bug_count")
    private Integer remainBugCount;

    //全部缺陷
    @Column(name = "total_bug_count")
    private Integer totalBugCount;

    //已完成缺陷
    @Column(name = "end_bug_count")
    private Integer endBugCount;

    //进行中缺陷
    @Column(name = "progress_bug_count")
    private Integer progressBugCount;

    //未开始缺陷
    @Column(name = "nostart_bug_count")
    private Integer nostartBugCount;

    //剩余需求量
    @Column(name = "remain_demand_count")
    private Integer remainDemandCount;

    //全部需求
    @Column(name = "total_demand_count")
    private Integer totalDemandCount;

    //已完成需求
    @Column(name = "end_demand_count")
    private Integer endDemandCount;

    //进行中需求
    @Column(name = "progress_demand_count")
    private Integer progressDemandCount;

    //未开始需求
    @Column(name = "nostart_demand_count")
    private Integer nostartDemandCount;

    //剩余需求量
    @Column(name = "remain_task_count")
    private Integer remainTaskCount;

    //全部任务
    @Column(name = "total_task_count")
    private Integer totalTaskCount;

    //已完成任务
    @Column(name = "end_task_count")
    private Integer endTaskCount;

    //进行中任务
    @Column(name = "progress_task_count")
    private Integer progressTaskCount;

    //未开始任务
    @Column(name = "nostart_task_count")
    private Integer nostartTaskCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getNostartBugCount() {
        return nostartBugCount;
    }

    public void setNostartBugCount(Integer nostartBugCount) {
        this.nostartBugCount = nostartBugCount;
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

    public Integer getNostartDemandCount() {
        return nostartDemandCount;
    }

    public void setNostartDemandCount(Integer nostartDemandCount) {
        this.nostartDemandCount = nostartDemandCount;
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

    public Integer getNostartTaskCount() {
        return nostartTaskCount;
    }

    public void setNostartTaskCount(Integer nostartTaskCount) {
        this.nostartTaskCount = nostartTaskCount;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
}
