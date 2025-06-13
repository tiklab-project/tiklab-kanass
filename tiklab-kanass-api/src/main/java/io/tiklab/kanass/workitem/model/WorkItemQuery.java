package io.tiklab.kanass.workitem.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.core.page.Page;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.toolkit.join.annotation.Join;

import java.io.Serializable;
import java.util.List;
/**
 * 事项查找条件模型
 * @pi.model: WorkItemQuery
 */
@ApiModel
@Join
public class WorkItemQuery implements Serializable {

    /**
     * @pi.name: id
     * @pi.value: String
     */
    @ApiProperty(name ="id",desc = "事项ID，精确匹配")
    private String id;

    @ApiProperty(name ="ids",desc = "事项ID，精确匹配")
    private String[] ids;

    @ApiProperty(name ="statisticsNum",desc = "统计数量")
    private Boolean statisticsNum;


    @ApiProperty(name ="likeId",desc = "事项ID，精确匹配")
    private String likeId;


    @ApiProperty(name ="epicView",desc = "是否史诗视图，精确匹配")
    private Boolean epicView;

    /**
     * @pi.name: overdue
     * @pi.value: 是否逾期
     */
    @ApiProperty(name ="overdue",desc = "是否逾期")
    private Boolean overdue;

    @ApiProperty(name ="workStatus",desc = "事项状态 0待办  1进行中  2已完成")
    private Integer workStatus;

    @ApiProperty(name ="leftJoinPlanWorkItemEntity",desc = "文档id，精确匹配")
    private String leftJoinPlanWorkItemEntity;

    @ApiProperty(name ="planId",desc = "计划id")
    private String planId;

    @ApiProperty(name ="keyWord",desc = "计划id")
    private String keyWord;

    @ApiProperty(name ="planIdIn",desc = "计划ID in范围，范围匹配")
    private String[] planIdIn;

    @ApiProperty(name ="planIdIn",desc = "左链接事项")
    private String leftJoinWorkItemEntity;

    @ApiProperty(name ="epicId",desc = "史诗id")
    private String epicId;

    @ApiProperty(name ="idNotIn",desc = "idNotIn，范围匹配")
    private String[] idNotIn;

    @ApiProperty(name ="parentIdIsNull",desc = "parentId是否为空,true:为空")
    private Boolean parentIdIsNull;

    @ApiProperty(name ="parentId",desc = "父事项ID，精确匹配")
    private String parentId;

    @ApiProperty(name ="parentIdIn",desc = "父事项ID in范围，范围匹配")
    private String[] parentIdIn;

    @ApiProperty(name ="projectId",desc = "所属项目ID，精确匹配")
    private String projectId;

    @ApiProperty(name ="projectIds",desc = "所属项目ID，in")
    private List<String> projectIds;

    @ApiProperty(name ="currentSprintId",desc = "所属当前迭代ID，精确匹配")
    private String currentSprintId;

    @ApiProperty(name ="currentSprintIds",desc = "所属当前迭代IDs，范围匹配")
    private List<String> currentSprintIds;

    @ApiProperty(name = "sprintId",desc = "迭代ID，范围匹配")
    private String sprintId;

    @ApiProperty(name ="neqSprintId",desc = "不在当前迭代")
    private String neqSprintId;

    @ApiProperty(name = "stageId",desc = "计划ID，范围匹配")
    private String stageId;

    @ApiProperty(name = "stageIds",desc = "计划ID，范围匹配")
    private String[] stageIds;

    @ApiProperty(name ="neqStageId",desc = "不在当前计划")
    private String neqStageId;

    @ApiProperty(name ="builderId",desc = "创建者ID，精确匹配")
    private String builderId;

    @ApiProperty(name ="builderIds",desc = "创建者ID，范围匹配")
    private List<String> builderIds;

    @ApiProperty(name ="assignerId",desc = "负责人id，精确匹配")
    private String assignerId;

    @ApiProperty(name ="assignerIds",desc = "负责人ids，范围匹配")
    private List<String> assignerIds;

    @ApiProperty(name ="userId",desc = "创建人ID，精确匹配")
    private String userId;

    @ApiProperty(name ="reporterId",desc = "报告人ID，精确匹配")
    private String reporterId;

    @ApiProperty(name ="sprintIdIsNull",desc = "迭代是否为空,true:为空")
    private Boolean sprintIdIsNull;


    @ApiProperty(name ="currentVersionId",desc = "解决版本ID，精确匹配")
    private String currentVersionId;

