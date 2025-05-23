package io.tiklab.kanass.workitem.model;

import io.tiklab.core.BaseModel;
import io.tiklab.core.page.Page;
import io.tiklab.core.page.Pagination;
import io.tiklab.flow.flow.model.Flow;
import io.tiklab.flow.transition.model.Transition;
import io.tiklab.form.field.model.FieldEx;
import io.tiklab.form.form.model.Form;
import io.tiklab.kanass.project.module.model.Module;
import io.tiklab.kanass.project.stage.model.Stage;
import io.tiklab.kanass.project.version.model.ProjectVersion;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.user.dmUser.model.DmUser;

import java.util.HashMap;
import java.util.List;

/**
 * 创建事项时需要的元数据
 */
public class WorkItemDetailMetaData extends BaseModel {

    // 表单信息
    private Form form;

    // 流程信息
    private Flow flow;

    // 字段信息
    private List<List<FieldEx>> fieldList;

    // 模块信息
    private List<Module> moduleList;

    // 迭代信息
    private List<Sprint> sprintList;

    // 事项迭代信息
    private List<Sprint> workSprintList;

    // 计划信息
    private List<Stage> stageList;

    // 版本信息
    private List<ProjectVersion> versionList;

    // 事项版本信息
    private List<ProjectVersion> workVersionList;

    // 人员信息
    private Pagination<DmUser> dmUserPage;

    // 事项
    private WorkItem workItem;

    // 附件信息
    private List<WorkAttach> workAttachList;

    // 可关联父工单
    private Pagination<WorkItem> canBeRelationParentWorkItemList;

    // 可关联前置工单
    private Pagination<WorkItem> canBeRelationPerWorkItemList;

    // workTypeDmList
    private List<WorkTypeDm> workTypeDmList;



    // 流转信息
    private List<Transition> transitionListByBusiness;
    // 根据查询事项关联各个模型的数量，ex: 关联事项，下级事项， 日志， 文档，动态等
    private HashMap<String, Integer> workItemRelationModelCount;

    // 权限code
    private List<String> workItemRoleFunctionDmCode;


    public List<WorkTypeDm> getWorkTypeDmList() {
        return workTypeDmList;
    }

    public void setWorkTypeDmList(List<WorkTypeDm> workTypeDmList) {
        this.workTypeDmList = workTypeDmList;
    }

    public List<Transition> getTransitionListByBusiness() {
        return transitionListByBusiness;
    }

    public void setTransitionListByBusiness(List<Transition> transitionListByBusiness) {
        this.transitionListByBusiness = transitionListByBusiness;
    }

    public HashMap<String, Integer> getWorkItemRelationModelCount() {
        return workItemRelationModelCount;
    }

    public void setWorkItemRelationModelCount(HashMap<String, Integer> workItemRelationModelCount) {
        this.workItemRelationModelCount = workItemRelationModelCount;
    }

    public List<String> getWorkItemRoleFunctionDmCode() {
        return workItemRoleFunctionDmCode;
    }

    public void setWorkItemRoleFunctionDmCode(List<String> workItemRoleFunctionDmCode) {
        this.workItemRoleFunctionDmCode = workItemRoleFunctionDmCode;
    }

    public Pagination<WorkItem> getCanBeRelationParentWorkItemList() {
        return canBeRelationParentWorkItemList;
    }

    public void setCanBeRelationParentWorkItemList(Pagination<WorkItem> canBeRelationParentWorkItemList) {
        this.canBeRelationParentWorkItemList = canBeRelationParentWorkItemList;
    }

    public Pagination<WorkItem> getCanBeRelationPerWorkItemList() {
        return canBeRelationPerWorkItemList;
    }

    public void setCanBeRelationPerWorkItemList(Pagination<WorkItem> canBeRelationPerWorkItemList) {
        this.canBeRelationPerWorkItemList = canBeRelationPerWorkItemList;
    }

    public List<WorkAttach> getWorkAttachList() {
        return workAttachList;
    }

    public void setWorkAttachList(List<WorkAttach> workAttachList) {
        this.workAttachList = workAttachList;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public List<List<FieldEx>> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<List<FieldEx>> fieldList) {
        this.fieldList = fieldList;
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public List<Sprint> getSprintList() {
        return sprintList;
    }

    public void setSprintList(List<Sprint> sprintList) {
        this.sprintList = sprintList;
    }

    public List<Stage> getStageList() {
        return stageList;
    }

    public void setStageList(List<Stage> stageList) {
        this.stageList = stageList;
    }

    public List<ProjectVersion> getVersionList() {
        return versionList;
    }

    public void setVersionList(List<ProjectVersion> versionList) {
        this.versionList = versionList;
    }

    public Pagination<DmUser> getDmUserPage() {
        return dmUserPage;
    }

    public void setDmUserPage(Pagination<DmUser> dmUserPage) {
        this.dmUserPage = dmUserPage;
    }

    public WorkItem getWorkItem() {
        return workItem;
    }

    public void setWorkItem(WorkItem workItem) {
        this.workItem = workItem;
    }

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public List<Sprint> getWorkSprintList() {
        return workSprintList;
    }

    public void setWorkSprintList(List<Sprint> workSprintList) {
        this.workSprintList = workSprintList;
    }

    public List<ProjectVersion> getWorkVersionList() {
        return workVersionList;
    }

    public void setWorkVersionList(List<ProjectVersion> workVersionList) {
        this.workVersionList = workVersionList;
    }
}
