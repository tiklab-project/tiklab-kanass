package io.thoughtware.kanass.home.statistic.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

/**
 * 按照传入字段统计单位时间内事项数量的搜索模型
 */
@ApiModel
public class StatisticWorkItemQuery {
    @ApiProperty(name ="projectSetId",desc = "所属项目集ID，精确匹配")
    private String projectSetId;

    @ApiProperty(name ="projectId",desc = "所属项目ID，精确匹配")
    private String projectId;

    @ApiProperty(name ="sprintId",desc = "所属迭代ID，精确匹配")
    private String sprintId;

    @ApiProperty(name ="dateRanger",desc = "统计时长，精确匹配")
    private Integer dateRanger;

    @ApiProperty(name ="cellTime",desc = "统计时间单位，精确匹配")
    private String cellTime;

    @ApiProperty(name ="collectionField",desc = "统计字段")
    private String collectionField;

    @ApiProperty(name ="collectionType",desc = "统计类型")
    private String collectionType;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }

    public Integer getDateRanger() {
        return dateRanger;
    }

    public void setDateRanger(Integer dateRanger) {
        this.dateRanger = dateRanger;
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

    public String getCellTime() {
        return cellTime;
    }

    public void setCellTime(String cellTime) {
        this.cellTime = cellTime;
    }

    public String getProjectSetId() {
        return projectSetId;
    }

    public void setProjectSetId(String projectSetId) {
        this.projectSetId = projectSetId;
    }
}
