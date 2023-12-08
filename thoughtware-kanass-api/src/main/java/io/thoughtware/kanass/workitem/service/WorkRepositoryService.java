package io.thoughtware.kanass.workitem.service;

import io.thoughtware.kanass.project.wiki.model.KanassRepository;
import io.thoughtware.user.dmUser.model.DmUser;
import io.thoughtware.user.dmUser.model.DmUserQuery;
import io.thoughtware.user.user.model.User;

import java.util.List;

/**
* 事项附件服务接口
*/
public interface WorkRepositoryService {

    /**
    * 根据条件查询事项附件列表
    * @return
    */
    List<KanassRepository> findAllRepository();

    List<KanassRepository> findList(List<String> idList);

    List<DmUser> findRepositoryUserList(DmUserQuery dmUserQuery);

}