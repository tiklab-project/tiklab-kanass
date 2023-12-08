package io.thoughtware.kanass.project.wiki.service;

import io.thoughtware.kanass.project.wiki.dao.ProjectWikiRepositoryDao;
import io.thoughtware.kanass.project.wiki.entity.ProjectWikiRepositoryEntity;
import io.thoughtware.kanass.project.wiki.model.KanassRepository;
import io.thoughtware.kanass.project.wiki.model.ProjectWikiRepository;
import io.thoughtware.kanass.project.wiki.model.ProjectWikiRepositoryQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;

import io.thoughtware.beans.BeanMapper;
import io.thoughtware.join.JoinTemplate;
import io.thoughtware.kanass.workitem.service.WorkRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 收藏迭代的服务
 */
@Service
public class ProjectWikiRepositoryImpl implements ProjectWikiRepositoryService {

    @Autowired
    ProjectWikiRepositoryDao projectWikiRepositoryDao;

    @Autowired
    JoinTemplate joinTemplate;


    @Autowired
    WorkRepositoryService workRepositoryService;

    @Override
    public String createProjectWikiRepository(@NotNull @Valid ProjectWikiRepository projectWikiRepository) {
        ProjectWikiRepositoryEntity projectWikiRepositoryEntity = BeanMapper.map(projectWikiRepository, ProjectWikiRepositoryEntity.class);

        return projectWikiRepositoryDao.createProjectWikiRepository(projectWikiRepositoryEntity);
    }

    @Override
    public void updateProjectWikiRepository(@NotNull @Valid ProjectWikiRepository projectWikiRepository) {
        ProjectWikiRepositoryEntity projectWikiRepositoryEntity = BeanMapper.map(projectWikiRepository, ProjectWikiRepositoryEntity.class);

        projectWikiRepositoryDao.updateProjectWikiRepository(projectWikiRepositoryEntity);
    }

    @Override
    public void deleteProjectWikiRepository(@NotNull String id) {
        projectWikiRepositoryDao.deleteProjectWikiRepository(id);
    }

    @Override
    public void deleteProjectWikiRepositoryByCondition(@NotNull String repositoryId, @NotNull String projectId) {
        ProjectWikiRepositoryQuery projectWikiRepositoryQuery = new ProjectWikiRepositoryQuery();
        projectWikiRepositoryQuery.setProjectId(projectId);
        projectWikiRepositoryQuery.setWikiRepositoryId(repositoryId);
        List<ProjectWikiRepositoryEntity> projectWikiRepositoryList = projectWikiRepositoryDao.findProjectWikiRepositoryList(projectWikiRepositoryQuery);
        for (ProjectWikiRepositoryEntity projectWikiRepository : projectWikiRepositoryList) {
            projectWikiRepositoryDao.deleteProjectWikiRepository(projectWikiRepository.getId());
        }

    }

    @Override
    public ProjectWikiRepository findOne(String id) {
        ProjectWikiRepositoryEntity projectWikiRepositoryEntity = projectWikiRepositoryDao.findProjectWikiRepository(id);

        ProjectWikiRepository projectWikiRepository = BeanMapper.map(projectWikiRepositoryEntity, ProjectWikiRepository.class);
        return projectWikiRepository;
    }

    @Override
    public List<ProjectWikiRepository> findList(List<String> idList) {
        List<ProjectWikiRepositoryEntity> projectWikiRepositoryEntityList = projectWikiRepositoryDao.findProjectWikiRepositoryList(idList);

        List<ProjectWikiRepository> projectWikiRepositoryList =  BeanMapper.mapList(projectWikiRepositoryEntityList,ProjectWikiRepository.class);
        return projectWikiRepositoryList;
    }

    @Override
    public List<KanassRepository> findUnProjectWikiRepository(@NotNull String projectId) {
        ProjectWikiRepositoryQuery projectWikiRepositoryQuery = new ProjectWikiRepositoryQuery();
        projectWikiRepositoryQuery.setProjectId(projectId);
        List<KanassRepository> projectWikiWikiRepositoryList = findProjectWikiRepositoryList(projectWikiRepositoryQuery);
        List<String> repositoryIds = projectWikiWikiRepositoryList.stream().map(projectWikiRepository -> projectWikiRepository.getId()).collect(Collectors.toList());

        List<KanassRepository> allWikiRepository = workRepositoryService.findAllRepository();

        List<KanassRepository> wikiRepositoryList = allWikiRepository.stream().filter(repository -> !repositoryIds.contains(repository.getId())).collect(Collectors.toList());


        return wikiRepositoryList;
    }

    @Override
    public ProjectWikiRepository findProjectWikiRepository(@NotNull String id) {
        ProjectWikiRepository projectWikiRepository = findOne(id);

        joinTemplate.joinQuery(projectWikiRepository);

        return projectWikiRepository;
    }

    @Override
    public List<ProjectWikiRepository> findAllProjectWikiRepository() {
        List<ProjectWikiRepositoryEntity> projectWikiRepositoryEntityList =  projectWikiRepositoryDao.findAllProjectWikiRepository();

        List<ProjectWikiRepository> projectWikiRepositoryList =  BeanMapper.mapList(projectWikiRepositoryEntityList,ProjectWikiRepository.class);

        joinTemplate.joinQuery(projectWikiRepositoryList);

        return projectWikiRepositoryList;
    }

    @Override
    public List<KanassRepository> findProjectWikiRepositoryList(ProjectWikiRepositoryQuery projectWikiRepositoryQuery) {
        List<ProjectWikiRepositoryEntity> projectWikiRepositoryEntityList = projectWikiRepositoryDao.findProjectWikiRepositoryList(projectWikiRepositoryQuery);

        List<ProjectWikiRepository> projectWikiRepositoryList = BeanMapper.mapList(projectWikiRepositoryEntityList,ProjectWikiRepository.class);

        List<String> repositoryIds = projectWikiRepositoryList.stream().map(projectWikiRepository -> projectWikiRepository.getWikiRepository().getId()).collect(Collectors.toList());

        List<KanassRepository> wikiRepositoryList = new ArrayList<KanassRepository>();

        if(repositoryIds.size() > 0){
            wikiRepositoryList = workRepositoryService.findList(repositoryIds);

        }

        return wikiRepositoryList;
    }


    @Override
    public Pagination<ProjectWikiRepository> findProjectWikiRepositoryPage(ProjectWikiRepositoryQuery projectWikiRepositoryQuery) {
        Pagination<ProjectWikiRepositoryEntity>  pagination = projectWikiRepositoryDao.findProjectWikiRepositoryPage(projectWikiRepositoryQuery);

        List<ProjectWikiRepository> projectWikiRepositoryList = BeanMapper.mapList(pagination.getDataList(),ProjectWikiRepository.class);

        joinTemplate.joinQuery(projectWikiRepositoryList);

        return PaginationBuilder.build(pagination,projectWikiRepositoryList);
    }
}