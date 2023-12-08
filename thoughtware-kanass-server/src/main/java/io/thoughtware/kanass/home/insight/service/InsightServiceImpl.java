package io.thoughtware.kanass.home.insight.service;

import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.kanass.home.insight.model.Insight;
import io.thoughtware.kanass.home.insight.model.InsightQuery;
import io.thoughtware.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.join.JoinTemplate;
import io.thoughtware.kanass.home.insight.dao.InsightDao;
import io.thoughtware.kanass.home.insight.entity.InsightEntity;

import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 仪表盘服务接口
*/
@Service
public class InsightServiceImpl implements InsightService {

    @Autowired
    InsightDao insightDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    UserService userService;

    @Override
    public String createInsight(@NotNull @Valid Insight insight) {
        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        insight.setMaster(user);

        InsightEntity insightEntity = BeanMapper.map(insight, InsightEntity.class);

        return insightDao.createInsight(insightEntity);
    }

    @Override
    public void updateInsight(@NotNull @Valid Insight insight) {
        InsightEntity insightEntity = BeanMapper.map(insight, InsightEntity.class);

        insightDao.updateInsight(insightEntity);
    }

    @Override
    public void deleteInsight(@NotNull String id) {
        insightDao.deleteInsight(id);
    }

    @Override
    public Insight findOne(String id) {
        InsightEntity insightEntity = insightDao.findInsight(id);

        Insight insight = BeanMapper.map(insightEntity, Insight.class);
        return insight;
    }

    @Override
    public List<Insight> findList(List<String> idList) {
        List<InsightEntity> insightEntityList =  insightDao.findInsightList(idList);

        List<Insight> insightList =  BeanMapper.mapList(insightEntityList,Insight.class);
        return insightList;
    }

    @Override
    public Insight findInsight(@NotNull String id) {
        Insight insight = findOne(id);

        joinTemplate.joinQuery(insight);

        return insight;
    }

    @Override
    public List<Insight> findAllInsight() {
        List<InsightEntity> insightEntityList =  insightDao.findAllInsight();

        List<Insight> insightList =  BeanMapper.mapList(insightEntityList,Insight.class);

        joinTemplate.joinQuery(insightList);

        return insightList;
    }

    @Override
    public List<Insight> findInsightList(InsightQuery insightQuery) {
        List<InsightEntity> insightEntityList = insightDao.findInsightList(insightQuery);

        List<Insight> insightList = BeanMapper.mapList(insightEntityList,Insight.class);

        joinTemplate.joinQuery(insightList);

        return insightList;
    }

    @Override
    public Pagination<Insight> findInsightPage(InsightQuery insightQuery) {
        Pagination<InsightEntity>  pagination = insightDao.findInsightPage(insightQuery);

        List<Insight> insightList = BeanMapper.mapList(pagination.getDataList(),Insight.class);

        joinTemplate.joinQuery(insightList);

        return PaginationBuilder.build(pagination,insightList);
    }

    /**
     * 查找我最近查看的仪表盘
     * @param insightQuery
     * @return
     */
    public List<Insight> findRecentInsightList(InsightQuery insightQuery) {
        String userId = LoginContext.getLoginId();
        insightQuery.setMasterId(userId);

        List<InsightEntity> recentInsightList = insightDao.findRecentInsightList(insightQuery);

        List<Insight> insightList = BeanMapper.mapList(recentInsightList,Insight.class);

        joinTemplate.joinQuery(insightList);

        return insightList;
    }

    public List<Insight> findFocusInsightList(InsightQuery insightQuery) {
        String userId = LoginContext.getLoginId();
        insightQuery.setMasterId(userId);

        List<InsightEntity> recentInsightList = insightDao.findFocusInsightList(insightQuery);

        List<Insight> insightList = BeanMapper.mapList(recentInsightList,Insight.class);

        joinTemplate.joinQuery(insightList);

        return insightList;
    }

}