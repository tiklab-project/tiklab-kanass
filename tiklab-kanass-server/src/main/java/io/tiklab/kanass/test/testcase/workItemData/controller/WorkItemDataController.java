package io.tiklab.kanass.test.testcase.workItemData.controller;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.flow.transition.model.Transition;
import io.tiklab.flow.transition.model.TransitionQuery;
import io.tiklab.kanass.test.testcase.workItemData.model.WorkItemTestOnQuery;
import io.tiklab.kanass.test.testcase.workItemData.service.WorkItemDataService;
import io.tiklab.kanass.workitem.model.WorkItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * teamwire 需求 缺陷 控制器
 */
@RestController
@RequestMapping("/teamWire")
//@Api(name = "WorkItemController",desc = "postin接口转用例")
public class WorkItemDataController {

    private static Logger logger = LoggerFactory.getLogger(WorkItemDataController.class);

    @Autowired
    private WorkItemDataService workItemDataService;


    @RequestMapping(path = "/findWorkItemList",method = RequestMethod.POST)
//    @ApiMethod(name = "findWorkItemList",desc = "查询teamwire 需求缺陷列表")
//    @ApiParam(name = "workItemTestOnQuery",desc = "workItemTestOnQuery")
    public Result<Pagination<WorkItem>> findWorkItemList(@RequestBody @Valid WorkItemTestOnQuery workItemTestOnQuery){
        Pagination<WorkItem> workItemTestOnList = workItemDataService.findWorkItemList(workItemTestOnQuery);

        return Result.ok(workItemTestOnList);
    }

    @RequestMapping(path="/findWorkItem",method = RequestMethod.POST)
//    @ApiMethod(name = "findWorkItem",desc = "根据id查找需求")
//    @ApiParam(name = "id",desc = "id",required = true)
    public Result<WorkItem> findCategory(@NotNull String id){
        WorkItem workItem = workItemDataService.findWorkItem(id);

        return Result.ok(workItem);
    }

    @RequestMapping(path = "/findTransitionList",method = RequestMethod.POST)
//    @ApiMethod(name = "findTransitionList",desc = "查询状态流转列表")
//    @ApiParam(name = "transitionQuery",desc = "transitionQuery")
    public Result<List<Transition>> findWorkItemList(@RequestBody @Valid TransitionQuery transitionQuery){
        List<Transition> transitionList = workItemDataService.findTransitionList(transitionQuery);

        return Result.ok(transitionList);
    }


}
