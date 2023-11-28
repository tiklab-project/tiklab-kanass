package io.tiklab.teamwire.home.insight.model;


import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;


/**
 * 事项统计条件模型
 */
@ApiModel
public class WorkItemCountQuery {

    @ApiProperty(name="projectId",desc="projectId")
    private String projectId;

    @ApiProperty(name="projectIds",desc="projectIds")
    private List<String> projectIds;

    @ApiProperty(name="projectSetId",desc="projectSetId")
    private String projectSetId;

    @ApiProperty(name="sprintId",desc="sprintId")
    private String sprintId;

    @ApiProperty(name="workItemTypeCode",desc="workItemTypeCode")
    private String workItemTypeCode;

    @ApiProperty(name="workItemStatusCode",desc="workItemStatusCode")
    private String workItemStatusCode;

    @ApiProperty(name="startDate",desc="startDate")
    private String startDate;

    @ApiProperty(name="endDate",desc="endDate")
    private String endDate;

    @ApiProperty(name ="cellTime",desc = "统计时间单位，精确匹配")
    private String cellTime;

    @ApiProperty(name ="collectionField",desc = "统计字段")
    private String collectionField;


    @ApiProperty(name ="collectionType",desc = "统计类型")
    private String collectionType;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectSetId() {
        return projectSetId;
    }

    public void setProjectSetId(String projectSetId) {
        this.projectSetId = projectSetId;
    }

    public String getCellTime() {
        return cellTime;
    }

    public void setCellTime(String cellTime) {
        this.cellTime = cellTime;
    }

    public String getCollectionField() {
        return collectionField;
    }

    public void setCollectionField(String collectionField) {
        this.collectionField = collectionField;
    }

    public String getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    public String getWorkItemTypeCode() {
        return workItemTypeCode;
    }

    public void setWorkItemTypeCode(String workItemTypeCode) {
        this.workItemTypeCode = workItemTypeCode;
    }

    public String getWorkItemStatusCode() {
        return workItemStatusCode;
    }

    public void setWorkItemStatusCode(String workItemStatusCode) {
        this.workItemStatusCode = workItemStatusCode;
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }

    public List<String> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<String> projectIds) {
        this.projectIds = projectIds;
    }
}