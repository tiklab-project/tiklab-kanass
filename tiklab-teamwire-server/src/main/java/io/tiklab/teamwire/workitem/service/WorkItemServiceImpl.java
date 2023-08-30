package io.tiklab.teamwire.workitem.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.flow.flow.model.*;
import io.tiklab.flow.flow.service.FlowWorkRelationService;
import io.tiklab.flow.statenode.model.*;
import io.tiklab.flow.statenode.service.StateNodeWorkRelationService;
import io.tiklab.flow.transition.dao.TransitionRuleDao;
import io.tiklab.flow.transition.entity.TransitionRuleEntity;
import io.tiklab.flow.transition.model.Transition;
import io.tiklab.flow.transition.model.TransitionRule;
import io.tiklab.flow.transition.model.TransitionRuleQuery;
import io.tiklab.flow.transition.service.TransitionRuleService;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.teamwire.project.project.service.ProjectService;
import io.tiklab.teamwire.workitem.model.*;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.exception.ApplicationException;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.flow.flow.service.DmFlowService;
import io.tiklab.flow.flow.service.FlowService;
import io.tiklab.flow.statenode.service.StateNodeFlowService;
import io.tiklab.flow.statenode.service.StateNodeService;
import io.tiklab.join.JoinTemplate;
import io.tiklab.message.message.model.Message;
import io.tiklab.message.message.model.MessageReceiver;
import io.tiklab.message.message.service.SingleSendMessageService;
import io.tiklab.message.setting.model.MessageType;
import io.tiklab.security.logging.model.Logging;
import io.tiklab.security.logging.model.LoggingTemplate;
import io.tiklab.security.logging.model.LoggingType;
import io.tiklab.security.logging.service.LoggingByTemplService;
import io.tiklab.teamwire.workitem.dao.WorkItemDao;
import io.tiklab.teamwire.workitem.entity.WorkItemEntity;
import io.tiklab.teamwire.workitem.support.MessageTemplateConstant;
import io.tiklab.teamwire.workitem.support.OpLogTemplateConstant;
import io.tiklab.teamwire.workitem.support.TodoTemplateConstant;
import io.tiklab.todotask.model.Task;
import io.tiklab.todotask.model.TaskType;
import io.tiklab.todotask.service.TaskByTempService;
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
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
* 事项服务
*/
@Exporter
@Service
public class WorkItemServiceImpl implements WorkItemService {

    private static Logger logger = LoggerFactory.getLogger(WorkItemServiceImpl.class);

    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    WorkItemDao workItemDao;

    @Autowired
    FlowWorkRelationService flowWorkRelationService;

    @Autowired
    StateNodeWorkRelationService stateNodeWorkRelationService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    WorkTypeDmService workTypeDmService;

    @Autowired
    StateNodeFlowService stateNodeflowService;

    @Autowired
    StateNodeService stateNodeService;

    @Autowired
    DmFlowService dmFlowService;

    @Autowired
    SingleSendMessageService singleSendMessageService;


    @Autowired
    ProjectService projectService;

    @Autowired
    UserService userService;

    @Autowired
    DmUserService dmUserService;

    @Autowired
    TransitionRuleService transitionRuleService;
    @Autowired
    LoggingByTemplService opLogByTemplService;

    @Autowired
    TaskByTempService taskByTempService;

    @Autowired
    FlowService flowService;

    @Value("${base.url:null}")
    String baseUrl;

    /**
     * 设置事项id
     */
    public String setWorkItemId(WorkItem workItem){
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
        workItem.setId(newId);
        return newId;
    }

    /**
     * 发送消息提醒
     * @param workItem
     */
    void sendMessageForCreate(WorkItem workItem){
//        Message message = new Message();
//        //设置模板ID
//        MessageTemplate messageTemplate = new MessageTemplate();
//        messageTemplate.setId(MessageTemplateConstant.TEAMWIRE_MESSAGETEMPLATE_TASKTODO);
//        message.setMessageTemplate(messageTemplate);
//        //设置发送数据
//        String data = JSON.toJSONString(workItem, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteDateUseDateFormat);
//        message.setData(data);
//        //设置接收人
//        List<MessageReceiver> messageReceiverList = new ArrayList<>();
//        MessageReceiver messageReceiver = new MessageReceiver();
//        messageReceiver.setReceiver(workItem.getAssigner().getId()); //去除message->user依賴 zhangzh
//        messageReceiverList.add(messageReceiver);
//        message.setMessageReceiverList(messageReceiverList);
//        message.setApplication("teamwire");
//        messageService.sendMessage(message);
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
        content.put("projectId", workItem.getProject().getId());
        content.put("receiverIcon",receiver.getName().substring(0, 1));
        content.put("receiver", receiver);

        if(workItem.getSprint() != null) {
            content.put("sprintId", workItem.getSprint().getId());
        }

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);

