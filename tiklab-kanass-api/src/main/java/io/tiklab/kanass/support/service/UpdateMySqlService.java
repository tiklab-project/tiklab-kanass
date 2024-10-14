
package io.tiklab.kanass.support.service;

/**
* 图标服务接口
*/
public interface UpdateMySqlService {

    void updateAllData();

    void updateProject();

    void updateModule();

    void updateSprint();

    void updateSprintStatus();

    void updateVersion();

    void updateWorkItemDocument();

    void updateWorkItemAttach();

    void updateWorkItemLog();

    void updateWorkPriority();

    void updateWorkRelate();

    void updateWorkType();

    void updateProjectSet();

    void updateEpic();

    void updateEpicWorkItem();

    void updateIcon();

    void updateWorkComment();

    void updateReport();

    void updateRecent();

    void updateSprintFocus();

    void updateSprintBurndowm();

    void updateMilestone();

    void updatePlan();

    void updatePlanWorkItem();

    void updateInsight();

    void updateProjectBurndowm();

    void updateProjectFocus();

    void updateProjectSetFocus();

    void updateWorkTypeDm();

    void updateStage();

    void updateStageWorkItem();
}