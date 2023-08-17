package io.tiklab.teamwire.project.test.service;

import io.tiklab.teamwire.project.test.model.TestRepository;

import java.util.List;

/**
* 事项附件服务接口
*/
public interface TestRepositoryService {

    /**
    * 根据条件查询事项附件列表
    * @return
    */
    List<TestRepository> findAllRepository();

    List<TestRepository> findList(List<String> idList);

}