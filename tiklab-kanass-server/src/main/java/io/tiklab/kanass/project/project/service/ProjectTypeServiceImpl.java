package io.tiklab.kanass.project.project.service;

import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.model.ProjectQuery;
import io.tiklab.kanass.project.project.model.ProjectType;
import io.tiklab.kanass.project.project.model.ProjectTypeQuery;
import io.tiklab.core.exception.SystemException;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.project.project.dao.ProjectTypeDao;
import io.tiklab.kanass.project.project.entity.ProjectTypeEntity;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.beans.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 项目类型服务
*/
@Service
public class ProjectTypeServiceImpl implements ProjectTypeService {

    @Autowired
    ProjectTypeDao projectTypeDao;

    @Autowired
    ProjectService projectService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createProjectType(@NotNull @Valid ProjectType projectType) {
        ProjectTypeEntity projectTypeEntity = BeanMapper.map(projectType, ProjectTypeEntity.class);

        return projectTypeDao.createProjectType(projectTypeEntity);
    }

    @Override
    public void updateProjectType(@NotNull @Valid ProjectType projectType) {
        ProjectTypeEntity projectTypeEntity = BeanMapper.map(projectType, ProjectTypeEntity.class);

        projectTypeDao.updateProjectType(projectTypeEntity);
    }

    @Override
    public String deleteProjectType(@NotNull String id) {

        ProjectQuery projectQuery = new ProjectQuery();
        projectQuery.setProjectTypeId(id);
        List<Project> projectList = projectService.findProjectList(projectQuery);
        if(projectList != null && projectList.size()>0){
            throw new SystemException(3001,"类型使用中，不可删除");
        }else {
            projectTypeDao.deleteProjectType(id);
            return "删除成功";
        }

    }

    @Override
    public ProjectType findOne(String id) {
        ProjectTypeEntity projectTypeEntity = projectTypeDao.findProjectType(id);

        ProjectType projectType = BeanMapper.map(projectTypeEntity, ProjectType.class);
        return projectType;
    }

    @Override
    public List<ProjectType> findList(List<String> idList) {
        List<ProjectTypeEntity> projectTypeEntityList =  projectTypeDao.findProjectTypeList(idList);

        List<ProjectType> projectTypeList =  BeanMapper.mapList(projectTypeEntityList,ProjectType.class);
        return projectTypeList;
    }

    @Override
    public ProjectType findProjectType(@NotNull String id) {
        ProjectType projectType = findOne(id);

        joinTemplate.joinQuery(projectType);
        return projectType;
    }

    @Override
    public List<ProjectType> findAllProjectType() {
        List<ProjectTypeEntity> projectTypeEntityList =  projectTypeDao.findAllProjectType();

        List<ProjectType> projectTypeList =  BeanMapper.mapList(projectTypeEntityList,ProjectType.class);

        joinTemplate.joinQuery(projectTypeList);

        return projectTypeList;
    }

    @Override
    public List<ProjectType> findProjectTypeList(ProjectTypeQuery projectTypeQuery) {
        List<ProjectTypeEntity> projectTypeEntityList = projectTypeDao.findProjectTypeList(projectTypeQuery);

        List<ProjectType> projectTypeList = BeanMapper.mapList(projectTypeEntityList,ProjectType.class);

        joinTemplate.joinQuery(projectTypeList);

        return projectTypeList;
    }

    @Override
    public Pagination<ProjectType> findProjectTypePage(ProjectTypeQuery projectTypeQuery) {


        Pagination<ProjectTypeEntity>  pagination = projectTypeDao.findProjectTypePage(projectTypeQuery);


        List<ProjectType> projectTypeList = BeanMapper.mapList(pagination.getDataList(),ProjectType.class);

        joinTemplate.joinQuery(projectTypeList);


        return PaginationBuilder.build(pagination,projectTypeList);
    }
}