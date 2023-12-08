package io.thoughtware.kanass.home.statistic.service;

import io.thoughtware.kanass.project.epic.model.Epic;
import io.thoughtware.kanass.project.version.model.ProjectVersion;
import io.thoughtware.kanass.sprint.model.Sprint;

import java.util.List;

/*
* 路线图服务接口
* */
public interface RoadMapService {
    /**
     * 迭代路线图
     * @return
     */
    List<Sprint> findSprintRoadMap(String projectId);

    /**
     * 版本路线图
     * @return
     */
    List<ProjectVersion> findVersionRoadMap(String projectId);

    /**
     *史诗路线图
     * @return
     */
    List<Epic> findEpicRoadMap(String projectId);

}
