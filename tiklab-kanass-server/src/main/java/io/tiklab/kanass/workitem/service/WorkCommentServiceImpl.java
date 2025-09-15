package io.tiklab.kanass.workitem.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.common.SendMessageUtil;
import io.tiklab.kanass.project.worklog.model.WorkLog;
import io.tiklab.kanass.project.worklog.model.WorkLogQuery;
import io.tiklab.kanass.project.worklog.service.WorkLogService;
import io.tiklab.kanass.workitem.model.WorkComment;
import io.tiklab.kanass.workitem.model.WorkCommentQuery;
import io.tiklab.kanass.workitem.dao.WorkCommentDao;
import io.tiklab.kanass.workitem.entity.WorkCommentEntity;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.message.message.model.Message;
import io.tiklab.message.message.model.MessageReceiver;
import io.tiklab.message.message.service.SendMessageNoticeService;
import io.tiklab.message.setting.model.MessageType;
import io.tiklab.security.logging.logging.model.Logging;
import io.tiklab.security.logging.logging.model.LoggingQuery;
import io.tiklab.security.logging.logging.model.LoggingType;
import io.tiklab.security.logging.logging.service.LoggingByTempService;
import io.tiklab.security.logging.logging.service.LoggingService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* 事项评论服务
*/
@Service
public class WorkCommentServiceImpl implements WorkCommentService {
    public final ExecutorService executorService = Executors.newCachedThreadPool();
    private static Logger logger = LoggerFactory.getLogger(WorkCommentServiceImpl.class);
    @Autowired
    WorkCommentDao workCommentDao;

    @Autowired
    UserProcessor userProcessor;

    @Autowired
    JoinTemplate joinTemplate;

    @Value("${base.url:null}")
    String baseUrl;

    @Autowired
    LoggingByTempService opLogByTemplService;

    @Autowired
    LoggingService loggingService;

    @Autowired
    WorkLogService workLogService;

    @Autowired
    SendMessageUtil sendMessageUtil;

    @Override
    public String createWorkComment(@NotNull @Valid WorkComment workComment) {
        WorkCommentEntity workCommentEntity = BeanMapper.map(workComment, WorkCommentEntity.class);
        String workCommentId = workCommentDao.createWorkComment(workCommentEntity);
        executorService.submit(() -> {
            creatWorkItemCommentDynamic(workCommentId);
            sendMessageForComment(workCommentId);
        });
        return workCommentId;
    }

    public String createImportWorkComment(@NotNull @Valid WorkComment workComment) {
        WorkCommentEntity workCommentEntity = BeanMapper.map(workComment, WorkCommentEntity.class);
        String workCommentId = workCommentDao.createWorkComment(workCommentEntity);
        creatImportWorkItemCommentDynamic(workCommentId, workComment);
        return workCommentId;
    }

    @Override
    public void updateWorkComment(@NotNull @Valid WorkComment workComment) {
        WorkCommentEntity workCommentEntity = BeanMapper.map(workComment, WorkCommentEntity.class);

        workCommentDao.updateWorkComment(workCommentEntity);
    }

    @Override
    public void deleteWorkComment(@NotNull String id) {
        workCommentDao.deleteWorkComment(id);
    }

    public void deleteWorkCommentList(WorkCommentQuery workCommentQuery) {
        DeleteBuilders deleteBuilders = DeleteBuilders.createDelete(WorkCommentEntity.class)
                .eq("workItemId", workCommentQuery.getWorkItemId());

        if(workCommentQuery.getWorkItemIds() != null && workCommentQuery.getWorkItemIds().length != 0){
            deleteBuilders.in("workItemId", workCommentQuery.getWorkItemIds());
        }
        DeleteCondition deleteCondition = deleteBuilders.get();
        workCommentDao.deleteWorkComment(deleteCondition);
    }

    @Override
    public WorkComment findOne(String id) {
        WorkCommentEntity workCommentEntity = workCommentDao.findWorkComment(id);

        WorkComment workComment = BeanMapper.map(workCommentEntity, WorkComment.class);
        return workComment;
    }

    @Override
    public List<WorkComment> findList(List<String> idList) {
        List<WorkCommentEntity> workCommentEntityList =  workCommentDao.findWorkCommentList(idList);

        List<WorkComment> workCommentList =  BeanMapper.mapList(workCommentEntityList,WorkComment.class);
        return workCommentList;
    }

    @Override
    public WorkComment findWorkComment(@NotNull String id) {
        WorkComment workComment = findOne(id);

        joinTemplate.joinQuery(workComment, new String[]{"workItem", "user"});

        return workComment;
    }

    @Override
    public List<WorkComment> findAllWorkComment() {
        List<WorkCommentEntity> workCommentEntityList =  workCommentDao.findAllWorkComment();

        List<WorkComment> workCommentList =  BeanMapper.mapList(workCommentEntityList,WorkComment.class);

        joinTemplate.joinQuery(workCommentList, new String[]{"workItem", "user"});

        return workCommentList;
    }

    @Override
    public List<WorkComment> findWorkCommentList(WorkCommentQuery workCommentQuery) {
        List<WorkCommentEntity> workCommentEntityList = workCommentDao.findWorkCommentList(workCommentQuery);

        List<WorkComment> workCommentList = BeanMapper.mapList(workCommentEntityList,WorkComment.class);

        joinTemplate.joinQuery(workCommentList, new String[]{"workItem", "user"});

        return workCommentList;
    }

