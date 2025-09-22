package io.tiklab.kanass.project.appraised.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.kanass.project.appraised.dao.AppraisedItemDao;
import io.tiklab.kanass.project.appraised.entity.AppraisedItemEntity;
import io.tiklab.kanass.project.appraised.model.Appraised;
import io.tiklab.kanass.project.appraised.model.AppraisedItem;
import io.tiklab.kanass.project.appraised.model.AppraisedItemQuery;
import io.tiklab.kanass.project.wiki.model.ProjectDocument;
import io.tiklab.kanass.project.wiki.model.ProjectDocumentQuery;
import io.tiklab.kanass.project.wiki.service.ProjectDocumentService;
import io.tiklab.kanass.test.test.model.TestCase;
import io.tiklab.kanass.test.test.model.TestCaseQuery;
import io.tiklab.kanass.test.test.service.TestCaseService;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.model.WorkItemQuery;
import io.tiklab.kanass.workitem.service.WorkItemService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//@Service
public class AppraisedItemServiceImpl implements AppraisedItemService {

    @Autowired
    private AppraisedItemDao appraisedItemDao;

    @Autowired
    private JoinTemplate joinTemplate;

    @Autowired
    private AppraisedService appraisedService;

    @Autowired
    private WorkItemService workItemService;

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private ProjectDocumentService projectDocumentService;

    @Override
    public String createAppraisedItem(AppraisedItem appraisedItem) {
//        List<AppraisedItemEntity> list = new ArrayList<>();
        String appraisedId = appraisedItem.getAppraised().getId();
        for (String targetId : appraisedItem.getTargetIds()) {
            Date nowDate = new Date();
            AppraisedItemEntity entity = new AppraisedItemEntity();
            entity.setTargetId(targetId);
            entity.setAppraisedId(appraisedId);
            entity.setAppraisedItemState("0");// 未评审
            entity.setAppraisedTypeId(appraisedItem.getAppraisedType().getId());

            entity.setCreateTime(new Timestamp(nowDate.getTime()));
            entity.setUpdateTime(new Timestamp(nowDate.getTime()));

            appraisedItemDao.createAppraisedItem(entity);
        }
        return "";
    }

    @Override
    public void updateAppraisedItem(AppraisedItem appraisedItem) {
        AppraisedItemEntity map = BeanMapper.map(appraisedItem, AppraisedItemEntity.class);
        appraisedItemDao.updateAppraisedItem(map);
    }

    @Override
    public void deleteAppraisedItem(String id) {
        appraisedItemDao.deleteAppraisedItem(id);
    }

    @Override
    public AppraisedItem findOne(String id) {
        AppraisedItemEntity entity = appraisedItemDao.findAppraisedItem(id);
        AppraisedItem appraisedItem = BeanMapper.map(entity, AppraisedItem.class);
        return appraisedItem;
    }

    @Override
    public AppraisedItem findAppraisedItem(String id) {
        AppraisedItem one = findOne(id);
        this.getTarget( one);
        joinTemplate.joinQuery(one, new String[]{"appraised"});
        return one;
    }

    @Override
    public List<AppraisedItem> findList(List<String> idList) {
        List<AppraisedItemEntity> list = appraisedItemDao.findAppraisedItemList(idList);
        List<AppraisedItem> mapList = BeanMapper.mapList(list, AppraisedItem.class);
        return mapList;
    }

    @Override
    public List<AppraisedItem> findAppraisedItemList(List<String> idList) {
        List<AppraisedItem> list = findList(idList);
        joinTemplate.joinQuery(list, new String[]{"appraised"});
        return list;
    }

    @Override
    public List<AppraisedItem> findAll() {
        List<AppraisedItemEntity> all = appraisedItemDao.findAllAppraisedItem();
        List<AppraisedItem> items = BeanMapper.mapList(all, AppraisedItem.class);
        return items;
    }

    @Override
    public List<AppraisedItem> findAppraisedItemList(AppraisedItemQuery appraisedItemQuery) {
        List<AppraisedItemEntity> appraisedList = appraisedItemDao.findAppraisedList(appraisedItemQuery);
        List<AppraisedItem> items = BeanMapper.mapList(appraisedList, AppraisedItem.class);
        for (AppraisedItem item : items) {
            this.getTarget( item);
        }
        joinTemplate.joinQuery(items, new String[]{"appraised"});
        return items;
    }

