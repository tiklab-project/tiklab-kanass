package io.tiklab.kanass.workitem.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.exception.SystemException;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.flow.flow.model.*;
import io.tiklab.flow.statenode.model.*;
import io.tiklab.flow.statenode.service.StateNodeRelationService;
import io.tiklab.flow.transition.model.TransitionRule;
import io.tiklab.flow.transition.model.TransitionRuleQuery;
import io.tiklab.flow.transition.service.BusinessRoleService;
import io.tiklab.flow.transition.service.TransitionRuleService;
import io.tiklab.flow.transition.service.TransitionService;
import io.tiklab.form.field.model.SelectItem;
import io.tiklab.form.field.model.SelectItemRelation;
import io.tiklab.form.field.model.SelectItemRelationQuery;
import io.tiklab.form.field.service.SelectItemRelationService;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.version.model.ProjectVersion;
import io.tiklab.kanass.project.version.service.ProjectVersionService;
import io.tiklab.kanass.project.worklog.model.WorkLogQuery;
import io.tiklab.kanass.project.worklog.service.WorkLogService;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.kanass.sprint.service.SprintService;
import io.tiklab.message.message.model.SendMessageNotice;
import io.tiklab.message.message.service.SendMessageNoticeService;
import io.tiklab.privilege.vRole.model.VRoleDomain;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.security.logging.logging.model.Logging;
import io.tiklab.security.logging.logging.model.LoggingQuery;
import io.tiklab.security.logging.logging.model.LoggingType;
import io.tiklab.security.logging.logging.service.LoggingByTempService;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.kanass.workitem.model.*;
import io.tiklab.security.logging.logging.service.LoggingService;
import io.tiklab.todotask.todo.model.Task;
import io.tiklab.todotask.todo.model.TaskQuery;
import io.tiklab.todotask.todo.model.TaskType;
import io.tiklab.todotask.todo.service.TaskByTempService;
import io.tiklab.todotask.todo.service.TaskService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.flow.flow.service.DmFlowService;
import io.tiklab.flow.flow.service.FlowService;
import io.tiklab.flow.statenode.service.StateNodeFlowService;
import io.tiklab.flow.statenode.service.StateNodeService;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.message.message.model.Message;
import io.tiklab.message.message.model.MessageReceiver;
import io.tiklab.message.setting.model.MessageType;
import io.tiklab.kanass.workitem.dao.WorkItemDao;
import io.tiklab.kanass.workitem.entity.WorkItemEntity;
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
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
* 事项服务
*/
@Exporter
@Service
public class WorkItemServiceImpl implements WorkItemService {
    public final ExecutorService executorService = Executors.newCachedThreadPool();
    private static Logger logger = LoggerFactory.getLogger(WorkItemServiceImpl.class);

    private final Lock lock = new ReentrantLock();
    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    WorkItemDao workItemDao;

    @Autowired
    WorkRelateService workRelateService;

    @Autowired
    WorkLogService workLogService;

    @Autowired
    WorkItemDocumentService workItemDocumentService;

    @Autowired
    WorkCommentService workCommentService;

    @Autowired
    WorkTestCaseService workTestCaseService;

    @Autowired
    WorkAttachService workAttachService;

    @Autowired
    StateNodeRelationService stateNodeRelationService;

    @Autowired
    StateNodeService stateNodeService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    WorkTypeDmService workTypeDmService;

    @Autowired
    StateNodeFlowService stateNodeflowService;

    @Autowired
    DmFlowService dmFlowService;

    @Autowired
    SendMessageNoticeService sendMessageNoticeService;

    @Autowired
    WorkSprintService workSprintService;

    @Autowired
    WorkVersionService workVersionService;

    @Autowired
    BusinessRoleService businessRoleService;


    @Autowired
    UserService userService;

    @Autowired
    DmUserService dmUserService;

    @Autowired
    TransitionRuleService transitionRuleService;

    @Autowired
    TransitionService transitionService;
    @Autowired
    LoggingByTempService opLogByTemplService;

    @Autowired
    TaskByTempService taskByTempService;

    @Autowired
    TaskService taskService;

    @Autowired
    FlowService flowService;

    @Autowired
    SelectItemRelationService selectItemRelationService;

    @Autowired
    LoggingService loggingService;

    @Autowired
    SprintService sprintService;

    @Autowired
    ProjectVersionService projectVersionService;

    @Autowired
    ProjectService projectService;

    @Value("${base.url:null}")
    String baseUrl;

    /**
     * 设置事项code
     */
    public String setWorkItemCode(WorkItem workItem){
        String projectId = workItem.getProject().getId();
        String projectKey = projectService.findProject(projectId).getProjectKey();
        Integer maxOrderNum = workItemDao.findMaxIdWorkItem(projectId);
        //项目key - id
        String newId = new String();
        if(maxOrderNum != null){
            maxOrderNum = maxOrderNum + 1;
            int idInt = maxOrderNum;
            newId = projectKey.concat("-" + String.valueOf(idInt));
        }else {
            maxOrderNum = 1;
            newId = projectKey.concat("-" + String.valueOf(1));
        }
        workItem.setOrderNum(maxOrderNum);
        workItem.setCode(newId);
        return newId;
    }

    /**
     * 发送消息提醒
     * @param workItem
     */
    void sendMessageForCreate(WorkItem workItem){
        HashMap<String, Object> content = new HashMap<>();
        content.put("workItemTitle", workItem.getTitle());
        content.put("workItemId", workItem.getId());
        content.put("workTypeIcon", workItem.getWorkTypeSys().getIconUrl());
        content.put("workType", workItem.getWorkTypeSys().getName());
        content.put("projectId", workItem.getProject().getId());
        if(workItem.getSprint() != null) {
            content.put("sprintId", workItem.getSprint().getId());
        }
        if(workItem.getProjectVersion() != null) {
            content.put("versionId", workItem.getProjectVersion().getId());
        }
        Message message = new Message();
        MessageType messageType = new MessageType();
        messageType.setId("KANASS_MESSAGETYPE_TASKTODO");
        message.setMessageType(messageType);

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        content.put("createUser", user);
        content.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());
        content.put("receiveTime", new SimpleDateFormat("MM-dd").format(new Date()));

        // 接收者
        User assigner = workItem.getAssigner();
        List<MessageReceiver> objects = new ArrayList<>();
        MessageReceiver messageReceiver = new MessageReceiver();
        messageReceiver.setUserId(assigner.getId());

        objects.add(messageReceiver);
        message.setMessageReceiverList(objects);
        message.setBaseUrl(baseUrl);
        message.setLink("/project/${projectId}/workitem/${workItemId}");
        message.setAction(workItem.getTitle());
        message.setSendId(user.getId());
        message.setData(content);

        message.setMessageSendTypeId("site");
        sendMessageNoticeService.sendMessage(message);

        message.setId(null);
        message.setMessageSendTypeId("qywechat");
        sendMessageNoticeService.sendMessage(message);

    }

    /**
     * 更新负责人发送消息
     * @param workItem
     * @param receiver
     */
    void sendMessageForUpdateAssigner(WorkItem workItem, User receiver){
        HashMap<String, Object> content = new HashMap<>();
        content.put("workItemTitle", workItem.getTitle());
        content.put("workItemId", workItem.getId());
        content.put("workTypeIcon", workItem.getWorkTypeSys().getIconUrl());
        content.put("workType", workItem.getWorkTypeSys().getName());
        content.put("projectId", workItem.getProject().getId());
        content.put("receiverIcon",receiver.getNickname().substring(0, 1));
        content.put("receiver", receiver);

        if(workItem.getSprint() != null) {
            content.put("sprintId", workItem.getSprint().getId());
        }
        if(workItem.getProjectVersion() != null) {
            content.put("versionId", workItem.getProjectVersion().getId());
        }
        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        content.put("createUser", user);
        content.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());
        content.put("receiveTime", new SimpleDateFormat("MM-dd").format(new Date()));

        Message message = new Message();
        MessageType messageType = new MessageType();
        messageType.setId("KANASS_MESSAGETYPE_TASKTODO");
        message.setMessageType(messageType);
        message.setData(content);

        // 接收者
        List<MessageReceiver> objects = new ArrayList<>();
        MessageReceiver messageReceiver = new MessageReceiver();
        messageReceiver.setUserId(receiver.getId());
        objects.add(messageReceiver);
        message.setMessageReceiverList(objects);


        message.setBaseUrl(baseUrl);
        message.setLink("/project/${projectId}/workitem/${workItemId}");
        message.setAction(workItem.getTitle());
        message.setMessageSendTypeId("site");
        // 发送者
        message.setSendId(user.getId());
        sendMessageNoticeService.sendMessage(message);

        message.setId(null);
        message.setMessageSendTypeId("qywechat");
        sendMessageNoticeService.sendMessage(message);
    }

    /**
     * 事项状态变化发送消息
     * @param oldWorkItem
     * @param workItem
     */
    void sendMessageForUpdateStatus(WorkItem oldWorkItem, WorkItem workItem){
        String projectId = oldWorkItem.getProject().getId();
        String workItemId = workItem.getId();
        HashMap<String, Object> content = new HashMap<>();
        content.put("workItemTitle", workItem.getTitle());
        content.put("workItemId", workItemId);
        content.put("workTypeIcon", oldWorkItem.getWorkTypeSys().getIconUrl());
        content.put("projectId", projectId);
        content.put("oldValue", oldWorkItem.getWorkStatusNode().getName());
        content.put("newValue", workItem.getWorkStatusNode().getName());
        if(workItem.getSprint() != null) {
            content.put("sprintId", workItem.getSprint().getId());
        }
        if(workItem.getProjectVersion() != null) {
            content.put("versionId", workItem.getProjectVersion().getId());
        }
        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        content.put("createUser", user);
        content.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());
        content.put("receiveTime", new SimpleDateFormat("MM-dd").format(new Date()));
        String msg = JSONObject.toJSONString(content);
