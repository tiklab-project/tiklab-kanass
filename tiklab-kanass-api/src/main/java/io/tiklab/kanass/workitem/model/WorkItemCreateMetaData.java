package io.tiklab.kanass.workitem.model;

import io.tiklab.core.BaseModel;
import io.tiklab.core.page.Pagination;
import io.tiklab.form.field.model.FieldEx;
import io.tiklab.form.form.model.Form;
import io.tiklab.kanass.project.module.model.Module;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.stage.model.Stage;
import io.tiklab.kanass.project.version.model.ProjectVersion;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.user.dmUser.model.DmUser;

import java.util.List;

/**
 * 创建事项时需要的元数据
 */
public class WorkItemCreateMetaData extends BaseModel {

    // 表单信息
    private Form form;

    // 字段信息
    private List<List<FieldEx>> fieldList;

    // 模块信息
    private List<Module> moduleList;

    // 迭代信息
    private List<Sprint> sprintList;

    // 计划信息
    private List<Stage> stageList;

    // 版本信息
    private List<ProjectVersion> versionList;

    // 人员信息
    private Pagination<DmUser> dmUserPage;

    // 项目信息
    private Project project;

    // 事项类型
    private WorkTypeDm workTypeDm;


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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public WorkTypeDm getWorkTypeDm() {
        return workTypeDm;
    }

    public void setWorkTypeDm(WorkTypeDm workTypeDm) {
        this.workTypeDm = workTypeDm;
    }
}
