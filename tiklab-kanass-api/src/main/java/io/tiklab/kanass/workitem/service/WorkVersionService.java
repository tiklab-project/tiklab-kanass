package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.workitem.model.WorkVersion;
import io.tiklab.kanass.workitem.model.WorkVersionQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项状态服务接口
*/
@JoinProvider(model = WorkVersion.class)
public interface WorkVersionService {

    /**
    * 创建事项状态
    * @param workVersion
    * @return
    */
    String createWorkVersion(@NotNull @Valid WorkVersion workVersion);

    void createBatchWorkVersion(@NotNull String valueStrings);
    /**
    * 更新事项状态
    * @param workVersion
    */
    void updateWorkVersion(@NotNull @Valid WorkVersion workVersion);

    /**
    * 删除事项状态
    * @param id
    */
    void deleteWorkVersion(@NotNull String id);
    void deleteWorkVersionList(@NotNull @Valid WorkVersionQuery workVersionQuery);
    @FindOne
    WorkVersion findOne(@NotNull String id);

    @FindList
    List<WorkVersion> findList(@NotNull List<String> idList);

    /**
    * 根据id查找事项状态
    * @param id
    * @return
    */
    WorkVersion findWorkVersion(@NotNull String id);

    /**
    * 查找所有事项状态列表
    * @return
    */
    @FindAll
    List<WorkVersion> findAllWorkVersion();

    /**
    * 根据条件查询事项状态列表
    * @param workVersionQuery
    * @return
    */
    List<WorkVersion> findWorkVersionList(WorkVersionQuery workVersionQuery);

    /**
    * 按分页查询事项状态列表
    * @param workVersionQuery
    * @return
    */
    Pagination<WorkVersion> findWorkVersionPage(WorkVersionQuery workVersionQuery);

    List<String> findVersionWorkItemNum(String sprintIds);
}