//        Message message = new Message();
//        MessageType messageType = new MessageType();
//        messageType.setId("KANASS_MESSAGETYPE_UPDATESTATUS");


        SendMessageNotice sendMessageNotice = new SendMessageNotice();
        sendMessageNotice.setId("KANASS_MESSAGE_UPDATESTATUS");
        sendMessageNotice.setDomainId(projectId);
        sendMessageNotice.setLink("/project/${projectId}/workitem/${workItemId}");
        sendMessageNotice.setAction(workItem.getTitle());
        sendMessageNotice.setBaseUrl(baseUrl);
        sendMessageNotice.setSiteData(msg);
        sendMessageNotice.setQywechatData(msg);
        sendMessageNotice.setSendId(createUserId);
        sendMessageNotice.setAction(oldWorkItem.getTitle());
        VRoleDomain vRoleDomain = new VRoleDomain();
        vRoleDomain.setType("workItem");
        vRoleDomain.setModelId(workItemId);
        vRoleDomain.setDomainId(projectId);
        sendMessageNotice.setvRoleDomain(vRoleDomain);
        sendMessageNoticeService.sendDmMessageNotice(sendMessageNotice);
    }


    /**
     * 创建待办
     * @param workItem
     * @param receiver
     */
    void creatTodoTask(WorkItem workItem, User receiver){
        Task task = new Task();
        task.setBgroup("kanass");
        task.setTitle("待办事项");

        task.setAssignUser(receiver);

        TaskType taskType = new TaskType();
        taskType.setId("KANASS_TODOTYPE_WORKITEMTODO");
        task.setTodoType(taskType);

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        task.setCreateUser(user);

        HashMap<String, Object> content = new HashMap<>();
        content.put("workItemTitle", workItem.getTitle());
        content.put("workItemId", workItem.getId());
        content.put("projectId", workItem.getProject().getId());
        if(workItem.getSprint() != null) {
            content.put("sprintId", workItem.getSprint().getId());
        }
        if(workItem.getProjectVersion() != null) {
            content.put("versionId", workItem.getProjectVersion().getId());
        }
        content.put("createUser", user);
        content.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());
        content.put("receiveTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        task.setData(JSON.toJSONString(content));
        task.setBaseUrl(baseUrl);

        //设置任务结束事件
        String planEndTime = workItem.getPlanEndTime();
        if(planEndTime != null){
            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            Date date = null;
            try {
                date = dateFormat.parse(planEndTime);
            } catch (Exception e) {
                throw new ApplicationException(e.getMessage());
            }
            Timestamp timestamp = new Timestamp(date.getTime());
            task.setEndTime(timestamp);
        }

        task.setAction(workItem.getTitle());
        task.setLink("/project/${projectId}/workitem/${workItemId}");
        taskByTempService.createTask(task);
    }

    /**
     * 更新待办
     * @param workItem
     * @param taskId
     */
    void updateTodoTask(WorkItem workItem, String taskId){
        Task task = taskService.findOne(taskId);
        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        String updateField = workItem.getUpdateField();

        if(updateField.equals("assigner")){
            task.setAssignUser(workItem.getAssigner());
        }

        if(updateField.equals("title") || updateField.equals("sprint") || updateField.equals("projectVersion")){
            HashMap<String, Object> content = new HashMap<>();
            content.put("workItemTitle", workItem.getTitle());
            content.put("workItemId", workItem.getId());
            content.put("projectId", workItem.getProject().getId());
            if(workItem.getSprint() != null) {
                content.put("sprintId", workItem.getSprint().getId());
            }
            if(workItem.getProjectVersion() != null) {
                content.put("versionId", workItem.getProjectVersion().getId());
            }
            content.put("createUser", user);
            content.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());
            content.put("receiveTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            task.setData(JSON.toJSONString(content));
            task.setAction(workItem.getTitle());
        }

        if(updateField.equals("planBeginTime")){
            String planEndTime = workItem.getPlanEndTime();
            if(planEndTime != null){
                String pattern = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                Date date = null;
                try {
                    date = dateFormat.parse(planEndTime);
                } catch (Exception e) {
                    throw new ApplicationException(e.getMessage());
                }
                Timestamp timestamp = new Timestamp(date.getTime());
                task.setEndTime(timestamp);
            }
        }

        task.setId(taskId);
        taskService.updateTask(task);
    }

    /**
     * 根据事项类型查找配置流程开始状态
     * @param workTypeId
     * @return
     */
    StateNodeFlow findStartState(WorkItem workItem, String workTypeId){
        //设置事项状态
        WorkTypeDm workTypeDm = workTypeDmService.findWorkTypeDm(workTypeId);
        Flow flow = workTypeDm.getFlow();

        if(flow == null){
            throw new ApplicationException("为设置流程id");
        }
        FlowStartNode flowRemoveStart = flowService.findFlowRemoveStart(flow.getId());

        StateNodeFlow stateNode = new StateNodeFlow();
        try {
            stateNode = flowRemoveStart.getStartNode();
        }catch (Exception e){
            throw new ApplicationException("start state not found.请创建流程");
        }
        workItem.setWorkStatus(stateNode);
        workItem.setWorkStatusNode(stateNode.getNode());
        workItem.setWorkStatusCode(stateNode.getNodeStatus());
        return stateNode;
    }

    /**
     * 创建事项与状态节点的关联关系
     * @param workItem
     * @param stateNode
     */
    void createWorkStateNodeRelation(WorkItem workItem, StateNodeFlow stateNode){
        //设置节点跟事项关联
        String id = workItem.getId();
        StateNodeRelation stateNodeRelation = new StateNodeRelation();
        stateNodeRelation.setWorkId(id);
        stateNodeRelation.setWorkName(workItem.getTitle());
        stateNodeRelation.setStateNodeId(stateNode.getId());
        stateNodeRelation.setNodeId(stateNode.getNode().getId());
        stateNodeRelation.setFlowId(stateNode.getFlow().getId());
        stateNodeRelation.setProjectId(workItem.getProject().getId());
        try {
            stateNodeRelationService.createStateNodeRelation(stateNodeRelation);
        }catch (Exception e){
            throw new ApplicationException(e);
        }
    }

    /**
     * 更新事项创建日志
     * @param logContent
     * @param workItem
     */
    void creatUpdateOplog(WorkItem workItem, HashMap<String, Object> logContent, String actionType){
        Logging log = new Logging();
        log.setBgroup("kanass");
        log.setModule("workItem");
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        log.setUser(user);

        logContent.put("workItemTitle", workItem.getTitle());
        logContent.put("workItemId", workItem.getId());
        logContent.put("projectId", workItem.getProject().getId());
        logContent.put("master", user);
        logContent.put("receiveTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        logContent.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());

        if(workItem.getSprint() != null) {
            logContent.put("sprintId", workItem.getSprint().getId());
        }
        if(workItem.getProjectVersion() != null) {
            logContent.put("versionId", workItem.getProjectVersion().getId());
        }

        LoggingType opLogType = new LoggingType();
        opLogType.setId(actionType);
        log.setActionType(opLogType);

        log.setBaseUrl(baseUrl);
        log.setAction(workItem.getTitle());
        log.setLink("/project/${projectId}/workitem/${workItemId}");
        log.setData(JSON.toJSONString(logContent));

        opLogByTemplService.createLog(log);
    }

    /**
     * 新增创建事项的动态
     * @param workItem
     */
    void creatWorkItemDynamic(WorkItem workItem){

        Logging log = new Logging();
        log.setBgroup("kanass");

        LoggingType opLogType = new LoggingType();
        opLogType.setId("KANASS_LOGTYPE_WORKITEMADD");
        log.setActionType(opLogType);

        log.setModule("workItem");
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        log.setUser(user);

        Map<String, String> content = new HashMap<>();
        content.put("projectId", workItem.getProject().getId());
        content.put("projectName", workItem.getProject().getProjectName());
        content.put("workItemId", workItem.getId());
        content.put("workItemTitle",  workItem.getTitle());
        content.put("workItemTypeName",  workItem.getWorkTypeSys().getName());
        content.put("workItemType", workItem.getWorkType().getId());
        content.put("workItemIcon", workItem.getWorkTypeSys().getIconUrl());
        content.put("master", user.getNickname());
        content.put("createTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        content.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());
        log.setData(JSONObject.toJSONString(content));
        log.setLink("/project/${projectId}/workitem/${workItemId}");
        log.setBaseUrl(baseUrl);
        log.setAction(content.get("workItemTitle"));
        opLogByTemplService.createLog(log);
    }

    /**
     * 创建事项
     * @param workItem
     * @return
     */
    @Override
    public String createWorkItem(@NotNull @Valid WorkItem workItem) {
        // 设置初始状态
        WorkTypeDm workType = workItem.getWorkType();
        String workTypeId = workType.getId();

        //设置创建时间
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formater.format(new Date());
        workItem.setBuildTime(format);

        //设置事项类型code,关联的系统事项类型
        WorkTypeDm workTypeDm = workTypeDmService.findWorkTypeDm(workTypeId);
        String code = workTypeDm.getWorkType().getCode();
        workItem.setWorkTypeCode(code);
        workItem.setWorkTypeSys(workTypeDm.getWorkType());

        //设置treePath,rootId
        if(workItem.getParentWorkItem() != null){
            if(workItem.getParentWorkItem().getId() != null && !workItem.getParentWorkItem().getId().equals("nullstring") ) {
                String treePath = workItem.getParentWorkItem().getId() + ";";
                WorkItem parentWorkItem = findWorkItem(workItem.getParentWorkItem().getId());
                if(parentWorkItem != null && parentWorkItem.getTreePath() != null){
                    treePath = treePath.concat(parentWorkItem.getTreePath());
                }
                String rootId = parentWorkItem.getRootId();
                workItem.setRootId(rootId);
                workItem.setTreePath(treePath);
            }
        }

        lock.lock();
        // 设置id和序号
        String id = new String();
        StateNodeFlow startState = findStartState(workItem, workTypeId);
        try {
            setWorkItemCode(workItem);
            WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
            id = workItemDao.createWorkItem(workItemEntity);
            workItem.setId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

        createWorkStateNodeRelation(workItem, startState);

        // 创建事项【类别】与选项的关联关系
        String eachType = workItem.getEachType();
        if(eachType != null){
            SelectItemRelation selectItemRelation = new SelectItemRelation();
            selectItemRelation.setSelectItemId(eachType);
            selectItemRelation.setRelationId(id);
            selectItemRelation.setFieldId(workItem.getFieldId());
            selectItemRelationService.createSelectItemRelation(selectItemRelation);
        }

        // 设置默认优先级
        if(workItem.getWorkPriority() != null && workItem.getWorkPriority().getId() != null
                && workItem.getWorkPriority().getId() != "nullstring" ){
            SelectItemRelation selectItemRelation = new SelectItemRelation();
            String workPriorityId = workItem.getWorkPriority().getId();
            selectItemRelation.setSelectItemId(workPriorityId);
            selectItemRelation.setFieldId("187d7a58");
            selectItemRelation.setRelationId(id);
            selectItemRelationService.createSelectItemRelation(selectItemRelation);
        }

        // 如果添加的时候有迭代, 创建迭代与事项的关联记录
        Sprint sprint = workItem.getSprint();
        if(sprint != null && sprint.getId() != null && !sprint.getId().equals("nullstring")){
            WorkSprint workSprint = new WorkSprint();
            workSprint.setWorkItemId(id);
            workSprint.setSprintId(sprint.getId());
            workSprintService.createWorkSprint(workSprint);
        }

        // 如果添加的时候有版本, 创建版本与事项的关联记录
        ProjectVersion projectVersion = workItem.getProjectVersion();
        if(projectVersion != null && projectVersion.getId() != null && !projectVersion.getId().equals("nullstring")){
            WorkVersion workVersion = new WorkVersion();
            workVersion.setVersionId(projectVersion.getId());
            workVersion.setWorkItemId(id);
            workVersionService.createWorkVersion(workVersion);
        }

        //如果父级为空，更新根节点
        if(workItem.getParentWorkItem() == null){
            WorkItem workItem1 = new WorkItem();
            workItem1.setRootId(id);
            workItem1.setId(id);
            WorkItemEntity workItemEntity1 = BeanMapper.map(workItem1, WorkItemEntity.class);
            workItemDao.updateWorkItem(workItemEntity1);
        }

        if(workItem.getParentWorkItem() != null){
            if(workItem.getParentWorkItem().getId() == null || workItem.getParentWorkItem().getId().equals("nullstring")){
                WorkItem workItem1 = new WorkItem();
                workItem1.setRootId(id);
                workItem1.setId(id);
                WorkItemEntity workItemEntity1 = BeanMapper.map(workItem1, WorkItemEntity.class);
                workItemDao.updateWorkItem(workItemEntity1);
            }
        }

        WorkItem workItem1 = findWorkItem(id);
        executorService.submit(() -> {
            creatWorkItemDynamic(workItem1);
            creatTodoTask(workItem1, workItem1.getBuilder());
            sendMessageForCreate(workItem1);
        });

        return id;
    }

    /**
     * 用于jira导入
     * @param workItem
     * @return
     */
    @Override
    public String createJiraWorkItem(@NotNull @Valid WorkItem workItem) {

        WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
        String workItemId = workItemDao.createWorkItem(workItemEntity);
        return workItemId;
    }

    /**
     * 更新事项
     * @param workItem
     */
    @Override
    public void updateWorkItem(@NotNull WorkItem workItem) {
        String updateField = workItem.getUpdateField();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formater.format(new Date());
        workItem.setUpdateTime(format);

        switch (updateField){
            case "parentWorkItem":
                updateParentWorkItem(workItem);
                break;
            case "workStatusNode":
                updateStatus(workItem);
                break;
            case "assigner":
                updateAssigner(workItem);
                break;
            case "title":
                updateTitle(workItem);
                break;
            case "sprint":
                updateWorkItemSprint(workItem);
                break;
            case "sprints":
                updateWorkItemListSprint(workItem);
                break;
            case "projectVersions":
                updateWorkItemListVersion(workItem);
                break;
            case "projectVersion":
                updateWorkItemVersion(workItem);
                break;
            case "planBeginTime":
                updateWorkItemPlanTime(workItem);
                break;
            case "eachType":
                updateEachType(workItem);
                break;
            case "workPriority":
                updateWorkPriority(workItem);
                break;
            case "preDependWorkItem":
                updatePreDependWorkItem(workItem);
                break;
            default:
                WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
                workItemDao.updateWorkItem(workItemEntity);
                break;
        };
    }


    @Override
    public void updateWork(@NotNull WorkItem workItem){
        WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
        workItemDao.updateWorkItem(workItemEntity);
    }

    /**
     * 更新事项标题
     * @param workItem
     */
    public void updateTitle(WorkItem workItem){
        WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
        workItemDao.updateWorkItem(workItemEntity);
        WorkItem workItem1 = findWorkItem(workItem.getId());
        updateFlowRelation(workItem);
        workItem1.setUpdateField(workItem.getUpdateField());
        updateTodoTaskData(workItem1);
    }

    /**
     * 更新待办
     * @param workItem
     */
    public void updateTodoTaskData(WorkItem workItem) {
        String id = workItem.getId();
        TaskQuery taskQuery = new TaskQuery();
        LinkedHashMap data = new LinkedHashMap();
        data.put("workItemId", id);
        taskQuery.setData(data);
        taskQuery.setBgroup("kanass");
        try {
            Pagination<Task> taskPage = taskService.findTaskPage(taskQuery);
            for (Task task : taskPage.getDataList()) {
                updateTodoTask(workItem,task.getId());
            }
        } catch (Exception e){
            throw new ApplicationException(e.getMessage());
        }

    }

    /**
     * 更新事项迭代
     * @param workItem
     */
    public void updateWorkItemListSprint(WorkItem workItem){
        String id = workItem.getId();
        Sprint sprint = workItem.getSprint();
        List<WorkItemEntity> workItemAndChildren = workItemDao.findWorkItemAndChildren(id);
        List<WorkItem> workItemList =  BeanMapper.mapList(workItemAndChildren,WorkItem.class);
        workItemList.add(workItem);
        if(workItemList.size() > 0){
            for (WorkItem workItem1 : workItemList) {
                workItem1.setUpdateField("sprint");
                workItem1.setSprint(sprint);
                updateWorkItemSprint(workItem1);
            }
        }
    }

    /**
     * 更新事项迭代，并更新事项与迭代的关联表
     * @param workItem
     */
    public void updateWorkItemSprint(WorkItem workItem){
        WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
        // 更新事项的迭代
        WorkItem oldWorkItem = findWorkItem(workItem.getId());

        Sprint oldSprint = oldWorkItem.getSprint();
        Sprint newSprint = workItem.getSprint();
        if(oldSprint != null){
            WorkSprintQuery workSprintQuery = new WorkSprintQuery();
            workSprintQuery.setWorkItemId(workItem.getId());
            workSprintQuery.setSprintId(oldSprint.getId());
            List<WorkSprint> workSprintList = workSprintService.findWorkSprintList(workSprintQuery);

            if(workSprintList.size() > 0){
                String workSprintId = workSprintList.get(0).getId();
                if(newSprint != null && !newSprint.getId().equals("nullstring")){
                    // 如果已经有关联，更新事项与迭代关联
                    WorkSprint workSprint = new WorkSprint();
                    workSprint.setId(workSprintId);
                    workSprint.setSprintId(newSprint.getId());
                    workSprint.setWorkItemId(workItem.getId());
                    workSprintService.updateWorkSprint(workSprint);
                }else {
                    // 如果已经有关联更新到没有关联迭代，更新事项与迭代关联
                    workSprintService.deleteWorkSprint(workSprintId);
                }
            }else {
                // 如果之前没有关联过事项，现在关联了
                if(newSprint != null && !newSprint.getId().equals("nullstring")){
                    WorkSprint workSprint = new WorkSprint();
                    workSprint.setSprintId(newSprint.getId());
                    workSprint.setWorkItemId(workItem.getId());
                    workSprintService.createWorkSprint(workSprint);
                }
            }
        }else {
            if(newSprint != null && !newSprint.getId().equals("nullstring")){
                WorkSprint workSprint = new WorkSprint();
                workSprint.setSprintId(newSprint.getId());
                workSprint.setWorkItemId(workItem.getId());
                workSprintService.createWorkSprint(workSprint);
            }

        }
        workItemDao.updateWorkItem(workItemEntity);
        // 更新待办
        WorkItem workItem1 = findWorkItem(workItem.getId());
        workItem1.setUpdateField(workItem.getUpdateField());
        updateTodoTaskData(workItem1);
    }

    /**
     * 更新事项的版本
     * @param workItem
     */
    public void updateWorkItemListVersion(WorkItem workItem){
        String id = workItem.getId();
        ProjectVersion projectVersion = workItem.getProjectVersion();
        List<WorkItemEntity> workItemAndChildren = workItemDao.findWorkItemAndChildren(id);
        List<WorkItem> workItemList =  BeanMapper.mapList(workItemAndChildren,WorkItem.class);
        workItemList.add(workItem);
        if(workItemList.size() > 0){
            for (WorkItem workItem1 : workItemList) {
                workItem1.setUpdateField("version");
                workItem1.setProjectVersion(projectVersion);
                updateWorkItemVersion(workItem1);
            }
        }
    }

    /**
     * 更新事项版本，并更新事项与版本的关联表
     * @param workItem
     */
    public void updateWorkItemVersion(WorkItem workItem){
        WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
        // 更新事项的版本
        WorkItem oldWorkItem = findWorkItem(workItem.getId());
        ProjectVersion newVersion = workItem.getProjectVersion();
        ProjectVersion oldVersion = oldWorkItem.getProjectVersion();
        if(oldVersion != null){
            WorkVersionQuery workVersionQuery = new WorkVersionQuery();
            workVersionQuery.setWorkItemId(workItem.getId());
            workVersionQuery.setVersionId(oldVersion.getId());
            List<WorkVersion> workVersionList = workVersionService.findWorkVersionList(workVersionQuery);

            if(workVersionList.size() > 0){
                String workVersionId = workVersionList.get(0).getId();
                if(newVersion != null && !newVersion.getId().equals("nullstring")){
                    // 如果已经有关联，更新事项与迭代关联
                    WorkVersion workVersion = new WorkVersion();
                    workVersion.setId(workVersionId);
                    workVersion.setVersionId(newVersion.getId());
                    workVersion.setWorkItemId(workItem.getId());
                    workVersionService.updateWorkVersion(workVersion);
                }else {
                    // 如果已经有关联更新到没有关联迭代，更新事项与迭代关联
                    workVersionService.deleteWorkVersion(workVersionId);
                }
            }else {
                // 如果之前没有关联过事项，现在关联了
                if(newVersion != null && !newVersion.getId().equals("nullstring")){
                    WorkVersion workVersion = new WorkVersion();
                    workVersion.setVersionId(newVersion.getId());
                    workVersion.setWorkItemId(workItem.getId());
                    workVersionService.createWorkVersion(workVersion);
                }
            }
        }else {
            if(newVersion != null && !newVersion.getId().equals("nullstring")){
                WorkVersion workVersion = new WorkVersion();
                workVersion.setVersionId(newVersion.getId());
                workVersion.setWorkItemId(workItem.getId());
                workVersionService.createWorkVersion(workVersion);
            }
        }
        workItemDao.updateWorkItem(workItemEntity);
        // 更新待办
        WorkItem workItem1 = findWorkItem(workItem.getId());
        workItem1.setUpdateField(workItem.getUpdateField());
        updateTodoTaskData(workItem1);
    }

    /**
     * 更新事项计划时间
     * @param workItem
     */
    public void updateWorkItemPlanTime(WorkItem workItem){
        String id = workItem.getId();
        TaskQuery taskQuery = new TaskQuery();
        LinkedHashMap data = new LinkedHashMap();
        data.put("workItemId", id);
        taskQuery.setData(data);
        taskQuery.setBgroup("kanass");
        try {
            Pagination<Task> taskPage = taskService.findTaskPage(taskQuery);
            for (Task task : taskPage.getDataList()) {
                //设置任务结束事件
                String planEndTime = workItem.getPlanEndTime();
                String pattern = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                Date date = null;
                try {
                    date = dateFormat.parse(planEndTime);
                } catch (Exception e) {
                    throw new ApplicationException(e.getMessage());
                }
                Timestamp timestamp = new Timestamp(date.getTime());
                task.setEndTime(timestamp);
                taskService.updateTask(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
        workItemDao.updateWorkItem(workItemEntity);
    }

    /**
     * 更新前置事项
     * @param workItem
     */
    public void updatePreDependWorkItem(WorkItem workItem){
        // 判断所选事项是否能添加为前置
        String id = workItem.getId();

        if((workItem.getPreDependWorkItem() != null && workItem.getPreDependWorkItem().getId() != null ) &&
                !workItem.getPreDependWorkItem().getId().equals("nullstring")){
            String preDependWorkItemId = workItem.getPreDependWorkItem().getId();
            WorkItem preDependWorkItem = findWorkItem(preDependWorkItemId);
            WorkItem workItem1 = findWorkItem(id);
                if(!preDependWorkItem.getWorkStatusCode().equals("DONE") && !workItem1.getWorkStatusCode().equals("TODO")){
                throw new SystemException(3001, "当前事项已经开始，所选择前置事项未开始，不可添加为前置事项");
            }
        }

        WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
        workItemDao.updateWorkItem(workItemEntity);
    }

    /**
     * 更新缺陷类型、需求类型、任务类型
     * @param workItem
     */
    public void updateEachType(WorkItem workItem){
        // 添加选项与事项的关联关系
        String eachType = workItem.getEachType();
        if(!eachType.equals("nullstring")){
            SelectItemRelation selectItemRelation = new SelectItemRelation();
            selectItemRelation.setSelectItemId(eachType);
            selectItemRelation.setRelationId(workItem.getId());
            selectItemRelation.setFieldId(workItem.getFieldId());
            selectItemRelationService.createSelectItemRelation(selectItemRelation);
        }else {
            SelectItemRelationQuery selectItemRelationQuery = new SelectItemRelationQuery();
            selectItemRelationQuery.setRelationId(workItem.getId());
            selectItemRelationQuery.setFieldId(workItem.getFieldId());
            selectItemRelationService.deleteSelectItemRelationCondition(selectItemRelationQuery);
        }
        WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
        workItemDao.updateWorkItem(workItemEntity);
    }

    /**
     * 更新事项优先级
     * @param workItem
     */
    public void updateWorkPriority(WorkItem workItem){
        // 添加选项与事项的关联关系
        SelectItem workPriority = workItem.getWorkPriority();
        if(workPriority != null && workPriority.getId() != null && !workPriority.getId().equals("nullstring")){
            String workPriorityId = workPriority.getId();
            SelectItemRelation selectItemRelation = new SelectItemRelation();
            selectItemRelation.setSelectItemId(workPriorityId);
            selectItemRelation.setRelationId(workItem.getId());
            selectItemRelation.setFieldId("187d7a58");
            selectItemRelationService.createSelectItemRelation(selectItemRelation);
        }else {
            SelectItemRelationQuery selectItemRelationQuery = new SelectItemRelationQuery();
            selectItemRelationQuery.setRelationId(workItem.getId());
            selectItemRelationQuery.setFieldId("187d7a58");
            selectItemRelationService.deleteSelectItemRelationCondition(selectItemRelationQuery);
        }
        WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
        workItemDao.updateWorkItem(workItemEntity);
    }

    // 更新上级事项
    public void updateParentWorkItem(WorkItem workItem){
        //设置treePath,rootId
        String id = workItem.getId();

        if((workItem.getParentWorkItem() != null && workItem.getParentWorkItem().getId() != null ) &&
                !workItem.getParentWorkItem().getId().equals("nullstring")){
            // 如果上级事项不为空
            String parentId = workItem.getParentWorkItem().getId();
            WorkItem parentWorkItem = findWorkItem(parentId);
            WorkItem workItem1 = findWorkItem(id);
            String workStatusCode = workItem1.getWorkStatusCode();
            String parentWorkStatusCode = parentWorkItem.getWorkStatusCode();
            // 判断是否能修改选择的事项为父级
            // 判断上级的状态
            if(parentWorkStatusCode.equals("DONE") && !workStatusCode.equals("DONE")){
                throw new SystemException(3001, "当前事项未完成，所选择父级已完成，不可添加为父级");
            }

            // 判断是否能修改选择的事项为父级
            // 判断上级的层级
            Integer childrenLevel = findChildrenLevel(id);
            if(childrenLevel.equals(2)){
                throw new SystemException(3001, "事项限制为三级，当前事项不能添加父级");
            }
            String parentTreePath = parentWorkItem.getTreePath();
            int length =0;
            if(parentTreePath != null){
                String[] split = parentTreePath.split(";");
                length = split.length;
            }
            if(childrenLevel.equals(1)){
               if(length > 0){
                   throw new SystemException(3001, "事项限制为三级，不能添加当前事项为父级");
               }
            }

            if(childrenLevel.equals(0)){
                if(length > 1){
                    throw new SystemException(3001, "事项限制为三级，不能添加当前事项为父级");
                }
            }

            // 获取选择的事项的treePath
            String treePath = workItem.getParentWorkItem().getId() + ";";
            if(parentWorkItem.getTreePath() != null){
                treePath = treePath.concat(parentWorkItem.getTreePath());
            }
            workItem.setRootId(parentWorkItem.getRootId());
            workItem.setTreePath(treePath);

            //修改当前事项下级的treepath
            String childrenTreePath = id + ";" + treePath ;
            String rootId = parentWorkItem.getRootId();
            workItemDao.updateChildrenTreePath(id, childrenTreePath, rootId);

        }else if((workItem.getParentWorkItem() != null && workItem.getParentWorkItem().getId() != null ) &&
                workItem.getParentWorkItem().getId().equals("nullstring")){
            // 如果上级事项设置为空
            workItem.setRootId(id);
            workItem.setTreePath("nullstring");

            //修改当前事项下级的treepath
            String childrenTreePath = id + ";";
            workItemDao.updateChildrenTreePath(id, childrenTreePath, id);
        }
        WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
        workItemDao.updateWorkItem(workItemEntity);
    }

    /**
     * 更新状态
     * @param workItem
     */
    public void updateStatus(WorkItem workItem){
        // 设置状态之后处理前置事项，后置事项，完成时间

        // 1. 判断子事项是否全部解决完成
        String statusId = workItem.getWorkStatusNode().getId();
        String id = workItem.getId();
        if(statusId.equals("done")){
            WorkItemQuery childWorkItemQuery = new WorkItemQuery();
            childWorkItemQuery.setParentId(id);
            List<WorkItemEntity> workItemList = workItemDao.findWorkItemList(childWorkItemQuery);
            List<WorkItemEntity> collect = workItemList.stream().filter(work -> !work.getWorkStatusNodeId().equals("done")).collect(Collectors.toList());
            if(collect.size() > 0){
                throw new ApplicationException("还有下级事项没有关闭");
            }
        }

        if(!statusId.equals("todo")){
            WorkItemEntity workItem1 = workItemDao.findWorkItem(id);
            String preDependId = workItem1.getPreDependId();
            if(preDependId != null && preDependId.length() > 1){
                WorkItemEntity workItem2 = workItemDao.findWorkItem(preDependId);
                if(workItem2.getWorkStatusNodeId() != null && !workItem2.getWorkStatusNodeId() .equals("done") ){
                    throw new ApplicationException("前置事项没有关闭，当前事项不能开启");
                }
            }
        }

        WorkItem oldWorkItem = findWorkItem(id);
        updateWorkItemStatus(workItem, oldWorkItem);
        String transitionId = workItem.getTransitionId();
        WorkItem newWorkItem = findWorkItem(id);
        executorService.submit(() -> {
            sendMessageForUpdateStatus(oldWorkItem, newWorkItem);
        });

        if(transitionId != null){
            updateByTransitionRule(workItem, oldWorkItem, transitionId);
        }
    }

    /**
     * 更新负责人
     * @param workItem
     */
    public void updateAssigner(WorkItem workItem){
        // 若更新负责人发送待办、消息和更新日志
        String id = workItem.getId();
        WorkItem oldWorkItem = findWorkItem(id);

        String assignerId = workItem.getAssigner().getId();
        User assigner = userService.findOne(assignerId);
        WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
        workItemDao.updateWorkItem(workItemEntity);

        WorkItem newWorkItem = findWorkItem(id);
//        creatTodoTask(newWorkItem, assigner);
        // 更新待办的被分配人
        newWorkItem.setUpdateField("assigner");
        updateTodoTaskData(newWorkItem);

        sendMessageForUpdateAssigner(newWorkItem, assigner);
        HashMap<String, Object> logContent = new HashMap<>();
        if(ObjectUtils.isEmpty(oldWorkItem.getAssigner())){
            User user = new User();
            user.setNickname("无");
            user.setName("wu");
            logContent.put("oldValue", user);
        }else {
            logContent.put("oldValue", oldWorkItem.getAssigner());
        }
        logContent.put("newValue", newWorkItem.getAssigner());
        creatUpdateOplog(newWorkItem, logContent, "KANASS_LOGTYPE_WORKUPDATEMASTER");
    }

    /**
     * 更新状态之后更新对应的待办事项
     * @param workItem
     * @param oldWorkItem
     */
    void updateWorkItemStatus(WorkItem workItem, WorkItem oldWorkItem) {
        // 1. 判断子事项是否全部解决完成
        String status = workItem.getWorkStatusNode().getStatus();
        String id = workItem.getId();
        if(status.equals("DONE")){
            // 若更新到完成，设置事项实际完成时间
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = formater.format(new Date());
            workItem.setActualEndTime(format);

            // 更新todoTask到完成
            TaskQuery taskQuery = new TaskQuery();
            LinkedHashMap data = new LinkedHashMap();
            data.put("workItemId", id);
            taskQuery.setData(data);
            taskQuery.setBgroup("kanass");
            try {
                Pagination<Task> taskPage = taskService.findTaskPage(taskQuery);
                for (Task task : taskPage.getDataList()) {
                    task.setStatus(3);
                    taskService.updateTask(task);
                }

            } catch (Exception e){
                throw new ApplicationException(e.getMessage());
            }

        }
        if(status.equals("PROGRESS")){
            // 更新todotask状态
            TaskQuery taskQuery = new TaskQuery();
            LinkedHashMap data = new LinkedHashMap();
            data.put("workItemId", id);
            taskQuery.setData(data);
            taskQuery.setBgroup("kanass");
            try {
                Pagination<Task> taskPage = taskService.findTaskPage(taskQuery);
                for (Task task : taskPage.getDataList()) {
                    task.setStatus(2);
                    taskService.updateTask(task);
                }

            } catch (Exception e){
                throw new ApplicationException(e.getMessage());
            }

        }
        // 记录更新动态
        HashMap<String, Object> logContent = new HashMap<>();
        logContent.put("oldValue", oldWorkItem.getWorkStatusNode());

        // 查找状态对应的项目状态
        StateNode workStatusNode = workItem.getWorkStatusNode();
        StateNodeFlowQuery stateNodeFlowQuery = new StateNodeFlowQuery();
        stateNodeFlowQuery.setNodeId(workStatusNode.getId());
        stateNodeFlowQuery.setFlowId(workItem.getFlowId());
        List<StateNodeFlow> stateNodeFlowList = stateNodeflowService.findStateNodeFlowList(stateNodeFlowQuery);
        StateNodeFlow stateNodeFlow = stateNodeFlowList.get(0);
        // 设置节点对应的状态, 状态code
        workItem.setWorkStatus(stateNodeFlow);
        workItem.setWorkStatusCode(stateNodeFlow.getNodeStatus());
        String nodeStatus = stateNodeFlow.getNodeStatus();
        // 设置事项结束时间
        if(nodeStatus.equals("DONE")){
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = formater.format(new Date());
            workItem.setActualEndTime(format);
        }
        WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
        workItemDao.updateWorkItem(workItemEntity);

        WorkItem newWorkItem = findWorkItem(id);
        setFlowRelation(newWorkItem);
        logContent.put("newValue", newWorkItem.getWorkStatusNode());
        creatUpdateOplog(newWorkItem, logContent, "KANASS_LOGTYPE_WORKUPDATESTATUS");
    }

    /**
     * 根据流程规则处理更新完流程之后的后置事项
     * @param workItem
     * @param transitionId
     */
    void updateByTransitionRule(WorkItem workItem, WorkItem oldWorkItem, String transitionId){
        String id = workItem.getId();
        TransitionRuleQuery transitionRuleQuery = new TransitionRuleQuery();
        transitionRuleQuery.setTransitionId(transitionId);
        List<TransitionRule> transitionRuleList = transitionRuleService.findTransitionRuleList(transitionRuleQuery);
        if(transitionRuleList.size() > 0){
            List<TransitionRule> distributionWork = transitionRuleList.stream().filter(item -> item.getRuleType().equals("distributionWork")).
                    collect(Collectors.toList());
            if(distributionWork.size() > 0){
                TransitionRule transitionRule = distributionWork.get(0);
                List<User> transitionRuleUser = businessRoleService.findDistributionUser(transitionRule, id);
                if(transitionRuleUser.size() > 0){
                    User user = transitionRuleUser.get(0);
                    workItem.setAssigner(user);
                    WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
                    workItemDao.updateWorkItem(workItemEntity);
                    // 发送消息和待办事项
                    WorkItem newWorkItem = findWorkItem(id);
//                    creatTodoTask(oldWorkItem, user);
                    newWorkItem.setUpdateField("assigner");
                    updateTodoTaskData(newWorkItem);
                    sendMessageForUpdateAssigner(oldWorkItem, user);
                }
            }
        }

    }


    void updateFlowRelation(WorkItem workItem){
        StateNodeRelation stateNodeRelation = new StateNodeRelation();
        StateNodeRelationQuery stateNodeRelationQuery = new StateNodeRelationQuery();
        stateNodeRelationQuery.setWorkId(workItem.getId());

        List<StateNodeRelation> stateNodeRelationList = stateNodeRelationService.findStateNodeRelationList(stateNodeRelationQuery);
        if(stateNodeRelationList.size() > 0){
            String id = stateNodeRelationList.get(0).getId();
            stateNodeRelation.setId(id);
            stateNodeRelation.setWorkName(workItem.getTitle());
            stateNodeRelationService.updateStateNodeRelation(stateNodeRelation);
        }
    }

    /**
     * 设置事项与流程的关联关系
     * @param workItem
     */
    void setFlowRelation(WorkItem workItem){
        StateNodeRelation stateNodeRelation = new StateNodeRelation();
        StateNodeRelationQuery stateNodeRelationQuery = new StateNodeRelationQuery();
        stateNodeRelationQuery.setWorkId(workItem.getId());

        List<StateNodeRelation> stateNodeRelationList = stateNodeRelationService.findStateNodeRelationList(stateNodeRelationQuery);
        if(stateNodeRelationList.size() > 0){
            String id = stateNodeRelationList.get(0).getId();
            stateNodeRelation.setId(id);
            stateNodeRelation.setWorkName(workItem.getTitle());
            stateNodeRelation.setStateNodeId(workItem.getWorkStatus().getId());
            stateNodeRelation.setNodeId(workItem.getWorkStatusNode().getId());
            stateNodeRelation.setFlowId(workItem.getWorkType().getFlow().getId());
            stateNodeRelationService.updateStateNodeRelation(stateNodeRelation);
        }else {
            stateNodeRelation.setWorkId(workItem.getId());
            stateNodeRelation.setWorkName(workItem.getTitle());
            stateNodeRelation.setStateNodeId(workItem.getWorkStatus().getId());
            stateNodeRelation.setNodeId(workItem.getWorkStatusNode().getId());
            stateNodeRelation.setProjectId(workItem.getProject().getId());
            stateNodeRelation.setFlowId(workItem.getWorkType().getFlow().getId());
            stateNodeRelationService.createStateNodeRelation(stateNodeRelation);
        }

    }


    /**
     * 删除事项及其子事项的关联数据
     * @param id
     */
    @Override
    public void deleteWorkItemAndChildren(@NotNull String id) {
        List<String> workItemAndChildren = workItemDao.findWorkItemAndChildrenIds(id);

        String[] workItemIds = workItemAndChildren.toArray(new String[workItemAndChildren.size()]);

        // 删除事项与流程的关联关系
        DmFlowQuery dmFlowQuery = new DmFlowQuery();
        dmFlowQuery.setDomainIds(workItemIds);
        dmFlowService.deleteDmFlowCondition(dmFlowQuery);

        // 删除关联事项
        WorkRelateQuery workRelateQuery = new WorkRelateQuery();
        workRelateQuery.setWorkItemIds(workItemIds);
        workRelateService.deleteWorkRelateCondition(workRelateQuery);

        // 删除日志
        WorkLogQuery workLogQuery = new WorkLogQuery();
        workLogQuery.setWorkItemIds(workItemIds);
        workLogService.deleteWorkLogList(workLogQuery);

        // 删除事项关联的文档
        WorkItemDocumentQuery workItemDocumentQuery = new WorkItemDocumentQuery();
        workItemDocumentQuery.setWorkItemIds(workItemIds);
        workItemDocumentService.deleteWorkItemDocumentList(workItemDocumentQuery);

        // 删除评论
        WorkCommentQuery workCommentQuery = new WorkCommentQuery();
        workCommentQuery.setWorkItemIds(workItemIds);
        workCommentService.deleteWorkCommentList(workCommentQuery);

        // 删除用例
        WorkTestCaseQuery workTestCaseQuery = new WorkTestCaseQuery();
        workTestCaseQuery.setWorkItemIds(workItemIds);
        workTestCaseService.deleteWorkTestCaseList(workTestCaseQuery);

        // 删除附件
        WorkAttachQuery workAttachQuery = new WorkAttachQuery();
        workAttachQuery.setWorkItemIds(workItemIds);
        workAttachService.deleteWorkAttachList(workAttachQuery);

        // 删除下级
        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setIds(workItemIds);
        deleteWorkItemCondition(workItemQuery);


        // 更新以当前事项以及下级事项为前置事项的事项
        workItemDao.updatePredepandWorkItemList(workItemAndChildren);

        // 删除与事项关联的迭代的关联关系
        WorkSprintQuery workSprintQuery = new WorkSprintQuery();
        workSprintQuery.setWorkItemIds(workItemIds);
        workSprintService.deleteWorkSprint(workSprintQuery);

        // 删除事项与版本关联的关系关系
        WorkVersionQuery workVersionQuery = new WorkVersionQuery();
        workVersionQuery.setWorkItemIds(workItemIds);
        workVersionService.deleteWorkVersionList(workVersionQuery);

        try {
            // 删除事项产生的待办
            for (String workItemAndChild : workItemAndChildren) {
                TaskQuery taskQuery = new TaskQuery();
                LinkedHashMap data = new LinkedHashMap();
                data.put("workItemId", workItemAndChild);
                taskQuery.setData(data);
                taskQuery.setBgroup("kanass");
                taskService.deleteAllTask(taskQuery);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 只删除事项及关联数据
     * @param id
     */
    @Override
    public void deleteWorkItem(@NotNull String id) {
        boolean haveChildren = haveChildren(id);
        if(haveChildren){
            deleteWorkItemAndChildren(id);
        }else {
            deleteCurrentWorkItem(id);
        }
    }

    public void deleteCurrentWorkItem(@NotNull String id) {
        // 删除事项与流程的关联关系
        DmFlowQuery dmFlowQuery = new DmFlowQuery();
        dmFlowQuery.setDomainId(id);
        dmFlowService.deleteDmFlowCondition(dmFlowQuery);

        // 删除关联事项
        WorkRelateQuery workRelateQuery = new WorkRelateQuery();
        workRelateQuery.setWorkItemId(id);
        workRelateService.deleteWorkRelateCondition(workRelateQuery);

        // 删除日志
        WorkLogQuery workLogQuery = new WorkLogQuery();
        workLogQuery.setWorkItemId(id);
        workLogService.deleteWorkLogList(workLogQuery);

        // 删除事项关联的文档
        WorkItemDocumentQuery workItemDocumentQuery = new WorkItemDocumentQuery();
        workItemDocumentQuery.setWorkItemId(id);
        workItemDocumentService.deleteWorkItemDocumentList(workItemDocumentQuery);

        // 删除评论
        WorkCommentQuery workCommentQuery = new WorkCommentQuery();
        workCommentQuery.setWorkItemId(id);
        workCommentService.deleteWorkCommentList(workCommentQuery);

        // 删除用例
        WorkTestCaseQuery workTestCaseQuery = new WorkTestCaseQuery();
        workTestCaseQuery.setWorkItemId(id);
        workTestCaseService.deleteWorkTestCaseList(workTestCaseQuery);

        // 删除附件
        WorkAttachQuery workAttachQuery = new WorkAttachQuery();
        workAttachQuery.setWorkItemId(id);
        workAttachService.deleteWorkAttachList(workAttachQuery);

        // 删除下级
        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setId(id);
        deleteWorkItemCondition(workItemQuery);


        // 更新以当前事项以及下级事项为前置事项的事项
        workItemDao.updatePredepandWorkItem(id);

        // 删除与事项关联的迭代的关联关系
        WorkSprintQuery workSprintQuery = new WorkSprintQuery();
        workSprintQuery.setWorkItemId(id);
        workSprintService.deleteWorkSprint(workSprintQuery);

        // 删除事项与版本关联的关系关系
        WorkVersionQuery workVersionQuery = new WorkVersionQuery();
        workVersionQuery.setWorkItemId(id);
        workVersionService.deleteWorkVersionList(workVersionQuery);

        // 处理下级的treepath
        List<WorkItemEntity> workItemAndChildren = workItemDao.findWorkItemAndChildren(id);
        for (WorkItemEntity workItemAndChild : workItemAndChildren) {
            if(workItemAndChild.getParentId().equals(id)){
                workItemAndChild.setParentId("nullstring");
                workItemAndChild.setTreePath("nullstring");
            }else {
                String treePath = workItemAndChild.getTreePath();
                if(treePath != null){
                    int index = treePath.indexOf(id);
                    treePath = treePath.substring(index);
                    workItemAndChild.setTreePath(treePath);
                }
            }
            workItemDao.updateWorkItem(workItemAndChild);
        }


        // 删除产生的待办
        TaskQuery taskQuery = new TaskQuery();
        LinkedHashMap data = new LinkedHashMap();
        data.put("workItemId", id);
        taskQuery.setData(data);
        taskQuery.setBgroup("kanass");
        taskService.deleteAllTask(taskQuery);
    }

    public void deleteWorkItemCondition(WorkItemQuery workItemQuery) {
        DeleteBuilders deleteBuilders = DeleteBuilders.createDelete(WorkItemEntity.class)
                .eq("id", workItemQuery.getId());

        if(workItemQuery.getIds() != null && workItemQuery.getIds().length != 0){
            deleteBuilders.in("id", workItemQuery.getIds());
        }

        if(workItemQuery.getStageIds() != null && workItemQuery.getStageIds().length != 0){
            deleteBuilders.in("stageId", workItemQuery.getStageIds());
        }

        DeleteCondition deleteCondition = deleteBuilders.get();
        workItemDao.deleteWorkItemList(deleteCondition);
    }
    @Override
    public WorkItem findOne(String id) {
        WorkItemEntity workItemEntity = workItemDao.findWorkItem(id);

        return BeanMapper.map(workItemEntity, WorkItem.class);
    }

    @Override
    public List<WorkItem> findList(List<String> idList) {
        List<WorkItemEntity> workItemEntityList =  workItemDao.findWorkItemList(idList);

        List<WorkItem> workItemList =  BeanMapper.mapList(workItemEntityList,WorkItem.class);
        return workItemList;
    }

    @Override
    public WorkItem findWorkItem(@NotNull String id) {
        WorkItem workItem = findOne(id);

        joinTemplate.joinQuery(workItem);

        return workItem;
    }


    /**
     * 获取事项详情和事项关联过得版本与迭代的列表
     * @param id
     * @return
     */
    @Override
    public WorkItem findWorkItemAndSprintVersion(@NotNull String id) {
        WorkItem workItem = findWorkItem(id);

        String projectId = workItem.getProject().getId();
        Project project = projectService.findProject(projectId);
        workItem.setProject(project);

        List<Sprint> workSprintList = sprintService.findWorkSprintList(id);
        workItem.setSprintList(workSprintList);

        List<ProjectVersion> workVersionList = projectVersionService.findWorkVersionList(id);
        workItem.setProjectVersionList(workVersionList);

        Integer workItemUsedTime = workLogService.findWorkItemUsedTime(id);
        if(workItemUsedTime != null){
            workItem.setUsedTime(workItemUsedTime);
        }else {
            workItem.setUsedTime(0);
        }

        return workItem;
    }

    /**
     * 根据事项ID查找事项和事项所用时间
     * @param id
     * @return
     */
    @Override
    public WorkItem findWorkItemAndUsedTime(@NotNull String id) {
        WorkItem workItem = findWorkItem(id);

        Integer workItemUsedTime = workLogService.findWorkItemUsedTime(id);
        if(workItemUsedTime != null){
            workItem.setUsedTime(workItemUsedTime);
        }else {
            workItem.setUsedTime(0);
        }
        return workItem;
    }


    @Override
    public List<WorkItem> findAllWorkItem() {
        List<WorkItemEntity> workItemEntityList =  workItemDao.findAllWorkItem();

        List<WorkItem> workItemList =  BeanMapper.mapList(workItemEntityList,WorkItem.class);

        joinTemplate.joinQuery(workItemList);

        return workItemList;
    }

    @Override
    public List<WorkItem> findWorkItemList(WorkItemQuery workItemQuery) {
        List<WorkItemEntity> workItemEntityList = workItemDao.findWorkItemList(workItemQuery);

        List<WorkItem> workItemList = BeanMapper.mapList(workItemEntityList,WorkItem.class);

        joinTemplate.joinQuery(workItemList);
        return workItemList;
    }

    @Override
    public Integer findWorkItemListCount(WorkItemQuery workItemQuery) {
        workItemQuery.setStatisticsNum(true);
        Integer size = workItemDao.findWorkItemListCount(workItemQuery);
        return size;
    }

    @Override
    public Pagination<WorkItem> findEpicSelectWorkItemList(WorkItemQuery workItemQuery) {
        //查询所有事项
        Pagination<WorkItemEntity> pagination = workItemDao.findEpicSelectWorkItemList(workItemQuery);

        List<WorkItem> workItemList = BeanMapper.mapList(pagination.getDataList(),WorkItem.class);

        joinTemplate.joinQuery(workItemList);

        return PaginationBuilder.build(pagination,workItemList);
    }

    /**
     * 根据查询对象查找可被添加的子事项列表
     * @param workItemQuery
     * @return
     */
    @Override
    public Pagination<WorkItem> findSelectChildrenWorkItemList(WorkItemQuery workItemQuery) {
        Pagination<WorkItemEntity> pagination = workItemDao.findSelectChildrenWorkItemList(workItemQuery);

        List<WorkItem> workItemList = BeanMapper.mapList(pagination.getDataList(),WorkItem.class);

        joinTemplate.joinQuery(workItemList);

        return PaginationBuilder.build(pagination,workItemList);
    }

    /**
     * 查找事项列表，平铺
     * @param workItemQuery
     * @return
     */
    @Override
    public List<WorkItem> findConditionWorkItemList(WorkItemQuery workItemQuery) {
        List<WorkItemEntity> workItemEntityList = workItemDao.findConditionWorkItemList(workItemQuery);

        List<WorkItem> workItemList = BeanMapper.mapList(workItemEntityList,WorkItem.class);

        joinTemplate.joinQuery(workItemList);
        return workItemList;
    }

    @Override
    public List<WorkItem> findWorkItemList(WorkItemQuery workItemQuery, boolean isJoinQuery) {
        List<WorkItemEntity> workItemEntityList = workItemDao.findWorkItemList(workItemQuery);

        List<WorkItem> workItemList = BeanMapper.mapList(workItemEntityList,WorkItem.class);

        if(isJoinQuery){
            joinTemplate.joinQuery(workItemList);
        }
        return workItemList;
    }

    @Override
    public Pagination<WorkItem> findWorkItemPage(WorkItemQuery workItemQuery) {

        Pagination<WorkItemEntity> pagination = workItemDao.findWorkItemPage(workItemQuery);

        List<WorkItem> workItemList = BeanMapper.mapList(pagination.getDataList(),WorkItem.class);

        joinTemplate.joinQuery(workItemList);

        return PaginationBuilder.build(pagination,workItemList);
    }

    /**
     * 安装分页查找事项列表，平铺
     * @param workItemQuery
     * @return
     */
    @Override
    public Pagination<WorkItem> findConditionWorkItemPage(WorkItemQuery workItemQuery) {
        Pagination<WorkItemEntity> pagination = workItemDao.findConditionWorkItemPage(workItemQuery);

        List<WorkItem> workItemList = BeanMapper.mapList(pagination.getDataList(),WorkItem.class);

        joinTemplate.joinQuery(workItemList);

        return PaginationBuilder.build(pagination,workItemList);
    }


    //按条件查找一级节点以及子事项
    @Override
    public List<WorkItem> findWorkItemListTree(WorkItemQuery workItemQuery) {
        //查找事项列表
        workItemQuery.setParentIdIsNull(true);
        List<WorkItem> workItemList = findWorkItemList(workItemQuery);
        if(workItemList == null || workItemList.size() == 0){
            return workItemList;
        }

        //查询子事项列表
        List<WorkItem> childWorkItemList = findChildWorkItemList(workItemList);
        //设置子事项
        if(childWorkItemList != null && childWorkItemList.size() > 0){
            for(WorkItem topWorkItem:workItemList){
                setChildren(childWorkItemList,topWorkItem);
            }
        }
        return workItemList;
    }

    /**
     * 按条件分页查询列表树, 先查找符合条件事项,
     * 再查找出所有的根节点，在根据根节点查找符合条件的事项
     * @param workItemQuery
     * @return
     */
    @Override
    public Pagination<WorkItem> findWorkItemPageTreeByQuery(WorkItemQuery workItemQuery) {
        // 1. 按照查找条件分页查找最顶级的事项
        long aTime1 = System.currentTimeMillis();
        Pagination<WorkItemEntity> topWorkItemPageEntity = workItemDao.findTopWorkItemIds(workItemQuery);
        long bTime1 = System.currentTimeMillis();

        logger.info("joinQuery cost time1:{}",bTime1-aTime1);

        List<WorkItem> topWorkItemList = BeanMapper.mapList(topWorkItemPageEntity.getDataList(), WorkItem.class);
        long aTime = System.currentTimeMillis();

        joinTemplate.joinQuery(topWorkItemList,  new String[]{"project","assigner", "workPriority", "workStatusNode", "workTypeSys", "builder", "module"});
        long bTime = System.currentTimeMillis();

        logger.info("joinQuery cost time2:{}",bTime-aTime);
        Pagination<WorkItem> topWorkItemPageList = PaginationBuilder.build(topWorkItemPageEntity, topWorkItemList);

        if(topWorkItemPageEntity.getDataList() == null || topWorkItemPageEntity.getDataList().size() == 0){
            //去重,查找出所有符合条件和构建树需要的事项
            return topWorkItemPageList;
        }

        // 按条件查找根事项下的符合条件的所有事项
        List<String> topWorkItemIds = topWorkItemList.stream().map(workItem -> workItem.getId()).collect(Collectors.toList());
        workItemQuery.setRootIds(topWorkItemIds);
        workItemQuery.setParentIdIsNull(false);

        long aTime3 = System.currentTimeMillis();
        if(workItemQuery.getEpicView() != null && workItemQuery.getEpicView() == true){
            workItemQuery.setWorkTypeId(null);
            workItemQuery.setWorkTypeCode(null);
        }

        List<WorkItemEntity> topChildWorkItemEntity = workItemDao.findTopChildWorkItem(workItemQuery);
        long bTime3 = System.currentTimeMillis();

        logger.info("joinQuery cost time3:{}",bTime3-aTime3);

        if(topChildWorkItemEntity == null || topChildWorkItemEntity.size() == 0){
            return topWorkItemPageList;
        }else {
            List<WorkItem> workItemList = BeanMapper.mapList(topChildWorkItemEntity,WorkItem.class);
            long cTime = System.currentTimeMillis();
            joinTemplate.joinQuery(workItemList, new String[]{"project", "assigner", "workPriority", "workStatusNode", "workTypeSys", "builder", "module"});
//            joinTemplate.joinQuery(workItemList);
            long eTime = System.currentTimeMillis();
            logger.info("joinQuery cost time4:{}",eTime-aTime1);


            // 去重
            workItemList = workItemList.stream().collect(
                    collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getId()))),
                            ArrayList::new));

            List<WorkItem> allWorkItem = new ArrayList<>();
            setRootWorkItemList(workItemList, allWorkItem, topWorkItemList);
            topWorkItemPageList.setDataList(allWorkItem);
            return topWorkItemPageList;
        }

    }

    /**
     * 找寻根节点
     * @param workItemList
     * @param workItemTree
     */
    void setRootWorkItemList(List<WorkItem> workItemList, List<WorkItem> workItemTree,List<WorkItem> topWorkItemList){
        //找到根节点
        List<WorkItem> rootWorkItemList = workItemList.stream().filter(item -> (StringUtils.isEmpty(item.getParentId()))).collect(Collectors.toList());
        workItemList.removeAll(rootWorkItemList);
        for(WorkItem rootWorkItem:topWorkItemList){
            setChildrenWorkItemList(rootWorkItem,workItemList);
            workItemTree.add(rootWorkItem);
        }
    }

    /**
     * 根据根节点递归查找下级事项
     * @param rootWorkItem
     * @param workItemList
     */
    void setChildrenWorkItemList(WorkItem rootWorkItem,List<WorkItem> workItemList){
        List<WorkItem> childrenWorkItemList = workItemList.stream().filter(item -> (item.getParentId().equals(rootWorkItem.getId()))).collect(Collectors.toList());
        if(childrenWorkItemList.size() > 0){
            rootWorkItem.setChildren(childrenWorkItemList);
            workItemList.removeAll(childrenWorkItemList);
            for(WorkItem childrenWorkItem:childrenWorkItemList){
                setChildrenWorkItemList(childrenWorkItem,workItemList);
            }
        }

    }

    /**
     * 从根节点向下构建事项树
     * @param workItemQuery
     * @return
     */
    @Override
    public Pagination<WorkItem> findWorkItemPageTree(WorkItemQuery workItemQuery) {
        //按分页查找列表
        Pagination<WorkItem> pg = findConditionWorkItemPage(workItemQuery);
        List<WorkItem> topWorkItemList = pg.getDataList();

        if(topWorkItemList == null || topWorkItemList.size() == 0){
            return pg;
        }
        //查询所有子事项列表
        List<WorkItem> childWorkItemList = findChildWorkItemList(topWorkItemList);
        //设置子事项
        if(childWorkItemList != null && childWorkItemList.size() > 0){
            for(WorkItem topWorkItem:topWorkItemList){
                setChildren(childWorkItemList,topWorkItem);
            }
        }
        return pg;
    }

    /**
     * 根据上级事项列表查询所有子事项列表
     * @param workItemList
     * @return
     */
    @Override
    public List<WorkItem> findChildWorkItemList(List<WorkItem> workItemList){
        //拼接ids查询范围
        List<String> idList = new ArrayList<>();
        for(WorkItem workItem:workItemList){
            idList.add(workItem.getId());
        }
        String[] ids = new String[idList.size()];
        ids = idList.toArray(ids);

        //设置查询条件
        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setParentIdIn(ids);

        List<WorkItem> childWorkItemList = findWorkItemList(workItemQuery,true);
        return childWorkItemList;
    }

    /**
     * 查找并设置子事项列表
     * @param matchWorkItemList
     * @param parentWorkItem
     * @return
     */
    @Override
    public List<WorkItem> setChildren(List<WorkItem> matchWorkItemList, WorkItem parentWorkItem){
        List<WorkItem> childList = matchWorkItemList.stream()
                .filter(workItem -> (workItem.getParentId() != null  && workItem.getParentId().equals(parentWorkItem.getId())))
                .collect(Collectors.toList());

        if(childList != null && childList.size()>0){
            //查询所有子事项列表
            List<WorkItem> childWorkItemList = findChildWorkItemList(childList);
            for(WorkItem child:childList){
                setChildren(childWorkItemList,child);
            }
            parentWorkItem.setChildren(childList);
        }

        return childList;
    }

    /**
     * 根据成员分组查找看板结构的事项列表，暂时没用
     * @param workItemQuery
     * @return
     */
    @Override
    public List<WorkUserGroupBoard> findWorkUserGroupBoardList(WorkItemQuery workItemQuery) {
        ArrayList<WorkUserGroupBoard> workUserGroupBoardArrayList = new ArrayList<>();

        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setDomainId(workItemQuery.getProjectIds().get(0));
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);

        if(dmUserList != null){
            for(DmUser dmUser:dmUserList){
                WorkUserGroupBoard workUserGroupBoard = new WorkUserGroupBoard();
                String useId = dmUser.getUser().getId();
                User user = userService.findUser(useId);
                workUserGroupBoard.setUser(user);

                List<String> list = new ArrayList<String>();
                list.add(useId);
                workItemQuery.setAssignerIds(list);

                List<WorkBoard> workBoardList = findWorkBoardList(workItemQuery);
                workUserGroupBoard.setWorkBoardList(workBoardList);

                workUserGroupBoardArrayList.add(workUserGroupBoard);
            }
        }
        return workUserGroupBoardArrayList;
    }

    /**
     * 查找看板状态下的事项列表
     * @param workItemQuery
     * @return
     */
    @Override
    public List<WorkBoard> findWorkBoardList(WorkItemQuery workItemQuery) {
        List<WorkBoard> workBoardList = new ArrayList<>();
        String projectId = workItemQuery.getProjectId();
        DmFlowQuery dmFlowQuery = new DmFlowQuery();
        if(!StringUtils.isEmpty(projectId) ){
            dmFlowQuery.setDomainId(projectId);
        }
        if(!ObjectUtils.isEmpty(workItemQuery.getProjectIds())){
            List<String> projectIds = workItemQuery.getProjectIds();
            String[] projectIdsString = projectIds.toArray(new String[projectIds.size()]);
            dmFlowQuery.setDomainIds(projectIdsString);
        }
        //查找项目下所有的流程
        List<DmFlow> dmFlowList = dmFlowService.findDmFlowList(dmFlowQuery);
        List<StateNodeFlow> stateNodeFlows = new ArrayList<>();

        //循环流程查找所有的节点
        for (DmFlow dmFlow : dmFlowList) {
            Flow flow = dmFlow.getFlow();
            StateNodeFlowQuery stateNodeFlowQuery = new StateNodeFlowQuery();
            stateNodeFlowQuery.setFlowId(flow.getId());
            List<StateNodeFlow> stateNodeFlowList = stateNodeflowService.findStateNodeFlowList(stateNodeFlowQuery);
            stateNodeFlows.addAll(stateNodeFlowList);
        }

        List<StateNodeFlow> stateNodeFlowList = stateNodeFlows.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(stateNodeFlow-> stateNodeFlow.getNode().getId()))), ArrayList::new)
        );

        //查找事项看板
        if(stateNodeFlowList != null){
            //循环节点，查找状态的所有事项
            for(StateNodeFlow stateFlowNode:stateNodeFlowList){
                if(!stateFlowNode.getNodeStatus().equals("START")){
                    List<String> list = new ArrayList<String>();
                    list.add(stateFlowNode.getId());
                    WorkBoard workBoard = new WorkBoard();

                    StateNode stateNode = stateNodeService.findStateNode(stateFlowNode.getNode().getId());
                    workBoard.setState(stateNode);

                    workItemQuery.setWorkStatusId(stateFlowNode.getNode().getId());
                    Pagination<WorkItem> conditionWorkItemList = this.findConditionWorkItemPage(workItemQuery);
                    workBoard.setWorkItemList(conditionWorkItemList);
                    if(workBoard != null){
                        workBoardList.add(workBoard);
                    }
                }
            }
        }
        return workBoardList;
    }

    /**
     * 看板分页
     * @param workItemQuery
     * @return
     */
    @Override
    public WorkBoard findChangePageWorkBoardList(WorkItemQuery workItemQuery) {
        WorkBoard workBoard = new WorkBoard();

        StateNode stateNode = stateNodeService.findStateNode(workItemQuery.getWorkStatusId());
        workBoard.setState(stateNode);
        workItemQuery.setWorkStatusId(workItemQuery.getWorkStatusId());
        Pagination<WorkItem> conditionWorkItemList = this.findConditionWorkItemPage(workItemQuery);
        workBoard.setWorkItemList(conditionWorkItemList);

        return workBoard;
    }

    @Override
    public Pagination<WorkItem> findUnEpicWorkItemPage(WorkItemQuery workItemQuery) {
        //查询所有事项
        Pagination<WorkItemEntity>  pagination = workItemDao.findUnEpicWorkItemPage(workItemQuery);

        List<WorkItem> workItemList = BeanMapper.mapList(pagination.getDataList(),WorkItem.class);

        joinTemplate.joinQuery(workItemList);

        return PaginationBuilder.build(pagination,workItemList);
    }

    /**
     * 计划中使用，弃用
     * @param workItemQuery@return
     * @return
     */
    @Override
    public Pagination<WorkItem> findUnPlanWorkItemPage(WorkItemQuery workItemQuery) {
        Pagination<WorkItemEntity>  pagination = workItemDao.findUnPlanWorkItemPage(workItemQuery);

        List<WorkItem> workItemList = BeanMapper.mapList(pagination.getDataList(),WorkItem.class);

        joinTemplate.joinQuery(workItemList);

        return PaginationBuilder.build(pagination,workItemList);
    }

    /**
     * 弃用
     * @param workItemQuery
     * @return
     */
    public List<WorkItem> findPlanWorkItemPage(WorkItemQuery workItemQuery) {
        List<WorkItemEntity> workItemEntityList= workItemDao.findPlanWorkItemPage(workItemQuery);

        List<WorkItem> workItemList = BeanMapper.mapList(workItemEntityList,WorkItem.class);

        joinTemplate.joinQuery(workItemList);

        return workItemList;
    }

    /**
     * 根据标题关键字搜索事项
     * @param workItemQuery
     * @return
     */
    @Override
    public Pagination<WorkItem> findWorkItemByKeyWorks(WorkItemQuery workItemQuery) {
        Pagination<WorkItemEntity> pagination = workItemDao.findWorkItemByKeyWorks(workItemQuery);

        List<WorkItem> workItemList = BeanMapper.mapList(pagination.getDataList(),WorkItem.class);

        joinTemplate.joinQuery(workItemList,  new String[]{"project","assigner", "workPriority", "workStatusNode", "workTypeSys"});
        return PaginationBuilder.build(pagination,workItemList);
    }

    /**
     * 查找各个事项类型下事项的数量，只查第一级
     * @param workItemQuery
     * @return
     */
    @Override
    public HashMap<String, Integer> findWorkItemNumByWorkType(WorkItemQuery workItemQuery) {
        HashMap<String, Integer> workItemNumByWorkType = workItemDao.findWorkItemNumByWorkType(workItemQuery);
        return workItemNumByWorkType;
    }

    /**
     * 查找各个事项类型下事项的数量，差所有的
     * @param workItemQuery
     * @return
     */
    @Override
    public HashMap<String, Integer> findWorkItemListNumByWorkType(WorkItemQuery workItemQuery) {
        HashMap<String, Integer> workItemNumByWorkType = workItemDao.findWorkItemListNumByWorkType(workItemQuery);
        return workItemNumByWorkType;
    }

    /**
     * 查找各个状态下事项的个数
     * @param workItemQuery
     * @return
     */
    @Override
    public HashMap<String, Integer> findWorkItemNumByWorkStatus(WorkItemQuery workItemQuery) {
        HashMap<String, Integer> workItemNumByWorkType = workItemDao.findWorkItemNumByWorkStatus(workItemQuery);
        return workItemNumByWorkType;
    }

    /**
     * 查找我的待办，已完成，已逾期，进行中的事项个数
     * @param workItemQuery
     * @return
     */
    public HashMap<String, Integer> findWorkItemNumByQuickSearch(WorkItemQuery workItemQuery) {
        HashMap<String, Integer> workItemNumByQuickSearch = workItemDao.findWorkItemNumByQuickSearch(workItemQuery);

        return  workItemNumByQuickSearch;
    }

    /**
     * 查找能被设置为上级的事项列表
     * @param workItemQuery
     * @return
     */
    @Override
    public Pagination<WorkItem> findCanBeRelationParentWorkItemList(WorkItemQuery workItemQuery) {
        Pagination<WorkItemEntity> pagination = workItemDao.findCanBeRelationParentWorkItemList(workItemQuery);
        List<WorkItem> workItemList = BeanMapper.mapList(pagination.getDataList(),WorkItem.class);

        joinTemplate.joinQuery(workItemList,  new String[]{"assigner", "workPriority", "workStatusNode", "workTypeSys"});
        return PaginationBuilder.build(pagination,workItemList);
    }

    /**
     * 查找能被设置为前置事项的事项列表
     * @param workItemQuery
     * @return
     */
    @Override
    public Pagination<WorkItem> findCanBeRelationPerWorkItemList(WorkItemQuery workItemQuery) {
        Pagination<WorkItemEntity> pagination = workItemDao.findCanBeRelationPreWorkItemList(workItemQuery);
        List<WorkItem> workItemList = BeanMapper.mapList(pagination.getDataList(),WorkItem.class);

        joinTemplate.joinQuery(workItemList,  new String[]{"assigner", "workPriority", "workStatusNode", "workTypeSys"});
        return PaginationBuilder.build(pagination,workItemList);
    }


    /**
     * 查找事项关联的各个模型的格式，子事项，子需求，关联事项，评论，动态
     * 文档这些的个数，用于事项详情页展示
     * @param workItemId
     * @param workTypeCode
     * @return
     */
    @Override
    public HashMap<String, Integer> findWorkItemRelationModelCount(String workItemId, String workTypeCode) {
        // 查找关联事项个数
        HashMap<String, Integer> workItemRelationModelCount = workItemDao.findWorkItemRelationModelCount(workItemId, workTypeCode);

        LoggingQuery loggingQuery = new LoggingQuery();
        LinkedHashMap linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("workItemId", workItemId);
        loggingQuery.setData(linkedHashMap);
        loggingQuery.setBgroup("kanass");
        List<Logging> logList = loggingService.findLogList(loggingQuery);
        workItemRelationModelCount.put("dynamic", logList.size());

        return workItemRelationModelCount;
    }


    /**
     * 批量更新事项的迭代
     * @param oldSprintId
     * @param newSprintId
     */
    @Override
    public void updateBatchWorkItemSprint(String oldSprintId, String newSprintId) {
        workItemDao.updateBatchWorkItemSprint(oldSprintId, newSprintId);
    }

    /**
     * 批量更新事项的版本
     * @param oldVersionId
     * @param newVersionId
     */
    @Override
    public void updateBatchWorkItemVersion(String oldVersionId, String newVersionId) {
        workItemDao.updateBatchWorkItemVersion(oldVersionId, newVersionId);
    }

    /**
     * 根据迭代id查找没有完成的事项列表
     * @param sprintId
     * @return
     */
    @Override
    public List<WorkItem> findSprintWorkItemList(String sprintId) {
        List<WorkItemEntity> sprintWorkItemList = workItemDao.findSprintWorkItemList(sprintId);

        List<WorkItem> workItemList = BeanMapper.mapList(sprintWorkItemList,WorkItem.class);
        joinTemplate.joinQuery(workItemList);
        return workItemList;
    }

    /**
     * 根据版本id查找没有完成的事项列表
     * @param versionId
     * @return
     */
    @Override
    public List<WorkItem> findVersionWorkItemList(String versionId) {
        List<WorkItemEntity> versionWorkItemList = workItemDao.findVersionWorkItemList(versionId);
        List<WorkItem> workItemList = BeanMapper.mapList(versionWorkItemList,WorkItem.class);
        joinTemplate.joinQuery(workItemList);
        return workItemList;
    }

    /**
     * 查找迭代下事项的个数
     * @param sprintId
     * @return
     */
    @Override
    public HashMap<String, Integer> findSprintWorkItemNum(String sprintId) {
        HashMap<String, Integer> sprintWorkItemNum = workItemDao.findSprintWorkItemNum(sprintId);
        return sprintWorkItemNum;
    }

    /**
     * 查找版本下事项的个数
     * @param versionId
     * @return
     */
    @Override
    public HashMap<String, Integer> findVersionWorkItemNum(String versionId) {
        HashMap<String, Integer> versionWorkItemNum = workItemDao.findVersionWorkItemNum(versionId);
        return versionWorkItemNum;
    }

    /**
     * 查找当前事项有几层下级事项
     * @param id
     * @return
     */
    @Override
    public Integer findChildrenLevel(String id) {
        Integer childrenLevel = workItemDao.findChildrenLevel(id);
        return childrenLevel;
    }


    @Override
    public void updateEpicWork(String projectId, String workTypeId, String dmWorkTypeId){
       workItemDao.updateEpicWork(projectId, workTypeId, dmWorkTypeId);
    }

    /**
     * 查找事项以及下级事项
     * @param id
     * @return
     */
    public WorkItem findWorkItemAndChidren(String id){
        WorkItem workItem = findWorkItem(id);
        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setParentId(id);
        List<WorkItem> workItemList = findWorkItemList(workItemQuery);
        if(workItemList.size() > 0){
            workItem.setChildren(workItemList);
            for (WorkItem workItem1 : workItemList) {
                String id1 = workItem1.getId();
                workItemQuery.setParentId(id1);
                List<WorkItem> workItemList1 = findWorkItemList(workItemQuery);
                if(workItemList1.size() > 0){
                    workItem1.setChildren(workItemList1);
                }
            }
        }
        return  workItem;
    }

    /**
     * 是否有下级
     * @param id
     * @return
     */
    public boolean haveChildren(String id){
        boolean isHave = workItemDao.haveChildren(id);
        return isHave;
    }

    /**
     * 查找事项的下级事项id
     * @param workItemId
     * @return
     */
    @Override
   public List<String> findWorkItemAndChildrenIds(String workItemId){
       List<String> workItemAndChildrenIds = workItemDao.findWorkItemAndChildrenIds(workItemId);
       return  workItemAndChildrenIds;
   }
}