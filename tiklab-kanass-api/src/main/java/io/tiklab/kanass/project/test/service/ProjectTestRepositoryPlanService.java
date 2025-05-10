package io.tiklab.kanass.project.test.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.test.model.ProjectTestRepositoryPlan;
import io.tiklab.kanass.project.test.model.ProjectTestRepositoryPlanQuery;
import io.tiklab.kanass.project.test.model.TestPlan;
import io.tiklab.kanass.project.test.model.TestRepository;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 计划服务接口
 */
@JoinProvider(model = ProjectTestRepositoryPlan.class)
public interface ProjectTestRepositoryPlanService {

    /**
     * 创建计划
     * @param projectTestRepositoryPlan
     * @return
     */
    String createProjectTestRepositoryPlan(@NotNull @Valid ProjectTestRepositoryPlan projectTestRepositoryPlan);

    /**
     * 更新计划
     * @param projectTestRepositoryPlan
     */
    void updateProjectTestRepositoryPlan(@NotNull @Valid ProjectTestRepositoryPlan projectTestRepositoryPlan);

    /**
     * 删除计划
     * @param id
     */
    void deleteProjectTestRepositoryPlan(@NotNull String id);
    void deleteProjectTestRepositoryPlanByCondition(@NotNull String repositoryId, @NotNull String planId, @NotNull String projectId);

    @FindOne
    ProjectTestRepositoryPlan findOne(@NotNull String id);
    @FindList
    List<ProjectTestRepositoryPlan> findList(List<String> idList);

    /**
     * 根据id查找计划
     * @param id
     * @return
     */
    ProjectTestRepositoryPlan findProjectTestRepositoryPlan(@NotNull String id);

    List<TestPlan> findUnProjectTestRepositoryPlan(@NotNull String projectId, @NotNull String repositoryId);

    List<TestPlan> findAllProjectTestRepositoryPlanByRepositoryId(@NotNull String repositoryId);

    /**
     * 查找所有计划
     * @return
     */
    @FindAll
    List<ProjectTestRepositoryPlan> findAllProjectTestRepositoryPlan();


//    /**
//     * 根据条件查找计划列表
//     * @param projectTestRepositoryPlanQuery
//     * @return
//     */
//    List<TestRepository> findProjectTestRepositoryPlanList(ProjectTestRepositoryPlanQuery projectTestRepositoryPlanQuery);

    /**
     * 根据条件按照分页查找计划列表
     * @param projectTestRepositoryPlanQuery
     * @return
     */
    Pagination<ProjectTestRepositoryPlan> findProjectTestRepositoryPlanPage(ProjectTestRepositoryPlanQuery projectTestRepositoryPlanQuery);

    List<ProjectTestRepositoryPlan> findProjectTestRepositoryPlanList(ProjectTestRepositoryPlanQuery projectTestRepositoryPlanQuery);

}
