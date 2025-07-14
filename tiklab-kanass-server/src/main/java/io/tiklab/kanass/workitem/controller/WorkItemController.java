package io.tiklab.kanass.workitem.controller;

import io.tiklab.kanass.workitem.model.WorkBoard;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.model.WorkItemQuery;
import io.tiklab.kanass.workitem.model.WorkUserGroupBoard;
import io.tiklab.postin.annotation.Api;
import io.tiklab.kanass.workitem.service.WorkItemService;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @pi.protocol: http
 * @pi.groupName: 事项控制器
 */
@RestController
@RequestMapping("/workItem")
@Api(name = "事项管理",desc = "事项管理")
public class WorkItemController {

    private static Logger logger = LoggerFactory.getLogger(WorkItemController.class);

    @Autowired
    private WorkItemService workItemService;


    /**
     * @pi.name:创建事项
     * @pi.path:/workItem/createWorkItem
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItem
     */
    @RequestMapping(path="/createWorkItem",method = RequestMethod.POST)
    //@ApiMethod(name = "createWorkItem",desc = "创建事项")
    //@ApiParam(name = "workItem",desc = "事项DTO",required = true)
    public Result<String> createWorkItem(@RequestBody @NotNull @Valid WorkItem workItem){
        String id = workItemService.createWorkItem(workItem);

        return Result.ok(id);
    }

    /**
     * @pi.name:创建事项
     * @pi.path:/workItem/updateWorkItem
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItem
     */
    @RequestMapping(path="/updateWorkItem",method = RequestMethod.POST)
    //@ApiMethod(name = "updateWorkItem",desc = "更新事项")
    //@ApiParam(name = "workItem",desc = "事项DTO",required = true)
    public Result<Void> updateWorkItem(@RequestBody @NotNull WorkItem workItem){
        workItemService.updateWorkItem(workItem);

        return Result.ok();
    }

