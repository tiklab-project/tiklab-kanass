package io.tiklab.kanass.project.wiki.service;

import io.tiklab.core.page.Page;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.kanass.project.wiki.dao.ProjectDocumentDao;
import io.tiklab.kanass.project.wiki.entity.ProjectDocumentEntity;
import io.tiklab.kanass.project.wiki.model.*;
import io.tiklab.kanass.support.model.SystemUrl;
import io.tiklab.kanass.support.model.SystemUrlQuery;
import io.tiklab.kanass.support.service.SystemUrlService;
import io.tiklab.kanass.support.util.HttpRequestUtil;
import io.tiklab.kanass.workitem.dao.WorkItemDocumentDao;
import io.tiklab.kanass.workitem.entity.WorkItemDocumentEntity;
import io.tiklab.kanass.workitem.service.WikiDocumentService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProjectDocumentServiceImpl implements ProjectDocumentService{
    @Autowired
    ProjectDocumentDao projectDocumentDao;

    @Autowired
    HttpRequestUtil httpRequestUtil;
    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    SystemUrlService systemUrlService;

    @Autowired
    WikiDocumentService wikiDocumentService;

    @Autowired
    WorkItemDocumentDao workItemDocumentDao;

    String getSystemUrl(){
        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("sward");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return url;
    }
    @Override
    public String createProjectDocument(@NotNull List<ProjectDocument> projectDocument) {
        String document=null;

        //批量创建文档
        for (ProjectDocument itemDocument: projectDocument){
            ProjectDocumentEntity projectDocumentEntity = BeanMapper.map(itemDocument, ProjectDocumentEntity.class);
            document = projectDocumentDao.createProjectDocument(projectDocumentEntity);
        }
        return document;
    }

    @Override
    public void updateProjectDocument(@NotNull @Valid ProjectDocument projectDocument) {
        ProjectDocumentEntity projectDocumentEntity = BeanMapper.map(projectDocument, ProjectDocumentEntity.class);

        projectDocumentDao.updateProjectDocument(projectDocumentEntity);
    }

    @Override
    public void deleteProjectDocument(@NotNull String id) {
        ProjectDocumentEntity projectDocument = projectDocumentDao.findProjectDocument(id);
        projectDocumentDao.deleteProjectDocument(id);
        // 删除与事项关联的
        DeleteBuilders deleteBuilders = DeleteBuilders.createDelete(WorkItemDocumentEntity.class)
                .eq("documentId", projectDocument.getDocumentId())
                .eq("projectId", projectDocument.getProjectId());

        DeleteCondition deleteCondition = deleteBuilders.get();
        workItemDocumentDao.deleteWorkItemDocument(deleteCondition);
    }

    @Override
    public void deleteProjectDocumentList(@NotNull @Valid ProjectDocumentQuery projectDocumentQuery) {
        DeleteBuilders deleteBuilders = DeleteBuilders.createDelete(ProjectDocumentEntity.class)
                .eq("documentId", projectDocumentQuery.getDocumentId())
                .eq("workItemId", projectDocumentQuery.getWorkItemId());

        if(projectDocumentQuery.getWorkItemIds() != null && projectDocumentQuery.getWorkItemIds().length != 0){
            deleteBuilders.in("workItemId", projectDocumentQuery.getWorkItemIds());
        }
        DeleteCondition deleteCondition = deleteBuilders.get();
        projectDocumentDao.deleteProjectDocument(deleteCondition);
    }

    @Override
    public void deleteProjectDocumentRele(ProjectDocumentQuery projectDocumentQuery) {
        //通过事项id和文档id查询
        List<ProjectDocumentEntity> projectDocumentList = projectDocumentDao.findProjectDocumentList(projectDocumentQuery);
        projectDocumentDao.deleteProjectDocument(projectDocumentList.get(0).getId());
    }


    @Override
    public ProjectDocument findOne(String id) {
        ProjectDocumentEntity itemDocument = projectDocumentDao.findProjectDocument(id);

        ProjectDocument projectDocument = BeanMapper.map(itemDocument, ProjectDocument.class);
        return projectDocument;
    }

    @Override
    public List<ProjectDocument> findList(List<String> idList) {
        List<ProjectDocumentEntity> projectDocumentEntityList =  projectDocumentDao.findProjectDocumentList(idList);

        List<ProjectDocument> projectDocumentList =  BeanMapper.mapList(projectDocumentEntityList,ProjectDocument.class);
        return projectDocumentList;
    }

    @Override
    public ProjectDocument findProjectDocument(@NotNull String id) {
        ProjectDocument projectDocument = findOne(id);

        List<String> documentIds = new ArrayList<>();
        documentIds.add(projectDocument.getDocumentId());
        NodeQuery nodeQuery = new NodeQuery();
        nodeQuery.setIds(documentIds.toArray());
        nodeQuery.setType("document");
        nodeQuery.setPageParam(new Page(1,100));
        List<WikiDocument> documentList = wikiDocumentService.findDocumentList(nodeQuery);
        KanassDocument kanassDocument = new KanassDocument();
        if (CollectionUtils.isNotEmpty(documentList)){
            WikiDocument wikiDocument = documentList.get(0);
            kanassDocument.setId(wikiDocument.getId());
            kanassDocument.setDocumentName(wikiDocument.getName());
            kanassDocument.setKanassRepositoryId(wikiDocument.getWikiRepository().getId());
            kanassDocument.setKanassRepositoryName(wikiDocument.getWikiRepository().getName());
            kanassDocument.setUserName(wikiDocument.getMaster().getNickname());
            kanassDocument.setCreateTime(wikiDocument.getUpdateTime());
            kanassDocument.setDocumentType(wikiDocument.getDocumentType());
            projectDocument.setKanassDocument(kanassDocument);
        }else {

            kanassDocument.setDocumentName("文档已删除");
            kanassDocument.setId(projectDocument.getDocumentId());
            kanassDocument.setExist(false);
            projectDocument.setKanassDocument(kanassDocument);
        }

        joinTemplate.joinQuery(projectDocument, new String[]{"workItem"});
        return projectDocument;
    }

    @Override
    public List<ProjectDocument> findAllProjectDocument() {
        List<ProjectDocumentEntity> projectDocumentEntityList =  projectDocumentDao.findAllProjectDocument();

        List<ProjectDocument> projectDocumentList =  BeanMapper.mapList(projectDocumentEntityList,ProjectDocument.class);

        joinTemplate.joinQuery(projectDocumentList, new String[]{"workItem"});
        return projectDocumentList;
    }

    @Override
    public List<ProjectDocument> findProjectDocumentList(ProjectDocumentQuery projectDocumentQuery) {
        List<ProjectDocumentEntity> projectDocumentEntityList = projectDocumentDao.findProjectDocumentList(projectDocumentQuery);

        List<ProjectDocument> projectDocumentList = BeanMapper.mapList(projectDocumentEntityList,ProjectDocument.class);

        joinTemplate.joinQuery(projectDocumentList, new String[]{"workItem"});

        return projectDocumentList;
    }

    @Override
    public List<KanassDocument> findDocumentPageByProjectId(String projectId) {
        //构建查询条件
        ProjectDocumentQuery projectDocumentQuery = new ProjectDocumentQuery();
        projectDocumentQuery.setProjectId(projectId);
        //查询工作项文档列表
        List<ProjectDocument> projectDocumentList = findProjectDocumentList(projectDocumentQuery);

        List<KanassDocument> list = new ArrayList<KanassDocument>();
        if (!ObjectUtils.isEmpty(projectDocumentList)){
            for (ProjectDocument projectDocument:projectDocumentList){
                //初始化 HTTP 请求头 httpHeaders，指定内容类型为 application/json
                HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
                //获取系统url地址
                String systemUrl = getSystemUrl();

                MultiValueMap param = new LinkedMultiValueMap<>();
                param.add("id", projectDocument.getDocumentId());
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
                    kanassDocument.setId(projectDocument.getDocumentId());
                    kanassDocument.setExist(false);
                    list.add(kanassDocument);
                }
            }
        }
        return list;
    }

    @Override
    public Pagination<ProjectDocument> findProjectDocumentPage(ProjectDocumentQuery projectDocumentQuery) {
        if (StringUtils.isBlank(projectDocumentQuery.getName())){
            // 没有查询name的情况
            Pagination<ProjectDocumentEntity> pagination = projectDocumentDao.findProjectDocumentPage(projectDocumentQuery);

            List<ProjectDocument> projectDocumentList = BeanMapper.mapList(pagination.getDataList(),ProjectDocument.class);

            if (!ObjectUtils.isEmpty(projectDocumentList)){
                List<String> documentIds = projectDocumentList.stream().map(ProjectDocument::getDocumentId).collect(Collectors.toList());
                NodeQuery nodeQuery = new NodeQuery();
                nodeQuery.setIds(documentIds.toArray());
                nodeQuery.setType("document");
                nodeQuery.setPageParam(new Page(1,100));
                List<WikiDocument> documentList = wikiDocumentService.findDocumentList(nodeQuery);
                Map<String, WikiDocument> documentMap = documentList.stream().collect(Collectors.toMap(WikiDocument::getId, Function.identity()));
                for (ProjectDocument projectDocument:projectDocumentList){
                    WikiDocument wikiDocument = documentMap.get(projectDocument.getDocumentId());
                    KanassDocument kanassDocument = new KanassDocument();
                    if (!ObjectUtils.isEmpty(wikiDocument)){
                        kanassDocument.setId(wikiDocument.getId());
                        kanassDocument.setDocumentName(wikiDocument.getName());
                        kanassDocument.setKanassRepositoryId(wikiDocument.getWikiRepository().getId());
                        kanassDocument.setKanassRepositoryName(wikiDocument.getWikiRepository().getName());
                        kanassDocument.setUserName(wikiDocument.getMaster().getNickname());
                        kanassDocument.setCreateTime(wikiDocument.getUpdateTime());
                        kanassDocument.setDocumentType(wikiDocument.getDocumentType());
                        projectDocument.setKanassDocument(kanassDocument);
                    }else {
                        kanassDocument.setDocumentName("文档已删除");
                        kanassDocument.setId(projectDocument.getDocumentId());
                        kanassDocument.setExist(false);
                        projectDocument.setKanassDocument(kanassDocument);
//                    list.add(kanassDocument);
                    }
                }
            }

            joinTemplate.joinQuery(projectDocumentList, new String[]{"workItem"});

            return PaginationBuilder.build(pagination,projectDocumentList);
        }
        else{
            // 参数中有name，手动分页
            // 查询所有记录并填充名称
            List<ProjectDocumentEntity> allProjectDocument = projectDocumentDao.findAllProjectDocument();
            List<String> allDocumentIds = allProjectDocument.stream().map(ProjectDocumentEntity::getDocumentId).collect(Collectors.toList());
            projectDocumentQuery.setDocumentIds(allDocumentIds.toArray(new String[allDocumentIds.size()]));

            NodeQuery nodeQuery = new NodeQuery();
            nodeQuery.setPageParam(projectDocumentQuery.getPageParam());
            nodeQuery.setName(projectDocumentQuery.getName());
            nodeQuery.setType("document");
            nodeQuery.setIds(projectDocumentQuery.getDocumentIds());
            Pagination<KanassDocument> workDocumentPage = wikiDocumentService.findWorkDocumentPage(nodeQuery);

            Map<String, KanassDocument> dataList = workDocumentPage.getDataList().stream().collect(Collectors.toMap(KanassDocument::getId, doc -> doc));
            List<ProjectDocumentEntity> pageProjectDocumentEntityList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(dataList.keySet())){
                pageProjectDocumentEntityList = allProjectDocument.stream().filter(entity -> dataList.containsKey(entity.getDocumentId())).collect(Collectors.toList());
            }

            List<ProjectDocument> projectDocumentList = BeanMapper.mapList(pageProjectDocumentEntityList,ProjectDocument.class);

            for (ProjectDocument projectDocument : projectDocumentList) {
                projectDocument.setKanassDocument(dataList.get(projectDocument.getDocumentId()));
            }

            joinTemplate.joinQuery(projectDocumentList, new String[]{"workItem"});

            return PaginationBuilder.build(workDocumentPage,projectDocumentList);
        }
    }
}
