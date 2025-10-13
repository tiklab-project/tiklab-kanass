package io.tiklab.kanass.project.wiki.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;
import io.tiklab.kanass.project.wiki.model.KanassRepository;
import io.tiklab.kanass.project.wiki.model.ProjectWikiRepository;
import io.tiklab.kanass.project.wiki.model.ProjectWikiRepositoryQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 计划服务接口
 */
@JoinProvider(model = ProjectWikiRepository.class)
public interface ProjectWikiRepositoryService {

    /**
     * 创建计划
     * @param projectWikiRepository
     * @return
     */
    String createProjectWikiRepository(@NotNull @Valid ProjectWikiRepository projectWikiRepository);

    /**
     * 创建知识库，然后关联
     * @param projectWikiRepository
     * @return
     */
    String createWikiRepository(@NotNull @Valid ProjectWikiRepository projectWikiRepository);

    /**
     * 更新计划
     * @param projectWikiRepository
     */
    void updateProjectWikiRepository(@NotNull @Valid ProjectWikiRepository projectWikiRepository);

    /**
     * 删除计划
     * @param id
     */
    void deleteProjectWikiRepository(@NotNull String id);

    void deleteProjectWikiRepositoryByCondition(@NotNull String repositoryId, @NotNull String projectId);
    @FindOne
    ProjectWikiRepository findOne(@NotNull String id);
    @FindList
    List<ProjectWikiRepository> findList(List<String> idList);

    /**
     * 根据id查找计划
     * @param id
     * @return
     */
    ProjectWikiRepository findProjectWikiRepository(@NotNull String id);

    List<KanassRepository> findUnProjectWikiRepository(@NotNull String ProjectId);

    /**
     * 查找所有计划
     * @return
     */
    @FindAll
    List<ProjectWikiRepository> findAllProjectWikiRepository();


    /**
     * 根据条件查找计划列表
     * @param projectWikiRepositoryQuery
     * @return
     */
    List<KanassRepository> findProjectWikiRepositoryList(ProjectWikiRepositoryQuery projectWikiRepositoryQuery);

    /**
     * 根据条件查找人员
     * @return
     */


    /**
     * 根据条件按照分页查找计划列表
     * @param projectWikiRepositoryQuery
     * @return
     */
    Pagination<ProjectWikiRepository> findProjectWikiRepositoryPage(ProjectWikiRepositoryQuery projectWikiRepositoryQuery);


}
