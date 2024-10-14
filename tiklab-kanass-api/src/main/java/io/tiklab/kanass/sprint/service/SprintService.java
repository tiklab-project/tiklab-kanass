package io.tiklab.kanass.sprint.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.JoinProvider;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.kanass.sprint.model.SprintQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 迭代服务接口
*/
@JoinProvider(model = Sprint.class)
public interface SprintService {

    /**
    * 创建迭代
    * @param sprint
    * @return
    */
    String createSprint(@NotNull @Valid Sprint sprint);

    String createJiraSprint(@NotNull @Valid Sprint sprint);

    /**
    * 更新迭代
    * @param sprint
    */
    void updateSprint(@NotNull @Valid Sprint sprint);

    /**
    * 删除迭代
    * @param id
    */
    void deleteSprint(@NotNull String id);

    /**
     * 根据迭代id查找迭代
     * @param id
     * @return
     */
    @FindOne
    Sprint findOne(@NotNull String id);

    /**
     * 根据多个id,查找迭代列表
     * @param idList
     * @return
     */
    @FindList
    List<Sprint> findList(@NotNull List<String> idList);

    /**
    * 根据id查找迭代列表
    * @param id
    * @return
    */
    Sprint findSprint(@NotNull String id);

    /**
    * 查找所有迭代列表
    * @return
    */
    @FindAll
    List<Sprint> findAllSprint();

    /**
     * 根据条件查找迭代列表
     * @param sprintQuery
     * @return
     */
    List<Sprint> findSprintList(SprintQuery sprintQuery);

    List<Sprint> findSelectSprintList(SprintQuery sprintQuery);

    /**
     * 根据条件查找我收藏的迭代
     * @param sprintQuery
     * @return
     */
    List<Sprint> findFocusSprintList(SprintQuery sprintQuery);

    /**
     * 根据条件按照分页查找迭代
     * @param sprintQuery
     * @return
     */
    Pagination<Sprint> findSprintPage(SprintQuery sprintQuery);

    /**
     * 查找进行中的迭代
     * @return
     */
    List<Sprint> findProgressSprint();

    /**
     * 根据事项id查找迭代
     * @param workId
     */
    List<Sprint> findWorkSprintList(@NotNull String workId);

}