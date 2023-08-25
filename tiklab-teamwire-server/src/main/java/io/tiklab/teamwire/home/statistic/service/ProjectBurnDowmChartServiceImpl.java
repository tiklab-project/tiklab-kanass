package io.tiklab.teamwire.home.statistic.service;

import io.tiklab.teamwire.home.statistic.model.ProjectBurnDowmChart;
import io.tiklab.teamwire.home.statistic.model.ProjectBurnDowmChartQuery;
import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.teamwire.project.project.service.ProjectService;
import io.tiklab.teamwire.home.statistic.dao.ProjectBurnDowmChartDao;
import io.tiklab.teamwire.home.statistic.entity.ProjectBurnDowmChartEntity;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 项目
*/
@Service
public class ProjectBurnDowmChartServiceImpl implements ProjectBurnDowmChartService {

    @Autowired
    ProjectBurnDowmChartDao projectBurnDowmChartDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ProjectService projectService;

//    @Scheduled(cron="0 0 23 * * ?")
//    @Scheduled(cron="0/10 * * * * ?")
//    @Scheduled(cron="0 0 23 * * ?")
    public void everyProjectBurnDownCharts() {
        List<Project> ProjectList = projectService.findAllProject();
        for(int i=0;i<ProjectList.size();i++) {
           String id = ProjectList.get(i).getId();
            ProjectBurnDowmChart projectBurnDowmChart = new ProjectBurnDowmChart();
            projectBurnDowmChart.setProjectId(id);
            createProjectBurnDowmChart(projectBurnDowmChart);
        }

    }


    @Override
    public String createProjectBurnDowmChart(@NotNull @Valid ProjectBurnDowmChart projectBurnDowmChart) {
        ProjectBurnDowmChartEntity projectBurnDowmChartEntity = BeanMapper.map(projectBurnDowmChart, ProjectBurnDowmChartEntity.class);

        return projectBurnDowmChartDao.createProjectBurnDowmChart(projectBurnDowmChartEntity);
    }

    @Override
    public void updateProjectBurnDowmChart(@NotNull @Valid ProjectBurnDowmChart projectBurnDowmChart) {
        ProjectBurnDowmChartEntity projectBurnDowmChartEntity = BeanMapper.map(projectBurnDowmChart, ProjectBurnDowmChartEntity.class);

        projectBurnDowmChartDao.updateProjectBurnDowmChart(projectBurnDowmChartEntity);
    }

    @Override
    public void deleteProjectBurnDowmChart(@NotNull String id) {
        projectBurnDowmChartDao.deleteProjectBurnDowmChart(id);
    }

    @Override
    public ProjectBurnDowmChart findOne(String id) {
        ProjectBurnDowmChartEntity projectBurnDowmChartEntity = projectBurnDowmChartDao.findProjectBurnDowmChart(id);

        ProjectBurnDowmChart projectBurnDowmChart = BeanMapper.map(projectBurnDowmChartEntity, ProjectBurnDowmChart.class);
        return projectBurnDowmChart;
    }

    @Override
    public List<ProjectBurnDowmChart> findList(List<String> idList) {
        List<ProjectBurnDowmChartEntity> projectBurnDowmChartEntityList =  projectBurnDowmChartDao.findProjectBurnDowmChartList(idList);

        List<ProjectBurnDowmChart> projectBurnDowmChartList =  BeanMapper.mapList(projectBurnDowmChartEntityList,ProjectBurnDowmChart.class);
        return projectBurnDowmChartList;
    }

    @Override
    public ProjectBurnDowmChart findProjectBurnDowmChart(@NotNull String id) {
        ProjectBurnDowmChart projectBurnDowmChart = findOne(id);

        joinTemplate.joinQuery(projectBurnDowmChart);

        return projectBurnDowmChart;
    }

    @Override
    public List<ProjectBurnDowmChart> findAllProjectBurnDowmChart() {
        List<ProjectBurnDowmChartEntity> projectBurnDowmChartEntityList =  projectBurnDowmChartDao.findAllProjectBurnDowmChart();

        List<ProjectBurnDowmChart> projectBurnDowmChartList =  BeanMapper.mapList(projectBurnDowmChartEntityList,ProjectBurnDowmChart.class);

        joinTemplate.joinQuery(projectBurnDowmChartList);

        return projectBurnDowmChartList;
    }

    @Override
    public List<ProjectBurnDowmChart> findProjectBurnDowmChartList(ProjectBurnDowmChartQuery projectBurnDowmChartQuery) {
        List<ProjectBurnDowmChartEntity> projectBurnDowmChartEntityList = projectBurnDowmChartDao.findProjectBurnDowmChartList(projectBurnDowmChartQuery);

        List<ProjectBurnDowmChart> projectBurnDowmChartList = BeanMapper.mapList(projectBurnDowmChartEntityList,ProjectBurnDowmChart.class);

        joinTemplate.joinQuery(projectBurnDowmChartList);

        return projectBurnDowmChartList;
    }

    @Override
    public Pagination<ProjectBurnDowmChart> findProjectBurnDowmChartPage(ProjectBurnDowmChartQuery projectBurnDowmChartQuery) {
        Pagination<ProjectBurnDowmChartEntity>  pagination = projectBurnDowmChartDao.findProjectBurnDowmChartPage(projectBurnDowmChartQuery);

        List<ProjectBurnDowmChart> projectBurnDowmChartList = BeanMapper.mapList(pagination.getDataList(),ProjectBurnDowmChart.class);

        joinTemplate.joinQuery(projectBurnDowmChartList);

        return PaginationBuilder.build(pagination,projectBurnDowmChartList);
    }
}