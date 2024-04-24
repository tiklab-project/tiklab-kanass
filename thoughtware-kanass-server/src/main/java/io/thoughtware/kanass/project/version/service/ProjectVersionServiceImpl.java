package io.thoughtware.kanass.project.version.service;

import io.thoughtware.core.utils.UuidGenerator;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.kanass.project.version.model.*;
import io.thoughtware.kanass.sprint.model.Sprint;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.kanass.workitem.service.WorkVersionService;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.kanass.project.version.dao.ProjectVersionDao;
import io.thoughtware.kanass.project.version.entity.ProjectVersionEntity;
import io.thoughtware.kanass.workitem.service.WorkItemService;
import io.thoughtware.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    WorkVersionService workVersionService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createVersion(@NotNull @Valid ProjectVersion projectVersion) {
        String createId = LoginContext.getLoginId();
        User user = new User();
        user.setId(createId);
        projectVersion.setBuilder(user);

        ProjectVersionEntity projectVersionEntity = BeanMapper.map(projectVersion, ProjectVersionEntity.class);

        return projectVersionDao.createVersion(projectVersionEntity);
    }

    @Override
    public void updateVersion(@NotNull @Valid ProjectVersion projectVersion) {

        VersionState versionState = projectVersion.getVersionState();
        if(versionState != null && versionState.getId().equals("222222")){
            // 创建新的迭代与事项的记录
            String versionId = projectVersion.getId();
            String newVersionId = projectVersion.getNewVersionId();
            List<WorkItem> versionWorkItemList = workItemService.findVersionWorkItemList(versionId);
            if(versionWorkItemList.size() > 0){
                String valueString = "";
                for (WorkItem workItem : versionWorkItemList) {
                    if(newVersionId != null) {
                        ProjectVersion projectVersion1 = new ProjectVersion();
                        projectVersion1.setId(newVersionId);
                        workItem.setProjectVersion(projectVersion1);
                        workItem.setUpdateField("projectVersion");
                        workItemService.updateTodoTaskData(workItem);
                    }else {
                        workItem.setUpdateField("projectVersion");
                        workItem.setProjectVersion(null);
                        workItemService.updateTodoTaskData(workItem);
                    }

                    String id = UuidGenerator.getRandomIdByUUID(12);
                    String sql = "('" + id + "', '" + workItem.getId() + "', '" + newVersionId + "'),";
                    valueString = valueString.concat(sql);
                }
                int length = valueString.length() - 1;
                String substring = valueString.substring(0, length);
                if(newVersionId != null){
                    workVersionService.createBatchWorkVersion(substring);
                }


            }


            workItemService.updateBatchWorkItemVersion(versionId, newVersionId);
        }
        if(versionState != null && versionState.getId().equals("111111")){
            //设置创建时间
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = formater.format(new Date());
            projectVersion.setRelaStartTime(format);
        }

        //设置结束时间
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formater.format(new Date());
        projectVersion.setRelaPublishTime(format);
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
        HashMap<String, Integer> versionWorkItemNum = workItemService.findVersionWorkItemNum(id);
        projectVersion.setWorkNumber(versionWorkItemNum.get("all"));
        projectVersion.setWorkDoneNumber(versionWorkItemNum.get("done"));
        projectVersion.setWorkProgressNumber(versionWorkItemNum.get("progress"));
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
        if(projectVersionList.size() > 0){
            List<String> focusVersionIds = versionFocusService.findFocusVersionIds();
            String versionIds = "(" + projectVersionList.stream().map(item -> "'" + item.getId() + "'").
                    collect(Collectors.joining(", ")) + ")";
            List<String> versionWorkItemNum = workVersionService.findVersionWorkItemNum(versionIds);


            for (ProjectVersion projectVersion : projectVersionList) {
                String id = projectVersion.getId();
                if(focusVersionIds.contains(id)){
                    projectVersion.setFocusIs(true);
                }
                List<String> countList = versionWorkItemNum.stream().filter(item -> item.equals(id)).collect(Collectors.toList());
                projectVersion.setWorkNumber(countList.size());
            }
        }
        // 查找版本的事项数量

        joinTemplate.joinQuery(projectVersionList);

        return projectVersionList;
    }

    @Override
    public Pagination<ProjectVersion> findVersionPage(ProjectVersionQuery projectVersionQuery) {
        Pagination<ProjectVersionEntity> pagination = projectVersionDao.findVersionPage(projectVersionQuery);
        List<ProjectVersion> projectVersionList = BeanMapper.mapList(pagination.getDataList(), ProjectVersion.class);

        if(projectVersionList.size() > 0){
            List<String> focusVersionIds = versionFocusService.findFocusVersionIds();
            String versionIds = "(" + projectVersionList.stream().map(item -> "'" + item.getId() + "'").
                    collect(Collectors.joining(", ")) + ")";
            List<String> versionWorkItemNum = workVersionService.findVersionWorkItemNum(versionIds);


            for (ProjectVersion projectVersion : projectVersionList) {
                String id = projectVersion.getId();
                if(focusVersionIds.contains(id)){
                    projectVersion.setFocusIs(true);
                }
                List<String> countList = versionWorkItemNum.stream().filter(item -> item.equals(id)).collect(Collectors.toList());
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

    @Override
    public List<ProjectVersion> findSelectVersionList(ProjectVersionQuery projectVersionQuery) {
        List<ProjectVersionEntity> selectVersionList = projectVersionDao.findSelectVersionList(projectVersionQuery);
        List<ProjectVersion> projectVersionList = BeanMapper.mapList(selectVersionList, ProjectVersion.class);
        joinTemplate.joinQuery(projectVersionList);
        return projectVersionList;
    }

    @Override
    public List<ProjectVersion> findWorkVersionList(@NotNull String workId){
        List<ProjectVersionEntity> projectVersionEntityList = projectVersionDao.findWorkVersionList(workId);
        List<ProjectVersion> projectVersionList = BeanMapper.mapList(projectVersionEntityList, ProjectVersion.class);
        joinTemplate.joinQuery(projectVersionList);
        return projectVersionList;
    }


}