        content.put("createUser", user);
        content.put("createUserIcon",user.getName().substring( 0, 1));
        content.put("receiveTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        Message message = new Message();
        MessageType messageType = new MessageType();
        messageType.setId(MessageTemplateConstant.TEAMWIRE_MESSAGETYPE_TASKTODO);
        message.setMessageType(messageType);
        message.setMessageSendTypeId("site");
        message.setData(content);

        List<MessageReceiver> objects = new ArrayList<>();
        MessageReceiver messageReceiver = new MessageReceiver();
        messageReceiver.setUserId(receiver.getId());
        objects.add(messageReceiver);
        message.setMessageReceiverList(objects);
        message.setBaseUrl(baseUrl);

        singleSendMessageService.sendMessage(message);

    }


    /**
     * 创建待办
     * @param workItem
     * @param receiver
     */
    void creatTodoTask(WorkItem workItem, User receiver){
        Task task = new Task();
        task.setBgroup("teamwire");
        task.setTitle("待办事项");

        task.setAssignUser(receiver);
        task.setTemplateId(TodoTemplateConstant.TEAMWIRE_TODOTEMPLATE_WORKITEMTODO);

        TaskType taskType = new TaskType();
        taskType.setId(TodoTemplateConstant.TEAMWIRE_TODOTYPE_WORKITEMTODO);
        task.setTodoType(taskType);

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        task.setCreateUser(user);

        HashMap<String, Object> content = new HashMap<>();
        content.put("workItemTitle", workItem.getTitle());
        content.put("workItemId", workItem.getId());
        content.put("workTypeIcon", workItem.getWorkTypeSys().getIconUrl());
        content.put("projectId", workItem.getProject().getId());
        content.put("receiverIcon",receiver.getName().substring(0, 1));
        content.put("receiver", receiver);
        if(workItem.getSprint() != null) {
            content.put("sprintId", workItem.getSprint().getId());
        }
        content.put("createUser", user);
        content.put("createUserIcon",user.getName().substring( 0, 1));
        content.put("receiveTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        task.setContent(JSON.toJSONString(content));
        task.setBaseUrl(baseUrl);
        taskByTempService.createTask(task);
    }

    /**
     * 根据事项类型查找配置流程开始状态
     * @param workTypeId
     * @return
     */
    StateNodeFlow findStartState(WorkItem workItem, String workTypeId, String id){
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

        // 设置流程跟事项关联
        FlowWorkRelation flowWorkRelation = new FlowWorkRelation();
        flowWorkRelation.setWorkId(id);
        flowWorkRelation.setWorkName(workItem.getTitle());
        flowWorkRelation.setFlowId(flow.getId());
        flowWorkRelation.setFlowName(flow.getName());
        flowWorkRelation.setProjectId(workItem.getProject().getId());
        try {
            flowWorkRelationService.createFlowWorkRelation(flowWorkRelation);
        }catch (Exception e){
            throw new ApplicationException(e);
        }

        //设置节点跟事项关联
        StateNodeWorkRelation stateNodeWorkRelation = new StateNodeWorkRelation();
        stateNodeWorkRelation.setWorkId(id);
        stateNodeWorkRelation.setWorkName(workItem.getTitle());
        stateNodeWorkRelation.setStateNodeId(stateNode.getId());
        stateNodeWorkRelation.setNodeId(stateNode.getNode().getId());
        stateNodeWorkRelation.setProjectId(workItem.getProject().getId());
        try {
            stateNodeWorkRelationService.createStateNodeWorkRelation(stateNodeWorkRelation);
        }catch (Exception e){
            throw new ApplicationException(e);
        }

        return stateNode;
    }

    /**
     * 更新事项创建日志
     * @param logContent
     * @param workItem
     */
    void creatUpdateOplog( Map<String, Object> logContent, WorkItem workItem){

        Logging log = new Logging();
        log.setBgroup("teamwire");
        log.setModule("workItem");
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        log.setUser(user);

        logContent.put("workItemTitle", workItem.getTitle());
        logContent.put("workItemId", workItem.getId());
        logContent.put("workTypeIcon",workItem.getWorkType().getWorkType().getIconUrl());
        logContent.put("projectId", workItem.getProject().getId());
        logContent.put("master", user);
        logContent.put("receiveTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        logContent.put("createUserIcon",user.getName().substring( 0, 1));
        if(workItem.getSprint() != null) {
            logContent.put("sprintId", workItem.getSprint().getId());
        }


        LoggingType opLogType = new LoggingType();
        opLogType.setId(OpLogTemplateConstant.TEAMWIRE_LOGTYPE_WORKITEMUPDATE);
        log.setActionType(opLogType);

        LoggingTemplate opLogTemplate = new LoggingTemplate();
        Object updateField = logContent.get("updateField");
        log.setBaseUrl(baseUrl);
        if ("assigner".equals(updateField)) {
            log.setLoggingTemplateId(OpLogTemplateConstant.TEAMWIRE_LOGTEMPLATE_WORKITEMASSIGNER);
            log.setContent(JSON.toJSONString(logContent));

            opLogByTemplService.createLog(log);

        }

        if ("workStatusNode".equals(updateField)) {
            log.setLoggingTemplateId(OpLogTemplateConstant.TEAMWIRE_LOGTEMPLATE_WORKITEMSTATUS);
            log.setContent(JSON.toJSONString(logContent));
            opLogByTemplService.createLog(log);
        }

    }

    /**
     * 新增创建事项的动态
     * @param content
     */
    void creatWorkItemDynamic( Map<String, String> content){
        Logging log = new Logging();
        log.setBgroup("teamwire");

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        log.setUser(user);
        content.put("master", user.getName());
        content.put("createTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        LoggingTemplate opLogTemplate = new LoggingTemplate();
        opLogTemplate.setId(OpLogTemplateConstant.TEAMWIRE_LOGTEMPLATE_WORKITEMADD);
        log.setLoggingTemplateId(OpLogTemplateConstant.TEAMWIRE_LOGTEMPLATE_WORKITEMADD);

        LoggingType opLogType = new LoggingType();
        opLogType.setId(OpLogTemplateConstant.TEAMWIRE_LOGTYPE_WORKITEMADD);
        log.setActionType(opLogType);

        log.setModule("workItem");
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));
        content.put("createUserIcon",user.getName().substring( 0, 1));
        log.setContent(JSONObject.toJSONString(content));
        log.setBaseUrl(baseUrl);
        opLogByTemplService.createLog(log);
    }

    @Override
    public String createWorkItem(@NotNull @Valid WorkItem workItem) {
        // 设置id和序号
        String id = setWorkItemId(workItem);
        WorkTypeDm workType = workItem.getWorkType();
        String workTypeId = workType.getId();

        //查找并设置初始化状态
        StateNodeFlow startState = findStartState(workItem, workTypeId, id);

        //设置创建时间
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formater.format(new Date());
        workItem.setBuildTime(format);


        //设置事项类型code,关联的系统事项类型

        WorkTypeDm workTypeDm = workTypeDmService.findWorkTypeDm(workTypeId);
        workItem.setWorkTypeCode(workTypeDm.getWorkType().getCode());
        workItem.setWorkTypeSys(workTypeDm.getWorkType());

        //设置treePath,
        if(workItem.getParentWorkItem() != null){
            if(workItem.getParentWorkItem().getId() != null) {
                String treePath = workItem.getParentWorkItem().getId() + ";";
                WorkItem parentWorkItem = findWorkItem(workItem.getParentWorkItem().getId());
                if(parentWorkItem.getTreePath() != null){
                    treePath = treePath.concat(parentWorkItem.getTreePath());
                }
                String rootId = parentWorkItem.getRootId();
                workItem.setRootId(rootId);
                workItem.setTreePath(treePath);
            }
        }

        WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
        workItemDao.createWorkItem(workItemEntity);
        // 设置事项跟流程节点关联关系


        //如果更新父级，更新根节点
        if(workItem.getParentWorkItem() == null){
            WorkItem workItem1 = new WorkItem();
            workItem1.setRootId(id);
            workItem1.setId(id);
            WorkItemEntity workItemEntity1 = BeanMapper.map(workItem1, WorkItemEntity.class);
            workItemDao.updateWorkItem(workItemEntity1);
        }

        if(workItem.getParentWorkItem() != null){
            if(workItem.getParentWorkItem().getId() == null){
                WorkItem workItem1 = new WorkItem();
                workItem1.setRootId(id);
                workItem1.setId(id);
                WorkItemEntity workItemEntity1 = BeanMapper.map(workItem1, WorkItemEntity.class);
                workItemDao.updateWorkItem(workItemEntity1);
            }
        }
        WorkItem workItem1 = findWorkItem(id);

        Map<String, String> content = new HashMap<>();
        content.put("projectId", workItem1.getProject().getId());
        content.put("projectName", workItem1.getProject().getProjectName());
        content.put("workItemId", workItem1.getId());
        content.put("workItemTitle",  workItem1.getTitle());
        content.put("workItemTypeName",  workItem1.getWorkTypeSys().getName());
        content.put("workItemType", workItem1.getWorkType().getId());
        content.put("workItemIcon", workItem1.getWorkTypeSys().getIconUrl());

        creatWorkItemDynamic(content);

        //添加索引

        return id;
    }

    @Override
    public String createJiraWorkItem(@NotNull @Valid WorkItem workItem) {


        WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
        String workItemId = workItemDao.createWorkItem(workItemEntity);
        return workItemId;
    }
    @Override
    public void updateWorkItem(@NotNull WorkItem workItem) {
        String id = workItem.getId();
        String updateField = workItem.getUpdateField();
        WorkItem oldWorkItem = findWorkItem(id);
        //设置treePath,rootId
        if((workItem.getParentWorkItem() != null && workItem.getParentWorkItem().getId() != null ) &&
            !workItem.getParentWorkItem().getId().equals("nullstring")){

            String treePath = workItem.getParentWorkItem().getId() + ";";
            WorkItem parentWorkItem = findWorkItem(workItem.getParentWorkItem().getId());
            if(parentWorkItem.getTreePath() != null){
                treePath = treePath.concat(parentWorkItem.getTreePath());
            }
            workItem.setRootId(parentWorkItem.getRootId());
            workItem.setTreePath(treePath);
        }
        //更新事项状态
        if(updateField != null  &&
                updateField.equals("workStatusNode")){
            updateWorkItemStatus(workItem);
            updateByTransitionRule(workItem, oldWorkItem);
        }

        Object oldValue = getFieldValueByName(updateField, oldWorkItem);

        //待办事项, 日志信息记录
        HashMap<String, Object> content = new HashMap<>();
        HashMap<String, Object> logContent = new HashMap<>();
        content.put("updateField", updateField);
        logContent.put("updateField", updateField);
        logContent.put("oldValue", oldValue);

        //更新数据
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formater.format(new Date());
        workItem.setUpdateTime(format);
        WorkItemEntity workItemEntity = BeanMapper.map(workItem, WorkItemEntity.class);
        workItemDao.updateWorkItem(workItemEntity);

        //动态保存新数据
        WorkItem newWorkItem = findWorkItem(workItem.getId());
        Object newValue = getFieldValueByName(updateField, newWorkItem);
        logContent.put("newValue", newValue);

        // 若更新负责人发送待办、消息和更新日志
        if(updateField != null  && updateField.equals("assigner")){
            String assignerId = workItem.getAssigner().getId();
            User assigner = userService.findOne(assignerId);
            creatTodoTask(newWorkItem, assigner);
            sendMessageForUpdateAssigner(newWorkItem, assigner);

            if(ObjectUtils.isEmpty(oldValue)){
                User user = new User();
                user.setNickname("无");
                user.setName("wu");
                logContent.put("oldValue", user);
            }
        }

        if(updateField != null  && updateField.equals("workStatusNode")){

            setFlowRelation(newWorkItem);
        }
        creatUpdateOplog(logContent, newWorkItem);

    }

    void updateWorkItemStatus(WorkItem workItem) {
            // 查找状态对应的项目状态
            StateNode workStatusNode = workItem.getWorkStatusNode();
            StateNodeFlowQuery stateNodeFlowQuery = new StateNodeFlowQuery();
            stateNodeFlowQuery.setNodeId(workStatusNode.getId());
            stateNodeFlowQuery.setFlowId(workItem.getFlowId());
            List<StateNodeFlow> stateNodeFlowList = stateNodeflowService.findStateNodeFlowList(stateNodeFlowQuery);

            StateNodeFlow stateNodeFlow = stateNodeFlowList.get(0);
            // 设置状态对应的系统状态
            workItem.setWorkStatus(stateNodeFlow);
            workItem.setWorkStatusCode(stateNodeFlow.getNodeStatus());
            String nodeStatus = stateNodeFlow.getNodeStatus();
            // 设置事项结束时间
            if(nodeStatus.equals("DONE")){
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = formater.format(new Date());
                workItem.setActualEndTime(format);
            }


    }
    void updateByTransitionRule(WorkItem workItem, WorkItem oldWorkItem){

        String transitionId = workItem.getTransitionId();
        if(transitionId != null){
            TransitionRuleQuery transitionRuleQuery = new TransitionRuleQuery();
            transitionRuleQuery.setTransitionId(transitionId);
            List<TransitionRule> transitionRuleList = transitionRuleService.findTransitionRuleList(transitionRuleQuery);
            for (TransitionRule transitionRule : transitionRuleList) {
                User allocationUser = transitionRule.getAllocationUser();
                // 修改事项负责人
                workItem.setAssigner(allocationUser);
                // 发送消息和待办事项
                creatTodoTask(oldWorkItem, allocationUser);
                sendMessageForUpdateAssigner(oldWorkItem, allocationUser);
            }
        }
    }
    void setFlowRelation(WorkItem workItem){
        StateNodeWorkRelation stateNodeWorkRelation = new StateNodeWorkRelation();
        StateNodeWorkRelationQuery stateNodeWorkRelationQuery = new StateNodeWorkRelationQuery();
        stateNodeWorkRelationQuery.setWorkId(workItem.getId());

        List<StateNodeWorkRelation> stateNodeWorkRelationList = stateNodeWorkRelationService.findStateNodeWorkRelationList(stateNodeWorkRelationQuery);
        if(stateNodeWorkRelationList.size() > 0){
            String id = stateNodeWorkRelationList.get(0).getId();
            stateNodeWorkRelation.setId(id);
            stateNodeWorkRelation.setWorkName(workItem.getTitle());
            stateNodeWorkRelation.setStateNodeId(workItem.getWorkStatus().getId());
            stateNodeWorkRelation.setNodeId(workItem.getWorkStatusNode().getId());
            stateNodeWorkRelationService.updateStateNodeWorkRelation(stateNodeWorkRelation);
        }else {
            stateNodeWorkRelation.setWorkId(workItem.getId());
            stateNodeWorkRelation.setWorkName(workItem.getTitle());
            stateNodeWorkRelation.setStateNodeId(workItem.getWorkStatus().getId());
            stateNodeWorkRelation.setNodeId(workItem.getWorkStatusNode().getId());
            stateNodeWorkRelation.setProjectId(workItem.getProject().getId());
            stateNodeWorkRelationService.createStateNodeWorkRelation(stateNodeWorkRelation);
        }

    }
    /**
     * get property value of a object
     * @param fieldName
     * @param o
     * @return
     */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }


