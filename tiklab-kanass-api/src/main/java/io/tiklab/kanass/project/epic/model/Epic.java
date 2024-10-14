package io.tiklab.kanass.project.epic.model;

import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;
import io.tiklab.user.user.model.User;

import java.util.List;

/**
 * 史诗模型
 */
@ApiModel
@Mapper
@Join
public class Epic extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="epicName",desc="史诗名称")
    private java.lang.String epicName;

    @ApiProperty(name="parentEpic",desc="父级史诗")
    @Mappings({
            @Mapping(source = "parentEpic.id",target = "parentId")
    })
    @JoinQuery(key = "id")
    private Epic parentEpic;

    @ApiProperty(name="children",desc="下级史诗列表")
    private List<Epic> children;

    @ApiProperty(name="childrenWorkItem",desc="下级史诗列表")
    private List<WorkItem> childrenWorkItem;

    @ApiProperty(name = "progress",desc="进度")
    private Integer progress = 0;

    @ApiProperty(name = "status",desc="状态")
    private String status = "0";

    @ApiProperty(name="desc",desc="desc")
    private String desc;

    @ApiProperty(name="master",desc="负责人")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinQuery(key = "id")
    private User master;

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
    public java.lang.String getEpicName() {
        return epicName;
    }

    public void setEpicName(java.lang.String epicName) {
        this.epicName = epicName;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public java.lang.String getDesc() {
        return desc;
    }

    public void setDesc(java.lang.String desc) {
        this.desc = desc;
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

    public Epic getParentEpic() {
        return parentEpic;
    }

    public void setParentEpic(Epic parentEpic) {
        this.parentEpic = parentEpic;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Epic> getChildren() {
        return children;
    }

    public void setChildren(List<Epic> children) {
        this.children = children;
    }

    public List<WorkItem> getChildrenWorkItem() {
        return childrenWorkItem;
    }

    public void setChildrenWorkItem(List<WorkItem> childrenWorkItem) {
        this.childrenWorkItem = childrenWorkItem;
    }
}
