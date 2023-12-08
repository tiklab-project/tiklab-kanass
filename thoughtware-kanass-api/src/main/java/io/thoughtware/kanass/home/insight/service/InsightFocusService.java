package io.thoughtware.kanass.home.insight.service;


import io.thoughtware.core.page.Pagination;
import io.thoughtware.kanass.home.insight.model.InsightFocus;
import io.thoughtware.kanass.home.insight.model.InsightFocusQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 项目收藏服务接口
*/
public interface InsightFocusService {

    /**
    * 创建收藏的项目
    * @param insightFocus
    * @return
    */
    String createInsightFocus(@NotNull @Valid InsightFocus insightFocus);

    /**
    * 更新项目收藏数据
    * @param insightFocus
    */
    void updateInsightFocus(@NotNull @Valid InsightFocus insightFocus);

    void deleteInsightFocusByQuery(@NotNull @Valid InsightFocusQuery insightFocusQuery);

    /**
    * 根据id删除收藏的项目
    * @param id
    */
    void deleteInsightFocus(@NotNull String id);

    InsightFocus findOne(@NotNull String id);

    /**
     * 根据多个id 查找收藏的项目列表
     * @param idList
     * @return
     */
    List<InsightFocus> findList(List<String> idList);

    /**
    * 根据查找收藏的项目
    * @param id
    * @return
    */
    InsightFocus findInsightFocus(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<InsightFocus> findAllInsightFocus();

    /**
    * 根据条件查询项目收藏列表
    * @param insightFocusQuery
    * @return
    */
    List<InsightFocus> findInsightFocusList(InsightFocusQuery insightFocusQuery);

    /**
    * 根据条件按分页查询项目的收藏列表
    * @param insightFocusQuery
    * @return
    */
    Pagination<InsightFocus> findInsightFocusPage(InsightFocusQuery insightFocusQuery);

}