package io.tiklab.kanass.workitem.service;

import io.tiklab.kanass.workitem.model.WorkPriority;
import io.tiklab.kanass.workitem.model.WorkPriorityQuery;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.kanass.workitem.dao.WorkPriorityDao;
import io.tiklab.kanass.workitem.entity.WorkPriorityEntity;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.beans.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项优先级服务, 弃用
*/
@Service
public class WorkPriorityServiceImpl implements WorkPriorityService {

    @Autowired
    WorkPriorityDao workPriorityDao;

    @Override
    public String createWorkPriority(@NotNull @Valid WorkPriority workPriority) {
        WorkPriorityEntity workPriorityEntity = BeanMapper.map(workPriority, WorkPriorityEntity.class);

        return workPriorityDao.createWorkPriority(workPriorityEntity);
    }

    @Override
    public void updateWorkPriority(@NotNull @Valid WorkPriority workPriority) {
        WorkPriorityEntity workPriorityEntity = BeanMapper.map(workPriority, WorkPriorityEntity.class);

        workPriorityDao.updateWorkPriority(workPriorityEntity);
    }

    @Override
    public void deleteWorkPriority(@NotNull String id) {
        workPriorityDao.deleteWorkPriority(id);
    }

    @Override
    public WorkPriority findOne(String id) {
        WorkPriorityEntity workPriorityEntity = workPriorityDao.findWorkPriority(id);

        return BeanMapper.map(workPriorityEntity, WorkPriority.class);
    }

    @Override
    public List<WorkPriority> findList(List<String> idList) {
        List<WorkPriorityEntity> workPriorityEntityList =  workPriorityDao.findWorkPriorityList(idList);

        return BeanMapper.mapList(workPriorityEntityList,WorkPriority.class);
    }

    @Override
    public WorkPriority findWorkPriority(@NotNull String id) {
        return findOne(id);
    }

    @Override
    public List<WorkPriority> findAllWorkPriority() {
        List<WorkPriorityEntity> workPriorityEntityList =  workPriorityDao.findAllWorkPriority();

        return BeanMapper.mapList(workPriorityEntityList,WorkPriority.class);
    }

    @Override
    public List<WorkPriority> findWorkPriorityList(WorkPriorityQuery workPriorityQuery) {
        List<WorkPriorityEntity> workPriorityEntityList = workPriorityDao.findWorkPriorityList(workPriorityQuery);

        return BeanMapper.mapList(workPriorityEntityList,WorkPriority.class);
    }

    @Override
    public Pagination<WorkPriority> findWorkPriorityPage(WorkPriorityQuery workPriorityQuery) {

        Pagination<WorkPriorityEntity>  pagination = workPriorityDao.findWorkPriorityPage(workPriorityQuery);

        List<WorkPriority> workPriorityList = BeanMapper.mapList(pagination.getDataList(),WorkPriority.class);

        return PaginationBuilder.build(pagination,workPriorityList);
    }

    @Override
    public Integer findAllWorkPriorityNumber() {
        Integer workPriorityNumber = workPriorityDao.findAllWorkPriorityNumber();
        return workPriorityNumber;
    }
}