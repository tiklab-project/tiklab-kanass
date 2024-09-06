package io.thoughtware.kanass.project.jira.service;

import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.flow.flow.service.DmFlowService;
import io.thoughtware.flow.statenode.model.StateNodeFlow;
import io.thoughtware.flow.statenode.service.StateNodeFlowService;
import io.thoughtware.form.field.model.SelectItem;
import io.thoughtware.kanass.project.project.model.Project;
import io.thoughtware.kanass.project.project.model.ProjectType;
import io.thoughtware.kanass.project.project.service.ProjectService;
import io.thoughtware.kanass.project.version.model.ProjectVersion;
import io.thoughtware.kanass.project.version.model.VersionState;
import io.thoughtware.kanass.workitem.model.WorkItem;
import io.thoughtware.kanass.workitem.model.WorkType;
import io.thoughtware.kanass.workitem.model.WorkTypeDm;
import io.thoughtware.kanass.workitem.service.WorkItemService;
import io.thoughtware.kanass.workitem.service.WorkTypeDmService;
import io.thoughtware.kanass.workitem.service.WorkTypeService;
import io.thoughtware.privilege.dmRole.model.DmRole;
import io.thoughtware.privilege.dmRole.model.DmRoleUser;
import io.thoughtware.privilege.dmRole.service.DmRoleService;
import io.thoughtware.privilege.dmRole.service.DmRoleUserService;
import io.thoughtware.privilege.role.model.Role;
import io.thoughtware.privilege.role.model.RoleQuery;
import io.thoughtware.privilege.role.model.RoleUserQuery;
import io.thoughtware.privilege.role.service.RoleService;
import io.thoughtware.user.dmUser.model.DmUser;
import io.thoughtware.user.dmUser.model.DmUserQuery;
import io.thoughtware.user.dmUser.service.DmUserService;
import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ObjectUtils;
import org.w3c.dom.Element;

import java.sql.Date;
import java.util.*;

/**
 * jira 数据导入服务
 */
@Service
@EnableTransactionManagement
public class JiraImportData94ServiceImpl implements JiraImportData94Service {

    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    WorkTypeService workTypeService;

    @Autowired
    ProjectService projectService;

    @Autowired
    RoleService roleService;

    @Autowired
    DmUserService dmUserService;

    @Autowired
    DmRoleService dmRoleService;

    @Autowired
    DmRoleUserService dmRoleUserService;

    @Autowired
    WorkTypeDmService workTypeDmService;

    @Autowired
    UserService userService;

    @Autowired
    DmFlowService dmFlowService;

    @Autowired
    StateNodeFlowService stateNodeFlowService;


    @Value("${unzip.path}")
    String unzipAddress;
    private ThreadLocal<ArrayList<Element>> GlobalUserElementList = new ThreadLocal<>();

    private ThreadLocal<ArrayList<Element>> GlobalApplicationUserElementList = new ThreadLocal<>();
    private ThreadLocal<ArrayList<Element>> UserHistoryItemElementList = new ThreadLocal<ArrayList<Element>>();
    private ThreadLocal<ArrayList<String>> ProjectRoleElementList = new ThreadLocal<ArrayList<String>>();
    private ThreadLocal<ArrayList<Element>> ProjectRoleActorElementList = new ThreadLocal<ArrayList<Element>>();
    private ThreadLocal<ArrayList<Element>> IssueElementList = new ThreadLocal<ArrayList<Element>>();

    private ThreadLocal<ArrayList<Element>> IssueParentAssociationElementList = new ThreadLocal<ArrayList<Element>>();
    private ThreadLocal<ArrayList<Element>> SubWorkItemElementList = new ThreadLocal<>();

    private ThreadLocal<ArrayList<Element>> VersionElementList = new ThreadLocal<>();
    private ThreadLocal<ArrayList<Element>> IssueLinkElementList = new ThreadLocal<>();
    private ThreadLocal<ArrayList<String>> TodoStatusIds = new ThreadLocal<ArrayList<String>>();
    private ThreadLocal<ArrayList<String>> ProcessStatusIds = new ThreadLocal<ArrayList<String>>();
    private ThreadLocal<ArrayList<String>> DoneStatusIds = new ThreadLocal<ArrayList<String>>();
    private ThreadLocal<Map<String, Object>> ImportSchedule = new ThreadLocal<Map<String, Object>>();

