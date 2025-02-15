package io.tiklab.kanass.home.statistic.service;

import io.tiklab.kanass.home.statistic.model.SprintBurnDowmChart;
import io.tiklab.kanass.home.statistic.model.SprintBurnDowmChartQuery;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.kanass.sprint.service.SprintService;
import io.tiklab.kanass.home.statistic.dao.SprintBurnDowmChartDao;
import io.tiklab.kanass.home.statistic.entity.SprintBurnDowmChartEntity;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 迭代动态数据记录
*/
@Service
public class SprintBurnDowmChartServiceImpl implements SprintBurnDowmChartService {

    @Autowired
    SprintBurnDowmChartDao sprintBurnDowmChartDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    SprintService sprintService;


    /**
     * 每天23点跑一次
     */
    @Scheduled(cron="0 0 23 * * ?")
    public void everyProjectBurnDownCharts() {
        System.out.println("20");
        List<Sprint> allSprint = sprintService.findAllSprint();
        for(int i=0;i<allSprint.size();i++) {
            String id = allSprint.get(i).getId();
            SprintBurnDowmChart sprintBurnDowmChart = new SprintBurnDowmChart();
            sprintBurnDowmChart.setSprintId(id);
            createSprintBurnDowmChart(sprintBurnDowmChart);
        }

    }


    @Override
    public String createSprintBurnDowmChart(@NotNull @Valid SprintBurnDowmChart sprintBurnDowmChart) {
        SprintBurnDowmChartEntity sprintBurnDowmChartEntity = BeanMapper.map(sprintBurnDowmChart, SprintBurnDowmChartEntity.class);

        return sprintBurnDowmChartDao.createSprintBurnDowmChart(sprintBurnDowmChartEntity);
    }

    @Override
    public void updateSprintBurnDowmChart(@NotNull @Valid SprintBurnDowmChart sprintBurnDowmChart) {
        SprintBurnDowmChartEntity sprintBurnDowmChartEntity = BeanMapper.map(sprintBurnDowmChart, SprintBurnDowmChartEntity.class);

        sprintBurnDowmChartDao.updateSprintBurnDowmChart(sprintBurnDowmChartEntity);
    }

    @Override
    public void deleteSprintBurnDowmChart(@NotNull String id) {
        sprintBurnDowmChartDao.deleteSprintBurnDowmChart(id);
    }

    @Override
    public SprintBurnDowmChart findOne(String id) {
        SprintBurnDowmChartEntity sprintBurnDowmChartEntity = sprintBurnDowmChartDao.findSprintBurnDowmChart(id);

        SprintBurnDowmChart sprintBurnDowmChart = BeanMapper.map(sprintBurnDowmChartEntity, SprintBurnDowmChart.class);
        return sprintBurnDowmChart;
    }

    @Override
    public List<SprintBurnDowmChart> findList(List<String> idList) {
        List<SprintBurnDowmChartEntity> sprintBurnDowmChartEntityList =  sprintBurnDowmChartDao.findSprintBurnDowmChartList(idList);

        List<SprintBurnDowmChart> sprintBurnDowmChartList =  BeanMapper.mapList(sprintBurnDowmChartEntityList,SprintBurnDowmChart.class);
        return sprintBurnDowmChartList;
    }

    @Override
    public SprintBurnDowmChart findSprintBurnDowmChart(@NotNull String id) {
        SprintBurnDowmChart sprintBurnDowmChart = findOne(id);

        joinTemplate.joinQuery(sprintBurnDowmChart);

        return sprintBurnDowmChart;
    }

    @Override
    public List<SprintBurnDowmChart> findAllSprintBurnDowmChart() {
        List<SprintBurnDowmChartEntity> sprintBurnDowmChartEntityList =  sprintBurnDowmChartDao.findAllSprintBurnDowmChart();

        List<SprintBurnDowmChart> sprintBurnDowmChartList =  BeanMapper.mapList(sprintBurnDowmChartEntityList,SprintBurnDowmChart.class);

        joinTemplate.joinQuery(sprintBurnDowmChartList);

        return sprintBurnDowmChartList;
    }

    @Override
    public List<SprintBurnDowmChart> findSprintBurnDowmChartList(SprintBurnDowmChartQuery sprintBurnDowmChartQuery) {
        List<SprintBurnDowmChartEntity> sprintBurnDowmChartEntityList = sprintBurnDowmChartDao. findSprintBurnDowmChartList(sprintBurnDowmChartQuery);

        List<SprintBurnDowmChart> sprintBurnDowmChartList = BeanMapper.mapList(sprintBurnDowmChartEntityList,SprintBurnDowmChart.class);

        joinTemplate.joinQuery(sprintBurnDowmChartList);

        return sprintBurnDowmChartList;
    }

    @Override
    public Pagination<SprintBurnDowmChart> findSprintBurnDowmChartPage(SprintBurnDowmChartQuery sprintBurnDowmChartQuery) {
        Pagination<SprintBurnDowmChartEntity>  pagination = sprintBurnDowmChartDao.findSprintBurnDowmChartPage(sprintBurnDowmChartQuery);

        List<SprintBurnDowmChart> sprintBurnDowmChartList = BeanMapper.mapList(pagination.getDataList(),SprintBurnDowmChart.class);

        joinTemplate.joinQuery(sprintBurnDowmChartList);

        return PaginationBuilder.build(pagination,sprintBurnDowmChartList);
    }
}