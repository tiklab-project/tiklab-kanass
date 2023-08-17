package io.tiklab.teamwire.workitem.service;

import io.tiklab.kanass.document.model.WikiDocument;
import io.tiklab.kanass.document.model.DocumentQuery;
import io.tiklab.kanass.document.service.DocumentService;
import io.tiklab.rpc.client.router.lookup.FixedLookup;
import io.tiklab.teamwire.project.wiki.model.KanassDocument;
import io.tiklab.teamwire.support.model.SystemUrl;
import io.tiklab.teamwire.support.model.SystemUrlQuery;
import io.tiklab.teamwire.support.service.SystemUrlService;
import io.tiklab.teamwire.support.util.RpcClientTeamWireUtil;
import io.tiklab.teamwire.workitem.model.WorkItemDocument;
import io.tiklab.teamwire.workitem.model.WorkItemDocumentQuery;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;

import io.tiklab.teamwire.workitem.dao.WorkItemDocumentDao;
import io.tiklab.teamwire.workitem.entity.WorkItemDocumentEntity;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
import io.tiklab.rpc.annotation.Exporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    JoinTemplate joinTemplate;

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
    public void delete(@NotNull String documentId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(WorkItemDocumentEntity.class)
                .eq("documentId", documentId)
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
                DocumentQuery documentQuery = new DocumentQuery();
                documentQuery.setId(workItemDocument.getDocumentId());
                WikiDocument wikiDocument = documentServiceRpc().findDocument(workItemDocument.getDocumentId());
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