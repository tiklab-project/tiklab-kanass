package io.tiklab.teamwire.project.project.model;

import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.teamwire.sprint.model.Sprint;
import io.tiklab.user.user.model.User;

import javax.validation.constraints.NotNull;
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
//    public interface Insert{}
//
//    public interface Update{}

//    @ApiProperty(name="id",desc = "项目ID")
//    @NotNull(groups = {Update.class})
//    @IndexField
//    @IndexId
//    private String id;

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
    @JoinQuery(key = "id")
    private ProjectType projectType;

    /**
     * @pi.model: User
     */
    @ApiProperty(name="master",desc="负责人")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinQuery(key = "id")
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

    /**
     * @pi.name: projectState
     * @pi.value: 1: 未完成
     */
    @ApiProperty(name="projectState",desc="项目状态")
    private java.lang.String projectState;

    @ApiProperty(name="percent",desc="完成率")
    private Integer percent;

    @ApiProperty(name="worklItemNumber",desc="事项数量")
    private Integer worklItemNumber;

    @ApiProperty(name="quantityNumber",desc="完成数量")
    private Integer quantityNumber;

    @ApiProperty(name="member",desc="成员")
    private Integer member;

    @ApiProperty(name="sprintList",desc="迭代")
    private List<Sprint> sprintList;

    @NotNull
    @ApiProperty(name="iconUrl",desc="项目类型icon",required = true)
    private java.lang.String iconUrl;

    public java.lang.String getId() {
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

    public java.lang.String getProjectName() {
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

    public String getProjectSetId() {
        return projectSetId;
    }

    public void setProjectSetId(String projectSetId) {
        this.projectSetId = projectSetId;
    }

    public java.lang.String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Integer getWorklItemNumber() {
        return worklItemNumber;
    }

    public void setWorklItemNumber(Integer worklItemNumber) {
        this.worklItemNumber = worklItemNumber;
    }

    public Integer getQuantityNumber() {
        return quantityNumber;
    }

    public void setQuantityNumber(Integer quantityNumber) {
        this.quantityNumber = quantityNumber;
    }

    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }

    public List<Sprint> getSprintList() {
        return sprintList;
    }

    public void setSprintList(List<Sprint> sprintList) {
        this.sprintList = sprintList;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getProjectLimits() {
        return projectLimits;
    }

    public void setProjectLimits(String projectLimits) {
        this.projectLimits = projectLimits;
    }
}
