package io.tiklab.kanass.workitem.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.core.exception.SystemException;
import io.tiklab.kanass.common.ErrorCode;
import io.tiklab.kanass.project.wiki.model.KanassRepository;
import io.tiklab.kanass.project.wiki.model.WikiRepository;
import io.tiklab.kanass.support.model.SystemUrl;
import io.tiklab.kanass.support.model.SystemUrlQuery;
import io.tiklab.kanass.support.service.SystemUrlService;
import io.tiklab.kanass.support.util.HttpRequestUtil;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

//    UserProcessor userServiceRpc(){
//        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
//        systemUrlQuery.setName("sward");
//        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
//        String url = systemUrlList.get(0).getSystemUrl();
//        return new RpcClientTeamWireUtil().rpcClient().getBean(UserProcessor.class, new FixedLookup(url));
//    }


    @Override
    public List<KanassRepository> findAllRepository() {
        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        List<WikiRepository> allRepository = httpRequestUtil.requestPostList(httpHeaders, systemUrl + "/api/repository/findAllRepository", null, WikiRepository.class);

        List<KanassRepository> kanassRepositoryList = new ArrayList<KanassRepository>();
        for (WikiRepository wikiRepository : allRepository) {
            if (wikiRepository.getLimits().equals("1")){
                // 私密知识库不关联
                continue;
            }
            KanassRepository kanassRepository = new KanassRepository();
            kanassRepository.setId(wikiRepository.getId());
            kanassRepository.setKanassRepositoryName(wikiRepository.getName());
            kanassRepository.setUserName(wikiRepository.getMaster().getNickname());
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
            if(!Objects.isNull(wikiRepository)){
                KanassRepository kanassRepository = new KanassRepository();
                kanassRepository.setId(wikiRepository.getId());
                kanassRepository.setKanassRepositoryName(wikiRepository.getName());
                kanassRepository.setUserName(wikiRepository.getMaster().getNickname());
                kanassRepository.setCreateTime(wikiRepository.getCreateTime());
                kanassRepository.setIconUrl(wikiRepository.getIconUrl());
                kanassRepositoryList.add(kanassRepository);
            }

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

    /**
     * 创建知识库
     *
     * @return
     */
    @Override
    public String createRepository(WikiRepository wikiRepository) {
        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        JSONObject paramJson = new JSONObject();
        paramJson.put("name", wikiRepository.getName());
        paramJson.put("desc", wikiRepository.getDesc());
        paramJson.put("limits", wikiRepository.getLimits());
        paramJson.put("master", wikiRepository.getMaster());
        paramJson.put("iconUrl", wikiRepository.getIconUrl());
        JSONObject jsonObject = httpRequestUtil.sendPost(httpHeaders, systemUrl + "/api/repository/createRepositoryByValidCreateUser", paramJson);

        // 解析结果
        if (Objects.isNull(jsonObject)){
            throw new ApplicationException(ErrorCode.OTHER_ERROR,"获取接口返回数据为空！");
        }
        Integer code = jsonObject.getInteger("code");
        if (code != 0){
            String msg = jsonObject.getString("msg");
            throw new SystemException(msg);
        }
        String wikiRepositoryId = jsonObject.getString("data");
        if (Objects.isNull(wikiRepositoryId)){
            return null;
        }

        return wikiRepositoryId;
    }

}
