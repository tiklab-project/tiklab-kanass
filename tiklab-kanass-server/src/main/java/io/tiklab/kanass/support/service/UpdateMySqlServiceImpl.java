
package io.tiklab.kanass.support.service;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.dal.jdbc.JdbcTemplate;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.kanass.common.ErrorCode;
import io.tiklab.kanass.home.insight.model.Insight;
import io.tiklab.kanass.home.statistic.model.ProjectBurnDowmChart;
import io.tiklab.kanass.home.statistic.model.Report;
import io.tiklab.kanass.home.statistic.model.SprintBurnDowmChart;
import io.tiklab.kanass.project.epic.model.Epic;
import io.tiklab.kanass.project.epic.model.EpicWorkItem;
import io.tiklab.kanass.project.milestone.model.Milestone;
import io.tiklab.kanass.project.module.model.Module;
import io.tiklab.kanass.project.plan.model.Plan;
import io.tiklab.kanass.project.plan.model.PlanWorkItem;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.model.ProjectFocus;
import io.tiklab.kanass.project.stage.model.Stage;
import io.tiklab.kanass.project.stage.model.StageWorkItem;
import io.tiklab.kanass.project.version.model.ProjectVersion;
import io.tiklab.kanass.projectset.model.ProjectSet;
import io.tiklab.kanass.projectset.model.ProjectSetFocus;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.kanass.sprint.model.SprintFocus;
import io.tiklab.kanass.sprint.model.SprintState;
import io.tiklab.kanass.support.dao.UpdateMySqlDao;
import io.tiklab.kanass.support.model.Recent;
import io.tiklab.kanass.workitem.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 洗数据
*/
@Service
public class UpdateMySqlServiceImpl implements UpdateMySqlService {

    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    UpdateMySqlDao updateMySqlDao;
    @Override
    public void updateAllData() {
        updateMySqlDao.updateId();
    }


