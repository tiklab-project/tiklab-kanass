package io.tiklab.kanass.project.mantis.service;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.dfs.client.DfsClient;
import io.tiklab.dfs.common.model.object.PutRequest;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.flow.statenode.model.StateNodeFlow;
import io.tiklab.form.field.model.SelectItem;
import io.tiklab.kanass.common.ErrorCode;
import io.tiklab.kanass.project.mantis.model.*;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.model.ProjectQuery;
import io.tiklab.kanass.project.project.model.ProjectType;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.kanass.project.version.model.ProjectVersion;
import io.tiklab.kanass.project.version.model.VersionState;
import io.tiklab.kanass.project.version.service.ProjectVersionService;
import io.tiklab.kanass.workitem.model.*;
import io.tiklab.kanass.workitem.service.WorkAttachService;
import io.tiklab.kanass.workitem.service.WorkCommentService;
import io.tiklab.kanass.workitem.service.WorkItemService;
import io.tiklab.kanass.workitem.service.WorkVersionService;
import io.tiklab.privilege.dmRole.model.DmRole;
import io.tiklab.privilege.dmRole.model.DmRoleUser;
import io.tiklab.privilege.dmRole.service.DmRoleService;
import io.tiklab.privilege.dmRole.service.DmRoleUserService;
import io.tiklab.privilege.role.model.Role;
import io.tiklab.privilege.role.model.RoleQuery;
import io.tiklab.privilege.role.model.RoleUserQuery;
import io.tiklab.privilege.role.service.RoleService;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.model.UserQuery;
import io.tiklab.user.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class Mantis2271ImportDataServiceImpl implements Mantis2271ImportDataService {
    private static final Logger log = LoggerFactory.getLogger(Mantis2271ImportDataServiceImpl.class);
    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectVersionService projectVersionService;

    @Autowired
    private WorkItemService workItemService;

    @Autowired
    private WorkVersionService workVersionService;

    @Autowired
    private DfsClient dfsClient;

    @Autowired
    private WorkAttachService workAttachService;

    @Autowired
    private WorkCommentService workCommentService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DmRoleService dmRoleService;

    @Autowired
    private DmUserService dmUserService;

    @Autowired
    private DmRoleUserService dmRoleUserService;

    // 存储项目对应的事项类型信息
    private ThreadLocal<Map<String, List<WorkTypeDm>>> ProjectWorkTypeDmMap = new ThreadLocal<>();

    private ThreadLocal<Set<MantisUser>> MantisUserSet = new ThreadLocal<>();
    private ThreadLocal<Set<MantisProject>> MantisProjectSet = new ThreadLocal<>();

    @Value("${unzip.mantisAttachment}")
    private String attachmentPath;
    @Override
    public String writeData(List<MantisIssue> mantisIssueList, Map<String, String> Steps, Map<String, Integer> Percent) throws InterruptedException {
        this.MantisUserSet.remove();
        this.MantisProjectSet.remove();
        this.ProjectWorkTypeDmMap.remove();

        String createUserId = LoginContext.getLoginId();
        Steps.put("step1", "process");
        Thread.sleep(2000);
        try {
            // 遍历list，先添加用户、项目、版本信息
            Set<MantisUser> mantisUsers = new HashSet<>();
            Set<MantisProject> mantisProjects = new HashSet<>();
            Set<MantisVersion> mantisVersions = new HashSet<>();
            for (MantisIssue issue : mantisIssueList) {
                // 添加创建者
                if (!issue.getReporterId().isBlank() && !issue.getReporterName().isBlank()) {
                    mantisUsers.add(new MantisUser(issue.getReporterId(), issue.getReporterName()));
                }
                // 添加处理者
                if (issue.getHandlerId() != null && issue.getHandlerName() != null && !issue.getHandlerId().isBlank() && !issue.getHandlerName().isBlank()) {
                    mantisUsers.add(new MantisUser(issue.getHandlerId(), issue.getHandlerName()));
                }
                // 添加项目
                if (!issue.getProjectId().isBlank() && !issue.getProjectName().isBlank()){
                    mantisProjects.add(new MantisProject(issue.getProjectId(), issue.getProjectName()));
                }
                // 添加版本
                if (issue.getVersion() != null && !issue.getVersion().isBlank()){
                    mantisVersions.add(new MantisVersion(issue.getVersion(), issue.getProjectId()));
                }
                if (issue.getFixVersion() != null && !issue.getFixVersion().isBlank()){
                    mantisVersions.add(new MantisVersion(issue.getFixVersion(), issue.getProjectId()));
                }
                if (issue.getTargetVersion() != null && !issue.getTargetVersion().isBlank()){
                    mantisVersions.add(new MantisVersion(issue.getTargetVersion(), issue.getProjectId()));
                }
                // 添加评论创建者
                if (issue.getMantisNoteList() != null) {
                    for (MantisNote note : issue.getMantisNoteList()) {
                        if (!note.getReporterId().isBlank() && !note.getReporterName().isBlank()) {
                            mantisUsers.add(new MantisUser(note.getReporterId(), note.getReporterName()));
                        }
                    }
                }
            }



            Steps.put("step2", "process");

            // 获取导入事项的总数
            int size = mantisIssueList.size();
            Percent.put(createUserId + "total", size);

            // 添加用户、项目、版本
            setGlobalUser(mantisUsers);
            this.MantisUserSet.set(mantisUsers);

            setProjectAndVersion(mantisProjects, mantisVersions);
            this.MantisProjectSet.set(mantisProjects);

            // 以项目分组的导入记录
            Map<String, List<MantisIssue>> projectInfo = mantisIssueList.stream().collect(Collectors.groupingBy(item -> item.getProjectId()));
            for (String s : projectInfo.keySet()) {
                // 某个版本对应的事项列表
                List<MantisIssue> issues = projectInfo.get(s);
                Set<MantisUser> userSet = new HashSet<>();
                for (MantisIssue issue : issues) {
                    // 添加创建者
                    if (!issue.getReporterId().isBlank() && !issue.getReporterName().isBlank()) {
                        userSet.add(new MantisUser(issue.getReporterId(), issue.getReporterName()));
                    }
                    // 添加处理者
                    if (issue.getHandlerId() != null && issue.getHandlerName() != null && !issue.getHandlerId().isBlank() && !issue.getHandlerName().isBlank()) {
                        userSet.add(new MantisUser(issue.getHandlerId(), issue.getHandlerName()));
                    }
                    // 添加评论创建者
                    if (issue.getMantisNoteList() != null) {
                        for (MantisNote note : issue.getMantisNoteList()) {
                            if (!note.getReporterId().isBlank() && !note.getReporterName().isBlank()) {
                                userSet.add(new MantisUser(note.getReporterId(), note.getReporterName()));
                            }
                        }
                    }

                }

                // 设置项目用户
                Map<String, String> roleIds = setProjectRole(s);

                setProjectUser(s,  userSet, roleIds);
            }

            // 添加事项
            setWorkItem(mantisIssueList,  mantisProjects, mantisVersions, mantisUsers, createUserId, Percent);

        }catch (Exception e){
            throw new ApplicationException(e);
        }finally {
            this.MantisUserSet.remove();
            this.MantisProjectSet.remove();
            this.ProjectWorkTypeDmMap.remove();
        }
        return "";
    }

    // 设置项目role
    public Map<String, String> setProjectRole(String oldProjectId){
        MantisProject mantisProject = MantisProjectSet.get().stream().filter(item -> item.getId().equals(oldProjectId)).toList().get(0);
        String projectId = mantisProject.getNewID();
        //查找项目级角色
        Map<String, String> roleIds = new HashMap<>();
        RoleQuery roleQuery = new RoleQuery();
        roleQuery.setType("2");
        roleQuery.setScope(1);
        List<Role> roleList = roleService.findRoleList(roleQuery);
        if(roleList == null || roleList.size()==0){
            return roleIds;
        }
        try {
            for (Role role : roleList) {
                // 标识业务类型，0:普通角色 1： 超级管理员角色
                Integer businessType = role.getBusinessType();
                Integer defaultRole = role.getDefaultRole();
                RoleUserQuery roleUserQuery = new RoleUserQuery();
                roleUserQuery.setRoleId(role.getId());
                String newRoleId = roleService.cloneRole(role.getId());

                //建立域->项目角色关系
                DmRole dmRole = new DmRole();
                Role role1 = new Role();
                role1.setId(newRoleId);
                role1.setName(role.getName());
                dmRole.setRole(role1);
                dmRole.setDomainId(projectId);
                dmRole.setBusinessType(businessType);
                String dmRole1 = dmRoleService.saveDmRole(dmRole);
                if(defaultRole == 1){
                    roleIds.put("common", dmRole1);
                }

                if(businessType == 2){
                    roleIds.put("admin", dmRole1);
                }
            }
        }catch (Exception e){
            throw new ApplicationException(ErrorCode.CREATE_ERROR,"项目角色添加失败" + e.getMessage());
        }

        return roleIds;
    }

    // 设置项目user
    public void setProjectUser(String oldProjectId, Set<MantisUser> users, Map<String, String> roleIds){
        MantisProject mantisProject = this.MantisProjectSet.get().stream().filter(item -> item.getId().equals(oldProjectId)).toList().get(0);
        String projectId = mantisProject.getNewID();

        String admin = roleIds.get("admin");
        String common = roleIds.get("common");
        for (MantisUser mantisUser : users) {
            String userId = getUserId(mantisUser.getId());
            User user = new User();
            user.setId(userId);
            // 查找是否已经添加
            DmUserQuery dmUserQuery = new DmUserQuery();
            dmUserQuery.setDomainId(projectId);
            dmUserQuery.setUserId(userId);
            List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);

            if(dmUserList.isEmpty()){
                // 创建项目人员
                DmUser dmUser = new DmUser();
                dmUser.setUser(user);
                dmUser.setDomainId(projectId);
                try {
                    dmUserService.createDmUserEntity(dmUser);
                }catch (Exception e){
                    throw new ApplicationException(ErrorCode.CREATE_ERROR,"项目成员添加失败" + e.getMessage());
                }
                // 创建角色与项目成员的关联
                try{
                    if(!StringUtils.isEmpty(common)) {
                        DmRole dmRole = new DmRole();
                        dmRole.setId(common);
                        DmRoleUser dmRoleUser = new DmRoleUser();
                        dmRoleUser.setDomainId(projectId);
                        dmRoleUser.setDmRole(dmRole);
                        dmRoleUser.setUserId(userId);
                        dmRoleUserService.createDmRoleUser(dmRoleUser);
                    }
                }catch (Exception e){
                    throw new ApplicationException(ErrorCode.CREATE_ERROR,"项目角色成员添加失败" + e.getMessage());

                }

            }
        }
    }

    /**
     * 新增用户信息
     * @param users
     */
    public void setGlobalUser(Set<MantisUser> users){
        for (MantisUser mantisUser : users) {
            String userName = mantisUser.getUserName();
            String nickName = mantisUser.getUserName();
            UserQuery userQuery = new UserQuery();
            userQuery.setName(userName);
            List<User> nameUserList = userService.findUserList(userQuery);

            try {
                String  userId = new String();
                if (ObjectUtils.isEmpty(nameUserList)){
                    User user = new User();
                    user.setNickname(nickName);
                    user.setName(userName);
                    user.setEmail("");
                    user.setStatus(1);
                    user.setDirId("1");
                    user.setPassword("123456");
                    user.setType(0);
                    userId = userService.createUser(user);
                }else {
                    User user = new User();
                    user.setNickname(nickName);
                    user.setName(userName + "_mantis");// name重复的情况
                    user.setEmail("");
                    user.setStatus(1);
                    user.setDirId("1");
                    user.setPassword("123456");
                    user.setType(0);


                    userId = userService.createUser(user);
                }

                mantisUser.setNewID(userId);
            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR,"成员添加失败" + e.getMessage());
            }
        }
    }

    /**
     * 新增项目、版本信息
     * @param projects
     * @param mantisVersions
     */
    public void setProjectAndVersion(Set<MantisProject> projects, Set<MantisVersion> mantisVersions){
        String createUserId = LoginContext.getLoginId();
        Map<String, List<WorkTypeDm>> workTypeDmMap = new HashMap<>();
        for (MantisProject mantisProject : projects) {
            int index = 0;
            String projectName = mantisProject.getProjectName();
            ProjectQuery projectQuery = new ProjectQuery();
            projectQuery.setProjectName(projectName);
            List<Project> nameProjectList = projectService.findProjectList(projectQuery);


            if (ObjectUtils.isEmpty(nameProjectList)){
                Project project1 = new Project();
                project1.setProjectName(projectName);
                project1.setProjectKey("Mantis" + index);// 默认key为Mantis+偏移量
//                    project1.setDesc(description);
                project1.setProjectLimits("0");
                project1.setProjectState("1");
                ProjectType projectType = new ProjectType();
                projectType.setId("5a46432a");
                projectType.setType("scrum");
                project1.setProjectType(projectType);

                // 默认创建者为当前系统的用户
                User user = new User();
                user.setId(createUserId);
                project1.setMaster(user);
                project1.setCreator(createUserId);
                project1.setIconUrl("project1.png");

                java.sql.Date currentUtilDate = new Date(System.currentTimeMillis());
                // 转换为SQL日期
                java.sql.Date currentSqlDate = new java.sql.Date(currentUtilDate.getTime());
                project1.setStartTime(currentSqlDate);
                project1.setEndTime(currentSqlDate);

                try {
                    String newProjectId = projectService.createImportProject(project1);
                    mantisProject.setNewID(newProjectId);
                    mantisProject.setProjectKey(project1.getProjectKey());
                    for (MantisVersion mantisVersion : mantisVersions) {
                        if (mantisVersion.getProjectId().equals(mantisProject.getId())){
                            mantisVersion.setNewProjectId(newProjectId);
                            setVersion(mantisVersion);
                        }
                    }

                    // 初始化事项类型
                    List<WorkTypeDm> workTypeDms = projectService.initWorkType(newProjectId);
                    workTypeDmMap.put(mantisProject.getId(), workTypeDms);// 这里的id是旧id
                }catch (Exception e){
                    throw new ApplicationException(ErrorCode.CREATE_ERROR,"项目添加失败" + e.getMessage());
                }
            }

        }
        this.ProjectWorkTypeDmMap.set(workTypeDmMap);
    }

    /**
     * 新增版本信息
     * @param mantisVersion
     */
    public void setVersion(MantisVersion mantisVersion){
        String projectId = mantisVersion.getNewProjectId();
        String versionName = mantisVersion.getVersionName();

        ProjectVersion projectVersion = new ProjectVersion();
        Project project = new Project();
        project.setId(projectId);
        projectVersion.setProject(project);

        User user = new User();
        user.setId("111111");
        projectVersion.setBuilder(user);
        projectVersion.setMaster(user);

        projectVersion.setName(versionName);
        int color = new Random().nextInt(3) + 1;
        projectVersion.setColor(color);

        // 默认都为未发布
        VersionState versionState = new VersionState();
        versionState.setId("000000");
        projectVersion.setVersionState(versionState);

        // 默认时间都是导入的时间
        projectVersion.setStartTime(getDateByTimestamp(System.currentTimeMillis()));
        projectVersion.setPublishTime(getDateByTimestamp(System.currentTimeMillis()));

        String version = projectVersionService.createVersion(projectVersion);
        mantisVersion.setNewID(version);

    }

    /**
     * 新增事项信息
     * @param mantisIssueList
     * @param mantisProjects
     * @param mantisVersions
     * @param mantisUsers
     */
    public void setWorkItem(List<MantisIssue> mantisIssueList,
                            Set<MantisProject> mantisProjects,
                            Set<MantisVersion> mantisVersions,
                            Set<MantisUser> mantisUsers,
                            String createUserId,
                            Map<String, Integer> Percent){
        try {
            for (MantisIssue issue : mantisIssueList) {
                // 获取事项类型，mantis导入的都是缺陷
                List<WorkTypeDm> workTypeDmList = ProjectWorkTypeDmMap.get().get(issue.getProjectId());
                WorkTypeDm workTypeDm = workTypeDmList.stream().filter(item -> item.getWorkType().getCode().equals("defect")).toList().get(0);
                // 获取版本、项目信息
                MantisProject mantisProject = mantisProjects.stream().filter(item -> item.getId().equals(issue.getProjectId())).toList().get(0);
                List<MantisVersion> mantisVersionList = mantisVersions.stream().filter(version -> version.getProjectId().equals(mantisProject.getId())).toList();

                WorkItem workItem = new WorkItem();
                workItem.setId(issue.getId());
                workItem.setTitle(issue.getSummary());
                workItem.setCode(mantisProject.getProjectKey() + "-" + issue.getId());// 手动生成
                workItem.setOrderNum(Integer.valueOf(issue.getId()));

                // 项目信息
                Project project = new Project();
                project.setId(mantisProject.getNewID());
                workItem.setProject(project);

                // 事项类型
                WorkType workType = new WorkType();
                workType.setId(workTypeDm.getWorkType().getId());
                workItem.setWorkType(workTypeDm);
                workItem.setWorkTypeCode(workTypeDm.getWorkType().getCode());
                workItem.setWorkTypeSys(workType);

                // 优先级
                String priorityName = issue.getPriority();
                SelectItem selectItem = new SelectItem();
                switch (priorityName){
                    case "无":
                        selectItem.setId("04b440ad");
                        break;
                    case "低":
                        selectItem.setId("04b440ad");
                        break;
                    case "中":
                        selectItem.setId("faaecb3d");
                        break;
                    case "高":
                        selectItem.setId("56035266");
                        break;
                    case "紧急":
                        selectItem.setId("56035266");
                        break;
                    case "非常紧急":
                        selectItem.setId("56035266");
                        break;
                    default:
                        break;
                }
                workItem.setWorkPriority(selectItem);

                // 创建人
                User user = new User();
                String creator = issue.getReporterId();
                String userId = getUserId(creator);
                user.setId(userId);
                workItem.setBuilder(user);
                workItem.setReporter(user);
                // 经办人
                user = new User();
                String handlerId = issue.getHandlerId();
                if (!handlerId.isBlank()){
                    user.setId(getUserId(handlerId));
                    workItem.setAssigner(user);
                }else {
                    user.setId(getUserId(creator));
                    workItem.setAssigner(user);
                }

                // 创建时间更新时间
                String dateSubmitted = issue.getDateSubmitted();
                String lastUpdate = issue.getLastUpdate();
                workItem.setBuildTime(getDateByTimestamp(Long.valueOf(dateSubmitted)*1000));
                workItem.setUpdateTime(getDateByTimestamp(Long.valueOf(lastUpdate)*1000));

                // 设置描述
                String description = issue.getDescription();
                String stepsToReproduce = issue.getStepsToReproduce();
                String additionalInformation = issue.getAdditionalInformation();
//                String desc = "描述：" + description + "\n" + "步骤：" + stepsToReproduce + "\n" + "附加信息：" + additionalInformation;
//                workItem.setDesc("[{\"type\":\"paragraph\",\"children\":[{\"text\":\""+ desc + "\"}]}]");
                StringBuffer descBuffer = new StringBuffer("[");
                if (description != null && !description.isBlank()){
                    descBuffer.append("{\"type\":\"paragraph\",\"children\":[{\"text\":\"描述：\"}]},");
                    descBuffer.append("{\"type\":\"paragraph\",\"children\":[{\"text\":\""+ description + "\"}]}");
                }
                if (stepsToReproduce != null && !stepsToReproduce.isBlank()) {
                    descBuffer.append("{\"type\":\"paragraph\",\"children\":[{\"text\":\"步骤：\"}]},");
                    descBuffer.append("{\"type\":\"paragraph\",\"children\":[{\"text\":\""+ stepsToReproduce + "\"}]}");
                }
                if (additionalInformation != null && !additionalInformation.isBlank()) {
                    descBuffer.append("{\"type\":\"paragraph\",\"children\":[{\"text\":\"附加信息：\"}]}");
                    descBuffer.append("{\"type\":\"paragraph\",\"children\":[{\"text\":\""+ additionalInformation + "\"}]}");
                }
                if (descBuffer.equals("[")){
                    descBuffer.append("{\"type\":\"paragraph\",\"children\":[{\"text\":\"\"}]}");
                }
                descBuffer.append("]");
                workItem.setDesc(descBuffer.toString());



                // 设置状态
                String status = issue.getStatus();
                if (status.equals("新建") || status.equals("已分配")){
                    List<StateNodeFlow> nodeFlowList = workTypeDm.getFlow().getNodeFlowList();
                    for (StateNodeFlow stateNodeFlow : nodeFlowList) {
                        if(stateNodeFlow.getNodeStatus().equals("TODO")){
                            workItem.setWorkStatus(stateNodeFlow);
                            workItem.setWorkStatusNode(stateNodeFlow.getNode());

                            workItem.setWorkStatusCode("TODO");
                        }
                    }
                }else if (status.equals("反馈") || status.equals("认可") || status.equals("已确认")){
                    List<StateNodeFlow> nodeFlowList = workTypeDm.getFlow().getNodeFlowList();
                    for (StateNodeFlow stateNodeFlow : nodeFlowList) {
                        if(stateNodeFlow.getNodeStatus().equals("PROGRESS")){
                            workItem.setWorkStatus(stateNodeFlow);
                            workItem.setWorkStatusNode(stateNodeFlow.getNode());

                            workItem.setWorkStatusCode("PROGRESS");
                        }
                    }
                }else {
                    List<StateNodeFlow> nodeFlowList = workTypeDm.getFlow().getNodeFlowList();
                    for (StateNodeFlow stateNodeFlow : nodeFlowList) {
                        if(stateNodeFlow.getNodeStatus().equals("DONE")){
                            workItem.setWorkStatus(stateNodeFlow);
                            workItem.setWorkStatusNode(stateNodeFlow.getNode());

                            workItem.setWorkStatusCode("DONE");
                        }
                    }
                }

                String workId = workItemService.createImportWorkItem(workItem);
                issue.setNewId(workId);
                workItem.setId(workId);
                workItem.setRootId(workId + ";");

                // 创建事项与版本的对应关系
                setWorkItemVersion(workItem, issue, mantisVersionList);
                // 创建附件
                setAttachment(workItem, issue);
                // 创建评论
                setWorkComment(workItem, issue);
                workItemService.updateWork(workItem);

                Integer integer = Percent.get(createUserId + "currentNum");
                if(!ObjectUtils.isEmpty(integer)){
                    integer++;
                    Percent.put(createUserId + "currentNum", integer);
                }else {
                    Percent.put(createUserId + "currentNum", 1);
                }
            }
        }catch (Exception e){
            log.error("事项添加失败" + e);
            throw new ApplicationException(ErrorCode.CREATE_ERROR,"事项添加失败" + e.getMessage());
        }
    }

    public String getDateByTimestamp(Long timestamp){
        LocalDateTime dateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(timestamp),
                ZoneId.systemDefault()
        );

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateTime.format(formatter);
        return formattedDate;
    }

    public String getUserId(String key){
        String userId = new String();
        for (MantisUser mantisUser : this.MantisUserSet.get()) {
            String userKey = mantisUser.getId();
            String newID = mantisUser.getNewID();
            if(userKey.equals(key)){
                userId = newID;
            }
        }
        return  userId;
    }

    /**
     * 设置事项与版本的对应关系
     * @param workItem
     * @param issue
     * @param mantisVersionList
     */
    public void setWorkItemVersion(WorkItem workItem, MantisIssue issue, List<MantisVersion> mantisVersionList){
        String versionName = issue.getVersion();
        String fixVersionName = issue.getFixVersion();
        String targetVersionName = issue.getTargetVersion();

        if (!versionName.isBlank()){
            MantisVersion version = mantisVersionList.stream().filter(mantisVersion -> mantisVersion.getVersionName().equals(versionName)).toList().get(0);
            ProjectVersion projectVersion = new ProjectVersion();
            projectVersion.setId(version.getNewID());
            workItem.setProjectVersion(projectVersion);
        }else if (!fixVersionName.isBlank()){
            MantisVersion version = mantisVersionList.stream().filter(mantisVersion -> mantisVersion.getVersionName().equals(fixVersionName)).toList().get(0);
            ProjectVersion projectVersion = new ProjectVersion();
            projectVersion.setId(version.getNewID());
            workItem.setProjectVersion(projectVersion);
        } else if (!targetVersionName.isBlank()) {
            MantisVersion version = mantisVersionList.stream().filter(mantisVersion -> mantisVersion.getVersionName().equals(targetVersionName)).toList().get(0);
            ProjectVersion projectVersion = new ProjectVersion();
            projectVersion.setId(version.getNewID());
            workItem.setProjectVersion(projectVersion);
        }else {
            return;
        }

        if (issue.getVersion() != null && !versionName.isBlank()){
            // 添加版本与事项的关联
            MantisVersion version = mantisVersionList.stream().filter(mantisVersion -> mantisVersion.getVersionName().equals(versionName)).toList().get(0);
            WorkVersion workVersion = new WorkVersion();
            workVersion.setVersionId(version.getNewID());
            String id1 = workItem.getId();
            workVersion.setWorkItemId(id1);
            workVersionService.createWorkVersion(workVersion);
        }
        if (issue.getFixVersion() != null && !fixVersionName.isBlank()){
            MantisVersion version = mantisVersionList.stream().filter(mantisVersion -> mantisVersion.getVersionName().equals(fixVersionName)).toList().get(0);
            WorkVersion workVersion = new WorkVersion();
            workVersion.setVersionId(version.getNewID());
            String id1 = workItem.getId();
            workVersion.setWorkItemId(id1);
            workVersionService.createWorkVersion(workVersion);
        }
        if (issue.getTargetVersion() != null && !targetVersionName.isBlank()){
            MantisVersion version = mantisVersionList.stream().filter(mantisVersion -> mantisVersion.getVersionName().equals(targetVersionName)).toList().get(0);
            WorkVersion workVersion = new WorkVersion();
            workVersion.setVersionId(version.getNewID());
            String id1 = workItem.getId();
            workVersion.setWorkItemId(id1);
            workVersionService.createWorkVersion(workVersion);
        }
    }

    /**
     * 设置附件
     * @param workItem
     * @param issue
     */
    public void setAttachment(WorkItem workItem, MantisIssue issue){
        List<MantisAttachment> mantisAttachmentList = issue.getMantisAttachmentList();
        if (mantisAttachmentList == null){
            return;
        }
        for (MantisAttachment mantisAttachment : mantisAttachmentList) {
            String filePath = attachmentPath + "/" + issue.getId() + "/";
            String fileName = mantisAttachment.getFileName();
            String fileType = mantisAttachment.getFileType();
            String objectID;
            try (InputStream inputStream = new FileInputStream(filePath + fileName)){
                PutRequest putRequest = new PutRequest();
                putRequest.setInputStream(inputStream);
                putRequest.setFileName(fileName);
                putRequest.setFileType(fileName.substring(fileName.lastIndexOf('.') + 1));
                objectID = dfsClient.put(putRequest);
            }catch (Exception e){
                throw new ApplicationException(ErrorCode.CREATE_ERROR, "附件添加失败，检查文件是否存在" + e.getMessage());
            }

            WorkAttach workAttach = new WorkAttach();
            workAttach.setWorkItem(workItem);
            workAttach.setAttachmentName(fileName);
            workAttach.setAttachmentUrl(objectID);
            workAttach.setType(fileType);
            workAttachService.createWorkAttach(workAttach);
        }
    }

    /**
     * 设置评论
     * @param workItem
     * @param issue
     */
    public void setWorkComment(WorkItem workItem, MantisIssue issue){
        List<MantisNote> mantisNoteList = issue.getMantisNoteList();
        if (mantisNoteList == null){
            return;
        }
        for (MantisNote mantisNote : mantisNoteList) {
            WorkComment workComment = new WorkComment();
            workComment.setWorkItem(workItem);
            workComment.setDetails(mantisNote.getNote());
            User user = new User();
            user.setId(getUserId(mantisNote.getReporterId()));
            workComment.setUser(user);
//            Instant instant = Instant.ofEpochSecond(timestampSeconds);

            workComment.setCreateTime(new java.util.Date(Long.valueOf(mantisNote.getDateSubmitted()) * 1000));
            workComment.setUpdateTime(new java.util.Date(Long.valueOf(mantisNote.getDateSubmitted()) * 1000));
            workCommentService.createWorkComment(workComment);
        }
    }

//
//    public static void main(String[] args)throws Exception {
//        MantisSaxParseService mantisSaxParseService = new MantisSaxParseService();
//
//        try {
//            String path="/Users/admin/Downloads/exported_issues (8).xml";
//
//            SAXParserFactory factory = SAXParserFactory.newInstance();
//            SAXParser parser = factory.newSAXParser();
//
//            try (FileInputStream fis = new FileInputStream(path);
//                 Reader reader = new InvalidXMLCharFilter(new InputStreamReader(fis, "UTF-8"))) {
//                // Wrap the Reader in InputSource
//                InputSource is = new InputSource(reader);
//                // Parse the XML using SAX parser
//                parser.parse(is, mantisSaxParseService);
//            }
//
//        } catch (Exception e) {
//            throw new ApplicationException(e);
//        }
//        ArrayList<MantisIssue> elementList = mantisSaxParseService.getMantisIssueList();
//
//
//        System.out.println(elementList.size());
//    }
}
