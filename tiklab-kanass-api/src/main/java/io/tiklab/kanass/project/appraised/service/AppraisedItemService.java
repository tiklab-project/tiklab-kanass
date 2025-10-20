package io.tiklab.kanass.project.appraised.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.appraised.model.AppraisedItem;
import io.tiklab.kanass.project.appraised.model.AppraisedItemQuery;
import io.tiklab.kanass.project.wiki.model.ProjectDocument;
import io.tiklab.kanass.test.testcase.test.model.TestCase;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import java.util.List;

@JoinProvider(model = AppraisedItem.class)
public interface AppraisedItemService {
    /**
     * 新建评审项
     * @param appraisedItem
     * @return
     */
    String createAppraisedItem(AppraisedItem appraisedItem);

    /**
     * 更新评审项
     * @param appraisedItem
     */
    void updateAppraisedItem(AppraisedItem appraisedItem);

    /**
     * 删除评审项
     * @param id
     */
    void deleteAppraisedItem(String id);

    /**
     * 查询单个
     * @param id
     * @return
     */
    @FindOne
    AppraisedItem findOne(String id);

    AppraisedItem findAppraisedItem(String id);

    /**
     * 查询多个评审项
     * @param idList
     * @return
     */
    @FindList
    List<AppraisedItem> findList(List<String> idList);
    List<AppraisedItem> findAppraisedItemList(List<String> idList);

    /**
     * 查询所有评审项
     * @return
     */
    @FindAll
    List<AppraisedItem> findAll();

    /**
     * 查询评审项列表
     * @param appraisedItemQuery
     * @return
     */
    List<AppraisedItem> findAppraisedItemList(AppraisedItemQuery appraisedItemQuery);

    /**
     * 按分页查询评审项
     * @param appraisedItemQuery
     * @return
     */
    Pagination<AppraisedItem> findAppraisedItemPage(AppraisedItemQuery appraisedItemQuery);

    /**
     * 查询能关联的事项列表
     * @param appraisedItemQuery
     * @return
     */
    Pagination<WorkItem> findCanConnectWorkItemPage(AppraisedItemQuery appraisedItemQuery);

    /**
     * 查询能关联的文档列表
     * @param appraisedItemQuery
     * @return
     */
    Pagination<ProjectDocument> findCanConnectDocumentPage(AppraisedItemQuery appraisedItemQuery);

    /**
     * 查询能关联的用例列表
     * @param appraisedItemQuery
     * @return
     */
    Pagination<TestCase> findCanConnectTestCasePage(AppraisedItemQuery appraisedItemQuery);
}
