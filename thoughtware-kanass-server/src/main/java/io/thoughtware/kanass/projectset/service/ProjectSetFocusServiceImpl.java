package io.thoughtware.kanass.projectset.service;

import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.kanass.projectset.model.ProjectSetFocus;
import io.thoughtware.kanass.projectset.model.ProjectSetFocusQuery;
import io.thoughtware.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.join.JoinTemplate;
import io.thoughtware.kanass.projectset.dao.ProjectSetFocusDao;
import io.thoughtware.kanass.projectset.entity.ProjectSetFocusEntity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 项目集收藏服务
*/
@Service
public class ProjectSetFocusServiceImpl implements ProjectSetFocusService {

    @Autowired
    ProjectSetFocusDao projectSetFocusDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createProjectSetFocus(@NotNull @Valid ProjectSetFocus projectSetFocus) {

        projectSetFocus.setMasterId(LoginContext.getLoginId());
        ProjectSetFocusEntity projectSetFocusEntity = BeanMapper.map(projectSetFocus, ProjectSetFocusEntity.class);

        return projectSetFocusDao.createProjectSetFocus(projectSetFocusEntity);
    }

    @Override
    public void updateProjectSetFocus(@NotNull @Valid ProjectSetFocus projectSetFocus) {
        ProjectSetFocusEntity projectSetFocusEntity = BeanMapper.map(projectSetFocus, ProjectSetFocusEntity.class);

        projectSetFocusDao.updateProjectSetFocus(projectSetFocusEntity);
    }

    @Override
    public void deleteProjectSetFocus(@NotNull String id) {
        projectSetFocusDao.deleteProjectSetFocus(id);
    }

    @Override
    public ProjectSetFocus findOne(String id) {
        ProjectSetFocusEntity projectSetFocusEntity = projectSetFocusDao.findProjectSetFocus(id);

        ProjectSetFocus projectSetFocus = BeanMapper.map(projectSetFocusEntity, ProjectSetFocus.class);
        return projectSetFocus;
    }

    @Override
    public List<ProjectSetFocus> findList(List<String> idList) {
        List<ProjectSetFocusEntity> projectSetFocusEntityList =  projectSetFocusDao.findProjectSetFocusList(idList);

        List<ProjectSetFocus> projectSetFocusList =  BeanMapper.mapList(projectSetFocusEntityList,ProjectSetFocus.class);
        return projectSetFocusList;
    }

    @Override
    public ProjectSetFocus findProjectSetFocus(@NotNull String id) {
        ProjectSetFocus projectSetFocus = findOne(id);

        joinTemplate.joinQuery(projectSetFocus);

        return projectSetFocus;
    }

    @Override
    public List<ProjectSetFocus> findAllProjectSetFocus() {
        List<ProjectSetFocusEntity> projectSetFocusEntityList =  projectSetFocusDao.findAllProjectSetFocus();

        List<ProjectSetFocus> projectSetFocusList =  BeanMapper.mapList(projectSetFocusEntityList,ProjectSetFocus.class);

        joinTemplate.joinQuery(projectSetFocusList);

        return projectSetFocusList;
    }

    @Override
    public List<ProjectSetFocus> findProjectSetFocusList(ProjectSetFocusQuery projectSetFocusQuery) {
        List<ProjectSetFocusEntity> projectSetFocusEntityList = projectSetFocusDao.findProjectSetFocusList(projectSetFocusQuery);

        List<ProjectSetFocus> projectSetFocusList = BeanMapper.mapList(projectSetFocusEntityList,ProjectSetFocus.class);

        joinTemplate.joinQuery(projectSetFocusList);

        return projectSetFocusList;
    }

    @Override
    public Pagination<ProjectSetFocus> findProjectSetFocusPage(ProjectSetFocusQuery projectSetFocusQuery) {
        Pagination<ProjectSetFocusEntity>  pagination = projectSetFocusDao.findProjectSetFocusPage(projectSetFocusQuery);

        List<ProjectSetFocus> projectSetFocusList = BeanMapper.mapList(pagination.getDataList(),ProjectSetFocus.class);

        joinTemplate.joinQuery(projectSetFocusList);

        return PaginationBuilder.build(pagination,projectSetFocusList);
    }

    @Override
    public void deleteProjectSetFocusByQuery(@NotNull @Valid ProjectSetFocusQuery projectSetFocusQuery) {
        List<ProjectSetFocusEntity> projectSetFocusList = projectSetFocusDao.findProjectSetFocusList(projectSetFocusQuery);
        if(projectSetFocusList.size() > 0){
            for (ProjectSetFocusEntity projectSetFocusEntity : projectSetFocusList) {
                projectSetFocusDao.deleteProjectSetFocus(projectSetFocusEntity.getId());
            }
        }
    }
}