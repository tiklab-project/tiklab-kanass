package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.kanass.project.test.model.ProjectTestCase;
import io.tiklab.kanass.support.model.SystemUrl;
import io.tiklab.kanass.support.model.SystemUrlQuery;
import io.tiklab.kanass.support.service.SystemUrlService;
import io.tiklab.kanass.support.util.HttpRequestUtil;
import io.tiklab.kanass.support.util.RpcClientTeamWireUtil;
import io.tiklab.kanass.test.test.model.TestCase;
import io.tiklab.kanass.test.test.model.TestCaseQuery;
import io.tiklab.kanass.test.test.service.TestCaseService;
import io.tiklab.kanass.workitem.dao.WorkTransitionHistoryDao;
import io.tiklab.kanass.workitem.entity.WorkTransitionHistoryEntity;
import io.tiklab.kanass.workitem.model.WorkTransitionHistory;
import io.tiklab.kanass.workitem.model.WorkTransitionHistoryQuery;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.rpc.client.router.lookup.FixedLookup;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
* 事项文档服务
*/
@Service
@Exporter
public class WorkTransitionHistoryServiceImpl implements WorkTransitionHistoryService {

    @Autowired
    WorkTransitionHistoryDao workTransitionHistoryDao;

    @Autowired
    HttpRequestUtil httpRequestUtil;
    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    SystemUrlService systemUrlService;

    @Autowired
    TestCaseService testCaseService;

    @Override
    public String createWorkTransitionHistory(@NotNull WorkTransitionHistory workTransitionHistory) {
        String document=null;

        WorkTransitionHistoryEntity workTransitionHistoryEntity = BeanMapper.map(workTransitionHistory, WorkTransitionHistoryEntity.class);
        document = workTransitionHistoryDao.createWorkTransitionHistory(workTransitionHistoryEntity);
        return document;
    }

    @Override
    public void updateWorkTransitionHistory(@NotNull @Valid WorkTransitionHistory workTransitionHistory) {
        WorkTransitionHistoryEntity workTransitionHistoryEntity = BeanMapper.map(workTransitionHistory, WorkTransitionHistoryEntity.class);

        workTransitionHistoryDao.updateWorkTransitionHistory(workTransitionHistoryEntity);
    }

    @Override
    public void deleteWorkTransitionHistory(@NotNull String id) {
        workTransitionHistoryDao.deleteWorkTransitionHistory(id);
    }

    @Override
    public void deleteWorkTransitionHistoryList(WorkTransitionHistoryQuery workTransitionHistoryQuery) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(WorkTransitionHistoryEntity.class)
                .eq("workItemId", workTransitionHistoryQuery.getWorkItemId())
                .get();
        workTransitionHistoryDao.deleteWorkTransitionHistory(deleteCondition);
    }


    @Override
    public WorkTransitionHistory findOne(String id) {
        WorkTransitionHistoryEntity itemTestCase = workTransitionHistoryDao.findWorkTransitionHistory(id);

        WorkTransitionHistory workTransitionHistory = BeanMapper.map(itemTestCase, WorkTransitionHistory.class);
        return workTransitionHistory;
    }

    @Override
    public List<WorkTransitionHistory> findList(List<String> idList) {
        List<WorkTransitionHistoryEntity> workTransitionHistoryEntityList =  workTransitionHistoryDao.findWorkTransitionHistoryList(idList);

        List<WorkTransitionHistory> workTransitionHistoryList =  BeanMapper.mapList(workTransitionHistoryEntityList,WorkTransitionHistory.class);
        return workTransitionHistoryList;
    }

    @Override
    public WorkTransitionHistory findWorkTransitionHistory(@NotNull String id) {
        WorkTransitionHistory workTransitionHistory = findOne(id);

        joinTemplate.joinQuery(workTransitionHistory, new String[]{"workItem"});
        return workTransitionHistory;
    }

    @Override
    public List<WorkTransitionHistory> findAllWorkTransitionHistory() {
        List<WorkTransitionHistoryEntity> workTransitionHistoryEntityList =  workTransitionHistoryDao.findAllWorkTransitionHistory();

        List<WorkTransitionHistory> workTransitionHistoryList =  BeanMapper.mapList(workTransitionHistoryEntityList,WorkTransitionHistory.class);

        joinTemplate.joinQuery(workTransitionHistoryList, new String[]{"workItem"});
        return workTransitionHistoryList;
    }

    @Override
    public List<WorkTransitionHistory> findWorkTransitionHistoryList(WorkTransitionHistoryQuery workTransitionHistoryQuery) {
        List<WorkTransitionHistoryEntity> workTransitionHistoryEntityList = workTransitionHistoryDao.findWorkTransitionHistoryList(workTransitionHistoryQuery);

        List<WorkTransitionHistory> workTransitionHistoryList = BeanMapper.mapList(workTransitionHistoryEntityList,WorkTransitionHistory.class);

        joinTemplate.joinQuery(workTransitionHistoryList, new String[]{"workItem"});

        return workTransitionHistoryList;
    }

    @Override
    public Pagination<WorkTransitionHistory> findWorkTransitionHistoryPage(WorkTransitionHistoryQuery workTransitionHistoryQuery) {
        Pagination<WorkTransitionHistoryEntity> pagination = workTransitionHistoryDao.findWorkTransitionHistoryPage(workTransitionHistoryQuery);

        List<WorkTransitionHistory> workTransitionHistoryList = BeanMapper.mapList(pagination.getDataList(),WorkTransitionHistory.class);

        joinTemplate.joinQuery(workTransitionHistoryList, new String[]{"workItem"});

        return PaginationBuilder.build(pagination,workTransitionHistoryList);
    }

}