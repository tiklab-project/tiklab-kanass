package io.tiklab.kanass.workitem.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.form.field.model.SelectItem;
import io.tiklab.kanass.project.stage.model.Stage;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.flow.statenode.model.StateNode;
import io.tiklab.flow.statenode.model.StateNodeFlow;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.kanass.project.module.model.Module;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.version.model.ProjectVersion;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.user.user.model.User;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 事项模型
 */
@ApiModel
@Mapper
@Join
public class WorkItem extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private String id;

    @ApiProperty(name = "code",desc = "编码")
    private String code;

    @ApiProperty(name="orderNum",desc="序号")
    private Integer orderNum;

    @ApiProperty(name="transitionId",desc="转换id")
    private String transitionId;

    @NotNull
    @ApiProperty(name="title",desc="标题",required = true)
    private java.lang.String title;

    @ApiProperty(name="desc",desc="描述")
    private java.lang.String desc;

    @ApiProperty(name="parentWorkItem",desc="上级事项")
    @Mappings({
            @Mapping(source = "parentWorkItem.id",target = "parentId")
    })
    @JoinQuery(key = "id")
    private WorkItem parentWorkItem;

    @ApiProperty(name="parentId",desc="上级事项id")
    private String parentId;

    @ApiProperty(name="preDependWorkItem",desc="前置依赖事项")
    @Mappings({
            @Mapping(source = "preDependWorkItem.id",target = "preDependId")
    })
    @JoinQuery(key = "id")
    private WorkItem preDependWorkItem;

    @ApiProperty(name="treePath",desc="所有上级事项按层级排序")
    private String treePath;

    @NotNull
    @ApiProperty(name="project",desc="所属项目",required = true)
    @Mappings({
            @Mapping(source = "project.id",target = "projectId")
    })
    @JoinQuery(key = "id")
    private Project project;

    @NotNull
    @ApiProperty(name="workType",desc="所属的项目事项类型")
    @Mappings({
            @Mapping(source = "workType.id",target = "workTypeId")
    })
    @JoinQuery(key = "id")
    private WorkTypeDm workType;


    @ApiProperty(name="workTypeSys",desc="所属系统的事项类型")
    @Mappings({
            @Mapping(source = "workTypeSys.id",target = "workTypeSysId")
    })
    @JoinQuery(key = "id")
    private WorkType workTypeSys;

    @ApiProperty(name="workTypeCode",desc="事项状态code, DONE, START, PROGRESS")
    private String workTypeCode;


    @ApiProperty(name="workPriority",desc="优先级")
    @Mappings({
            @Mapping(source = "workPriority.id",target = "workPriorityId")
    })
    @JoinQuery(key = "id")
    private SelectItem workPriority;

    @ApiProperty(name="workStatus",desc="事项所属项目的状态id")
    @Mappings({
            @Mapping(source = "workStatus.id",target = "workStatusId")
    })
    @JoinQuery(key = "id")
    private StateNodeFlow workStatus;

    @ApiProperty(name="workStatusCode",desc="事项状态编码")
    private String workStatusCode;


    @ApiProperty(name="workStatusNode",desc="所属系统事项状态id")
    @Mappings({
            @Mapping(source = "workStatusNode.id",target = "workStatusNodeId")
    })
    @JoinQuery(key = "id")
    private StateNode workStatusNode;

    @ApiProperty(name="module",desc="所属模块")
    @Mappings({
            @Mapping(source = "module.id",target = "moduleId")
    })
    @JoinQuery(key = "id")
    private Module module;

    @ApiProperty(name="sprint",desc="所属迭代")
    @Mappings({
            @Mapping(source = "sprint.id",target = "sprintId")
    })
    @JoinQuery(key = "id")
    private Sprint sprint;

    @ApiProperty(name="stage",desc="所属计划")
    @Mappings({
            @Mapping(source = "stage.id",target = "stageId")
    })
    @JoinQuery(key = "id")
    private Stage stage;

    @ApiProperty(name="sprintList",desc="分配过的迭代")
    private List<Sprint> sprintList;

    @ApiProperty(name="projectVersionList",desc="分配过的版本")
    private List<ProjectVersion> projectVersionList;

    @ApiProperty(name="version",desc="所属版本")
    @Mappings({
            @Mapping(source = "projectVersion.id",target = "versionId")
    })
    @JoinQuery(key = "id")
    private ProjectVersion projectVersion;

    @ApiProperty(name="builder",desc="创建人")
    @Mappings({
            @Mapping(source = "builder.id",target = "builderId")
    })
    @JoinQuery(key = "id")
    private User builder;

    @ApiProperty(name="assigner",desc="经办人")
    @Mappings({
            @Mapping(source = "assigner.id",target = "assignerId")
    })
    @JoinQuery(key = "id")
    private User assigner;

    @ApiProperty(name="reporter",desc="报告人")
    @Mappings({
            @Mapping(source = "reporter.id",target = "reporterId")
    })
    @JoinQuery(key = "id")
    private User reporter;

    @ApiProperty(name="children",desc="下级事项列表")
    private List<WorkItem> children;

    @ApiProperty(name="planBeginTime",desc="计划开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private String planBeginTime;

    @ApiProperty(name="planEndTime",desc="计划结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private String planEndTime;

    @ApiProperty(name="actualBeginTime",desc="实际开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private String actualBeginTime;

    @ApiProperty(name="actualEndTime",desc="实际结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd  hh:mm:ss")
    private String actualEndTime;

    @ApiProperty(name="buildTime",desc="事项创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String buildTime;

    @ApiProperty(name="updateTime",desc="事项更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;

    @ApiProperty(name="percent",desc="完成率")
    private Integer percent;

    @ApiProperty(name="estimateTime",desc="计划用时")
    private Integer estimateTime;

    @ApiProperty(name="surplusTime",desc="剩余用时")
    private Integer surplusTime;

    @ApiProperty(name="usedTime",desc="已用时")
    private Integer usedTime;


    @ApiProperty(name="eachType",desc="eg: 需求类型、任务类型、缺陷类型")
    private String eachType;

    @ApiProperty(name="fieldId",desc="eg: 类型的字段id")
    private String fieldId;

    @ApiProperty(name="extData",desc="扩展数据")
    private String extData;

    @ApiProperty(name="flowId",desc="流程id")
    private String flowId;

    @ApiProperty(name="updateField",desc="更新字段")
    private String updateField;

    @ApiProperty(name="rootId",desc="根节点")
    private String rootId;

    @ApiProperty(
            name = "isRele",
            desc = "是否关联事项"
    )
    private Boolean isRele;


    public String getId() {
        return id;
    }

    public WorkItem setId(String id) {
        this.id = id;
        return this;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public WorkTypeDm getWorkType() {
        return workType;
    }

    public void setWorkType(WorkTypeDm workType) {
        this.workType = workType;
    }



    public StateNodeFlow getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(StateNodeFlow workStatus) {
        this.workStatus = workStatus;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public User getBuilder() {
        return builder;
    }

    public void setBuilder(User builder) {
        this.builder = builder;
    }

    public User getAssigner() {
        return assigner;
    }

    public void setAssigner(User assigner) {
        this.assigner = assigner;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public WorkItem getParentWorkItem() {
        return parentWorkItem;
    }

    public void setParentWorkItem(WorkItem parentWorkItem) {
        this.parentWorkItem = parentWorkItem;
    }


    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<WorkItem> getChildren() {
        return children;
    }

    public void setChildren(List<WorkItem> children) {
        this.children = children;
    }


    public String getPlanBeginTime() {
        return planBeginTime;
    }

    public void setPlanBeginTime(String planBeginTime) {
        this.planBeginTime = planBeginTime;
    }

    public String getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(String planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getActualBeginTime() {
        return actualBeginTime;
    }

    public void setActualBeginTime(String actualBeginTime) {
        this.actualBeginTime = actualBeginTime;
    }

    public String getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(String actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Integer getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(Integer estimateTime) {
        this.estimateTime = estimateTime;
    }

    public WorkItem getPreDependWorkItem() {
        return preDependWorkItem;
    }

    public void setPreDependWorkItem(WorkItem preDependWorkItem) {
        this.preDependWorkItem = preDependWorkItem;
    }

    public ProjectVersion getProjectVersion() {
        return projectVersion;
    }

    public void setProjectVersion(ProjectVersion projectVersion) {
        this.projectVersion = projectVersion;
    }

    public String getExtData() {
        return extData;
    }

    public void setExtData(String extData) {
        this.extData = extData;
    }

    public Boolean getRele() {
        return isRele;
    }

    public void setRele(Boolean rele) {
        isRele = rele;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public String getUpdateField() {
        return updateField;
    }

    public void setUpdateField(String updateField) {
        this.updateField = updateField;
    }

    public String getRootId() {
        return rootId;
    }

    public void setRootId(String rootId) {
        this.rootId = rootId;
    }

    public String getWorkStatusCode() {
        return workStatusCode;
    }

    public void setWorkStatusCode(String workStatusCode) {
        this.workStatusCode = workStatusCode;
    }

    public String getWorkTypeCode() {
        return workTypeCode;
    }

    public void setWorkTypeCode(String workTypeCode) {
        this.workTypeCode = workTypeCode;
    }

    public Integer getSurplusTime() {
        return surplusTime;
    }

    public void setSurplusTime(Integer surplusTime) {
        this.surplusTime = surplusTime;
    }

    public WorkType getWorkTypeSys() {
        return workTypeSys;
    }

    public void setWorkTypeSys(WorkType workTypeSys) {
        this.workTypeSys = workTypeSys;
    }

    public StateNode getWorkStatusNode() {
        return workStatusNode;
    }

    public void setWorkStatusNode(StateNode workStatusNode) {
        this.workStatusNode = workStatusNode;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getEachType() {
        return eachType;
    }

    public void setEachType(String eachType) {
        this.eachType = eachType;
    }

    public String getTransitionId() {
        return transitionId;
    }

    public void setTransitionId(String transitionId) {
        this.transitionId = transitionId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<Sprint> getSprintList() {
        return sprintList;
    }

    public void setSprintList(List<Sprint> sprintList) {
        this.sprintList = sprintList;
    }

    public List<ProjectVersion> getProjectVersionList() {
        return projectVersionList;
    }

    public void setProjectVersionList(List<ProjectVersion> projectVersionList) {
        this.projectVersionList = projectVersionList;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public SelectItem getWorkPriority() {
        return workPriority;
    }

    public void setWorkPriority(SelectItem workPriority) {
        this.workPriority = workPriority;
    }

    public Integer getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Integer usedTime) {
        this.usedTime = usedTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
