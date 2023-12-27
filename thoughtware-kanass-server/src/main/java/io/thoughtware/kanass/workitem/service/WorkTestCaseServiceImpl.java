package io.thoughtware.kanass.workitem.service;

import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.rpc.client.router.lookup.FixedLookup;
import io.thoughtware.kanass.project.test.model.ProjectTestCase;
import io.thoughtware.kanass.project.test.model.TestCase;
import io.thoughtware.kanass.support.model.SystemUrl;
import io.thoughtware.kanass.support.model.SystemUrlQuery;
import io.thoughtware.kanass.support.service.SystemUrlService;
import io.thoughtware.kanass.support.util.HttpRequestUtil;
import io.thoughtware.kanass.support.util.RpcClientTeamWireUtil;
import io.thoughtware.kanass.workitem.dao.WorkTestCaseDao;
import io.thoughtware.kanass.workitem.entity.WorkTestCaseEntity;
import io.thoughtware.kanass.workitem.model.WorkTestCase;
import io.thoughtware.kanass.workitem.model.WorkTestCaseQuery;
import io.thoughtware.kanass.project.test.model.TestCaseQuery;
import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
    HttpRequestUtil httpRequestUtil;
    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    SystemUrlService systemUrlService;

    String getSystemUrl(){
        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("teston");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return url;
    }

    UserService userServiceRpc(){
        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("teston");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return new RpcClientTeamWireUtil().rpcClient().getBean(UserService.class, new FixedLookup(url));
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
                HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
                String systemUrl = getSystemUrl();
                MultiValueMap param = new LinkedMultiValueMap<>();
                param.add("id", workTestCase.getTestCaseId());
                TestCase testCase = httpRequestUtil.requestPost(httpHeaders, systemUrl + "/api/testCase/findTestCase", param, TestCase.class);

//                TestCase testCase = testCaseServiceRpc().findTestCase(workTestCase.getTestCaseId());
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
        testCaseQuery.setName(workTestCaseQuery.getName());
        testCaseQuery.setCreateUser(workTestCaseQuery.getCreatUserId());

        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();
        Pagination<TestCase> testCaseListPage = httpRequestUtil.requestPostPage(httpHeaders, systemUrl + "/api/testCase/findTestCasePage", testCaseQuery, TestCase.class);

        Pagination<ProjectTestCase> projectTestCasePagination = new Pagination<ProjectTestCase>();
        projectTestCasePagination.setTotalRecord(testCaseListPage.getTotalRecord());
        projectTestCasePagination.setTotalPage(testCaseListPage.getTotalPage());
        projectTestCasePagination.setPageSize(testCaseListPage.getPageSize());
        projectTestCasePagination.setCurrentPage(testCaseListPage.getCurrentPage());

        List<ProjectTestCase> projectTestCaseList = new ArrayList<ProjectTestCase>();
        for (TestCase testCase : testCaseListPage.getDataList()) {
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

    @Override
    public List<User> findTestOnRepositoryUserList(String[] repositoryIds) {
        List<User> allUser = userServiceRpc().findAllUser();
        return allUser;
    }

}