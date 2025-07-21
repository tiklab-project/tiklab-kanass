package io.tiklab.kanass.project.test.service;

import io.tiklab.kanass.project.test.model.TestPlan;
import io.tiklab.kanass.project.test.model.RepositoryTestPlan;
import io.tiklab.kanass.project.test.model.RepositoryTestPlanQuery;
import io.tiklab.kanass.support.model.SystemUrl;
import io.tiklab.kanass.support.model.SystemUrlQuery;
import io.tiklab.kanass.support.service.SystemUrlService;
import io.tiklab.kanass.support.util.HttpRequestUtil;
import io.tiklab.toolkit.join.JoinTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TestRepositoryPlanServiceImpl implements TestRepositoryPlanService {
    private static Logger logger = LoggerFactory.getLogger(TestRepositoryPlanServiceImpl.class);

    @Autowired
    SystemUrlService systemUrlService;

    @Autowired
    HttpRequestUtil httpRequestUtil;
    @Autowired
    JoinTemplate joinTemplate;

    String getSystemUrl(){

        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("testhubo");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return url;
    }


    @Override
    public List<TestPlan> listRepositoryTestPlan(String repositoryId) {
        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        RepositoryTestPlanQuery repositoryTestPlanQuery = new RepositoryTestPlanQuery();
        repositoryTestPlanQuery.setRepositoryId(repositoryId);
        List<RepositoryTestPlan> repositoryTestPlanList = httpRequestUtil.requestPostList(httpHeaders,
                systemUrl + "/api/testPlan/findTestPlanList",
                repositoryTestPlanQuery,
                RepositoryTestPlan.class);

        List<TestPlan> list = new ArrayList<>();
        for (RepositoryTestPlan repositoryTestPlan : repositoryTestPlanList) {
            TestPlan testPlan = new TestPlan();
            BeanUtils.copyProperties(repositoryTestPlan, testPlan);
            list.add(testPlan);
        }
        return list;
    }

    @Override
    public List<TestPlan> listRepositoryTestPlanByName(String name) {
        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        RepositoryTestPlanQuery repositoryTestPlanQuery = new RepositoryTestPlanQuery();
        repositoryTestPlanQuery.setName(name);
        List<RepositoryTestPlan> repositoryTestPlanList = httpRequestUtil.requestPostList(httpHeaders,
                systemUrl + "/api/testPlan/findTestPlanList",
                repositoryTestPlanQuery,
                RepositoryTestPlan.class);

        List<TestPlan> list = new ArrayList<>();
        for (RepositoryTestPlan repositoryTestPlan : repositoryTestPlanList) {
            TestPlan testPlan = new TestPlan();
            BeanUtils.copyProperties(repositoryTestPlan, testPlan);
            list.add(testPlan);
        }
        return list;
    }

//    @Override
//    public List<RepositoryTestPlan> findList(List<String> idList) {
//        return List.of();
//    }
}
