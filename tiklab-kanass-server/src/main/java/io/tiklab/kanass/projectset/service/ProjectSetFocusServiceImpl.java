package io.tiklab.kanass.projectset.service;

import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.projectset.model.ProjectSetFocus;
import io.tiklab.kanass.projectset.model.ProjectSetFocusQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.projectset.dao.ProjectSetFocusDao;
import io.tiklab.kanass.projectset.entity.ProjectSetFocusEntity;


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

    /**
     * 通过条件删除项目集关联关系
     * @param projectSetFocusQuery
     */
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