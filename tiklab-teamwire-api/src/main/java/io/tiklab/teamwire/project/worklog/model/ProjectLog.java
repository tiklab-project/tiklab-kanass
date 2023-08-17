package io.tiklab.teamwire.project.worklog.model;

import io.tiklab.join.annotation.Join;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.user.user.model.User;

import java.util.List;

/**
 * 项目成员日志统计列表模型
 */
@ApiModel
@Join
public class ProjectLog {
    @ApiProperty(name = "userId", desc = "成员id")
    private String userId;

    @ApiProperty(name = "user", desc = "成员")
    private User user;

    @ApiProperty(name = "projectId", desc = "项目id")
    private String projectId;

    @ApiProperty(name = "project", desc = "项目")
    public Project project;

    @ApiProperty(name = "total", desc = "统计")
    private Integer total;

    @ApiProperty(name = "ProjectListLogList", desc = "项目成员统计数组")
    private List<ProjectListLog> projectListLogList;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<ProjectListLog> getProjectListLogList() {
        return projectListLogList;
    }

    public void setProjectListLogList(List<ProjectListLog> projectListLogList) {
        this.projectListLogList = projectListLogList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


}
