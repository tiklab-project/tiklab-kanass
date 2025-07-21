package io.tiklab.kanass.project.test.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.kanass.project.test.dao.ProjectTestRepositoryPlanDao;
import io.tiklab.kanass.project.test.entity.ProjectTestRepositoryPlanEntity;
import io.tiklab.kanass.project.test.model.*;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProjectTestRepositoryPlanImpl implements ProjectTestRepositoryPlanService{

    @Autowired
    private ProjectTestRepositoryPlanDao projectTestRepositoryPlanDao;
    @Autowired
    private JoinTemplate joinTemplate;

    @Autowired
    private TestRepositoryPlanService testRepositoryPlanService;
    @Autowired
    private TestRepositoryService testRepositoryService;


    @Override
    public String createProjectTestRepositoryPlan(ProjectTestRepositoryPlan projectTestRepositoryPlan) {
        ProjectTestRepositoryPlanEntity entity = BeanMapper.map(projectTestRepositoryPlan, ProjectTestRepositoryPlanEntity.class);
        return projectTestRepositoryPlanDao.createProjectTestRepositoryPlan(entity);
    }

    @Override
    public void updateProjectTestRepositoryPlan(ProjectTestRepositoryPlan projectTestRepositoryPlan) {
        ProjectTestRepositoryPlanEntity entity = BeanMapper.map(projectTestRepositoryPlan, ProjectTestRepositoryPlanEntity.class);
        projectTestRepositoryPlanDao.updateProjectTestRepositoryPlan(entity);
    }

    @Override
    public void deleteProjectTestRepositoryPlan(String id) {
        projectTestRepositoryPlanDao.deleteProjectTestRepositoryPlan(id);
    }

    @Override
    public void deleteProjectTestRepositoryPlanByCondition(String repositoryId, String planId, String projectId) {
        ProjectTestRepositoryPlanQuery query = new ProjectTestRepositoryPlanQuery();
        query.setProjectId(projectId);
        query.setTestRepositoryId(repositoryId);
        query.setPlanId(planId);
        List<ProjectTestRepositoryPlanEntity> list = projectTestRepositoryPlanDao.findProjectTestRepositoryPlanList(query);
        for (ProjectTestRepositoryPlanEntity entity : list) {
            projectTestRepositoryPlanDao.deleteProjectTestRepositoryPlan(entity.getId());
        }
    }

    @Override
    public ProjectTestRepositoryPlan findOne(String id) {
        ProjectTestRepositoryPlanEntity entity = projectTestRepositoryPlanDao.findProjectTestRepositoryPlan(id);

        ProjectTestRepositoryPlan projectTestRepositoryPlan = BeanMapper.map(entity, ProjectTestRepositoryPlan.class);
        return projectTestRepositoryPlan;
    }

    @Override
    public List<ProjectTestRepositoryPlan> findList(List<String> idList) {
        List<ProjectTestRepositoryPlanEntity> projectTestRepositoryPlanEntityList = projectTestRepositoryPlanDao.findProjectTestRepositoryPlanList(idList);

        List<ProjectTestRepositoryPlan> list =  BeanMapper.mapList(projectTestRepositoryPlanEntityList,ProjectTestRepositoryPlan.class);
        return list;
    }

    @Override
    public ProjectTestRepositoryPlan findProjectTestRepositoryPlan(String id) {
        ProjectTestRepositoryPlan one = findOne(id);
        joinTemplate.joinQuery(one, new String[]{"testRepository", "project", "testPlan"});
        return one;
    }

    @Override
    public List<TestPlan> findUnProjectTestRepositoryPlan(String projectId, String repositoryId) {
        ProjectTestRepositoryPlanQuery projectTestRepositoryPlanQuery = new ProjectTestRepositoryPlanQuery();
        projectTestRepositoryPlanQuery.setProjectId(projectId);
        projectTestRepositoryPlanQuery.setTestRepositoryId(repositoryId);
        List<ProjectTestRepositoryPlan> projectTestRepositoryPlanList = findProjectTestRepositoryPlanList(projectTestRepositoryPlanQuery);
        List<String> idList = projectTestRepositoryPlanList.stream().map(item -> item.getTestPlan().getId()).distinct().collect(Collectors.toList());

        List<TestPlan> allPlanList = testRepositoryPlanService.listRepositoryTestPlan(repositoryId);
        List<TestPlan> testPlanList = allPlanList.stream().filter(item -> !idList.contains(item.getId())).collect(Collectors.toList());


        return testPlanList;
    }

//    @Override
//    public List<TestPlan> findAllProjectTestRepositoryPlanByRepositoryId(String repositoryId) {
//        return List.of();
//    }

    @Override
    public List<TestPlan> findAllProjectTestRepositoryPlanByRepositoryId(String repositoryId) {
        List<TestPlan> allPlanList = testRepositoryPlanService.listRepositoryTestPlan(repositoryId);
        return allPlanList;
    }

    @Override
    public List<ProjectTestRepositoryPlan> findAllProjectTestRepositoryPlan() {
        List<ProjectTestRepositoryPlanEntity> allProjectTestRepositoryPlan = projectTestRepositoryPlanDao.findAllProjectTestRepositoryPlan();
        List<ProjectTestRepositoryPlan> projectTestRepositoryPlans = BeanMapper.mapList(allProjectTestRepositoryPlan, ProjectTestRepositoryPlan.class);

        joinTemplate.joinQuery(projectTestRepositoryPlans, new String[]{"testRepository", "project", "testPlan"});
        return projectTestRepositoryPlans;
    }

    @Override
    public List<ProjectTestRepositoryPlan> findProjectTestRepositoryPlanList(ProjectTestRepositoryPlanQuery projectTestRepositoryPlanQuery) {
        List<ProjectTestRepositoryPlanEntity> projectTestRepositoryPlanEntityList = projectTestRepositoryPlanDao.findProjectTestRepositoryPlanList(projectTestRepositoryPlanQuery);
        List<ProjectTestRepositoryPlan> projectTestRepositoryPlans = BeanMapper.mapList(projectTestRepositoryPlanEntityList, ProjectTestRepositoryPlan.class);
        List<TestRepository> allRepository = testRepositoryService.findAllRepository();
        Map<String, TestRepository> repositoryMap = allRepository.stream().collect(Collectors.toMap(TestRepository::getId, Function.identity()));
        for (ProjectTestRepositoryPlan projectTestRepositoryPlan : projectTestRepositoryPlans) {
            String testRepositoryId = projectTestRepositoryPlan.getTestRepositoryId();
            TestRepository testRepository = repositoryMap.get(testRepositoryId);
            projectTestRepositoryPlan.setTestRepository(testRepository);
            List<TestPlan> testPlanList = testRepositoryPlanService.listRepositoryTestPlan(testRepositoryId);
            for (TestPlan testPlan : testPlanList) {
                if (testPlan.getId().equals(projectTestRepositoryPlan.getPlanId())){
                    projectTestRepositoryPlan.setTestPlan(testPlan);
                    break;
                }
            }
        }

        return projectTestRepositoryPlans;
    }

    @Override
    public Pagination<ProjectTestRepositoryPlan> findProjectTestRepositoryPlanPage(ProjectTestRepositoryPlanQuery projectTestRepositoryPlanQuery) {
        List<TestRepository> allRepository = testRepositoryService.findAllRepository();
        Map<String, TestRepository> repositoryMap = allRepository.stream().collect(Collectors.toMap(TestRepository::getId, Function.identity()));
        if (StringUtils.isBlank(projectTestRepositoryPlanQuery.getName())){
            Pagination<ProjectTestRepositoryPlanEntity> projectTestRepositoryPlanPage = projectTestRepositoryPlanDao.findProjectTestRepositoryPlanPage(projectTestRepositoryPlanQuery);

            List<ProjectTestRepositoryPlan> projectTestRepositoryPlans = BeanMapper.mapList(projectTestRepositoryPlanPage.getDataList(), ProjectTestRepositoryPlan.class);

            for (ProjectTestRepositoryPlan projectTestRepositoryPlan : projectTestRepositoryPlans) {
                String testRepositoryId = projectTestRepositoryPlan.getTestRepositoryId();
                TestRepository testRepository = repositoryMap.get(testRepositoryId);
                projectTestRepositoryPlan.setTestRepository(testRepository);
                List<TestPlan> testPlanList = testRepositoryPlanService.listRepositoryTestPlan(testRepositoryId);
                for (TestPlan testPlan : testPlanList) {
                    if (testPlan.getId().equals(projectTestRepositoryPlan.getPlanId())){
                        projectTestRepositoryPlan.setTestPlan(testPlan);
                        break;
                    }
                }
            }

            return PaginationBuilder.build(projectTestRepositoryPlanPage, projectTestRepositoryPlans);
        }else {
            // 查询符合条件的计划
            List<TestPlan> testPlanList = testRepositoryPlanService.listRepositoryTestPlanByName(projectTestRepositoryPlanQuery.getName());
            if (CollectionUtils.isEmpty(testPlanList)){
                return PaginationBuilder.build(new Pagination<>(), new ArrayList<>());
            }
            Map<String, TestPlan> testPlanMap = testPlanList.stream().collect(Collectors.toMap(TestPlan::getId, Function.identity()));
            projectTestRepositoryPlanQuery.setPlanIds(testPlanMap.keySet().toArray(new String[0]));
            Pagination<ProjectTestRepositoryPlanEntity> repositoryPlanPage = projectTestRepositoryPlanDao.findProjectTestRepositoryPlanPage(projectTestRepositoryPlanQuery);

            List<ProjectTestRepositoryPlan> projectTestRepositoryPlans = BeanMapper.mapList(repositoryPlanPage.getDataList(), ProjectTestRepositoryPlan.class);

            for (ProjectTestRepositoryPlan projectTestRepositoryPlan : projectTestRepositoryPlans) {
                String testRepositoryId = projectTestRepositoryPlan.getTestRepositoryId();
                TestRepository testRepository = repositoryMap.get(testRepositoryId);
                projectTestRepositoryPlan.setTestRepository(testRepository);
                projectTestRepositoryPlan.setTestPlan(testPlanMap.get(projectTestRepositoryPlan.getPlanId()));
            }
            return PaginationBuilder.build(repositoryPlanPage, projectTestRepositoryPlans);
        }
    }
}
