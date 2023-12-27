package io.thoughtware.kanass.project.stage.model;

import io.thoughtware.kanass.project.project.model.Project;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.user.user.model.User;

import java.util.List;

/**
 * 项目阶段模型
 */
@ApiModel
@Mapper
@Join
public class Stage extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="stageName",desc="阶段名称")
    private java.lang.String stageName;

    @ApiProperty(name="parentStage",desc="父级阶段")
    @Mappings({
            @Mapping(source = "parentStage.id",target = "parentId")
    })
    @JoinQuery(key = "id")
    private Stage parentStage;

    @ApiProperty(name="status",desc="状态")
    private java.lang.String status = "0";

    @ApiProperty(name="children",desc="下级阶段列表")
    private List<Stage> children;

    @ApiProperty(name="childrenWorkItem",desc="阶段关联事项列表")
    private List<WorkItem> childrenWorkItem;

    @ApiProperty(name="progress",desc="进度，百分比")
    private java.lang.Integer progress = 0;

    @ApiProperty(name="master",desc="负责人")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinQuery(key = "id")
    private User master;

    @ApiProperty(name="desc",desc="desc")
    private java.lang.String desc;

    @ApiProperty(name="project",desc="所属项目",eg="@selectOne")
    @Mappings({
            @Mapping(source = "project.id",target = "projectId")
    })

    @JoinQuery(key = "id")
    private Project project;

    @ApiProperty(name="startTime",desc="startTime")
    private java.util.Date startTime;

    @ApiProperty(name="endTime",desc="endTime")
    private java.util.Date endTime;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getStageName() {
        return stageName;
    }

    public void setStageName(java.lang.String stageName) {
        this.stageName = stageName;
    }
    public java.lang.String getStatus() {
        return status;
    }

    public void setStatus(java.lang.String status) {
        this.status = status;
    }
    public java.lang.Integer getProgress() {
        return progress;
    }

    public void setProgress(java.lang.Integer progress) {
        this.progress = progress;
    }

    public Stage getParentStage() {
        return parentStage;
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public java.lang.String getDesc() {
        return desc;
    }

    public void setDesc(java.lang.String desc) {
        this.desc = desc;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public java.util.Date getStartTime() {
        return startTime;
    }

    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }
    public java.util.Date getEndTime() {
        return endTime;
    }

    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    public List<Stage> getChildren() {
        return children;
    }

    public void setChildren(List<Stage> children) {
        this.children = children;
    }

    public List<WorkItem> getChildrenWorkItem() {
        return childrenWorkItem;
    }

    public void setChildrenWorkItem(List<WorkItem> childrenWorkItem) {
        this.childrenWorkItem = childrenWorkItem;
    }
}
