package io.tiklab.teamwire.home.statistic.service;

import io.tiklab.teamwire.project.epic.model.Epic;
import io.tiklab.teamwire.project.version.model.ProjectVersion;
import io.tiklab.teamwire.sprint.model.Sprint;

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
