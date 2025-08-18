package io.tiklab.kanass.project.appraised.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.appraised.model.AppraisedType;
import io.tiklab.kanass.project.appraised.model.AppraisedTypeQuery;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@JoinProvider(model = AppraisedType.class)
public interface AppraisedTypeService {
    /**
     * 创建评审类型
     * @param appraisedType
     * @return
     */
    String createAppraisedType(@NotNull @Valid AppraisedType appraisedType);

    /**
     * 更新评审类型
     * @param appraisedType
     */
    void updateAppraisedType(@NotNull @Valid AppraisedType appraisedType);

    /**
     * 删除评审类型
     * @param id
     */
    void deleteAppraisedType(@NotNull String id);
    @FindOne
    AppraisedType findOne(@NotNull String id);
    @FindList
    List<AppraisedType> findList(List<String> idList);

    /**
     * 根据id查找评审类型
     * @param id
     * @return
     */
    AppraisedType findAppraisedType(@NotNull String id);

    /**
     * 查找所有评审类型
     * @return
     */
    @FindAll
    List<AppraisedType> findAllAppraisedType();

    /**
     * 根据条件查询评审类型列表
     * @param appraisedTypeQuery
     * @return
     */
    List<AppraisedType> findAppraisedTypeList(AppraisedTypeQuery appraisedTypeQuery);

    /**
     * 根据条件按分页查询评审类型列表
     * @param appraisedTypeQuery
     * @return
     */
    Pagination<AppraisedType> findAppraisedTypePage(AppraisedTypeQuery appraisedTypeQuery);

}
