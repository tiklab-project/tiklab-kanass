package io.tiklab.kanass.project.project.model;

import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.user.user.model.User;

import java.sql.Date;
import java.util.List;


/**
 * 项目模型
 * @pi.model: Project
 */
@ApiModel
@Mapper
@Join
public class Project extends BaseModel{

    /**
     * @pi.name: id
     * @pi.value: projectId
     */
    @ApiProperty(name="id",desc="id")
    private String id;

    /**
     * @pi.name: projectKey
     * @pi.value: 项目key
     */
    @ApiProperty(name = "projectKey",desc = "项目key")
    private String projectKey;

    /**
     * @pi.name: creator
     * @pi.value: 项目创建者
     */
    @ApiProperty(name = "creator",desc = "项目创建者")
    private String creator;

    /**
     * @pi.name: projectLimits
     * @pi.value: 0 1
     */
    @ApiProperty(name = "projectLimits",desc = "项目可见范围")
    private String projectLimits;

    /**
     * @pi.name: projectName
     * @pi.value: 项目名称
     */
    @ApiProperty(name="projectName",desc = "项目名称",eg="@text32")
    public java.lang.String projectName;

    /**
     * @pi.model: ProjectType
     */
    @ApiProperty(name="projectType",desc = "项目类型")
    @Mappings({
            @Mapping(source = "projectType.id",target = "projectTypeId")
    })
    @JoinField(key = "id")
    private ProjectType projectType;

    /**
     * @pi.model: User
     */
    @ApiProperty(name="master",desc="负责人")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinField(key = "id")
    private User master;

    /**
     * @pi.name: desc
     * @pi.value: 项目描述
     */
    @ApiProperty(name="desc",desc = "项目描述",eg="@text32")
    private java.lang.String desc;

    /**
     * @pi.name: projectSetId
     * @pi.value: projectSetId
     */
    @ApiProperty(name = "projectSetId",desc = "项目集")
    private java.lang.String projectSetId;

    /**
     * @pi.name: productId
     * @pi.value: productId
     */
    @ApiProperty(name = "productId",desc = "产品")
    private java.lang.String productId;

    /**
     * @pi.name: startTime
     * @pi.value: 2023-01-01 12:00:00
     */
    @ApiProperty(name="startTime",desc="开始时间")
    private Date startTime;

    /**
     * @pi.name: endTime
     * @pi.value: 2023-01-01 12:00:00
     */
    @ApiProperty(name="endTime",desc="结束时间")
    private Date endTime;

    @ApiProperty(name="createTime",desc="创建时间")
    private Date createTime;

    @ApiProperty(name="updateTime",desc="更新时间")
    private Date updateTime;

    /**
     * @pi.name: projectState
     * @pi.value: 1: 未完成   2:进行中   3：已结束
     */
    @ApiProperty(name="projectState",desc="项目状态")
    private java.lang.String projectState;

    @ApiProperty(name="percent",desc="完成率")
    private float percent;

    @ApiProperty(name="workItemNumber",desc="事项数量")
    private int workItemNumber;

    @ApiProperty(name="endWorkItemNumber",desc="完成数量")
    private int endWorkItemNumber;

    @ApiProperty(name="processWorkItemNumber",desc="进行中数量")
    private int processWorkItemNumber;

    @ApiProperty(name="member",desc="成员")
    private int member;

    @ApiProperty(name="sprintList",desc="迭代")
    private List<Sprint> sprintList;

    @ApiProperty(name="iconUrl",desc="项目类型icon")
    private java.lang.String iconUrl;

    @ApiProperty(name="estimateTime",desc="计划工时")
    private Integer estimateTime;

    @ApiProperty(name="surplusTime",desc="剩余工时")
    private Integer surplusTime;

    @ApiProperty(name="color",desc="项目颜色")
    private int color;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getProjectLimits() {
        return projectLimits;
    }

    public void setProjectLimits(String projectLimits) {
        this.projectLimits = projectLimits;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getProjectSetId() {
        return projectSetId;
    }

    public void setProjectSetId(String projectSetId) {
        this.projectSetId = projectSetId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getProjectState() {
        return projectState;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public int getWorkItemNumber() {
        return workItemNumber;
    }

    public void setWorkItemNumber(int workItemNumber) {
        this.workItemNumber = workItemNumber;
    }

    public int getEndWorkItemNumber() {
        return endWorkItemNumber;
    }

    public void setEndWorkItemNumber(int endWorkItemNumber) {
        this.endWorkItemNumber = endWorkItemNumber;
    }

    public int getProcessWorkItemNumber() {
        return processWorkItemNumber;
    }

    public void setProcessWorkItemNumber(int processWorkItemNumber) {
        this.processWorkItemNumber = processWorkItemNumber;
    }

    public int getMember() {
        return member;
    }

    public void setMember(int member) {
        this.member = member;
    }

    public List<Sprint> getSprintList() {
        return sprintList;
    }

    public void setSprintList(List<Sprint> sprintList) {
        this.sprintList = sprintList;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(Integer estimateTime) {
        this.estimateTime = estimateTime;
    }

    public Integer getSurplusTime() {
        return surplusTime;
    }

    public void setSurplusTime(Integer surplusTime) {
        this.surplusTime = surplusTime;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
