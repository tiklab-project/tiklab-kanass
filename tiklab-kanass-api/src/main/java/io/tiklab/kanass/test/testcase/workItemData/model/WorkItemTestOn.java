package io.tiklab.kanass.test.testcase.workItemData.model;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.sql.Date;

/**
 * postinUrl配置 模型
 */
@ApiModel
public class WorkItemTestOn extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="projectName",desc="项目名称")
    private String name;

    @ApiProperty(name="projectId",desc="项目名称")
    private String projectId;

    @ApiProperty(name = "planBeginTime", desc = "计划开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date planBeginTime;

    @ApiProperty(name = "planEndTime", desc = "计划结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date planEndTime;

    @ApiProperty(name="caseId",desc="获取当前用例id")
    private String caseId;

    @ApiProperty(name="projectName",desc="所属项目")
    private String projectName;

    @ApiProperty(name="director",desc="负责人")
    private String director;

    @ApiProperty(name="status",desc="状态")
    private String status;

    @ApiProperty(name="priority",desc="优先级")
    private String priority;

    @ApiProperty(name="projectUrl",desc="teamwire服务端地址")
    private String projectUrl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getPlanBeginTime() {
        return planBeginTime;
    }

    public void setPlanBeginTime(Date planBeginTime) {
        this.planBeginTime = planBeginTime;
    }

    public Date getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
