package io.tiklab.kanass.home.statistic.service;

import io.tiklab.kanass.project.version.model.ProjectVersion;
import io.tiklab.kanass.project.version.model.ProjectVersionQuery;
import io.tiklab.kanass.project.version.service.ProjectVersionService;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.kanass.sprint.model.SprintQuery;
import io.tiklab.kanass.sprint.service.SprintService;
import io.tiklab.kanass.project.epic.model.Epic;
import io.tiklab.kanass.project.epic.model.EpicQuery;
import io.tiklab.kanass.project.epic.service.EpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 路线图服务
* */
@Service
public class RoadMapServiceImpl implements RoadMapService {
    @Autowired
    SprintService sprintService;

    @Autowired
    ProjectVersionService projectVersionService;

    @Autowired
    EpicService epicService;

    @Override
    public List<Sprint> findSprintRoadMap(String projectId) {
        SprintQuery sprintQuery = new SprintQuery();
        sprintQuery.setProjectId(projectId);
        List<Sprint> sprintList = sprintService.findSprintList(sprintQuery);
        return sprintList;
    }

    @Override
    public List<ProjectVersion> findVersionRoadMap(String projectId) {
        ProjectVersionQuery ProjectVersionQuery = new ProjectVersionQuery();
        ProjectVersionQuery.setProjectId(projectId);
        List<ProjectVersion> projectVersionList = projectVersionService.findVersionList(ProjectVersionQuery);
        return projectVersionList;
    }

    @Override
    public List<Epic> findEpicRoadMap(String projectId) {
        EpicQuery epicQuery = new EpicQuery();
        epicQuery.setProjectId(projectId);
        List<Epic> epicList = epicService.findEpicList(epicQuery);
        return epicList;
    }

}
