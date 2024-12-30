package io.tiklab.kanass.project.workPrivilege.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.kanass.project.workPrivilege.dao.ProjectVroleDao;
import io.tiklab.kanass.project.workPrivilege.entity.ProjectVroleEntity;
import io.tiklab.kanass.project.workPrivilege.model.ProjectVrole;
import io.tiklab.kanass.project.workPrivilege.model.ProjectVroleQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 虚拟角色服务， 弃用
*/
@Service
public class ProjectVroleServiceImpl implements ProjectVroleService {

    @Autowired
    ProjectVroleDao projectVroleDao;

    @Override
    public String createProjectVrole(@NotNull @Valid ProjectVrole projectVrole) {
        ProjectVroleEntity projectVroleEntity = BeanMapper.map(projectVrole, ProjectVroleEntity.class);

        return projectVroleDao.createProjectVrole(projectVroleEntity);
    }

    @Override
    public void updateProjectVrole(@NotNull @Valid ProjectVrole projectVrole) {
        ProjectVroleEntity projectVroleEntity = BeanMapper.map(projectVrole, ProjectVroleEntity.class);

        projectVroleDao.updateProjectVrole(projectVroleEntity);
    }

    @Override
    public void deleteProjectVrole(@NotNull String id) {
        projectVroleDao.deleteProjectVrole(id);
    }

    @Override
    public ProjectVrole findOne(String id) {
        ProjectVroleEntity projectVroleEntity = projectVroleDao.findProjectVrole(id);

        return BeanMapper.map(projectVroleEntity, ProjectVrole.class);
    }

    @Override
    public List<ProjectVrole> findList(List<String> idList) {
        List<ProjectVroleEntity> projectVroleEntityList =  projectVroleDao.findProjectVroleList(idList);

        return BeanMapper.mapList(projectVroleEntityList,ProjectVrole.class);
    }

    @Override
    public ProjectVrole findProjectVrole(@NotNull String id) {
        return findOne(id);
    }

    @Override
    public List<ProjectVrole> findAllProjectVrole() {
        List<ProjectVroleEntity> projectVroleEntityList =  projectVroleDao.findAllProjectVrole();

        return BeanMapper.mapList(projectVroleEntityList,ProjectVrole.class);
    }

    @Override
    public List<ProjectVrole> findProjectVroleList(ProjectVroleQuery projectVroleQuery) {
        List<ProjectVroleEntity> projectVroleEntityList = projectVroleDao.findProjectVroleList(projectVroleQuery);

        return BeanMapper.mapList(projectVroleEntityList,ProjectVrole.class);
    }

    @Override
    public Pagination<ProjectVrole> findProjectVrolePage(ProjectVroleQuery projectVroleQuery) {

        Pagination<ProjectVroleEntity>  pagination = projectVroleDao.findProjectVrolePage(projectVroleQuery);

        List<ProjectVrole> projectVroleList = BeanMapper.mapList(pagination.getDataList(),ProjectVrole.class);

        return PaginationBuilder.build(pagination,projectVroleList);
    }

}