    @Override
    public void deleteWorkItem(@NotNull String id) {
        WorkItem workItem = findWorkItem(id);
        Map<String, String> content = new HashMap<>();
        content.put("workItemName", workItem.getTitle());

        StateNodeWorkRelationQuery stateNodeWorkRelationQuery = new StateNodeWorkRelationQuery();
        stateNodeWorkRelationQuery.setWorkId(id);
        List<StateNodeWorkRelation> stateNodeWorkRelationList = stateNodeWorkRelationService.findStateNodeWorkRelationList(stateNodeWorkRelationQuery);
        for (StateNodeWorkRelation stateNodeWorkRelation : stateNodeWorkRelationList) {
            try {
                stateNodeWorkRelationService.deleteStateNodeWorkRelation(stateNodeWorkRelation.getId());
            }catch (Exception e){

                throw new ApplicationException(2000,"删除失败" + e.getMessage());
            }
        }

        FlowWorkRelationQuery flowWorkRelationQuery = new FlowWorkRelationQuery();
        flowWorkRelationQuery.setWorkId(id);
        List<FlowWorkRelation> flowWorkRelationList = flowWorkRelationService.findFlowWorkRelationList(flowWorkRelationQuery);
        for (FlowWorkRelation flowWorkRelation : flowWorkRelationList) {
            try {
                flowWorkRelationService.deleteFlowWorkRelation(flowWorkRelation.getId());
            }catch (Exception e){
                throw new ApplicationException(2000,"删除失败" + e.getMessage());
            }
        }

//        creatOplog(id,"delete", contentString, opLogTemplateId);
        //添加动态
//        creatDynamic(id,"delete",null);
        //删除事项
        workItemDao.deleteWorkItem(id);

        //删除索引
//        dssClient.delete(WorkItem.class,id);
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

    @Override
    public Pagination<WorkItem> findSelectWorkItemList(WorkItemQuery workItemQuery) {
        Pagination<WorkItemEntity> pagination = workItemDao.findSelectWorkItemList(workItemQuery);

        List<WorkItem> workItemList = BeanMapper.mapList(pagination.getDataList(),WorkItem.class);

        joinTemplate.joinQuery(workItemList);

        return PaginationBuilder.build(pagination,workItemList);
    }

    List<WorkItem> findConditionWorkItemList(WorkItemQuery workItemQuery) {
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

        logger.info("joinQuery cost time:{}",bTime1-aTime1);

        List<WorkItem> topWorkItemList = BeanMapper.mapList(topWorkItemPageEntity.getDataList(), WorkItem.class);
        long aTime = System.currentTimeMillis();

        joinTemplate.joinQuery(topWorkItemList,  new String[]{"project","assigner", "workPriority", "workStatusNode", "workTypeSys", "builder"});
        long bTime = System.currentTimeMillis();

        logger.info("joinQuery cost time:{}",bTime-aTime);
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
        List<WorkItemEntity> topChildWorkItemEntity = workItemDao.findTopChildWorkItem(workItemQuery);
        long bTime3 = System.currentTimeMillis();

        logger.info("joinQuery cost time:{}",bTime3-aTime3);

        if(topChildWorkItemEntity == null || topChildWorkItemEntity.size() == 0){
            return topWorkItemPageList;
        }else {
            List<WorkItem> workItemList = BeanMapper.mapList(topChildWorkItemEntity,WorkItem.class);
            long cTime = System.currentTimeMillis();
            joinTemplate.joinQuery(workItemList, new String[]{"project", "assigner", "workPriority", "workStatusNode", "workTypeSys", "builder"});
            long eTime = System.currentTimeMillis();
            logger.info("joinQuery cost time:{}",eTime-cTime);


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
                    List<WorkItem> workItemList = this.findConditionWorkItemList(workItemQuery);
                    workBoard.setWorkItemList(workItemList);
                    if(workBoard != null){
                        workBoardList.add(workBoard);
                    }
                }
            }
        }
        return workBoardList;
    }

    @Override
    public Pagination<WorkItem> findUnEpicWorkItemPage(WorkItemQuery workItemQuery) {
        //查询所有事项
        Pagination<WorkItemEntity>  pagination = workItemDao.findUnEpicWorkItemPage(workItemQuery);

        List<WorkItem> workItemList = BeanMapper.mapList(pagination.getDataList(),WorkItem.class);

        joinTemplate.joinQuery(workItemList);

        return PaginationBuilder.build(pagination,workItemList);
    }

    @Override
    public Pagination<WorkItem> findUnPlanWorkItemPage(WorkItemQuery workItemQuery) {
        Pagination<WorkItemEntity>  pagination = workItemDao.findUnPlanWorkItemPage(workItemQuery);

        List<WorkItem> workItemList = BeanMapper.mapList(pagination.getDataList(),WorkItem.class);

        joinTemplate.joinQuery(workItemList);

        return PaginationBuilder.build(pagination,workItemList);
    }

    public List<WorkItem> findPlanWorkItemPage(WorkItemQuery workItemQuery) {
        List<WorkItemEntity> workItemEntityList= workItemDao.findPlanWorkItemPage(workItemQuery);

        List<WorkItem> workItemList = BeanMapper.mapList(workItemEntityList,WorkItem.class);

        joinTemplate.joinQuery(workItemList);

        return workItemList;
    }

    @Override
    public Pagination<WorkItem> findWorkItemByKeyWorks(WorkItemQuery workItemQuery) {
        Pagination<WorkItemEntity> pagination = workItemDao.findWorkItemByKeyWorks(workItemQuery);

        List<WorkItem> workItemList = BeanMapper.mapList(pagination.getDataList(),WorkItem.class);

        joinTemplate.joinQuery(workItemList,  new String[]{"project","assigner", "workPriority", "workStatusNode", "workTypeSys"});
        return PaginationBuilder.build(pagination,workItemList);
    }

    @Override
    public HashMap<String, Integer> findWorkItemNumByWorkType(WorkItemQuery workItemQuery) {
        HashMap<String, Integer> workItemNumByWorkType = workItemDao.findWorkItemNumByWorkType(workItemQuery);
        return workItemNumByWorkType;
    }

    @Override
    public HashMap<String, Integer> findWorkItemNumByWorkList(WorkItemQuery workItemQuery) {
        HashMap<String, Integer> workItemNumByWorkType = workItemDao.findWorkItemNumByWorkList(workItemQuery);
        return workItemNumByWorkType;
    }

}