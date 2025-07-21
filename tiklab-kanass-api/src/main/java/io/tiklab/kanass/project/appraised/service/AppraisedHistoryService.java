package io.tiklab.kanass.project.appraised.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.appraised.model.AppraisedHistory;
import io.tiklab.kanass.project.appraised.model.AppraisedHistoryQuery;
import io.tiklab.kanass.workitem.model.WorkItem;

import java.util.List;

public interface AppraisedHistoryService {
    /**
     * 新建评审项
     * @param appraisedHistory
     * @return
     */
    String createAppraisedHistory(AppraisedHistory appraisedHistory);

    /**
     * 更新评审项
     * @param appraisedHistory
     */
    void updateAppraisedHistory(AppraisedHistory appraisedHistory);

    /**
     * 删除评审项
     * @param id
     */
    void deleteAppraisedHistory(String id);

    /**
     * 查询单个
     * @param id
     * @return
     */
    AppraisedHistory findOne(String id);

    AppraisedHistory findAppraisedHistory(String id);

    /**
     * 查询多个评审项
     * @param idList
     * @return
     */
    List<AppraisedHistory> findList(List<String> idList);
    List<AppraisedHistory> findAppraisedHistoryList(List<String> idList);

    /**
     * 查询所有评审项
     * @return
     */
    List<AppraisedHistory> findAll();

    /**
     * 查询评审项列表
     * @param appraisedHistoryQuery
     * @return
     */
    List<AppraisedHistory> findAppraisedHistoryList(AppraisedHistoryQuery appraisedHistoryQuery);

    /**
     * 按分页查询评审项
     * @param appraisedHistoryQuery
     * @return
     */
    Pagination<AppraisedHistory> findAppraisedHistoryPage(AppraisedHistoryQuery appraisedHistoryQuery);

}