    @ApiProperty(name ="currentVersionIds",desc = "解决版本IDs，精确匹配")
    private List<String> currentVersionIds;

    @ApiProperty(name ="versionId",desc = "解决版本ID，精确匹配")
    private String versionId;

    @ApiProperty(name ="neqVersionId",desc = "不在当前版本")
    private String neqVersionId;

    @ApiProperty(name ="versionIdIsNull",desc = "versionId是否为空,true:为空")
    private Boolean versionIdIsNull;

    @ApiProperty(name ="workTypeId",desc = "事项类型ID，精确匹配")
    private String workTypeId;

    @ApiProperty(name ="workTypeSysId",desc = "事项类型ID，精确匹配")
    private String workTypeSysId;
    @ApiProperty(name ="workTypeIds",desc = "事项类型IDs，范围匹配")
    private List<String> workTypeIds;

    @ApiProperty(name ="moduleIds",desc = "模块ids,范围匹配")
    private List<String> moduleIds;

    @ApiProperty(name ="updateTimeStart",desc = "搜索创建开始时间")
    private String updateTimeStart;

    @ApiProperty(name ="updateTimeEnd",desc = "搜索创建开始时间")
    private String updateTimeEnd;

    @ApiProperty(name ="buildTimeStart",desc = "搜索创建开始时间")
    private String buildTimeStart;

    @ApiProperty(name ="buildTimeEnd",desc = "统计创建结束时间")
    private String buildTimeEnd;

    @ApiProperty(name ="planStartDateStart",desc = "计划开始搜索起始时间")
    private String planStartDateStart;

    @ApiProperty(name ="planStartDateEnd",desc = "计划开始搜索结束时间")
    private String planStartDateEnd;

    @ApiProperty(name ="planEndDateStart",desc = "计划结束事件搜索起始时间")
    private String planEndDateStart;

    @ApiProperty(name ="planEndDateEnd",desc = "计划结束事件搜索结束时间")
    private String planEndDateEnd;

    @ApiProperty(name ="rootIds",desc = "根节点")
    private List<String> rootIds;

    public String getWorkPriorityId() {
        return workPriorityId;
    }

    public void setWorkPriorityId(String workPriorityId) {
        this.workPriorityId = workPriorityId;
    }

    public List<String> getWorkPriorityIds() {
        return workPriorityIds;
    }

    public void setWorkPriorityIds(List<String> workPriorityIds) {
        this.workPriorityIds = workPriorityIds;
    }

    @ApiProperty(name ="workPriorityId",desc = "事项优先级ID，精确匹配")
    private String workPriorityId;

    @ApiProperty(name ="workPriorityIds",desc = "事项优先级ID，精确匹配")
    private List<String> workPriorityIds;

    @ApiProperty(name ="workTypeCode",desc = "事项类型Code，精确匹配")
    private String workTypeCode;



    @ApiProperty(name ="workTypeCodes",desc = "事项类型Code，精确匹配")
    private List<String> workTypeCodes;

    @ApiProperty(name ="workTypeNoInIds",desc = "事项类型ID，精确匹配")
    private List<String> workTypeNoInIds;

    @ApiProperty(name ="workStatusId",desc = "事项状态ID，精确匹配")
    private String workStatusId;

    @ApiProperty(name ="workStatusCode",desc = "事项状态ID，精确匹配")
    private String workStatusCode;

    @ApiProperty(name ="workStatusCodes",desc = "事项状态编码ids，精确匹配")
    private List<String> workStatusCodes;

    @ApiProperty(name ="workStatusIds",desc = "事项类型ID，精确匹配")
    private List<String> workStatusIds;

    @ApiProperty(name ="workStatusTypeIds",desc = "事项类型ID，精确匹配")
    private List<String> workStatusTypeIds;

    @ApiProperty(name ="title",desc = "标题，模糊匹配")
    private String title;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().desc("code").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    @ApiProperty(name ="treePath",desc = "祖宗节点")
    private String treePath;

    @ApiProperty(name ="rootId",desc = "父事项ID，精确匹配")
    private String rootId;

    @ApiProperty(name ="recent",desc = "最近点击")
    private Boolean recent;
    public String getLeftJoinPlanWorkItemEntity() {
        return leftJoinPlanWorkItemEntity;
    }

