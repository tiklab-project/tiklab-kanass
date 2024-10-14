package io.tiklab.kanass.project.version.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;
import io.tiklab.kanass.project.version.model.VersionState;
import io.tiklab.kanass.project.version.model.VersionStateQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 迭代状态服务
*/
@JoinProvider(model = VersionState.class)
public interface VersionStateService {

    /**
    * 创建迭代状态
    * @param versionState
    * @return
    */
    String createVersionState(@NotNull @Valid VersionState versionState);

    /**
    * 更新迭代状态
    * @param versionState
    */
    void updateVersionState(@NotNull @Valid VersionState versionState);

    /**
    * 删除迭代状态
    * @param id
    */
    void deleteVersionState(@NotNull String id);
    @FindOne
    VersionState findOne(@NotNull String id);
    @FindList
    List<VersionState> findList(List<String> idList);

    /**
    * 根据id查找迭代状态
    * @param id
    * @return
    */
    VersionState findVersionState(@NotNull String id);

    /**
    * 查找所有迭代状态
    * @return
    */
    @FindAll
    List<VersionState> findAllVersionState();

    /**
    * 根据条件查询迭代状态列表
    * @param versionStateQuery
    * @return
    */
    List<VersionState> findVersionStateList(VersionStateQuery versionStateQuery);

    /**
    * 根据条件按分页查询迭代状态列表
    * @param versionStateQuery
    * @return
    */
    Pagination<VersionState> findVersionStatePage(VersionStateQuery versionStateQuery);

}