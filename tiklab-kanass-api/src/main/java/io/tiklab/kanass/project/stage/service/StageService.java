package io.tiklab.kanass.project.stage.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;
import io.tiklab.kanass.project.stage.model.Stage;
import io.tiklab.kanass.project.stage.model.StageQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 项目阶段服务接口
*/
@JoinProvider(model = Stage.class)
public interface StageService {

    /**
    * 创建阶段
    * @param stage
    * @return
    */
    String createStage(@NotNull @Valid Stage stage);

    /**
    * 更新阶段
    * @param stage
    */
    void updateStage(@NotNull @Valid Stage stage);

    /**
    * 删除阶段
    * @param id
    */
    void deleteStage(@NotNull String id);

    @FindOne
    Stage findOne(@NotNull String id);

    /**
     * 根据多个id 查找阶段列表
     * @param idList
     * @return
     */
    @FindList
    List<Stage> findList(List<String> idList);

    /**
    * 根据id查找项目阶段
    * @param id
    * @return
    */
    Stage findStage(@NotNull String id);

    /**
    * 查找所有阶段
    * @return
    */
    @FindAll
    List<Stage> findAllStage();

    /**
    * 根据条件查找阶段列表
    * @param stageQuery
    * @return
    */
    List<Stage> findStageList(StageQuery stageQuery);

    /**
    * 根据条件按分页查询阶段列表
    * @param stageQuery
    * @return
    */
    Pagination<Stage> findStagePage(StageQuery stageQuery);
    Pagination<Stage> findStageListTreePage(StageQuery stageQuery);
    /**
     * 根据条件查找阶段树形列表
     * @param stageQuery
     * @return
     */
    List<Stage> findStageListTree(StageQuery stageQuery);

    Pagination<Stage> findParentStageList(StageQuery stageQuery);
    Integer findStageLevel(String id);
}