package io.tiklab.teamwire.home.insight.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.teamwire.home.insight.model.Insight;
import io.tiklab.teamwire.home.insight.model.InsightQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 仪表盘接口
*/
public interface InsightService {

    /**
    * 创建仪表盘
    * @param insight
    * @return
    */
    String createInsight(@NotNull @Valid Insight insight);

    /**
    * 更新仪表盘
    * @param insight
    */
    void updateInsight(@NotNull @Valid Insight insight);

    /**
    * 删除仪表盘
    * @param id
    */
    void deleteInsight(@NotNull String id);

    Insight findOne(@NotNull String id);

    List<Insight> findList(List<String> idList);

    /**
    * 根据id查找仪表盘
    * @param id
    * @return
    */
    Insight findInsight(@NotNull String id);

    /**
    * 查找所有仪表盘
    * @return
    */
    List<Insight> findAllInsight();

    /**
    * 根据条件查询仪表盘列表
    * @param insightQuery
    * @return
    */
    List<Insight> findInsightList(InsightQuery insightQuery);

    /**
    * 按条件分页查询仪表盘列表
    * @param insightQuery
    * @return
    */
    Pagination<Insight> findInsightPage(InsightQuery insightQuery);

    List<Insight> findRecentInsightList(InsightQuery insightQuery);
    List<Insight> findFocusInsightList(InsightQuery insightQuery);

}