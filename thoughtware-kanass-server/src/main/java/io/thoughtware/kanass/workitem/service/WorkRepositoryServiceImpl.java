package io.thoughtware.kanass.workitem.service;

import io.thoughtware.kanass.project.wiki.model.KanassRepository;
import io.thoughtware.kanass.project.wiki.model.WikiRepository;
import io.thoughtware.kanass.support.model.SystemUrl;
import io.thoughtware.kanass.support.model.SystemUrlQuery;
import io.thoughtware.kanass.support.service.SystemUrlService;
import io.thoughtware.kanass.support.util.HttpRequestUtil;
import io.thoughtware.user.dmUser.model.DmUser;
import io.thoughtware.user.dmUser.model.DmUserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkRepositoryServiceImpl implements WorkRepositoryService {

    @Autowired
    SystemUrlService systemUrlService;

    @Autowired
    HttpRequestUtil httpRequestUtil;

    String getSystemUrl(){
        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("sward");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return url;
    }

//    UserService userServiceRpc(){
//        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
//        systemUrlQuery.setName("sward");
//        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
//        String url = systemUrlList.get(0).getSystemUrl();
//        return new RpcClientTeamWireUtil().rpcClient().getBean(UserService.class, new FixedLookup(url));
//    }


    @Override
    public List<KanassRepository> findAllRepository() {
        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        List<WikiRepository> allRepository = httpRequestUtil.requestPostList(httpHeaders, systemUrl + "/api/repository/findAllRepository", null, WikiRepository.class);

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
        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        List<WikiRepository> list = httpRequestUtil.requestPostList(httpHeaders, systemUrl + "/api/repository/findList", idList, WikiRepository.class);


//        List<WikiRepository> list = repositoryServiceRpc().findList(idList);
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
    public List<DmUser> findRepositoryUserList(DmUserQuery dmUserQuery){
        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        List<DmUser> dmUsers = httpRequestUtil.requestPostList(httpHeaders, systemUrl + "/api/dmUser/findDmUserList", dmUserQuery, DmUser.class);

        return dmUsers;
    }

}
