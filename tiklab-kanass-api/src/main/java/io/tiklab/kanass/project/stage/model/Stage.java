package io.tiklab.kanass.project.stage.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.user.user.model.User;

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

    @ApiProperty(name="treePath",desc="所有上级事项按层级排序")
    private String treePath;

    private int color;

    @ApiProperty(name="rootId",desc="所有根节点")
    private String rootId;

    @ApiProperty(name="deep",desc="深度")
    private Integer deep;

    @ApiProperty(name="isChangeParent",desc="是否修改上级")
    private boolean isChangeParent;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String startTime;

    @ApiProperty(name="endTime",desc="endTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    public String getRootId() {
        return rootId;
    }

    public void setRootId(String rootId) {
        this.rootId = rootId;
    }

    public Integer getDeep() {
        return deep;
    }

    public void setDeep(Integer deep) {
        this.deep = deep;
    }

    public boolean getIsChangeParent() {
        return isChangeParent;
    }

    public void setChangeParent(boolean changeParent) {
        isChangeParent = changeParent;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
