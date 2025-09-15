package io.tiklab.kanass.home.statistic.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.kanass.home.statistic.dao.ProjectSetBurnDowmChartDao;
import io.tiklab.kanass.home.statistic.entity.ProjectSetBurnDowmChartEntity;
import io.tiklab.kanass.home.statistic.model.ProjectSetBurnDowmChart;
import io.tiklab.kanass.home.statistic.model.ProjectSetBurnDowmChartQuery;
import io.tiklab.kanass.projectset.model.ProjectSet;
import io.tiklab.kanass.projectset.service.ProjectSetService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 项目集动态数据统计，定时器每天跑一次
*/
@Service
public class ProjectSetBurnDowmChartServiceImpl implements ProjectSetBurnDowmChartService {
    @Autowired
    ProjectSetBurnDowmChartDao projectSetBurnDowmChartDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ProjectSetService projectSetService;

//    @Scheduled(cron="0 0/1 * * * ?")
    @Scheduled(cron="0 0 23 * * ?")
    public void everyProjectSetBurnDownCharts() {
        List<ProjectSet> ProjectSetList = projectSetService.findAllProjectSet();
        for(int i=0;i<ProjectSetList.size();i++) {
           String id = ProjectSetList.get(i).getId();
            ProjectSetBurnDowmChart projectSetBurnDowmChart = new ProjectSetBurnDowmChart();
            projectSetBurnDowmChart.setProjectSetId(id);
            createProjectSetBurnDowmChart(projectSetBurnDowmChart);
        }
    }

    @Override
    public String createProjectSetBurnDowmChart(@NotNull @Valid ProjectSetBurnDowmChart projectSetBurnDowmChart) {
        ProjectSetBurnDowmChartEntity projectSetBurnDowmChartEntity = BeanMapper.map(projectSetBurnDowmChart, ProjectSetBurnDowmChartEntity.class);

        return projectSetBurnDowmChartDao.createProjectSetBurnDowmChart(projectSetBurnDowmChartEntity);
    }

    @Override
    public void updateProjectSetBurnDowmChart(@NotNull @Valid ProjectSetBurnDowmChart projectSetBurnDowmChart) {
        ProjectSetBurnDowmChartEntity projectSetBurnDowmChartEntity = BeanMapper.map(projectSetBurnDowmChart, ProjectSetBurnDowmChartEntity.class);

        projectSetBurnDowmChartDao.updateProjectSetBurnDowmChart(projectSetBurnDowmChartEntity);
    }

    @Override
    public void deleteProjectSetBurnDowmChart(@NotNull String id) {
        projectSetBurnDowmChartDao.deleteProjectSetBurnDowmChart(id);
    }

    @Override
    public ProjectSetBurnDowmChart findOne(String id) {
        ProjectSetBurnDowmChartEntity projectSetBurnDowmChartEntity = projectSetBurnDowmChartDao.findProjectSetBurnDowmChart(id);

        ProjectSetBurnDowmChart projectSetBurnDowmChart = BeanMapper.map(projectSetBurnDowmChartEntity, ProjectSetBurnDowmChart.class);
        return projectSetBurnDowmChart;
    }

    @Override
    public List<ProjectSetBurnDowmChart> findList(List<String> idList) {
        List<ProjectSetBurnDowmChartEntity> projectSetBurnDowmChartEntityList =  projectSetBurnDowmChartDao.findProjectSetBurnDowmChartList(idList);

        List<ProjectSetBurnDowmChart> projectSetBurnDowmChartList =  BeanMapper.mapList(projectSetBurnDowmChartEntityList,ProjectSetBurnDowmChart.class);
        return projectSetBurnDowmChartList;
    }

    @Override
    public ProjectSetBurnDowmChart findProjectSetBurnDowmChart(@NotNull String id) {
        ProjectSetBurnDowmChart projectSetBurnDowmChart = findOne(id);

        joinTemplate.joinQuery(projectSetBurnDowmChart);

        return projectSetBurnDowmChart;
    }

    @Override
    public List<ProjectSetBurnDowmChart> findAllProjectSetBurnDowmChart() {
        List<ProjectSetBurnDowmChartEntity> projectSetBurnDowmChartEntityList =  projectSetBurnDowmChartDao.findAllProjectSetBurnDowmChart();

        List<ProjectSetBurnDowmChart> projectSetBurnDowmChartList =  BeanMapper.mapList(projectSetBurnDowmChartEntityList,ProjectSetBurnDowmChart.class);

        joinTemplate.joinQuery(projectSetBurnDowmChartList);

        return projectSetBurnDowmChartList;
    }

    @Override
    public List<ProjectSetBurnDowmChart> findProjectSetBurnDowmChartList(ProjectSetBurnDowmChartQuery projectSetBurnDowmChartQuery) {
        List<ProjectSetBurnDowmChartEntity> projectSetBurnDowmChartEntityList = projectSetBurnDowmChartDao.findProjectSetBurnDowmChartList(projectSetBurnDowmChartQuery);

        List<ProjectSetBurnDowmChart> projectSetBurnDowmChartList = BeanMapper.mapList(projectSetBurnDowmChartEntityList,ProjectSetBurnDowmChart.class);

        joinTemplate.joinQuery(projectSetBurnDowmChartList);

        return projectSetBurnDowmChartList;
    }

    @Override
    public Pagination<ProjectSetBurnDowmChart> findProjectSetBurnDowmChartPage(ProjectSetBurnDowmChartQuery projectSetBurnDowmChartQuery) {
        Pagination<ProjectSetBurnDowmChartEntity>  pagination = projectSetBurnDowmChartDao.findProjectSetBurnDowmChartPage(projectSetBurnDowmChartQuery);

        List<ProjectSetBurnDowmChart> projectSetBurnDowmChartList = BeanMapper.mapList(pagination.getDataList(),ProjectSetBurnDowmChart.class);

        joinTemplate.joinQuery(projectSetBurnDowmChartList);

        return PaginationBuilder.build(pagination,projectSetBurnDowmChartList);
    }
}