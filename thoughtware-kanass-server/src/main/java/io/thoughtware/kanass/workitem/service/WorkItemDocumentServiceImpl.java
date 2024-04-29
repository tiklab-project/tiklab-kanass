package io.thoughtware.kanass.workitem.service;

import io.thoughtware.kanass.project.wiki.model.KanassDocument;
import io.thoughtware.kanass.project.wiki.model.NodeQuery;
import io.thoughtware.kanass.project.wiki.model.WikiDocument;
import io.thoughtware.kanass.support.model.SystemUrl;
import io.thoughtware.kanass.support.model.SystemUrlQuery;
import io.thoughtware.kanass.support.service.SystemUrlService;
import io.thoughtware.kanass.support.util.HttpRequestUtil;
import io.thoughtware.kanass.workitem.model.WorkItemDocument;
import io.thoughtware.kanass.workitem.model.WorkItemDocumentQuery;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;

import io.thoughtware.kanass.workitem.dao.WorkItemDocumentDao;
import io.thoughtware.kanass.workitem.entity.WorkItemDocumentEntity;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.rpc.annotation.Exporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


/**
* 事项文档服务
*/
@Service
@Exporter
public class WorkItemDocumentServiceImpl implements WorkItemDocumentService {

    @Autowired
    WorkItemDocumentDao workItemDocumentDao;

    @Autowired
    HttpRequestUtil httpRequestUtil;
    @Autowired
    JoinTemplate joinTemplate;

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
    public String createWorkItemDocument(@NotNull List<WorkItemDocument> workItemDocument) {
        String document=null;

        //批量创建文档
        for (WorkItemDocument itemDocument: workItemDocument){
            WorkItemDocumentEntity workItemDocumentEntity = BeanMapper.map(itemDocument, WorkItemDocumentEntity.class);
             document = workItemDocumentDao.createWorkItemDocument(workItemDocumentEntity);
        }
        return document;
    }

    @Override
    public void updateWorkItemDocument(@NotNull @Valid WorkItemDocument workItemDocument) {
        WorkItemDocumentEntity workItemDocumentEntity = BeanMapper.map(workItemDocument, WorkItemDocumentEntity.class);

        workItemDocumentDao.updateWorkItemDocument(workItemDocumentEntity);
    }

    @Override
    public void deleteWorkItemDocument(@NotNull String id) {
        workItemDocumentDao.deleteWorkItemDocument(id);
    }

    @Override
    public void deleteWorkItemDocumentList(@NotNull @Valid WorkItemDocumentQuery workItemDocumentQuery) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(WorkItemDocumentEntity.class)
                .eq("documentId", workItemDocumentQuery.getDocumentId())
                .eq("workItemId", workItemDocumentQuery.getWorkItemId())
                .in("workItemId", workItemDocumentQuery.getWorkItemIds())
                .get();
        workItemDocumentDao.deleteWorkItemDocument(deleteCondition);
    }

    @Override
    public void deleteWorkItemDocumentRele(WorkItemDocumentQuery workItemDocumentQuery) {
        //通过事项id和文档id查询
        List<WorkItemDocumentEntity> workItemDocumentList = workItemDocumentDao.findWorkItemDocumentList(workItemDocumentQuery);
        workItemDocumentDao.deleteWorkItemDocument(workItemDocumentList.get(0).getId());
    }


    @Override
    public WorkItemDocument findOne(String id) {
        WorkItemDocumentEntity itemDocument = workItemDocumentDao.findWorkItemDocument(id);

        WorkItemDocument workItemDocument = BeanMapper.map(itemDocument, WorkItemDocument.class);
        return workItemDocument;
    }

    @Override
    public List<WorkItemDocument> findList(List<String> idList) {
        List<WorkItemDocumentEntity> workItemDocumentEntityList =  workItemDocumentDao.findWorkItemDocumentList(idList);

        List<WorkItemDocument> workItemDocumentList =  BeanMapper.mapList(workItemDocumentEntityList,WorkItemDocument.class);
        return workItemDocumentList;
    }

    @Override
    public WorkItemDocument findWorkItemDocument(@NotNull String id) {
        WorkItemDocument workItemDocument = findOne(id);

        joinTemplate.joinQuery(workItemDocument);
        return workItemDocument;
    }

    @Override
    public List<WorkItemDocument> findAllWorkItemDocument() {
        List<WorkItemDocumentEntity> workItemDocumentEntityList =  workItemDocumentDao.findAllWorkItemDocument();

        List<WorkItemDocument> workItemDocumentList =  BeanMapper.mapList(workItemDocumentEntityList,WorkItemDocument.class);

        joinTemplate.joinQuery(workItemDocumentList);
        return workItemDocumentList;
    }

    @Override
    public List<WorkItemDocument> findWorkItemDocumentList(WorkItemDocumentQuery workItemDocumentQuery) {
        List<WorkItemDocumentEntity> workItemDocumentEntityList = workItemDocumentDao.findWorkItemDocumentList(workItemDocumentQuery);

        List<WorkItemDocument> workItemDocumentList = BeanMapper.mapList(workItemDocumentEntityList,WorkItemDocument.class);

        joinTemplate.joinQuery(workItemDocumentList);

        return workItemDocumentList;
    }

    @Override
    public List<KanassDocument> findDocumentPageByWorkItemId(String workItemId) {
        WorkItemDocumentQuery workItemDocumentQuery = new WorkItemDocumentQuery();
        workItemDocumentQuery.setWorkItemId(workItemId);

        List<WorkItemDocument> workItemDocumentList = findWorkItemDocumentList(workItemDocumentQuery);

        List<KanassDocument> list = new ArrayList<KanassDocument>();
        if (!ObjectUtils.isEmpty(workItemDocumentList)){
            for (WorkItemDocument workItemDocument:workItemDocumentList){

                HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
                String systemUrl = getSystemUrl();

                MultiValueMap param = new LinkedMultiValueMap<>();
                param.add("id", workItemDocument.getDocumentId());
                WikiDocument wikiDocument = httpRequestUtil.requestPost(httpHeaders, systemUrl + "/api/node/findNode", param, WikiDocument.class);


                KanassDocument kanassDocument = new KanassDocument();
                if (!ObjectUtils.isEmpty(wikiDocument)){
                    kanassDocument.setId(wikiDocument.getId());
                    kanassDocument.setDocumentName(wikiDocument.getName());
                    kanassDocument.setKanassRepositoryId(wikiDocument.getWikiRepository().getId());
                    kanassDocument.setKanassRepositoryName(wikiDocument.getWikiRepository().getName());
                    kanassDocument.setUserName(wikiDocument.getMaster().getName());
                    kanassDocument.setCreateTime(wikiDocument.getUpdateTime());
                    list.add(kanassDocument);
                }else {
                    kanassDocument.setDocumentName("文档已删除");
                    kanassDocument.setId(workItemDocument.getDocumentId());
                    kanassDocument.setExist(false);
                    list.add(kanassDocument);
                }
            }
        }
        return list;
    }

    @Override
    public Pagination<WorkItemDocument> findWorkItemDocumentPage(WorkItemDocumentQuery workItemDocumentQuery) {
        Pagination<WorkItemDocumentEntity> pagination = workItemDocumentDao.findWorkItemDocumentPage(workItemDocumentQuery);

        List<WorkItemDocument> workItemDocumentList = BeanMapper.mapList(pagination.getDataList(),WorkItemDocument.class);

        joinTemplate.joinQuery(workItemDocumentList);

        return PaginationBuilder.build(pagination,workItemDocumentList);
    }
}