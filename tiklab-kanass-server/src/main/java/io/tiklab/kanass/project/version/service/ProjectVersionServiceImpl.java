package io.tiklab.kanass.project.version.service;

import io.tiklab.core.utils.UuidGenerator;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.project.version.model.*;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.service.WorkVersionService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.project.version.dao.ProjectVersionDao;
import io.tiklab.kanass.project.version.entity.ProjectVersionEntity;
import io.tiklab.kanass.workitem.service.WorkItemService;
import io.tiklab.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
* 版本接口
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

        // 设置版本头像颜色
        int color = new Random().nextInt(3) + 1;
        System.out.println(color);
        projectVersion.setColor(color);

        ProjectVersionEntity projectVersionEntity = BeanMapper.map(projectVersion, ProjectVersionEntity.class);
        return projectVersionDao.createVersion(projectVersionEntity);
    }

    @Override
    public void updateVersion(@NotNull @Valid ProjectVersion projectVersion) {
        VersionState versionState = projectVersion.getVersionState();
        if(versionState != null && versionState.getId().equals("222222")){
            // 若版本完成，没有完成事项转到新版本或者暂时不关联版本
            // 更新事项表中版本的信息为新版本或者为版本为空
            // 未完成事项转移到新版本，创建新的版本与事项的关联关系
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
                        // 更新待办中版本信息
                        workItemService.updateTodoTaskData(workItem);
                    }else {
                        workItem.setUpdateField("projectVersion");
                        workItem.setProjectVersion(null);
                        // 更新待办中版本信息
                        workItemService.updateTodoTaskData(workItem);
                    }

                    String id = UuidGenerator.getRandomIdByUUID(12);
                    String sql = "('" + id + "', '" + workItem.getId() + "', '" + newVersionId + "'),";
                    valueString = valueString.concat(sql);
                }
                int length = valueString.length() - 1;
                String substring = valueString.substring(0, length);
                if(newVersionId != null){
                    // 创建新版本与事项的关联
                    workVersionService.createBatchWorkVersion(substring);
                }
            }
            // 批量更新事项的版本
            workItemService.updateBatchWorkItemVersion(versionId, newVersionId);
        }
        if(versionState != null && versionState.getId().equals("111111")){
            //设置开始时间
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
        // 设置已完成的，进行中，所有的事项个数
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