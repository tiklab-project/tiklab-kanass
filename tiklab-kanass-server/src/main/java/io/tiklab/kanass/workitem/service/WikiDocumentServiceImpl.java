package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.wiki.entity.ProjectDocumentEntity;
import io.tiklab.kanass.project.wiki.model.*;
import io.tiklab.kanass.project.wiki.service.ProjectDocumentService;
import io.tiklab.kanass.support.model.SystemUrl;
import io.tiklab.kanass.support.model.SystemUrlQuery;
import io.tiklab.kanass.support.service.SystemUrlService;
import io.tiklab.kanass.support.util.HttpRequestUtil;
import io.tiklab.kanass.workitem.model.WorkItemDocument;
import io.tiklab.kanass.workitem.model.WorkItemDocumentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 事项关联的文档接口
 */
@Service
public class WikiDocumentServiceImpl implements WikiDocumentService {
    @Autowired
    HttpRequestUtil httpRequestUtil;
    @Autowired
    WorkItemDocumentService workItemDocumentService;
    @Autowired
    ProjectDocumentService projectDocumentService;

    @Autowired
    SystemUrlService systemUrlService;

    String getSystemUrl(){
        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("sward");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return url;
    }

    @Override
    public Pagination<KanassDocument> findUnRelationWorkDocumentList(WorkItemDocumentQuery workItemDocumentQuery) {

        NodeQuery nodeQuery = new NodeQuery();
        nodeQuery.setRepositoryId(workItemDocumentQuery.getRepositoryId());
        nodeQuery.setRepositoryIds(workItemDocumentQuery.getRepositoryIds());

        nodeQuery.setPageParam(workItemDocumentQuery.getPageParam());
        nodeQuery.setName(workItemDocumentQuery.getName());
        nodeQuery.setType("document");
        List<WorkItemDocument> workItemDocumentList = workItemDocumentService.findWorkItemDocumentList(workItemDocumentQuery);
        List<String> workItemDocumentIds = workItemDocumentList.stream().map(workItemDocument -> workItemDocument.getDocumentId()).collect(Collectors.toList());

        int sizeId = workItemDocumentIds.size();
        String[] stringIds = new String[sizeId];
        String[] documentIds = workItemDocumentIds.toArray(stringIds);
        nodeQuery.setNotIds(documentIds);


        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        Pagination<WikiDocument> documentPage = httpRequestUtil.requestPostPage(httpHeaders, systemUrl + "/api/node/findNodePage", nodeQuery, WikiDocument.class);

        Pagination<KanassDocument> kanassDocumentPage = new Pagination<KanassDocument>();
        kanassDocumentPage.setTotalRecord(documentPage.getTotalRecord());
        kanassDocumentPage.setTotalPage(documentPage.getTotalPage());
        kanassDocumentPage.setPageSize(documentPage.getPageSize());
        kanassDocumentPage.setCurrentPage(documentPage.getCurrentPage());

        List<KanassDocument> kanassDocumentList = new ArrayList<KanassDocument>();
        for (WikiDocument wikiDocument : documentPage.getDataList()) {
            KanassDocument kanassDocument = new KanassDocument();
            kanassDocument.setId(wikiDocument.getId());
            kanassDocument.setDocumentName(wikiDocument.getName());
            kanassDocument.setKanassRepositoryId(wikiDocument.getWikiRepository().getId());
            kanassDocument.setKanassRepositoryName(wikiDocument.getWikiRepository().getName());
            kanassDocument.setUserName(wikiDocument.getMaster().getNickname());
            kanassDocument.setCreateTime(wikiDocument.getUpdateTime());
            kanassDocumentList.add(kanassDocument);
        }
        kanassDocumentPage.setDataList(kanassDocumentList);
        return kanassDocumentPage;
    }

