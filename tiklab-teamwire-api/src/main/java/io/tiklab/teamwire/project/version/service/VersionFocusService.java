package io.tiklab.teamwire.project.version.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.teamwire.project.version.model.VersionFocus;
import io.tiklab.teamwire.project.version.model.VersionFocusQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 迭代收藏服务接口
*/
public interface VersionFocusService {

    /**
    * 创建迭代收藏
    * @param versionFocus
    * @return
    */
    String createVersionFocus(@NotNull @Valid VersionFocus versionFocus);

    /**
    * 更新收藏的迭代
    * @param versionFocus
    */
    void updateVersionFocus(@NotNull @Valid VersionFocus versionFocus);

    /**
    * 删除收藏的迭代
    * @param id
    */
    void deleteVersionFocus(@NotNull String id);

    /**
     * 根据添加删除收藏的迭代
     * @param versionFocusQuery
     */
    void deleteVersionFocusByQuery(VersionFocusQuery versionFocusQuery);

    /**
     * 根据id查找收藏的迭代
     * @param id
     * @return
     */
    VersionFocus findOne(@NotNull String id);

    /**
     * 根据多个迭代id,查找收藏的迭代
     * @param idList
     * @return
     */
    List<VersionFocus> findList(List<String> idList);

    /**
    * 根据id查找迭代收藏
    * @param id
    * @return
    */
    VersionFocus findVersionFocus(@NotNull String id);

    /**
    * 查找所有收藏迭代
    * @return
    */
    List<VersionFocus> findAllVersionFocus();

    /**
    * 根据条件查询收藏的迭代列表
    * @param versionFocusQuery
    * @return
    */
    List<VersionFocus> findVersionFocusList(VersionFocusQuery versionFocusQuery);

    /**
    * 根据条件按分页查询收藏的迭代列表
    * @param versionFocusQuery
    * @return
    */
    Pagination<VersionFocus> findVersionFocusPage(VersionFocusQuery versionFocusQuery);
    List<String> findFocusVersionIds();
}