package io.tiklab.teamwire.workitem.service;

import io.tiklab.kanass.repository.model.WikiRepository;
import io.tiklab.kanass.repository.service.WikiRepositoryService;
import io.tiklab.rpc.client.router.lookup.FixedLookup;
import io.tiklab.teamwire.project.wiki.model.KanassRepository;
import io.tiklab.teamwire.support.model.SystemUrl;
import io.tiklab.teamwire.support.model.SystemUrlQuery;
import io.tiklab.teamwire.support.service.SystemUrlService;
import io.tiklab.teamwire.support.util.RpcClientTeamWireUtil;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkRepositoryServiceImpl implements WorkRepositoryService {

    @Autowired
    SystemUrlService systemUrlService;

    WikiRepositoryService repositoryServiceRpc(){
        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("kanass");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return new RpcClientTeamWireUtil().rpcClient().getBean(WikiRepositoryService.class, new FixedLookup(url));
    }

    UserService userServiceRpc(){
        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("kanass");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return new RpcClientTeamWireUtil().rpcClient().getBean(UserService.class, new FixedLookup(url));
    }


    @Override
    public List<KanassRepository> findAllRepository() {
        List<WikiRepository> allRepository = repositoryServiceRpc().findAllRepository();
        List<KanassRepository> kanassRepositoryList = new ArrayList<KanassRepository>();
        for (WikiRepository wikiRepository : allRepository) {
            KanassRepository kanassRepository = new KanassRepository();
            kanassRepository.setId(wikiRepository.getId());
            kanassRepository.setKanassRepositoryName(wikiRepository.getName());
            kanassRepository.setUserName(wikiRepository.getMaster().getName());
            kanassRepository.setCreateTime(wikiRepository.getCreateTime());

            kanassRepositoryList.add(kanassRepository);
        }

        return kanassRepositoryList;
    }

    @Override
    public List<KanassRepository> findList(List<String> idList) {
        List<WikiRepository> list = repositoryServiceRpc().findList(idList);
        List<KanassRepository> kanassRepositoryList = new ArrayList<KanassRepository>();
        for (WikiRepository wikiRepository : list) {
            KanassRepository kanassRepository = new KanassRepository();
            kanassRepository.setId(wikiRepository.getId());
            kanassRepository.setKanassRepositoryName(wikiRepository.getName());
            kanassRepository.setUserName(wikiRepository.getMaster().getName());
            kanassRepository.setCreateTime(wikiRepository.getCreateTime());
            kanassRepository.setIconUrl(wikiRepository.getIconUrl());
            kanassRepositoryList.add(kanassRepository);
        }

        return kanassRepositoryList;
    }

    @Override
    public List<User> findRepositoryUserList(List<String> repositoryIds){
        List<User> allUser = userServiceRpc().findAllUser();
        return allUser;
    }

}
