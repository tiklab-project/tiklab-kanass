package io.tiklab.teamwire.project.test.service;


import io.tiklab.join.JoinTemplate;
import io.tiklab.kanass.repository.service.WikiRepositoryService;
import io.tiklab.rpc.client.router.lookup.FixedLookup;
import io.tiklab.teamwire.project.test.model.TestRepository;
import io.tiklab.teamwire.support.model.SystemUrl;
import io.tiklab.teamwire.support.model.SystemUrlQuery;
import io.tiklab.teamwire.support.service.SystemUrlService;
import io.tiklab.teamwire.support.support.SystemId;
import io.tiklab.teamwire.support.util.RpcClientTeamWireUtil;
import io.tiklab.teston.repository.model.Repository;
import io.tiklab.teston.repository.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestRepositoryServiceImpl implements TestRepositoryService {

    @Autowired
    SystemUrlService systemUrlService;

    @Autowired
    JoinTemplate joinTemplate;

    RepositoryService repositoryServiceRpc(){

        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("teston");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return new RpcClientTeamWireUtil().rpcClient().getBean(RepositoryService.class, new FixedLookup(url));
    }

    @Override
    public List<TestRepository> findAllRepository() {
        List<Repository> allRepository = repositoryServiceRpc().findAllRepository();
        List<TestRepository> testRepositoryList = new ArrayList<>();
        for (Repository repository : allRepository) {
            TestRepository testRepository = new TestRepository();
            testRepository.setId(repository.getId());
            testRepository.setUserName(repository.getUser().getNickname());
            testRepository.setTestRepositoryName(repository.getName());
            testRepository.setIconUrl(repository.getIconUrl());
            testRepositoryList.add(testRepository);
        }

        return  testRepositoryList;
    }

    @Override
    public List<TestRepository> findList(List<String> idList) {
        List<Repository> list = repositoryServiceRpc().findList(idList);
        joinTemplate.joinQuery(list);
        List<TestRepository> testRepositoryList = new ArrayList<>();
        for (Repository repository : list) {
            TestRepository testRepository = new TestRepository();
            testRepository.setId(repository.getId());
            testRepository.setUserName(repository.getUser().getNickname());
            testRepository.setTestRepositoryName(repository.getName());
            testRepository.setIconUrl(repository.getIconUrl());
            testRepositoryList.add(testRepository);
        }
        return testRepositoryList;
    }
}
