//package io.thoughtware.kanass.project.jira.service;
//
//import io.thoughtware.core.exception.ApplicationException;
//import io.thoughtware.dal.jpa.JpaTemplate;
//import io.thoughtware.eam.common.context.LoginContext;
//import io.thoughtware.flow.flow.service.DmFlowService;
//import io.thoughtware.flow.statenode.model.StateNodeFlow;
//import io.thoughtware.flow.statenode.service.StateNodeFlowService;
//import io.thoughtware.form.field.model.SelectItem;
//import io.thoughtware.kanass.project.project.model.Project;
//import io.thoughtware.kanass.project.project.model.ProjectType;
//import io.thoughtware.kanass.project.project.service.ProjectService;
//import io.thoughtware.kanass.workitem.model.WorkItem;
//import io.thoughtware.kanass.workitem.model.WorkType;
//import io.thoughtware.kanass.workitem.model.WorkTypeDm;
//import io.thoughtware.kanass.workitem.service.WorkItemService;
//import io.thoughtware.kanass.workitem.service.WorkTypeDmService;
//import io.thoughtware.kanass.workitem.service.WorkTypeService;
//import io.thoughtware.privilege.dmRole.model.DmRole;
//import io.thoughtware.privilege.dmRole.model.DmRoleUser;
//import io.thoughtware.privilege.dmRole.service.DmRoleService;
//import io.thoughtware.privilege.dmRole.service.DmRoleUserService;
//import io.thoughtware.privilege.role.model.Role;
//import io.thoughtware.privilege.role.model.RoleQuery;
//import io.thoughtware.privilege.role.model.RoleUserQuery;
//import io.thoughtware.privilege.role.service.RoleService;
//import io.thoughtware.user.dmUser.model.DmUser;
//import io.thoughtware.user.dmUser.model.DmUserQuery;
//import io.thoughtware.user.dmUser.service.DmUserService;
//import io.thoughtware.user.user.model.User;
//import io.thoughtware.user.user.service.UserService;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.util.ObjectUtils;
//import org.w3c.dom.Element;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * jira 数据导入服务
// */
//@Service
//@EnableTransactionManagement
//public class JiraImportDataCloud1ServiceImpl {
//
//    @Autowired
//    JpaTemplate jpaTemplate;
//
//    @Autowired
//    WorkItemService workItemService;
//
//    @Autowired
//    WorkTypeService workTypeService;
//
//    @Autowired
//    ProjectService projectService;
//
//    @Autowired
//    RoleService roleService;
//
//    @Autowired
//    DmUserService dmUserService;
//
//    @Autowired
//    DmRoleService dmRoleService;
//
//    @Autowired
//    DmRoleUserService dmRoleUserService;
//
//    @Autowired
//    WorkTypeDmService workTypeDmService;
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    DmFlowService dmFlowService;
//
//    @Autowired
//    StateNodeFlowService stateNodeFlowService;
//
//
//    @Value("${unzip.path}")
//    String unzipAddress;
//
//    private ThreadLocal<ArrayList<Element>> UserHistoryItemElementList = new ThreadLocal<ArrayList<Element>>();
//    private ThreadLocal<ArrayList<String>> ProjectRoleElementList = new ThreadLocal<ArrayList<String>>();
//    private ThreadLocal<ArrayList<Element>> ProjectRoleActorElementList = new ThreadLocal<ArrayList<Element>>();
//    private ThreadLocal<ArrayList<Element>> IssueElementList = new ThreadLocal<ArrayList<Element>>();
//
//    private ThreadLocal<ArrayList<Element>> IssueParentAssociationElementList = new ThreadLocal<ArrayList<Element>>();
//    private ThreadLocal<ArrayList<String>> SubWorkItemIds = new ThreadLocal<ArrayList<String>>();
//    private ThreadLocal<ArrayList<String>> TodoStatusIds = new ThreadLocal<ArrayList<String>>();
//    private ThreadLocal<ArrayList<String>> ProcessStatusIds = new ThreadLocal<ArrayList<String>>();
//    private ThreadLocal<ArrayList<String>> DoneStatusIds = new ThreadLocal<ArrayList<String>>();
//    private ThreadLocal<Map<String, Object>> ImportSchedule = new ThreadLocal<Map<String, Object>>();
//
//    public static Map<String, Integer> Percent = new HashMap();
//    public static Map<String, Project> CurrentProject = new HashMap();
//
//    @Override
//    public String writeData(List<Element> elements) {
//        String createUserId = LoginContext.getLoginId();
//        try {
//
//            List<Element> globalUserElementList = new ArrayList<>();
//
//            ArrayList<Element> projectElementList = new ArrayList<>();
//
//            ArrayList<Element> userHistoryItemElementList = new ArrayList<>();
//
//            ArrayList<String> projectRoleElementList = new ArrayList<>();
//
//            ArrayList<Element> projectRoleActorElementList = new ArrayList<>();
//
//            ArrayList<Element> issueElementList = new ArrayList<>();
//
//            ArrayList<Element> issueParentAssociationElementList = new ArrayList<>();
//
//            ArrayList<String> subWorkItemIds = new ArrayList<>();
//
//            ArrayList<String> todoStatusIds = new ArrayList<>();
//
//            ArrayList<String> processStatusIds = new ArrayList<>();
//
//            ArrayList<String> doneStatusIds = new ArrayList<>();
//
//
//            for (Element element : elements) {
//                String name = element.getTagName();
//                switch (name){
//                    case "User":
//                        String userName = element.getAttribute("userName");
//                        boolean status = userName.contains("ug:");
//                        String active = element.getAttribute("active");
//                        if(status == true && active.equals("1")){
//                            globalUserElementList.add(element);
//                        }
//                        break;
//                    case "Project":
//                        String projecttype = element.getAttribute("projecttype");
//                        if(projecttype.equals("software")){
//                            projectElementList.add(element);
//                        }
//
//                        break;
//                    case "Issue":
//                        issueElementList.add(element);
//                        break;
//                    case "UserHistoryItem":
//                        userHistoryItemElementList.add(element);
//                        break;
//                    case "ProjectRole":
//                        String name1 = element.getAttribute("name");
//                        if(name1.equals("Administrator")){
//                            String id = element.getAttribute("id");
//                            projectRoleElementList.add(id);
//                        }
//                        break;
//                    case "ProjectRoleActor":
//                        projectRoleActorElementList.add(element);
//                        break;
//                    case "IssueParentAssociation":
//                        issueParentAssociationElementList.add(element);
//                        String issueId = element.getAttribute("issueId");
//                        subWorkItemIds.add(issueId);
//                        break;
//                    case "Status":
//                        String statuscategory = element.getAttribute("statuscategory");
//                        String statusId = element.getAttribute("id");
//                        switch (statuscategory){
//                            case "2":
//                                todoStatusIds.add(statusId);
//                                break;
//                            case "3":
//                                doneStatusIds.add(statusId);
//                                break;
//                            case "4":
//                                processStatusIds.add(statusId);
//                                break;
//                            default:
//                                break;
//                        }
//                    default:
//                        break;
//                }
//            }
//
//            this.UserHistoryItemElementList.set(userHistoryItemElementList);
//            this.ProjectRoleElementList.set(projectRoleElementList);
//            this.ProjectRoleActorElementList.set(projectRoleActorElementList);
//            this.IssueElementList.set(issueElementList);
//            this.SubWorkItemIds.set(subWorkItemIds);
//            this.TodoStatusIds.set(todoStatusIds);
//            this.ProcessStatusIds.set(processStatusIds);
//            this.DoneStatusIds.set(doneStatusIds);
//            this.IssueParentAssociationElementList.set(issueParentAssociationElementList);
//            for (Element globalUserElement : globalUserElementList) {
//                setGlobalUser(globalUserElement);
//            }
//            // 获取导入项目的总数
//            int size = projectElementList.size();
//            Percent.put(createUserId + "total", size);
//            for (Element projectElement : projectElementList) {
//                setProject(projectElement, createUserId);
//            }
//            this.UserHistoryItemElementList.remove();
//            this.ProjectRoleElementList.remove();
//            this.ProjectRoleActorElementList.remove();
//            this.IssueElementList.remove();
//            this.SubWorkItemIds.remove();
//            this.TodoStatusIds.remove();
//            this.ProcessStatusIds.remove();
//            this.DoneStatusIds.remove();
//            return "succed";
//        } catch (Exception e) {
//            throw new ApplicationException(e);
//        }
//    }
//
//    public void setGlobalUser(Element element){
//        String id = element.getAttribute("userName");
//        String active = element.getAttribute("active");
//        boolean status = id.contains("ug:");
//        User user1 = userService.findUser(id.substring(0, 12));
//        if(ObjectUtils.isEmpty(user1)){
//            if(status == true && active.equals("1")){
//                String userName = element.getAttribute("displayName");
//                String emailAddress = element.getAttribute("emailAddress");
//                User user = new User();
//                user.setId(id.substring(0, 12));
//                user.setNickname(userName);
//                user.setName(userName);
//                user.setEmail(emailAddress);
//                user.setStatus(1);
//                user.setDirId("1");
//                user.setPassword("123456");
//                try {
//                    userService.createUser(user);
//                }catch (Exception e){
//                    throw new ApplicationException(2000,"成员添加失败" + e.getMessage());
//                }
//
//            }
//        }
//
//    }
//    public void setProject(Element element, String createUserId){
//        String projectId = element.getAttribute("id");
//        //项目名字
//        String projectName = element.getAttribute("name");
//        //项目描述
//        String description = element.getAttribute("description");
//        //项目负责人
//        String lead = element.getAttribute("lead");
//        String originalkey = element.getAttribute("originalkey");
//        String projecttype = element.getAttribute("projecttype");
//        Project project = projectService.findProject(projectId);
//        if (ObjectUtils.isEmpty(project) && projecttype.equals("software")){
//            Project project1 = new Project();
//            project1.setId(projectId);
//            project1.setProjectName(projectName);
//            project1.setProjectKey(originalkey);
//            project1.setDesc(description);
//            project1.setProjectLimits("0");
//            project1.setProjectState("1");
//            ProjectType projectType = new ProjectType();
//            projectType.setId("5a46432a");
//            projectType.setType("scrum");
//            project1.setProjectType(projectType);
//            User user = new User();
//            user.setId(lead.substring(0, 12));
//            project1.setMaster(user);
//            project1.setCreator(lead.substring(0, 12));
//            try {
//                String jiraProjectId = projectService.createJiraProject(project1);
//                List<WorkTypeDm> workTypeDmList = projectService.initWorkType(jiraProjectId);
//
//                Map<String, String> roleIds = setProjectRole(projectId);
//                setProjectUser(projectId, roleIds);
//
//                for (WorkTypeDm workTypeDm : workTypeDmList) {
//                    String code = workTypeDm.getWorkType().getCode();
//                    if(code.equals("task") ){
//                        setWorkItem(projectId, workTypeDm);
//                    }
//                }
//
//                Integer integer = Percent.get(createUserId + "currentNum");
//                if(!ObjectUtils.isEmpty(integer)){
//                    integer++;
//                    Percent.put(createUserId + "currentNum", integer);
//                }else {
//                    Percent.put(createUserId + "currentNum", 1);
//                }
//                CurrentProject.put(createUserId + "project",project1);
//            }catch (Exception e) {
//                throw new ApplicationException(2000, "项目添加失败: " + e.getMessage());
//            }
//        }
//    }
//
//
//
//    public void setWorkItem(String projectId, WorkTypeDm workTypeDm){
//        try {
//            ArrayList<Element> issueElementList = this.IssueElementList.get();
//            for (Element element : issueElementList) {
//                String id = element.getAttribute("id");
//                String currentProject = element.getAttribute("project");
//                String projectKey = element.getAttribute("projectKey");
//
//                String workId = "jr" +projectKey + "-" + id;
//                WorkItem workItem1 = workItemService.findWorkItem(workId);
//                if(currentProject.equals(projectId) && ObjectUtils.isEmpty(workItem1)){
//                    WorkItem workItem = new WorkItem();
//                    workItem.setId(workId);
//                    String summary = element.getAttribute("summary");
//                    workItem.setTitle(summary);
//
//                    Project project = new Project();
//                    project.setId(projectId);
//                    workItem.setProject(project);
//
//                    WorkType workType = new WorkType();
//                    workType.setId(workTypeDm.getWorkType().getId());
//                    workItem.setWorkType(workTypeDm);
//                    workItem.setWorkTypeCode("task");
//                    workItem.setWorkTypeSys(workType);
//
//
//                    String priorityId = element.getAttribute("priority");
//                    SelectItem selectItem = new SelectItem();
//                    selectItem.setId(priorityId);
//                    workItem.setWorkPriority(selectItem);
//
//                    // 设置父事项
//                    if(this.SubWorkItemIds.get().contains(id)){
//                        for (Element element1 : this.IssueParentAssociationElementList.get()) {
//                            String issueId = element1.getAttribute("issueId");
//                            if(issueId.equals(id)){
//                                WorkItem workItem2 = new WorkItem();
//                                String parentId = element1.getAttribute("parentId");
//                                workItem.setParentWorkItem(workItem2.setId("jr" +projectKey + "-" + parentId));
//                            }
//                        }
//                    }
//                    String treePath = findWorkItemParent(id, workItem, projectKey);
//                    workItem.setTreePath(treePath);
//
//                    User user = new User();
//                    String creator = element.getAttribute("creator");
//                    user.setId(creator.substring(0, 12));
//                    workItem.setBuilder(user);
//
//                    String assignee = element.getAttribute("assignee");
//                    if(!ObjectUtils.isEmpty(assignee)){
//                        user.setId(assignee.substring(0, 12));
//                        workItem.setAssigner(user);
//                    }
//
//                    String reporter = element.getAttribute("reporter");
//                    user.setId(reporter.substring(0, 12));
//                    workItem.setAssigner(user);
//
//                    String created = element.getAttribute("created");
//                    workItem.setBuildTime(created);
//
//                    // 设置状态
//                    String status = element.getAttribute("status");
//                   if(this.ProcessStatusIds.get().contains(status)){
//                        List<StateNodeFlow> nodeFlowList = workTypeDm.getFlow().getNodeFlowList();
//                        for (StateNodeFlow stateNodeFlow : nodeFlowList) {
//                            if(stateNodeFlow.getNodeStatus().equals("PROGRESS")){
//                                workItem.setWorkStatus(stateNodeFlow);
//                                workItem.setWorkStatusNode(stateNodeFlow.getNode());
//
//                                workItem.setWorkStatusCode("PROGRESS");
//                            }
//                        }
//                   }else if(this.DoneStatusIds.get().contains(status)){
//                        List<StateNodeFlow> nodeFlowList = workTypeDm.getFlow().getNodeFlowList();
//                        for (StateNodeFlow stateNodeFlow : nodeFlowList) {
//                            if(stateNodeFlow.getNodeStatus().equals("DONE")){
//                                workItem.setWorkStatus(stateNodeFlow);
//                                workItem.setWorkStatusNode(stateNodeFlow.getNode());
//
//                                workItem.setWorkStatusCode("DONE");
//                            }
//                        }
//                   }else {
//                        List<StateNodeFlow> nodeFlowList = workTypeDm.getFlow().getNodeFlowList();
//                        for (StateNodeFlow stateNodeFlow : nodeFlowList) {
//                            if(stateNodeFlow.getNodeStatus().equals("TODO")){
//                                workItem.setWorkStatus(stateNodeFlow);
//                                workItem.setWorkStatusNode(stateNodeFlow.getNode());
//
//                                workItem.setWorkStatusCode("TODO");
//                            }
//                        }
//                   }
//                    workItemService.createJiraWorkItem(workItem);
//                }
//            }
//        }catch (Exception e){
//            throw new ApplicationException(2000, "添加事项失败" + e.getMessage());
//        }
//
//
//    }
//
//    public void setProjectUser(String projectId, Map<String, String> roleIds){
//        String admin = roleIds.get("admin");
//        String common = roleIds.get("common");
//        for (Element element : this.ProjectRoleActorElementList.get()) {
//            String pid = element.getAttribute("pid");
//            if(!ObjectUtils.isEmpty(pid)){
//                String userIdLong = element.getAttribute("roletypeparameter");
//                String projectroleId = element.getAttribute("projectroleid");
//                if(pid.equals(projectId) && userIdLong.contains("ug:")){
//                    String userId = userIdLong.substring(0, 12);
//
//                    User user = new User();
//                    user.setId(userId);
//                    // 查找人员是否在已经在当前项目中
//                    DmUserQuery dmUserQuery = new DmUserQuery();
//                    dmUserQuery.setDomainId(projectId);
//                    dmUserQuery.setUserId(userId);
//                    List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
//
//                    if(dmUserList.isEmpty()){
//                        // 创建项目人员
//                        DmUser dmUser = new DmUser();
//                        dmUser.setUser(user);
//                        dmUser.setDomainId(projectId);
//                        try {
//                            dmUserService.createDmUser(dmUser);
//                        }catch (Exception e){
//                            throw new ApplicationException(2000,"项目成员添加失败" + e.getMessage());
//                        }
//
//                        boolean contains = this.ProjectRoleElementList.get().contains(projectroleId);
//
//                        try{
//                            if(contains && !StringUtils.isEmpty(admin)){
//
//                                DmRole dmRole = new DmRole();
//                                dmRole.setId(admin);
//                                DmRoleUser dmRoleUser = new DmRoleUser();
//                                dmRoleUser.setDomainId(projectId);
//                                dmRoleUser.setDmRole(dmRole);
//                                dmRoleUser.setUserId(userId);
//                                dmRoleUserService.createDmRoleUser(dmRoleUser);
//                            }
//                            if(!contains && !StringUtils.isEmpty(common)) {
//                                DmRole dmRole = new DmRole();
//                                dmRole.setId(common);
//                                DmRoleUser dmRoleUser = new DmRoleUser();
//                                dmRoleUser.setDomainId(projectId);
//                                dmRoleUser.setDmRole(dmRole);
//                                dmRoleUser.setUserId(userId);
//                                dmRoleUserService.createDmRoleUser(dmRoleUser);
//                            }
//                        }catch (Exception e){
//                            throw new ApplicationException(2000,"项目角色成员添加失败" + e.getMessage());
//
//                        }
//
//                    }
//                }
//            }
//        }
//        String loginId = LoginContext.getLoginId();
//        //设置导入者为项目成员
//        User user = new User();
//        user.setId(loginId);
//        DmUser dmUser = new DmUser();
//        dmUser.setUser(user);
//        dmUser.setDomainId(projectId);
//        dmUserService.createDmUser(dmUser);
//
//        DmRole dmRole = new DmRole();
//        DmRoleUser dmRoleUser = new DmRoleUser();
//        dmRoleUser.setUserId(loginId);
//        dmRole.setId(admin);
//        dmRoleUser.setDmRole(dmRole);
//        dmRoleUser.setDomainId(projectId);
//        dmRoleUserService.createDmRoleUser(dmRoleUser);
//    }
//
//    public Map<String, String> setProjectRole(String projectId){
//        //查找项目级角色
//        Map<String, String> roleIds = new HashMap<>();
//        RoleQuery roleQuery = new RoleQuery();
//        roleQuery.setType("2");
//        List<Role> roleList = roleService.findProjectRoleList(roleQuery);
//        if(roleList == null || roleList.size()==0){
//            return roleIds;
//        }
//        try {
//            for (Role role : roleList) {
//                // 标识业务类型，0:普通角色 1： 超级管理员角色
//                Integer businessType = role.getBusinessType();
//
//                RoleUserQuery roleUserQuery = new RoleUserQuery();
//                roleUserQuery.setRoleId(role.getId());
//                String newRoleId = roleService.cloneRole(role.getId());
//
//                //建立域->项目角色关系
//                DmRole dmRole = new DmRole();
//                Role role1 = new Role();
//                role1.setId(newRoleId);
//                role1.setName(role.getName());
//                dmRole.setRole(role1);
//                dmRole.setDomainId(projectId);
//                dmRole.setBusinessType(businessType);
//                String dmRole1 = dmRoleService.saveDmRole(dmRole);
//                if(businessType == 1){
//                    roleIds.put("admin", dmRole1);
//
//                }else {
//                    if(StringUtils.isEmpty(roleIds.get("common"))){
//                        roleIds.put("common", dmRole1);
//                    }
//
//                }
//            }
//        }catch (Exception e){
//            throw new ApplicationException(2000,"项目角色添加失败" + e.getMessage());
//        }
//
//        return roleIds;
//    }
//
//    public String findWorkItemParent(String workItemId, WorkItem workItem, String projectKey){
//        String treePath = new String();
//        for (Element element1 : this.IssueParentAssociationElementList.get()) {
//            String issueId = element1.getAttribute("issueId");
//            if(issueId.equals(workItemId)){
//                String parentId = element1.getAttribute("parentId");
//                String parentWorkId = "jr" + projectKey+ "-" + parentId ;
//                treePath = treePath  + parentWorkId + ";";
//                workItem.setRootId(parentWorkId);
//                if(this.SubWorkItemIds.get().contains(parentId)){
//                    findWorkItemParent(parentId, workItem, projectKey);
//
//                }else {
//                    return treePath;
//                }
//            }
//        }
//        return  treePath;
//    }
//
//
//    public JdbcTemplate getJdbcTemplet(){
//      return  jpaTemplate.getJdbcTemplate();
//    }
//
//}
