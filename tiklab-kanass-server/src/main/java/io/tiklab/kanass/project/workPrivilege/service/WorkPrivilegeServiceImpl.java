package io.tiklab.kanass.project.workPrivilege.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.kanass.project.workPrivilege.dao.WorkPrivilegeDao;
import io.tiklab.kanass.project.workPrivilege.entity.WorkPrivilegeEntity;
import io.tiklab.kanass.project.workPrivilege.model.WorkPrivilege;
import io.tiklab.kanass.project.workPrivilege.model.WorkPrivilegeQuery;
import io.tiklab.kanass.project.workPrivilege.model.WorkRoleFunction;
import io.tiklab.toolkit.beans.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 事项优先级服务
*/
@Service
public class WorkPrivilegeServiceImpl implements WorkPrivilegeService {

    @Autowired
    WorkPrivilegeDao workPrivilegeDao;

    @Autowired
    WorkRoleFunctionService workRoleFunctionService;

    @Override
    public String createWorkPrivilege(@NotNull @Valid WorkPrivilege workPrivilege) {
        WorkPrivilegeEntity workPrivilegeEntity = BeanMapper.map(workPrivilege, WorkPrivilegeEntity.class);

        return workPrivilegeDao.createWorkPrivilege(workPrivilegeEntity);
    }


    @Override
    public String copyWorkPrivilege(@NotNull @Valid WorkPrivilege workPrivilege, String dmWorkTypeId) {
        // 查找事项类型对应的权限方案
        String projectId = workPrivilege.getProjectId();
        String workTypeId = workPrivilege.getWorkTypeId();

        WorkPrivilegeQuery workPrivilegeQuery = new WorkPrivilegeQuery();
        workPrivilegeQuery.setWorkTypeId(workTypeId);
        List<WorkPrivilege> workPrivilegeList = findWorkPrivilegeList(workPrivilegeQuery);

        String workPrivilegeId = new String();
        // 复制权限方案
        if(workPrivilegeList.size() > 0){
            WorkPrivilege workPrivilege1 = workPrivilegeList.get(0);
            String id = workPrivilege1.getId();
            workPrivilege1.setProjectId(projectId);
            workPrivilege1.setId(null);
            workPrivilege1.setWorkTypeId(dmWorkTypeId);
            workPrivilege1.setScope("2");
            workPrivilegeId = createWorkPrivilege(workPrivilege1);


            // 复制所有的角色与权限的关联
            WorkRoleFunction workRoleFunction = new WorkRoleFunction();
            workRoleFunction.setPrivilegeId(id);
            workRoleFunction.setNewPrivilegeId(workPrivilegeId);
            workRoleFunctionService.copyAllWorkRoleFunction(workRoleFunction);
        }


        return workPrivilegeId;
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