package io.tiklab.kanass.project.test.service;

import io.tiklab.kanass.project.test.model.TestPlan;

import java.util.List;


public interface TestRepositoryPlanService {
    List<TestPlan> listRepositoryTestPlan(String repositoryId);
//    List<RepositoryTestPlan> findList(List<String> idList);
}
