package io.thoughtware.kanass.projectset.service;

import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.privilege.dmRole.service.DmRoleService;
import io.thoughtware.privilege.role.model.PatchUser;
import io.thoughtware.kanass.project.project.model.Project;
import io.thoughtware.kanass.project.project.model.ProjectQuery;
import io.thoughtware.kanass.project.project.service.ProjectService;
import io.thoughtware.kanass.projectset.dao.ProjectSetDao;
import io.thoughtware.kanass.projectset.entity.ProjectSetEntity;
import io.thoughtware.kanass.projectset.model.ProjectSet;
import io.thoughtware.kanass.projectset.model.ProjectSetQuery;
import io.thoughtware.kanass.sprint.model.Sprint;
import io.thoughtware.kanass.sprint.model.SprintQuery;
import io.thoughtware.kanass.sprint.service.SprintService;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.kanass.workitem.model.WorkItemQuery;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.kanass.workitem.service.WorkItemService;
import io.thoughtware.user.dmUser.model.DmUser;
import io.thoughtware.user.dmUser.model.DmUserQuery;
import io.thoughtware.user.dmUser.service.DmUserService;
import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
* 项目集服务
*/
@Service
public class ProjectSetServiceImpl implements ProjectSetService {

    @Autowired
    ProjectSetDao projectSetDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ProjectService projectService;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    SprintService sprintService;

    @Autowired
    DmUserService dmUserService;

    @Autowired
    DmRoleService dmRoleService;

    @Autowired
    UserService userService;

    @Override
    public String createProjectSet(@NotNull @Valid ProjectSet projectSet) {
        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        projectSet.setMaster(user);
        int color = new Random().nextInt(3) + 1;
        System.out.println(color);
        projectSet.setColor(color);
        projectSet.setCreator(createUserId);
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        projectSet.setCreatTime(format);



        ProjectSetEntity projectSetEntity = BeanMapper.map(projectSet, ProjectSetEntity.class);
        String projectSetId = projectSetDao.createProjectSet(projectSetEntity);

        String masterId = user.getId();
        initProjectSetDmRole(masterId, projectSetId);

        return projectSetId;
    }

    public void initProjectSetDmRole(String masterId, String projectSetId){
        List<PatchUser> patchUsers = new ArrayList<PatchUser>();
        if(!masterId.equals("111111")){
            // 初始化创建者
            PatchUser patchUser = new PatchUser();
            DmUser dmUser = new DmUser();
            dmUser.setDomainId(projectSetId);
            User user = new User();
            user.setId(masterId);
            dmUser.setUser(user);
            patchUser.setId(masterId);
            patchUser.setAdminRole(true);
            patchUsers.add(patchUser);

            // 初始化"111111"
            PatchUser patchUser1 = new PatchUser();
            DmUser dmUser1 = new DmUser();
            dmUser1.setDomainId(projectSetId);
            User user1 = new User();
            user1.setId("111111");
            dmUser1.setUser(user1);

            patchUser1.setId("111111");
            patchUser1.setAdminRole(true);
            patchUsers.add(patchUser1);

        }else {
            PatchUser patchUser = new PatchUser();
            DmUser dmUser = new DmUser();
            dmUser.setDomainId(projectSetId);
            User user = new User();
            user.setId(masterId);
            dmUser.setUser(user);
            patchUser.setId(masterId);
            patchUser.setAdminRole(true);
            patchUsers.add(patchUser);
        }
        dmRoleService.initPatchDmRole(projectSetId,patchUsers, "kanass");
    }

    @Override
    public void updateProjectSet(@NotNull @Valid ProjectSet projectSet) {
        ProjectSetEntity projectSetEntity = BeanMapper.map(projectSet, ProjectSetEntity.class);

        projectSetDao.updateProjectSet(projectSetEntity);
    }

    @Override
    public void deleteProjectSet(@NotNull String id) {
        // 删除项目集与项目的关联
        ProjectQuery projectQuery = new ProjectQuery();
        projectQuery.setProjectSetId(id);
        List<Project> projectList = projectService.findProjectList(projectQuery);
        if(projectList.size() > 0){
            for (Project project : projectList) {
                project.setProjectSetId("nullstring");
                projectService.updateProject(project);
            }
        }

        projectSetDao.deleteProjectSet(id);
    }

    @Override
    public ProjectSet findOne(String id) {
        ProjectSetEntity projectSetEntity = projectSetDao.findProjectSet(id);

        ProjectSet projectSet = BeanMapper.map(projectSetEntity, ProjectSet.class);
        return projectSet;
    }

    @Override
    public List<ProjectSet> findList(List<String> idList) {
        List<ProjectSetEntity> projectSetEntityList =  projectSetDao.findProjectSetList(idList);

        List<ProjectSet> projectSetList =  BeanMapper.mapList(projectSetEntityList,ProjectSet.class);
        return projectSetList;
    }