    /**
     * @pi.name:根据事项ID删除事项
     * @pi.path:/workItem/deleteWorkItem
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;
     */
    @RequestMapping(path="/deleteWorkItem",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteWorkItem",desc = "根据事项ID删除事项")
    //@ApiParam(name = "id",desc = "事项ID",required = true)
    public Result<Void> deleteWorkItem(@NotNull String id){
        workItemService.deleteWorkItem(id);

        return Result.ok();
    }

    @RequestMapping(path="/deleteWorkItemAndChildren",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteWorkItemAndChildren",desc = "根据事项ID删除事项")
    //@ApiParam(name = "id",desc = "事项ID",required = true)
    public Result<Void> deleteWorkItemAndChildren(@NotNull String id){
        workItemService.deleteWorkItemAndChildren(id);

        return Result.ok();
    }


    /**
     * @pi.name:根据事项ID查找事项
     * @pi.path:/workItem/findWorkItem
     * @pi.methodType:post
     * @pi.request-type:formdata
     * @pi.param: name=id;dataType=string;value=id;
     */
    @RequestMapping(path="/findWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "根据事项ID查找事项",desc = "根据事项ID查找事项")
//    @ApiParam(name = "事项ID",desc = "事项ID",required = true)
    public Result<WorkItem> findWorkItem(@NotNull String id){
        WorkItem workItem = workItemService.findWorkItem(id);

        return Result.ok(workItem);
    }

    @RequestMapping(path="/findWorkItemAndSprintVersion",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemAndSprintVersion",desc = "根据事项ID查找事项")
    //@ApiParam(name = "id",desc = "事项ID",required = true)
    public Result<WorkItem> findWorkItemAndSprintVersion(@NotNull String id){
        WorkItem workItem = workItemService.findWorkItemAndSprintVersion(id);

        return Result.ok(workItem);
    }

    @RequestMapping(path="/findWorkItemAndUsedTime",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemAndUsedTime",desc = "根据事项ID查找事项和事项所用时间")
    //@ApiParam(name = "id",desc = "事项ID",required = true)
    public Result<WorkItem> findWorkItemAndUsedTime(@NotNull String id){
        WorkItem workItem = workItemService.findWorkItemAndUsedTime(id);

        return Result.ok(workItem);
    }

    /**
     * @pi.name:查找所有事项
     * @pi.path:/workItem/findAllWorkItem
     * @pi.methodType:post
     * @pi.request-type:none
     */
    @RequestMapping(path="/findAllWorkItem",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllWorkItem",desc = "查找所有事项")
    public Result<List<WorkItem>> findAllWorkItem(){
        List<WorkItem> workItemList = workItemService.findAllWorkItem();

        return Result.ok(workItemList);
    }

    /**
     * @pi.name:根据事项ID查找事项
     * @pi.path:/workItem/findWorkItemList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findWorkItemList",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemList",desc = "根据查询对象查找事项列表")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<List<WorkItem>> findWorkItemList(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);

        return Result.ok(workItemList);
    }

    /**
     * @pi.name:根据事项ID查找事项
     * @pi.path:/workItem/findWorkItemList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findEpicSelectWorkItemList",method = RequestMethod.POST)
    //@ApiMethod(name = "findEpicSelectWorkItemList",desc = "根据查询对象查找可被史诗添加的子事项列表")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkItem>> findEpicSelectWorkItemList(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        Pagination<WorkItem> workItemList = workItemService.findEpicSelectWorkItemList(workItemQuery);

        return Result.ok(workItemList);
    }

    /**
     * @pi.name:根据查询对象查找可被其他类型  添加的子事项列表
     * @pi.path:/workItem/findSelectWorkItemList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findSelectChildrenWorkItemList",method = RequestMethod.POST)
    //@ApiMethod(name = "findSelectChildrenWorkItemList",desc = "根据查询对象查找可被添加的子事项列表")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkItem>> findSelectChildrenWorkItemList(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        Pagination<WorkItem> workItemList = workItemService.findSelectChildrenWorkItemList(workItemQuery);

        return Result.ok(workItemList);

    }


    /**
     * @pi.name:根据查询对象查找事项列表树
     * @pi.path:/workItem/findWorkItemListTree
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findWorkItemListTree",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemListTree",desc = "根据查询对象查找事项列表树")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<List<WorkItem>> findWorkItemListTree(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        List<WorkItem> workItemList = workItemService.findWorkItemListTree(workItemQuery);

        return Result.ok(workItemList);
    }

    /**
     * @pi.name:根据查询对象查找事项列表树
     * @pi.path:/workItem/findConditionWorkItemPage
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findConditionWorkItemPage",method = RequestMethod.POST)
    @ApiMethod(name = "根据查询对象查找事项列表页",desc = "根据查询对象查找事项列表页")
    @ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkItem>> findConditionWorkItemPage(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        Pagination<WorkItem> workItemList = workItemService.findConditionWorkItemPage(workItemQuery);

        return Result.ok(workItemList);
    }

    /**
     * @pi.name:根据查询对象按分页查找事项列表
     * @pi.path:/workItem/findWorkItemPage
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findWorkItemPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemPage",desc = "根据查询对象按分页查找事项列表")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkItem>> findWorkItemPage(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        Pagination<WorkItem> pagination = workItemService.findWorkItemPage(workItemQuery);

        return Result.ok(pagination);
    }


    /**
     * @pi.name:根据查询对象按分页查找事项列表树
     * @pi.path:/workItem/findWorkItemPageTree
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findWorkItemPageTree",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemPageTree",desc = "根据查询对象按分页查找事项列表树")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkItem>> findWorkItemPageTree(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        Pagination<WorkItem> pagination = workItemService.findWorkItemPageTree(workItemQuery);

        return Result.ok(pagination);
    }

    /**
     * @pi.name:根据条件查找事项列表树
     * @pi.path:/workItem/findWorkItemPageTreeByQuery
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findWorkItemPageTreeByQuery",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemPageTreeByQuery",desc = "根据条件查找事项列表树")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkItem>> findWorkItemPageTreeByQuery(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        long aTime1 = System.currentTimeMillis();
        Pagination<WorkItem> workItemList = workItemService.findWorkItemPageTreeByQuery(workItemQuery);
        long bTime1 = System.currentTimeMillis();

        logger.info("joinQuery cost time:{}",bTime1-aTime1);


        return Result.ok(workItemList);
    }

    /**
     * @pi.name:根据查询对象查找事项看板列表
     * @pi.path:/workItem/findWorkBoardList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findWorkBoardList",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkBoardList",desc = "根据查询对象查找事项看板列表")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<List<WorkBoard>> findWorkBoardList(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        List<WorkBoard> workBoardList = workItemService.findWorkBoardList(workItemQuery);

        return Result.ok(workBoardList);
    }

    /**
     * @pi.name:根据查询对象查找事项看板列表
     * @pi.path:/workItem/findChangePageWorkBoardList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findChangePageWorkBoardList",method = RequestMethod.POST)
    //@ApiMethod(name = "findChangePageWorkBoardList",desc = "根据查询对象查找事项看板列表")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<WorkBoard> findChangePageWorkBoardList(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        WorkBoard changePageWorkBoardList = workItemService.findChangePageWorkBoardList(workItemQuery);

        return Result.ok(changePageWorkBoardList);
    }

    /**
     * @pi.name:根据查询对象查找事项看板列表
     * @pi.path:/workItem/findWorkUserGroupBoardList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findWorkUserGroupBoardList",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkUserGroupBoardList",desc = "根据查询对象查找事项看板列表")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<List<WorkUserGroupBoard>> findWorkUserGroupBoardList(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        List<WorkUserGroupBoard> workUserGroupBoardList = workItemService.findWorkUserGroupBoardList(workItemQuery);

        return Result.ok(workUserGroupBoardList);
    }


//    @RequestMapping(path = "/findWorkBoard",method = RequestMethod.POST)
//    //@ApiMethod(name = "findWorkBoard",desc = "根据查询对象查找事项看板")
//    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
//    public Result<WorkBoard> findWorkBoard(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
//        WorkBoard workBoard = workItemService.findWorkBoard(workItemQuery);
//
//        return Result.ok(workBoard);
//    }

    /**
     * @pi.name:根据查询对象按分页查找未被史诗关联的事项列表
     * @pi.path:/workItem/findUnEpicWorkItemPage
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findUnEpicWorkItemPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findUnEpicWorkItemPage",desc = "根据查询对象按分页查找未被史诗关联的事项列表")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkItem>> findUnEpicWorkItemPage(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        Pagination<WorkItem> pagination = workItemService.findUnEpicWorkItemPage(workItemQuery);

        return Result.ok(pagination);
    }

    /**
     * @pi.name:根据查询对象按分页查找未被计划关联的事项列表
     * @pi.path:/workItem/findUnPlanWorkItemPage
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findUnPlanWorkItemPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findUnPlanWorkItemPage",desc = "根据查询对象按分页查找事项列表")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkItem>> findUnPlanWorkItemPage(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        Pagination<WorkItem> pagination = workItemService.findUnPlanWorkItemPage(workItemQuery);

        return Result.ok(pagination);
    }

    /**
     * @pi.name:根据名称查找事项列表
     * @pi.path:/workItem/findWorkItemByKeyWorks
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findWorkItemByKeyWorks",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemByKeyWorks",desc = "根据查询对象按分页查找事项列表")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkItem>> findWorkItemByKeyWorks(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        Pagination<WorkItem> pagination = workItemService.findWorkItemByKeyWorks(workItemQuery);

        return Result.ok(pagination);
    }

    /**
     * @pi.name:按照分类查找各个事项类型的事项数量
     * @pi.path:/workItem/findWorkItemNumByWorkType
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findWorkItemNumByWorkType",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemNumByWorkType",desc = "根据查询对象按分页查找事项列表")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkItem>> findWorkItemNumByWorkType(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        HashMap<String, Integer> workItemNumByWorkType = workItemService.findWorkItemNumByWorkType(workItemQuery);

        return Result.ok(workItemNumByWorkType);
    }

    @RequestMapping(path = "/findWorkItemListNumByWorkType",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemListNumByWorkType",desc = "根据查询对象按分页查找事项列表")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkItem>> findWorkItemListNumByWorkType(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        HashMap<String, Integer> workItemNumByWorkType = workItemService.findWorkItemListNumByWorkType(workItemQuery);

        return Result.ok(workItemNumByWorkType);
    }

    /**
     * @pi.name:查找各个事项状态的事项数量
     * @pi.path:/workItem/findWorkItemNumByWorkStatus
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findWorkItemNumByWorkStatus",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemNumByWorkStatus",desc = "根据查询事项状态查找事项数量")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<HashMap<String, Integer>> findWorkItemNumByWorkStatus(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        HashMap<String, Integer> workItemNumByWorkType = workItemService.findWorkItemNumByWorkStatus(workItemQuery);

        return Result.ok(workItemNumByWorkType);
    }

    /**
     * @pi.name:按照快速搜索条件查找各个条件下事项的数量
     * @pi.path:/workItem/findWorkItemNumByQuickSearch
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findWorkItemNumByQuickSearch",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemNumByQuickSearch",desc = "根据快速筛选查询事项状态查找事项数量")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<HashMap<String, Integer>> findWorkItemNumByQuickSearch(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        HashMap<String, Integer> workItemNumByWorkType = workItemService.findWorkItemNumByQuickSearch(workItemQuery);

        return Result.ok(workItemNumByWorkType);
    }

    /**
     * @pi.name:根据查询事项状态查找可被关联的上级事项
     * @pi.path:/workItem/findCanBeRelationParentWorkItemList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findCanBeRelationParentWorkItemList",method = RequestMethod.POST)
    //@ApiMethod(name = "findCanBeRelationParentWorkItemList",desc = "根据查询事项状态查找可被关联的上级事项")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkItem>> findCanBeRelationParentWorkItemList(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        Pagination<WorkItem> canBeRelationParentWorkItemList = workItemService.findCanBeRelationParentWorkItemList(workItemQuery);

        return Result.ok(canBeRelationParentWorkItemList);
    }

    /**
     * @pi.name:根据查询事项状态查找可被关联的前置事项
     * @pi.path:/workItem/findCanBeRelationPerWorkItemList
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findCanBeRelationPerWorkItemList",method = RequestMethod.POST)
    //@ApiMethod(name = "findCanBeRelationPerWorkItemList",desc = "根据查询事项状态查找可被关联的前置事项")
    //@ApiParam(name = "workItemQuery",desc = "查询对象",required = true)
    public Result<Pagination<WorkItem>> findCanBeRelationPerWorkItemList(@RequestBody @Valid @NotNull WorkItemQuery workItemQuery){
        Pagination<WorkItem> canBeRelationPerWorkItemList = workItemService.findCanBeRelationPerWorkItemList(workItemQuery);

        return Result.ok(canBeRelationPerWorkItemList);
    }

    /**
     * @pi.name:根据查询事项关联各个模型的数量，ex: 关联事项，下级事项， 日志， 文档，动态等
     * @pi.path:/workItem/findWorkItemRelationModelCount
     * @pi.methodType:post
     * @pi.request-type:json
     * @pi.param: model=io.tiklab.kanass.workitem.model.WorkItemQuery
     */
    @RequestMapping(path = "/findWorkItemRelationModelCount",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemRelationModelCount",desc = "根据查询事项状态查找可被关联的事项")
    public Result<HashMap<String, Integer>> findWorkItemRelationModelCount(@NotNull String workItemId, @NotNull String workTypeCode){
        HashMap<String, Integer> workItemRelationModelCount = workItemService.findWorkItemRelationModelCount(workItemId, workTypeCode);

        return Result.ok(workItemRelationModelCount);
    }

    @RequestMapping(path = "/updateBatchWorkItemSprint",method = RequestMethod.POST)
    //@ApiMethod(name = "updateBatchWorkItemSprint",desc = "批量更新事项迭代")
    public Result<Void> updateBatchWorkItemSprint(@NotNull String oldSprintId, String newSprintId){
        workItemService.updateBatchWorkItemSprint(oldSprintId, newSprintId);

        return Result.ok();
    }

    @RequestMapping(path = "/findSprintWorkItemNum",method = RequestMethod.POST)
    //@ApiMethod(name = "findSprintWorkItemNum",desc = "查找迭代下不同状态的事项个数")
    public Result<Void> findSprintWorkItemNum(@NotNull String sprintId){
        HashMap<String, Integer> sprintWorkItemNum = workItemService.findSprintWorkItemNum(sprintId);

        return Result.ok(sprintWorkItemNum);
    }

    @RequestMapping(path = "/findChildrenLevel",method = RequestMethod.POST)
    //@ApiMethod(name = "findChildrenLevel",desc = "查看事项有几级下级事项")
    public Result<Void> findChildrenLevel(@NotNull String id){
        Integer childrenLevel = workItemService.findChildrenLevel(id);

        return Result.ok(childrenLevel);
    }
    @RequestMapping(path = "/findWorkItemAndChidren",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemAndChidren",desc = "查看事项有几级下级事项")
    public Result<WorkItem> findWorkItemAndChidren(@NotNull String id){
        WorkItem workItemAndChidren = workItemService.findWorkItemAndChidren(id);

        return Result.ok(workItemAndChidren);
    }

    @RequestMapping(path = "/haveChildren",method = RequestMethod.POST)
    //@ApiMethod(name = "haveChildren",desc = "查看事项有几级下级事项")
    public Result<Boolean> haveChildren(@NotNull String id){
        boolean isHave = workItemService.haveChildren(id);

        return Result.ok(isHave);
    }

    @RequestMapping(path = "/findWorkItemAndChildrenIds",method = RequestMethod.POST)
    //@ApiMethod(name = "findWorkItemAndChildrenIds",desc = "查看事项所有下级的id, 包括下级的下级，当前事项")
    public Result<List<String>> findWorkItemAndChildrenIds(@NotNull String id){
        List<String> workItemAndChildrenIds = workItemService.findWorkItemAndChildrenIds(id);

        return Result.ok(workItemAndChildrenIds);
    }

    @RequestMapping(path = "/findTodoWorkItemNum",method = RequestMethod.POST)
    public Result<Map<String, Integer>> findTodoWorkItemNum(WorkItemQuery workItemQuery){
//        Map<String, Integer> todoWorkItemNum = workItemService.findTodoWorkItemNum(userId);

        return Result.ok(Map.of());
    }

    /**
     * 查询我的待办和我的创建事项个数
     * @param workItemQuery
     * @return
     */
    @RequestMapping(path = "/findUserCreateAndTodoWorkNum",method = RequestMethod.POST)
    public Result<Map<String, Integer>> findUserCreateAndTodoWorkNum(@RequestBody @Valid WorkItemQuery workItemQuery){
        Map<String, Integer> map = workItemService.findUserCreateAndTodoWorkNum(workItemQuery);

        return Result.ok(map);
    }
}
