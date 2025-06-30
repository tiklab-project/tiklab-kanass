package io.tiklab.kanass.project.worklog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.user.user.model.User;

import java.sql.Timestamp;

/**
 * 事项日志模型
 */
@ApiModel
@Join
@Mapper
public class WorkLog extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private java.lang.String id;


    @ApiProperty(name="workItem",desc="所属事项")
    @Mappings({
            @Mapping(source = "workItem.id",target = "workItemId")
    })
    @JoinField(key = "id")
    private WorkItem workItem;


    @ApiProperty(name="project",desc="所属项目")
    @Mappings({
            @Mapping(source = "project.id",target = "projectId")
    })
    @JoinField(key = "id")
    private Project project;

    @ApiProperty(name="user",desc="记录人")
    @Mappings({
            @Mapping(source = "user.id",target = "worker")
    })
    @JoinField(key = "id")
    private User user;

    @ApiProperty(name="workDate",desc="日志记录日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp workDate;


    @ApiProperty(name="takeupTime",desc="用时，以小时为单位")
    private java.lang.Integer takeupTime;


    @ApiProperty(name="workContent",desc="工作内容")
    private java.lang.String workContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WorkItem getWorkItem() {
        return workItem;
    }

    public void setWorkItem(WorkItem workItem) {
        this.workItem = workItem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTakeupTime() {
        return takeupTime;
    }

    public void setTakeupTime(Integer takeupTime) {
        this.takeupTime = takeupTime;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Timestamp getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Timestamp workDate) {
        this.workDate = workDate;
    }
}
