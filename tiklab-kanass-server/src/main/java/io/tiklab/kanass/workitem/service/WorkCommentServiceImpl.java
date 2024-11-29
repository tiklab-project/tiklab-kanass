package io.tiklab.kanass.workitem.service;

import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.kanass.workitem.model.WorkComment;
import io.tiklab.kanass.workitem.model.WorkCommentQuery;
import io.tiklab.kanass.workitem.dao.WorkCommentDao;
import io.tiklab.kanass.workitem.entity.WorkCommentEntity;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项评论服务
*/
@Service
public class WorkCommentServiceImpl implements WorkCommentService {

    @Autowired
    WorkCommentDao workCommentDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createWorkComment(@NotNull @Valid WorkComment workComment) {
        WorkCommentEntity workCommentEntity = BeanMapper.map(workComment, WorkCommentEntity.class);

        return workCommentDao.createWorkComment(workCommentEntity);
    }

    @Override
    public void updateWorkComment(@NotNull @Valid WorkComment workComment) {
        WorkCommentEntity workCommentEntity = BeanMapper.map(workComment, WorkCommentEntity.class);

        workCommentDao.updateWorkComment(workCommentEntity);
    }

    @Override
    public void deleteWorkComment(@NotNull String id) {
        workCommentDao.deleteWorkComment(id);
    }

    public void deleteWorkCommentList(WorkCommentQuery workCommentQuery) {
        DeleteBuilders deleteBuilders = DeleteBuilders.createDelete(WorkCommentEntity.class)
                .eq("workItemId", workCommentQuery.getWorkItemId());

        if(workCommentQuery.getWorkItemIds() != null && workCommentQuery.getWorkItemIds().length != 0){
            deleteBuilders.in("workItemId", workCommentQuery.getWorkItemIds());
        }
        DeleteCondition deleteCondition = deleteBuilders.get();
        workCommentDao.deleteWorkComment(deleteCondition);
    }

    @Override
    public WorkComment findOne(String id) {
        WorkCommentEntity workCommentEntity = workCommentDao.findWorkComment(id);

        WorkComment workComment = BeanMapper.map(workCommentEntity, WorkComment.class);
        return workComment;
    }

    @Override
    public List<WorkComment> findList(List<String> idList) {
        List<WorkCommentEntity> workCommentEntityList =  workCommentDao.findWorkCommentList(idList);

        List<WorkComment> workCommentList =  BeanMapper.mapList(workCommentEntityList,WorkComment.class);
        return workCommentList;
    }

    @Override
    public WorkComment findWorkComment(@NotNull String id) {
        WorkComment workComment = findOne(id);

        joinTemplate.joinQuery(workComment);

        return workComment;
    }

    @Override
    public List<WorkComment> findAllWorkComment() {
        List<WorkCommentEntity> workCommentEntityList =  workCommentDao.findAllWorkComment();

        List<WorkComment> workCommentList =  BeanMapper.mapList(workCommentEntityList,WorkComment.class);

        joinTemplate.joinQuery(workCommentList);

        return workCommentList;
    }

    @Override
    public List<WorkComment> findWorkCommentList(WorkCommentQuery workCommentQuery) {
        List<WorkCommentEntity> workCommentEntityList = workCommentDao.findWorkCommentList(workCommentQuery);

        List<WorkComment> workCommentList = BeanMapper.mapList(workCommentEntityList,WorkComment.class);

        joinTemplate.joinQuery(workCommentList);

        return workCommentList;
    }

    @Override
    public Pagination<WorkComment> findWorkCommentPage(WorkCommentQuery workCommentQuery) {
        Pagination<WorkCommentEntity>  pagination = workCommentDao.findWorkCommentPage(workCommentQuery);

        List<WorkComment> workCommentList = BeanMapper.mapList(pagination.getDataList(),WorkComment.class);

        joinTemplate.joinQuery(workCommentList);

        return PaginationBuilder.build(pagination,workCommentList);
    }
}