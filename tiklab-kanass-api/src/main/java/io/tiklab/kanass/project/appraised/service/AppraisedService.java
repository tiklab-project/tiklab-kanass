package io.tiklab.kanass.project.appraised.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.kanass.project.appraised.model.Appraised;
import io.tiklab.kanass.project.appraised.model.AppraisedQuery;
import io.tiklab.kanass.project.stage.model.Stage;
import io.tiklab.toolkit.join.annotation.FindAll;
import io.tiklab.toolkit.join.annotation.FindList;
import io.tiklab.toolkit.join.annotation.FindOne;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 项目评审服务接口
 */
@JoinProvider(model = Appraised.class)
public interface AppraisedService {


    /**
     * 创建项目评审
     * @param appraised
     * @return
     */
    String createAppraised(@NotNull @Valid Appraised appraised);

    /**
     * 更新项目评审
     * @param appraised
     */
    void updateAppraised(@NotNull @Valid Appraised appraised);

    /**
     * 删除项目评审
     * @param id
     */
    void deleteAppraised(@NotNull String id);

    @FindOne
    Appraised findOne(@NotNull String id);

    @FindList
    List<Appraised> findList(List<String> idList);

    /**
     * 根据id查找项目评审
     * @param id
     * @return
     */
    Appraised findAppraised(@NotNull String id);

    /**
     * 查找所有项目评审
     * @return
     */
    @FindAll
    List<Appraised> findAllAppraised();

    /**
     * 根据条件查找项目类型列表
     * @param AppraisedQuery
     * @return
     */
    List<Appraised> findAppraisedList(AppraisedQuery AppraisedQuery);

    /**
     * 根据条件按分页查询项目类型列表
     * @param AppraisedQuery
     * @return
     */
    Pagination<Appraised> findAppraisedPage(AppraisedQuery AppraisedQuery);
}
