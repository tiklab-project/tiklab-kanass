package io.tiklab.teamwire.project.wiki.service;

import io.tiklab.teamwire.project.wiki.model.KanassRepository;

import java.util.List;

/**
* 事项附件服务接口
*/
public interface WikiRepositoryService {

    /**
    * 根据条件查询事项附件列表
    * @return
    */
    List<KanassRepository> findAllRepository();

    List<KanassRepository> findList(List<String> idList);

}