package io.tiklab.kanass.support.sql.service;

import io.tiklab.dsm.support.DsmProcessTask;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.message.message.model.MessageDmNotice;
import io.tiklab.message.message.model.MessageNotice;
import io.tiklab.message.message.model.MessageNoticeQuery;
import io.tiklab.message.message.model.MessageNoticeUser;
import io.tiklab.message.message.service.MessageDmNoticeService;
import io.tiklab.message.message.service.MessageNoticeService;
import io.tiklab.message.message.service.MessageNoticeUserService;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.model.UserQuery;
import io.tiklab.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KanassMessage100Task implements DsmProcessTask {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MessageNoticeService messageNoticeService;

    @Autowired
    private MessageDmNoticeService messageDmNoticeService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageNoticeUserService messageNoticeUserService;

    @Override
    public void execute() {
        List<String> domainIdList = projectService.findAllProject().stream().map(Project::getId).collect(Collectors.toList());

        MessageNoticeQuery messageNoticeQuery = new MessageNoticeQuery();
        messageNoticeQuery.setBgroup("kanass");
        List<MessageNotice> messageNoticeList = messageNoticeService.findMessageNoticeList(messageNoticeQuery);

        UserQuery userQuery = new UserQuery();
        userQuery.setType(1);
        List<User> userList = userService.findUserList(userQuery);

        for (MessageNotice messageNotice : messageNoticeList) {
            // 创建项目级的消息  获取所有项目id并遍历
            for (String domainId : domainIdList) {
                MessageDmNotice messageDmNotice = new MessageDmNotice();
                messageDmNotice.setDomainId(domainId);
                messageDmNotice.setMessageNotice(messageNotice);
                messageDmNotice.setSourceNoticeId(messageNotice.getId());
                messageDmNotice.setOpen(true);

                messageDmNoticeService.createMessageDmNotice(messageDmNotice);
            }

            // 配置消息下的用户， 管理员
            for (User user : userList) {
                MessageNoticeUser messageNoticeUser = new MessageNoticeUser();
                messageNoticeUser.setMessageNotice(messageNotice);
                messageNoticeUser.setUser(user);

                messageNoticeUserService.createMessageNoticeConnectUser(messageNoticeUser);
            }
        }

    }
}
