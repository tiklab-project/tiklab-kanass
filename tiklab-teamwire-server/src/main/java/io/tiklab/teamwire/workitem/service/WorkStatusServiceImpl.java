package io.tiklab.teamwire.workitem.service;

import io.tiklab.teamwire.workitem.model.WorkStatus;
import io.tiklab.teamwire.workitem.model.WorkStatusQuery;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.teamwire.workitem.dao.WorkStatusDao;
import io.tiklab.teamwire.workitem.entity.WorkStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项状态服务
*/
@Service
public class WorkStatusServiceImpl implements WorkStatusService {

    @Autowired
    WorkStatusDao workStatusDao;

    @Override
    public String createWorkStatus(@NotNull @Valid WorkStatus workStatus) {
        WorkStatusEntity workStatusEntity = BeanMapper.map(workStatus, WorkStatusEntity.class);

        return workStatusDao.createWorkStatus(workStatusEntity);
    }

    @Override
    public void updateWorkStatus(@NotNull @Valid WorkStatus workStatus) {
        WorkStatusEntity workStatusEntity = BeanMapper.map(workStatus, WorkStatusEntity.class);

        workStatusDao.updateWorkStatus(workStatusEntity);
    }

    @Override
    public void deleteWorkStatus(@NotNull String id) {
        workStatusDao.deleteWorkStatus(id);
    }

    @Override
    public WorkStatus findOne(String id) {
        WorkStatusEntity workStatusEntity = workStatusDao.findWorkStatus(id);

        return BeanMapper.map(workStatusEntity, WorkStatus.class);
    }

    @Override
    public List<WorkStatus> findList(List<String> idList) {
        List<WorkStatusEntity> workStatusEntityList = workStatusDao.findWorkStatusList(idList);

        return BeanMapper.mapList(workStatusEntityList,WorkStatus.class);
    }

    @Override
    public WorkStatus findWorkStatus(@NotNull String id) {
        return findOne(id);
    }

    @Override
    public List<WorkStatus> findAllWorkStatus() {
        List<WorkStatusEntity> workStatusEntityList =  workStatusDao.findAllWorkStatus();

        return BeanMapper.mapList(workStatusEntityList,WorkStatus.class);
    }

    @Override
    public List<WorkStatus> findWorkStatusList(WorkStatusQuery workStatusQuery) {
        List<WorkStatusEntity> workStatusEntityList = workStatusDao.findWorkStatusList(workStatusQuery);

        return BeanMapper.mapList(workStatusEntityList,WorkStatus.class);
    }

    @Override
    public List<WorkStatus> findWorkStatusListBySorts(WorkStatusQuery workStatusQuery) {
        List<WorkStatusEntity> workStatusEntityList = workStatusDao.findWorkStatusListBySorts(workStatusQuery);

        return BeanMapper.mapList(workStatusEntityList,WorkStatus.class);
    }

    @Override
    public Pagination<WorkStatus> findWorkStatusPage(WorkStatusQuery workStatusQuery) {

        Pagination<WorkStatusEntity>  pagination = workStatusDao.findWorkStatusPage(workStatusQuery);

        List<WorkStatus> workStatusList = BeanMapper.mapList(pagination.getDataList(),WorkStatus.class);

        return PaginationBuilder.build(pagination,workStatusList);

    }

}