package io.tiklab.teamwire.project.test.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.rpc.client.router.lookup.FixedLookup;
import io.tiklab.teamwire.project.test.dao.ProjectTestRepositoryDao;
import io.tiklab.teamwire.project.test.entity.ProjectTestRepositoryEntity;
import io.tiklab.teamwire.project.test.model.ProjectTestRepository;
import io.tiklab.teamwire.project.test.model.ProjectTestRepositoryQuery;
import io.tiklab.teamwire.project.test.model.TestRepository;
import io.tiklab.teamwire.support.model.SystemUrl;
import io.tiklab.teamwire.support.service.SystemUrlService;
import io.tiklab.teamwire.support.support.SystemId;
import io.tiklab.teamwire.support.util.RpcClientTeamWireUtil;
import io.tiklab.teston.repository.model.Repository;
import io.tiklab.teston.repository.service.RepositoryService;
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
public class ProjectTestRepositoryImpl implements ProjectTestRepositoryService {

    @Autowired
    ProjectTestRepositoryDao projectTestRepositoryDao;

    @Autowired
    JoinTemplate joinTemplate;


    @Autowired
    TestRepositoryService testRepositoryService;

    @Autowired
    SystemUrlService systemUrlService;
    RepositoryService repositoryServiceRpc(){
        SystemUrl systemUrl = systemUrlService.findSystemUrl(SystemId.TESTON_ID);
        String url = systemUrl.getSystemUrl();
        return new RpcClientTeamWireUtil().rpcClient().getBean(RepositoryService.class, new FixedLookup(url));
    }

    @Override
    public String createProjectTestRepository(@NotNull @Valid ProjectTestRepository projectTestRepository) {
        ProjectTestRepositoryEntity projectTestRepositoryEntity = BeanMapper.map(projectTestRepository, ProjectTestRepositoryEntity.class);

        return projectTestRepositoryDao.createProjectTestRepository(projectTestRepositoryEntity);
    }

    @Override
    public void updateProjectTestRepository(@NotNull @Valid ProjectTestRepository projectTestRepository) {
        ProjectTestRepositoryEntity projectTestRepositoryEntity = BeanMapper.map(projectTestRepository, ProjectTestRepositoryEntity.class);

        projectTestRepositoryDao.updateProjectTestRepository(projectTestRepositoryEntity);
    }

    @Override
    public void deleteProjectTestRepository(@NotNull String id) {
        projectTestRepositoryDao.deleteProjectTestRepository(id);
    }

    @Override
    public void deleteProjectTestRepositoryByCondition(@NotNull String repositoryId, @NotNull String projectId) {
        ProjectTestRepositoryQuery projectTestRepositoryQuery = new ProjectTestRepositoryQuery();
        projectTestRepositoryQuery.setProjectId(projectId);
        projectTestRepositoryQuery.setTestRepositoryId(repositoryId);
        List<ProjectTestRepositoryEntity> projectTestRepositoryList = projectTestRepositoryDao.findProjectTestRepositoryList(projectTestRepositoryQuery);
        for (ProjectTestRepositoryEntity projectTestRepository : projectTestRepositoryList) {
            projectTestRepositoryDao.deleteProjectTestRepository(projectTestRepository.getId());
        }

    }

    @Override
    public ProjectTestRepository findOne(String id) {
        ProjectTestRepositoryEntity projectTestRepositoryEntity = projectTestRepositoryDao.findProjectTestRepository(id);

        ProjectTestRepository projectTestRepository = BeanMapper.map(projectTestRepositoryEntity, ProjectTestRepository.class);
        return projectTestRepository;
    }

    @Override
    public List<ProjectTestRepository> findList(List<String> idList) {
        List<ProjectTestRepositoryEntity> projectTestRepositoryEntityList = projectTestRepositoryDao.findProjectTestRepositoryList(idList);

        List<ProjectTestRepository> projectTestRepositoryList =  BeanMapper.mapList(projectTestRepositoryEntityList,ProjectTestRepository.class);
        return projectTestRepositoryList;
    }

    @Override
    public List<TestRepository> findUnProjectTestRepository(@NotNull String projectId) {
        ProjectTestRepositoryQuery projectTestRepositoryQuery = new ProjectTestRepositoryQuery();
        projectTestRepositoryQuery.setProjectId(projectId);
        List<TestRepository> projectTestRepositoryList = findProjectTestRepositoryList(projectTestRepositoryQuery);
        List<String> repositoryIds = projectTestRepositoryList.stream().map(projectTestRepository -> projectTestRepository.getId()).collect(Collectors.toList());

        List<TestRepository> allRepository = testRepositoryService.findAllRepository();

        List<TestRepository> repositoryList = allRepository.stream().filter(repository -> !repositoryIds.contains(repository.getId())).collect(Collectors.toList());


        return repositoryList;
    }

    @Override
    public ProjectTestRepository findProjectTestRepository(@NotNull String id) {
        ProjectTestRepository projectTestRepository = findOne(id);

        joinTemplate.joinQuery(projectTestRepository);

        return projectTestRepository;
    }

    @Override
    public List<ProjectTestRepository> findAllProjectTestRepository() {
        List<ProjectTestRepositoryEntity> projectTestRepositoryEntityList =  projectTestRepositoryDao.findAllProjectTestRepository();

        List<ProjectTestRepository> projectTestRepositoryList =  BeanMapper.mapList(projectTestRepositoryEntityList,ProjectTestRepository.class);

        joinTemplate.joinQuery(projectTestRepositoryList);

        return projectTestRepositoryList;
    }

    @Override
    public List<TestRepository> findProjectTestRepositoryList(ProjectTestRepositoryQuery projectTestRepositoryQuery) {
        List<ProjectTestRepositoryEntity> projectTestRepositoryEntityList = projectTestRepositoryDao.findProjectTestRepositoryList(projectTestRepositoryQuery);

        List<ProjectTestRepository> projectTestRepositoryList = BeanMapper.mapList(projectTestRepositoryEntityList,ProjectTestRepository.class);

        List<String> repositoryIds = projectTestRepositoryList.stream().map(projectTestRepository -> projectTestRepository.getTestRepository().getId()).collect(Collectors.toList());

        List<TestRepository> repositoryList = new ArrayList<TestRepository>();

        if(repositoryIds.size() > 0){
            repositoryList = testRepositoryService.findList(repositoryIds);

        }


        return repositoryList;
    }

    @Override
    public Pagination<ProjectTestRepository> findProjectTestRepositoryPage(ProjectTestRepositoryQuery projectTestRepositoryQuery) {
        Pagination<ProjectTestRepositoryEntity>  pagination = projectTestRepositoryDao.findProjectTestRepositoryPage(projectTestRepositoryQuery);

        List<ProjectTestRepository> projectTestRepositoryList = BeanMapper.mapList(pagination.getDataList(),ProjectTestRepository.class);

        joinTemplate.joinQuery(projectTestRepositoryList);

        return PaginationBuilder.build(pagination,projectTestRepositoryList);
    }
}