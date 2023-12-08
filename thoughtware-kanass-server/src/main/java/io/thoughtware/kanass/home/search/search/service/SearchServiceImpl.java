//package io.thoughtware.kanass.home.search.search.service;
//
//import io.thoughtware.dss.common.document.model.CountResponse;
//import io.thoughtware.dss.common.document.model.PageCondition;
//import io.thoughtware.dss.common.document.model.PageResponse;
//import io.thoughtware.dss.common.document.model.TopResponse;
//import io.thoughtware.kanass.home.search.service.SearchService;
//import Project;
//import ProjectService;
//import WorkItem;
//import WorkItemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Map;
//
///**
//* 全局搜索服务
//*/
//@Service
//public class SearchServiceImpl implements SearchService {
//
//
//
//    @Autowired
//    ProjectService projectService;
//
//    @Autowired
//    WorkItemService workItemService;
//
//    @Override
//    public void rebuild() {
//        //删除索引
//        deleteIndex();
//
//        //构建索引
//        buildIndex();
//    }
//
//    /**
//     * 删除索引
//     */
//    void deleteIndex(){
//        dssClient.deleteAll(Project.class);
//        dssClient.deleteAll(WorkItem.class);
//    }
//
//    /**
//     * 构建索引
//     */
//    void buildIndex(){
//        //构建项目索引
//        List<Project> projectList = projectService.findAllProject();
//        if(projectList != null && projectList.size() > 0){
//            dssClient.saveBatch(projectList);
//        }
//
//        //构建事项索引
//        List<WorkItem> workItemList = workItemService.findAllWorkItem();
//        if(workItemList != null || workItemList.size() > 0){
//            dssClient.saveBatch(workItemList);
//        }
//    }
//
//    @Override
//    public <T> void save(T entity) {
//        dssClient.save(entity);
//    }
//
//    @Override
//    public <T> Map<String, Object> get(Class<T> entityClass, String id) {
//        return dssClient.findOne(entityClass, id);
//    }
//
//    @Override
//    public <T> TopResponse<T> searchForTop(Class<T> entityClass, String keyword) {
//        return dssClient.searchForTop(entityClass,keyword);
//    }
//
//    @Override
//    public <T> CountResponse<T> searchForCount(Class<T> entityClass, String keyword) {
//        return dssClient.searchForCount(entityClass, keyword);
//    }
//
//    @Override
//    public <T> PageResponse<T> searchForPage(Class<T> entityClass, String keyword, PageCondition pageCondition) {
//        return dssClient.searchForPage(entityClass, keyword, pageCondition);
//    }
//}