    public void setLeftJoinPlanWorkItemEntity(String leftJoinPlanWorkItemEntity) {
        this.leftJoinPlanWorkItemEntity = leftJoinPlanWorkItemEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getEpicView() {
        return epicView;
    }

    public void setEpicView(Boolean epicView) {
        this.epicView = epicView;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String[] getPlanIdIn() {
        return planIdIn;
    }

    public void setPlanIdIn(String[] planIdIn) {
        this.planIdIn = planIdIn;
    }

    public Boolean getSprintIdIsNull() {
        return sprintIdIsNull;
    }

    public void setSprintIdIsNull(Boolean sprintIdIsNull) {
        this.sprintIdIsNull = sprintIdIsNull;
    }

    public List<Order> getOrderParams() {
        return orderParams;
    }

    public void setOrderParams(List<Order> orderParams) {
        this.orderParams = orderParams;
    }

    public Page getPageParam() {
        return pageParam;
    }

    public void setPageParam(Page pageParam) {
        this.pageParam = pageParam;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(String workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String getWorkStatusId() {
        return workStatusId;
    }

    public void setWorkStatusId(String workStatusId) {
        this.workStatusId = workStatusId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrentSprintId() {
        return currentSprintId;
    }

    public void setCurrentSprintId(String currentSprintId) {
        this.currentSprintId = currentSprintId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCurrentVersionId() {
        return currentVersionId;
    }

    public void setCurrentVersionId(String currentVersionId) {
        this.currentVersionId = currentVersionId;
    }

    public Boolean getVersionIdIsNull() {
        return versionIdIsNull;
    }

    public void setVersionIdIsNull(Boolean versionIdIsNull) {
        this.versionIdIsNull = versionIdIsNull;
    }

    public String[] getIdNotIn() {
        return idNotIn;
    }

    public void setIdNotIn(String[] idNotIn) {
        this.idNotIn = idNotIn;
    }

    public String[] getParentIdIn() {
        return parentIdIn;
    }

    public void setParentIdIn(String[] parentIdIn) {
        this.parentIdIn = parentIdIn;
    }

    public Boolean getParentIdIsNull() {
        return parentIdIsNull;
    }

    public void setParentIdIsNull(Boolean parentIdIsNull) {
        this.parentIdIsNull = parentIdIsNull;
    }

    public String getLeftJoinWorkItemEntity() {
        return leftJoinWorkItemEntity;
    }

    public void setLeftJoinWorkItemEntity(String leftJoinWorkItemEntity) {
        this.leftJoinWorkItemEntity = leftJoinWorkItemEntity;
    }

    public String getEpicId() {
        return epicId;
    }

    public WorkItemQuery setEpicId(String epicId) {
        this.epicId = epicId;
        return this;
    }

    public String getBuilderId() {
        return builderId;
    }

    public void setBuilderId(String builderId) {
        this.builderId = builderId;
    }

    public String getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(String assignerId) {
        this.assignerId = assignerId;
    }

    public String getReporterId() {
        return reporterId;
    }

    public void setReporterId(String reporterId) {
        this.reporterId = reporterId;
    }

    public List<String> getWorkTypeIds() {
        return workTypeIds;
    }

    public void setWorkTypeIds(List<String> workTypeIds) {
        this.workTypeIds = workTypeIds;
    }

    public List<String> getWorkStatusIds() {
        return workStatusIds;
    }

    public void setWorkStatusIds(List<String> workStatusIds) {
        this.workStatusIds = workStatusIds;
    }

    public List<String> getCurrentSprintIds() {
        return currentSprintIds;
    }

    public void setCurrentSprintIds(List<String> currentSprintIds) {
        this.currentSprintIds = currentSprintIds;
    }

    public List<String> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<String> projectIds) {
        this.projectIds = projectIds;
    }

    public List<String> getAssignerIds() {
        return assignerIds;
    }

    public void setAssignerIds(List<String> assignerIds) {
        this.assignerIds = assignerIds;
    }

    public List<String> getWorkStatusTypeIds() {
        return workStatusTypeIds;
    }

    public void setWorkStatusTypeIds(List<String> workStatusTypeIds) {
        this.workStatusTypeIds = workStatusTypeIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getWorkTypeCode() {
        return workTypeCode;
    }

    public void setWorkTypeCode(String workTypeCode) {
        this.workTypeCode = workTypeCode;
    }

    public List<String> getWorkTypeCodes() {
        return workTypeCodes;
    }

    public void setWorkTypeCodes(List<String> workTypeCodes) {
        this.workTypeCodes = workTypeCodes;
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

    public Boolean getRecent() {
        return recent;
    }

    public void setRecent(Boolean recent) {
        this.recent = recent;
    }

    public List<String> getWorkTypeNoInIds() {
        return workTypeNoInIds;
    }

    public void setWorkTypeNoInIds(List<String> workTypeNoInIds) {
        this.workTypeNoInIds = workTypeNoInIds;
    }

    public String getWorkStatusCode() {
        return workStatusCode;
    }

    public void setWorkStatusCode(String workStatusCode) {
        this.workStatusCode = workStatusCode;
    }

    public String getBuildTimeEnd() {
        return buildTimeEnd;
    }

    public void setBuildTimeEnd(String buildTimeEnd) {
        this.buildTimeEnd = buildTimeEnd;
    }

    public String getBuildTimeStart() {
        return buildTimeStart;
    }

    public void setBuildTimeStart(String buildTimeStart) {
        this.buildTimeStart = buildTimeStart;
    }

    public String getPlanStartDateStart() {
        return planStartDateStart;
    }

    public void setPlanStartDateStart(String planStartDateStart) {
        this.planStartDateStart = planStartDateStart;
    }

    public String getPlanStartDateEnd() {
        return planStartDateEnd;
    }

    public void setPlanStartDateEnd(String planStartDateEnd) {
        this.planStartDateEnd = planStartDateEnd;
    }

    public String getPlanEndDateStart() {
        return planEndDateStart;
    }

    public void setPlanEndDateStart(String planEndDateStart) {
        this.planEndDateStart = planEndDateStart;
    }

    public String getPlanEndDateEnd() {
        return planEndDateEnd;
    }

    public void setPlanEndDateEnd(String planEndDateEnd) {
        this.planEndDateEnd = planEndDateEnd;
    }

    public List<String> getModuleIds() {
        return moduleIds;
    }

    public void setModuleIds(List<String> moduleIds) {
        this.moduleIds = moduleIds;
    }

    public List<String> getRootIds() {
        return rootIds;
    }

    public void setRootIds(List<String> rootIds) {
        this.rootIds = rootIds;
    }

    public String getWorkTypeSysId() {
        return workTypeSysId;
    }

    public void setWorkTypeSysId(String workTypeSysId) {
        this.workTypeSysId = workTypeSysId;
    }

    public List<String> getBuilderIds() {
        return builderIds;
    }

    public void setBuilderIds(List<String> builderIds) {
        this.builderIds = builderIds;
    }


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Boolean getOverdue() {
        return overdue;
    }

    public void setOverdue(Boolean overdue) {
        this.overdue = overdue;
    }

    public String getUpdateTimeStart() {
        return updateTimeStart;
    }

    public void setUpdateTimeStart(String updateTimeStart) {
        this.updateTimeStart = updateTimeStart;
    }

    public String getUpdateTimeEnd() {
        return updateTimeEnd;
    }

    public void setUpdateTimeEnd(String updateTimeEnd) {
        this.updateTimeEnd = updateTimeEnd;
    }

    public String getLikeId() {
        return likeId;
    }

    public void setLikeId(String likeId) {
        this.likeId = likeId;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public Boolean getStatisticsNum() {
        return statisticsNum;
    }

    public void setStatisticsNum(Boolean statisticsNum) {
        this.statisticsNum = statisticsNum;
    }

    public List<String> getCurrentVersionIds() {
        return currentVersionIds;
    }

    public void setCurrentVersionIds(List<String> currentVersionIds) {
        this.currentVersionIds = currentVersionIds;
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public List<String> getWorkStatusCodes() {
        return workStatusCodes;
    }

    public void setWorkStatusCodes(List<String> workStatusCodes) {
        this.workStatusCodes = workStatusCodes;
    }

    public String getNeqVersionId() {
        return neqVersionId;
    }

    public void setNeqVersionId(String neqVersionId) {
        this.neqVersionId = neqVersionId;
    }

    public String getNeqSprintId() {
        return neqSprintId;
    }

    public void setNeqSprintId(String neqSprintId) {
        this.neqSprintId = neqSprintId;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getNeqStageId() {
        return neqStageId;
    }

    public void setNeqStageId(String neqStageId) {
        this.neqStageId = neqStageId;
    }

    public String[] getStageIds() {
        return stageIds;
    }

    public void setStageIds(String[] stageIds) {
        this.stageIds = stageIds;
    }
}