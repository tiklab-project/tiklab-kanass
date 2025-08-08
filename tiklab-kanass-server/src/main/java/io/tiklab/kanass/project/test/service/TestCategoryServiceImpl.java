package io.tiklab.kanass.project.test.service;

import io.tiklab.kanass.project.test.model.Category;
import io.tiklab.kanass.project.test.model.CategoryQuery;
import io.tiklab.kanass.project.test.model.RepositoryTestPlan;
import io.tiklab.kanass.project.test.model.RepositoryTestPlanQuery;
import io.tiklab.kanass.support.model.SystemUrl;
import io.tiklab.kanass.support.model.SystemUrlQuery;
import io.tiklab.kanass.support.service.SystemUrlService;
import io.tiklab.kanass.support.util.HttpRequestUtil;
import io.tiklab.toolkit.join.JoinTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCategoryServiceImpl implements TestCategoryService{
    private static Logger logger = LoggerFactory.getLogger(TestCategoryServiceImpl.class);

    @Autowired
    SystemUrlService systemUrlService;

    @Autowired
    HttpRequestUtil httpRequestUtil;
    @Autowired
    JoinTemplate joinTemplate;

    String getSystemUrl(){

        SystemUrlQuery systemUrlQuery = new SystemUrlQuery();
        systemUrlQuery.setName("kanass");
        List<SystemUrl> systemUrlList = systemUrlService.findSystemUrlList(systemUrlQuery);
        String url = systemUrlList.get(0).getSystemUrl();
        return url;
    }

    @Override
    public List<Category> findCategoryListTreeTable(CategoryQuery categoryQuery) {
        HttpHeaders httpHeaders = httpRequestUtil.initHeaders(MediaType.APPLICATION_JSON, null);
        String systemUrl = getSystemUrl();

        List<Category> categoryList = httpRequestUtil.requestPostList(httpHeaders,
                systemUrl + "/api/category/findCategoryListTreeTable",
                categoryQuery,
                Category.class);

        return categoryList;
    }
}
