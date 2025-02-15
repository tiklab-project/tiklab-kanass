package io.tiklab.kanass.workitem.service;

import io.tiklab.kanass.project.wiki.model.KanassRepository;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;

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