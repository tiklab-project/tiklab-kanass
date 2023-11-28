package io.tiklab.teamwire.home.statistic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.user.user.model.User;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 统计报表模型
 */
@ApiModel
@Join
@Mapper
public class Report extends BaseModel{

    @ApiProperty(name="id",desc="id",required = true)
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="title",desc="报表标题",required = true)
    private java.lang.String title;

    @ApiProperty(name="programId",desc="报表所属项目集id")
    private String programId;

    @ApiProperty(name="projectId",desc="报表所属项目id")
    private java.lang.String projectId;

    @ApiProperty(name ="sprintId",desc = "报表所属迭代id")
    private String sprintId;

    @ApiProperty(name="creatTime",desc="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp creatTime;

    @ApiProperty(name="creater",desc="创建人")
    @Mappings({
            @Mapping(source = "creater.id",target = "createrId")
    })
    @JoinQuery(key = "id")
    private User creater;


    @ApiProperty(name="reportType",desc="报表类型")
    private java.lang.String reportType;


    @ApiProperty(name="type",desc="报表分类, (work,log)")
    private java.lang.String type;


    @ApiProperty(name="reportData",desc="报表统计具体数据")
    private java.lang.String reportData;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public java.lang.String getReportType() {
        return reportType;
    }

    public void setReportType(java.lang.String reportType) {
        this.reportType = reportType;
    }

    public String getReportData() {
        return reportData;
    }

    public void setReportData(String reportData) {
        this.reportData = reportData;
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }

    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }
}
