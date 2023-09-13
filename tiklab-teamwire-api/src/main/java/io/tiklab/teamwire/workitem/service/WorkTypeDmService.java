package io.tiklab.teamwire.workitem.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.teamwire.workitem.model.WorkTypeDm;
import io.tiklab.teamwire.workitem.model.WorkTypeDmQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 项目的事项类型服务接口
*/
@JoinProvider(model = WorkTypeDm.class)
public interface WorkTypeDmService {

    /**
    * 创建事项类型
    * @param workTypeDm
    * @return
    */
    WorkTypeDm createWorkTypeDm(@NotNull @Valid WorkTypeDm workTypeDm);

    /**
    * 更新事项类型
    * @param workTypeDm
    */
    void updateWorkTypeDm(@NotNull @Valid WorkTypeDm workTypeDm);

    /**
    * 删除事项类型
    * @param id
    */
    void deleteWorkTypeDm(@NotNull String id);


    @FindOne
    WorkTypeDm findOne(@NotNull String id);

    @FindList
    List<WorkTypeDm> findList(List<String> idList);

    /**
    * 根据id查找事项类型
    * @param id
    * @return
    */
    WorkTypeDm findWorkTypeDm(@NotNull String id);

    /**
    * 查找所有事项类型列表
    * @return
    */
    @FindAll
    List<WorkTypeDm> findAllWorkTypeDm();

    /**
    * 根据条件查询事项类型列表
    * @param workTypeDmQuery
    * @return
    */
    List<WorkTypeDm> findWorkTypeDmList(WorkTypeDmQuery workTypeDmQuery);

    /**
     * 根据条件查询事项类型列表
     * @param workTypeDmQuery
     * @return
     */
    List<WorkTypeDm> findWorkTypeDmListNoRepeat(WorkTypeDmQuery workTypeDmQuery);

    /**
    * 根据条件按分页查询事项类型列表
    * @param workTypeDmQuery
    * @return
    */
    Pagination<WorkTypeDm> findWorkTypeDmPage(WorkTypeDmQuery workTypeDmQuery);

    /**
     * 根据编码，项目id 查找事项类型
     * @param projectId
     * @param code
     * @return
     */
    WorkTypeDm findTaskWorkType(String projectId, String code);

    String findDemandWorkType(String projectId, String code);
}