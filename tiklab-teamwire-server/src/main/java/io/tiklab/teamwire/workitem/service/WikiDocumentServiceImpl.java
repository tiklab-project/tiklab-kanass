package io.tiklab.teamwire.workitem.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.document.model.WikiDocument;
import io.tiklab.kanass.document.model.DocumentQuery;
import io.tiklab.kanass.document.service.DocumentService;
import io.tiklab.rpc.client.router.lookup.FixedLookup;
import io.tiklab.teamwire.project.wiki.model.KanassDocument;
import io.tiklab.teamwire.support.model.SystemUrl;
import io.tiklab.teamwire.support.model.SystemUrlQuery;
import io.tiklab.teamwire.support.service.SystemUrlService;
import io.tiklab.teamwire.support.util.HttpClientTeamWireUtil;
import io.tiklab.teamwire.support.util.RpcClientTeamWireUtil;
import io.tiklab.teamwire.workitem.model.WorkItemDocument;
import io.tiklab.teamwire.workitem.model.WorkItemDocumentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WikiDocumentServiceImpl implements WikiDocumentService {

    @Autowired
    WorkItemDocumentService workItemDocumentService;

    @Autowired
    SystemUrlService systemUrlService;

    DocumentService documentServiceRpc(){
        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("kanass");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return new RpcClientTeamWireUtil().rpcClient().getBean(DocumentService.class, new FixedLookup(url));
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

        Pagination<WikiDocument> documentPage = documentServiceRpc().findDocumentPage(documentQuery);

//        HttpClientTeamWireUtil httpClientTeamWireUtil = new HttpClientTeamWireUtil();
//        httpClientTeamWireUtil.httpResposeJson("/api/" )

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
}
