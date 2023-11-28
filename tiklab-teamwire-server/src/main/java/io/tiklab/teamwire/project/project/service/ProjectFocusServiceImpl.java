package io.tiklab.teamwire.project.project.service;

import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.teamwire.project.project.model.ProjectFocus;
import io.tiklab.teamwire.project.project.model.ProjectFocusQuery;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teamwire.project.project.dao.ProjectFocusDao;
import io.tiklab.teamwire.project.project.entity.ProjectFocusEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 项目收藏服务
*/
@Service
public class ProjectFocusServiceImpl implements ProjectFocusService {

    @Autowired
    ProjectFocusDao projectFocusDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createProjectFocus(@NotNull @Valid ProjectFocus projectFocus) {
        projectFocus.setMasterId(LoginContext.getLoginId());

        ProjectFocusEntity projectFocusEntity = BeanMapper.map(projectFocus, ProjectFocusEntity.class);

        return projectFocusDao.createProjectFocus(projectFocusEntity);
    }

    @Override
    public void updateProjectFocus(@NotNull @Valid ProjectFocus projectFocus) {
        ProjectFocusEntity projectFocusEntity = BeanMapper.map(projectFocus, ProjectFocusEntity.class);

        projectFocusDao.updateProjectFocus(projectFocusEntity);
    }

    @Override
    public void deleteProjectFocusByQuery(@NotNull @Valid ProjectFocusQuery projectFocusQuery) {
        List<ProjectFocusEntity> projectFocusList = projectFocusDao.findProjectFocusList(projectFocusQuery);
        if(projectFocusList.size() > 0){
            for (ProjectFocusEntity projectFocusEntity : projectFocusList) {
                projectFocusDao.deleteProjectFocus(projectFocusEntity.getId());
            }
        }
    }

    @Override
    public void deleteProjectFocus(@NotNull String id) {
        projectFocusDao.deleteProjectFocus(id);
    }

    @Override
    public ProjectFocus findOne(String id) {
        ProjectFocusEntity projectFocusEntity = projectFocusDao.findProjectFocus(id);

        ProjectFocus projectFocus = BeanMapper.map(projectFocusEntity, ProjectFocus.class);
        return projectFocus;
    }

    @Override
    public List<ProjectFocus> findList(List<String> idList) {
        List<ProjectFocusEntity> projectFocusEntityList =  projectFocusDao.findProjectFocusList(idList);

        List<ProjectFocus> projectFocusList =  BeanMapper.mapList(projectFocusEntityList,ProjectFocus.class);
        return projectFocusList;
    }

    @Override
    public ProjectFocus findProjectFocus(@NotNull String id) {
        ProjectFocus projectFocus = findOne(id);

        joinTemplate.joinQuery(projectFocus);

        return projectFocus;
    }

    @Override
    public List<ProjectFocus> findAllProjectFocus() {
        List<ProjectFocusEntity> projectFocusEntityList =  projectFocusDao.findAllProjectFocus();

        List<ProjectFocus> projectFocusList =  BeanMapper.mapList(projectFocusEntityList,ProjectFocus.class);

        joinTemplate.joinQuery(projectFocusList);

        return projectFocusList;
    }

    @Override
    public List<ProjectFocus> findProjectFocusList(ProjectFocusQuery projectFocusQuery) {
        List<ProjectFocusEntity> projectFocusEntityList = projectFocusDao.findProjectFocusList(projectFocusQuery);

        List<ProjectFocus> projectFocusList = BeanMapper.mapList(projectFocusEntityList,ProjectFocus.class);

        joinTemplate.joinQuery(projectFocusList);

        return projectFocusList;
    }

    @Override
    public Pagination<ProjectFocus> findProjectFocusPage(ProjectFocusQuery projectFocusQuery) {
        Pagination<ProjectFocusEntity>  pagination = projectFocusDao.findProjectFocusPage(projectFocusQuery);

        List<ProjectFocus> projectFocusList = BeanMapper.mapList(pagination.getDataList(),ProjectFocus.class);

        joinTemplate.joinQuery(projectFocusList);

        return PaginationBuilder.build(pagination,projectFocusList);
    }
}