    public static Map<String, Integer> Percent = new HashMap();
    public static Map<String, Project> CurrentProject = new HashMap();

    @Override
    public String writeData(List<Element> elements) {
        String createUserId = LoginContext.getLoginId();
        try {

            ArrayList<Element> globalUserElementList = new ArrayList<>();
            ArrayList<Element> globalApplicationUserElementList = new ArrayList<>();

            ArrayList<Element> projectElementList = new ArrayList<>();
            ArrayList<Element> versionElementList = new ArrayList<>();
            ArrayList<Element> userHistoryItemElementList = new ArrayList<>();

            ArrayList<String> projectRoleElementList = new ArrayList<>();

            ArrayList<Element> projectRoleActorElementList = new ArrayList<>();

            ArrayList<Element> issueElementList = new ArrayList<>();

            ArrayList<Element> issueParentAssociationElementList = new ArrayList<>();

            ArrayList<Element> subWorkItemElementList= new ArrayList<>();

            ArrayList<Element> issueLinkElementList= new ArrayList<>();

            ArrayList<String> todoStatusIds = new ArrayList<>();

            ArrayList<String> processStatusIds = new ArrayList<>();

            ArrayList<String> doneStatusIds = new ArrayList<>();

            for (Element element : elements) {
                String name = element.getTagName();
                switch (name){
                    case "User":
                        String active = element.getAttribute("active");
                        if(active.equals("1")){
                            globalUserElementList.add(element);
                        }
                        break;
                    case "ApplicationUser":
                        globalApplicationUserElementList.add(element);
                        break;
                    case "Project":
                        String projecttype = element.getAttribute("projecttype");
                        if(projecttype.equals("software")){
                            projectElementList.add(element);
                        }
                        break;
                    case "Issue":
                        String type = element.getAttribute("type");
                        if(!type.equals("10000")){
                            issueElementList.add(element);
                        }else {
                            subWorkItemElementList.add(element);
                        }
                        break;
                    case "IssueLink":
                        issueLinkElementList.add(element);
                        break;

                    case "Version":
                        versionElementList.add(element);
                        break;
                    case "UserHistoryItem":
                        userHistoryItemElementList.add(element);
                        break;
                    case "ProjectRole":
                        String name1 = element.getAttribute("name");
                        if(name1.equals("Administrator")){
                            String id = element.getAttribute("id");
                            projectRoleElementList.add(id);
                        }
                        break;
                    case "ProjectRoleActor":
                        projectRoleActorElementList.add(element);
                        break;
                    case "Status":
                        String statuscategory = element.getAttribute("statuscategory");
                        String statusId = element.getAttribute("id");
                        switch (statuscategory){
                            case "2":
                                todoStatusIds.add(statusId);
                                break;
                            case "3":
                                doneStatusIds.add(statusId);
                                break;
                            case "4":
                                processStatusIds.add(statusId);
                                break;
                            default:
                                break;
                        }
                    default:
                        break;
                }
            }
            this.GlobalUserElementList.set(globalUserElementList);
            this.GlobalApplicationUserElementList.set(globalApplicationUserElementList);
            this.UserHistoryItemElementList.set(userHistoryItemElementList);
            this.ProjectRoleElementList.set(projectRoleElementList);
            this.ProjectRoleActorElementList.set(projectRoleActorElementList);
            this.IssueElementList.set(issueElementList);
            this.SubWorkItemElementList.set(subWorkItemElementList);
            this.IssueLinkElementList.set(issueLinkElementList);
            this.TodoStatusIds.set(todoStatusIds);
            this.ProcessStatusIds.set(processStatusIds);
            this.DoneStatusIds.set(doneStatusIds);
            this.IssueParentAssociationElementList.set(issueParentAssociationElementList);
            this.VersionElementList.set(versionElementList);
            for (Element globalUserElement : this.GlobalUserElementList.get()) {
                setGlobalUser(globalUserElement);
            }
            // 获取导入项目的总数
            int size = projectElementList.size();
            Percent.put(createUserId + "total", size);
            for (Element projectElement : projectElementList) {
                setProject(projectElement, createUserId);
            }
            this.UserHistoryItemElementList.remove();
            this.ProjectRoleElementList.remove();
            this.ProjectRoleActorElementList.remove();
            this.IssueElementList.remove();
            this.SubWorkItemElementList.remove();
            this.TodoStatusIds.remove();
            this.ProcessStatusIds.remove();
            this.DoneStatusIds.remove();
            return "succed";
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }


    public void setGlobalUser(Element element){
        String active = element.getAttribute("active");
        String id = element.getAttribute("id");
        if(active.equals("1")){
            String userName = element.getAttribute("displayName");
            String emailAddress = element.getAttribute("emailAddress");
            User user = new User();
            user.setNickname(userName);
            user.setName(userName);
            user.setEmail(emailAddress);
            user.setStatus(1);
            user.setDirId("1");
            user.setPassword("123456");
            user.setType(0);
            try {
                String userId = userService.createUser(user);
                element.setAttribute("newId", userId);
                ArrayList<Element> globalApplicationUserElementList = this.GlobalApplicationUserElementList.get();
                for (Element applicationElement : globalApplicationUserElementList) {
                    String userKey = applicationElement.getAttribute("userKey");
                    String applicationUserId = applicationElement.getAttribute("id");
                    if(applicationUserId.equals(id)){
                        element.setAttribute("userKey", userKey);
                    }
                }
                System.out.println( element.getAttribute("newId"));
            }catch (Exception e){
                throw new ApplicationException(2000,"成员添加失败" + e.getMessage());
            }
        }
    }

    public String getUserId(String key){
        String userId = new String();
        ArrayList<Element> globalUserElementList = this.GlobalUserElementList.get();
        for (Element globalUserElement : globalUserElementList) {
            String userKey = globalUserElement.getAttribute("userKey");
            String applicationUserId = globalUserElement.getAttribute("newId");
            if(userKey.equals(key)){
                userId = applicationUserId;
            }
        }
        return  userId;
    }


    public void setProject(Element element, String createUserId){
        String projectId = element.getAttribute("id");
        //项目名字
        String projectName = element.getAttribute("name");
        //项目描述
        String description = element.getAttribute("description");
        //项目负责人
        String lead = element.getAttribute("lead");

        String originalkey = element.getAttribute("originalkey");
        String projecttype = element.getAttribute("projecttype");
        Project project = projectService.findProject(projectId);
        if (ObjectUtils.isEmpty(project) && projecttype.equals("software")){

            Project project1 = new Project();
            project1.setProjectName(projectName);
            project1.setProjectKey(originalkey);
            project1.setDesc(description);
            project1.setProjectLimits("0");
            project1.setProjectState("1");
            ProjectType projectType = new ProjectType();
            projectType.setId("5a46432a");
            projectType.setType("scrum");
            project1.setProjectType(projectType);
            User user = new User();

            // 根据lead 获取用户
            String userId = getUserId(lead);
            user.setId(userId);
            project1.setMaster(user);
            project1.setCreator(userId);
            project1.setIconUrl("project1.png");

            Date currentUtilDate = new Date(System.currentTimeMillis());

            // 转换为SQL日期
            java.sql.Date currentSqlDate = new java.sql.Date(currentUtilDate.getTime());

            project1.setStartTime(currentSqlDate);
            project1.setEndTime(currentSqlDate);

            try {
                String jiraProjectId = projectService.createJiraProject(project1);
                element.setAttribute("newId", jiraProjectId);

                Map<String, String> roleIds = setProjectRole(jiraProjectId);
                setProjectUser(element, roleIds);
                setVersion(element);
                // 初始化事项类型
                List<WorkTypeDm> workTypeDmList = projectService.initWorkType(jiraProjectId);

                setFirstLevelWorkItem(element, workTypeDmList, "firstLevel");
                setChildrenWorkItem(element, workTypeDmList, "children");
                Integer integer = Percent.get(createUserId + "currentNum");
                if(!ObjectUtils.isEmpty(integer)){
                    integer++;
                    Percent.put(createUserId + "currentNum", integer);
                }else {
                    Percent.put(createUserId + "currentNum", 1);
                }
                CurrentProject.put(createUserId + "project",project1);
            }catch (Exception e) {
                throw new ApplicationException(2000, "项目添加失败" + e.getMessage());
            }
        }
    }

    public void setVersion(Element element){
        String pid = element.getAttribute("id");
        String newId = element.getAttribute("newId");
        for (Element versionElement : this.VersionElementList.get()) {
            String project1 = versionElement.getAttribute("project");
            if(pid.equals(project1)){
                String name = element.getAttribute("name");
                String startdate = element.getAttribute("startdate");
                String releasedate = element.getAttribute("releasedate");
                String released = element.getAttribute("released");

                ProjectVersion projectVersion = new ProjectVersion();
                Project project = new Project();
                project.setId(newId);
                projectVersion.setProject(project);

                User user = new User();
                user.setId("111111");
                projectVersion.setBuilder(user);
                projectVersion.setMaster(user);

                projectVersion.setName(name);
                projectVersion.setStartTime(startdate);
                projectVersion.setPublishTime(releasedate);

                int color = new Random().nextInt(3) + 1;
                System.out.println(color);
                projectVersion.setColor(color);

                if(released.equals("true")){
                    VersionState versionState = new VersionState();
                    versionState.setId("222222");
                    projectVersion.setVersionState(versionState);
                    projectVersion.setRelaStartTime(startdate);
                    projectVersion.setRelaPublishTime(releasedate);
                }else {
                    VersionState versionState = new VersionState();
                    versionState.setId("000000");
                    projectVersion.setVersionState(versionState);
                }
            }

        }


    }
    public void setWorkItem(Element projectElement, List<WorkTypeDm> workTypeDmList,String action){
        WorkTypeDm taskWorkTypeDm = new WorkTypeDm();
        WorkTypeDm demandWorkTypeDm = new WorkTypeDm();
        WorkTypeDm defectWorkTypeDm = new WorkTypeDm();
        for (WorkTypeDm workTypeDm : workTypeDmList) {
            String code = workTypeDm.getWorkType().getCode();

            switch (code){
                case "defect":
                    defectWorkTypeDm = workTypeDm;
                    break;
                case "demand":
                    demandWorkTypeDm = workTypeDm;
                    break;
                case "task":
                    taskWorkTypeDm = workTypeDm;
                    break;
                default:
                    break;
            }
        }

        try {
            ArrayList<Element> issueElementList = new ArrayList<>();
            if(action == "firstLevel"){
                issueElementList =  this.IssueElementList.get();
            }
            if(action == "children"){
                issueElementList =  this.SubWorkItemElementList.get();
            }
            for (Element element : issueElementList) {
                WorkTypeDm workTypeDm = new WorkTypeDm();
                String type = element.getAttribute("type");
                switch (type){
                    case "10002":
                        workTypeDm = demandWorkTypeDm;
                        break;
                    case "10001":
                        workTypeDm = demandWorkTypeDm;
                        break;
                    case "10003":
                        workTypeDm = taskWorkTypeDm;
                        break;
                    case "10000":
                        workTypeDm = taskWorkTypeDm;
                        break;
                    case "10004":
                        workTypeDm = defectWorkTypeDm;
                        break;
                }
                WorkItem workItem = new WorkItem();
                String id = element.getAttribute("id");
                String currentProject = element.getAttribute("project");
                String code = element.getAttribute("key");
                String number = element.getAttribute("number");
                String pid = projectElement.getAttribute("id");
                String projectId = projectElement.getAttribute("newId");
                if(currentProject.equals(pid)){

                    workItem.setCode(code);
                    workItem.setOrderNum(Integer.valueOf(number));
                    String summary = element.getAttribute("summary");
                    workItem.setTitle(summary);

                    Project project = new Project();
                    project.setId(projectId);
                    workItem.setProject(project);

                    WorkType workType = new WorkType();
                    workType.setId(workTypeDm.getWorkType().getId());
                    workItem.setWorkType(workTypeDm);
                    workItem.setWorkTypeCode(workTypeDm.getWorkType().getCode());
                    workItem.setWorkTypeSys(workType);


                    String priorityId = element.getAttribute("priority");
                    SelectItem selectItem = new SelectItem();
                    switch (priorityId){
                        case "1":
                            selectItem.setId("56035266");
                            break;
                        case "2":
                            selectItem.setId("56035266");
                            break;
                        case "3":
                            selectItem.setId("faaecb3d");
                            break;
                        case "4":
                            selectItem.setId("04b440ad");
                            break;
                        case "5":
                            selectItem.setId("04b440ad");
                            break;
                        default:
                            break;
                    }

                    workItem.setWorkPriority(selectItem);



                    User user = new User();
                    String creator = element.getAttribute("creator");
                    String userId = getUserId(creator);
                    user.setId(userId);
                    workItem.setBuilder(user);

                    String created = element.getAttribute("created");
                    workItem.setBuildTime(created);

                    String assignee = element.getAttribute("assignee");
                    if(!ObjectUtils.isEmpty(assignee)){
                        user.setId(getUserId(assignee));
                        workItem.setAssigner(user);
                    }else {
                        user.setId(getUserId(created));
                        workItem.setAssigner(user);
                    }

                    String reporter = element.getAttribute("reporter");
                    user.setId(getUserId(reporter));
                    workItem.setReporter(user);



                    String updated = element.getAttribute("updated");
                    workItem.setUpdateTime(updated);

                    workItem.setDesc("[{\"type\":\"paragraph\",\"children\":[{\"text\":\"\"}]}]");
                    // 设置状态
                    String status = element.getAttribute("status");
                   if(this.ProcessStatusIds.get().contains(status)){
                        List<StateNodeFlow> nodeFlowList = workTypeDm.getFlow().getNodeFlowList();
                        for (StateNodeFlow stateNodeFlow : nodeFlowList) {
                            if(stateNodeFlow.getNodeStatus().equals("PROGRESS")){
                                workItem.setWorkStatus(stateNodeFlow);
                                workItem.setWorkStatusNode(stateNodeFlow.getNode());

                                workItem.setWorkStatusCode("PROGRESS");
                            }
                        }
                   }else if(this.DoneStatusIds.get().contains(status)){
                        List<StateNodeFlow> nodeFlowList = workTypeDm.getFlow().getNodeFlowList();
                        for (StateNodeFlow stateNodeFlow : nodeFlowList) {
                            if(stateNodeFlow.getNodeStatus().equals("DONE")){
                                workItem.setWorkStatus(stateNodeFlow);
                                workItem.setWorkStatusNode(stateNodeFlow.getNode());

                                workItem.setWorkStatusCode("DONE");
                            }
                        }
                   }else {
                        List<StateNodeFlow> nodeFlowList = workTypeDm.getFlow().getNodeFlowList();
                        for (StateNodeFlow stateNodeFlow : nodeFlowList) {
                            if(stateNodeFlow.getNodeStatus().equals("TODO")){
                                workItem.setWorkStatus(stateNodeFlow);
                                workItem.setWorkStatusNode(stateNodeFlow.getNode());

                                workItem.setWorkStatusCode("TODO");
                            }
                        }
                   }
                   String workId = workItemService.createJiraWorkItem(workItem);
                   element.setAttribute("newId", workId);
                   workItem.setId(workId);
                   workItem.setRootId(workId + ";");
                   workItemService.updateWork(workItem);

                }
            }
        }catch (Exception e){
            throw new ApplicationException(2000, "添加事项失败" + e.getMessage());
        }
    }
    public void setFirstLevelWorkItem(Element projectElement, List<WorkTypeDm> workTypeDmList,String action){
        setWorkItem(projectElement, workTypeDmList, action);
    }
    public void setChildrenWorkItem(Element projectElement, List<WorkTypeDm> workTypeDmList,String action){
        setWorkItem(projectElement, workTypeDmList, action);
        ArrayList<Element> subWorkItemElementList = this.SubWorkItemElementList.get();
        // 设置treePath, parentId
        for (Element element : subWorkItemElementList) {
            String newId = element.getAttribute("newId");
            String id = element.getAttribute("id");
            WorkItem workItem = new WorkItem();
            workItem.setId(newId);
            for (Element issueLinkElement : this.IssueLinkElementList.get()) {
                String destination = issueLinkElement.getAttribute("destination");
                String parentId = issueLinkElement.getAttribute("source");
                if(destination.equals(id)){
                    for (Element parentIssue : this.IssueElementList.get()) {
                        String id1 = parentIssue.getAttribute("id");
                        if(id1.equals(parentId)){
                            String newParentId = parentIssue.getAttribute("newId");
                            WorkItem workItem1 = new WorkItem();
                            workItem1.setId(newParentId);
                            workItem.setParentWorkItem(workItem1);
                            workItem.setTreePath(newParentId + ";");
                            workItemService.updateWork(workItem);
                        }
                    }

                }
            }

        }

    }
    public void setProjectUser(Element element, Map<String, String> roleIds){
        String projectId = element.getAttribute("newId");
        String pid1 = element.getAttribute("id");
        String admin = roleIds.get("admin");
        String common = roleIds.get("common");
        for (Element projectUser : this.ProjectRoleActorElementList.get()) {
            String userPid = projectUser.getAttribute("pid");
            if(!ObjectUtils.isEmpty(userPid)){
                String roletypeparameter = projectUser.getAttribute("roletypeparameter");
                String projectroleId = projectUser.getAttribute("projectroleid");
                if(userPid.equals(pid1) && roletypeparameter.contains("JIRAUSER")){
                    for (Element userElement : this.GlobalUserElementList.get()) {
                        String userKey = userElement.getAttribute("userKey");
                        if(userKey.equals(roletypeparameter)){
                            String userId = userElement.getAttribute("newId");
                            User user = new User();
                            user.setId(userId);
                            // 查找人员是否在已经在当前项目中
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
                                    throw new ApplicationException(2000,"项目成员添加失败" + e.getMessage());
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
                                    throw new ApplicationException(2000,"项目角色成员添加失败" + e.getMessage());

                                }

                            }
                        }
                    }


                }
            }
        }
        String loginId = LoginContext.getLoginId();
        //设置导入者为项目成员
        User user = new User();
        user.setId("111111");
        DmUser dmUser = new DmUser();
        dmUser.setUser(user);
        dmUser.setDomainId(projectId);
        dmUserService.createDmUserEntity(dmUser);

