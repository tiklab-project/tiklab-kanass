package io.tiklab.kanass.workitem.service;

import io.tiklab.kanass.project.wiki.model.KanassDocument;
import io.tiklab.kanass.project.wiki.model.WikiDocument;
import io.tiklab.kanass.support.model.SystemUrl;
import io.tiklab.kanass.support.model.SystemUrlQuery;
import io.tiklab.kanass.support.service.SystemUrlService;
import io.tiklab.kanass.support.util.HttpRequestUtil;
import io.tiklab.kanass.workitem.entity.WorkItemDocumentEntity;
import io.tiklab.kanass.workitem.model.WorkItemDocument;
import io.tiklab.kanass.workitem.model.WorkItemDocumentQuery;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;

import io.tiklab.kanass.workitem.dao.WorkItemDocumentDao;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.rpc.annotation.Exporter;
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
        DeleteBuilders deleteBuilders = DeleteBuilders.createDelete(WorkItemDocumentEntity.class)
                .eq("documentId", workItemDocumentQuery.getDocumentId())
                .eq("workItemId", workItemDocumentQuery.getWorkItemId());

        if(workItemDocumentQuery.getWorkItemIds() != null && workItemDocumentQuery.getWorkItemIds().length != 0){
            deleteBuilders.in("workItemId", workItemDocumentQuery.getWorkItemIds());
        }
        DeleteCondition deleteCondition = deleteBuilders.get();
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

        joinTemplate.joinQuery(workItemDocument, new String[]{"workItem"});
        return workItemDocument;
    }

    @Override
    public List<WorkItemDocument> findAllWorkItemDocument() {
        List<WorkItemDocumentEntity> workItemDocumentEntityList =  workItemDocumentDao.findAllWorkItemDocument();

        List<WorkItemDocument> workItemDocumentList =  BeanMapper.mapList(workItemDocumentEntityList,WorkItemDocument.class);

        joinTemplate.joinQuery(workItemDocumentList, new String[]{"workItem"});
        return workItemDocumentList;
    }

    @Override
    public List<WorkItemDocument> findWorkItemDocumentList(WorkItemDocumentQuery workItemDocumentQuery) {
        List<WorkItemDocumentEntity> workItemDocumentEntityList = workItemDocumentDao.findWorkItemDocumentList(workItemDocumentQuery);

        List<WorkItemDocument> workItemDocumentList = BeanMapper.mapList(workItemDocumentEntityList,WorkItemDocument.class);

        joinTemplate.joinQuery(workItemDocumentList, new String[]{"workItem"});

        return workItemDocumentList;
    }

    @Override
    public List<KanassDocument> findDocumentPageByWorkItemId(String workItemId) {
        //构建查询条件
        WorkItemDocumentQuery workItemDocumentQuery = new WorkItemDocumentQuery();
        workItemDocumentQuery.setWorkItemId(workItemId);
        //查询工作项文档列表
        List<WorkItemDocument> workItemDocumentList = findWorkItemDocumentList(workItemDocumentQuery);

        List<KanassDocument> list = new ArrayList<KanassDocument>();
        if (!ObjectUtils.isEmpty(workItemDocumentList)){
            for (WorkItemDocument workItemDocument:workItemDocumentList){
                //初始化 HTTP 请求头 httpHeaders，指定内容类型为 application/json
                HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
                //获取系统url地址
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
                    kanassDocument.setUserName(wikiDocument.getMaster().getNickname());
                    kanassDocument.setCreateTime(wikiDocument.getUpdateTime());
                    kanassDocument.setDocumentType(wikiDocument.getDocumentType());
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

//        List<KanassDocument> list = new ArrayList<KanassDocument>();
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
                    kanassDocument.setUserName(wikiDocument.getMaster().getNickname());
                    kanassDocument.setCreateTime(wikiDocument.getUpdateTime());
                    kanassDocument.setDocumentType(wikiDocument.getDocumentType());
                    workItemDocument.setKanassDocument(kanassDocument);
//                    list.add(kanassDocument);
                }else {
                    kanassDocument.setDocumentName("文档已删除");
                    kanassDocument.setId(workItemDocument.getDocumentId());
                    kanassDocument.setExist(false);
                    workItemDocument.setKanassDocument(kanassDocument);
//                    list.add(kanassDocument);
                }
            }
        }

        joinTemplate.joinQuery(workItemDocumentList, new String[]{"workItem"});

        return PaginationBuilder.build(pagination,workItemDocumentList);
    }
}