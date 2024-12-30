package io.tiklab.kanass.project.jira.service;

import io.tiklab.core.exception.ApplicationException;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.flow.flow.service.DmFlowService;
import io.tiklab.flow.statenode.model.StateNodeFlow;
import io.tiklab.flow.statenode.service.StateNodeFlowService;
import io.tiklab.form.field.model.SelectItem;
import io.tiklab.kanass.project.module.model.Module;
import io.tiklab.kanass.project.module.service.ModuleService;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.model.ProjectType;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.kanass.project.version.model.ProjectVersion;
import io.tiklab.kanass.project.version.model.VersionState;
import io.tiklab.kanass.project.version.service.ProjectVersionService;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.kanass.sprint.model.SprintState;
import io.tiklab.kanass.sprint.service.SprintService;
import io.tiklab.kanass.workitem.model.*;
import io.tiklab.kanass.workitem.service.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ObjectUtils;
import org.w3c.dom.Element;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * cloud 版本jira 数据导入
 */
@Service
@EnableTransactionManagement
public class JiraImportDataCloudServiceImpl implements JiraImportDataCloudService {

    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    WorkSprintService workSprintService;

    @Autowired
    WorkTypeService workTypeService;

    @Autowired
    SprintService sprintService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ModuleService moduleService;

    @Autowired
    WorkVersionService workVersionService;

    @Autowired
    ProjectVersionService projectVersionService;

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

    private ThreadLocal<ArrayList<Element>> IssueTypeElementList = new ThreadLocal<ArrayList<Element>>();
    private ThreadLocal<ArrayList<Element>> IssueParentAssociationElementList = new ThreadLocal<ArrayList<Element>>();
    private ThreadLocal<ArrayList<Element>> SubWorkItemElementList = new ThreadLocal<>();

    private ThreadLocal<ArrayList<Element>> VersionElementList = new ThreadLocal<>();

    private ThreadLocal<ArrayList<Element>> ComponentElementList = new ThreadLocal<>();
    private ThreadLocal<ArrayList<Element>> ChangeItemElementList = new ThreadLocal<>();
    private ThreadLocal<ArrayList<Element>> SprintElementList = new ThreadLocal<>();
    private ThreadLocal<ArrayList<Element>> IssueLinkElementList = new ThreadLocal<>();
    private ThreadLocal<ArrayList<String>> TodoStatusIds = new ThreadLocal<ArrayList<String>>();
    private ThreadLocal<ArrayList<String>> ProcessStatusIds = new ThreadLocal<ArrayList<String>>();
    private ThreadLocal<ArrayList<String>> DoneStatusIds = new ThreadLocal<ArrayList<String>>();

    private ThreadLocal<ArrayList<Element>> CustomFieldValueElementList = new ThreadLocal<>();
    private ThreadLocal<ArrayList<Element>> CustomFieldElementList = new ThreadLocal<>();
    private ThreadLocal<ArrayList<Element>>  ChangeGroupElementList = new  ThreadLocal<>();

    private ThreadLocal<Map<String, Object>> ImportSchedule = new ThreadLocal<Map<String, Object>>();

    public static Map<String, Integer> Percent = new HashMap();

