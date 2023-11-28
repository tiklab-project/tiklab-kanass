package io.tiklab.teamwire.project.epic.controller;

import io.tiklab.teamwire.workitem.model.WorkItem;
import io.tiklab.postin.annotation.Api;
import io.tiklab.teamwire.project.epic.model.EpicWorkItem;
import io.tiklab.teamwire.project.epic.model.EpicWorkItemQuery;
import io.tiklab.teamwire.project.epic.service.EpicWorkItemService;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 史诗关联事项控制器
 */
@RestController
@RequestMapping("/epicWorkItem")
@Api(name = "EpicWorkItemController",desc = "EpicWorkItemController")
public class EpicWorkItemController {

    private static Logger logger = LoggerFactory.getLogger(EpicWorkItemController.class);

    @Autowired
    private EpicWorkItemService epicWorkItemService;

    @RequestMapping(path="/createEpicWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "createEpicWorkItem",desc = "创建史诗事项关联")
    @ApiParam(name = "epicWorkItem",desc = "史诗关联事项模型",required = true)
    public Result<String> createEpicWorkItem(@RequestBody @NotNull @Valid EpicWorkItem epicWorkItem){
        String id = epicWorkItemService.createEpicWorkItem(epicWorkItem);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateEpicWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "updateEpicWorkItem",desc = "更新关联史诗事项关联关系")
    @ApiParam(name = "epicWorkItem",desc = "史诗关联事项模型",required = true)
    public Result<Void> updateEpicWorkItem(@RequestBody @NotNull @Valid EpicWorkItem epicWorkItem){
        epicWorkItemService.updateEpicWorkItem(epicWorkItem);

        return Result.ok();
    }

    @RequestMapping(path="/deleteEpicWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "deleteEpicWorkItem",desc = "删除事项史诗关联")
    @ApiParam(name = "id",desc = "史诗id",required = true)
    public Result<Void> deleteEpicWorkItem(@NotNull String id){
        epicWorkItemService.deleteEpicWorkItem(id);

        return Result.ok();
    }

    @RequestMapping(path="/deleteEpicWorkItemCondition",method = RequestMethod.POST)
    @ApiMethod(name = "deleteEpicWorkItemCondition",desc = "根据条件删除史诗")
    @ApiParam(name = "epicWorkItem",desc = "删除条件",required = true)
    public Result<Void> deleteEpicWorkItemCondition(@RequestBody @NotNull EpicWorkItem epicWorkItem){
        epicWorkItemService.deleteEpicWorkItem(epicWorkItem);

        return Result.ok();
    }

    @RequestMapping(path="/findEpicWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "findEpicWorkItem",desc = "根据id查找史诗下事项列表")
    @ApiParam(name = "id",desc = "史诗id",required = true)
    public Result<EpicWorkItem> findEpicWorkItem(@NotNull String id){
        EpicWorkItem epicWorkItem = epicWorkItemService.findEpicWorkItem(id);

        return Result.ok(epicWorkItem);
    }

    @RequestMapping(path="/findAllEpicWorkItem",method = RequestMethod.POST)
    @ApiMethod(name = "findAllEpicWorkItem",desc = "查找所有史诗关联的事项")
    public Result<List<EpicWorkItem>> findAllEpicWorkItem(){
        List<EpicWorkItem> epicWorkItemList = epicWorkItemService.findAllEpicWorkItem();

        return Result.ok(epicWorkItemList);
    }

    @RequestMapping(path = "/findEpicWorkItemList",method = RequestMethod.POST)
    @ApiMethod(name = "findEpicWorkItemList",desc = "根据条件查询史诗关联的事项列表")
    @ApiParam(name = "epicWorkItemQuery",desc = "史诗事项搜索模型",required = true)
    public Result<List<EpicWorkItem>> findEpicWorkItemList(@RequestBody @Valid @NotNull EpicWorkItemQuery epicWorkItemQuery){
        List<EpicWorkItem> epicWorkItemList = epicWorkItemService.findEpicWorkItemList(epicWorkItemQuery);

        return Result.ok(epicWorkItemList);
    }

    @RequestMapping(path = "/findWorkItemListByEpic",method = RequestMethod.POST)
    @ApiMethod(name = "findWorkItemListByEpic",desc = "根据条件查询史诗关联的事项列表")
    @ApiParam(name = "epicWorkItemQuery",desc = "史诗事项搜索模型",required = true)
    public Result<List<WorkItem>> findWorkItemListByEpic(@RequestBody @Valid @NotNull EpicWorkItemQuery epicWorkItemQuery){
        List<WorkItem> workItemList = epicWorkItemService.findWorkItemListByEpic(epicWorkItemQuery);

        return Result.ok(workItemList);
    }

    @RequestMapping(path = "/findEpicWorkItemPage",method = RequestMethod.POST)
    @ApiMethod(name = "findEpicWorkItemPage",desc = "根据条件按分页查询史诗关联的事项列表")
    @ApiParam(name = "epicWorkItemQuery",desc = "史诗事项搜索模型",required = true)
    public Result<Pagination<EpicWorkItem>> findEpicWorkItemPage(@RequestBody @Valid @NotNull EpicWorkItemQuery epicWorkItemQuery){
        Pagination<EpicWorkItem> pagination = epicWorkItemService.findEpicWorkItemPage(epicWorkItemQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findEpicChildWorkItemAndEpic",method = RequestMethod.POST)
    @ApiMethod(name = "findEpicChildWorkItemAndEpic",desc = "查询需求集下面的子需求集和事项")
    @ApiParam(name = "epicWorkItemQuery",desc = "史诗事项搜索模型",required = true)
    public Result<Map<String, Object>> findEpicChildWorkItemAndEpic(@RequestBody @Valid @NotNull EpicWorkItemQuery epicWorkItemQuery){
        Map<String, Object> workItemAndEpic = epicWorkItemService.findEpicChildWorkItemAndEpic(epicWorkItemQuery);

        return Result.ok(workItemAndEpic);
    }

}