    @Override
    public Pagination<AppraisedItem> findAppraisedItemPage(AppraisedItemQuery appraisedItemQuery) {
        Pagination<AppraisedItemEntity> appraisedPage = appraisedItemDao.findAppraisedPage(appraisedItemQuery);

        List<AppraisedItem> items = BeanMapper.mapList(appraisedPage.getDataList(), AppraisedItem.class);

        joinTemplate.joinQuery(items, new String[]{"appraised"});

        for (AppraisedItem item : items) {
            this.getTarget( item);
//            joinTemplate.joinQuery(item.getWorkItem(), new String[]{"parentWorkItem", "preDependWorkItem", "project", "workType", "workTypeSys", "workPriority", "workStatus", "workStatusNode", "module", "sprint", "stage", "projectVersion", "builder", "assigner", "reporter"});
        }

        return PaginationBuilder.build(appraisedPage, items);
    }

    @Override
    public Pagination<WorkItem> findCanConnectWorkItemPage(AppraisedItemQuery appraisedItemQuery) {
        String appraisedId = appraisedItemQuery.getAppraisedId();
        // 查询评审对应的项目
        Appraised appraised = appraisedService.findAppraised(appraisedId);
        String projectId = appraised.getProject().getId();

        List<AppraisedItem> appraisedItemList = findAppraisedItemList(appraisedItemQuery);
        List<String> alreadyConnectWorkItemIdList = appraisedItemList.stream().map(obj -> obj.getTargetId()).collect(Collectors.toList());

        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setPageParam(appraisedItemQuery.getPageParam());
        workItemQuery.setWorkTypeCode("demand");// 需求才要评审
        workItemQuery.setIdNotIn(alreadyConnectWorkItemIdList.stream().toArray(String[]::new));
        workItemQuery.setProjectId(projectId);
        Pagination<WorkItem> workItemPage = workItemService.findWorkItemPage(workItemQuery);

        return workItemPage;
    }

    /**
     * 查询能关联的文档列表
     *
     * @param appraisedItemQuery
     * @return
     */
    @Override
    public Pagination<ProjectDocument> findCanConnectDocumentPage(AppraisedItemQuery appraisedItemQuery) {
        String appraisedId = appraisedItemQuery.getAppraisedId();
        // 查询评审对应的项目
        Appraised appraised = appraisedService.findAppraised(appraisedId);
        String projectId = appraised.getProject().getId();

        List<AppraisedItem> appraisedItemList = findAppraisedItemList(appraisedItemQuery);
        List<String> alreadyConnectItemIdList = appraisedItemList.stream().map(obj -> obj.getTargetId()).collect(Collectors.toList());

        ProjectDocumentQuery documentQuery = new ProjectDocumentQuery();
        documentQuery.setPageParam(appraisedItemQuery.getPageParam());
        documentQuery.setIdNotIn(alreadyConnectItemIdList.stream().toArray(String[]::new));
        documentQuery.setProjectId(projectId);
        Pagination<ProjectDocument> projectDocumentPage = projectDocumentService.findProjectDocumentPage(documentQuery);

        return projectDocumentPage;
    }

    /**
     * 查询能关联的用例列表
     *
     * @param appraisedItemQuery
     * @return
     */
    @Override
    public Pagination<TestCase> findCanConnectTestCasePage(AppraisedItemQuery appraisedItemQuery) {
        String appraisedId = appraisedItemQuery.getAppraisedId();
        // 查询评审对应的项目
        Appraised appraised = appraisedService.findAppraised(appraisedId);
        String projectId = appraised.getProject().getId();

        List<AppraisedItem> appraisedItemList = findAppraisedItemList(appraisedItemQuery);
        List<String> alreadyConnectItemIdList = appraisedItemList.stream().map(obj -> obj.getTargetId()).collect(Collectors.toList());

        TestCaseQuery testCaseQuery = new TestCaseQuery();
        testCaseQuery.setPageParam(appraisedItemQuery.getPageParam());
        testCaseQuery.setNotInList(alreadyConnectItemIdList.stream().toArray(String[]::new));
        testCaseQuery.setProjectId(projectId);
        Pagination<TestCase> testCasePage = testCaseService.findTestCasePage(testCaseQuery);

        return testCasePage;
    }

    /**
     * 查询并填充评审对象
     * @param appraisedItem
     */
    public void getTarget(AppraisedItem appraisedItem){
        if (appraisedItem == null){
            return;
        }
        switch (appraisedItem.getAppraisedType().getId()){
            // 需求评审
            case "000000":
                WorkItem workItem = workItemService.findWorkItem(appraisedItem.getTargetId());
                appraisedItem.setWorkItem(workItem);
                break;
            // 用例评审
            case "111111":
                TestCase testCase = testCaseService.findTestCase(appraisedItem.getTargetId());
                appraisedItem.setTestCase(testCase);
                break;
            case "222222":
            case "333333":
                ProjectDocument projectDocument = projectDocumentService.findProjectDocument(appraisedItem.getTargetId());
                appraisedItem.setProjectDocument(projectDocument);
                break;
            default:
                break;
        }
    }
}
