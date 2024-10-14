package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.form.form.model.Form;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.JoinProvider;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.kanass.workitem.model.WorkType;
import io.tiklab.kanass.workitem.model.WorkTypeQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项类型服务接口
*/
@JoinProvider(model = WorkType.class)
public interface WorkTypeService {

    /**
    * 创建事项类型
    * @param workType
    * @return
    */
    String createWorkType(@NotNull @Valid WorkType workType);

    /**
    * 更新事项类型
    * @param workType
    */
    void updateWorkType(@NotNull @Valid WorkType workType);

    /**
    * 删除事项类型
    * @param id
    */
    String deleteWorkType(@NotNull String id);
//    String deleteWorkTypeById(@NotNull String id);
    @FindOne
    WorkType findOne(@NotNull String id);

    @FindList
    List<WorkType> findList(@NotNull List<String> idList);

    /**
    * 根据id查找事项类型
    * @param id
    * @return
    */
    WorkType findWorkType(@NotNull String id);

    /**
    * 查找所有事项类型
    * @return
    */
    @FindAll
    List<WorkType> findAllWorkType();

    /**
    * 根据条件查询事项类型列表
    * @param workTypeQuery
    * @return
    */
    List<WorkType> findWorkTypeList(WorkTypeQuery workTypeQuery);


    /**
    * 根据条件按分页查询事项类型列表
    * @param workTypeQuery
    * @return
    */
    Pagination<WorkType> findWorkTypePage(WorkTypeQuery workTypeQuery);

    /**
     * 根据事项类型ID查找关联表单设置
     * @param formId
     * @return
     */
    Form findFormConfig(String formId);

    /**
     * 根据事项编码查找事项
     * @param code
     * @return
     */
    String findWorkTypeByCode(@NotNull String code);

    Integer findAllWorkTypeNum();

}