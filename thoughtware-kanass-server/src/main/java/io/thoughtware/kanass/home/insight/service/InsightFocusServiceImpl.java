package io.thoughtware.kanass.home.insight.service;

import io.thoughtware.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.join.JoinTemplate;
import io.thoughtware.kanass.home.insight.dao.InsightFocusDao;
import io.thoughtware.kanass.home.insight.entity.InsightFocusEntity;
import io.thoughtware.kanass.home.insight.model.InsightFocus;
import io.thoughtware.kanass.home.insight.model.InsightFocusQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 项目收藏服务
*/
@Service
public class InsightFocusServiceImpl implements InsightFocusService {

    @Autowired
    InsightFocusDao insightFocusDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createInsightFocus(@NotNull @Valid InsightFocus insightFocus) {
        insightFocus.setMasterId(LoginContext.getLoginId());

        InsightFocusEntity insightFocusEntity = BeanMapper.map(insightFocus, InsightFocusEntity.class);

        return insightFocusDao.createInsightFocus(insightFocusEntity);
    }

    @Override
    public void updateInsightFocus(@NotNull @Valid InsightFocus insightFocus) {
        InsightFocusEntity insightFocusEntity = BeanMapper.map(insightFocus, InsightFocusEntity.class);

        insightFocusDao.updateInsightFocus(insightFocusEntity);
    }

    @Override
    public void deleteInsightFocusByQuery(@NotNull @Valid InsightFocusQuery insightFocusQuery) {
        List<InsightFocusEntity> insightFocusList = insightFocusDao.findInsightFocusList(insightFocusQuery);
        if(insightFocusList.size() > 0){
            for (InsightFocusEntity insightFocusEntity : insightFocusList) {
                insightFocusDao.deleteInsightFocus(insightFocusEntity.getId());
            }
        }
    }

    @Override
    public void deleteInsightFocus(@NotNull String id) {
        insightFocusDao.deleteInsightFocus(id);
    }

    @Override
    public InsightFocus findOne(String id) {
        InsightFocusEntity insightFocusEntity = insightFocusDao.findInsightFocus(id);

        InsightFocus insightFocus = BeanMapper.map(insightFocusEntity, InsightFocus.class);
        return insightFocus;
    }

    @Override
    public List<InsightFocus> findList(List<String> idList) {
        List<InsightFocusEntity> insightFocusEntityList =  insightFocusDao.findInsightFocusList(idList);

        List<InsightFocus> insightFocusList =  BeanMapper.mapList(insightFocusEntityList,InsightFocus.class);
        return insightFocusList;
    }

    @Override
    public InsightFocus findInsightFocus(@NotNull String id) {
        InsightFocus insightFocus = findOne(id);

        joinTemplate.joinQuery(insightFocus);

        return insightFocus;
    }

    @Override
    public List<InsightFocus> findAllInsightFocus() {
        List<InsightFocusEntity> insightFocusEntityList =  insightFocusDao.findAllInsightFocus();

        List<InsightFocus> insightFocusList =  BeanMapper.mapList(insightFocusEntityList,InsightFocus.class);

        joinTemplate.joinQuery(insightFocusList);

        return insightFocusList;
    }

    @Override
    public List<InsightFocus> findInsightFocusList(InsightFocusQuery insightFocusQuery) {
        List<InsightFocusEntity> insightFocusEntityList = insightFocusDao.findInsightFocusList(insightFocusQuery);

        List<InsightFocus> insightFocusList = BeanMapper.mapList(insightFocusEntityList,InsightFocus.class);

        joinTemplate.joinQuery(insightFocusList);

        return insightFocusList;
    }

    @Override
    public Pagination<InsightFocus> findInsightFocusPage(InsightFocusQuery insightFocusQuery) {
        Pagination<InsightFocusEntity>  pagination = insightFocusDao.findInsightFocusPage(insightFocusQuery);

        List<InsightFocus> insightFocusList = BeanMapper.mapList(pagination.getDataList(),InsightFocus.class);

        joinTemplate.joinQuery(insightFocusList);

        return PaginationBuilder.build(pagination,insightFocusList);
    }
}