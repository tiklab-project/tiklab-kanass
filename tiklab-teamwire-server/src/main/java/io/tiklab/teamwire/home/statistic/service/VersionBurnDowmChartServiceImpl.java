package io.tiklab.teamwire.home.statistic.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teamwire.home.statistic.dao.VersionBurnDowmChartDao;
import io.tiklab.teamwire.home.statistic.entity.VersionBurnDowmChartEntity;
import io.tiklab.teamwire.home.statistic.model.VersionBurnDowmChart;
import io.tiklab.teamwire.home.statistic.model.VersionBurnDowmChartQuery;
import io.tiklab.teamwire.project.version.model.ProjectVersion;
import io.tiklab.teamwire.project.version.service.ProjectVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* VersionBurnDowmChartServiceImpl
*/
@Service
public class VersionBurnDowmChartServiceImpl implements VersionBurnDowmChartService {

    @Autowired
    VersionBurnDowmChartDao versionBurnDowmChartDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ProjectVersionService versionService;



//    @Scheduled(cron="0 0/1 * * * ?")
    @Scheduled(cron="0 0 23 * * ?")
    public void everyProjectBurnDownCharts() {
        System.out.println("20");
        List<ProjectVersion> allVersion = versionService.findAllVersion();
        for(int i=0;i<allVersion.size();i++) {
            String id = allVersion.get(i).getId();
            VersionBurnDowmChart versionBurnDowmChart = new VersionBurnDowmChart();
            versionBurnDowmChart.setVersionId(id);
            createVersionBurnDowmChart(versionBurnDowmChart);
        }

    }

    @Override
    public String createVersionBurnDowmChart(@NotNull @Valid VersionBurnDowmChart versionBurnDowmChart) {
        VersionBurnDowmChartEntity versionBurnDowmChartEntity = BeanMapper.map(versionBurnDowmChart, VersionBurnDowmChartEntity.class);

        return versionBurnDowmChartDao.createVersionBurnDowmChart(versionBurnDowmChartEntity);
    }

    @Override
    public void updateVersionBurnDowmChart(@NotNull @Valid VersionBurnDowmChart versionBurnDowmChart) {
        VersionBurnDowmChartEntity versionBurnDowmChartEntity = BeanMapper.map(versionBurnDowmChart, VersionBurnDowmChartEntity.class);

        versionBurnDowmChartDao.updateVersionBurnDowmChart(versionBurnDowmChartEntity);
    }

    @Override
    public void deleteVersionBurnDowmChart(@NotNull String id) {
        versionBurnDowmChartDao.deleteVersionBurnDowmChart(id);
    }

    @Override
    public VersionBurnDowmChart findOne(String id) {
        VersionBurnDowmChartEntity versionBurnDowmChartEntity = versionBurnDowmChartDao.findVersionBurnDowmChart(id);

        VersionBurnDowmChart versionBurnDowmChart = BeanMapper.map(versionBurnDowmChartEntity, VersionBurnDowmChart.class);
        return versionBurnDowmChart;
    }

    @Override
    public List<VersionBurnDowmChart> findList(List<String> idList) {
        List<VersionBurnDowmChartEntity> versionBurnDowmChartEntityList =  versionBurnDowmChartDao.findVersionBurnDowmChartList(idList);

        List<VersionBurnDowmChart> versionBurnDowmChartList =  BeanMapper.mapList(versionBurnDowmChartEntityList,VersionBurnDowmChart.class);
        return versionBurnDowmChartList;
    }

    @Override
    public VersionBurnDowmChart findVersionBurnDowmChart(@NotNull String id) {
        VersionBurnDowmChart versionBurnDowmChart = findOne(id);

        joinTemplate.joinQuery(versionBurnDowmChart);

        return versionBurnDowmChart;
    }

    @Override
    public List<VersionBurnDowmChart> findAllVersionBurnDowmChart() {
        List<VersionBurnDowmChartEntity> versionBurnDowmChartEntityList =  versionBurnDowmChartDao.findAllVersionBurnDowmChart();

        List<VersionBurnDowmChart> versionBurnDowmChartList =  BeanMapper.mapList(versionBurnDowmChartEntityList,VersionBurnDowmChart.class);

        joinTemplate.joinQuery(versionBurnDowmChartList);

        return versionBurnDowmChartList;
    }

    @Override
    public List<VersionBurnDowmChart> findVersionBurnDowmChartList(VersionBurnDowmChartQuery versionBurnDowmChartQuery) {
        List<VersionBurnDowmChartEntity> versionBurnDowmChartEntityList = versionBurnDowmChartDao. findVersionBurnDowmChartList(versionBurnDowmChartQuery);

        List<VersionBurnDowmChart> versionBurnDowmChartList = BeanMapper.mapList(versionBurnDowmChartEntityList,VersionBurnDowmChart.class);

        joinTemplate.joinQuery(versionBurnDowmChartList);

        return versionBurnDowmChartList;
    }

    @Override
    public Pagination<VersionBurnDowmChart> findVersionBurnDowmChartPage(VersionBurnDowmChartQuery versionBurnDowmChartQuery) {
        Pagination<VersionBurnDowmChartEntity>  pagination = versionBurnDowmChartDao.findVersionBurnDowmChartPage(versionBurnDowmChartQuery);

        List<VersionBurnDowmChart> versionBurnDowmChartList = BeanMapper.mapList(pagination.getDataList(),VersionBurnDowmChart.class);

        joinTemplate.joinQuery(versionBurnDowmChartList);

        return PaginationBuilder.build(pagination,versionBurnDowmChartList);
    }
}