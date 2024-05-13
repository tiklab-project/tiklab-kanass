package io.thoughtware.kanass.starter.config;

import io.thoughtware.dal.jdbc.JdbcTemplate;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.kanass.project.project.model.Project;
import io.thoughtware.kanass.project.project.service.ProjectService;
import io.thoughtware.message.message.model.*;
import io.thoughtware.message.message.service.MessageDmNoticeService;
import io.thoughtware.message.message.service.MessageNoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

//@Component
public class ProjectMessageInit implements ApplicationRunner {

    @Autowired
    ProjectService projectService;

    @Autowired
    MessageNoticeService messageNoticeService;

    @Autowired
    MessageDmNoticeService messageDmNoticeService;

    @Autowired
    JpaTemplate jpaTemplate;


    private static Logger logger = LoggerFactory.getLogger(ProjectMessageInit.class);

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        System.out.println("0");
        updateWorkItemId();
    }

    public void updateWorkItemId(){
        MessageNoticeQuery messageNoticeQuery = new MessageNoticeQuery();
        messageNoticeQuery.setScope(1);
        messageNoticeQuery.setType(2);
        List<MessageNotice> messageNoticeList = messageNoticeService.findMessageNoticeList(messageNoticeQuery);

        for (Project project : projectService.findAllProject()) {
            String id = project.getId();
            MessageDmNoticeQuery messageDmNoticeQuery = new MessageDmNoticeQuery();
            messageDmNoticeQuery.setDomainId(id);
            List<MessageDmNotice> messageDmNoticeList = messageDmNoticeService.findMessageDmNoticeList(messageDmNoticeQuery);

            List<String> messageDmNoticeIds = messageDmNoticeList.stream().map(item -> item.getSourceNoticeId()).collect(Collectors.toList());
            List<MessageNotice> messageNoticeList2 = messageNoticeList.stream().filter(item -> !messageDmNoticeIds.contains(item.getId())).collect(Collectors.toList());
            for (MessageNotice messageNotice : messageNoticeList2) {
                MessageNotice messageNotice2 = new MessageNotice(messageNotice.getId());
                MessageDmNotice messageDmNotice = new MessageDmNotice();
                messageDmNotice.setMessageNotice(messageNotice2);
                messageDmNotice.setDomainId(id);
                messageDmNotice.setMessageSendTypeId(messageNotice.getMessageSendTypeId());
                messageDmNotice.setSourceNoticeId(messageNotice.getId());
                messageDmNoticeService.addMessageDmNotice(messageDmNotice);
            }
        }

    }

}
