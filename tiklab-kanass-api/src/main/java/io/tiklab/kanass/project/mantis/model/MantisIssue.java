package io.tiklab.kanass.project.mantis.model;

import java.util.List;

// 事项
public class MantisIssue {
    // 事项id
    private String id;

    // 迁移到本系统后的id
    private String newId;

    // 事项标题  等于 title
    private String summary;

    // 项目id
    private String projectId;

    // 项目名称
    private String projectName;

    // 创建者id
    private String reporterId;

    // 创建者名称
    private String reporterName;

    // 处理人id
    private String handlerId;

    // 处理人名称
    private String handlerName;

    // 优先级  无、低 =》低    中-》中     高、紧急、非常紧急=》高
    private String priority;

    // 状态  新建、已分配=》待办    反馈、认可、已确认=》进行中  已解决=》已解决  已关闭=》完成
    private String status;

    // 产品版本名称
    private String version;

    // 修正版本名称
    private String fixVersion;

    // 目标版本名称
    private String targetVersion;

    // 事项注释list
    private List<MantisNote> mantisNoteList;

    // 附件list
    private List<MantisAttachment> mantisAttachmentList;

    // 事项描述  下面3个内容可以合成为事项的描述
    private String description;

    // 事项复现步骤
    private String stepsToReproduce;

    // 事项附注
    private String additionalInformation;

    // 事项创建时间戳
    private String dateSubmitted;

    // 最后更新时间戳
    private String lastUpdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getReporterId() {
        return reporterId;
    }

    public void setReporterId(String reporterId) {
        this.reporterId = reporterId;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(String handlerId) {
        this.handlerId = handlerId;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFixVersion() {
        return fixVersion;
    }

    public void setFixVersion(String fixVersion) {
        this.fixVersion = fixVersion;
    }

    public String getTargetVersion() {
        return targetVersion;
    }

    public void setTargetVersion(String targetVersion) {
        this.targetVersion = targetVersion;
    }

    public List<MantisNote> getMantisNoteList() {
        return mantisNoteList;
    }

    public void setMantisNoteList(List<MantisNote> mantisNoteList) {
        this.mantisNoteList = mantisNoteList;
    }

    public List<MantisAttachment> getMantisAttachmentList() {
        return mantisAttachmentList;
    }

    public void setMantisAttachmentList(List<MantisAttachment> mantisAttachmentList) {
        this.mantisAttachmentList = mantisAttachmentList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStepsToReproduce() {
        return stepsToReproduce;
    }

    public void setStepsToReproduce(String stepsToReproduce) {
        this.stepsToReproduce = stepsToReproduce;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
