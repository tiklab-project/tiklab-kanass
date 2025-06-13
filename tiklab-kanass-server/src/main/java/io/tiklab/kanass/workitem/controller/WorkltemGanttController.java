package io.tiklab.kanass.workitem.controller;

import io.tiklab.core.Result;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.model.WorkItemQuery;
import io.tiklab.kanass.workitem.service.WorkItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/workItemGantt")
//@Api(name = "WorkItemController",desc = "事项甘特图管理")
public class WorkltemGanttController {

    @Autowired
    private WorkItemService workItemGanttService;

    @RequestMapping(path = "/findWorkItemListTree",method = RequestMethod.POST)
//    @ApiMethod(name = "findWorkItemListTree",desc = "根据查询对象查找甘特图")
//    @ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<List<WorkItem>> findWorkItemListTree(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        List<WorkItem> workItemList = workItemGanttService.findWorkItemListTree(workItemQuery);

        List<WorkItem> collect = workItemList.stream().filter(workItem -> ObjectUtils.isEmpty(workItem.getParentId())).collect(Collectors.toList());
        return Result.ok(collect);
    }
}
