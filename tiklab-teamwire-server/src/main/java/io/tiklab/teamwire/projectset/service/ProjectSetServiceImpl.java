package io.tiklab.teamwire.projectset.service;

import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.privilege.role.model.PatchUser;
import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.teamwire.project.project.model.ProjectQuery;
import io.tiklab.teamwire.project.project.service.ProjectService;
import io.tiklab.teamwire.projectset.dao.ProjectSetDao;
import io.tiklab.teamwire.projectset.entity.ProjectSetEntity;
import io.tiklab.teamwire.projectset.model.ProjectSet;
import io.tiklab.teamwire.projectset.model.ProjectSetQuery;
import io.tiklab.teamwire.sprint.model.Sprint;
import io.tiklab.teamwire.sprint.model.SprintQuery;
import io.tiklab.teamwire.sprint.service.SprintService;
import io.tiklab.teamwire.workitem.model.WorkItem;
import io.tiklab.teamwire.workitem.model.WorkItemQuery;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.privilege.dmRole.service.DmRoleService;

import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.teamwire.workitem.service.WorkItemService;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        projectSet.setCreator(createUserId);
        ProjectSetEntity projectSetEntity = BeanMapper.map(projectSet, ProjectSetEntity.class);
        String projectSetId = projectSetDao.createProjectSet(projectSetEntity);

        String masterId = user.getId();
        initProjectSetDmRole(masterId, projectSetId);

        //初始化项目权限

//        DmUser dmUser = new DmUser();
//        dmUser.setDomainId(projectSetId);
//        dmUser.setUser(user);;
//        dmUserService.createDmUser(dmUser);
//        dmRoleService.initDmRoles(projectSetId,masterId,"teamwire");

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
        dmRoleService.initPatchDmRole(projectSetId,patchUsers, "teamwire");
    }

    @Override
    public void updateProjectSet(@NotNull @Valid ProjectSet projectSet) {
        ProjectSetEntity projectSetEntity = BeanMapper.map(projectSet, ProjectSetEntity.class);

        projectSetDao.updateProjectSet(projectSetEntity);
    }

    @Override
    public void deleteProjectSet(@NotNull String id) {
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
        String projectIds = "(" + projectList.stream().map(item -> "'" + item.getId() + "'").collect(Collectors.joining(", ")) + ")";
        List<Map<String, Object>> projectWorkItemCount = projectService.findProjectWorkItemCount(projectIds);
        for (Project project : projectList) {
            String id = project.getId();
            List<Map<String, Object>> allList = projectWorkItemCount.stream().filter(workItem -> workItem.get("project_id").equals(id)).collect(Collectors.toList());

            project.setWorklItemNumber(allList.size());

            List<Map<String, Object>> doneList = projectWorkItemCount.stream().filter(workItem -> (workItem.get("project_id").equals(id) && workItem.get("work_status_code").equals("DONE"))).collect(Collectors.toList());
            project.setQuantityNumber(doneList.size());

            DmUserQuery dmUserQuery = new DmUserQuery();
            dmUserQuery.setDomainId(id);
            List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
            project.setMember(dmUserList.size());
        }
        return projectList;
    }



    @Override
    public List<Sprint> findSprintList(String projectId) {
        //查询迭代
        List<Sprint> sprintList = findSprint(projectId);
        List<Sprint> collect = sprintList.stream().map(sprint -> {
            List<WorkItem> workItemList = findWorkItemList(sprint.getId(),1);
            //添加总数
            sprint.setWorkNumber(workItemList.size());

            sprint.setQuantityNumber(0);
            return sprint;
        }).collect(Collectors.toList());
        joinTemplate.joinQuery(collect);
       return collect;
    }



    @Override
    public Map findProjectIsOrNotRe()  {
        Map projectMap = new HashMap<>();
        List<Project> allProject = projectService.findAllProject();
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
           workItemQuery.setSprintId(id);
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

        joinTemplate.joinQuery(projectSetList);

        return projectSetList;
    }

    @Override
    public List<ProjectSet> findRecentProjectSetList(ProjectSetQuery projectSetQuery) {
        String userId = LoginContext.getLoginId();
        projectSetQuery.setRecentMasterId(userId);

        List<ProjectSetEntity> projectSetEntityList = projectSetDao.findRecentProjectSetList(projectSetQuery);

        List<ProjectSet> projectSetList = BeanMapper.mapList(projectSetEntityList,ProjectSet.class);

        joinTemplate.joinQuery(projectSetList);

        return projectSetList;
    }


}