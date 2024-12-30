package io.tiklab.kanass.support.service;

import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.kanass.projectset.model.ProjectSet;
import io.tiklab.kanass.projectset.service.ProjectSetService;
import io.tiklab.message.message.model.Message;
import io.tiklab.message.message.model.MessageReceiver;
import io.tiklab.message.message.service.SendMessageNoticeService;
import io.tiklab.message.setting.model.MessageType;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.service.DmUserCallbackServiceImpl;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
@Primary
@Service
public class ProjectAddUserMessageServiceImpl extends DmUserCallbackServiceImpl {
    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectSetService projectSetService;

    @Autowired
    UserService userService;

    @Autowired
    SendMessageNoticeService sendMessageNoticeService;

    @Value("${base.url:null}")
    String baseUrl;


    /**
     * 重写方法，发送创建项目或者项目集的消息给相关人员
     * @param dmUser
     */
    @Override
    public void dmUserCallback(DmUser dmUser) {
        String domainId = dmUser.getDomainId();
        User user = dmUser.getUser();
        String id = user.getId();
        user = userService.findUser(id);
        Project project = projectService.findProject(domainId);
        if(project != null){
            creatProjectMessage(user, project);
        }

        ProjectSet projectSet = projectSetService.findProjectSet(domainId);
        if(projectSet != null){
            creatPojectSetMessage(user, projectSet);
        }
    }

    /**
     * 创建项目发送消息
     * @param user
     * @param project
     */
    void creatProjectMessage(User user, Project project){
        HashMap<String, Object> content = new HashMap<>();
        content.put("projectName", project.getProjectName());
        content.put("projectId", project.getId());
        content.put("projectIcon", project.getIconUrl());

        String createUserId = LoginContext.getLoginId();
        User createUser = userService.findOne(createUserId);
        content.put("createUser", createUser);
        content.put("createUserIcon", user.getNickname().substring( 0, 1).toUpperCase());
        content.put("receiveTime", new SimpleDateFormat("MM-dd").format(new Date()));

        // 接收者
        List<MessageReceiver> objects = new ArrayList<>();
        MessageReceiver messageReceiver = new MessageReceiver();
        messageReceiver.setUserId(user.getId());
        objects.add(messageReceiver);

        Message message = new Message();
        MessageType messageType = new MessageType();
        messageType.setId("KANASS_MESSAGETYPE_JOINPROJECT");
        message.setMessageType(messageType);
        message.setMessageReceiverList(objects);
        message.setBaseUrl(baseUrl);
        message.setLink("/project/${projectId}/workitem");
        message.setAction(project.getProjectName());
        message.setSendId(createUser.getId());
        message.setData(content);

        message.setMessageSendTypeId("site");
        sendMessageNoticeService.sendMessage(message);

        message.setId(null);
        message.setMessageSendTypeId("qywechat");
        sendMessageNoticeService.sendMessage(message);
    }

    /**
     * 创建项目集发送消息
     * @param user
     * @param projectSet
     */
    void creatPojectSetMessage(User user, ProjectSet projectSet){
        HashMap<String, Object> content = new HashMap<>();
        content.put("projectSetName", projectSet.getName());
        content.put("projectSetId", projectSet.getId());

        String createUserId = LoginContext.getLoginId();
        User createUser = userService.findOne(createUserId);
        content.put("createUser", createUser);
        content.put("createUserIcon", user.getNickname().substring( 0, 1).toUpperCase());
        content.put("receiveTime", new SimpleDateFormat("MM-dd").format(new Date()));

        // 接收者
        List<MessageReceiver> objects = new ArrayList<>();
        MessageReceiver messageReceiver = new MessageReceiver();
        messageReceiver.setUserId(user.getId());
        objects.add(messageReceiver);

        Message message = new Message();
        MessageType messageType = new MessageType();
        messageType.setId("KANASS_MESSAGETYPE_JOINPROSET");
        message.setMessageType(messageType);
        message.setMessageReceiverList(objects);
        message.setBaseUrl(baseUrl);
        message.setLink("/projectSetdetail/${projectSetId}/projectSetProjectList");
        message.setAction(projectSet.getName());
        message.setSendId(createUser.getId());
        message.setData(content);

        message.setMessageSendTypeId("site");
        sendMessageNoticeService.sendMessage(message);

        message.setId(null);
        message.setMessageSendTypeId("qywechat");
        sendMessageNoticeService.sendMessage(message);
    }
}
