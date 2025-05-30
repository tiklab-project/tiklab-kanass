package io.tiklab.kanass.project.epic.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.kanass.project.epic.model.Epic;
import io.tiklab.kanass.project.epic.model.EpicQuery;
import io.tiklab.kanass.project.epic.service.EpicService;
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

/**
 * 史诗控制器
 */
@RestController
@RequestMapping("/epic")
//@Api(name = "EpicController",desc = "EpicController")
public class EpicController {

    private static Logger logger = LoggerFactory.getLogger(EpicController.class);

    @Autowired
    private EpicService epicService;

    @RequestMapping(path="/createEpic",method = RequestMethod.POST)
    //@ApiMethod(name = "createEpic",desc = "创建史诗")
    //@ApiParam(name = "epic",desc = "史诗模型",required = true)
    public Result<String> createEpic(@RequestBody @NotNull @Valid Epic epic){
        String id = epicService.createEpic(epic);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateEpic",method = RequestMethod.POST)
    //@ApiMethod(name = "updateEpic",desc = "更新史诗")
    //@ApiParam(name = "epic",desc = "史诗模型",required = true)
    public Result<Void> updateEpic(@RequestBody @NotNull @Valid Epic epic){
        epicService.updateEpic(epic);

        return Result.ok();
    }

    @RequestMapping(path="/deleteEpic",method = RequestMethod.POST)
    //@ApiMethod(name = "deleteEpic",desc = "删除史诗")
    //@ApiParam(name = "id",desc = "史诗id",required = true)
    public Result<Void> deleteEpic(@NotNull String id){
        epicService.deleteEpic(id);

        return Result.ok();
    }

    @RequestMapping(path="/findEpic",method = RequestMethod.POST)
    //@ApiMethod(name = "findEpic",desc = "根据id 查找史诗")
    //@ApiParam(name = "id",desc = "史诗id",required = true)
    public Result<Epic> findEpic(@NotNull String id){
        Epic epic = epicService.findEpic(id);

        return Result.ok(epic);
    }

    @RequestMapping(path="/findAllEpic",method = RequestMethod.POST)
    //@ApiMethod(name = "findAllEpic",desc = "查找全部史诗")
    public Result<List<Epic>> findAllEpic(){
        List<Epic> epicList = epicService.findAllEpic();

        return Result.ok(epicList);
    }

    @RequestMapping(path = "/findEpicList",method = RequestMethod.POST)
    //@ApiMethod(name = "findEpicList",desc = "根据搜索条件查找史诗列表")
    //@ApiParam(name = "epicQuery",desc = "史诗搜索条件模型",required = true)
    public Result<List<Epic>> findEpicList(@RequestBody @Valid @NotNull EpicQuery epicQuery){
        List<Epic> epicList = epicService.findEpicList(epicQuery);

        return Result.ok(epicList);
    }

    @RequestMapping(path = "/findEpicListTree",method = RequestMethod.POST)
    //@ApiMethod(name = "findEpicListTree",desc = "根据搜索条件按分页查找史诗树形列表")
    //@ApiParam(name = "epicQuery",desc = "根据搜索条件按分页查找史诗列表",required = true)
    public Result<List<Epic>> findEpicListTree(@RequestBody @Valid @NotNull EpicQuery epicQuery){
        List<Epic> epicList = epicService.findEpicListTree(epicQuery);

        return Result.ok(epicList);
    }

    @RequestMapping(path = "/findEpicPage",method = RequestMethod.POST)
    //@ApiMethod(name = "findEpicPage",desc = "根据搜索条件按分页查找史诗列表")
    //@ApiParam(name = "epicQuery",desc = "根据搜索条件按分页查找史诗列表",required = true)
    public Result<Pagination<Epic>> findEpicPage(@RequestBody @Valid @NotNull EpicQuery epicQuery){
        Pagination<Epic> pagination = epicService.findEpicPage(epicQuery);

        return Result.ok(pagination);
    }

}
