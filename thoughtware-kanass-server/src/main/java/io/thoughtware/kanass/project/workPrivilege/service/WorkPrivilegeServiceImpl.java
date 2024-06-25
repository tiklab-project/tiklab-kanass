package io.thoughtware.kanass.project.workPrivilege.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.kanass.project.workPrivilege.dao.WorkPrivilegeDao;
import io.thoughtware.kanass.project.workPrivilege.entity.WorkPrivilegeEntity;
import io.thoughtware.kanass.project.workPrivilege.model.WorkPrivilege;
import io.thoughtware.kanass.project.workPrivilege.model.WorkPrivilegeQuery;
import io.thoughtware.toolkit.beans.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* 事项优先级服务
*/
@Service
public class WorkPrivilegeServiceImpl implements WorkPrivilegeService {

    @Autowired
    WorkPrivilegeDao workPrivilegeDao;

    @Override
    public String createWorkPrivilege(@NotNull @Valid WorkPrivilege workPrivilege) {
        WorkPrivilegeEntity workPrivilegeEntity = BeanMapper.map(workPrivilege, WorkPrivilegeEntity.class);

        return workPrivilegeDao.createWorkPrivilege(workPrivilegeEntity);
    }

    @Override
    public void updateWorkPrivilege(@NotNull @Valid WorkPrivilege workPrivilege) {
        WorkPrivilegeEntity workPrivilegeEntity = BeanMapper.map(workPrivilege, WorkPrivilegeEntity.class);

        workPrivilegeDao.updateWorkPrivilege(workPrivilegeEntity);
    }

    @Override
    public void deleteWorkPrivilege(@NotNull String id) {
        workPrivilegeDao.deleteWorkPrivilege(id);
    }

    @Override
    public WorkPrivilege findOne(String id) {
        WorkPrivilegeEntity workPrivilegeEntity = workPrivilegeDao.findWorkPrivilege(id);

        return BeanMapper.map(workPrivilegeEntity, WorkPrivilege.class);
    }

    @Override
    public List<WorkPrivilege> findList(List<String> idList) {
        List<WorkPrivilegeEntity> workPrivilegeEntityList =  workPrivilegeDao.findWorkPrivilegeList(idList);

        return BeanMapper.mapList(workPrivilegeEntityList,WorkPrivilege.class);
    }

    @Override
    public WorkPrivilege findWorkPrivilege(@NotNull String id) {
        return findOne(id);
    }

    @Override
    public List<WorkPrivilege> findAllWorkPrivilege() {
        List<WorkPrivilegeEntity> workPrivilegeEntityList =  workPrivilegeDao.findAllWorkPrivilege();

        return BeanMapper.mapList(workPrivilegeEntityList,WorkPrivilege.class);
    }

    @Override
    public List<WorkPrivilege> findWorkPrivilegeList(WorkPrivilegeQuery workPrivilegeQuery) {
        List<WorkPrivilegeEntity> workPrivilegeEntityList = workPrivilegeDao.findWorkPrivilegeList(workPrivilegeQuery);

        return BeanMapper.mapList(workPrivilegeEntityList,WorkPrivilege.class);
    }

    @Override
    public Pagination<WorkPrivilege> findWorkPrivilegePage(WorkPrivilegeQuery workPrivilegeQuery) {

        Pagination<WorkPrivilegeEntity>  pagination = workPrivilegeDao.findWorkPrivilegePage(workPrivilegeQuery);

        List<WorkPrivilege> workPrivilegeList = BeanMapper.mapList(pagination.getDataList(),WorkPrivilege.class);

        return PaginationBuilder.build(pagination,workPrivilegeList);
    }



}