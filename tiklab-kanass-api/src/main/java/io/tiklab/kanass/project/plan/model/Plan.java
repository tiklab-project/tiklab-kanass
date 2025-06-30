package io.tiklab.kanass.project.plan.model;

import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.user.user.model.User;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 计划模型
 */
@ApiModel
@Join
@Mapper
public class Plan extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="planName",desc="计划名称",required = true)
    private java.lang.String planName;

    @NotNull
    @ApiProperty(name="project",desc="所属项目",eg="@selectOne")
    @Mappings({
            @Mapping(source = "project.id",target = "projectId")
    })
    @JoinField(key = "id")
    private Project project;

    @ApiProperty(name="parentPlan",desc="上级计划")
    @Mappings({
            @Mapping(source = "parentPlan.id",target = "parentId")
    })
    @JoinField(key = "id")
    private Plan parentPlan;

    @ApiProperty(name="startTime",desc="计划开始时间")
    private java.sql.Date startTime;

    @ApiProperty(name="endTime",desc="计划结束时间")
    private java.sql.Date endTime;

    @ApiProperty(name="master",desc="计划负责人")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinField(key = "id")
    private User master;

    @ApiProperty(name="children",desc="下级计划列表")
    private List<Plan> children;

    @ApiProperty(name="planState",desc="计划状态")
    private java.lang.String  planState;

    @ApiProperty(name="workItemList",desc="下级事项列表")
    private List<WorkItem> workItemList;

    @ApiProperty(name="desc",desc="计划描述")
    private java.lang.String desc;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getPlanName() {
        return planName;
    }

    public void setPlanName(java.lang.String planName) {
        this.planName = planName;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Plan getParentPlan() {
        return parentPlan;
    }

    public void setParentPlan(Plan parentPlan) {
        this.parentPlan = parentPlan;
    }

    public java.sql.Date getStartTime() {
        return startTime;
    }

    public void setStartTime(java.sql.Date startTime) {
        this.startTime = startTime;
    }
    public java.sql.Date getEndTime() {
        return endTime;
    }

    public void setEndTime(java.sql.Date endTime) {
        this.endTime = endTime;
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

    public List<Plan> getChildren() {
        return children;
    }

    public void setChildren(List<Plan> children) {
        this.children = children;
    }

    public List<WorkItem> getWorkItemList() {
        return workItemList;
    }

    public void setWorkItemList(List<WorkItem> workItemList) {
        this.workItemList = workItemList;
    }

    public String getPlanState() {
        return planState;
    }

    public void setPlanState(String planState) {
        this.planState = planState;
    }
}
