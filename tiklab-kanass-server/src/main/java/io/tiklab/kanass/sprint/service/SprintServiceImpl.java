package io.tiklab.kanass.sprint.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.utils.UuidGenerator;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.kanass.sprint.model.Sprint;
import io.tiklab.kanass.sprint.model.SprintQuery;
import io.tiklab.kanass.sprint.model.SprintState;
import io.tiklab.kanass.sprint.model.SprintStateQuery;
import io.tiklab.kanass.workitem.service.WorkSprintService;
import io.tiklab.privilege.vRole.model.VRoleDomain;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.sprint.entity.SprintStateEntity;
import io.tiklab.kanass.sprint.dao.SprintDao;
import io.tiklab.kanass.sprint.dao.SprintStateDao;
import io.tiklab.kanass.sprint.entity.SprintEntity;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.service.WorkItemService;
import io.tiklab.message.message.model.Message;
import io.tiklab.message.message.model.SendMessageNotice;
import io.tiklab.message.message.service.SendMessageNoticeService;
import io.tiklab.message.setting.model.MessageType;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
* 迭代服务
*/
@Service
public class SprintServiceImpl implements SprintService {
    public final ExecutorService executorService = Executors.newCachedThreadPool();
    @Autowired
    SprintDao sprintDao;

    @Autowired
    SprintStateDao sprintStateDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ProjectService projectService;

    @Autowired
    SprintFocusService sprintFocusService;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    WorkSprintService workSprintService;
    @Autowired
    UserService userService;

    @Value("${base.url:null}")
    String baseUrl;

    @Autowired
    SendMessageNoticeService sendMessageNoticeService;



    /**
     * 发送消息
     * @param sprint
     */
    void sendMessageForCreatSprint(Sprint sprint ){
        String projectId = sprint.getProject().getId();
        HashMap<String, Object> content = new HashMap<>();
        content.put("sprintName", sprint.getSprintName());
        content.put("sprintId", sprint.getId());
        content.put("projectId", sprint.getProject().getId());

        Message message = new Message();
        MessageType messageType = new MessageType();
        messageType.setId("KANASS_MESSAGETYPE_SPRINTCREATE");
        message.setMessageType(messageType);
        message.setData(content);

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        content.put("createUser", user);
        content.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());
        content.put("receiveTime", new SimpleDateFormat("MM-dd").format(new Date()));
        SendMessageNotice sendMessageNotice = new SendMessageNotice();
        String msg = JSONObject.toJSONString(content);

