package io.tiklab.kanass.workitem.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 事项实例
 */
@Entity
@Table(name="pmc_work_item")
public class WorkItemEntity implements Serializable {
    //唯一标识
    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 12)
    private String id;

    @Column(name = "code",length = 32)
    private String code;

    // id 排序
    @Column(name = "order_num",length = 32)
    private Integer orderNum;

    //所属项目ID
    @Column(name = "project_id",length = 32,notNull = true)
    private String projectId;

    //上级事项ID
    @Column(name = "parent_id",length = 32)
    private String parentId;

    //树形结构的路径
    @Column(name = "tree_path",length = 1024)
    private String treePath;

    //前置依赖事项ID
    @Column(name = "pre_depend_id",length = 32)
    private String preDependId;

    //标题
    @Column(name = "title",length = 64,notNull = true)
    private String title;

    //描述
    @Column(name = "description",length = 64)
    private String desc;

    //事项类型ID
    @Column(name = "work_type_id",length = 32,notNull = true)
    private String workTypeId;

    // 系统的事项类型id
    @Column(name = "work_type_sys_id",length = 32,notNull = true)
    private String workTypeSysId;

    // 事项类型编码
    @Column(name = "work_type_code",length = 32,notNull = true)
    private String workTypeCode;

    //事项优先级ID
    @Column(name = "work_priority_id",length = 32)
    private String workPriorityId;

    //系统事项状态ID
    @Column(name = "work_status_id",length = 32,notNull = true)
    private String workStatusId;

    // 事项优先级编码
    @Column(name = "work_status_code",length = 32,notNull = true)
    private String workStatusCode;

    // 事项节点id
    @Column(name = "work_status_node_id",length = 32,notNull = true)
    private String workStatusNodeId;

    @Column(name = "stage_id",length = 32,notNull = true)
    private String stageId;

    // 需求，任务，缺陷的类型id
    @Column(name = "each_type",length = 64)
    private String eachType;

    //所属模块ID
    @Column(name = "module_id",length = 32)
    private String moduleId;

    //所属迭代ID
    @Column(name = "sprint_id",length = 32)
    private String sprintId;

    //所属版本ID
    @Column(name = "version_id",length = 32)
    private String versionId;

    //所属产品计划D
    @Column(name = "product_plan_id",length = 32)
    private String productPlanId;

    //经办人ID
    @Column(name = "assigner_id",length = 32)
    private String assignerId;

    //创建人ID
    @Column(name = "builder_id",length = 32,notNull = true)
    private String builderId;

    //报告人ID
    @Column(name = "reporter_id",length = 32)
    private String reporterId;

    //计划开始时间
    @Column(name = "plan_begin_time")
    private String planBeginTime;

    //计划结束时间
    @Column(name = "plan_end_time")
    private String planEndTime;

    @Column(name = "update_time")
    private String updateTime;

    //事项创建时间
    @Column(name = "build_time")
    private String buildTime;

    //实际开始时间
    @Column(name = "actual_begin_time")
    private String actualBeginTime;

    //实际结束时间
    @Column(name = "actual_end_time")
    private String actualEndTime;

    //完成百分比
    @Column(name = "percent")
    private Integer percent;

    //计划用时
    @Column(name = "estimate_time")
    private Integer estimateTime;

    // 剩余时间
    @Column(name = "surplus_time")
    private Integer surplusTime;


    //扩展数据
    @Column(name = "ext_data",length = 2048)
    private String extData;

    //根节点
    @Column(name = "root_id",length = 64)
    private String rootId;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    public String getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(String workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String getWorkPriorityId() {
        return workPriorityId;
    }

    public void setWorkPriorityId(String workPriorityId) {
        this.workPriorityId = workPriorityId;
    }

    public String getWorkStatusId() {
        return workStatusId;
    }

    public void setWorkStatusId(String workStatusId) {
        this.workStatusId = workStatusId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }

    public String getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(String assignerId) {
        this.assignerId = assignerId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public String getPreDependId() {
        return preDependId;
    }

    public void setPreDependId(String preDependId) {
        this.preDependId = preDependId;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getExtData() {
        return extData;
    }

    public void setExtData(String extData) {
        this.extData = extData;
    }

    public String getBuilderId() {
        return builderId;
    }

    public void setBuilderId(String builderId) {
        this.builderId = builderId;
    }

    public String getReporterId() {
        return reporterId;
    }

    public void setReporterId(String reporterId) {
        this.reporterId = reporterId;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
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

    public String getWorkTypeSysId() {
        return workTypeSysId;
    }

    public void setWorkTypeSysId(String workTypeSysId) {
        this.workTypeSysId = workTypeSysId;
    }

    public String getWorkStatusNodeId() {
        return workStatusNodeId;
    }

    public void setWorkStatusNodeId(String workStatusNodeId) {
        this.workStatusNodeId = workStatusNodeId;
    }

    public String getEachType() {
        return eachType;
    }

    public void setEachType(String eachType) {
        this.eachType = eachType;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Integer getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(Integer estimateTime) {
        this.estimateTime = estimateTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProductPlanId() {
        return productPlanId;
    }

    public void setProductPlanId(String productPlanId) {
        this.productPlanId = productPlanId;
    }
}
