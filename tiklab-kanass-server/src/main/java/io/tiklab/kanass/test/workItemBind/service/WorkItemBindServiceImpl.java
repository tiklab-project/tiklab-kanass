package io.tiklab.kanass.test.workItemBind.service;

import io.tiklab.kanass.test.test.service.TestCaseService;
import io.tiklab.kanass.test.workItemBind.dao.WorkItemBindDao;
import io.tiklab.kanass.test.workItemBind.entity.WorkItemBindEntity;
import io.tiklab.kanass.test.workItemBind.model.WorkItemBind;
import io.tiklab.kanass.test.workItemBind.model.WorkItemBindQuery;
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

        joinTemplate.joinQuery(workItemBindList,new String[]{"testCase","testPlan","workItem"});

        return PaginationBuilder.build(pagination,workItemBindList);
    }


}