package io.tiklab.kanass.project.appraised.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.kanass.project.appraised.dao.AppraisedWorkItemDao;
import io.tiklab.kanass.project.appraised.entity.AppraisedEntity;
import io.tiklab.kanass.project.appraised.entity.AppraisedWorkItemEntity;
import io.tiklab.kanass.project.appraised.model.Appraised;
import io.tiklab.kanass.project.appraised.model.AppraisedWorkItem;
import io.tiklab.kanass.project.appraised.model.AppraisedWorkItemQuery;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.model.WorkItemQuery;
import io.tiklab.kanass.workitem.service.WorkItemService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.FetchProfile;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppraisedWorkItemServiceImpl implements AppraisedWorkItemService{

    @Autowired
    private AppraisedWorkItemDao appraisedWorkItemDao;

    @Autowired
    private JoinTemplate joinTemplate;

    @Autowired
    private AppraisedService appraisedService;

    @Autowired
    private WorkItemService workItemService;

    @Override
    public String createAppraisedWorkItem(AppraisedWorkItem appraisedWorkItem) {
//        List<AppraisedWorkItemEntity> list = new ArrayList<>();
        String appraisedId = appraisedWorkItem.getAppraised().getId();
        for (String workItemId : appraisedWorkItem.getWorkItemIds()) {
            AppraisedWorkItemEntity entity = new AppraisedWorkItemEntity();
            entity.setWorkItemId(workItemId);
            entity.setAppraisedId(appraisedId);
            entity.setWorkItemAppraisedState("0");// 未评审
            appraisedWorkItemDao.createAppraisedWorkItem(entity);
        }
        return "";
    }

    @Override
    public void updateAppraisedWorkItem(AppraisedWorkItem appraisedWorkItem) {
        AppraisedWorkItemEntity map = BeanMapper.map(appraisedWorkItem, AppraisedWorkItemEntity.class);
        appraisedWorkItemDao.updateAppraisedWorkItem(map);
    }

    @Override
    public void deleteAppraisedWorkItem(String id) {
        appraisedWorkItemDao.deleteAppraisedWorkItem(id);
    }

    @Override
    public AppraisedWorkItem findOne(String id) {
        AppraisedWorkItemEntity entity = appraisedWorkItemDao.findAppraisedWorkItem(id);
        AppraisedWorkItem appraisedWorkItem = BeanMapper.map(entity, AppraisedWorkItem.class);
        return appraisedWorkItem;
    }

    @Override
    public AppraisedWorkItem findAppraisedWorkItem(String id) {
        AppraisedWorkItem one = findOne(id);

        joinTemplate.joinQuery(one, new String[]{"appraised", "workItem"});
        return one;
    }

    @Override
    public List<AppraisedWorkItem> findList(List<String> idList) {
        List<AppraisedWorkItemEntity> list = appraisedWorkItemDao.findAppraisedWorkItemList(idList);
        List<AppraisedWorkItem> mapList = BeanMapper.mapList(list, AppraisedWorkItem.class);
        return mapList;
    }

    @Override
    public List<AppraisedWorkItem> findAppraisedWorkItemList(List<String> idList) {
        List<AppraisedWorkItem> list = findList(idList);
        joinTemplate.joinQuery(list, new String[]{"appraised", "workItem"});
        return list;
    }

    @Override
    public List<AppraisedWorkItem> findAll() {
        List<AppraisedWorkItemEntity> all = appraisedWorkItemDao.findAllAppraisedWorkItem();
        List<AppraisedWorkItem> items = BeanMapper.mapList(all, AppraisedWorkItem.class);
        return items;
    }

    @Override
    public List<AppraisedWorkItem> findAppraisedWorkItemList(AppraisedWorkItemQuery appraisedWorkItemQuery) {
        List<AppraisedWorkItemEntity> appraisedList = appraisedWorkItemDao.findAppraisedList(appraisedWorkItemQuery);
        List<AppraisedWorkItem> items = BeanMapper.mapList(appraisedList, AppraisedWorkItem.class);
        joinTemplate.joinQuery(items, new String[]{"appraised", "workItem"});
        return items;
    }

    @Override
    public Pagination<AppraisedWorkItem> findAppraisedWorkItemPage(AppraisedWorkItemQuery appraisedWorkItemQuery) {
        Pagination<AppraisedWorkItemEntity> appraisedPage = appraisedWorkItemDao.findAppraisedPage(appraisedWorkItemQuery);

        List<AppraisedWorkItem> items = BeanMapper.mapList(appraisedPage.getDataList(), AppraisedWorkItem.class);

        joinTemplate.joinQuery(items, new String[]{"appraised", "workItem"});

        for (AppraisedWorkItem item : items) {
            joinTemplate.joinQuery(item.getWorkItem(), new String[]{"parentWorkItem", "preDependWorkItem", "project", "workType", "workTypeSys", "workPriority", "workStatus", "workStatusNode", "module", "sprint", "stage", "projectVersion", "builder", "assigner", "reporter"});
        }

        return PaginationBuilder.build(appraisedPage, items);
    }

    @Override
    public Pagination<WorkItem> findCanConnectWorkItemPage(AppraisedWorkItemQuery appraisedWorkItemQuery) {
        String appraisedId = appraisedWorkItemQuery.getAppraisedId();
        // 查询评审对应的项目
        Appraised appraised = appraisedService.findAppraised(appraisedId);
        String projectId = appraised.getProject().getId();

        List<AppraisedWorkItem> appraisedWorkItemList = findAppraisedWorkItemList(appraisedWorkItemQuery);
        List<String> alreadyConnectWorkItemIdList = appraisedWorkItemList.stream().map(obj -> obj.getWorkItem().getId()).collect(Collectors.toList());

        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setPageParam(appraisedWorkItemQuery.getPageParam());
//        workItemQuery.setWorkTypeCode(appraisedWorkItemQuery.getWorkItemTypeCode());
        workItemQuery.setWorkTypeCode("demand");// 需求才要评审
        workItemQuery.setIdNotIn(alreadyConnectWorkItemIdList.stream().toArray(String[]::new));
        workItemQuery.setProjectId(projectId);
        Pagination<WorkItem> workItemPage = workItemService.findWorkItemPage(workItemQuery);

        return workItemPage;
    }
}
