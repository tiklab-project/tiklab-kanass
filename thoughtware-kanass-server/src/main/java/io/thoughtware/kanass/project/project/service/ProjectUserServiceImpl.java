package io.thoughtware.kanass.project.project.service;

import io.thoughtware.user.dmUser.model.DmUser;
import io.thoughtware.user.dmUser.service.DmUserCallbackServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class ProjectUserServiceImpl extends DmUserCallbackServiceImpl {


    @Override
    public void dmUserCallback(DmUser dmUser) {
        String domainId = dmUser.getDomainId();

    }
}
