package io.tiklab.kanass.test.workItemData.service;


import io.tiklab.core.page.Pagination;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.flow.transition.model.Transition;
import io.tiklab.flow.transition.model.TransitionQuery;
import io.tiklab.flow.transition.service.TransitionService;
import io.tiklab.kanass.test.workItemBind.model.WorkItemBind;
import io.tiklab.kanass.test.workItemBind.model.WorkItemBindQuery;
import io.tiklab.kanass.test.workItemBind.service.WorkItemBindService;
import io.tiklab.kanass.test.workItemData.model.WorkItemTestOnQuery;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.model.WorkItemQuery;
import io.tiklab.kanass.workitem.service.WorkItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * teamwire 需求 缺陷
 */
@Service
public class WorkItemDataServiceImpl implements WorkItemDataService {

    @Autowired
    WorkItemBindService workItemBindService;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    TransitionService transitionService;


    @Override
    public Pagination<WorkItem> findWorkItemList(WorkItemTestOnQuery workItemTestOnQuery) {

        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setWorkTypeCode(workItemTestOnQuery.getWorkTypeCode());
        workItemQuery.setProjectId(workItemTestOnQuery.getProjectId());
        workItemQuery.setTitle(workItemTestOnQuery.getName());
        workItemQuery.setPageParam(workItemTestOnQuery.getPageParam());

        Pagination<WorkItem> workItemPagination = workItemService.findConditionWorkItemPage(workItemQuery);


        if (workItemPagination == null || workItemPagination.getDataList() == null) {
            return workItemPagination;
        }

        String caseId = workItemTestOnQuery.getCaseId();
        WorkItemBindQuery workItemBindQuery = new WorkItemBindQuery();
        workItemBindQuery.setCaseId(caseId);
        List<WorkItemBind> workItemBindList = workItemBindService.findWorkItemBindList(workItemBindQuery);

        if (workItemBindList == null) {
            workItemBindList = Collections.emptyList();
        }

        // 获取绑定的WorkItem ID
        Set<String> workItemBindIds = workItemBindList.stream()
                .map(bind -> bind.getWorkItem().getId())
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        // 过滤掉已绑定的 WorkItem
        List<WorkItem> filteredList = workItemPagination.getDataList().stream()
                .filter(item -> !workItemBindIds.contains(item.getId()))
                .collect(Collectors.toList());

        // 设置过滤后的数据
        workItemPagination.setDataList(filteredList);

        return workItemPagination;
    }

    @Override
    public WorkItem findWorkItem(String id) {
        WorkItem workItem = workItemService.findWorkItem(id);


        return workItem;
    }

    @Override
    public List<Transition> findTransitionList(TransitionQuery transitionQuery) {
        String loginId = LoginContext.getLoginId();
        transitionQuery.setUserId(loginId);
        List<Transition> transitionList = transitionService.findTransitionList(transitionQuery);
        return transitionList;
    }


}
