package io.tiklab.kanass.project.appraised.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.appraised.model.Appraised;
import io.tiklab.kanass.project.appraised.model.AppraisedWorkItem;
import io.tiklab.kanass.project.appraised.model.AppraisedWorkItemQuery;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import java.util.List;

@JoinProvider(model = AppraisedWorkItem.class)
public interface AppraisedWorkItemService {
    /**
     * 新建评审项
     * @param appraisedWorkItem
     * @return
     */
    String createAppraisedWorkItem(AppraisedWorkItem appraisedWorkItem);

    /**
     * 更新评审项
     * @param appraisedWorkItem
     */
    void updateAppraisedWorkItem(AppraisedWorkItem appraisedWorkItem);

    /**
     * 删除评审项
     * @param id
     */
    void deleteAppraisedWorkItem(String id);

    /**
     * 查询单个
     * @param id
     * @return
     */
    @FindOne
    AppraisedWorkItem findOne(String id);

    AppraisedWorkItem findAppraisedWorkItem(String id);

    /**
     * 查询多个评审项
     * @param idList
     * @return
     */
    @FindList
    List<AppraisedWorkItem> findList(List<String> idList);
    List<AppraisedWorkItem> findAppraisedWorkItemList(List<String> idList);

    /**
     * 查询所有评审项
     * @return
     */
    @FindAll
    List<AppraisedWorkItem> findAll();

    /**
     * 查询评审项列表
     * @param appraisedWorkItemQuery
     * @return
     */
    List<AppraisedWorkItem> findAppraisedWorkItemList(AppraisedWorkItemQuery appraisedWorkItemQuery);

    /**
     * 按分页查询评审项
     * @param appraisedWorkItemQuery
     * @return
     */
    Pagination<AppraisedWorkItem> findAppraisedWorkItemPage(AppraisedWorkItemQuery appraisedWorkItemQuery);

    /**
     * 查询能关联的事项列表
     * @param appraisedWorkItemQuery
     * @return
     */
    Pagination<WorkItem> findCanConnectWorkItemPage(AppraisedWorkItemQuery appraisedWorkItemQuery);
}
