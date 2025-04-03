package io.tiklab.kanass.project.test.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.kanass.project.test.model.TestRepository;
import io.tiklab.kanass.workitem.model.WorkItem;

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


    void createTestHuboBindWorkItem(JSONObject paramJson);

}