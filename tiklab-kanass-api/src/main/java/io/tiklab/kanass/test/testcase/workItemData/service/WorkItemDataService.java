package io.tiklab.kanass.test.testcase.workItemData.service;


import io.tiklab.core.page.Pagination;
import io.tiklab.flow.transition.model.Transition;
import io.tiklab.flow.transition.model.TransitionQuery;
import io.tiklab.kanass.test.testcase.workItemData.model.WorkItemTestOnQuery;
import io.tiklab.kanass.workitem.model.WorkItem;

import java.util.List;

/**
* kanass需求 缺陷 服务接口
*/
public interface WorkItemDataService {


    /**
     *  查询kanass 查询需求 缺陷列表
     *  需求： demand
     * 缺陷： defect
     * @param workItemTestOnQuery
     * @return
     */
    Pagination<WorkItem> findWorkItemList(WorkItemTestOnQuery workItemTestOnQuery);

    /**
     * 查询通过id需求 缺陷
     * @param id
     * @return
     */
    WorkItem findWorkItem(String id);

    List<Transition> findTransitionList(TransitionQuery transitionQuery);
}