package io.tiklab.kanass.test.testcase.workItemBind.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.kanass.test.testcase.test.service.TestCaseService;
import io.tiklab.kanass.test.testcase.workItemBind.dao.WorkItemBindDao;
import io.tiklab.kanass.test.testcase.workItemBind.entity.WorkItemBindEntity;
import io.tiklab.kanass.test.testcase.workItemBind.model.WorkItemBind;
import io.tiklab.kanass.test.testcase.workItemBind.model.WorkItemBindQuery;
import io.tiklab.kanass.test.testplan.cases.service.TestPlanCaseService;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.service.WorkItemService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 绑定的缺陷 服务
*/
@Service
public class WorkItemBindServiceImpl implements WorkItemBindService {

    @Autowired
    WorkItemBindDao workItemBindDao;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    TestPlanCaseService testPlanCaseService;

    @Autowired
    WorkItemService workItemService;


    @Override
    public String createWorkItemBind(@NotNull @Valid WorkItemBind workItemBind) {
        WorkItemBindEntity workItemBindEntity = BeanMapper.map(workItemBind, WorkItemBindEntity.class);
        workItemBindEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return workItemBindDao.createWorkItemBind(workItemBindEntity);
    }

    @Override
    public void updateWorkItemBind(@NotNull @Valid WorkItemBind workItemBind) {
        WorkItemBindEntity workItemBindEntity = BeanMapper.map(workItemBind, WorkItemBindEntity.class);

        workItemBindDao.updateWorkItemBind(workItemBindEntity);
    }

    @Override
    public void deleteWorkItemBind(@NotNull String id) {
        workItemBindDao.deleteWorkItemBind(id);
    }

    @Override
    public void deleteWorkItemBindByCondition(WorkItemBindQuery workspaceBindQuery) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(WorkItemBindEntity.class)
                .eq("caseId", workspaceBindQuery.getCaseId())
                .eq("workItemId", workspaceBindQuery.getWorkItemId())
                .get();

        workItemBindDao.deleteWorkItemBind(deleteCondition);
    }

    @Override
    public void deleteAllWorkItemBind(String caseId) {
        WorkItemBindQuery workItemBindQuery = new WorkItemBindQuery();
        workItemBindQuery.setCaseId(caseId);
        List<WorkItemBind> workItemBindList = findWorkItemBindList(workItemBindQuery);
        for(WorkItemBind workItemBind : workItemBindList){
            deleteWorkItemBind(workItemBind.getId());
        }
    }

    @Override
    public int findTotalNum(String caseId){
        int totalNum = workItemBindDao.findTotalNum(caseId);
        return totalNum;
    }


    @Override
    public WorkItemBind findOne(String id) {
        WorkItemBindEntity workItemBindEntity = workItemBindDao.findWorkItemBind(id);

        WorkItemBind workItemBind = BeanMapper.map(workItemBindEntity, WorkItemBind.class);
        return workItemBind;
    }

    @Override
    public List<WorkItemBind> findList(List<String> idList) {
        List<WorkItemBindEntity> workItemBindEntityList =  workItemBindDao.findWorkItemBindList(idList);

        List<WorkItemBind> workItemBindList =  BeanMapper.mapList(workItemBindEntityList,WorkItemBind.class);
        return workItemBindList;
    }

    @Override
    public WorkItemBind findWorkItemBind(@NotNull String id) {
        WorkItemBind workItemBind = findOne(id);

        joinTemplate.joinQuery(workItemBind);

        return workItemBind;
    }

    @Override
    public List<WorkItemBind> findAllWorkItemBind() {
        List<WorkItemBindEntity> workItemBindEntityList =  workItemBindDao.findAllWorkItemBind();

        List<WorkItemBind> workItemBindList =  BeanMapper.mapList(workItemBindEntityList,WorkItemBind.class);

        joinTemplate.joinQuery(workItemBindList);

        return workItemBindList;
    }

    @Override
    public List<WorkItemBind> findWorkItemBindList(WorkItemBindQuery workItemBindQuery) {
        List<WorkItemBindEntity> workItemBindEntityList = workItemBindDao.findWorkItemBindList(workItemBindQuery);
        List<WorkItemBind> workItemBindList = BeanMapper.mapList(workItemBindEntityList,WorkItemBind.class);

        joinTemplate.joinQuery(workItemBindList,new String[]{"testCase","testPlan","workItem"});

        return workItemBindList;
    }

    @Override
    public Pagination<WorkItemBind> findWorkItemBindPage(WorkItemBindQuery workItemBindQuery) {
        Pagination<WorkItemBindEntity>  pagination = workItemBindDao.findWorkItemBindPage(workItemBindQuery);

        List<WorkItemBind> workItemBindList = BeanMapper.mapList(pagination.getDataList(),WorkItemBind.class);

        joinTemplate.joinQuery(workItemBindList,new String[]{"testCase","testPlan"});

        for (WorkItemBind workItemBind : workItemBindList) {
            String workItemId = workItemBind.getWorkItem().getId();
            WorkItem workItem = workItemService.findWorkItem(workItemId);
            workItemBind.setWorkItem(workItem);
        }
        return PaginationBuilder.build(pagination,workItemBindList);
    }


}