    @Override
    public Pagination<KanassDocument> findUnRelationProjectDocumentList(ProjectDocumentQuery projectDocumentQuery) {
        NodeQuery nodeQuery = new NodeQuery();
        nodeQuery.setRepositoryId(projectDocumentQuery.getRepositoryId());
        nodeQuery.setRepositoryIds(projectDocumentQuery.getRepositoryIds());

        nodeQuery.setPageParam(projectDocumentQuery.getPageParam());
        nodeQuery.setName(projectDocumentQuery.getName());
        nodeQuery.setType("document");
        List<ProjectDocument> projectDocumentList = projectDocumentService.findProjectDocumentList(projectDocumentQuery);
        List<String> projectDocumentIds = projectDocumentList.stream().map(item -> item.getDocumentId()).collect(Collectors.toList());

        int sizeId = projectDocumentIds.size();
        String[] stringIds = new String[sizeId];
        String[] documentIds = projectDocumentIds.toArray(stringIds);
        nodeQuery.setNotIds(documentIds);

        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        Pagination<WikiDocument> documentPage = httpRequestUtil.requestPostPage(httpHeaders, systemUrl + "/api/node/findNodePage", nodeQuery, WikiDocument.class);

        Pagination<KanassDocument> kanassDocumentPage = new Pagination<KanassDocument>();
        kanassDocumentPage.setTotalRecord(documentPage.getTotalRecord());
        kanassDocumentPage.setTotalPage(documentPage.getTotalPage());
        kanassDocumentPage.setPageSize(documentPage.getPageSize());
        kanassDocumentPage.setCurrentPage(documentPage.getCurrentPage());

        List<KanassDocument> kanassDocumentList = new ArrayList<KanassDocument>();
        for (WikiDocument wikiDocument : documentPage.getDataList()) {
            KanassDocument kanassDocument = new KanassDocument();
            kanassDocument.setId(wikiDocument.getId());
            kanassDocument.setDocumentName(wikiDocument.getName());
            kanassDocument.setKanassRepositoryId(wikiDocument.getWikiRepository().getId());
            kanassDocument.setKanassRepositoryName(wikiDocument.getWikiRepository().getName());
            kanassDocument.setUserName(wikiDocument.getMaster().getName());
            kanassDocument.setCreateTime(wikiDocument.getUpdateTime());
            kanassDocumentList.add(kanassDocument);
        }
        kanassDocumentPage.setDataList(kanassDocumentList);
        return kanassDocumentPage;
    }

    @Override
    public Pagination<KanassDocument> findWorkDocumentPage(NodeQuery nodeQuery) {

//        NodeQuery nodeQuery = new NodeQuery();
//
//        nodeQuery.setPageParam(workItemDocumentQuery.getPageParam());
//        nodeQuery.setName(workItemDocumentQuery.getName());
//        nodeQuery.setType("document");
//
//        nodeQuery.setIds(workItemDocumentQuery.getDocumentIds());


        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        Pagination<WikiDocument> documentPage = httpRequestUtil.requestPostPage(httpHeaders, systemUrl + "/api/node/findNodePage", nodeQuery, WikiDocument.class);

        Pagination<KanassDocument> kanassDocumentPage = new Pagination<KanassDocument>();
        kanassDocumentPage.setTotalRecord(documentPage.getTotalRecord());
        kanassDocumentPage.setTotalPage(documentPage.getTotalPage());
        kanassDocumentPage.setPageSize(documentPage.getPageSize());
        kanassDocumentPage.setCurrentPage(documentPage.getCurrentPage());

        List<KanassDocument> kanassDocumentList = new ArrayList<KanassDocument>();
        for (WikiDocument wikiDocument : documentPage.getDataList()) {
            KanassDocument kanassDocument = new KanassDocument();
            kanassDocument.setId(wikiDocument.getId());
            kanassDocument.setDocumentName(wikiDocument.getName());
            kanassDocument.setKanassRepositoryId(wikiDocument.getWikiRepository().getId());
            kanassDocument.setKanassRepositoryName(wikiDocument.getWikiRepository().getName());
            kanassDocument.setUserName(wikiDocument.getMaster().getName());
            kanassDocument.setCreateTime(wikiDocument.getUpdateTime());
            kanassDocumentList.add(kanassDocument);
        }
        kanassDocumentPage.setDataList(kanassDocumentList);
        return kanassDocumentPage;
    }


    @Override
    public List<WikiDocument> findDocumentList(NodeQuery nodeQuery) {
        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        Pagination<WikiDocument> wikiDocumentPage = httpRequestUtil.requestPostPage(httpHeaders, systemUrl + "/api/node/findNodePage", nodeQuery, WikiDocument.class);
        List<WikiDocument> wikiDocumentList = wikiDocumentPage.getDataList();
        return wikiDocumentList;
    }


}
