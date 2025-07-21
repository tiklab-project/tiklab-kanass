package io.tiklab.kanass.project.appraised.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.kanass.project.appraised.dao.AppraisedHistoryDao;
import io.tiklab.kanass.project.appraised.entity.AppraisedHistoryEntity;
import io.tiklab.kanass.project.appraised.model.Appraised;
import io.tiklab.kanass.project.appraised.model.AppraisedHistory;
import io.tiklab.kanass.project.appraised.model.AppraisedHistoryQuery;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.model.WorkItemQuery;
import io.tiklab.kanass.workitem.service.WorkItemService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppraisedHistoryServiceImpl implements AppraisedHistoryService{

    @Autowired
    private AppraisedHistoryDao appraisedHistoryDao;

    @Autowired
    private JoinTemplate joinTemplate;

    @Override
    public String createAppraisedHistory(AppraisedHistory appraisedHistory) {
        appraisedHistory.setCreateTime(new Date());
        appraisedHistory.setUpdateTime(new Date());

        AppraisedHistoryEntity appraisedHistoryEntity = BeanMapper.map(appraisedHistory, AppraisedHistoryEntity.class);
        return appraisedHistoryDao.createAppraisedHistory(appraisedHistoryEntity);
    }

    @Override
    public void updateAppraisedHistory(AppraisedHistory appraisedHistory) {
        AppraisedHistoryEntity map = BeanMapper.map(appraisedHistory, AppraisedHistoryEntity.class);
        appraisedHistoryDao.updateAppraisedHistory(map);
    }

    @Override
    public void deleteAppraisedHistory(String id) {
        appraisedHistoryDao.deleteAppraisedHistory(id);
    }

    @Override
    public AppraisedHistory findOne(String id) {
        AppraisedHistoryEntity entity = appraisedHistoryDao.findAppraisedHistory(id);
        AppraisedHistory appraisedHistory = BeanMapper.map(entity, AppraisedHistory.class);
        return appraisedHistory;
    }

    @Override
    public AppraisedHistory findAppraisedHistory(String id) {
        AppraisedHistory one = findOne(id);

        joinTemplate.joinQuery(one, new String[]{"workAppraised"});
        return one;
    }

    @Override
    public List<AppraisedHistory> findList(List<String> idList) {
        List<AppraisedHistoryEntity> list = appraisedHistoryDao.findAppraisedHistoryList(idList);
        List<AppraisedHistory> mapList = BeanMapper.mapList(list, AppraisedHistory.class);
        return mapList;
    }

    @Override
    public List<AppraisedHistory> findAppraisedHistoryList(List<String> idList) {
        List<AppraisedHistory> list = findList(idList);
        joinTemplate.joinQuery(list, new String[]{"workAppraised"});
        return list;
    }

    @Override
    public List<AppraisedHistory> findAll() {
        List<AppraisedHistoryEntity> all = appraisedHistoryDao.findAllAppraisedHistory();
        List<AppraisedHistory> items = BeanMapper.mapList(all, AppraisedHistory.class);
        return items;
    }

    @Override
    public List<AppraisedHistory> findAppraisedHistoryList(AppraisedHistoryQuery appraisedHistoryQuery) {
        List<AppraisedHistoryEntity> appraisedList = appraisedHistoryDao.findAppraisedList(appraisedHistoryQuery);
        List<AppraisedHistory> items = BeanMapper.mapList(appraisedList, AppraisedHistory.class);
        joinTemplate.joinQuery(items, new String[]{"workAppraised","creater"});
        return items;
    }

    @Override
    public Pagination<AppraisedHistory> findAppraisedHistoryPage(AppraisedHistoryQuery appraisedHistoryQuery) {
        Pagination<AppraisedHistoryEntity> appraisedPage = appraisedHistoryDao.findAppraisedPage(appraisedHistoryQuery);

        List<AppraisedHistory> items = BeanMapper.mapList(appraisedPage.getDataList(), AppraisedHistory.class);

        joinTemplate.joinQuery(items, new String[]{"creater"});

        return PaginationBuilder.build(appraisedPage, items);
    }

}
