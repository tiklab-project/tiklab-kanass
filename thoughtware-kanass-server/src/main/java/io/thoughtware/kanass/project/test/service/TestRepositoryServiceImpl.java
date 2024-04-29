package io.thoughtware.kanass.project.test.service;

import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.kanass.project.test.model.TestRepository;
import io.thoughtware.kanass.support.model.SystemUrl;
import io.thoughtware.kanass.support.model.SystemUrlQuery;
import io.thoughtware.kanass.support.service.SystemUrlService;
import io.thoughtware.kanass.support.util.HttpRequestUtil;
import io.thoughtware.kanass.project.test.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestRepositoryServiceImpl implements TestRepositoryService {

    @Autowired
    SystemUrlService systemUrlService;

    @Autowired
    HttpRequestUtil httpRequestUtil;
    @Autowired
    JoinTemplate joinTemplate;

    String getSystemUrl(){

        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("teston");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return url;
    }

    @Override
    public List<TestRepository> findAllRepository() {
        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        List<Repository> allRepository = httpRequestUtil.requestPostList(httpHeaders, systemUrl + "/api/repository/findAllRepository", null, Repository.class);


//        List<Repository> allRepository = repositoryServiceRpc().findAllRepository();
        List<TestRepository> testRepositoryList = new ArrayList<>();
        for (Repository repository : allRepository) {
            TestRepository testRepository = new TestRepository();
            testRepository.setId(repository.getId());
            testRepository.setUser(repository.getUser().getNickname());
            testRepository.setTestRepositoryName(repository.getName());
            testRepository.setIconUrl(repository.getIconUrl());
            testRepositoryList.add(testRepository);
        }

        return  testRepositoryList;
    }

    @Override
    public List<TestRepository> findList(List<String> idList) {

        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        List<Repository> list = httpRequestUtil.requestPostList(httpHeaders, systemUrl + "/api/repository/findList", idList, Repository.class);

//        List<Repository> list = repositoryServiceRpc().findList(idList);
        joinTemplate.joinQuery(list);
        List<TestRepository> testRepositoryList = new ArrayList<>();
        for (Repository repository : list) {
            TestRepository testRepository = new TestRepository();
            testRepository.setId(repository.getId());
            testRepository.setUser(repository.getUser().getNickname());
            testRepository.setTestRepositoryName(repository.getName());
            testRepository.setIconUrl(repository.getIconUrl());
            testRepositoryList.add(testRepository);
        }
        return testRepositoryList;
    }
}
