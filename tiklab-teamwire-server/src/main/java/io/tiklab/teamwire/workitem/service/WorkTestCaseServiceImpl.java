package io.tiklab.teamwire.workitem.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.join.JoinTemplate;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.rpc.client.router.lookup.FixedLookup;
import io.tiklab.teamwire.project.test.model.ProjectTestCase;
import io.tiklab.teamwire.support.model.SystemUrl;
import io.tiklab.teamwire.support.model.SystemUrlQuery;
import io.tiklab.teamwire.support.service.SystemUrlService;
import io.tiklab.teamwire.support.util.RpcClientTeamWireUtil;
import io.tiklab.teamwire.workitem.dao.WorkTestCaseDao;
import io.tiklab.teamwire.workitem.entity.WorkTestCaseEntity;
import io.tiklab.teamwire.workitem.model.WorkTestCase;
import io.tiklab.teamwire.workitem.model.WorkTestCaseQuery;
import io.tiklab.teston.test.test.model.TestCaseQuery;
import io.tiklab.teston.test.test.model.TestCase;
import io.tiklab.teston.test.test.service.TestCaseService;
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
public class WorkTestCaseServiceImpl implements WorkTestCaseService {

    @Autowired
    WorkTestCaseDao workTestCaseDao;


    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    SystemUrlService systemUrlService;

    TestCaseService testCaseServiceRpc(){
        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("teston");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return new RpcClientTeamWireUtil().rpcClient().getBean(TestCaseService.class, new FixedLookup(url));
    }


    @Override
    public String createWorkTestCase(@NotNull List<WorkTestCase> workTestCase) {
        String document=null;

        //批量创建文档
        for (WorkTestCase itemTestCase: workTestCase){
            WorkTestCaseEntity workTestCaseEntity = BeanMapper.map(itemTestCase, WorkTestCaseEntity.class);
             document = workTestCaseDao.createWorkTestCase(workTestCaseEntity);
        }
        return document;
    }

    @Override
    public void updateWorkTestCase(@NotNull @Valid WorkTestCase workTestCase) {
        WorkTestCaseEntity workTestCaseEntity = BeanMapper.map(workTestCase, WorkTestCaseEntity.class);

        workTestCaseDao.updateWorkTestCase(workTestCaseEntity);
    }

    @Override
    public void deleteWorkTestCase(@NotNull String id) {
        workTestCaseDao.deleteWorkTestCase(id);
    }

    @Override
    public void delete(@NotNull String documentId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(WorkTestCaseEntity.class)
                .eq("documentId", documentId)
                .get();
        workTestCaseDao.deleteWorkTestCase(deleteCondition);
    }

    @Override
    public void deleteWorkTestCaseRele(WorkTestCaseQuery workTestCaseQuery) {
        //通过事项id和文档id查询
        List<WorkTestCaseEntity> workTestCaseList = workTestCaseDao.findWorkTestCaseList(workTestCaseQuery);
        workTestCaseDao.deleteWorkTestCase(workTestCaseList.get(0).getId());
    }


    @Override
    public WorkTestCase findOne(String id) {
        WorkTestCaseEntity itemTestCase = workTestCaseDao.findWorkTestCase(id);

        WorkTestCase workTestCase = BeanMapper.map(itemTestCase, WorkTestCase.class);
        return workTestCase;
    }

    @Override
    public List<WorkTestCase> findList(List<String> idList) {
        List<WorkTestCaseEntity> workTestCaseEntityList =  workTestCaseDao.findWorkTestCaseList(idList);

        List<WorkTestCase> workTestCaseList =  BeanMapper.mapList(workTestCaseEntityList,WorkTestCase.class);
        return workTestCaseList;
    }

    @Override
    public WorkTestCase findWorkTestCase(@NotNull String id) {
        WorkTestCase workTestCase = findOne(id);

        joinTemplate.joinQuery(workTestCase);
        return workTestCase;
    }

    @Override
    public List<WorkTestCase> findAllWorkTestCase() {
        List<WorkTestCaseEntity> workTestCaseEntityList =  workTestCaseDao.findAllWorkTestCase();

        List<WorkTestCase> workTestCaseList =  BeanMapper.mapList(workTestCaseEntityList,WorkTestCase.class);

        joinTemplate.joinQuery(workTestCaseList);
        return workTestCaseList;
    }

