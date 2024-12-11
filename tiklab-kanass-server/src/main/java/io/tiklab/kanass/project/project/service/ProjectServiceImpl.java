package io.tiklab.kanass.project.project.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.utils.UuidGenerator;
import io.tiklab.dal.jdbc.JdbcTemplate;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.project.workPrivilege.service.WorkPrivilegeService;
import io.tiklab.message.message.model.MessageNoticePatch;
import io.tiklab.message.message.service.MessageDmNoticeService;
import io.tiklab.privilege.role.model.PatchUser;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.model.ProjectQuery;
import io.tiklab.kanass.project.project.model.ProjectType;
import io.tiklab.kanass.workitem.model.*;
import io.tiklab.kanass.workitem.service.WorkTypeDmService;
import io.tiklab.kanass.workitem.service.WorkTypeService;
import io.tiklab.privilege.role.service.RoleService;
import io.tiklab.privilege.vRole.service.VRoleService;
import io.tiklab.security.logging.logging.model.Logging;
import io.tiklab.security.logging.logging.model.LoggingType;
import io.tiklab.security.logging.logging.service.LoggingByTempService;
import io.tiklab.todotask.todo.model.TaskQuery;
import io.tiklab.todotask.todo.service.TaskService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.flow.flow.service.DmFlowService;
import io.tiklab.form.form.service.DmFormService;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.privilege.dmRole.service.DmRoleService;
import io.tiklab.kanass.project.project.dao.ProjectDao;
import io.tiklab.kanass.project.project.entity.ProjectEntity;
import io.tiklab.kanass.workitem.service.WorkItemService;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
* 项目服务
*/
@Exporter
@Service
public class ProjectServiceImpl implements ProjectService {
    public final ExecutorService executorService = Executors.newCachedThreadPool();

    private static Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ProjectDao projectDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    DmUserService dmUserService;

    @Autowired
    DmRoleService dmRoleService;


    @Autowired
    LoggingByTempService opLogByTemplService;

    @Autowired
    UserService userService;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    WorkTypeService workTypeService;

    @Autowired
    WorkTypeDmService workTypeDmService;

    @Autowired
    DmFlowService dmFlowService;

    @Autowired
    DmFormService dmFormService;


    @Autowired
    ProjectTypeService projectTypeService;

    @Autowired
    TaskService taskService;

    @Autowired
    MessageDmNoticeService messageDmNoticeService;

    @Autowired
    WorkPrivilegeService workPrivilegeService;

    @Autowired
    RoleService roleService;

    @Autowired
    VRoleService vRoleService;



    @Value("${base.url:null}")
    String baseUrl;

    /**
     * 添加项目创建的动态
     */
    void creatDynamic( Map<String, String> content){
        Logging log = new Logging();

        //查找设置发送人的信息
        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);