        DmRole dmRole = new DmRole();
        DmRoleUser dmRoleUser = new DmRoleUser();
        dmRoleUser.setUserId(loginId);
        dmRole.setId(admin);
        dmRoleUser.setDmRole(dmRole);
        dmRoleUser.setDomainId(projectId);
        dmRoleUserService.createDmRoleUser(dmRoleUser);
    }

    public Map<String, String> setProjectRole(String projectId){
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

                if(businessType == 1){
                    roleIds.put("admin", dmRole1);
                }
            }
        }catch (Exception e){
            throw new ApplicationException(2000,"项目角色添加失败" + e.getMessage());
        }

        return roleIds;
    }

    public String findWorkItemParent(String workItemId, WorkItem workItem, String projectKey){
        String treePath = new String();
        for (Element element1 : this.IssueParentAssociationElementList.get()) {
            String issueId = element1.getAttribute("issueId");
            if(issueId.equals(workItemId)){
                String parentId = element1.getAttribute("parentId");
                String parentWorkId = "jr" + projectKey+ "-" + parentId ;
                treePath = treePath  + parentWorkId + ";";
                workItem.setRootId(parentWorkId);
                if(this.SubWorkItemElementList.get().contains(parentId)){
                    findWorkItemParent(parentId, workItem, projectKey);

                }else {
                    return treePath;
                }
            }
        }
        return  treePath;
    }


    public JdbcTemplate getJdbcTemplet(){
      return  jpaTemplate.getJdbcTemplate();
    }

}