    @Override
    public ProjectSet findProjectSet(@NotNull String id) {
        ProjectSet projectSet = findOne(id);

        joinTemplate.joinQuery(projectSet);
        return projectSet;
    }

    @Override
    public List<ProjectSet> findAllProjectSet() {
        List<ProjectSetEntity> projectSetEntityList =  projectSetDao.findAllProjectSet();

        List<ProjectSet> projectSetList =  BeanMapper.mapList(projectSetEntityList,ProjectSet.class);

        joinTemplate.joinQuery(projectSetList);
           if (projectSetList.size() > 0){
               //查询项目集下面相关的项目
            for (ProjectSet projectSet:projectSetList){
                Integer projectNum = projectSetDao.findProjectNum(projectSet.getId());
                //添加数量
                projectSet.setProjectNumber(projectNum);

            }
        }
        return projectSetList;
    }

    @Override
    public List<ProjectSet> findProjectSetList(ProjectSetQuery projectSetQuery) {
        List<ProjectSetEntity> projectSetEntityList = projectSetDao.findProjectSetList(projectSetQuery);

        List<ProjectSet> projectSetList = BeanMapper.mapList(projectSetEntityList,ProjectSet.class);
        findProjectNum(projectSetList);
        joinTemplate.joinQuery(projectSetList);

        return projectSetList;
    }

    @Override
    public Pagination<ProjectSet> findProjectSetPage(ProjectSetQuery projectSetQuery) {

        Pagination<ProjectSetEntity>  pagination = projectSetDao.findProjectSetPage(projectSetQuery);

        List<ProjectSet> projectSetList = BeanMapper.mapList(pagination.getDataList(),ProjectSet.class);

        joinTemplate.joinQuery(projectSetList);

        return PaginationBuilder.build(pagination,projectSetList);
    }

    @Override
    public List<Project> findProjectSetDetailList(ProjectQuery projectQuery) {
        List<Project> projectList = projectService.findProjectList(projectQuery);
        if(projectList.size() > 0){
            String projectIds = "(" + projectList.stream().map(item -> "'" + item.getId() + "'").collect(Collectors.joining(", ")) + ")";
            List<Map<String, Object>> projectWorkItemCount = projectService.findProjectWorkItemStatus(projectIds);
            for (Project project : projectList) {
                String id = project.getId();
                List<Map<String, Object>> allList = projectWorkItemCount.stream().filter(workItem -> workItem.get("project_id").equals(id)).collect(Collectors.toList());
                int size = allList.size();
                project.setWorkItemNumber(size);

                List<Map<String, Object>> doneList = projectWorkItemCount.stream().filter(workItem -> (workItem.get("project_id").equals(id) && workItem.get("work_status_code").equals("DONE"))).collect(Collectors.toList());
                project.setEndWorkItemNumber(doneList.size());

                DmUserQuery dmUserQuery = new DmUserQuery();
                dmUserQuery.setDomainId(id);
                List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
                project.setMember(dmUserList.size());
            }
        }

        return projectList;
    }




    @Override
    public Map findProjectIsOrNotRe()  {
        Map projectMap = new HashMap<>();
        ProjectQuery projectQuery = new ProjectQuery();
        List<Project> allProject = projectService.findJoinProjectList(projectQuery);
        if (!allProject.isEmpty()){
            //已关联的项目
            List<Project> relatedProjects = allProject.stream().filter(a -> !StringUtils.isEmpty(a.getProjectSetId()) ).collect(Collectors.toList());
            //未关联的项目
            List<Project> noRelatedProjects = allProject.stream().filter(b -> StringUtils.isEmpty(b.getProjectSetId())).collect(Collectors.toList());
            projectMap.put("relatedProjects",relatedProjects);
            projectMap.put("noRelatedProjects",noRelatedProjects);
        }
        return projectMap;
    }

    @Override
    public void addRelevance(ProjectSet projectSet) {
        //关联的项目id
        List<String> ids = projectSet.getIds();
        for (String id:ids){
            Project project = new Project();
            project.setProjectSetId(projectSet.getId());
            project.setId(id);
            projectService.updateProject(project);
        }
    }