    @Override
    public String writeData(List<Element> elements, Map<String, Project> CurrentProject, Map<String, Integer> Percent) {
        String createUserId = LoginContext.getLoginId();
        try {

            ArrayList<Element> globalUserElementList = new ArrayList<>();

            ArrayList<Element> globalApplicationUserElementList = new ArrayList<>();

            ArrayList<Element> projectElementList = new ArrayList<>();

            ArrayList<Element> versionElementList = new ArrayList<>();
            ArrayList<Element> changeItemElementList = new ArrayList<>();
            ArrayList<Element> changeGroupElementList = new ArrayList<>();
            ArrayList<Element> componentElementList = new ArrayList<>();

            ArrayList<Element> sprintElementList = new ArrayList<>();
            ArrayList<Element> issueTypeElementList = new ArrayList<>();
            ArrayList<Element> customFieldValueElementList = new ArrayList<>();
            ArrayList<Element> customFieldElementList = new ArrayList<>();
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

            // 根据标签，归类各个模型
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
                    case "IssueType":
                        issueTypeElementList.add(element);
                        break;
                    case "IssueLink":
                        issueLinkElementList.add(element);
                        break;

                    case "Version":
                        versionElementList.add(element);
                        break;
                    case "Sprint":
                        sprintElementList.add(element);
                        break;
                    case "Component":
                        componentElementList.add(element);
                        break;
                    case "CustomFieldValue":
                        customFieldValueElementList.add(element);
                        break;
                    case "CustomField":
                        customFieldElementList.add(element);
                        break;
                    case "ChangeItem":
                        changeItemElementList.add(element);
                        break;
                    case "ChangeGroup":
                        changeGroupElementList.add(element);
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
                        break;
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
            this.IssueTypeElementList.set(issueTypeElementList);
            this.SubWorkItemElementList.set(subWorkItemElementList);
            this.IssueLinkElementList.set(issueLinkElementList);
            this.TodoStatusIds.set(todoStatusIds);
            this.ProcessStatusIds.set(processStatusIds);
            this.DoneStatusIds.set(doneStatusIds);
            this.IssueParentAssociationElementList.set(issueParentAssociationElementList);
            this.VersionElementList.set(versionElementList);
            this.SprintElementList.set(sprintElementList);
            this.CustomFieldElementList.set(customFieldElementList);
            this.ChangeItemElementList.set(changeItemElementList);
            this.ComponentElementList.set(componentElementList);
            this.ChangeGroupElementList.set(changeGroupElementList);

            ArrayList<Element> sprintCustomField = getSprintCustomField(customFieldValueElementList);
            this.CustomFieldValueElementList.set(sprintCustomField);

            for (Element globalUserElement : this.GlobalUserElementList.get()) {
                setGlobalUser(globalUserElement);
            }
            // 获取导入项目的总数
            int size = projectElementList.size();
            Percent.put(createUserId + "total", size);

            setGroupItemIssue();
            for (Element projectElement : projectElementList) {
                String simplified = projectElement.getAttribute("simplified");
                if(simplified.equals("true")){
                    setProject(projectElement, createUserId, CurrentProject, Percent);
                }
            }
            this.UserHistoryItemElementList.remove();
            this.ProjectRoleElementList.remove();
            this.ProjectRoleActorElementList.remove();
            this.IssueElementList.remove();
            this.IssueTypeElementList.remove();
            this.SubWorkItemElementList.remove();
            this.TodoStatusIds.remove();
            this.ProcessStatusIds.remove();
            this.DoneStatusIds.remove();
            this.ChangeItemElementList.remove();
            this.CustomFieldValueElementList.remove();
            this.ChangeGroupElementList.remove();
            this.ComponentElementList.remove();
            this.SprintElementList.remove();
            this.CustomFieldElementList.remove();
            return "succed";
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    /**
     * 导入迭代
     * @param customFieldValueElementList
     * @return
     */
    public ArrayList<Element> getSprintCustomField(ArrayList<Element> customFieldValueElementList){
        ArrayList<Element> fieldValueElementList = new ArrayList<>();
        ArrayList<Element> elementList = this.CustomFieldElementList.get();
        for (Element element : elementList) {
            String customFieldname = element.getAttribute("name");
            if(customFieldname.equals("Sprint")){
                String id = element.getAttribute("id");
                for (Element customFieldValue : customFieldValueElementList) {
                    String customfield = customFieldValue.getAttribute("customfield");
                    if(customfield.equals(id)){
                        fieldValueElementList.add(customFieldValue);
                    }
                }

            }
        }
        return fieldValueElementList;
    }


    public void setGroupItemIssue(){
        for (Element changeItemElement : this.ChangeItemElementList.get()) {
            String group = changeItemElement.getAttribute("group");
            for (Element changeGroupElement : this.ChangeGroupElementList.get()) {
                String id = changeGroupElement.getAttribute("id");
                String issue = changeGroupElement.getAttribute("issue");
                if(group.equals(id)){
                    changeItemElement.setAttribute("issue", issue);
                }
            }
        }

    }

    public void setGlobalUser(Element element){
        String active = element.getAttribute("active");
        String userName = element.getAttribute("userName");
        boolean status = userName.contains("ug:");
        if(status && active.equals("1")){
            String displayName = element.getAttribute("displayName");
            String emailAddress = element.getAttribute("emailAddress");
            UserQuery userQuery = new UserQuery();
            userQuery.setEmail(emailAddress);

            List<User> userList = userService.findUserList(userQuery);
            if(ObjectUtils.isEmpty(userList)){
                User user = new User();
                user.setNickname(displayName);
                user.setName(displayName);
                user.setEmail(emailAddress);
                user.setStatus(1);
                user.setDirId("1");
                user.setPassword("123456");
                user.setType(0);

                try {
                    String userId = userService.createUser(user);
                    element.setAttribute("newId", userId);
                    System.out.println( element.getAttribute("newId"));
                }catch (Exception e){
                    throw new ApplicationException(2000,"成员添加失败" + e.getMessage());
                }
            }else {
                User user = userList.get(0);
                element.setAttribute("newId", user.getId());
            }


        }
    }

    public String getUserId(String key){
        String userId = new String();
        ArrayList<Element> globalUserElementList = this.GlobalUserElementList.get();
        for (Element globalUserElement : globalUserElementList) {
            String userKey = globalUserElement.getAttribute("userName");
            String applicationUserId = globalUserElement.getAttribute("newId");
            if(userKey.equals(key)){
                userId = applicationUserId;
            }
        }
        return  userId;
    }


    public void setProject(Element element, String createUserId, Map<String, Project> CurrentProject, Map<String, Integer> Percent){
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
        String simplified = element.getAttribute("simplified");

        if (ObjectUtils.isEmpty(project) && simplified.equals("true")){
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
//                setVersion(element);
                setSprint(element);
//                setModule(element);
//
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
                Percent.put(createUserId + "status", 2);
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
                String name = versionElement.getAttribute("name");
                String startdate = versionElement.getAttribute("startdate");
                String releasedate = versionElement.getAttribute("releasedate");
                String released = versionElement.getAttribute("released");

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
                String version = projectVersionService.createVersion(projectVersion);
                versionElement.setAttribute("newId", version);
            }
        }
    }

    public void setModule(Element element){
        String pid = element.getAttribute("id");
        String newId = element.getAttribute("newId");
        for (Element moduleElement : this.ComponentElementList.get()) {
            String project1 = moduleElement.getAttribute("project");
            if(pid.equals(project1)){
                String name = moduleElement.getAttribute("name");
                String description = moduleElement.getAttribute("description");

                Module module = new Module();
                Project project = new Project();
                project.setId(newId);
                module.setProject(project);
                module.setModuleName(name);
                module.setDesc(description);

                String moduleId = moduleService.createModule(module);
                moduleElement.setAttribute("newId", moduleId);
            }
        }
    }
    public void setSprint(Element element){
        String pid = element.getAttribute("id");
        String newId = element.getAttribute("newId");
        List<String> sprintIdList = new ArrayList<String>();
        for (Element changeItem : this.ChangeItemElementList.get()) {
            String field = changeItem.getAttribute("field");
            String issue = changeItem.getAttribute("issue");
            String newvalue = changeItem.getAttribute("newvalue");
            if(field.equals("Sprint")){
                for (Element issueElement: this.IssueElementList.get()) {
                    String project = issueElement.getAttribute("project");
                    if(pid.equals(project)){
                        String oldWorkId = issueElement.getAttribute("id");
                        if(oldWorkId.equals(issue)){
                            issueElement.setAttribute("sprint", newvalue);
                            if(!sprintIdList.contains(newvalue)){
                                sprintIdList.add(newvalue);
                            }
                        }
                    }
                }
            }
        }

        for(Element sprintElement : this.SprintElementList.get()){
            String sprintId = sprintElement.getAttribute("id");
            if(sprintIdList.contains(sprintId)){
                // 创建迭代
                Sprint sprint = new Sprint();

                String name = sprintElement.getAttribute("name");
                String activatedDate = sprintElement.getAttribute("activated_date");
                activatedDate = toDateString(activatedDate);
                if(activatedDate != null && !activatedDate.isEmpty()){

                    sprint.setRelaStartTime(activatedDate);
                }

                String completeDate = sprintElement.getAttribute("complete_date");
                completeDate = toDateString(completeDate);
                if(completeDate != null && !completeDate.isEmpty()){
                    sprint.setRelaEndTime(completeDate);
                }

                String endDate = sprintElement.getAttribute("end_date");
                endDate = toDateString(endDate);
                if(endDate != null && !endDate.isEmpty()){
                    sprint.setEndTime(endDate);
                }

                String startDate = sprintElement.getAttribute("start_date");
                startDate = toDateString(startDate);
                if(startDate != null && !startDate.isEmpty()){
                    sprint.setStartTime(startDate);
                }

                sprint.setSprintName(name);

                User user = new User();
                user.setId("111111");
                sprint.setBuilder(user);
                sprint.setMaster(user);

                Project project = new Project();
                project.setId(newId);
                sprint.setProject(project);


                String started = sprintElement.getAttribute("started");

                String closed = sprintElement.getAttribute("closed");
                SprintState sprintState = new SprintState();
                if(started.equals("true") && closed.equals("true")){
                    sprintState.setId("222222");
                }else if(started.equals("true") && closed.equals("false")){
                    sprintState.setId("111111");
                }else if(started.equals("false")){
                    sprintState.setId("000000");
                }
                sprint.setSprintState(sprintState);

                int color = new Random().nextInt(3) + 1;
                System.out.println(color);
                sprint.setColor(color);
                String jiraSprintId = sprintService.createJiraSprint(sprint);
                sprintElement.setAttribute("newId", jiraSprintId);
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
                for (Element issueTypeElement : this.IssueTypeElementList.get()) {
                    String id = issueTypeElement.getAttribute("id");
                    if(id.equals(type)){
                        String typeName = issueTypeElement.getAttribute("name");
                        switch (typeName){
                            case "长篇故事":
                                workTypeDm = demandWorkTypeDm;
                                break;
                            case "需求":
                                workTypeDm = demandWorkTypeDm;
                                break;
                            case "Epic":
                                workTypeDm = demandWorkTypeDm;
                                break;
                            case "故事":
                                workTypeDm = demandWorkTypeDm;
                                break;
                            case "子任务":
                                workTypeDm = taskWorkTypeDm;
                                break;
                            case "任务":
                                workTypeDm = taskWorkTypeDm;
                                break;
                            case "故障":
                                workTypeDm = defectWorkTypeDm;
                                break;
                            case "缺陷":
                                workTypeDm = defectWorkTypeDm;
                                break;
                            default:
                                workTypeDm = demandWorkTypeDm;
                                break;
                        }
                    }
                }


                WorkItem workItem = new WorkItem();
                String id = element.getAttribute("id");
                String currentProject = element.getAttribute("project");
                String code = element.getAttribute("projectKey");
                String number = element.getAttribute("number");

                String pid = projectElement.getAttribute("id");
                String projectId = projectElement.getAttribute("newId");
                if(currentProject.equals(pid)){

                    workItem.setCode(code + "-" + number);
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
                            if(stateNodeFlow.getNodeStatus().equals("PROGRESS") && stateNodeFlow.getNode().getName().equals("进行中")){
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
                    // 设置版本
//                    setWorkVersion(workItem, element);
                    // 设置迭代
                    setWorkSprint(workItem, element);
                    // 设置模块
//                    setWorkModule(workItem, element);
                    workItemService.updateWork(workItem);
                }
            }
        }catch (Exception e){
            throw new ApplicationException(2000, "添加事项失败" + e.getMessage());
        }
    }

    public void setWorkVersion(WorkItem workItem, Element element){
        String id = element.getAttribute("id");
        for (Element changeItem : this.ChangeItemElementList.get()) {
            String field = changeItem.getAttribute("field");
            String issue = changeItem.getAttribute("issue");
            String newvalue = changeItem.getAttribute("newvalue");
            if(issue.equals(id) && field.equals("Fix Version")){

                for (Element element1 : this.VersionElementList.get()) {
                    String newId = element1.getAttribute("newId");
                    String versionId = element1.getAttribute("id");
                    if(versionId.equals(newvalue)){
                        ProjectVersion projectVersion = new ProjectVersion();
                        projectVersion.setId(newId);
                        workItem.setProjectVersion(projectVersion);

                        // 添加版本与事项的关联
                        WorkVersion workVersion = new WorkVersion();
                        workVersion.setVersionId(newId);
                        String id1 = workItem.getId();
                        workVersion.setWorkItemId(id1);
                        workVersionService.createWorkVersion(workVersion);
                    }
                }
            }
        }

    }

    public void setWorkModule(WorkItem workItem, Element element){
        String id = element.getAttribute("id");
        for (Element changeItem : this.ChangeItemElementList.get()) {
            String field = changeItem.getAttribute("field");
            String issue = changeItem.getAttribute("issue");
            String newvalue = changeItem.getAttribute("newvalue");
            if(issue.equals(id) && field.equals("Component")){

                for (Element element1 : this.ComponentElementList.get()) {
                    String newId = element1.getAttribute("newId");
                    String moduleId = element1.getAttribute("id");
                    if(moduleId.equals(newvalue)){
                        Module module = new Module();
                        module.setId(newId);
                        workItem.setModule(module);
                    }

                }
            }
        }

    }

    public void setWorkSprint(WorkItem workItem, Element element){
        String sprintId = element.getAttribute("sprint");
        String id = workItem.getId();
        if(sprintId != null && !sprintId.isEmpty()){
            for (Element sprintElement : this.SprintElementList.get()) {
                String sprintOldId = sprintElement.getAttribute("id");
                String sprintNewId = sprintElement.getAttribute("newId");
                if(sprintOldId.equals(sprintId)){
                    Sprint sprint = new Sprint();
                    sprint.setId(sprintNewId);
                    workItem.setSprint(sprint);

                    WorkSprint workSprint = new WorkSprint();
                    workSprint.setWorkItemId(id);
                    workSprint.setSprintId(sprintNewId);
                    workSprintService.createWorkSprint(workSprint);
                }
            }
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
        String lead = element.getAttribute("lead");
        boolean haveLead = false;
        String admin = roleIds.get("admin");
        String common = roleIds.get("common");
        for (Element projectUser : this.ProjectRoleActorElementList.get()) {
            String userPid = projectUser.getAttribute("pid");
            if(!ObjectUtils.isEmpty(userPid)){
                String roletypeparameter = projectUser.getAttribute("roletypeparameter");
                String projectroleId = projectUser.getAttribute("projectroleid");
                if(userPid.equals(pid1) && roletypeparameter.contains("ug:")){
                    for (Element userElement : this.GlobalUserElementList.get()) {
                        String userKey = userElement.getAttribute("userName");

                        if(userKey.equals(roletypeparameter)){
                            if(userKey.equals(lead)){
                                haveLead = true;
                            }
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
        // 如果项目角色中没有项目管理员，加上管理员
        if(!haveLead){
            for (Element userElement : this.GlobalUserElementList.get()) {
                String userKey = userElement.getAttribute("userKey");
                if(userKey.equals(lead)){
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
                            if(!StringUtils.isEmpty(admin)) {
                                DmRole dmRole = new DmRole();
                                dmRole.setId(admin);
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

    /**
     * 转换为时间戳转换为日期
     * @param timetamp
     * @return
     */
    public String toDateString(String timetamp){
        // 示例时间戳字符串
        String formattedDate = new String();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long timestamp = Long.parseLong(timetamp);
            Date date = new Date(timestamp); // 时间戳通常是以秒为单位，所以需要乘以1000转成毫秒
            formattedDate = sdf.format(date);
            System.out.println("Formatted Date: " + formattedDate);
        } catch (NumberFormatException e) {
            System.out.println("Invalid timestamp");
        }
        return formattedDate;
    }
    public JdbcTemplate getJdbcTemplet(){
        return  jpaTemplate.getJdbcTemplate();
    }

}
