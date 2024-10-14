package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.kanass.workitem.model.WorkComment;
import io.tiklab.kanass.workitem.model.WorkCommentQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项评论服务接口
*/
public interface WorkCommentService {

    /**
    * 创建事项评论
    * @param workComment
    * @return
    */
    String createWorkComment(@NotNull @Valid WorkComment workComment);

    /**
    * 更新事项评论
    * @param workComment
    */
    void updateWorkComment(@NotNull @Valid WorkComment workComment);

    /**
    * 删除事项评论
    * @param id
    */
    void deleteWorkComment(@NotNull String id);

    void deleteWorkCommentList(WorkCommentQuery workCommentQuery);
    WorkComment findOne(@NotNull String id);

    /**
     * 根据多个id 查找事项评论
     * @param idList
     * @return
     */
    List<WorkComment> findList(List<String> idList);

    /**
    * 根据id查找事项评论
    * @param id
    * @return
    */
    WorkComment findWorkComment(@NotNull String id);

    /**
    * 查找所有事项评论
    * @return
    */
    List<WorkComment> findAllWorkComment();

    /**
    * 根据条件查询事项评论列表
    * @param workCommentQuery
    * @return
    */
    List<WorkComment> findWorkCommentList(WorkCommentQuery workCommentQuery);

    /**
    * 根据条件按分页查询事项评论列表
    * @param workCommentQuery
    * @return
    */
    Pagination<WorkComment> findWorkCommentPage(WorkCommentQuery workCommentQuery);

}