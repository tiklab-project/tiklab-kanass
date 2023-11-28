package io.tiklab.teamwire.workitem.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.teamwire.workitem.model.WorkAttach;
import io.tiklab.teamwire.workitem.model.WorkAttachQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项附件服务接口
*/
public interface WorkAttachService {

    /**
    * 创建事项附件
    * @param workAttach
    * @return
    */
    String createWorkAttach(@NotNull @Valid WorkAttach workAttach);

    /**
    * 更新事项附件
    * @param workAttach
    */
    void updateWorkAttach(@NotNull @Valid WorkAttach workAttach);

    /**
    * 删除事项附件
    * @param id
    */
    void deleteWorkAttach(@NotNull String id);

    /**
    * 根据id查找事项附件
    * @param id
    * @return
    */
    WorkAttach findWorkAttach(@NotNull String id);

    /**
    * 查找所有事项附件
    * @return
    */
    List<WorkAttach> findAllWorkAttach();

    /**
    * 根据条件查询事项附件列表
    * @param workAttachQuery
    * @return
    */
    List<WorkAttach> findWorkAttachList(WorkAttachQuery workAttachQuery);

    /**
    * 根据条件按分页查询事项附件
    * @param workAttachQuery
    * @return
    */
    Pagination<WorkAttach> findWorkAttachPage(WorkAttachQuery workAttachQuery);

}