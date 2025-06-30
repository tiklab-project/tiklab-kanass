package io.tiklab.kanass.home.insight.service;

import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.home.insight.model.Insight;
import io.tiklab.kanass.home.insight.model.InsightQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.home.insight.dao.InsightDao;
import io.tiklab.kanass.home.insight.entity.InsightEntity;

import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
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
        String createUserId = LoginContext.getLoginId();// 获取当前登录用户id
        User user = userService.findOne(createUserId);//根据用户id查询用户信息
        insight.setMaster(user);//设置仪表盘的所有者

        InsightEntity insightEntity = BeanMapper.map(insight, InsightEntity.class);//将仪表盘对象映射为实体对象

        return insightDao.createInsight(insightEntity);//调用dao层创建仪表盘
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

    /**
     * 根据id查找某一个并返回仪表盘对象
     * @param id
     * @return
     */
    @Override
    public Insight findOne(String id) {
        InsightEntity insightEntity = insightDao.findInsight(id);

        Insight insight = BeanMapper.map(insightEntity, Insight.class);
        return insight;
    }

    /**
     * 根据id列表查找并返回仪表盘对象列表
     * @param idList
     * @return
     */
    @Override
    public List<Insight> findList(List<String> idList) {
        List<InsightEntity> insightEntityList =  insightDao.findInsightList(idList);
        // 将实体对象列表映射为仪表盘对象列表
        List<Insight> insightList =  BeanMapper.mapList(insightEntityList,Insight.class);
        return insightList;
    }

    /**
     * 根据id查找并返回仪表盘对象
     * @param id
     * @return
     */
    @Override
    public Insight findInsight(@NotNull String id) {
        Insight insight = findOne(id);

        joinTemplate.joinQuery(insight, new String[]{"master"});

        return insight;
    }

    /**
     * 查找所有仪表盘
     * @return
     */
    @Override
    public List<Insight> findAllInsight() {
        List<InsightEntity> insightEntityList =  insightDao.findAllInsight();

        List<Insight> insightList =  BeanMapper.mapList(insightEntityList,Insight.class);

        joinTemplate.joinQuery(insightList, new String[]{"master"});

        return insightList;
    }

    /**
     * 根据查询条件查找并返回仪表盘对象列表
     * @param insightQuery
     * @return
     */
    @Override
    public List<Insight> findInsightList(InsightQuery insightQuery) {
        List<InsightEntity> insightEntityList = insightDao.findInsightList(insightQuery);

        List<Insight> insightList = BeanMapper.mapList(insightEntityList,Insight.class);

        joinTemplate.joinQuery(insightList, new String[]{"master"});

        return insightList;
    }

    /**
     * 根据查询条件查找并返回仪表盘对象分页
     * @param insightQuery
     * @return
     */
    @Override
    public Pagination<Insight> findInsightPage(InsightQuery insightQuery) {
        Pagination<InsightEntity>  pagination = insightDao.findInsightPage(insightQuery);

        List<Insight> insightList = BeanMapper.mapList(pagination.getDataList(),Insight.class);

        joinTemplate.joinQuery(insightList, new String[]{"master"});

        return PaginationBuilder.build(pagination,insightList);
    }

    /**
     * 根据当前登录用户查询其最近查看的 Insight 列表，并返回处理后的结果
     * @param insightQuery
     * @return
     */
    public List<Insight> findRecentInsightList(InsightQuery insightQuery) {
        String userId = LoginContext.getLoginId();// 获取当前登录用户id
        insightQuery.setMasterId(userId);//设置当前登录用户id

        List<InsightEntity> recentInsightList = insightDao.findRecentInsightList(insightQuery);

        List<Insight> insightList = BeanMapper.mapList(recentInsightList,Insight.class);

        joinTemplate.joinQuery(insightList, new String[]{"master"});// 关联查询

        return insightList;
    }

    /**
     * 根据当前登录用户查询其关注的 Insight 列表，并返回处理后的结果
     * @param insightQuery
     * @return
     */
    public List<Insight> findFocusInsightList(InsightQuery insightQuery) {
        String userId = LoginContext.getLoginId();
        insightQuery.setMasterId(userId);

        List<InsightEntity> recentInsightList = insightDao.findFocusInsightList(insightQuery);

        List<Insight> insightList = BeanMapper.mapList(recentInsightList,Insight.class);

        joinTemplate.joinQuery(insightList, new String[]{"master"});

        return insightList;
    }

}