    /**
     * 洗数据
     */
    @Override
    public void updateProject() {
        String sql = "select * from pmc_project";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<Project> projectList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(Project.class));
        for (Project project : projectList) {
            String id = project.getId();
            try {
                String newId = id.substring(0, 12);
                // 更新项目id
                sql = "UPDATE pmc_project" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

                // 更新项目关联模块的项目id
                sql = "UPDATE pmc_module" + " SET project_id = '" + newId + "' WHERE project_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                // 更新项目关联迭代的项目id
                sql = "UPDATE pmc_sprint" + " SET project_id = '" + newId + "' WHERE project_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                // 更新项目关联版本的项目id
                sql = "UPDATE pmc_version" + " SET project_id = '" + newId + "' WHERE project_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                // 更新项目关联事项的项目id
                sql = "UPDATE pmc_work_item" + " SET project_id = '" + newId + "' WHERE project_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                // 更新项目关联事项日志的项目id
                sql = "UPDATE pmc_work_log" + " SET project_id = '" + newId + "' WHERE project_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                // 更新项目关联史诗的项目id
                sql = "UPDATE pmc_epic" + " SET project_id = '" + newId + "' WHERE project_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                // 更新项目关联动态的项目id
                sql = "UPDATE pmc_dynamic" + " SET project_id = '" + newId + "' WHERE project_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                // 更新项目关联报表的项目id
                sql = "UPDATE pmc_report" + " SET project_id = '" + newId + "' WHERE project_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                // 更新项目最近查看的的项目id
                sql = "UPDATE pmc_recent" + " SET project_id = '" + newId + "' WHERE project_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_recent" + " SET model_id = '" + newId + "' WHERE model_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                // 更新项目关联关注的迭代的的项目id
                sql = "UPDATE pmc_sprint_focus" + " SET project_id = '" + newId + "' WHERE project_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                // 更新项目关联里程碑的的项目id
                sql = "UPDATE pmc_milestone" + " SET project_id = '" + newId + "' WHERE project_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                // 更新项目关联计划的的项目id
                sql = "UPDATE pmc_plan" + " SET project_id = '" + newId + "' WHERE project_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                // 更新项目关联燃尽图的项目id
                sql = "UPDATE pmc_project_burndowm" + " SET project_id = '" + newId + "' WHERE project_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                // 更新关注项目的项目id
                sql = "UPDATE pmc_project_focus" + " SET project_id = '" + newId + "' WHERE project_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                // 更新项目关联的事项类型的项目id
                sql = "UPDATE pmc_work_type_dm" + " SET project_id = '" + newId + "' WHERE project_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                // 更新项目关联的阶段的项目id
                sql = "UPDATE pmc_stage" + " SET project_id = '" + newId + "' WHERE project_id = '" + id + "'";
                jdbcTemplate.execute(sql);


            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"项目id更新失败" + e.getMessage());
            }

        }

    }

    @Override
    public void updateModule() {
        String sql = "select * from pmc_module";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<Module> moduleList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(Module.class));
        for (Module module : moduleList) {
            try {
                String id = module.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_module" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_work_item" + " SET module_id = '" + newId + "' WHERE module_id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateSprint() {
        String sql = "select * from pmc_sprint";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<Sprint> sprintList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(Sprint.class));
        for (Sprint sprint : sprintList) {
            try {
                String id = sprint.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_sprint" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_work_item" + " SET sprint_id = '" + newId + "' WHERE sprint_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_report" + " SET sprint_id = '" + newId + "' WHERE sprint_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_sprint_focus" + " SET sprint_id = '" + newId + "' WHERE sprint_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_sprint_burndowm" + " SET sprint_id = '" + newId + "' WHERE sprint_id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"迭代更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateSprintStatus() {
        String sql = "select * from pmc_sprint_status";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<SprintState> sprintStateList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(SprintState.class));
        for (SprintState sprintState : sprintStateList) {
            try {
                String id = sprintState.getId();
                String newId = id.substring(0, 8);
                sql = "UPDATE pmc_sprint_status" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_sprint" + " SET sprint_state_id = '" + newId + "' WHERE sprint_state_id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"迭代状态更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateVersion() {
        String sql = "select * from pmc_version";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<ProjectVersion> versionList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(ProjectVersion.class));
        for (ProjectVersion version : versionList) {
            try {
                String id = version.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_version" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_work_item" + " SET version_id = '" + newId + "' WHERE version_id = '" + id + "'";
                jdbcTemplate.execute(sql);
            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR, "版本更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateWorkItemDocument() {
        String sql = "select * from pmc_work_item_document";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<WorkItemDocument> documentList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(WorkItemDocument.class));
        for (WorkItemDocument document : documentList) {
            try {
                String id = document.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_work_item_document" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

                String documentId = document.getDocumentId();
                String newDocumentId = documentId.substring(0, 12);
                sql = "UPDATE pmc_work_item_document" + " SET document_id = " + newDocumentId + " WHERE document_id = " + documentId;
                jdbcTemplate.execute(sql);

                String repositoryId = document.getRepositoryId();
                String newRepositoryId = documentId.substring(0, 12);
                sql = "UPDATE pmc_work_item_document" + " SET repository_id = " + newRepositoryId + " WHERE repository_id = " + repositoryId;
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"事项文档id更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateWorkItemAttach() {
        String sql = "select * from pmc_work_attach";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<WorkAttach> workAttachList= jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(WorkAttach.class));
        for (WorkAttach workAttach : workAttachList) {
            try {
                String id = workAttach.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_work_attach" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);
            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"事项附件id更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateWorkItemLog() {
        String sql = "select * from pmc_work_log";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<WorkItemDocument> documentList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(WorkItemDocument.class));
        for (WorkItemDocument document : documentList) {
            try {
                String id = document.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_work_log" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);
            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"事项日志更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateWorkPriority() {
        String sql = "select * from pmc_work_priority";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<WorkPriority> priorityList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(WorkPriority.class));
        for (WorkPriority priority : priorityList) {
            try {
                String id = priority.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_work_priority" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_work_item" + " SET work_priority_id = '" + newId + "' WHERE work_priority_id = '" + id + "'";
                jdbcTemplate.execute(sql);
            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"事项优先级更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateWorkRelate() {
        String sql = "select * from pmc_work_relate";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<WorkRelate> workRelateList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(WorkRelate.class));
        for (WorkRelate workRelate : workRelateList) {
            try {
                String id = workRelate.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_work_relate" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);
            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"事项优先级更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateWorkType() {
        String sql = "select * from pmc_work_type";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<WorkType> workTypeList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(WorkType.class));
        for (WorkType workType : workTypeList) {
            try {
                String id = workType.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_work_type" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_work_item" + " SET work_type_sys_id = '" + newId + "' WHERE work_type_sys_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_work_type_dm" + " SET work_type_id = '" + newId + "' WHERE work_type_id = '" + id + "'";
                jdbcTemplate.execute(sql);
            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"事项优先级更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateProjectSet() {
        String sql = "select * from pmc_project_set";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<ProjectSet> projectSetList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(ProjectSet.class));
        for (ProjectSet projectSet : projectSetList) {
            try {
                String id = projectSet.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_project_set" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_project" + " SET project_set_id = '" + newId + "' WHERE project_set_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_project_set_focus" + " SET project_set_id = '" + newId + "' WHERE project_set_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_recent" + " SET model_id = '" + newId + "' WHERE model_id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"项目集更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateEpic() {
        String sql = "select * from pmc_epic";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<Epic> epicList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(Epic.class));
        for (Epic epic : epicList) {
            try {
                String id = epic.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_epic" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_epic" + " SET parent_id = '" + newId + "' WHERE parent_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_epic_work_item" + " SET epic_id = '" + newId + "' WHERE project_set_id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"史诗更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateEpicWorkItem() {
        String sql = "select * from pmc_epic_work_item";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<EpicWorkItem> epicWorkItemList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(EpicWorkItem.class));
        for (EpicWorkItem epicWorkItem : epicWorkItemList) {
            try {
                String id = epicWorkItem.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_epic_work_item" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"史诗更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateIcon() {
        String sql = "select * from pmc_icon";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<Epic> epicList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(Epic.class));
        for (Epic epic : epicList) {
            try {
                String id = epic.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_icon" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"图标更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateWorkComment() {
        String sql = "select * from pmc_work_comment";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<WorkComment> workCommentList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(WorkComment.class));
        for (WorkComment workComment : workCommentList) {
            try {
                String id = workComment.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_work_comment" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"事项评论更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateReport() {
        String sql = "select * from pmc_report";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<Report> reportList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(Report.class));
        for (Report report : reportList) {
            try {
                String id = report.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_report" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"项目报表更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateRecent() {
        String sql = "select * from pmc_recent";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<Recent> recentList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(Recent.class));
        for (Recent recent : recentList) {
            try {
                String id = recent.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_recent" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"项目报表更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateSprintFocus() {
        String sql = "select * from pmc_sprint_focus";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<SprintFocus> sprintFocusList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(SprintFocus.class));
        for (SprintFocus sprintFocus : sprintFocusList) {
            try {
                String id = sprintFocus.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_sprint_focus" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"项目报表更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateSprintBurndowm() {
        String sql = "select * from pmc_sprint_burndowm";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<SprintBurnDowmChart> sprintBurnDowmChartList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(SprintBurnDowmChart.class));
        for (SprintBurnDowmChart sprintBurnDowmChart : sprintBurnDowmChartList) {
            try {
                String id = sprintBurnDowmChart.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_sprint_burndowm" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"迭代燃尽图更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateMilestone() {
        String sql = "select * from pmc_milestone";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<Milestone> milestoneList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(Milestone.class));
        for (Milestone milestone : milestoneList) {
            try {
                String id = milestone.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_milestone" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"里程碑更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updatePlan() {
        String sql = "select * from pmc_plan";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<Plan> planList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(Plan.class));
        for (Plan plan : planList) {
            try {
                String id = plan.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_plan" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_plan" + " SET parent_id = '" + newId + "' WHERE parent_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_plan_work_item" + " SET plan_id = '" + newId + "' WHERE plan_id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"里程碑更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updatePlanWorkItem() {
        String sql = "select * from pmc_plan_work_item";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<PlanWorkItem> planWorkItemList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(PlanWorkItem.class));
        for (PlanWorkItem planWorkItem : planWorkItemList) {
            try {
                String id = planWorkItem.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_plan_work_item" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"计划的事项更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateInsight() {
        String sql = "select * from pmc_insight";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<Insight> insightList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(Insight.class));
        for (Insight insight : insightList) {
            try {
                String id = insight.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_insight" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"计划的事项更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateProjectBurndowm() {
        String sql = "select * from pmc_project_burndowm";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<ProjectBurnDowmChart> projectBurnDowmChartList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(ProjectBurnDowmChart.class));
        for (ProjectBurnDowmChart projectBurnDowmChart : projectBurnDowmChartList) {
            try {
                String id = projectBurnDowmChart.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_project_burndowm" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"项目燃尽图更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateProjectFocus() {
        String sql = "select * from pmc_project_focus";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<ProjectFocus> projectFocusList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(ProjectFocus.class));
        for (ProjectFocus projectFocus : projectFocusList) {
            try {
                String id = projectFocus.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_project_focus" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"项目燃尽图更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateProjectSetFocus() {
        String sql = "select * from pmc_project_set_focus";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<ProjectSetFocus> projectSetFocusList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(ProjectSetFocus.class));
        for (ProjectSetFocus projectSetFocus : projectSetFocusList) {
            try {
                String id = projectSetFocus.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_project_set_focus" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"项目燃尽图更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateWorkTypeDm() {
        String sql = "select * from pmc_work_type_dm";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<WorkTypeDm> workTypeDmList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(WorkTypeDm.class));
        for (WorkTypeDm workTypeDm : workTypeDmList) {
            try {
                String id = workTypeDm.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_work_type_dm" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"项目燃尽图更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateStage() {
        String sql = "select * from pmc_stage";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<Stage> stageList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(Stage.class));
        for (Stage stage : stageList) {
            try {
                String id = stage.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_stage" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_stage" + " SET parent_id = '" + newId + "' WHERE parent_id = '" + id + "'";
                jdbcTemplate.execute(sql);

                sql = "UPDATE pmc_stage_work_item" + " SET stage_id = '" + newId + "' WHERE stage_id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"项目阶段更新失败" + e.getMessage());
            }
        }
    }

    @Override
    public void updateStageWorkItem() {
        String sql = "select * from pmc_stage_work_item";
        JdbcTemplate jdbcTemplate = jpaTemplate.getJdbcTemplate();
        List<StageWorkItem> stageWorkItemList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper(StageWorkItem.class));
        for (StageWorkItem stageWorkItem : stageWorkItemList) {
            try {
                String id = stageWorkItem.getId();
                String newId = id.substring(0, 12);
                sql = "UPDATE pmc_stage_work_item" + " SET id = '" + newId + "' WHERE id = '" + id + "'";
                jdbcTemplate.execute(sql);

            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"项目阶段更新失败" + e.getMessage());
            }
        }
    }

}