        sendMessageNotice.setBaseUrl(baseUrl);
        sendMessageNotice.setId("KANASS_MESSAGE_SPRINTCREATE");
        sendMessageNotice.setLink("/${projectId}/sprintdetail/${sprintId}/workTable");
        sendMessageNotice.setAction(sprint.getSprintName());
        sendMessageNotice.setSendId(user.getId());
        sendMessageNotice.setSiteData(msg);
        sendMessageNotice.setQywechatData(msg);
        sendMessageNotice.setDomainId(projectId);
        VRoleDomain vRoleDomain = new VRoleDomain();
        vRoleDomain.setModelId(sprint.getId());
        vRoleDomain.setType("sprint");
        sendMessageNotice.setvRoleDomain(vRoleDomain);
        sendMessageNoticeService.sendDmMessageNotice(sendMessageNotice);
    }

    /**
     * 发送消息
     * @param oldSprint
     * @param newSprint
     */
    void sendMessageForUpdateSprintState(Sprint oldSprint, Sprint newSprint ){
        String projectId = newSprint.getProject().getId();
        HashMap<String, Object> content = new HashMap<>();
        content.put("sprintName", newSprint.getSprintName());
        content.put("sprintId", newSprint.getId());
        content.put("projectId", projectId);
        content.put("oldValue", oldSprint.getSprintState().getName());
        content.put("newValue", newSprint.getSprintState().getName());

        String createUserId = LoginContext.getLoginId();
        User user = userService.findOne(createUserId);
        content.put("createUser", user);
        content.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());
        content.put("receiveTime", new SimpleDateFormat("MM-dd").format(new Date()));


        Message message = new Message();
        MessageType messageType = new MessageType();
        messageType.setId("KANASS_MESSAGETYPE_SPRINTUPDATE");
        message.setMessageType(messageType);
        message.setData(content);

        SendMessageNotice sendMessageNotice = new SendMessageNotice();
        String msg = JSONObject.toJSONString(content);

        sendMessageNotice.setBaseUrl(baseUrl);
        sendMessageNotice.setId("KANASS_MESSAGE_SPRINTUPDATE");
        sendMessageNotice.setLink("/${projectId}/sprintdetail/${sprintId}/workTable");
        sendMessageNotice.setAction(newSprint.getSprintName());
        sendMessageNotice.setSendId(user.getId());
        sendMessageNotice.setSiteData(msg);
        sendMessageNotice.setQywechatData(msg);
        sendMessageNotice.setDomainId(projectId);
        VRoleDomain vRoleDomain = new VRoleDomain();
        vRoleDomain.setModelId(newSprint.getId());
        vRoleDomain.setType("sprint");
        sendMessageNotice.setvRoleDomain(vRoleDomain);
        sendMessageNoticeService.sendDmMessageNotice(sendMessageNotice);
    }

    @Override
    public String createSprint(@NotNull @Valid Sprint sprint) {
        //初始化迭代状态
        SprintState sprintState = new SprintState();
        sprintState.setId("000000");
        sprint.setSprintState(sprintState);

        String createUserId = LoginContext.getLoginId();
        User user = new User();
        user.setId(createUserId);
        sprint.setBuilder(user);

        int color = new Random().nextInt(3) + 1;
        System.out.println(color);
        sprint.setColor(color);

        SprintEntity sprintEntity = BeanMapper.map(sprint, SprintEntity.class);
        String id = sprintDao.createSprint(sprintEntity);
        Sprint sprint1 = findSprint(id);
        sendMessageForCreatSprint(sprint1);

        return id;
    }

    @Override
    public String createJiraSprint(@NotNull @Valid Sprint sprint) {
        //初始化迭代状态
        SprintEntity sprintEntity = BeanMapper.map(sprint, SprintEntity.class);
        String id = sprintDao.createSprint(sprintEntity);
        return id;
    }

    @Override
    public void updateSprint(@NotNull @Valid Sprint sprint) {
        String sprintId = sprint.getId();
        Sprint oldSprint = findSprint(sprintId);
        SprintState sprintState = sprint.getSprintState();
        String newSprintId = sprint.getNewSprintId();
        // 如果状态更新为完成
        if(sprintState != null ){
            if(sprintState.getId().equals("222222")){
                // 创建新的迭代与事项的记录
                // 只查询迭代中未完成的事项
                List<WorkItem> sprintWorkItemList = workItemService.findSprintWorkItemList(sprintId);
                if(sprintWorkItemList.size() > 0){
                    String valueString = "";
                    for (WorkItem workItem : sprintWorkItemList) {
                        // 更新迭代之后更新待办
                        if(newSprintId != null) {
                            Sprint sprint1 = new Sprint();
                            sprint1.setId(newSprintId);
                            workItem.setSprint(sprint1);
                            workItem.setUpdateField("sprint");
                            workItemService.updateTodoTaskData(workItem);
                        }else {
                            workItem.setUpdateField("sprint");
                            workItem.setSprint(null);
                            workItemService.updateTodoTaskData(workItem);
                        }

                        String id = UuidGenerator.getRandomIdByUUID(12);
                        String sql = "('" + id + "', '" + workItem.getId() + "', '" + newSprintId + "'),";
                        valueString = valueString.concat(sql);
                    }
                    int length = valueString.length() - 1;
                    String substring = valueString.substring(0, length);
                    if(newSprintId != null){
                        // 更新事项与迭代的关联
                        workSprintService.createBatchWorkSprint(substring);
                    }
                }

                // 更新事项的迭代, 没有完成的更新到选择的新的迭代或者待办列表
                workItemService.updateBatchWorkItemSprint(sprintId, newSprintId);

            }

            if(sprintState.getId().equals("111111")){
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = formater.format(new Date());
                sprint.setRelaStartTime(format);
            }

        }

        //设置结束时间
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formater.format(new Date());
        sprint.setRelaEndTime(format);
        SprintEntity sprintEntity = BeanMapper.map(sprint, SprintEntity.class);
        sprintDao.updateSprint(sprintEntity);
        Sprint newSprint = findSprint(sprintId);
        if(sprintState != null){
            executorService.submit(() -> {
                sendMessageForUpdateSprintState(oldSprint, newSprint);
            });
        }
    }

    @Override
    public void deleteSprint(@NotNull String id) {
        sprintDao.deleteSprint(id);

        // 删除关注的迭代数据
        sprintDao.deleteSprintFocus(id);

    }

    @Override
    public Sprint findOne(String id) {
        SprintEntity sprintEntity = sprintDao.findSprint(id);

        Sprint sprint = BeanMapper.map(sprintEntity, Sprint.class);
        return sprint;
    }

    @Override
    public List<Sprint> findList(List<String> idList) {
        List<SprintEntity> sprintEntityList = sprintDao.findSprintList(idList);

        List<Sprint> sprintList = BeanMapper.mapList(sprintEntityList, Sprint.class);

        return sprintList;
    }

    @Override
    public Sprint findSprint(@NotNull String id) {
        Sprint sprint = findOne(id);

//        WorkItemQuery workItemQuery = new WorkItemQuery();
//        workItemQuery.setCurrentSprintId(id);
//        List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);
        HashMap<String, Integer> sprintWorkItemNum = workItemService.findSprintWorkItemNum(id);
        sprint.setWorkNumber(sprintWorkItemNum.get("all"));
        sprint.setWorkDoneNumber(sprintWorkItemNum.get("done"));
        sprint.setWorkProgressNumber(sprintWorkItemNum.get("progress"));

        joinTemplate.joinQuery(sprint);
        return sprint;
    }

    @Override
    public List<Sprint> findAllSprint() {
        List<SprintEntity> sprintEntityList = sprintDao.findAllSprint();

        List<Sprint> sprintList = BeanMapper.mapList(sprintEntityList, Sprint.class);

        joinTemplate.joinQuery(sprintList);
        return sprintList;
    }

    @Override
    public List<Sprint> findSprintList(SprintQuery sprintQuery) {
        List<SprintEntity> sprintEntityList = sprintDao.findSprintList(sprintQuery);
        List<Sprint> sprintList = BeanMapper.mapList(sprintEntityList, Sprint.class);
        if(sprintList.size() > 0){
            String sprintIds = "(" + sprintEntityList.stream().map(item -> "'" + item.getId() + "'").
                    collect(Collectors.joining(", ")) + ")";
            List<String> sprintWorkItemNum = workSprintService.findSprintWorkItemNum(sprintIds);
            for (Sprint sprint : sprintList) {
                String id = sprint.getId();
                List<String> countList = sprintWorkItemNum.stream().filter(item -> item.equals(id))
                        .collect(Collectors.toList());
                sprint.setWorkNumber(countList.size());
            }
        }
        // 查找迭代的事项数量
        joinTemplate.joinQuery(sprintList);
        return sprintList;
    }

    public List<Sprint> findSelectSprintList(SprintQuery sprintQuery) {
        List<SprintEntity> sprintEntityList = sprintDao.findSelectSprintList(sprintQuery);
        List<Sprint> sprintList = BeanMapper.mapList(sprintEntityList, Sprint.class);

        joinTemplate.joinQuery(sprintList);
        return sprintList;
    }

    @Override
    public Pagination<Sprint> findSprintPage(SprintQuery sprintQuery) {

        Pagination<SprintEntity> pagination = sprintDao.findSprintPage(sprintQuery);

        List<Sprint> sprintList = BeanMapper.mapList(pagination.getDataList(), Sprint.class);

        if(sprintList.size() > 0){
            String sprintIds = "(" + sprintList.stream().map(item -> "'" + item.getId() + "'").
                    collect(Collectors.joining(", ")) + ")";
            List<String> focusSprintIds = sprintFocusService.findFocusSprintIds();
            List<String> sprintWorkItemNum = workSprintService.findSprintWorkItemNum(sprintIds);

            for (Sprint sprint : sprintList) {
                String id = sprint.getId();
                if(focusSprintIds.contains(id)){
                    sprint.setFocusIs(true);
                }
                List<String> countList = sprintWorkItemNum.stream().filter(item -> item.equals(id))
                        .collect(Collectors.toList());
                sprint.setWorkNumber(countList.size());
            }
        }
        joinTemplate.joinQuery(sprintList);
        return PaginationBuilder.build(pagination,sprintList);
    }

    @Override
    public List<Sprint> findFocusSprintList(SprintQuery sprintQuery) {

        List<SprintEntity> sprintEntityList = sprintDao.findFocusSprintList(sprintQuery);

        List<Sprint> sprintList = BeanMapper.mapList(sprintEntityList, Sprint.class);

        joinTemplate.joinQuery(sprintList);
        return sprintList;
    }

    /**
     * 查询请迭代状态
     *
     * @param sprintState
     */
    public List<SprintStateEntity> findSprintState(SprintState sprintState) {
        SprintStateQuery sprintStateQuery = new SprintStateQuery();
        sprintStateQuery.setSort(sprintState.getSort());
        return sprintStateDao.findSprintStateList(sprintStateQuery);
    }

    @Override
    public List<Sprint> findProgressSprint() {
        List<SprintEntity> sprintEntityList = sprintDao.findProgressSprint();

        List<Sprint> sprintList = BeanMapper.mapList(sprintEntityList, Sprint.class);

        joinTemplate.joinQuery(sprintList);
        return sprintList;
    }

    @Override
    public List<Sprint> findWorkSprintList(String workId) {
        List<SprintEntity> sprintEntityList = sprintDao.findWorkSprintList(workId);
        List<Sprint> sprintList = BeanMapper.mapList(sprintEntityList, Sprint.class);

        joinTemplate.joinQuery(sprintList);
        return sprintList;
    }
}