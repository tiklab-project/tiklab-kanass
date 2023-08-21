package io.tiklab.teamwire.project.project.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.teamwire.project.project.model.ProjectQuery;
import io.tiklab.teamwire.workitem.model.WorkTypeDm;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 项目服务接口
*/
@JoinProvider(model = Project.class)
public interface ProjectService {

    /**
    * 创建项目
    * @param project
    * @return
    */
    String createProject(@NotNull @Valid Project project);
    String createJiraProject(@NotNull @Valid Project project);
    /**
    * 更新项目
    * @param project
    */
    void updateProject(@NotNull @Valid Project project);

    /**
    * 删除项目
    * @param id
    */
    void deleteProject(@NotNull String id);

    @FindOne
    Project findOne(@NotNull String id);

    /**
     * 根据多个id 查找项目列表
     * @param idList
     * @return
     */
    @FindList
    List<Project> findList(@NotNull List<String> idList);

    List<WorkTypeDm> initWorkType(@NotNull String projectId);

    /**
    * 根据id查找项目
    * @param id
    * @return
    */
    Project findProject(@NotNull String id);

    Project findProjectAndWorkNum(@NotNull String id);
    /**
    * 查找所有项目
    * @return
    */
    @FindAll
    List<Project> findAllProject();

    /**
     * 根据条件查找项目列表
     * @param projectQuery
     * @return
     */
    List<Project> findProjectList(ProjectQuery projectQuery);

    /**
     * 查找我负责的项目
     * @param masterId
     * @return
     */
    List<Project> findMaterProjectList(@NotNull String masterId);

    /**
     * 查找进行中的项目
     * @return
     */
    List<Project> findProgressProjectList();


    /**
     * 根据条件分页查找项目列表
     * @param projectQuery
     * @return
     */
    Pagination<Project> findProjectPage(ProjectQuery projectQuery);

    /**
     * 查找我参与的项目列表
     * @param projectQuery
     * @return
     */
    List<Project> findJoinProjectList(ProjectQuery projectQuery);

    /**
     * 查找最近查看的项目
     * @param projectQuery
     * @return
     */
    List<Project> findRecentProjectList(ProjectQuery projectQuery);

    /**
     * 查找收藏的项目
     * @param projectQuery
     * @return
     */
    List<Project> findFocusProjectList(ProjectQuery projectQuery);

    List<Project> findProjectListByKeyWords(String keyWord);

    String creatProjectKey();

//    Pagination<WorkItem> findProjectByKeyWorks(WorkItemQuery workItemQuery);

}