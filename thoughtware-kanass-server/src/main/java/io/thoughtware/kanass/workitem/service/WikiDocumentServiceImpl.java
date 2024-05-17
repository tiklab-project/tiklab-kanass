package io.thoughtware.kanass.workitem.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.kanass.project.wiki.model.KanassDocument;
import io.thoughtware.kanass.project.wiki.model.NodeQuery;
import io.thoughtware.kanass.project.wiki.model.WikiDocument;
import io.thoughtware.kanass.support.model.SystemUrl;
import io.thoughtware.kanass.support.model.SystemUrlQuery;
import io.thoughtware.kanass.support.service.SystemUrlService;
import io.thoughtware.kanass.support.util.HttpRequestUtil;
import io.thoughtware.kanass.workitem.model.WorkItemDocument;
import io.thoughtware.kanass.workitem.model.WorkItemDocumentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
public class WikiDocumentServiceImpl implements WikiDocumentService {
    @Autowired
    HttpRequestUtil httpRequestUtil;
    @Autowired
    WorkItemDocumentService workItemDocumentService;

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
        List<WikiDocument> wikiDocumentList = httpRequestUtil.requestPostList(httpHeaders, systemUrl + "/api/node/findNodePage", nodeQuery, WikiDocument.class);
        
        return wikiDocumentList;
    }


}