        log.setUser(user);
        content.put("master", user.getNickname());
        content.put("creatTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        content.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());

        LoggingType opLogType = new LoggingType();
        opLogType.setId("KANASS_LOGTYPE_PROJECTADD");
        log.setActionType(opLogType);

        log.setBgroup("kanass");
        log.setModule("project");
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));
        log.setData(JSONObject.toJSONString(content));
        log.setBaseUrl(baseUrl);
        log.setLink("/project/${projectId}/workitem");
        log.setAction(content.get("projectName"));
        opLogByTemplService.createLog(log);
    }

    @Override
    public String createProject(@NotNull @Valid Project project) {
        if (project.getId() == null) {
            String id = UuidGenerator.getRandomIdByUUID(12);
            project.setId(id);
        }
        if(ObjectUtils.isEmpty(project.getCreator())){
            project.setProjectState("1");
            String createUserId = LoginContext.getLoginId();
            project.setCreator(createUserId);
        }

        ProjectEntity projectEntity = BeanMapper.map(project, ProjectEntity.class);

        String projectKey = project.getProjectKey();
        String usedProjectName = projectDao.projectKeyIsOnly(projectKey);
        if(usedProjectName != null && !usedProjectName.isEmpty()){
            throw new ApplicationException("项目key已被" + usedProjectName + "项目使用");
        }

        String projectName = project.getProjectName();
        Boolean nameIsOnly = projectDao.projectNameIsOnly(projectName);
        if(!nameIsOnly){
            throw new ApplicationException("已存在同名项目");
        }
        String id = projectDao.createProject(projectEntity);
        // 初始化项目成员角色
        String masterId = project.getMaster().getId();
        initProjectDmRole(masterId, id);
        //创建动态
        Map<String, String> content = new HashMap<>();
        content.put("projectId", id);
        content.put("projectName", project.getProjectName());
        content.put("projectIcon", project.getIconUrl());
        ProjectType projectType = projectTypeService.findProjectType(project.getProjectType().getId());

        //设置项目类型
        if(projectType.getType().equals("scrum")){
            content.put("projectType", "projectScrumDetail");
        }
        if(projectType.getType().equals("nomal")){
            content.put("projectType", "projectNomalDetail");
        }
        executorService.submit(() -> {
            creatDynamic(content);
            MessageNoticePatch messageNoticePatch = new MessageNoticePatch();
            messageNoticePatch.setDomainId(id);
            messageDmNoticeService.initMessageDmNotice(messageNoticePatch);
        });

        //初始事项类型
        initWorkType(id);
        // 复制项目通知方案
        return id;
    }

    public void initProjectDmRole(String masterId, String projectId){
        List<PatchUser> patchUsers = new ArrayList<PatchUser>();
        if(!masterId.equals("111111")){
            // 初始化创建者
            PatchUser patchUser = new PatchUser();
            DmUser dmUser = new DmUser();
            dmUser.setDomainId(projectId);
            User user = new User();
            user.setId(masterId);
            dmUser.setUser(user);
            patchUser.setUserId(masterId);
            patchUser.setRoleType(2);
            patchUsers.add(patchUser);

            // 初始化"111111"
            PatchUser patchUser1 = new PatchUser();
            DmUser dmUser1 = new DmUser();
            dmUser1.setDomainId(projectId);
            User user1 = new User();
            user1.setId("111111");
            dmUser1.setUser(user1);

            patchUser1.setUserId("111111");
            patchUser1.setRoleType(2);
            patchUsers.add(patchUser1);

        }else {
            PatchUser patchUser = new PatchUser();
            DmUser dmUser = new DmUser();
            dmUser.setDomainId(projectId);
            User user = new User();
            user.setId(masterId);
            dmUser.setUser(user);
            patchUser.setUserId(masterId);
            patchUser.setRoleType(2);
            patchUsers.add(patchUser);
        }
        dmRoleService.initPatchDmRole(projectId,patchUsers);
    }
    @Override
    public String createJiraProject(@NotNull @Valid Project project) {
        if (project.getId() == null) {
            String id = UuidGenerator.getRandomIdByUUID(12);
            project.setId(id);
        }
        if(ObjectUtils.isEmpty(project.getCreator())){
            project.setProjectState("1");
            String createUserId = LoginContext.getLoginId();
            project.setCreator(createUserId);
        }

        ProjectEntity projectEntity = BeanMapper.map(project, ProjectEntity.class);

        String projectKey = project.getProjectKey();
        String usedProjectName = projectDao.projectKeyIsOnly(projectKey);
        if(usedProjectName != null && !usedProjectName.isEmpty()){
            throw new ApplicationException("项目key已被" + usedProjectName + "项目使用");
        }

        String projectName = project.getProjectName();
        Boolean nameIsOnly = projectDao.projectNameIsOnly(projectName);
        if(!nameIsOnly){
            throw new ApplicationException("已存在同名项目");
        }
        String id = projectDao.createProject(projectEntity);

        //初始事项类型
//        List<WorkTypeDm> workTypeDmList = initWorkType(id);

            return id;
    }

    //初始化类型，表单，流程

    /**
     * 初始化项目类型，只初始化系统的
     * @param projectId
     */
    public List<WorkTypeDm> initWorkType(String projectId) {

        List<WorkTypeDm> workTypeDmList = new ArrayList<>();
        WorkTypeQuery workTypeQuery = new WorkTypeQuery();
        workTypeQuery.setGrouper("system");
        List<WorkType> workTypeList = workTypeService.findWorkTypeList(workTypeQuery);


        for (WorkType workType : workTypeList) {
            WorkTypeDm workTypeDm = new WorkTypeDm();
            workTypeDm.setWorkType(workType);
            workTypeDm.setFlow(workType.getFlow());
            workTypeDm.setProjectId(projectId);
            workTypeDm.setForm(workType.getForm());
            WorkTypeDm workTypeDm1 = workTypeDmService.createWorkTypeDm(workTypeDm);

            workTypeDmList.add(workTypeDm1);
        }

        return workTypeDmList;
    }

    /**
     * 初始化项目类型，只初始化系统的
     * @param projectId
     */
    @Override
    public List<WorkTypeDm> initWorkTypeEpic(String projectId) {
        List<WorkTypeDm> workTypeDmList = new ArrayList<>();
        WorkTypeQuery workTypeQuery = new WorkTypeQuery();
        workTypeQuery.setGrouper("system");
        workTypeQuery.setCode("epic");
        List<WorkType> workTypeList = workTypeService.findWorkTypeList(workTypeQuery);

        for (WorkType workType : workTypeList) {
            WorkTypeDm workTypeDm = new WorkTypeDm();
            workTypeDm.setWorkType(workType);
            workTypeDm.setFlow(workType.getFlow());
            workTypeDm.setProjectId(projectId);
            WorkTypeDm workTypeDm1 = workTypeDmService.createWorkTypeDm(workTypeDm);
            workTypeDmList.add(workTypeDm1);
        }
        return workTypeDmList;
    }

    /**
     * 更新项目
     * @param project
     */
    @Override
    public void updateProject(@NotNull @Valid Project project) {
        //更新项目
        ProjectEntity projectEntity = BeanMapper.map(project, ProjectEntity.class);

        projectDao.updateProject(projectEntity);
        String id = project.getId();
        //创建动态
        Map<String, String> content = new HashMap<>();
        content.put("projectId", id);
        content.put("projectName", project.getProjectName());
    }

    /**
     * 删除项目
     * @param id
     */
    @Override
    public void deleteProject(@NotNull String id) {
        //删除模块、里程碑、事项类型、知识库、测试用例、模块、最近查看、项目燃尽图、关注的项目
        projectDao.deleteProjectAndRelation(id);
        //删除事项
        projectDao.deleteProjectWorkItem(id);
        //删除迭代
        projectDao.deleteSprint(id);
        //删除版本
        projectDao.deleteVersion(id);
        //删除阶段
        projectDao.deleteStage(id);

        // 项目成员
        dmUserService.deleteDmUserByDomainId(id);
        // 项目角色、权限
        dmRoleService.deleteDmRoleByDomainId(id);
        // 流程
        dmFlowService.deleteProjectFlow(id);
        // 表单
        dmFormService.deleteProjectForm(id);
        //删除项目
        projectDao.deleteProject(id);

        // 删除todotask
        // 删除事项产生的待办
        TaskQuery taskQuery = new TaskQuery();
        LinkedHashMap data = new LinkedHashMap();
        data.put("projectId", id);
        taskQuery.setData(data);
        taskQuery.setBgroup("kanass");
        try {
            taskService.deleteAllTask(taskQuery);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * 根据id 精确查找项目
     * @param id
     * @return
     */
    @Override
    public Project findOne(@NotNull String id) {
        ProjectEntity projectEntity = projectDao.findProject(id);

        return BeanMapper.map(projectEntity, Project.class);
    }

    /**
     * 根据项目多个id 查找项目
     * @param idList
     * @return
     */
    @Override
    public List<Project> findList(List<String> idList) {
        List<ProjectEntity> projectEntityList =  projectDao.findProjectList(idList);

        return BeanMapper.mapList(projectEntityList,Project.class);
    }

    /**
     * 根据项目id 查找项目
     * @param id
     * @return
     */
    @Override
    public Project findProject(@NotNull String id) {
        Project project = findOne(id);
        joinTemplate.joinQuery(project);
        return project;
    }

    @Override
    public Project findProjectAndWorkNum(@NotNull String id) {
        Project project = findOne(id);

        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setProjectId(id);
        int workItemListCount = workItemService.findWorkItemListCount(workItemQuery);

        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setDomainId(id);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);


        project.setWorkItemNumber(workItemListCount);
        project.setMember(dmUserList.size());
        joinTemplate.joinQuery(project);
        return project;
    }


    /**
     * 查找所有项目列表
     * @return
     */
    @Override
    public List<Project> findAllProject() {
        List<ProjectEntity> projectEntityList =  projectDao.findAllProject();

        List<Project> projectList = BeanMapper.mapList(projectEntityList,Project.class);

        joinTemplate.joinQuery(projectList);
        return projectList;
    }

    /**
     * 根据条件查找项目列表
     * @param projectQuery
     * @return
     */
    @Override
    public List<Project> findProjectList(ProjectQuery projectQuery) {
        List<ProjectEntity> projectEntityList =  projectDao.findProjectList(projectQuery);

        List<Project> projectList = BeanMapper.mapList(projectEntityList,Project.class);


        joinTemplate.joinQuery(projectList);
        return projectList;
    }

    /**
     * 查找我创建的项目
     * @param masterId
     * @return
     */
    @Override
    public List<Project> findMaterProjectList(String masterId) {
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setUserId(masterId);


        List<ProjectEntity> projectEntityList =  projectDao.findMaterProjectList(masterId);

        List<Project> projectList = BeanMapper.mapList(projectEntityList,Project.class);

        joinTemplate.joinQuery(projectList);

        return projectList;
    }


    /**
     * 查找我参与的项目列表
     * @param projectQuery
     * @return
     */
    @Override
    public List<Project> findJoinProjectList(ProjectQuery projectQuery){
        // 查找我所参与的私有项目
        DmUserQuery dmUserQuery = new DmUserQuery();
        String createUserId = LoginContext.getLoginId();
        dmUserQuery.setUserId(createUserId);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
        List<String> privateProjectIds = dmUserList.stream().map(DmUser::getDomainId).collect(Collectors.toList());

        //查找所有公开项目
        projectQuery.setProjectLimits("0");
        List<Project> projectListCommons = findProjectList(projectQuery);
        List<String> commonProjectListIds = projectListCommons.stream().map(project -> project.getId()).collect(Collectors.toList());

        privateProjectIds.addAll(commonProjectListIds);
        String[] allProjectIds = privateProjectIds.toArray(new String[privateProjectIds.size()]);
        projectQuery.setProjectLimits(null);
        projectQuery.setProjectIds(allProjectIds);

        List<ProjectEntity> joinProjectListEntity = projectDao.findJoinProjectList(projectQuery);
        List<Project> projectList = BeanMapper.mapList(joinProjectListEntity,Project.class);
        joinTemplate.joinQuery(projectList);
        return projectList;

    }

    /**
     * 查找我能查看的公开项目和有权限的私有项目
     * @param projectQuery
     * @return
     */
    @Override
    public Pagination<Project> findProjectPage(ProjectQuery projectQuery) {
        String userId = LoginContext.getLoginId();
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setUserId(userId);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
        List<String> collect = dmUserList.stream().map(DmUser::getDomainId).collect(Collectors.toList());
        String[] arr = collect.toArray(new String[collect.size()]);
        projectQuery.setProjectIds(arr);


        Pagination<ProjectEntity>  pagination = projectDao.findProjectPage(projectQuery);

        List<Project> projectList = BeanMapper.mapList(pagination.getDataList(),Project.class);

        joinTemplate.joinQuery(projectList);

        return PaginationBuilder.build(pagination,projectList);
    }

    /**
     * 查找进行中的项目
     * @return
     */
    public List<Project> findProgressProjectList() {
        List<ProjectEntity> projectEntityList =  projectDao.findProgressProjectList();

        List<Project> projectList = BeanMapper.mapList(projectEntityList,Project.class);

        joinTemplate.joinQuery(projectList);

        return projectList;
    }

    /**
     * 查找我最近查看的项目
     * @param projectQuery
     * @return
     */
    public List<Project> findRecentProjectList(ProjectQuery projectQuery) {
        String userId = LoginContext.getLoginId();
        projectQuery.setRecentMasterId(userId);

        List<ProjectEntity> projectEntityList =  projectDao.findAllRecentProjectList(projectQuery);
        List<Project> projectList = BeanMapper.mapList(projectEntityList,Project.class);
        joinTemplate.joinQuery(projectList);

        return projectList;
    }

    public List<Map<String, Object>> findRecentProjectWorkItemCount(String projectIds, String statusCode) {
        String sql = "select project_id, count(1) as count from pmc_work_item t where t.project_id in "+ projectIds + " and t.work_status_code = '" + statusCode + "' GROUP BY project_id";
        List<Map<String, Object>> maps = this.jpaTemplate.getJdbcTemplate().queryForList(sql);
        System.out.println(maps);
        return maps;
    }

    public List<Map<String, Object>> findProjectWorkItemStatus(String projectIds) {
        String sql = "select project_id, work_status_code from pmc_work_item t where t.project_id in "+ projectIds;
        List<Map<String, Object>> workItemList = this.jpaTemplate.getJdbcTemplate().queryForList(sql);
        return workItemList;
    }

    public List<Map<String, Object>> findProjectWorkItemType(String projectIds) {
        String sql = "select project_id, work_type_code from pmc_work_item t where t.project_id in "+ projectIds;
        List<Map<String, Object>> workItemList = this.jpaTemplate.getJdbcTemplate().queryForList(sql);
        return workItemList;
    }
    /**
     * 查找我收藏的项目
     * @param projectQuery
     * @return
     */
    @Override
    public List<Project> findFocusProjectList(ProjectQuery projectQuery) {
        String userId = LoginContext.getLoginId();
        projectQuery.setRecentMasterId(userId);

        List<ProjectEntity> projectEntityList =  projectDao.findFocusProjectList(projectQuery);

        List<Project> projectList = BeanMapper.mapList(projectEntityList,Project.class);

        joinTemplate.joinQuery(projectList);

        return projectList;
    }

    @Override
    public List<Project> findProjectListByKeyWords(String keyWord) {
        List<ProjectEntity> projectEntityList = projectDao.findProjectListByKeyWords(keyWord);

        List<Project> projectList = BeanMapper.mapList(projectEntityList,Project.class);

        joinTemplate.joinQuery(projectList);

        return null;
    }

    @Override
    public String creatProjectKey(String projectName) {
        return projectDao.creatProjectKey(projectName);

    }

    @Override
    public List<Project> findProjectSortRecentTime(ProjectQuery projectQuery) {

        List<Project> list = new ArrayList<>();
        projectQuery.setRecentMasterId(LoginContext.getLoginId());
        // 查找最近项目
        List<ProjectEntity> projectSortRecentTime = projectDao.findProjectSortRecentTime(projectQuery);
        List<Project> projectList = BeanMapper.mapList(projectSortRecentTime,Project.class);
        joinTemplate.joinQuery(projectList);

        int size = projectList.size();
        if(size < 5){
            List<String> collect = projectList.stream().map(item -> item.getId()).collect(Collectors.toList());
            String projectId = projectQuery.getProjectId();
            if(projectId != null){
                collect.add(projectId);
            }

            //如果不够5条，查找我可见的项目
            projectQuery.setProjectId(null);
            List<Project> joinProjectList = findJoinProjectList(projectQuery);
            // 去除已经被点击过的
            joinProjectList = joinProjectList.stream().filter(item -> !collect.contains(item.getId())).collect(Collectors.toList());
            int lackSize = 5 - size;
            if(joinProjectList.size() > lackSize){
                List<Project> projects = joinProjectList.subList(0, lackSize);
                projectList.addAll(projects);
            }else {
                projectList.addAll(joinProjectList);
            }

        }
        if(projectList.size() <= 0)  return list;

        // 拼接projects
        if(projectList.size() > 5){
            projectList = projectList.subList(0, 5);
        }
        String projectIds = "(" + projectList.stream().map(item -> "'" + item.getId() + "'").collect(Collectors.joining(", ")) + ")";


        List<Map<String, Object>> projectWorkItemCount = findProjectWorkItemStatus(projectIds);
        for (Project project : projectList) {
            String id = project.getId();
            List<Map<String, Object>> doneList = projectWorkItemCount.stream().filter(workItem -> (workItem.get("project_id").
                    equals(id) && workItem.get("work_status_code").equals("DONE"))).collect(Collectors.toList());
            project.setEndWorkItemNumber(doneList.size());

            List<Map<String, Object>> processList = projectWorkItemCount.stream().filter(workItem -> (workItem.get("project_id").
                    equals(id) && !workItem.get("work_status_code").equals("DONE"))).collect(Collectors.toList());
            project.setProcessWorkItemNumber(processList.size());
        }
        return projectList;
    }


    @Override
    public void batchCreateProject(){
        String sql = "INSERT INTO pmc_work_version (id, work_item_id, version_id) VALUES (?, ?, ?)";

        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{"61a4e8c529e6", "Xcodes-132", "04db9419d5ef"});
        batchArgs.add(new Object[]{"85b7a5bd8ea0", "Xcodes-133", "04db9419d5ef"});


        try {
            int[] updateCounts = jdbcTemplate.batchUpdate(sql, batchArgs);

            // updateCounts 数组将包含每个更新操作影响的记录数
            // 通常情况下，对于 INSERT 语句，这个数应该是 1（除非有数据库触发器导致额外的插入）
            for (int count : updateCounts) {
                System.out.println("Number of records inserted: " + count);
            }
        } catch (Exception e) {
            // 处理异常，例如回滚事务（如果在一个事务中）
            e.printStackTrace();
        }
    }
}