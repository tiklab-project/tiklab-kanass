package io.tiklab.teamwire.support.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.teamwire.support.model.Recent;
import io.tiklab.teamwire.support.model.RecentQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 最近访问的服务
*/
public interface RecentService {

    /**
    * 创建最近访问的
    * @param recent
    * @return
    */
    String createRecent(@NotNull @Valid Recent recent);

    /**
    * 更新最近访问的
    * @param recent
    */
    void updateRecent(@NotNull @Valid Recent recent);

    /**
    * 删除最近访问的
    * @param id
    */
    void deleteRecent(@NotNull String id);

    /**
     * 根据id 查找最近访问的
     * @param id
     * @return
     */
    Recent findOne(@NotNull String id);

    /**
     * 根据多个id, 查找最近访问的
     * @param idList
     * @return
     */
    List<Recent> findList(List<String> idList);

    /**
    * 根据id, 查找最近访问的
    * @param id
    * @return
    */
    Recent findRecent(@NotNull String id);

    /**
    * 查找所有最近访问的
    * @return
    */
    List<Recent> findAllRecent();

    /**
    * 查询最近访问列表
    * @param recentQuery
    * @return
    */
    List<Recent> findRecentList(RecentQuery recentQuery);
    List<Recent> findRecentListToModel(RecentQuery recentQuery);
    /**
    * 按分页查询最近访问的
    * @param recentQuery
    * @return
    */
    Pagination<Recent> findRecentPage(RecentQuery recentQuery);

}