    @Override
    public List<WorkTestCase> findWorkTestCaseList(WorkTestCaseQuery workTestCaseQuery) {
        List<WorkTestCaseEntity> workTestCaseEntityList = workTestCaseDao.findWorkTestCaseList(workTestCaseQuery);

        List<WorkTestCase> workTestCaseList = BeanMapper.mapList(workTestCaseEntityList,WorkTestCase.class);

        joinTemplate.joinQuery(workTestCaseList);

        return workTestCaseList;
    }

    @Override
    public List<ProjectTestCase> findTestCasePageByWorkItemId(String workItemId) {
        WorkTestCaseQuery workTestCaseQuery = new WorkTestCaseQuery();
        workTestCaseQuery.setWorkItemId(workItemId);

        List<WorkTestCase> workTestCaseList = findWorkTestCaseList(workTestCaseQuery);

        List<ProjectTestCase> list = new ArrayList<>();
        if (!ObjectUtils.isEmpty(workTestCaseList)){
            for (WorkTestCase workTestCase:workTestCaseList){
                TestCase testCase = testCaseServiceRpc().findTestCase(workTestCase.getTestCaseId());
                ProjectTestCase projectTestCase = new ProjectTestCase();

                if (!ObjectUtils.isEmpty(testCase)){
                    projectTestCase.setTestCaseName(testCase.getName());
                    projectTestCase.setId(testCase.getId());
                    projectTestCase.setCreateUser(testCase.getCreateUser().getName());
                    if(!ObjectUtils.isEmpty(testCase.getCategory())){
                        projectTestCase.setTestCategoryName(testCase.getCategory().getName());
                    }
                    projectTestCase.setCaseType(testCase.getCaseType());
                    list.add(projectTestCase);
                }else {
                    projectTestCase.setTestCaseName("用例已被删除");
                    projectTestCase.setId(workTestCase.getTestCaseId());
                    projectTestCase.setExist(false);
                    list.add(projectTestCase);
                }
            }
        }
        return list;
    }

    @Override
    public Pagination<WorkTestCase> findWorkTestCasePage(WorkTestCaseQuery workTestCaseQuery) {
        Pagination<WorkTestCaseEntity> pagination = workTestCaseDao.findWorkTestCasePage(workTestCaseQuery);

        List<WorkTestCase> workTestCaseList = BeanMapper.mapList(pagination.getDataList(),WorkTestCase.class);

        joinTemplate.joinQuery(workTestCaseList);

        return PaginationBuilder.build(pagination,workTestCaseList);
    }

    @Override
    public Pagination<ProjectTestCase> findUnRelationWorkTestCaseList(WorkTestCaseQuery workTestCaseQuery) {
        TestCaseQuery testCaseQuery = new TestCaseQuery();

        String[] repositoryIds = workTestCaseQuery.getRepositoryIds();
        testCaseQuery.setInList(repositoryIds);


        List<WorkTestCase> workTestCaseList = findWorkTestCaseList(workTestCaseQuery);
        List<String> workTestCaseIds = workTestCaseList.stream().map(workTestCase -> workTestCase.getTestCaseId()).collect(Collectors.toList());
        int size = workTestCaseIds.size();
        String[] stringIds = new String[size];
        String[] documentIds = workTestCaseIds.toArray(stringIds);
        testCaseQuery.setNotInList(documentIds);
        testCaseQuery.setInList(repositoryIds);

        Pagination<TestCase> testCasePage = testCaseServiceRpc().findTestCasePage(testCaseQuery);


        Pagination<ProjectTestCase> projectTestCasePagination = new Pagination<ProjectTestCase>();
        projectTestCasePagination.setTotalRecord(testCasePage.getTotalRecord());
        projectTestCasePagination.setTotalPage(testCasePage.getTotalPage());
        projectTestCasePagination.setPageSize(testCasePage.getPageSize());
        projectTestCasePagination.setCurrentPage(testCasePage.getCurrentPage());

        List<ProjectTestCase> projectTestCaseList = new ArrayList<ProjectTestCase>();
        for (TestCase testCase : testCasePage.getDataList()) {
            ProjectTestCase projectTestCase = new ProjectTestCase();
            projectTestCase.setTestCaseName(testCase.getName());
            projectTestCase.setId(testCase.getId());
            projectTestCase.setCreateUser(testCase.getCreateUser().getName());
            if(!ObjectUtils.isEmpty(testCase.getCategory())){
                projectTestCase.setTestCategoryName(testCase.getCategory().getName());
            }

            projectTestCase.setCaseType(testCase.getCaseType());
            projectTestCaseList.add(projectTestCase);
        }
        projectTestCasePagination.setDataList(projectTestCaseList);
        return projectTestCasePagination;
    }

}