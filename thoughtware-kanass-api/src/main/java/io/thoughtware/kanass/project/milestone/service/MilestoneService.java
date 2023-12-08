package io.thoughtware.kanass.project.milestone.service;


import io.thoughtware.core.page.Pagination;
import io.thoughtware.kanass.project.milestone.model.Milestone;
import io.thoughtware.kanass.project.milestone.model.MilestoneQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 里程碑服务接口
*/
public interface MilestoneService {

    /**
    * 创建里程碑
    * @param milestone
    * @return
    */
    String createMilestone(@NotNull @Valid Milestone milestone);

    /**
    * 更新里程碑
    * @param milestone
    */
    void updateMilestone(@NotNull @Valid Milestone milestone);

    /**
    * 删除里程碑
    * @param id
    */
    void deleteMilestone(@NotNull String id);

    Milestone findOne(@NotNull String id);

    List<Milestone> findList(List<String> idList);

    /**
    * 根据id查找里程碑
    * @param id
    * @return
    */
    Milestone findMilestone(@NotNull String id);

    /**
    * 查找所有里程碑
    * @return
    */
    List<Milestone> findAllMilestone();

    /**
    * 根据条件查询里程碑列表
    * @param milestoneQuery
    * @return
    */
    List<Milestone> findMilestoneList(MilestoneQuery milestoneQuery);

    /**
    * 根据条件按分页查询里程碑列表
    * @param milestoneQuery
    * @return
    */
    Pagination<Milestone> findMilestonePage(MilestoneQuery milestoneQuery);

}