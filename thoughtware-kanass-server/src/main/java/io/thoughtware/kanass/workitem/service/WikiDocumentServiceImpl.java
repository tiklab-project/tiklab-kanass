package io.thoughtware.kanass.workitem.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.kanass.project.wiki.model.DocumentQuery;
import io.thoughtware.kanass.project.wiki.model.KanassDocument;
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

import java.util.ArrayList;
import java.util.List;
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

        DocumentQuery documentQuery = new DocumentQuery();
        documentQuery.setRepositoryId(workItemDocumentQuery.getRepositoryId());
        documentQuery.setRepositoryIds(workItemDocumentQuery.getRepositoryIds());

        documentQuery.setPageParam(workItemDocumentQuery.getPageParam());
        documentQuery.setName(workItemDocumentQuery.getName());
        List<WorkItemDocument> workItemDocumentList = workItemDocumentService.findWorkItemDocumentList(workItemDocumentQuery);
        List<String> workItemDocumentIds = workItemDocumentList.stream().map(workItemDocument -> workItemDocument.getDocumentId()).collect(Collectors.toList());

        int sizeId = workItemDocumentIds.size();
        String[] stringIds = new String[sizeId];
        String[] documentIds = workItemDocumentIds.toArray(stringIds);
        documentQuery.setIds(documentIds);

//        Pagination<WikiDocument> documentPage = documentServiceRpc().findDocumentPage(documentQuery);

        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        Pagination<WikiDocument> documentPage = httpRequestUtil.requestPostPage(httpHeaders, systemUrl + "/api/document/findDocumentPage", documentQuery, WikiDocument.class);

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
    public List<WikiDocument> findDocumentList(DocumentQuery documentQuery) {
        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        List<WikiDocument> wikiDocumentList = httpRequestUtil.requestPostList(httpHeaders, systemUrl + "/api/document/findDocumentList", documentQuery, WikiDocument.class);
        
        return wikiDocumentList;
    }


}
