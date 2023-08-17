package io.tiklab.teamwire.project.worklog.model;

import io.tiklab.teamwire.workitem.model.WorkItem;
import io.tiklab.join.annotation.Join;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.user.user.model.User;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 项目成员日志统计模型
 */
@ApiModel
@Join
public class ProjectListLog {


    @ApiProperty(name="project",desc="所属项目")
    private Project project;

    @ApiProperty(name="projectId",desc="所属项目")
    private String projectId;

    @ApiProperty(name="User",desc="成员")
    private User user;

    @NotNull
    @ApiProperty(name="userId",desc="成员Id")
    private String userId;

    @ApiProperty(name="workItemId",desc="事项id")
    private String workItemId;

    @NotNull
    @ApiProperty(name="workItem",desc="事项")
    private WorkItem workItem;

    @ApiProperty(name = "statisticsList", desc = "统计数组")
    private List<EneryDayTotal> statisticsList;

    @ApiProperty(name = "projectLogTotal", desc = "项目日志总工时")
    private Integer projectLogTotal;

    @ApiProperty(name = "pageLogTotal", desc = "本页工时统计")
    private Integer pageLogTotal;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<EneryDayTotal> getStatisticsList() {
        return statisticsList;
    }

    public void setStatisticsList(List<EneryDayTotal> statisticsList) {
        this.statisticsList = statisticsList;
    }

    public Integer getProjectLogTotal() {
        return projectLogTotal;
    }

    public void setProjectLogTotal(Integer projectLogTotal) {
        this.projectLogTotal = projectLogTotal;
    }

    public Integer getPageLogTotal() {
        return pageLogTotal;
    }

    public void setPageLogTotal(Integer pageLogTotal) {
        this.pageLogTotal = pageLogTotal;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWorkItemId() {
        return workItemId;
    }

    public void setWorkItemId(String workItemId) {
        this.workItemId = workItemId;
    }

    public WorkItem getWorkItem() {
        return workItem;
    }

    public void setWorkItem(WorkItem workItem) {
        this.workItem = workItem;
    }
}
