package io.tiklab.teamwire.support.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.teamwire.support.dao.RecentDao;
import io.tiklab.teamwire.support.entity.RecentEntity;
import io.tiklab.teamwire.support.model.Recent;
import io.tiklab.teamwire.support.model.RecentQuery;


import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
* 最近访问的服务
*/
@Service
public class RecentServiceImpl implements RecentService {

    @Autowired
    RecentDao recentDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRecent(@NotNull @Valid Recent recent) {
        recent.setRecentTime(new Timestamp(System.currentTimeMillis()));
        recent.setMasterId(LoginContext.getLoginId());
        RecentEntity recentEntity = BeanMapper.map(recent, RecentEntity.class);

        // 根据访问模型id和点击人查找是否加入过最近访问，若有，则删除重新创建
        RecentQuery recentQuery = new RecentQuery();
        recentQuery.setModelId(recent.getModelId());
        recentQuery.setMasterId(LoginContext.getLoginId());
        List<Recent> recentList = findRecentList(recentQuery);
        if(recentList.size() > 0){
            for(Recent recent1:recentList){
                deleteRecent(recent1.getId());
            }
        }
        String recentRecent = recentDao.createRecent(recentEntity);
        return recentRecent;
    }

    @Override
    public void updateRecent(@NotNull @Valid Recent recent) {
        recent.setRecentTime(new Timestamp(System.currentTimeMillis()));
        RecentEntity recentEntity = BeanMapper.map(recent, RecentEntity.class);

        recentDao.updateRecent(recentEntity);
    }

    @Override
    public void deleteRecent(@NotNull String id) {
        recentDao.deleteRecent(id);
    }

    @Override
    public Recent findOne(String id) {
        RecentEntity recentEntity = recentDao.findRecent(id);

        Recent recent = BeanMapper.map(recentEntity, Recent.class);
        return recent;
    }

    @Override
    public List<Recent> findList(List<String> idList) {
        List<RecentEntity> recentEntityList =  recentDao.findRecentList(idList);

        List<Recent> recentList =  BeanMapper.mapList(recentEntityList,Recent.class);
        return recentList;
    }

    @Override
    public Recent findRecent(@NotNull String id) {
        Recent recent = findOne(id);

        joinTemplate.joinQuery(recent);

        return recent;
    }

    @Override
    public List<Recent> findAllRecent() {
        List<RecentEntity> recentEntityList =  recentDao.findAllRecent();

        List<Recent> recentList =  BeanMapper.mapList(recentEntityList,Recent.class);

        joinTemplate.joinQuery(recentList);

        return recentList;
    }

    @Override
    public List<Recent> findRecentList(RecentQuery recentQuery) {
        List<RecentEntity> recentEntityList = recentDao.findRecentList(recentQuery);

        List<Recent> recentList = BeanMapper.mapList(recentEntityList,Recent.class);

        joinTemplate.joinQuery(recentList);

        return recentList;
    }

    @Override
    public Pagination<Recent> findRecentPage(RecentQuery recentQuery) {
        Pagination<RecentEntity>  pagination = recentDao.findRecentPage(recentQuery);

        List<Recent> recentList = BeanMapper.mapList(pagination.getDataList(),Recent.class);

        joinTemplate.joinQuery(recentList);

        return PaginationBuilder.build(pagination,recentList);
    }
}