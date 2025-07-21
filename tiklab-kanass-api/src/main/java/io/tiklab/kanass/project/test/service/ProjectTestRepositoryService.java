package io.tiklab.kanass.project.test.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;
import io.tiklab.kanass.project.test.model.ProjectTestRepository;
import io.tiklab.kanass.project.test.model.ProjectTestRepositoryQuery;
import io.tiklab.kanass.project.test.model.TestRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 计划服务接口
 */
@JoinProvider(model = ProjectTestRepository.class)
public interface ProjectTestRepositoryService {

    /**
     * 创建计划
     * @param projectTestRepository
     * @return
     */
    String createProjectTestRepository(@NotNull @Valid ProjectTestRepository projectTestRepository);

    /**
     * 更新计划
     * @param projectTestRepository
     */
    void updateProjectTestRepository(@NotNull @Valid ProjectTestRepository projectTestRepository);

    /**
     * 删除计划
     * @param id
     */
    void deleteProjectTestRepository(@NotNull String id);
    void deleteProjectTestRepositoryByCondition(@NotNull String repositoryId, @NotNull String projectId);

    @FindOne
    ProjectTestRepository findOne(@NotNull String id);
    @FindList
    List<ProjectTestRepository> findList(List<String> idList);

    /**
     * 根据id查找计划
     * @param id
     * @return
     */
    ProjectTestRepository findProjectTestRepository(@NotNull String id);

    List<TestRepository> findUnProjectTestRepository(@NotNull String ProjectId);

    /**
     * 查找所有计划
     * @return
     */
    @FindAll
    List<ProjectTestRepository> findAllProjectTestRepository();


    /**
     * 根据条件查找计划列表
     * @param projectTestRepositoryQuery
     * @return
     */
    List<TestRepository> findProjectTestRepositoryList(ProjectTestRepositoryQuery projectTestRepositoryQuery);

    /**
     * 根据条件按照分页查找计划列表
     * @param projectTestRepositoryQuery
     * @return
     */
    Pagination<ProjectTestRepository> findProjectTestRepositoryPage(ProjectTestRepositoryQuery projectTestRepositoryQuery);



}
