package io.tiklab.teamwire.project.version.service;

import io.tiklab.teamwire.project.version.model.ProjectVersion;
import io.tiklab.teamwire.project.version.model.ProjectVersionQuery;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teamwire.project.version.dao.ProjectVersionDao;
import io.tiklab.teamwire.project.version.entity.ProjectVersionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 项目版本服务
*/
@Service
public class ProjectProjectVersionServiceImpl implements ProjectVersionService {

    @Autowired
    ProjectVersionDao projectVersionDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createVersion(@NotNull @Valid ProjectVersion projectVersion) {
        ProjectVersionEntity projectVersionEntity = BeanMapper.map(projectVersion, ProjectVersionEntity.class);

        return projectVersionDao.createVersion(projectVersionEntity);
    }

    @Override
    public void updateVersion(@NotNull @Valid ProjectVersion projectVersion) {
        ProjectVersionEntity projectVersionEntity = BeanMapper.map(projectVersion, ProjectVersionEntity.class);

        projectVersionDao.updateVersion(projectVersionEntity);
    }

    @Override
    public void deleteVersion(@NotNull String id) {
        projectVersionDao.deleteVersion(id);
    }

    @Override
    public ProjectVersion findOne(@NotNull String id) {
        ProjectVersionEntity projectVersionEntity = projectVersionDao.findVersion(id);

        ProjectVersion projectVersion = BeanMapper.map(projectVersionEntity, ProjectVersion.class);
        return projectVersion;
    }

    @Override
    public List<ProjectVersion> findList(List<String> idList) {
        List<ProjectVersionEntity> projectVersionEntityList =  projectVersionDao.findVersionList(idList);

        List<ProjectVersion> projectVersionList =  BeanMapper.mapList(projectVersionEntityList, ProjectVersion.class);
        return projectVersionList;
    }

    @Override
    public ProjectVersion findVersion(@NotNull String id) {
        ProjectVersion projectVersion = findOne(id);

        joinTemplate.joinQuery(projectVersion);
        return projectVersion;
    }

    @Override
    public List<ProjectVersion> findAllVersion() {
        List<ProjectVersionEntity> projectVersionEntityList =  projectVersionDao.findAllVersion();

        List<ProjectVersion> projectVersionList =  BeanMapper.mapList(projectVersionEntityList, ProjectVersion.class);

        joinTemplate.joinQuery(projectVersionList);

        return projectVersionList;
    }

    @Override
    public List<ProjectVersion> findVersionList(ProjectVersionQuery ProjectVersionQuery) {
        List<ProjectVersionEntity> projectVersionEntityList = projectVersionDao.findVersionList(ProjectVersionQuery);

        List<ProjectVersion> projectVersionList = BeanMapper.mapList(projectVersionEntityList, ProjectVersion.class);

        joinTemplate.joinQuery(projectVersionList);

        return projectVersionList;
    }

    @Override
    public Pagination<ProjectVersion> findVersionPage(ProjectVersionQuery ProjectVersionQuery) {

        Pagination<ProjectVersionEntity>  pagination = projectVersionDao.findVersionPage(ProjectVersionQuery);

        List<ProjectVersion> projectVersionList = BeanMapper.mapList(pagination.getDataList(), ProjectVersion.class);

        joinTemplate.joinQuery(projectVersionList);

        return PaginationBuilder.build(pagination, projectVersionList);
    }
}