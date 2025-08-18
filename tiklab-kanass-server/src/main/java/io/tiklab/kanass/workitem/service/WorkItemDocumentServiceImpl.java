package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Page;
import io.tiklab.core.page.PageEntity;
import io.tiklab.kanass.project.wiki.controller.ProjectDocumentController;
import io.tiklab.kanass.project.wiki.dao.ProjectDocumentDao;
import io.tiklab.kanass.project.wiki.entity.ProjectDocumentEntity;
import io.tiklab.kanass.project.wiki.model.*;
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
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


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

    @Autowired
    WikiDocumentService wikiDocumentService;

    @Autowired
    ProjectDocumentDao projectDocumentDao;

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

            // 查询是否已经存在
            ProjectDocumentQuery projectDocumentQuery = new ProjectDocumentQuery();
            projectDocumentQuery.setDocumentId(itemDocument.getDocumentId());
            projectDocumentQuery.setProjectId(itemDocument.getProjectId());
            List<ProjectDocumentEntity> projectDocumentList = projectDocumentDao.findProjectDocumentList(projectDocumentQuery);
            if (CollectionUtils.isEmpty(projectDocumentList)){
                ProjectDocumentEntity projectDocumentEntity = new ProjectDocumentEntity();
                projectDocumentEntity.setDocumentId(itemDocument.getDocumentId());
                projectDocumentEntity.setProjectId(itemDocument.getProjectId());
                projectDocumentEntity.setRepositoryId(itemDocument.getRepositoryId());
                projectDocumentEntity.setSort(itemDocument.getSort());
//                ProjectDocumentEntity projectDocumentEntity = BeanMapper.map(itemDocument, ProjectDocumentEntity.class);
                document = projectDocumentDao.createProjectDocument(projectDocumentEntity);
            }
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
        if (StringUtils.isBlank(workItemDocumentQuery.getName())){
            // 没有查询name的情况
            Pagination<WorkItemDocumentEntity> pagination = workItemDocumentDao.findWorkItemDocumentPage(workItemDocumentQuery);

            List<WorkItemDocument> workItemDocumentList = BeanMapper.mapList(pagination.getDataList(),WorkItemDocument.class);

//        List<KanassDocument> list = new ArrayList<KanassDocument>();
            if (!ObjectUtils.isEmpty(workItemDocumentList)){
                List<String> documentIds = workItemDocumentList.stream().map(WorkItemDocument::getDocumentId).collect(Collectors.toList());
                NodeQuery nodeQuery = new NodeQuery();
                nodeQuery.setIds(documentIds.toArray());
                nodeQuery.setType("document");
                nodeQuery.setPageParam(new Page(1,100));
                List<WikiDocument> documentList = wikiDocumentService.findDocumentList(nodeQuery);
                Map<String, WikiDocument> documentMap = documentList.stream().collect(Collectors.toMap(WikiDocument::getId, Function.identity()));
                for (WorkItemDocument workItemDocument:workItemDocumentList){

//                    HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
//                    String systemUrl = getSystemUrl();
//
//                    MultiValueMap param = new LinkedMultiValueMap<>();
//                    param.add("id", workItemDocument.getDocumentId());
//                    WikiDocument wikiDocument = httpRequestUtil.requestPost(httpHeaders, systemUrl + "/api/node/findNode", param, WikiDocument.class);

                    WikiDocument wikiDocument = documentMap.get(workItemDocument.getDocumentId());
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
        else{
            // 参数中有name，手动分页
            // 查询所有记录并填充名称
            List<WorkItemDocumentEntity> allWorkItemDocument = workItemDocumentDao.findAllWorkItemDocument();
            List<String> allDocumentIds = allWorkItemDocument.stream().map(WorkItemDocumentEntity::getDocumentId).collect(Collectors.toList());
            workItemDocumentQuery.setDocumentIds(allDocumentIds.toArray(new String[allDocumentIds.size()]));

            NodeQuery nodeQuery = new NodeQuery();
            nodeQuery.setPageParam(workItemDocumentQuery.getPageParam());
            nodeQuery.setName(workItemDocumentQuery.getName());
            nodeQuery.setType("document");
            nodeQuery.setIds(workItemDocumentQuery.getDocumentIds());
            Pagination<KanassDocument> workDocumentPage = wikiDocumentService.findWorkDocumentPage(nodeQuery);

            Map<String, KanassDocument> dataList = workDocumentPage.getDataList().stream().collect(Collectors.toMap(KanassDocument::getId, doc -> doc));
            List<WorkItemDocumentEntity> pageWorkItemDocumentEntityList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(dataList.keySet())){
                pageWorkItemDocumentEntityList = allWorkItemDocument.stream().filter(entity -> dataList.containsKey(entity.getDocumentId())).collect(Collectors.toList());
            }

            List<WorkItemDocument> workItemDocumentList = BeanMapper.mapList(pageWorkItemDocumentEntityList,WorkItemDocument.class);

            for (WorkItemDocument workItemDocument : workItemDocumentList) {
                workItemDocument.setKanassDocument(dataList.get(workItemDocument.getDocumentId()));
            }

            joinTemplate.joinQuery(workItemDocumentList, new String[]{"workItem"});

            return PaginationBuilder.build(workDocumentPage,workItemDocumentList);
        }
    }
}