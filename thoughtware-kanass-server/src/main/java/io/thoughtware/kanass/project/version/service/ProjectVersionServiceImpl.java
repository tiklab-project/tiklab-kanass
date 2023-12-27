package io.thoughtware.kanass.project.version.service;

import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.kanass.project.version.model.*;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.kanass.project.version.dao.ProjectVersionDao;
import io.thoughtware.kanass.project.version.entity.ProjectVersionEntity;
import io.thoughtware.kanass.workitem.service.WorkItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* 项目版本服务
*/
@Service
public class ProjectVersionServiceImpl implements ProjectVersionService {

    @Autowired
    ProjectVersionDao projectVersionDao;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    VersionFocusService versionFocusService;
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
        // 查找版本的事项数量
        String versionIds = "(" + projectVersionList.stream().map(item -> "'" + item.getId() + "'").
                collect(Collectors.joining(", ")) + ")";
        List<Map<String, Object>> sprintCount = workItemService.findWorkItemNum("version_id", versionIds);
        for (ProjectVersion version : projectVersionList) {
            String id = version.getId();
            List<Map<String, Object>> countList = sprintCount.stream().filter(item -> item.get("version_id").equals(id)).collect(Collectors.toList());
            version.setWorkNumber(countList.size());
        }
        joinTemplate.joinQuery(projectVersionList);

        return projectVersionList;
    }

    @Override
    public Pagination<ProjectVersion> findVersionPage(ProjectVersionQuery projectVersionQuery) {
        Pagination<ProjectVersionEntity>  pagination = projectVersionDao.findVersionPage(projectVersionQuery);
        List<ProjectVersion> projectVersionList = BeanMapper.mapList(pagination.getDataList(), ProjectVersion.class);

        if(projectVersionList.size() > 0){
            List<String> focusVersionIds = versionFocusService.findFocusVersionIds();
            String versionIds = "(" + projectVersionList.stream().map(item -> "'" + item.getId() + "'").
                    collect(Collectors.joining(", ")) + ")";
            List<Map<String, Object>> versionCount = workItemService.findWorkItemNum("version_id", versionIds);


            for (ProjectVersion projectVersion : projectVersionList) {
                String id = projectVersion.getId();
                if(focusVersionIds.contains(id)){
                    projectVersion.setFocusIs(true);
                }

                List<Map<String, Object>> countList = versionCount.stream().filter(item -> item.get("version_id").equals(id)).collect(Collectors.toList());
                projectVersion.setWorkNumber(countList.size());
            }

        }
        // 查找版本的事项数量

        joinTemplate.joinQuery(projectVersionList);

        return PaginationBuilder.build(pagination, projectVersionList);
    }

    @Override
    public List<ProjectVersion> findVersionFocusList(ProjectVersionQuery projectVersionQuery) {

        List<ProjectVersionEntity>  projectVersionListEntity = projectVersionDao.findVersionFocusList(projectVersionQuery);

        List<ProjectVersion> projectVersionList = BeanMapper.mapList(projectVersionListEntity, ProjectVersion.class);
        for (ProjectVersion projectVersion : projectVersionList) {
            projectVersion.setFocusIs(true);
        }


        joinTemplate.joinQuery(projectVersionList);

        return projectVersionList;
    }
}