    @Override
    public Pagination<WorkComment> findWorkCommentPage(WorkCommentQuery workCommentQuery) {
        Pagination<WorkCommentEntity>  pagination = workCommentDao.findWorkCommentPage(workCommentQuery);

        List<WorkComment> workCommentList = BeanMapper.mapList(pagination.getDataList(),WorkComment.class);

        joinTemplate.joinQuery(workCommentList, new String[]{"workItem", "user"});

        return PaginationBuilder.build(pagination,workCommentList);
    }

    @Override
    public Map<String, Integer> findWorkDynamicAndCommentAndLogNum(WorkCommentQuery workCommentQuery) {
        Map<String, Integer> result = new HashMap<>();

        List<WorkCommentEntity> workCommentList = workCommentDao.findWorkCommentList(workCommentQuery);
        result.put("commentNum", workCommentList.size());

        LoggingQuery loggingQuery = new LoggingQuery();
        LinkedHashMap linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("workItemId", workCommentQuery.getWorkItemId());
        loggingQuery.setData(linkedHashMap);
        loggingQuery.setBgroup("kanass");
        List<Logging> loggingList = loggingService.findLogList(loggingQuery);
        result.put("dynamicNum", loggingList.size());

        WorkLogQuery workLogQuery = new WorkLogQuery();
        workLogQuery.setWorkItemId(workCommentQuery.getWorkItemId());
        List<WorkLog> workLogList = workLogService.findWorkLogList(workLogQuery);
        result.put("workLogNum", workLogList.size());

        return result;
    }

    public void creatWorkItemCommentDynamic(String workCommentId){
        Logging log = new Logging();
        log.setBgroup("kanass");

        LoggingType opLogType = new LoggingType();
        opLogType.setId("KANASS_LOGTYPE_WORKITEMCOMMENTADD");
        log.setActionType(opLogType);

        log.setModule("workItem");
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));

        WorkComment workComment = this.findWorkComment(workCommentId);
//        String createUserId = LoginContext.getLoginId();
//        User user = userProcessor.findOne(createUserId);
        log.setUser(workComment.getUser());

        Map<String, String> content = new HashMap<>();
        content.put("id", workComment.getId());
        content.put("projectId", workComment.getWorkItem().getProject().getId());
        content.put("workItemId", workComment.getWorkItem().getId());
        content.put("workItemTitle",  workComment.getWorkItem().getTitle());
        content.put("details",  workComment.getDetails());
        content.put("master", workComment.getUser().getNickname());
        content.put("createTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        content.put("createUserIcon",workComment.getUser().getNickname().substring( 0, 1).toUpperCase());
        log.setData(JSONObject.toJSONString(content));
        log.setLink("/project/${projectId}/workitem/${workItemId}");
        log.setBaseUrl(baseUrl);
        log.setAction(content.get("workItemTitle"));
        opLogByTemplService.createLog(log);
    }

    public void creatImportWorkItemCommentDynamic(String workCommentId, WorkComment workComment){
        Logging log = new Logging();
        log.setBgroup("kanass");

        LoggingType opLogType = new LoggingType();
        opLogType.setId("KANASS_LOGTYPE_WORKITEMCOMMENTADD");
        log.setActionType(opLogType);

        log.setModule("workItem");
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));

        // 导入的时候事项还没有创建，所以join填充的workitem是空的
//        WorkComment workComment = this.findWorkComment(workCommentId);
        log.setUser(workComment.getUser());

        Map<String, String> content = new HashMap<>();
        content.put("id", workComment.getId());
        content.put("projectId", workComment.getWorkItem().getProject().getId());
        content.put("workItemId", workComment.getWorkItem().getId());
        content.put("workItemTitle",  workComment.getWorkItem().getTitle());
        content.put("details",  workComment.getDetails());
        content.put("master", workComment.getUser().getNickname());
        content.put("createTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        content.put("createUserIcon",workComment.getUser().getNickname().substring( 0, 1).toUpperCase());
        log.setData(JSONObject.toJSONString(content));
        log.setLink("/project/${projectId}/workitem/${workItemId}");
        log.setBaseUrl(baseUrl);
        log.setAction(content.get("workItemTitle"));
        opLogByTemplService.createLog(log);
    }

    /**
     * 评论 发送消息
     * @param workCommentId
     */
    void sendMessageForComment(String workCommentId){
        WorkComment workComment = findWorkComment(workCommentId);
        HashMap<String, Object> content = new HashMap<>();
        content.put("comment", workComment.getDetails());
        content.put("workItemTitle", workComment.getWorkItem().getTitle());
        content.put("workItemId", workComment.getWorkItem().getId());
        content.put("projectId", workComment.getWorkItem().getProject().getId());
        String createUserId = LoginContext.getLoginId();
        User user = userProcessor.findOne(createUserId);
        content.put("creater", user.getNickname());
        content.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());
        content.put("receiveTime", new SimpleDateFormat("MM-dd").format(new Date()));

        content.put("link", "/project/${projectId}/work/${workItemId}");
        content.put("action", "创建事项评论");
        content.put("noticeId", "KANASS_MESSAGETYPE_WORKITEM_COMMENT");

        sendMessageUtil.sendDomainMessage(content, workComment.getWorkItem().getProject().getId());
    }
}