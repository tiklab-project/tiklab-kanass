package io.tiklab.teamwire.workitem.service;

import io.tiklab.teamwire.project.wiki.model.KanassRepository;
import io.tiklab.user.user.model.User;

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

    List<User> findRepositoryUserList(List<String> repositoryIds);

}