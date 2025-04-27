package io.tiklab.kanass.workitem.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.kanass.workitem.dao.WorkSprintDao;
import io.tiklab.kanass.workitem.entity.WorkSprintEntity;
import io.tiklab.kanass.workitem.model.WorkSprint;
import io.tiklab.kanass.workitem.model.WorkSprintQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 事项状态服务
 */
@Service
public class WorkSprintServiceImpl implements WorkSprintService {

    @Autowired
    WorkSprintDao workSprintDao;

    @Override
    public String createWorkSprint(@NotNull @Valid WorkSprint workSprint) {
        WorkSprintEntity workSprintEntity = BeanMapper.map(workSprint, WorkSprintEntity.class);

        return workSprintDao.createWorkSprint(workSprintEntity);
    }

    @Override
    public void createBatchWorkSprint(@NotNull String valueStrings) {
        workSprintDao.createBatchWorkSprint(valueStrings);

    }
    @Override
    public void updateWorkSprint(@NotNull @Valid WorkSprint workSprint) {
        WorkSprintEntity workSprintEntity = BeanMapper.map(workSprint, WorkSprintEntity.class);

        workSprintDao.updateWorkSprint(workSprintEntity);
    }

    @Override
    public void deleteWorkSprint(@NotNull String id) {
        workSprintDao.deleteWorkSprint(id);
    }

    @Override
    public void deleteWorkSprint(WorkSprintQuery workSprintQuery) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(WorkSprintEntity.class)
                .eq("workItemId", workSprintQuery.getWorkItemId())
                .in("workItemId", workSprintQuery.getWorkItemIds())
                .get();
        workSprintDao.deleteWorkSprintList(deleteCondition);
    }

    @Override
    public WorkSprint findOne(String id) {
        WorkSprintEntity workSprintEntity = workSprintDao.findWorkSprint(id);

        return BeanMapper.map(workSprintEntity, WorkSprint.class);
    }

    @Override
    public List<WorkSprint> findList(List<String> idList) {
        List<WorkSprintEntity> workSprintEntityList = workSprintDao.findWorkSprintList(idList);

        return BeanMapper.mapList(workSprintEntityList,WorkSprint.class);
    }

    @Override
    public WorkSprint findWorkSprint(@NotNull String id) {
        return findOne(id);
    }

    @Override
    public List<WorkSprint> findAllWorkSprint() {
        List<WorkSprintEntity> workSprintEntityList =  workSprintDao.findAllWorkSprint();

        return BeanMapper.mapList(workSprintEntityList,WorkSprint.class);
    }

    @Override
    public List<WorkSprint> findWorkSprintList(WorkSprintQuery workSprintQuery) {
        List<WorkSprintEntity> workSprintEntityList = workSprintDao.findWorkSprintList(workSprintQuery);

        return BeanMapper.mapList(workSprintEntityList,WorkSprint.class);
    }



    @Override
    public Pagination<WorkSprint> findWorkSprintPage(WorkSprintQuery workSprintQuery) {

        Pagination<WorkSprintEntity>  pagination = workSprintDao.findWorkSprintPage(workSprintQuery);

        List<WorkSprint> workSprintList = BeanMapper.mapList(pagination.getDataList(),WorkSprint.class);

        return PaginationBuilder.build(pagination,workSprintList);

    }

    @Override
    public List<String> findSprintWorkItemIds(String sprintId) {
        List<String> workItemIds = workSprintDao.findSprintWorkItemIds(sprintId);
        return workItemIds;
    }


    @Override
    public List<Map<String, String>> findSprintWorkItemNum(String sprintIds) {
        List<Map<String, String>> workItemIds = workSprintDao.findSprintWorkItemNum(sprintIds);
        return workItemIds;
    }

}