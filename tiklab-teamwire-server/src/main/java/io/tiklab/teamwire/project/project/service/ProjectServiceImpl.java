package io.tiklab.teamwire.project.project.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.utils.UuidGenerator;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.teamwire.project.milestone.model.Milestone;
import io.tiklab.teamwire.project.milestone.model.MilestoneQuery;
import io.tiklab.teamwire.project.milestone.service.MilestoneService;
import io.tiklab.teamwire.project.module.model.Module;
import io.tiklab.teamwire.project.module.model.ModuleQuery;
import io.tiklab.teamwire.project.module.service.ModuleService;
import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.teamwire.project.project.model.ProjectQuery;
import io.tiklab.teamwire.project.project.model.ProjectType;
import io.tiklab.teamwire.project.version.model.ProjectVersion;
import io.tiklab.teamwire.project.version.model.ProjectVersionQuery;
import io.tiklab.teamwire.project.version.service.ProjectVersionService;
import io.tiklab.teamwire.sprint.model.Sprint;
import io.tiklab.teamwire.sprint.model.SprintQuery;
import io.tiklab.teamwire.sprint.service.SprintService;
import io.tiklab.teamwire.workitem.model.*;
import io.tiklab.teamwire.workitem.service.WorkTypeDmService;
import io.tiklab.teamwire.workitem.service.WorkTypeService;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.flow.flow.model.DmFlow;
import io.tiklab.flow.flow.model.DmFlowQuery;
import io.tiklab.flow.flow.model.Flow;
import io.tiklab.flow.flow.service.DmFlowService;
import io.tiklab.flow.flow.service.FlowService;
import io.tiklab.flow.statenode.model.StateNodeFlow;
import io.tiklab.flow.statenode.model.StateNodeFlowQuery;
import io.tiklab.flow.statenode.service.StateNodeFlowService;
import io.tiklab.flow.statenode.service.StateNodeService;
import io.tiklab.flow.transition.model.Transition;
import io.tiklab.flow.transition.model.TransitionQuery;
import io.tiklab.flow.transition.service.TransitionService;
import io.tiklab.form.form.model.*;
import io.tiklab.form.form.service.DmFormService;
import io.tiklab.form.form.service.FormFieldService;
import io.tiklab.form.form.service.FormService;
import io.tiklab.join.JoinTemplate;
import io.tiklab.message.message.model.SendMessageNotice;
import io.tiklab.message.message.service.SendMessageNoticeService;
import io.tiklab.privilege.dmRole.service.DmRoleService;
import io.tiklab.security.logging.model.Logging;
import io.tiklab.security.logging.model.LoggingType;
import io.tiklab.security.logging.service.LoggingByTemplService;
import io.tiklab.teamwire.project.project.dao.ProjectDao;
import io.tiklab.teamwire.project.project.entity.ProjectEntity;
import io.tiklab.teamwire.project.project.support.MessageTemplateProject;
import io.tiklab.teamwire.project.project.support.OpLogTemplateProject;
import io.tiklab.teamwire.workitem.service.WorkItemService;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
* 项目服务
*/
@Exporter
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectDao projectDao;


    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    DmUserService dmUserService;

    @Autowired
    DmRoleService dmRoleService;


    @Autowired
    LoggingByTemplService opLogByTemplService;

    @Autowired
    UserService userService;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    SprintService sprintService;

    @Autowired
    ProjectVersionService projectVersionService;

    @Autowired
    ModuleService moduleService;

    @Autowired
    MilestoneService milestoneService;

    @Autowired
    WorkTypeService workTypeService;

    @Autowired
    WorkTypeDmService workTypeDmService;

    @Autowired
    DmFlowService dmFlowService;

    @Autowired
    StateNodeService stateNodeService;

    @Autowired
    TransitionService transitionService;

    @Autowired
    StateNodeFlowService stateNodeFlowService;

    @Autowired
    FlowService flowService;

    @Autowired
    FormService formService;

    @Autowired
    DmFormService dmFormService;

    @Autowired
    FormFieldService formFieldService;

    @Autowired
    SendMessageNoticeService messageDispatchNoticeService;

    @Autowired
    ProjectTypeService projectTypeService;


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
        content.put("master", user.getName());
        content.put("creatTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        content.put("createUserIcon",user.getName().substring( 0, 1));

        LoggingType opLogType = new LoggingType();
        opLogType.setId(OpLogTemplateProject.TEAMWIRE_LOGTYPE_PROJECTADD);
        log.setActionType(opLogType);

        log.setBgroup("teamwire");
        log.setLoggingTemplateId(OpLogTemplateProject.TEAMWIRE_LOGTEMPLATE_PROJECTADD);
        log.setModule("project");
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));
        log.setContent(JSONObject.toJSONString(content));
        log.setBaseUrl(baseUrl);

        opLogByTemplService.createLog(log);
    }

    /**
     * 发送消息
     * @param content
     */
    void sendMessageForCreat(Map<String, String> content ){
        SendMessageNotice messageDispatchNotice = new SendMessageNotice();

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);

        content.put("master", user.getName());
        content.put("createUserIcon",user.getName().substring( 0, 1));
        content.put("sendTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        String msg = JSONObject.toJSONString(content);
        messageDispatchNotice.setId(MessageTemplateProject.TEAMWIRE_MESSAGETYPE_PROJECTADD);
        messageDispatchNotice.setSiteData(msg);
        messageDispatchNotice.setDingdingData(msg);
        messageDispatchNotice.setQywechatData(msg);
        messageDispatchNotice.setEmailData(msg);
        messageDispatchNotice.setBaseUrl(baseUrl);
        messageDispatchNoticeService.createMessageItem(messageDispatchNotice);
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

        //初始化项目成员，项目权限
        String masterId = project.getMaster().getId();
        DmUser dmUser = new DmUser();
        dmUser.setDomainId(id);
        User user = new User();
        user.setId(masterId);
        dmUser.setUser(user);
        dmUserService.createDmUser(dmUser);
        dmRoleService.initDmRoles(id, masterId,"teamwire");

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

        creatDynamic(content);
        sendMessageForCreat(content);

        //初始事项类型
        initWorkType(id);

        return id;
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
        if(!usedProjectName.isEmpty()){
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
            workTypeDm.setForm(workType.getForm());
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
        //creatDynamic(project.getId(),"update", null);


    }

    /**
     * 删除项目
     * @param id
     */
    @Override
    public void deleteProject(@NotNull String id) {
        //创建动态
        //creatDynamic(id,"delete", null);

                //删除事项
        //        deleteWorkItem(id);
        //
        //        //删除迭代
        //        deleteSprint(id);
        //
        //        //删除版本
        //        deleteVersion(id);
        //
        //        //删除模块
        //        deleteModule(id);
        //
        //        //删除里程碑
        //        deleteMileStone(id);
        //
        //        //删除事项类型
        //        deleteWorkTypeDm(id);
        //        //删除项目
        projectDao.deleteProject(id);

        dmRoleService.deleteDmRoleByDomainId(id);


    }

    /**
     * 删除项目下的迭代
     * @param id
     */
    public void deleteSprint(@NotNull String id){
        SprintQuery sprintQuery = new SprintQuery();
        sprintQuery.setProjectId(id);
        List<Sprint> sprintList = sprintService.findSprintList(sprintQuery);
        if(sprintList.size()>0){
            for (Sprint sprint : sprintList) {
                sprintService.deleteSprint(sprint.getId());
            }
        }
    }

    /**
     * 删除项目下的事项
     * @param id
     */
    public void deleteWorkItem(@NotNull String id){
        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setProjectId(id);
        List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);
        if(workItemList.size() > 0){
            for (WorkItem workItem : workItemList) {
                try {
                    workItemService.deleteWorkItem(workItem.getId());
                } catch (Exception e) {
                    throw new ApplicationException(e);
                }

            }
        }
    }

    /**
     * 删除项目下的版本
     * @param id
     */
    public void deleteVersion(@NotNull String id){
        ProjectVersionQuery ProjectVersionQuery = new ProjectVersionQuery();
        ProjectVersionQuery.setProjectId(id);
        List<ProjectVersion> projectVersionList = projectVersionService.findVersionList(ProjectVersionQuery);
        if(projectVersionList.size() >0){
            for (ProjectVersion projectVersion : projectVersionList) {
                projectVersionService.deleteVersion(projectVersion.getId());
            }
        }
    }

    /**
     * 删除项目下的模块
     * @param id
     */
    public void deleteModule(@NotNull String id){
        ModuleQuery moduleQuery = new ModuleQuery();
        moduleQuery.setProjectId(id);
        List<Module> moduleList = moduleService.findModuleList(moduleQuery);
        if(moduleList.size() > 0){
            for (Module module : moduleList) {
                moduleService.deleteModule(module.getId());
            }
        }
    }

    /**
     * 删除项目下的里程碑
     * @param id
     */
    public void deleteMileStone(@NotNull String id){
        MilestoneQuery milestoneQuery = new MilestoneQuery();
        milestoneQuery.setProjectId(id);
        List<Milestone> milestoneList = milestoneService.findMilestoneList(milestoneQuery);
        if(milestoneList.size() > 0){
            for (Milestone milestone : milestoneList) {
                milestoneService.deleteMilestone(milestone.getId());
            }

        }
    }

    /**
     * 删除项目下的事项类型
     * @param id
     */
    public void deleteWorkTypeDm(@NotNull String id){
        //查找项目下的事项类型
        WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
        workTypeDmQuery.setProjectId(id);
        List<WorkTypeDm> workTypeDmList = workTypeDmService.findWorkTypeDmList(workTypeDmQuery);
        if(workTypeDmList.size() > 0){
            for (WorkTypeDm workTypeDm : workTypeDmList) {
                Flow flow = workTypeDm.getFlow();
                String flowId = flow.getId();
                flowService.deleteFlow(flowId);

                //删除关联关系
                DmFlowQuery dmFlowQuery = new DmFlowQuery();
                dmFlowQuery.setDomainId(id);
                List<DmFlow> dmFlowList = dmFlowService.findDmFlowList(dmFlowQuery);
                if(dmFlowList.size() > 0){
                    for (DmFlow dmFlow : dmFlowList) {
                        dmFlowService.deleteDmFlow(dmFlow.getId());
                    }
                }

                //删除节点
                StateNodeFlowQuery stateNodeFlowQuery = new StateNodeFlowQuery();
                stateNodeFlowQuery.setFlowId(flowId);
                List<StateNodeFlow> stateNodeFlowList = stateNodeFlowService.findStateNodeFlowList(stateNodeFlowQuery);
                if(stateNodeFlowList.size() > 0){
                    for (StateNodeFlow stateNodeFlow : stateNodeFlowList) {
                        stateNodeFlowService.deleteStateNodeFlow(stateNodeFlow.getId());
                    }
                }

                //删除流转
                TransitionQuery transitionQuery = new TransitionQuery();
                transitionQuery.setFlowId(flowId);
                List<Transition> transitionList = transitionService.findTransitionList(transitionQuery);
                if(transitionList.size() > 0){
                    for (Transition transition : transitionList) {
                        transitionService.deleteTransition(transition.getId());
                    }
                }

                //删除表单
                Form form = workTypeDm.getForm();
                String formId = form.getId();
                formService.deleteForm(formId);

                //删除项目与表单关联
                DmFormQuery dmFormQuery = new DmFormQuery();
                dmFormQuery.setDomainId(id);
                List<DmForm> dmFormList = dmFormService.findDmFormList(dmFormQuery);
                if(dmFormList.size() > 0){
                    for (DmForm dmForm : dmFormList) {
                        // dmFormService.deleteDmForm(dmForm.getId());
                    }
                }

                FormFieldQuery formFieldQuery = new FormFieldQuery();
                formFieldQuery.setFormId(formId);
                List<FormField> formFieldList = formFieldService.findFormFieldList(formFieldQuery);
                if(formFieldList.size() > 0){
                    for (FormField formField : formFieldList) {
                        formFieldService.deleteFormField(formField.getId());
                    }
                }

                String workTypeDmId = workTypeDm.getId();
                workTypeDmService.deleteWorkTypeDm(workTypeDmId);
            }
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
        Integer workItemListCount = workItemService.findWorkItemListCount(workItemQuery);

        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setDomainId(id);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);

        project.setWorklItemNumber(workItemListCount);
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

        List<Project> projectList = findProjectList(projectQuery);

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

        List<ProjectEntity> projectEntityList =  projectDao.findRecentProjectList(projectQuery);

        List<Project> projectList = BeanMapper.mapList(projectEntityList,Project.class);

        joinTemplate.joinQuery(projectList);

        return projectList;
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
    public String creatProjectKey() {
        return projectDao.creatProjectKey();

    }

}