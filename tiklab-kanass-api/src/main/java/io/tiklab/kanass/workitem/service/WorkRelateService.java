package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.kanass.workitem.model.WorkRelate;
import io.tiklab.kanass.workitem.model.WorkRelateQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
* 关联事项服务接口
*/
public interface WorkRelateService {

    /**
    * 创建关联事项
    * @param workRelate
    * @return
    */
    String createWorkRelate(@NotNull @Valid WorkRelate workRelate);

    /**
    * 更新关联事项
    * @param workRelate
    */
    void updateWorkRelate(@NotNull @Valid WorkRelate workRelate);

    /**
    * 删除关联事项
    * @param id
    */
    void deleteWorkRelate(@NotNull String id);

    void deleteWorkRelateCondition(@NotNull @Valid WorkRelateQuery workRelateQuery);
    /**
    * 根据id查找关联事项
    * @param id
    * @return
    */
    WorkRelate findWorkRelate(@NotNull String id);

    /**
    * 查找所有关联事项
    * @return
    */
    List<WorkRelate> findAllWorkRelate();

    /**
    * 查询关联事项列表
    * @param workRelateQuery
    * @return
    */
    List<Map<String, Object>> findWorkRelateList(WorkRelateQuery workRelateQuery);

    /**
    * 按分页查询关联事项列表
    * @param workRelateQuery
    * @return
    */
    Pagination<WorkRelate> findWorkRelatePage(WorkRelateQuery workRelateQuery);

}