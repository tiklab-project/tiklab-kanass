package io.tiklab.teamwire.project.epic.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.teamwire.project.epic.model.Epic;
import io.tiklab.teamwire.project.epic.model.EpicQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 史诗服务接口
*/
@JoinProvider(model = Epic.class)
public interface EpicService {

    /**
    * 创建史诗
    * @param epic
    * @return
    */
    String createEpic(@NotNull @Valid Epic epic);

    /**
    * 更新史诗
    * @param epic
    */
    void updateEpic(@NotNull @Valid Epic epic);

    /**
    * 删除史诗
    * @param id
    */
    void deleteEpic(@NotNull String id);

    @FindOne
    Epic findOne(@NotNull String id);

    @FindList
    List<Epic> findList(List<String> idList);

    /**
    * 按id查找史诗
    * @param id
    * @return
    */
    Epic findEpic(@NotNull String id);

    /**
    * 查找所有史诗
    * @return
    */
    @FindAll
    List<Epic> findAllEpic();

    /**
    * 按条件查询史诗列表
    * @param epicQuery
    * @return
    */
    List<Epic> findEpicList(EpicQuery epicQuery);

    /**
     * 分级查找史诗列表
     * @param epicQuery
     * @return
     */
    List<Epic> findEpicListTree(EpicQuery epicQuery);

    /**
    * 按分页查询史诗列表
    * @param epicQuery
    * @return
    */
    Pagination<Epic> findEpicPage(EpicQuery epicQuery);

}