    /**
     * 查询事项
     * @param id,type
     */
    public List<WorkItem> findWorkItemList(String id,Integer type){
        WorkItemQuery workItemQuery = new WorkItemQuery();
        if (type==0){
            workItemQuery.setProjectId(id);
        }
       if (type==1){
           workItemQuery.setCurrentSprintId(id);
       }
        List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);
        joinTemplate.joinQuery(workItemList);
        return workItemList;
    }

    /**
     * 查询迭代
     * @param id
     */
    public List<Sprint> findSprint(String id){
        SprintQuery sprintQuery = new SprintQuery();
        sprintQuery.setProjectId(id);
        List<Sprint> sprintList = sprintService.findSprintList(sprintQuery);
        return sprintList;
    }

    @Override
    public List<ProjectSet> findFocusProjectSetList(ProjectSetQuery projectSetQuery) {
        String userId = LoginContext.getLoginId();
        projectSetQuery.setFocusMasterId(userId);

        List<ProjectSetEntity> projectSetEntityList = projectSetDao.findFocusProjectSetList(projectSetQuery);

        List<ProjectSet> projectSetList = BeanMapper.mapList(projectSetEntityList,ProjectSet.class);
        findProjectNum(projectSetList);
        joinTemplate.joinQuery(projectSetList);

        return projectSetList;
    }

    @Override
    public List<ProjectSet> findRecentProjectSetList(ProjectSetQuery projectSetQuery) {
        String userId = LoginContext.getLoginId();
        projectSetQuery.setRecentMasterId(userId);

        List<ProjectSetEntity> projectSetEntityList = projectSetDao.findRecentProjectSetList(projectSetQuery);

        List<ProjectSet> projectSetList = BeanMapper.mapList(projectSetEntityList,ProjectSet.class);
        findProjectNum(projectSetList);
        joinTemplate.joinQuery(projectSetList);

        return projectSetList;
    }

//    @Override
    /**
     * 查看我能看到的所有项目集
     */
    @Override
    public List<ProjectSet> findJoinProjectSetList(ProjectSetQuery projectSetQuery){
        // 查找我所参与的私有项目
        DmUserQuery dmUserQuery = new DmUserQuery();
        String createUserId = LoginContext.getLoginId();
        dmUserQuery.setUserId(createUserId);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
        List<String> privateProjectSetIds = dmUserList.stream().map(DmUser::getDomainId).collect(Collectors.toList());

        String[] ids = privateProjectSetIds.toArray(new String[privateProjectSetIds.size()]);
        projectSetQuery.setProjectSetIds(ids);

        List<ProjectSetEntity> joinProjectSetList = projectSetDao.findJoinProjectSetList(projectSetQuery);
        List<ProjectSet> projectSetList = BeanMapper.mapList(joinProjectSetList,ProjectSet.class);

        findProjectNum(projectSetList);
        joinTemplate.joinQuery(projectSetList);
        return projectSetList;

    }


    public void findProjectNum(List<ProjectSet> projectSetList){
        int size = projectSetList.size();
        if(size > 0){
            List<String>  projectSetIds = projectSetList.stream().map(item -> item.getId() ).collect(Collectors.toList());
            String[] list = projectSetIds.toArray(new String[projectSetIds.size()]);
            ProjectQuery projectQuery = new ProjectQuery();
            projectQuery.setProjectSetIds(list);
            List<Project> projectList = projectService.findProjectList(projectQuery);
            for (ProjectSet projectSet : projectSetList) {
                List<Project> collect = projectList.stream().filter(item -> item.getProjectSetId().equals(projectSet.getId())).collect(Collectors.toList());
                projectSet.setProjectNumber(collect.size());
            }

        }
    }

    /**
     * 查找最近的5个点击的，没有5个，用最近添加的凑
     * @param projectSetQuery
     * @return
     */
    @Override
    public List<ProjectSet> findProjectSetSortRecentTime(ProjectSetQuery projectSetQuery) {

        // 查找最近项目集合
        List<ProjectSet> recentProjectSetList = findRecentProjectSetList(projectSetQuery);
        String projectSetId = projectSetQuery.getProjectSetId();
        if(projectSetId != null){
            recentProjectSetList = recentProjectSetList.stream().filter(item -> !item.getId().equals(projectSetId)).collect(Collectors.toList());
        }

        int size = recentProjectSetList.size();
        if(size < 5){
            List<String> collect = recentProjectSetList.stream().map(item -> item.getId()).collect(Collectors.toList());

            if(projectSetId != null){
                collect.add(projectSetId);
            }
            //如果不够5条，查找我可见的项目
            projectSetQuery.setProjectSetId(null);
            List<ProjectSet> joinProjectSetList = findJoinProjectSetList(projectSetQuery);
            // 去除已经被点击过的
            joinProjectSetList = joinProjectSetList.stream().filter(item -> !collect.contains(item.getId())).collect(Collectors.toList());
            int lackSize = 5 - size;
            if(joinProjectSetList.size() > lackSize){
                List<ProjectSet> projectSets = joinProjectSetList.subList(0, lackSize);
                recentProjectSetList.addAll(projectSets);
            }else {
                recentProjectSetList.addAll(joinProjectSetList);
            }
        }

        return recentProjectSetList;
    }

}