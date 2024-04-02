package io.thoughtware.kanass.home.statistic.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.kanass.home.statistic.dao.StageBurnDowmChartDao;
import io.thoughtware.kanass.home.statistic.entity.StageBurnDowmChartEntity;
import io.thoughtware.kanass.home.statistic.model.StageBurnDowmChart;
import io.thoughtware.kanass.home.statistic.model.StageBurnDowmChartQuery;
import io.thoughtware.kanass.project.stage.model.Stage;
import io.thoughtware.kanass.project.stage.service.StageService;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* StageBurnDowmChartServiceImpl
*/
@Service
public class StageBurnDowmChartServiceImpl implements StageBurnDowmChartService {

    @Autowired
    StageBurnDowmChartDao stageBurnDowmChartDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    StageService stageService;



//    @Scheduled(cron="0 0/1 * * * ?")
    @Scheduled(cron="0 0 23 * * ?")
    public void everyProjectBurnDownCharts() {
        System.out.println("20");
        List<Stage> allStage = stageService.findAllStage();
        for(int i=0;i<allStage.size();i++) {
            String id = allStage.get(i).getId();
            StageBurnDowmChart stageBurnDowmChart = new StageBurnDowmChart();
            stageBurnDowmChart.setStageId(id);
            createStageBurnDowmChart(stageBurnDowmChart);
        }

    }

    @Override
    public String createStageBurnDowmChart(@NotNull @Valid StageBurnDowmChart stageBurnDowmChart) {
        StageBurnDowmChartEntity stageBurnDowmChartEntity = BeanMapper.map(stageBurnDowmChart, StageBurnDowmChartEntity.class);

        return stageBurnDowmChartDao.createStageBurnDowmChart(stageBurnDowmChartEntity);
    }

    @Override
    public void updateStageBurnDowmChart(@NotNull @Valid StageBurnDowmChart stageBurnDowmChart) {
        StageBurnDowmChartEntity stageBurnDowmChartEntity = BeanMapper.map(stageBurnDowmChart, StageBurnDowmChartEntity.class);

        stageBurnDowmChartDao.updateStageBurnDowmChart(stageBurnDowmChartEntity);
    }

    @Override
    public void deleteStageBurnDowmChart(@NotNull String id) {
        stageBurnDowmChartDao.deleteStageBurnDowmChart(id);
    }

    @Override
    public StageBurnDowmChart findOne(String id) {
        StageBurnDowmChartEntity stageBurnDowmChartEntity = stageBurnDowmChartDao.findStageBurnDowmChart(id);

        StageBurnDowmChart stageBurnDowmChart = BeanMapper.map(stageBurnDowmChartEntity, StageBurnDowmChart.class);
        return stageBurnDowmChart;
    }

    @Override
    public List<StageBurnDowmChart> findList(List<String> idList) {
        List<StageBurnDowmChartEntity> stageBurnDowmChartEntityList =  stageBurnDowmChartDao.findStageBurnDowmChartList(idList);

        List<StageBurnDowmChart> stageBurnDowmChartList =  BeanMapper.mapList(stageBurnDowmChartEntityList,StageBurnDowmChart.class);
        return stageBurnDowmChartList;
    }

    @Override
    public StageBurnDowmChart findStageBurnDowmChart(@NotNull String id) {
        StageBurnDowmChart stageBurnDowmChart = findOne(id);

        joinTemplate.joinQuery(stageBurnDowmChart);

        return stageBurnDowmChart;
    }

    @Override
    public List<StageBurnDowmChart> findAllStageBurnDowmChart() {
        List<StageBurnDowmChartEntity> stageBurnDowmChartEntityList =  stageBurnDowmChartDao.findAllStageBurnDowmChart();

        List<StageBurnDowmChart> stageBurnDowmChartList =  BeanMapper.mapList(stageBurnDowmChartEntityList,StageBurnDowmChart.class);

        joinTemplate.joinQuery(stageBurnDowmChartList);

        return stageBurnDowmChartList;
    }

    @Override
    public List<StageBurnDowmChart> findStageBurnDowmChartList(StageBurnDowmChartQuery stageBurnDowmChartQuery) {
        List<StageBurnDowmChartEntity> stageBurnDowmChartEntityList = stageBurnDowmChartDao. findStageBurnDowmChartList(stageBurnDowmChartQuery);

        List<StageBurnDowmChart> stageBurnDowmChartList = BeanMapper.mapList(stageBurnDowmChartEntityList,StageBurnDowmChart.class);

        joinTemplate.joinQuery(stageBurnDowmChartList);

        return stageBurnDowmChartList;
    }

    @Override
    public Pagination<StageBurnDowmChart> findStageBurnDowmChartPage(StageBurnDowmChartQuery stageBurnDowmChartQuery) {
        Pagination<StageBurnDowmChartEntity>  pagination = stageBurnDowmChartDao.findStageBurnDowmChartPage(stageBurnDowmChartQuery);

        List<StageBurnDowmChart> stageBurnDowmChartList = BeanMapper.mapList(pagination.getDataList(),StageBurnDowmChart.class);

        joinTemplate.joinQuery(stageBurnDowmChartList);

        return PaginationBuilder.build(pagination,stageBurnDowmChartList);
    }
}