package io.tiklab.kanass.home.insight.model;

import io.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

/**
 * 项目数据统计模型
 */
public class ProjectOperateReport {

    @ApiProperty(name="id",desc="报告id")
    private String id;

    @NotNull
    @ApiProperty(name="projectId",desc="被统计项目id",required = true)
    private String projectId;

    @NotNull
    @ApiProperty(name="projectName",desc="被统计项目名称",required = true)
    private String projectName;

    @NotNull
    @ApiProperty(name="precent",desc="项目进度",required = true)
    private float precent;

    @ApiProperty(name="newWorkItemCount",desc="新增事项数量")
    private Integer newWorkItemCount = 0;

    @ApiProperty(name="endWorkItemCount",desc="完成事项数量")
    private Integer endWorkItemCount = 0;

    @ApiProperty(name="noEndWorkItemCount",desc="未完成事项数量")
    private Integer noEndWorkItemCount = 0;

    @ApiProperty(name="workItemEndAveragePeriod",desc="事项完成存量")
    private float workItemEndAveragePeriod = 0;

    @ApiProperty(name="overdueWorkItemCount",desc="逾期事项数量")
    private Integer overdueWorkItemCount = 0;

    @ApiProperty(name="newDemand",desc="新增需求事项")
    private Integer newDemand =0;

    @ApiProperty(name="endDemandCount",desc="完成的需求")
    private Integer endDemandCount = 0;

    @ApiProperty(name="noEndDemandCount",desc="未完成的需求事项")
    private Integer noEndDemandCount = 0;

    @ApiProperty(name="demandEndAveragePeriod",desc="需求完成存量")
    private float demandEndAveragePeriod = 0;

    @ApiProperty(name="overdueDemandCount",desc="逾期需求数量")
    private Integer overdueDemandCount = 0;

    @ApiProperty(name="newTask",desc="所有新增任务")
    private Integer newTask =0;

    @ApiProperty(name="endTaskCount",desc="完成任务数量")
    private Integer endTaskCount = 0;

    @ApiProperty(name="noEndTaskCount",desc="未完成任务数量")
    private Integer noEndTaskCount = 0;

    @ApiProperty(name="taskEndAveragePeriod",desc="任务完成存量")
    private float taskEndAveragePeriod = 0;

    @ApiProperty(name="overdueTaskCount",desc="逾期任务数量")
    private Integer overdueTaskCount = 0;

    @ApiProperty(name="newBug",desc="所有新增缺陷数量")
    private Integer newBug =0;

    @ApiProperty(name="endBugCount",desc="完成缺陷数量")
    private Integer endBugCount = 0;

    @ApiProperty(name="noEndBugCount",desc="未完成缺陷数量")
    private Integer noEndBugCount = 0;

    @ApiProperty(name="bugEndAveragePeriod",desc="缺陷完成增量")
    private float bugEndAveragePeriod = 0;

    @ApiProperty(name="overdueBugCount",desc="逾期缺陷数量")
    private Integer overdueBugCount = 0;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public float getPrecent() {
        return precent;
    }

    public void setPrecent(float precent) {
        this.precent = precent;
    }

    public Integer getNewWorkItemCount() {
        return newWorkItemCount;
    }

    public void setNewWorkItemCount(Integer newWorkItemCount) {
        this.newWorkItemCount = newWorkItemCount;
    }

    public Integer getEndWorkItemCount() {
        return endWorkItemCount;
    }

    public void setEndWorkItemCount(Integer endWorkItemCount) {
        this.endWorkItemCount = endWorkItemCount;
    }

    public Integer getNoEndWorkItemCount() {
        return noEndWorkItemCount;
    }

    public void setNoEndWorkItemCount(Integer noEndWorkItemCount) {
        this.noEndWorkItemCount = noEndWorkItemCount;
    }

    public float getWorkItemEndAveragePeriod() {
        return workItemEndAveragePeriod;
    }

    public void setWorkItemEndAveragePeriod(float workItemEndAveragePeriod) {
        this.workItemEndAveragePeriod = workItemEndAveragePeriod;
    }

    public Integer getNewBug() {
        return newBug;
    }

    public void setNewBug(Integer newBug) {
        this.newBug = newBug;
    }

    public Integer getEndBugCount() {
        return endBugCount;
    }

    public void setEndBugCount(Integer endBugCount) {
        this.endBugCount = endBugCount;
    }

    public Integer getNoEndBugCount() {
        return noEndBugCount;
    }

    public void setNoEndBugCount(Integer noEndBugCount) {
        this.noEndBugCount = noEndBugCount;
    }

    public float getBugEndAveragePeriod() {
        return bugEndAveragePeriod;
    }

    public void setBugEndAveragePeriod(float bugEndAveragePeriod) {
        this.bugEndAveragePeriod = bugEndAveragePeriod;
    }

    public Integer getOverdueWorkItemCount() {
        return overdueWorkItemCount;
    }

    public void setOverdueWorkItemCount(Integer overdueWorkItemCount) {
        this.overdueWorkItemCount = overdueWorkItemCount;
    }

    public Integer getNewDemand() {
        return newDemand;
    }

    public void setNewDemand(Integer newDemand) {
        this.newDemand = newDemand;
    }

    public Integer getEndDemandCount() {
        return endDemandCount;
    }

    public void setEndDemandCount(Integer endDemandCount) {
        this.endDemandCount = endDemandCount;
    }

    public Integer getNoEndDemandCount() {
        return noEndDemandCount;
    }

    public void setNoEndDemandCount(Integer noEndDemandCount) {
        this.noEndDemandCount = noEndDemandCount;
    }

    public float getDemandEndAveragePeriod() {
        return demandEndAveragePeriod;
    }

    public void setDemandEndAveragePeriod(float demandEndAveragePeriod) {
        this.demandEndAveragePeriod = demandEndAveragePeriod;
    }

    public Integer getOverdueDemandCount() {
        return overdueDemandCount;
    }

    public void setOverdueDemandCount(Integer overdueDemandCount) {
        this.overdueDemandCount = overdueDemandCount;
    }

    public Integer getNewTask() {
        return newTask;
    }

    public void setNewTask(Integer newTask) {
        this.newTask = newTask;
    }

    public Integer getEndTaskCount() {
        return endTaskCount;
    }

    public void setEndTaskCount(Integer endTaskCount) {
        this.endTaskCount = endTaskCount;
    }

    public Integer getNoEndTaskCount() {
        return noEndTaskCount;
    }

    public void setNoEndTaskCount(Integer noEndTaskCount) {
        this.noEndTaskCount = noEndTaskCount;
    }

    public float getTaskEndAveragePeriod() {
        return taskEndAveragePeriod;
    }

    public void setTaskEndAveragePeriod(float taskEndAveragePeriod) {
        this.taskEndAveragePeriod = taskEndAveragePeriod;
    }

    public Integer getOverdueTaskCount() {
        return overdueTaskCount;
    }

    public void setOverdueTaskCount(Integer overdueTaskCount) {
        this.overdueTaskCount = overdueTaskCount;
    }

    public Integer getOverdueBugCount() {
        return overdueBugCount;
    }

    public void setOverdueBugCount(Integer overdueBugCount) {
        this.overdueBugCount = overdueBugCount;
    }
}
